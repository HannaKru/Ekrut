package controller;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;

import db.mysqlConnection;
import ocsf.server.*;
import gui.ServerPortFrameController;
import gui.ServerUI;
import db.ShowSubscriber;
import db.UpdateDB;
import db.Query;
import Entities.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Aviram Fishman
 * @author Hanna Kruchenetzky
 * @author Nofar Oshri Bensimon
 * @author Asaf Schneiderman
 * @author marina.shteinfer
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;
	public static int clientNumber = 1;
	private Message resMessage;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	@SuppressWarnings("unchecked")
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		if (msg instanceof Message) {
			resMessage = (Message) msg;

			// System.out.println("Message: "+ resMessage.getMessageData().toString() +"
			// --"+ resMessage.getMessageType().toString());
			switch (resMessage.getMessageType()) { // message - type
			case login: // the user login to the system and change him to '1' in the DB.
				String[] data = resMessage.getMessageData().toString().split("#");
				try {
					
					client.sendToClient(new Message(MessageType.login, (Object) (Query.login(data[0], data[1]))));

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case logout: // the user logout from the system and change him to '0' in the DB.
				Query.logout(resMessage.getMessageData().toString());
			case disconnected: // disconnected from server
				ServerUI.serverGUI.appendToConsole(client.getName() + " has disconnected");
				break;
			case connected: // connected to server
				client.setName("client #" + clientNumber + " (" + resMessage.getMessageData().toString() + ") ");
				clientNumber++;
				ServerUI.serverGUI.appendToConsole(client.getName() + " connected successfully");
				break;
			case update_thresholdLevel: //update the threshold level of the vending machines in the DB
					Query.UpdateVendingMachineThresholdLevel((ArrayList<VendingMachine>)resMessage.getMessageData());
				break;
			case Get_vendingMachines: //get list of vending machines from DB
				
				try {
					client.sendToClient(new Message(MessageType.Get_vendingMachines, (Object) (Query.getVendingMachines())));
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			case Get_reports:
				try {

					client.sendToClient(new Message(MessageType.Get_reports, (Object) (Query.getReports())));

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			} // end of case
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		// mysqlConnection.connectionDb(); //connecting to server.
		ServerUI.serverGUI.appendToConsole("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		ServerUI.serverGUI.appendToConsole("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */
}
//End of EchoServer class

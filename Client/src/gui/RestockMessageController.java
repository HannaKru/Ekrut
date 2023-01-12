package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import Entities.VendingMachine;
import controller.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class RestockMessageController implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private TableView<VendingMachine> tblViewVendingMachines;

	@FXML
	private TableColumn<VendingMachine, String> regionCol;

	@FXML
	private TableColumn<VendingMachine, String> locationCol;

	@FXML
	private TableColumn<VendingMachine, String> thresholdLevelCol;

	@FXML
	private TableColumn<VendingMachine, String> statusCol;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnUpdateRestockStatus;

	@FXML
	private Label lblAlert;

	private static Message msg; // message to send to server

	static ArrayList<VendingMachine> vendingMachines = new ArrayList<>(); // list of vending machines in the DB

	/**
	 * Goes back to the previous window of RegionManagerFrameController
	 * 
	 * @param event (Click on Back button)
	 */
	@FXML
	void backToPreviousPage(ActionEvent event) {

		RegionManagerFrameController RegionManagerController = new RegionManagerFrameController();
		try {
			RegionManagerController.start(ClientMenuController.clientStage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * update the status of the vending machines in the DB
	 * 
	 * @param event (Click on Update Status button)
	 */
	@FXML
	void updateRestockStatus(ActionEvent event) {
		// update the status changes
		for (VendingMachine row : vendingMachines)
			if (row.getRegion().equals(LoginFrameController.user.getRegion())) 	//update the vending machines at his region
				row.comboBoxUpdateStatus();

		lblAlert.setText("A restock message sent to the worker"); // show update Alert
		lblAlert.setStyle("-fx-background-color:white");
		msg = new Message(MessageType.update_restockStatus, vendingMachines);
		ClientMenuController.clientControl.accept(msg);
	}

	/**
	 * start the RestockMessageFrame
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void start(Stage primaryStage) throws IOException {
		ClientMenuController.clientStage = primaryStage;
		primaryStage.setTitle("Ekrut - Region Manager >> Restock Messages");
		Parent root = FXMLLoader.load(getClass().getResource("/gui/RestockMessage.fxml"));
		Scene home = new Scene(root);
		primaryStage.setScene(home);

		// On pressing X (close window) the user logout from system and the client is
		// disconnect from server.
		primaryStage.setOnCloseRequest(e -> {
			msg = new Message(MessageType.logout, LoginFrameController.user.getUserName());
			ClientMenuController.clientControl.accept(msg);
		});
		primaryStage.show();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// initialize the background image
		BackgroundSize backgroundSize = new BackgroundSize(pane.getPrefWidth(), pane.getPrefHeight(), true, true, true,
				false);
		BackgroundImage image = new BackgroundImage(new Image("images/RestockMessageFrame.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
		pane.setBackground(new Background(image));

		// initialize the vending machines table from DB
		tblViewVendingMachines.setEditable(true);

		regionCol.setCellValueFactory(new PropertyValueFactory<VendingMachine, String>("region"));
		locationCol.setCellValueFactory(new PropertyValueFactory<VendingMachine, String>("location"));
		thresholdLevelCol.setCellValueFactory(new PropertyValueFactory<VendingMachine, String>("thresholdLevel"));
		statusCol.setCellValueFactory(new PropertyValueFactory<VendingMachine, String>("cmbBoxStatus")); // ENUM('WaitToRestock',
																											// 'Done')

		ObservableList<VendingMachine> tvObservableList = FXCollections.observableArrayList();
		vendingMachines = (ArrayList<VendingMachine>) ChatClient.msgServer.getMessageData();
		for (VendingMachine row : vendingMachines) {
			if (row.getRegion().equals(LoginFrameController.user.getRegion())) {	//show the vending machines at his region
				// row.comboBoxInitialize(FXCollections.observableArrayList("LowStock","WaitToRestock",
				// "Done")); //initialize the comboBox
				if (row.getRestockStatus().equals("LowStock")) // can change the status from 'LowStock' to
																// 'WaitToRestock'
					row.comboBoxInitialize(FXCollections.observableArrayList("WaitToRestock")); // initialize the
																								// comboBox
				else
					row.comboBoxInitialize(FXCollections.observableArrayList(row.getRestockStatus())); // initialize the
																										// comboBox to
																										// the
																										// RestockStatus
				tvObservableList.add(row);
			}
		}

		tblViewVendingMachines.setItems(tvObservableList);

	}

}
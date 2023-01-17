package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * frame for when the order process is complete shows order number
 * 
 * @author Marina
 *
 */
public class FinishOrderFrameController implements Initializable {

	public static Message msg;

	@FXML
	private AnchorPane pane;

	@FXML
	private Label lblOrderID;

	@FXML
	private Button btnClose;
	@FXML
	private Label lblWelcome;

	@FXML
	private ImageView imgPic;

	/*
	 * closing order back to login frame
	 * 
	 * @param event click on close button
	 */
	@FXML
	void close(ActionEvent event) {

		ClientMenuController.clientStage.setScene(LoginFrameController.home);
		// Logout
		msg = new Message(MessageType.logout, LoginFrameController.user.getUserName());
		ClientMenuController.clientControl.accept(msg);

	}

	/**
	 * start the finishOrderFrame
	 * 
	 * @param primaryStage
	 * @throws IOException
	 */
	public void start(Stage customerStage) throws IOException {
		ClientMenuController.clientStage = customerStage;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/FinishOrderFrame.fxml"));
		ClientMenuController.clientStage.setTitle("Ekrut - Costumer >> End Of Order");
		Scene home = new Scene(root);
		customerStage.setScene(home);
		// On pressing X (close window) the user logout from system and the client is
		// disconnect from server.
		customerStage.setOnCloseRequest(e -> {
			msg = new Message(MessageType.logout, LoginFrameController.user.getUserName());
			ClientMenuController.clientControl.accept(msg);
			ClientMenuController.clientControl
					.accept(new Message(MessageType.disconnected, LoginFrameController.user.getUserName()));
		});

		customerStage.show();
	}

	/**
	 * 
	 * 
	 * initialize parameters when the frame start
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// initialize the background image and pic
		BackgroundSize backgroundSize = new BackgroundSize(pane.getPrefWidth(), pane.getPrefHeight(), true, true, true,
				false);
		BackgroundImage image = new BackgroundImage(new Image("images/FinishBackgroundFrame.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
		pane.setBackground(new Background(image));
		lblWelcome.setText(
				"Welcome " + LoginFrameController.user.getFirstName() + " " + LoginFrameController.user.getLastName());
		Image Icone = new Image("images/foodDelivery.png");
		imgPic.setImage(Icone);
		imgPic.setFitWidth(80);
		imgPic.setFitHeight(80);
		lblOrderID.setText(String.valueOf(ConfirmOrderFrameController.order.getOrderNum()));

	}

}

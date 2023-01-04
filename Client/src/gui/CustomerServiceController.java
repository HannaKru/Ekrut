package gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Entities.Message;
import Entities.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

public class CustomerServiceController {
	public static CustomerServiceController customerService;
	public static EmployeeRegistrationController employeeRegistration;
	public static CustomerRegistrationController coustomerRegistration;
	public static LoginFrameController loginFrame;
	public static RegistrateClubMemberController registrateClubMember;

	@FXML
    private AnchorPane pane;
	
	@FXML
	private Label customerServicelbl;

	@FXML
	private Button registerCustomerBtn;

	@FXML
	private Button registerEmployeeBtn;

	@FXML
	private Button logoutCustomerServiceBtn;
	
	@FXML
    private Button registerClubMemberBtn;

	
	@FXML
	void clickRegisterNewCustomer(ActionEvent event) throws IOException{
		coustomerRegistration= new CustomerRegistrationController();
    	try {
    		coustomerRegistration.start(ClientMenuController.clientStage);
	} catch (IOException e) {
		
		e.printStackTrace();
	    } //send to UI*/
    }

	    @FXML
	    void clickRegisterNewEmployee(ActionEvent event) {
	    	employeeRegistration= new EmployeeRegistrationController();
	    	try {
	    		employeeRegistration.start(ClientMenuController.clientStage);
		} catch (IOException e) {
			
			e.printStackTrace();
		    } //send to UI*/
	    }
	    
	    @FXML
	    void clickOnLogout(ActionEvent event) {
	    	ClientMenuController.clientControl.accept(new Message(MessageType.disconnected,""));
	    	loginFrame= new LoginFrameController();
	    	try {
	    		loginFrame.start(ClientMenuController.clientStage);
		} catch (IOException e) {
			
			e.printStackTrace();
		    } //send to UI*/
	    }
	    
	    @FXML
	    void clickRegisterClubMember(ActionEvent event) {
	    	registrateClubMember= new RegistrateClubMemberController();
	    	try {
	    		registrateClubMember.start(ClientMenuController.clientStage);
		} catch (IOException e) {
			
			e.printStackTrace();
		    } //send to UI*/	
	    }


		public void start(Stage primaryStage) throws IOException {
			ClientMenuController.clientStage = primaryStage;
		    primaryStage.setTitle("Ekrut - Customer");
			Parent root = FXMLLoader.load(getClass().getResource("/gui/CustomerServiceFrame.fxml"));
			Scene home = new Scene(root);
			primaryStage.setScene(home);
			// On pressing X (close window) the client is disconnect from server.
			primaryStage.setOnCloseRequest(e -> {
				ClientMenuController.clientControl.accept(new Message(MessageType.disconnected,""));
			});
			primaryStage.show(); 
			
		}
		public void initialize(URL location, ResourceBundle resources) {

			// initialize the background image
			BackgroundSize backgroundSize = new BackgroundSize(pane.getPrefWidth(), pane.getPrefHeight(), true, true, true,
					false);
			BackgroundImage image = new BackgroundImage(new Image("images/LoginFrame.png"), BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
			pane.setBackground(new Background(image));
		}
	 

	

}

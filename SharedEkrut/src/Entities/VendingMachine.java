package Entities;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

//import javafx.scene.control.ComboBox;

public class VendingMachine implements Serializable {

	private static final long serialVersionUID = 1L;
	private String region;
	private String location;
	private String thresholdLevel;
	private String restockStatus;
	private Button btnRestock;

	// private ComboBox<String> cmbBoxStatus; ////////////////////////

	/**
	 * @param region         - the region of the vending machine
	 * @param location       - the location of the vending machine
	 * @param thresholdLevel - the threshold Level of the vending machine
	 * @param restockStatus  - 'WaitToRestock' or 'Done', comboBox
	 *                       of('WaitToRestock', 'Done')
	 */
	public VendingMachine(String region, String location, String thresholdLevel, String restockStatus) {
		super();
		this.region = region;
		this.location = location;
		this.thresholdLevel = thresholdLevel;
		this.restockStatus = restockStatus;
	}

	public String getRegion() {
		return region;
	}

	public String getLocation() {
		return location;
	}

	public String getThresholdLevel() {
		return thresholdLevel;
	}

	public String getRestockStatus() {
		return restockStatus;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setThresholdLevel(String thresholdLevel) {
		this.thresholdLevel = thresholdLevel;
	}

	public void setRestockStatus(String restockStatus) {
		this.restockStatus = restockStatus;
	}

	public Button getBtnRestock() {
		return btnRestock;
	}

	public void setBtnRestock(Button btnRestock) {
		this.btnRestock = btnRestock;
	}

	/**
	 * Initialize the Restock Button
	 * 
	 */
	public void buttonInitialize() {
		this.btnRestock = new Button("Restock");
	}
	/**
	 * close the buttons
	 */
	public void buttonClose() {
		this.btnRestock = null;
	}

//////////////////////////////////

//	public ComboBox<String> getCmbBoxStatus() {
//		return cmbBoxStatus;
//	}
//	public void setCmbBoxStatus(ComboBox<String> cmbBoxStatus) {
//		this.cmbBoxStatus = cmbBoxStatus;
//	}

	/**
	 * Initialize the comboBox with the statusData list
	 * 
	 * @param statusData - ("LowStock","WaitToRestock", "Done")
	 */
//	public void comboBoxInitialize(ObservableList<String> statusData) {
//		cmbBoxStatus = new ComboBox<String>(statusData);
//		cmbBoxStatus.setValue(restockStatus);
//	}

	/**
	 * update the restockStatus from the comboBox selected options
	 */
//	public void comboBoxUpdateStatus() {
//		this.restockStatus = cmbBoxStatus.getValue();
//		cmbBoxStatus = null;
//	}

}

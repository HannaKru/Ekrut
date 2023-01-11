package Entities;
import java.io.Serializable;
import java.util.ArrayList;
public class Order implements Serializable{
	private static final long serialVersionUID = -8005643298429981017L;
	private int orderNum;
	private String orderDate, orderStatus, customerID, vendingMachineLocation,orderType;
	private float totalPrice;
	private String products;
	private int quantityOfProducts;
	int [] quantityPerProducts;
	
	public Order (String vendingMachineLocation,String orderDate,String orderStatus,String customerID,float totalPrice, String orderType, int quantityOfProducts) {
		this.vendingMachineLocation=vendingMachineLocation;
		this.orderDate=orderDate;
		this.orderStatus=orderStatus;
		this.customerID=customerID;
		this.totalPrice=totalPrice;
		this.orderType=orderType;
		this.quantityOfProducts = quantityOfProducts;
	}
	public Order(int orderNum,String vendingMachineLocation,String orderDate) {
		this.orderNum=orderNum;
		this.vendingMachineLocation=vendingMachineLocation;
		this.orderDate=orderDate;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public int getQuantityOfProducts() {
		return quantityOfProducts;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum=orderNum;
	}
	public void setQuantityOfProducts(int quantityOfProducts) {
		this.quantityOfProducts=quantityOfProducts;
	}
	public String getVendingMachineLocation() {
		return vendingMachineLocation;
	}
	public void setVendingMachineLocation(String vendingMachineLocation) {
		this.vendingMachineLocation=vendingMachineLocation;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate=orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus=orderStatus;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID=customerID;
	}
	
	
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice=totalPrice;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType=orderType;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products=products;
	}
	public int getQuantityPerProducts(int index) {
		return quantityPerProducts[index];
	}
	public void setQuantityPerProducts(int index, int quantity) {
		quantityPerProducts[index] = quantity;
	}
}
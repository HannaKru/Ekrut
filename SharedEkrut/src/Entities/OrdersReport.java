package Entities;

import java.util.List;

public class OrdersReport extends Report{
	private static final long serialVersionUID = 1983595595251314658L;
	
	private List<Order> orders;
	
	public OrdersReport(String month, String year,List<Order> orders) {
		super("Order", month, year);
		this.orders=orders;
	}
	
	public String toString() {
		return "month: "+this.getMonth()+", year: "+this.getYear()+ "\nOrders:\n" + orders;
	}
	
}

package Entities;

import java.io.Serializable;

import gui.LoginFrameController;

public class Report implements Serializable{
	
	private static final long serialVersionUID = 583542510006825068L;
	
	private String reportName;
	private ReportType reportType;
	private String month;
	private String year;
	
	public Report(ReportType reportType, String month, String year) {
		this.reportType = reportType;
		this.month = month;
		this.year = year;
		
	}
	
	public ReportType getReportType() {
		return reportType;
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}	
	public void buildReportName() {
		reportName = reportType.toString() + "_" + month + "_" + year + "_" + LoginFrameController.user.getRegion();
	}
	public String getReportName(){
		return reportName;
	}
	
}

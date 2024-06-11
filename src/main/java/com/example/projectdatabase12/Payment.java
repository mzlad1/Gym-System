
package com.example.projectdatabase12;
public class Payment {
	private int PTID;
	private double amount;
	private String date;
	private String GID;

	public Payment(int PTID, double amount, String date , String GID){
		this.PTID = PTID;
		this.amount = amount;
		this.date = date;
		this.GID = GID;
	}

	public Payment( double amount, String date, String GID){
		this.amount = amount;
		this.date = date;
		this.GID = GID;
	}

	public int getPTID(){
		return PTID;
	}

	public double getAmount(){
		return amount;
	}

	public String getDate(){
		return date;
	}

	public void setPTID(int PTID){
		this.PTID = PTID;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getGID() {
		return GID;
	}

	public void setGID(String GID) {
		this.GID = GID;
	}


}

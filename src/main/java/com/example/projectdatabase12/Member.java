package com.example.projectdatabase12;
public class Member {

	private String GID;
	private String name;
	private int age;
	private double weight;
	private double height;
	private String address;
	private String phoneNum;
	private boolean lockerOption;
	private String membershipEndDate;
	private String CID;
	private String NID;


	private String PID;
	private String WID;

	public Member(String GID, String name, int age, double weight, double height, String address, String phoneNum,
				  boolean lockerOption, String membershipEndDate, String CID, String NID, String PID, String WID) {
		super();
		this.GID = GID;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.address = address;
		this.phoneNum = phoneNum;
		this.lockerOption = lockerOption;
		this.membershipEndDate = membershipEndDate;
		this.CID = CID;
		this.NID = NID;
		this.PID = PID;
		this.WID = WID;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getNID() {
		return NID;
	}

	public void setNID(String nID) {
		NID = nID;
	}

	public String getGID() {
		return GID;
	}


	public void setGID(String gID) {
		GID = gID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isLockerOption() {
		return lockerOption;
	}

	public void setLockerOption(boolean lockerOption) {
		this.lockerOption = lockerOption;
	}

	public String getMembershipEndDate() {
		return membershipEndDate;
	}

	public void setMembershipEndDate(String membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}


	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getWID() {
		return WID;
	}

	public void setWID(String wID) {
		WID = wID;
	}



	@Override
	public String toString() {
		return "" + GID + "," + name + "," + address + "," + phoneNum + ","
				+ age + "," + weight + "," + height + "," + lockerOption
				+ "," + membershipEndDate + "";
	}
}




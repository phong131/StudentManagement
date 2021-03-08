package com.pn.trainning.dto;


public class StudentDTO {
	private int id;
	private String firstname;
	private String lastname;
	private String gender;
	private String birthday;
	private String address;
	
	public StudentDTO(int id, String firstname, String lastname, String gender, String birthday, String address) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this. gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public StudentDTO(String firstname, String lastname, String gender, String birthday, String address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public StudentDTO(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}

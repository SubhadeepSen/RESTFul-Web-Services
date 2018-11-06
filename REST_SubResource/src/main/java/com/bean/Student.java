package com.bean;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Student {
	private int studentId;
	private String firstName;
	private String lastName;
	private Map<Integer, Address> addresses = new HashMap<>();

	public Student() {

	}

	public Student(int studentId, String firstName, String lastName) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@XmlTransient
	public Map<Integer, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<Integer, Address> addresses) {
		this.addresses = addresses;
	}

}

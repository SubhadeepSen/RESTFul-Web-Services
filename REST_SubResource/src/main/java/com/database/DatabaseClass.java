package com.database;

import java.util.HashMap;
import java.util.Map;

import com.bean.Address;
import com.bean.Student;

public class DatabaseClass {

	private static Map<Integer, Student> students = new HashMap<>();

	public static Map<Integer, Student> getStudents() {
		Map<Integer,Address> address_1 = new HashMap<>();
		address_1.put(1, new Address(1, "Kolkata"));
		address_1.put(2, new Address(2, "Purulia"));
		Student student_1 = new Student(1, "Subhadeep", "Sen");
		student_1.setAddresses(address_1);
		students.put(1, student_1);
		
		Map<Integer,Address> address_2 = new HashMap<>();
		address_2.put(1, new Address(1, "India"));
		address_2.put(2, new Address(2, "West Bengal"));
		Student student_2 = new Student(2, "Sunny", "Sen");
		student_2.setAddresses(address_2);
		students.put(2, student_2);
		
		return students;
	}
}

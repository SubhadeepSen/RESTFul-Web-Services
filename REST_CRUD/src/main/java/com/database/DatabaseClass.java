package com.database;

import java.util.HashMap;
import java.util.Map;

import com.bean.Student;

public class DatabaseClass {

	private static Map<Integer, Student> students = new HashMap<>();

	public static Map<Integer, Student> getStudents() {
		students.put(1, new Student(1, "Subhadeep", "Sen"));
		students.put(2, new Student(2, "Sunny", "Sen"));
		return students;
	}
}

package com.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bean.Student;
import com.database.DatabaseClass;

@Path("crud")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class CRUDResource {

	private Map<Integer, Student> students = DatabaseClass.getStudents();
	
	@GET
	public List<Student> getStudents(){
		return new ArrayList<>(students.values());
	}
	
	@GET
	@Path("{studentId}")
	public Student getStudent(@PathParam("studentId") int studentId){
		return students.get(studentId);
	}
	
	@POST
	public Student addStudent(Student student){
		student.setStudentId(students.size() + 1);
		students.put(student.getStudentId(), student);
		return student;
	}
	
	@PUT
	public Student updateStudent(Student student){
		students.put(student.getStudentId(), student);
		return students.get(student.getStudentId());
	}
	
	@DELETE
	@Path("{studentId}")
	public Student removeStudent(@PathParam("studentId") int StudentId){
		return students.remove(StudentId);
	}
}

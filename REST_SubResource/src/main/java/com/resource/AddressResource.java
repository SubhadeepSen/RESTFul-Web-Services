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

import com.bean.Address;
import com.bean.Student;
import com.database.DatabaseClass;

@Path("/")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class AddressResource {
	private Map<Integer, Student> students = DatabaseClass.getStudents();
	
	@GET
	public List<Address> getAllAddresses(@PathParam("studentId") int studentId){
		Map<Integer, Address> addresses = students.get(studentId).getAddresses();
		return new ArrayList<>(addresses.values());
	}
	
	@GET
	@Path("/{addressId}")
	public Address getAddress(@PathParam("studentId") int studentId, @PathParam("addressId") int addressId){
		Map<Integer, Address> addresses = students.get(studentId).getAddresses();
		return addresses.get(addressId);
	}
	
	@POST
	public Address addAddress(@PathParam("studentId") int studentId, Address address){
		Map<Integer, Address> addresses = students.get(studentId).getAddresses();
		address.setId(addresses.size()+1);
		addresses.put(address.getId(), address);
		return address;
	}
	
	@PUT
	@Path("/{addressId}")
	public Address updateAddress(@PathParam("studentId") int studentId, @PathParam("addressId") int addressId, Address address){
		Map<Integer, Address> addresses = students.get(studentId).getAddresses();
		address.setId(addressId);
		addresses.put(addressId, address);
		return address;
	}
	
	@DELETE
	@Path("/{addressId}")
	public Address removeAddress(@PathParam("studentId") int studentId, @PathParam("addressId") int addressId){
		Map<Integer, Address> addresses = students.get(studentId).getAddresses();
		return addresses.remove(addressId);
	}
}

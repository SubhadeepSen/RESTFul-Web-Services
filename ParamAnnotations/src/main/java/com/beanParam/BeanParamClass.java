package com.beanParam;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class BeanParamClass {
	@PathParam("name") private String pathParam;
	@QueryParam("surname") private String surName;
	@QueryParam("age") private String age;

	public String getPathParam() {
		return pathParam;
	}

	public void setPathParam(String pathParam) {
		this.pathParam = pathParam;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}

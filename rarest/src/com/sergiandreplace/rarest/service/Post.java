package com.sergiandreplace.rarest.service;

import org.simpleframework.xml.Attribute;

public class Post {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Post clone() {
		Post other=new Post();
		other.setName(name);
		return other;
	}
}

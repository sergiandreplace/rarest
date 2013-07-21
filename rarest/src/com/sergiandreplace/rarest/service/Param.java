package com.sergiandreplace.rarest.service;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

import com.sergiandreplace.rarest.logger.Logger;

public class Param {

	private ParamType type;
	private String name;
	private String alias;
	private Boolean mandatory=true;
	private String value;

	private Logger logger;
	

	public Param() {}
	
	
	public Param(ParamType type, String name, String alias, boolean mandatory, String value) {
		super();
		this.type = type;
		this.name = name;
		this.alias = alias;
		this.mandatory = mandatory;
		this.value = value;
	}
	
	public Param(Param other) {
		this(other.getType(), other.getName(), other.getAlias(), other.isMandatory(), other.getValue());
	}

	public Param clone() { 
		return new Param(this);
	}
	
	

	public ParamType getType() {
		return type;
	}
	public void setType(ParamType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		if (alias==null) {
			return name;
		}else{
			return alias;
		}
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}
	public String getValue() {
		return value;
	}
	public Param setValue(String value) {
		this.value = value;
		return this;
	}
	
	public String toString() {
		return String.format("Param name=%1$s alias=%2$s type=%3$s mandatory=%4$s value=%5$s",name,getAlias(),type,mandatory,value);
	}

	public static Param merge(Param template, Param param) {
		Param result=template.clone();
		if (param.getAlias()!=null) {
			result.setAlias(param.getAlias());
		}
		if (param.isMandatory()!=null) {
			result.setMandatory(param.isMandatory());
		}
		if (param.getType()!=null) {
			result.setType(param.getType());
		}
		if (param.getValue()!=null) {
			result.setValue(param.getValue());
		}
		
		return result;
	}
	
	public void merge(Param param) {
		if (param.getAlias()!=null) {
			this.setAlias(param.getAlias());
		}
		if (param.isMandatory()!=null) {
			this.setMandatory(param.isMandatory());
		}
		if (param.getType()!=null) {
			this.setType(param.getType());
		}
		if (param.getValue()!=null) {
			this.setValue(param.getValue());
		}
		
	}
	public Logger getLogger()  {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger=logger;
		
	}
}

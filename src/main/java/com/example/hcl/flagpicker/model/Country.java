package com.example.hcl.flagpicker.model;

public class Country {

	public Country(String name, String flag) {
		this.name = name;
		this.flag = flag;
	}

	public Country() {
	}

	String name;
	String flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", flag=" + flag + "]";
	}

}

package com.example.hcl.flagpicker.model;

public class Role {

	private String roleName;
	private String description;
	
	public Role(String roleName, String description) {
		super();
		this.roleName = roleName;
		this.description = description;
	}
	
	
	public Role() {
		
	}


	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

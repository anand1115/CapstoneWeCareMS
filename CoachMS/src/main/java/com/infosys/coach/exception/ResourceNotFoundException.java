package com.infosys.coach.exception;


public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ResourceName;
	private String FieldName;
	private String FieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldValue));
		ResourceName = resourceName;
		FieldName = fieldName;
		FieldValue = fieldValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getResourceName() {
		return ResourceName;
	}
	public String getFieldName() {
		return FieldName;
	}
	public String getFieldValue() {
		return FieldValue;
	}
	
}


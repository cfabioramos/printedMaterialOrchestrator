package com.hdi.integration.orchDeliveryDocument.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "Error Enitity")
public class ObjectError implements Serializable {

	private static final long serialVersionUID = -7236760757579754892L;
	
	@ApiModelProperty(value = "Id of the technical exception")
	private Integer code = 0;	
	@ApiModelProperty(value = "Technical message")
	private String message = "";	
	@ApiModelProperty(value = "Native error message")
	private String nativeMessage = "";	
	@ApiModelProperty(value = "Track id of transaction process")
	private String trackid = "";
	
	public ObjectError() {
		
	}
	
	public ObjectError(String message) {
		this.message = message;
	}

	public ObjectError(Integer code, String message, String nativeMessage, String trackid) {
		this.code = code;
		this.message = message;
		this.nativeMessage = nativeMessage;
		this.trackid = trackid;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getNativeMessage() {
		return nativeMessage;
	}

	public void setNativeMessage(String nativeMessage) {
		this.nativeMessage = nativeMessage;
	}

	public String getTrackid() {
		return trackid;
	}

	public void setTrackid(String trackid) {
		this.trackid = trackid;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

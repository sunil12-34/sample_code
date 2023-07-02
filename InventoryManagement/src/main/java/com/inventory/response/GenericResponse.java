package com.inventory.response;

import java.util.Date;
import java.util.List;

import com.inventory.model.Item;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericResponse {
	
	private String status;
	private Date date = new Date();
	private List<Item> data;
	private Item item;
	
	public GenericResponse(String status, List<Item> data) {
		super();
		this.status = status;
		this.data = data;
	}
	public GenericResponse(String status) {
		super();
		this.status = status;
	}
	public GenericResponse(String status, Item item) {
		super();
		this.status = status;
		this.item = item;
	}
	
	
	
	
	
	
	

}

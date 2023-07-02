package com.inventory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.Item;
import com.inventory.response.GenericResponse;
import com.inventory.service.ItemService;

@RestController
@RequestMapping
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService service;
	
	@GetMapping("/all")
	public GenericResponse getAll(){
		
		List<Item> all = service.getAll();
		
		return new GenericResponse(HttpStatus.ACCEPTED.toString(), all);
		
	}
	
	@PostMapping("/add")
	public GenericResponse addItem(@RequestBody Item item) {
		logger.info("add api starting");
		Item addItem = service.addItem(item);
		List<Item> aman =  new ArrayList<>();
		aman.add(addItem);
		
		return new GenericResponse(HttpStatus.CREATED.toString(), aman);
	}
	
	@PutMapping("/update")
	public GenericResponse updateItem(@RequestBody Item item) {
		logger.info("update controller start");
		
		 Item updateItem = service.updateItem(item);
		 List<Item> xy =  new ArrayList<>();
			xy.add(updateItem);
		 
		 return new GenericResponse(HttpStatus.CREATED.toString(), xy);
	}
	
	@DeleteMapping("/delete")
	public GenericResponse deleteItem(@RequestBody Item item) {
		service.deleteItem(item);
		return new GenericResponse(HttpStatus.ACCEPTED.toString());
	}
	
	@GetMapping("/get/{id}")
	public GenericResponse getItem(@PathVariable Integer id) {
		Item item = service.getItem(id);
		return new GenericResponse(HttpStatus.ACCEPTED.toString(),item);
	}
	@GetMapping("/filter")
	public List<Item> getFilter(@RequestParam Double x,@RequestParam Double y){
		return service.priceRange(x, y);
	}
	@GetMapping("/sort")
    public List<Item> sortbynameItems(){
		
		logger.info("Sorting to be done");
		
    	return service.sortbynameItems();
    }
}

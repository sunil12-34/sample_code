package com.inventory.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.exception.GenericException;
import com.inventory.model.Item;
import com.inventory.repo.ItemRepo;

@Service
public class ItemService {
	
	private static final Logger logger=LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemRepo itemRepo;
	
	public List<Item> getAll(){
		
		List<Item> findAll = itemRepo.findAll();
		
		if(findAll.isEmpty()) {
			throw new GenericException("No items to display");
		}
		
		return findAll;
		
	}
	
	public Item addItem(Item item) {
		logger.debug("add service start");
		
		Item save = itemRepo.save(item);
		
		return save;
		
	}
	
	public Item updateItem(Item item) {
		
		Optional<Item> findById = itemRepo.findById(item.getId());
		logger.debug("find item by id");
		
		if(findById.isPresent()) {
			Item save = itemRepo.save(item);
			
			return save;
		}else {
			throw new GenericException("No item with this ID found");
		}
	}
	
	public void deleteItem(Item item) {
		
		Optional<Item> findById = itemRepo.findById(item.getId());
		   if(findById.isPresent()) {
			   itemRepo.delete(item);
		   }else {
			   throw new GenericException("no such item to delete");
		   }
		
	}
	
	public Item getItem(Integer id) {
		
		Item item = itemRepo.findById(id).orElseThrow(() -> new GenericException("Resource not found"));
		
		return item;
		
	}
	
	public List <Item> priceRange(Double x,Double y) {
		List<Item> allItems = getAll();
		List<Item> filteredItems = allItems.stream().filter(a -> a.getPrice() >= x && a.getPrice() <= y).collect(Collectors.toList());
		return filteredItems;
	}
	public List<Item>sortbynameItems(){
		logger.info("service is hit.....starting sorting");
		List<Item> allItems = getAll();
		logger.info("List got -> The list is => ");
		List<Item> sorted = allItems.stream().sorted((a,b) -> b.getPrice().compareTo(a.getPrice())).collect(Collectors.toList()); 
		
		logger.info("service complete");
				return sorted;
		
	}

}

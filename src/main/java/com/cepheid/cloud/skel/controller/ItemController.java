package com.cepheid.cloud.skel.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cepheid.cloud.skel.CustomException.BusinessException;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.service.ItemService;

import io.swagger.annotations.Api;

// curl http:/localhost:9443/app/api/1.0/items

@Component
@Path("/api/1.0/items")
@Api()
public class ItemController {

	private final ItemService mItemService;

	@Autowired
	public ItemController(ItemService itemService) {
		mItemService = itemService;
	}

	/** Get all Items from database **/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getAllItems() {
		try {
			List<Item> item = mItemService.getAllItems();
			return new ResponseEntity<List<Item>>(item, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<BusinessException>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			BusinessException be = new BusinessException(700,
					"Something went wrong in th controller layer while fetching the items from Database");
			return new ResponseEntity<BusinessException>(be, HttpStatus.BAD_REQUEST);
		}

	}

	/** Get single Item from Name property from database **/
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getItemByName(@PathParam("name") String name) {
		try {
			Item singleItem = mItemService.getItemByName(name);
			return new ResponseEntity<Item>(singleItem, HttpStatus.FOUND);
		} catch (BusinessException e) {
			return new ResponseEntity<BusinessException>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			BusinessException be = new BusinessException(706,
					"Something went wrong in th controller layer while fetching the item of a given Name from Database");
			return new ResponseEntity<BusinessException>(be, HttpStatus.BAD_REQUEST);
		}
	}

	/** Insert a new record to the database **/
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> addItem(Item item) {
		try {
			Item savedItem = mItemService.addItem(item);
			return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<BusinessException>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			BusinessException be = new BusinessException(702,
					"Something went wrong in th controller layer while adding an item to the Database");
			return new ResponseEntity<BusinessException>(be, HttpStatus.BAD_REQUEST);
		}
	}

	/** Update the existing record in the database **/
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> updateItem(Item item) {
		try {
			Item updatedItem = mItemService.updateItem(item);
			return new ResponseEntity<Item>(updatedItem, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			return new ResponseEntity<BusinessException>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			BusinessException be = new BusinessException(703,
					"Something went wrong in th controller layer while updating the item to Database");
			return new ResponseEntity<BusinessException>(be, HttpStatus.BAD_REQUEST);
		}
	}

	/** Delete the existing record in the database **/
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> deleteItemById(@PathParam("id") Long id) {
		try {
			String response = mItemService.deleteItemById(id);
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<BusinessException>(e, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			BusinessException be = new BusinessException(704,
					"Something went wrong in th controller layer while deleting the item of a given ID from Database");
			return new ResponseEntity<BusinessException>(be, HttpStatus.BAD_REQUEST);
		}
	}

}

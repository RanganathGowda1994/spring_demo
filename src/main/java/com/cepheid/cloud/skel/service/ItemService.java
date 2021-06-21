package com.cepheid.cloud.skel.service;

import java.util.List;

import com.cepheid.cloud.skel.model.Item;

public interface ItemService {

	public List<Item> getAllItems();

	public Item addItem(Item item);

	public Item updateItem(Item item);

	public String deleteItemById(Long id);

	public Item getItemByName(String name);
}

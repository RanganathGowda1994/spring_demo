package com.cepheid.cloud.skel.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cepheid.cloud.skel.CustomException.BusinessException;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	Set<String> stateValues = new HashSet<>(Arrays.asList("valid", "invalid", "undefined"));

	private final ItemRepository mItemRepository;

	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		mItemRepository = itemRepository;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Item> getAllItems() {
		try {
			return this.mItemRepository.findAll();

		} catch (Exception e) {
			throw new BusinessException(601, "Cannot find all items from Database");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Item addItem(Item item) {
		try {
			if (!stateValues.contains(item.getState().toLowerCase())) {
				throw new BusinessException(605,
						"Please provide any of the 3 valid state values, undefined, valid or invalid");
			}
			return this.mItemRepository.saveAndFlush(item);

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(606,
					"Something went wrong in the Service layer while Saving the Item to Database");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Item updateItem(Item item) {
		try {
			if (!stateValues.contains(item.getState())) {
				throw new BusinessException(607,
						"Please provide any of the 3 valid state values, undefined, valid or invalid");
			}
			this.mItemRepository.updateItem(item.getId(), item.getName(), item.getState());
			return item;

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(608,
					"Something went wrong in the Service layer while updating the Item to Database");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public String deleteItemById(Long id) {
		try {
			if (id == null) {
				throw new BusinessException(609,
						"Please provide a valid Id to delete the corresponding entry from Database");
			}
			this.mItemRepository.deleteById(id);
			this.mItemRepository.flush();
			return "deleted entry with Id " + id;

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(610,
					"Something went wrong in the Service layer while deleting the Item to Database");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Item getItemByName(String name) {
		try {
			if (name == null) {
				throw new BusinessException(613, "Please provide a valid Name to fetch the data");
			}
			System.out.println("name " + name);
			Item item = this.mItemRepository.findByName(name);
			System.out.println("Item by name is: " + item);

			if (item == null) {
				throw new BusinessException(614, "Provided Name does not exists in Database");
			}
			return item;

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(615,
					"Something went wrong in the Service layer while fetching a Database by its Name attribute");
		}
	}

}

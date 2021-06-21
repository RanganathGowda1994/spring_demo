package com.cepheid.cloud.skel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cepheid.cloud.skel.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	@Modifying
	@Query("update Item i set i.name = :name, i.state = :state where i.mId = :id")
	void updateItem(@Param(value = "id") long id, @Param(value = "name") String name,
			@Param(value = "state") String state);

	@Query("select i from Item i where i.name = :name")
	Item findByName(@Param("name") String name);

}

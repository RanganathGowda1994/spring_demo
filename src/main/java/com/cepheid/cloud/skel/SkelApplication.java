package com.cepheid.cloud.skel;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cepheid.cloud.skel.controller.ItemController;
import com.cepheid.cloud.skel.model.Description;
import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.DescriptionRepository;
import com.cepheid.cloud.skel.repository.ItemRepository;

@SpringBootApplication(scanBasePackageClasses = { ItemController.class, SkelApplication.class })
@EnableJpaRepositories(basePackageClasses = { ItemRepository.class, DescriptionRepository.class })
public class SkelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkelApplication.class, args);
	}

	@Bean
	ApplicationRunner initItems(ItemRepository itemRepository) {

		return args -> {
			Stream.of("Lord of the rings", "Hobbit", "Silmarillion", "Unfinished Tales and The History of Middle-earth")
					.forEach(name -> {

						// create a new item and save it to DB
						Item item = new Item(name, "valid");

						Description description1 = new Description("legendary 1", "informative");
						Description description2 = new Description("comic 2", "informative");

						item.getDescriptionList().add(description1);
						item.getDescriptionList().add(description2);

						itemRepository.save(item);
					});
			itemRepository.findAll().forEach(System.out::println);
		};
	}

}

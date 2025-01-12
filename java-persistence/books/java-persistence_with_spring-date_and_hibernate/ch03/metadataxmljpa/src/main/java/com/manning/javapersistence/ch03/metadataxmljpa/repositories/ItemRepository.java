package com.manning.javapersistence.ch03.metadataxmljpa.repositories;

import com.manning.javapersistence.ch03.metadataxmljpa.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}

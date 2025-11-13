package edu.ifrs.si.inventorymanagerpdv.repository;

import org.springframework.data.repository.CrudRepository;

import edu.ifrs.si.inventorymanagerpdv.model.ProductItem;

public interface ProductItemRepository extends CrudRepository<ProductItem, Long> {

}

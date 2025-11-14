package edu.ifrs.si.inventorymanagerpdv.controller;

import edu.ifrs.si.inventorymanagerpdv.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
}

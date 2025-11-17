package edu.ifrs.si.inventorymanagerpdv.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.ifrs.si.inventorymanagerpdv.model.Batch;

public interface BatchRepository extends CrudRepository<Batch, Long>, PagingAndSortingRepository<Batch, Long>{
    
    @Query("SELECT * FROM batch WHERE product_id = :id")
    public List<Batch> findAllByProductId(Long id);

}

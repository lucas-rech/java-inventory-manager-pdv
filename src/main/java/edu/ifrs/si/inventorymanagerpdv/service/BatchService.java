package edu.ifrs.si.inventorymanagerpdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.ifrs.si.inventorymanagerpdv.model.Batch;
import edu.ifrs.si.inventorymanagerpdv.model.ProductBatchDTO;
import edu.ifrs.si.inventorymanagerpdv.model.ProductItem;
import edu.ifrs.si.inventorymanagerpdv.repository.BatchRepository;

@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final ProductItemService productItemService;

    public BatchService(BatchRepository batchRepository, ProductItemService productItemService) {
        this.batchRepository = batchRepository;
        this.productItemService = productItemService;
    }


    public List<Batch> findBatchesByProductId(Long id) {
        List<Batch> batches = batchRepository.findAllByProductId(id);

        if (batches.isEmpty()) {
            return List.of();
        }

        ProductItem product = productItemService.getProductItemById(id);
        return batches.stream().map(b ->
        new Batch(
            b.id(), 
            b.batchId(), 
            new ProductBatchDTO(product.id(), product.name()), 
            b.cost(), 
            b.quantity(), 
            b.validationDate(), 
            b.createdAt(),
            b.updatedAt()
        )).toList();
    }

}

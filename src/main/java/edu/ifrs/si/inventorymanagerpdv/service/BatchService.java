package edu.ifrs.si.inventorymanagerpdv.service;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.ifrs.si.inventorymanagerpdv.model.Batch;
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

        return batches;
    }


    public Batch createBatch(Batch batch) {
        ProductItem productItem = productItemService.getProductItemById(batch.productId());
        if (productItem == null) {
            throw new InvalidParameterException("Product item id not found");
        }
        return batchRepository.save(batch);
    }

}

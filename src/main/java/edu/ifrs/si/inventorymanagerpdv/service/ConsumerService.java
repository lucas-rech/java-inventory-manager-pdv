package edu.ifrs.si.inventorymanagerpdv.service;

import edu.ifrs.si.inventorymanagerpdv.config.exceptions.NullableIdException;
import edu.ifrs.si.inventorymanagerpdv.model.Consumer;
import edu.ifrs.si.inventorymanagerpdv.repository.ConsumerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public List<Consumer> getAllConsumers(@PageableDefault(size = 50) Pageable pageRequest) {
        return consumerRepository.findAll(
                PageRequest.of(
                        pageRequest.getPageNumber(),
                        pageRequest.getPageSize(),
                        pageRequest.getSortOr(Sort.by(Sort.Direction.ASC, "createdAt"))
                )
        ).getContent();
    }


    public Consumer getConsumerById(Long id) {
        if (id == null) {
            throw new NullableIdException();
        }
        Optional<Consumer> consumer = consumerRepository.findById(id);
        return consumer.orElse(null);
    }
}

package com.itau.pix.service;

import com.itau.pix.data.Pix;
import com.itau.pix.data.enums.AccountType;
import com.itau.pix.dto.request.CreateRequestDTO;
import com.itau.pix.dto.request.SearchRequestDTO;
import com.itau.pix.dto.request.UpdateRequestDTO;
import com.itau.pix.exception.PixAlreadyExistsException;
import com.itau.pix.exception.PixNoExistsException;
import com.itau.pix.repository.PixRepository;
import jakarta.persistence.criteria.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PixService {

    private static final Logger LOG = LogManager.getLogger(PixService.class);

    private final PixRepository pixRepository;

    @Autowired
    public PixService(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    public Pix create(CreateRequestDTO request) {
        LOG.info("Creating new pix record");
        if (pixRepository.existsByValueAndDisabledFalse(request.getValue())) {
            throw new PixAlreadyExistsException(request.getValue());
        }
        return pixRepository.save(Pix.of(request));
    }

    public Pix update(Long id, UpdateRequestDTO request) {
        LOG.info("Updating  pix record ID:" + id);

        var result = pixRepository.findByIdAndDisabledFalse(id);

        if (result.isEmpty()) {
            throw new PixNoExistsException(id);
        }

        var pix = result.get();
        pix.setAccountType(AccountType.fromString(request.getAccountType()));
        pix.setAgency(request.getAgency());
        pix.setAccount(request.getAccount());
        pix.setFirstName(request.getFirstName());
        pix.setLastName(request.getLastName());

        return pixRepository.save(pix);
    }

    public Optional<Pix> findById(Long id) {
        return pixRepository.findById(id);
    }


    public List<Pix> findAll(SearchRequestDTO request) {
        Specification<Pix> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (request.getAccount() != null)
                predicateList.add(criteriaBuilder.equal(root.get("account"), request.getAccount()));
            if (request.getAgency() != null)
                predicateList.add(criteriaBuilder.equal(root.get("agency"), request.getAgency()));
            if (request.getFirstName() != null)
                predicateList.add(criteriaBuilder.equal(root.get("firstName"), request.getFirstName()));
            if (request.getLastName() != null)
                predicateList.add(criteriaBuilder.equal(root.get("lastName"), request.getLastName()));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };
        PageRequest pagination = PageRequest.of(request.getPage(), request.getSize());
        return pixRepository.findAll(specification, pagination).stream().toList();
    }

    public List<Pix> findAll(Map<String, String> fields, PageRequest pagination) {
        Specification<Pix> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            for (Map.Entry<String, String> e : fields.entrySet()) {
                predicateList.add(criteriaBuilder.equal(root.get(e.getKey()), e.getValue()));
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };

        return pixRepository.findAll(specification, pagination).stream().toList();
    }


}

package com.itau.pix.repository;

import com.itau.pix.data.Pix;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixRepository extends CrudRepository<Pix, Long>, PagingAndSortingRepository<Pix, Long>,
        JpaSpecificationExecutor {
    boolean existsByValueAndDisabledFalse(String value);

    Optional<Pix> findByIdAndDisabledFalse(Long id);
}

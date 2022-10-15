package com.example.ci.repository;

import com.example.ci.model.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourPackageRepository extends CrudRepository<TourPackage ,String> {
    @Override
    Optional<TourPackage> findById(String s);
    Optional<TourPackage> findByName(String Name);
}

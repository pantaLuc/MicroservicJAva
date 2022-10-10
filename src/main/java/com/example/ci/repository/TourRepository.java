package com.example.ci.repository;

import com.example.ci.model.Tour;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TourRepository extends CrudRepository<Tour ,Integer> {

}

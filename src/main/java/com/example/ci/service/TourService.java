package com.example.ci.service;

import com.example.ci.model.Difficulty;
import com.example.ci.model.Region;
import com.example.ci.model.Tour;
import com.example.ci.model.TourPackage;
import com.example.ci.repository.TourPackageRepository;
import com.example.ci.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }


    public Tour createTour(String title, String description, String blurb, Integer price,
                           String duration, String bullets,
                           String keywords, String tourPackageName, Difficulty difficulty, Region region ) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(()->new RuntimeException("tourPackageName doesn't exist"));

        return new Tour(title, description,blurb, price, duration, bullets, keywords, tourPackage, difficulty, region);
    }

    public long number(){
        return tourRepository.count();
    }

}

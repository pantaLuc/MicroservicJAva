package com.example.ci.service;

import com.example.ci.model.TourPackage;
import com.example.ci.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository ;
    @Autowired
    public  TourPackageService(TourPackageRepository tourPackageRepository){
        this.tourPackageRepository=tourPackageRepository;
    }

    /*
    *  Create a tour Package
    * */

    public TourPackage createTourPackage(String code , String name){
        return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code ,name)));
    }
    /*Find
    * Find all TourPackage Repository
    * */
    public Iterable<TourPackage> listTourPackage(){
        return tourPackageRepository.findAll();
    }

    /*
    * Total of tour Package
    * */

    public long totalTourpackage(){
        return  tourPackageRepository.count();
    }
}

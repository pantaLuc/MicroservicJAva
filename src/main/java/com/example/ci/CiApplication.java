package com.example.ci;

import com.example.ci.service.TourPackageService;
import com.example.ci.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CiApplication implements CommandLineRunner {
	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService ;

	public static void main(String[] args) {
		SpringApplication.run(CiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Create the Tour Packages
		createTourPackage();
		long numOfPackages = tourPackageService.totalTourpackage();
	}

	private void createTourPackage(){
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
	}

	/*
	* Create element from an external File
	*
	* */

	private void createTours(String fileToImport) throws IOException{

	}


}

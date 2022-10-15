package com.example.ci;

import com.example.ci.model.Difficulty;
import com.example.ci.model.Region;
import com.example.ci.repository.TourPackageRepository;
import com.example.ci.service.TourPackageService;
import com.example.ci.service.TourService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class CiApplication implements CommandLineRunner {
	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService ;
	@Autowired
	private TourPackageRepository tourPackageRepository;
	public static void main(String[] args) {
		SpringApplication.run(CiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Create the Tour Packages
		createTourPackage();
		long numOfPackages = tourPackageService.totalTourpackage();
		tourService.createTour("luc" ,"kkk" ,"lll" ,22,"22","ddd","ddd" ,"Backpack Cal",Difficulty.Medium ,Region.Central_Coast);
		//System.out.println(tourPackageRepository.findByName("Backpack Cal").get().getName());
		//Load the tours from an external Json File
		createTours("ExploreCalifornia.json");
		//long numOfTours = tourService.number();
		System.out.println("Le nombre de package Tour :"+numOfPackages);
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
		TourFromFile.read(fileToImport).forEach(importedTour->tourService.createTour(
			importedTour.getTitle(),
			importedTour.getDescription(),
			importedTour.getBlurb(),
			importedTour.getPrice(),
			importedTour.getLength(),
			importedTour.getBullets(),
			importedTour.getKeywords(),
			importedTour.getPackageType(),
			importedTour.getDifficulty(),
			importedTour.getRegion()
		));
	}

	private static  class TourFromFile{
		//fields
		private String packageType, title, description, blurb, price, length,
				bullets, keywords, difficulty, region;
		static List<TourFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
		}

		protected TourFromFile(){}

		String getPackageType() { return packageType; }

		String getTitle() { return title; }

		String getDescription() { return description; }

		 String getBlurb() { return blurb; }

		Integer getPrice() { return Integer.parseInt(price); }

		String getLength() { return length; }

		String getBullets() { return bullets; }

		String getKeywords() { return keywords; }

		Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }

		Region getRegion() { return Region.findByLabel(region); }


	}

}

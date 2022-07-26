package com.mahir.appairport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("airports")
public class AirportController {
	List<Airport> airports;
	
	public AirportController(){
		loadAirport();
	}
	
	private void loadAirport() {
		try {
			List<String> linesOfCSV = Files.readAllLines(Path.of("d:\\workspaces\\database\\airports.csv"));
			airports = linesOfCSV.stream().skip(1).map(eachLine -> {
	            Airport airport = new Airport();
	            String[] splitColumns = eachLine.split(",");
	            airport.setAirportName(splitColumns[3].replaceAll("\"", ""));
	            airport.setAirportType(splitColumns[2].replaceAll("\"", ""));
	            airport.setCountry(splitColumns[8].replaceAll("\"", ""));
	            return airport;
	        }).collect(Collectors.toList());
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}

	@GetMapping(path = "all")
	public List<Airport> allAirports(){
		return airports;
	}
	
	@GetMapping(path = "search/{q}")
	public List<Airport> airportByName(@PathVariable("q") String searchString) {
		List<Airport> filteredAirports = airports.stream().filter((a) -> a.getAirportName().contains(searchString)).collect(Collectors.toList());
		return filteredAirports;
	}
	
	/**
	 * Sending a payload JSON
	 * and i parsing in back in object
	 * @param airport
	 * @return
	 */
	@PostMapping(path="add")
	public String  addAirport(@RequestBody Airport airport) {
		airports.add(airport);
		return "Success";
	}
	
	@DeleteMapping(path="delete/{name}")
	public String dropAirport(@PathVariable("name") String name) {
		// myAirports.get(0)
		return "Done";
	}
}

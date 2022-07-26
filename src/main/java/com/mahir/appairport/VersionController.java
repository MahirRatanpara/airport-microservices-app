package com.mahir.appairport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@GetMapping(path = "version")
	public String getVersion() {
		System.out.println("Here we Goooo :) !");
		return "1.0";
	}
}

package com.example.library;

import org.springframework.boot.SpringApplication;

public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(LibraryApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

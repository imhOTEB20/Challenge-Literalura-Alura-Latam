package com.challenge.literalura;

import com.challenge.literalura.model.consumption.ResponseData;
import com.challenge.literalura.model.persists.Book;
import com.challenge.literalura.service.consumption.APIConsumption;
import com.challenge.literalura.service.consumption.ConvertData;
import com.challenge.literalura.service.persists.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private App app;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		app.start();
	}
}

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
	BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String json = APIConsumption.obtenerDatos("https://gutendex.com/books/?search=Don%20Quijote%20de%20la%20Mancha");
		var convertData = new ConvertData();

		var responseData = convertData.getData(json, ResponseData.class);

		System.out.println(responseData);

		Book libroBuscado = new Book(responseData.results().get(0));

		bookService.saveBook(libroBuscado);
	}
}

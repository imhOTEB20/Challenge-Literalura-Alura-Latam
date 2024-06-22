package com.challenge.literalura;

import com.challenge.literalura.model.consumption.ResponseData;
import com.challenge.literalura.service.consumption.APIConsumption;
import com.challenge.literalura.service.consumption.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String json = APIConsumption.obtenerDatos("https://gutendex.com/books/?search=quijote");
		var convertData = new ConvertData();

		var responseData = convertData.getData(json, ResponseData.class);

		System.out.println(json);

		System.out.println(responseData);
	}
}

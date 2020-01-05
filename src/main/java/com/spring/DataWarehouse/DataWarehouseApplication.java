package com.spring.DataWarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.spring.DataWarehouse.model")
@SpringBootApplication
public class DataWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataWarehouseApplication.class, args);
	}

}

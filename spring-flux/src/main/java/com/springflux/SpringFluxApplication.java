package com.springflux;

import com.springflux.domain.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringFluxApplication {

    public static void main(String[] args) {
		SpringApplication.run(SpringFluxApplication.class, args);
	}

}

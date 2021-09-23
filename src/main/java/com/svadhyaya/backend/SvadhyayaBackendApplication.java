package com.svadhyaya.backend;

import com.svadhyaya.backend.repository.template.DefaultSvadhyayaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = DefaultSvadhyayaRepository.class)
public class SvadhyayaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SvadhyayaBackendApplication.class, args);
	}

}

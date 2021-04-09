package com.ty.attendancesystem;

import com.ty.attendancesystem.config.CustomRepository;
import com.ty.attendancesystem.config.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class AttendanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApplication.class, args);
	}

}

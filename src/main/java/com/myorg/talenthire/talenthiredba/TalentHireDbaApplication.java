package com.myorg.talenthire.talenthiredba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@EnableJpaRepositories(basePackages="com.myorg.talenthire.talenthiredba.dao")
@EntityScan(basePackages="com.myorg.talenthire.pojo.model")
@EnableTransactionManagement
@SpringBootApplication
public class TalentHireDbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentHireDbaApplication.class, args);
	}
}

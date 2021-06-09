package com.agenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;

@SpringBootApplication
public class AnotacaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotacaoApiApplication.class, args);

		System.out.println(LocaleContextHolder.getLocale().getDisplayLanguage());
		System.out.println(LocaleContextHolder.getLocale().getDisplayCountry());
		System.out.println(LocaleContextHolder.getLocale().getDisplayName());
	}

}

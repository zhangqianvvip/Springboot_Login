package com.example.demo;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.demo.inteceptor.SignUpSuccessInteceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.demo.controller")
public class ConfigAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		super.addInterceptors(registry);

		registry.addInterceptor(new LocaleChangeInterceptor());

		registry.addInterceptor(new SignUpSuccessInteceptor()).addPathPatterns("/submitSignUp");
	}

	@Bean(name = "messageSource")
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("signup");
		return resourceBundleMessageSource;
	}

	@Bean(name = "localeResolver")
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return sessionLocaleResolver;
	}

}

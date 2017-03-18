package br.com.contatos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ViewConfig {
	   @Bean
	    public ServletContextTemplateResolver templateResolver() {
	        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
	        resolver.setPrefix("/WEB-INF/views/");
	        resolver.setSuffix(".html");
	        resolver.setTemplateMode("HTML5");
	        resolver.setOrder(1);
	        return resolver;
	    }

	    @Bean
	    public SpringTemplateEngine templateEngine() {
	        SpringTemplateEngine engine = new SpringTemplateEngine();
	        engine.setTemplateResolver(templateResolver());
	        return engine;
	    }

	    @Bean
	    public ThymeleafViewResolver thymeleafViewResolver() {
	        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	        resolver.setTemplateEngine(templateEngine());
	        return resolver;
	    }
}

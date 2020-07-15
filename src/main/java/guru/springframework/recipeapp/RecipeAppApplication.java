package guru.springframework.recipeapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RecipeAppApplication implements WebMvcConfigurer {

private static Logger logger = LoggerFactory.getLogger(RecipeAppApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(RecipeAppApplication.class, args);
		System.out.println("JVM started.....");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/static/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS)
				.cachePublic());
	}

}

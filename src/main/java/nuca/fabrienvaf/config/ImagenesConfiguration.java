package nuca.fabrienvaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagenesConfiguration implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		//Esta clase especifica un directorio externo para que el sistema del proyecto lo detecte en las vistas etc.
		registry.addResourceHandler("/recursos/**").addResourceLocations("file:/home/jorge/recursos/");
	}
	
}

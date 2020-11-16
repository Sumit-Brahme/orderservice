package com.sumit.project.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.any;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket orderAPIs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Order")
                .select()
                //Ignores controllers annotated with @CustomIgnore
                .apis(any()) //Selection by RequestHandler
                .paths(PathSelectors.ant("/order/*")) // and by paths
                .build();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("**/swagger-ui.html")
                //.addResourceLocations("classpath:/META-INF/resources/");
        //registry.addResourceHandler("**/webjars/**")
               // .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("**/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

}

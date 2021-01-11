package ni.org.ics.smil.cssfv.api.conf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	private ApiKey apiKey = new ApiKey("JWT", "Authorization", "header");
	
	
	private ApiInfo apiInfo = new ApiInfo(
		      "REST API Laboratorio CSSFV - ICS",
		      "API para el manejo de información de laboratorio",
		      "1.0",
		      "Terms of service",
		      new Contact("Ing. Miguel Salinas", "https://www.sustainablesciences.org/", "msalinasr@icsnicaragua.org"),
		      "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
		      Collections.emptyList());
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 
	
	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(apiInfo)
          .securityContexts(Arrays.asList(securityContext()))
          .securitySchemes(Arrays.asList(apiKey))
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
	
	
}

package com.cdbd.chat.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.RequiredArgsConstructor;

/**  
 * 스프링 스큐리티 설정 
 */
@Configuration  
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean  
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {  
    	
    	http.cors(cors ->
    			cors.disable());
    	
        http.authorizeExchange(exchanges -> 
            exchanges
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .pathMatchers(
                    "/swagger-ui/**",
                    "/h2-console/**"
                ).permitAll()
                .anyExchange().authenticated()
        );
        	  
        return http.build();
    }      

    @Bean  
    public WebSecurityCustomizer webSecurityCustomizer() {  
        return (web) -> web.ignoring().requestMatchers(
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/h2-console/**"
        		);  
    }
  

}

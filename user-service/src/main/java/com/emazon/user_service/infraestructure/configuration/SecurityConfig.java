package com.emazon.user_service.infraestructure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    //Para definir la cadena de seguridad
    //Http security es un obj que se pasa por todos los filtros
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())                        //Sin estado significa que no se guardara la sesion
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http  ->{
                    //Configurar endpoints públicos
                    http.requestMatchers(HttpMethod.GET, "/user/**").permitAll();
                    // Permitir solicitudes POST a /user
                    http.requestMatchers(HttpMethod.POST, "/user").permitAll();
                    http.requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll();
                    //Configurar cualquier otro endpoint (Zero trust)
                    http.anyRequest().denyAll(); //Niega todos los otros request a endpoints no configurados
                    //http.anyRequest().authenticated(); // Niegas los otros request a endpoints solo si no estan autenticados
                })
                .build();
    }




}

package com.emazon.user_service.infraestructure.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                    //Configurar endpoints privados
                    http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("CREATE");
                    //Configurar cualquier otro endpoint (Zero trust)
                    http.anyRequest().denyAll(); //Niega todos los otros request a endpoints no configurados
                    //http.anyRequest().authenticated(); // Niegas los otros request a endpoints solo si no estan autenticados
                })
                .build();
    }




}

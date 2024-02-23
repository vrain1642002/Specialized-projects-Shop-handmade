package com.project.shophandmade.configurations;

import com.project.shophandmade.filters.JwtTokenFilter;
import com.project.shophandmade.models.Vaitro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity



@RequiredArgsConstructor
public class WebSecurityConfig {
    private  final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    //Pair.of(String.format("%s/products", apiPrefix), "GET"),

    //kiem tra quyen thoa moi di qua
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/nguoidungs/dangki", apiPrefix),
                                    String.format("%s/nguoidungs/dangnhap", apiPrefix)


                            )
                            .permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/danhmucsanphams/**", apiPrefix)).permitAll()
                            .requestMatchers(GET,
                                    String.format("%s/vaitros**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/sanphams/images/*", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/danhmucsanphams/**", apiPrefix)).hasAnyRole(Vaitro.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/danhmucsanphams/**", apiPrefix)).hasAnyRole(Vaitro.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/danhmucsanphams/**", apiPrefix)).hasAnyRole( Vaitro.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/sanphams/**", apiPrefix)).permitAll()

                            .requestMatchers(POST,
                                    String.format("%s/sanphams/**", apiPrefix)).hasAnyRole(Vaitro.ADMIN)

                            .requestMatchers(PUT,
                                    String.format("%s/sanphams/**", apiPrefix)).hasAnyRole( Vaitro.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/sanphams/**", apiPrefix)).hasAnyRole(Vaitro.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/donhangs/**", apiPrefix)).permitAll()

                            .requestMatchers(GET,
                                    String.format("%s/donhangs/get-orders-by-keyword", apiPrefix)).hasAnyRole(Vaitro.ADMIN)

                            .requestMatchers(POST,
                                    String.format("%s/donhangs/**", apiPrefix)).hasAnyRole(Vaitro.KHACHHANG,Vaitro.ADMIN)

                           .requestMatchers(PUT,
                                    String.format("%s/donhangs/**", apiPrefix)).hasRole(Vaitro.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/donhangs**", apiPrefix)).hasRole(Vaitro.ADMIN)

                            .requestMatchers(POST,
                                    String.format("%s/chitietdonhangs/**", apiPrefix)).hasAnyRole(Vaitro.KHACHHANG,Vaitro.ADMIN)

                            .requestMatchers(GET,
                                    String.format("%s/chitietdonhangs/**", apiPrefix)).hasAnyRole(Vaitro.KHACHHANG,Vaitro.ADMIN)

                            .requestMatchers(DELETE,
                                    String.format("%s/chitietdonhangs/**", apiPrefix)).hasRole(Vaitro.ADMIN)

                            .anyRequest().authenticated();


                })
                .csrf(AbstractHttpConfigurer::disable);
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        })

        ;
        return http.build();
    }
}

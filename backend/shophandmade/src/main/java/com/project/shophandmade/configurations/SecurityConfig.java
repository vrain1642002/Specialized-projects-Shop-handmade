package com.project.shophandmade.configurations;


import com.project.shophandmade.repositories.NguoidungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

@RequiredArgsConstructor
public class SecurityConfig {
    //can doi tuong user's detail
    // la doi tuong user theo chuan spring khi dang nhap tao ra doi tuong va quan ly doi tuon do
    private final NguoidungRepository nguoidungRepository;



    //ham tra ve truong duy nhat cua doi tuong
    @Bean
    public UserDetailsService userDetailsService() {
        return sdt-> nguoidungRepository
                .findBySdt(sdt)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Khong the tim thay nguoi dung voi sdt = "+sdt));
    }
    //Ma hoa theo kieu sha256 sinh ra 64 bit
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Khoi tao authenticationProvider tu hai thuoc tinh
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}

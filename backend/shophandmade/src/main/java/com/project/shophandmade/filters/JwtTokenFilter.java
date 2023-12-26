package com.project.shophandmade.filters;


import com.project.shophandmade.components.JwtTokenUtil;
import com.project.shophandmade.models.Nguoidung;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
//ke thua de kiem tra tung request
public class JwtTokenFilter extends OncePerRequestFilter{
    @Value("${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if(khongcankiemToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            //phai co header khi cac yeu cau can token
            final String authHeader = request.getHeader("Authorization");
            //kiem tra neu khong thoa thi la chua chung thuc
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
            //lay token ra
            final String token = authHeader.substring(7);
            final String sdt= jwtTokenUtil.laySDT(token);
            if (sdt != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                //lay doi tuong nguoi dung
                Nguoidung userDetails = (Nguoidung) userDetailsService.loadUserByUsername(sdt);
                if(jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
           filterChain.doFilter(request, response); //enable bypass
        }catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

    }


    //cac request khong can kiem tra
    private boolean khongcankiemToken(@NonNull  HttpServletRequest request) {
        //tao danh sach ca request
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of(String.format("%s/vaitros", apiPrefix), "GET"),
                Pair.of(String.format("%s/sanphams", apiPrefix), "GET"),
                Pair.of(String.format("%s/donhangs", apiPrefix), "GET"),

                Pair.of(String.format("%s/danhmucsanphams", apiPrefix), "GET"),
                Pair.of(String.format("%s/nguoidungs/dangki", apiPrefix), "POST"),
                Pair.of(String.format("%s/nguoidungs/dangnhap", apiPrefix), "POST")
        );
        for(Pair<String, String> bypassToken: bypassTokens) {
            if (request.getServletPath().contains(bypassToken.getFirst()) &&
                    request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }
        return false;
    }
}

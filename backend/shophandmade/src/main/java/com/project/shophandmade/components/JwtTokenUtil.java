package com.project.shophandmade.components;


import com.project.shophandmade.exceptions.GiatrikhonghopleException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    //thoi luong su dung token
    @Value("${jwt.expiration}")
    private int expiration;

    //key de ky
    @Value("${jwt.secretKey}")
    private String secretKey;

    //ham tao ra token
    public String taoToken(com.project.shophandmade.models.Nguoidung nguoidung) throws Exception{
        //cac thuoc tinh cua doi tuong goi la claim
        Map<String, Object> claims = new HashMap<>();
        //this.generateSecretKey();
        claims.put("sdt", nguoidung.getSdt());
        try {
            // dung thu vien token
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(nguoidung.getSdt())
                    //set thoi luong lay bien nhan vao voi 1000 miligiay
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    //khi ki can key de dich tu token sang claim
                    .signWith(laySignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        }catch (Exception e) {
            //you can "inject" Logger, instead System.out.println
            throw new GiatrikhonghopleException("Khong the tao token, loi: "+e.getMessage());
            //return null;
        }
    }
    private Key laySignInKey() {
        //lay ra mang byte tu key
        byte[] bytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(bytes);
    }
    private String taoSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32]; // 256-bit key
        random.nextBytes(keyBytes);
        String secretKey = Encoders.BASE64.encode(keyBytes);
        return secretKey;
    }
    //trich xuat cac claims,can key de truyen va trich xuat claim
    private Claims layClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(laySignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //lay 1 calim muon lay
    public  <T> T layClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.layClaims(token);
        return claimsResolver.apply(claims);
    }
    //check token het han chua
    public boolean kiemtrahanToken(String token) {
        Date expirationDate = this.layClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    public String laySDT(String token) {
        return layClaim(token, Claims::getSubject);
    }
    //kiem tra user name va token
    public boolean validateToken(String token, UserDetails userDetails) {
        String phoneNumber = laySDT(token);
        return (phoneNumber.equals(userDetails.getUsername()))
                && !kiemtrahanToken(token);
    }
}

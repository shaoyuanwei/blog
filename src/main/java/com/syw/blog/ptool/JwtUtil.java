package com.syw.blog.ptool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * jwt工具类
 */
public class JwtUtil {

    public static String sercetKey = "lingling960830";

    public final static long keeptime = 1800000;

//    public static JwtUtil jwtUtil;
//
//    public JwtUtil(){}
//
//    @PostConstruct
//    public void init() {
//        jwtUtil = this;
//    }

    public static String generToken(String id, String issuer, String subject) {

        long ttlMillis = keeptime;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sercetKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now);
        if (subject != null) {
            builder.setSubject(subject);
        }
        if (issuer != null) {
            builder.setIssuer(issuer);
        }
        builder.signWith(signatureAlgorithm, signingKey);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();

    }

    public static String updateToken(String token) {
        try {
            Claims claims = verifyToken(token);
            String id = claims.getId();
            String subject = claims.getSubject();
            String issuer = claims.getIssuer();
//            Date date = claims.getExpiration();
            return generToken(id, issuer, subject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  "0";
    }

    public static String updateTokenBase64Code(String token) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            token = new String(decoder.decodeBuffer(token), "utf-8");
            Claims claims = verifyToken(token);
            String id = claims.getId();
            String subject = claims.getSubject();
            String issuer = claims.getIssuer();
//            Date date = claims.getExpiration();
            String nowToken = generToken(id, issuer, subject);
            return base64Encoder.encode(nowToken.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static Claims verifyToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(sercetKey)).parseClaimsJws(token).getBody();
        return claims;
    }

}

package com.edu.archives.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "classroomArchivesSecretKey";
    private static final long EXPIRATION_TIME = 86400000; // 24小时

    /**
     * 生成JWT token
     */
    public static String generateToken(String userId, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userId)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 解析JWT token
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token是否过期
     */
    public static boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
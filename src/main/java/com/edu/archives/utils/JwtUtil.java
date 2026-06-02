package com.edu.archives.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

// JWT 工具类。
// 调用关系：UserServiceImpl#login 调用 generateToken；后续鉴权流程可调用 parseToken/isTokenExpired。
public class JwtUtil {

    // 签名密钥。
    private static final String SECRET_KEY = "classroomArchivesSecretKey";
    // 默认过期时间（毫秒）。
    private static final long EXPIRATION_TIME = 86400000; // 24小时

    /**
     * 生成JWT token
     */
    public static String generateToken(String userId, String username) {
        // 当前时间。
        Date now = new Date();
        // 过期时间。
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        // 构建并签发 token。
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
            // 校验签名并解析 claims。
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // 解析失败统一返回 null，交由上层判断。
            return null;
        }
    }

    /**
     * 验证token是否过期
     */
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = parseToken(token);
            if (claims == null) {
                return true; // 解析失败视为过期
            }
            // 当前时间晚于过期时间即过期。
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            // 兜底：异常即视为过期。
            return true;
        }
    }
}
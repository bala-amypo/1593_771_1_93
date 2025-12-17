// package com.example.demo.util;

// import io.jsonwebtoken.*;
// import java.util.Date;

// public class JwtUtil {

//     private static final String KEY = "jwt-demo-key";

//     public static String generateToken(String email, String role) {
//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("role", role)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                 .signWith(SignatureAlgorithm.HS256, KEY)
//                 .compact();
//     }

//     public static boolean validateToken(String token) {
//         return extractClaims(token).getExpiration().after(new Date());
//     }

//     public static String extractUsername(String token) {
//         return extractClaims(token).getSubject();
//     }

//     public static String extractRole(String token) {
//         return extractClaims(token).get("role").toString();
//     }

//     private static Claims extractClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(KEY)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }
package com.service;

import com.dto.auth.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dto.auth.LoginDTO;
import com.entity.Lab;
import com.entity.Position;
import com.entity.User;
import com.repository.UserRepository;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.fake-hash}")
    private String fakeHash;

    @Autowired
    private UserRepository userRepository;


    public Optional<User> validateUser(LoginDTO loginDTO){
        return userRepository.findById(loginDTO.getId());
    }

    public String generateToken(Integer id, Position position, Lab lab) {
        return Jwts.builder()
                .subject(id.toString())
                .claim("positionId", position.getId())
                .claim("labId", lab.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    public Integer extractUserId(String token) {
        return Integer.parseInt(extractClaims(token).getSubject());
    }

    public Integer extractPositionId(String token) {
        return (Integer) extractClaims(token).get("positionId");
    }

    public String extractPositionName(String token) {
        return (String) extractClaims(token).get("positionName");
    }

    public Integer extractLabId(String token) {
        return (Integer) extractClaims(token).get("labId");
    }

    public String extractLabName(String token) {
        return (String) extractClaims(token).get("labName");
    }

    public Date extractIat(String token) { return extractClaims(token).getIssuedAt(); }

    public Date extractExp(String token) { return extractClaims(token).getExpiration(); }

    public TokenDTO extractTokenDetails(String token) {
        TokenDTO tokenInfo = new TokenDTO();
        tokenInfo.setSub(extractUserId(token));
        tokenInfo.setPositionId(extractPositionId(token));
        tokenInfo.setLabId(extractLabId(token));
        tokenInfo.setIat(extractIat(token));
        tokenInfo.setExp(extractExp(token));
        return tokenInfo;
    }
    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public String getFakeHash() {
        return fakeHash;
    }
}

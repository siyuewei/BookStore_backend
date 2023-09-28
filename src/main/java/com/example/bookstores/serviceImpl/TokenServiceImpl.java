package com.example.bookstores.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bookstores.entity.UserAuth;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenServiceImpl {
    private static final long EXPIRATION_TIME = 1000 * 60 * 30;
    private static final String SECRET_KEY = "your_secret_key";

    public String getToken(UserAuth userAuth) {
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = JWT.create()
                .withAudience(String.valueOf(userAuth.getUser().getId()))
                .withClaim("user_id", userAuth.getUser().getId())
                .withClaim("username", userAuth.getUser().getUsername())
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(userAuth.getUser().getUsername() + userAuth.getPassword()));

        return token;
    }

    public int getUserIdFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        int id = jwt.getClaim("user_id").asInt();
        return id;
    }

    public long getExpireTime(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expireTime = jwt.getExpiresAt();
        return expireTime.getTime();
    }
}
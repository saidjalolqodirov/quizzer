package uz.test.config;

import io.jsonwebtoken.Claims;

public interface TokenUtil {
    String getUsernameFromToken(String token);

    String generateAccessToken(UserDetailsImpl userDetails);

    String generateRefreshToken(UserDetailsImpl userDetails);

    Boolean validateToken(String token);

    Claims getClaims(String token);
}

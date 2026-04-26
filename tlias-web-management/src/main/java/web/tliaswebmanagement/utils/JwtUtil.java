package web.tliaswebmanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "d2VpMjAyNTA5MTExMDVhYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5eg==";
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;

    public static String generateToken(Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static Claims parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

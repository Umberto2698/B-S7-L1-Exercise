package lezione26.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lezione26.enteties.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTTools {
    @Value("${spring.jwt.secret}")
    private String secret;
    @Value("${time.millisecond.day}")
    private long milliseconds;

    public String createToken(User user) {
        return Jwts.builder().setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + milliseconds * 7))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
    }
}

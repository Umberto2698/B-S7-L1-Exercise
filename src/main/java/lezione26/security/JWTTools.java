package lezione26.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lezione26.enteties.User;
import lezione26.exceptions.UnauthorizedException;
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

    public void verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);

        } catch (Exception ex) {
            throw new UnauthorizedException("Invalid token, please log in.");
        }
    }

    public String extractIdFroToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }
}

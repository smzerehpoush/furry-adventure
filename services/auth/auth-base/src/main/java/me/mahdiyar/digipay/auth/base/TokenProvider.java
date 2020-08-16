package me.mahdiyar.digipay.auth.base;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
public class TokenProvider {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String SESSION_ID_KEY = "session_id";
    private static final String SECRET_KEY = "bh8TRlAaSoOb1wpc45rfTpJDRQQ9naXM";

    private static final int TOKEN_EXPIRATION_DAYS = 365; // 365 days

    public static String generateTokenForUser(String userId, String sessionId) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(SESSION_ID_KEY, sessionId)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addDays(new Date(), TOKEN_EXPIRATION_DAYS))
                .compact();
    }


    public static Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            logger.info("error in validating token : {}", authToken, e);
        }
        return false;
    }

    public static String resolveToken(String authorizationHeader) {
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(TokenProvider.BEARER)) {
            return authorizationHeader.replace(TokenProvider.BEARER, "");
        }
        return null;
    }
}

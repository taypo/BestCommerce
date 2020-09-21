package bestcommerce.apigateway.jwt;

import bestcommerce.apigateway.messaging.Merchant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenService {

	private static final String AUTH_HEADER = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";

	@Value("${bestcommerce.jwt.secret}")
	private String secret;

	@Value("${bestcommerce.session.default}")
	private int defaultDuration;

	@Value("${bestcommerce.session.remember-me}")
	private int rememberMeDuration;

	public String generate(Merchant merchant, boolean rememberMe) {
		Long now = System.currentTimeMillis();

		return Jwts.builder()
				.setSubject(merchant.email)
				.setIssuedAt(new Date())
				.setExpiration(rememberMe ? new Date(now + rememberMeDuration) : new Date(now + defaultDuration))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}


	public String validate(HttpServletRequest request) {
		String header = getAuthHeader(request);
		if (header == null)
			throw new InvalidTokenException("Auth header missing");

		if (header.startsWith(TOKEN_PREFIX) == false)
			throw new InvalidTokenException("Invalid header format");

		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(header.replace(TOKEN_PREFIX, "")).getBody();

		if(claims.getExpiration().before(new Date()))
			throw new InvalidTokenException("Token expired");

		return claims.getSubject();
	}

	public String getAuthHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
}

package bestcommerce.apigateway.jwt;

import bestcommerce.apigateway.messaging.Merchant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

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
}

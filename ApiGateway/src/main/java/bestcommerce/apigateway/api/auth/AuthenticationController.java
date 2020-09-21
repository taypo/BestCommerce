package bestcommerce.apigateway.api.auth;

import bestcommerce.apigateway.config.MessagingConfig;
import bestcommerce.apigateway.jwt.TokenService;
import bestcommerce.apigateway.messaging.Merchant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	private final RabbitTemplate template;
	private final TokenService tokenService;

	public AuthenticationController(RabbitTemplate template, TokenService tokenService) {
		this.template = template;
		this.tokenService = tokenService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		Merchant merchant = template.convertSendAndReceiveAsType(
				MessagingConfig.EXCHANGE_NAME,
				MessagingConfig.QUERY_MERCHANT_KEY,
				request,
				new ParameterizedTypeReference<Merchant>() {
				});

		if(merchant == null) {
			return ResponseEntity.badRequest().build();
		}
		AuthenticationResponse response = new AuthenticationResponse();
		response.token = tokenService.generate(merchant, request.rememberMe);
		return ResponseEntity.ok(response);
	}
}

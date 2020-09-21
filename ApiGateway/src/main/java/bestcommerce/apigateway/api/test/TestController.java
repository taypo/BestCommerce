package bestcommerce.apigateway.api.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is here just to test JWT authentication
 */
@RestController
public class TestController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello, world!";
	}
}

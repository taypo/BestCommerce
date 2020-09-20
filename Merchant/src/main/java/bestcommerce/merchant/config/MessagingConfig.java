package bestcommerce.merchant.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

	public static final String EXCHANGE_NAME = "best.commerce";
	public static final String MERCHANT_KEY = "merchant";
	public static final String QUEUE_NAME = "merchant_request";

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME);
	}

	@Bean
	public Binding binding(DirectExchange directExchange,
						   Queue queue) {
		return BindingBuilder.bind(queue)
				.to(directExchange)
				.with(MERCHANT_KEY);
	}

	@Bean
	public MessageConverter jackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}

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
	public static final String CREATE_MERCHANT_KEY = "create_merchant";
	public static final String CREATE_MERCHANT_QUEUE_NAME = "create_merchant_request";
	public static final String QUERY_MERCHANT_KEY = "query_merchant";
	public static final String QUERY_MERCHANT_QUEUE_NAME = "query_merchant_request";

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue createMerchantQueue() {
		return new Queue(CREATE_MERCHANT_QUEUE_NAME);
	}

	@Bean
	public Binding createMerchantBinding(DirectExchange directExchange, Queue createMerchantQueue) {
		return BindingBuilder.bind(createMerchantQueue)
				.to(directExchange)
				.with(CREATE_MERCHANT_KEY);
	}

	@Bean
	public Queue queryMerchantQueue() {
		return new Queue(QUERY_MERCHANT_QUEUE_NAME);
	}

	@Bean
	public Binding queryMerchantBinding(DirectExchange directExchange, Queue queryMerchantQueue) {
		return BindingBuilder.bind(queryMerchantQueue)
				.to(directExchange)
				.with(QUERY_MERCHANT_KEY);
	}

	@Bean
	public MessageConverter jackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}

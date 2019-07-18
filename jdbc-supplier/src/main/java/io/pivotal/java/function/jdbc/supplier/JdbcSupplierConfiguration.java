package io.pivotal.java.function.jdbc.supplier;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

@Configuration
@EnableConfigurationProperties(JdbcSupplierProperties.class)
public class JdbcSupplierConfiguration {

	@Autowired
	JdbcSupplierProperties properties;

	@Autowired
	private DataSource dataSource;

//	@Autowired
//	private Function<Message<?>, Flux<Message<?>>> splitterFunction;

	@Bean
	public MessageSource<Object> jdbcMessageSource() {
		JdbcPollingChannelAdapter jdbcPollingChannelAdapter =
				new JdbcPollingChannelAdapter(this.dataSource, this.properties.getQuery());
		jdbcPollingChannelAdapter.setMaxRowsPerPoll(this.properties.getMaxRowsPerPoll());
		jdbcPollingChannelAdapter.setUpdateSql(this.properties.getUpdate());
		return jdbcPollingChannelAdapter;
	}

	@Bean
	public Supplier<Message<?>> get() {
		return () -> {
			final Message<?> received = jdbcMessageSource().receive();
			System.out.println("Data received from JDBC Source: " + received);
			//TODO: Add splitting capability
//			if (properties.isSplit()) {
//				//todo
//				splitterFunction.apply(received);
//			}
			return received;
		};
	}
}

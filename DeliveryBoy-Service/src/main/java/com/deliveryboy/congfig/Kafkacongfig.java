package com.deliveryboy.congfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Kafkacongfig {
	
	@Bean
	public NewTopic topic() {
		return TopicBuilder
				.name(AppConstants.LOCATION_TOPICS_NAME)
			//	.partitions(0)
			//	.replicas(0)
				.build();
	}

}

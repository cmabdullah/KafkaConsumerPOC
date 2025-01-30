package org.cm.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "message-app", uniqueGroupId = true, uniqueGroupIdDeleteOnShutdown = true)
public class KafkaMessageConsumerClient {
	@Topic("user-online")
	void consumeEvent(String message) {
		System.out.println("event from Kafka: " + message);
	}
}

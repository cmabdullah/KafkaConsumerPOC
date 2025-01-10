package org.cm.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;
import org.apache.kafka.clients.producer.ProducerConfig;

@KafkaClient(
		id = "product-client"
)
public interface KafkaMessagePublishClient {
	@Topic("user-online")
	void send(@KafkaKey String key, String payload);
}

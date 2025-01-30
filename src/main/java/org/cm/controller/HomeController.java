package org.cm.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.cm.kafka.KafkaMessagePublishClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

@Controller
public class HomeController {

	private final KafkaMessagePublishClient kafkaMessagePublishClient;

	public HomeController(KafkaMessagePublishClient kafkaMessagePublishClient) {
		this.kafkaMessagePublishClient = kafkaMessagePublishClient;
	}

	@Get("/")
	public String index() {
		return "hello";
	}

	@Get("/publish/{id}/{message}")
	public String publish(@PathVariable String id, @PathVariable String message) {
		var mesg = "User " + id + " is online at " + LocalDateTime.now();
		kafkaMessagePublishClient.send(id, mesg);
		return mesg;
	}

	@Get("/deleteConsumerGroup/{id}")
	public void deleteConsumerGroupId(@PathVariable String id) {
		Properties props = new Properties();
		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "0.0.0.0:29092,0.0.0.0:29093,0.0.0.0:29094");
		AdminClient adminClient = AdminClient.create(props);
		adminClient.deleteConsumerGroups(List.of(id)).all().whenComplete((voidResult, throwable) -> {
			if (throwable == null) {
				System.out.println("Consumer group deleted successfully: " + id);
			} else {
				System.err.println("Error deleting consumer group: " + id);
				throwable.printStackTrace();
			}
		});
	}
}

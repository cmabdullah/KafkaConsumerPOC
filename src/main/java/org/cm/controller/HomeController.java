package org.cm.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import org.cm.kafka.KafkaMessagePublishClient;

import java.time.LocalDateTime;

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
}

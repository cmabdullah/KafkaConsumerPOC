

```java
@KafkaListener(groupId = "message-app", uniqueGroupId = true)
public class KafkaMessageConsumerClient {
	@Topic("user-online")
	void consumeEvent(String message) {
		System.out.println("event from Kafka: " + message);
	}
}

```
![consumer-groups-list.png](consumer-groups-list.png)
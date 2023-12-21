package ontechsystem.hbcha.spring.project.sample.restapi.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Collections;


import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.time.Duration;

public class KafkaConsumerHandler {
    private final Consumer<String, String> consumer;
    private final String topic;

    public KafkaConsumerHandler(String bootstrapServers, String groupId, String topic) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(properties);
        this.topic = topic;
    }


    public boolean startConsuming() {
        consumer.subscribe(Collections.singleton(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            records.forEach(record -> {
                // Kafka 메시지를 처리하는 코드를 작성
                Map<String, Object> consumedMessageInfo = new HashMap<>();
                consumedMessageInfo.put("topic", record.topic());
                consumedMessageInfo.put("key", record.key());
                consumedMessageInfo.put("value", record.value());
                consumedMessageInfo.put("offset", record.offset());
                consumedMessageInfo.put("partition", record.partition());
                // Map을 출력
                System.out.println("Consumed message: " + consumedMessageInfo);

            });

        }
    }

}
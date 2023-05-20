package org.knoldus.producer;

import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.knoldus.domain.generated.CoffeeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CoffeeOrderProducer {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderProducer.class);
    private static final String GREETING_TOPIC = "coffee-orders";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(properties);

        CoffeeOrder coffeeOrder = buildCoffeeOrder("Hello, Schema Registry");

        byte[] value = coffeeOrder.toByteBuffer().array();

        ProducerRecord<String, byte[]> producerRecord =
                new ProducerRecord<>(GREETING_TOPIC,value);

        var recordMetaData = producer.send(producerRecord).get();
        logger.info("recordMetaData :{}", recordMetaData);



    }

    private static CoffeeOrder buildCoffeeOrder(String greeting) {

        return CoffeeOrder.newBuilder()
                .setGreeting(greeting)
                .build();
    }

}

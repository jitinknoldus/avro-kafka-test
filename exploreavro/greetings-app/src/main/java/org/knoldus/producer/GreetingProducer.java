package org.knoldus.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.glassfish.jersey.internal.util.Producer;
import org.knoldus.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GreetingProducer {

    private static final String GREETING_TOPIC = "greeting";
    private static final Logger logger = LoggerFactory.getLogger(GreetingProducer.class);
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(properties);

        Greeting greeting = buildGreeting("Hello, Schema Registry");

        byte[] value = greeting.toByteBuffer().array();

        ProducerRecord<String, byte[]> producerRecord =
                new ProducerRecord<>(GREETING_TOPIC,value);

        var recordMetaData = producer.send(producerRecord).get();
        logger.info("recordMetaData :{}", recordMetaData);



    }

    private static Greeting buildGreeting(String greeting) {

        return Greeting.newBuilder()
                .setGreeting(greeting)
                .build();
    }

}

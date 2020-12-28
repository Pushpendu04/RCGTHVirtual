package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@EnableBinding(Sink.class)
@Log4j2
public class RcgthMessageBrokerApplication {

	@StreamListener(Sink.INPUT)
	public void consumeMessage(String s) {
		log.info(s);
	}

	public static void main(String[] args) {
		SpringApplication.run(RcgthMessageBrokerApplication.class, args);
	}

}

package com.exercise.spring.oxm.soap.client.soapclient;

import java.io.StringWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.util.UUID;

import javax.xml.soap.SAAJResult;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.example.orders.Order;
import com.example.orders.Order.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@SpringBootApplication
public class SoapclientApplication implements CommandLineRunner {

	private static final Logger Log = LoggerFactory.getLogger(SoapclientApplication.class);
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	public static void main(String[] args) {
		SpringApplication.run(SoapclientApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Address address = new Address();
		address.setAddress("234 Short Drive");
		address.setCity("WhoosVille");
		address.setCountry("Scandanavia");
		address.setName("Mayor Who");
		
		Order order = new Order();
		order.setOrderid(UUID.randomUUID().toString());
		order.setProduct("Spring Boot App");
		order.setAddress(address);
		
		StringWriter writer = new StringWriter();
		marshaller.marshal(order, new StreamResult(writer));
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		marshaller.marshal(order, new StreamResult(output));
		
		File file = new File("src\\main\\resources\\result.xml");
		marshaller.marshal(order, new StreamResult(file));
		String xml = writer.toString();
		Log.info("XML: ", xml);
		String xml2 = output.toString();
		Log.info("Byte: ", xml2);
	}


}

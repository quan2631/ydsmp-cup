package com.wish.ydsmp.cpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YdsmpCupApplication {

	public static void main(String[] args) {
		SpringApplication.run(YdsmpCupApplication.class, args);
	}
	@RequestMapping("hello")
	public String hello(){return "hello yd-smp-cpu.";};

}

package com.malcolmbaatjies;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class Main
{
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Welcome to Malcolm's Spring Boot Application";
	}

	@SneakyThrows
	@RequestMapping(value = "{*path}")
	@ResponseBody
	public String handleAll(@PathVariable(value = "path") String path)
	{
		throw new Exception("Invalid Path provided: [" + path + "].");
	}
}
package ch.bbw.pr.sospri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Logger logger = LoggerFactory.getLogger(LoggingController.class);
		logger.info("Starting my application with {} args.", args.length);
	}
}

package goa.systems.numrange;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NumrangeApplication {

	private static Logger logger = LoggerFactory.getLogger(NumrangeApplication.class);

	@Value("${goa.systems.numrange.datadir}")
	private String datadir;

	public static void main(String[] args) {
		SpringApplication.run(NumrangeApplication.class, args);
	}

	@PostConstruct
	private void init() {
		NumberRangeManager nrm = NumberRangeManager.getInstance();
		nrm.configure(datadir);
		logger.info("NumberRangeManager configured.");
	}
}

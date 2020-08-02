package goa.systems.numrange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumrangeController {

	private static Logger logger = LoggerFactory.getLogger(NumrangeController.class);

	@GetMapping(value = "/getnumber")
	public String getNumber() {
		logger.debug("Single number requested.");
		return "4711";
	}
}

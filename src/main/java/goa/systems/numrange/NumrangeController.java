package goa.systems.numrange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumrangeController {

	private static Logger logger = LoggerFactory.getLogger(NumrangeController.class);

	@GetMapping(value = "/getnumber/{uid}")
	@ResponseBody
	public String getNumber(@PathVariable("uid") String uid) {
		logger.info("Number from range {} requested.", uid);
		return "4711";
	}
}

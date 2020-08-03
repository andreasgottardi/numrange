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

	@GetMapping(value = "/getnewnumber/{uid}")
	@ResponseBody
	public String getnewnumber(@PathVariable("uid") String uid) {
		logger.debug("Number from range {} requested.", uid);
		NumberRange nr = NumberRangeManager.getInstance().getNumberRange(uid);
		return String.format("%d", nr.getNewNumber());
	}
}

package goa.systems.numrange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

	@GetMapping(value = "/getnewnumbers/{uid}/{nrofnrs}")
	@ResponseBody
	public ResponseEntity<String> getnewnumbers(@PathVariable("uid") String uid,
			@PathVariable("nrofnrs") long nrofnrs) {
		logger.debug("{} numbers from range {} requested.", nrofnrs, uid);
		NumberRange nr = NumberRangeManager.getInstance().getNumberRange(uid);
		StringBuilder sb = new StringBuilder();
		long currnr = nr.getCurrentnumber();
		long numbers = nr.getNewNumbers(nrofnrs);
		long i;
		for (i = currnr; i < numbers - 1; i++) {
			sb.append(i);
			sb.append(",");
		}
		sb.append(i);
		String content = sb.toString();
		HttpHeaders hh = new HttpHeaders();
		hh.add(HttpHeaders.CONTENT_LENGTH, Integer.toString(content.length()));
		return ResponseEntity.ok().headers(hh).body(content);
	}
}

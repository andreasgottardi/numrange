package goa.systems.numrange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumrangeController {

	private static Logger logger = LoggerFactory.getLogger(NumrangeController.class);

	@GetMapping(value = "/")
	@ResponseBody
	public ResponseEntity<String> showranges() {
		logger.debug("Overview page requested.");
		String content = InputOutput.read(NumrangeController.class.getResourceAsStream("/index.html"));
		HttpHeaders hh = new HttpHeaders();
		hh.add(HttpHeaders.CONTENT_LENGTH, Integer.toString(content.length()));
		return ResponseEntity.ok().headers(hh).body(content);
	}

	@GetMapping(value = "/getnewnumber")
	@ResponseBody
	public String getnewnumber(@RequestParam String uid) {
		logger.debug("Number from range {} requested.", uid);
		NumberRange nr = NumberRangeManager.getInstance().getNumberRange(uid);
		return String.format("%d", nr.getNewNumber());
	}

	@GetMapping(value = "/getcurrentnumber")
	@ResponseBody
	public String getcurrentnumber(@RequestParam String uid) {
		logger.debug("Number from range {} requested.", uid);
		NumberRange nr = NumberRangeManager.getInstance().getNumberRange(uid);
		return String.format("%d", nr.getCurrentnumber());
	}

	@GetMapping(value = "/getnewnumbers")
	@ResponseBody
	public ResponseEntity<String> getnewnumbers(@RequestParam String uid, @RequestParam long nrofnrs) {
		logger.debug("{} numbers from range {} requested.", nrofnrs, uid);
		NumberRange nr = NumberRangeManager.getInstance().getNumberRange(uid);
		StringBuilder sb = new StringBuilder();
		long currnr = nr.getCurrentnumber();
		long numbers = nr.getNewNumbers(nrofnrs);
		long i;
		for (i = currnr + 1; i < numbers; i++) {
			sb.append(i);
			sb.append(",");
		}
		sb.append(i);
		String content = sb.toString();
		HttpHeaders hh = new HttpHeaders();
		hh.add(HttpHeaders.CONTENT_LENGTH, Integer.toString(content.length()));
		return ResponseEntity.ok().headers(hh).body(content);
	}

	@GetMapping(value = "/getranges", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getranges() {
		logger.debug("getranges page requested.");
		NumberRangeManager nr = NumberRangeManager.getInstance();
		String json = GsonFactory.getGson().toJson(nr.getRanges());
		HttpHeaders hh = new HttpHeaders();
		hh.add(HttpHeaders.CONTENT_LENGTH, Integer.toString(json.length()));
		return ResponseEntity.ok().headers(hh).body(json);
	}
}

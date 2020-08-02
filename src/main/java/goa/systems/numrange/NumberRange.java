package goa.systems.numrange;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberRange {

	private static Logger logger = LoggerFactory.getLogger(NumberRange.class);

	private int currentnumber;
	private List<String> accesstokens;

	public NumberRange() {
		this(0, new ArrayList<>());
	}

	public NumberRange(int currentnumber, List<String> accesstokens) {
		this.currentnumber = currentnumber;
		this.accesstokens = accesstokens;
		logger.debug("NumberRange initialized.");
	}

	public int getCurrentnumber() {
		return currentnumber;
	}

	public void setCurrentnumber(int currentnumber) {
		this.currentnumber = currentnumber;
	}

	public List<String> getAccesstokens() {
		return accesstokens;
	}

	public void setAccesstokens(List<String> accesstokens) {
		this.accesstokens = accesstokens;
	}

	public void addAccesstokens(String accesstoken) {
		this.accesstokens.add(accesstoken);
	}
}

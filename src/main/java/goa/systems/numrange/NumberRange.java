package goa.systems.numrange;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberRange {

	private static Logger logger = LoggerFactory.getLogger(NumberRange.class);

	private String uid;
	private int currentnumber;
	private List<String> accesstokens;

	public NumberRange() {
		this(UUID.randomUUID().toString(), 0, new ArrayList<>());
	}

	public NumberRange(String uid, int currentnumber, List<String> accesstokens) {
		this.uid = uid;
		this.currentnumber = currentnumber;
		this.accesstokens = accesstokens;
		logger.debug("NumberRange initialized.");
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public void store(File file) {
		if (file != null && file.exists() && file.isDirectory()) {
			File jsonfile = new File(file, String.format("%s.json", uid));
			try (FileOutputStream fos = new FileOutputStream(jsonfile)) {
				fos.write(GsonFactory.getGson().toJson(this).getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				logger.error("Error storing number range.", e);
			}
		}
	}
}

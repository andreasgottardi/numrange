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

import com.google.gson.annotations.Expose;

public class NumberRange {

	private static Logger logger = LoggerFactory.getLogger(NumberRange.class);

	@Expose(serialize = true, deserialize = true)
	private String uid;

	@Expose(serialize = true, deserialize = true)
	private long currentnumber;

	@Expose(serialize = true, deserialize = true)
	private List<String> accesstokens;

	private File file;

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

	public long getCurrentnumber() {
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

	public File getFile() {
		return this.file;
	}

	public void setFile(File numrangedef) {
		this.file = numrangedef;
	}

	public void store() {
		if (this.file != null) {
			try (FileOutputStream fos = new FileOutputStream(this.file)) {
				fos.write(GsonFactory.getGson().toJson(this).getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				logger.error("Error storing number range.", e);
			}
		}
	}

	public long getNewNumber() {
		return getNewNumbers(1);
	}

	public synchronized long getNewNumbers(long nrofnrs) {
		this.currentnumber += nrofnrs;
		store();
		return this.currentnumber;
	}
}

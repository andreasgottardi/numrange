package goa.systems.numrange;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

@Component
public class NumberRangeManager {

	private static Logger logger = LoggerFactory.getLogger(NumberRangeManager.class);

	private static NumberRangeManager mthis = null;
	private boolean isconfigured = false;

	Map<String, NumberRange> numberranges;
	private File datadir;

	private NumberRangeManager() {
		this.numberranges = new HashMap<>();
		this.datadir = null;
	}

	public void configure(String datadir) {
		this.datadir = new File(datadir);
		for (File numrangedef : this.datadir.listFiles()) {
			Matcher m = Pattern.compile("(.*)\\.json").matcher(numrangedef.getName());
			if (m != null && m.find()) {
				try (FileInputStream fis = new FileInputStream(numrangedef);
						InputStreamReader isr = new InputStreamReader(fis);) {
					numberranges.put(m.group(1), GsonFactory.getGson().fromJson(isr, NumberRange.class));
				} catch (JsonSyntaxException | JsonIOException | IOException e) {
					logger.error("Error loading number range from file {}", numrangedef.getAbsolutePath(), e);
				}
			}
		}
		this.isconfigured = true;
	}

	public static NumberRangeManager getInstance() {

		if (mthis == null) {
			mthis = new NumberRangeManager();
			return mthis;
		}

		if (!mthis.isconfigured) {
			logger.error("NumberRangeManager not yet configured."
					+ "Call \"NumberRangeManager.configure(String datadir)\" first.");
			return null;
		} else {
			return mthis;
		}
	}
}

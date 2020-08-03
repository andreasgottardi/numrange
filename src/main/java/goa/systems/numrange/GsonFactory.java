package goa.systems.numrange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {

	private static Logger logger = LoggerFactory.getLogger(GsonFactory.class);

	private static Gson gson = null;

	private GsonFactory() {
	}

	public static Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			logger.debug("Gson instance created.");
		}
		return gson;
	}
}

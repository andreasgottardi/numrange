package goa.systems.numrange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;

@SpringBootTest
class NumberRangeTests {

	private static Logger logger = LoggerFactory.getLogger(NumberRangeTests.class);

	@Value("${goa.systems.numrange.datadir}")
	private String datadir;

	@Test
	void testNumberRangeInit() {

		assertEquals("datadir", datadir);

		NumberRange nr = new NumberRange();
		nr.setCurrentnumber(0);
		nr.addAccesstokens(UUID.randomUUID().toString());
		nr.addAccesstokens(UUID.randomUUID().toString());
		nr.addAccesstokens(UUID.randomUUID().toString());

		Gson gson = GsonFactory.getGson();
		String json = gson.toJson(nr);
		assertNotNull(json);

		logger.info("Json for id {} generated: {}", nr.getUid(), json);

		nr.store(new File(datadir));
	}
}

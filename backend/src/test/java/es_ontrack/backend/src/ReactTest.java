package es_ontrack.backend.src;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ReactTest {



	@Test
	void JsonPackageFileExists() {

		File tmp = new File("../react/package.json");
		assert (tmp.exists());
		if(tmp.exists()) System.out.println("package.json exists!");
	}


}
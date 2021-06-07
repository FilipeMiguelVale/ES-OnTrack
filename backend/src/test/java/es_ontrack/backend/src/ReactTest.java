package es_ontrack.backend.src;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ReactTest {



	@Test
	void DummyFileExists() {

		File tmp = new File("../dummytest");
		assert (tmp.exists());
		if(tmp.exists()) System.out.println("Dummytest exists!");
	}


}
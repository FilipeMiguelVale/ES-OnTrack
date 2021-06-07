package es_ontrack.backend.src;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class SrcApplicationTests {

	@Test
	void contextLoads() {
	}
	
    @Test
	void DockerFileExists() {

		File tmp = new File("Dockerfile");
		assert (tmp.exists());
		System.out.println("DockerFile exists!");
	}

}

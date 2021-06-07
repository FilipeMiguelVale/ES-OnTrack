package es_ontrack.backend.src;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class DeployConnectionTest {
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {
	}
	
	@Test
	public void getSpring() throws Exception {
		System.out.println("Request Spring");
		this.mockMvc.perform(get("http://192.168.160.87:23001/test/data")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void getReact() throws Exception {
		System.out.println("Request React");
		this.mockMvc.perform(get("http://192.168.160.87:23000/")).andDo(print());//.andExpect(status().isOk());
	}
	

}

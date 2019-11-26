import com.org.home.WeatherApp;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WeatherApp.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeatherTestController {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void verifyGoodRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/tommorrow/us/55123").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void verifyInvalidZipcode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather/tommorrow/us/551").accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());
    }
}

package edu.knu.se.movierecommendation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
@AutoConfigureMockMvc
class MovieRecommendationControllerTest {

	@Autowired
	  private WebApplicationContext wac;
	  private MockMvc mockMvc;
	  
	@Before
    public void setup() {
      DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
      this.mockMvc = builder.build();
    }
	
	@Test
    public void testPutuidpasswd1() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users" + "?uid=test1&passwd=123"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"SUCCESS\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void testPutuidpasswd2() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users" + "?uid=test1&passwd=123"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                    								.string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void testPutuidpasswd3() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users" + "?uid=&passwd=123"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                    								.string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	
	
	@Test
    public void testPutuidpasswd4() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users" + "?uid=test1&passwd="); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                    								.string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }

	@Test
    public void testGetusers() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/users/"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

	@Test
    public void testGetusers_count_() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/users/_count_"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                    								.string("611"))
                    .andDo(MockMvcResultHandlers.print());
    }
	

	@Test
    public void testPutuidrating1() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users/" + "test1" + "/ratings" + "?movie=1&rating=1.5"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"SUCCESS\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	

	@Test
    public void testPutuidrating2() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users/" + "test1" + "/ratings" + "?movie=-1&rating=1.5"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	

	@Test
    public void testPutuidrating3() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users/" + "test1" + "/ratings" + "?movie=1&rating=-1.5"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }

	@Test
    public void testPutuidrating4() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users/" + "test1" + "/ratings" + "?movie=1&rating=5.5"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }

	@Test
    public void testPutuidrating5() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users/" + "test1" + "/ratings" + "?movie=987654321&rating=1.5"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }

	@Test
    public void testPutuidrating6() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/users/" + "test1" + "/ratings" + "?movie=1&rating=1.3"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void testDeleteuser1() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("/users/" + "test1"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"SUCCESS\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }

	@Test
    public void testDeleteuser2() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("/users/" + "test1"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                                                    .string("{\"result\":\"FAILED\"}"))
                    .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void testGetusersrating() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/users/"+"tests1"+"/ratings"); //need to change
        this.mockMvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status()
                                                    .isOk())
                    .andExpect(MockMvcResultMatchers.content()
                    								.string("{\"1\":1.5}"))
                    .andDo(MockMvcResultHandlers.print());
    }
}

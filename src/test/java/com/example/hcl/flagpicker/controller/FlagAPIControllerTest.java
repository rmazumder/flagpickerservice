package com.example.hcl.flagpicker.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//import com.example.hcl.flagpicker.config.RequestTraceFilter;
import com.example.hcl.flagpicker.repository.IFlagRepository;
import com.example.hcl.flagpicker.service.FlagService;
import com.example.hcl.flagpicker.util.TokenAuthenticationService;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class FlagAPIControllerTest {

    @MockBean
    private IFlagRepository flagRepository;
    
    @MockBean
    FlagAPIController flagController;
    
    @MockBean
	private FlagService flagService;
    
   // @MockBean 
    //RequestTraceFilter traceFilter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenUserControllerInjected_thenNotNull() throws Exception {
        assertThat(flagController).isNotNull();
    }

//    @Test
//    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
//    	mockMvc.perform(MockMvcRequestBuilders.get("/api/all")).andExpect(MockMvcResultMatchers.status().isForbidden());
//    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = TokenAuthenticationService.createToken("ruhul");

        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/all").header("Authorization", token)).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void whenGetRequestAllData_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/all")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    
    }
    
    @Test
    public void whenGetRequestFlagByContinent_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/continent/Asia")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    
    }
    
    @Test
    public void whenGetRequestFlagByCountry_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/country/India")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    
    }
   

   
    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectReponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/continen1/Asia")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

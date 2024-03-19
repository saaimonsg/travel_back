package com.example.travelling;

import com.example.travelling.infra.core.domain.appuser.data.AppUserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TravellingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AppUserData appUserData;

    @BeforeEach
    public void setup() {
        appUserData = new AppUserData();
        appUserData.setUsername("testUser");
        appUserData.setPassword("testPassword");
        appUserData.setPassword2("testPassword");
        appUserData.setName("testName");
        appUserData.setSurname("testSurname");
        appUserData.setEmail("testEmail@testmail.com");

    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/authenticate/register")
                        .content(objectMapper.writeValueAsString(appUserData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
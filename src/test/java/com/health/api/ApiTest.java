package com.health.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(JUnit4.class)
@TestMethodOrder(OrderAnnotation.class)
public class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    private static long enrolleId = 0;
    private static long dependentId = 0;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        enrolleId = System.currentTimeMillis();
    }

    @Test
    @Order(1)
    public void addEnrollee() throws Exception {
        enrolleId = System.currentTimeMillis();
        JSONObject data = new JSONObject();
        data.put("id", enrolleId);
        data.put("name", "Ravikumar");
        data.put("dateOfBirth", "1980-10-10");
        data.put("isActive", 1);
        mockMvc.perform( //
                post("/api/enrollee") //
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content(data.toString()) //
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void getEnrollee() throws Exception {
        this.mockMvc.perform( //
                get("/api/enrollee/" + enrolleId)) //
                .andDo(print()) //
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void updateEnrollee() throws Exception {
        JSONObject data = new JSONObject();
        data.put("name", "Ravikumar - Updated");
        mockMvc.perform( //
                put("/api/enrollee/" + enrolleId) //
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content(data.toString()) //
        ) //
                .andDo(print()) //
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void getEnrollee2() throws Exception {
        this.getEnrollee();
    }

    @Test
    @Order(6)
    public void addDependent() throws Exception {
        dependentId = System.currentTimeMillis();
        JSONObject data = new JSONObject();
        data.put("id", dependentId);
        data.put("name", "Ravikumar's Dependent");
        data.put("dateOfBirth", "1980-10-10");
        mockMvc.perform( //
                post("/api/enrollee/" + enrolleId + "/dependent") //
                        .contentType(MediaType.APPLICATION_JSON) //
                        .content(data.toString()) //
        ).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(7)
    public void getDependent() throws Exception {
        this.mockMvc.perform( //
                get("/api/dependent/" + dependentId)) //
                .andDo(print()) //
                .andExpect(status().isOk());
    }

    @Test
    @Order(100)
    public void deleteEnrollee() throws Exception {
        mockMvc.perform(delete("/api/enrollee/" + enrolleId)) //
                .andDo(print()) //
                .andExpect(status().isOk());
    }

    @Test
    @Order(101)
    public void deleteDependent() throws Exception {
        mockMvc.perform(delete("/api/dependent/" + dependentId)) //
                .andDo(print()) //
                .andExpect(status().isOk());
    }
}

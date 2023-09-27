package com.jitterted.ebp.blackjack.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRequestOfHomePageIsStatus200() throws Exception {
        mockMvc.perform(get("/index.html"))
               .andExpect(status().isOk());
    }

    @Test
    void postToStartGameEndpointIsRedirect() throws Exception {
        mockMvc.perform(post("/start-game"))
               .andExpect(status().is3xxRedirection());
    }

    @Test
    void getRequestToGameEndpointIsStatus200Ok() throws Exception {
        mockMvc.perform(get("/game"))
               .andExpect(status().isOk());
    }

    @Test
    public void postToHitEndpointIsRedirection() throws Exception {
        mockMvc.perform(post("/hit"))
               .andExpect(status().is3xxRedirection());
    }

    @Test
    public void getRequestToDoneEndpointIs200Ok() throws Exception {
        mockMvc.perform(get("/done"))
               .andExpect(status().isOk());
    }

    @Test
    public void postToStandEndpointIsRedirection() throws Exception {
        mockMvc.perform(post("/stand"))
               .andExpect(status().is3xxRedirection());
    }
}

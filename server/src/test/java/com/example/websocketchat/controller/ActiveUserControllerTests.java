package com.example.websocketchat.controller;

import com.example.websocketchat.entity.ActiveUserEntity;
import com.example.websocketchat.service.impl.ActiveUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@AutoConfigureJsonTesters
@WebMvcTest(ActiveUserController.class)
@AutoConfigureMockMvc
public class ActiveUserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ActiveUserServiceImpl service;

    @Autowired
    private JacksonTester<List<ActiveUserEntity>> jsonActiveUserEntityList;

    @Autowired
    private JacksonTester<ActiveUserEntity> jsonActiveUserEntity;

    @Test
    public void gettingAllActiveUsers() throws Exception{
        //given
        given(service.getAllActiveUsers())
                .willReturn(
                        List.of(
                        new ActiveUserEntity(1L,"Patryk", "Blue"),
                        new ActiveUserEntity(2L,"Bartek", "Yellow")
                        )
                );
        //when
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/app/activeusers/getAll")
        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonActiveUserEntityList.write(List.of(
                        new ActiveUserEntity(1L,"Patryk", "Blue"),
                        new ActiveUserEntity(2L,"Bartek", "Yellow")
                        )
                ).getJson()
        );
    }

    @Test
    public void saveActiveUser() throws Exception{
        //when
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/app/activeusers/save")
                .content(jsonActiveUserEntity.write(new ActiveUserEntity(1L,"Patryk", "Blue")).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}

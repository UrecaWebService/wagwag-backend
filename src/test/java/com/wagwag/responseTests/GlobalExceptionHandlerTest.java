package com.wagwag.responseTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest()
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

}

package com.example.library.controller;

import com.example.library.controller.BookController;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
//    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser(roles = "USER")
    void getBooks_asUser_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void addBook_asUser_shouldReturn403() throws Exception {
        mockMvc.perform(post("/api/books")
                        .contentType("application/json")
                        .content("""
                        {
                          "title": "Test",
                          "author": "Test"
                        }
                        """))
                .andExpect(status().isForbidden());
    }

    @Test
    void getBooks_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isUnauthorized());
    }
}

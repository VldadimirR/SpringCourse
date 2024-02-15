package com.example.demo.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/register"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post("/api/v1/auth/register")
                        .param("firstname", "John")
                        .param("lastname", "Doe")
                        .param("email", "john@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/login"))
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    @WithMockUser(username = "john@example.com")
    public void testSuccessfulLogin() throws Exception {
        mockMvc.perform(post("/process_login")
                        .param("username", "john@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
    }

    @Test
    public void testFailedLogin() throws Exception {
        mockMvc.perform(post("/process_login")
                        .param("username", "invaliduser")
                        .param("password", "invalidpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/v1/auth/login?error"));
    }

    @Test
    @WithMockUser(username = "john@example.com")
    public void testSuccessfulLoginCreatesNewSession() throws Exception {
        // Given
        MockHttpSession session = new MockHttpSession();

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/process_login")
                        .session(session)
                        .param("username", "john@example.com")
                        .param("password", "password"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/tasks"));

        // Then
        assertNotNull(session.getId());
    }

    @Test
    @WithMockUser
    public void testLogout() throws Exception {
        // Отправляем GET запрос на эндпоинт /logout
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/logout"))
                // Проверяем, что возвращается статус перенаправления (302)
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                // Проверяем, что происходит перенаправление на /login
                .andExpect(MockMvcResultMatchers.redirectedUrl("/api/v1/auth/login"));

        // Попытка доступа к защищенному ресурсу после выхода из системы
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection()) // Ожидаем перенаправление
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/v1/auth/login")); // Проверяем перенаправление на страницу входа


    }
}

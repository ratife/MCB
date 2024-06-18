package com.mcb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.controllers.UserController;
import com.mcb.manager.UserService;
import com.mcb.dto.UserDTO;
import com.mcb.dto.mapper.UserMapper;
import com.mcb.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController utilisateurController;

    @BeforeEach
    public void setUp() {
    }

    //@Test
    public void testAddUser() throws Exception {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("testuser");
        userDto.setFirstname("Test");
        userDto.setLastname("User");

        User user = UserMapper.toEntity(userDto);
        when(userService.saveUser(any(User.class))).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userDto.getUsername()));

        verify(userService, times(1)).saveUser(any(User.class));
    }

    //@Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        List<User> userList = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].username").value(user1.getUsername()))
                .andExpect(jsonPath("$[1].username").value(user2.getUsername()));

        verify(userService, times(1)).findAll();
    }

    // Add more tests for other methods such as getUserById, updateUser, deleteUser, addFile
}

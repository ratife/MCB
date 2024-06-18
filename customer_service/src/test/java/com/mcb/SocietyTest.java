package com.mcb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.controllers.SocietyController;
import com.mcb.dto.SocietyAddUser;
import com.mcb.dto.SocietyDTO;
import com.mcb.dto.mapper.SocietyMapper;
import com.mcb.manager.CustomerService;
import com.mcb.manager.UserService;
import com.mcb.model.Society;
import com.mcb.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SocietyTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private UserService userService;

    @InjectMocks
    private SocietyController societyController;

    @BeforeEach
    public void setUp() {
        // Initial setup if needed
    }

    @Test
    public void testAddCustomer() throws Exception {
        SocietyDTO societyDto = new SocietyDTO();
        societyDto.setCompanyName("Test Society");

        Society society = SocietyMapper.toEntity(societyDto);
        when(customerService.saveCustomer(any(Society.class))).thenReturn(society);

        ObjectMapper objectMapper = new ObjectMapper();
        String societyDtoJson = objectMapper.writeValueAsString(societyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/society/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(societyDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value(societyDto.getCompanyName()));

        verify(customerService, times(1)).saveCustomer(any(Society.class));
    }

    //@Test
    public void testGetAllCustomers() throws Exception {
        Society society1 = new Society();
        society1.setId(1L);
        society1.setCompanyName("Society 1");

        Society society2 = new Society();
        society2.setId(2L);
        society2.setCompanyName("Society 2");

        List<Society> societyList = Arrays.asList(society1, society2);
        when(customerService.findAll()).thenReturn(societyList);

        mockMvc.perform(MockMvcRequestBuilders.get("/society/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].companyName").value(society1.getCompanyName()))
                .andExpect(jsonPath("$[1].companyName").value(society2.getCompanyName()));

        verify(customerService, times(1)).findAll();
    }

    //@Test
    public void testGetCustomerById() throws Exception {
        Society society = new Society();
        society.setId(1L);
        society.setCompanyName("Test Society");

        when(customerService.findById(1L)).thenReturn(Optional.of(society));

        mockMvc.perform(MockMvcRequestBuilders.get("/society/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(society.getCompanyName()));

        verify(customerService, times(1)).findById(1L);
    }

    //@Test
    public void testUpdateCustomer() throws Exception {
        SocietyDTO societyDto = new SocietyDTO();
        societyDto.setCompanyName("Updated Society");

        Society existingSociety = new Society();
        existingSociety.setId(1L);
        existingSociety.setCompanyName("Old Society");

        Society updatedSociety = SocietyMapper.upDate(existingSociety, societyDto);

        when(customerService.findById(1L)).thenReturn(Optional.of(existingSociety));
        when(customerService.saveCustomer(any(Society.class))).thenReturn(updatedSociety);

        ObjectMapper objectMapper = new ObjectMapper();
        String societyDtoJson = objectMapper.writeValueAsString(societyDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/society/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(societyDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(societyDto.getCompanyName()));

        verify(customerService, times(1)).findById(1L);
        verify(customerService, times(1)).saveCustomer(any(Society.class));
    }

    //@Test
    public void testDeleteCustomer() throws Exception {
        Society existingSociety = new Society();
        existingSociety.setId(1L);
        existingSociety.setCompanyName("Test Society");

        when(customerService.findById(1L)).thenReturn(Optional.of(existingSociety));

        mockMvc.perform(MockMvcRequestBuilders.delete("/society/delete/1"))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).findById(1L);
        verify(customerService, times(1)).delete(1L);
    }

    //@Test
    public void testAddUser() throws Exception {
        SocietyAddUser societyAddUser = new SocietyAddUser();
        societyAddUser.setIdCustomer(1L);
        societyAddUser.setIdUser(1L);
        societyAddUser.setTypeUser(1);

        Society society = new Society();
        society.setId(1L);
        society.setCompanyName("Test Society");

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(customerService.findById(1L)).thenReturn(Optional.of(society));
        when(userService.findById(1L)).thenReturn(Optional.of(user));

        ObjectMapper objectMapper = new ObjectMapper();
        String societyAddUserJson = objectMapper.writeValueAsString(societyAddUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/society/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(societyAddUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(society.getCompanyName()));

        verify(customerService, times(1)).findById(1L);
        verify(userService, times(1)).findById(1L);
        verify(customerService, times(1)).saveCustomer(any(Society.class));
    }
}

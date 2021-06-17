package com.banking.pos;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.banking.pos.controller.PosApiController;
import com.banking.pos.repository.AccountRepository;
import com.banking.pos.service.PosService;

@WebMvcTest(PosApiController.class)
class PosOperationsApplicationTests {


    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    PosService posService;
    
    @MockBean 
    AccountRepository accountRepository;
    	
	@Test
	void getBalanceByAccountId_success() throws Exception {
				
	    mockMvc.perform(MockMvcRequestBuilders
	            .get("/api/account-balance/4755")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()))
	            .andExpect(jsonPath("$", is(1001.88)));
	}

	
}

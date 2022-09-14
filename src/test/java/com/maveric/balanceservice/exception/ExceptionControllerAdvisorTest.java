package com.maveric.balanceservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.APIV1;
import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalanceDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes= ExceptionControllerAdvisor.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ExceptionControllerAdvisor.class)
public class ExceptionControllerAdvisorTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void whenRequestSyntaxNotValidShouldGetError400WhenRequestMadeToCreateBalanceDetails() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.post(APIV1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getBalanceDto()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void whenBalanceIdNotFoundShouldGetError404WhenRequestMadeToGetBalanceDetails() throws Exception
    {
        MvcResult mvcResult =
                mvc.perform(get(APIV1+"/balanceId1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();

        int expectedErrorResponse = 404;
        int actualResponse = mvcResult.getResponse().getStatus();
        System.out.println("actualResponseBody------->"+actualResponse);
        assertThat(actualResponse)
                .isEqualTo(expectedErrorResponse);
    }

    @Test
    public void whenBalanceIdNotFoundShouldGetError404WhenRequestMadeToDeleteBalanceDetails() throws Exception
    {
        MvcResult mvcResult =
                mvc.perform(delete(APIV1+"/balanceId1")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();

        int expectedErrorResponse = 404;
        int actualResponse = mvcResult.getResponse().getStatus();
        System.out.println("actualResponseBody------->"+actualResponse);
        assertThat(actualResponse)
                .isEqualTo(expectedErrorResponse);
    }
}

package com.project.surveycow;

import com.project.surveycow.dtos.QuestionResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnswerControllerTest extends BasicTestConfig {

    //@Ignore this test after running once OR change values in QuestionResponseDto fields
    @Test
    public void saveAnswerTest() throws Exception {

        QuestionResponseDto questionResponseDto = buildQuestionResponseDto();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(questionResponseDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void saveAnswerThrowsExceptionTest() throws Exception {

        QuestionResponseDto questionResponseDto = buildQuestionResponseDto();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(questionResponseDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isConflict())
                .andDo(print());
    }

    private QuestionResponseDto buildQuestionResponseDto() {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setUserId(1L);
        questionResponseDto.setSurveyId(1L);
        questionResponseDto.setQuestionId(1L);
        questionResponseDto.setUserResponse("Response from Test");
        questionResponseDto.setIsLast(false);
        questionResponseDto.setCanceled(false);
        return questionResponseDto;
    }

}

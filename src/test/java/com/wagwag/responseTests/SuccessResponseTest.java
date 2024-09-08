package com.wagwag.responseTests;

import com.wagwag.global.response.SuccessResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuccessResponseTest {

    @Test
    public void successResponse_withData() {
        String testData = "Test Data";

        SuccessResponse<String> response = SuccessResponse.success(testData);

        assertTrue(response.getIsSuccess());
        assertEquals("200", response.getCode());
        assertEquals("OK", response.getMessage());
        assertEquals(testData, response.getResult());  //결과값 테스트
    }

    @Test
    public void successResponse_withoutResult() {
        SuccessResponse<String> response = SuccessResponse.successWithoutResult(null);

        assertTrue(response.getIsSuccess());
        assertEquals("200", response.getCode());
        assertEquals("OK", response.getMessage());
        assertNull(response.getResult());   //@JsonInclude(JsonInclude.Include.NON_EMPTY) 테스트
    }
}
package com.wagwag.responseTests;

import com.wagwag.global.exception.errorcode.CommonErrorCode;
import com.wagwag.global.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTest {

    @Test
    public void errorResponse_withErrorCode() {
        ErrorResponse response = ErrorResponse.of(CommonErrorCode.INVALID_PARAMETER);

        assertFalse(response.getIsSuccess());
        assertEquals("4000", response.getCode());
        assertEquals("Invalid parameter included", response.getMessage());
        assertTrue(response.getErrors().isEmpty());     //@JsonInclude(JsonInclude.Include.NON_EMPTY) 테스트
    }

    @Test
    public void errorResponse_withCustomMessage() {
        ErrorResponse response = ErrorResponse.of(CommonErrorCode.INTERNAL_SERVER_ERROR, "Custom Error Message");

        assertFalse(response.getIsSuccess());
        assertEquals("5000", response.getCode());
        assertEquals("Custom Error Message", response.getMessage());    //에러메시지 커스텀 테스트
        assertTrue(response.getErrors().isEmpty());     //@JsonInclude(JsonInclude.Include.NON_EMPTY) 테스트
    }
}
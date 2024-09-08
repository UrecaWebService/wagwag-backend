package com.wagwag.responseTests;

import com.wagwag.global.exception.errorcode.CommonErrorCode;
import com.wagwag.global.exception.errorcode.ErrorCode;
import com.wagwag.global.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.validation.FieldError;



public class ValidationErrorTest {

    @Test
    public void validationError_fromFieldError() {
        FieldError fieldError = new FieldError("objectName", "field", "Default message");

        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        ErrorResponse.ValidationError validationError = ErrorResponse.ValidationError.of(fieldError);

        assertEquals("field", validationError.getField());
        assertEquals("Default message", validationError.getMessage());

        assertEquals("{\n" +
                "    \"isSuccess\":" + "false"+ ",\n" +
                "    \"code\":\"" + errorCode.getCode() + "\",\n" +
                "    \"message\":\"" + errorCode.getMessage() + "\"\n" +
                "}",
                errorResponse.toString()
                );
    }
}
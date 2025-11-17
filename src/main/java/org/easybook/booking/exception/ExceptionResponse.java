package org.easybook.booking.exception;

import java.util.List;

public record ExceptionResponse(
        String message,
        List<String> validationErrors
) {}

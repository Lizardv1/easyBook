package org.easybook.booking.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final String REQUEST_VALIDATION_FAILED = "Request validation failed";

    @Override
    public Response toResponse(ConstraintViolationException e) {
        final List<String> violationErrors = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionResponse(REQUEST_VALIDATION_FAILED, violationErrors))
                .build();
    }
}

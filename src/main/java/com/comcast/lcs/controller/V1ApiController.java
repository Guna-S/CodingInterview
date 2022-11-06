package com.comcast.lcs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.lcs.config.exception.LcsBadRequestException;
import com.comcast.lcs.config.exception.LcsNotFoundException;
import com.comcast.lcs.config.exception.LcsServerException;
import com.comcast.lcs.dto.ErrorResponse;
import com.comcast.lcs.dto.LcsRequest;
import com.comcast.lcs.dto.LcsResponse;
import com.comcast.lcs.service.LcsService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name="LCS Controller")
public class V1ApiController implements V1Api {

    private final LcsService lcsService;

    @Override
    public LcsResponse findLcs(LcsRequest lcsRequest) {
        return lcsService.findLcs(lcsRequest);
    }

    @ExceptionHandler(value = LcsBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(final LcsBadRequestException badRequestException) {
        return new ErrorResponse(badRequestException.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = LcsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(final LcsNotFoundException notFoundException) {
        return new ErrorResponse(notFoundException.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = LcsServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleServerException(final LcsServerException serverException) {
        return new ErrorResponse(serverException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}

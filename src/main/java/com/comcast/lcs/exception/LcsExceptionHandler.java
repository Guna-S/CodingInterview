package com.comcast.lcs.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.comcast.lcs.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class LcsExceptionHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse errorResponse(final HttpMessageNotReadableException exception){
    log.error("[LCS] : {}", exception.getMessage(), exception);
    return new ErrorResponse("The format of the request is not acceptable");
  }

  @ExceptionHandler(value = LcsBadRequestException.class)
  @ResponseStatus(BAD_REQUEST)
  public ErrorResponse handleBadRequest(final LcsBadRequestException exception) {
    log.error("[LCS] : {}", exception.getMessage(), exception);
    return new ErrorResponse(exception.getMessage());
  }

  @ExceptionHandler(value = LcsNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleNotFound(final LcsNotFoundException exception) {
    log.error("[LCS] : {}", exception.getMessage(), exception);
    return new ErrorResponse(exception.getMessage());
  }

  @ExceptionHandler(value = LcsServerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleServerException(final LcsServerException exception) {
    log.error("[LCS] : {}", exception.getMessage(), exception);
    return new ErrorResponse(exception.getMessage());
  }
}

package com.comcast.lcs.exception;

public class LcsBadRequestException extends RuntimeException {

  private String message;

  public LcsBadRequestException(String message) {
    super(message);
    this.message = message;
  }

  public LcsBadRequestException() {}
}

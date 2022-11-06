package com.comcast.lcs.config.exception;

public class LcsServerException extends RuntimeException {

  private String message;

  public LcsServerException(String message) {
    super(message);
    this.message = message;
  }

  public LcsServerException() {}

}

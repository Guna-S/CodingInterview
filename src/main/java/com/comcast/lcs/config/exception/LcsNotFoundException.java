package com.comcast.lcs.config.exception;

public class LcsNotFoundException extends RuntimeException {

  private String message;

  public LcsNotFoundException(String message) {
    super(message);
    this.message = message;
  }

  public LcsNotFoundException() {}

}

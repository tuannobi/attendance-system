package com.ty.attendancesystem.exception;


public class ServiceException extends RuntimeException {
  public ServiceException(String message, Throwable cause){
    super(message,cause);
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }
}

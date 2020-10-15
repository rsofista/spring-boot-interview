package br.com.compasso.steffen.lucas.springbootinterview.dto;

public class ErrorResponseDto {
  private String error;
  private String message;

  ErrorResponseDto(String error, String message) {
    this.error = error;
    this.message = message;
  }

  public String getError() {
    return this.error;
  }

  public String getMessage() {
    return this.message;
  }
}

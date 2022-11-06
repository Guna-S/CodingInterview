package com.comcast.lcs.controller;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.comcast.lcs.AbstractIT;
import com.comcast.lcs.dto.ErrorResponse;
import com.comcast.lcs.dto.Keys;
import com.comcast.lcs.dto.LcsRequest;
import com.comcast.lcs.dto.LcsResponse;


class V1ApiIT extends AbstractIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @DisplayName("Expect bad request if there is no request body.")
  void verifyEmptyRequest(){
    //var expectedErrorResponse = new ErrorResponse("The format of the request is not acceptable",400);

      webTestClient
          .post()
          .uri("/v1/lcs")
          .contentType(MediaType.APPLICATION_JSON)
          .exchange()
          .expectStatus()
          .isBadRequest();
          //.expectBody(ErrorResponse.class)
          //.isEqualTo(expectedErrorResponse);
  }


  @Test
  @DisplayName("Expect bad request if the set is not unique.")
  void verifyNotUniqueSetRequest(){
    var expectedErrorResponse = new ErrorResponse("SetOfStrings should not be empty",400);

    webTestClient
        .post()
        .uri("/v1/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new LcsRequest(Set.of()))
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(ErrorResponse.class)
        .isEqualTo(expectedErrorResponse);
  }

  @Test
  @DisplayName("Expect not found if the set is not unique.")
  void verifyExceptionIfNoMatchFound(){
    var expectedErrorResponse = new ErrorResponse("No match found",404);

    webTestClient
        .post()
        .uri("/v1/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new LcsRequest(Set.of(new Keys("Comcast"),new Keys("Denver"))))
        .exchange()
        .expectStatus()
        .isNotFound()
        .expectBody(ErrorResponse.class)
        .isEqualTo(expectedErrorResponse);
  }

  @Test
  @DisplayName("Expect successful response returns longest common substring")
  void verifySuccessfulResponse(){
    var lcsResponse = new LcsResponse(Set.of(new Keys("cast")));

    webTestClient
        .post()
        .uri("/v1/lcs")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(new LcsRequest(Set.of(new Keys("comcast"),new Keys("comcastic"), new Keys("broadcaster"))))
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectBody(LcsResponse.class)
        .isEqualTo(lcsResponse);
  }
}
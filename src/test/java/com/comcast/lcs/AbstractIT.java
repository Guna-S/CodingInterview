package com.comcast.lcs;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = LcsApplication.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public abstract class AbstractIT {

}

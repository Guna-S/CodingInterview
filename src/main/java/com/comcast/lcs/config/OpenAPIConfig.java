package com.comcast.lcs.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        version = "1.0.0",
        title = "LCS API",
        description = "API to find the longest common substrings for the given set of strings",
        contact = @Contact(
            email = "gunaseelan.sedhuraman@gmail.com",
            name = "Gunaseelan Sethuraman",
            url = "https://springdoc.org"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://springdoc.org"
        )
    )
)
@Configuration
public class OpenAPIConfig {}

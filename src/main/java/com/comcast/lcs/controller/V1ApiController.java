package com.comcast.lcs.controller;

import org.springframework.web.bind.annotation.RestController;

import com.comcast.lcs.dto.LcsRequest;
import com.comcast.lcs.dto.LcsResponse;
import com.comcast.lcs.service.LcsService;
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
}

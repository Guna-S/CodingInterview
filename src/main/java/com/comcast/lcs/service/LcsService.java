package com.comcast.lcs.service;

import com.comcast.lcs.dto.LcsRequest;
import com.comcast.lcs.dto.LcsResponse;

public interface LcsService {

  LcsResponse findLcs(LcsRequest lcsRequest);
}

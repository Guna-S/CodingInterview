package com.comcast.lcs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

public record LcsRequest(@JsonProperty("setOfStrings") ImmutableList<Keys> keys) {}



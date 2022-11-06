package com.comcast.lcs.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LcsRequest(@JsonProperty("setOfStrings") Set<Keys> keys) {}



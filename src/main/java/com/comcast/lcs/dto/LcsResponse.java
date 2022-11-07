package com.comcast.lcs.dto;

import com.google.common.collect.ImmutableSet;

/**
 * The list of common substrings found.
 */
public record LcsResponse(ImmutableSet<Keys> lcs){}
package com.comcast.lcs.dto;

import java.util.Set;

/**
 * The list of common substrings found.
 */
public record LcsResponse(Set<Keys> lcs){}
package com.comcast.lcs.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.comcast.lcs.config.exception.LcsBadRequestException;
import com.comcast.lcs.config.exception.LcsNotFoundException;
import com.comcast.lcs.dto.Keys;
import com.comcast.lcs.dto.LcsRequest;
import com.comcast.lcs.dto.LcsResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultLcsService implements LcsService {

  @Override
  public LcsResponse findLcs(LcsRequest lcsRequest) {

    if(lcsRequest == null || lcsRequest.keys() == null) {
      throw new LcsBadRequestException("Invalid Format");
    }

    final List<String> wordsList = lcsRequest
        .keys()
        .stream()
        .map(Keys::value)
        .toList();

    if( wordsList == null || wordsList.isEmpty()) {
      throw new LcsBadRequestException("SetOfStrings should not be empty");
    }

    final String commonSubString = getLongestCommonSubstring(wordsList);
    log.info("commonSubString {} ", commonSubString);

    if( Strings.isBlank(commonSubString)) {
      throw new LcsNotFoundException("No match found");
    }

    return new LcsResponse(Set.of(new Keys(commonSubString)));

  }

  private String getLongestCommonSubstring(final List<String> wordsList) {
    final int listSize = wordsList.size();

    final String firstWord = wordsList.get(0);
    final int length = firstWord.length();

    String result = "";
    for( int i = 0; i < length; i++ ) {
      for (int j = i+1 ; j <= length; j++ ) {
        final String substr = firstWord.substring(i,j);
        int index = 1;

        while(index < listSize) {
          if(!wordsList.get(index).contains(substr)) {
            break;
          }
          index++;
        }
        if (index == listSize && result.length() < substr.length())
          result = substr;
      }
    }
    return result;
  }


/*  public static void main(String[] args){
    System.out.println("->"+getLongestCommonSubstring(List.of("grace", "graceful", "disgraceful", "gracefully")));
    System.out.println("->"+getLongestCommonSubstring(List.of("comcast", "comcastic", "broadcaster")));
    System.out.println("->"+getLongestCommonSubstring(List.of("comcast", "denver", "colorado")));
  }*/
}

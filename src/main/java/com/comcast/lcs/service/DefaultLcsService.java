package com.comcast.lcs.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.comcast.lcs.dto.Keys;
import com.comcast.lcs.dto.LcsRequest;
import com.comcast.lcs.dto.LcsResponse;
import com.comcast.lcs.exception.LcsBadRequestException;
import com.comcast.lcs.exception.LcsNotFoundException;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultLcsService implements LcsService {

  @Override
  public LcsResponse findLcs(LcsRequest lcsRequest) {

    if(lcsRequest == null || lcsRequest.keys() == null) {
      throw new LcsBadRequestException("Invalid Format");
    }

    final ImmutableList<String> wordsList = lcsRequest
        .keys()
        .stream()
        .map(Keys::value)
        .collect(ImmutableList.toImmutableList());

    if(wordsList.size() != wordsList.stream().collect(Collectors.toSet()).size()){
      throw new LcsBadRequestException("setOfStrings must be a set");
    }

    if(wordsList.isEmpty()) {
      throw new LcsBadRequestException("setOfStrings should not be empty");
    }

    final Set<String> resultSet = getLCSSet(wordsList);
    log.info("resultSet {} ", resultSet);

    if( resultSet.isEmpty() ) {
      throw new LcsNotFoundException("No match found");
    }

    return new LcsResponse (resultSet.stream().map(Keys::new).collect(ImmutableSet.toImmutableSet()));

  }

  private static String getLongestCommonSubstring(final ImmutableList<String> wordsList) {
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
            System.out.println(" wordsList.get(index) ->" + wordsList.get(index) + " substr " + substr);
            break;
          }
          index++;
        }
        if (index == listSize && result.length() < substr.length())
          result = substr;
      }
      System.out.println( " result -> " + result);
    }
    return result;
  }

  private static Set<String> getLCSSet(final ImmutableList<String> wordsList) {
    final int listSize = wordsList.size();

    final String firstWord = wordsList.get(0);
    final int length = firstWord.length();

    final List<String> list = new ArrayList<>();
    for( int i = 0; i < length; i++ ) {
      String commonStr = "";
      for (int j = i+1 ; j <= length; j++ ) {
        final String substr = firstWord.substring(i,j);
        int index = 1;
        while(index < listSize) {
          if(!wordsList.get(index).contains(substr)) {
            break;
          }
          index++;
        }
        if (index == listSize && commonStr.length() < substr.length())
          commonStr = substr;
      }
      if(!"".equals(commonStr)) {
        final String word = commonStr;
        if(list.isEmpty() || list.stream().noneMatch(str -> str.contains(word))) {
          list.add(word);
        }

      }
    }

    final Set<String> result = list.stream()
        .collect(groupingBy(String::length, Collectors.toCollection(TreeSet::new)))
        .entrySet()
        .stream()
        .max(Entry.comparingByKey())
        .map(Entry::getValue)
        .orElseGet(TreeSet::new);

    return result;

  }

  public static void main(String[] args){

    //System.out.println("->"+ (identifyCommonSubStrOfNStr(new String[] {"comcast","comcastic", "broadcaster"})));
    //System.out.println("->"+ (identifyCommonSubStrOfNStr(new String[] {"ab1cd1","ab2cd2", "ab2cd3"})));
    //System.out.println("->"+ getLongestCommonSubstring(ImmutableList.of("comcast","comcastic", "broadcaster")));
    //System.out.println("->"+ getLongestCommonSubstring(ImmutableList.of("cast","caste", "locaste","case")));
    //System.out.println("->"+ getLongestCommonSubstring(ImmutableList.of("testt1order1","testt2order2", "testt3order3","testt4order4")));
    //System.out.println("->"+ getLongestCommonSubstringSet(ImmutableList.of("ab1cd1","ab2cd2" )));
    //System.out.println("->"+ getLongestCommonSubstringSet(ImmutableList.of("testt1order1","testt2order2", "testt3order3","testt4order4")));
/*    test(ImmutableList.of("testt1order1","testt2order2", "testt3order3","testt4order4"));
    test(ImmutableList.of("comcast","comcastic", "broadcaster"));*/

  }
}

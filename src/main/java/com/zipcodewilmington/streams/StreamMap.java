package com.zipcodewilmington.streams;

import com.zipcodewilmington.streams.anthropoid.Person;
import com.zipcodewilmington.streams.conversions.StreamConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/24/17.
 */
public class StreamMap {
    /**
     * Section 8.3
     * @param someWord - word to convert to Stream<String>
     * @return - a Stream of single characters
     */ //TODO
    public static Stream<String> letters(String someWord) {
        Stream<String> characterStream = someWord.chars().mapToObj(c -> String.valueOf((char) c));
        return characterStream;
    }

    /**
     * @param someWords - variable amount of String arguments
     * @return - a Stream of several Streams of single characters
     */ //TODO
    // LITERALLY COPIED FROM THE MAP SLIDE IN OUR LECTURE NOTES
    public static Stream<Stream<String>> wordsMap(String... someWords) {
        return Stream.of(someWords).map(w -> letters(w));
    }

    /**
     * @param stringArray - variable amount of String arguments
     * @return - a Stream of several Streams of single characters
     */ //TODO
    // ALSO COPIED FROM THE LECTURE NOTES FROM THE FLATMAP SLIDE
    public static Stream<String> wordsFlatMap(String... stringArray) {
        Stream<String> wordStream = Stream.of(stringArray);
        List<String> wordList = wordStream.collect(Collectors.toList());
        return wordList.stream().flatMap(w -> letters(w));
    }
}
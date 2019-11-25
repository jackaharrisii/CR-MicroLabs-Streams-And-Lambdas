package com.zipcodewilmington.streams;

import com.zipcodewilmington.streams.anthropoid.Person;
import com.zipcodewilmington.streams.anthropoid.PersonFactory;
import com.zipcodewilmington.streams.anthropoid.PersonWarehouse;
import com.zipcodewilmington.streams.tools.RandomUtils;
import com.zipcodewilmington.streams.tools.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 5/2/17.
 */
public class StreamFilter {
    private final Stream<Person> personStream;
    public final String startingCharacter;

    /**
     * No arg constructor
     */ //TODO - construct person stream of 100 person objects; startingCharacter is a random capital letter
    public StreamFilter() {
        this.personStream = Stream.generate(new PersonFactory()::createRandomPerson).limit(100);
        this.startingCharacter = randomUpperCase();
    }

    public String randomUpperCase(){
        // PROBABLY AN EASIER WAY TO DO THIS......
        Random r = new Random();
        int letterIndex = r.nextInt(26);
        return String.valueOf((char)((int)'A' + letterIndex));
    }

    /**
     * @param people - Array of person objects
     * @param startingCharacter - character to filter by
     */ //TODO
    public StreamFilter(Person[] people, Character startingCharacter) {
        this.personStream = Arrays.stream(people);
        this.startingCharacter = startingCharacter.toString();
    }

    /**
     * @param people - List of person objects
     * @param startingCharacter - character to filter by
     */ //TODO
    public StreamFilter(List<Person> people, Character startingCharacter) {
        this.personStream = people.stream();
        this.startingCharacter = startingCharacter.toString();
    }


    /**
     * @param people - Stream of person objects
     * @param startingCharacter - character to filter by
     */ // I took care of the easy constructor (͡° ͜ʖ ͡°)
    public StreamFilter(Stream<Person> people, Character startingCharacter) {
        this.personStream = people;
        this.startingCharacter = startingCharacter.toString();
    }


    /**
     * Using multi-line lambda syntax
     * @return a list of person object whose name starts with `this.startingCharacter`
     */ //TODO
    public List<Person> toListMultiLine() {
        // ORIGINALLY USED .equals, BUT IT ONLY PASSED THE TESTS SOME TIMES
        return this.personStream.filter(person -> {
            String firstLetter = person.getName().substring(0,1);
            if (firstLetter.equalsIgnoreCase(startingCharacter)) return true;
            return false;
        }).collect(Collectors.toList());
    }


    /**
     * Using one-line lambda syntax
     * @return a list of person objects whose name starts with `this.startingCharacter`
     */ //TODO
    public List<Person> toListOneLine() {
        // ORIGINALLY USED .equals, BUT IT ONLY PASSED THE TESTS SOME TIMES
        // STILL OCCASIONALLY MISSING TESTS, BUT DIFFICULT TO SEE WHAT THE DIFFERENCE IS BECAUSE THE TESTS DON'T HAVE A "SHOW DIFFERENCE" LINK LIKE USUAL
        return this.personStream
                .filter(person -> person.getName().substring(0, 1).equalsIgnoreCase(startingCharacter))
                .collect(Collectors.toList());
    }


    /**
     * Using one-line lambda syntax
     * @return an array of person object whose name starts with `this.startingCharacter`
     */ //TODO
    public Person[] toArrayOneLine() {
        // TRIED SIMPLY CALLING THE LIST AND .toArray(), BUT IT BROKE BOTH TESTS.....
        // THIS IS STILL OCCASIONALLY BREAKING, AND I DON'T KNOW WHY
        // SOMETHING ABOUT PALINDROMES ON LINE 52 OF TestStreamFilter.java
        return this.personStream
                .filter(person -> person.getName().substring(0, 1).equalsIgnoreCase((startingCharacter)))
                .toArray(Person[]::new);
    }


    /**
     * Using multi-line lambda syntax
     * @return an array of person object whose name starts with `this.startingCharacter`
     */ //TODO
    public Person[] toArrayMultiLine() {
        return this.personStream.filter(person -> {
            String firstLetter = person.getName().substring(0,1);
            if (firstLetter.equalsIgnoreCase(startingCharacter)) return true;
            return false;
        }).toArray(Person[]::new);
    }

}

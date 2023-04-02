package com.example.dictionarytest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fraDictionary {

    public static void main(String[] args, String word) {

        float startTime = System.nanoTime();

        String secondLanguage, stringLanguage;

        for (int a = 0; a < 6; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    findWord(word, secondLanguage, stringLanguage, 1, 1000000);                }
                case 1 -> {
                    secondLanguage = "deu";
                    stringLanguage = "Deutsch";
                    findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 2 -> {
                    secondLanguage = "ell";
                    stringLanguage = "Modern Greek";
                    findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 3 -> {
                    secondLanguage = "eng";
                    stringLanguage = "English";
                    findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 4 -> {
                    secondLanguage = "ita";
                    stringLanguage = "Italian";
                    findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 5 -> {
                    secondLanguage = "swe";
                    stringLanguage = "Swedish";
                    findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
            }
        }

        final float duration = System.nanoTime() - startTime;
        System.out.println("\n"+duration/1000000000);
    }


    static void findWord(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint){

        boolean found = false;
        String fra = "fra";
        String placeHolder = ".dict";
        String fileName = fra+"-"+secondLanguage+placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches() && line.startsWith(word)) { //line starts with word we are looking for, and contains two slashes.
                        System.out.println("\n==========The word exists in French-"+ stringLanguage +" Dictionary: ======\n");
                        found = true;
                        System.out.println(line); //print the headword
                        while ((line = br.readLine()) != null) { //print the lines until the next headword.
                            matcher = pattern.matcher(line);
                            if (matcher.matches()) { //check if the line is a headword.
                                break;
                            }
                            System.out.println(line); //print the line.
                        }
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
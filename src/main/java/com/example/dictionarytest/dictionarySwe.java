package com.example.dictionarytest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionarySwe {

    public String isItSwedish(String word){
        String language = "Swedish";
        System.out.println(swedish(word));
        return language;
    }

    static String swedish(String word){
        String secondLanguage, stringLanguage;
        String allTranslations = "";

        for (int a = 0; a < 6; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    allTranslations += findWord(word, secondLanguage, stringLanguage, 1, 1000000);                }
                case 1 -> {
                    secondLanguage = "deu";
                    stringLanguage = "Deutsch";
                    allTranslations += findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 2 -> {
                    secondLanguage = "ell";
                    stringLanguage = "Modern Greek";
                    allTranslations += findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 3 -> {
                    secondLanguage = "eng";
                    stringLanguage = "English";
                    allTranslations += findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 4 -> {
                    secondLanguage = "ita";
                    stringLanguage = "Italian";
                    allTranslations += findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
                case 5 -> {
                    secondLanguage = "fra";
                    stringLanguage = "French";
                    allTranslations += findWord(word, secondLanguage, stringLanguage, 1, 1000000);
                }
            }
        }
        return allTranslations;
    }

    static String findWord(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint){

        String output = "";

        boolean found = false;
        String swe = "swe";
        String placeHolder = ".dict";
        String fileName = swe + "-" + secondLanguage + placeHolder;

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
                    if (matcher.matches() && line.startsWith(word+" /")) { //line starts with word we are looking for, and contains two slashes.
                        output = "\n==========The word exists in Sweedish-"+ stringLanguage +" Dictionary: ======\n";
                        found = true;
                        output += line;
                        while ((line = br.readLine()) != null) { //print the lines until the next headword.
                            matcher = pattern.matcher(line);
                            if (matcher.matches()) { //check if the line is a headword.
                                break;
                            }
                            output += ("\n"+line+"\n");
                        }
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
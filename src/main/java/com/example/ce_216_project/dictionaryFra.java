package com.example.ce_216_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionaryFra {

    public String isItFrench(String word){
        String language = "";
        String placeHolder = french(word);
        if (!placeHolder.matches("")){
            System.out.println(placeHolder);
            language = "French";
            return language;
        }
        return language;
    }

    public String french(String word){
        String secondLanguage, stringLanguage;
        StringBuilder allTranslations = new StringBuilder();

        for (int a = 0; a < 6; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 1 -> {
                    secondLanguage = "deu";
                    stringLanguage = "Deutsch";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 2 -> {
                    secondLanguage = "ell";
                    stringLanguage = "Modern Greek";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 3 -> {
                    secondLanguage = "eng";
                    stringLanguage = "English";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 4 -> {
                    secondLanguage = "ita";
                    stringLanguage = "Italian";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 5 -> {
                    secondLanguage = "swe";
                    stringLanguage = "Swedish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
            }
        }
        return allTranslations.toString();
    }

    static String findWord(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint){

        StringBuilder output = new StringBuilder();

        boolean found = false;
        String fra = "fra";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + fra+"-"+secondLanguage+placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            String wordWithSlash = word + " /";
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
                        output = new StringBuilder("\n==========The word exists in French-" + stringLanguage + " Dictionary: ======\n");
                        found = true;
                        output.append(line);
                        while ((line = br.readLine()) != null) { //print the lines until the next headword.
                            matcher = pattern.matcher(line);
                            if (matcher.matches()) { //check if the line is a headword.
                                break;
                            }
                            output.append("\n").append(line).append("\n");
                        }
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}

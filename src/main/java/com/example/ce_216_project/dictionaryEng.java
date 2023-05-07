package com.example.ce_216_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionaryEng {


    public String english(String word) {

        SmartSearch Ss = new SmartSearch();
        String secondLanguage, stringLanguage;
        StringBuilder allTranslations = new StringBuilder();

        for (int a = 0; a < 6; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathEngTur(word), Ss.limiterPathEngTur(word)));
                }
                case 1 -> {
                    secondLanguage = "deu";
                    stringLanguage = "Deutsch";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathEngGer(word), Ss.limiterPathEngGer(word)));
                }
                case 2 -> {
                    secondLanguage = "ell";
                    stringLanguage = "Modern Greek";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathEngEll(word), Ss.limiterPathEngEll(word)));
                }
                case 3 -> {
                    secondLanguage = "fra";
                    stringLanguage = "French";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathEngFra(word), Ss.limiterPathEngFra(word)));
                }
                case 4 -> {
                    secondLanguage = "ita";
                    stringLanguage = "Italian";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathEngIta(word), Ss.limiterPathEngIta(word)));
                }
                case 5 -> {
                    secondLanguage = "swe";
                    stringLanguage = "Swedish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathEngSwe(word), Ss.limiterPathEngSwe(word)));
                }
            }
        }
        return allTranslations.toString();
    }

    static String findWord(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint) {

        StringBuilder output = new StringBuilder();

        boolean found = false;
        String eng = "eng";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + eng + "-" + secondLanguage + placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String wordWithSlash = word + " /";
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
                        output = new StringBuilder("\n==========The word exists in English-" + stringLanguage + " Dictionary: ======\n");
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
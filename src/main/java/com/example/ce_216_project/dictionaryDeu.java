package com.example.ce_216_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionaryDeu {

    String language = "Deutch";
    String lanShort = "deu";
    SmartSearch Ss = new SmartSearch();

    public String deutsch(String word) {


        String secondLanguage, stringLanguage;
        StringBuilder allTranslations = new StringBuilder();


        for (int a = 0; a < 6; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 10000000));
                }
                case 1 -> {
                    secondLanguage = "eng";
                    stringLanguage = "English";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, Ss.shortPathDeuEng(word, true), Ss.shortPathDeuEng(word, false)));
                }
                case 2 -> {
                    secondLanguage = "ell";
                    stringLanguage = "Modern Greek";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 10000000));
                }
                case 3 -> {
                    secondLanguage = "fra";
                    stringLanguage = "French";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 10000000));
                }
                case 4 -> {
                    secondLanguage = "ita";
                    stringLanguage = "Italian";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 10000000));
                }
                case 5 -> {
                    secondLanguage = "swe";
                    stringLanguage = "Swedish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 10000000));
                }
            }
        }
        return allTranslations.toString();
    }

    public String findWord(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint) {

        String output = "";

        boolean found = false;
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + lanShort + "-" + secondLanguage + placeHolder;


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String wordWithSlash = word + " /";
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {

                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
                        output = "\n==========The word exists in Deutsch-" + stringLanguage + " Dictionary: ======\n";
                        found = true;
                        output += line;
                        StringBuilder outputBuilder = new StringBuilder(output);
                        while ((line = br.readLine()) != null) { //print the lines until the next headword.
                            matcher = pattern.matcher(line);
                            if (matcher.matches()) { //check if the line is a headword.
                                break;
                            }
                            outputBuilder.append("\n").append(line).append("\n");
                        }
                        output = outputBuilder.toString();
                        break;
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
package com.example.ce_216_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SynonymFinder {
    dictionaryDeu dictionaryDeu=new dictionaryDeu();


    public List<String> sinonimBul(String input) throws NullPointerException{
        Path filePath = Path.of("src/main/resources/dictionaries/deu-eng.dict");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> lineNumbers = new ArrayList<>();
        List<String> synonymsList = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("/")) {
                String headWord = line.split("/")[0];
                lineNumbers.add(headWord);
                lineNumbers.add(Integer.toString(i));
            }
        }

        String word = input + " ";
        if (lineNumbers.contains(word)) {
            int index = lineNumbers.indexOf(word);
            if (index == -1)
                return synonymsList;
            int indexStr = Integer.parseInt(lineNumbers.get(index + 1));
            System.out.println(lines.get(indexStr));
            for (int i = 0; i < 4; i++) {
                if (lines.get(indexStr + i).contains("Synonym")) {
                    String synonymsLine = lines.get(indexStr + i);
                    String[] synonyms = synonymsLine.replaceAll("[{}]", "").split(":")[1].split(",");
                    for (int j = 0; j < synonyms.length; j++) {
                        synonymsList.add(synonyms[j].trim());
                    }
                    return synonymsList;
                }
            }
        }
        return synonymsList;
    }
    public int findLineInDeu(String word, String langaugeCode, int startPoint, int limitPoint){

        int lineNumber = 1;

        boolean found = false;
        String placeHolder = ".dict";
        String fileName = langaugeCode +"-deu"+placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches() && line.startsWith(word+" /")) { //line starts with word we are looking for, and contains two slashes.
                        found = true;
                        while ((line = br.readLine()) != null) { //print the lines until the next headword.
                            matcher = pattern.matcher(line);
                            if (matcher.matches()) { //check if the line is a headword.
                                break;
                            }
                        }
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNumber;
    }

    public String getWordFromDeu(int startLine, String languageCode) {
        String fileName = languageCode+"-deu.dict";
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String output;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == startLine) {
                    if (line.matches("^\\d.*")) {  // Check if line starts with an integer
                        output = line.replaceFirst("^\\d+.+\\s", "");  // Remove integer and whitespace
                        output = (output);
                        return output;
                    } else {
                        output = (line);
                        return output;  // Return original line
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;  // Line number not found
    }
    public String sinonimCevirici(List<String> synonymsList, String languageCode) {
        StringBuilder allTranslations = new StringBuilder();
        for(int i= 0; i<synonymsList.size(); i++){
            switch (languageCode){

                case "eng":
                    allTranslations.append(dictionaryDeu.findWord(synonymsList.get(i), "eng","English", 1, 10000000));
                    break;
                case "ell":
                    allTranslations.append(dictionaryDeu.findWord(synonymsList.get(i), "ell","Greek", 1, 10000000));
                    break;
                case "fra":
                    allTranslations.append(dictionaryDeu.findWord(synonymsList.get(i), "fra","French", 1, 10000000));
                    break;
                case "ita":
                    allTranslations.append(dictionaryDeu.findWord(synonymsList.get(i), "ita","Italian", 1, 10000000));
                    break;
                case "swe":
                    allTranslations.append(dictionaryDeu.findWord(synonymsList.get(i), "swe","Swedish", 1, 10000000));
                    break;
                case "tur":
                    allTranslations.append(dictionaryDeu.findWord(synonymsList.get(i), "tur","Turkish", 1, 10000000));
                    break;
            }
        }

        return allTranslations.toString();
    }
}
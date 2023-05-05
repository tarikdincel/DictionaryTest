package com.example.dictionarytest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class denemeKismi {

    public static void main(String[] args) {

        Path filePath = Path.of("deu-eng.dict");
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> lineNumbers = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("/")) {
                String headWord = line.split("/")[0];
                lineNumbers.add(headWord);
                lineNumbers.add(Integer.toString(i));
            }
        }

        String word = "As ";
        if (lineNumbers.contains(word)) {
            int index = lineNumbers.indexOf(word);
            if (index == -1)
                return;
            int indexStr = Integer.parseInt(lineNumbers.get(index + 1));
            System.out.println(lines.get(indexStr));
            for (int i = 0; i < 4; i++) {
                if (lines.get(indexStr + i).contains("Synonym")) {
                    System.out.println(lines.get(indexStr + i));
                    return;
                }
            }
        }

        dictionaryEng engDictionary = new dictionaryEng();
        dictionaryFra fraDictionary = new dictionaryFra();
        dictionaryTur turDictionary = new dictionaryTur();
        dictionaryEll ellDictionary = new dictionaryEll();
        dictionarySwe sweDictionary = new dictionarySwe();
        dictionaryDeu deuDictionary = new dictionaryDeu();
        dictionaryIta itaDictionary = new dictionaryIta();

        Scanner input = new Scanner(System.in);
        String tryWord = input.next();
        char firstLetter = tryWord.charAt(0);
        int alphabet = (char) ((int) firstLetter);
        System.out.println(alphabet);

        if (97 <= alphabet && alphabet <= 122){
            System.out.println(turDictionary.isItTurkish(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(engDictionary.isItEnglish(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(fraDictionary.isItFrench(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(sweDictionary.isItSwedish(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(deuDictionary.isItDeutsch(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(itaDictionary.isItItalian(tryWord));
            System.out.println("-----------------------------------------------------------------");
        }
        else {
            System.out.println(turDictionary.isItTurkish(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(ellDictionary.isItGreek(tryWord));
            System.out.println("-----------------------------------------------------------------");
            System.out.println(sweDictionary.isItSwedish(tryWord));
            System.out.println("-----------------------------------------------------------------");


        }

    }
}
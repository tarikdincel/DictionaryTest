package com.example.dictionarytest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class denemeKismi {

    public static void main(String[] args) {


        SynonymFinder findSynonym= new SynonymFinder();
        int baseline= findSynonym.findLineInDeu("géant", "fra", 1, 1000000);
        String newWord= findSynonym.getWordFromDeu(baseline, "fra");
        findSynonym.sinonimBul(newWord);

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
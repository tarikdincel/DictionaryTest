package com.example.dictionarytest;

import java.util.Scanner;
import javafx.scene.image.Image;


public class denemeKısmı {

    public static void main(String[] args) {

        dictionaryEng engDictionary = new dictionaryEng();
        dictionaryFra fraDictionary = new dictionaryFra();
        dictionaryTur turDictionary = new dictionaryTur();
        dictionaryEll ellDictionary = new dictionaryEll();
        dictionarySwe sweDictionary = new dictionarySwe();

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
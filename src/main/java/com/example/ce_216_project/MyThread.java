package com.example.ce_216_project;

public class MyThread extends Thread {

    dictionaryEng engDictionary;
    dictionaryFra fraDictionary;
    dictionaryTur turDictionary;
    dictionaryEll ellDictionary;
    dictionarySwe sweDictionary;
    dictionaryDeu deuDictionary;
    dictionaryIta itaDictionary;

    String srcWord;
    int type;
    public String result;
    public String result1;
    public String result2;
    public String result3;

    public MyThread(String srcWord, int type) {
        engDictionary = new dictionaryEng();
        fraDictionary = new dictionaryFra();
        turDictionary = new dictionaryTur();
        ellDictionary = new dictionaryEll();
        sweDictionary = new dictionarySwe();
        deuDictionary = new dictionaryDeu();
        itaDictionary = new dictionaryIta();

        this.type = type;
        this.srcWord = srcWord;
    }

    public void function() {
        String s = srcWord;
        int a = type;
        if (a == 0) {
            result = deuDictionary.deutsch(s);
            result1 = itaDictionary.italian(s);
        } else if (a == 1) {
            result = fraDictionary.french(s);
            result1 = engDictionary.english(s);
        } else {
            result = sweDictionary.swedish(s);
            result1 = turDictionary.turkish(s);
            result2 = ellDictionary.greek(s);
        }
    }


    @Override
    public void run() {
        function();
    }
}

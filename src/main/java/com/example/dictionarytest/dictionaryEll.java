package com.example.dictionarytest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionaryEll {

    public String isItGreek(String word){
        String language = "Modern Greek";
        System.out.println(greek(word));
        return language;
    }

    static String greek(String word){

        int baseLine = findLineInEnglish(word, 1, 1000000);
        findWordInEnglish(word, baseLine-1);
        String fakeHeadWord = findFakeHeadWordInGreek(word, baseLine-1);
        String newWord = (getWordFromEnglish(baseLine));

        String secondLanguage, stringLanguage;
        String allTranslations = "";

        for (int a = 0; a < 5; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "deu";
                    stringLanguage = "Deutsch";
                    allTranslations += findWordFromEnglish(newWord, secondLanguage, stringLanguage, halfSplitterEngGer(newWord), 10000000, fakeHeadWord);
                }
                case 1 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    allTranslations += findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngTur(newWord), limiterPathEngTur(newWord), fakeHeadWord);
                }
                case 2 -> {
                    secondLanguage = "fra";
                    stringLanguage = "French";
                    allTranslations += findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngFra(newWord), limiterPathEngFra(newWord), fakeHeadWord);
                }
                case 3 -> {
                    secondLanguage = "ita";
                    stringLanguage = "Italian";
                    allTranslations += findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngIta(newWord), limiterPathEngIta(newWord), fakeHeadWord);
                }
                case 4 -> {
                    secondLanguage = "swe";
                    stringLanguage = "Swedish";
                    allTranslations += findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngSwe(newWord), limiterPathEngSwe(newWord), fakeHeadWord);
                }
            }
        }
        return allTranslations;
    }

    static int findLineInEnglish(String word, int startPoint, int limitPoint){

        int lineNumber = 1;

        boolean found = false;
        String ell = "ell";
        String placeHolder = ".dict";
        String fileName = ell +"-eng"+placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
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

    static String findWordInEnglish(String word, int startPoint){

        String output="";

        boolean found = false;
        String ell = "ell";
        String placeHolder = ".dict";
        String fileName = ell+"-eng"+placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches() && line.startsWith(word+" /")) { //line starts with word we are looking for, and contains two slashes.
                        output = "\n==========The word exists in Modern Greek-English Dictionary: ======\n";
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

    static String findFakeHeadWordInGreek(String word, int startPoint){

        String output="";

        boolean found = false;
        String ell = "ell";
        String placeHolder = ".dict";
        String fileName = ell +"-eng"+placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches() && line.startsWith(word+" /")) { //line starts with word we are looking for, and contains two slashes.
                        found = true;
                        output = line;
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    static String getWordFromEnglish(int startLine){
        try(BufferedReader reader = new BufferedReader(new FileReader("ell-eng.dict"))) {
            String line = "";
            String output = "";
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == startLine) {
                    if (line.matches("^\\d.*")) {  // Check if line starts with an integer
                        output = line.replaceFirst("^\\d+.+\\s", "");  // Remove integer and whitespace
                        output = (output+" /");
                        return output;
                    } else {
                        output = (line+" /");
                        return output;  // Return original line
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // Line number not found
    }

    static String findWordFromEnglish(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint, String fakeHeadWord){

        String output = "";

        boolean found = false;
        String eng = "eng";
        String placeHolder = ".dict";
        String fileName = eng+"-"+secondLanguage+placeHolder;

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
                        output = "\n==========The word exists in Modern Greek-"+ stringLanguage +" Dictionary: ======\n";
                        found = true;
                        output += fakeHeadWord;
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

    static int halfSplitterEngGer (String word){
        int firstHalf = 1;
        int secondHalf = 1000000;
        char firstLetter = word.charAt(0);
        int splitterLetter = (char) ((int) firstLetter);
        if (97 <= splitterLetter && splitterLetter <= 110){
            return firstHalf;
        }
        else {
            return secondHalf;
        }
    }

    static int shortPathEngTur (String word) {
        final int A = 2;
        final int B = 6059;
        final int C = 12998;
        final int D = 24411;
        final int E = 30289;
        final int F = 34514;
        final int G = 39615;
        final int H = 43539;
        final int I = 48043;
        final int J = 52543;
        final int K = 53694;
        final int L = 54739;
        final int M = 58690;
        final int N = 64574;
        final int O = 66666;
        final int P = 69279;
        final int Q = 79583;
        final int R = 80297;
        final int S = 86237;
        final int T = 102124;
        final int U = 109443;
        final int V = 112667;
        final int W = 114859;
        final int X = 118542;
        final int Y = 118637;
        final int Z = 119071;

        char firstLetter = word.charAt(0);

        return switch (firstLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'x' -> X;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }
    static int limiterPathEngTur (String word) {
        final int A = 2;
        final int B = 6059;
        final int C = 12998;
        final int D = 24411;
        final int E = 30289;
        final int F = 34514;
        final int G = 39615;
        final int H = 43539;
        final int I = 48043;
        final int J = 52543;
        final int K = 53694;
        final int L = 54739;
        final int M = 58690;
        final int N = 64574;
        final int O = 66666;
        final int P = 69279;
        final int Q = 79583;
        final int R = 80297;
        final int S = 86237;
        final int T = 102124;
        final int U = 109443;
        final int V = 112667;
        final int W = 114859;
        final int X = 118542;
        final int Y = 118637;
        final int Z = 119071;

        char firstLetter = word.charAt(0);
        char limiterLetter = (char) ((int) firstLetter +1);

        return switch (limiterLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'x' -> X;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }

    static int shortPathEngFra (String word) {
        final int A = 2508;
        final int B = 4943;
        final int C = 6126;
        final int D = 7602;
        final int E = 8470;
        final int F = 9020;
        final int G = 9838;
        final int H = 10437;
        final int I = 11031;
        final int J = 11576;
        final int K = 11682;
        final int L = 11782;
        final int M = 12301;
        final int N = 13137;
        final int O = 13464;
        final int P = 13867;
        final int Q = 15164;
        final int R = 15242;
        final int S = 16136;
        final int T = 18016;
        final int U = 19008;
        final int V = 19175;
        final int W = 19400;
        final int X = 19959;
        final int Y = 19963;
        final int Z = 20028;

        char firstLetter = word.charAt(0);

        return switch (firstLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'x' -> X;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }
    static int limiterPathEngFra (String word) {
        final int A = 2508;
        final int B = 4943;
        final int C = 6126;
        final int D = 7602;
        final int E = 8470;
        final int F = 9020;
        final int G = 9838;
        final int H = 10437;
        final int I = 11031;
        final int J = 11576;
        final int K = 11682;
        final int L = 11782;
        final int M = 12301;
        final int N = 13137;
        final int O = 13464;
        final int P = 13867;
        final int Q = 15164;
        final int R = 15242;
        final int S = 16136;
        final int T = 18016;
        final int U = 19008;
        final int V = 19175;
        final int W = 19400;
        final int X = 19959;
        final int Y = 19963;
        final int Z = 20028;

        char firstLetter = word.charAt(0);
        char limiterLetter = (char) ((int) firstLetter +1);

        return switch (limiterLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'x' -> X;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }

    static int shortPathEngSwe (String word) {
        final int A = 502;
        final int B = 1582;
        final int C = 2453;
        final int D = 3554;
        final int E = 4106;
        final int F = 4479;
        final int G = 5004;
        final int H = 5400;
        final int I = 5873;
        final int J = 6176;
        final int K = 6260;
        final int L = 6336;
        final int M = 6739;
        final int N = 7275;
        final int O = 7557;
        final int P = 7836;
        final int Q = 8736;
        final int R = 8774;
        final int S = 9314;
        final int T = 10579;
        final int U = 11218;
        final int V = 11361;
        final int W = 11515;
        final int X = 11913;
        final int Y = 11915;
        final int Z = 11960;

        char firstLetter = word.charAt(0);

        return switch (firstLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'x' -> X;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }
    static int limiterPathEngSwe (String word) {
        final int A = 502;
        final int B = 1582;
        final int C = 2453;
        final int D = 3554;
        final int E = 4106;
        final int F = 4479;
        final int G = 5004;
        final int H = 5400;
        final int I = 5873;
        final int J = 6176;
        final int K = 6260;
        final int L = 6336;
        final int M = 6739;
        final int N = 7275;
        final int O = 7557;
        final int P = 7836;
        final int Q = 8736;
        final int R = 8774;
        final int S = 9314;
        final int T = 10579;
        final int U = 11218;
        final int V = 11361;
        final int W = 11515;
        final int X = 11913;
        final int Y = 11915;
        final int Z = 11960;

        char firstLetter = word.charAt(0);
        char limiterLetter = (char) ((int) firstLetter +1);

        return switch (limiterLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'x' -> X;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }

    static int shortPathEngIta (String word) {
        final int A = 638;
        final int B = 1763;
        final int C = 2522;
        final int D = 3389;
        final int E = 3890;
        final int F = 4246;
        final int G = 4718;
        final int H = 5046;
        final int I = 5360;
        final int J = 5569;
        final int K = 5626;
        final int L = 5685;
        final int M = 5997;
        final int N = 6372;
        final int O = 6550;
        final int P = 6764;
        final int Q = 7451;
        final int R = 7483;
        final int S = 7880;
        final int T = 8836;
        final int U = 9347;
        final int V = 9434;
        final int W = 9523;
        final int Y = 9799;
        final int Z = 9834;

        char firstLetter = word.charAt(0);

        return switch (firstLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }
    static int limiterPathEngIta (String word) {
        final int A = 638;
        final int B = 1763;
        final int C = 2522;
        final int D = 3389;
        final int E = 3890;
        final int F = 4246;
        final int G = 4718;
        final int H = 5046;
        final int I = 5360;
        final int J = 5569;
        final int K = 5626;
        final int L = 5685;
        final int M = 5997;
        final int N = 6372;
        final int O = 6550;
        final int P = 6764;
        final int Q = 7451;
        final int R = 7483;
        final int S = 7880;
        final int T = 8836;
        final int U = 9347;
        final int V = 9434;
        final int W = 9523;
        final int Y = 9799;
        final int Z = 9834;

        char firstLetter = word.charAt(0);
        char limiterLetter = (char) ((int) firstLetter +1);

        return switch (limiterLetter) {
            case 'a' -> A;
            case 'b' -> B;
            case 'c' -> C;
            case 'd' -> D;
            case 'e' -> E;
            case 'f' -> F;
            case 'g' -> G;
            case 'h' -> H;
            case 'i' -> I;
            case 'j' -> J;
            case 'k' -> K;
            case 'l' -> L;
            case 'm' -> M;
            case 'n' -> N;
            case 'o' -> O;
            case 'p' -> P;
            case 'q' -> Q;
            case 'r' -> R;
            case 's' -> S;
            case 't' -> T;
            case 'u' -> U;
            case 'v' -> V;
            case 'w' -> W;
            case 'y' -> Y;
            case 'z' -> Z;
            default -> -1;
        };
    }
}
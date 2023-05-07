package com.example.ce_216_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionaryTur {


    public String turkish(String word) {
        StringBuilder allTranslations = new StringBuilder();
        String notFound = "";
        int baseLine = findLineInEnglish(word, 1, 1000000);
        String newWord = (getWordFromEnglish(baseLine));
        if (newWord != null) {
            allTranslations = new StringBuilder(findWordInEnglish(word, baseLine - 1));
            String fakeHeadWord = findFakeHeadWordInTurkish(word, baseLine - 1);

            String secondLanguage, stringLanguage;

            for (int a = 0; a < 5; a++) {
                switch (a) {
                    case 0 -> {
                        secondLanguage = "deu";
                        stringLanguage = "Deutsch";
                        allTranslations.append(findWordFromEnglish(newWord, secondLanguage, stringLanguage, halfSplitterEngGer(newWord), 10000000, fakeHeadWord));
                    }
                    case 1 -> {
                        secondLanguage = "ell";
                        stringLanguage = "Modern Greek";
                        allTranslations.append(findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngEll(newWord), limiterPathEngEll(newWord), fakeHeadWord));
                    }
                    case 2 -> {
                        secondLanguage = "fra";
                        stringLanguage = "French";
                        allTranslations.append(findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngFra(newWord), limiterPathEngFra(newWord), fakeHeadWord));
                    }
                    case 3 -> {
                        secondLanguage = "ita";
                        stringLanguage = "Italian";
                        allTranslations.append(findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngIta(newWord), limiterPathEngIta(newWord), fakeHeadWord));
                    }
                    case 4 -> {
                        secondLanguage = "swe";
                        stringLanguage = "Swedish";
                        allTranslations.append(findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngSwe(newWord), limiterPathEngSwe(newWord), fakeHeadWord));
                    }
                }
            }
            return allTranslations.toString();
        }
        return notFound;
    }

    static int findLineInEnglish(String word, int startPoint, int limitPoint) {

        int lineNumber = 1;

        boolean found = false;
        String tur = "tur";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + tur + "-eng" + placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String wordWithSlash = word + " /";
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
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

    static String findWordInEnglish(String word, int startPoint) {

        StringBuilder output = new StringBuilder();

        boolean found = false;
        String tur = "tur";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + tur + "-eng" + placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            String wordWithSlash = word + " /";
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
                        output = new StringBuilder("\n==========The word exists in Turkish-English Dictionary: ======\n");
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

    static String findFakeHeadWordInTurkish(String word, int startPoint) {

        String output = "";

        boolean found = false;
        String tur = "tur";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + tur + "-eng" + placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            String wordWithSlash = word + " /";
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
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

    static String getWordFromEnglish(int startLine) {
        String fileName = "src/main/resources/dictionaries/" + "tur" + "-eng" + ".dict";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String output;
            int currentLineNumber = 0;

            while ((line = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber == startLine) {
                    if (line.matches("^\\d.*")) {  // Check if line starts with an integer
                        output = line.replaceFirst("^\\d+.+\\s", "");  // Remove integer and whitespace
                        output = (output + " /");
                        return output;
                    } else {
                        output = (line + " /");
                        return output;  // Return original line
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;  // Line number not found
    }

    static String findWordFromEnglish(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint, String fakeHeadWord) {

        StringBuilder output = new StringBuilder();

        boolean found = false;
        String eng = "eng";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + eng + "-" + secondLanguage + placeHolder;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            String wordWithSlash = word;
            while ((line = br.readLine()) != null) {
                if (lineNumber >= startPoint && lineNumber < limitPoint) {
                    if (found) { //to ignore the other words that startsWith the word we are looking for. ("high" and "highway" i.e.).
                        break;   //it works because dictionaries are in alphabetical order. so our word is always the shortest one.
                    }
                    Pattern pattern = Pattern.compile("(.*\\w.*)\\s*/.*"); //a string with exactly two slashes (/).
                    Matcher matcher = pattern.matcher(line);
                    if (line.startsWith(wordWithSlash) && matcher.matches()) { //line starts with word we are looking for, and contains two slashes.
                        output = new StringBuilder("\n==========The word exists in Turkish-" + stringLanguage + " Dictionary: ======\n");
                        found = true;
                        output.append(fakeHeadWord);
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

    static int halfSplitterEngGer(String word) {
        int firstHalf = 1;
        int secondHalf = 1000000;
        char firstLetter = word.charAt(0);
        int splitterLetter = (char) ((int) firstLetter);
        if (97 <= splitterLetter && splitterLetter <= 110) {
            return firstHalf;
        } else {
            return secondHalf;
        }
    }

    static int shortPathEngEll(String word) {
        final int A = 4;
        final int B = 3721;
        final int C = 6495;
        final int D = 10145;
        final int E = 12373;
        final int F = 13906;
        final int G = 15959;
        final int H = 17593;
        final int I = 19224;
        final int J = 21548;
        final int K = 21828;
        final int L = 22063;
        final int M = 23563;
        final int N = 25636;
        final int O = 26350;
        final int P = 27485;
        final int Q = 30567;
        final int R = 30715;
        final int S = 32829;
        final int T = 37537;
        final int U = 40009;
        final int V = 40755;
        final int W = 41401;
        final int Y = 41951;

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
            default -> -1;
        };
    }

    static int limiterPathEngEll(String word) {
        final int A = 4;
        final int B = 3721;
        final int C = 6495;
        final int D = 10145;
        final int E = 12373;
        final int F = 13906;
        final int G = 15959;
        final int H = 17593;
        final int I = 19224;
        final int J = 21548;
        final int K = 21828;
        final int L = 22063;
        final int M = 23563;
        final int N = 25636;
        final int O = 26350;
        final int P = 27485;
        final int Q = 30567;
        final int R = 30715;
        final int S = 32829;
        final int T = 37537;
        final int U = 40009;
        final int V = 40755;
        final int W = 41401;
        final int Y = 41951;

        char firstLetter = word.charAt(0);
        char limiterLetter = (char) ((int) firstLetter + 1);

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
            default -> -1;
        };
    }

    static int shortPathEngFra(String word) {
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

    static int limiterPathEngFra(String word) {
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
        char limiterLetter = (char) ((int) firstLetter + 1);

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

    static int shortPathEngSwe(String word) {
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

    static int limiterPathEngSwe(String word) {
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
        char limiterLetter = (char) ((int) firstLetter + 1);

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

    static int shortPathEngIta(String word) {
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

    static int limiterPathEngIta(String word) {
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
        char limiterLetter = (char) ((int) firstLetter + 1);

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
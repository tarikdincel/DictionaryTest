package com.example.ce_216_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dictionaryIta {


    public String italian(String word) {
        StringBuilder allTranslations = new StringBuilder();
        String notFound = "";
        int baseLine = findLineInEnglish(word, 1, 1000000) - 1;
        String newWord = (getWordFromEnglish(baseLine));
        String secondLanguage, stringLanguage;
        for (int a = 0; a < 5; a++) {
            switch (a) {
                case 0 -> {
                    secondLanguage = "deu";
                    stringLanguage = "Deutsch";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));                    }
                case 1 -> {
                    secondLanguage = "ell";
                    stringLanguage = "Modern Greek";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 2 -> {
                    secondLanguage = "swe";
                    stringLanguage = "Swedish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 3 -> {
                    secondLanguage = "tur";
                    stringLanguage = "Turkish";
                    allTranslations.append(findWord(word, secondLanguage, stringLanguage, 1, 1000000));
                }
                case 4 -> {
                    allTranslations.append(findWordInEnglish(word, baseLine - 1));
                    String fakeHeadWord = findFakeHeadWordInItalian(word, baseLine - 1);
                    secondLanguage = "fra";
                    stringLanguage = "French";
                    allTranslations.append(findWordFromEnglish(newWord, secondLanguage, stringLanguage, shortPathEngFra(newWord), limiterPathEngFra(newWord), fakeHeadWord));
                }
            }
        }
        return allTranslations.toString();
    }

    static String findWord(String word, String secondLanguage, String stringLanguage, int startPoint, int limitPoint) {

        StringBuilder output = new StringBuilder();

        boolean found = false;
        String ita = "ita";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + ita + "-" + secondLanguage + placeHolder;

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
                        output = new StringBuilder("\n==========The word exists in Italian-" + stringLanguage + " Dictionary: ======\n");
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

    static int findLineInEnglish(String word, int startPoint, int limitPoint) {

        int lineNumber = 1;

        boolean found = false;
        String ita = "ita";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + ita + "-eng" + placeHolder;

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
        String ita = "ita";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + ita + "-eng" + placeHolder;

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
                        output = new StringBuilder("\n==========The word exists in Italian-English Dictionary: ======\n");
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

    static String findFakeHeadWordInItalian(String word, int startPoint) {

        String output = "";

        boolean found = false;
        String italian = "ita";
        String placeHolder = ".dict";
        String fileName = "src/main/resources/dictionaries/" + italian + "-eng" + placeHolder;

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
                    if (matcher.matches() && line.startsWith(word + " /")) { //line starts with word we are looking for, and contains two slashes.
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
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dictionaries/ita-eng.dict"))) {
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
                        output = new StringBuilder("\n==========The word exists in Italian-" + stringLanguage + " Dictionary: ======\n");
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

    static int shortPathItaEng(String word) {
        final int A = 408;
        final int B = 1387;
        final int C = 1741;
        final int D = 2641;
        final int E = 3094;
        final int F = 3249;
        final int G = 3619;
        final int H = 7195;
        final int I = 3852;
        final int J = 7195;
        final int K = 7195;
        final int L = 4128;
        final int M = 4297;
        final int N = 4632;
        final int O = 4768;
        final int P = 4926;
        final int Q = 5602;
        final int R = 5634;
        final int S = 5932;
        final int T = 6655;
        final int U = 6905;
        final int V = 7004;
        final int Z = 7195;
        final int W = 7195;
        final int Y = 7195;

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
            case 'z' -> Z;
            case 'w' -> W;
            case 'y' -> Y;
            default -> -1;
        };
    }

    static int limiterPathItaEng(String word) {
        final int A = 408;
        final int B = 1387;
        final int C = 1741;
        final int D = 2641;
        final int E = 3094;
        final int F = 3249;
        final int G = 3619;
        final int H = 7195;
        final int I = 3852;
        final int J = 7195;
        final int K = 7195;
        final int L = 4128;
        final int M = 4297;
        final int N = 4632;
        final int O = 4768;
        final int P = 4926;
        final int Q = 5602;
        final int R = 5634;
        final int S = 5932;
        final int T = 6655;
        final int U = 6905;
        final int V = 7004;
        final int Z = 7195;
        final int W = 7195;
        final int Y = 7195;

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
            case 'z' -> Z;
            case 'w' -> W;
            case 'y' -> Y;
            default -> -1;
        };
    }

    static int shortPathEngGer(String word) {
        final int A = 1;
        final int B = 98940;
        final int C = 210420;
        final int D = 374032;
        final int E = 466731;
        final int F = 527785;
        final int G = 609582;
        final int H = 664603;
        final int I = 720918;
        final int J = 784052;
        final int K = 795663;
        final int L = 806625;
        final int M = 863657;
        final int N = 944905;
        final int O = 974508;
        final int P = 1012411;
        final int Q = 1140610;
        final int R = 1146177;
        final int S = 1237127;
        final int T = 1437696;
        final int U = 1540839;
        final int V = 1565129;
        final int W = 1586785;
        final int X = 1637020;
        final int Y = 1637666;
        final int Z = 1643281;

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
            case 'z' -> 1645755;
            default -> Z;
        };

    }

    static int limiterPathEngGer(String word) {
        final int A = 1;
        final int B = 98940;
        final int C = 210420;
        final int D = 374032;
        final int E = 466731;
        final int F = 527785;
        final int G = 609582;
        final int H = 664603;
        final int I = 720918;
        final int J = 784052;
        final int K = 795663;
        final int L = 806625;
        final int M = 863657;
        final int N = 944905;
        final int O = 974508;
        final int P = 1012411;
        final int Q = 1140610;
        final int R = 1146177;
        final int S = 1237127;
        final int T = 1437696;
        final int U = 1540839;
        final int V = 1565129;
        final int W = 1586785;
        final int X = 1637020;
        final int Y = 1637666;
        final int Z = 1643281;

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
            default -> 1645755;
        };
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

}

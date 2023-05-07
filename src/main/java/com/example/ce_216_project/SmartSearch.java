package com.example.ce_216_project;

public class SmartSearch {

    public int shortPathDeuEng(String word, boolean f) {


        final int A = 1;
        final int B = 153690;
        final int C = 261373;
        final int D = 272051;
        final int E = 348419;
        final int F = 470967;
        final int G = 543335;
        final int H = 640259;
        final int I = 702585;
        final int J = 719609;
        final int K = 741361;
        final int L = 828211;
        final int M = 874286;
        final int N = 934948;
        final int O = 975547;
        final int P = 990652;
        final int Q = 1049840;
        final int R = 1054673;
        final int S = 1110415;
        final int T = 1318230;
        final int U = 1366686;
        final int V = 1404889;
        final int W = 1480993;
        final int X = 1541691;
        final int Y = 1542007;
        final int Z = 1542366;

        char firstLetter;
        if (f) {
            firstLetter = word.charAt(0);
        }
        else {
            firstLetter = word.charAt(0);
            char limiterLetter = (char) ((int) firstLetter + 1);
        }


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
            case '{' -> 2500000; //more than max size of all dictionaries.
            default -> Z;
        };
    }

    public int limiterPathDeuEng(String word) {
        final int A = 1;
        final int B = 153690;
        final int C = 261373;
        final int D = 272051;
        final int E = 348419;
        final int F = 470967;
        final int G = 543335;
        final int H = 640259;
        final int I = 702585;
        final int J = 719609;
        final int K = 741361;
        final int L = 828211;
        final int M = 874286;
        final int N = 934948;
        final int O = 975547;
        final int P = 990652;
        final int Q = 1049840;
        final int R = 1054673;
        final int S = 1110415;
        final int T = 1318230;
        final int U = 1366686;
        final int V = 1404889;
        final int W = 1480993;
        final int X = 1541691;
        final int Y = 1542007;
        final int Z = 1542366;

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
            default -> 1629391;
        };
    }

    public int shortPathEngTur(String word) {
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

    public int limiterPathEngTur(String word) {
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

    public int shortPathEngGer(String word) {
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

    public int limiterPathEngGer(String word) {
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

    public int shortPathEngEll(String word) {
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

    public int limiterPathEngEll(String word) {
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

    public int shortPathEngFra(String word) {
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

    public int limiterPathEngFra(String word) {
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

    public int shortPathEngSwe(String word) {
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

    public int limiterPathEngSwe(String word) {
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

    public int shortPathEngIta(String word) {
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

    public int limiterPathEngIta(String word) {
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

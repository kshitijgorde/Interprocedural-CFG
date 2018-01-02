// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression.parser;

import java.io.IOException;
import java.io.PrintStream;

public class ExpressionParserTokenManager implements ExpressionParserConstants
{
    public static PrintStream debugStream;
    static final long[] jjbitVec0;
    static final long[] jjbitVec2;
    static final long[] jjbitVec3;
    static final long[] jjbitVec4;
    static final long[] jjbitVec5;
    static final long[] jjbitVec6;
    static final long[] jjbitVec7;
    static final long[] jjbitVec8;
    static final int[] jjnextStates;
    public static final String[] jjstrLiteralImages;
    public static final String[] lexStateNames;
    static final long[] jjtoToken;
    static final long[] jjtoSkip;
    protected static JavaCharStream input_stream;
    private static final int[] jjrounds;
    private static final int[] jjstateSet;
    protected static char curChar;
    static int curLexState;
    static int defaultLexState;
    static int jjnewStateCnt;
    static int jjround;
    static int jjmatchedPos;
    static int jjmatchedKind;
    
    public static void setDebugStream(final PrintStream debugStream) {
        ExpressionParserTokenManager.debugStream = debugStream;
    }
    
    private static final int jjStopStringLiteralDfa_0(final int n, final long n2) {
        switch (n) {
            case 0: {
                if ((n2 & 0x600000000L) != 0x0L) {
                    return 71;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private static final int jjStartNfa_0(final int n, final long n2) {
        return jjMoveNfa_0(jjStopStringLiteralDfa_0(n, n2), n + 1);
    }
    
    private static final int jjStopAtPos(final int jjmatchedPos, final int jjmatchedKind) {
        ExpressionParserTokenManager.jjmatchedKind = jjmatchedKind;
        return (ExpressionParserTokenManager.jjmatchedPos = jjmatchedPos) + 1;
    }
    
    private static final int jjMoveStringLiteralDfa0_0() {
        switch (ExpressionParserTokenManager.curChar) {
            case '%': {
                return jjStopAtPos(0, 42);
            }
            case '(': {
                return jjStopAtPos(0, 29);
            }
            case ')': {
                return jjStopAtPos(0, 30);
            }
            case '*': {
                return jjStopAtPos(0, 39);
            }
            case '+': {
                return jjStopAtPos(0, 37);
            }
            case ',': {
                return jjStopAtPos(0, 43);
            }
            case '-': {
                return jjStopAtPos(0, 38);
            }
            case '/': {
                return jjStopAtPos(0, 40);
            }
            case '<': {
                ExpressionParserTokenManager.jjmatchedKind = 33;
                return jjMoveStringLiteralDfa1_0(17179869184L);
            }
            case '>': {
                ExpressionParserTokenManager.jjmatchedKind = 32;
                return jjMoveStringLiteralDfa1_0(34359738368L);
            }
            case '^': {
                return jjStopAtPos(0, 41);
            }
            default: {
                return jjMoveNfa_0(4, 0);
            }
        }
    }
    
    private static final int jjMoveStringLiteralDfa1_0(final long n) {
        try {
            ExpressionParserTokenManager.curChar = JavaCharStream.readChar();
        }
        catch (IOException ex) {
            jjStopStringLiteralDfa_0(0, n);
            return 1;
        }
        switch (ExpressionParserTokenManager.curChar) {
            case '=': {
                if ((n & 0x400000000L) != 0x0L) {
                    return jjStopAtPos(1, 34);
                }
                if ((n & 0x800000000L) != 0x0L) {
                    return jjStopAtPos(1, 35);
                }
                break;
            }
        }
        return jjStartNfa_0(0, n);
    }
    
    private static final void jjCheckNAdd(final int n) {
        if (ExpressionParserTokenManager.jjrounds[n] != ExpressionParserTokenManager.jjround) {
            ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = n;
            ExpressionParserTokenManager.jjrounds[n] = ExpressionParserTokenManager.jjround;
        }
    }
    
    private static final void jjAddStates(int n, final int n2) {
        do {
            ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = ExpressionParserTokenManager.jjnextStates[n];
        } while (n++ != n2);
    }
    
    private static final void jjCheckNAddTwoStates(final int n, final int n2) {
        jjCheckNAdd(n);
        jjCheckNAdd(n2);
    }
    
    private static final void jjCheckNAddStates(int n, final int n2) {
        do {
            jjCheckNAdd(ExpressionParserTokenManager.jjnextStates[n]);
        } while (n++ != n2);
    }
    
    private static final int jjMoveNfa_0(final int n, int jjmatchedPos) {
        int jjnewStateCnt = 0;
        ExpressionParserTokenManager.jjnewStateCnt = 141;
        int i = 1;
        ExpressionParserTokenManager.jjstateSet[0] = n;
        int jjmatchedKind = Integer.MAX_VALUE;
        while (true) {
            if (++ExpressionParserTokenManager.jjround == Integer.MAX_VALUE) {
                ReInitRounds();
            }
            if (ExpressionParserTokenManager.curChar < '@') {
                final long n2 = 1L << ExpressionParserTokenManager.curChar;
                do {
                    switch (ExpressionParserTokenManager.jjstateSet[--i]) {
                        case 4: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(0, 7);
                            }
                            else if (ExpressionParserTokenManager.curChar == '.') {
                                if (jjmatchedKind > 21) {
                                    jjmatchedKind = 21;
                                }
                                jjCheckNAddStates(8, 12);
                            }
                            else if (ExpressionParserTokenManager.curChar == '<') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 71;
                            }
                            else if (ExpressionParserTokenManager.curChar == '!') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 69;
                            }
                            else if (ExpressionParserTokenManager.curChar == '=') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 67;
                            }
                            else if (ExpressionParserTokenManager.curChar == '$') {
                                if (jjmatchedKind > 26) {
                                    jjmatchedKind = 26;
                                }
                                jjCheckNAdd(65);
                            }
                            else if (ExpressionParserTokenManager.curChar == '\'') {
                                jjCheckNAddStates(13, 15);
                            }
                            else if (ExpressionParserTokenManager.curChar == '\"') {
                                jjCheckNAddStates(16, 18);
                            }
                            else if (ExpressionParserTokenManager.curChar == '&') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 28;
                            }
                            if ((0x3FE000000000000L & n2) != 0x0L) {
                                if (jjmatchedKind > 16) {
                                    jjmatchedKind = 16;
                                }
                                jjCheckNAddStates(19, 21);
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == '0') {
                                if (jjmatchedKind > 16) {
                                    jjmatchedKind = 16;
                                }
                                jjCheckNAddStates(22, 26);
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == '=') {
                                if (jjmatchedKind > 31) {
                                    jjmatchedKind = 31;
                                    continue;
                                }
                                continue;
                            }
                            else {
                                if (ExpressionParserTokenManager.curChar == '!' && jjmatchedKind > 14) {
                                    jjmatchedKind = 14;
                                    continue;
                                }
                                continue;
                            }
                            break;
                        }
                        case 28: {
                            if (ExpressionParserTokenManager.curChar == '&' && jjmatchedKind > 12) {
                                jjmatchedKind = 12;
                                continue;
                            }
                            continue;
                        }
                        case 29: {
                            if (ExpressionParserTokenManager.curChar == '&') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 28;
                                continue;
                            }
                            continue;
                        }
                        case 36: {
                            if (ExpressionParserTokenManager.curChar == '!' && jjmatchedKind > 14) {
                                jjmatchedKind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 43: {
                            if (ExpressionParserTokenManager.curChar == '\"') {
                                jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 44: {
                            if ((0xFFFFFFFBFFFFDBFFL & n2) != 0x0L) {
                                jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 46: {
                            if ((0x8400000000L & n2) != 0x0L) {
                                jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 47: {
                            if (ExpressionParserTokenManager.curChar == '\"' && jjmatchedKind > 24) {
                                jjmatchedKind = 24;
                                continue;
                            }
                            continue;
                        }
                        case 48: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(27, 30);
                                continue;
                            }
                            continue;
                        }
                        case 49: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 50: {
                            if ((0xF000000000000L & n2) != 0x0L) {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 51;
                                continue;
                            }
                            continue;
                        }
                        case 51: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAdd(49);
                                continue;
                            }
                            continue;
                        }
                        case 52: {
                            if (ExpressionParserTokenManager.curChar == '\'') {
                                jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 53: {
                            if ((0xFFFFFF7FFFFFDBFFL & n2) != 0x0L) {
                                jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 55: {
                            if ((0x8400000000L & n2) != 0x0L) {
                                jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 56: {
                            if (ExpressionParserTokenManager.curChar == '\'' && jjmatchedKind > 24) {
                                jjmatchedKind = 24;
                                continue;
                            }
                            continue;
                        }
                        case 57: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(31, 34);
                                continue;
                            }
                            continue;
                        }
                        case 58: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 59: {
                            if ((0xF000000000000L & n2) != 0x0L) {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 60;
                                continue;
                            }
                            continue;
                        }
                        case 60: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAdd(58);
                                continue;
                            }
                            continue;
                        }
                        case 64: {
                            if (ExpressionParserTokenManager.curChar != '$') {
                                continue;
                            }
                            if (jjmatchedKind > 26) {
                                jjmatchedKind = 26;
                            }
                            jjCheckNAdd(65);
                            continue;
                        }
                        case 65: {
                            if ((0x3FF001000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 26) {
                                jjmatchedKind = 26;
                            }
                            jjCheckNAdd(65);
                            continue;
                        }
                        case 66:
                        case 67: {
                            if (ExpressionParserTokenManager.curChar == '=' && jjmatchedKind > 31) {
                                jjmatchedKind = 31;
                                continue;
                            }
                            continue;
                        }
                        case 68: {
                            if (ExpressionParserTokenManager.curChar == '=') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 67;
                                continue;
                            }
                            continue;
                        }
                        case 69: {
                            if (ExpressionParserTokenManager.curChar == '=' && jjmatchedKind > 36) {
                                jjmatchedKind = 36;
                                continue;
                            }
                            continue;
                        }
                        case 70: {
                            if (ExpressionParserTokenManager.curChar == '!') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 69;
                                continue;
                            }
                            continue;
                        }
                        case 71: {
                            if (ExpressionParserTokenManager.curChar == '>' && jjmatchedKind > 36) {
                                jjmatchedKind = 36;
                                continue;
                            }
                            continue;
                        }
                        case 72: {
                            if (ExpressionParserTokenManager.curChar == '<') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 71;
                                continue;
                            }
                            continue;
                        }
                        case 99: {
                            if ((0x3FE000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 16) {
                                jjmatchedKind = 16;
                            }
                            jjCheckNAddStates(19, 21);
                            continue;
                        }
                        case 100: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 16) {
                                jjmatchedKind = 16;
                            }
                            jjCheckNAdd(100);
                            continue;
                        }
                        case 101: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(101, 102);
                                continue;
                            }
                            continue;
                        }
                        case 103: {
                            if (ExpressionParserTokenManager.curChar != '.') {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAddStates(8, 12);
                            continue;
                        }
                        case 104: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAddTwoStates(104, 105);
                            continue;
                        }
                        case 106: {
                            if ((0x280000000000L & n2) != 0x0L) {
                                jjCheckNAdd(107);
                                continue;
                            }
                            continue;
                        }
                        case 107: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAdd(107);
                            continue;
                        }
                        case 108: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(37, 39);
                                continue;
                            }
                            continue;
                        }
                        case 110: {
                            if ((0x280000000000L & n2) != 0x0L) {
                                jjCheckNAdd(111);
                                continue;
                            }
                            continue;
                        }
                        case 111: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(111, 112);
                                continue;
                            }
                            continue;
                        }
                        case 113: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(0, 7);
                                continue;
                            }
                            continue;
                        }
                        case 114: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(114, 115);
                                continue;
                            }
                            continue;
                        }
                        case 115: {
                            if (ExpressionParserTokenManager.curChar != '.') {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAddTwoStates(116, 117);
                            continue;
                        }
                        case 116: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAddTwoStates(116, 117);
                            continue;
                        }
                        case 118: {
                            if ((0x280000000000L & n2) != 0x0L) {
                                jjCheckNAdd(119);
                                continue;
                            }
                            continue;
                        }
                        case 119: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAdd(119);
                            continue;
                        }
                        case 120: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(120, 121);
                                continue;
                            }
                            continue;
                        }
                        case 122: {
                            if ((0x280000000000L & n2) != 0x0L) {
                                jjCheckNAdd(123);
                                continue;
                            }
                            continue;
                        }
                        case 123: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 21) {
                                jjmatchedKind = 21;
                            }
                            jjCheckNAdd(123);
                            continue;
                        }
                        case 124: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(124, 125);
                                continue;
                            }
                            continue;
                        }
                        case 125: {
                            if (ExpressionParserTokenManager.curChar == '.') {
                                jjCheckNAddStates(40, 42);
                                continue;
                            }
                            continue;
                        }
                        case 126: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddStates(40, 42);
                                continue;
                            }
                            continue;
                        }
                        case 128: {
                            if ((0x280000000000L & n2) != 0x0L) {
                                jjCheckNAdd(129);
                                continue;
                            }
                            continue;
                        }
                        case 129: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(129, 112);
                                continue;
                            }
                            continue;
                        }
                        case 130: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(130, 131);
                                continue;
                            }
                            continue;
                        }
                        case 132: {
                            if ((0x280000000000L & n2) != 0x0L) {
                                jjCheckNAdd(133);
                                continue;
                            }
                            continue;
                        }
                        case 133: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(133, 112);
                                continue;
                            }
                            continue;
                        }
                        case 134: {
                            if (ExpressionParserTokenManager.curChar != '0') {
                                continue;
                            }
                            if (jjmatchedKind > 16) {
                                jjmatchedKind = 16;
                            }
                            jjCheckNAddStates(22, 26);
                            continue;
                        }
                        case 136: {
                            if ((0x3FF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 16) {
                                jjmatchedKind = 16;
                            }
                            ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 136;
                            continue;
                        }
                        case 137: {
                            if ((0xFF000000000000L & n2) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 16) {
                                jjmatchedKind = 16;
                            }
                            jjCheckNAdd(137);
                            continue;
                        }
                        case 139: {
                            if ((0x3FF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(139, 102);
                                continue;
                            }
                            continue;
                        }
                        case 140: {
                            if ((0xFF000000000000L & n2) != 0x0L) {
                                jjCheckNAddTwoStates(140, 102);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 62: {
                            jjAddStates(35, 36);
                            continue;
                        }
                    }
                } while (i != jjnewStateCnt);
            }
            else if (ExpressionParserTokenManager.curChar < '\u0080') {
                final long n3 = 1L << (ExpressionParserTokenManager.curChar & '?');
                do {
                    switch (ExpressionParserTokenManager.jjstateSet[--i]) {
                        case 4: {
                            if ((0x7FFFFFE87FFFFFEL & n3) != 0x0L) {
                                if (jjmatchedKind > 26) {
                                    jjmatchedKind = 26;
                                }
                                jjCheckNAdd(65);
                            }
                            else if (ExpressionParserTokenManager.curChar == '[') {
                                jjCheckNAdd(62);
                            }
                            else if (ExpressionParserTokenManager.curChar == '|') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 34;
                            }
                            if (ExpressionParserTokenManager.curChar == 'n') {
                                jjAddStates(43, 44);
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'N') {
                                jjAddStates(45, 46);
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 't') {
                                jjAddStates(47, 48);
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'T') {
                                jjAddStates(49, 50);
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'x') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 41;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'X') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 38;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'o') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 32;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'O') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 30;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'a') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 26;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'A') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 23;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'e') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 20;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'E') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 16;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'i') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 12;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'I') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 10;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'f') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 8;
                                continue;
                            }
                            if (ExpressionParserTokenManager.curChar == 'F') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 3;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if (ExpressionParserTokenManager.curChar == 'E' && jjmatchedKind > 7) {
                                jjmatchedKind = 7;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (ExpressionParserTokenManager.curChar == 'S') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 0;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (ExpressionParserTokenManager.curChar == 'L') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if (ExpressionParserTokenManager.curChar == 'A') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (ExpressionParserTokenManager.curChar == 'e' && jjmatchedKind > 7) {
                                jjmatchedKind = 7;
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if (ExpressionParserTokenManager.curChar == 's') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 5;
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (ExpressionParserTokenManager.curChar == 'l') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 6;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (ExpressionParserTokenManager.curChar == 'a') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 7;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (ExpressionParserTokenManager.curChar == 'f') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 8;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if (ExpressionParserTokenManager.curChar == 'F' && jjmatchedKind > 9) {
                                jjmatchedKind = 9;
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (ExpressionParserTokenManager.curChar == 'I') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 10;
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if (ExpressionParserTokenManager.curChar == 'f' && jjmatchedKind > 9) {
                                jjmatchedKind = 9;
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (ExpressionParserTokenManager.curChar == 'i') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 12;
                                continue;
                            }
                            continue;
                        }
                        case 14: {
                            if (ExpressionParserTokenManager.curChar == 'E' && jjmatchedKind > 11) {
                                jjmatchedKind = 11;
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if (ExpressionParserTokenManager.curChar == 'S') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 14;
                                continue;
                            }
                            continue;
                        }
                        case 16: {
                            if (ExpressionParserTokenManager.curChar == 'L') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 15;
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if (ExpressionParserTokenManager.curChar == 'E') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 16;
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if (ExpressionParserTokenManager.curChar == 'e' && jjmatchedKind > 11) {
                                jjmatchedKind = 11;
                                continue;
                            }
                            continue;
                        }
                        case 19: {
                            if (ExpressionParserTokenManager.curChar == 's') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 18;
                                continue;
                            }
                            continue;
                        }
                        case 20: {
                            if (ExpressionParserTokenManager.curChar == 'l') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 19;
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if (ExpressionParserTokenManager.curChar == 'e') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 20;
                                continue;
                            }
                            continue;
                        }
                        case 22: {
                            if (ExpressionParserTokenManager.curChar == 'D' && jjmatchedKind > 12) {
                                jjmatchedKind = 12;
                                continue;
                            }
                            continue;
                        }
                        case 23: {
                            if (ExpressionParserTokenManager.curChar == 'N') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 22;
                                continue;
                            }
                            continue;
                        }
                        case 24: {
                            if (ExpressionParserTokenManager.curChar == 'A') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 23;
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if (ExpressionParserTokenManager.curChar == 'd' && jjmatchedKind > 12) {
                                jjmatchedKind = 12;
                                continue;
                            }
                            continue;
                        }
                        case 26: {
                            if (ExpressionParserTokenManager.curChar == 'n') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 25;
                                continue;
                            }
                            continue;
                        }
                        case 27: {
                            if (ExpressionParserTokenManager.curChar == 'a') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 26;
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (ExpressionParserTokenManager.curChar == 'R' && jjmatchedKind > 13) {
                                jjmatchedKind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 31: {
                            if (ExpressionParserTokenManager.curChar == 'O') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 30;
                                continue;
                            }
                            continue;
                        }
                        case 32: {
                            if (ExpressionParserTokenManager.curChar == 'r' && jjmatchedKind > 13) {
                                jjmatchedKind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 33: {
                            if (ExpressionParserTokenManager.curChar == 'o') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 32;
                                continue;
                            }
                            continue;
                        }
                        case 34: {
                            if (ExpressionParserTokenManager.curChar == '|' && jjmatchedKind > 13) {
                                jjmatchedKind = 13;
                                continue;
                            }
                            continue;
                        }
                        case 35: {
                            if (ExpressionParserTokenManager.curChar == '|') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 34;
                                continue;
                            }
                            continue;
                        }
                        case 37: {
                            if (ExpressionParserTokenManager.curChar == 'R' && jjmatchedKind > 15) {
                                jjmatchedKind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if (ExpressionParserTokenManager.curChar == 'O') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 37;
                                continue;
                            }
                            continue;
                        }
                        case 39: {
                            if (ExpressionParserTokenManager.curChar == 'X') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 38;
                                continue;
                            }
                            continue;
                        }
                        case 40: {
                            if (ExpressionParserTokenManager.curChar == 'r' && jjmatchedKind > 15) {
                                jjmatchedKind = 15;
                                continue;
                            }
                            continue;
                        }
                        case 41: {
                            if (ExpressionParserTokenManager.curChar == 'o') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 40;
                                continue;
                            }
                            continue;
                        }
                        case 42: {
                            if (ExpressionParserTokenManager.curChar == 'x') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 41;
                                continue;
                            }
                            continue;
                        }
                        case 44: {
                            if ((0xFFFFFFFFEFFFFFFFL & n3) != 0x0L) {
                                jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 45: {
                            if (ExpressionParserTokenManager.curChar == '\\') {
                                jjAddStates(51, 53);
                                continue;
                            }
                            continue;
                        }
                        case 46: {
                            if ((0x14404410000000L & n3) != 0x0L) {
                                jjCheckNAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 53: {
                            if ((0xFFFFFFFFEFFFFFFFL & n3) != 0x0L) {
                                jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 54: {
                            if (ExpressionParserTokenManager.curChar == '\\') {
                                jjAddStates(54, 56);
                                continue;
                            }
                            continue;
                        }
                        case 55: {
                            if ((0x14404410000000L & n3) != 0x0L) {
                                jjCheckNAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 61: {
                            if (ExpressionParserTokenManager.curChar == '[') {
                                jjCheckNAdd(62);
                                continue;
                            }
                            continue;
                        }
                        case 62: {
                            if ((0xFFFFFFFFDFFFFFFFL & n3) != 0x0L) {
                                jjCheckNAddTwoStates(62, 63);
                                continue;
                            }
                            continue;
                        }
                        case 63: {
                            if (ExpressionParserTokenManager.curChar == ']' && jjmatchedKind > 25) {
                                jjmatchedKind = 25;
                                continue;
                            }
                            continue;
                        }
                        case 64:
                        case 65: {
                            if ((0x7FFFFFE87FFFFFEL & n3) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 26) {
                                jjmatchedKind = 26;
                            }
                            jjCheckNAdd(65);
                            continue;
                        }
                        case 73: {
                            if (ExpressionParserTokenManager.curChar == 'T') {
                                jjAddStates(49, 50);
                                continue;
                            }
                            continue;
                        }
                        case 74: {
                            if (ExpressionParserTokenManager.curChar == 'E' && jjmatchedKind > 6) {
                                jjmatchedKind = 6;
                                continue;
                            }
                            continue;
                        }
                        case 75: {
                            if (ExpressionParserTokenManager.curChar == 'U') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 74;
                                continue;
                            }
                            continue;
                        }
                        case 76: {
                            if (ExpressionParserTokenManager.curChar == 'R') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 75;
                                continue;
                            }
                            continue;
                        }
                        case 77: {
                            if (ExpressionParserTokenManager.curChar == 'N' && jjmatchedKind > 10) {
                                jjmatchedKind = 10;
                                continue;
                            }
                            continue;
                        }
                        case 78: {
                            if (ExpressionParserTokenManager.curChar == 'E') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 77;
                                continue;
                            }
                            continue;
                        }
                        case 79: {
                            if (ExpressionParserTokenManager.curChar == 'H') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 78;
                                continue;
                            }
                            continue;
                        }
                        case 80: {
                            if (ExpressionParserTokenManager.curChar == 't') {
                                jjAddStates(47, 48);
                                continue;
                            }
                            continue;
                        }
                        case 81: {
                            if (ExpressionParserTokenManager.curChar == 'e' && jjmatchedKind > 6) {
                                jjmatchedKind = 6;
                                continue;
                            }
                            continue;
                        }
                        case 82: {
                            if (ExpressionParserTokenManager.curChar == 'u') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 81;
                                continue;
                            }
                            continue;
                        }
                        case 83: {
                            if (ExpressionParserTokenManager.curChar == 'r') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 82;
                                continue;
                            }
                            continue;
                        }
                        case 84: {
                            if (ExpressionParserTokenManager.curChar == 'n' && jjmatchedKind > 10) {
                                jjmatchedKind = 10;
                                continue;
                            }
                            continue;
                        }
                        case 85: {
                            if (ExpressionParserTokenManager.curChar == 'e') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 84;
                                continue;
                            }
                            continue;
                        }
                        case 86: {
                            if (ExpressionParserTokenManager.curChar == 'h') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 85;
                                continue;
                            }
                            continue;
                        }
                        case 87: {
                            if (ExpressionParserTokenManager.curChar == 'N') {
                                jjAddStates(45, 46);
                                continue;
                            }
                            continue;
                        }
                        case 88: {
                            if (ExpressionParserTokenManager.curChar == 'L' && jjmatchedKind > 8) {
                                jjmatchedKind = 8;
                                continue;
                            }
                            continue;
                        }
                        case 89: {
                            if (ExpressionParserTokenManager.curChar == 'L') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 88;
                                continue;
                            }
                            continue;
                        }
                        case 90: {
                            if (ExpressionParserTokenManager.curChar == 'U') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 89;
                                continue;
                            }
                            continue;
                        }
                        case 91: {
                            if (ExpressionParserTokenManager.curChar == 'T' && jjmatchedKind > 14) {
                                jjmatchedKind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 92: {
                            if (ExpressionParserTokenManager.curChar == 'O') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 91;
                                continue;
                            }
                            continue;
                        }
                        case 93: {
                            if (ExpressionParserTokenManager.curChar == 'n') {
                                jjAddStates(43, 44);
                                continue;
                            }
                            continue;
                        }
                        case 94: {
                            if (ExpressionParserTokenManager.curChar == 'l' && jjmatchedKind > 8) {
                                jjmatchedKind = 8;
                                continue;
                            }
                            continue;
                        }
                        case 95: {
                            if (ExpressionParserTokenManager.curChar == 'l') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 94;
                                continue;
                            }
                            continue;
                        }
                        case 96: {
                            if (ExpressionParserTokenManager.curChar == 'u') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 95;
                                continue;
                            }
                            continue;
                        }
                        case 97: {
                            if (ExpressionParserTokenManager.curChar == 't' && jjmatchedKind > 14) {
                                jjmatchedKind = 14;
                                continue;
                            }
                            continue;
                        }
                        case 98: {
                            if (ExpressionParserTokenManager.curChar == 'o') {
                                ExpressionParserTokenManager.jjstateSet[ExpressionParserTokenManager.jjnewStateCnt++] = 97;
                                continue;
                            }
                            continue;
                        }
                        case 102: {
                            if ((0x100000001000L & n3) != 0x0L && jjmatchedKind > 20) {
                                jjmatchedKind = 20;
                                continue;
                            }
                            continue;
                        }
                        case 105: {
                            if ((0x2000000020L & n3) != 0x0L) {
                                jjAddStates(57, 58);
                                continue;
                            }
                            continue;
                        }
                        case 109: {
                            if ((0x2000000020L & n3) != 0x0L) {
                                jjAddStates(59, 60);
                                continue;
                            }
                            continue;
                        }
                        case 112: {
                            if ((0x4000000040L & n3) != 0x0L && jjmatchedKind > 22) {
                                jjmatchedKind = 22;
                                continue;
                            }
                            continue;
                        }
                        case 117: {
                            if ((0x2000000020L & n3) != 0x0L) {
                                jjAddStates(61, 62);
                                continue;
                            }
                            continue;
                        }
                        case 121: {
                            if ((0x2000000020L & n3) != 0x0L) {
                                jjAddStates(63, 64);
                                continue;
                            }
                            continue;
                        }
                        case 127: {
                            if ((0x2000000020L & n3) != 0x0L) {
                                jjAddStates(65, 66);
                                continue;
                            }
                            continue;
                        }
                        case 131: {
                            if ((0x2000000020L & n3) != 0x0L) {
                                jjAddStates(67, 68);
                                continue;
                            }
                            continue;
                        }
                        case 135: {
                            if ((0x100000001000000L & n3) != 0x0L) {
                                jjCheckNAdd(136);
                                continue;
                            }
                            continue;
                        }
                        case 136: {
                            if ((0x7E0000007EL & n3) == 0x0L) {
                                continue;
                            }
                            if (jjmatchedKind > 16) {
                                jjmatchedKind = 16;
                            }
                            jjCheckNAdd(136);
                            continue;
                        }
                        case 138: {
                            if ((0x100000001000000L & n3) != 0x0L) {
                                jjCheckNAdd(139);
                                continue;
                            }
                            continue;
                        }
                        case 139: {
                            if ((0x7E0000007EL & n3) != 0x0L) {
                                jjCheckNAddTwoStates(139, 102);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != jjnewStateCnt);
            }
            else {
                final char c = (char)(ExpressionParserTokenManager.curChar >> 8);
                final char c2 = (char)(c >> 6);
                final long n4 = 1L << (c & '?');
                final char c3 = (char)((ExpressionParserTokenManager.curChar & '\u00ff') >> 6);
                final long n5 = 1L << (ExpressionParserTokenManager.curChar & '?');
                do {
                    switch (ExpressionParserTokenManager.jjstateSet[--i]) {
                        case 4:
                        case 65: {
                            if (!jjCanMove_1(c, c2, c3, n4, n5)) {
                                continue;
                            }
                            if (jjmatchedKind > 26) {
                                jjmatchedKind = 26;
                            }
                            jjCheckNAdd(65);
                            continue;
                        }
                        case 44: {
                            if (jjCanMove_0(c, c2, c3, n4, n5)) {
                                jjAddStates(16, 18);
                                continue;
                            }
                            continue;
                        }
                        case 53: {
                            if (jjCanMove_0(c, c2, c3, n4, n5)) {
                                jjAddStates(13, 15);
                                continue;
                            }
                            continue;
                        }
                        case 62: {
                            if (jjCanMove_0(c, c2, c3, n4, n5)) {
                                jjAddStates(35, 36);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != jjnewStateCnt);
            }
            if (jjmatchedKind != Integer.MAX_VALUE) {
                ExpressionParserTokenManager.jjmatchedKind = jjmatchedKind;
                ExpressionParserTokenManager.jjmatchedPos = jjmatchedPos;
                jjmatchedKind = Integer.MAX_VALUE;
            }
            ++jjmatchedPos;
            if ((i = ExpressionParserTokenManager.jjnewStateCnt) == (jjnewStateCnt = 141 - (ExpressionParserTokenManager.jjnewStateCnt = jjnewStateCnt))) {
                break;
            }
            try {
                ExpressionParserTokenManager.curChar = JavaCharStream.readChar();
            }
            catch (IOException ex) {
                return jjmatchedPos;
            }
        }
        return jjmatchedPos;
    }
    
    private static final boolean jjCanMove_0(final int n, final int n2, final int n3, final long n4, final long n5) {
        switch (n) {
            case 0: {
                return (ExpressionParserTokenManager.jjbitVec2[n3] & n5) != 0x0L;
            }
            default: {
                return (ExpressionParserTokenManager.jjbitVec0[n2] & n4) != 0x0L;
            }
        }
    }
    
    private static final boolean jjCanMove_1(final int n, final int n2, final int n3, final long n4, final long n5) {
        switch (n) {
            case 0: {
                return (ExpressionParserTokenManager.jjbitVec4[n3] & n5) != 0x0L;
            }
            case 48: {
                return (ExpressionParserTokenManager.jjbitVec5[n3] & n5) != 0x0L;
            }
            case 49: {
                return (ExpressionParserTokenManager.jjbitVec6[n3] & n5) != 0x0L;
            }
            case 51: {
                return (ExpressionParserTokenManager.jjbitVec7[n3] & n5) != 0x0L;
            }
            case 61: {
                return (ExpressionParserTokenManager.jjbitVec8[n3] & n5) != 0x0L;
            }
            default: {
                return (ExpressionParserTokenManager.jjbitVec3[n2] & n4) != 0x0L;
            }
        }
    }
    
    public ExpressionParserTokenManager(final JavaCharStream input_stream) {
        if (ExpressionParserTokenManager.input_stream != null) {
            throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", 1);
        }
        ExpressionParserTokenManager.input_stream = input_stream;
    }
    
    public ExpressionParserTokenManager(final JavaCharStream javaCharStream, final int n) {
        this(javaCharStream);
        SwitchTo(n);
    }
    
    public static void ReInit(final JavaCharStream input_stream) {
        ExpressionParserTokenManager.jjmatchedPos = (ExpressionParserTokenManager.jjnewStateCnt = 0);
        ExpressionParserTokenManager.curLexState = ExpressionParserTokenManager.defaultLexState;
        ExpressionParserTokenManager.input_stream = input_stream;
        ReInitRounds();
    }
    
    private static final void ReInitRounds() {
        ExpressionParserTokenManager.jjround = -2147483647;
        int n = 141;
        while (n-- > 0) {
            ExpressionParserTokenManager.jjrounds[n] = Integer.MIN_VALUE;
        }
    }
    
    public static void ReInit(final JavaCharStream javaCharStream, final int n) {
        ReInit(javaCharStream);
        SwitchTo(n);
    }
    
    public static void SwitchTo(final int curLexState) {
        if (curLexState >= 1 || curLexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + curLexState + ". State unchanged.", 2);
        }
        ExpressionParserTokenManager.curLexState = curLexState;
    }
    
    protected static Token jjFillToken() {
        final Token token = Token.newToken(ExpressionParserTokenManager.jjmatchedKind);
        token.kind = ExpressionParserTokenManager.jjmatchedKind;
        final String s = ExpressionParserTokenManager.jjstrLiteralImages[ExpressionParserTokenManager.jjmatchedKind];
        token.image = ((s == null) ? JavaCharStream.GetImage() : s);
        token.beginLine = JavaCharStream.getBeginLine();
        token.beginColumn = JavaCharStream.getBeginColumn();
        token.endLine = JavaCharStream.getEndLine();
        token.endColumn = JavaCharStream.getEndColumn();
        return token;
    }
    
    public static Token getNextToken() {
        while (true) {
            try {
                ExpressionParserTokenManager.curChar = JavaCharStream.BeginToken();
            }
            catch (IOException ex) {
                ExpressionParserTokenManager.jjmatchedKind = 0;
                return jjFillToken();
            }
            try {
                JavaCharStream.backup(0);
                while (ExpressionParserTokenManager.curChar <= ' ' && (0x100003600L & 1L << ExpressionParserTokenManager.curChar) != 0x0L) {
                    ExpressionParserTokenManager.curChar = JavaCharStream.BeginToken();
                }
            }
            catch (IOException ex2) {
                continue;
            }
            ExpressionParserTokenManager.jjmatchedKind = Integer.MAX_VALUE;
            ExpressionParserTokenManager.jjmatchedPos = 0;
            final int jjMoveStringLiteralDfa0_0 = jjMoveStringLiteralDfa0_0();
            if (ExpressionParserTokenManager.jjmatchedKind == Integer.MAX_VALUE) {
                int endLine = JavaCharStream.getEndLine();
                int endColumn = JavaCharStream.getEndColumn();
                String s = null;
                boolean b = false;
                try {
                    JavaCharStream.readChar();
                    JavaCharStream.backup(1);
                }
                catch (IOException ex3) {
                    b = true;
                    s = ((jjMoveStringLiteralDfa0_0 <= 1) ? "" : JavaCharStream.GetImage());
                    if (ExpressionParserTokenManager.curChar == '\n' || ExpressionParserTokenManager.curChar == '\r') {
                        ++endLine;
                        endColumn = 0;
                    }
                    else {
                        ++endColumn;
                    }
                }
                if (!b) {
                    JavaCharStream.backup(1);
                    s = ((jjMoveStringLiteralDfa0_0 <= 1) ? "" : JavaCharStream.GetImage());
                }
                throw new TokenMgrError(b, ExpressionParserTokenManager.curLexState, endLine, endColumn, s, ExpressionParserTokenManager.curChar, 0);
            }
            if (ExpressionParserTokenManager.jjmatchedPos + 1 < jjMoveStringLiteralDfa0_0) {
                JavaCharStream.backup(jjMoveStringLiteralDfa0_0 - ExpressionParserTokenManager.jjmatchedPos - 1);
            }
            if ((ExpressionParserTokenManager.jjtoToken[ExpressionParserTokenManager.jjmatchedKind >> 6] & 1L << (ExpressionParserTokenManager.jjmatchedKind & 0x3F)) != 0x0L) {
                return jjFillToken();
            }
        }
    }
    
    static {
        ExpressionParserTokenManager.debugStream = System.out;
        jjbitVec0 = new long[] { -2L, -1L, -1L, -1L };
        jjbitVec2 = new long[] { 0L, 0L, -1L, -1L };
        jjbitVec3 = new long[] { 2301339413881290750L, -16384L, 4294967295L, 432345564227567616L };
        jjbitVec4 = new long[] { 0L, 0L, 0L, -36028797027352577L };
        jjbitVec5 = new long[] { 0L, -1L, -1L, -1L };
        jjbitVec6 = new long[] { -1L, -1L, 65535L, 0L };
        jjbitVec7 = new long[] { -1L, -1L, 0L, 0L };
        jjbitVec8 = new long[] { 70368744177663L, 0L, 0L, 0L };
        jjnextStates = new int[] { 114, 115, 120, 121, 124, 125, 130, 131, 104, 105, 108, 109, 112, 53, 54, 56, 44, 45, 47, 100, 101, 102, 135, 137, 138, 140, 102, 44, 45, 49, 47, 53, 54, 58, 56, 62, 63, 108, 109, 112, 126, 127, 112, 96, 98, 90, 92, 83, 86, 76, 79, 46, 48, 50, 55, 57, 59, 106, 107, 110, 111, 118, 119, 122, 123, 128, 129, 132, 133 };
        jjstrLiteralImages = new String[] { "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "(", ")", null, ">", "<", "<=", ">=", null, "+", "-", "*", "/", "^", "%", "," };
        lexStateNames = new String[] { "DEFAULT" };
        jjtoToken = new long[] { 17591774085057L };
        jjtoSkip = new long[] { 62L };
        jjrounds = new int[141];
        jjstateSet = new int[282];
        ExpressionParserTokenManager.curLexState = 0;
        ExpressionParserTokenManager.defaultLexState = 0;
    }
}

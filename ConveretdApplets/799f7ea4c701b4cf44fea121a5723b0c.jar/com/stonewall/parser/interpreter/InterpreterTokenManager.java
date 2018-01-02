// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.interpreter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.PrintStream;

public class InterpreterTokenManager implements InterpreterConstants
{
    public PrintStream debugStream;
    static final long[] jjbitVec0;
    static final int[] jjnextStates;
    public static final String[] jjstrLiteralImages;
    public static final String[] lexStateNames;
    public static final int[] jjnewLexState;
    static final long[] jjtoToken;
    static final long[] jjtoSkip;
    static final long[] jjtoSpecial;
    static final long[] jjtoMore;
    protected SimpleCharStream input_stream;
    private final int[] jjrounds;
    private final int[] jjstateSet;
    private final StringBuilder jjimage;
    private StringBuilder image;
    private int jjimageLen;
    private int lengthOfMatch;
    protected char curChar;
    int curLexState;
    int defaultLexState;
    int jjnewStateCnt;
    int jjround;
    int jjmatchedPos;
    int jjmatchedKind;
    
    static {
        jjbitVec0 = new long[] { 0L, 0L, -1L, -1L };
        jjnextStates = new int[] { 7, 8, 10, 11 };
        jjstrLiteralImages = new String[36];
        lexStateNames = new String[] { "DEFAULT", "COMMENT" };
        jjnewLexState = new int[] { -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0 };
        jjtoToken = new long[] { 9126805249L };
        jjtoSkip = new long[] { 51539607582L };
        jjtoSpecial = new long[] { 51539607552L };
        jjtoMore = new long[] { 224L };
    }
    
    public void setDebugStream(final PrintStream ds) {
        this.debugStream = ds;
    }
    
    private final int jjStopStringLiteralDfa_0(final int pos, final long active0) {
        switch (pos) {
            case 0: {
                if ((active0 & 0x1000000L) != 0x0L) {
                    this.jjmatchedKind = 27;
                    return 5;
                }
                return -1;
            }
            case 1: {
                if ((active0 & 0x1000000L) != 0x0L) {
                    this.jjmatchedKind = 27;
                    this.jjmatchedPos = 1;
                    return 5;
                }
                return -1;
            }
            case 2: {
                if ((active0 & 0x1000000L) != 0x0L) {
                    this.jjmatchedKind = 27;
                    this.jjmatchedPos = 2;
                    return 5;
                }
                return -1;
            }
            case 3: {
                if ((active0 & 0x1000000L) != 0x0L) {
                    this.jjmatchedKind = 27;
                    this.jjmatchedPos = 3;
                    return 5;
                }
                return -1;
            }
            case 4: {
                if ((active0 & 0x1000000L) != 0x0L) {
                    this.jjmatchedKind = 27;
                    this.jjmatchedPos = 4;
                    return 5;
                }
                return -1;
            }
            case 5: {
                if ((active0 & 0x1000000L) != 0x0L) {
                    this.jjmatchedKind = 27;
                    return this.jjmatchedPos = 5;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int jjStartNfa_0(final int pos, final long active0) {
        return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0), pos + 1);
    }
    
    private int jjStopAtPos(final int pos, final int kind) {
        this.jjmatchedKind = kind;
        return (this.jjmatchedPos = pos) + 1;
    }
    
    private int jjMoveStringLiteralDfa0_0() {
        switch (this.curChar) {
            case '#': {
                return this.jjStopAtPos(0, 6);
            }
            case '$': {
                return this.jjStopAtPos(0, 5);
            }
            case '%': {
                return this.jjStopAtPos(0, 22);
            }
            case '(': {
                return this.jjStopAtPos(0, 10);
            }
            case ')': {
                return this.jjStopAtPos(0, 11);
            }
            case '+': {
                return this.jjStopAtPos(0, 21);
            }
            case ',': {
                return this.jjStopAtPos(0, 12);
            }
            case '/': {
                return this.jjMoveStringLiteralDfa1_0(128L);
            }
            case ':': {
                this.jjmatchedKind = 15;
                return this.jjMoveStringLiteralDfa1_0(262144L);
            }
            case ';': {
                return this.jjStopAtPos(0, 16);
            }
            case '=': {
                return this.jjStopAtPos(0, 17);
            }
            case '?': {
                return this.jjStopAtPos(0, 19);
            }
            case '@': {
                return this.jjStopAtPos(0, 20);
            }
            case '[': {
                return this.jjStopAtPos(0, 13);
            }
            case ']': {
                return this.jjStopAtPos(0, 14);
            }
            case 'd': {
                return this.jjMoveStringLiteralDfa1_0(16777216L);
            }
            case '{': {
                return this.jjStopAtPos(0, 8);
            }
            case '|': {
                return this.jjStopAtPos(0, 23);
            }
            case '}': {
                return this.jjStopAtPos(0, 9);
            }
            default: {
                return this.jjMoveNfa_0(0, 0);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa1_0(final long active0) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(0, active0);
            return 1;
        }
        switch (this.curChar) {
            case '/': {
                if ((active0 & 0x80L) != 0x0L) {
                    return this.jjStopAtPos(1, 7);
                }
                break;
            }
            case '=': {
                if ((active0 & 0x40000L) != 0x0L) {
                    return this.jjStopAtPos(1, 18);
                }
                break;
            }
            case 'e': {
                return this.jjMoveStringLiteralDfa2_0(active0, 16777216L);
            }
        }
        return this.jjStartNfa_0(0, active0);
    }
    
    private int jjMoveStringLiteralDfa2_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(0, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(1, active0);
            return 2;
        }
        switch (this.curChar) {
            case 'f': {
                return this.jjMoveStringLiteralDfa3_0(active0, 16777216L);
            }
            default: {
                return this.jjStartNfa_0(1, active0);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa3_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(1, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(2, active0);
            return 3;
        }
        switch (this.curChar) {
            case 'i': {
                return this.jjMoveStringLiteralDfa4_0(active0, 16777216L);
            }
            default: {
                return this.jjStartNfa_0(2, active0);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa4_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(2, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(3, active0);
            return 4;
        }
        switch (this.curChar) {
            case 'n': {
                return this.jjMoveStringLiteralDfa5_0(active0, 16777216L);
            }
            default: {
                return this.jjStartNfa_0(3, active0);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa5_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(3, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(4, active0);
            return 5;
        }
        switch (this.curChar) {
            case 'e': {
                return this.jjMoveStringLiteralDfa6_0(active0, 16777216L);
            }
            default: {
                return this.jjStartNfa_0(4, active0);
            }
        }
    }
    
    private int jjMoveStringLiteralDfa6_0(final long old0, long active0) {
        if ((active0 &= old0) == 0x0L) {
            return this.jjStartNfa_0(4, old0);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            this.jjStopStringLiteralDfa_0(5, active0);
            return 6;
        }
        switch (this.curChar) {
            case 'd': {
                if ((active0 & 0x1000000L) != 0x0L) {
                    return this.jjStartNfaWithStates_0(6, 24, 5);
                }
                break;
            }
        }
        return this.jjStartNfa_0(5, active0);
    }
    
    private int jjStartNfaWithStates_0(final int pos, final int kind, final int state) {
        this.jjmatchedKind = kind;
        this.jjmatchedPos = pos;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException e) {
            return pos + 1;
        }
        return this.jjMoveNfa_0(state, pos + 1);
    }
    
    private int jjMoveNfa_0(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 12;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x3FF000000000000L & l) != 0x0L) {
                                if (kind > 26) {
                                    kind = 26;
                                }
                                this.jjCheckNAdd(3);
                            }
                            else if ((0x640000000000L & l) != 0x0L) {
                                if (kind > 27) {
                                    kind = 27;
                                }
                                this.jjCheckNAdd(5);
                            }
                            else if (this.curChar == '\'') {
                                this.jjCheckNAddTwoStates(10, 11);
                            }
                            else if (this.curChar == '\"') {
                                this.jjCheckNAddTwoStates(7, 8);
                            }
                            if (this.curChar == '-') {
                                this.jjCheckNAdd(3);
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 25) {
                                kind = 25;
                                continue;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '-') {
                                this.jjCheckNAdd(3);
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if ((0x3FF000000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 26) {
                                kind = 26;
                            }
                            this.jjCheckNAdd(3);
                            continue;
                        }
                        case 4: {
                            if ((0x640000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 27) {
                                kind = 27;
                            }
                            this.jjCheckNAdd(5);
                            continue;
                        }
                        case 5: {
                            if ((0x3FF640000000000L & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 27) {
                                kind = 27;
                            }
                            this.jjCheckNAdd(5);
                            continue;
                        }
                        case 6: {
                            if (this.curChar == '\"') {
                                this.jjCheckNAddTwoStates(7, 8);
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if ((0xFFFFFFFBFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(7, 8);
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if (this.curChar == '\"' && kind > 28) {
                                kind = 28;
                                continue;
                            }
                            continue;
                        }
                        case 9: {
                            if (this.curChar == '\'') {
                                this.jjCheckNAddTwoStates(10, 11);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if ((0xFFFFFF7FFFFFFFFFL & l) != 0x0L) {
                                this.jjCheckNAddTwoStates(10, 11);
                                continue;
                            }
                            continue;
                        }
                        case 11: {
                            if (this.curChar == '\'' && kind > 28) {
                                kind = 28;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0: {
                            if ((0x7FFFFFE87FFFFFEL & l) != 0x0L) {
                                if (kind > 27) {
                                    kind = 27;
                                }
                                this.jjCheckNAdd(5);
                                continue;
                            }
                            if (this.curChar == '\\') {
                                this.jjstateSet[this.jjnewStateCnt++] = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if (kind > 25) {
                                kind = 25;
                                continue;
                            }
                            continue;
                        }
                        case 4:
                        case 5: {
                            if ((0x7FFFFFE87FFFFFEL & l) == 0x0L) {
                                continue;
                            }
                            if (kind > 27) {
                                kind = 27;
                            }
                            this.jjCheckNAdd(5);
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 7: {
                            this.jjAddStates(0, 1);
                            continue;
                        }
                        case 10: {
                            this.jjAddStates(2, 3);
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int i2 = (this.curChar & '\u00ff') >> 6;
                final long l2 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if ((InterpreterTokenManager.jjbitVec0[i2] & l2) != 0x0L && kind > 25) {
                                kind = 25;
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if ((InterpreterTokenManager.jjbitVec0[i2] & l2) != 0x0L) {
                                this.jjAddStates(0, 1);
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if ((InterpreterTokenManager.jjbitVec0[i2] & l2) != 0x0L) {
                                this.jjAddStates(2, 3);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 12;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    private int jjMoveStringLiteralDfa0_1() {
        return this.jjMoveNfa_1(1, 0);
    }
    
    private int jjMoveNfa_1(final int startState, int curPos) {
        int startsAt = 0;
        this.jjnewStateCnt = 4;
        int i = 1;
        this.jjstateSet[0] = startState;
        int kind = Integer.MAX_VALUE;
        while (true) {
            if (++this.jjround == Integer.MAX_VALUE) {
                this.ReInitRounds();
            }
            if (this.curChar < '@') {
                final long l = 1L << this.curChar;
                do {
                    switch (this.jjstateSet[--i]) {
                        case 1: {
                            if ((0xFFFFFFFFFFFFDBFFL & l) != 0x0L) {
                                if (kind > 34) {
                                    kind = 34;
                                }
                                this.jjCheckNAdd(0);
                            }
                            else if ((0x2400L & l) != 0x0L && kind > 35) {
                                kind = 35;
                            }
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if ((0xFFFFFFFFFFFFDBFFL & l) == 0x0L) {
                                continue;
                            }
                            kind = 34;
                            this.jjCheckNAdd(0);
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '\n' && kind > 35) {
                                kind = 35;
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if (this.curChar == '\r') {
                                this.jjstateSet[this.jjnewStateCnt++] = 2;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else if (this.curChar < '\u0080') {
                final long l = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0:
                        case 1: {
                            kind = 34;
                            this.jjCheckNAdd(0);
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            else {
                final int i2 = (this.curChar & '\u00ff') >> 6;
                final long l2 = 1L << (this.curChar & '?');
                do {
                    switch (this.jjstateSet[--i]) {
                        case 0:
                        case 1: {
                            if ((InterpreterTokenManager.jjbitVec0[i2] & l2) == 0x0L) {
                                continue;
                            }
                            if (kind > 34) {
                                kind = 34;
                            }
                            this.jjCheckNAdd(0);
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != startsAt);
            }
            if (kind != Integer.MAX_VALUE) {
                this.jjmatchedKind = kind;
                this.jjmatchedPos = curPos;
                kind = Integer.MAX_VALUE;
            }
            ++curPos;
            final int n = i = this.jjnewStateCnt;
            final int n2 = 4;
            final int jjnewStateCnt = startsAt;
            this.jjnewStateCnt = jjnewStateCnt;
            if (n == (startsAt = n2 - jjnewStateCnt)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException e) {
                return curPos;
            }
        }
        return curPos;
    }
    
    public InterpreterTokenManager(final SimpleCharStream stream) {
        this.debugStream = System.out;
        this.jjrounds = new int[12];
        this.jjstateSet = new int[24];
        this.jjimage = new StringBuilder();
        this.image = this.jjimage;
        this.curLexState = 0;
        this.defaultLexState = 0;
        this.input_stream = stream;
    }
    
    public InterpreterTokenManager(final SimpleCharStream stream, final int lexState) {
        this(stream);
        this.SwitchTo(lexState);
    }
    
    public void ReInit(final SimpleCharStream stream) {
        final boolean b = false;
        this.jjnewStateCnt = (b ? 1 : 0);
        this.jjmatchedPos = (b ? 1 : 0);
        this.curLexState = this.defaultLexState;
        this.input_stream = stream;
        this.ReInitRounds();
    }
    
    private void ReInitRounds() {
        this.jjround = -2147483647;
        int i = 12;
        while (i-- > 0) {
            this.jjrounds[i] = Integer.MIN_VALUE;
        }
    }
    
    public void ReInit(final SimpleCharStream stream, final int lexState) {
        this.ReInit(stream);
        this.SwitchTo(lexState);
    }
    
    public void SwitchTo(final int lexState) {
        if (lexState >= 2 || lexState < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
        }
        this.curLexState = lexState;
    }
    
    protected Token jjFillToken() {
        final String im = InterpreterTokenManager.jjstrLiteralImages[this.jjmatchedKind];
        final String curTokenImage = (im == null) ? this.input_stream.GetImage() : im;
        final int beginLine = this.input_stream.getBeginLine();
        final int beginColumn = this.input_stream.getBeginColumn();
        final int endLine = this.input_stream.getEndLine();
        final int endColumn = this.input_stream.getEndColumn();
        final Token t = Token.newToken(this.jjmatchedKind);
        t.kind = this.jjmatchedKind;
        t.image = curTokenImage;
        t.beginLine = beginLine;
        t.endLine = endLine;
        t.beginColumn = beginColumn;
        t.endColumn = endColumn;
        return t;
    }
    
    public Token getNextToken() {
        Token specialToken = null;
        int curPos = 0;
    Label_0004_Outer:
        while (true) {
        Label_0004:
            while (true) {
                try {
                    this.curChar = this.input_stream.BeginToken();
                }
                catch (IOException e) {
                    this.jjmatchedKind = 0;
                    final Token matchedToken = this.jjFillToken();
                    matchedToken.specialToken = specialToken;
                    return matchedToken;
                }
                (this.image = this.jjimage).setLength(0);
                this.jjimageLen = 0;
                while (true) {
                    switch (this.curLexState) {
                        case 0: {
                            try {
                                this.input_stream.backup(0);
                                while (this.curChar <= ' ') {
                                    if ((0x100002600L & 1L << this.curChar) == 0x0L) {
                                        break;
                                    }
                                    this.curChar = this.input_stream.BeginToken();
                                }
                            }
                            catch (IOException e2) {
                                continue Label_0004;
                            }
                            this.jjmatchedKind = Integer.MAX_VALUE;
                            this.jjmatchedPos = 0;
                            curPos = this.jjMoveStringLiteralDfa0_0();
                            if (this.jjmatchedPos == 0 && this.jjmatchedKind > 33) {
                                this.jjmatchedKind = 33;
                                break;
                            }
                            break;
                        }
                        case 1: {
                            this.jjmatchedKind = Integer.MAX_VALUE;
                            this.jjmatchedPos = 0;
                            curPos = this.jjMoveStringLiteralDfa0_1();
                            break;
                        }
                    }
                    if (this.jjmatchedKind == Integer.MAX_VALUE) {
                        break Label_0004_Outer;
                    }
                    if (this.jjmatchedPos + 1 < curPos) {
                        this.input_stream.backup(curPos - this.jjmatchedPos - 1);
                    }
                    if ((InterpreterTokenManager.jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0x0L) {
                        final Token matchedToken = this.jjFillToken();
                        matchedToken.specialToken = specialToken;
                        this.TokenLexicalActions(matchedToken);
                        if (InterpreterTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                            this.curLexState = InterpreterTokenManager.jjnewLexState[this.jjmatchedKind];
                        }
                        return matchedToken;
                    }
                    if ((InterpreterTokenManager.jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) == 0x0L) {
                        this.jjimageLen += this.jjmatchedPos + 1;
                        if (InterpreterTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                            this.curLexState = InterpreterTokenManager.jjnewLexState[this.jjmatchedKind];
                        }
                        curPos = 0;
                        this.jjmatchedKind = Integer.MAX_VALUE;
                        try {
                            this.curChar = this.input_stream.readChar();
                            continue Label_0004_Outer;
                        }
                        catch (IOException ex) {}
                        break Label_0004_Outer;
                    }
                    if ((InterpreterTokenManager.jjtoSpecial[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 0x3F)) != 0x0L) {
                        final Token matchedToken = this.jjFillToken();
                        if (specialToken == null) {
                            specialToken = matchedToken;
                        }
                        else {
                            matchedToken.specialToken = specialToken;
                            final Token token = specialToken;
                            final Token next = matchedToken;
                            token.next = next;
                            specialToken = next;
                        }
                        this.SkipLexicalActions(matchedToken);
                    }
                    else {
                        this.SkipLexicalActions(null);
                    }
                    if (InterpreterTokenManager.jjnewLexState[this.jjmatchedKind] != -1) {
                        this.curLexState = InterpreterTokenManager.jjnewLexState[this.jjmatchedKind];
                        break;
                    }
                    break;
                }
                break;
            }
        }
        int error_line = this.input_stream.getEndLine();
        int error_column = this.input_stream.getEndColumn();
        String error_after = null;
        boolean EOFSeen = false;
        try {
            this.input_stream.readChar();
            this.input_stream.backup(1);
        }
        catch (IOException e3) {
            EOFSeen = true;
            error_after = ((curPos <= 1) ? "" : this.input_stream.GetImage());
            if (this.curChar == '\n' || this.curChar == '\r') {
                ++error_line;
                error_column = 0;
            }
            else {
                ++error_column;
            }
        }
        if (!EOFSeen) {
            this.input_stream.backup(1);
            error_after = ((curPos <= 1) ? "" : this.input_stream.GetImage());
        }
        throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
    }
    
    void SkipLexicalActions(final Token matchedToken) {
        final int jjmatchedKind = this.jjmatchedKind;
    }
    
    void TokenLexicalActions(final Token matchedToken) {
        switch (this.jjmatchedKind) {
            case 26: {
                final StringBuilder image = this.image;
                final SimpleCharStream input_stream = this.input_stream;
                final int jjimageLen = this.jjimageLen;
                final int lengthOfMatch = this.jjmatchedPos + 1;
                this.lengthOfMatch = lengthOfMatch;
                image.append(input_stream.GetSuffix(jjimageLen + lengthOfMatch));
                if (matchedToken.image.charAt(0) == '$') {
                    matchedToken.kind = 27;
                    break;
                }
                break;
            }
        }
    }
    
    private void jjCheckNAdd(final int state) {
        if (this.jjrounds[state] != this.jjround) {
            this.jjstateSet[this.jjnewStateCnt++] = state;
            this.jjrounds[state] = this.jjround;
        }
    }
    
    private void jjAddStates(int start, final int end) {
        do {
            this.jjstateSet[this.jjnewStateCnt++] = InterpreterTokenManager.jjnextStates[start];
        } while (start++ != end);
    }
    
    private void jjCheckNAddTwoStates(final int state1, final int state2) {
        this.jjCheckNAdd(state1);
        this.jjCheckNAdd(state2);
    }
}

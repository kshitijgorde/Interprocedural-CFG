// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.oro.text.regex;

import java.util.HashMap;

public final class Perl5Compiler implements PatternCompiler
{
    private static final int __WORSTCASE = 0;
    private static final int __NONNULL = 1;
    private static final int __SIMPLE = 2;
    private static final int __SPSTART = 4;
    private static final int __TRYAGAIN = 8;
    private static final char __CASE_INSENSITIVE = '\u0001';
    private static final char __GLOBAL = '\u0002';
    private static final char __KEEP = '\u0004';
    private static final char __MULTILINE = '\b';
    private static final char __SINGLELINE = '\u0010';
    private static final char __EXTENDED = ' ';
    private static final char __READ_ONLY = '\u8000';
    private static final String __HEX_DIGIT = "0123456789abcdef0123456789ABCDEFx";
    private CharStringPointer __input;
    private boolean __sawBackreference;
    private char[] __modifierFlags;
    private int __numParentheses;
    private int __programSize;
    private int __cost;
    private char[] __program;
    private static final HashMap __hashPOSIX;
    public static final int DEFAULT_MASK = 0;
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int MULTILINE_MASK = 8;
    public static final int SINGLELINE_MASK = 16;
    public static final int EXTENDED_MASK = 32;
    public static final int READ_ONLY_MASK = 32768;
    
    public Perl5Compiler() {
        this.__modifierFlags = new char[] { '\0' };
    }
    
    public static final String quotemeta(final char[] array) {
        final StringBuffer sb = new StringBuffer(2 * array.length);
        for (int i = 0; i < array.length; ++i) {
            if (!OpCode._isWordCharacter(array[i])) {
                sb.append('\\');
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static final String quotemeta(final String s) {
        return quotemeta(s.toCharArray());
    }
    
    private static boolean __isSimpleRepetitionOp(final char c) {
        return c == '*' || c == '+' || c == '?';
    }
    
    private static boolean __isComplexRepetitionOp(final char[] array, final int n) {
        return n < array.length && n >= 0 && (array[n] == '*' || array[n] == '+' || array[n] == '?' || (array[n] == '{' && __parseRepetition(array, n)));
    }
    
    private static boolean __parseRepetition(final char[] array, int n) {
        if (array[n] != '{') {
            return false;
        }
        if (++n >= array.length || !Character.isDigit(array[n])) {
            return false;
        }
        while (n < array.length && Character.isDigit(array[n])) {
            ++n;
        }
        if (n < array.length && array[n] == ',') {
            ++n;
        }
        while (n < array.length && Character.isDigit(array[n])) {
            ++n;
        }
        return n < array.length && array[n] == '}';
    }
    
    private static int __parseHex(final char[] array, int n, int n2, final int[] array2) {
        int n3 = 0;
        array2[0] = 0;
        int index;
        while (n < array.length && n2-- > 0 && (index = "0123456789abcdef0123456789ABCDEFx".indexOf(array[n])) != -1) {
            n3 = (n3 << 4 | (index & 0xF));
            ++n;
            final int n4 = 0;
            ++array2[n4];
        }
        return n3;
    }
    
    private static int __parseOctal(final char[] array, int n, int n2, final int[] array2) {
        int n3 = 0;
        array2[0] = 0;
        while (n < array.length && n2 > 0 && array[n] >= '0' && array[n] <= '7') {
            n3 = (n3 << 3 | array[n] - '0');
            --n2;
            ++n;
            final int n4 = 0;
            ++array2[n4];
        }
        return n3;
    }
    
    private static void __setModifierFlag(final char[] array, final char c) {
        switch (c) {
            case 'i': {
                final int n = 0;
                array[n] |= '\u0001';
            }
            case 'g': {
                final int n2 = 0;
                array[n2] |= '\u0002';
            }
            case 'o': {
                final int n3 = 0;
                array[n3] |= '\u0004';
            }
            case 'm': {
                final int n4 = 0;
                array[n4] |= '\b';
            }
            case 's': {
                final int n5 = 0;
                array[n5] |= '\u0010';
            }
            case 'x': {
                final int n6 = 0;
                array[n6] |= ' ';
            }
            default: {}
        }
    }
    
    private void __emitCode(final char c) {
        if (this.__program != null) {
            this.__program[this.__programSize] = c;
        }
        ++this.__programSize;
    }
    
    private int __emitNode(final char c) {
        final int _programSize = this.__programSize;
        if (this.__program == null) {
            this.__programSize += 2;
        }
        else {
            this.__program[this.__programSize++] = c;
            this.__program[this.__programSize++] = '\0';
        }
        return _programSize;
    }
    
    private int __emitArgNode(final char c, final char c2) {
        final int _programSize = this.__programSize;
        if (this.__program == null) {
            this.__programSize += 3;
        }
        else {
            this.__program[this.__programSize++] = c;
            this.__program[this.__programSize++] = '\0';
            this.__program[this.__programSize++] = c2;
        }
        return _programSize;
    }
    
    private void __programInsertOperator(final char c, int n) {
        int n2 = (OpCode._opType[c] == '\n') ? 2 : 0;
        if (this.__program == null) {
            this.__programSize += 2 + n2;
            return;
        }
        int i = this.__programSize;
        this.__programSize += 2 + n2;
        for (int _programSize = this.__programSize; i > n; --i, --_programSize, this.__program[_programSize] = this.__program[i]) {}
        this.__program[n++] = c;
        this.__program[n++] = '\0';
        while (n2-- > 0) {
            this.__program[n++] = '\0';
        }
    }
    
    private void __programAddTail(final int n, final int n2) {
        if (this.__program == null || n == -1) {
            return;
        }
        int n3 = n;
        while (true) {
            final int getNext = OpCode._getNext(this.__program, n3);
            if (getNext == -1) {
                break;
            }
            n3 = getNext;
        }
        int n4;
        if (this.__program[n3] == '\r') {
            n4 = n3 - n2;
        }
        else {
            n4 = n2 - n3;
        }
        this.__program[n3 + 1] = (char)n4;
    }
    
    private void __programAddOperatorTail(final int n, final int n2) {
        if (this.__program == null || n == -1 || OpCode._opType[this.__program[n]] != '\f') {
            return;
        }
        this.__programAddTail(OpCode._getNextOperator(n), n2);
    }
    
    private char __getNextChar() {
        final char postIncrement = this.__input._postIncrement();
        while (true) {
            char c = this.__input._getValue();
            if (c == '(' && this.__input._getValueRelative(1) == '?' && this.__input._getValueRelative(2) == '#') {
                while (c != '\uffff' && c != ')') {
                    c = this.__input._increment();
                }
                this.__input._increment();
            }
            else {
                if ((this.__modifierFlags[0] & ' ') == '\0') {
                    break;
                }
                if (Character.isWhitespace(c)) {
                    this.__input._increment();
                }
                else {
                    if (c != '#') {
                        break;
                    }
                    while (c != '\uffff' && c != '\n') {
                        c = this.__input._increment();
                    }
                    this.__input._increment();
                }
            }
        }
        return postIncrement;
    }
    
    private int __parseAlternation(final int[] array) throws MalformedPatternException {
        int n = 0;
        array[0] = 0;
        final int _emitNode = this.__emitNode('\f');
        int n2 = -1;
        if (this.__input._getOffset() == 0) {
            this.__input._setOffset(-1);
            this.__getNextChar();
        }
        else {
            this.__input._decrement();
            this.__getNextChar();
        }
        for (char c = this.__input._getValue(); c != '\uffff' && c != '|' && c != ')'; c = this.__input._getValue()) {
            n &= 0xFFFFFFF7;
            final int _parseBranch = this.__parseBranch(array);
            if (_parseBranch == -1) {
                if ((n & 0x8) == 0x0) {
                    return -1;
                }
            }
            else {
                final int n3 = 0;
                array[n3] |= (n & 0x1);
                if (n2 == -1) {
                    final int n4 = 0;
                    array[n4] |= (n & 0x4);
                }
                else {
                    ++this.__cost;
                    this.__programAddTail(n2, _parseBranch);
                }
                n2 = _parseBranch;
            }
        }
        if (n2 == -1) {
            this.__emitNode('\u000f');
        }
        return _emitNode;
    }
    
    private int __parseAtom(final int[] array) throws MalformedPatternException {
        final int[] array2 = { 0 };
        array[0] = 0;
        boolean b = false;
        int n = -1;
        Label_1296: {
        Label_1283:
            while (true) {
                switch (this.__input._getValue()) {
                    case '^': {
                        this.__getNextChar();
                        if ((this.__modifierFlags[0] & '\b') != '\0') {
                            n = this.__emitNode('\u0002');
                            break Label_1296;
                        }
                        if ((this.__modifierFlags[0] & '\u0010') != '\0') {
                            n = this.__emitNode('\u0003');
                            break Label_1296;
                        }
                        n = this.__emitNode('\u0001');
                        break Label_1296;
                    }
                    case '$': {
                        this.__getNextChar();
                        if ((this.__modifierFlags[0] & '\b') != '\0') {
                            n = this.__emitNode('\u0005');
                            break Label_1296;
                        }
                        if ((this.__modifierFlags[0] & '\u0010') != '\0') {
                            n = this.__emitNode('\u0006');
                            break Label_1296;
                        }
                        n = this.__emitNode('\u0004');
                        break Label_1296;
                    }
                    case '.': {
                        this.__getNextChar();
                        if ((this.__modifierFlags[0] & '\u0010') != '\0') {
                            n = this.__emitNode('\b');
                        }
                        else {
                            n = this.__emitNode('\u0007');
                        }
                        ++this.__cost;
                        final int n2 = 0;
                        array[n2] |= 0x3;
                        break Label_1296;
                    }
                    case '[': {
                        this.__input._increment();
                        n = this.__parseUnicodeClass();
                        final int n3 = 0;
                        array[n3] |= 0x3;
                        break Label_1296;
                    }
                    case '(': {
                        this.__getNextChar();
                        n = this.__parseExpression(true, array2);
                        if (n != -1) {
                            final int n4 = 0;
                            array[n4] |= (array2[0] & 0x5);
                            break Label_1296;
                        }
                        if ((array2[0] & 0x8) != 0x0) {
                            continue;
                        }
                        return -1;
                    }
                    case ')':
                    case '|': {
                        if ((array2[0] & 0x8) != 0x0) {
                            final int n5 = 0;
                            array[n5] |= 0x8;
                            return -1;
                        }
                        throw new MalformedPatternException("Error in expression at " + this.__input._toString(this.__input._getOffset()));
                    }
                    case '*':
                    case '+':
                    case '?': {
                        throw new MalformedPatternException("?+* follows nothing in expression");
                    }
                    case '\\': {
                        switch (this.__input._increment()) {
                            case 'A': {
                                n = this.__emitNode('\u0003');
                                final int n6 = 0;
                                array[n6] |= 0x2;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'G': {
                                n = this.__emitNode('\u001e');
                                final int n7 = 0;
                                array[n7] |= 0x2;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'Z': {
                                n = this.__emitNode('\u0006');
                                final int n8 = 0;
                                array[n8] |= 0x2;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'w': {
                                n = this.__emitNode('\u0012');
                                final int n9 = 0;
                                array[n9] |= 0x3;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'W': {
                                n = this.__emitNode('\u0013');
                                final int n10 = 0;
                                array[n10] |= 0x3;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'b': {
                                n = this.__emitNode('\u0014');
                                final int n11 = 0;
                                array[n11] |= 0x2;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'B': {
                                n = this.__emitNode('\u0015');
                                final int n12 = 0;
                                array[n12] |= 0x2;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 's': {
                                n = this.__emitNode('\u0016');
                                final int n13 = 0;
                                array[n13] |= 0x3;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'S': {
                                n = this.__emitNode('\u0017');
                                final int n14 = 0;
                                array[n14] |= 0x3;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'd': {
                                n = this.__emitNode('\u0018');
                                final int n15 = 0;
                                array[n15] |= 0x3;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case 'D': {
                                n = this.__emitNode('\u0019');
                                final int n16 = 0;
                                array[n16] |= 0x3;
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case '0':
                            case 'a':
                            case 'c':
                            case 'e':
                            case 'f':
                            case 'n':
                            case 'r':
                            case 't':
                            case 'x': {
                                b = true;
                                break Label_1296;
                            }
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9': {
                                final StringBuffer sb = new StringBuffer(10);
                                int n17 = 0;
                                for (char c = this.__input._getValueRelative(n17); Character.isDigit(c); c = this.__input._getValueRelative(n17)) {
                                    sb.append(c);
                                    ++n17;
                                }
                                int int1;
                                try {
                                    int1 = Integer.parseInt(sb.toString());
                                }
                                catch (NumberFormatException ex) {
                                    throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex.getMessage());
                                }
                                if (int1 > 9 && int1 >= this.__numParentheses) {
                                    b = true;
                                    break Label_1296;
                                }
                                if (int1 >= this.__numParentheses) {
                                    throw new MalformedPatternException("Invalid backreference: \\" + int1);
                                }
                                this.__sawBackreference = true;
                                n = this.__emitArgNode('\u001a', (char)int1);
                                final int n18 = 0;
                                array[n18] |= 0x1;
                                for (char c2 = this.__input._getValue(); Character.isDigit(c2); c2 = this.__input._increment()) {}
                                this.__input._decrement();
                                this.__getNextChar();
                                break Label_1296;
                            }
                            case '\0':
                            case '\uffff': {
                                if (this.__input._isAtEnd()) {
                                    throw new MalformedPatternException("Trailing \\ in expression.");
                                }
                                break;
                            }
                        }
                        b = true;
                        break Label_1296;
                    }
                    case '#': {
                        if ((this.__modifierFlags[0] & ' ') == '\0') {
                            break Label_1283;
                        }
                        while (!this.__input._isAtEnd() && this.__input._getValue() != '\n') {
                            this.__input._increment();
                        }
                        if (!this.__input._isAtEnd()) {
                            continue;
                        }
                        break Label_1283;
                    }
                    default: {
                        break Label_1283;
                    }
                }
            }
            this.__input._increment();
            b = true;
        }
        if (b) {
            n = this.__emitNode('\u000e');
            this.__emitCode('\uffff');
            int n19 = 0;
            int n20 = this.__input._getOffset() - 1;
        Label_2320:
            for (int getLength = this.__input._getLength(); n19 < 127 && n20 < getLength; ++n19) {
                final int n21 = n20;
                char c3 = '\0';
                Label_2236: {
                    switch (this.__input._getValue(n20)) {
                        case '$':
                        case '(':
                        case ')':
                        case '.':
                        case '[':
                        case '^':
                        case '|': {
                            break Label_2320;
                        }
                        case '\\': {
                            switch (this.__input._getValue(++n20)) {
                                case 'A':
                                case 'B':
                                case 'D':
                                case 'G':
                                case 'S':
                                case 'W':
                                case 'Z':
                                case 'b':
                                case 'd':
                                case 's':
                                case 'w': {
                                    --n20;
                                    break Label_2320;
                                }
                                case 'n': {
                                    c3 = '\n';
                                    ++n20;
                                    break Label_2236;
                                }
                                case 'r': {
                                    c3 = '\r';
                                    ++n20;
                                    break Label_2236;
                                }
                                case 't': {
                                    c3 = '\t';
                                    ++n20;
                                    break Label_2236;
                                }
                                case 'f': {
                                    c3 = '\f';
                                    ++n20;
                                    break Label_2236;
                                }
                                case 'e': {
                                    c3 = '\u001b';
                                    ++n20;
                                    break Label_2236;
                                }
                                case 'a': {
                                    c3 = '\u0007';
                                    ++n20;
                                    break Label_2236;
                                }
                                case 'x': {
                                    final int[] array3 = { 0 };
                                    c3 = (char)__parseHex(this.__input._array, ++n20, 2, array3);
                                    n20 += array3[0];
                                    break Label_2236;
                                }
                                case 'c': {
                                    ++n20;
                                    char c4 = this.__input._getValue(n20++);
                                    if (Character.isLowerCase(c4)) {
                                        c4 = Character.toUpperCase(c4);
                                    }
                                    c3 = (char)(c4 ^ '@');
                                    break Label_2236;
                                }
                                case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9': {
                                    boolean b2 = false;
                                    if (this.__input._getValue(n20) == '0') {
                                        b2 = true;
                                    }
                                    if (Character.isDigit(this.__input._getValue(n20 + 1))) {
                                        final StringBuffer sb2 = new StringBuffer(10);
                                        int n22 = n20;
                                        for (char c5 = this.__input._getValue(n22); Character.isDigit(c5); c5 = this.__input._getValue(n22)) {
                                            sb2.append(c5);
                                            ++n22;
                                        }
                                        int int2;
                                        try {
                                            int2 = Integer.parseInt(sb2.toString());
                                        }
                                        catch (NumberFormatException ex2) {
                                            throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex2.getMessage());
                                        }
                                        if (!b2) {
                                            b2 = (int2 >= this.__numParentheses);
                                        }
                                    }
                                    if (b2) {
                                        final int[] array4 = { 0 };
                                        c3 = (char)__parseOctal(this.__input._array, n20, 3, array4);
                                        n20 += array4[0];
                                        break Label_2236;
                                    }
                                    --n20;
                                    break Label_2320;
                                }
                                case '\0':
                                case '\uffff': {
                                    if (n20 >= getLength) {
                                        throw new MalformedPatternException("Trailing \\ in expression.");
                                    }
                                    break;
                                }
                            }
                            c3 = this.__input._getValue(n20++);
                            break Label_2236;
                        }
                        case '#': {
                            if ((this.__modifierFlags[0] & ' ') != '\0') {
                                while (n20 < getLength && this.__input._getValue(n20) != '\n') {
                                    ++n20;
                                }
                            }
                        }
                        case '\t':
                        case '\n':
                        case '\u000b':
                        case '\f':
                        case '\r':
                        case ' ': {
                            if ((this.__modifierFlags[0] & ' ') != '\0') {
                                ++n20;
                                --n19;
                                continue;
                            }
                            break;
                        }
                    }
                    c3 = this.__input._getValue(n20++);
                }
                if ((this.__modifierFlags[0] & '\u0001') != '\0' && Character.isUpperCase(c3)) {
                    c3 = Character.toLowerCase(c3);
                }
                if (n20 < getLength && __isComplexRepetitionOp(this.__input._array, n20)) {
                    if (n19 > 0) {
                        n20 = n21;
                        break;
                    }
                    ++n19;
                    this.__emitCode(c3);
                    break;
                }
                else {
                    this.__emitCode(c3);
                }
            }
            this.__input._setOffset(n20 - 1);
            this.__getNextChar();
            if (n19 < 0) {
                throw new MalformedPatternException("Unexpected compilation failure.  Please report this bug!");
            }
            if (n19 > 0) {
                final int n23 = 0;
                array[n23] |= 0x1;
            }
            if (n19 == 1) {
                final int n24 = 0;
                array[n24] |= 0x2;
            }
            if (this.__program != null) {
                this.__program[OpCode._getOperand(n)] = (char)n19;
            }
            this.__emitCode('\uffff');
        }
        return n;
    }
    
    private int __parseUnicodeClass() throws MalformedPatternException {
        int n = 0;
        char c = '\uffff';
        final int[] array = { 0 };
        final boolean[] array2 = { false };
        int n2;
        if (this.__input._getValue() == '^') {
            n2 = this.__emitNode('$');
            this.__input._increment();
        }
        else {
            n2 = this.__emitNode('#');
        }
        char c2 = this.__input._getValue();
        int n3;
        if (c2 == ']' || c2 == '-') {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        while ((!this.__input._isAtEnd() && (c2 = this.__input._getValue()) != ']') || n3 != 0) {
            n3 = 0;
            int n4 = 0;
            this.__input._increment();
            if (c2 == '\\' || c2 == '[') {
                if (c2 == '\\') {
                    c2 = this.__input._postIncrement();
                }
                else {
                    final char _parsePOSIX = this.__parsePOSIX(array2);
                    if (_parsePOSIX != '\0') {
                        n4 = 1;
                        c2 = _parsePOSIX;
                    }
                }
                if (n4 != 1) {
                    switch (c2) {
                        case 119: {
                            n4 = 1;
                            c2 = '\u0012';
                            c = '\uffff';
                            break;
                        }
                        case 87: {
                            n4 = 1;
                            c2 = '\u0013';
                            c = '\uffff';
                            break;
                        }
                        case 115: {
                            n4 = 1;
                            c2 = '\u0016';
                            c = '\uffff';
                            break;
                        }
                        case 83: {
                            n4 = 1;
                            c2 = '\u0017';
                            c = '\uffff';
                            break;
                        }
                        case 100: {
                            n4 = 1;
                            c2 = '\u0018';
                            c = '\uffff';
                            break;
                        }
                        case 68: {
                            n4 = 1;
                            c2 = '\u0019';
                            c = '\uffff';
                            break;
                        }
                        case 110: {
                            c2 = '\n';
                            break;
                        }
                        case 114: {
                            c2 = '\r';
                            break;
                        }
                        case 116: {
                            c2 = '\t';
                            break;
                        }
                        case 102: {
                            c2 = '\f';
                            break;
                        }
                        case 98: {
                            c2 = '\b';
                            break;
                        }
                        case 101: {
                            c2 = '\u001b';
                            break;
                        }
                        case 97: {
                            c2 = '\u0007';
                            break;
                        }
                        case 120: {
                            c2 = (char)__parseHex(this.__input._array, this.__input._getOffset(), 2, array);
                            this.__input._increment(array[0]);
                            break;
                        }
                        case 99: {
                            char c3 = this.__input._postIncrement();
                            if (Character.isLowerCase(c3)) {
                                c3 = Character.toUpperCase(c3);
                            }
                            c2 = (char)(c3 ^ '@');
                            break;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            c2 = (char)__parseOctal(this.__input._array, this.__input._getOffset() - 1, 3, array);
                            this.__input._increment(array[0] - 1);
                            break;
                        }
                    }
                }
            }
            if (n != 0) {
                if (c > c2) {
                    throw new MalformedPatternException("Invalid [] range in expression.");
                }
                n = 0;
            }
            else {
                c = c2;
                if (n4 == 0 && this.__input._getValue() == '-' && this.__input._getOffset() + 1 < this.__input._getLength() && this.__input._getValueRelative(1) != ']') {
                    this.__input._increment();
                    n = 1;
                    continue;
                }
            }
            if (c == c2) {
                if (n4 == 1) {
                    if (!array2[0]) {
                        this.__emitCode('/');
                    }
                    else {
                        this.__emitCode('0');
                    }
                }
                else {
                    this.__emitCode('1');
                }
                this.__emitCode(c2);
                if ((this.__modifierFlags[0] & '\u0001') != '\0' && Character.isUpperCase(c2) && Character.isUpperCase(c)) {
                    --this.__programSize;
                    this.__emitCode(Character.toLowerCase(c2));
                }
            }
            if (c < c2) {
                this.__emitCode('%');
                this.__emitCode(c);
                this.__emitCode(c2);
                if ((this.__modifierFlags[0] & '\u0001') != '\0' && Character.isUpperCase(c2) && Character.isUpperCase(c)) {
                    this.__programSize -= 2;
                    this.__emitCode(Character.toLowerCase(c));
                    this.__emitCode(Character.toLowerCase(c2));
                }
                n = 0;
            }
            c = c2;
        }
        if (this.__input._getValue() != ']') {
            throw new MalformedPatternException("Unmatched [] in expression.");
        }
        this.__getNextChar();
        this.__emitCode('\0');
        return n2;
    }
    
    private char __parsePOSIX(final boolean[] array) throws MalformedPatternException {
        final int getOffset = this.__input._getOffset();
        final int getLength = this.__input._getLength();
        int n = getOffset;
        if (this.__input._getValue(n++) != ':') {
            return '\0';
        }
        if (this.__input._getValue(n) == '^') {
            array[0] = true;
            ++n;
        }
        else {
            array[0] = false;
        }
        final StringBuffer sb = new StringBuffer();
        try {
            char getValue;
            while ((getValue = this.__input._getValue(n++)) != ':' && n < getLength) {
                sb.append(getValue);
            }
        }
        catch (Exception ex) {
            return '\0';
        }
        if (this.__input._getValue(n++) != ']') {
            return '\0';
        }
        final Object value = Perl5Compiler.__hashPOSIX.get(sb.toString());
        if (value == null) {
            return '\0';
        }
        this.__input._setOffset(n);
        return (char)value;
    }
    
    private int __parseBranch(final int[] array) throws MalformedPatternException {
        boolean b = false;
        boolean b2 = false;
        final int[] array2 = { 0 };
        int int1 = 0;
        int int2 = 65535;
        final int _parseAtom = this.__parseAtom(array2);
        if (_parseAtom == -1) {
            if ((array2[0] & 0x8) != 0x0) {
                final int n = 0;
                array[n] |= 0x8;
            }
            return -1;
        }
        char c = this.__input._getValue();
        if (c == '(' && this.__input._getValueRelative(1) == '?' && this.__input._getValueRelative(2) == '#') {
            while (c != '\uffff' && c != ')') {
                c = this.__input._increment();
            }
            if (c != '\uffff') {
                this.__getNextChar();
                c = this.__input._getValue();
            }
        }
        if (c == '{' && __parseRepetition(this.__input._array, this.__input._getOffset())) {
            int n2 = this.__input._getOffset() + 1;
            int n4;
            final int n3 = n4 = this.__input._getLength();
            char c2;
            for (c2 = this.__input._getValue(n2); Character.isDigit(c2) || c2 == ','; c2 = this.__input._getValue(n2)) {
                if (c2 == ',') {
                    if (n4 != n3) {
                        break;
                    }
                    n4 = n2;
                }
                ++n2;
            }
            if (c2 == '}') {
                final StringBuffer sb = new StringBuffer(10);
                if (n4 == n3) {
                    n4 = n2;
                }
                this.__input._increment();
                int getOffset = this.__input._getOffset();
                for (char c3 = this.__input._getValue(getOffset); Character.isDigit(c3); c3 = this.__input._getValue(getOffset)) {
                    sb.append(c3);
                    ++getOffset;
                }
                try {
                    int1 = Integer.parseInt(sb.toString());
                }
                catch (NumberFormatException ex) {
                    throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex.getMessage());
                }
                if (this.__input._getValue(n4) == ',') {
                    ++n4;
                }
                else {
                    n4 = this.__input._getOffset();
                }
                int n5 = n4;
                final StringBuffer sb2 = new StringBuffer(10);
                for (char c4 = this.__input._getValue(n5); Character.isDigit(c4); c4 = this.__input._getValue(n5)) {
                    sb2.append(c4);
                    ++n5;
                }
                try {
                    if (n5 != n4) {
                        int2 = Integer.parseInt(sb2.toString());
                    }
                }
                catch (NumberFormatException ex2) {
                    throw new MalformedPatternException("Unexpected number format exception.  Please report this bug.NumberFormatException message: " + ex2.getMessage());
                }
                if (int2 == 0 && this.__input._getValue(n4) != '0') {
                    int2 = 65535;
                }
                this.__input._setOffset(n2);
                this.__getNextChar();
                b = true;
                b2 = true;
            }
        }
        if (!b) {
            b2 = false;
            if (!__isSimpleRepetitionOp(c)) {
                array[0] = array2[0];
                return _parseAtom;
            }
            this.__getNextChar();
            array[0] = ((c != '+') ? 4 : 1);
            if (c == '*' && (array2[0] & 0x2) != 0x0) {
                this.__programInsertOperator('\u0010', _parseAtom);
                this.__cost += 4;
            }
            else if (c == '*') {
                int1 = 0;
                b2 = true;
            }
            else if (c == '+' && (array2[0] & 0x2) != 0x0) {
                this.__programInsertOperator('\u0011', _parseAtom);
                this.__cost += 3;
            }
            else if (c == '+') {
                int1 = 1;
                b2 = true;
            }
            else if (c == '?') {
                int1 = 0;
                int2 = 1;
                b2 = true;
            }
        }
        if (b2) {
            if ((array2[0] & 0x2) != 0x0) {
                this.__cost += (2 + this.__cost) / 2;
                this.__programInsertOperator('\n', _parseAtom);
            }
            else {
                this.__cost += 4 + this.__cost;
                this.__programAddTail(_parseAtom, this.__emitNode('\"'));
                this.__programInsertOperator('\u000b', _parseAtom);
                this.__programAddTail(_parseAtom, this.__emitNode('\u000f'));
            }
            if (int1 > 0) {
                array[0] = 1;
            }
            if (int2 != 0 && int2 < int1) {
                throw new MalformedPatternException("Invalid interval {" + int1 + "," + int2 + "}");
            }
            if (this.__program != null) {
                this.__program[_parseAtom + 2] = (char)int1;
                this.__program[_parseAtom + 3] = (char)int2;
            }
        }
        if (this.__input._getValue() == '?') {
            this.__getNextChar();
            this.__programInsertOperator('\u001d', _parseAtom);
            this.__programAddTail(_parseAtom, _parseAtom + 2);
        }
        if (__isComplexRepetitionOp(this.__input._array, this.__input._getOffset())) {
            throw new MalformedPatternException("Nested repetitions *?+ in expression");
        }
        return _parseAtom;
    }
    
    private int __parseExpression(final boolean b, final int[] array) throws MalformedPatternException {
        final char[] array2 = { '\0' };
        final char[] array3 = { '\0' };
        int _emitArgNode = -1;
        int _numParentheses = 0;
        final int[] array4 = { 0 };
        final String s = "iogmsx-";
        char[] array5 = array2;
        array[0] = 1;
        char postIncrement;
        if (b) {
            postIncrement = '\u0001';
            if (this.__input._getValue() == '?') {
                this.__input._increment();
                switch (postIncrement = this.__input._postIncrement()) {
                    case '!':
                    case ':':
                    case '=': {
                        break;
                    }
                    case '#': {
                        char c;
                        for (c = this.__input._getValue(); c != '\uffff' && c != ')'; c = this.__input._increment()) {}
                        if (c != ')') {
                            throw new MalformedPatternException("Sequence (?#... not terminated");
                        }
                        this.__getNextChar();
                        array[0] = 8;
                        return -1;
                    }
                    default: {
                        this.__input._decrement();
                        char c2;
                        for (c2 = this.__input._getValue(); c2 != '\uffff' && s.indexOf(c2) != -1; c2 = this.__input._increment()) {
                            if (c2 == '-') {
                                array5 = array3;
                            }
                            else {
                                __setModifierFlag(array5, c2);
                            }
                        }
                        final char[] _modifierFlags = this.__modifierFlags;
                        final int n = 0;
                        _modifierFlags[n] |= array2[0];
                        final char[] _modifierFlags2 = this.__modifierFlags;
                        final int n2 = 0;
                        _modifierFlags2[n2] &= (char)~array3[0];
                        if (c2 != ')') {
                            throw new MalformedPatternException("Sequence (?" + c2 + "...) not recognized");
                        }
                        this.__getNextChar();
                        array[0] = 8;
                        return -1;
                    }
                }
            }
            else {
                _numParentheses = this.__numParentheses;
                ++this.__numParentheses;
                _emitArgNode = this.__emitArgNode('\u001b', (char)_numParentheses);
            }
        }
        else {
            postIncrement = '\0';
        }
        final int _parseAlternation = this.__parseAlternation(array4);
        if (_parseAlternation == -1) {
            return -1;
        }
        if (_emitArgNode != -1) {
            this.__programAddTail(_emitArgNode, _parseAlternation);
        }
        else {
            _emitArgNode = _parseAlternation;
        }
        if ((array4[0] & 0x1) == 0x0) {
            final int n3 = 0;
            array[n3] &= 0xFFFFFFFE;
        }
        final int n4 = 0;
        array[n4] |= (array4[0] & 0x4);
        while (this.__input._getValue() == '|') {
            this.__getNextChar();
            final int _parseAlternation2 = this.__parseAlternation(array4);
            if (_parseAlternation2 == -1) {
                return -1;
            }
            this.__programAddTail(_emitArgNode, _parseAlternation2);
            if ((array4[0] & 0x1) == 0x0) {
                final int n5 = 0;
                array[n5] &= 0xFFFFFFFE;
            }
            final int n6 = 0;
            array[n6] |= (array4[0] & 0x4);
        }
        int n7 = 0;
        switch (postIncrement) {
            case 58: {
                n7 = this.__emitNode('\u000f');
                break;
            }
            case 1: {
                n7 = this.__emitArgNode('\u001c', (char)_numParentheses);
                break;
            }
            case 33:
            case 61: {
                n7 = this.__emitNode('!');
                final int n8 = 0;
                array[n8] &= 0xFFFFFFFE;
                break;
            }
            default: {
                n7 = this.__emitNode('\0');
                break;
            }
        }
        this.__programAddTail(_emitArgNode, n7);
        for (int i = _emitArgNode; i != -1; i = OpCode._getNext(this.__program, i)) {
            this.__programAddOperatorTail(i, n7);
        }
        if (postIncrement == '=') {
            this.__programInsertOperator('\u001f', _emitArgNode);
            this.__programAddTail(_emitArgNode, this.__emitNode('\u000f'));
        }
        else if (postIncrement == '!') {
            this.__programInsertOperator(' ', _emitArgNode);
            this.__programAddTail(_emitArgNode, this.__emitNode('\u000f'));
        }
        if (postIncrement != '\0' && (this.__input._isAtEnd() || this.__getNextChar() != ')')) {
            throw new MalformedPatternException("Unmatched parentheses.");
        }
        if (postIncrement != '\0' || this.__input._isAtEnd()) {
            return _emitArgNode;
        }
        if (this.__input._getValue() == ')') {
            throw new MalformedPatternException("Unmatched parentheses.");
        }
        throw new MalformedPatternException("Unreached characters at end of expression.  Please report this bug!");
    }
    
    public Pattern compile(final char[] array, final int options) throws MalformedPatternException {
        final int[] array2 = { 0 };
        boolean b = false;
        boolean b2 = false;
        char minLength = '\0';
        this.__input = new CharStringPointer(array);
        this.__modifierFlags[0] = (char)options;
        this.__sawBackreference = false;
        this.__numParentheses = 1;
        this.__programSize = 0;
        this.__cost = 0;
        this.__program = null;
        this.__emitCode('\0');
        if (this.__parseExpression(false, array2) == -1) {
            throw new MalformedPatternException("Unknown compilation error.");
        }
        if (this.__programSize >= 65534) {
            throw new MalformedPatternException("Expression is too large.");
        }
        this.__program = new char[this.__programSize];
        final Perl5Pattern perl5Pattern = new Perl5Pattern();
        perl5Pattern._program = this.__program;
        perl5Pattern._expression = new String(array);
        this.__input._setOffset(0);
        this.__numParentheses = 1;
        this.__programSize = 0;
        this.__cost = 0;
        this.__emitCode('\0');
        if (this.__parseExpression(false, array2) == -1) {
            throw new MalformedPatternException("Unknown compilation error.");
        }
        final char c = (char)(this.__modifierFlags[0] & '\u0001');
        perl5Pattern._isExpensive = (this.__cost >= 10);
        perl5Pattern._startClassOffset = -1;
        perl5Pattern._anchor = 0;
        perl5Pattern._back = -1;
        perl5Pattern._options = options;
        perl5Pattern._startString = null;
        perl5Pattern._mustString = null;
        String string = null;
        String s = null;
        final int n = 1;
        if (this.__program[OpCode._getNext(this.__program, n)] == '\0') {
            int n3;
            int n2 = n3 = OpCode._getNextOperator(n);
            for (char c2 = this.__program[n3]; (c2 == '\u001b' && (b = true)) || (c2 == '\f' && this.__program[OpCode._getNext(this.__program, n3)] != '\f') || c2 == '\u0011' || c2 == '\u001d' || (OpCode._opType[c2] == '\n' && OpCode._getArg1(this.__program, n3) > '\0'); n3 = OpCode._getNextOperator(n3), c2 = this.__program[n3]) {
                if (c2 == '\u0011') {
                    b2 = true;
                }
                else {
                    n3 += OpCode._operandLength[c2];
                }
            }
            int i = 1;
            while (i != 0) {
                i = 0;
                final char c3 = this.__program[n3];
                if (c3 == '\u000e') {
                    s = new String(this.__program, OpCode._getOperand(n3 + 1), this.__program[OpCode._getOperand(n3)]);
                }
                else if (OpCode._isInArray(c3, OpCode._opLengthOne, 2)) {
                    perl5Pattern._startClassOffset = n3;
                }
                else if (c3 == '\u0014' || c3 == '\u0015') {
                    perl5Pattern._startClassOffset = n3;
                }
                else if (OpCode._opType[c3] == '\u0001') {
                    if (c3 == '\u0001') {
                        perl5Pattern._anchor = 1;
                    }
                    else if (c3 == '\u0002') {
                        perl5Pattern._anchor = 2;
                    }
                    else {
                        perl5Pattern._anchor = 3;
                    }
                    n3 = OpCode._getNextOperator(n3);
                    i = 1;
                }
                else {
                    if (c3 != '\u0010' || OpCode._opType[this.__program[OpCode._getNextOperator(n3)]] != '\u0007' || (perl5Pattern._anchor & 0x3) == 0x0) {
                        continue;
                    }
                    perl5Pattern._anchor = 11;
                    n3 = OpCode._getNextOperator(n3);
                    i = 1;
                }
            }
            if (b2 && (!b || !this.__sawBackreference)) {
                final Perl5Pattern perl5Pattern2 = perl5Pattern;
                perl5Pattern2._anchor |= 0x4;
            }
            StringBuffer sb = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            int n4 = 0;
            minLength = '\0';
            int n5 = 0;
            char c4 = '\0';
            int back = 0;
            char c5;
            while (n2 > 0 && (c5 = this.__program[n2]) != '\0') {
                if (c5 == '\f') {
                    if (this.__program[OpCode._getNext(this.__program, n2)] == '\f') {
                        n5 = -30000;
                        while (this.__program[n2] == '\f') {
                            n2 = OpCode._getNext(this.__program, n2);
                        }
                    }
                    else {
                        n2 = OpCode._getNextOperator(n2);
                    }
                }
                else if (c5 == ' ') {
                    n5 = -30000;
                    n2 = OpCode._getNext(this.__program, n2);
                }
                else {
                    if (c5 == '\u000e') {
                        n3 = n2;
                        int getNext;
                        while (this.__program[getNext = OpCode._getNext(this.__program, n2)] == '\u001c') {
                            n2 = getNext;
                        }
                        minLength += this.__program[OpCode._getOperand(n3)];
                        final char c6 = this.__program[OpCode._getOperand(n3)];
                        if (n5 - c4 == n4) {
                            sb.append(new String(this.__program, OpCode._getOperand(n3) + 1, c6));
                            n4 += c6;
                            n5 += c6;
                            n3 = OpCode._getNext(this.__program, n2);
                        }
                        else if (c6 >= n4 + ((n5 >= '\0') ? 1 : 0)) {
                            n4 = c6;
                            sb = new StringBuffer(new String(this.__program, OpCode._getOperand(n3) + 1, c6));
                            c4 = (char)n5;
                            n5 += n4;
                            n3 = OpCode._getNext(this.__program, n2);
                        }
                        else {
                            n5 += c6;
                        }
                    }
                    else if (OpCode._isInArray(c5, OpCode._opLengthVaries, 0)) {
                        n5 = -30000;
                        n4 = '\0';
                        if (sb.length() > sb2.length()) {
                            sb2 = sb;
                            back = c4;
                        }
                        sb = new StringBuffer();
                        if (c5 == '\u0011' && OpCode._isInArray(this.__program[OpCode._getNextOperator(n2)], OpCode._opLengthOne, 0)) {
                            ++minLength;
                        }
                        else if (OpCode._opType[c5] == '\n' && OpCode._isInArray(this.__program[OpCode._getNextOperator(n2) + 2], OpCode._opLengthOne, 0)) {
                            minLength += OpCode._getArg1(this.__program, n2);
                        }
                    }
                    else if (OpCode._isInArray(c5, OpCode._opLengthOne, 0)) {
                        ++n5;
                        ++minLength;
                        n4 = '\0';
                        if (sb.length() > sb2.length()) {
                            sb2 = sb;
                            back = c4;
                        }
                        sb = new StringBuffer();
                    }
                    n2 = OpCode._getNext(this.__program, n2);
                }
            }
            if (sb.length() + ((OpCode._opType[this.__program[n3]] == '\u0004') ? 1 : 0) > sb2.length()) {
                sb2 = sb;
                back = c4;
            }
            else {
                final StringBuffer sb3 = new StringBuffer();
            }
            if (sb2.length() > 0 && s == null) {
                string = sb2.toString();
                if (back < 0) {
                    back = -1;
                }
                perl5Pattern._back = back;
            }
        }
        perl5Pattern._isCaseInsensitive = ((c & '\u0001') != '\0');
        perl5Pattern._numParentheses = this.__numParentheses - 1;
        perl5Pattern._minLength = minLength;
        if (string != null) {
            perl5Pattern._mustString = string.toCharArray();
            perl5Pattern._mustUtility = 100;
        }
        if (s != null) {
            perl5Pattern._startString = s.toCharArray();
        }
        return perl5Pattern;
    }
    
    public Pattern compile(final char[] array) throws MalformedPatternException {
        return this.compile(array, 0);
    }
    
    public Pattern compile(final String s) throws MalformedPatternException {
        return this.compile(s.toCharArray(), 0);
    }
    
    public Pattern compile(final String s, final int n) throws MalformedPatternException {
        return this.compile(s.toCharArray(), n);
    }
    
    static {
        (__hashPOSIX = new HashMap()).put("alnum", new Character('2'));
        Perl5Compiler.__hashPOSIX.put("word", new Character('\u0012'));
        Perl5Compiler.__hashPOSIX.put("alpha", new Character('&'));
        Perl5Compiler.__hashPOSIX.put("blank", new Character('\''));
        Perl5Compiler.__hashPOSIX.put("cntrl", new Character('('));
        Perl5Compiler.__hashPOSIX.put("digit", new Character('\u0018'));
        Perl5Compiler.__hashPOSIX.put("graph", new Character(')'));
        Perl5Compiler.__hashPOSIX.put("lower", new Character('*'));
        Perl5Compiler.__hashPOSIX.put("print", new Character('+'));
        Perl5Compiler.__hashPOSIX.put("punct", new Character(','));
        Perl5Compiler.__hashPOSIX.put("space", new Character('\u0016'));
        Perl5Compiler.__hashPOSIX.put("upper", new Character('-'));
        Perl5Compiler.__hashPOSIX.put("xdigit", new Character('.'));
        Perl5Compiler.__hashPOSIX.put("ascii", new Character('3'));
    }
}

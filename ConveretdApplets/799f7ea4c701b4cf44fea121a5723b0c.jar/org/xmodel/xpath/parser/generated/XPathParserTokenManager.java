// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.parser.generated;

import java.io.IOException;
import java.io.PrintStream;

public class XPathParserTokenManager implements XPathParserConstants
{
    public PrintStream debugStream;
    static final long[] B;
    static final int[] G;
    public static final String[] jjstrLiteralImages;
    public static final String[] lexStateNames;
    static final long[] L;
    static final long[] I;
    protected SimpleCharStream input_stream;
    private final int[] E;
    private final int[] K;
    protected char curChar;
    int D;
    int C;
    int H;
    int A;
    int F;
    int J;
    
    static {
        B = new long[] { 0L, 0L, -1L, -1L };
        G = new int[] { 3, 4, 6, 7, 15, 16 };
        jjstrLiteralImages = new String[] { "", null, null, null, null, "@", "/", "=", "<", ">", "+", "*", "-", "|", "//", "!=", "<=", ">=", ":=", "::", "or", "in", "if", "for", "let", "and", "div", "mod", "then", "else", "self", "text()", "node()", "child", "return", "nested", "parent", "ancestor", "comment()", "following", "preceding", "attribute", "namespace", "descendant", "nested-or-self", "ancestor-or-self", "following-sibling", "preceding-sibling", "descendant-or-self", null, null, null, ";", "$", ".", "..", "(", ")", ":", ",", "[", "]" };
        lexStateNames = new String[] { "DEFAULT" };
        L = new long[] { 4609434218613702645L };
        I = new long[] { 2L };
    }
    
    private final int B(final int n, final long n2) {
        switch (n) {
            case 0: {
                if ((n2 & 0xC0000000000000L) != 0x0L) {
                    return 12;
                }
                if ((n2 & 0x17EEFFFF00000L) != 0x0L) {
                    this.J = 50;
                    return 39;
                }
                if ((n2 & 0x811000000000L) != 0x0L) {
                    this.J = 50;
                    return 36;
                }
                return -1;
            }
            case 1: {
                if ((n2 & 0x810000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 1;
                    return 35;
                }
                if ((n2 & 0x17EFFFF800000L) != 0x0L) {
                    this.J = 50;
                    this.F = 1;
                    return 39;
                }
                if ((n2 & 0x700000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 2: {
                if ((n2 & 0x1FFFFF0000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 2;
                    return 39;
                }
                if ((n2 & 0xF800000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 3: {
                if ((n2 & 0x1FFFF80000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 3;
                    return 39;
                }
                if ((n2 & 0x70000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 4: {
                if ((n2 & 0x180000000L) != 0x0L) {
                    if (this.F < 3) {
                        this.J = 50;
                        this.F = 3;
                    }
                    return -1;
                }
                if ((n2 & 0x1FFFC00000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 4;
                    return 39;
                }
                if ((n2 & 0x200000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 5: {
                if ((n2 & 0x1EFE000000000L) != 0x0L) {
                    if (this.F != 5) {
                        this.J = 50;
                        this.F = 5;
                    }
                    return 39;
                }
                if ((n2 & 0x180000000L) != 0x0L) {
                    if (this.F < 3) {
                        this.J = 50;
                        this.F = 3;
                    }
                    return -1;
                }
                if ((n2 & 0x101C00000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 6: {
                if ((n2 & 0x1FFE000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 6;
                    return 39;
                }
                return -1;
            }
            case 7: {
                if ((n2 & 0x4000000000L) != 0x0L) {
                    if (this.F < 6) {
                        this.J = 50;
                        this.F = 6;
                    }
                    return -1;
                }
                if ((n2 & 0x1DF8000000000L) != 0x0L) {
                    if (this.F != 7) {
                        this.J = 50;
                        this.F = 7;
                    }
                    return 39;
                }
                if ((n2 & 0x202000000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 8: {
                if ((n2 & 0x4000000000L) != 0x0L) {
                    if (this.F < 6) {
                        this.J = 50;
                        this.F = 6;
                    }
                    return -1;
                }
                if ((n2 & 0xC78000000000L) != 0x0L) {
                    return 39;
                }
                if ((n2 & 0x1380000000000L) != 0x0L) {
                    if (this.F != 8) {
                        this.J = 50;
                        this.F = 8;
                    }
                    return 39;
                }
                return -1;
            }
            case 9: {
                if ((n2 & 0xF00000000000L) != 0x0L) {
                    if (this.F != 9) {
                        this.J = 50;
                        this.F = 9;
                    }
                    return 39;
                }
                if ((n2 & 0x1080000000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 10: {
                if ((n2 & 0x1F00000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 10;
                    return 39;
                }
                return -1;
            }
            case 11: {
                if ((n2 & 0x1F00000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 11;
                    return 39;
                }
                return -1;
            }
            case 12: {
                if ((n2 & 0x1F00000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 12;
                    return 39;
                }
                return -1;
            }
            case 13: {
                if ((n2 & 0x1E00000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 13;
                    return 39;
                }
                if ((n2 & 0x100000000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 14: {
                if ((n2 & 0x1E00000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 14;
                    return 39;
                }
                return -1;
            }
            case 15: {
                if ((n2 & 0x1C00000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 15;
                    return 39;
                }
                if ((n2 & 0x200000000000L) != 0x0L) {
                    return 39;
                }
                return -1;
            }
            case 16: {
                if ((n2 & 0xC00000000000L) != 0x0L) {
                    return 39;
                }
                if ((n2 & 0x1000000000000L) != 0x0L) {
                    this.J = 50;
                    this.F = 16;
                    return 39;
                }
                return -1;
            }
            default: {
                return -1;
            }
        }
    }
    
    private final int A(final int n, final long n2) {
        return this.B(this.B(n, n2), n + 1);
    }
    
    private int D(final int f, final int j) {
        this.J = j;
        return (this.F = f) + 1;
    }
    
    private int A() {
        switch (this.curChar) {
            case '!': {
                return this.A(32768L);
            }
            case '$': {
                return this.D(0, 53);
            }
            case '(': {
                return this.D(0, 56);
            }
            case ')': {
                return this.D(0, 57);
            }
            case '*': {
                return this.D(0, 11);
            }
            case '+': {
                return this.D(0, 10);
            }
            case ',': {
                return this.D(0, 59);
            }
            case '-': {
                return this.D(0, 12);
            }
            case '.': {
                this.J = 54;
                return this.A(36028797018963968L);
            }
            case '/': {
                this.J = 6;
                return this.A(16384L);
            }
            case ':': {
                this.J = 58;
                return this.A(786432L);
            }
            case ';': {
                return this.D(0, 52);
            }
            case '<': {
                this.J = 8;
                return this.A(65536L);
            }
            case '=': {
                return this.D(0, 7);
            }
            case '>': {
                this.J = 9;
                return this.A(131072L);
            }
            case '@': {
                return this.D(0, 5);
            }
            case '[': {
                return this.D(0, 60);
            }
            case ']': {
                return this.D(0, 61);
            }
            case 'a': {
                return this.A(37520867852288L);
            }
            case 'c': {
                return this.A(283467841536L);
            }
            case 'd': {
                return this.A(290271136841728L);
            }
            case 'e': {
                return this.A(536870912L);
            }
            case 'f': {
                return this.A(70918508380160L);
            }
            case 'i': {
                return this.A(6291456L);
            }
            case 'l': {
                return this.A(16777216L);
            }
            case 'm': {
                return this.A(134217728L);
            }
            case 'n': {
                return this.A(22028887261184L);
            }
            case 'o': {
                return this.A(1048576L);
            }
            case 'p': {
                return this.A(141905719459840L);
            }
            case 'r': {
                return this.A(17179869184L);
            }
            case 's': {
                return this.A(1073741824L);
            }
            case 't': {
                return this.A(2415919104L);
            }
            case '|': {
                return this.D(0, 13);
            }
            default: {
                return this.B(0, 0);
            }
        }
    }
    
    private int A(final long n) {
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(0, n);
            return 1;
        }
        switch (this.curChar) {
            case '.': {
                if ((n & 0x80000000000000L) != 0x0L) {
                    return this.D(1, 55);
                }
                break;
            }
            case '/': {
                if ((n & 0x4000L) != 0x0L) {
                    return this.D(1, 14);
                }
                break;
            }
            case ':': {
                if ((n & 0x80000L) != 0x0L) {
                    return this.D(1, 19);
                }
                break;
            }
            case '=': {
                if ((n & 0x8000L) != 0x0L) {
                    return this.D(1, 15);
                }
                if ((n & 0x10000L) != 0x0L) {
                    return this.D(1, 16);
                }
                if ((n & 0x20000L) != 0x0L) {
                    return this.D(1, 17);
                }
                if ((n & 0x40000L) != 0x0L) {
                    return this.D(1, 18);
                }
                break;
            }
            case 'a': {
                return this.L(n, 4466765987840L);
            }
            case 'e': {
                return this.L(n, 307918033387520L);
            }
            case 'f': {
                if ((n & 0x400000L) != 0x0L) {
                    return this.A(1, 22, 39);
                }
                break;
            }
            case 'h': {
                return this.L(n, 8858370048L);
            }
            case 'i': {
                return this.L(n, 67108864L);
            }
            case 'l': {
                return this.L(n, 536870912L);
            }
            case 'n': {
                if ((n & 0x200000L) != 0x0L) {
                    return this.A(1, 21, 39);
                }
                return this.L(n, 35321844596736L);
            }
            case 'o': {
                return this.L(n, 71197815472128L);
            }
            case 'r': {
                if ((n & 0x100000L) != 0x0L) {
                    return this.A(1, 20, 39);
                }
                return this.L(n, 141836999983104L);
            }
            case 't': {
                return this.L(n, 2199023255552L);
            }
        }
        return this.A(0, n);
    }
    
    private int L(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(0, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(1, n2);
            return 2;
        }
        switch (this.curChar) {
            case 'c': {
                return this.C(n2, 35321811042304L);
            }
            case 'd': {
                if ((n2 & 0x2000000L) != 0x0L) {
                    return this.A(2, 25, 39);
                }
                if ((n2 & 0x8000000L) != 0x0L) {
                    return this.A(2, 27, 39);
                }
                return this.C(n2, 4294967296L);
            }
            case 'e': {
                return this.C(n2, 141837268418560L);
            }
            case 'i': {
                return this.C(n2, 8589934592L);
            }
            case 'l': {
                return this.C(n2, 70919573733376L);
            }
            case 'm': {
                return this.C(n2, 4672924418048L);
            }
            case 'r': {
                if ((n2 & 0x800000L) != 0x0L) {
                    return this.A(2, 23, 39);
                }
                return this.C(n2, 68719476736L);
            }
            case 's': {
                return this.C(n2, 307898152386560L);
            }
            case 't': {
                if ((n2 & 0x1000000L) != 0x0L) {
                    return this.A(2, 24, 39);
                }
                return this.C(n2, 2216203124736L);
            }
            case 'v': {
                if ((n2 & 0x4000000L) != 0x0L) {
                    return this.A(2, 26, 39);
                }
                break;
            }
            case 'x': {
                return this.C(n2, 2147483648L);
            }
        }
        return this.A(1, n2);
    }
    
    private int C(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(1, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(2, n2);
            return 3;
        }
        switch (this.curChar) {
            case 'c': {
                return this.J(n2, 432108069715968L);
            }
            case 'e': {
                if ((n2 & 0x20000000L) != 0x0L) {
                    return this.A(3, 29, 39);
                }
                return this.J(n2, 39792871997440L);
            }
            case 'f': {
                if ((n2 & 0x40000000L) != 0x0L) {
                    return this.A(3, 30, 39);
                }
                break;
            }
            case 'l': {
                return this.J(n2, 70927089926144L);
            }
            case 'm': {
                return this.J(n2, 274877906944L);
            }
            case 'n': {
                if ((n2 & 0x10000000L) != 0x0L) {
                    return this.A(3, 28, 39);
                }
                break;
            }
            case 'r': {
                return this.J(n2, 2199023255552L);
            }
            case 't': {
                return this.J(n2, 17628693266432L);
            }
            case 'u': {
                return this.J(n2, 17179869184L);
            }
        }
        return this.A(2, n2);
    }
    
    private int J(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(2, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(3, n2);
            return 4;
        }
        switch (this.curChar) {
            case '(': {
                return this.A(n2, 6442450944L);
            }
            case 'd': {
                if ((n2 & 0x200000000L) != 0x0L) {
                    return this.A(4, 33, 39);
                }
                break;
            }
            case 'e': {
                return this.A(n2, 450009493405696L);
            }
            case 'i': {
                return this.A(n2, 2199023255552L);
            }
            case 'n': {
                return this.A(n2, 68719476736L);
            }
            case 'o': {
                return this.A(n2, 70918499991552L);
            }
            case 'r': {
                return this.A(n2, 17179869184L);
            }
            case 's': {
                return this.A(n2, 39719857553408L);
            }
        }
        return this.A(3, n2);
    }
    
    private int A(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(3, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(4, n2);
            return 5;
        }
        switch (this.curChar) {
            case ')': {
                if ((n2 & 0x80000000L) != 0x0L) {
                    return this.D(5, 31);
                }
                if ((n2 & 0x100000000L) != 0x0L) {
                    return this.D(5, 32);
                }
                break;
            }
            case 'b': {
                return this.H(n2, 2199023255552L);
            }
            case 'd': {
                if ((n2 & 0x800000000L) != 0x0L) {
                    this.J = 35;
                    this.F = 5;
                }
                return this.H(n2, 159429186027520L);
            }
            case 'n': {
                if ((n2 & 0x400000000L) != 0x0L) {
                    return this.A(5, 34, 39);
                }
                return this.H(n2, 290545947639808L);
            }
            case 'p': {
                return this.H(n2, 4398046511104L);
            }
            case 't': {
                if ((n2 & 0x1000000000L) != 0x0L) {
                    return this.A(5, 36, 39);
                }
                return this.H(n2, 35321811042304L);
            }
            case 'w': {
                return this.H(n2, 70918499991552L);
            }
        }
        return this.A(4, n2);
    }
    
    private int H(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(4, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(5, n2);
            return 6;
        }
        switch (this.curChar) {
            case '-': {
                return this.K(n2, 17592186044416L);
            }
            case 'a': {
                return this.K(n2, 4398046511104L);
            }
            case 'd': {
                return this.K(n2, 290271069732864L);
            }
            case 'i': {
                return this.K(n2, 212755499974656L);
            }
            case 'o': {
                return this.K(n2, 35321811042304L);
            }
            case 't': {
                return this.K(n2, 274877906944L);
            }
            case 'u': {
                return this.K(n2, 2199023255552L);
            }
            default: {
                return this.A(5, n2);
            }
        }
    }
    
    private int K(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(5, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(6, n2);
            return 7;
        }
        switch (this.curChar) {
            case '(': {
                return this.B(n2, 274877906944L);
            }
            case 'a': {
                return this.B(n2, 290271069732864L);
            }
            case 'c': {
                return this.B(n2, 4398046511104L);
            }
            case 'n': {
                return this.B(n2, 212755499974656L);
            }
            case 'o': {
                return this.B(n2, 17592186044416L);
            }
            case 'r': {
                if ((n2 & 0x2000000000L) != 0x0L) {
                    this.J = 37;
                    this.F = 7;
                }
                return this.B(n2, 35184372088832L);
            }
            case 't': {
                return this.B(n2, 2199023255552L);
            }
            default: {
                return this.A(6, n2);
            }
        }
    }
    
    private int B(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(6, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(7, n2);
            return 8;
        }
        switch (this.curChar) {
            case ')': {
                if ((n2 & 0x4000000000L) != 0x0L) {
                    return this.D(8, 38);
                }
                break;
            }
            case '-': {
                return this.I(n2, 35184372088832L);
            }
            case 'e': {
                if ((n2 & 0x20000000000L) != 0x0L) {
                    return this.A(8, 41, 39);
                }
                if ((n2 & 0x40000000000L) != 0x0L) {
                    return this.A(8, 42, 39);
                }
                break;
            }
            case 'g': {
                if ((n2 & 0x8000000000L) != 0x0L) {
                    this.J = 39;
                    this.F = 8;
                }
                else if ((n2 & 0x10000000000L) != 0x0L) {
                    this.J = 40;
                    this.F = 8;
                }
                return this.I(n2, 211106232532992L);
            }
            case 'n': {
                return this.I(n2, 290271069732864L);
            }
            case 'r': {
                return this.I(n2, 17592186044416L);
            }
        }
        return this.A(7, n2);
    }
    
    private int I(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(7, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(8, n2);
            return 9;
        }
        switch (this.curChar) {
            case '-': {
                return this.P(n2, 228698418577408L);
            }
            case 'o': {
                return this.P(n2, 35184372088832L);
            }
            case 't': {
                if ((n2 & 0x80000000000L) != 0x0L) {
                    this.J = 43;
                    this.F = 9;
                }
                return this.P(n2, 281474976710656L);
            }
            default: {
                return this.A(8, n2);
            }
        }
    }
    
    private int P(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(8, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(9, n2);
            return 10;
        }
        switch (this.curChar) {
            case '-': {
                return this.G(n2, 281474976710656L);
            }
            case 'r': {
                return this.G(n2, 35184372088832L);
            }
            case 's': {
                return this.G(n2, 228698418577408L);
            }
            default: {
                return this.A(9, n2);
            }
        }
    }
    
    private int G(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(9, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(10, n2);
            return 11;
        }
        switch (this.curChar) {
            case '-': {
                return this.O(n2, 35184372088832L);
            }
            case 'e': {
                return this.O(n2, 17592186044416L);
            }
            case 'i': {
                return this.O(n2, 211106232532992L);
            }
            case 'o': {
                return this.O(n2, 281474976710656L);
            }
            default: {
                return this.A(10, n2);
            }
        }
    }
    
    private int O(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(10, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(11, n2);
            return 12;
        }
        switch (this.curChar) {
            case 'b': {
                return this.E(n2, 211106232532992L);
            }
            case 'l': {
                return this.E(n2, 17592186044416L);
            }
            case 'r': {
                return this.E(n2, 281474976710656L);
            }
            case 's': {
                return this.E(n2, 35184372088832L);
            }
            default: {
                return this.A(11, n2);
            }
        }
    }
    
    private int E(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(11, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(12, n2);
            return 13;
        }
        switch (this.curChar) {
            case '-': {
                return this.M(n2, 281474976710656L);
            }
            case 'e': {
                return this.M(n2, 35184372088832L);
            }
            case 'f': {
                if ((n2 & 0x100000000000L) != 0x0L) {
                    return this.A(13, 44, 39);
                }
                break;
            }
            case 'l': {
                return this.M(n2, 211106232532992L);
            }
        }
        return this.A(12, n2);
    }
    
    private int M(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(12, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(13, n2);
            return 14;
        }
        switch (this.curChar) {
            case 'i': {
                return this.D(n2, 211106232532992L);
            }
            case 'l': {
                return this.D(n2, 35184372088832L);
            }
            case 's': {
                return this.D(n2, 281474976710656L);
            }
            default: {
                return this.A(13, n2);
            }
        }
    }
    
    private int D(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(13, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(14, n2);
            return 15;
        }
        switch (this.curChar) {
            case 'e': {
                return this.F(n2, 281474976710656L);
            }
            case 'f': {
                if ((n2 & 0x200000000000L) != 0x0L) {
                    return this.A(15, 45, 39);
                }
                break;
            }
            case 'n': {
                return this.F(n2, 211106232532992L);
            }
        }
        return this.A(14, n2);
    }
    
    private int F(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(14, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(15, n2);
            return 16;
        }
        switch (this.curChar) {
            case 'g': {
                if ((n2 & 0x400000000000L) != 0x0L) {
                    return this.A(16, 46, 39);
                }
                if ((n2 & 0x800000000000L) != 0x0L) {
                    return this.A(16, 47, 39);
                }
                break;
            }
            case 'l': {
                return this.N(n2, 281474976710656L);
            }
        }
        return this.A(15, n2);
    }
    
    private int N(final long n, long n2) {
        if ((n2 &= n) == 0x0L) {
            return this.A(15, n);
        }
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            this.B(16, n2);
            return 17;
        }
        switch (this.curChar) {
            case 'f': {
                if ((n2 & 0x1000000000000L) != 0x0L) {
                    return this.A(17, 48, 39);
                }
                break;
            }
        }
        return this.A(16, n2);
    }
    
    private int A(final int f, final int j, final int n) {
        this.J = j;
        this.F = f;
        try {
            this.curChar = this.input_stream.readChar();
        }
        catch (IOException ex) {
            return f + 1;
        }
        return this.B(n, f + 1);
    }
    
    private int B(final int n, int f) {
        int n2 = 0;
        this.H = 40;
        int i = 1;
        this.K[0] = n;
        int j = Integer.MAX_VALUE;
        while (true) {
            if (++this.A == Integer.MAX_VALUE) {
                this.B();
            }
            if (this.curChar < '@') {
                final long n3 = 1L << this.curChar;
                do {
                    switch (this.K[--i]) {
                        case 35:
                        case 39: {
                            if ((0x3FF600000000000L & n3) == 0x0L) {
                                continue;
                            }
                            if (j > 50) {
                                j = 50;
                            }
                            this.A(39);
                            continue;
                        }
                        case 36: {
                            if ((0x3FF600000000000L & n3) == 0x0L) {
                                continue;
                            }
                            if (j > 50) {
                                j = 50;
                            }
                            this.A(39);
                            continue;
                        }
                        case 0: {
                            if ((0x3FF000000000000L & n3) != 0x0L) {
                                if (j > 4) {
                                    j = 4;
                                }
                                this.C(8, 9);
                                continue;
                            }
                            if ((0x2600L & n3) != 0x0L) {
                                if (j > 1) {
                                    j = 1;
                                }
                                this.A(1);
                                continue;
                            }
                            if (this.curChar == '.') {
                                this.A(12);
                                continue;
                            }
                            if (this.curChar == '\'') {
                                this.C(6, 7);
                                continue;
                            }
                            if (this.curChar == '\"') {
                                this.C(3, 4);
                                continue;
                            }
                            if (this.curChar == ' ' && j > 1) {
                                j = 1;
                                continue;
                            }
                            continue;
                        }
                        case 1: {
                            if ((0x2600L & n3) == 0x0L) {
                                continue;
                            }
                            if (j > 1) {
                                j = 1;
                            }
                            this.A(1);
                            continue;
                        }
                        case 2: {
                            if (this.curChar == '\"') {
                                this.C(3, 4);
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            if ((0xFFFFFFFBFFFFFFFFL & n3) != 0x0L) {
                                this.C(3, 4);
                                continue;
                            }
                            continue;
                        }
                        case 4: {
                            if (this.curChar == '\"' && j > 2) {
                                j = 2;
                                continue;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.curChar == '\'') {
                                this.C(6, 7);
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if ((0xFFFFFF7FFFFFFFFFL & n3) != 0x0L) {
                                this.C(6, 7);
                                continue;
                            }
                            continue;
                        }
                        case 7: {
                            if (this.curChar == '\'' && j > 2) {
                                j = 2;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            if ((0x3FF000000000000L & n3) == 0x0L) {
                                continue;
                            }
                            if (j > 4) {
                                j = 4;
                            }
                            this.C(8, 9);
                            continue;
                        }
                        case 9: {
                            if (this.curChar != '.') {
                                continue;
                            }
                            if (j > 4) {
                                j = 4;
                            }
                            this.A(10);
                            continue;
                        }
                        case 10: {
                            if ((0x3FF000000000000L & n3) == 0x0L) {
                                continue;
                            }
                            if (j > 4) {
                                j = 4;
                            }
                            this.A(10);
                            continue;
                        }
                        case 11: {
                            if (this.curChar == '.') {
                                this.A(12);
                                continue;
                            }
                            continue;
                        }
                        case 12: {
                            if ((0x3FF000000000000L & n3) == 0x0L) {
                                continue;
                            }
                            if (j > 4) {
                                j = 4;
                            }
                            this.A(12);
                            continue;
                        }
                        case 14: {
                            if (this.curChar == '(') {
                                this.C(15, 16);
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if ((0xFFFFFDFFFFFFFFFFL & n3) != 0x0L) {
                                this.C(15, 16);
                                continue;
                            }
                            continue;
                        }
                        case 16: {
                            if (this.curChar == ')' && j > 49) {
                                j = 49;
                                continue;
                            }
                            continue;
                        }
                        case 27: {
                            if (this.curChar == '-') {
                                this.K[this.H++] = 26;
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != n2);
            }
            else if (this.curChar < '\u0080') {
                final long n4 = 1L << (this.curChar & '?');
                do {
                    switch (this.K[--i]) {
                        case 35: {
                            if ((0x7FFFFFE87FFFFFEL & n4) != 0x0L) {
                                if (j > 50) {
                                    j = 50;
                                }
                                this.A(39);
                            }
                            if (this.curChar == 'o') {
                                this.K[this.H++] = 34;
                                continue;
                            }
                            continue;
                        }
                        case 36: {
                            if ((0x7FFFFFE87FFFFFEL & n4) != 0x0L) {
                                if (j > 50) {
                                    j = 50;
                                }
                                this.A(39);
                            }
                            if (this.curChar == 'r') {
                                this.K[this.H++] = 35;
                                continue;
                            }
                            continue;
                        }
                        case 0: {
                            if ((0x7FFFFFE87FFFFFEL & n4) != 0x0L) {
                                if (j > 50) {
                                    j = 50;
                                }
                                this.A(39);
                            }
                            if (this.curChar == 'p') {
                                this.K[this.H++] = 36;
                                continue;
                            }
                            continue;
                        }
                        case 13: {
                            if (this.curChar == 'n') {
                                this.K[this.H++] = 14;
                                continue;
                            }
                            continue;
                        }
                        case 17: {
                            if (this.curChar == 'o') {
                                this.K[this.H++] = 13;
                                continue;
                            }
                            continue;
                        }
                        case 18: {
                            if (this.curChar == 'i') {
                                this.K[this.H++] = 17;
                                continue;
                            }
                            continue;
                        }
                        case 19: {
                            if (this.curChar == 't') {
                                this.K[this.H++] = 18;
                                continue;
                            }
                            continue;
                        }
                        case 20: {
                            if (this.curChar == 'c') {
                                this.K[this.H++] = 19;
                                continue;
                            }
                            continue;
                        }
                        case 21: {
                            if (this.curChar == 'u') {
                                this.K[this.H++] = 20;
                                continue;
                            }
                            continue;
                        }
                        case 22: {
                            if (this.curChar == 'r') {
                                this.K[this.H++] = 21;
                                continue;
                            }
                            continue;
                        }
                        case 23: {
                            if (this.curChar == 't') {
                                this.K[this.H++] = 22;
                                continue;
                            }
                            continue;
                        }
                        case 24: {
                            if (this.curChar == 's') {
                                this.K[this.H++] = 23;
                                continue;
                            }
                            continue;
                        }
                        case 25: {
                            if (this.curChar == 'n') {
                                this.K[this.H++] = 24;
                                continue;
                            }
                            continue;
                        }
                        case 26: {
                            if (this.curChar == 'i') {
                                this.K[this.H++] = 25;
                                continue;
                            }
                            continue;
                        }
                        case 28: {
                            if (this.curChar == 'g') {
                                this.K[this.H++] = 27;
                                continue;
                            }
                            continue;
                        }
                        case 29: {
                            if (this.curChar == 'n') {
                                this.K[this.H++] = 28;
                                continue;
                            }
                            continue;
                        }
                        case 30: {
                            if (this.curChar == 'i') {
                                this.K[this.H++] = 29;
                                continue;
                            }
                            continue;
                        }
                        case 31: {
                            if (this.curChar == 's') {
                                this.K[this.H++] = 30;
                                continue;
                            }
                            continue;
                        }
                        case 32: {
                            if (this.curChar == 's') {
                                this.K[this.H++] = 31;
                                continue;
                            }
                            continue;
                        }
                        case 33: {
                            if (this.curChar == 'e') {
                                this.K[this.H++] = 32;
                                continue;
                            }
                            continue;
                        }
                        case 34: {
                            if (this.curChar == 'c') {
                                this.K[this.H++] = 33;
                                continue;
                            }
                            continue;
                        }
                        case 37: {
                            if (this.curChar == 'p') {
                                this.K[this.H++] = 36;
                                continue;
                            }
                            continue;
                        }
                        case 38: {
                            if ((0x7FFFFFE87FFFFFEL & n4) == 0x0L) {
                                continue;
                            }
                            if (j > 50) {
                                j = 50;
                            }
                            this.A(39);
                            continue;
                        }
                        case 39: {
                            if ((0x7FFFFFE87FFFFFEL & n4) == 0x0L) {
                                continue;
                            }
                            if (j > 50) {
                                j = 50;
                            }
                            this.A(39);
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 3: {
                            this.A(0, 1);
                            continue;
                        }
                        case 6: {
                            this.A(2, 3);
                            continue;
                        }
                        case 15: {
                            this.A(4, 5);
                            continue;
                        }
                    }
                } while (i != n2);
            }
            else {
                final char c = (char)((this.curChar & '\u00ff') >> 6);
                final long n5 = 1L << (this.curChar & '?');
                do {
                    switch (this.K[--i]) {
                        case 3: {
                            if ((XPathParserTokenManager.B[c] & n5) != 0x0L) {
                                this.A(0, 1);
                                continue;
                            }
                            continue;
                        }
                        case 6: {
                            if ((XPathParserTokenManager.B[c] & n5) != 0x0L) {
                                this.A(2, 3);
                                continue;
                            }
                            continue;
                        }
                        case 15: {
                            if ((XPathParserTokenManager.B[c] & n5) != 0x0L) {
                                this.A(4, 5);
                                continue;
                            }
                            continue;
                        }
                        default: {
                            continue;
                        }
                    }
                } while (i != n2);
            }
            if (j != Integer.MAX_VALUE) {
                this.J = j;
                this.F = f;
                j = Integer.MAX_VALUE;
            }
            ++f;
            final int n6 = i = this.H;
            final int n7 = 40;
            final int h = n2;
            this.H = h;
            if (n6 == (n2 = n7 - h)) {
                break;
            }
            try {
                this.curChar = this.input_stream.readChar();
            }
            catch (IOException ex) {
                return f;
            }
        }
        return f;
    }
    
    public XPathParserTokenManager(final SimpleCharStream input_stream) {
        this.debugStream = System.out;
        this.E = new int[40];
        this.K = new int[80];
        this.D = 0;
        this.C = 0;
        this.input_stream = input_stream;
    }
    
    private void B() {
        this.A = -2147483647;
        int n = 40;
        while (n-- > 0) {
            this.E[n] = Integer.MIN_VALUE;
        }
    }
    
    protected Token jjFillToken() {
        final String s = XPathParserTokenManager.jjstrLiteralImages[this.J];
        final String s2 = (s == null) ? this.input_stream.GetImage() : s;
        final int beginLine = this.input_stream.getBeginLine();
        final int beginColumn = this.input_stream.getBeginColumn();
        final int endLine = this.input_stream.getEndLine();
        final int endColumn = this.input_stream.getEndColumn();
        final Token token = Token.newToken(this.J, s2);
        token.beginLine = beginLine;
        token.endLine = endLine;
        token.beginColumn = beginColumn;
        token.endColumn = endColumn;
        return token;
    }
    
    public Token getNextToken() {
        while (true) {
            try {
                this.curChar = this.input_stream.BeginToken();
            }
            catch (IOException ex) {
                this.J = 0;
                return this.jjFillToken();
            }
            this.J = Integer.MAX_VALUE;
            this.F = 0;
            final int a = this.A();
            if (this.J == Integer.MAX_VALUE) {
                int endLine = this.input_stream.getEndLine();
                int endColumn = this.input_stream.getEndColumn();
                String s = null;
                boolean b = false;
                try {
                    this.input_stream.readChar();
                    this.input_stream.backup(1);
                }
                catch (IOException ex2) {
                    b = true;
                    s = ((a <= 1) ? "" : this.input_stream.GetImage());
                    if (this.curChar == '\n' || this.curChar == '\r') {
                        ++endLine;
                        endColumn = 0;
                    }
                    else {
                        ++endColumn;
                    }
                }
                if (!b) {
                    this.input_stream.backup(1);
                    s = ((a <= 1) ? "" : this.input_stream.GetImage());
                }
                throw new TokenMgrError(b, this.D, endLine, endColumn, s, this.curChar, 0);
            }
            if (this.F + 1 < a) {
                this.input_stream.backup(a - this.F - 1);
            }
            if ((XPathParserTokenManager.L[this.J >> 6] & 1L << (this.J & 0x3F)) != 0x0L) {
                return this.jjFillToken();
            }
        }
    }
    
    private void A(final int n) {
        if (this.E[n] != this.A) {
            this.K[this.H++] = n;
            this.E[n] = this.A;
        }
    }
    
    private void A(int n, final int n2) {
        do {
            this.K[this.H++] = XPathParserTokenManager.G[n];
        } while (n++ != n2);
    }
    
    private void C(final int n, final int n2) {
        this.A(n);
        this.A(n2);
    }
}

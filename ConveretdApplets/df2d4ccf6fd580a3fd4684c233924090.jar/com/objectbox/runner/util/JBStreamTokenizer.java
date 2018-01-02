// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class JBStreamTokenizer
{
    private boolean inQoute;
    private Reader reader;
    private InputStream input;
    private char[] buf;
    private int peekc;
    private boolean pushedBack;
    private boolean forceLower;
    private int LINENO;
    private boolean eolIsSignificantP;
    private boolean slashSlashCommentsP;
    private boolean slashStarCommentsP;
    private byte[] ctype;
    private static final byte CT_WHITESPACE = 1;
    private static final byte CT_DIGIT = 2;
    private static final byte CT_ALPHA = 4;
    private static final byte CT_QUOTE = 8;
    private static final byte CT_COMMENT = 16;
    public int ttype;
    public static final int TT_EOF = -1;
    public static final int TT_EOL = 10;
    public static final int TT_NUMBER = -2;
    public static final int TT_WORD = -3;
    public static final int TT_NOTHING = -4;
    public String sval;
    public double nval;
    
    private JBStreamTokenizer() {
        this.inQoute = false;
        this.reader = null;
        this.input = null;
        this.buf = new char[20];
        this.LINENO = 1;
        this.eolIsSignificantP = false;
        this.slashSlashCommentsP = false;
        this.slashStarCommentsP = false;
        this.ctype = new byte[256];
        this.ttype = -4;
        this.wordChars(97, 122);
        this.wordChars(65, 90);
        this.wordChars(160, 255);
        this.whitespaceChars(0, 32);
        this.commentChar(47);
        this.quoteChar(34);
        this.quoteChar(39);
    }
    
    public JBStreamTokenizer(final InputStream input) {
        this();
        this.input = input;
    }
    
    public JBStreamTokenizer(final Reader reader) {
        this();
        this.reader = reader;
    }
    
    public void commentChar(final int n) {
        if (n >= 0 && n < this.ctype.length) {
            this.ctype[n] = 16;
        }
    }
    
    public void eolIsSignificant(final boolean eolIsSignificantP) {
        this.eolIsSignificantP = eolIsSignificantP;
    }
    
    public int lineno() {
        return this.LINENO;
    }
    
    public void lowerCaseMode(final boolean forceLower) {
        this.forceLower = forceLower;
    }
    
    public int nextToken() throws IOException {
        if (this.pushedBack) {
            this.pushedBack = false;
            return this.ttype;
        }
        final byte[] ctype = this.ctype;
        this.sval = null;
        int ttype;
        if (this.ttype == -4) {
            ttype = this.read();
            if (ttype >= 0) {
                this.ttype = ttype;
            }
        }
        else {
            ttype = this.peekc;
        }
        if (ttype < 0) {
            return this.ttype = -1;
        }
        byte b;
        for (b = (byte)((ttype < 256) ? ctype[ttype] : 4); (b & 0x1) != 0x0; b = (byte)((ttype < 256) ? ctype[ttype] : 4)) {
            if (ttype == 13) {
                ++this.LINENO;
                ttype = this.read();
                if (ttype == 10) {
                    ttype = this.read();
                }
                if (this.eolIsSignificantP) {
                    this.peekc = ttype;
                    return this.ttype = 10;
                }
            }
            else {
                if (ttype == 10) {
                    ++this.LINENO;
                    if (this.eolIsSignificantP) {
                        this.peekc = this.read();
                        return this.ttype = 10;
                    }
                }
                ttype = this.read();
            }
            if (ttype < 0) {
                return this.ttype = -1;
            }
        }
        if ((b & 0x2) != 0x0) {
            boolean b2 = false;
            if (ttype == 45) {
                ttype = this.read();
                if (ttype != 46 && (ttype < 48 || ttype > 57)) {
                    this.peekc = ttype;
                    return this.ttype = 45;
                }
                b2 = true;
            }
            double n = 0.0;
            int i = 0;
            int n2 = 0;
            while (true) {
                if (ttype == 46 && n2 == 0) {
                    n2 = 1;
                }
                else {
                    if (48 > ttype || ttype > 57) {
                        break;
                    }
                    n = n * 10.0 + (ttype - 48);
                    i += n2;
                }
                ttype = this.read();
            }
            this.peekc = ttype;
            if (i != 0) {
                double n3 = 10.0;
                --i;
                while (i > 0) {
                    n3 *= 10.0;
                    --i;
                }
                n /= n3;
            }
            this.nval = (b2 ? (-n) : n);
            return this.ttype = -2;
        }
        if ((b & 0x4) != 0x0) {
            int n4 = 0;
            byte b3;
            do {
                if (n4 >= this.buf.length) {
                    final char[] buf = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, buf, 0, this.buf.length);
                    this.buf = buf;
                }
                this.buf[n4++] = (char)ttype;
                ttype = this.read();
                b3 = (byte)((ttype < 0) ? 1 : ((ttype < 256) ? ctype[ttype] : 4));
                if (ttype == 34) {
                    this.inQoute = !this.inQoute;
                }
            } while ((b3 & 0x6) != 0x0 || (this.inQoute && ttype != -1));
            this.peekc = ttype;
            this.sval = String.copyValueOf(this.buf, 0, n4);
            if (this.forceLower) {
                this.sval = this.sval.toLowerCase();
            }
            return this.ttype = -3;
        }
        if ((b & 0x10) != 0x0) {
            int read;
            while ((read = this.read()) != 10 && read != 13 && read >= 0) {}
            this.peekc = read;
            return this.nextToken();
        }
        if ((b & 0x8) != 0x0) {
            this.ttype = ttype;
            int n5 = 0;
            this.peekc = this.read();
            while (this.peekc >= 0 && this.peekc != this.ttype && this.peekc != 10 && this.peekc != 13) {
                int peekc;
                if (this.peekc == 92) {
                    final int read2;
                    peekc = (read2 = this.read());
                    if (peekc >= 48 && peekc <= 55) {
                        peekc -= 48;
                        final int read3 = this.read();
                        if (48 <= read3 && read3 <= 55) {
                            peekc = (peekc << 3) + (read3 - 48);
                            final int read4 = this.read();
                            if (48 <= read4 && read4 <= 55 && read2 <= 51) {
                                peekc = (peekc << 3) + (read4 - 48);
                                this.peekc = this.read();
                            }
                            else {
                                this.peekc = read4;
                            }
                        }
                        else {
                            this.peekc = read3;
                        }
                    }
                    else {
                        switch (peekc) {
                            case 97: {
                                peekc = 7;
                                break;
                            }
                            case 98: {
                                peekc = 8;
                                break;
                            }
                            case 102: {
                                peekc = 12;
                                break;
                            }
                            case 110: {
                                peekc = 10;
                                break;
                            }
                            case 114: {
                                peekc = 13;
                                break;
                            }
                            case 116: {
                                peekc = 9;
                                break;
                            }
                            case 118: {
                                peekc = 11;
                                break;
                            }
                        }
                        this.peekc = this.read();
                    }
                }
                else {
                    peekc = this.peekc;
                    this.peekc = this.read();
                }
                if (n5 >= this.buf.length) {
                    final char[] buf2 = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, buf2, 0, this.buf.length);
                    this.buf = buf2;
                }
                this.buf[n5++] = (char)peekc;
            }
            if (this.peekc == this.ttype) {
                this.peekc = this.read();
            }
            this.sval = String.copyValueOf(this.buf, 0, n5);
            return this.ttype;
        }
        if (ttype != 47 || (!this.slashSlashCommentsP && !this.slashStarCommentsP)) {
            this.peekc = this.read();
            return this.ttype = ttype;
        }
        final int read5 = this.read();
        if (read5 == 42 && this.slashStarCommentsP) {
            int n7;
            for (int n6 = 0; (n7 = this.read()) != 47 || n6 != 42; n6 = n7) {
                if (n7 == 13) {
                    ++this.LINENO;
                    n7 = this.read();
                    if (n7 == 10) {
                        n7 = this.read();
                    }
                }
                else if (n7 == 10) {
                    ++this.LINENO;
                    n7 = this.read();
                }
                if (n7 < 0) {
                    return this.ttype = -1;
                }
            }
            this.peekc = this.read();
            return this.nextToken();
        }
        if (read5 == 47 && this.slashSlashCommentsP) {
            int read6;
            while ((read6 = this.read()) != 10 && read6 != 13 && read6 >= 0) {}
            this.peekc = read6;
            return this.nextToken();
        }
        this.peekc = read5;
        return this.ttype = 47;
    }
    
    public void ordinaryChar(final int n) {
        if (n >= 0 && n < this.ctype.length) {
            this.ctype[n] = 0;
        }
    }
    
    public void ordinaryChars(int i, int n) {
        if (i < 0) {
            i = 0;
        }
        if (n >= this.ctype.length) {
            n = this.ctype.length - 1;
        }
        while (i <= n) {
            this.ctype[i++] = 0;
        }
    }
    
    public void parseNumbers() {
        for (int i = 48; i <= 57; ++i) {
            final byte[] ctype = this.ctype;
            final int n = i;
            ctype[n] |= 0x2;
        }
        final byte[] ctype2 = this.ctype;
        final int n2 = 46;
        ctype2[n2] |= 0x2;
        final byte[] ctype3 = this.ctype;
        final int n3 = 45;
        ctype3[n3] |= 0x2;
    }
    
    public void pushBack() {
        if (this.ttype != -4) {
            this.pushedBack = true;
        }
    }
    
    public void quoteChar(final int n) {
        if (n >= 0 && n < this.ctype.length) {
            this.ctype[n] = 8;
        }
    }
    
    private int read() throws IOException {
        if (this.reader != null) {
            return this.reader.read();
        }
        if (this.input != null) {
            return this.input.read();
        }
        throw new IllegalStateException();
    }
    
    public void resetSyntax() {
        int length = this.ctype.length;
        while (--length >= 0) {
            this.ctype[length] = 0;
        }
    }
    
    public void slashSlashComments(final boolean slashSlashCommentsP) {
        this.slashSlashCommentsP = slashSlashCommentsP;
    }
    
    public void slashStarComments(final boolean slashStarCommentsP) {
        this.slashStarCommentsP = slashStarCommentsP;
    }
    
    public String toString() {
        String s = null;
        switch (this.ttype) {
            case -1: {
                s = "EOF";
                break;
            }
            case 10: {
                s = "EOL";
                break;
            }
            case -3: {
                s = this.sval;
                break;
            }
            case -2: {
                s = "n=" + this.nval;
                break;
            }
            case -4: {
                s = "NOTHING";
                break;
            }
            default: {
                final char[] array = new char[3];
                array[0] = (array[2] = '\'');
                array[1] = (char)this.ttype;
                s = new String(array);
                break;
            }
        }
        return "Token[" + s + "], line " + this.LINENO;
    }
    
    public void whitespaceChars(int i, int n) {
        if (i < 0) {
            i = 0;
        }
        if (n >= this.ctype.length) {
            n = this.ctype.length - 1;
        }
        while (i <= n) {
            this.ctype[i++] = 1;
        }
    }
    
    public void wordChars(int i, int n) {
        if (i < 0) {
            i = 0;
        }
        if (n >= this.ctype.length) {
            n = this.ctype.length - 1;
        }
        while (i <= n) {
            final byte[] ctype = this.ctype;
            final int n2 = i++;
            ctype[n2] |= 0x4;
        }
    }
}

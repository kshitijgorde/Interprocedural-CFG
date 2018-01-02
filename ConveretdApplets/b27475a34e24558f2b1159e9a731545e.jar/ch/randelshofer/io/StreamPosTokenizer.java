// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.io;

import java.io.IOException;
import java.util.Vector;
import java.io.Reader;

public class StreamPosTokenizer
{
    private Reader reader;
    private int readpos;
    private int startpos;
    private int endpos;
    private Vector unread;
    private char[] buf;
    private int peekc;
    private static final int NEED_CHAR = Integer.MAX_VALUE;
    private static final int SKIP_LF = 2147483646;
    private boolean pushedBack;
    private boolean forceLower;
    private int LINENO;
    private boolean eolIsSignificantP;
    private boolean slashSlashCommentsP;
    private boolean slashStarCommentsP;
    private char[] slashSlash;
    private char[] slashStar;
    private char[] starSlash;
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
    private static final int TT_NOTHING = -4;
    public String sval;
    public double nval;
    
    private StreamPosTokenizer() {
        this.reader = null;
        this.readpos = 0;
        this.startpos = -1;
        this.endpos = -1;
        this.unread = new Vector();
        this.buf = new char[20];
        this.peekc = Integer.MAX_VALUE;
        this.LINENO = 1;
        this.eolIsSignificantP = false;
        this.slashSlashCommentsP = false;
        this.slashStarCommentsP = false;
        this.slashSlash = new char[] { '/', '/' };
        this.slashStar = new char[] { '/', '*' };
        this.starSlash = new char[] { '*', '/' };
        this.ctype = new byte[256];
        this.ttype = -4;
        this.wordChars(97, 122);
        this.wordChars(65, 90);
        this.wordChars(160, 255);
        this.whitespaceChars(0, 32);
        this.commentChar(47);
        this.quoteChar(34);
        this.quoteChar(39);
        this.parseNumbers();
    }
    
    public StreamPosTokenizer(final Reader reader) {
        this();
        if (reader == null) {
            throw new NullPointerException();
        }
        this.reader = reader;
    }
    
    public void resetSyntax() {
        int length = this.ctype.length;
        while (--length >= 0) {
            this.ctype[length] = 0;
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
    
    public void ordinaryChar(final int n) {
        if (n >= 0 && n < this.ctype.length) {
            this.ctype[n] = 0;
        }
    }
    
    public void commentChar(final int n) {
        if (n >= 0 && n < this.ctype.length) {
            this.ctype[n] = 16;
        }
    }
    
    public void quoteChar(final int n) {
        if (n >= 0 && n < this.ctype.length) {
            this.ctype[n] = 8;
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
    
    public void eolIsSignificant(final boolean eolIsSignificantP) {
        this.eolIsSignificantP = eolIsSignificantP;
    }
    
    public void slashStarComments(final boolean slashStarCommentsP) {
        this.slashStarCommentsP = slashStarCommentsP;
    }
    
    public void slashSlashComments(final boolean slashSlashCommentsP) {
        this.slashSlashCommentsP = slashSlashCommentsP;
    }
    
    public void lowerCaseMode(final boolean forceLower) {
        this.forceLower = forceLower;
    }
    
    private int read() throws IOException {
        int n;
        if (this.unread.size() > 0) {
            n = this.unread.lastElement();
            this.unread.removeElementAt(this.unread.size() - 1);
        }
        else {
            n = this.reader.read();
        }
        if (n != -1) {
            ++this.readpos;
        }
        return n;
    }
    
    private void unread(final int n) {
        this.unread.addElement(new Integer(n));
        --this.readpos;
    }
    
    public int nextToken() throws IOException {
        if (this.pushedBack) {
            this.pushedBack = false;
            return this.ttype;
        }
        final byte[] ctype = this.ctype;
        this.sval = null;
        int n = this.peekc;
        if (n < 0) {
            n = Integer.MAX_VALUE;
        }
        if (n == 2147483646) {
            n = this.read();
            if (n < 0) {
                final int n2 = this.readpos - 1;
                this.endpos = n2;
                this.startpos = n2;
                return this.ttype = -1;
            }
            if (n == 10) {
                n = Integer.MAX_VALUE;
            }
        }
        if (n == Integer.MAX_VALUE) {
            n = this.read();
            if (n < 0) {
                final int n3 = this.readpos - 1;
                this.endpos = n3;
                this.startpos = n3;
                return this.ttype = -1;
            }
        }
        this.ttype = n;
        this.peekc = Integer.MAX_VALUE;
        byte b;
        for (b = (byte)((n < '\u0100') ? ctype[n] : 4); (b & 0x1) != 0x0; b = (byte)((n < '\u0100') ? ctype[n] : 4)) {
            if (n == 13) {
                ++this.LINENO;
                if (this.eolIsSignificantP) {
                    this.peekc = 2147483646;
                    final int n4 = this.readpos - 1;
                    this.endpos = n4;
                    this.startpos = n4;
                    return this.ttype = 10;
                }
                n = this.read();
                if (n == 10) {
                    n = this.read();
                }
            }
            else {
                if (n == 10) {
                    ++this.LINENO;
                    if (this.eolIsSignificantP) {
                        final int n5 = this.readpos - 1;
                        this.endpos = n5;
                        this.startpos = n5;
                        return this.ttype = 10;
                    }
                }
                n = this.read();
            }
            if (n < 0) {
                final int readpos = this.readpos;
                this.endpos = readpos;
                this.startpos = readpos;
                return this.ttype = -1;
            }
        }
        this.startpos = this.readpos - 1;
        Label_0584: {
            if ((b & 0x2) != 0x0) {
                int n6 = 0;
                boolean b2 = false;
                if (n == 45) {
                    n = this.read();
                    if (n != 46 && (n < 48 || n > 57)) {
                        this.unread(this.peekc = n);
                        n = 45;
                        break Label_0584;
                    }
                    b2 = true;
                }
                double n7 = 0.0;
                int i = 0;
                int n8 = 0;
                while (true) {
                    if (n == '.' && n8 == 0) {
                        n8 = 1;
                    }
                    else {
                        if (48 > n || n > 57) {
                            break;
                        }
                        ++n6;
                        n7 = n7 * 10.0 + (n - '0');
                        i += n8;
                    }
                    n = this.read();
                }
                this.peekc = n;
                if (i != 0) {
                    double n9 = 10.0;
                    --i;
                    while (i > 0) {
                        n9 *= 10.0;
                        --i;
                    }
                    n7 /= n9;
                }
                this.nval = (b2 ? (-n7) : n7);
                this.endpos = ((n == -1) ? (this.readpos - 1) : (this.readpos - 2));
                if (n6 != 0) {
                    return this.ttype = -2;
                }
                this.unread(n);
                if (b2) {
                    this.unread(46);
                    n = 45;
                }
                else {
                    n = 46;
                }
            }
        }
        if ((b & 0x4) != 0x0) {
            int n10 = 0;
            do {
                if (n10 >= this.buf.length) {
                    final char[] buf = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, buf, 0, this.buf.length);
                    this.buf = buf;
                }
                this.buf[n10++] = (char)n;
                n = this.read();
            } while ((((n < 0) ? 1 : ((n < 256) ? ctype[n] : 4)) & 0x6) != 0x0);
            this.peekc = n;
            this.sval = String.copyValueOf(this.buf, 0, n10);
            if (this.forceLower) {
                this.sval = this.sval.toLowerCase();
            }
            this.endpos = ((n == -1) ? (this.readpos - 1) : (this.readpos - 2));
            return this.ttype = -3;
        }
        if ((b & 0x8) != 0x0) {
            this.ttype = n;
            int n11 = 0;
            int n12 = this.read();
            while (n12 >= 0 && n12 != this.ttype && n12 != 10 && n12 != 13) {
                int n13;
                if (n12 == 92) {
                    final int read;
                    n13 = (read = this.read());
                    if (n13 >= 48 && n13 <= 55) {
                        n13 -= 48;
                        final int read2 = this.read();
                        if (48 <= read2 && read2 <= 55) {
                            n13 = (n13 << 3) + (read2 - 48);
                            final int read3 = this.read();
                            if (48 <= read3 && read3 <= 55 && read <= 51) {
                                n13 = (n13 << 3) + (read3 - 48);
                                n12 = this.read();
                            }
                            else {
                                n12 = read3;
                            }
                        }
                        else {
                            n12 = read2;
                        }
                    }
                    else {
                        switch (n13) {
                            case 97: {
                                n13 = 7;
                                break;
                            }
                            case 98: {
                                n13 = 8;
                                break;
                            }
                            case 102: {
                                n13 = 12;
                                break;
                            }
                            case 110: {
                                n13 = 10;
                                break;
                            }
                            case 114: {
                                n13 = 13;
                                break;
                            }
                            case 116: {
                                n13 = 9;
                                break;
                            }
                            case 118: {
                                n13 = 11;
                                break;
                            }
                        }
                        n12 = this.read();
                    }
                }
                else {
                    n13 = n12;
                    n12 = this.read();
                }
                if (n11 >= this.buf.length) {
                    final char[] buf2 = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, buf2, 0, this.buf.length);
                    this.buf = buf2;
                }
                this.buf[n11++] = (char)n13;
            }
            this.peekc = ((n12 == this.ttype) ? Integer.MAX_VALUE : n12);
            this.sval = String.copyValueOf(this.buf, 0, n11);
            this.endpos = this.readpos - 2;
            return this.ttype;
        }
        if ((this.slashSlashCommentsP && n == this.slashSlash[0]) || (this.slashStarCommentsP && n == this.slashStar[0])) {
            if (n == this.slashStar[0] && this.slashStar.length == 1) {
                int n14;
                while ((n14 = this.read()) != this.starSlash[0]) {
                    if (n14 == 13) {
                        ++this.LINENO;
                        n14 = this.read();
                        if (n14 == 10) {
                            n14 = this.read();
                        }
                    }
                    else if (n14 == 10) {
                        ++this.LINENO;
                        n14 = this.read();
                    }
                    if (n14 < 0) {
                        this.endpos = this.readpos;
                        return this.ttype = -1;
                    }
                }
                return this.nextToken();
            }
            if (n == this.slashSlash[0] && this.slashSlash.length == 1) {
                int read4;
                while ((read4 = this.read()) != 10 && read4 != 13 && read4 >= 0) {}
                this.peekc = read4;
                return this.nextToken();
            }
            final int read5 = this.read();
            if (read5 == this.slashStar[1] && this.slashStarCommentsP) {
                int n16;
                for (int n15 = 0; (n16 = this.read()) != this.starSlash[1] || n15 != this.starSlash[0]; n15 = n16) {
                    if (n16 == 13) {
                        ++this.LINENO;
                        n16 = this.read();
                        if (n16 == 10) {
                            n16 = this.read();
                        }
                    }
                    else if (n16 == 10) {
                        ++this.LINENO;
                        n16 = this.read();
                    }
                    if (n16 < 0) {
                        this.endpos = this.readpos;
                        return this.ttype = -1;
                    }
                }
                return this.nextToken();
            }
            if (read5 == this.slashSlash[1] && this.slashSlashCommentsP) {
                int read6;
                while ((read6 = this.read()) != 10 && read6 != 13 && read6 >= 0) {}
                this.peekc = read6;
                return this.nextToken();
            }
            if ((ctype[this.slashSlash[0]] & 0x10) != 0x0) {
                int read7;
                while ((read7 = this.read()) != 10 && read7 != 13 && read7 >= 0) {}
                this.peekc = read7;
                return this.nextToken();
            }
            this.peekc = read5;
            this.endpos = this.readpos - 2;
            return this.ttype = this.slashSlash[0];
        }
        else {
            if ((b & 0x10) != 0x0) {
                int read8;
                while ((read8 = this.read()) != 10 && read8 != 13 && read8 >= 0) {}
                this.peekc = read8;
                return this.nextToken();
            }
            this.endpos = this.readpos - 1;
            return this.ttype = n;
        }
    }
    
    public void setSlashStarTokens(final String s, final String s2) {
        if (s.length() != s2.length()) {
            throw new IllegalArgumentException("SlashStar and StarSlash tokens must be of same length: '" + s + "' '" + s2 + "'");
        }
        if (s.length() < 1 || s.length() > 2) {
            throw new IllegalArgumentException("SlashStar and StarSlash tokens must be of length 1 or 2: '" + s + "' '" + s2 + "'");
        }
        this.slashStar = s.toCharArray();
        this.starSlash = s2.toCharArray();
        this.commentChar(this.slashStar[0]);
    }
    
    public void setSlashSlashToken(final String s) {
        if (s.length() < 1 || s.length() > 2) {
            throw new IllegalArgumentException("SlashSlash token must be of length 1 or 2: '" + s + "'");
        }
        this.slashSlash = s.toCharArray();
        this.commentChar(this.slashSlash[0]);
    }
    
    public void pushBack() {
        if (this.ttype != -4) {
            this.pushedBack = true;
        }
    }
    
    public int lineno() {
        return this.LINENO;
    }
    
    public int getStartPosition() {
        return this.startpos;
    }
    
    public void setStartPosition(final int startpos) {
        this.startpos = startpos;
    }
    
    public int getEndPosition() {
        return this.endpos;
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
}

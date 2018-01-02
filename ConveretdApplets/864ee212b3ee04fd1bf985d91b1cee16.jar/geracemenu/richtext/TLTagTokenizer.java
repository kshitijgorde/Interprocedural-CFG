// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.util.Enumeration;

public class TLTagTokenizer implements Enumeration
{
    public static final int TTT_NOTHING = -1;
    public static final int TTT_TAGPARAM = 0;
    public static final int TTT_TAGNAMEPARAM = 1;
    public static final int TTT_EQUALITY = 2;
    public static final int TTT_PARAMSEPARATOR = 3;
    public static final int TTT_TAGTERMINATOR = 4;
    private static final int QUOTE_SINGLE = 0;
    private static final int QUOTE_DOUBLE = 1;
    protected String stream;
    protected int tokenType;
    private int streamPos;
    private int maxStreamPos;
    
    public void finalize() {
        this.stream = null;
    }
    
    protected int read() {
        if (this.streamPos > this.maxStreamPos) {
            return -1;
        }
        return this.stream.charAt(this.streamPos++);
    }
    
    protected void pushBack() {
        if (this.streamPos > 0) {
            --this.streamPos;
        }
    }
    
    public int ttype() {
        return this.tokenType;
    }
    
    public String nextToken() {
        String s = "";
        int n;
        for (n = this.read(); Character.isWhitespace((char)n); n = this.read()) {}
        if (n == 34 || n == 39) {
            final boolean b = n == 34;
            int i = this.read();
            if (i != 39 && i != 34) {
                while (i != -1) {
                    final char c = (char)i;
                    i = this.read();
                    if (c != '\\') {
                        s += c;
                        if ((i == 39 && !b) || (i == 34 && b)) {
                            break;
                        }
                        continue;
                    }
                }
            }
            if ((b && i == 39) || (!b && i == 34)) {
                throw new IllegallyTerminatedStringException();
            }
            if (i == -1) {
                throw new UnterminatedStringException(s);
            }
            this.tokenType = 1;
            return s;
        }
        else {
            if (n == 61) {
                this.tokenType = 2;
                return s + '=';
            }
            if (n == 44) {
                this.tokenType = 3;
                return s + ',';
            }
            if (n == 47) {
                this.tokenType = 4;
                return s + '/';
            }
            while (n != -1 && " =,".indexOf(n) == -1) {
                s += (char)n;
                n = this.read();
            }
            if (",=".indexOf(n) != -1) {
                this.pushBack();
            }
            if (s.length() == 0) {
                this.tokenType = -1;
            }
            else {
                this.tokenType = 1;
            }
            return s;
        }
    }
    
    public boolean hasMoreTokens() {
        return this.streamPos <= this.maxStreamPos;
    }
    
    public boolean hasMoreElements() {
        return this.hasMoreTokens();
    }
    
    public Object nextElement() {
        return this.nextToken();
    }
    
    public String typeToString(final int n) {
        switch (n) {
            case 0:
            case 1: {
                return "tag parameter or name";
            }
            case 2: {
                return "equality sign '='";
            }
            case 3: {
                return "tag parameter separator ','";
            }
            case 4: {
                return "tag terminator '/'";
            }
            default: {
                return "nothing";
            }
        }
    }
    
    public TLTagTokenizer(final String s) {
        this.tokenType = -1;
        this.streamPos = 0;
        this.maxStreamPos = 0;
        this.stream = s.trim();
        this.maxStreamPos = this.stream.length() - 1;
    }
}

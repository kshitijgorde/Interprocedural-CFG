// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

import java.util.Enumeration;

public class TLStreamTokenizer implements Enumeration
{
    public static final int STT_NOTHING = -1;
    public static final int STT_TAG = 0;
    public static final int STT_STRDATA = 1;
    public static final int STT_ESCCHAR = 2;
    public static final int STT_EOI = 3;
    protected String stream;
    protected int tokenType;
    private boolean inTag;
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
        int n = this.read();
        if (n == 92) {
            final int read = this.read();
            this.tokenType = 2;
            return s + (char)read;
        }
        if (n == 60) {
            int n2;
            for (n2 = this.read(); n2 != -1 && n2 != 13 && n2 != 62; n2 = this.read()) {
                s += (char)n2;
            }
            if (n2 != 62) {
                throw new UnterminatedTagException();
            }
            this.tokenType = 0;
            return s;
        }
        else {
            if (n == 62) {
                throw new UnterminatedTagException();
            }
            while (n != -1 && !this.isValid(n)) {
                s += (char)n;
                n = this.read();
            }
            if (this.isValid(n)) {
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
    
    protected boolean isValid(final int n) {
        return n == 13 || "<>\\".indexOf(n) != -1;
    }
    
    public boolean hasMoreElements() {
        return this.hasMoreTokens();
    }
    
    public Object nextElement() {
        return this.nextToken();
    }
    
    public TLStreamTokenizer(final String stream) {
        this.tokenType = -1;
        this.inTag = false;
        this.streamPos = 0;
        this.maxStreamPos = 0;
        this.stream = stream;
        this.maxStreamPos = stream.length() - 1;
    }
}

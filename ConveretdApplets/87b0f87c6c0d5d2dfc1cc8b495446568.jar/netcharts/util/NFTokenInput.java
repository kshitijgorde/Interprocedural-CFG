// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class NFTokenInput
{
    public static final int EOF = -1;
    protected static String fontEncoding;
    protected String str;
    protected StringBuffer buf;
    protected InputStream is;
    protected int len;
    protected int count;
    protected int pushBack;
    
    public NFTokenInput() {
        this.str = null;
        this.buf = null;
        this.is = null;
        this.len = 0;
        this.count = 0;
        this.pushBack = -1;
    }
    
    public static void setFontEncoding(final String fontEncoding) {
        NFTokenInput.fontEncoding = fontEncoding;
    }
    
    public void setInput(final String str) {
        this.close();
        this.str = str;
        this.len = str.length();
    }
    
    public void setInput(final StringBuffer buf) {
        this.close();
        this.buf = buf;
        this.len = buf.length();
    }
    
    public void setInput(final InputStream inputStream) {
        this.setInput(inputStream, true);
    }
    
    public void setInput(final BufferedInputStream bufferedInputStream) {
        this.setInput(bufferedInputStream, false);
    }
    
    public void setInput(final InputStream inputStream, final boolean b) {
        InputStream is;
        if (b) {
            is = new BufferedInputStream(inputStream);
        }
        else {
            is = inputStream;
        }
        this.close();
        this.is = is;
    }
    
    public int nextChar() {
        if (this.pushBack != -1) {
            final int pushBack = this.pushBack;
            this.pushBack = -1;
            return pushBack;
        }
        if (this.str != null) {
            if (this.count >= this.len) {
                return -1;
            }
            final char char1 = this.str.charAt(this.count);
            ++this.count;
            return char1;
        }
        else if (this.buf != null) {
            if (this.count >= this.len) {
                return -1;
            }
            final char char2 = this.buf.charAt(this.count);
            ++this.count;
            return char2;
        }
        else {
            if (this.is != null) {
                int read;
                try {
                    read = this.is.read();
                    if (read == -1) {
                        return -1;
                    }
                }
                catch (Exception ex) {
                    return -1;
                }
                ++this.count;
                return read;
            }
            return -1;
        }
    }
    
    public void pushBack(final int pushBack) {
        this.pushBack = pushBack;
    }
    
    public void close() {
        this.str = null;
        this.buf = null;
        this.len = 0;
        this.count = 0;
        this.pushBack = -1;
        if (this.is != null) {
            try {
                this.is.close();
            }
            catch (Exception ex) {}
        }
        this.is = null;
    }
    
    static {
        NFTokenInput.fontEncoding = null;
    }
}

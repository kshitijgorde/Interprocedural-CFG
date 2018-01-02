// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Reader;

public class NFTokenInput11 extends NFTokenInput
{
    private Reader a;
    
    public NFTokenInput11() {
        this.a = null;
    }
    
    public void setInput(final InputStream inputStream, final boolean b) {
        InputStream inputStream2;
        if (b) {
            inputStream2 = new BufferedInputStream(inputStream);
        }
        else {
            inputStream2 = inputStream;
        }
        Reader a = null;
        if (NFTokenInput.fontEncoding != null) {
            try {
                a = new InputStreamReader(inputStream2, NFTokenInput.fontEncoding);
            }
            catch (Exception ex) {
                a = null;
            }
        }
        if (a == null) {
            a = new InputStreamReader(inputStream2);
        }
        this.close();
        this.a = a;
    }
    
    public void setInput(final Reader reader) {
        this.setInput(new BufferedReader(reader));
    }
    
    public void setInput(final BufferedReader a) {
        this.close();
        this.a = a;
    }
    
    public int nextChar() {
        if (super.pushBack != -1) {
            final int pushBack = super.pushBack;
            super.pushBack = -1;
            return pushBack;
        }
        if (this.a != null) {
            int read;
            try {
                read = this.a.read();
                if (read == -1) {
                    return -1;
                }
            }
            catch (Exception ex) {
                return -1;
            }
            ++super.count;
            return read;
        }
        return super.nextChar();
    }
    
    public void close() {
        super.close();
        if (this.a != null) {
            try {
                this.a.close();
            }
            catch (Exception ex) {}
        }
        this.a = null;
    }
}

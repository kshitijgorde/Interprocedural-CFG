// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.Reader;
import java.io.IOException;
import java.io.PushbackReader;

public class ExtendedPushbackReader extends PushbackReader
{
    public static final String Ident = "$Id: ExtendedPushbackReader.java,v 1.2 2011/01/10 05:13:52 blm Exp $";
    
    public boolean atWhitespace() throws IOException {
        final int read = this.read();
        if (read != -1) {
            if (Character.isWhitespace((char)read)) {
                return true;
            }
            this.unread(read);
        }
        return false;
    }
    
    public boolean skipWhitespace() throws IOException {
        int read;
        while ((read = this.read()) != -1) {
            if (!Character.isWhitespace((char)read)) {
                this.unread(read);
                return true;
            }
        }
        return false;
    }
    
    public ExtendedPushbackReader(final Reader reader, final int n) {
        super(reader, n);
    }
    
    public int peek() throws IOException {
        final int read = this.read();
        this.unread(read);
        return read;
    }
    
    public boolean at(final char c) throws IOException {
        final int read = this.read();
        if (read == c) {
            return true;
        }
        this.unread(read);
        return false;
    }
    
    public boolean at(final String s) throws IOException {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final int read;
            if ((read = this.read()) != s.charAt(i)) {
                this.unread(read);
                while (i-- > 0) {
                    this.unread(s.charAt(i));
                }
                return false;
            }
        }
        return true;
    }
    
    public boolean at(final String[] array) throws IOException {
        if (!this.at(array[0])) {
            return false;
        }
        for (int length = array.length, i = 1; i < length; ++i) {
            if ((array[i] == null) ? (!this.atWhitespace()) : (!this.skipWhitespace() || !this.at(array[i]))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean skipTo(final String s) throws IOException {
        int n = 0;
        int read;
        while ((read = this.read()) != -1) {
            if (read != s.charAt(n)) {
                n = 0;
            }
            else {
                if (++n == s.length()) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    public boolean skipTo(final String[] array) throws IOException {
        while (this.skipTo(array[0])) {
            int length;
            int n;
            for (length = array.length, n = 1; n < length && ((array[n] == null) ? (!this.atWhitespace()) : (!this.skipWhitespace() || !this.at(array[n]))); ++n) {}
            if (n == length) {
                return true;
            }
        }
        return false;
    }
}

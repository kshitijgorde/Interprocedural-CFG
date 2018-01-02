// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

import java.io.IOException;
import java.io.InputStream;

public final class StreamCharacterIterator implements CharacterIterator
{
    private final InputStream is;
    private final StringBuffer buff;
    private boolean closed;
    
    public StreamCharacterIterator(final InputStream is) {
        this.is = is;
        this.buff = new StringBuffer(512);
        this.closed = false;
    }
    
    public char charAt(final int n) {
        try {
            this.ensure(n);
            return this.buff.charAt(n);
        }
        catch (IOException ex) {
            throw new StringIndexOutOfBoundsException(ex.getMessage());
        }
    }
    
    private void ensure(final int n) throws IOException {
        if (this.closed) {
            return;
        }
        if (n < this.buff.length()) {
            return;
        }
        this.read(n + 1 - this.buff.length());
    }
    
    public boolean isEnd(final int n) {
        if (this.buff.length() > n) {
            return false;
        }
        try {
            this.ensure(n);
            return this.buff.length() <= n;
        }
        catch (IOException ex) {
            throw new StringIndexOutOfBoundsException(ex.getMessage());
        }
    }
    
    private int read(int n) throws IOException {
        if (this.closed) {
            return 0;
        }
        while (--n >= 0) {
            final int read = this.is.read();
            if (read < 0) {
                this.closed = true;
                break;
            }
            this.buff.append((char)read);
        }
        return n - n;
    }
    
    private void readAll() throws IOException {
        while (!this.closed) {
            this.read(1000);
        }
    }
    
    public String substring(final int n) {
        try {
            this.readAll();
            return this.buff.toString().substring(n);
        }
        catch (IOException ex) {
            throw new StringIndexOutOfBoundsException(ex.getMessage());
        }
    }
    
    public String substring(final int n, final int n2) {
        try {
            this.ensure(n + n2);
            return this.buff.toString().substring(n, n2);
        }
        catch (IOException ex) {
            throw new StringIndexOutOfBoundsException(ex.getMessage());
        }
    }
}

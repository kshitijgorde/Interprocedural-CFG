// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

import java.io.IOException;
import java.io.Reader;

public final class ReaderCharacterIterator implements CharacterIterator
{
    private final Reader reader;
    private final StringBuffer buff;
    private boolean closed;
    
    public ReaderCharacterIterator(final Reader reader) {
        this.reader = reader;
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
    
    private int read(final int n) throws IOException {
        if (this.closed) {
            return 0;
        }
        final char[] array = new char[n];
        int i = 0;
        do {
            final int read = this.reader.read(array);
            if (read < 0) {
                this.closed = true;
                break;
            }
            i += read;
            this.buff.append(array, 0, read);
        } while (i < n);
        return i;
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

// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

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
    
    public String substring(final int offset, final int length) {
        try {
            this.ensure(offset + length);
            return this.buff.toString().substring(offset, length);
        }
        catch (IOException e) {
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }
    }
    
    public String substring(final int offset) {
        try {
            this.readAll();
            return this.buff.toString().substring(offset);
        }
        catch (IOException e) {
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }
    }
    
    public char charAt(final int pos) {
        try {
            this.ensure(pos);
            return this.buff.charAt(pos);
        }
        catch (IOException e) {
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }
    }
    
    public boolean isEnd(final int pos) {
        if (this.buff.length() > pos) {
            return false;
        }
        try {
            this.ensure(pos);
            return this.buff.length() <= pos;
        }
        catch (IOException e) {
            throw new StringIndexOutOfBoundsException(e.getMessage());
        }
    }
    
    private int read(final int n) throws IOException {
        if (this.closed) {
            return 0;
        }
        final char[] c = new char[n];
        int count = 0;
        int read = 0;
        do {
            read = this.reader.read(c);
            if (read < 0) {
                this.closed = true;
                break;
            }
            count += read;
            this.buff.append(c, 0, read);
        } while (count < n);
        return count;
    }
    
    private void readAll() throws IOException {
        while (!this.closed) {
            this.read(1000);
        }
    }
    
    private void ensure(final int idx) throws IOException {
        if (this.closed) {
            return;
        }
        if (idx < this.buff.length()) {
            return;
        }
        this.read(idx + 1 - this.buff.length());
    }
}

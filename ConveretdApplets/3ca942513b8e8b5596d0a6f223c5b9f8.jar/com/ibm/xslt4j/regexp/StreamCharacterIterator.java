// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

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
    
    private int read(int n) throws IOException {
        if (this.closed) {
            return 0;
        }
        while (--n >= 0) {
            final int c = this.is.read();
            if (c < 0) {
                this.closed = true;
                break;
            }
            this.buff.append((char)c);
        }
        return n - n;
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

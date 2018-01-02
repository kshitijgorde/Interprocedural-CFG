// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.parser.generated;

import java.io.IOException;
import java.io.Reader;

public class SimpleCharStream
{
    int C;
    int B;
    int A;
    public int bufpos;
    protected int[] bufline;
    protected int[] bufcolumn;
    protected int column;
    protected int line;
    protected boolean prevCharIsCR;
    protected boolean prevCharIsLF;
    protected Reader inputStream;
    protected char[] buffer;
    protected int maxNextCharInd;
    protected int inBuf;
    protected int tabSize;
    
    protected void ExpandBuff(final boolean b) {
        final char[] array = new char[this.C + 2048];
        final int[] array2 = new int[this.C + 2048];
        final int[] array3 = new int[this.C + 2048];
        try {
            if (b) {
                System.arraycopy(this.buffer, this.A, array, 0, this.C - this.A);
                System.arraycopy(this.buffer, 0, array, this.C - this.A, this.bufpos);
                this.buffer = array;
                System.arraycopy(this.bufline, this.A, array2, 0, this.C - this.A);
                System.arraycopy(this.bufline, 0, array2, this.C - this.A, this.bufpos);
                this.bufline = array2;
                System.arraycopy(this.bufcolumn, this.A, array3, 0, this.C - this.A);
                System.arraycopy(this.bufcolumn, 0, array3, this.C - this.A, this.bufpos);
                this.bufcolumn = array3;
                final int n = this.bufpos + (this.C - this.A);
                this.bufpos = n;
                this.maxNextCharInd = n;
            }
            else {
                System.arraycopy(this.buffer, this.A, array, 0, this.C - this.A);
                this.buffer = array;
                System.arraycopy(this.bufline, this.A, array2, 0, this.C - this.A);
                this.bufline = array2;
                System.arraycopy(this.bufcolumn, this.A, array3, 0, this.C - this.A);
                this.bufcolumn = array3;
                final int n2 = this.bufpos - this.A;
                this.bufpos = n2;
                this.maxNextCharInd = n2;
            }
        }
        catch (Throwable t) {
            throw new Error(t.getMessage());
        }
        this.C += 2048;
        this.B = this.C;
        this.A = 0;
    }
    
    protected void FillBuff() throws IOException {
        if (this.maxNextCharInd == this.B) {
            if (this.B == this.C) {
                if (this.A > 2048) {
                    final boolean b = false;
                    this.maxNextCharInd = (b ? 1 : 0);
                    this.bufpos = (b ? 1 : 0);
                    this.B = this.A;
                }
                else if (this.A < 0) {
                    final boolean b2 = false;
                    this.maxNextCharInd = (b2 ? 1 : 0);
                    this.bufpos = (b2 ? 1 : 0);
                }
                else {
                    this.ExpandBuff(false);
                }
            }
            else if (this.B > this.A) {
                this.B = this.C;
            }
            else if (this.A - this.B < 2048) {
                this.ExpandBuff(true);
            }
            else {
                this.B = this.A;
            }
        }
        try {
            final int read;
            if ((read = this.inputStream.read(this.buffer, this.maxNextCharInd, this.B - this.maxNextCharInd)) == -1) {
                this.inputStream.close();
                throw new IOException();
            }
            this.maxNextCharInd += read;
        }
        catch (IOException ex) {
            --this.bufpos;
            this.backup(0);
            if (this.A == -1) {
                this.A = this.bufpos;
            }
            throw ex;
        }
    }
    
    public char BeginToken() throws IOException {
        this.A = -1;
        final char char1 = this.readChar();
        this.A = this.bufpos;
        return char1;
    }
    
    protected void UpdateLineColumn(final char c) {
        ++this.column;
        if (this.prevCharIsLF) {
            this.prevCharIsLF = false;
            final int line = this.line;
            final int column = 1;
            this.column = column;
            this.line = line + column;
        }
        else if (this.prevCharIsCR) {
            this.prevCharIsCR = false;
            if (c == '\n') {
                this.prevCharIsLF = true;
            }
            else {
                final int line2 = this.line;
                final int column2 = 1;
                this.column = column2;
                this.line = line2 + column2;
            }
        }
        switch (c) {
            case '\r': {
                this.prevCharIsCR = true;
                break;
            }
            case '\n': {
                this.prevCharIsLF = true;
                break;
            }
            case '\t': {
                --this.column;
                this.column += this.tabSize - this.column % this.tabSize;
                break;
            }
        }
        this.bufline[this.bufpos] = this.line;
        this.bufcolumn[this.bufpos] = this.column;
    }
    
    public char readChar() throws IOException {
        if (this.inBuf > 0) {
            --this.inBuf;
            if (++this.bufpos == this.C) {
                this.bufpos = 0;
            }
            return this.buffer[this.bufpos];
        }
        if (++this.bufpos >= this.maxNextCharInd) {
            this.FillBuff();
        }
        final char c = this.buffer[this.bufpos];
        this.UpdateLineColumn(c);
        return c;
    }
    
    public int getEndColumn() {
        return this.bufcolumn[this.bufpos];
    }
    
    public int getEndLine() {
        return this.bufline[this.bufpos];
    }
    
    public int getBeginColumn() {
        return this.bufcolumn[this.A];
    }
    
    public int getBeginLine() {
        return this.bufline[this.A];
    }
    
    public void backup(final int n) {
        this.inBuf += n;
        final int bufpos = this.bufpos - n;
        this.bufpos = bufpos;
        if (bufpos < 0) {
            this.bufpos += this.C;
        }
    }
    
    public SimpleCharStream(final Reader inputStream, final int line, final int n, final int n2) {
        this.bufpos = -1;
        this.column = 0;
        this.line = 1;
        this.prevCharIsCR = false;
        this.prevCharIsLF = false;
        this.maxNextCharInd = 0;
        this.inBuf = 0;
        this.tabSize = 8;
        this.inputStream = inputStream;
        this.line = line;
        this.column = n - 1;
        this.C = n2;
        this.B = n2;
        this.buffer = new char[n2];
        this.bufline = new int[n2];
        this.bufcolumn = new int[n2];
    }
    
    public SimpleCharStream(final Reader reader, final int n, final int n2) {
        this(reader, n, n2, 4096);
    }
    
    public String GetImage() {
        if (this.bufpos >= this.A) {
            return new String(this.buffer, this.A, this.bufpos - this.A + 1);
        }
        return String.valueOf(new String(this.buffer, this.A, this.C - this.A)) + new String(this.buffer, 0, this.bufpos + 1);
    }
}

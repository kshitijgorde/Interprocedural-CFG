// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.text.CharacterIterator;

public class CDataIterator implements CharacterIterator
{
    private char[] cData;
    private int cStart;
    private int cOffset;
    private int cLength;
    
    CDataIterator() {
    }
    
    CDataIterator(final CTextPointer cTextPointer) {
        this.init(cTextPointer);
    }
    
    CDataIterator(final CTextPointer cTextPointer, final int n) {
        this.init(cTextPointer, n);
    }
    
    void init(final CTextPointer cTextPointer) {
        this.cData = cTextPointer.getPara().getData();
        final int offset = cTextPointer.getOffset();
        this.cStart = offset;
        this.cOffset = offset;
        this.cLength = cTextPointer.getDataLen();
    }
    
    void init(final CTextPointer cTextPointer, final int cStart) {
        this.init(cTextPointer);
        this.cStart = cStart;
    }
    
    public char first() {
        final char[] cData = this.cData;
        final int cStart = this.cStart;
        this.cOffset = cStart;
        return cData[cStart];
    }
    
    public char last() {
        final char[] cData = this.cData;
        final int cOffset = this.cLength - 1;
        this.cOffset = cOffset;
        return cData[cOffset];
    }
    
    public char current() {
        if (this.cOffset >= this.cStart && this.cOffset < this.cLength) {
            return this.cData[this.cOffset];
        }
        return '\uffff';
    }
    
    public char next() {
        if (this.cData[this.cOffset] == '\u2028') {
            this.cOffset += 3;
        }
        else {
            ++this.cOffset;
        }
        if (this.cOffset < this.cLength) {
            return this.cData[this.cOffset];
        }
        this.cOffset = this.cLength;
        return '\uffff';
    }
    
    public char previous() {
        final int cOffset = this.cOffset - 3;
        if (cOffset >= this.cStart && this.cData[cOffset] == '\u2028') {
            this.cOffset = cOffset;
        }
        else {
            --this.cOffset;
        }
        if (this.cOffset >= this.cStart) {
            return this.cData[this.cOffset];
        }
        this.cOffset = this.cStart;
        return '\uffff';
    }
    
    public char setIndex(final int cOffset) {
        if (cOffset >= this.cStart && cOffset < this.cLength) {
            final char[] cData = this.cData;
            this.cOffset = cOffset;
            return cData[cOffset];
        }
        throw new IllegalArgumentException();
    }
    
    public int getBeginIndex() {
        return this.cStart;
    }
    
    public int getEndIndex() {
        return this.cLength;
    }
    
    public int getIndex() {
        return this.cOffset;
    }
    
    public boolean isLast(final int n) {
        boolean b = false;
        if (n + 1 == this.cLength) {
            b = true;
        }
        else if (this.cData[n] == '\u2028' && n + 3 == this.cLength) {
            b = true;
        }
        return b;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
}

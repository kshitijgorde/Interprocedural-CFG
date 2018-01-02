// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import org.apache.xerces.readers.XMLEntityHandler;

public final class ChunkyCharArray implements CharBuffer
{
    private static final int INITIAL_CHUNK_SHIFT = 7;
    private static final int INITIAL_CHUNK_SIZE = 128;
    private StringPool fStringPool;
    private CharDataChunk fCurrentChunk;
    private char[] fCurrentData;
    private int fCurrentIndex;
    private int fLength;
    
    public ChunkyCharArray(final StringPool fStringPool) {
        this.fStringPool = fStringPool;
        this.fCurrentChunk = CharDataChunk.createChunk(fStringPool, null);
    }
    
    public int length() {
        return this.fLength;
    }
    
    public void append(final char c) {
        try {
            this.fCurrentData[this.fCurrentIndex] = c;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.fCurrentIndex == 16384) {
                this.fCurrentChunk = CharDataChunk.createChunk(this.fStringPool, this.fCurrentChunk);
                this.fCurrentData = new char[128];
                this.fCurrentIndex = 0;
            }
            else {
                final char[] fCurrentData = new char[this.fCurrentIndex * 2];
                System.arraycopy(this.fCurrentData, 0, fCurrentData, 0, this.fCurrentIndex);
                this.fCurrentData = fCurrentData;
            }
            this.fCurrentChunk.setCharArray(this.fCurrentData);
            this.fCurrentData[this.fCurrentIndex] = c;
        }
        catch (NullPointerException ex2) {
            this.fCurrentData = new char[128];
            this.fCurrentChunk.setCharArray(this.fCurrentData);
            this.fCurrentData[this.fCurrentIndex] = c;
        }
        ++this.fCurrentIndex;
        ++this.fLength;
    }
    
    public void append(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.append(s.charAt(i));
        }
    }
    
    public void append(final char[] array, int n, int n2) {
        while (n2-- > 0) {
            this.append(array[n++]);
        }
    }
    
    public void append(final ChunkyCharArray chunkyCharArray, final int n, final int n2) {
        this.fCurrentChunk.append(chunkyCharArray, n, n2);
    }
    
    public int addString(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fCurrentChunk.addString(n, n2);
    }
    
    public int addSymbol(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
        return this.fCurrentChunk.addSymbol(n, n2, 0);
    }
}

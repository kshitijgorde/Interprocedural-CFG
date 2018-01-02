// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import org.apache.xerces.readers.XMLEntityHandler;
import java.util.Locale;

public final class CharDataChunk implements StringProducer
{
    public static final int CHUNK_SHIFT = 14;
    public static final int CHUNK_SIZE = 16384;
    public static final int CHUNK_MASK = 16383;
    private StringPool fStringPool;
    private int fRefCount;
    private int fChunk;
    private char[] fData;
    private CharDataChunk fNextChunk;
    private CharDataChunk fPreviousChunk;
    private static CharDataChunk fgFreeChunks;
    
    public static CharDataChunk createChunk(final StringPool fStringPool, final CharDataChunk fPreviousChunk) {
        CharDataChunk fgFreeChunks = CharDataChunk.fgFreeChunks;
        if (fgFreeChunks != null) {
            CharDataChunk.fgFreeChunks = fgFreeChunks.fNextChunk;
        }
        else {
            fgFreeChunks = new CharDataChunk();
        }
        fgFreeChunks.fStringPool = fStringPool;
        fgFreeChunks.fRefCount = 1;
        fgFreeChunks.fChunk = ((fPreviousChunk == null) ? 0 : (fPreviousChunk.fChunk + 1));
        fgFreeChunks.fNextChunk = null;
        fgFreeChunks.fPreviousChunk = fPreviousChunk;
        if (fPreviousChunk != null) {
            fPreviousChunk.setNextChunk(fgFreeChunks);
        }
        return fgFreeChunks;
    }
    
    public CharDataChunk chunkFor(final int n) {
        final int i = n >> 14;
        if (i == this.fChunk) {
            return this;
        }
        CharDataChunk charDataChunk;
        for (charDataChunk = this.fPreviousChunk; i != charDataChunk.fChunk; charDataChunk = charDataChunk.fPreviousChunk) {}
        return charDataChunk;
    }
    
    public char[] toCharArray() {
        return this.fData;
    }
    
    public void setCharArray(final char[] fData) {
        this.fData = fData;
    }
    
    public CharDataChunk nextChunk() {
        return this.fNextChunk;
    }
    
    public boolean clearPreviousChunk() {
        if (this.fPreviousChunk != null) {
            this.fPreviousChunk.clearNextChunk();
            this.fPreviousChunk.removeRef();
            this.fPreviousChunk = null;
            return true;
        }
        return false;
    }
    
    public void releaseChunk() {
        this.removeRef();
    }
    
    public int addString(final int n, final int n2) {
        final int n3 = n >> 14;
        if (n3 != this.fChunk) {
            if (this.fPreviousChunk == null) {
                throw new RuntimeException(new ImplementationMessages().createMessage(null, 15, 0, null));
            }
            return this.fPreviousChunk.addString(n, n2);
        }
        else {
            if (n3 == n + n2 - 1 >> 14) {
                this.addRef();
                return this.fStringPool.addString((StringPool.StringProducer)this, n & 0x3FFF, n2);
            }
            return this.fStringPool.addString(this.toString(n & 0x3FFF, n2));
        }
    }
    
    public int addSymbol(final int n, final int n2, int hashChars) {
        final int n3 = n >> 14;
        if (n3 != this.fChunk) {
            if (this.fPreviousChunk == null) {
                throw new RuntimeException(new ImplementationMessages().createMessage(null, 15, 0, null));
            }
            return this.fPreviousChunk.addSymbol(n, n2, hashChars);
        }
        else {
            final int n4 = n + n2 - 1 >> 14;
            final int n5 = n & 0x3FFF;
            if (n3 == n4) {
                if (hashChars == 0) {
                    hashChars = StringHasher.hashChars(this.fData, n5, n2);
                }
                int n6 = this.fStringPool.lookupSymbol((StringPool.StringProducer)this, n & 0x3FFF, n2, hashChars);
                if (n6 == -1) {
                    n6 = this.fStringPool.addNewSymbol(this.toString(n & 0x3FFF, n2), hashChars);
                }
                return n6;
            }
            return this.fStringPool.addSymbol(this.toString(n & 0x3FFF, n2));
        }
    }
    
    public void append(final XMLEntityHandler.CharBuffer charBuffer, final int n, int n2) {
        CharDataChunk charDataChunk = this.chunkFor(n);
        int n3 = n & 0x3FFF;
        int n4 = (n3 + n2 <= 16384) ? n2 : (16384 - n3);
        while (true) {
            charBuffer.append(charDataChunk.fData, n3, n4);
            n2 -= n4;
            if (n2 == 0) {
                break;
            }
            charDataChunk = charDataChunk.fNextChunk;
            n3 = 0;
            n4 = ((n2 <= 16384) ? n2 : 16384);
        }
    }
    
    public String toString(final int n, int i) {
        if (n + i <= 16384) {
            return new String(this.fData, n, i);
        }
        final StringBuffer sb = new StringBuffer(i);
        final int n2 = 16384 - n;
        sb.append(this.fData, n, n2);
        i -= n2;
        CharDataChunk charDataChunk = this.fNextChunk;
        do {
            final int n3 = (i <= 16384) ? i : 16384;
            sb.append(charDataChunk.fData, 0, n3);
            i -= n3;
            charDataChunk = charDataChunk.fNextChunk;
        } while (i > 0);
        return sb.toString();
    }
    
    public void releaseString(final int n, final int n2) {
        this.removeRef();
    }
    
    public boolean equalsString(int n, int i, final char[] array, int n2, final int n3) {
        if (i != n3) {
            return false;
        }
        if (n + i <= 16384) {
            for (int j = 0; j < i; ++j) {
                if (this.fData[n++] != array[n2++]) {
                    return false;
                }
            }
            return true;
        }
        int n4 = 16384 - n;
        i -= n4;
        while (n4-- > 0) {
            if (this.fData[n++] != array[n2++]) {
                return false;
            }
        }
        CharDataChunk charDataChunk = this.fNextChunk;
        do {
            n = 0;
            int n5 = (i <= 16384) ? i : 16384;
            i -= n5;
            while (n5-- > 0) {
                if (charDataChunk.fData[n++] != array[n2++]) {
                    return false;
                }
            }
            charDataChunk = charDataChunk.fNextChunk;
        } while (i > 0);
        return true;
    }
    
    private void addRef() {
        ++this.fRefCount;
    }
    
    private void removeRef() {
        --this.fRefCount;
        if (this.fRefCount == 0) {
            this.fStringPool = null;
            this.fChunk = -1;
            this.fPreviousChunk = null;
            this.fNextChunk = CharDataChunk.fgFreeChunks;
            CharDataChunk.fgFreeChunks = this;
        }
    }
    
    private void clearNextChunk() {
        if (this.fNextChunk != null) {
            this.fNextChunk.removeRef();
        }
        this.fNextChunk = null;
    }
    
    private void setNextChunk(final CharDataChunk fNextChunk) {
        if (this.fNextChunk != null) {
            throw new RuntimeException("CharDataChunk::setNextChunk");
        }
        fNextChunk.addRef();
        this.fNextChunk = fNextChunk;
    }
    
    static {
        CharDataChunk.fgFreeChunks = null;
    }
}

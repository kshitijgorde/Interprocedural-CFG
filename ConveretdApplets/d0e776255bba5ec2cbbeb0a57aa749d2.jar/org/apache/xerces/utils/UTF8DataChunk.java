// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import org.apache.xerces.readers.XMLEntityHandler;
import java.util.Locale;

public class UTF8DataChunk implements StringPool.StringProducer
{
    public static final int CHUNK_SHIFT = 14;
    public static final int CHUNK_SIZE = 16384;
    public static final int CHUNK_MASK = 16383;
    private StringPool fStringPool;
    private int fRefCount;
    private int fChunk;
    private byte[] fData;
    private UTF8DataChunk fNextChunk;
    private UTF8DataChunk fPreviousChunk;
    private static UTF8DataChunk fgFreeChunks;
    private static char[] fgTempBuffer;
    private static Object fgTempBufferLock;
    static /* synthetic */ Class class$org$apache$xerces$utils$UTF8DataChunk;
    
    public static UTF8DataChunk createChunk(final StringPool stringPool, final UTF8DataChunk utf8DataChunk) {
        final Class clazz = (UTF8DataChunk.class$org$apache$xerces$utils$UTF8DataChunk == null) ? (UTF8DataChunk.class$org$apache$xerces$utils$UTF8DataChunk = class$("org.apache.xerces.utils.UTF8DataChunk")) : UTF8DataChunk.class$org$apache$xerces$utils$UTF8DataChunk;
        synchronized (clazz) {
            if (UTF8DataChunk.fgFreeChunks != null) {
                final UTF8DataChunk fgFreeChunks = UTF8DataChunk.fgFreeChunks;
                UTF8DataChunk.fgFreeChunks = fgFreeChunks.fNextChunk;
                fgFreeChunks.fNextChunk = null;
                fgFreeChunks.init(stringPool, utf8DataChunk);
                return fgFreeChunks;
            }
        }
        return new UTF8DataChunk(stringPool, utf8DataChunk);
    }
    
    public final byte[] toByteArray() {
        return this.fData;
    }
    
    public void setByteArray(final byte[] fData) {
        this.fData = fData;
    }
    
    public UTF8DataChunk nextChunk() {
        return this.fNextChunk;
    }
    
    public boolean clearPreviousChunk() {
        if (this.fPreviousChunk != null) {
            this.fPreviousChunk.setNextChunk(null);
            this.fPreviousChunk.removeRef();
            this.fPreviousChunk = null;
            return true;
        }
        return this.fChunk == 0;
    }
    
    public void releaseChunk() {
        this.removeRef();
    }
    
    public void releaseString(final int n, final int n2) {
        this.removeRef();
    }
    
    public String toString(int i, final int n) {
        synchronized (UTF8DataChunk.fgTempBufferLock) {
            int n2 = 0;
            UTF8DataChunk utf8DataChunk = this;
            final int n3 = i + n;
            int n4 = i & 0x3FFF;
            byte[] array = this.fData;
            int n5 = 0;
            while (i < n3) {
                int n6 = array[n4++] & 0xFF;
                ++i;
                if (n4 == 16384 && i < n3) {
                    utf8DataChunk = utf8DataChunk.fNextChunk;
                    array = utf8DataChunk.fData;
                    n4 = 0;
                }
                if (n6 < 128) {
                    if (n5 != 0) {
                        n5 = 0;
                        if (n6 == 10) {
                            continue;
                        }
                    }
                    if (n6 == 13) {
                        n6 = 10;
                        n5 = 1;
                    }
                    try {
                        UTF8DataChunk.fgTempBuffer[n2] = (char)n6;
                        ++n2;
                    }
                    catch (NullPointerException ex) {
                        (UTF8DataChunk.fgTempBuffer = new char[16384])[n2++] = (char)n6;
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        final char[] fgTempBuffer = new char[n2 * 2];
                        System.arraycopy(UTF8DataChunk.fgTempBuffer, 0, fgTempBuffer, 0, n2);
                        (UTF8DataChunk.fgTempBuffer = fgTempBuffer)[n2++] = (char)n6;
                    }
                }
                else {
                    final int n7 = array[n4++] & 0xFF;
                    ++i;
                    if (n4 == 16384 && i < n3) {
                        utf8DataChunk = utf8DataChunk.fNextChunk;
                        array = utf8DataChunk.fData;
                        n4 = 0;
                    }
                    if ((0xE0 & n6) == 0xC0) {
                        final int n8 = ((0x1F & n6) << 6) + (0x3F & n7);
                        try {
                            UTF8DataChunk.fgTempBuffer[n2] = (char)n8;
                            ++n2;
                        }
                        catch (NullPointerException ex3) {
                            (UTF8DataChunk.fgTempBuffer = new char[16384])[n2++] = (char)n8;
                        }
                        catch (ArrayIndexOutOfBoundsException ex4) {
                            final char[] fgTempBuffer2 = new char[n2 * 2];
                            System.arraycopy(UTF8DataChunk.fgTempBuffer, 0, fgTempBuffer2, 0, n2);
                            (UTF8DataChunk.fgTempBuffer = fgTempBuffer2)[n2++] = (char)n8;
                        }
                    }
                    else {
                        final int n9 = array[n4++] & 0xFF;
                        ++i;
                        if (n4 == 16384 && i < n3) {
                            utf8DataChunk = utf8DataChunk.fNextChunk;
                            array = utf8DataChunk.fData;
                            n4 = 0;
                        }
                        if ((0xF0 & n6) == 0xE0) {
                            final int n10 = ((0xF & n6) << 12) + ((0x3F & n7) << 6) + (0x3F & n9);
                            try {
                                UTF8DataChunk.fgTempBuffer[n2] = (char)n10;
                                ++n2;
                            }
                            catch (NullPointerException ex5) {
                                (UTF8DataChunk.fgTempBuffer = new char[16384])[n2++] = (char)n10;
                            }
                            catch (ArrayIndexOutOfBoundsException ex6) {
                                final char[] fgTempBuffer3 = new char[n2 * 2];
                                System.arraycopy(UTF8DataChunk.fgTempBuffer, 0, fgTempBuffer3, 0, n2);
                                (UTF8DataChunk.fgTempBuffer = fgTempBuffer3)[n2++] = (char)n10;
                            }
                        }
                        else {
                            final int n11 = array[n4++] & 0xFF;
                            ++i;
                            if (n4 == 16384 && i < n3) {
                                utf8DataChunk = utf8DataChunk.fNextChunk;
                                array = utf8DataChunk.fData;
                                n4 = 0;
                            }
                            final int n12 = ((0xF & n6) << 18) + ((0x3F & n7) << 12) + ((0x3F & n9) << 6) + (0x3F & n11);
                            if (n12 < 65536) {
                                try {
                                    UTF8DataChunk.fgTempBuffer[n2] = (char)n12;
                                    ++n2;
                                }
                                catch (NullPointerException ex7) {
                                    (UTF8DataChunk.fgTempBuffer = new char[16384])[n2++] = (char)n12;
                                }
                                catch (ArrayIndexOutOfBoundsException ex8) {
                                    final char[] fgTempBuffer4 = new char[n2 * 2];
                                    System.arraycopy(UTF8DataChunk.fgTempBuffer, 0, fgTempBuffer4, 0, n2);
                                    (UTF8DataChunk.fgTempBuffer = fgTempBuffer4)[n2++] = (char)n12;
                                }
                            }
                            else {
                                final char c = (char)((n12 - 65536 >> 10) + 55296);
                                final char c2 = (char)((n12 - 65536 & 0x3FF) + 56320);
                                try {
                                    UTF8DataChunk.fgTempBuffer[n2] = c;
                                    ++n2;
                                }
                                catch (NullPointerException ex9) {
                                    (UTF8DataChunk.fgTempBuffer = new char[16384])[n2++] = c;
                                }
                                catch (ArrayIndexOutOfBoundsException ex10) {
                                    final char[] fgTempBuffer5 = new char[n2 * 2];
                                    System.arraycopy(UTF8DataChunk.fgTempBuffer, 0, fgTempBuffer5, 0, n2);
                                    (UTF8DataChunk.fgTempBuffer = fgTempBuffer5)[n2++] = c;
                                }
                                try {
                                    UTF8DataChunk.fgTempBuffer[n2] = c2;
                                    ++n2;
                                }
                                catch (NullPointerException ex11) {
                                    (UTF8DataChunk.fgTempBuffer = new char[16384])[n2++] = c2;
                                }
                                catch (ArrayIndexOutOfBoundsException ex12) {
                                    final char[] fgTempBuffer6 = new char[n2 * 2];
                                    System.arraycopy(UTF8DataChunk.fgTempBuffer, 0, fgTempBuffer6, 0, n2);
                                    (UTF8DataChunk.fgTempBuffer = fgTempBuffer6)[n2++] = c2;
                                }
                            }
                        }
                    }
                }
            }
            return new String(UTF8DataChunk.fgTempBuffer, 0, n2);
        }
    }
    
    public boolean equalsString(int i, final int n, final char[] array, int n2, int n3) {
        UTF8DataChunk utf8DataChunk = this;
        final int n4 = i + n;
        int n5 = i & 0x3FFF;
        byte[] array2 = this.fData;
        int n6 = 0;
        while (i < n4) {
            if (n3-- == 0) {
                return false;
            }
            int n7 = array2[n5++] & 0xFF;
            ++i;
            if (n5 == 16384 && i < n4) {
                utf8DataChunk = utf8DataChunk.fNextChunk;
                array2 = utf8DataChunk.fData;
                n5 = 0;
            }
            if (n7 < 128) {
                if (n6 != 0) {
                    n6 = 0;
                    if (n7 == 10) {
                        continue;
                    }
                }
                if (n7 == 13) {
                    n7 = 10;
                    n6 = 1;
                }
                if (n7 != array[n2++]) {
                    return false;
                }
                continue;
            }
            else {
                final int n8 = array2[n5++] & 0xFF;
                ++i;
                if (n5 == 16384 && i < n4) {
                    utf8DataChunk = utf8DataChunk.fNextChunk;
                    array2 = utf8DataChunk.fData;
                    n5 = 0;
                }
                if (('\u00e0' & n7) == '\u00c0') {
                    if ((('\u001f' & n7) << 6) + (0x3F & n8) != array[n2++]) {
                        return false;
                    }
                    continue;
                }
                else {
                    final int n9 = array2[n5++] & 0xFF;
                    ++i;
                    if (n5 == 16384 && i < n4) {
                        utf8DataChunk = utf8DataChunk.fNextChunk;
                        array2 = utf8DataChunk.fData;
                        n5 = 0;
                    }
                    if (('\u00f0' & n7) == '\u00e0') {
                        if ((('\u000f' & n7) << 12) + ((0x3F & n8) << 6) + (0x3F & n9) != array[n2++]) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        final int n10 = array2[n5++] & 0xFF;
                        ++i;
                        if (n5 == 16384 && i < n4) {
                            utf8DataChunk = utf8DataChunk.fNextChunk;
                            array2 = utf8DataChunk.fData;
                            n5 = 0;
                        }
                        final int n11 = (('\u000f' & n7) << 18) + ((0x3F & n8) << 12) + ((0x3F & n9) << 6) + (0x3F & n10);
                        if (n11 < 65536) {
                            if (n11 != array[n2++]) {
                                return false;
                            }
                            continue;
                        }
                        else {
                            if ((n11 - 65536 >> 10) + 55296 != array[n2++]) {
                                return false;
                            }
                            if (n3-- == 0) {
                                return false;
                            }
                            if ((n11 - 65536 & 0x3FF) + 56320 != array[n2++]) {
                                return false;
                            }
                            continue;
                        }
                    }
                }
            }
        }
        return n3 == 0;
    }
    
    public int addString(final int n, final int n2) {
        if (n2 == 0) {
            return 0;
        }
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
                return this.fStringPool.addString(this, n & 0x3FFF, n2);
            }
            return this.fStringPool.addString(this.toString(n & 0x3FFF, n2));
        }
    }
    
    public int addSymbol(final int n, final int n2, int hashcode) {
        if (n2 == 0) {
            return 0;
        }
        final int n3 = n >> 14;
        if (n3 != this.fChunk) {
            if (this.fPreviousChunk == null) {
                throw new RuntimeException(new ImplementationMessages().createMessage(null, 15, 0, null));
            }
            return this.fPreviousChunk.addSymbol(n, n2, hashcode);
        }
        else {
            final int n4 = n + n2 - 1 >> 14;
            final int n5 = n & 0x3FFF;
            if (n3 == n4) {
                if (hashcode == 0) {
                    hashcode = this.getHashcode(n5, n2);
                }
                int n6 = this.fStringPool.lookupSymbol(this, n5, n2, hashcode);
                if (n6 == -1) {
                    n6 = this.fStringPool.addNewSymbol(this.toString(n5, n2), hashcode);
                }
                return n6;
            }
            return this.fStringPool.addSymbol(this.toString(n5, n2));
        }
    }
    
    public void append(final XMLEntityHandler.CharBuffer charBuffer, int i, final int n) {
        UTF8DataChunk utf8DataChunk = this.chunkFor(i);
        final int n2 = i + n;
        int n3 = i & 0x3FFF;
        byte[] array = utf8DataChunk.fData;
        int n4 = 0;
        while (i < n2) {
            int n5 = array[n3++] & 0xFF;
            ++i;
            if (n3 == 16384 && i < n2) {
                utf8DataChunk = utf8DataChunk.fNextChunk;
                array = utf8DataChunk.fData;
                n3 = 0;
            }
            if (n5 < 128) {
                if (n4 != 0) {
                    n4 = 0;
                    if (n5 == 10) {
                        continue;
                    }
                }
                if (n5 == 13) {
                    n5 = 10;
                    n4 = 1;
                }
                charBuffer.append((char)n5);
            }
            else {
                final int n6 = array[n3++] & 0xFF;
                ++i;
                if (n3 == 16384 && i < n2) {
                    utf8DataChunk = utf8DataChunk.fNextChunk;
                    array = utf8DataChunk.fData;
                    n3 = 0;
                }
                if ((0xE0 & n5) == 0xC0) {
                    charBuffer.append((char)(((0x1F & n5) << 6) + (0x3F & n6)));
                }
                else {
                    final int n7 = array[n3++] & 0xFF;
                    ++i;
                    if (n3 == 16384 && i < n2) {
                        utf8DataChunk = utf8DataChunk.fNextChunk;
                        array = utf8DataChunk.fData;
                        n3 = 0;
                    }
                    if ((0xF0 & n5) == 0xE0) {
                        charBuffer.append((char)(((0xF & n5) << 12) + ((0x3F & n6) << 6) + (0x3F & n7)));
                    }
                    else {
                        final int n8 = array[n3++] & 0xFF;
                        ++i;
                        if (n3 == 16384 && i < n2) {
                            utf8DataChunk = utf8DataChunk.fNextChunk;
                            array = utf8DataChunk.fData;
                            n3 = 0;
                        }
                        final int n9 = ((0xF & n5) << 18) + ((0x3F & n6) << 12) + ((0x3F & n7) << 6) + (0x3F & n8);
                        if (n9 < 65536) {
                            charBuffer.append((char)n9);
                        }
                        else {
                            charBuffer.append((char)((n9 - 65536 >> 10) + 55296));
                            charBuffer.append((char)((n9 - 65536 & 0x3FF) + 56320));
                        }
                    }
                }
            }
        }
    }
    
    private int getHashcode(int i, final int n) {
        final int n2 = i + n;
        int n3 = 0;
        final byte[] fData = this.fData;
        while (i < n2) {
            final int n4 = fData[i++] & 0xFF;
            if ((n4 & 0x80) == 0x0) {
                n3 = StringHasher.hashChar(n3, n4);
            }
            else {
                final int n5 = fData[i++] & 0xFF;
                if ((0xE0 & n4) == 0xC0) {
                    n3 = StringHasher.hashChar(n3, ((0x1F & n4) << 6) + (0x3F & n5));
                }
                else {
                    final int n6 = fData[i++] & 0xFF;
                    if ((0xF0 & n4) == 0xE0) {
                        n3 = StringHasher.hashChar(n3, ((0xF & n4) << 12) + ((0x3F & n5) << 6) + (0x3F & n6));
                    }
                    else {
                        final int n7 = ((0xF & n4) << 18) + ((0x3F & n5) << 12) + ((0x3F & n6) << 6) + (0x3F & (fData[i++] & 0xFF));
                        if (n7 < 65536) {
                            n3 = StringHasher.hashChar(n3, n7);
                        }
                        else {
                            n3 = StringHasher.hashChar(StringHasher.hashChar(n3, (n7 - 65536 >> 10) + 55296), (n7 - 65536 & 0x3FF) + 56320);
                        }
                    }
                }
            }
        }
        return StringHasher.finishHash(n3);
    }
    
    private void init(final StringPool fStringPool, final UTF8DataChunk fPreviousChunk) {
        this.fStringPool = fStringPool;
        this.fRefCount = 1;
        this.fChunk = ((fPreviousChunk == null) ? 0 : (fPreviousChunk.fChunk + 1));
        this.fNextChunk = null;
        this.fPreviousChunk = fPreviousChunk;
        if (fPreviousChunk != null) {
            fPreviousChunk.addRef();
            fPreviousChunk.setNextChunk(this);
            fPreviousChunk.removeRef();
        }
    }
    
    private UTF8DataChunk(final StringPool stringPool, final UTF8DataChunk utf8DataChunk) {
        this.fData = null;
        this.init(stringPool, utf8DataChunk);
    }
    
    private final UTF8DataChunk chunkFor(final int n) {
        if (n >> 14 == this.fChunk) {
            return this;
        }
        return this.slowChunkFor(n);
    }
    
    private UTF8DataChunk slowChunkFor(final int n) {
        int i;
        UTF8DataChunk fPreviousChunk;
        for (i = n >> 14, fPreviousChunk = this; i != fPreviousChunk.fChunk; fPreviousChunk = fPreviousChunk.fPreviousChunk) {}
        return fPreviousChunk;
    }
    
    private final void addRef() {
        ++this.fRefCount;
    }
    
    private final void removeRef() {
        --this.fRefCount;
        if (this.fRefCount == 0) {
            this.fStringPool = null;
            this.fChunk = -1;
            this.fPreviousChunk = null;
            final Class clazz = (UTF8DataChunk.class$org$apache$xerces$utils$UTF8DataChunk == null) ? (UTF8DataChunk.class$org$apache$xerces$utils$UTF8DataChunk = class$("org.apache.xerces.utils.UTF8DataChunk")) : UTF8DataChunk.class$org$apache$xerces$utils$UTF8DataChunk;
            synchronized (clazz) {
                this.fNextChunk = null;
                UTF8DataChunk.fgFreeChunks = this;
            }
        }
    }
    
    private void setNextChunk(final UTF8DataChunk fNextChunk) {
        if (fNextChunk == null) {
            if (this.fNextChunk != null) {
                this.fNextChunk.removeRef();
            }
        }
        else {
            if (this.fNextChunk != null) {
                throw new RuntimeException("UTF8DataChunk::setNextChunk");
            }
            fNextChunk.addRef();
        }
        this.fNextChunk = fNextChunk;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        UTF8DataChunk.fgFreeChunks = null;
        UTF8DataChunk.fgTempBuffer = null;
        UTF8DataChunk.fgTempBufferLock = new Object();
    }
}

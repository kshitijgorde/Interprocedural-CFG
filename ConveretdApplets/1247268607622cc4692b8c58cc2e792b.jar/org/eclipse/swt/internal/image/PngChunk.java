// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;
import org.eclipse.swt.SWT;

class PngChunk
{
    byte[] reference;
    static final int LENGTH_OFFSET = 0;
    static final int TYPE_OFFSET = 4;
    static final int DATA_OFFSET = 8;
    static final int TYPE_FIELD_LENGTH = 4;
    static final int LENGTH_FIELD_LENGTH = 4;
    static final int MIN_LENGTH = 12;
    static final int CHUNK_UNKNOWN = -1;
    static final int CHUNK_IHDR = 0;
    static final int CHUNK_PLTE = 1;
    static final int CHUNK_IDAT = 2;
    static final int CHUNK_IEND = 3;
    static final int CHUNK_tRNS = 5;
    static final byte[] TYPE_IHDR;
    static final byte[] TYPE_PLTE;
    static final byte[] TYPE_IDAT;
    static final byte[] TYPE_IEND;
    static final byte[] TYPE_tRNS;
    static final int[] CRC_TABLE;
    int length;
    
    static {
        TYPE_IHDR = new byte[] { 73, 72, 68, 82 };
        TYPE_PLTE = new byte[] { 80, 76, 84, 69 };
        TYPE_IDAT = new byte[] { 73, 68, 65, 84 };
        TYPE_IEND = new byte[] { 73, 69, 78, 68 };
        TYPE_tRNS = new byte[] { 116, 82, 78, 83 };
        CRC_TABLE = new int[256];
        for (int i = 0; i < 256; ++i) {
            PngChunk.CRC_TABLE[i] = i;
            for (int j = 0; j < 8; ++j) {
                if ((PngChunk.CRC_TABLE[i] & 0x1) == 0x0) {
                    PngChunk.CRC_TABLE[i] = (PngChunk.CRC_TABLE[i] >> 1 & Integer.MAX_VALUE);
                }
                else {
                    PngChunk.CRC_TABLE[i] = (0xEDB88320 ^ (PngChunk.CRC_TABLE[i] >> 1 & Integer.MAX_VALUE));
                }
            }
        }
    }
    
    PngChunk(final byte[] reference) {
        this.setReference(reference);
        if (reference.length < 4) {
            SWT.error(40);
        }
        this.length = this.getInt32(0);
    }
    
    PngChunk(final int length) {
        this(new byte[12 + length]);
        this.setLength(length);
    }
    
    byte[] getReference() {
        return this.reference;
    }
    
    void setReference(final byte[] reference) {
        this.reference = reference;
    }
    
    int getInt16(final int n) {
        return 0x0 | (this.reference[n] & 0xFF) << 8 | (this.reference[n + 1] & 0xFF);
    }
    
    void setInt16(final int n, final int n2) {
        this.reference[n] = (byte)(n2 >> 8 & 0xFF);
        this.reference[n + 1] = (byte)(n2 & 0xFF);
    }
    
    int getInt32(final int n) {
        return 0x0 | (this.reference[n] & 0xFF) << 24 | (this.reference[n + 1] & 0xFF) << 16 | (this.reference[n + 2] & 0xFF) << 8 | (this.reference[n + 3] & 0xFF);
    }
    
    void setInt32(final int n, final int n2) {
        this.reference[n] = (byte)(n2 >> 24 & 0xFF);
        this.reference[n + 1] = (byte)(n2 >> 16 & 0xFF);
        this.reference[n + 2] = (byte)(n2 >> 8 & 0xFF);
        this.reference[n + 3] = (byte)(n2 & 0xFF);
    }
    
    int getLength() {
        return this.length;
    }
    
    void setLength(final int length) {
        this.setInt32(0, length);
        this.length = length;
    }
    
    byte[] getTypeBytes() {
        final byte[] array = new byte[4];
        System.arraycopy(this.reference, 4, array, 0, 4);
        return array;
    }
    
    void setType(final byte[] array) {
        if (array.length != 4) {
            SWT.error(5);
        }
        System.arraycopy(array, 0, this.reference, 4, 4);
    }
    
    byte[] getData() {
        final int length = this.getLength();
        if (this.reference.length < 12 + length) {
            SWT.error(6);
        }
        final byte[] array = new byte[length];
        System.arraycopy(this.reference, 8, array, 0, length);
        return array;
    }
    
    void setData(final byte[] array) {
        this.setLength(array.length);
        System.arraycopy(array, 0, this.reference, 8, array.length);
        this.setCRC(this.computeCRC());
    }
    
    int getCRC() {
        return this.getInt32(8 + this.getLength());
    }
    
    void setCRC(final int n) {
        this.setInt32(8 + this.getLength(), n);
    }
    
    int getSize() {
        return 12 + this.getLength();
    }
    
    boolean checkCRC() {
        return this.computeCRC() == this.getCRC();
    }
    
    int computeCRC() {
        int n = -1;
        final int n2 = 4;
        for (int n3 = 8 + this.getLength(), i = n2; i < n3; ++i) {
            n = (PngChunk.CRC_TABLE[(n ^ this.reference[i]) & 0xFF] ^ (n >> 8 & 0xFFFFFF));
        }
        return ~n;
    }
    
    boolean typeMatchesArray(final byte[] array) {
        for (int i = 0; i < 4; ++i) {
            if (this.reference[4 + i] != array[i]) {
                return false;
            }
        }
        return true;
    }
    
    boolean isCritical() {
        final char c = (char)this.getTypeBytes()[0];
        return 'A' <= c && c <= 'Z';
    }
    
    int getChunkType() {
        if (this.typeMatchesArray(PngChunk.TYPE_IHDR)) {
            return 0;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_PLTE)) {
            return 1;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_IDAT)) {
            return 2;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_IEND)) {
            return 3;
        }
        if (this.typeMatchesArray(PngChunk.TYPE_tRNS)) {
            return 5;
        }
        return -1;
    }
    
    static PngChunk readNextFromStream(final LEDataInputStream leDataInputStream) {
        try {
            final int n = 8;
            final byte[] array = new byte[n];
            final int read = leDataInputStream.read(array, 0, n);
            leDataInputStream.unread(array);
            if (read != n) {
                return null;
            }
            final PngChunk pngChunk = new PngChunk(array);
            final int size = pngChunk.getSize();
            final byte[] array2 = new byte[size];
            if (leDataInputStream.read(array2, 0, size) != size) {
                return null;
            }
            switch (pngChunk.getChunkType()) {
                case 0: {
                    return new PngIhdrChunk(array2);
                }
                case 1: {
                    return new PngPlteChunk(array2);
                }
                case 2: {
                    return new PngIdatChunk(array2);
                }
                case 3: {
                    return new PngIendChunk(array2);
                }
                case 5: {
                    return new PngTrnsChunk(array2);
                }
                default: {
                    return new PngChunk(array2);
                }
            }
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    void validate(final PngFileReadState pngFileReadState, final PngIhdrChunk pngIhdrChunk) {
        if (this.reference.length < 12) {
            SWT.error(40);
        }
        final byte[] typeBytes = this.getTypeBytes();
        final char c = (char)typeBytes[2];
        if ('A' > c || c > 'Z') {
            SWT.error(40);
        }
        for (int i = 0; i < 4; ++i) {
            final char c2 = (char)typeBytes[i];
            if (('a' > c2 || c2 > 'z') && ('A' > c2 || c2 > 'Z')) {
                SWT.error(40);
            }
        }
        if (!this.checkCRC()) {
            SWT.error(40);
        }
    }
    
    void contributeToString(final StringBuffer sb) {
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\n\tLength: ");
        sb.append(this.getLength());
        sb.append("\n\tType: ");
        final byte[] typeBytes = this.getTypeBytes();
        for (int i = 0; i < typeBytes.length; ++i) {
            sb.append((char)typeBytes[i]);
        }
        this.contributeToString(sb);
        sb.append("\n\tCRC: ");
        sb.append(Integer.toHexString(this.getCRC()));
        sb.append("\n}");
        return sb.toString();
    }
}

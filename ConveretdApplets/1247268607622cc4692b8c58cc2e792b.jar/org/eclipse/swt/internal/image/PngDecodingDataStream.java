// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;
import java.io.IOException;
import java.io.InputStream;

public class PngDecodingDataStream extends InputStream
{
    InputStream stream;
    byte currentByte;
    int nextBitIndex;
    PngLzBlockReader lzBlockReader;
    int adlerValue;
    static final int PRIME = 65521;
    static final int MAX_BIT = 7;
    
    PngDecodingDataStream(final InputStream stream) throws IOException {
        this.stream = stream;
        this.nextBitIndex = 8;
        this.adlerValue = 1;
        this.lzBlockReader = new PngLzBlockReader(this);
        this.readCompressedDataHeader();
        this.lzBlockReader.readNextBlockHeader();
    }
    
    void assertImageDataAtEnd() throws IOException {
        this.lzBlockReader.assertCompressedDataAtEnd();
    }
    
    public void close() throws IOException {
        this.assertImageDataAtEnd();
        this.checkAdler();
    }
    
    int getNextIdatBits(final int n) throws IOException {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 |= this.getNextIdatBit() << i;
        }
        return n2;
    }
    
    int getNextIdatBit() throws IOException {
        if (this.nextBitIndex > 7) {
            this.currentByte = this.getNextIdatByte();
            this.nextBitIndex = 0;
        }
        return (this.currentByte & 1 << this.nextBitIndex) >> this.nextBitIndex++;
    }
    
    byte getNextIdatByte() throws IOException {
        final byte b = (byte)this.stream.read();
        this.nextBitIndex = 8;
        return b;
    }
    
    void updateAdler(final byte b) {
        final int n = this.adlerValue & 0xFFFF;
        final int n2 = this.adlerValue >> 16 & 0xFFFF;
        final int n3 = (n + (b & 0xFF)) % 65521;
        this.adlerValue = ((n3 + n2) % 65521 << 16 | n3);
    }
    
    public int read() throws IOException {
        final byte nextByte = this.lzBlockReader.getNextByte();
        this.updateAdler(nextByte);
        return nextByte & 0xFF;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        for (int i = 0; i < n2; ++i) {
            final int read = this.read();
            if (read == -1) {
                return i;
            }
            array[n + i] = (byte)read;
        }
        return n2;
    }
    
    void error() {
        SWT.error(40);
    }
    
    private void readCompressedDataHeader() throws IOException {
        final byte nextIdatByte = this.getNextIdatByte();
        final byte nextIdatByte2 = this.getNextIdatByte();
        if (((nextIdatByte & 0xFF) << 8 | (nextIdatByte2 & 0xFF)) % 31 != 0) {
            this.error();
        }
        if ((nextIdatByte & 0xF) != 0x8) {
            this.error();
        }
        final int n = (nextIdatByte & 0xF0) >> 4;
        if (n > 7) {
            this.error();
        }
        this.lzBlockReader.setWindowSize(1 << n + 8);
        if ((nextIdatByte2 & 0x20) != 0x0) {
            this.error();
        }
    }
    
    void checkAdler() throws IOException {
        if (((this.getNextIdatByte() & 0xFF) << 24 | (this.getNextIdatByte() & 0xFF) << 16 | (this.getNextIdatByte() & 0xFF) << 8 | (this.getNextIdatByte() & 0xFF)) != this.adlerValue) {
            this.error();
        }
    }
}

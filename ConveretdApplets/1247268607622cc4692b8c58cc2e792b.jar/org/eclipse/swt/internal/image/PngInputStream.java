// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;
import java.io.InputStream;

public class PngInputStream extends InputStream
{
    PngChunkReader reader;
    PngChunk chunk;
    int offset;
    int length;
    static final int DATA_OFFSET = 8;
    
    public PngInputStream(final PngIdatChunk chunk, final PngChunkReader reader) {
        this.chunk = chunk;
        this.reader = reader;
        this.length = chunk.getLength();
        this.offset = 0;
    }
    
    private boolean checkChunk() throws IOException {
        while (this.offset == this.length) {
            this.chunk = this.reader.readNextChunk();
            if (this.chunk == null) {
                throw new IOException();
            }
            if (this.chunk.getChunkType() == 3) {
                return false;
            }
            if (this.chunk.getChunkType() != 2) {
                throw new IOException();
            }
            this.length = this.chunk.getLength();
            this.offset = 0;
        }
        return true;
    }
    
    public void close() throws IOException {
        this.chunk = null;
    }
    
    public int read() throws IOException {
        if (this.chunk == null) {
            throw new IOException();
        }
        if (this.offset == this.length && !this.checkChunk()) {
            return -1;
        }
        final int n = this.chunk.reference[8 + this.offset] & 0xFF;
        ++this.offset;
        return n;
    }
    
    public int read(final byte[] array, final int n, int min) throws IOException {
        if (this.chunk == null) {
            throw new IOException();
        }
        if (this.offset == this.length && !this.checkChunk()) {
            return -1;
        }
        min = Math.min(min, this.length - this.offset);
        System.arraycopy(this.chunk.reference, 8 + this.offset, array, n, min);
        this.offset += min;
        return min;
    }
}

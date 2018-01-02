// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;

final class TIFFRandomFileAccess
{
    LEDataInputStream inputStream;
    int start;
    int current;
    int next;
    byte[][] buffers;
    static final int CHUNK_SIZE = 8192;
    static final int LIST_SIZE = 128;
    
    public TIFFRandomFileAccess(final LEDataInputStream inputStream) {
        this.inputStream = inputStream;
        final int position = this.inputStream.getPosition();
        this.next = position;
        this.current = position;
        this.start = position;
        this.buffers = new byte[128][];
    }
    
    void seek(final int current) throws IOException {
        if (current == this.current) {
            return;
        }
        if (current < this.start) {
            throw new IOException();
        }
        this.current = current;
        if (this.current > this.next) {
            int i = this.current - this.next;
            int n = this.next / 8192;
            int n2 = this.next % 8192;
            while (i > 0) {
                if (n >= this.buffers.length) {
                    final byte[][] buffers = this.buffers;
                    System.arraycopy(buffers, 0, this.buffers = new byte[Math.max(n + 1, buffers.length + 128)][], 0, buffers.length);
                }
                if (this.buffers[n] == null) {
                    this.buffers[n] = new byte[8192];
                }
                final int read = this.inputStream.read(this.buffers[n], n2, Math.min(i, 8192 - n2));
                i -= read;
                this.next += read;
                ++n;
                n2 = 0;
            }
        }
    }
    
    void read(final byte[] array) throws IOException {
        final int length = array.length;
        int i = Math.min(length, this.next - this.current);
        int j = length - this.next + this.current;
        int n = 0;
        if (i > 0) {
            int n2 = this.current / 8192;
            int n3 = this.current % 8192;
            while (i > 0) {
                final int min = Math.min(i, 8192 - n3);
                System.arraycopy(this.buffers[n2], n3, array, n, min);
                i -= min;
                n += min;
                ++n2;
                n3 = 0;
            }
        }
        if (j > 0) {
            int n4 = this.next / 8192;
            int n5 = this.next % 8192;
            while (j > 0) {
                if (n4 >= this.buffers.length) {
                    final byte[][] buffers = this.buffers;
                    System.arraycopy(buffers, 0, this.buffers = new byte[Math.max(n4, buffers.length + 128)][], 0, buffers.length);
                }
                if (this.buffers[n4] == null) {
                    this.buffers[n4] = new byte[8192];
                }
                final int read = this.inputStream.read(this.buffers[n4], n5, Math.min(j, 8192 - n5));
                System.arraycopy(this.buffers[n4], n5, array, n, read);
                j -= read;
                this.next += read;
                n += read;
                ++n4;
                n5 = 0;
            }
        }
        this.current += length;
    }
}

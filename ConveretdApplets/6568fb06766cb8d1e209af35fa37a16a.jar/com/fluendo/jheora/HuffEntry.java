// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

import com.jcraft.jogg.Buffer;

public class HuffEntry
{
    HuffEntry[] Child;
    HuffEntry previous;
    HuffEntry next;
    int value;
    int frequency;
    
    public HuffEntry() {
        this.Child = new HuffEntry[2];
    }
    
    public HuffEntry copy() {
        final HuffEntry huffEntry = new HuffEntry();
        huffEntry.value = this.value;
        if (this.value < 0) {
            huffEntry.Child[0] = this.Child[0].copy();
            huffEntry.Child[1] = this.Child[1].copy();
        }
        return huffEntry;
    }
    
    public int read(int n, final Buffer buffer) {
        final int b = buffer.readB(1);
        if (b < 0) {
            return -20;
        }
        if (b == 0) {
            if (++n > 32) {
                return -20;
            }
            this.Child[0] = new HuffEntry();
            final int read = this.Child[0].read(n, buffer);
            if (read < 0) {
                return read;
            }
            this.Child[1] = new HuffEntry();
            final int read2 = this.Child[1].read(n, buffer);
            if (read2 < 0) {
                return read2;
            }
            this.value = -1;
        }
        else {
            this.Child[0] = null;
            this.Child[1] = null;
            this.value = buffer.readB(5);
            if (this.value < 0) {
                return -20;
            }
        }
        return 0;
    }
}

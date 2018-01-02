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
        final HuffEntry huffDst = new HuffEntry();
        huffDst.value = this.value;
        if (this.value < 0) {
            huffDst.Child[0] = this.Child[0].copy();
            huffDst.Child[1] = this.Child[1].copy();
        }
        return huffDst;
    }
    
    public int read(int depth, final Buffer opb) {
        final int bit = opb.readB(1);
        if (bit < 0) {
            return -20;
        }
        if (bit == 0) {
            if (++depth > 32) {
                return -20;
            }
            this.Child[0] = new HuffEntry();
            int ret = this.Child[0].read(depth, opb);
            if (ret < 0) {
                return ret;
            }
            this.Child[1] = new HuffEntry();
            ret = this.Child[1].read(depth, opb);
            if (ret < 0) {
                return ret;
            }
            this.value = -1;
        }
        else {
            this.Child[0] = null;
            this.Child[1] = null;
            this.value = opb.readB(5);
            if (this.value < 0) {
                return -20;
            }
        }
        return 0;
    }
}

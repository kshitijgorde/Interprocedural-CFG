// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jorbis;

class Residue1 extends Residue0
{
    int forward(final Block vb, final Object vl, final float[][] in, final int ch) {
        System.err.println("Residue0.forward: not implemented");
        return 0;
    }
    
    int inverse(final Block vb, final Object vl, final float[][] in, final int[] nonzero, final int ch) {
        int used = 0;
        for (int i = 0; i < ch; ++i) {
            if (nonzero[i] != 0) {
                in[used++] = in[i];
            }
        }
        if (used != 0) {
            return Residue0._01inverse(vb, vl, in, used, 1);
        }
        return 0;
    }
}

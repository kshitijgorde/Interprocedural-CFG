// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public final class Recon
{
    private static final short clamp255(int val) {
        val -= 255;
        val = -(255 + (val >> 31 & val));
        return (short)(-(val >> 31 & val));
    }
    
    public static final void CopyBlock(final short[] src, final short[] dest, final int idx, final int srcstride) {
        int off = idx;
        for (int i = 0; i < 8; ++i) {
            dest[off + 0] = src[off + 0];
            dest[off + 1] = src[off + 1];
            dest[off + 2] = src[off + 2];
            dest[off + 3] = src[off + 3];
            dest[off + 4] = src[off + 4];
            dest[off + 5] = src[off + 5];
            dest[off + 6] = src[off + 6];
            dest[off + 7] = src[off + 7];
            off += srcstride;
        }
    }
    
    public static final void ReconIntra(final short[] ReconPtr, final int idx, final short[] ChangePtr, final int LineStep) {
        int roff = idx;
        int coff = 0;
        for (int i = 0; i < 8; ++i) {
            ReconPtr[roff + 0] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 1] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 2] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 3] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 4] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 5] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 6] = clamp255(ChangePtr[coff++] + 128);
            ReconPtr[roff + 7] = clamp255(ChangePtr[coff++] + 128);
            roff += LineStep;
        }
    }
    
    public static final void ReconInter(final short[] ReconPtr, final int idx1, final short[] RefPtr, final int idx2, final short[] ChangePtr, final int LineStep) {
        int coff = 0;
        int roff1 = idx1;
        int roff2 = idx2;
        for (int i = 0; i < 8; ++i) {
            ReconPtr[roff1 + 0] = clamp255(RefPtr[roff2 + 0] + ChangePtr[coff++]);
            ReconPtr[roff1 + 1] = clamp255(RefPtr[roff2 + 1] + ChangePtr[coff++]);
            ReconPtr[roff1 + 2] = clamp255(RefPtr[roff2 + 2] + ChangePtr[coff++]);
            ReconPtr[roff1 + 3] = clamp255(RefPtr[roff2 + 3] + ChangePtr[coff++]);
            ReconPtr[roff1 + 4] = clamp255(RefPtr[roff2 + 4] + ChangePtr[coff++]);
            ReconPtr[roff1 + 5] = clamp255(RefPtr[roff2 + 5] + ChangePtr[coff++]);
            ReconPtr[roff1 + 6] = clamp255(RefPtr[roff2 + 6] + ChangePtr[coff++]);
            ReconPtr[roff1 + 7] = clamp255(RefPtr[roff2 + 7] + ChangePtr[coff++]);
            roff1 += LineStep;
            roff2 += LineStep;
        }
    }
    
    public static final void ReconInterHalfPixel2(final short[] ReconPtr, final int idx1, final short[] RefPtr1, final int idx2, final short[] RefPtr2, final int idx3, final short[] ChangePtr, final int LineStep) {
        int coff = 0;
        int roff1 = idx1;
        int roff2 = idx2;
        int roff3 = idx3;
        for (int i = 0; i < 8; ++i) {
            ReconPtr[roff1 + 0] = clamp255((RefPtr1[roff2 + 0] + RefPtr2[roff3 + 0] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 1] = clamp255((RefPtr1[roff2 + 1] + RefPtr2[roff3 + 1] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 2] = clamp255((RefPtr1[roff2 + 2] + RefPtr2[roff3 + 2] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 3] = clamp255((RefPtr1[roff2 + 3] + RefPtr2[roff3 + 3] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 4] = clamp255((RefPtr1[roff2 + 4] + RefPtr2[roff3 + 4] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 5] = clamp255((RefPtr1[roff2 + 5] + RefPtr2[roff3 + 5] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 6] = clamp255((RefPtr1[roff2 + 6] + RefPtr2[roff3 + 6] >> 1) + ChangePtr[coff++]);
            ReconPtr[roff1 + 7] = clamp255((RefPtr1[roff2 + 7] + RefPtr2[roff3 + 7] >> 1) + ChangePtr[coff++]);
            roff1 += LineStep;
            roff2 += LineStep;
            roff3 += LineStep;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public final class BlockMapping
{
    private int[][][] blockMap;
    private static final int[] mbOrderMap;
    private static final int[][] blockOrderMap1;
    
    public final int quadMapToIndex1(final int n, final int n2, final int n3) {
        return this.blockMap[n][BlockMapping.mbOrderMap[n2]][BlockMapping.blockOrderMap1[n2][n3]];
    }
    
    public final int quadMapToMBTopLeft(final int n, final int n2) {
        return this.blockMap[n][BlockMapping.mbOrderMap[n2]][0];
    }
    
    private void CreateMapping(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = n;
        int n7 = n2;
        final int n8 = (n4 >> 2) + (((n4 & 0x3) != 0x0) ? 1 : 0);
        final int n9 = (n3 >> 2) + (((n3 & 0x3) != 0x0) ? 1 : 0);
        for (int i = 0; i < n8; ++i) {
            for (int j = 0; j < n9; ++j) {
                int n10;
                int n11;
                for (n10 = i << 2, n11 = 0; n11 < 4 && n10 < n4; ++n11, ++n10) {
                    int n12;
                    for (n12 = j << 2, n5 = 0; n5 < 4 && n12 < n3; ++n5, ++n12) {
                        this.blockMap[n6][(n11 & 0x2) + ((n5 & 0x2) >> 1)][((n11 & 0x1) << 1) + (n5 & 0x1)] = n7++;
                    }
                    n7 += n3 - n5;
                }
                ++n6;
                n7 -= n11 * n3 - n5;
            }
            n7 += 3 * n3;
        }
    }
    
    public BlockMapping(final int n, final int n2, final int n3, final int n4) {
        this.blockMap = new int[n + n2 * 2][4][4];
        for (int i = 0; i < n + n2 * 2; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.blockMap[i][j][0] = -1;
                this.blockMap[i][j][1] = -1;
                this.blockMap[i][j][2] = -1;
                this.blockMap[i][j][3] = -1;
            }
        }
        this.CreateMapping(0, 0, n3, n4);
        this.CreateMapping(n, n3 * n4, n3 / 2, n4 / 2);
        this.CreateMapping(n + n2, n3 * n4 * 5 / 4, n3 / 2, n4 / 2);
    }
    
    static {
        mbOrderMap = new int[] { 0, 2, 3, 1 };
        blockOrderMap1 = new int[][] { { 0, 1, 3, 2 }, { 0, 2, 3, 1 }, { 0, 2, 3, 1 }, { 3, 2, 0, 1 } };
    }
}

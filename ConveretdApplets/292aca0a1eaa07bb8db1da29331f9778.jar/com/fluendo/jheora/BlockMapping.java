// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public final class BlockMapping
{
    private int[][][] blockMap;
    private static final int[] mbOrderMap;
    private static final int[][] blockOrderMap1;
    
    public final int quadMapToIndex1(final int sb, final int mb, final int b) {
        return this.blockMap[sb][BlockMapping.mbOrderMap[mb]][BlockMapping.blockOrderMap1[mb][b]];
    }
    
    public final int quadMapToMBTopLeft(final int sb, final int mb) {
        return this.blockMap[sb][BlockMapping.mbOrderMap[mb]][0];
    }
    
    private void CreateMapping(final int firstSB, final int firstFrag, final int hFrags, final int vFrags) {
        int i = 0;
        int j = 0;
        int sb = firstSB;
        int fragIndex = firstFrag;
        final int sBRows = (vFrags >> 2) + (((vFrags & 0x3) != 0x0) ? 1 : 0);
        final int sBCols = (hFrags >> 2) + (((hFrags & 0x3) != 0x0) ? 1 : 0);
        for (int sBRow = 0; sBRow < sBRows; ++sBRow) {
            for (int sBCol = 0; sBCol < sBCols; ++sBCol) {
                int ypos;
                for (ypos = sBRow << 2, i = 0; i < 4 && ypos < vFrags; ++i, ++ypos) {
                    int xpos;
                    for (xpos = sBCol << 2, j = 0; j < 4 && xpos < hFrags; ++j, ++xpos) {
                        final int mb = (i & 0x2) + ((j & 0x2) >> 1);
                        final int B = ((i & 0x1) << 1) + (j & 0x1);
                        this.blockMap[sb][mb][B] = fragIndex++;
                    }
                    fragIndex += hFrags - j;
                }
                ++sb;
                fragIndex -= i * hFrags - j;
            }
            fragIndex += 3 * hFrags;
        }
    }
    
    public BlockMapping(final int ySuperBlocks, final int uvSuperBlocks, final int hFrags, final int vFrags) {
        this.blockMap = new int[ySuperBlocks + uvSuperBlocks * 2][4][4];
        for (int i = 0; i < ySuperBlocks + uvSuperBlocks * 2; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.blockMap[i][j][0] = -1;
                this.blockMap[i][j][1] = -1;
                this.blockMap[i][j][2] = -1;
                this.blockMap[i][j][3] = -1;
            }
        }
        this.CreateMapping(0, 0, hFrags, vFrags);
        this.CreateMapping(ySuperBlocks, hFrags * vFrags, hFrags / 2, vFrags / 2);
        this.CreateMapping(ySuperBlocks + uvSuperBlocks, hFrags * vFrags * 5 / 4, hFrags / 2, vFrags / 2);
    }
    
    static {
        mbOrderMap = new int[] { 0, 2, 3, 1 };
        blockOrderMap1 = new int[][] { { 0, 1, 3, 2 }, { 0, 2, 3, 1 }, { 0, 2, 3, 1 }, { 3, 2, 0, 1 } };
    }
}

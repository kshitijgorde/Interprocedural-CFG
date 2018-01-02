import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class SolverSlice extends Solver
{
    int maxdepth;
    byte[][][][] prune;
    byte[][][][][] prune2;
    final int[][] transCorn;
    final int[][] transEdge;
    int[][] transOri;
    
    public SolverSlice(final ActionListener actionListener) {
        super(actionListener);
        this.transCorn = new int[][] { { 1, 5, 6, 2, 0, 4, 7, 3 }, { 1, 2, 3, 0, 5, 6, 7, 4 }, { 4, 5, 1, 0, 7, 6, 2, 3 } };
        this.transEdge = new int[][] { { 5, 1, 6, 3, 0, 8, 10, 2, 4, 9, 7, 11 }, { 1, 2, 3, 0, 4, 5, 6, 7, 9, 10, 11, 8 }, { 0, 5, 2, 4, 11, 9, 1, 3, 8, 6, 10, 7 } };
    }
    
    public MoveSequence getGenerator() {
        final int[] array = new int[super.sollen];
        final int[] array2 = new int[super.sollen];
        for (int i = 0; i < super.sollen; ++i) {
            array[i] = 12 + super.solmoves[i];
            array2[i] = super.solamount[i];
            if (array[i] > 14) {
                final int[] array3 = array;
                final int n = i;
                array3[n] -= 3;
                array2[i] = 4 - array2[i];
            }
        }
        return new MoveSequence(super.sollen, array, array2);
    }
    
    public void mix(final CubePosition cubePosition) {
        cubePosition.reset();
        final int n = (int)(3.0 * Math.random());
        if (n == 1) {
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        else if (n == 2) {
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        if ((int)(2.0 * Math.random()) != 0) {
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        for (int i = (int)(4.0 * Math.random()); i > 0; --i) {
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        for (int j = (int)(4.0 * Math.random()); j > 0; --j) {
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        for (int k = (int)(4.0 * Math.random()); k > 0; --k) {
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        if ((int)(2.0 * Math.random()) != 0) {
            this.swap(cubePosition.cubeletPerm, 12, 14);
            this.swap(cubePosition.cubeletPerm, 13, 15);
        }
        final int n2 = (int)(4.0 * Math.random());
        cubePosition.cubeletOri[20] = n2;
        cubePosition.cubeletOri[23] = (4 - n2 & 0x3);
        final int n3 = (int)(4.0 * Math.random());
        cubePosition.cubeletOri[21] = n3;
        cubePosition.cubeletOri[24] = (4 - n3 & 0x3);
        final int n4 = (int)(2.0 * Math.random());
        cubePosition.cubeletOri[22] = n4 + n4;
        if (cubePosition.cubeletPerm[1] == 0 || cubePosition.cubeletPerm[3] == 0 || cubePosition.cubeletPerm[4] == 0 || cubePosition.cubeletPerm[6] == 0) {
            final int[] cubeletOri = cubePosition.cubeletOri;
            final int n5 = 22;
            cubeletOri[n5] ^= 0x1;
        }
        if ((cubePosition.cubeletOri[20] + cubePosition.cubeletOri[21] & 0x1) != 0x0) {
            final int[] cubeletOri2 = cubePosition.cubeletOri;
            final int n6 = 22;
            cubeletOri2[n6] ^= 0x1;
        }
        cubePosition.cubeletOri[25] = (4 - cubePosition.cubeletOri[22] & 0x3);
    }
    
    public boolean setPosition(final CubePosition cubePosition, final boolean b) {
        for (int i = 0; i < 7; ++i) {
            int n;
            if (i == 3) {
                n = cubePosition.cubeletOri[0] + cubePosition.cubeletOri[4];
            }
            else {
                n = cubePosition.cubeletOri[i] + cubePosition.cubeletOri[i + 1];
            }
            if (n != 0 && n != 3) {
                return false;
            }
        }
        if (cubePosition.cubeletOri[8] != cubePosition.cubeletOri[10] || cubePosition.cubeletOri[8] != cubePosition.cubeletOri[16] || cubePosition.cubeletOri[8] != cubePosition.cubeletOri[18]) {
            return false;
        }
        if (cubePosition.cubeletOri[9] != cubePosition.cubeletOri[11] || cubePosition.cubeletOri[9] != cubePosition.cubeletOri[17] || cubePosition.cubeletOri[9] != cubePosition.cubeletOri[19]) {
            return false;
        }
        if (cubePosition.cubeletOri[12] != cubePosition.cubeletOri[13] || cubePosition.cubeletOri[12] != cubePosition.cubeletOri[14] || cubePosition.cubeletOri[12] != cubePosition.cubeletOri[15]) {
            return false;
        }
        if (Cubie.settings.superGroup) {
            for (int j = 0; j < 3; ++j) {
                if ((cubePosition.cubeletOri[20 + j] + cubePosition.cubeletOri[23 + j] & 0x3) != 0x0) {
                    return false;
                }
            }
            if (cubePosition.cubeletPerm[1] == 0 || cubePosition.cubeletPerm[3] == 0 || cubePosition.cubeletPerm[4] == 0 || cubePosition.cubeletPerm[6] == 0) {
                if ((cubePosition.cubeletOri[20] + cubePosition.cubeletOri[21] + cubePosition.cubeletOri[22] & 0x1) == 0x0) {
                    return false;
                }
            }
            else if ((cubePosition.cubeletOri[20] + cubePosition.cubeletOri[21] + cubePosition.cubeletOri[22] & 0x1) != 0x0) {
                return false;
            }
        }
        final int[] array = new int[20];
        final int[] array2 = new int[3];
        for (int k = 0; k < 20; ++k) {
            array[k] = cubePosition.cubeletPerm[k];
        }
        array2[0] = cubePosition.cubeletOri[8];
        array2[1] = cubePosition.cubeletOri[9];
        array2[2] = cubePosition.cubeletOri[12];
        if (cubePosition.cubeletOri[0] == 1) {
            this.cycle(array, 0, 4, 7, 3);
            this.cycle(array, 1, 5, 6, 2);
            this.cycle(array, 9, 13, 17, 14);
            this.cycle(array, 11, 12, 19, 15);
            this.swap(array2, 1, 2);
        }
        else if (cubePosition.cubeletOri[0] == 2) {
            this.cycle(array, 0, 1, 5, 4);
            this.cycle(array, 3, 2, 6, 7);
            this.cycle(array, 8, 13, 16, 12);
            this.cycle(array, 10, 14, 18, 15);
            final int n2 = array2[0];
            array2[0] = 1 - array2[2];
            array2[2] = 1 - n2;
        }
        if (array[4] == 0 || array[5] == 0 || array[6] == 0 || array[7] == 0) {
            this.swap(array, 0, 7);
            this.swap(array, 4, 3);
            this.swap(array, 1, 6);
            this.swap(array, 5, 2);
            this.swap(array, 11, 19);
            this.swap(array, 12, 15);
            this.swap(array, 9, 17);
            this.swap(array, 13, 14);
        }
        while (array[0] != 0) {
            this.cycle(array, 0, 1, 2, 3);
            this.cycle(array, 4, 5, 6, 7);
            this.cycle(array, 8, 9, 10, 11);
            this.cycle(array, 16, 17, 18, 19);
            this.swap(array2, 0, 1);
        }
        for (int l = 0; l < 8; ++l) {
            if (array[l] != l) {
                return false;
            }
        }
        if (array[9] != 9 && array[11] != 9 && array[17] != 9 && array[19] != 9) {
            return false;
        }
        while (array[9] != 9) {
            this.cycle(array, 9, 17, 19, 11);
            this.cycle(array, 12, 13, 14, 15);
            array2[1] = 1 - array2[1];
            array2[2] = 1 - array2[2];
        }
        if (array2[1] != 0 || array[11] != 11 || array[17] != 17 || array[19] != 19) {
            return false;
        }
        if (array[8] != 8 && array[10] != 8 && array[16] != 8 && array[18] != 8) {
            return false;
        }
        while (array[8] != 8) {
            this.cycle(array, 8, 16, 18, 10);
            this.cycle(array, 12, 13, 14, 15);
            array2[0] = 1 - array2[0];
            array2[2] = 1 - array2[2];
        }
        if (array2[0] != 0 || array[10] != 10 || array[16] != 16 || array[18] != 18) {
            return false;
        }
        if (array[14] == 12) {
            this.swap(array, 12, 14);
            this.swap(array, 13, 15);
        }
        if (array2[2] != 0 || array[12] != 12 || array[13] != 13 || array[14] != 14 || array[15] != 15) {
            return false;
        }
        if (b) {
            return true;
        }
        if (!super.prepared) {
            return false;
        }
        for (int n3 = 0; n3 < 8; ++n3) {
            if (cubePosition.cubeletPerm[n3] == 0) {
                array[0] = n3;
            }
        }
        for (int n4 = 8; n4 < 20; ++n4) {
            if (cubePosition.cubeletPerm[n4] == 8) {
                array[1] = n4 - 8;
            }
            else if (cubePosition.cubeletPerm[n4] == 9) {
                array[2] = n4 - 8;
            }
            else if (cubePosition.cubeletPerm[n4] == 12) {
                array[3] = n4 - 8;
            }
        }
        array[4] = (cubePosition.cubeletOri[20] << 4) + (cubePosition.cubeletOri[21] << 2) + cubePosition.cubeletOri[22];
        if (super.positionlist == null) {
            super.positionlist = new int[40][5];
            final boolean b2 = false;
            super.sollen = (b2 ? 1 : 0);
            this.maxdepth = (b2 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        else if (super.positionlist[0][0] != array[0] || super.positionlist[0][1] != array[1] || super.positionlist[0][2] != array[2] || super.positionlist[0][3] != array[3] || (super.positionlist[0][4] != array[4] && Cubie.settings.superGroup)) {
            final boolean b3 = false;
            super.sollen = (b3 ? 1 : 0);
            this.maxdepth = (b3 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        super.positionlist[0][0] = array[0];
        super.positionlist[0][1] = array[1];
        super.positionlist[0][2] = array[2];
        super.positionlist[0][3] = array[3];
        super.positionlist[0][4] = array[4];
        return true;
    }
    
    protected void init() {
        this.prune = new byte[8][12][12][12];
        this.prune2 = new byte[8][12][12][12][64];
        this.transOri = new int[64][3];
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    this.transOri[n][0] = ((i + 3 & 0x3) << 4) + (j << 2) + k;
                    this.transOri[n][1] = (i << 4) + ((j + 3 & 0x3) << 2) + k;
                    this.transOri[n][2] = (i << 4) + (j << 2) + (k + 3 & 0x3);
                    ++n;
                }
            }
        }
        byte b = 1;
        this.prune[0][0][1][4] = 1;
        int l;
        do {
            l = 0;
            for (int n2 = 0; n2 < 8; ++n2) {
                for (int n3 = 0; n3 < 12; ++n3) {
                    for (int n4 = 0; n4 < 12; ++n4) {
                        for (int n5 = 0; n5 < 12; ++n5) {
                            if (this.prune[n2][n3][n4][n5] == b) {
                                for (int n6 = 0; n6 < 3; ++n6) {
                                    int n7 = n2;
                                    int n8 = n3;
                                    int n9 = n4;
                                    int n10 = n5;
                                    for (int n11 = 0; n11 < 3; ++n11) {
                                        n7 = this.transCorn[n6][n7];
                                        n8 = this.transEdge[n6][n8];
                                        n9 = this.transEdge[n6][n9];
                                        n10 = this.transEdge[n6][n10];
                                        if (this.prune[n7][n8][n9][n10] == 0) {
                                            this.prune[n7][n8][n9][n10] = (byte)(b + 1);
                                            ++l;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ++b;
        } while (l != 0);
        byte b2 = 1;
        this.prune2[0][0][1][4][0] = 1;
        int n12;
        do {
            n12 = 0;
            for (int n13 = 0; n13 < 8; ++n13) {
                for (int n14 = 0; n14 < 12; ++n14) {
                    for (int n15 = 0; n15 < 12; ++n15) {
                        for (int n16 = 0; n16 < 12; ++n16) {
                            for (int n17 = 0; n17 < 64; ++n17) {
                                if (this.prune2[n13][n14][n15][n16][n17] == b2) {
                                    for (int n18 = 0; n18 < 3; ++n18) {
                                        int n19 = n13;
                                        int n20 = n14;
                                        int n21 = n15;
                                        int n22 = n16;
                                        int n23 = n17;
                                        for (int n24 = 0; n24 < 3; ++n24) {
                                            n19 = this.transCorn[n18][n19];
                                            n20 = this.transEdge[n18][n20];
                                            n21 = this.transEdge[n18][n21];
                                            n22 = this.transEdge[n18][n22];
                                            n23 = this.transOri[n23][n18];
                                            if (this.prune2[n19][n20][n21][n22][n23] == 0) {
                                                this.prune2[n19][n20][n21][n22][n23] = (byte)(b2 + 1);
                                                ++n12;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ++b2;
        } while (n12 != 0);
    }
    
    protected boolean solve() {
        while (!this.search()) {
            if (super.wanttostop) {
                return false;
            }
            ++this.maxdepth;
        }
        return true;
    }
    
    private boolean search() {
        if (this.maxdepth == 0 && super.sollen == 0 && super.positionlist[0][0] == 0 && super.positionlist[0][1] == 0 && super.positionlist[0][2] == 1 && super.positionlist[0][3] == 4 && (!Cubie.settings.superGroup || super.positionlist[0][4] == 0)) {
            return true;
        }
        while (super.sollen >= 0) {
            final int sollen = super.sollen + 1;
            final int n = super.solmoves[super.sollen];
            if (n >= 0) {
                super.positionlist[sollen][0] = this.transCorn[n][super.positionlist[sollen][0]];
                super.positionlist[sollen][1] = this.transEdge[n][super.positionlist[sollen][1]];
                super.positionlist[sollen][2] = this.transEdge[n][super.positionlist[sollen][2]];
                super.positionlist[sollen][3] = this.transEdge[n][super.positionlist[sollen][3]];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][4] = this.transOri[super.positionlist[sollen][4]][n];
                }
            }
            else {
                super.positionlist[sollen][0] = super.positionlist[super.sollen][0];
                super.positionlist[sollen][1] = super.positionlist[super.sollen][1];
                super.positionlist[sollen][2] = super.positionlist[super.sollen][2];
                super.positionlist[sollen][3] = super.positionlist[super.sollen][3];
                super.positionlist[sollen][4] = super.positionlist[super.sollen][4];
            }
            final int[] solamount = super.solamount;
            final int sollen2 = super.sollen;
            ++solamount[sollen2];
            if (super.solamount[super.sollen] > 3) {
                super.solamount[super.sollen] = 0;
                do {
                    final int[] solmoves = super.solmoves;
                    final int sollen3 = super.sollen;
                    ++solmoves[sollen3];
                } while (super.sollen != 0 && super.solmoves[super.sollen] == super.solmoves[super.sollen - 1]);
                if (super.solmoves[super.sollen] < 3) {
                    continue;
                }
                --super.sollen;
            }
            else {
                if ((Cubie.settings.superGroup || super.sollen + this.prune[super.positionlist[sollen][0]][super.positionlist[sollen][1]][super.positionlist[sollen][2]][super.positionlist[sollen][3]] < this.maxdepth + 1) && (!Cubie.settings.superGroup || super.sollen + this.prune2[super.positionlist[sollen][0]][super.positionlist[sollen][1]][super.positionlist[sollen][2]][super.positionlist[sollen][3]][super.positionlist[sollen][4]] < this.maxdepth + 1)) {
                    super.solmoves[sollen] = -1;
                    super.solamount[sollen] = 3;
                    super.sollen = sollen;
                    if (super.sollen >= this.maxdepth) {
                        return true;
                    }
                }
                if (super.wanttostop) {
                    return false;
                }
                continue;
            }
        }
        super.solmoves[0] = -1;
        super.solamount[0] = 3;
        super.sollen = 0;
        return false;
    }
}

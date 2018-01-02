import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class SolverAntiSlice extends Solver
{
    int maxdepth;
    byte[][][][][] prune;
    byte[] pruneFace;
    int[][] transFace;
    final int[][] transCorn;
    final int[][] transEdge;
    final int[][] transOri;
    
    public SolverAntiSlice(final ActionListener actionListener) {
        super(actionListener);
        this.transCorn = new int[][] { { 4, 0, 6, 2, 5, 1, 7, 3 }, { 1, 2, 3, 0, 7, 4, 5, 6 }, { 3, 5, 1, 7, 0, 6, 2, 4 } };
        this.transEdge = new int[][] { { 4, 1, 6, 3, 8, 0, 10, 2, 5, 9, 7, 11 }, { 1, 2, 3, 0, 4, 5, 6, 7, 11, 8, 9, 10 }, { 0, 5, 2, 7, 3, 9, 1, 11, 8, 6, 10, 4 } };
        this.transOri = new int[][] { { 2, 1, 0, 3 }, { 0, 2, 1, 3 }, { 0, 1, 3, 2 } };
    }
    
    public MoveSequence getGenerator() {
        final int[] array = new int[super.sollen];
        for (int i = 0; i < super.sollen; ++i) {
            array[i] = 15 + super.solmoves[i];
            if (array[i] > 17) {
                final int[] array2 = array;
                final int n = i;
                array2[n] -= 3;
            }
        }
        return new MoveSequence(super.sollen, array, super.solamount);
    }
    
    public void mix(final CubePosition cubePosition) {
        cubePosition.reset();
        final int n = (int)(4.0 * Math.random());
        if (n == 1 || n == 3) {
            final int[] cubeletOri = cubePosition.cubeletOri;
            final int n2 = 12;
            final int[] cubeletOri2 = cubePosition.cubeletOri;
            final int n3 = 13;
            final int[] cubeletOri3 = cubePosition.cubeletOri;
            final int n4 = 14;
            final int[] cubeletOri4 = cubePosition.cubeletOri;
            final int n5 = 15;
            final boolean b = true;
            cubeletOri3[n4] = (cubeletOri4[n5] = (b ? 1 : 0));
            cubeletOri[n2] = (cubeletOri2[n3] = (b ? 1 : 0));
        }
        if (n > 1) {
            final int[] cubeletOri5 = cubePosition.cubeletOri;
            final int n6 = 8;
            final int[] cubeletOri6 = cubePosition.cubeletOri;
            final int n7 = 10;
            final int[] cubeletOri7 = cubePosition.cubeletOri;
            final int n8 = 16;
            final int[] cubeletOri8 = cubePosition.cubeletOri;
            final int n9 = 18;
            final boolean b2 = true;
            cubeletOri7[n8] = (cubeletOri8[n9] = (b2 ? 1 : 0));
            cubeletOri5[n6] = (cubeletOri6[n7] = (b2 ? 1 : 0));
        }
        if (n == 1 || n == 2) {
            final int[] cubeletOri9 = cubePosition.cubeletOri;
            final int n10 = 9;
            final int[] cubeletOri10 = cubePosition.cubeletOri;
            final int n11 = 11;
            final int[] cubeletOri11 = cubePosition.cubeletOri;
            final int n12 = 17;
            final int[] cubeletOri12 = cubePosition.cubeletOri;
            final int n13 = 19;
            final boolean b3 = true;
            cubeletOri11[n12] = (cubeletOri12[n13] = (b3 ? 1 : 0));
            cubeletOri9[n10] = (cubeletOri10[n11] = (b3 ? 1 : 0));
        }
        final int n14 = (int)(4.0 * Math.random());
        if (n14 < 2) {
            this.swap(cubePosition.cubeletPerm, 9, 11);
            this.swap(cubePosition.cubeletPerm, 17, 19);
            this.swap(cubePosition.cubeletPerm, 8, 10);
            this.swap(cubePosition.cubeletPerm, 16, 18);
        }
        if (n14 == 1 || n14 == 3) {
            this.swap(cubePosition.cubeletPerm, 9, 17);
            this.swap(cubePosition.cubeletPerm, 11, 19);
            this.swap(cubePosition.cubeletPerm, 13, 14);
            this.swap(cubePosition.cubeletPerm, 12, 15);
        }
        final int n15 = (int)(4.0 * Math.random());
        if (n15 < 2) {
            this.swap(cubePosition.cubeletPerm, 8, 10);
            this.swap(cubePosition.cubeletPerm, 16, 18);
            this.swap(cubePosition.cubeletPerm, 12, 14);
            this.swap(cubePosition.cubeletPerm, 13, 15);
        }
        if (n15 == 1 || n15 == 3) {
            this.swap(cubePosition.cubeletPerm, 8, 18);
            this.swap(cubePosition.cubeletPerm, 10, 16);
            this.swap(cubePosition.cubeletPerm, 13, 14);
            this.swap(cubePosition.cubeletPerm, 12, 15);
        }
        if ((int)(2.0 * Math.random()) == 1) {
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        for (int i = (int)(4.0 * Math.random()); i > 0; --i) {
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        for (int j = (int)(3.0 * Math.random()); j > 0; --j) {
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        final int n16 = (int)(4.0 * Math.random());
        if (n16 == 0) {
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            return;
        }
        if (n16 == 1) {
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            return;
        }
        if (n16 == 2) {
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(0, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(4, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
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
        if ((cubePosition.cubeletOri[8] ^ cubePosition.cubeletOri[9] ^ cubePosition.cubeletOri[12]) != 0x0) {
            return false;
        }
        if (Cubie.settings.superGroup && (cubePosition.cubeletOri[20] != cubePosition.cubeletOri[23] || cubePosition.cubeletOri[21] != cubePosition.cubeletOri[24] || cubePosition.cubeletOri[22] != cubePosition.cubeletOri[25])) {
            return false;
        }
        final int[] array = new int[23];
        for (int j = 0; j < 20; ++j) {
            array[j] = cubePosition.cubeletPerm[j];
        }
        for (int k = 20; k < 23; ++k) {
            array[k] = (cubePosition.cubeletOri[k] & 0x1);
        }
        if (cubePosition.cubeletOri[0] == 1) {
            final int[] array2 = array;
            final int n2 = 22;
            array2[n2] ^= 0x1;
            this.cycle(array, 0, 3, 7, 4);
            this.cycle(array, 1, 5, 6, 2);
            this.cycle(array, 9, 13, 17, 14);
            this.cycle(array, 11, 15, 19, 12);
        }
        else if (cubePosition.cubeletOri[0] == 2) {
            final int[] array3 = array;
            final int n3 = 20;
            array3[n3] ^= 0x1;
            this.cycle(array, 0, 1, 5, 4);
            this.cycle(array, 3, 7, 6, 2);
            this.cycle(array, 8, 13, 16, 12);
            this.cycle(array, 10, 15, 18, 14);
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
            final int[] array4 = array;
            final int n4 = 21;
            array4[n4] ^= 0x1;
            this.cycle(array, 0, 1, 2, 3);
            this.cycle(array, 4, 7, 6, 5);
            this.cycle(array, 8, 9, 10, 11);
            this.cycle(array, 16, 19, 18, 17);
        }
        if (array[0] != 0 || array[2] != 2 || array[5] != 5 || array[7] != 7) {
            return false;
        }
        if (array[3] == 1) {
            final int[] array5 = array;
            final int n5 = 20;
            array5[n5] ^= 0x1;
            final int[] array6 = array;
            final int n6 = 22;
            array6[n6] ^= 0x1;
            this.swap(array, 1, 3);
            this.swap(array, 4, 6);
            this.swap(array, 12, 14);
            this.swap(array, 13, 15);
        }
        else if (array[4] == 1) {
            final int[] array7 = array;
            final int n7 = 21;
            array7[n7] ^= 0x1;
            final int[] array8 = array;
            final int n8 = 22;
            array8[n8] ^= 0x1;
            this.swap(array, 1, 4);
            this.swap(array, 3, 6);
            this.swap(array, 9, 19);
            this.swap(array, 11, 17);
        }
        else if (array[6] == 1) {
            final int[] array9 = array;
            final int n9 = 20;
            array9[n9] ^= 0x1;
            final int[] array10 = array;
            final int n10 = 21;
            array10[n10] ^= 0x1;
            this.swap(array, 1, 6);
            this.swap(array, 3, 4);
            this.swap(array, 8, 18);
            this.swap(array, 10, 16);
        }
        if (array[1] != 1 || array[3] != 3 || array[4] != 4 || array[6] != 6) {
            return false;
        }
        if (array[11] == 9) {
            this.swap(array, 9, 11);
            this.swap(array, 17, 19);
            this.swap(array, 8, 10);
            this.swap(array, 16, 18);
        }
        else if (array[17] == 9) {
            this.swap(array, 9, 17);
            this.swap(array, 11, 19);
            this.swap(array, 13, 14);
            this.swap(array, 12, 15);
        }
        else if (array[19] == 9) {
            this.swap(array, 9, 19);
            this.swap(array, 11, 17);
            this.swap(array, 12, 13);
            this.swap(array, 14, 15);
        }
        if (array[9] != 9 || array[11] != 11 || array[17] != 17 || array[19] != 19) {
            return false;
        }
        if (array[10] == 8) {
            this.swap(array, 8, 10);
            this.swap(array, 16, 18);
            this.swap(array, 12, 14);
            this.swap(array, 13, 15);
        }
        else if (array[18] == 8) {
            this.swap(array, 8, 18);
            this.swap(array, 10, 16);
            this.swap(array, 13, 14);
            this.swap(array, 12, 15);
        }
        else if (array[16] == 8) {
            this.swap(array, 8, 16);
            this.swap(array, 10, 18);
            this.swap(array, 12, 13);
            this.swap(array, 14, 15);
        }
        for (int l = 0; l < 20; ++l) {
            if (array[l] != l) {
                return false;
            }
        }
        if (Cubie.settings.superGroup) {
            for (int n11 = 20; n11 < 23; ++n11) {
                if (array[n11] != 0) {
                    return false;
                }
            }
        }
        if (b) {
            return true;
        }
        if (!super.prepared) {
            return false;
        }
        for (int n12 = 0; n12 < 8; ++n12) {
            if (cubePosition.cubeletPerm[n12] == 0) {
                array[0] = n12;
            }
        }
        for (int n13 = 8; n13 < 20; ++n13) {
            if (cubePosition.cubeletPerm[n13] == 8) {
                array[1] = n13 - 8;
            }
            else if (cubePosition.cubeletPerm[n13] == 9) {
                array[2] = n13 - 8;
            }
            else if (cubePosition.cubeletPerm[n13] == 12) {
                array[3] = n13 - 8;
            }
        }
        array[4] = cubePosition.cubeletOri[8] * 2 + cubePosition.cubeletOri[9];
        if (Cubie.settings.superGroup) {
            array[5] = (cubePosition.cubeletOri[20] << 4) + (cubePosition.cubeletOri[21] << 2) + cubePosition.cubeletOri[22];
        }
        else {
            array[5] = -1;
        }
        if (super.positionlist == null) {
            super.positionlist = new int[40][6];
            final boolean b2 = false;
            super.sollen = (b2 ? 1 : 0);
            this.maxdepth = (b2 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        else if (super.positionlist[0][0] != array[0] || super.positionlist[0][1] != array[1] || super.positionlist[0][2] != array[2] || super.positionlist[0][3] != array[3] || super.positionlist[0][4] != array[4] || super.positionlist[0][5] != array[5]) {
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
        super.positionlist[0][5] = array[5];
        return true;
    }
    
    protected void init() {
        this.transFace = new int[3][64];
        this.prune = new byte[8][12][12][12][4];
        this.pruneFace = new byte[64];
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    this.transFace[0][n] = ((i + 3 & 0x3) << 4) + (j << 2) + k;
                    this.transFace[1][n] = (i << 4) + ((j + 3 & 0x3) << 2) + k;
                    this.transFace[2][n] = (i << 4) + (j << 2) + (k + 3 & 0x3);
                    this.pruneFace[n] = 1;
                    if (i != 0) {
                        final byte[] pruneFace = this.pruneFace;
                        final int n2 = n;
                        ++pruneFace[n2];
                    }
                    if (j != 0) {
                        final byte[] pruneFace2 = this.pruneFace;
                        final int n3 = n;
                        ++pruneFace2[n3];
                    }
                    if (k != 0) {
                        final byte[] pruneFace3 = this.pruneFace;
                        final int n4 = n;
                        ++pruneFace3[n4];
                    }
                    ++n;
                }
            }
        }
        byte b = 1;
        this.prune[0][0][1][4][0] = 1;
        int l;
        do {
            l = 0;
            for (int n5 = 0; n5 < 8; ++n5) {
                for (int n6 = 0; n6 < 12; ++n6) {
                    for (int n7 = 0; n7 < 12; ++n7) {
                        for (int n8 = 0; n8 < 12; ++n8) {
                            for (int n9 = 0; n9 < 4; ++n9) {
                                if (this.prune[n5][n6][n7][n8][n9] == b) {
                                    for (int n10 = 0; n10 < 3; ++n10) {
                                        int n11 = n5;
                                        int n12 = n6;
                                        int n13 = n7;
                                        int n14 = n8;
                                        int n15 = n9;
                                        for (int n16 = 0; n16 < 3; ++n16) {
                                            n11 = this.transCorn[n10][n11];
                                            n12 = this.transEdge[n10][n12];
                                            n13 = this.transEdge[n10][n13];
                                            n14 = this.transEdge[n10][n14];
                                            n15 = this.transOri[n10][n15];
                                            if (this.prune[n11][n12][n13][n14][n15] == 0) {
                                                this.prune[n11][n12][n13][n14][n15] = (byte)(b + 1);
                                                ++l;
                                            }
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
        if (this.maxdepth == 0 && super.sollen == 0 && super.positionlist[0][0] == 0 && super.positionlist[0][1] == 0 && super.positionlist[0][2] == 1 && super.positionlist[0][3] == 4 && super.positionlist[0][4] == 0 && super.positionlist[0][5] <= 0) {
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
                super.positionlist[sollen][4] = this.transOri[n][super.positionlist[sollen][4]];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][5] = this.transFace[n][super.positionlist[sollen][5]];
                }
            }
            else {
                super.positionlist[sollen][0] = super.positionlist[super.sollen][0];
                super.positionlist[sollen][1] = super.positionlist[super.sollen][1];
                super.positionlist[sollen][2] = super.positionlist[super.sollen][2];
                super.positionlist[sollen][3] = super.positionlist[super.sollen][3];
                super.positionlist[sollen][4] = super.positionlist[super.sollen][4];
                super.positionlist[sollen][5] = super.positionlist[super.sollen][5];
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
                if (super.sollen + this.prune[super.positionlist[sollen][0]][super.positionlist[sollen][1]][super.positionlist[sollen][2]][super.positionlist[sollen][3]][super.positionlist[sollen][4]] < this.maxdepth + 1 && (!Cubie.settings.superGroup || super.sollen + this.pruneFace[super.positionlist[sollen][5]] < this.maxdepth + 1)) {
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

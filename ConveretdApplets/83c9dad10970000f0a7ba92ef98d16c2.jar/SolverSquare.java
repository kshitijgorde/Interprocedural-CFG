import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class SolverSquare extends Solver
{
    int[][][] transEdge;
    int[][] transCorn;
    int[][] transCorn2;
    byte[][][] pruneEdge;
    byte[] pruneCorn;
    byte[] pruneCorn2;
    int maxdepth;
    final int[][] orbits;
    
    public SolverSquare(final ActionListener actionListener) {
        super(actionListener);
        this.orbits = new int[][] { { 8, 16, 18, 10 }, { 12, 13, 14, 15 }, { 9, 17, 19, 11 }, { 0, 2, 5, 7 }, { 1, 3, 4, 6 } };
    }
    
    public MoveSequence getGenerator() {
        final int[] array = new int[super.sollen];
        for (int i = 0; i < super.sollen; ++i) {
            array[i] = 2;
        }
        return new MoveSequence(super.sollen, super.solmoves, array);
    }
    
    public void mix(final CubePosition cubePosition) {
        cubePosition.reset();
        final int n = (int)(4.0 * Math.random());
        if (n != 3) {
            cubePosition.doMove(n, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(n, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        final int n2 = (int)(3.0 + 3.0 * Math.random());
        if (n2 != 5) {
            cubePosition.doMove(n2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(n2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        if ((int)(2.0 * Math.random()) != 0) {
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(5, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        final int n3 = (int)(4.0 * Math.random());
        if (n3 == 0) {
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        else if (n3 == 1) {
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        else if (n3 == 2) {
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(2, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        final int[] array = new int[4];
        for (int i = 0; i < 3; ++i) {
            this.num2perm(array, 0, 4, (int)(4.0 * Math.random()));
            for (int j = 0; j < 4; ++j) {
                cubePosition.cubeletPerm[this.orbits[i][j]] = this.orbits[i][array[j]];
            }
        }
        if (this.parityOdd(cubePosition.cubeletPerm, 8, 12)) {
            this.swap(cubePosition.cubeletPerm, 8, 10);
        }
        final int[] array2 = new int[20];
        int n4 = 0;
        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 4; ++l) {
                int n5;
                for (n5 = 0; n5 < 4 && cubePosition.cubeletPerm[this.orbits[k][l]] != this.orbits[k][n5]; ++n5) {}
                array2[n4++] = n5;
            }
        }
        int n6 = 0;
        for (int n7 = 0; n7 < 6; ++n7) {
            cubePosition.cubeletOri[20 + n7] = 0;
        }
        if (this.parityOdd(array2, 12, 4)) {
            n6 ^= 0x7;
        }
        if (this.parityOdd(array2, 0, 4)) {
            n6 ^= 0x6;
        }
        if (this.parityOdd(array2, 4, 4)) {
            n6 ^= 0x5;
        }
        if (this.parityOdd(array2, 8, 4)) {
            n6 ^= 0x3;
        }
        if ((n6 & 0x4) != 0x0) {
            cubePosition.cubeletOri[20] = 2;
        }
        if ((n6 & 0x2) != 0x0) {
            cubePosition.cubeletOri[21] = 2;
        }
        if ((n6 & 0x1) != 0x0) {
            cubePosition.cubeletOri[22] = 2;
        }
        final int n8 = (int)(8.0 * Math.random());
        if ((n8 & 0x4) != 0x0) {
            final int[] cubeletOri = cubePosition.cubeletOri;
            final int n9 = 20;
            cubeletOri[n9] ^= 0x2;
            final int[] cubeletOri2 = cubePosition.cubeletOri;
            final int n10 = 23;
            cubeletOri2[n10] ^= 0x2;
        }
        if ((n8 & 0x2) != 0x0) {
            final int[] cubeletOri3 = cubePosition.cubeletOri;
            final int n11 = 21;
            cubeletOri3[n11] ^= 0x2;
            final int[] cubeletOri4 = cubePosition.cubeletOri;
            final int n12 = 24;
            cubeletOri4[n12] ^= 0x2;
        }
        if ((n8 & 0x1) != 0x0) {
            final int[] cubeletOri5 = cubePosition.cubeletOri;
            final int n13 = 22;
            cubeletOri5[n13] ^= 0x2;
            final int[] cubeletOri6 = cubePosition.cubeletOri;
            final int n14 = 25;
            cubeletOri6[n14] ^= 0x2;
        }
    }
    
    public boolean setPosition(final CubePosition cubePosition, final boolean b) {
        final int[] array = new int[20];
        if (this.parityOdd(cubePosition.cubeletPerm, 0, 20)) {
            return false;
        }
        for (int i = 0; i < 20; ++i) {
            if (cubePosition.cubeletOri[i] != 0) {
                return false;
            }
        }
        int n = 0;
        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 4; ++k) {
                int n2;
                for (n2 = 0; n2 < 4 && cubePosition.cubeletPerm[this.orbits[j][k]] != this.orbits[j][n2]; ++n2) {}
                if (n2 >= 4) {
                    return false;
                }
                array[n++] = n2;
            }
        }
        if (this.parityOdd(array, 12, 4) != this.parityOdd(array, 16, 4)) {
            return false;
        }
        if (Cubie.settings.superGroup) {
            for (int l = 0; l < 6; ++l) {
                if ((cubePosition.cubeletOri[20 + l] & 0x1) != 0x0) {
                    return false;
                }
            }
            int n3 = 0;
            if (this.parityOdd(array, 12, 4)) {
                n3 ^= 0x7;
            }
            if (this.parityOdd(array, 0, 4)) {
                n3 ^= 0x6;
            }
            if (this.parityOdd(array, 4, 4)) {
                n3 ^= 0x5;
            }
            if (this.parityOdd(array, 8, 4)) {
                n3 ^= 0x3;
            }
            if (cubePosition.cubeletOri[20] != cubePosition.cubeletOri[23]) {
                n3 ^= 0x4;
            }
            if (cubePosition.cubeletOri[21] != cubePosition.cubeletOri[24]) {
                n3 ^= 0x2;
            }
            if (cubePosition.cubeletOri[22] != cubePosition.cubeletOri[25]) {
                n3 ^= 0x1;
            }
            if (n3 != 0) {
                return false;
            }
        }
        final int[] array2 = { this.perm2num(array, 0, 4), this.perm2num(array, 4, 4), this.perm2num(array, 8, 4), this.perm2num(array, 12, 4) };
        int n4;
        for (n4 = 0; n4 < 4 && cubePosition.cubeletPerm[this.orbits[4][n4]] != this.orbits[4][0]; ++n4) {}
        array2[3] = array2[3] * 4 + n4;
        if (Cubie.settings.superGroup) {
            int n5 = 0;
            if (cubePosition.cubeletOri[20] != 0) {
                n5 ^= 0x4;
            }
            if (cubePosition.cubeletOri[21] != 0) {
                n5 ^= 0x2;
            }
            if (cubePosition.cubeletOri[22] != 0) {
                n5 ^= 0x1;
            }
            array2[3] = (array2[3] << 3) + n5;
        }
        if (array[13] == 0) {
            this.swap(array, 12, 13);
            this.swap(array, 14, 15);
        }
        else if (array[14] == 0) {
            this.swap(array, 12, 14);
            this.swap(array, 13, 15);
        }
        else if (array[15] == 0) {
            this.swap(array, 12, 15);
            this.swap(array, 13, 14);
        }
        if (array[14] == 1) {
            this.swap(array, 13, 14);
            this.swap(array, 16, 19);
        }
        else if (array[15] == 1) {
            this.swap(array, 13, 15);
            this.swap(array, 17, 19);
        }
        if (array[15] == 2) {
            this.swap(array, 14, 15);
            this.swap(array, 18, 19);
        }
        if (array[17] == 0) {
            this.swap(array, 16, 17);
            this.swap(array, 18, 19);
        }
        else if (array[18] == 0) {
            this.swap(array, 16, 18);
            this.swap(array, 17, 19);
        }
        else if (array[19] == 0) {
            this.swap(array, 16, 19);
            this.swap(array, 17, 18);
        }
        if (array[17] != 1 || array[18] != 2 || array[19] != 3) {
            return false;
        }
        if (b) {
            return true;
        }
        if (!super.prepared) {
            return false;
        }
        if (super.positionlist == null) {
            super.positionlist = new int[40][4];
            final boolean b2 = false;
            super.sollen = (b2 ? 1 : 0);
            this.maxdepth = (b2 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        else if (super.positionlist[0][0] != array2[0] || super.positionlist[0][1] != array2[1] || super.positionlist[0][2] != array2[2] || super.positionlist[0][3] != array2[3]) {
            final boolean b3 = false;
            super.sollen = (b3 ? 1 : 0);
            this.maxdepth = (b3 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        super.positionlist[0][0] = array2[0];
        super.positionlist[0][1] = array2[1];
        super.positionlist[0][2] = array2[2];
        super.positionlist[0][3] = array2[3];
        return true;
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
        if (this.maxdepth == 0 && super.sollen == 0 && super.positionlist[0][0] == 0 && super.positionlist[0][1] == 0 && super.positionlist[0][2] == 0 && super.positionlist[0][3] == 0) {
            return true;
        }
        while (super.sollen >= 0) {
            final int sollen = super.sollen + 1;
            final int n = super.solmoves[super.sollen];
            if (n >= 0) {
                super.positionlist[sollen][0] = this.transEdge[0][super.positionlist[sollen][0]][n];
                super.positionlist[sollen][1] = this.transEdge[1][super.positionlist[sollen][1]][n];
                super.positionlist[sollen][2] = this.transEdge[2][super.positionlist[sollen][2]][n];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][3] = this.transCorn2[super.positionlist[sollen][3]][n];
                }
                else {
                    super.positionlist[sollen][3] = this.transCorn[super.positionlist[sollen][3]][n];
                }
            }
            else {
                super.positionlist[sollen][0] = super.positionlist[super.sollen][0];
                super.positionlist[sollen][1] = super.positionlist[super.sollen][1];
                super.positionlist[sollen][2] = super.positionlist[super.sollen][2];
                super.positionlist[sollen][3] = super.positionlist[super.sollen][3];
            }
            final int[] solamount = super.solamount;
            final int sollen2 = super.sollen;
            solamount[sollen2] += 2;
            if (super.solamount[super.sollen] > 3) {
                super.solamount[super.sollen] = 0;
                do {
                    final int[] solmoves = super.solmoves;
                    final int sollen3 = super.sollen;
                    ++solmoves[sollen3];
                } while (super.sollen != 0 && (super.solmoves[super.sollen] == super.solmoves[super.sollen - 1] || super.solmoves[super.sollen] == super.solmoves[super.sollen - 1] + 3));
                if (super.solmoves[super.sollen] < 6) {
                    continue;
                }
                --super.sollen;
            }
            else {
                if (super.sollen + this.pruneEdge[super.positionlist[sollen][0]][super.positionlist[sollen][1]][super.positionlist[sollen][2]] < this.maxdepth + 1 && (Cubie.settings.superGroup || super.sollen + this.pruneCorn[super.positionlist[sollen][3]] < this.maxdepth + 1) && (!Cubie.settings.superGroup || super.sollen + this.pruneCorn2[super.positionlist[sollen][3]] < this.maxdepth + 1)) {
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
    
    protected void init() {
        this.transEdge = new int[3][24][6];
        this.transCorn = new int[96][6];
        this.transCorn2 = new int[768][6];
        this.pruneEdge = new byte[24][24][24];
        this.pruneCorn = new byte[96];
        this.pruneCorn2 = new byte[768];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 24; ++j) {
                for (int k = 0; k < 6; ++k) {
                    this.transEdge[i][j][k] = this.gettransEdge(i, j, k);
                }
            }
        }
        for (int l = 0; l < 96; ++l) {
            for (int n = 0; n < 6; ++n) {
                this.transCorn[l][n] = this.gettransCorn(l, n);
            }
        }
        for (int n2 = 0; n2 < 768; ++n2) {
            for (int n3 = 0; n3 < 6; ++n3) {
                this.transCorn2[n2][n3] = this.gettransCorn2(n2, n3);
            }
        }
        byte b = 1;
        this.pruneCorn[0] = 1;
        int n4;
        do {
            n4 = 0;
            for (int n5 = 0; n5 < 96; ++n5) {
                if (this.pruneCorn[n5] == b) {
                    for (int n6 = 0; n6 < 6; ++n6) {
                        if (this.pruneCorn[this.transCorn[n5][n6]] == 0) {
                            this.pruneCorn[this.transCorn[n5][n6]] = (byte)(b + 1);
                            ++n4;
                        }
                    }
                }
            }
            ++b;
        } while (n4 != 0);
        byte b2 = 1;
        this.pruneCorn2[0] = 1;
        int n7;
        do {
            n7 = 0;
            for (int n8 = 0; n8 < 768; ++n8) {
                if (this.pruneCorn2[n8] == b2) {
                    for (int n9 = 0; n9 < 6; ++n9) {
                        if (this.pruneCorn2[this.transCorn2[n8][n9]] == 0) {
                            this.pruneCorn2[this.transCorn2[n8][n9]] = (byte)(b2 + 1);
                            ++n7;
                        }
                    }
                }
            }
            ++b2;
        } while (n7 != 0);
        byte b3 = 1;
        this.pruneEdge[0][0][0] = 1;
        int n10;
        do {
            n10 = 0;
            for (int n11 = 0; n11 < 24; ++n11) {
                for (int n12 = 0; n12 < 24; ++n12) {
                    for (int n13 = 0; n13 < 24; ++n13) {
                        if (this.pruneEdge[n11][n12][n13] == b3) {
                            for (int n14 = 0; n14 < 6; ++n14) {
                                if (this.pruneEdge[this.transEdge[0][n11][n14]][this.transEdge[1][n12][n14]][this.transEdge[2][n13][n14]] == 0) {
                                    this.pruneEdge[this.transEdge[0][n11][n14]][this.transEdge[1][n12][n14]][this.transEdge[2][n13][n14]] = (byte)(b3 + 1);
                                    ++n10;
                                }
                            }
                        }
                    }
                }
            }
            ++b3;
        } while (n10 != 0);
    }
    
    private int gettransEdge(final int n, final int n2, final int n3) {
        final int[] array = { 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3 };
        this.num2perm(array, n * 4, 4, n2);
        if (n3 == 0) {
            this.swap(array, 2, 3);
            this.swap(array, 6, 7);
        }
        else if (n3 == 1) {
            this.swap(array, 0, 3);
            this.swap(array, 8, 11);
        }
        else if (n3 == 2) {
            this.swap(array, 5, 6);
            this.swap(array, 8, 9);
        }
        else if (n3 == 3) {
            this.swap(array, 0, 1);
            this.swap(array, 4, 5);
        }
        else if (n3 == 4) {
            this.swap(array, 1, 2);
            this.swap(array, 9, 10);
        }
        else if (n3 == 5) {
            this.swap(array, 4, 7);
            this.swap(array, 10, 11);
        }
        return this.perm2num(array, n * 4, 4);
    }
    
    private int gettransCorn(final int n, final int n2) {
        final int[] array = { 0, 1, 2, 3, 0, 0, 0, 0 };
        this.num2perm(array, 0, 4, n >> 2);
        array[4 + (n & 0x3)] = 1;
        if (n2 == 0) {
            this.swap(array, 1, 3);
            this.swap(array, 5, 7);
        }
        else if (n2 == 1) {
            this.swap(array, 0, 1);
            this.swap(array, 4, 5);
        }
        else if (n2 == 2) {
            this.swap(array, 1, 2);
            this.swap(array, 4, 7);
        }
        else if (n2 == 3) {
            this.swap(array, 0, 2);
            this.swap(array, 4, 6);
        }
        else if (n2 == 4) {
            this.swap(array, 2, 3);
            this.swap(array, 6, 7);
        }
        else if (n2 == 5) {
            this.swap(array, 0, 3);
            this.swap(array, 5, 6);
        }
        return this.perm2num(array, 0, 4) * 4 + array[5] + array[6] * 2 + array[7] * 3;
    }
    
    private int gettransCorn2(final int n, final int n2) {
        final int[] array = { 0, 1, 2, 3, 0, 0, 0, 0 };
        this.num2perm(array, 0, 4, n >> 5);
        array[4 + (n >> 3 & 0x3)] = 1;
        int n3 = n & 0x7;
        if (n2 == 0) {
            this.swap(array, 1, 3);
            this.swap(array, 5, 7);
            n3 ^= 0x4;
        }
        else if (n2 == 1) {
            this.swap(array, 0, 1);
            this.swap(array, 4, 5);
            n3 ^= 0x2;
        }
        else if (n2 == 2) {
            this.swap(array, 1, 2);
            this.swap(array, 4, 7);
            n3 ^= 0x1;
        }
        else if (n2 == 3) {
            this.swap(array, 0, 2);
            this.swap(array, 4, 6);
        }
        else if (n2 == 4) {
            this.swap(array, 2, 3);
            this.swap(array, 6, 7);
        }
        else if (n2 == 5) {
            this.swap(array, 0, 3);
            this.swap(array, 5, 6);
        }
        return (this.perm2num(array, 0, 4) << 5) + (array[5] + array[6] * 2 + array[7] * 3 << 3) + n3;
    }
}

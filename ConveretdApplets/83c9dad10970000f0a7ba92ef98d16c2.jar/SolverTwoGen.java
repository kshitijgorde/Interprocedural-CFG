import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class SolverTwoGen extends Solver
{
    int maxdepth;
    int[][] transCorn;
    int[][] transEdge;
    int[][] transOri;
    int[][] transOri2;
    byte[] pruneCorn;
    byte[] pruneEdge;
    byte[] pruneOri;
    byte[] pruneOri2;
    
    public SolverTwoGen(final ActionListener actionListener) {
        super(actionListener);
    }
    
    public MoveSequence getGenerator() {
        final int[] array = new int[super.sollen];
        for (int i = 0; i < super.sollen; ++i) {
            array[i] = super.solmoves[i] * 2 + 1;
        }
        return new MoveSequence(super.sollen, array, super.solamount);
    }
    
    public void mix(final CubePosition cubePosition) {
        cubePosition.reset();
        int i = (int)(6.0 * Math.random());
        if (i == 4) {
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        else if (i < 4) {
            while (i > 0) {
                cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
                --i;
            }
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        int j = (int)(5.0 * Math.random());
        if (j < 4) {
            while (j > 0) {
                cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
                --j;
            }
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
            cubePosition.doMove(3, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        for (int k = (int)(4.0 * Math.random()); k > 0; --k) {
            cubePosition.doMove(1, cubePosition.cubeletPerm, cubePosition.cubeletOri, cubePosition.faceOri);
        }
        this.num2ori(cubePosition.cubeletOri, 0, 6, 3, (int)(243.0 * Math.random()));
        final int[] array = { 8, 9, 10, 11, 12, 13, 16 };
        final int[] array2 = new int[7];
        do {
            this.num2perm(array2, 0, 7, (int)(5040.0 * Math.random()));
        } while (this.parityOdd(array2, 0, 7) != this.parityOdd(cubePosition.cubeletPerm, 0, 6));
        for (int l = 0; l < 7; ++l) {
            cubePosition.cubeletPerm[array[l]] = array[array2[l]];
            int n = (l == 4 || l == 5) ? 1 : 0;
            if (array2[l] == 4 || array2[l] == 5) {
                n = 1 - n;
            }
            cubePosition.cubeletOri[array[l]] = n;
        }
        final int n2 = (int)(8.0 * Math.random());
        cubePosition.cubeletOri[21] = (n2 & 0x3);
        cubePosition.cubeletOri[23] = (n2 & 0x4) >> 1;
        if (this.parityOdd(cubePosition.cubeletPerm, 0, 6)) {
            final int[] cubeletOri = cubePosition.cubeletOri;
            final int n3 = 23;
            cubeletOri[n3] += 1 - (n2 & 0x1);
            return;
        }
        final int[] cubeletOri2 = cubePosition.cubeletOri;
        final int n4 = 23;
        cubeletOri2[n4] += (n2 & 0x1);
    }
    
    public boolean setPosition(final CubePosition cubePosition, final boolean b) {
        final int[] array = { 6, 7, 14, 15, 17, 18, 19 };
        for (int i = 0; i < 7; ++i) {
            if (cubePosition.cubeletOri[array[i]] != 0 || cubePosition.cubeletPerm[array[i]] != array[i]) {
                return false;
            }
        }
        int n = 0;
        for (int j = 0; j < 8; ++j) {
            n += cubePosition.cubeletOri[j];
            if (n > 2) {
                n -= 3;
            }
        }
        if (n != 0) {
            return false;
        }
        for (int k = 8; k < 20; ++k) {
            int n2 = cubePosition.cubeletOri[k];
            if (k >= 12 && k < 16) {
                n2 = 1 - n2;
            }
            if (cubePosition.cubeletPerm[k] >= 12 && cubePosition.cubeletPerm[k] < 16) {
                n2 = 1 - n2;
            }
            if (n2 != 0) {
                return false;
            }
        }
        if (this.parityOdd(cubePosition.cubeletPerm, 0, 20)) {
            return false;
        }
        if (Cubie.settings.superGroup) {
            if (cubePosition.cubeletOri[20] != 0 || cubePosition.cubeletOri[22] != 0 || cubePosition.cubeletOri[24] != 0 || cubePosition.cubeletOri[25] != 0) {
                return false;
            }
            if (this.parityOdd(cubePosition.cubeletPerm, 0, 8)) {
                if ((cubePosition.cubeletOri[21] & 0x1) == (cubePosition.cubeletOri[23] & 0x1)) {
                    return false;
                }
            }
            else if ((cubePosition.cubeletOri[21] & 0x1) != (cubePosition.cubeletOri[23] & 0x1)) {
                return false;
            }
        }
        final int[] array2 = new int[7];
        for (int l = 0; l < 6; ++l) {
            array2[l] = cubePosition.cubeletPerm[l];
        }
        if (array2[1] == 0) {
            this.cycle(array2, 1, 2, 3, 0);
        }
        else if (array2[2] == 0) {
            this.cycle(array2, 2, 1, 5, 0);
        }
        else if (array2[3] == 0) {
            this.cycle(array2, 3, 2, 1, 0);
        }
        else if (array2[4] == 0) {
            this.cycle(array2, 4, 5, 1, 0);
        }
        else if (array2[5] == 0) {
            this.cycle(array2, 5, 1, 2, 0);
        }
        if (array2[2] == 1) {
            this.cycle(array2, 2, 5, 4, 1);
        }
        else if (array2[3] == 1) {
            this.cycle(array2, 3, 2, 5, 1);
        }
        else if (array2[4] == 1) {
            this.cycle(array2, 4, 5, 2, 1);
        }
        else if (array2[5] == 1) {
            this.cycle(array2, 5, 2, 3, 1);
        }
        while (array2[3] == 2 || array2[4] == 2 || array2[5] == 2) {
            this.cycle(array2, 2, 3, 5, 4);
        }
        if (array2[3] != 3 || array2[4] != 4 || array2[5] != 5) {
            return false;
        }
        if (b) {
            return true;
        }
        if (!super.prepared) {
            return false;
        }
        final int[] array3 = new int[3];
        for (int n3 = 0; n3 < 6; ++n3) {
            array2[n3] = cubePosition.cubeletPerm[n3];
        }
        array3[0] = this.perm2num(array2, 0, 6);
        final int[] array4 = { 8, 9, 10, 11, 12, 13, 16 };
        for (int n4 = 0; n4 < 7; ++n4) {
            array2[n4] = cubePosition.cubeletPerm[array4[n4]];
        }
        array3[1] = this.perm2num(array2, 0, 7);
        array3[2] = 0;
        for (int n5 = 4; n5 >= 0; --n5) {
            array3[2] = array3[2] * 3 + cubePosition.cubeletOri[n5];
        }
        if (Cubie.settings.superGroup) {
            array3[2] = (array3[2] << 4) + (cubePosition.cubeletOri[23] << 2) + cubePosition.cubeletOri[21];
        }
        if (super.positionlist == null) {
            super.positionlist = new int[40][3];
            final boolean b2 = false;
            super.sollen = (b2 ? 1 : 0);
            this.maxdepth = (b2 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        else if (super.positionlist[0][0] != array3[0] || super.positionlist[0][1] != array3[1] || super.positionlist[0][2] != array3[2]) {
            final boolean b3 = false;
            super.sollen = (b3 ? 1 : 0);
            this.maxdepth = (b3 ? 1 : 0);
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        super.positionlist[0][0] = array3[0];
        super.positionlist[0][1] = array3[1];
        super.positionlist[0][2] = array3[2];
        return true;
    }
    
    protected void init() {
        this.transEdge = new int[5040][2];
        this.transCorn = new int[720][2];
        this.transOri = new int[243][2];
        this.transOri2 = new int[3888][2];
        this.pruneEdge = new byte[5040];
        this.pruneCorn = new byte[720];
        this.pruneOri = new byte[243];
        this.pruneOri2 = new byte[3888];
        for (int i = 0; i < 5040; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.transEdge[i][j] = this.gettransEdge(i, j);
            }
        }
        for (int k = 0; k < 720; ++k) {
            for (int l = 0; l < 2; ++l) {
                this.transCorn[k][l] = this.gettransCorn(k, l);
            }
        }
        for (int n = 0; n < 243; ++n) {
            for (int n2 = 0; n2 < 2; ++n2) {
                this.transOri[n][n2] = this.gettransOri(n, n2);
            }
        }
        for (int n3 = 0; n3 < 3888; ++n3) {
            for (int n4 = 0; n4 < 2; ++n4) {
                this.transOri2[n3][n4] = this.gettransOri2(n3, n4);
            }
        }
        byte b = 1;
        this.pruneEdge[0] = 1;
        int n5;
        do {
            n5 = 0;
            for (int n6 = 0; n6 < 5040; ++n6) {
                if (this.pruneEdge[n6] == b) {
                    for (int n7 = 0; n7 < 2; ++n7) {
                        int n8 = n6;
                        for (int n9 = 0; n9 < 3; ++n9) {
                            n8 = this.transEdge[n8][n7];
                            if (this.pruneEdge[n8] == 0) {
                                this.pruneEdge[n8] = (byte)(b + 1);
                                ++n5;
                            }
                        }
                    }
                }
            }
            ++b;
        } while (n5 != 0);
        byte b2 = 1;
        this.pruneCorn[0] = 1;
        int n10;
        do {
            n10 = 0;
            for (int n11 = 0; n11 < 720; ++n11) {
                if (this.pruneCorn[n11] == b2) {
                    for (int n12 = 0; n12 < 2; ++n12) {
                        int n13 = n11;
                        for (int n14 = 0; n14 < 3; ++n14) {
                            n13 = this.transCorn[n13][n12];
                            if (this.pruneCorn[n13] == 0) {
                                this.pruneCorn[n13] = (byte)(b2 + 1);
                                ++n10;
                            }
                        }
                    }
                }
            }
            ++b2;
        } while (n10 != 0);
        byte b3 = 1;
        this.pruneOri[0] = 1;
        int n15;
        do {
            n15 = 0;
            for (int n16 = 0; n16 < 243; ++n16) {
                if (this.pruneOri[n16] == b3) {
                    for (int n17 = 0; n17 < 2; ++n17) {
                        int n18 = n16;
                        for (int n19 = 0; n19 < 3; ++n19) {
                            n18 = this.transOri[n18][n17];
                            if (this.pruneOri[n18] == 0) {
                                this.pruneOri[n18] = (byte)(b3 + 1);
                                ++n15;
                            }
                        }
                    }
                }
            }
            ++b3;
        } while (n15 != 0);
        byte b4 = 1;
        this.pruneOri2[0] = 1;
        int n20;
        do {
            n20 = 0;
            for (int n21 = 0; n21 < 3888; ++n21) {
                if (this.pruneOri2[n21] == b4) {
                    for (int n22 = 0; n22 < 2; ++n22) {
                        int n23 = n21;
                        for (int n24 = 0; n24 < 3; ++n24) {
                            n23 = this.transOri2[n23][n22];
                            if (this.pruneOri2[n23] == 0) {
                                this.pruneOri2[n23] = (byte)(b4 + 1);
                                ++n20;
                            }
                        }
                    }
                }
            }
            ++b4;
        } while (n20 != 0);
    }
    
    private int gettransEdge(final int n, final int n2) {
        final int[] array = new int[7];
        this.num2perm(array, 0, 7, n);
        if (n2 == 0) {
            this.cycle(array, 0, 3, 2, 1);
        }
        else if (n2 == 1) {
            this.cycle(array, 0, 5, 6, 4);
        }
        return this.perm2num(array, 0, 7);
    }
    
    private int gettransCorn(final int n, final int n2) {
        final int[] array = new int[6];
        this.num2perm(array, 0, 6, n);
        if (n2 == 0) {
            this.cycle(array, 0, 3, 2, 1);
        }
        else if (n2 == 1) {
            this.cycle(array, 0, 1, 5, 4);
        }
        return this.perm2num(array, 0, 6);
    }
    
    private int gettransOri(final int n, final int n2) {
        final int[] array = new int[6];
        this.num2ori(array, 0, 6, 3, n);
        if (n2 == 0) {
            this.cycle(array, 0, 3, 2, 1);
        }
        else if (n2 == 1) {
            this.cycle(array, 0, 1, 5, 4);
            final int[] array2 = array;
            final int n3 = 0;
            array2[n3] += 2;
            final int[] array3 = array;
            final int n4 = 1;
            ++array3[n4];
            final int[] array4 = array;
            final int n5 = 5;
            array4[n5] += 2;
            final int[] array5 = array;
            final int n6 = 4;
            ++array5[n6];
        }
        return this.ori2num(array, 0, 6, 3);
    }
    
    private int gettransOri2(final int n, final int n2) {
        final int[] array = new int[8];
        this.num2ori(array, 0, 6, 3, n >> 4);
        array[6] = (n & 0x3);
        array[7] = (n >> 2 & 0x3);
        if (n2 == 0) {
            this.cycle(array, 0, 3, 2, 1);
            array[6] = (array[6] + 3 & 0x3);
        }
        else if (n2 == 1) {
            this.cycle(array, 0, 1, 5, 4);
            final int[] array2 = array;
            final int n3 = 0;
            array2[n3] += 2;
            final int[] array3 = array;
            final int n4 = 1;
            ++array3[n4];
            final int[] array4 = array;
            final int n5 = 5;
            array4[n5] += 2;
            final int[] array5 = array;
            final int n6 = 4;
            ++array5[n6];
            array[7] = (array[7] + 3 & 0x3);
        }
        return (this.ori2num(array, 0, 6, 3) << 4) + (array[7] << 2) + array[6];
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
        if (this.maxdepth == 0 && super.sollen == 0 && super.positionlist[0][0] == 0 && super.positionlist[0][1] == 0 && super.positionlist[0][2] == 0) {
            return true;
        }
        while (super.sollen >= 0) {
            final int sollen = super.sollen + 1;
            final int n = super.solmoves[super.sollen];
            if (n >= 0) {
                super.positionlist[sollen][0] = this.transCorn[super.positionlist[sollen][0]][n];
                super.positionlist[sollen][1] = this.transEdge[super.positionlist[sollen][1]][n];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][2] = this.transOri2[super.positionlist[sollen][2]][n];
                }
                else {
                    super.positionlist[sollen][2] = this.transOri[super.positionlist[sollen][2]][n];
                }
            }
            else {
                super.positionlist[sollen][0] = super.positionlist[super.sollen][0];
                super.positionlist[sollen][1] = super.positionlist[super.sollen][1];
                super.positionlist[sollen][2] = super.positionlist[super.sollen][2];
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
                if (super.solmoves[super.sollen] < 2) {
                    continue;
                }
                --super.sollen;
            }
            else {
                if (super.sollen + this.pruneCorn[super.positionlist[sollen][0]] < this.maxdepth + 1 && super.sollen + this.pruneEdge[super.positionlist[sollen][1]] < this.maxdepth + 1 && (Cubie.settings.superGroup || super.sollen + this.pruneOri[super.positionlist[sollen][2]] < this.maxdepth + 1) && (!Cubie.settings.superGroup || super.sollen + this.pruneOri2[super.positionlist[sollen][2]] < this.maxdepth + 1)) {
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

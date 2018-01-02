import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class SolverKociemba extends Solver
{
    int[][] transEdgeOri;
    int[][] transCornOri;
    int[][] transCornPerm;
    int[][] transSliceFull;
    int[][] transEdgePerm;
    int[][] transSlicePerm;
    int[][] transChoice;
    int[][] transFace;
    byte[] pruneEdgeOri;
    byte[] pruneCornOri;
    byte[] pruneChoice;
    byte[] pruneCornPerm;
    byte[] pruneEdgePerm;
    byte[] pruneSlicePerm;
    byte[] pruneFace1;
    byte[] pruneFace2;
    int phase1len;
    int phase2len;
    int maxdepth;
    
    public SolverKociemba(final ActionListener actionListener) {
        super(actionListener);
        this.maxdepth = 25;
    }
    
    public MoveSequence getGenerator() {
        return new MoveSequence(super.sollen, super.solmoves, super.solamount);
    }
    
    public void mix(final CubePosition cubePosition) {
        cubePosition.reset();
        do {
            this.num2perm(cubePosition.cubeletPerm, 0, 8, (int)(40320.0 * Math.random()));
            this.num2perm(cubePosition.cubeletPerm, 8, 12, (int)(4.790016E8 * Math.random()));
            for (int i = 8; i < 20; ++i) {
                final int[] cubeletPerm = cubePosition.cubeletPerm;
                final int n = i;
                cubeletPerm[n] += 8;
            }
        } while (this.parityOdd(cubePosition.cubeletPerm, 0, 20));
        this.num2ori(cubePosition.cubeletOri, 0, 8, 3, (int)(2187.0 * Math.random()));
        this.num2ori(cubePosition.cubeletOri, 8, 12, 2, (int)(2048.0 * Math.random()));
        this.num2ori(cubePosition.cubeletOri, 20, 6, 4, (int)(1024.0 * Math.random()));
        if (Math.random() < 0.5) {
            final int[] cubeletOri = cubePosition.cubeletOri;
            final int n2 = 25;
            cubeletOri[n2] ^= 0x2;
        }
        if (this.parityOdd(cubePosition.cubeletPerm, 0, 8)) {
            final int[] cubeletOri2 = cubePosition.cubeletOri;
            final int n3 = 25;
            cubeletOri2[n3] ^= 0x1;
        }
    }
    
    public boolean setPosition(final CubePosition cubePosition, final boolean b) {
        int n = 0;
        for (int i = 0; i < 8; ++i) {
            n += cubePosition.cubeletOri[i];
            if (n > 2) {
                n -= 3;
            }
        }
        if (n != 0) {
            return false;
        }
        int n2 = 0;
        for (int j = 8; j < 20; ++j) {
            n2 += cubePosition.cubeletOri[j];
            if (n2 > 1) {
                n2 -= 2;
            }
        }
        if (n2 != 0) {
            return false;
        }
        if (Cubie.settings.superGroup) {
            boolean parityOdd = this.parityOdd(cubePosition.cubeletPerm, 0, 8);
            for (int k = 20; k < 26; ++k) {
                if ((cubePosition.cubeletOri[k] & 0x1) != 0x0) {
                    parityOdd = !parityOdd;
                }
            }
            if (parityOdd) {
                return false;
            }
        }
        if (this.parityOdd(cubePosition.cubeletPerm, 0, 20)) {
            return false;
        }
        if (b) {
            return true;
        }
        if (!super.prepared) {
            return false;
        }
        final int[] array = new int[8];
        array[0] = 0;
        for (int l = 10; l >= 0; --l) {
            array[0] = array[0] * 2 + cubePosition.cubeletOri[8 + l];
        }
        array[1] = 0;
        for (int n3 = 6; n3 >= 0; --n3) {
            array[1] = array[1] * 3 + cubePosition.cubeletOri[n3];
        }
        array[2] = this.perm2num(cubePosition.cubeletPerm, 0, 8);
        array[3] = this.partperm2num(cubePosition.cubeletPerm, 12, 8, 8, 4);
        array[4] = this.partperm2num(cubePosition.cubeletPerm, 12, 8, 12, 4);
        array[5] = this.partperm2num(cubePosition.cubeletPerm, 12, 8, 16, 4);
        array[6] = 0;
        for (int n4 = 10; n4 >= 0; --n4) {
            array[6] = array[6] * 2 + ((cubePosition.cubeletPerm[8 + n4] >= 12 && cubePosition.cubeletPerm[8 + n4] < 16) ? 1 : 0);
        }
        if (Cubie.settings.superGroup) {
            array[7] = 0;
            for (int n5 = 20; n5 < 26; ++n5) {
                array[7] = array[7] * 4 + cubePosition.cubeletOri[n5];
            }
        }
        else {
            array[7] = -1;
        }
        if (super.positionlist == null) {
            super.positionlist = new int[40][10];
            final boolean sollen = false;
            this.phase2len = (sollen ? 1 : 0);
            this.phase1len = (sollen ? 1 : 0);
            super.sollen = (sollen ? 1 : 0);
            this.maxdepth = 25;
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        else if (super.positionlist[0][0] != array[0] || super.positionlist[0][1] != array[1] || super.positionlist[0][2] != array[2] || super.positionlist[0][3] != array[3] || super.positionlist[0][4] != array[4] || super.positionlist[0][5] != array[5] || super.positionlist[0][6] != array[6] || super.positionlist[0][9] != array[7]) {
            final boolean sollen2 = false;
            this.phase2len = (sollen2 ? 1 : 0);
            this.phase1len = (sollen2 ? 1 : 0);
            super.sollen = (sollen2 ? 1 : 0);
            this.maxdepth = 25;
            super.solmoves[0] = -1;
            super.solamount[0] = 3;
        }
        super.positionlist[0][0] = array[0];
        super.positionlist[0][1] = array[1];
        super.positionlist[0][2] = array[2];
        super.positionlist[0][3] = array[3];
        super.positionlist[0][4] = array[4];
        super.positionlist[0][5] = array[5];
        super.positionlist[0][6] = array[6];
        super.positionlist[0][9] = array[7];
        return true;
    }
    
    protected boolean solve() {
        if (this.phase1len >= this.maxdepth) {
            return false;
        }
        System.out.println("p1 len=" + this.phase1len);
        while (!this.search1()) {
            if (super.wanttostop) {
                return false;
            }
            ++this.phase1len;
            if (this.phase1len >= this.maxdepth) {
                return false;
            }
            System.out.println("p1 len=" + this.phase1len);
        }
        this.maxdepth = super.sollen - 1;
        return true;
    }
    
    private boolean search1() {
        if (super.sollen >= this.phase1len) {
            if (super.positionlist[this.phase1len][0] == 0 && super.positionlist[this.phase1len][1] == 0 && super.positionlist[this.phase1len][6] == 240 && (!Cubie.settings.superGroup || (super.positionlist[this.phase1len][9] & 0x451) == 0x0) && this.solve2()) {
                return true;
            }
            super.sollen = this.phase1len;
        }
        while (super.sollen >= 0) {
            final int sollen = super.sollen + 1;
            final int n = super.solmoves[super.sollen];
            if (n >= 0) {
                super.positionlist[sollen][0] = this.transEdgeOri[super.positionlist[sollen][0]][n];
                super.positionlist[sollen][1] = this.transCornOri[super.positionlist[sollen][1]][n];
                super.positionlist[sollen][2] = this.transCornPerm[super.positionlist[sollen][2]][n];
                super.positionlist[sollen][3] = this.transSliceFull[super.positionlist[sollen][3]][n];
                super.positionlist[sollen][4] = this.transSliceFull[super.positionlist[sollen][4]][n];
                super.positionlist[sollen][5] = this.transSliceFull[super.positionlist[sollen][5]][n];
                super.positionlist[sollen][6] = this.transChoice[super.positionlist[sollen][6]][n];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][9] = this.transFace[super.positionlist[sollen][9]][n];
                }
            }
            else {
                super.positionlist[sollen][0] = super.positionlist[super.sollen][0];
                super.positionlist[sollen][1] = super.positionlist[super.sollen][1];
                super.positionlist[sollen][2] = super.positionlist[super.sollen][2];
                super.positionlist[sollen][3] = super.positionlist[super.sollen][3];
                super.positionlist[sollen][4] = super.positionlist[super.sollen][4];
                super.positionlist[sollen][5] = super.positionlist[super.sollen][5];
                super.positionlist[sollen][6] = super.positionlist[super.sollen][6];
                super.positionlist[sollen][9] = super.positionlist[super.sollen][9];
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
                } while (super.sollen != 0 && (super.solmoves[super.sollen] == super.solmoves[super.sollen - 1] || super.solmoves[super.sollen] == super.solmoves[super.sollen - 1] + 3));
                if (super.solmoves[super.sollen] < 6) {
                    continue;
                }
                --super.sollen;
            }
            else {
                if (super.sollen + this.pruneEdgeOri[super.positionlist[sollen][0]] < this.phase1len + 1 && super.sollen + this.pruneCornOri[super.positionlist[sollen][1]] < this.phase1len + 1 && super.sollen + this.pruneChoice[super.positionlist[sollen][6]] < this.phase1len + 1 && (!Cubie.settings.superGroup || super.sollen + this.pruneFace1[super.positionlist[sollen][9]] < this.phase1len + 1)) {
                    super.solmoves[sollen] = -1;
                    super.solamount[sollen] = 3;
                    super.sollen = sollen;
                    if (super.sollen >= this.phase1len && this.solve2()) {
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
    
    private boolean solve2() {
        if (super.sollen > 0 && (super.solamount[super.sollen - 1] == 2 || super.solmoves[super.sollen - 1] == 1 || super.solmoves[super.sollen - 1] == 4)) {
            return false;
        }
        if (this.phase1len + this.pruneCornPerm[super.positionlist[this.phase1len][2]] > this.maxdepth + 1 || (Cubie.settings.superGroup && this.phase1len + this.pruneFace2[super.positionlist[this.phase1len][9]] > this.maxdepth + 1)) {
            return false;
        }
        this.phase2len = super.sollen - this.phase1len;
        if (super.sollen > this.maxdepth) {
            this.phase2len = this.maxdepth - this.phase1len;
        }
        if (this.phase2len == 0) {
            if (super.positionlist[this.phase1len][2] == 0 && super.positionlist[this.phase1len][3] == 0 && super.positionlist[this.phase1len][4] == 5860 && super.positionlist[this.phase1len][5] == 11720 && super.positionlist[this.phase1len][9] <= 0) {
                return true;
            }
            final int[] array = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
            this.num2partperm(array, 0, 12, 4, 0, super.positionlist[this.phase1len][3]);
            this.num2partperm(array, 0, 12, 4, 4, super.positionlist[this.phase1len][4]);
            this.num2partperm(array, 0, 12, 4, 8, super.positionlist[this.phase1len][5]);
            if (Cubie.settings.superGroup) {
                boolean parityOdd = this.parityOdd(array, 4, 4);
                if ((super.positionlist[this.phase1len][9] & 0x2) != 0x0) {
                    parityOdd = !parityOdd;
                }
                if ((super.positionlist[this.phase1len][9] & 0x20) != 0x0) {
                    parityOdd = !parityOdd;
                }
                if ((super.positionlist[this.phase1len][9] & 0x80) != 0x0) {
                    parityOdd = !parityOdd;
                }
                if ((super.positionlist[this.phase1len][9] & 0x800) != 0x0) {
                    parityOdd = !parityOdd;
                }
                if (parityOdd) {
                    return false;
                }
            }
            super.positionlist[this.phase1len][8] = this.perm2num(array, 4, 4);
            array[4] = array[8];
            array[5] = array[9];
            array[6] = array[10];
            array[7] = array[11];
            super.positionlist[this.phase1len][7] = this.perm2num(array, 0, 8);
        }
        System.out.println("p2 len=" + this.phase2len);
        while (!this.search2()) {
            ++this.phase2len;
            if (this.phase1len + this.phase2len > this.maxdepth) {
                return false;
            }
            System.out.println("p2 len=" + this.phase2len);
        }
        return true;
    }
    
    private boolean search2() {
        while (super.sollen >= this.phase1len) {
            final int sollen = super.sollen + 1;
            final int n = super.solmoves[super.sollen];
            if (n == 1 || n == 4) {
                super.positionlist[sollen][2] = this.transCornPerm[super.positionlist[sollen][2]][n];
                super.positionlist[sollen][7] = this.transEdgePerm[super.positionlist[sollen][7]][n];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][9] = this.transFace[super.positionlist[sollen][9]][n];
                }
            }
            else if (n >= 0) {
                super.positionlist[sollen][2] = this.transCornPerm[this.transCornPerm[super.positionlist[sollen][2]][n]][n];
                super.positionlist[sollen][7] = this.transEdgePerm[super.positionlist[sollen][7]][n];
                super.positionlist[sollen][8] = this.transSlicePerm[super.positionlist[sollen][8]][n];
                if (Cubie.settings.superGroup) {
                    super.positionlist[sollen][9] = this.transFace[this.transFace[super.positionlist[sollen][9]][n]][n];
                }
            }
            else {
                super.positionlist[sollen][2] = super.positionlist[super.sollen][2];
                super.positionlist[sollen][7] = super.positionlist[super.sollen][7];
                super.positionlist[sollen][8] = super.positionlist[super.sollen][8];
                super.positionlist[sollen][9] = super.positionlist[super.sollen][9];
            }
            final int[] solamount = super.solamount;
            final int sollen2 = super.sollen;
            solamount[sollen2] += ((n == 1 || n == 4) ? 1 : 2);
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
                if (super.sollen + this.pruneEdgePerm[super.positionlist[sollen][7]] < this.phase1len + this.phase2len + 1 && super.sollen + this.pruneCornPerm[super.positionlist[sollen][2]] < this.phase1len + this.phase2len + 1 && super.sollen + this.pruneSlicePerm[super.positionlist[sollen][8]] < this.phase1len + this.phase2len + 1 && (!Cubie.settings.superGroup || super.sollen + this.pruneFace2[super.positionlist[sollen][9]] < this.phase1len + this.phase2len + 1)) {
                    super.solmoves[sollen] = -1;
                    super.solamount[sollen] = 3;
                    super.sollen = sollen;
                    if (super.sollen >= this.phase1len + this.phase2len) {
                        return true;
                    }
                }
                if (super.wanttostop) {
                    return false;
                }
                continue;
            }
        }
        super.solmoves[this.phase1len] = -1;
        super.solamount[this.phase1len] = 3;
        super.sollen = this.phase1len;
        return false;
    }
    
    protected void init() {
        this.transEdgeOri = new int[2048][6];
        this.transCornOri = new int[2187][6];
        this.transCornPerm = new int[40320][6];
        this.transEdgePerm = new int[40320][6];
        this.transSliceFull = new int[11880][6];
        this.transChoice = new int[2048][6];
        this.transSlicePerm = new int[24][6];
        this.transFace = new int[4096][6];
        this.pruneEdgeOri = new byte[2048];
        this.pruneCornOri = new byte[2187];
        this.pruneChoice = new byte[2048];
        this.pruneCornPerm = new byte[40320];
        this.pruneEdgePerm = new byte[40320];
        this.pruneSlicePerm = new byte[24];
        this.pruneFace1 = new byte[4096];
        this.pruneFace2 = new byte[4096];
        for (int i = 0; i < 2048; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.transEdgeOri[i][j] = this.gettransEdgeOri(i, j);
            }
        }
        for (int k = 0; k < 2187; ++k) {
            for (int l = 0; l < 6; ++l) {
                this.transCornOri[k][l] = this.gettransCornOri(k, l);
            }
        }
        for (int n = 0; n < 40320; ++n) {
            for (int n2 = 0; n2 < 6; ++n2) {
                this.transCornPerm[n][n2] = this.gettransCornPerm(n, n2);
            }
        }
        for (int n3 = 0; n3 < 40320; ++n3) {
            for (int n4 = 0; n4 < 6; ++n4) {
                this.transEdgePerm[n3][n4] = this.gettransEdgePerm(n3, n4);
            }
        }
        for (int n5 = 0; n5 < 11880; ++n5) {
            for (int n6 = 0; n6 < 6; ++n6) {
                this.transSliceFull[n5][n6] = this.gettransSliceFull(n5, n6);
            }
        }
        for (int n7 = 0; n7 < 2048; ++n7) {
            for (int n8 = 0; n8 < 6; ++n8) {
                this.transChoice[n7][n8] = this.gettransChoice(n7, n8);
            }
        }
        for (int n9 = 0; n9 < 24; ++n9) {
            for (int n10 = 0; n10 < 6; ++n10) {
                this.transSlicePerm[n9][n10] = this.gettransSlicePerm(n9, n10);
            }
        }
        for (int n11 = 0; n11 < 4096; ++n11) {
            this.transFace[n11][0] = (n11 + 3072 & 0xFFF);
            this.transFace[n11][1] = (n11 + 768 & 0x3FF) + (n11 & 0xC00);
            this.transFace[n11][2] = (n11 + 192 & 0xFF) + (n11 & 0xF00);
            this.transFace[n11][3] = (n11 + 48 & 0x3F) + (n11 & 0xFC0);
            this.transFace[n11][4] = (n11 + 12 & 0xF) + (n11 & 0xFF0);
            this.transFace[n11][5] = (n11 + 3 & 0x3) + (n11 & 0xFFC);
        }
        byte b = 1;
        this.pruneEdgeOri[0] = 1;
        int n12;
        do {
            n12 = 0;
            for (int n13 = 0; n13 < 2048; ++n13) {
                if (this.pruneEdgeOri[n13] == b) {
                    for (int n14 = 0; n14 < 6; ++n14) {
                        int n15 = n13;
                        for (int n16 = 0; n16 < 3; ++n16) {
                            n15 = this.transEdgeOri[n15][n14];
                            if (this.pruneEdgeOri[n15] == 0) {
                                this.pruneEdgeOri[n15] = (byte)(b + 1);
                                ++n12;
                            }
                        }
                    }
                }
            }
            ++b;
        } while (n12 != 0);
        byte b2 = 1;
        this.pruneCornOri[0] = 1;
        int n17;
        do {
            n17 = 0;
            for (int n18 = 0; n18 < 2187; ++n18) {
                if (this.pruneCornOri[n18] == b2) {
                    for (int n19 = 0; n19 < 6; ++n19) {
                        int n20 = n18;
                        for (int n21 = 0; n21 < 3; ++n21) {
                            n20 = this.transCornOri[n20][n19];
                            if (this.pruneCornOri[n20] == 0) {
                                this.pruneCornOri[n20] = (byte)(b2 + 1);
                                ++n17;
                            }
                        }
                    }
                }
            }
            ++b2;
        } while (n17 != 0);
        byte b3 = 1;
        this.pruneChoice[240] = 1;
        int n22;
        do {
            n22 = 0;
            for (int n23 = 0; n23 < 2048; ++n23) {
                if (this.pruneChoice[n23] == b3) {
                    for (int n24 = 0; n24 < 6; ++n24) {
                        int n25 = n23;
                        for (int n26 = 0; n26 < 3; ++n26) {
                            n25 = this.transChoice[n25][n24];
                            if (this.pruneChoice[n25] == 0) {
                                this.pruneChoice[n25] = (byte)(b3 + 1);
                                ++n22;
                            }
                        }
                    }
                }
            }
            ++b3;
        } while (n22 != 0);
        byte b4 = 1;
        this.pruneCornPerm[0] = 1;
        int n27;
        do {
            n27 = 0;
            for (int n28 = 0; n28 < 40320; ++n28) {
                if (this.pruneCornPerm[n28] == b4) {
                    for (int n29 = 0; n29 < 6; ++n29) {
                        int n30 = n28;
                        for (int n31 = 0; n31 < 3; ++n31) {
                            n30 = this.transCornPerm[n30][n29];
                            if ((n29 == 1 || n29 == 4 || n31 == 1) && this.pruneCornPerm[n30] == 0) {
                                this.pruneCornPerm[n30] = (byte)(b4 + 1);
                                ++n27;
                            }
                        }
                    }
                }
            }
            ++b4;
        } while (n27 != 0);
        byte b5 = 1;
        this.pruneEdgePerm[0] = 1;
        int n32;
        do {
            n32 = 0;
            for (int n33 = 0; n33 < 40320; ++n33) {
                if (this.pruneEdgePerm[n33] == b5) {
                    for (int n34 = 0; n34 < 6; ++n34) {
                        int n35 = n33;
                        for (int n36 = 0; n36 < 3; ++n36) {
                            n35 = this.transEdgePerm[n35][n34];
                            if (this.pruneEdgePerm[n35] == 0) {
                                this.pruneEdgePerm[n35] = (byte)(b5 + 1);
                                ++n32;
                            }
                        }
                    }
                }
            }
            ++b5;
        } while (n32 != 0);
        byte b6 = 1;
        this.pruneSlicePerm[0] = 1;
        int n37;
        do {
            n37 = 0;
            for (int n38 = 0; n38 < 24; ++n38) {
                if (this.pruneSlicePerm[n38] == b6) {
                    for (int n39 = 0; n39 < 6; ++n39) {
                        int n40 = n38;
                        for (int n41 = 0; n41 < 3; ++n41) {
                            n40 = this.transSlicePerm[n40][n39];
                            if (this.pruneSlicePerm[n40] == 0) {
                                this.pruneSlicePerm[n40] = (byte)(b6 + 1);
                                ++n37;
                            }
                        }
                    }
                }
            }
            ++b6;
        } while (n37 != 0);
        for (int n42 = 0; n42 < 4096; ++n42) {
            this.pruneFace1[n42] = 1;
            if ((n42 & 0x1) != 0x0) {
                final byte[] pruneFace1 = this.pruneFace1;
                final int n43 = n42;
                ++pruneFace1[n43];
            }
            if ((n42 & 0x10) != 0x0) {
                final byte[] pruneFace2 = this.pruneFace1;
                final int n44 = n42;
                ++pruneFace2[n44];
            }
            if ((n42 & 0x40) != 0x0) {
                final byte[] pruneFace3 = this.pruneFace1;
                final int n45 = n42;
                ++pruneFace3[n45];
            }
            if ((n42 & 0x400) != 0x0) {
                final byte[] pruneFace4 = this.pruneFace1;
                final int n46 = n42;
                ++pruneFace4[n46];
            }
            this.pruneFace2[n42] = 1;
            if ((n42 & 0x3) != 0x0) {
                final byte[] pruneFace5 = this.pruneFace2;
                final int n47 = n42;
                ++pruneFace5[n47];
            }
            if ((n42 & 0xC) != 0x0) {
                final byte[] pruneFace6 = this.pruneFace2;
                final int n48 = n42;
                ++pruneFace6[n48];
            }
            if ((n42 & 0x30) != 0x0) {
                final byte[] pruneFace7 = this.pruneFace2;
                final int n49 = n42;
                ++pruneFace7[n49];
            }
            if ((n42 & 0xC0) != 0x0) {
                final byte[] pruneFace8 = this.pruneFace2;
                final int n50 = n42;
                ++pruneFace8[n50];
            }
            if ((n42 & 0x300) != 0x0) {
                final byte[] pruneFace9 = this.pruneFace2;
                final int n51 = n42;
                ++pruneFace9[n51];
            }
            if ((n42 & 0xC00) != 0x0) {
                final byte[] pruneFace10 = this.pruneFace2;
                final int n52 = n42;
                ++pruneFace10[n52];
            }
        }
        super.prepared = true;
    }
    
    private int gettransEdgeOri(final int n, final int n2) {
        final int[] array = new int[12];
        this.num2ori(array, 0, 12, 2, n);
        if (n2 == 3) {
            this.cycle(array, 0, 5, 8, 4);
            final int[] array2 = array;
            final int n3 = 0;
            array2[n3] ^= 0x1;
            final int[] array3 = array;
            final int n4 = 5;
            array3[n4] ^= 0x1;
            final int[] array4 = array;
            final int n5 = 8;
            array4[n5] ^= 0x1;
            final int[] array5 = array;
            final int n6 = 4;
            array5[n6] ^= 0x1;
        }
        else if (n2 == 2) {
            this.cycle(array, 1, 6, 9, 5);
        }
        else if (n2 == 0) {
            this.cycle(array, 2, 7, 10, 6);
            final int[] array6 = array;
            final int n7 = 2;
            array6[n7] ^= 0x1;
            final int[] array7 = array;
            final int n8 = 7;
            array7[n8] ^= 0x1;
            final int[] array8 = array;
            final int n9 = 10;
            array8[n9] ^= 0x1;
            final int[] array9 = array;
            final int n10 = 6;
            array9[n10] ^= 0x1;
        }
        else if (n2 == 5) {
            this.cycle(array, 3, 4, 11, 7);
        }
        else if (n2 == 1) {
            this.cycle(array, 3, 2, 1, 0);
        }
        else if (n2 == 4) {
            this.cycle(array, 8, 9, 10, 11);
        }
        return this.ori2num(array, 0, 12, 2);
    }
    
    private int gettransCornOri(final int n, final int n2) {
        final int[] array = new int[8];
        this.num2ori(array, 0, 8, 3, n);
        if (n2 == 3) {
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
        else if (n2 == 2) {
            this.cycle(array, 1, 2, 6, 5);
            final int[] array6 = array;
            final int n7 = 1;
            array6[n7] += 2;
            final int[] array7 = array;
            final int n8 = 2;
            ++array7[n8];
            final int[] array8 = array;
            final int n9 = 6;
            array8[n9] += 2;
            final int[] array9 = array;
            final int n10 = 5;
            ++array9[n10];
        }
        else if (n2 == 0) {
            this.cycle(array, 2, 3, 7, 6);
            final int[] array10 = array;
            final int n11 = 2;
            array10[n11] += 2;
            final int[] array11 = array;
            final int n12 = 3;
            ++array11[n12];
            final int[] array12 = array;
            final int n13 = 7;
            array12[n13] += 2;
            final int[] array13 = array;
            final int n14 = 6;
            ++array13[n14];
        }
        else if (n2 == 5) {
            this.cycle(array, 3, 0, 4, 7);
            final int[] array14 = array;
            final int n15 = 3;
            array14[n15] += 2;
            final int[] array15 = array;
            final int n16 = 0;
            ++array15[n16];
            final int[] array16 = array;
            final int n17 = 4;
            array16[n17] += 2;
            final int[] array17 = array;
            final int n18 = 7;
            ++array17[n18];
        }
        else if (n2 == 1) {
            this.cycle(array, 3, 2, 1, 0);
        }
        else if (n2 == 4) {
            this.cycle(array, 4, 5, 6, 7);
        }
        return this.ori2num(array, 0, 8, 3);
    }
    
    private int gettransCornPerm(final int n, final int n2) {
        final int[] array = new int[8];
        this.num2perm(array, 0, 8, n);
        if (n2 == 3) {
            this.cycle(array, 0, 1, 5, 4);
        }
        else if (n2 == 2) {
            this.cycle(array, 1, 2, 6, 5);
        }
        else if (n2 == 0) {
            this.cycle(array, 2, 3, 7, 6);
        }
        else if (n2 == 5) {
            this.cycle(array, 3, 0, 4, 7);
        }
        else if (n2 == 1) {
            this.cycle(array, 3, 2, 1, 0);
        }
        else if (n2 == 4) {
            this.cycle(array, 4, 5, 6, 7);
        }
        return this.perm2num(array, 0, 8);
    }
    
    private int gettransEdgePerm(final int n, final int n2) {
        final int[] array = new int[8];
        this.num2perm(array, 0, 8, n);
        if (n2 == 3) {
            this.swap(array, 0, 4);
        }
        else if (n2 == 2) {
            this.swap(array, 1, 5);
        }
        else if (n2 == 0) {
            this.swap(array, 2, 6);
        }
        else if (n2 == 5) {
            this.swap(array, 3, 7);
        }
        else if (n2 == 1) {
            this.cycle(array, 3, 2, 1, 0);
        }
        else if (n2 == 4) {
            this.cycle(array, 4, 5, 6, 7);
        }
        return this.perm2num(array, 0, 8);
    }
    
    private int gettransSliceFull(int n, final int n2) {
        final int[] array = new int[12];
        for (int i = 0; i < 4; ++i) {
            int n3 = n % (12 - i);
            n = (n - n3) / (12 - i);
            int n4;
            for (n4 = 0; n4 < 12 && (array[n4] != 0 || n3 > 0); ++n4) {
                if (array[n4] == 0) {
                    --n3;
                }
            }
            array[n4] = i + 1;
        }
        if (n2 == 3) {
            this.cycle(array, 0, 5, 8, 4);
        }
        else if (n2 == 2) {
            this.cycle(array, 1, 6, 9, 5);
        }
        else if (n2 == 0) {
            this.cycle(array, 2, 7, 10, 6);
        }
        else if (n2 == 5) {
            this.cycle(array, 3, 4, 11, 7);
        }
        else if (n2 == 1) {
            this.cycle(array, 3, 2, 1, 0);
        }
        else if (n2 == 4) {
            this.cycle(array, 8, 9, 10, 11);
        }
        n = 0;
        for (int j = 3; j >= 0; --j) {
            int n5 = 0;
            for (int n6 = 0; n6 < 12 && array[n6] != j + 1; ++n6) {
                if (array[n6] == 0 || array[n6] > j + 1) {
                    ++n5;
                }
            }
            n = n * (12 - j) + n5;
        }
        return n;
    }
    
    private int gettransSlicePerm(final int n, final int n2) {
        final int[] array = new int[4];
        this.num2perm(array, 0, 4, n);
        if (n2 == 3) {
            this.swap(array, 0, 1);
        }
        else if (n2 == 2) {
            this.swap(array, 1, 2);
        }
        else if (n2 == 0) {
            this.swap(array, 2, 3);
        }
        else if (n2 == 5) {
            this.swap(array, 3, 0);
        }
        return this.perm2num(array, 0, 4);
    }
    
    private int gettransChoice(final int n, final int n2) {
        final int[] array = new int[12];
        this.num2ori(array, 0, 12, 2, n);
        if (n2 == 3) {
            this.cycle(array, 0, 5, 8, 4);
        }
        else if (n2 == 2) {
            this.cycle(array, 1, 6, 9, 5);
        }
        else if (n2 == 0) {
            this.cycle(array, 2, 7, 10, 6);
        }
        else if (n2 == 5) {
            this.cycle(array, 3, 4, 11, 7);
        }
        else if (n2 == 1) {
            this.cycle(array, 3, 2, 1, 0);
        }
        else if (n2 == 4) {
            this.cycle(array, 8, 9, 10, 11);
        }
        return this.ori2num(array, 0, 12, 2);
    }
}

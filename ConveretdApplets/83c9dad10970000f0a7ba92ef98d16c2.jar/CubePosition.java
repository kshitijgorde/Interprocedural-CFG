// 
// Decompiled by Procyon v0.5.30
// 

public final class CubePosition
{
    public int[] cubeletPerm;
    public int[] cubeletOri;
    public int[] faceOri;
    final int[][][] movePerm;
    static final int[] pceTypes;
    public int[] faceletColor;
    public int[] faceletOri;
    public static int[] faceletOriDiff;
    public static int[][] cubeletColors;
    public static int[][] cubelet2facelet;
    
    public void reset() {
        for (int i = 0; i < 20; ++i) {
            this.cubeletPerm[i] = i;
            this.cubeletOri[i] = 0;
        }
        for (int j = 0; j < 6; ++j) {
            this.cubeletOri[j + 20] = 0;
        }
    }
    
    public void resetView() {
        while (this.faceOri[1] != 0) {
            this.doMove(7, this.cubeletPerm, this.cubeletOri, this.faceOri);
        }
        if (this.faceOri[0] == 1 || this.faceOri[0] == 3) {
            this.doMove(6, this.cubeletPerm, this.cubeletOri, this.faceOri);
        }
        else if (this.faceOri[0] == 2) {
            this.doMove(6, this.cubeletPerm, this.cubeletOri, this.faceOri);
            this.doMove(6, this.cubeletPerm, this.cubeletOri, this.faceOri);
        }
        while (this.faceOri[1] != 0) {
            this.doMove(7, this.cubeletPerm, this.cubeletOri, this.faceOri);
        }
    }
    
    public void doMove(final int n, int i, final boolean b) {
        if (i < 0) {
            i += 4;
        }
        while (i > 0) {
            if (n >= 15) {
                this.doMove(n - 15, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 12, this.cubeletPerm, this.cubeletOri, this.faceOri);
            }
            else if (n >= 12) {
                this.doMove(n - 12, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 9, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 9, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 9, this.cubeletPerm, this.cubeletOri, this.faceOri);
            }
            else if (n >= 9) {
                if (b) {
                    this.doMove(n - 3, this.cubeletPerm, this.cubeletOri, this.faceOri);
                }
            }
            else if (n >= 6) {
                if (b) {
                    this.doMove(n, this.cubeletPerm, this.cubeletOri, this.faceOri);
                }
                this.doMove(n - 3, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 6, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 6, this.cubeletPerm, this.cubeletOri, this.faceOri);
                this.doMove(n - 6, this.cubeletPerm, this.cubeletOri, this.faceOri);
            }
            else {
                this.doMove(n, this.cubeletPerm, this.cubeletOri, this.faceOri);
            }
            --i;
        }
    }
    
    public void doMove(final int n) {
        this.doMove(n, this.cubeletPerm, this.cubeletOri, this.faceOri);
    }
    
    void doMove(final int n, final int[] array, final int[] array2, final int[] array3) {
        final int[] array4 = new int[26];
        final int[] array5 = new int[26];
        final int[] array6 = new int[6];
        for (int i = 0; i < 26; ++i) {
            array4[i] = array[this.movePerm[n][0][i]];
            array5[i] = array2[this.movePerm[n][0][i]];
            if (array5[i] >= 0) {
                final int[] array7 = array5;
                final int n2 = i;
                array7[n2] += this.movePerm[n][1][i];
            }
            if (array4[i] >= 0) {
                array4[i] = this.movePerm[n][2][array4[i]];
                if (array5[i] >= 0) {
                    final int[] array8 = array5;
                    final int n3 = i;
                    array8[n3] += this.movePerm[n][3][array4[i]];
                }
            }
            if (array5[i] >= 0) {
                if (i < 8) {
                    while (array5[i] > 2) {
                        final int[] array9 = array5;
                        final int n4 = i;
                        array9[n4] -= 3;
                    }
                }
                else if (i < 20) {
                    while (array5[i] > 1) {
                        final int[] array10 = array5;
                        final int n5 = i;
                        array10[n5] -= 2;
                    }
                }
                else {
                    while (array5[i] > 3) {
                        final int[] array11 = array5;
                        final int n6 = i;
                        array11[n6] -= 4;
                    }
                }
            }
        }
        for (int j = 0; j < 6; ++j) {
            array6[j] = array3[this.movePerm[n][0][j + 20] - 20] + this.movePerm[n][4][j];
            while (array6[j] > 3) {
                final int[] array12 = array6;
                final int n7 = j;
                array12[n7] -= 4;
            }
        }
        for (int k = 0; k < 26; ++k) {
            array[k] = array4[k];
            array2[k] = array5[k];
        }
        for (int l = 0; l < 6; ++l) {
            array3[l] = array6[l];
        }
    }
    
    public void doSequence(final MoveSequence moveSequence) {
        this.doSequence(moveSequence, moveSequence.len);
    }
    
    public void doSequence(final MoveSequence moveSequence, final int n) {
        this.reset();
        for (int i = 0; i < n; ++i) {
            this.doMove(moveSequence.moves[i], moveSequence.amount[i], true);
        }
    }
    
    public void editMove(final int n, final int n2, final int n3, final int n4) {
        if (n < 8 && n3 < 8) {
            if (n == n3) {
                final int[] cubeletOri = this.cubeletOri;
                cubeletOri[n] += n2 + 3 - n4;
                while (this.cubeletOri[n] > 2) {
                    final int[] cubeletOri2 = this.cubeletOri;
                    cubeletOri2[n] -= 3;
                }
                return;
            }
            final int n5 = this.cubeletPerm[n];
            this.cubeletPerm[n] = this.cubeletPerm[n3];
            this.cubeletPerm[n3] = n5;
            final int n6 = this.cubeletOri[n];
            this.cubeletOri[n] = this.cubeletOri[n3];
            this.cubeletOri[n3] = n6;
            final int[] cubeletOri3 = this.cubeletOri;
            cubeletOri3[n] += n4 + 3 - n2;
            final int[] cubeletOri4 = this.cubeletOri;
            cubeletOri4[n3] += n2 + 3 - n4;
            while (this.cubeletOri[n] > 2) {
                final int[] cubeletOri5 = this.cubeletOri;
                cubeletOri5[n] -= 3;
            }
            while (this.cubeletOri[n3] > 2) {
                final int[] cubeletOri6 = this.cubeletOri;
                cubeletOri6[n3] -= 3;
            }
        }
        else {
            if (n < 8 || n3 < 8 || n >= 20 || n3 >= 20) {
                if (n >= 20 && n3 >= 20) {
                    if (n == n3) {
                        if (this.cubeletOri[n] <= 0) {
                            this.cubeletOri[n] = 3;
                            return;
                        }
                        final int[] cubeletOri7 = this.cubeletOri;
                        --cubeletOri7[n];
                    }
                    else {
                        final int n7 = this.cubeletPerm[n];
                        this.cubeletPerm[n] = this.cubeletPerm[n3];
                        this.cubeletPerm[n3] = n7;
                        final int n8 = this.cubeletOri[n];
                        this.cubeletOri[n] = this.cubeletOri[n3];
                        this.cubeletOri[n3] = n8;
                    }
                }
                return;
            }
            if (n == n3) {
                final int[] cubeletOri8 = this.cubeletOri;
                cubeletOri8[n] += n4 + n2;
                while (this.cubeletOri[n] > 1) {
                    final int[] cubeletOri9 = this.cubeletOri;
                    cubeletOri9[n] -= 2;
                }
                return;
            }
            final int n9 = this.cubeletPerm[n];
            this.cubeletPerm[n] = this.cubeletPerm[n3];
            this.cubeletPerm[n3] = n9;
            final int n10 = this.cubeletOri[n];
            this.cubeletOri[n] = this.cubeletOri[n3];
            this.cubeletOri[n3] = n10;
            final int[] cubeletOri10 = this.cubeletOri;
            cubeletOri10[n] += n4 + n2;
            final int[] cubeletOri11 = this.cubeletOri;
            cubeletOri11[n3] += n2 + n4;
            while (this.cubeletOri[n] > 1) {
                final int[] cubeletOri12 = this.cubeletOri;
                cubeletOri12[n] -= 2;
            }
            while (this.cubeletOri[n3] > 1) {
                final int[] cubeletOri13 = this.cubeletOri;
                cubeletOri13[n3] -= 2;
            }
        }
    }
    
    void doReflect(final int[] array, final int[] array2, final int[] array3) {
        final int[] array4 = { 6, 7, 4, 5, 2, 3, 0, 1, 18, 19, 16, 17, 14, 15, 12, 13, 10, 11, 8, 9, 23, 24, 25, 20, 21, 22 };
        final int[] array5 = new int[26];
        final int[] array6 = new int[26];
        final int[] array7 = new int[6];
        for (int i = 0; i < 26; ++i) {
            array5[i] = array[array4[i]];
            array6[i] = array2[array4[i]];
            if (array6[i] > 0) {
                if (i < 8) {
                    final int[] array8 = array6;
                    final int n = i;
                    array8[n] ^= 0x3;
                }
                else if (i >= 20) {
                    array6[i] = 4 - array6[i];
                }
            }
            if (i < 20 && array5[i] >= 0) {
                array5[i] = array4[array5[i]];
            }
        }
        for (int j = 0; j < 6; ++j) {
            array7[j] = 4 - array3[array4[j + 20] - 20];
            if (j != 1 && j != 4) {
                final int[] array9 = array7;
                final int n2 = j;
                array9[n2] ^= 0x2;
            }
        }
        for (int k = 0; k < 26; ++k) {
            array[k] = array5[k];
            array2[k] = array6[k];
        }
        for (int l = 0; l < 6; ++l) {
            array3[l] = array7[l];
        }
    }
    
    public void doSym(final int n, final boolean b) {
        if (b) {
            final int[] array = new int[6];
            for (int i = 0; i < 6; ++i) {
                array[i] = this.cubeletPerm[20 + i];
            }
            this.doSym(n, this.cubeletPerm, this.cubeletOri, this.faceOri);
            for (int j = 0; j < 6; ++j) {
                this.cubeletPerm[20 + j] = array[j];
            }
            return;
        }
        this.doSym(n, this.cubeletPerm, this.cubeletOri, this.faceOri);
    }
    
    void doSym(final int n, final int[] array, final int[] array2, final int[] array3) {
        switch (n) {
            case 0: {
                this.doReflect(array, array2, array3);
            }
            case 1:
            case 2:
            case 3: {
                this.doSym(26 + (n - 1), array, array2, array3);
                this.doReflect(array, array2, array3);
            }
            case 4:
            case 5:
            case 6: {
                this.doSym(23 + (n - 4), array, array2, array3);
                this.doReflect(array, array2, array3);
            }
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: {
                this.doSym(13 + (n - 7), array, array2, array3);
                this.doReflect(array, array2, array3);
            }
            case 13:
            case 14:
            case 15: {
                this.doSym(16 + (n - 13), array, array2, array3);
                this.doSym(16 + (n - 13), array, array2, array3);
            }
            case 16: {
                this.doMove(7, array, array2, array3);
            }
            case 17: {
                this.doMove(6, array, array2, array3);
                this.doMove(6, array, array2, array3);
                this.doMove(6, array, array2, array3);
            }
            case 18: {
                this.doMove(8, array, array2, array3);
            }
            case 19: {
                this.doMove(6, array, array2, array3);
                this.doMove(7, array, array2, array3);
            }
            case 20: {
                this.doMove(7, array, array2, array3);
                this.doMove(6, array, array2, array3);
            }
            case 21: {
                this.doMove(7, array, array2, array3);
                this.doSym(17, array, array2, array3);
            }
            case 22: {
                this.doMove(7, array, array2, array3);
                this.doMove(8, array, array2, array3);
            }
            case 23: {
                this.doSym(22, array, array2, array3);
                this.doMove(8, array, array2, array3);
            }
            case 24: {
                this.doSym(20, array, array2, array3);
                this.doMove(8, array, array2, array3);
            }
            case 25: {
                this.doSym(22, array, array2, array3);
                this.doSym(17, array, array2, array3);
            }
            case 26: {
                this.doSym(21, array, array2, array3);
                this.doSym(17, array, array2, array3);
            }
            case 27: {
                this.doSym(19, array, array2, array3);
                this.doMove(7, array, array2, array3);
            }
            case 28: {
                this.doSym(20, array, array2, array3);
                this.doMove(7, array, array2, array3);
            }
            default: {}
        }
    }
    
    public int getSym() {
        int n = 0;
        int n2 = 1;
        for (int i = 0; i < 29; ++i) {
            if (this.checkSym(i)) {
                n |= n2;
            }
            n2 <<= 1;
        }
        return n;
    }
    
    boolean checkSym(final int n) {
        final int[] array = new int[26];
        final int[] array2 = new int[26];
        final int[] array3 = new int[6];
        for (int i = 0; i < 26; ++i) {
            array[i] = this.cubeletPerm[i];
            array2[i] = this.cubeletOri[i];
        }
        for (int j = 0; j < 6; ++j) {
            array3[j] = this.faceOri[j];
        }
        this.doSym(n, array, array2, array3);
        boolean b = true;
        for (int n2 = 0; n2 < 20 && b; b &= (array[n2] == this.cubeletPerm[n2] && array2[n2] == this.cubeletOri[n2]), ++n2) {}
        return b;
    }
    
    public void mix(final int n, final boolean b, final boolean b2) {
        final int[] array = new int[26];
        final int[] array2 = new int[26];
        final int[] array3 = new int[6];
        for (int i = 0; i < 20; ++i) {
            array[i] = (array2[i] = -1);
        }
        for (int j = 20; j < 26; ++j) {
            array3[j - 20] = -1;
            array2[array[j] = j] = (b ? -1 : 0);
        }
        if (this.mixRest(0, array, array2, array3, n, b, b2, false)) {
            for (int k = 0; k < 26; ++k) {
                if (k < 20) {
                    this.cubeletPerm[k] = array[k];
                }
                this.cubeletOri[k] = array2[k];
            }
            return;
        }
        System.out.println("Programming error - no mixed position found");
    }
    
    boolean mixRest(int n, final int[] array, final int[] array2, final int[] array3, final int n2, final boolean b, final boolean b2, final boolean b3) {
        final int[] array4 = new int[26];
        final int[] array5 = new int[26];
        for (int i = 0; i < 26; ++i) {
            array4[i] = array[i];
            array5[i] = array2[i];
        }
        if (this.testSym(array, array2, array3, n2, b, b2)) {
            int n3;
            for (n3 = CubePosition.pceTypes[n]; n3 < CubePosition.pceTypes[n + 1] && ((!b3 && array[n3] >= 0) || (b3 && array2[n3] >= 0)); ++n3) {}
            if (n3 >= CubePosition.pceTypes[n + 1]) {
                if (!b3) {
                    n += 3;
                }
                if (n >= CubePosition.pceTypes.length || this.mixRest(n, array, array2, array3, n2, b, b2, b3)) {
                    return true;
                }
            }
            else {
                int j = 0;
                int[] array6;
                if (b3) {
                    array6 = new int[CubePosition.pceTypes[n + 2]];
                    for (int k = 0; k < CubePosition.pceTypes[n + 2]; ++k) {
                        array6[j++] = k;
                    }
                }
                else {
                    array6 = new int[12];
                    for (int l = CubePosition.pceTypes[n]; l < CubePosition.pceTypes[n + 1]; ++l) {
                        array6[j++] = l;
                    }
                    for (int n4 = CubePosition.pceTypes[n]; n4 < CubePosition.pceTypes[n + 1]; ++n4) {
                        if (array[n4] >= 0) {
                            int n5;
                            for (n5 = 0; n5 < j && array6[n5] != array[n4]; ++n5) {}
                            array6[n5] = array6[j - 1];
                            --j;
                        }
                    }
                }
                while (j > 0) {
                    final int n6 = (int)(j * Math.random());
                    if (b3) {
                        array2[n3] = array6[n6];
                    }
                    else {
                        array[n3] = array6[n6];
                    }
                    if (this.mixRest(n, array, array2, array3, n2, b, b2, !b3)) {
                        return true;
                    }
                    --j;
                    array6[n6] = array6[j];
                }
            }
        }
        for (int n7 = 0; n7 < 26; ++n7) {
            array[n7] = array4[n7];
            array2[n7] = array5[n7];
        }
        return false;
    }
    
    boolean testSym(final int[] array, final int[] array2, final int[] array3, final int n, final boolean b, final boolean b2) {
        final int[] array4 = new int[26];
        final int[] array5 = new int[26];
        boolean b3;
        do {
            b3 = false;
            for (int i = 0; i < 29; ++i) {
                if ((n & 1 << i) != 0x0) {
                    for (int j = 0; j < 26; ++j) {
                        array4[j] = array[j];
                        array5[j] = array2[j];
                    }
                    this.doSym(i, array, array2, array3);
                    for (int k = 0; k < 26; ++k) {
                        if (array[k] >= 0) {
                            if (array4[k] >= 0 && array4[k] != array[k] && k < 20) {
                                return false;
                            }
                        }
                        else if (array4[k] >= 0) {
                            array[k] = array4[k];
                            b3 = true;
                        }
                        if (array2[k] >= 0) {
                            if (array5[k] >= 0 && array5[k] != array2[k]) {
                                return false;
                            }
                        }
                        else if (array5[k] >= 0) {
                            array2[k] = array5[k];
                            b3 = true;
                        }
                    }
                }
            }
        } while (b3);
        for (int l = 0; l < 20; ++l) {
            if (array[l] >= 0) {
                for (int n2 = l + 1; n2 < 20; ++n2) {
                    if (array[l] == array[n2]) {
                        return false;
                    }
                }
            }
        }
        int n3 = 0;
        int n4;
        for (n4 = 0; n4 < 8 && array2[n4] >= 0; ++n4) {
            n3 += array2[n4];
        }
        if (n4 >= 8 && n3 % 3 != 0) {
            return false;
        }
        int n5 = 0;
        int n6;
        for (n6 = 8; n6 < 20 && array2[n6] >= 0; ++n6) {
            n5 += array2[n6];
        }
        if (n6 >= 20 && (n5 & 0x1) != 0x0) {
            return false;
        }
        boolean b4 = false;
        int n7;
        for (n7 = 0; n7 < 20 && array[n7] >= 0; ++n7) {
            for (int n8 = n7 + 1; n8 < 20; ++n8) {
                if (array[n8] < array[n7]) {
                    b4 = !b4;
                }
            }
        }
        if (n7 >= 20 && b4) {
            return false;
        }
        if (b) {
            boolean b5 = false;
            for (int n9 = 0; n9 < 8; ++n9) {
                for (int n10 = n9 + 1; n10 < 8; ++n10) {
                    if (array[n10] < array[n9]) {
                        b5 = !b5;
                    }
                }
            }
            int n11;
            for (n11 = 20; n11 < 26 && array2[n11] >= 0; ++n11) {
                if ((array2[n11] & 0x1) != 0x0) {
                    b5 = !b5;
                }
            }
            if (n11 >= 26 && b5) {
                return false;
            }
        }
        if (b2) {
            final int[] array6 = new int[54];
            this.getFaceletColors(array, array2, array3, array6, new int[54]);
            final int[] array7 = new int[7];
            for (int n12 = 0; n12 < 54; n12 += 9) {
                int n13 = 0;
                for (int n14 = n12; n14 < n12 + 9; ++n14) {
                    if (array6[n14] >= 0) {
                        int n15;
                        for (n15 = 0; n15 < n13 && array7[n15] != array6[n14]; ++n15) {}
                        if (n15 >= n13) {
                            array7[n13++] = array6[n14];
                        }
                    }
                }
                if (n13 > 2) {
                    return false;
                }
            }
        }
        return true;
    }
    
    protected void getFaceletColors() {
        this.getFaceletColors(this.cubeletPerm, this.cubeletOri, this.faceOri, this.faceletColor, this.faceletOri);
    }
    
    void getFaceletColors(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5) {
        for (int i = 0; i < 54; ++i) {
            array4[i] = -1;
        }
        for (int j = 0; j < 20; ++j) {
            final int n = array[j];
            int n2 = array2[j];
            if (n >= 0 && n2 >= 0) {
                for (int n3 = 0; n3 < 3 && (n3 < 2 || j < 8); ++n3) {
                    final int n4 = CubePosition.cubeletColors[n][n2];
                    array4[CubePosition.cubelet2facelet[j][n3]] = n4;
                    array5[CubePosition.cubelet2facelet[j][n3]] = (array3[n4] + (CubePosition.faceletOriDiff[CubePosition.cubelet2facelet[n][n2]] - CubePosition.faceletOriDiff[CubePosition.cubelet2facelet[j][n3]]) & 0x3);
                    n2 = ((n2 == 2 || (n2 == 1 && j >= 8)) ? 0 : (n2 + 1));
                }
            }
        }
        for (int k = 20; k < 26; ++k) {
            final int n5 = array2[k];
            array4[CubePosition.cubelet2facelet[k][0]] = k - 20;
            if (n5 >= 0) {
                array5[CubePosition.cubelet2facelet[k][0]] = (array3[k - 20] + n5 & 0x3);
            }
        }
    }
    
    public CubePosition() {
        this.cubeletPerm = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
        this.cubeletOri = new int[26];
        this.faceOri = new int[6];
        this.movePerm = new int[][][] { { { 0, 1, 3, 7, 4, 5, 2, 6, 8, 9, 15, 11, 12, 13, 10, 18, 16, 17, 14, 19, 20, 21, 22, 23, 24, 25 }, { 0, 0, 2, 1, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, new int[26], new int[6] }, { { 3, 0, 1, 2, 4, 5, 6, 7, 11, 8, 9, 10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, new int[26], new int[6] }, { { 0, 2, 6, 3, 4, 1, 5, 7, 8, 14, 10, 11, 12, 9, 17, 15, 16, 13, 18, 19, 20, 21, 22, 23, 24, 25 }, { 0, 2, 1, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, new int[26], new int[6] }, { { 1, 5, 2, 3, 0, 4, 6, 7, 13, 9, 10, 11, 8, 16, 14, 15, 12, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, { 2, 1, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 0, 0 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, new int[26], new int[6] }, { { 0, 1, 2, 3, 5, 6, 7, 4, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 16, 20, 21, 22, 23, 24, 25 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, new int[26], new int[6] }, { { 4, 1, 2, 0, 7, 5, 6, 3, 8, 9, 10, 12, 19, 13, 14, 11, 16, 17, 18, 15, 20, 21, 22, 23, 24, 25 }, { 1, 0, 0, 2, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 }, new int[26], new int[6] }, { { 4, 0, 3, 7, 5, 1, 2, 6, 12, 11, 15, 19, 16, 8, 10, 18, 13, 9, 14, 17, 20, 25, 21, 23, 22, 24 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, { 1, 5, 6, 2, 0, 4, 7, 3, 13, 17, 14, 9, 8, 16, 18, 10, 12, 19, 15, 11, 20, 21, 22, 23, 24, 25 }, { 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }, { 3, 2, 0, 1, 0, 2 } }, { { 3, 0, 1, 2, 7, 4, 5, 6, 11, 8, 9, 10, 15, 12, 13, 14, 19, 16, 17, 18, 22, 21, 23, 25, 24, 20 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 2, 3, 0, 5, 6, 7, 4, 9, 10, 11, 8, 13, 14, 15, 12, 17, 18, 19, 16, 20, 21, 22, 23, 24, 25 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 3, 0, 0, 1, 0 } }, { { 3, 2, 6, 7, 0, 1, 5, 4, 10, 14, 18, 15, 11, 9, 17, 19, 8, 13, 16, 12, 24, 20, 22, 21, 23, 25 }, { 1, 2, 1, 2, 2, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 4, 5, 1, 0, 7, 6, 2, 3, 16, 13, 8, 12, 19, 17, 9, 11, 18, 14, 10, 15, 20, 21, 22, 23, 24, 25 }, { 2, 1, 2, 1, 1, 2, 1, 2, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 3, 3, 3, 3, 3, 1 } } };
        this.faceletColor = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
        this.faceletOri = new int[54];
    }
    
    static {
        pceTypes = new int[] { 20, 26, 4, 0, 8, 3, 8, 20, 2 };
        CubePosition.faceletOriDiff = new int[] { 0, 0, 1, 3, 0, 1, 3, 2, 2, 0, 0, 1, 3, 0, 1, 3, 2, 2, 0, 0, 1, 3, 0, 1, 3, 2, 2, 0, 0, 1, 3, 0, 1, 3, 2, 2, 0, 0, 1, 3, 0, 1, 3, 2, 2, 0, 0, 1, 3, 0, 1, 3, 2, 2 };
        CubePosition.cubeletColors = new int[][] { { 1, 5, 3 }, { 1, 3, 2 }, { 1, 2, 0 }, { 1, 0, 5 }, { 4, 3, 5 }, { 4, 2, 3 }, { 4, 0, 2 }, { 4, 5, 0 }, { 1, 3, -1 }, { 1, 2, -1 }, { 1, 0, -1 }, { 1, 5, -1 }, { 3, 5, -1 }, { 3, 2, -1 }, { 0, 2, -1 }, { 0, 5, -1 }, { 4, 3, -1 }, { 4, 2, -1 }, { 4, 0, -1 }, { 4, 5, -1 } };
        CubePosition.cubelet2facelet = new int[][] { { 11, 45, 29 }, { 17, 27, 20 }, { 15, 18, 2 }, { 9, 0, 47 }, { 44, 35, 51 }, { 38, 26, 33 }, { 36, 8, 24 }, { 42, 53, 6 }, { 14, 28, -1 }, { 16, 19, -1 }, { 12, 1, -1 }, { 10, 46, -1 }, { 32, 48, -1 }, { 30, 23, -1 }, { 5, 21, -1 }, { 3, 50, -1 }, { 41, 34, -1 }, { 37, 25, -1 }, { 39, 7, -1 }, { 43, 52, -1 }, { 4, -1, -1 }, { 13, -1, -1 }, { 22, -1, -1 }, { 31, -1, -1 }, { 40, -1, -1 }, { 49, -1, -1 } };
    }
}

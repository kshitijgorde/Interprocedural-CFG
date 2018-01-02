// 
// Decompiled by Procyon v0.5.30
// 

public final class MoveSequence
{
    int len;
    int[] moves;
    int[] amount;
    final int[][] symPerm;
    
    public MoveSequence() {
        this.symPerm = new int[][] { { 3, 4, 5, 0, 1, 2 }, { 5, 1, 3, 2, 4, 0 }, { 0, 2, 1, 3, 5, 4 }, { 4, 3, 2, 1, 0, 5 }, { 2, 1, 0, 5, 4, 3 }, { 0, 5, 4, 3, 2, 1 }, { 1, 0, 2, 4, 3, 5 }, { 0, 4, 2, 3, 1, 5 }, { 3, 1, 2, 0, 4, 5 }, { 0, 1, 5, 3, 4, 2 }, { 2, 4, 3, 5, 1, 0 }, { 3, 2, 4, 0, 5, 1 }, { 4, 0, 5, 1, 3, 2 }, { 3, 1, 5, 0, 4, 2 }, { 0, 4, 5, 3, 1, 2 }, { 3, 4, 2, 0, 1, 5 }, { 5, 1, 0, 2, 4, 3 }, { 0, 5, 1, 3, 2, 4 }, { 1, 3, 2, 4, 0, 5 }, { 5, 0, 4, 2, 3, 1 }, { 1, 2, 0, 4, 5, 3 }, { 4, 5, 0, 1, 2, 3 }, { 5, 3, 1, 2, 0, 4 }, { 5, 4, 3, 2, 1, 0 }, { 3, 2, 1, 0, 5, 4 }, { 4, 3, 5, 1, 0, 2 }, { 2, 4, 0, 5, 1, 3 }, { 3, 5, 4, 0, 2, 1 }, { 1, 0, 5, 4, 3, 2 } };
    }
    
    public MoveSequence(final int len, final int[] array, final int[] array2) {
        this.symPerm = new int[][] { { 3, 4, 5, 0, 1, 2 }, { 5, 1, 3, 2, 4, 0 }, { 0, 2, 1, 3, 5, 4 }, { 4, 3, 2, 1, 0, 5 }, { 2, 1, 0, 5, 4, 3 }, { 0, 5, 4, 3, 2, 1 }, { 1, 0, 2, 4, 3, 5 }, { 0, 4, 2, 3, 1, 5 }, { 3, 1, 2, 0, 4, 5 }, { 0, 1, 5, 3, 4, 2 }, { 2, 4, 3, 5, 1, 0 }, { 3, 2, 4, 0, 5, 1 }, { 4, 0, 5, 1, 3, 2 }, { 3, 1, 5, 0, 4, 2 }, { 0, 4, 5, 3, 1, 2 }, { 3, 4, 2, 0, 1, 5 }, { 5, 1, 0, 2, 4, 3 }, { 0, 5, 1, 3, 2, 4 }, { 1, 3, 2, 4, 0, 5 }, { 5, 0, 4, 2, 3, 1 }, { 1, 2, 0, 4, 5, 3 }, { 4, 5, 0, 1, 2, 3 }, { 5, 3, 1, 2, 0, 4 }, { 5, 4, 3, 2, 1, 0 }, { 3, 2, 1, 0, 5, 4 }, { 4, 3, 5, 1, 0, 2 }, { 2, 4, 0, 5, 1, 3 }, { 3, 5, 4, 0, 2, 1 }, { 1, 0, 5, 4, 3, 2 } };
        this.len = len;
        this.moves = new int[this.len];
        this.amount = new int[this.len];
        for (int i = 0; i < len; ++i) {
            this.amount[i] = 4 - array2[len - 1 - i];
            this.moves[i] = array[len - 1 - i];
        }
        this.simplify();
    }
    
    public int[] getMoves() {
        return this.moves;
    }
    
    public int[] getAmount() {
        return this.amount;
    }
    
    public int getLength() {
        return this.len;
    }
    
    public String toString(final boolean b) {
        return this.toString(b, -1);
    }
    
    public String toString(final boolean b, final int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final StringBuffer sb = new StringBuffer();
        if (this.len == 0) {
            return "";
        }
        int n5;
        int n6;
        if (b) {
            n5 = this.len - 1;
            n6 = -1;
        }
        else {
            n5 = 0;
            n6 = 1;
        }
        while (n5 >= 0 && n5 < this.len) {
            if ((n5 == n && n6 > 0) || (n5 + 1 == n && n6 < 0)) {
                sb.append("_ ");
            }
            int n7 = this.amount[n5];
            final int n8 = this.moves[n5];
            if (b) {
                n7 = 4 - n7;
            }
            int n9;
            if (n8 < 6) {
                sb.append("LUFRDB".charAt(n8));
                n9 = 1;
            }
            else {
                sb.append("LUF".charAt(n8 % 3));
                sb.append("xxmcsa".charAt(n8 / 3));
                n9 = ((n8 < 9 || n8 > 11) ? 2 : 0);
            }
            if (n7 > 1) {
                sb.append("2'".charAt(n7 - 2));
            }
            if (n7 == 2) {
                n2 += n9;
            }
            n2 += n9;
            n3 += n9;
            if (n9 != 0) {
                ++n4;
            }
            n5 += n6;
            sb.append(" ");
        }
        if ((n == this.len && n6 > 0) || (n == 0 && n6 < 0)) {
            sb.append("_ ");
        }
        if (n2 != 0) {
            sb.append("(" + n3);
            if (n3 != n2) {
                sb.append("," + n2 + "q");
            }
            if (n4 != n3) {
                sb.append("," + n4 + "s");
            }
            sb.append(")");
        }
        return sb.toString();
    }
    
    public void parse(final String s, final boolean b) {
        final int[] array = new int[80];
        final int[] array2 = new int[80];
        int len = 0;
        int index = -1;
        int index2 = -1;
        int n = 0;
        int n2 = 0;
        while ((n != 0 || n2 < s.length()) && len < 80) {
            char char1;
            if (n2 < s.length()) {
                char1 = s.charAt(n2);
            }
            else {
                char1 = '\0';
            }
            if (n == 0) {
                ++n2;
                index = "LUFRDBTlufrdbt".indexOf(char1);
                if (index < 0) {
                    continue;
                }
                if (index > 6) {
                    index -= 7;
                }
                if (index == 6) {
                    index = 1;
                }
                ++n;
            }
            else if (n == 1) {
                index2 = "mcsaMCSA".indexOf(char1);
                if (index2 >= 4) {
                    index2 -= 4;
                }
                if (index2 >= 0) {
                    ++n2;
                }
                ++n;
            }
            else {
                if (n != 2) {
                    continue;
                }
                int index3 = "1+23'-".indexOf(char1);
                if (index3 >= 0) {
                    ++n2;
                }
                else {
                    index3 = 1;
                }
                if (index3 > 3) {
                    index3 = 3;
                }
                else if (index3 <= 1) {
                    index3 = 1;
                }
                if (index2 >= 0 && index > 2) {
                    index -= 3;
                    if (index2 != 3) {
                        index3 = 4 - index3;
                    }
                }
                array[len] = ((index2 < 0) ? index : (index + 6 + 3 * index2));
                array2[len] = index3;
                ++len;
                n = 0;
            }
        }
        this.len = len;
        this.moves = new int[len];
        this.amount = new int[len];
        for (int i = 0; i < len; ++i) {
            this.moves[i] = array[i];
            this.amount[i] = array2[i];
        }
        this.simplify();
        if (b) {
            int j;
            int n3;
            for (j = 0, n3 = this.len - 1; j < n3; ++j, --n3) {
                final int n4 = this.moves[j];
                this.moves[j] = this.moves[n3];
                this.moves[n3] = n4;
                final int n5 = this.amount[j];
                this.amount[j] = 4 - this.amount[n3];
                this.amount[n3] = 4 - n5;
            }
            if (j == n3) {
                this.amount[j] = 4 - this.amount[j];
            }
        }
    }
    
    private void simplify() {
        final int[] array = new int[this.len];
        final int[] array2 = new int[this.len];
        final int[] array3 = new int[3];
        for (int i = 0; i < this.len; ++i) {
            array[i] = this.moves[i] % 3;
            array2[i] = (this.moves[i] - array[i]) / 3;
        }
        for (int j = 0; j < this.len; ++j) {
            final int[] array4 = array3;
            final int n = 0;
            final int[] array5 = array3;
            final int n2 = 1;
            final int[] array6 = array3;
            final int n3 = 2;
            final boolean b = false;
            array6[n3] = (b ? 1 : 0);
            array4[n] = (array5[n2] = (b ? 1 : 0));
            int n4;
            for (n4 = j; n4 < this.len && array[j] == array[n4]; ++n4) {
                switch (array2[n4]) {
                    case 0: {
                        final int[] array7 = array3;
                        final int n5 = 0;
                        array7[n5] += this.amount[n4];
                        break;
                    }
                    case 1: {
                        final int[] array8 = array3;
                        final int n6 = 2;
                        array8[n6] += this.amount[n4];
                        break;
                    }
                    case 2: {
                        final int[] array9 = array3;
                        final int n7 = 1;
                        array9[n7] += this.amount[n4];
                        break;
                    }
                    case 3: {
                        final int[] array10 = array3;
                        final int n8 = 0;
                        array10[n8] += this.amount[n4];
                        final int[] array11 = array3;
                        final int n9 = 1;
                        array11[n9] += this.amount[n4];
                        final int[] array12 = array3;
                        final int n10 = 2;
                        array12[n10] -= this.amount[n4];
                        break;
                    }
                    case 4: {
                        final int[] array13 = array3;
                        final int n11 = 0;
                        array13[n11] += this.amount[n4];
                        final int[] array14 = array3;
                        final int n12 = 2;
                        array14[n12] -= this.amount[n4];
                        break;
                    }
                    case 5: {
                        final int[] array15 = array3;
                        final int n13 = 0;
                        array15[n13] += this.amount[n4];
                        final int[] array16 = array3;
                        final int n14 = 2;
                        array16[n14] += this.amount[n4];
                        break;
                    }
                }
            }
            if (n4 > j + 1) {
                final int[] array17 = array3;
                final int n15 = 0;
                array17[n15] &= 0x3;
                final int[] array18 = array3;
                final int n16 = 1;
                array18[n16] &= 0x3;
                final int[] array19 = array3;
                final int n17 = 2;
                array19[n17] &= 0x3;
                if (array3[0] != 0 || array3[1] != 0 || array3[2] != 0) {
                    if (array3[0] == array3[1] && array3[0] + array3[2] == 4) {
                        this.amount[j] = array3[0];
                        array2[j] = 3;
                        this.moves[j] = 9 + array[j];
                        ++j;
                    }
                    else if (array3[1] == 0 && array3[2] == 0) {
                        this.amount[j] = array3[0];
                        array2[j] = 0;
                        this.moves[j] = array[j];
                        ++j;
                    }
                    else if (array3[0] == 0 && array3[1] == 0) {
                        this.amount[j] = array3[2];
                        array2[j] = 1;
                        this.moves[j] = 3 + array[j];
                        ++j;
                    }
                    else if (array3[0] == 0 && array3[2] == 0) {
                        this.amount[j] = array3[1];
                        array2[j] = 2;
                        this.moves[j] = 6 + array[j];
                        ++j;
                    }
                    else if (array3[1] == 0 && array3[0] + array3[2] == 4) {
                        this.amount[j] = array3[0];
                        array2[j] = 4;
                        this.moves[j] = 12 + array[j];
                        ++j;
                    }
                    else if (array3[1] == 0 && array3[0] == array3[2]) {
                        this.amount[j] = array3[0];
                        array2[j] = 5;
                        this.moves[j] = 15 + array[j];
                        ++j;
                    }
                    else if (array3[0] == array3[1]) {
                        this.amount[j] = array3[0];
                        array2[j] = 3;
                        this.moves[j] = 9 + array[j];
                        ++j;
                        this.amount[j] = (array3[2] + array3[0] & 0x3);
                        array2[j] = 1;
                        this.moves[j] = 3 + array[j];
                        ++j;
                    }
                    else if (array3[2] + array3[1] == 4) {
                        this.amount[j] = array3[1];
                        array2[j] = 3;
                        this.moves[j] = 9 + array[j];
                        ++j;
                        this.amount[j] = (array3[0] + array3[2] & 0x3);
                        array2[j] = 0;
                        this.moves[j] = array[j];
                        ++j;
                    }
                    else if (array3[2] + array3[0] == 4) {
                        this.amount[j] = array3[1];
                        array2[j] = 3;
                        this.moves[j] = 9 + array[j];
                        ++j;
                        this.amount[j] = (array3[0] - array3[1] & 0x3);
                        array2[j] = 4;
                        this.moves[j] = 12 + array[j];
                        ++j;
                    }
                    else if ((array3[0] - 2 * array3[1] - array3[2] & 0x3) == 0x0) {
                        this.amount[j] = array3[1];
                        array2[j] = 3;
                        this.moves[j] = 9 + array[j];
                        ++j;
                        this.amount[j] = (array3[0] - array3[1] & 0x3);
                        array2[j] = 5;
                        this.moves[j] = 15 + array[j];
                        ++j;
                    }
                    else if (array3[0] == 0 || array3[1] == 0 || array3[2] == 0) {
                        if (array3[0] != 0) {
                            this.amount[j] = array3[0];
                            array2[j] = 0;
                            this.moves[j] = array[j];
                            ++j;
                        }
                        if (array3[1] != 0) {
                            this.amount[j] = array3[1];
                            array2[j] = 2;
                            this.moves[j] = 6 + array[j];
                            ++j;
                        }
                        if (array3[2] != 0) {
                            this.amount[j] = array3[2];
                            array2[j] = 1;
                            this.moves[j] = 3 + array[j];
                            ++j;
                        }
                    }
                    else {
                        this.amount[j] = (array3[0] - array3[1] & 0x3);
                        array2[j] = 0;
                        this.moves[j] = array[j];
                        ++j;
                        this.amount[j] = array3[1];
                        array2[j] = 3;
                        this.moves[j] = 9 + array[j];
                        ++j;
                        this.amount[j] = (array3[1] + array3[2] & 0x3);
                        array2[j] = 1;
                        this.moves[j] = 3 + array[j];
                        ++j;
                    }
                }
                this.len -= n4 - j;
                for (int k = j; k < this.len; ++k) {
                    this.moves[k] = this.moves[k + n4 - j];
                    this.amount[k] = this.amount[k + n4 - j];
                    array[k] = array[k + n4 - j];
                    array2[k] = array2[k + n4 - j];
                }
            }
        }
    }
    
    public void doSym(final int n) {
        for (int i = 0; i < this.len; ++i) {
            int n2 = this.amount[i];
            final int n3 = this.moves[i];
            if (n < 13) {
                n2 = 4 - n2;
            }
            int n4;
            if (n3 < 6) {
                n4 = this.symPerm[n][n3];
            }
            else {
                final int n5 = (n3 - 6) / 3;
                int n6 = this.symPerm[n][n3 % 3];
                if (n6 > 2) {
                    n6 -= 3;
                    if (n5 < 3) {
                        n2 = 4 - n2;
                    }
                }
                n4 = 6 + 3 * n5 + n6;
            }
            this.amount[i] = n2;
            this.moves[i] = n4;
        }
    }
}

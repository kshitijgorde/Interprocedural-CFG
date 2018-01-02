// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.fec;

import anon.util.ByteArrayUtil;

public class FECMath
{
    public int gfBits;
    public int gfSize;
    public static final String[] prim_polys;
    public char[] gf_exp;
    public int[] gf_log;
    public char[] inverse;
    public char[][] gf_mul_table;
    
    public FECMath() {
        this(8);
    }
    
    public FECMath(final int gfBits) {
        this.gfBits = gfBits;
        this.gfSize = (1 << gfBits) - 1;
        this.gf_exp = new char[2 * this.gfSize];
        this.gf_log = new int[this.gfSize + 1];
        this.inverse = new char[this.gfSize + 1];
        if (gfBits < 2 || gfBits > 16) {
            throw new IllegalArgumentException("gfBits must be 2 .. 16");
        }
        this.generateGF();
        if (gfBits <= 8) {
            this.initMulTable();
        }
    }
    
    public final void generateGF() {
        final String s = FECMath.prim_polys[this.gfBits];
        char c = '\u0001';
        this.gf_exp[this.gfBits] = '\0';
        for (int i = 0; i < this.gfBits; ++i, c <<= 1) {
            this.gf_exp[i] = c;
            this.gf_log[this.gf_exp[i]] = i;
            if (s.charAt(i) == '1') {
                final char[] gf_exp = this.gf_exp;
                final int gfBits = this.gfBits;
                gf_exp[gfBits] ^= c;
            }
        }
        this.gf_log[this.gf_exp[this.gfBits]] = this.gfBits;
        final char c2 = (char)(1 << this.gfBits - 1);
        for (int j = this.gfBits + 1; j < this.gfSize; ++j) {
            if (this.gf_exp[j - 1] >= c2) {
                this.gf_exp[j] = (char)(this.gf_exp[this.gfBits] ^ (this.gf_exp[j - 1] ^ c2) << 1);
            }
            else {
                this.gf_exp[j] = (char)(this.gf_exp[j - 1] << 1);
            }
            this.gf_log[this.gf_exp[j]] = j;
        }
        this.gf_log[0] = this.gfSize;
        for (int k = 0; k < this.gfSize; ++k) {
            this.gf_exp[k + this.gfSize] = this.gf_exp[k];
        }
        this.inverse[0] = '\0';
        this.inverse[1] = '\u0001';
        for (int l = 2; l <= this.gfSize; ++l) {
            this.inverse[l] = this.gf_exp[this.gfSize - this.gf_log[l]];
        }
    }
    
    public final void initMulTable() {
        if (this.gfBits <= 8) {
            this.gf_mul_table = new char[this.gfSize + 1][this.gfSize + 1];
            for (int i = 0; i < this.gfSize + 1; ++i) {
                for (int j = 0; j < this.gfSize + 1; ++j) {
                    this.gf_mul_table[i][j] = this.gf_exp[this.modnn(this.gf_log[i] + this.gf_log[j])];
                }
            }
            for (int k = 0; k < this.gfSize + 1; ++k) {
                this.gf_mul_table[0][k] = (this.gf_mul_table[k][0] = '\0');
            }
        }
    }
    
    public final char modnn(int i) {
        while (i >= this.gfSize) {
            i -= this.gfSize;
            i = (i >> this.gfBits) + (i & this.gfSize);
        }
        return (char)i;
    }
    
    public final char mul(final char c, final char c2) {
        if (this.gfBits <= 8) {
            return this.gf_mul_table[c][c2];
        }
        if (c == '\0' || c2 == '\0') {
            return '\0';
        }
        return this.gf_exp[this.gf_log[c] + this.gf_log[c2]];
    }
    
    public static final char[] createGFMatrix(final int n, final int n2) {
        return new char[n * n2];
    }
    
    public final void addMul(final char[] array, final int n, final char[] array2, final int n2, final char c, final int n3) {
        if (c == '\0') {
            return;
        }
        final int n4 = 16;
        int i = n;
        int n5 = n2;
        final int n6 = n + n3;
        if (this.gfBits <= 8) {
            final char[] array3 = this.gf_mul_table[c];
            while (i < n6) {
                if (n6 - i <= n4) {
                    break;
                }
                final int n7 = i;
                array[n7] ^= array3[array2[n5]];
                final int n8 = i + 1;
                array[n8] ^= array3[array2[n5 + 1]];
                final int n9 = i + 2;
                array[n9] ^= array3[array2[n5 + 2]];
                final int n10 = i + 3;
                array[n10] ^= array3[array2[n5 + 3]];
                final int n11 = i + 4;
                array[n11] ^= array3[array2[n5 + 4]];
                final int n12 = i + 5;
                array[n12] ^= array3[array2[n5 + 5]];
                final int n13 = i + 6;
                array[n13] ^= array3[array2[n5 + 6]];
                final int n14 = i + 7;
                array[n14] ^= array3[array2[n5 + 7]];
                final int n15 = i + 8;
                array[n15] ^= array3[array2[n5 + 8]];
                final int n16 = i + 9;
                array[n16] ^= array3[array2[n5 + 9]];
                final int n17 = i + 10;
                array[n17] ^= array3[array2[n5 + 10]];
                final int n18 = i + 11;
                array[n18] ^= array3[array2[n5 + 11]];
                final int n19 = i + 12;
                array[n19] ^= array3[array2[n5 + 12]];
                final int n20 = i + 13;
                array[n20] ^= array3[array2[n5 + 13]];
                final int n21 = i + 14;
                array[n21] ^= array3[array2[n5 + 14]];
                final int n22 = i + 15;
                array[n22] ^= array3[array2[n5 + 15]];
                i += n4;
                n5 += n4;
            }
            while (i < n6) {
                final int n23 = i;
                array[n23] ^= array3[array2[n5]];
                ++i;
                ++n5;
            }
        }
        else {
            final int n24 = this.gf_log[c];
            while (i < n6) {
                final char c2;
                if ((c2 = array2[n5]) != '\0') {
                    final int n25 = i;
                    array[n25] ^= this.gf_exp[n24 + this.gf_log[c2]];
                }
                ++i;
                ++n5;
            }
        }
    }
    
    public final void addMul(final byte[] array, final int n, final byte[] array2, final int n2, final byte b, final int n3) {
        if (b == 0) {
            return;
        }
        final int n4 = 16;
        int i = n;
        int n5 = n2;
        final int n6 = n + n3;
        final char[] array3 = this.gf_mul_table[b & 0xFF];
        while (i < n6) {
            if (n6 - i <= n4) {
                break;
            }
            final int n7 = i;
            array[n7] ^= (byte)array3[array2[n5] & 0xFF];
            final int n8 = i + 1;
            array[n8] ^= (byte)array3[array2[n5 + 1] & 0xFF];
            final int n9 = i + 2;
            array[n9] ^= (byte)array3[array2[n5 + 2] & 0xFF];
            final int n10 = i + 3;
            array[n10] ^= (byte)array3[array2[n5 + 3] & 0xFF];
            final int n11 = i + 4;
            array[n11] ^= (byte)array3[array2[n5 + 4] & 0xFF];
            final int n12 = i + 5;
            array[n12] ^= (byte)array3[array2[n5 + 5] & 0xFF];
            final int n13 = i + 6;
            array[n13] ^= (byte)array3[array2[n5 + 6] & 0xFF];
            final int n14 = i + 7;
            array[n14] ^= (byte)array3[array2[n5 + 7] & 0xFF];
            final int n15 = i + 8;
            array[n15] ^= (byte)array3[array2[n5 + 8] & 0xFF];
            final int n16 = i + 9;
            array[n16] ^= (byte)array3[array2[n5 + 9] & 0xFF];
            final int n17 = i + 10;
            array[n17] ^= (byte)array3[array2[n5 + 10] & 0xFF];
            final int n18 = i + 11;
            array[n18] ^= (byte)array3[array2[n5 + 11] & 0xFF];
            final int n19 = i + 12;
            array[n19] ^= (byte)array3[array2[n5 + 12] & 0xFF];
            final int n20 = i + 13;
            array[n20] ^= (byte)array3[array2[n5 + 13] & 0xFF];
            final int n21 = i + 14;
            array[n21] ^= (byte)array3[array2[n5 + 14] & 0xFF];
            final int n22 = i + 15;
            array[n22] ^= (byte)array3[array2[n5 + 15] & 0xFF];
            i += n4;
            n5 += n4;
        }
        while (i < n6) {
            final int n23 = i;
            array[n23] ^= (byte)array3[array2[n5] & 0xFF];
            ++i;
            ++n5;
        }
    }
    
    public final void matMul(final char[] array, final char[] array2, final char[] array3, final int n, final int n2, final int n3) {
        this.matMul(array, 0, array2, 0, array3, 0, n, n2, n3);
    }
    
    public final void matMul(final char[] array, final int n, final char[] array2, final int n2, final char[] array3, final int n3, final int n4, final int n5, final int n6) {
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n6; ++j) {
                int n7 = i * n5;
                int n8 = j;
                char c = '\0';
                for (int k = 0; k < n5; ++k, ++n7, n8 += n6) {
                    c ^= this.mul(array[n + n7], array2[n2 + n8]);
                }
                array3[n3 + (i * n6 + j)] = c;
            }
        }
    }
    
    public static final boolean isIdentity(final char[] array, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == j && array[n2] != '\u0001') || (i != j && array[n2] != '\0')) {
                    return false;
                }
                ++n2;
            }
        }
        return true;
    }
    
    public final void invertMatrix(final char[] array, final int n) throws IllegalArgumentException {
        final int[] array2 = new int[n];
        final int[] array3 = new int[n];
        final int[] array4 = new int[n];
        final char[] gfMatrix = createGFMatrix(1, n);
        createGFMatrix(1, n);
        for (int i = 0; i < n; ++i) {
            int n2 = -1;
            int n3 = -1;
            int n4 = 0;
            if (array4[i] != 1 && array[i * n + i] != '\0') {
                n2 = i;
                n3 = i;
                n4 = 1;
            }
            Label_0172: {
                if (n4 == 0) {
                    for (int j = 0; j < n; ++j) {
                        if (array4[j] != 1) {
                            for (int k = 0; k < n; ++k) {
                                if (array4[k] == 0) {
                                    if (array[j * n + k] != '\0') {
                                        n2 = j;
                                        n3 = k;
                                        n4 = 1;
                                        break Label_0172;
                                    }
                                }
                                else if (array4[k] > 1) {
                                    throw new IllegalArgumentException("singular matrix");
                                }
                            }
                        }
                    }
                }
            }
            if (n4 == 0 && n3 == -1) {
                throw new IllegalArgumentException("XXX pivot not found!");
            }
            ++array4[n3];
            if (n2 != n3) {
                for (int l = 0; l < n; ++l) {
                    final char c = array[n2 * n + l];
                    array[n2 * n + l] = array[n3 * n + l];
                    array[n3 * n + l] = c;
                }
            }
            array3[i] = n2;
            array2[i] = n3;
            final int n5 = n3 * n;
            final char c2 = array[n5 + n3];
            if (c2 == '\0') {
                throw new IllegalArgumentException("singular matrix 2");
            }
            if (c2 != '\u0001') {
                final char c3 = this.inverse[c2];
                array[n5 + n3] = '\u0001';
                for (int n6 = 0; n6 < n; ++n6) {
                    array[n5 + n6] = this.mul(c3, array[n5 + n6]);
                }
            }
            gfMatrix[n3] = '\u0001';
            if (!ByteArrayUtil.equal(array, n5, gfMatrix, 0, n)) {
                for (int n7 = 0, n8 = 0; n8 < n; ++n8, n7 += n) {
                    if (n8 != n3) {
                        final char c4 = array[n7 + n3];
                        array[n7 + n3] = '\0';
                        this.addMul(array, n7, array, n5, c4, n);
                    }
                }
            }
            gfMatrix[n3] = '\0';
        }
        for (int n9 = n - 1; n9 >= 0; --n9) {
            if (array3[n9] < 0 || array3[n9] >= n) {
                System.err.println("AARGH, indxr[col] " + array3[n9]);
            }
            else if (array2[n9] < 0 || array2[n9] >= n) {
                System.err.println("AARGH, indxc[col] " + array2[n9]);
            }
            else if (array3[n9] != array2[n9]) {
                for (int n10 = 0; n10 < n; ++n10) {
                    final char c5 = array[n10 * n + array2[n9]];
                    array[n10 * n + array2[n9]] = array[n10 * n + array3[n9]];
                    array[n10 * n + array3[n9]] = c5;
                }
            }
        }
    }
    
    public final void invertVandermonde(final char[] array, final int n) {
        if (n == 1) {
            return;
        }
        final char[] gfMatrix = createGFMatrix(1, n);
        final char[] gfMatrix2 = createGFMatrix(1, n);
        final char[] gfMatrix3 = createGFMatrix(1, n);
        for (int n2 = 1, i = 0; i < n; ++i, n2 += n) {
            gfMatrix[i] = '\0';
            gfMatrix3[i] = array[n2];
        }
        gfMatrix[n - 1] = gfMatrix3[0];
        for (int j = 1; j < n; ++j) {
            final char c = gfMatrix3[j];
            for (int k = n - 1 - (j - 1); k < n - 1; ++k) {
                final char[] array2 = gfMatrix;
                final int n3 = k;
                array2[n3] ^= this.mul(c, gfMatrix[k + 1]);
            }
            final char[] array3 = gfMatrix;
            final int n4 = n - 1;
            array3[n4] ^= c;
        }
        for (int l = 0; l < n; ++l) {
            final char c2 = gfMatrix3[l];
            char c3 = '\u0001';
            gfMatrix2[n - 1] = '\u0001';
            for (int n5 = n - 2; n5 >= 0; --n5) {
                gfMatrix2[n5] = (char)(gfMatrix[n5 + 1] ^ this.mul(c2, gfMatrix2[n5 + 1]));
                c3 = (char)(this.mul(c2, c3) ^ gfMatrix2[n5]);
            }
            for (int n6 = 0; n6 < n; ++n6) {
                array[n6 * n + l] = this.mul(this.inverse[c3], gfMatrix2[n6]);
            }
        }
    }
    
    public final char[] createEncodeMatrix(final int n, final int n2) {
        if (n > this.gfSize + 1 || n2 > this.gfSize + 1 || n > n2) {
            throw new IllegalArgumentException("Invalid parameters n=" + n2 + ",k=" + n + ",gfSize=" + this.gfSize);
        }
        final char[] gfMatrix = createGFMatrix(n2, n);
        final char[] gfMatrix2 = createGFMatrix(n2, n);
        gfMatrix2[0] = '\u0001';
        for (int n3 = n, i = 0; i < n2 - 1; ++i, n3 += n) {
            for (int j = 0; j < n; ++j) {
                gfMatrix2[n3 + j] = this.gf_exp[this.modnn(i * j)];
            }
        }
        this.invertVandermonde(gfMatrix2, n);
        this.matMul(gfMatrix2, n * n, gfMatrix2, 0, gfMatrix, n * n, n2 - n, n, n);
        ByteArrayUtil.bzero(gfMatrix, 0, n * n);
        for (int n4 = 0, k = 0; k < n; ++k, n4 += n + 1) {
            gfMatrix[n4] = '\u0001';
        }
        return gfMatrix;
    }
    
    protected final char[] createDecodeMatrix(final char[] array, final int[] array2, final int n, final int n2) {
        final char[] gfMatrix = createGFMatrix(n, n);
        for (int i = 0, n3 = 0; i < n; ++i, n3 += n) {
            System.arraycopy(array, array2[i] * n, gfMatrix, n3, n);
        }
        this.invertMatrix(gfMatrix, n);
        return gfMatrix;
    }
    
    static {
        prim_polys = new String[] { null, null, "111", "1101", "11001", "101001", "1100001", "10010001", "101110001", "1000100001", "10010000001", "101000000001", "1100101000001", "11011000000001", "110000100010001", "1100000000000001", "11010000000010001" };
    }
}

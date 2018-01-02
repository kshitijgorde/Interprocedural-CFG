// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.sec.algorithms;

import java.util.Random;

public class AES
{
    public static final String Ident = "$Id: AES.java,v 1.7 2011/02/05 01:07:28 blm Exp $";
    public static final int MODE_ECB = 1;
    public static final int MODE_CBC = 2;
    public static final int PAD_NONE = 1;
    public static final int PAD_PKCS7 = 2;
    public static final int PAD_ISO10126 = 3;
    public static final int PAD_RANDOM = 4;
    public static final int BLOCK_SIZE = 16;
    private int padding;
    private int Nb;
    private int Nr;
    private int[] S_Box;
    private int[] InvS_Box;
    private byte[] IV;
    private int[] dot_2;
    private int[] dot_3;
    private int[] dot_9;
    private int[] dot_b;
    private int[] dot_d;
    private int[] dot_e;
    private int[][] w;
    private Random padGen;
    private byte[] tempBlock;
    
    public byte[] arrayencrypt(final byte[] array) {
        return this.arrayencrypt(array, 0, null, 0, array.length);
    }
    
    public byte[] arrayencrypt(final byte[] array, final byte[] array2) {
        return this.arrayencrypt(array, 0, array2, 0, array.length);
    }
    
    public byte[] arrayencrypt(byte[] addPadding, int n, byte[] array, int n2, int n3) {
        byte b = 0;
        final int padding = this.padding;
        if (n < 0) {
            n += addPadding.length;
        }
        if (n3 < 0) {
            n3 += addPadding.length - n;
        }
        if (padding != 1) {
            b = (byte)(16 - n3 % 16);
        }
        else if (n3 % 16 != 0) {
            throw new IllegalArgumentException("length isn't a multiple of the block size");
        }
        if (n2 < 0) {
            if (array == null) {
                n2 += n3 + b;
            }
            else {
                n2 += array.length;
            }
        }
        if (addPadding == array && n < n2 && n2 < n + n3) {
            throw new IllegalArgumentException("src and dst overlap");
        }
        if (n3 > 0) {
            final byte b2 = addPadding[n];
            final byte b3 = addPadding[n + n3 - 1];
        }
        n3 += b;
        if (array == null) {
            array = new byte[n2 + n3];
        }
        else if (n3 > 0) {
            final byte b4 = array[n2];
            final byte b5 = array[n2 + n3 - 1];
        }
        int n4 = 0;
        byte[] iv = this.IV;
        final boolean b6 = iv != null;
        for (int i = 0; i < n3; i += 16) {
            if (padding != 1 && i + 16 == n3) {
                addPadding = this.addPadding(addPadding, n + i, b);
                n = -i;
            }
            if (b6) {
                int n5 = 0;
                do {
                    array[n2 + i + n5] = (byte)(addPadding[n + i + n5] ^ iv[n4 + n5]);
                } while (++n5 < 16);
                iv = array;
                n4 = n2 + i;
                this.encryptBlock(array, n2 + i, array, n2 + i);
            }
            else {
                this.encryptBlock(addPadding, n + i, array, n2 + i);
            }
        }
        if (b6) {
            System.arraycopy(iv, n4, this.IV, 0, this.IV.length);
        }
        return array;
    }
    
    public byte[] decrypt(final byte[] array, int n, byte[] array2, int n2, final int n3) {
        final boolean b = array2 == null;
        if (array == array2 && n < n2 && n2 < n + n3) {
            throw new IllegalArgumentException("src and dst overlap");
        }
        if (n < 0) {
            n += array.length;
        }
        if (n2 < 0) {
            if (array2 == null) {
                n2 += n3;
            }
            else {
                n2 += array2.length;
            }
        }
        if (n3 > 0) {
            final byte b2 = array[n];
            final byte b3 = array[n + n3 - 1];
        }
        if (b) {
            array2 = new byte[n2 + n3];
        }
        else if (n3 > 0) {
            final byte b4 = array2[n2];
            final byte b5 = array2[n2 + n3 - 1];
        }
        int n4 = 0;
        byte[] iv = this.IV;
        final boolean b6 = iv != null;
        for (int i = 0; i < n3; i += 16) {
            this.decryptBlock(array, n + i, array2, n2 + i);
            final byte[] array3 = array2;
            final int n5 = n2 + i;
            if (b6) {
                int n6 = 0;
                do {
                    final byte[] array4 = array3;
                    final int n7 = n5 + n6;
                    array4[n7] ^= iv[n4 + n6];
                } while (++n6 < 16);
                iv = array;
                n4 = n + i;
            }
        }
        if (b6) {
            System.arraycopy(iv, n4, this.IV = new byte[16], 0, this.IV.length);
        }
        return array2;
    }
    
    private int xtime(final int n) {
        if ((n & 0x80) == 0x80) {
            return n << 1 ^ 0x11B;
        }
        return n << 1;
    }
    
    public static byte[] decrypt(final byte[] array, final int n, final byte[] array2, final int n2, final byte[] array3, final byte[] array4) {
        return crypt(array, n, array2, n2, array3, array4, false);
    }
    
    public void wipe() {
        final boolean b = false;
        this.Nr = (b ? 1 : 0);
        this.Nb = (b ? 1 : 0);
        this.padding = 3;
        final int[][] w = this.w;
        for (int i = 0; i < w.length; ++i) {
            for (int j = 0; j < w[i].length; ++j) {
                w[i][j] = 0;
            }
            w[i] = null;
        }
        this.w = null;
    }
    
    private void encryptBlock(final byte[] array, final int n, final byte[] array2, final int n2) {
        final int nr = this.Nr;
        final int[][] packBytes = this.packBytes(array, n);
        this.AddRoundKey(packBytes, 0);
        for (int i = 1; i < nr; ++i) {
            this.SubBytes(packBytes);
            this.ShiftRows(packBytes);
            this.MixColumn(packBytes);
            this.AddRoundKey(packBytes, i);
        }
        this.SubBytes(packBytes);
        this.ShiftRows(packBytes);
        this.AddRoundKey(packBytes, nr);
        this.unpackBytes(packBytes, array2, n2);
    }
    
    private void InvShiftRows(final int[][] array) {
        final int[] array2 = array[1];
        final int n = array2[3];
        array2[3] = array2[2];
        array2[2] = array2[1];
        array2[1] = array2[0];
        array2[0] = n;
        final int[] array3 = array[2];
        final int n2 = array3[2];
        final int n3 = array3[3];
        array3[3] = array3[1];
        array3[2] = array3[0];
        array3[1] = n3;
        array3[0] = n2;
        final int[] array4 = array[3];
        final int n4 = array4[1];
        final int n5 = array4[2];
        final int n6 = array4[3];
        array4[3] = array4[0];
        array4[2] = n6;
        array4[1] = n5;
        array4[0] = n4;
    }
    
    public byte[] finishDecrypt(final byte[] array, int n) {
        final int n2 = 16;
        if (n < 0) {
            n += array.length;
        }
        final byte b = array[n];
        final byte b2 = array[n + n2 - 1];
        final byte[] iv = this.IV;
        final byte[] tempBlock = this.tempBlock;
        final boolean b3 = iv != null;
        this.decryptBlock(array, n, tempBlock, 0);
        if (b3) {
            int n3 = 0;
            do {
                final byte[] array2 = tempBlock;
                final int n4 = n3;
                array2[n4] ^= iv[n3];
            } while (++n3 < 16);
        }
        final byte[] array3 = new byte[n2 - this.padBytes(tempBlock, 0)];
        System.arraycopy(tempBlock, 0, array3, 0, array3.length);
        return array3;
    }
    
    public static byte[] encrypt(final byte[] array, final int n, final byte[] array2, final int n2, final byte[] array3, final byte[] array4) {
        return crypt(array, n, array2, n2, array3, array4, true);
    }
    
    private void SubBytes(final int[][] array) {
        final int[] s_Box = this.S_Box;
        final int[] array2 = array[0];
        array2[0] = s_Box[array2[0]];
        array2[1] = s_Box[array2[1]];
        array2[2] = s_Box[array2[2]];
        array2[3] = s_Box[array2[3]];
        final int[] array3 = array[1];
        array3[0] = s_Box[array3[0]];
        array3[1] = s_Box[array3[1]];
        array3[2] = s_Box[array3[2]];
        array3[3] = s_Box[array3[3]];
        final int[] array4 = array[2];
        array4[0] = s_Box[array4[0]];
        array4[1] = s_Box[array4[1]];
        array4[2] = s_Box[array4[2]];
        array4[3] = s_Box[array4[3]];
        final int[] array5 = array[3];
        array5[0] = s_Box[array5[0]];
        array5[1] = s_Box[array5[1]];
        array5[2] = s_Box[array5[2]];
        array5[3] = s_Box[array5[3]];
    }
    
    public int padBytes(final byte[] array, final int n) {
        return this.padBytes(array, n, 16);
    }
    
    public int padBytes(final byte[] array, final int n, final int n2) {
        int n3 = (this.padding == 4) ? 0 : array[n2 - 1];
        if (n3 < 0 || n3 > 16) {
            n3 = 0;
        }
        return n3;
    }
    
    public AES(final byte[] array) {
        this(array, 1, null, 1);
    }
    
    public AES(final byte[] array, final int n) {
        this(array, n, null, 1);
    }
    
    public AES(final byte[] array, final int n, final int n2) {
        this(array, n, null, n2);
    }
    
    public AES(final byte[] array, final int n, final byte[] array2) {
        this(array, n, array2, 1);
    }
    
    public AES(final byte[] array, final int n, byte[] array2, final int padding) {
        this.padding = 3;
        this.Nb = 0;
        this.Nr = 0;
        this.S_Box = null;
        this.InvS_Box = null;
        this.IV = null;
        this.dot_2 = null;
        this.dot_3 = null;
        this.dot_9 = null;
        this.dot_b = null;
        this.dot_d = null;
        this.dot_e = null;
        this.w = null;
        this.padGen = null;
        this.tempBlock = new byte[16];
        switch (array.length) {
            default: {
                throw new IllegalArgumentException("invalid key size");
            }
            case 16:
            case 24:
            case 32: {
                switch (n) {
                    case 1: {
                        array2 = null;
                        break;
                    }
                    case 2: {
                        if (array2.length != 16) {
                            throw new IllegalArgumentException("invalid initialization vector length");
                        }
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("unknown mode");
                    }
                }
                switch (padding) {
                    default: {
                        throw new IllegalArgumentException("unknown padding");
                    }
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        final int nb = 4;
                        final int n2 = array.length / 4;
                        final int nr = n2 + 6;
                        final int[] invS_Box = { 82, 9, 106, 213, 48, 54, 165, 56, 191, 64, 163, 158, 129, 243, 215, 251, 124, 227, 57, 130, 155, 47, 255, 135, 52, 142, 67, 68, 196, 222, 233, 203, 84, 123, 148, 50, 166, 194, 35, 61, 238, 76, 149, 11, 66, 250, 195, 78, 8, 46, 161, 102, 40, 217, 36, 178, 118, 91, 162, 73, 109, 139, 209, 37, 114, 248, 246, 100, 134, 104, 152, 22, 212, 164, 92, 204, 93, 101, 182, 146, 108, 112, 72, 80, 253, 237, 185, 218, 94, 21, 70, 87, 167, 141, 157, 132, 144, 216, 171, 0, 140, 188, 211, 10, 247, 228, 88, 5, 184, 179, 69, 6, 208, 44, 30, 143, 202, 63, 15, 2, 193, 175, 189, 3, 1, 19, 138, 107, 58, 145, 17, 65, 79, 103, 220, 234, 151, 242, 207, 206, 240, 180, 230, 115, 150, 172, 116, 34, 231, 173, 53, 133, 226, 249, 55, 232, 28, 117, 223, 110, 71, 241, 26, 113, 29, 41, 197, 137, 111, 183, 98, 14, 170, 24, 190, 27, 252, 86, 62, 75, 198, 210, 121, 32, 154, 219, 192, 254, 120, 205, 90, 244, 31, 221, 168, 51, 136, 7, 199, 49, 177, 18, 16, 89, 39, 128, 236, 95, 96, 81, 127, 169, 25, 181, 74, 13, 45, 229, 122, 159, 147, 201, 156, 239, 160, 224, 59, 77, 174, 42, 245, 176, 200, 235, 187, 60, 131, 83, 153, 97, 23, 43, 4, 126, 186, 119, 214, 38, 225, 105, 20, 99, 85, 33, 12, 125 };
                        final int[] s_Box = new int[256];
                        final int[] dot_2 = new int[256];
                        final int[] dot_3 = new int[256];
                        final int[] dot_4 = new int[256];
                        final int[] dot_b = new int[256];
                        final int[] dot_d = new int[256];
                        final int[] dot_e = new int[256];
                        int n3 = 0;
                        do {
                            s_Box[invS_Box[n3]] = n3;
                            final int xtime = this.xtime(n3);
                            final int xtime2 = this.xtime(xtime);
                            final int xtime3 = this.xtime(xtime2);
                            dot_3[n3] = ((dot_2[n3] = xtime) ^ n3);
                            dot_4[n3] = (xtime3 ^ n3);
                            dot_b[n3] = (xtime3 ^ xtime ^ n3);
                            dot_d[n3] = (xtime3 ^ xtime2 ^ n3);
                            dot_e[n3] = (xtime3 ^ xtime2 ^ xtime);
                        } while (++n3 < 256);
                        final int n4 = nb * (nr + 1);
                        final int[] array3 = new int[n4];
                        array3[0] = 0;
                        for (int i = n2, xtime4 = 1; i < n4; i += n2, xtime4 = this.xtime(xtime4)) {
                            array3[i] = xtime4;
                        }
                        final int[][] w = new int[n4][];
                        for (int j = 0, n5 = 0; j < n2; ++j, n5 += 4) {
                            w[j] = new int[] { array[n5] & 0xFF, array[n5 + 1] & 0xFF, array[n5 + 2] & 0xFF, array[n5 + 3] & 0xFF };
                        }
                        final int[] array4 = new int[4];
                        int[] array5 = w[n2 - 1];
                        for (int k = n2, n6 = 0; k < n4; ++k, ++n6) {
                            if (k % n2 == 0) {
                                array4[0] = (s_Box[array5[1]] ^ array3[k]);
                                array4[1] = s_Box[array5[2]];
                                array4[2] = s_Box[array5[3]];
                                array4[3] = s_Box[array5[0]];
                                array5 = array4;
                            }
                            else if (n2 > 6 && k % n2 == 4) {
                                array4[0] = s_Box[array5[0]];
                                array4[1] = s_Box[array5[1]];
                                array4[2] = s_Box[array5[2]];
                                array4[3] = s_Box[array5[3]];
                                array5 = array4;
                            }
                            final int[][] array6 = w;
                            final int n7 = k;
                            final int[] array7 = { w[n6][0] ^ array5[0], w[n6][1] ^ array5[1], w[n6][2] ^ array5[2], w[n6][3] ^ array5[3] };
                            array6[n7] = array7;
                            array5 = array7;
                        }
                        this.padding = padding;
                        this.Nb = nb;
                        this.Nr = nr;
                        this.S_Box = s_Box;
                        this.InvS_Box = invS_Box;
                        this.dot_2 = dot_2;
                        this.dot_3 = dot_3;
                        this.dot_9 = dot_4;
                        this.dot_b = dot_b;
                        this.dot_d = dot_d;
                        this.dot_e = dot_e;
                        this.w = w;
                        if (array2 != null) {
                            this.IV = array2.clone();
                        }
                        return;
                    }
                }
                break;
            }
        }
    }
    
    public Random getRandomPaddingGenerator() {
        return this.padGen;
    }
    
    public void setRandomPaddingGenerator(final Random padGen) {
        this.padGen = padGen;
    }
    
    public byte[] arraydecrypt(final byte[] array) {
        return this.arraydecrypt(array, 0, null, 0, array.length);
    }
    
    public byte[] arraydecrypt(final byte[] array, final byte[] array2) {
        return this.arraydecrypt(array, 0, array2, 0, array.length);
    }
    
    public byte[] arraydecrypt(final byte[] array, int n, byte[] array2, int n2, int n3) {
        int delPadding = 0;
        final int padding = this.padding;
        final boolean b = array2 == null;
        if (n < 0) {
            n += array.length;
        }
        if (n3 < 0) {
            n3 += array.length - n;
        }
        if (n3 % 16 != 0) {
            throw new IllegalArgumentException("length isn't a multiple of the block size");
        }
        if (n2 < 0) {
            if (array2 == null) {
                n2 += n3;
            }
            else {
                n2 += array2.length;
            }
        }
        if (array == array2 && n < n2 && n2 < n + n3) {
            throw new IllegalArgumentException("src and dst overlap");
        }
        if (n3 > 0) {
            final byte b2 = array[n];
            final byte b3 = array[n + n3 - 1];
        }
        if (b) {
            array2 = new byte[n2 + n3];
        }
        else if (n3 > 0) {
            final byte b4 = array2[n2];
            final byte b5 = array2[n2 + n3 - 1];
        }
        int n4 = 0;
        byte[] iv = this.IV;
        final boolean b6 = iv != null;
        for (int i = 0; i < n3; i += 16) {
            byte[] tempBlock;
            int n5;
            if (padding != 1 && i + 16 == n3) {
                this.decryptBlock(array, n + i, this.tempBlock, 0);
                tempBlock = this.tempBlock;
                n5 = 0;
            }
            else {
                this.decryptBlock(array, n + i, array2, n2 + i);
                tempBlock = array2;
                n5 = n2 + i;
            }
            if (b6) {
                int n6 = 0;
                do {
                    final byte[] array3 = tempBlock;
                    final int n7 = n5 + n6;
                    array3[n7] ^= iv[n4 + n6];
                } while (++n6 < 16);
                iv = array;
                n4 = n + i;
            }
            if (tempBlock == this.tempBlock) {
                delPadding = this.delPadding(tempBlock, 0, array2, n2 + i);
            }
        }
        if (delPadding > 0 && b) {
            final byte[] array4 = new byte[n2 + n3 - delPadding];
            System.arraycopy(array2, n2, array4, n2, n3 - delPadding);
            array2 = array4;
        }
        if (b6) {
            System.arraycopy(iv, n4, this.IV = new byte[16], 0, this.IV.length);
        }
        return array2;
    }
    
    private void InvSubBytes(final int[][] array) {
        final int[] invS_Box = this.InvS_Box;
        final int[] array2 = array[0];
        array2[0] = invS_Box[array2[0]];
        array2[1] = invS_Box[array2[1]];
        array2[2] = invS_Box[array2[2]];
        array2[3] = invS_Box[array2[3]];
        final int[] array3 = array[1];
        array3[0] = invS_Box[array3[0]];
        array3[1] = invS_Box[array3[1]];
        array3[2] = invS_Box[array3[2]];
        array3[3] = invS_Box[array3[3]];
        final int[] array4 = array[2];
        array4[0] = invS_Box[array4[0]];
        array4[1] = invS_Box[array4[1]];
        array4[2] = invS_Box[array4[2]];
        array4[3] = invS_Box[array4[3]];
        final int[] array5 = array[3];
        array5[0] = invS_Box[array5[0]];
        array5[1] = invS_Box[array5[1]];
        array5[2] = invS_Box[array5[2]];
        array5[3] = invS_Box[array5[3]];
    }
    
    private void InvMixColumn(final int[][] array) {
        final int[] array2 = array[0];
        final int[] array3 = array[1];
        final int[] array4 = array[2];
        final int[] array5 = array[3];
        final int[] dot_9 = this.dot_9;
        final int[] dot_b = this.dot_b;
        final int[] dot_d = this.dot_d;
        final int[] dot_e = this.dot_e;
        for (int i = 0; i < this.Nb; ++i) {
            final int n = dot_e[array2[i]] ^ dot_b[array3[i]] ^ dot_d[array4[i]] ^ dot_9[array5[i]];
            final int n2 = dot_9[array2[i]] ^ dot_e[array3[i]] ^ dot_b[array4[i]] ^ dot_d[array5[i]];
            final int n3 = dot_d[array2[i]] ^ dot_9[array3[i]] ^ dot_e[array4[i]] ^ dot_b[array5[i]];
            final int n4 = dot_b[array2[i]] ^ dot_d[array3[i]] ^ dot_9[array4[i]] ^ dot_e[array5[i]];
            array2[i] = n;
            array3[i] = n2;
            array4[i] = n3;
            array5[i] = n4;
        }
    }
    
    private void unpackBytes(final int[][] array, final byte[] array2, int n) {
        for (int nb = this.Nb, i = 0; i < nb; array2[n++] = (byte)array[3][i++]) {
            array2[n++] = (byte)array[0][i];
            array2[n++] = (byte)array[1][i];
            array2[n++] = (byte)array[2][i];
        }
    }
    
    public static byte[] crypt(final byte[] array, final int n, final byte[] array2, final int n2, final byte[] array3, byte[] array4, final boolean b) {
        final AES aes = new AES(normalizeKey(array, 0), n, array2, n2);
        array4 = (b ? aes.arrayencrypt(array3, array4) : aes.arraydecrypt(array3, array4));
        aes.wipe();
        return array4;
    }
    
    public static byte[] normalizeKey(byte[] array, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("key size must be non-negative");
        }
        if (n != 0 && n < array.length) {
            final byte[] array2 = new byte[n];
            System.arraycopy(array, 0, array2, 0, array2.length);
            array = array2;
        }
        final int length = array.length;
        if (length != 16 && length != 24 && length != 32) {
            final int n2 = (length < 16) ? 16 : ((length < 24) ? 24 : 32);
            final byte[] array3 = new byte[n2];
            int i = (n2 > length) ? length : n2;
            int n3 = 0;
            System.arraycopy(array, 0, array3, 0, i);
            while (i < n2) {
                int n4 = n2 - i;
                if (n4 > length) {
                    n4 = length;
                }
                else {
                    n3 = (length - n4) / 2;
                }
                for (int j = 0, n5 = n4 - 1; j < n4; ++j, --n5) {
                    array3[i + j] = (byte)(array[n3 + n5] ^ 0xA5);
                }
                i += n4;
            }
            array = array3;
        }
        return array;
    }
    
    private byte[] addPadding(final byte[] array, final int n, final byte b) {
        this.addPadding(array, n, this.tempBlock, 0, 16 - b, this.padding);
        return this.tempBlock;
    }
    
    public int addPadding(final byte[] array, final int n, final byte[] array2, int n2, final int n3, final int n4) {
        final byte b = (byte)(16 - n3 % 16);
        if (n3 > 0) {
            if (array != array2 || n != n2) {
                System.arraycopy(array, n, array2, n2, n3);
            }
            n2 += n3;
        }
        switch (n4) {
            case 2: {
                for (byte b2 = 0; b2 < b; ++b2) {
                    array2[n2 + b2] = b;
                }
                break;
            }
            case 3:
            case 4: {
                if (this.padGen == null) {
                    this.padGen = new Random((long)(Math.random() * 9.223372036854776E18));
                }
                final byte[] array3 = new byte[b];
                this.padGen.nextBytes(array3);
                System.arraycopy(array3, 0, array2, n2, array3.length);
                if (n4 == 3) {
                    array2[n2 + array3.length - 1] = b;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    public int delPadding(final byte[] array, final int n, final byte[] array2, final int n2) {
        final int padBytes = this.padBytes(array, n, array.length);
        final int n3 = array.length - n - padBytes;
        if (n3 > 0) {
            System.arraycopy(array, n, array2, n2, n3);
        }
        return padBytes;
    }
    
    private int[][] packBytes(final byte[] array, int n) {
        int nb;
        int i;
        int[][] array2;
        for (nb = this.Nb, i = 0, array2 = new int[][] { new int[nb], new int[nb], new int[nb], new int[nb] }; i < nb; array2[3][i++] = (array[n++] & 0xFF)) {
            array2[0][i] = (array[n++] & 0xFF);
            array2[1][i] = (array[n++] & 0xFF);
            array2[2][i] = (array[n++] & 0xFF);
        }
        return array2;
    }
    
    private void decryptBlock(final byte[] array, final int n, final byte[] array2, final int n2) {
        final int nr = this.Nr;
        final int[][] packBytes = this.packBytes(array, n);
        this.AddRoundKey(packBytes, nr);
        for (int i = nr - 1; i > 0; --i) {
            this.InvShiftRows(packBytes);
            this.InvSubBytes(packBytes);
            this.AddRoundKey(packBytes, i);
            this.InvMixColumn(packBytes);
        }
        this.InvShiftRows(packBytes);
        this.InvSubBytes(packBytes);
        this.AddRoundKey(packBytes, 0);
        this.unpackBytes(packBytes, array2, n2);
    }
    
    private void AddRoundKey(final int[][] array, int n) {
        final int[][] w = this.w;
        final int[] array2 = array[0];
        final int[] array3 = array[1];
        final int[] array4 = array[2];
        final int[] array5 = array[3];
        n *= this.Nb;
        final int[] array6 = w[n++];
        final int[] array7 = array2;
        final int n2 = 0;
        array7[n2] ^= array6[0];
        final int[] array8 = array3;
        final int n3 = 0;
        array8[n3] ^= array6[1];
        final int[] array9 = array4;
        final int n4 = 0;
        array9[n4] ^= array6[2];
        final int[] array10 = array5;
        final int n5 = 0;
        array10[n5] ^= array6[3];
        final int[] array11 = w[n++];
        final int[] array12 = array2;
        final int n6 = 1;
        array12[n6] ^= array11[0];
        final int[] array13 = array3;
        final int n7 = 1;
        array13[n7] ^= array11[1];
        final int[] array14 = array4;
        final int n8 = 1;
        array14[n8] ^= array11[2];
        final int[] array15 = array5;
        final int n9 = 1;
        array15[n9] ^= array11[3];
        final int[] array16 = w[n++];
        final int[] array17 = array2;
        final int n10 = 2;
        array17[n10] ^= array16[0];
        final int[] array18 = array3;
        final int n11 = 2;
        array18[n11] ^= array16[1];
        final int[] array19 = array4;
        final int n12 = 2;
        array19[n12] ^= array16[2];
        final int[] array20 = array5;
        final int n13 = 2;
        array20[n13] ^= array16[3];
        final int[] array21 = w[n];
        final int[] array22 = array2;
        final int n14 = 3;
        array22[n14] ^= array21[0];
        final int[] array23 = array3;
        final int n15 = 3;
        array23[n15] ^= array21[1];
        final int[] array24 = array4;
        final int n16 = 3;
        array24[n16] ^= array21[2];
        final int[] array25 = array5;
        final int n17 = 3;
        array25[n17] ^= array21[3];
    }
    
    private void ShiftRows(final int[][] array) {
        final int[] array2 = array[1];
        final int n = array2[0];
        array2[0] = array2[1];
        array2[1] = array2[2];
        array2[2] = array2[3];
        array2[3] = n;
        final int[] array3 = array[2];
        final int n2 = array3[0];
        final int n3 = array3[1];
        array3[0] = array3[2];
        array3[1] = array3[3];
        array3[2] = n2;
        array3[3] = n3;
        final int[] array4 = array[3];
        final int n4 = array4[0];
        final int n5 = array4[1];
        final int n6 = array4[2];
        array4[0] = array4[3];
        array4[1] = n4;
        array4[2] = n5;
        array4[3] = n6;
    }
    
    private void MixColumn(final int[][] array) {
        final int[] array2 = array[0];
        final int[] array3 = array[1];
        final int[] array4 = array[2];
        final int[] array5 = array[3];
        final int[] dot_2 = this.dot_2;
        final int[] dot_3 = this.dot_3;
        for (int i = 0; i < this.Nb; ++i) {
            final int n = dot_2[array2[i]] ^ dot_3[array3[i]] ^ array4[i] ^ array5[i];
            final int n2 = array2[i] ^ dot_2[array3[i]] ^ dot_3[array4[i]] ^ array5[i];
            final int n3 = array2[i] ^ array3[i] ^ dot_2[array4[i]] ^ dot_3[array5[i]];
            final int n4 = dot_3[array2[i]] ^ array3[i] ^ array4[i] ^ dot_2[array5[i]];
            array2[i] = n;
            array3[i] = n2;
            array4[i] = n3;
            array5[i] = n4;
        }
    }
}

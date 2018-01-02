// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;

public final class c
{
    private Mac a;
    private byte[] b;
    private byte[] c;
    private int[] d;
    private int[] e;
    
    public c() {
        this.b = new byte[32];
        this.c = new byte[132];
        this.d = new int[32];
        this.e = new int[32768];
        this.a = Mac.getInstance("HmacSHA256");
    }
    
    public final byte[] a(final byte[] array, int i) {
        System.arraycopy(array, 0, this.c, 0, 76);
        this.c[76] = (byte)i;
        this.c[77] = (byte)(i >> 8);
        this.c[78] = (byte)(i >> 16);
        this.c[79] = (byte)(i >> 24);
        this.a.init(new SecretKeySpec(this.c, 0, 80, "HmacSHA256"));
        this.c[80] = 0;
        this.c[81] = 0;
        this.c[82] = 0;
        for (int j = 0; j < 4; ++j) {
            this.c[83] = (byte)(j + 1);
            this.a.update(this.c, 0, 84);
            this.a.doFinal(this.b, 0);
            for (i = 0; i < 8; ++i) {
                this.d[(j << 3) + i] = ((this.b[i << 2] & 0xFF) | (this.b[(i << 2) + 1] & 0xFF) << 8 | (this.b[(i << 2) + 2] & 0xFF) << 16 | (this.b[(i << 2) + 3] & 0xFF) << 24);
            }
        }
        for (int k = 0; k < 1024; ++k) {
            System.arraycopy(this.d, 0, this.e, k << 5, 32);
            this.a(0, 16);
            this.a(16, 0);
        }
        for (int l = 0; l < 1024; ++l) {
            final int n = (this.d[16] & 0x3FF) << 5;
            int[] d;
            int n2;
            for (i = 0; i < 32; ++i) {
                d = this.d;
                n2 = i;
                d[n2] ^= this.e[n + i];
            }
            this.a(0, 16);
            this.a(16, 0);
        }
        for (int n3 = 0; n3 < 32; ++n3) {
            this.c[n3 << 2] = (byte)this.d[n3];
            this.c[(n3 << 2) + 1] = (byte)(this.d[n3] >> 8);
            this.c[(n3 << 2) + 2] = (byte)(this.d[n3] >> 16);
            this.c[(n3 << 2) + 3] = (byte)(this.d[n3] >> 24);
        }
        this.c[131] = 1;
        this.a.update(this.c, 0, 132);
        this.a.doFinal(this.b, 0);
        return this.b;
    }
    
    private void a(final int n, int n2) {
        final int[] d = this.d;
        final int n3 = d[n] ^ this.d[n2];
        d[n] = n3;
        int n4 = n3;
        final int[] d2 = this.d;
        final int n5 = n + 1;
        final int n6 = d2[n5] ^ this.d[n2 + 1];
        d2[n5] = n6;
        int n7 = n6;
        final int[] d3 = this.d;
        final int n8 = n + 2;
        final int n9 = d3[n8] ^ this.d[n2 + 2];
        d3[n8] = n9;
        int n10 = n9;
        final int[] d4 = this.d;
        final int n11 = n + 3;
        final int n12 = d4[n11] ^ this.d[n2 + 3];
        d4[n11] = n12;
        int n13 = n12;
        final int[] d5 = this.d;
        final int n14 = n + 4;
        final int n15 = d5[n14] ^ this.d[n2 + 4];
        d5[n14] = n15;
        int n16 = n15;
        final int[] d6 = this.d;
        final int n17 = n + 5;
        final int n18 = d6[n17] ^ this.d[n2 + 5];
        d6[n17] = n18;
        int n19 = n18;
        final int[] d7 = this.d;
        final int n20 = n + 6;
        final int n21 = d7[n20] ^ this.d[n2 + 6];
        d7[n20] = n21;
        int n22 = n21;
        final int[] d8 = this.d;
        final int n23 = n + 7;
        final int n24 = d8[n23] ^ this.d[n2 + 7];
        d8[n23] = n24;
        int n25 = n24;
        final int[] d9 = this.d;
        final int n26 = n + 8;
        final int n27 = d9[n26] ^ this.d[n2 + 8];
        d9[n26] = n27;
        int n28 = n27;
        final int[] d10 = this.d;
        final int n29 = n + 9;
        final int n30 = d10[n29] ^ this.d[n2 + 9];
        d10[n29] = n30;
        int n31 = n30;
        final int[] d11 = this.d;
        final int n32 = n + 10;
        final int n33 = d11[n32] ^ this.d[n2 + 10];
        d11[n32] = n33;
        int n34 = n33;
        final int[] d12 = this.d;
        final int n35 = n + 11;
        final int n36 = d12[n35] ^ this.d[n2 + 11];
        d12[n35] = n36;
        int n37 = n36;
        final int[] d13 = this.d;
        final int n38 = n + 12;
        final int n39 = d13[n38] ^ this.d[n2 + 12];
        d13[n38] = n39;
        int n40 = n39;
        final int[] d14 = this.d;
        final int n41 = n + 13;
        final int n42 = d14[n41] ^ this.d[n2 + 13];
        d14[n41] = n42;
        int n43 = n42;
        final int[] d15 = this.d;
        final int n44 = n + 14;
        final int n45 = d15[n44] ^ this.d[n2 + 14];
        d15[n44] = n45;
        int n46 = n45;
        final int[] d16 = this.d;
        final int n47 = n + 15;
        final int n48 = d16[n47] ^ this.d[n2 + 15];
        d16[n47] = n48;
        n2 = n48;
        for (int i = 0; i < 4; ++i) {
            final int n49 = n16 ^ Integer.rotateLeft(n4 + n40, 7);
            final int n50 = n28 ^ Integer.rotateLeft(n49 + n4, 9);
            final int n51 = n40 ^ Integer.rotateLeft(n50 + n49, 13);
            final int n52 = n4 ^ Integer.rotateLeft(n51 + n50, 18);
            final int n53 = n31 ^ Integer.rotateLeft(n19 + n7, 7);
            final int n54 = n43 ^ Integer.rotateLeft(n53 + n19, 9);
            final int n55 = n7 ^ Integer.rotateLeft(n54 + n53, 13);
            final int n56 = n19 ^ Integer.rotateLeft(n55 + n54, 18);
            final int n57 = n46 ^ Integer.rotateLeft(n34 + n22, 7);
            final int n58 = n10 ^ Integer.rotateLeft(n57 + n34, 9);
            final int n59 = n22 ^ Integer.rotateLeft(n58 + n57, 13);
            final int n60 = n34 ^ Integer.rotateLeft(n59 + n58, 18);
            final int n61 = n13 ^ Integer.rotateLeft(n2 + n37, 7);
            final int n62 = n25 ^ Integer.rotateLeft(n61 + n2, 9);
            final int n63 = n37 ^ Integer.rotateLeft(n62 + n61, 13);
            n2 ^= Integer.rotateLeft(n63 + n62, 18);
            n7 = (n55 ^ Integer.rotateLeft(n52 + n61, 7));
            n10 = (n58 ^ Integer.rotateLeft(n7 + n52, 9));
            n13 = (n61 ^ Integer.rotateLeft(n10 + n7, 13));
            n4 = (n52 ^ Integer.rotateLeft(n13 + n10, 18));
            n22 = (n59 ^ Integer.rotateLeft(n56 + n49, 7));
            n25 = (n62 ^ Integer.rotateLeft(n22 + n56, 9));
            n16 = (n49 ^ Integer.rotateLeft(n25 + n22, 13));
            n19 = (n56 ^ Integer.rotateLeft(n16 + n25, 18));
            n37 = (n63 ^ Integer.rotateLeft(n60 + n53, 7));
            n28 = (n50 ^ Integer.rotateLeft(n37 + n60, 9));
            n31 = (n53 ^ Integer.rotateLeft(n28 + n37, 13));
            n34 = (n60 ^ Integer.rotateLeft(n31 + n28, 18));
            n40 = (n51 ^ Integer.rotateLeft(n2 + n57, 7));
            n43 = (n54 ^ Integer.rotateLeft(n40 + n2, 9));
            n46 = (n57 ^ Integer.rotateLeft(n43 + n40, 13));
            n2 ^= Integer.rotateLeft(n46 + n43, 18);
        }
        final int[] d17 = this.d;
        d17[n] += n4;
        final int[] d18 = this.d;
        final int n64 = n + 1;
        d18[n64] += n7;
        final int[] d19 = this.d;
        final int n65 = n + 2;
        d19[n65] += n10;
        final int[] d20 = this.d;
        final int n66 = n + 3;
        d20[n66] += n13;
        final int[] d21 = this.d;
        final int n67 = n + 4;
        d21[n67] += n16;
        final int[] d22 = this.d;
        final int n68 = n + 5;
        d22[n68] += n19;
        final int[] d23 = this.d;
        final int n69 = n + 6;
        d23[n69] += n22;
        final int[] d24 = this.d;
        final int n70 = n + 7;
        d24[n70] += n25;
        final int[] d25 = this.d;
        final int n71 = n + 8;
        d25[n71] += n28;
        final int[] d26 = this.d;
        final int n72 = n + 9;
        d26[n72] += n31;
        final int[] d27 = this.d;
        final int n73 = n + 10;
        d27[n73] += n34;
        final int[] d28 = this.d;
        final int n74 = n + 11;
        d28[n74] += n37;
        final int[] d29 = this.d;
        final int n75 = n + 12;
        d29[n75] += n40;
        final int[] d30 = this.d;
        final int n76 = n + 13;
        d30[n76] += n43;
        final int[] d31 = this.d;
        final int n77 = n + 14;
        d31[n77] += n46;
        final int[] d32 = this.d;
        final int n78 = n + 15;
        d32[n78] += n2;
    }
}

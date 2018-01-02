// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class m
{
    int a;
    
    int a(final float[] array, final float[] array2, final int n, final float n2, final int n3, final float[] array3, final float[] array4, final af af) {
        final float[] array5 = new float[40];
        final float[] array6 = new float[616];
        if (n < 40) {
            for (int i = n; i < 40; ++i) {
                final int n4 = i;
                array2[n4] += n2 * array2[i - n];
            }
        }
        a(array2, array6);
        d.a(array2, array, array5);
        final int a = this.a(array5, array6, array2, array3, array4, af, n3);
        if (n < 40) {
            for (int j = n; j < 40; ++j) {
                final int n5 = j;
                array3[n5] += n2 * array3[j - n];
            }
        }
        return a;
    }
    
    static void a(final float[] array, final float[] array2) {
        final int n = 0;
        final int n2 = n + 8;
        final int n3 = n2 + 8;
        final int n4 = n3 + 8;
        final int n5 = n4 + 8;
        final int n6 = n5 + 8;
        final int n7 = n6 + 64;
        final int n8 = n7 + 64;
        final int n9 = n8 + 64;
        final int n10 = n9 + 64;
        final int n11 = n10 + 64;
        final int n12 = n11 + 64;
        final int n13 = n12 + 64;
        final int n14 = n13 + 64;
        int n15 = n + 8 - 1;
        int n16 = n2 + 8 - 1;
        int n17 = n3 + 8 - 1;
        int n18 = n4 + 8 - 1;
        int n19 = n5 + 8 - 1;
        int n20 = 0;
        float n21 = 0.0f;
        for (int i = 0; i < 8; ++i) {
            final float n22 = n21 + array[n20] * array[n20];
            ++n20;
            array2[n19--] = n22;
            final float n23 = n22 + array[n20] * array[n20];
            ++n20;
            array2[n18--] = n23;
            final float n24 = n23 + array[n20] * array[n20];
            ++n20;
            array2[n17--] = n24;
            final float n25 = n24 + array[n20] * array[n20];
            ++n20;
            array2[n16--] = n25;
            n21 = n25 + array[n20] * array[n20];
            ++n20;
            array2[n15--] = n21;
        }
        int n26 = 63;
        int n27 = n26 - 1;
        final int n28 = 9;
        final int n29 = 0;
        int n30 = n29 + 1;
        for (int j = 0; j < 8; ++j) {
            int n31 = n13 + n26;
            int n32 = n10 + n26;
            int n33 = n6 + n26;
            int n34 = n9 + n27;
            float n35 = 0.0f;
            int n36 = n29;
            int n37 = n30;
            for (int k = j + 1; k < 8; ++k) {
                final float n38 = n35 + array[n36] * array[n37];
                ++n36;
                ++n37;
                final float n39 = n38 + array[n36] * array[n37];
                ++n36;
                ++n37;
                array2[n31] = n39;
                final float n40 = n39 + array[n36] * array[n37];
                ++n36;
                ++n37;
                array2[n32] = n40;
                final float n41 = n40 + array[n36] * array[n37];
                ++n36;
                ++n37;
                array2[n33] = n41;
                n35 = n41 + array[n36] * array[n37];
                ++n36;
                ++n37;
                array2[n34] = n35;
                n31 -= n28;
                n32 -= n28;
                n33 -= n28;
                n34 -= n28;
            }
            final float n42 = n35 + array[n36] * array[n37];
            ++n36;
            ++n37;
            final float n43 = n42 + array[n36] * array[n37];
            ++n36;
            ++n37;
            array2[n31] = n43;
            final float n44 = n43 + array[n36] * array[n37];
            ++n36;
            ++n37;
            array2[n32] = n44;
            final float n45 = n44 + array[n36] * array[n37];
            ++n36;
            ++n37;
            array2[n33] = n45;
            n26 -= 8;
            --n27;
            n30 += 5;
        }
        final int n46 = 0;
        int n47 = n46 + 2;
        int n48 = 63;
        int n49 = n48 - 1;
        for (int l = 0; l < 8; ++l) {
            int n50 = n14 + n48;
            int n51 = n11 + n48;
            int n52 = n7 + n48;
            int n53 = n12 + n49;
            int n54 = n8 + n49;
            float n55 = 0.0f;
            int n56 = n46;
            int n57 = n47;
            for (int n58 = l + 1; n58 < 8; ++n58) {
                final float n59 = n55 + array[n56] * array[n57];
                ++n56;
                ++n57;
                array2[n50] = n59;
                final float n60 = n59 + array[n56] * array[n57];
                ++n56;
                ++n57;
                array2[n51] = n60;
                final float n61 = n60 + array[n56] * array[n57];
                ++n56;
                ++n57;
                array2[n52] = n61;
                final float n62 = n61 + array[n56] * array[n57];
                ++n56;
                ++n57;
                array2[n53] = n62;
                n55 = n62 + array[n56] * array[n57];
                ++n56;
                ++n57;
                array2[n54] = n55;
                n50 -= n28;
                n51 -= n28;
                n52 -= n28;
                n53 -= n28;
                n54 -= n28;
            }
            final float n63 = n55 + array[n56] * array[n57];
            ++n56;
            ++n57;
            array2[n50] = n63;
            final float n64 = n63 + array[n56] * array[n57];
            ++n56;
            ++n57;
            array2[n51] = n64;
            final float n65 = n64 + array[n56] * array[n57];
            ++n56;
            ++n57;
            array2[n52] = n65;
            n48 -= 8;
            --n49;
            n47 += 5;
        }
        final int n66 = 0;
        int n67 = n66 + 3;
        int n68 = 63;
        int n69 = n68 - 1;
        for (int n70 = 0; n70 < 8; ++n70) {
            int n71 = n12 + n68;
            int n72 = n8 + n68;
            int n73 = n14 + n69;
            int n74 = n11 + n69;
            int n75 = n7 + n69;
            int n76 = n66;
            int n77 = n67;
            float n78 = 0.0f;
            for (int n79 = n70 + 1; n79 < 8; ++n79) {
                final float n80 = n78 + array[n76] * array[n77];
                ++n76;
                ++n77;
                array2[n71] = n80;
                final float n81 = n80 + array[n76] * array[n77];
                ++n76;
                ++n77;
                array2[n72] = n81;
                final float n82 = n81 + array[n76] * array[n77];
                ++n76;
                ++n77;
                array2[n73] = n82;
                final float n83 = n82 + array[n76] * array[n77];
                ++n76;
                ++n77;
                array2[n74] = n83;
                n78 = n83 + array[n76] * array[n77];
                ++n76;
                ++n77;
                array2[n75] = n78;
                n71 -= n28;
                n72 -= n28;
                n73 -= n28;
                n74 -= n28;
                n75 -= n28;
            }
            final float n84 = n78 + array[n76] * array[n77];
            ++n76;
            ++n77;
            array2[n71] = n84;
            final float n85 = n84 + array[n76] * array[n77];
            ++n76;
            ++n77;
            array2[n72] = n85;
            n68 -= 8;
            --n69;
            n67 += 5;
        }
        final int n86 = 0;
        int n87 = n86 + 4;
        int n88 = 63;
        int n89 = n88 - 1;
        for (int n90 = 0; n90 < 8; ++n90) {
            int n91 = n9 + n88;
            int n92 = n13 + n89;
            int n93 = n10 + n89;
            int n94 = n6 + n89;
            int n95 = n86;
            int n96 = n87;
            float n97 = 0.0f;
            for (int n98 = n90 + 1; n98 < 8; ++n98) {
                final float n99 = n97 + array[n95] * array[n96];
                ++n95;
                ++n96;
                array2[n91] = n99;
                final float n100 = n99 + array[n95] * array[n96];
                ++n95;
                ++n96;
                final float n101 = n100 + array[n95] * array[n96];
                ++n95;
                ++n96;
                array2[n92] = n101;
                final float n102 = n101 + array[n95] * array[n96];
                ++n95;
                ++n96;
                array2[n93] = n102;
                n97 = n102 + array[n95] * array[n96];
                ++n95;
                ++n96;
                array2[n94] = n97;
                n91 -= n28;
                n92 -= n28;
                n93 -= n28;
                n94 -= n28;
            }
            final float n103 = n97 + array[n95] * array[n96];
            ++n95;
            ++n96;
            array2[n91] = n103;
            n88 -= 8;
            --n89;
            n87 += 5;
        }
    }
    
    int a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final float[] array5, final af af, final int n) {
        final float[] array6 = new float[40];
        final int n2 = 0;
        final int n3 = n2 + 8;
        final int n4 = n3 + 8;
        final int n5 = n4 + 8;
        final int n6 = n5 + 8;
        final int n7 = n6 + 8;
        final int n8 = n7 + 64;
        final int n9 = n8 + 64;
        final int n10 = n9 + 64;
        final int n11 = n10 + 64;
        final int n12 = n11 + 64;
        final int n13 = n12 + 64;
        final int n14 = n13 + 64;
        final int n15 = n14 + 64;
        if (n == 0) {
            this.a = 30;
        }
        for (int i = 0; i < 40; ++i) {
            if (array[i] >= 0.0f) {
                array6[i] = 1.0f;
            }
            else {
                array6[i] = -1.0f;
                array[i] = -array[i];
            }
        }
        float n16 = array[0] + array[1] + array[2];
        float n17 = array[0];
        float n18 = array[1];
        float n19 = array[2];
        for (int j = 5; j < 40; j += 5) {
            n16 += array[j] + array[j + 1] + array[j + 2];
            if (array[j] > n17) {
                n17 = array[j];
            }
            if (array[j + 1] > n18) {
                n18 = array[j + 1];
            }
            if (array[j + 2] > n19) {
                n19 = array[j + 2];
            }
        }
        final float n20 = n17 + (n18 + n19);
        final float n21 = n16 * 0.125f;
        final float n22 = n21 + (n20 - n21) * 0.4f;
        int n23 = n7;
        int n24 = n8;
        int n25 = n9;
        int n26 = n10;
        for (int k = 0; k < 40; k += 5) {
            for (int l = 1; l < 40; l += 5) {
                final int n27 = n23;
                array2[n27] *= array6[k] * array6[l];
                ++n23;
                final int n28 = n24;
                array2[n28] *= array6[k] * array6[l + 1];
                ++n24;
                final int n29 = n25;
                array2[n29] *= array6[k] * array6[l + 2];
                ++n25;
                final int n30 = n26;
                array2[n30] *= array6[k] * array6[l + 3];
                ++n26;
            }
        }
        int n31 = n11;
        int n32 = n12;
        int n33 = n13;
        for (int n34 = 1; n34 < 40; n34 += 5) {
            for (int n35 = 2; n35 < 40; n35 += 5) {
                final int n36 = n31;
                array2[n36] *= array6[n34] * array6[n35];
                ++n31;
                final int n37 = n32;
                array2[n37] *= array6[n34] * array6[n35 + 1];
                ++n32;
                final int n38 = n33;
                array2[n38] *= array6[n34] * array6[n35 + 2];
                ++n33;
            }
        }
        int n39 = n14;
        int n40 = n15;
        for (int n41 = 2; n41 < 40; n41 += 5) {
            for (int n42 = 3; n42 < 40; n42 += 5) {
                final int n43 = n39;
                array2[n43] *= array6[n41] * array6[n42];
                ++n39;
                final int n44 = n40;
                array2[n44] *= array6[n41] * array6[n42 + 1];
                ++n40;
            }
        }
        int n45 = 0;
        int n46 = 1;
        int n47 = 2;
        int n48 = 3;
        float n49 = 0.0f;
        float n50 = 1000000.0f;
        int a = 75 + this.a;
        int n51 = n2;
        int n52 = n7;
        int n53 = n8;
        int n54 = n9;
        int n55 = n10;
    Label_1135:
        for (int n56 = 0; n56 < 40; n56 += 5) {
            final float n57 = array[n56];
            final float n58 = array2[n51++];
            int n59 = n3;
            int n60 = n11;
            int n61 = n12;
            int n62 = n13;
            for (int n63 = 1; n63 < 40; n63 += 5) {
                final float n64 = n57 + array[n63];
                final float n65 = n58 + array2[n59++] + 2.0f * array2[n52++];
                int n66 = n4;
                int n67 = n14;
                int n68 = n15;
                for (int n69 = 2; n69 < 40; n69 += 5) {
                    final float n70 = n64 + array[n69];
                    final float n71 = n65 + array2[n66++] + 2.0f * (array2[n53++] + array2[n60++]);
                    if (n70 > n22) {
                        int n72 = n5;
                        for (int n73 = 3; n73 < 40; n73 += 5) {
                            final float n74 = n70 + array[n73];
                            final float n75 = n71 + array2[n72++] + 2.0f * (array2[n61++] + array2[n54++] + array2[n67++]);
                            final float n76 = n74 * n74;
                            if (n76 * n50 > n49 * n75) {
                                n49 = n76;
                                n50 = n75;
                                n45 = n56;
                                n46 = n63;
                                n47 = n69;
                                n48 = n73;
                            }
                        }
                        n54 -= 8;
                        n61 -= 8;
                        int n77 = n6;
                        for (int n78 = 4; n78 < 40; n78 += 5) {
                            final float n79 = n70 + array[n78];
                            final float n80 = n71 + array2[n77++] + 2.0f * (array2[n62++] + array2[n55++] + array2[n68++]);
                            final float n81 = n79 * n79;
                            if (n81 * n50 > n49 * n80) {
                                n49 = n81;
                                n50 = n80;
                                n45 = n56;
                                n46 = n63;
                                n47 = n69;
                                n48 = n78;
                            }
                        }
                        n55 -= 8;
                        n62 -= 8;
                        if (--a <= 0) {
                            break Label_1135;
                        }
                    }
                    else {
                        n67 += 8;
                        n68 += 8;
                    }
                }
                n53 -= 8;
                n61 += 8;
                n62 += 8;
            }
            n53 += 8;
            n54 += 8;
            n55 += 8;
        }
        this.a = a;
        for (int n82 = 0; n82 < 40; ++n82) {
            array4[n82] = 0.0f;
        }
        array4[n45] = array6[n45];
        array4[n46] = array6[n46];
        array4[n47] = array6[n47];
        array4[n48] = array6[n48];
        for (int n83 = 0; n83 < 40; ++n83) {
            array5[n83] = 0.0f;
        }
        if (array6[n45] > 0.0f) {
            for (int n84 = n45, n85 = 0; n84 < 40; ++n84, ++n85) {
                array5[n84] = array3[n85];
            }
        }
        else {
            for (int n86 = n45, n87 = 0; n86 < 40; ++n86, ++n87) {
                array5[n86] = -array3[n87];
            }
        }
        if (array6[n46] > 0.0f) {
            for (int n88 = n46, n89 = 0; n88 < 40; ++n88, ++n89) {
                array5[n88] += array3[n89];
            }
        }
        else {
            for (int n90 = n46, n91 = 0; n90 < 40; ++n90, ++n91) {
                array5[n90] -= array3[n91];
            }
        }
        if (array6[n47] > 0.0f) {
            for (int n92 = n47, n93 = 0; n92 < 40; ++n92, ++n93) {
                array5[n92] += array3[n93];
            }
        }
        else {
            for (int n94 = n47, n95 = 0; n94 < 40; ++n94, ++n95) {
                array5[n94] -= array3[n95];
            }
        }
        if (array6[n48] > 0.0f) {
            for (int n96 = n48, n97 = 0; n96 < 40; ++n96, ++n97) {
                array5[n96] += array3[n97];
            }
        }
        else {
            for (int n98 = n48, n99 = 0; n98 < 40; ++n98, ++n99) {
                array5[n98] -= array3[n99];
            }
        }
        int n100 = 0;
        if (array6[n45] > 0.0f) {
            ++n100;
        }
        if (array6[n46] > 0.0f) {
            n100 += 2;
        }
        if (array6[n47] > 0.0f) {
            n100 += 4;
        }
        if (array6[n48] > 0.0f) {
            n100 += 8;
        }
        af.a = new Integer(n100);
        return n45 / 5 + (n46 / 5 << 3) + (n47 / 5 << 6) + ((n48 / 5 << 1) + (n48 % 5 - 3) << 9);
    }
}

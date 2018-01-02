// 
// Decompiled by Procyon v0.5.30
// 

package org.netlib.lapack;

import org.netlib.util.intW;
import org.netlib.util.doubleW;

public final class Dlaed4
{
    public static void dlaed4(final int n, final int n2, final double[] array, final int n3, final double[] array2, final int n4, final double[] array3, final int n5, final double n6, final doubleW doubleW, final intW intW) {
        final doubleW doubleW2 = new doubleW(0.0);
        final double[] array4 = new double[3];
        intW.val = 0;
        if (n == 1) {
            doubleW.val = array[1 - 1 + n3] + n6 * array2[1 - 1 + n4] * array2[1 - 1 + n4];
            array3[1 - 1 + n5] = 1.0;
            return;
        }
        if (n == 2) {
            Dlaed5.dlaed5(n2, array, n3, array2, n4, array3, n5, n6, doubleW);
            return;
        }
        final double dlamch = Dlamch.dlamch("Epsilon");
        final double n7 = 1.0 / n6;
        if (n2 == n) {
            final int n8 = n - 1;
            final int n9 = 1;
            final double n10 = n6 / 2.0;
            int n11 = 1;
            for (int i = n - 1 + 1; i > 0; --i) {
                array3[n11 - 1 + n5] = array[n11 - 1 + n3] - array[n2 - 1 + n3] - n10;
                ++n11;
            }
            double n12 = 0.0;
            int n13 = 1;
            for (int j = n - 2 - 1 + 1; j > 0; --j) {
                n12 += array2[n13 - 1 + n4] * array2[n13 - 1 + n4] / array3[n13 - 1 + n5];
                ++n13;
            }
            final double n14 = n7 + n12;
            double n15;
            double n19;
            double n20;
            if (n14 + array2[n8 - 1 + n4] * array2[n8 - 1 + n4] / array3[n8 - 1 + n5] + array2[n - 1 + n4] * array2[n - 1 + n4] / array3[n - 1 + n5] <= 0.0) {
                if (n14 <= array2[n - 1 - 1 + n4] * array2[n - 1 - 1 + n4] / (array[n - 1 + n3] - array[n - 1 - 1 + n3] + n6) + array2[n - 1 + n4] * array2[n - 1 + n4] / n6) {
                    n15 = n6;
                }
                else {
                    final double n16 = array[n - 1 + n3] - array[n - 1 - 1 + n3];
                    final double n17 = -(n14 * n16) + array2[n - 1 - 1 + n4] * array2[n - 1 - 1 + n4] + array2[n - 1 + n4] * array2[n - 1 + n4];
                    final double n18 = array2[n - 1 + n4] * array2[n - 1 + n4] * n16;
                    if (n17 < 0.0) {
                        n15 = 2.0 * n18 / (Math.sqrt(n17 * n17 + 4.0 * n18 * n14) - n17);
                    }
                    else {
                        n15 = (n17 + Math.sqrt(n17 * n17 + 4.0 * n18 * n14)) / (2.0 * n14);
                    }
                }
                n19 = n10;
                n20 = n6;
            }
            else {
                final double n21 = array[n - 1 + n3] - array[n - 1 - 1 + n3];
                final double n22 = -(n14 * n21) + array2[n - 1 - 1 + n4] * array2[n - 1 - 1 + n4] + array2[n - 1 + n4] * array2[n - 1 + n4];
                final double n23 = array2[n - 1 + n4] * array2[n - 1 + n4] * n21;
                if (n22 < 0.0) {
                    n15 = 2.0 * n23 / (Math.sqrt(n22 * n22 + 4.0 * n23 * n14) - n22);
                }
                else {
                    n15 = (n22 + Math.sqrt(n22 * n22 + 4.0 * n23 * n14)) / (2.0 * n14);
                }
                n19 = 0.0;
                n20 = n10;
            }
            int n24 = 1;
            for (int k = n - 1 + 1; k > 0; --k) {
                array3[n24 - 1 + n5] = array[n24 - 1 + n3] - array[n2 - 1 + n3] - n15;
                ++n24;
            }
            double n25 = 0.0;
            double n26 = 0.0;
            double n27 = 0.0;
            int n28 = 1;
            for (int l = n8 - 1 + 1; l > 0; --l) {
                final double n29 = array2[n28 - 1 + n4] / array3[n28 - 1 + n5];
                n26 += array2[n28 - 1 + n4] * n29;
                n25 += n29 * n29;
                n27 += n26;
                ++n28;
            }
            final double abs = Math.abs(n27);
            final double n30 = array2[n - 1 + n4] / array3[n - 1 + n5];
            final double n31 = array2[n - 1 + n4] * n30;
            final double n32 = n30 * n30;
            final double n33 = 8.0 * (-n31 - n26) + abs - n31 + n7 + Math.abs(n15) * (n25 + n32);
            final double n34 = n7 + n31 + n26;
            if (Math.abs(n34) <= dlamch * n33) {
                doubleW.val = array[n2 - 1 + n3] + n15;
            }
            else {
                if (n34 <= 0.0) {
                    n19 = Math.max(n19, n15);
                }
                else {
                    n20 = Math.min(n20, n15);
                }
                final int n35 = n9 + 1;
                double abs2 = n34 - array3[n - 1 - 1 + n5] * n25 - array3[n - 1 + n5] * n32;
                final double n36 = (array3[n - 1 - 1 + n5] + array3[n - 1 + n5]) * n34 - array3[n - 1 - 1 + n5] * array3[n - 1 + n5] * (n25 + n32);
                final double n37 = array3[n - 1 - 1 + n5] * array3[n - 1 + n5] * n34;
                if (abs2 < 0.0) {
                    abs2 = Math.abs(abs2);
                }
                if (abs2 == 0.0) {
                    doubleW2.val = n20 - n15;
                }
                else if (n36 >= 0.0) {
                    doubleW2.val = (n36 + Math.sqrt(Math.abs(n36 * n36 - 4.0 * n37 * abs2))) / (2.0 * abs2);
                }
                else {
                    doubleW2.val = 2.0 * n37 / (n36 - Math.sqrt(Math.abs(n36 * n36 - 4.0 * n37 * abs2)));
                }
                if (n34 * doubleW2.val > 0.0) {
                    doubleW2.val = -(n34 / (n25 + n32));
                }
                final double n38 = n15 + doubleW2.val;
                if (n38 > n20 || n38 < n19) {
                    if (n34 < 0.0) {
                        doubleW2.val = (n20 - n15) / 2.0;
                    }
                    else {
                        doubleW2.val = (n19 - n15) / 2.0;
                    }
                }
                int n39 = 1;
                for (int n40 = n - 1 + 1; n40 > 0; --n40) {
                    array3[n39 - 1 + n5] -= doubleW2.val;
                    ++n39;
                }
                double n41 = n15 + doubleW2.val;
                double n42 = 0.0;
                double n43 = 0.0;
                double n44 = 0.0;
                int n45 = 1;
                for (int n46 = n8 - 1 + 1; n46 > 0; --n46) {
                    final double n47 = array2[n45 - 1 + n4] / array3[n45 - 1 + n5];
                    n43 += array2[n45 - 1 + n4] * n47;
                    n42 += n47 * n47;
                    n44 += n43;
                    ++n45;
                }
                final double abs3 = Math.abs(n44);
                final double n48 = array2[n - 1 + n4] / array3[n - 1 + n5];
                final double n49 = array2[n - 1 + n4] * n48;
                double n50 = n48 * n48;
                double n51 = 8.0 * (-n49 - n43) + abs3 - n49 + n7 + Math.abs(n41) * (n42 + n50);
                double n52 = n7 + n49 + n43;
                int n54;
                for (int n53 = 30 - (n54 = n35 + 1) + 1; n53 > 0; --n53) {
                    if (Math.abs(n52) <= dlamch * n51) {
                        doubleW.val = array[n2 - 1 + n3] + n41;
                        return;
                    }
                    if (n52 <= 0.0) {
                        n19 = Math.max(n19, n41);
                    }
                    else {
                        n20 = Math.min(n20, n41);
                    }
                    final double n55 = n52 - array3[n - 1 - 1 + n5] * n42 - array3[n - 1 + n5] * n50;
                    final double n56 = (array3[n - 1 - 1 + n5] + array3[n - 1 + n5]) * n52 - array3[n - 1 - 1 + n5] * array3[n - 1 + n5] * (n42 + n50);
                    final double n57 = array3[n - 1 - 1 + n5] * array3[n - 1 + n5] * n52;
                    if (n56 >= 0.0) {
                        doubleW2.val = (n56 + Math.sqrt(Math.abs(n56 * n56 - 4.0 * n57 * n55))) / (2.0 * n55);
                    }
                    else {
                        doubleW2.val = 2.0 * n57 / (n56 - Math.sqrt(Math.abs(n56 * n56 - 4.0 * n57 * n55)));
                    }
                    if (n52 * doubleW2.val > 0.0) {
                        doubleW2.val = -(n52 / (n42 + n50));
                    }
                    final double n58 = n41 + doubleW2.val;
                    if (n58 > n20 || n58 < n19) {
                        if (n52 < 0.0) {
                            doubleW2.val = (n20 - n41) / 2.0;
                        }
                        else {
                            doubleW2.val = (n19 - n41) / 2.0;
                        }
                    }
                    int n59 = 1;
                    for (int n60 = n - 1 + 1; n60 > 0; --n60) {
                        array3[n59 - 1 + n5] -= doubleW2.val;
                        ++n59;
                    }
                    n41 += doubleW2.val;
                    n42 = 0.0;
                    double n61 = 0.0;
                    double n62 = 0.0;
                    int n63 = 1;
                    for (int n64 = n8 - 1 + 1; n64 > 0; --n64) {
                        final double n65 = array2[n63 - 1 + n4] / array3[n63 - 1 + n5];
                        n61 += array2[n63 - 1 + n4] * n65;
                        n42 += n65 * n65;
                        n62 += n61;
                        ++n63;
                    }
                    final double abs4 = Math.abs(n62);
                    final double n66 = array2[n - 1 + n4] / array3[n - 1 + n5];
                    final double n67 = array2[n - 1 + n4] * n66;
                    n50 = n66 * n66;
                    n51 = 8.0 * (-n67 - n61) + abs4 - n67 + n7 + Math.abs(n41) * (n42 + n50);
                    n52 = n7 + n67 + n61;
                    ++n54;
                }
                intW.val = 1;
                doubleW.val = array[n2 - 1 + n3] + n41;
            }
        }
        else {
            final int n68 = 1;
            final int n69 = n2 + 1;
            final double n70 = array[n69 - 1 + n3] - array[n2 - 1 + n3];
            final double n71 = n70 / 2.0;
            int n72 = 1;
            for (int n73 = n - 1 + 1; n73 > 0; --n73) {
                array3[n72 - 1 + n5] = array[n72 - 1 + n3] - array[n2 - 1 + n3] - n71;
                ++n72;
            }
            double n74 = 0.0;
            int n75 = 1;
            for (int n76 = n2 - 1 - 1 + 1; n76 > 0; --n76) {
                n74 += array2[n75 - 1 + n4] * array2[n75 - 1 + n4] / array3[n75 - 1 + n5];
                ++n75;
            }
            double n77 = 0.0;
            int n78 = n;
            for (int n79 = (n2 + 2 - n - 1) / -1; n79 > 0; --n79) {
                n77 += array2[n78 - 1 + n4] * array2[n78 - 1 + n4] / array3[n78 - 1 + n5];
                --n78;
            }
            final double n80 = n7 + n74 + n77;
            boolean b;
            double n83;
            double n84;
            double n85;
            if (n80 + array2[n2 - 1 + n4] * array2[n2 - 1 + n4] / array3[n2 - 1 + n5] + array2[n69 - 1 + n4] * array2[n69 - 1 + n4] / array3[n69 - 1 + n5] > 0.0) {
                b = true;
                final double n81 = n80 * n70 + array2[n2 - 1 + n4] * array2[n2 - 1 + n4] + array2[n69 - 1 + n4] * array2[n69 - 1 + n4];
                final double n82 = array2[n2 - 1 + n4] * array2[n2 - 1 + n4] * n70;
                if (n81 > 0.0) {
                    n83 = 2.0 * n82 / (n81 + Math.sqrt(Math.abs(n81 * n81 - 4.0 * n82 * n80)));
                }
                else {
                    n83 = (n81 - Math.sqrt(Math.abs(n81 * n81 - 4.0 * n82 * n80))) / (2.0 * n80);
                }
                n84 = 0.0;
                n85 = n71;
            }
            else {
                b = false;
                final double n86 = n80 * n70 - array2[n2 - 1 + n4] * array2[n2 - 1 + n4] - array2[n69 - 1 + n4] * array2[n69 - 1 + n4];
                final double n87 = array2[n69 - 1 + n4] * array2[n69 - 1 + n4] * n70;
                if (n86 < 0.0) {
                    n83 = 2.0 * n87 / (n86 - Math.sqrt(Math.abs(n86 * n86 + 4.0 * n87 * n80)));
                }
                else {
                    n83 = -((n86 + Math.sqrt(Math.abs(n86 * n86 + 4.0 * n87 * n80))) / (2.0 * n80));
                }
                n84 = -n71;
                n85 = 0.0;
            }
            if (b) {
                int n88 = 1;
                for (int n89 = n - 1 + 1; n89 > 0; --n89) {
                    array3[n88 - 1 + n5] = array[n88 - 1 + n3] - array[n2 - 1 + n3] - n83;
                    ++n88;
                }
            }
            else {
                int n90 = 1;
                for (int n91 = n - 1 + 1; n91 > 0; --n91) {
                    array3[n90 - 1 + n5] = array[n90 - 1 + n3] - array[n69 - 1 + n3] - n83;
                    ++n90;
                }
            }
            int n92;
            if (b) {
                n92 = n2;
            }
            else {
                n92 = n2 + 1;
            }
            final int n93 = n92 - 1;
            final int n94 = n92 + 1;
            double n95 = 0.0;
            double n96 = 0.0;
            double n97 = 0.0;
            int n98 = 1;
            for (int n99 = n93 - 1 + 1; n99 > 0; --n99) {
                final double n100 = array2[n98 - 1 + n4] / array3[n98 - 1 + n5];
                n96 += array2[n98 - 1 + n4] * n100;
                n95 += n100 * n100;
                n97 += n96;
                ++n98;
            }
            double abs5 = Math.abs(n97);
            double n101 = 0.0;
            double n102 = 0.0;
            int n103 = n;
            for (int n104 = (n94 - n - 1) / -1; n104 > 0; --n104) {
                final double n105 = array2[n103 - 1 + n4] / array3[n103 - 1 + n5];
                n102 += array2[n103 - 1 + n4] * n105;
                n101 += n105 * n105;
                abs5 += n102;
                --n103;
            }
            final double n106 = n7 + n102 + n96;
            boolean b2 = false;
            if (b) {
                if (n106 < 0.0) {
                    b2 = true;
                }
            }
            else if (n106 > 0.0) {
                b2 = true;
            }
            if (n92 == 1 || n92 == n) {
                b2 = false;
            }
            final double n107 = array2[n92 - 1 + n4] / array3[n92 - 1 + n5];
            final double n108 = n95 + n101 + n107 * n107;
            final double n109 = array2[n92 - 1 + n4] * n107;
            final double n110 = n106 + n109;
            if (Math.abs(n110) <= dlamch * (8.0 * (n102 - n96) + abs5 + 2.0 * n7 + 3.0 * Math.abs(n109) + Math.abs(n83) * n108)) {
                if (b) {
                    doubleW.val = array[n2 - 1 + n3] + n83;
                }
                else {
                    doubleW.val = array[n69 - 1 + n3] + n83;
                }
            }
            else {
                if (n110 <= 0.0) {
                    n84 = Math.max(n84, n83);
                }
                else {
                    n85 = Math.min(n85, n83);
                }
                final int n111 = n68 + 1;
                if (b2 ^ true) {
                    double n112;
                    if (b) {
                        n112 = n110 - array3[n69 - 1 + n5] * n108 - (array[n2 - 1 + n3] - array[n69 - 1 + n3]) * Math.pow(array2[n2 - 1 + n4] / array3[n2 - 1 + n5], 2);
                    }
                    else {
                        n112 = n110 - array3[n2 - 1 + n5] * n108 - (array[n69 - 1 + n3] - array[n2 - 1 + n3]) * Math.pow(array2[n69 - 1 + n4] / array3[n69 - 1 + n5], 2);
                    }
                    double n113 = (array3[n2 - 1 + n5] + array3[n69 - 1 + n5]) * n110 - array3[n2 - 1 + n5] * array3[n69 - 1 + n5] * n108;
                    final double n114 = array3[n2 - 1 + n5] * array3[n69 - 1 + n5] * n110;
                    if (n112 == 0.0) {
                        if (n113 == 0.0) {
                            if (b) {
                                n113 = array2[n2 - 1 + n4] * array2[n2 - 1 + n4] + array3[n69 - 1 + n5] * array3[n69 - 1 + n5] * (n95 + n101);
                            }
                            else {
                                n113 = array2[n69 - 1 + n4] * array2[n69 - 1 + n4] + array3[n2 - 1 + n5] * array3[n2 - 1 + n5] * (n95 + n101);
                            }
                        }
                        doubleW2.val = n114 / n113;
                    }
                    else if (n113 <= 0.0) {
                        doubleW2.val = (n113 - Math.sqrt(Math.abs(n113 * n113 - 4.0 * n114 * n112))) / (2.0 * n112);
                    }
                    else {
                        doubleW2.val = 2.0 * n114 / (n113 + Math.sqrt(Math.abs(n113 * n113 - 4.0 * n114 * n112)));
                    }
                }
                else {
                    final double n115 = n7 + n96 + n102;
                    double n118;
                    if (b) {
                        final double n116 = array2[n93 - 1 + n4] / array3[n93 - 1 + n5];
                        final double n117 = n116 * n116;
                        n118 = n115 - array3[n94 - 1 + n5] * (n95 + n101) - (array[n93 - 1 + n3] - array[n94 - 1 + n3]) * n117;
                        array4[1 - 1] = array2[n93 - 1 + n4] * array2[n93 - 1 + n4];
                        array4[3 - 1] = array3[n94 - 1 + n5] * array3[n94 - 1 + n5] * (n95 - n117 + n101);
                    }
                    else {
                        final double n119 = array2[n94 - 1 + n4] / array3[n94 - 1 + n5];
                        final double n120 = n119 * n119;
                        n118 = n115 - array3[n93 - 1 + n5] * (n95 + n101) - (array[n94 - 1 + n3] - array[n93 - 1 + n3]) * n120;
                        array4[1 - 1] = array3[n93 - 1 + n5] * array3[n93 - 1 + n5] * (n95 + (n101 - n120));
                        array4[3 - 1] = array2[n94 - 1 + n4] * array2[n94 - 1 + n4];
                    }
                    array4[2 - 1] = array2[n92 - 1 + n4] * array2[n92 - 1 + n4];
                    Dlaed6.dlaed6(n111, b, n118, array3, n93 - 1 + n5, array4, 0, n110, doubleW2, intW);
                    if (intW.val != 0) {
                        return;
                    }
                }
                if (n110 * doubleW2.val >= 0.0) {
                    doubleW2.val = -(n110 / n108);
                }
                final double n121 = n83 + doubleW2.val;
                if (n121 > n85 || n121 < n84) {
                    if (n110 < 0.0) {
                        doubleW2.val = (n85 - n83) / 2.0;
                    }
                    else {
                        doubleW2.val = (n84 - n83) / 2.0;
                    }
                }
                final double n122 = n110;
                int n123 = 1;
                for (int n124 = n - 1 + 1; n124 > 0; --n124) {
                    array3[n123 - 1 + n5] -= doubleW2.val;
                    ++n123;
                }
                double n125 = 0.0;
                double n126 = 0.0;
                double n127 = 0.0;
                int n128 = 1;
                for (int n129 = n93 - 1 + 1; n129 > 0; --n129) {
                    final double n130 = array2[n128 - 1 + n4] / array3[n128 - 1 + n5];
                    n126 += array2[n128 - 1 + n4] * n130;
                    n125 += n130 * n130;
                    n127 += n126;
                    ++n128;
                }
                double abs6 = Math.abs(n127);
                double n131 = 0.0;
                double n132 = 0.0;
                int n133 = n;
                for (int n134 = (n94 - n - 1) / -1; n134 > 0; --n134) {
                    final double n135 = array2[n133 - 1 + n4] / array3[n133 - 1 + n5];
                    n132 += array2[n133 - 1 + n4] * n135;
                    n131 += n135 * n135;
                    abs6 += n132;
                    --n133;
                }
                final double n136 = array2[n92 - 1 + n4] / array3[n92 - 1 + n5];
                double n137 = n125 + n131 + n136 * n136;
                final double n138 = array2[n92 - 1 + n4] * n136;
                double n139 = n7 + n132 + n126 + n138;
                double n140 = 8.0 * (n132 - n126) + abs6 + 2.0 * n7 + 3.0 * Math.abs(n138) + Math.abs(n83 + doubleW2.val) * n137;
                int n141 = 0;
                if (b) {
                    if (-n139 > Math.abs(n122) / 10.0) {
                        n141 = 1;
                    }
                }
                else if (n139 > Math.abs(n122) / 10.0) {
                    n141 = 1;
                }
                double n142 = n83 + doubleW2.val;
                int n144;
                for (int n143 = 30 - (n144 = n111 + 1) + 1; n143 > 0; --n143) {
                    if (Math.abs(n139) <= dlamch * n140) {
                        if (b) {
                            doubleW.val = array[n2 - 1 + n3] + n142;
                        }
                        else {
                            doubleW.val = array[n69 - 1 + n3] + n142;
                        }
                        return;
                    }
                    if (n139 <= 0.0) {
                        n84 = Math.max(n84, n142);
                    }
                    else {
                        n85 = Math.min(n85, n142);
                    }
                    if (b2 ^ true) {
                        double n145;
                        if ((n141 ^ 0x1) != 0x0) {
                            if (b) {
                                n145 = n139 - array3[n69 - 1 + n5] * n137 - (array[n2 - 1 + n3] - array[n69 - 1 + n3]) * Math.pow(array2[n2 - 1 + n4] / array3[n2 - 1 + n5], 2);
                            }
                            else {
                                n145 = n139 - array3[n2 - 1 + n5] * n137 - (array[n69 - 1 + n3] - array[n2 - 1 + n3]) * Math.pow(array2[n69 - 1 + n4] / array3[n69 - 1 + n5], 2);
                            }
                        }
                        else {
                            final double n146 = array2[n92 - 1 + n4] / array3[n92 - 1 + n5];
                            if (b) {
                                n125 += n146 * n146;
                            }
                            else {
                                n131 += n146 * n146;
                            }
                            n145 = n139 - array3[n2 - 1 + n5] * n125 - array3[n69 - 1 + n5] * n131;
                        }
                        double n147 = (array3[n2 - 1 + n5] + array3[n69 - 1 + n5]) * n139 - array3[n2 - 1 + n5] * array3[n69 - 1 + n5] * n137;
                        final double n148 = array3[n2 - 1 + n5] * array3[n69 - 1 + n5] * n139;
                        if (n145 == 0.0) {
                            if (n147 == 0.0) {
                                if ((n141 ^ 0x1) != 0x0) {
                                    if (b) {
                                        n147 = array2[n2 - 1 + n4] * array2[n2 - 1 + n4] + array3[n69 - 1 + n5] * array3[n69 - 1 + n5] * (n125 + n131);
                                    }
                                    else {
                                        n147 = array2[n69 - 1 + n4] * array2[n69 - 1 + n4] + array3[n2 - 1 + n5] * array3[n2 - 1 + n5] * (n125 + n131);
                                    }
                                }
                                else {
                                    n147 = array3[n2 - 1 + n5] * array3[n2 - 1 + n5] * n125 + array3[n69 - 1 + n5] * array3[n69 - 1 + n5] * n131;
                                }
                            }
                            doubleW2.val = n148 / n147;
                        }
                        else if (n147 <= 0.0) {
                            doubleW2.val = (n147 - Math.sqrt(Math.abs(n147 * n147 - 4.0 * n148 * n145))) / (2.0 * n145);
                        }
                        else {
                            doubleW2.val = 2.0 * n148 / (n147 + Math.sqrt(Math.abs(n147 * n147 - 4.0 * n148 * n145)));
                        }
                    }
                    else {
                        final double n149 = n7 + n126 + n132;
                        double n150;
                        if (n141 != 0) {
                            n150 = n149 - array3[n93 - 1 + n5] * n125 - array3[n94 - 1 + n5] * n131;
                            array4[1 - 1] = array3[n93 - 1 + n5] * array3[n93 - 1 + n5] * n125;
                            array4[3 - 1] = array3[n94 - 1 + n5] * array3[n94 - 1 + n5] * n131;
                        }
                        else if (b) {
                            final double n151 = array2[n93 - 1 + n4] / array3[n93 - 1 + n5];
                            final double n152 = n151 * n151;
                            n150 = n149 - array3[n94 - 1 + n5] * (n125 + n131) - (array[n93 - 1 + n3] - array[n94 - 1 + n3]) * n152;
                            array4[1 - 1] = array2[n93 - 1 + n4] * array2[n93 - 1 + n4];
                            array4[3 - 1] = array3[n94 - 1 + n5] * array3[n94 - 1 + n5] * (n125 - n152 + n131);
                        }
                        else {
                            final double n153 = array2[n94 - 1 + n4] / array3[n94 - 1 + n5];
                            final double n154 = n153 * n153;
                            n150 = n149 - array3[n93 - 1 + n5] * (n125 + n131) - (array[n94 - 1 + n3] - array[n93 - 1 + n3]) * n154;
                            array4[1 - 1] = array3[n93 - 1 + n5] * array3[n93 - 1 + n5] * (n125 + (n131 - n154));
                            array4[3 - 1] = array2[n94 - 1 + n4] * array2[n94 - 1 + n4];
                        }
                        Dlaed6.dlaed6(n144, b, n150, array3, n93 - 1 + n5, array4, 0, n139, doubleW2, intW);
                        if (intW.val != 0) {
                            return;
                        }
                    }
                    if (n139 * doubleW2.val >= 0.0) {
                        doubleW2.val = -(n139 / n137);
                    }
                    final double n155 = n142 + doubleW2.val;
                    if (n155 > n85 || n155 < n84) {
                        if (n139 < 0.0) {
                            doubleW2.val = (n85 - n142) / 2.0;
                        }
                        else {
                            doubleW2.val = (n84 - n142) / 2.0;
                        }
                    }
                    int n156 = 1;
                    for (int n157 = n - 1 + 1; n157 > 0; --n157) {
                        array3[n156 - 1 + n5] -= doubleW2.val;
                        ++n156;
                    }
                    n142 += doubleW2.val;
                    final double n158 = n139;
                    n125 = 0.0;
                    n126 = 0.0;
                    double n159 = 0.0;
                    int n160 = 1;
                    for (int n161 = n93 - 1 + 1; n161 > 0; --n161) {
                        final double n162 = array2[n160 - 1 + n4] / array3[n160 - 1 + n5];
                        n126 += array2[n160 - 1 + n4] * n162;
                        n125 += n162 * n162;
                        n159 += n126;
                        ++n160;
                    }
                    double abs7 = Math.abs(n159);
                    n131 = 0.0;
                    n132 = 0.0;
                    int n163 = n;
                    for (int n164 = (n94 - n - 1) / -1; n164 > 0; --n164) {
                        final double n165 = array2[n163 - 1 + n4] / array3[n163 - 1 + n5];
                        n132 += array2[n163 - 1 + n4] * n165;
                        n131 += n165 * n165;
                        abs7 += n132;
                        --n163;
                    }
                    final double n166 = array2[n92 - 1 + n4] / array3[n92 - 1 + n5];
                    n137 = n125 + n131 + n166 * n166;
                    final double n167 = array2[n92 - 1 + n4] * n166;
                    n139 = n7 + n132 + n126 + n167;
                    n140 = 8.0 * (n132 - n126) + abs7 + 2.0 * n7 + 3.0 * Math.abs(n167) + Math.abs(n142) * n137;
                    if (n139 * n158 > 0.0 && Math.abs(n139) > Math.abs(n158) / 10.0) {
                        n141 ^= 0x1;
                    }
                    ++n144;
                }
                intW.val = 1;
                if (b) {
                    doubleW.val = array[n2 - 1 + n3] + n142;
                }
                else {
                    doubleW.val = array[n69 - 1 + n3] + n142;
                }
            }
        }
    }
}

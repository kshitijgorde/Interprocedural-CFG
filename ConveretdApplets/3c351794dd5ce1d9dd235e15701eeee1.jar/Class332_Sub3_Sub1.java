// 
// Decompiled by Procyon v0.5.30
// 

final class Class332_Sub3_Sub1 extends Class332_Sub3
{
    int[] anIntArray6212;
    
    @Override
    final void method3729(int anInt4509, int anInt4510, final aa aa, final int n, final int n2) {
        if (super.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        anInt4509 += super.anInt5446;
        anInt4510 += super.anInt5439;
        int n3 = 0;
        final int anInt4511 = super.aHa_Sub2_5434.anInt4505;
        int anInt4512 = super.anInt5433;
        int anInt4513 = super.anInt5454;
        int n4 = anInt4511 - anInt4512;
        int n5 = 0;
        int n6 = anInt4509 + anInt4510 * anInt4511;
        if (anInt4510 < super.aHa_Sub2_5434.anInt4495) {
            final int n7 = super.aHa_Sub2_5434.anInt4495 - anInt4510;
            anInt4513 -= n7;
            anInt4510 = super.aHa_Sub2_5434.anInt4495;
            n3 += n7 * anInt4512;
            n6 += n7 * anInt4511;
        }
        if (anInt4510 + anInt4513 > super.aHa_Sub2_5434.anInt4492) {
            anInt4513 -= anInt4510 + anInt4513 - super.aHa_Sub2_5434.anInt4492;
        }
        if (anInt4509 < super.aHa_Sub2_5434.anInt4509) {
            final int n8 = super.aHa_Sub2_5434.anInt4509 - anInt4509;
            anInt4512 -= n8;
            anInt4509 = super.aHa_Sub2_5434.anInt4509;
            n3 += n8;
            n6 += n8;
            n5 += n8;
            n4 += n8;
        }
        if (anInt4509 + anInt4512 > super.aHa_Sub2_5434.anInt4507) {
            final int n9 = anInt4509 + anInt4512 - super.aHa_Sub2_5434.anInt4507;
            anInt4512 -= n9;
            n5 += n9;
            n4 += n9;
        }
        if (anInt4512 > 0 && anInt4513 > 0) {
            final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
            final int[] anIntArray3555 = aa_Sub1.anIntArray3555;
            final int[] anIntArray3556 = aa_Sub1.anIntArray3557;
            final int[] anIntArray3557 = super.aHa_Sub2_5434.anIntArray4504;
            int n10 = anInt4510;
            if (n2 > n10) {
                n10 = n2;
                n6 += (n2 - anInt4510) * anInt4511;
                n3 += (n2 - anInt4510) * super.anInt5433;
            }
            for (int n11 = (n2 + anIntArray3555.length < anInt4510 + anInt4513) ? (n2 + anIntArray3555.length) : (anInt4510 + anInt4513), i = n10; i < n11; ++i) {
                final int n12 = anIntArray3555[i - n2] + n;
                int n13 = anIntArray3556[i - n2];
                int n14 = anInt4512;
                if (anInt4509 > n12) {
                    final int n15 = anInt4509 - n12;
                    if (n15 >= n13) {
                        n3 += anInt4512 + n5;
                        n6 += anInt4512 + n4;
                        continue;
                    }
                    n13 -= n15;
                }
                else {
                    final int n16 = n12 - anInt4509;
                    if (n16 >= anInt4512) {
                        n3 += anInt4512 + n5;
                        n6 += anInt4512 + n4;
                        continue;
                    }
                    n3 += n16;
                    n14 -= n16;
                    n6 += n16;
                }
                int n17 = 0;
                if (n14 < n13) {
                    n13 = n14;
                }
                else {
                    n17 = n14 - n13;
                }
                for (int j = -n13; j < 0; ++j) {
                    final int n18 = this.anIntArray6212[n3++];
                    final int n19 = n18 >>> 24;
                    final int n20 = 256 - n19;
                    final int n21 = anIntArray3557[n6];
                    anIntArray3557[n6++] = ((n18 & 0xFF00FF) * n19 + (n21 & 0xFF00FF) * n20 & 0xFF00FF00) + ((n18 & 0xFF00) * n19 + (n21 & 0xFF00) * n20 & 0xFF0000) >> 8;
                }
                n3 += n17 + n5;
                n6 += n17 + n4;
            }
        }
    }
    
    @Override
    final void method3759(final int n, final int n2) {
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (Class332_Sub3.anInt5444 == 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int i = Class332_Sub3.anInt5431; i < 0; ++i, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5451 = Class332_Sub3.anInt5451;
                    final int anInt5452 = Class332_Sub3.anInt5450;
                    final int anInt5453 = Class332_Sub3.anInt5443;
                    int j = Class332_Sub3.anInt5448;
                    if (anInt5452 >= 0 && anInt5453 >= 0 && anInt5452 - (super.anInt5433 << 12) < 0 && anInt5453 - (super.anInt5454 << 12) < 0) {
                        while (j < 0) {
                            int n3 = (anInt5453 >> 12) * super.anInt5433 + (anInt5452 >> 12);
                            final int n4 = anInt5451++;
                            final int[] array = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array[n4] = this.anIntArray6212[n3];
                                }
                                else if (n == 0) {
                                    final int n5 = this.anIntArray6212[n3++];
                                    array[n4] = (((n5 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n5 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n5 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n6 = this.anIntArray6212[n3++];
                                    final int anInt5454 = Class332_Sub3.anInt5432;
                                    final int n7 = n6 + anInt5454;
                                    final int n8 = (n6 & 0xFF00FF) + (anInt5454 & 0xFF00FF);
                                    final int n9 = (n8 & 0x1000100) + (n7 - n8 & 0x10000);
                                    array[n4] = (n7 - n9 | n9 - (n9 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n10 = this.anIntArray6212[n3];
                                    array[n4] = ((((n10 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n10 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n11 = this.anIntArray6212[n3];
                                    final int n12 = n11 >>> 24;
                                    final int n13 = 256 - n12;
                                    final int n14 = array[n4];
                                    array[n4] = ((n11 & 0xFF00FF) * n12 + (n14 & 0xFF00FF) * n13 & 0xFF00FF00) + ((n11 & 0xFF00) * n12 + (n14 & 0xFF00) * n13 & 0xFF0000) >> 8;
                                }
                                else if (n == 0) {
                                    final int n15 = this.anIntArray6212[n3];
                                    final int n16 = (n15 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n17 = 256 - n16;
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n18 = array[n4];
                                        array[n4] = ((n15 & 0xFF00FF) * n16 + (n18 & 0xFF00FF) * n17 & 0xFF00FF00) + ((n15 & 0xFF00) * n16 + (n18 & 0xFF00) * n17 & 0xFF0000) >> 8;
                                    }
                                    else if (n16 != 255) {
                                        final int n19 = (((n15 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n15 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n15 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n20 = array[n4];
                                        array[n4] = ((n19 & 0xFF00FF) * n16 + (n20 & 0xFF00FF) * n17 & 0xFF00FF00) + ((n19 & 0xFF00) * n16 + (n20 & 0xFF00) * n17 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array[n4] = (((n15 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n15 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n15 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                                else if (n == 3) {
                                    final int n21 = this.anIntArray6212[n3];
                                    final int anInt5455 = Class332_Sub3.anInt5432;
                                    final int n22 = n21 + anInt5455;
                                    final int n23 = (n21 & 0xFF00FF) + (anInt5455 & 0xFF00FF);
                                    final int n24 = (n23 & 0x1000100) + (n22 - n23 & 0x10000);
                                    int n25 = n22 - n24 | n24 - (n24 >>> 8);
                                    final int n26 = (n21 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n27 = 256 - n26;
                                    if (n26 != 255) {
                                        final int n28 = n25;
                                        final int n29 = array[n4];
                                        n25 = ((n28 & 0xFF00FF) * n26 + (n29 & 0xFF00FF) * n27 & 0xFF00FF00) + ((n28 & 0xFF00) * n26 + (n29 & 0xFF00) * n27 & 0xFF0000) >> 8;
                                    }
                                    array[n4] = n25;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n30 = this.anIntArray6212[n3];
                                    final int n31 = n30 >>> 24;
                                    final int n32 = 256 - n31;
                                    final int n33 = ((((n30 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n30 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n34 = array[n4];
                                    array[n4] = ((n33 & 0xFF00FF) * n31 + (n34 & 0xFF00FF) * n32 & 0xFF00FF00) + ((n33 & 0xFF00) * n31 + (n34 & 0xFF00) * n32 & 0xFF0000) >> 8;
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n35 = this.anIntArray6212[n3];
                                    final int n36 = array[n4];
                                    final int n37 = n35 + n36;
                                    final int n38 = (n35 & 0xFF00FF) + (n36 & 0xFF00FF);
                                    final int n39 = (n38 & 0x1000100) + (n37 - n38 & 0x10000);
                                    array[n4] = (n37 - n39 | n39 - (n39 >>> 8));
                                }
                                else if (n == 0) {
                                    final int n40 = this.anIntArray6212[n3];
                                    final int n41 = (((n40 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n40 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n40 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n42 = array[n4];
                                    final int n43 = n41 + n42;
                                    final int n44 = (n41 & 0xFF00FF) + (n42 & 0xFF00FF);
                                    final int n45 = (n44 & 0x1000100) + (n43 - n44 & 0x10000);
                                    array[n4] = (n43 - n45 | n45 - (n45 >>> 8));
                                }
                                else if (n == 3) {
                                    final int n46 = this.anIntArray6212[n3];
                                    final int anInt5456 = Class332_Sub3.anInt5432;
                                    final int n47 = n46 + anInt5456;
                                    final int n48 = (n46 & 0xFF00FF) + (anInt5456 & 0xFF00FF);
                                    final int n49 = (n48 & 0x1000100) + (n47 - n48 & 0x10000);
                                    final int n50 = n47 - n49 | n49 - (n49 >>> 8);
                                    final int n51 = array[n4];
                                    final int n52 = n50 + n51;
                                    final int n53 = (n50 & 0xFF00FF) + (n51 & 0xFF00FF);
                                    final int n54 = (n53 & 0x1000100) + (n52 - n53 & 0x10000);
                                    array[n4] = (n52 - n54 | n54 - (n54 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n55 = this.anIntArray6212[n3];
                                    final int n56 = ((((n55 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n55 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n57 = array[n4];
                                    final int n58 = n56 + n57;
                                    final int n59 = (n56 & 0xFF00FF) + (n57 & 0xFF00FF);
                                    final int n60 = (n59 & 0x1000100) + (n58 - n59 & 0x10000);
                                    array[n4] = (n58 - n60 | n60 - (n60 >>> 8));
                                }
                            }
                            ++j;
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int k = Class332_Sub3.anInt5431; k < 0; ++k, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5457 = Class332_Sub3.anInt5451;
                    final int anInt5458 = Class332_Sub3.anInt5450;
                    int n61 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int l = Class332_Sub3.anInt5448;
                    if (anInt5458 >= 0 && anInt5458 - (super.anInt5433 << 12) < 0) {
                        final int n62;
                        if ((n62 = n61 - (super.anInt5454 << 12)) >= 0) {
                            final int n63 = (Class332_Sub3.anInt5436 - n62) / Class332_Sub3.anInt5436;
                            l += n63;
                            n61 += Class332_Sub3.anInt5436 * n63;
                            anInt5457 += n63;
                        }
                        final int n64;
                        if ((n64 = (n61 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > l) {
                            l = n64;
                        }
                        while (l < 0) {
                            int n65 = (n61 >> 12) * super.anInt5433 + (anInt5458 >> 12);
                            final int n66 = anInt5457++;
                            final int[] array2 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array2[n66] = this.anIntArray6212[n65];
                                }
                                else if (n == 0) {
                                    final int n67 = this.anIntArray6212[n65++];
                                    array2[n66] = (((n67 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n67 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n67 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n68 = this.anIntArray6212[n65++];
                                    final int anInt5459 = Class332_Sub3.anInt5432;
                                    final int n69 = n68 + anInt5459;
                                    final int n70 = (n68 & 0xFF00FF) + (anInt5459 & 0xFF00FF);
                                    final int n71 = (n70 & 0x1000100) + (n69 - n70 & 0x10000);
                                    array2[n66] = (n69 - n71 | n71 - (n71 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n72 = this.anIntArray6212[n65];
                                    array2[n66] = ((((n72 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n72 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n73 = this.anIntArray6212[n65];
                                    final int n74 = n73 >>> 24;
                                    final int n75 = 256 - n74;
                                    final int n76 = array2[n66];
                                    array2[n66] = ((n73 & 0xFF00FF) * n74 + (n76 & 0xFF00FF) * n75 & 0xFF00FF00) + ((n73 & 0xFF00) * n74 + (n76 & 0xFF00) * n75 & 0xFF0000) >> 8;
                                }
                                else if (n == 0) {
                                    final int n77 = this.anIntArray6212[n65];
                                    final int n78 = (n77 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n79 = 256 - n78;
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n80 = array2[n66];
                                        array2[n66] = ((n77 & 0xFF00FF) * n78 + (n80 & 0xFF00FF) * n79 & 0xFF00FF00) + ((n77 & 0xFF00) * n78 + (n80 & 0xFF00) * n79 & 0xFF0000) >> 8;
                                    }
                                    else if (n78 != 255) {
                                        final int n81 = (((n77 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n77 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n77 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n82 = array2[n66];
                                        array2[n66] = ((n81 & 0xFF00FF) * n78 + (n82 & 0xFF00FF) * n79 & 0xFF00FF00) + ((n81 & 0xFF00) * n78 + (n82 & 0xFF00) * n79 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array2[n66] = (((n77 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n77 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n77 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                                else if (n == 3) {
                                    final int n83 = this.anIntArray6212[n65];
                                    final int anInt5460 = Class332_Sub3.anInt5432;
                                    final int n84 = n83 + anInt5460;
                                    final int n85 = (n83 & 0xFF00FF) + (anInt5460 & 0xFF00FF);
                                    final int n86 = (n85 & 0x1000100) + (n84 - n85 & 0x10000);
                                    int n87 = n84 - n86 | n86 - (n86 >>> 8);
                                    final int n88 = (n83 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n89 = 256 - n88;
                                    if (n88 != 255) {
                                        final int n90 = n87;
                                        final int n91 = array2[n66];
                                        n87 = ((n90 & 0xFF00FF) * n88 + (n91 & 0xFF00FF) * n89 & 0xFF00FF00) + ((n90 & 0xFF00) * n88 + (n91 & 0xFF00) * n89 & 0xFF0000) >> 8;
                                    }
                                    array2[n66] = n87;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n92 = this.anIntArray6212[n65];
                                    final int n93 = n92 >>> 24;
                                    final int n94 = 256 - n93;
                                    final int n95 = ((((n92 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n92 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n96 = array2[n66];
                                    array2[n66] = ((n95 & 0xFF00FF) * n93 + (n96 & 0xFF00FF) * n94 & 0xFF00FF00) + ((n95 & 0xFF00) * n93 + (n96 & 0xFF00) * n94 & 0xFF0000) >> 8;
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n97 = this.anIntArray6212[n65];
                                    final int n98 = array2[n66];
                                    final int n99 = n97 + n98;
                                    final int n100 = (n97 & 0xFF00FF) + (n98 & 0xFF00FF);
                                    final int n101 = (n100 & 0x1000100) + (n99 - n100 & 0x10000);
                                    array2[n66] = (n99 - n101 | n101 - (n101 >>> 8));
                                }
                                else if (n == 0) {
                                    final int n102 = this.anIntArray6212[n65];
                                    final int n103 = (((n102 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n102 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n102 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n104 = array2[n66];
                                    final int n105 = n103 + n104;
                                    final int n106 = (n103 & 0xFF00FF) + (n104 & 0xFF00FF);
                                    final int n107 = (n106 & 0x1000100) + (n105 - n106 & 0x10000);
                                    array2[n66] = (n105 - n107 | n107 - (n107 >>> 8));
                                }
                                else if (n == 3) {
                                    final int n108 = this.anIntArray6212[n65];
                                    final int anInt5461 = Class332_Sub3.anInt5432;
                                    final int n109 = n108 + anInt5461;
                                    final int n110 = (n108 & 0xFF00FF) + (anInt5461 & 0xFF00FF);
                                    final int n111 = (n110 & 0x1000100) + (n109 - n110 & 0x10000);
                                    final int n112 = n109 - n111 | n111 - (n111 >>> 8);
                                    final int n113 = array2[n66];
                                    final int n114 = n112 + n113;
                                    final int n115 = (n112 & 0xFF00FF) + (n113 & 0xFF00FF);
                                    final int n116 = (n115 & 0x1000100) + (n114 - n115 & 0x10000);
                                    array2[n66] = (n114 - n116 | n116 - (n116 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n117 = this.anIntArray6212[n65];
                                    final int n118 = ((((n117 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n117 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n119 = array2[n66];
                                    final int n120 = n118 + n119;
                                    final int n121 = (n118 & 0xFF00FF) + (n119 & 0xFF00FF);
                                    final int n122 = (n121 & 0x1000100) + (n120 - n121 & 0x10000);
                                    array2[n66] = (n120 - n122 | n122 - (n122 >>> 8));
                                }
                            }
                            n61 += Class332_Sub3.anInt5436;
                            ++l;
                        }
                    }
                }
            }
            else {
                for (int anInt5462 = Class332_Sub3.anInt5431; anInt5462 < 0; ++anInt5462, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5463 = Class332_Sub3.anInt5451;
                    final int anInt5464 = Class332_Sub3.anInt5450;
                    int n123 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5465 = Class332_Sub3.anInt5448;
                    if (anInt5464 >= 0 && anInt5464 - (super.anInt5433 << 12) < 0) {
                        if (n123 < 0) {
                            final int n124 = (Class332_Sub3.anInt5436 - 1 - n123) / Class332_Sub3.anInt5436;
                            anInt5465 += n124;
                            n123 += Class332_Sub3.anInt5436 * n124;
                            anInt5463 += n124;
                        }
                        final int n125;
                        if ((n125 = (1 + n123 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5465) {
                            anInt5465 = n125;
                        }
                        while (anInt5465 < 0) {
                            int n126 = (n123 >> 12) * super.anInt5433 + (anInt5464 >> 12);
                            final int n127 = anInt5463++;
                            final int[] array3 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array3[n127] = this.anIntArray6212[n126];
                                }
                                else if (n == 0) {
                                    final int n128 = this.anIntArray6212[n126++];
                                    array3[n127] = (((n128 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n128 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n128 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n129 = this.anIntArray6212[n126++];
                                    final int anInt5466 = Class332_Sub3.anInt5432;
                                    final int n130 = n129 + anInt5466;
                                    final int n131 = (n129 & 0xFF00FF) + (anInt5466 & 0xFF00FF);
                                    final int n132 = (n131 & 0x1000100) + (n130 - n131 & 0x10000);
                                    array3[n127] = (n130 - n132 | n132 - (n132 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n133 = this.anIntArray6212[n126];
                                    array3[n127] = ((((n133 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n133 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n134 = this.anIntArray6212[n126];
                                    final int n135 = n134 >>> 24;
                                    final int n136 = 256 - n135;
                                    final int n137 = array3[n127];
                                    array3[n127] = ((n134 & 0xFF00FF) * n135 + (n137 & 0xFF00FF) * n136 & 0xFF00FF00) + ((n134 & 0xFF00) * n135 + (n137 & 0xFF00) * n136 & 0xFF0000) >> 8;
                                }
                                else if (n == 0) {
                                    final int n138 = this.anIntArray6212[n126];
                                    final int n139 = (n138 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n140 = 256 - n139;
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n141 = array3[n127];
                                        array3[n127] = ((n138 & 0xFF00FF) * n139 + (n141 & 0xFF00FF) * n140 & 0xFF00FF00) + ((n138 & 0xFF00) * n139 + (n141 & 0xFF00) * n140 & 0xFF0000) >> 8;
                                    }
                                    else if (n139 != 255) {
                                        final int n142 = (((n138 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n138 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n138 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n143 = array3[n127];
                                        array3[n127] = ((n142 & 0xFF00FF) * n139 + (n143 & 0xFF00FF) * n140 & 0xFF00FF00) + ((n142 & 0xFF00) * n139 + (n143 & 0xFF00) * n140 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array3[n127] = (((n138 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n138 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n138 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                                else if (n == 3) {
                                    final int n144 = this.anIntArray6212[n126];
                                    final int anInt5467 = Class332_Sub3.anInt5432;
                                    final int n145 = n144 + anInt5467;
                                    final int n146 = (n144 & 0xFF00FF) + (anInt5467 & 0xFF00FF);
                                    final int n147 = (n146 & 0x1000100) + (n145 - n146 & 0x10000);
                                    int n148 = n145 - n147 | n147 - (n147 >>> 8);
                                    final int n149 = (n144 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n150 = 256 - n149;
                                    if (n149 != 255) {
                                        final int n151 = n148;
                                        final int n152 = array3[n127];
                                        n148 = ((n151 & 0xFF00FF) * n149 + (n152 & 0xFF00FF) * n150 & 0xFF00FF00) + ((n151 & 0xFF00) * n149 + (n152 & 0xFF00) * n150 & 0xFF0000) >> 8;
                                    }
                                    array3[n127] = n148;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n153 = this.anIntArray6212[n126];
                                    final int n154 = n153 >>> 24;
                                    final int n155 = 256 - n154;
                                    final int n156 = ((((n153 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n153 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n157 = array3[n127];
                                    array3[n127] = ((n156 & 0xFF00FF) * n154 + (n157 & 0xFF00FF) * n155 & 0xFF00FF00) + ((n156 & 0xFF00) * n154 + (n157 & 0xFF00) * n155 & 0xFF0000) >> 8;
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n158 = this.anIntArray6212[n126];
                                    final int n159 = array3[n127];
                                    final int n160 = n158 + n159;
                                    final int n161 = (n158 & 0xFF00FF) + (n159 & 0xFF00FF);
                                    final int n162 = (n161 & 0x1000100) + (n160 - n161 & 0x10000);
                                    array3[n127] = (n160 - n162 | n162 - (n162 >>> 8));
                                }
                                else if (n == 0) {
                                    final int n163 = this.anIntArray6212[n126];
                                    final int n164 = (((n163 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n163 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n163 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n165 = array3[n127];
                                    final int n166 = n164 + n165;
                                    final int n167 = (n164 & 0xFF00FF) + (n165 & 0xFF00FF);
                                    final int n168 = (n167 & 0x1000100) + (n166 - n167 & 0x10000);
                                    array3[n127] = (n166 - n168 | n168 - (n168 >>> 8));
                                }
                                else if (n == 3) {
                                    final int n169 = this.anIntArray6212[n126];
                                    final int anInt5468 = Class332_Sub3.anInt5432;
                                    final int n170 = n169 + anInt5468;
                                    final int n171 = (n169 & 0xFF00FF) + (anInt5468 & 0xFF00FF);
                                    final int n172 = (n171 & 0x1000100) + (n170 - n171 & 0x10000);
                                    final int n173 = n170 - n172 | n172 - (n172 >>> 8);
                                    final int n174 = array3[n127];
                                    final int n175 = n173 + n174;
                                    final int n176 = (n173 & 0xFF00FF) + (n174 & 0xFF00FF);
                                    final int n177 = (n176 & 0x1000100) + (n175 - n176 & 0x10000);
                                    array3[n127] = (n175 - n177 | n177 - (n177 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n178 = this.anIntArray6212[n126];
                                    final int n179 = ((((n178 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n178 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n180 = array3[n127];
                                    final int n181 = n179 + n180;
                                    final int n182 = (n179 & 0xFF00FF) + (n180 & 0xFF00FF);
                                    final int n183 = (n182 & 0x1000100) + (n181 - n182 & 0x10000);
                                    array3[n127] = (n181 - n183 | n183 - (n183 >>> 8));
                                }
                            }
                            n123 += Class332_Sub3.anInt5436;
                            ++anInt5465;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5444 < 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int anInt5469 = Class332_Sub3.anInt5431; anInt5469 < 0; ++anInt5469, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5470 = Class332_Sub3.anInt5451;
                    int n184 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    final int anInt5471 = Class332_Sub3.anInt5443;
                    int anInt5472 = Class332_Sub3.anInt5448;
                    if (anInt5471 >= 0 && anInt5471 - (super.anInt5454 << 12) < 0) {
                        final int n185;
                        if ((n185 = n184 - (super.anInt5433 << 12)) >= 0) {
                            final int n186 = (Class332_Sub3.anInt5444 - n185) / Class332_Sub3.anInt5444;
                            anInt5472 += n186;
                            n184 += Class332_Sub3.anInt5444 * n186;
                            anInt5470 += n186;
                        }
                        final int n187;
                        if ((n187 = (n184 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5472) {
                            anInt5472 = n187;
                        }
                        while (anInt5472 < 0) {
                            int n188 = (anInt5471 >> 12) * super.anInt5433 + (n184 >> 12);
                            final int n189 = anInt5470++;
                            final int[] array4 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array4[n189] = this.anIntArray6212[n188];
                                }
                                else if (n == 0) {
                                    final int n190 = this.anIntArray6212[n188++];
                                    array4[n189] = (((n190 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n190 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n190 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n191 = this.anIntArray6212[n188++];
                                    final int anInt5473 = Class332_Sub3.anInt5432;
                                    final int n192 = n191 + anInt5473;
                                    final int n193 = (n191 & 0xFF00FF) + (anInt5473 & 0xFF00FF);
                                    final int n194 = (n193 & 0x1000100) + (n192 - n193 & 0x10000);
                                    array4[n189] = (n192 - n194 | n194 - (n194 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n195 = this.anIntArray6212[n188];
                                    array4[n189] = ((((n195 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n195 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n196 = this.anIntArray6212[n188];
                                    final int n197 = n196 >>> 24;
                                    final int n198 = 256 - n197;
                                    final int n199 = array4[n189];
                                    array4[n189] = ((n196 & 0xFF00FF) * n197 + (n199 & 0xFF00FF) * n198 & 0xFF00FF00) + ((n196 & 0xFF00) * n197 + (n199 & 0xFF00) * n198 & 0xFF0000) >> 8;
                                }
                                else if (n == 0) {
                                    final int n200 = this.anIntArray6212[n188];
                                    final int n201 = (n200 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n202 = 256 - n201;
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n203 = array4[n189];
                                        array4[n189] = ((n200 & 0xFF00FF) * n201 + (n203 & 0xFF00FF) * n202 & 0xFF00FF00) + ((n200 & 0xFF00) * n201 + (n203 & 0xFF00) * n202 & 0xFF0000) >> 8;
                                    }
                                    else if (n201 != 255) {
                                        final int n204 = (((n200 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n200 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n200 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n205 = array4[n189];
                                        array4[n189] = ((n204 & 0xFF00FF) * n201 + (n205 & 0xFF00FF) * n202 & 0xFF00FF00) + ((n204 & 0xFF00) * n201 + (n205 & 0xFF00) * n202 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array4[n189] = (((n200 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n200 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n200 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                                else if (n == 3) {
                                    final int n206 = this.anIntArray6212[n188];
                                    final int anInt5474 = Class332_Sub3.anInt5432;
                                    final int n207 = n206 + anInt5474;
                                    final int n208 = (n206 & 0xFF00FF) + (anInt5474 & 0xFF00FF);
                                    final int n209 = (n208 & 0x1000100) + (n207 - n208 & 0x10000);
                                    int n210 = n207 - n209 | n209 - (n209 >>> 8);
                                    final int n211 = (n206 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                    final int n212 = 256 - n211;
                                    if (n211 != 255) {
                                        final int n213 = n210;
                                        final int n214 = array4[n189];
                                        n210 = ((n213 & 0xFF00FF) * n211 + (n214 & 0xFF00FF) * n212 & 0xFF00FF00) + ((n213 & 0xFF00) * n211 + (n214 & 0xFF00) * n212 & 0xFF0000) >> 8;
                                    }
                                    array4[n189] = n210;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n215 = this.anIntArray6212[n188];
                                    final int n216 = n215 >>> 24;
                                    final int n217 = 256 - n216;
                                    final int n218 = ((((n215 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n215 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n219 = array4[n189];
                                    array4[n189] = ((n218 & 0xFF00FF) * n216 + (n219 & 0xFF00FF) * n217 & 0xFF00FF00) + ((n218 & 0xFF00) * n216 + (n219 & 0xFF00) * n217 & 0xFF0000) >> 8;
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n220 = this.anIntArray6212[n188];
                                    final int n221 = array4[n189];
                                    final int n222 = n220 + n221;
                                    final int n223 = (n220 & 0xFF00FF) + (n221 & 0xFF00FF);
                                    final int n224 = (n223 & 0x1000100) + (n222 - n223 & 0x10000);
                                    array4[n189] = (n222 - n224 | n224 - (n224 >>> 8));
                                }
                                else if (n == 0) {
                                    final int n225 = this.anIntArray6212[n188];
                                    final int n226 = (((n225 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n225 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n225 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n227 = array4[n189];
                                    final int n228 = n226 + n227;
                                    final int n229 = (n226 & 0xFF00FF) + (n227 & 0xFF00FF);
                                    final int n230 = (n229 & 0x1000100) + (n228 - n229 & 0x10000);
                                    array4[n189] = (n228 - n230 | n230 - (n230 >>> 8));
                                }
                                else if (n == 3) {
                                    final int n231 = this.anIntArray6212[n188];
                                    final int anInt5475 = Class332_Sub3.anInt5432;
                                    final int n232 = n231 + anInt5475;
                                    final int n233 = (n231 & 0xFF00FF) + (anInt5475 & 0xFF00FF);
                                    final int n234 = (n233 & 0x1000100) + (n232 - n233 & 0x10000);
                                    final int n235 = n232 - n234 | n234 - (n234 >>> 8);
                                    final int n236 = array4[n189];
                                    final int n237 = n235 + n236;
                                    final int n238 = (n235 & 0xFF00FF) + (n236 & 0xFF00FF);
                                    final int n239 = (n238 & 0x1000100) + (n237 - n238 & 0x10000);
                                    array4[n189] = (n237 - n239 | n239 - (n239 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n240 = this.anIntArray6212[n188];
                                    final int n241 = ((((n240 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n240 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n242 = array4[n189];
                                    final int n243 = n241 + n242;
                                    final int n244 = (n241 & 0xFF00FF) + (n242 & 0xFF00FF);
                                    final int n245 = (n244 & 0x1000100) + (n243 - n244 & 0x10000);
                                    array4[n189] = (n243 - n245 | n245 - (n245 >>> 8));
                                }
                            }
                            n184 += Class332_Sub3.anInt5444;
                            ++anInt5472;
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int anInt5476 = Class332_Sub3.anInt5431; anInt5476 < 0; ++anInt5476, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5477 = Class332_Sub3.anInt5451;
                    int n246 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n247 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5478 = Class332_Sub3.anInt5448;
                    final int n248;
                    if ((n248 = n246 - (super.anInt5433 << 12)) >= 0) {
                        final int n249 = (Class332_Sub3.anInt5444 - n248) / Class332_Sub3.anInt5444;
                        anInt5478 += n249;
                        n246 += Class332_Sub3.anInt5444 * n249;
                        n247 += Class332_Sub3.anInt5436 * n249;
                        anInt5477 += n249;
                    }
                    final int n250;
                    if ((n250 = (n246 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5478) {
                        anInt5478 = n250;
                    }
                    final int n251;
                    if ((n251 = n247 - (super.anInt5454 << 12)) >= 0) {
                        final int n252 = (Class332_Sub3.anInt5436 - n251) / Class332_Sub3.anInt5436;
                        anInt5478 += n252;
                        n246 += Class332_Sub3.anInt5444 * n252;
                        n247 += Class332_Sub3.anInt5436 * n252;
                        anInt5477 += n252;
                    }
                    final int n253;
                    if ((n253 = (n247 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5478) {
                        anInt5478 = n253;
                    }
                    while (anInt5478 < 0) {
                        int n254 = (n247 >> 12) * super.anInt5433 + (n246 >> 12);
                        final int n255 = anInt5477++;
                        final int[] array5 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array5[n255] = this.anIntArray6212[n254];
                            }
                            else if (n == 0) {
                                final int n256 = this.anIntArray6212[n254++];
                                array5[n255] = (((n256 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n256 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n256 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n257 = this.anIntArray6212[n254++];
                                final int anInt5479 = Class332_Sub3.anInt5432;
                                final int n258 = n257 + anInt5479;
                                final int n259 = (n257 & 0xFF00FF) + (anInt5479 & 0xFF00FF);
                                final int n260 = (n259 & 0x1000100) + (n258 - n259 & 0x10000);
                                array5[n255] = (n258 - n260 | n260 - (n260 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n261 = this.anIntArray6212[n254];
                                array5[n255] = ((((n261 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n261 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final int n262 = this.anIntArray6212[n254];
                                final int n263 = n262 >>> 24;
                                final int n264 = 256 - n263;
                                final int n265 = array5[n255];
                                array5[n255] = ((n262 & 0xFF00FF) * n263 + (n265 & 0xFF00FF) * n264 & 0xFF00FF00) + ((n262 & 0xFF00) * n263 + (n265 & 0xFF00) * n264 & 0xFF0000) >> 8;
                            }
                            else if (n == 0) {
                                final int n266 = this.anIntArray6212[n254];
                                final int n267 = (n266 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                final int n268 = 256 - n267;
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n269 = array5[n255];
                                    array5[n255] = ((n266 & 0xFF00FF) * n267 + (n269 & 0xFF00FF) * n268 & 0xFF00FF00) + ((n266 & 0xFF00) * n267 + (n269 & 0xFF00) * n268 & 0xFF0000) >> 8;
                                }
                                else if (n267 != 255) {
                                    final int n270 = (((n266 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n266 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n266 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n271 = array5[n255];
                                    array5[n255] = ((n270 & 0xFF00FF) * n267 + (n271 & 0xFF00FF) * n268 & 0xFF00FF00) + ((n270 & 0xFF00) * n267 + (n271 & 0xFF00) * n268 & 0xFF0000) >> 8;
                                }
                                else {
                                    array5[n255] = (((n266 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n266 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n266 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                            else if (n == 3) {
                                final int n272 = this.anIntArray6212[n254];
                                final int anInt5480 = Class332_Sub3.anInt5432;
                                final int n273 = n272 + anInt5480;
                                final int n274 = (n272 & 0xFF00FF) + (anInt5480 & 0xFF00FF);
                                final int n275 = (n274 & 0x1000100) + (n273 - n274 & 0x10000);
                                int n276 = n273 - n275 | n275 - (n275 >>> 8);
                                final int n277 = (n272 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                final int n278 = 256 - n277;
                                if (n277 != 255) {
                                    final int n279 = n276;
                                    final int n280 = array5[n255];
                                    n276 = ((n279 & 0xFF00FF) * n277 + (n280 & 0xFF00FF) * n278 & 0xFF00FF00) + ((n279 & 0xFF00) * n277 + (n280 & 0xFF00) * n278 & 0xFF0000) >> 8;
                                }
                                array5[n255] = n276;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n281 = this.anIntArray6212[n254];
                                final int n282 = n281 >>> 24;
                                final int n283 = 256 - n282;
                                final int n284 = ((((n281 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n281 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n285 = array5[n255];
                                array5[n255] = ((n284 & 0xFF00FF) * n282 + (n285 & 0xFF00FF) * n283 & 0xFF00FF00) + ((n284 & 0xFF00) * n282 + (n285 & 0xFF00) * n283 & 0xFF0000) >> 8;
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final int n286 = this.anIntArray6212[n254];
                                final int n287 = array5[n255];
                                final int n288 = n286 + n287;
                                final int n289 = (n286 & 0xFF00FF) + (n287 & 0xFF00FF);
                                final int n290 = (n289 & 0x1000100) + (n288 - n289 & 0x10000);
                                array5[n255] = (n288 - n290 | n290 - (n290 >>> 8));
                            }
                            else if (n == 0) {
                                final int n291 = this.anIntArray6212[n254];
                                final int n292 = (((n291 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n291 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n291 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n293 = array5[n255];
                                final int n294 = n292 + n293;
                                final int n295 = (n292 & 0xFF00FF) + (n293 & 0xFF00FF);
                                final int n296 = (n295 & 0x1000100) + (n294 - n295 & 0x10000);
                                array5[n255] = (n294 - n296 | n296 - (n296 >>> 8));
                            }
                            else if (n == 3) {
                                final int n297 = this.anIntArray6212[n254];
                                final int anInt5481 = Class332_Sub3.anInt5432;
                                final int n298 = n297 + anInt5481;
                                final int n299 = (n297 & 0xFF00FF) + (anInt5481 & 0xFF00FF);
                                final int n300 = (n299 & 0x1000100) + (n298 - n299 & 0x10000);
                                final int n301 = n298 - n300 | n300 - (n300 >>> 8);
                                final int n302 = array5[n255];
                                final int n303 = n301 + n302;
                                final int n304 = (n301 & 0xFF00FF) + (n302 & 0xFF00FF);
                                final int n305 = (n304 & 0x1000100) + (n303 - n304 & 0x10000);
                                array5[n255] = (n303 - n305 | n305 - (n305 >>> 8));
                            }
                            else if (n == 2) {
                                final int n306 = this.anIntArray6212[n254];
                                final int n307 = ((((n306 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n306 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n308 = array5[n255];
                                final int n309 = n307 + n308;
                                final int n310 = (n307 & 0xFF00FF) + (n308 & 0xFF00FF);
                                final int n311 = (n310 & 0x1000100) + (n309 - n310 & 0x10000);
                                array5[n255] = (n309 - n311 | n311 - (n311 >>> 8));
                            }
                        }
                        n246 += Class332_Sub3.anInt5444;
                        n247 += Class332_Sub3.anInt5436;
                        ++anInt5478;
                    }
                }
            }
            else {
                for (int anInt5482 = Class332_Sub3.anInt5431; anInt5482 < 0; ++anInt5482, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5483 = Class332_Sub3.anInt5451;
                    int n312 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n313 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5484 = Class332_Sub3.anInt5448;
                    final int n314;
                    if ((n314 = n312 - (super.anInt5433 << 12)) >= 0) {
                        final int n315 = (Class332_Sub3.anInt5444 - n314) / Class332_Sub3.anInt5444;
                        anInt5484 += n315;
                        n312 += Class332_Sub3.anInt5444 * n315;
                        n313 += Class332_Sub3.anInt5436 * n315;
                        anInt5483 += n315;
                    }
                    final int n316;
                    if ((n316 = (n312 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5484) {
                        anInt5484 = n316;
                    }
                    if (n313 < 0) {
                        final int n317 = (Class332_Sub3.anInt5436 - 1 - n313) / Class332_Sub3.anInt5436;
                        anInt5484 += n317;
                        n312 += Class332_Sub3.anInt5444 * n317;
                        n313 += Class332_Sub3.anInt5436 * n317;
                        anInt5483 += n317;
                    }
                    final int n318;
                    if ((n318 = (1 + n313 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5484) {
                        anInt5484 = n318;
                    }
                    while (anInt5484 < 0) {
                        int n319 = (n313 >> 12) * super.anInt5433 + (n312 >> 12);
                        final int n320 = anInt5483++;
                        final int[] array6 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array6[n320] = this.anIntArray6212[n319];
                            }
                            else if (n == 0) {
                                final int n321 = this.anIntArray6212[n319++];
                                array6[n320] = (((n321 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n321 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n321 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n322 = this.anIntArray6212[n319++];
                                final int anInt5485 = Class332_Sub3.anInt5432;
                                final int n323 = n322 + anInt5485;
                                final int n324 = (n322 & 0xFF00FF) + (anInt5485 & 0xFF00FF);
                                final int n325 = (n324 & 0x1000100) + (n323 - n324 & 0x10000);
                                array6[n320] = (n323 - n325 | n325 - (n325 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n326 = this.anIntArray6212[n319];
                                array6[n320] = ((((n326 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n326 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final int n327 = this.anIntArray6212[n319];
                                final int n328 = n327 >>> 24;
                                final int n329 = 256 - n328;
                                final int n330 = array6[n320];
                                array6[n320] = ((n327 & 0xFF00FF) * n328 + (n330 & 0xFF00FF) * n329 & 0xFF00FF00) + ((n327 & 0xFF00) * n328 + (n330 & 0xFF00) * n329 & 0xFF0000) >> 8;
                            }
                            else if (n == 0) {
                                final int n331 = this.anIntArray6212[n319];
                                final int n332 = (n331 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                final int n333 = 256 - n332;
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n334 = array6[n320];
                                    array6[n320] = ((n331 & 0xFF00FF) * n332 + (n334 & 0xFF00FF) * n333 & 0xFF00FF00) + ((n331 & 0xFF00) * n332 + (n334 & 0xFF00) * n333 & 0xFF0000) >> 8;
                                }
                                else if (n332 != 255) {
                                    final int n335 = (((n331 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n331 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n331 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n336 = array6[n320];
                                    array6[n320] = ((n335 & 0xFF00FF) * n332 + (n336 & 0xFF00FF) * n333 & 0xFF00FF00) + ((n335 & 0xFF00) * n332 + (n336 & 0xFF00) * n333 & 0xFF0000) >> 8;
                                }
                                else {
                                    array6[n320] = (((n331 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n331 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n331 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                            else if (n == 3) {
                                final int n337 = this.anIntArray6212[n319];
                                final int anInt5486 = Class332_Sub3.anInt5432;
                                final int n338 = n337 + anInt5486;
                                final int n339 = (n337 & 0xFF00FF) + (anInt5486 & 0xFF00FF);
                                final int n340 = (n339 & 0x1000100) + (n338 - n339 & 0x10000);
                                int n341 = n338 - n340 | n340 - (n340 >>> 8);
                                final int n342 = (n337 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                final int n343 = 256 - n342;
                                if (n342 != 255) {
                                    final int n344 = n341;
                                    final int n345 = array6[n320];
                                    n341 = ((n344 & 0xFF00FF) * n342 + (n345 & 0xFF00FF) * n343 & 0xFF00FF00) + ((n344 & 0xFF00) * n342 + (n345 & 0xFF00) * n343 & 0xFF0000) >> 8;
                                }
                                array6[n320] = n341;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n346 = this.anIntArray6212[n319];
                                final int n347 = n346 >>> 24;
                                final int n348 = 256 - n347;
                                final int n349 = ((((n346 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n346 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n350 = array6[n320];
                                array6[n320] = ((n349 & 0xFF00FF) * n347 + (n350 & 0xFF00FF) * n348 & 0xFF00FF00) + ((n349 & 0xFF00) * n347 + (n350 & 0xFF00) * n348 & 0xFF0000) >> 8;
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final int n351 = this.anIntArray6212[n319];
                                final int n352 = array6[n320];
                                final int n353 = n351 + n352;
                                final int n354 = (n351 & 0xFF00FF) + (n352 & 0xFF00FF);
                                final int n355 = (n354 & 0x1000100) + (n353 - n354 & 0x10000);
                                array6[n320] = (n353 - n355 | n355 - (n355 >>> 8));
                            }
                            else if (n == 0) {
                                final int n356 = this.anIntArray6212[n319];
                                final int n357 = (((n356 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n356 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n356 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n358 = array6[n320];
                                final int n359 = n357 + n358;
                                final int n360 = (n357 & 0xFF00FF) + (n358 & 0xFF00FF);
                                final int n361 = (n360 & 0x1000100) + (n359 - n360 & 0x10000);
                                array6[n320] = (n359 - n361 | n361 - (n361 >>> 8));
                            }
                            else if (n == 3) {
                                final int n362 = this.anIntArray6212[n319];
                                final int anInt5487 = Class332_Sub3.anInt5432;
                                final int n363 = n362 + anInt5487;
                                final int n364 = (n362 & 0xFF00FF) + (anInt5487 & 0xFF00FF);
                                final int n365 = (n364 & 0x1000100) + (n363 - n364 & 0x10000);
                                final int n366 = n363 - n365 | n365 - (n365 >>> 8);
                                final int n367 = array6[n320];
                                final int n368 = n366 + n367;
                                final int n369 = (n366 & 0xFF00FF) + (n367 & 0xFF00FF);
                                final int n370 = (n369 & 0x1000100) + (n368 - n369 & 0x10000);
                                array6[n320] = (n368 - n370 | n370 - (n370 >>> 8));
                            }
                            else if (n == 2) {
                                final int n371 = this.anIntArray6212[n319];
                                final int n372 = ((((n371 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n371 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n373 = array6[n320];
                                final int n374 = n372 + n373;
                                final int n375 = (n372 & 0xFF00FF) + (n373 & 0xFF00FF);
                                final int n376 = (n375 & 0x1000100) + (n374 - n375 & 0x10000);
                                array6[n320] = (n374 - n376 | n376 - (n376 >>> 8));
                            }
                        }
                        n312 += Class332_Sub3.anInt5444;
                        n313 += Class332_Sub3.anInt5436;
                        ++anInt5484;
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 == 0) {
            for (int anInt5488 = Class332_Sub3.anInt5431; anInt5488 < 0; ++anInt5488, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                int anInt5489 = Class332_Sub3.anInt5451;
                int n377 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                final int anInt5490 = Class332_Sub3.anInt5443;
                int anInt5491 = Class332_Sub3.anInt5448;
                if (anInt5490 >= 0 && anInt5490 - (super.anInt5454 << 12) < 0) {
                    if (n377 < 0) {
                        final int n378 = (Class332_Sub3.anInt5444 - 1 - n377) / Class332_Sub3.anInt5444;
                        anInt5491 += n378;
                        n377 += Class332_Sub3.anInt5444 * n378;
                        anInt5489 += n378;
                    }
                    final int n379;
                    if ((n379 = (1 + n377 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5491) {
                        anInt5491 = n379;
                    }
                    while (anInt5491 < 0) {
                        int n380 = (anInt5490 >> 12) * super.anInt5433 + (n377 >> 12);
                        final int n381 = anInt5489++;
                        final int[] array7 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array7[n381] = this.anIntArray6212[n380];
                            }
                            else if (n == 0) {
                                final int n382 = this.anIntArray6212[n380++];
                                array7[n381] = (((n382 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n382 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n382 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n383 = this.anIntArray6212[n380++];
                                final int anInt5492 = Class332_Sub3.anInt5432;
                                final int n384 = n383 + anInt5492;
                                final int n385 = (n383 & 0xFF00FF) + (anInt5492 & 0xFF00FF);
                                final int n386 = (n385 & 0x1000100) + (n384 - n385 & 0x10000);
                                array7[n381] = (n384 - n386 | n386 - (n386 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n387 = this.anIntArray6212[n380];
                                array7[n381] = ((((n387 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n387 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final int n388 = this.anIntArray6212[n380];
                                final int n389 = n388 >>> 24;
                                final int n390 = 256 - n389;
                                final int n391 = array7[n381];
                                array7[n381] = ((n388 & 0xFF00FF) * n389 + (n391 & 0xFF00FF) * n390 & 0xFF00FF00) + ((n388 & 0xFF00) * n389 + (n391 & 0xFF00) * n390 & 0xFF0000) >> 8;
                            }
                            else if (n == 0) {
                                final int n392 = this.anIntArray6212[n380];
                                final int n393 = (n392 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                final int n394 = 256 - n393;
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n395 = array7[n381];
                                    array7[n381] = ((n392 & 0xFF00FF) * n393 + (n395 & 0xFF00FF) * n394 & 0xFF00FF00) + ((n392 & 0xFF00) * n393 + (n395 & 0xFF00) * n394 & 0xFF0000) >> 8;
                                }
                                else if (n393 != 255) {
                                    final int n396 = (((n392 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n392 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n392 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n397 = array7[n381];
                                    array7[n381] = ((n396 & 0xFF00FF) * n393 + (n397 & 0xFF00FF) * n394 & 0xFF00FF00) + ((n396 & 0xFF00) * n393 + (n397 & 0xFF00) * n394 & 0xFF0000) >> 8;
                                }
                                else {
                                    array7[n381] = (((n392 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n392 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n392 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                            else if (n == 3) {
                                final int n398 = this.anIntArray6212[n380];
                                final int anInt5493 = Class332_Sub3.anInt5432;
                                final int n399 = n398 + anInt5493;
                                final int n400 = (n398 & 0xFF00FF) + (anInt5493 & 0xFF00FF);
                                final int n401 = (n400 & 0x1000100) + (n399 - n400 & 0x10000);
                                int n402 = n399 - n401 | n401 - (n401 >>> 8);
                                final int n403 = (n398 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                                final int n404 = 256 - n403;
                                if (n403 != 255) {
                                    final int n405 = n402;
                                    final int n406 = array7[n381];
                                    n402 = ((n405 & 0xFF00FF) * n403 + (n406 & 0xFF00FF) * n404 & 0xFF00FF00) + ((n405 & 0xFF00) * n403 + (n406 & 0xFF00) * n404 & 0xFF0000) >> 8;
                                }
                                array7[n381] = n402;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n407 = this.anIntArray6212[n380];
                                final int n408 = n407 >>> 24;
                                final int n409 = 256 - n408;
                                final int n410 = ((((n407 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n407 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n411 = array7[n381];
                                array7[n381] = ((n410 & 0xFF00FF) * n408 + (n411 & 0xFF00FF) * n409 & 0xFF00FF00) + ((n410 & 0xFF00) * n408 + (n411 & 0xFF00) * n409 & 0xFF0000) >> 8;
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final int n412 = this.anIntArray6212[n380];
                                final int n413 = array7[n381];
                                final int n414 = n412 + n413;
                                final int n415 = (n412 & 0xFF00FF) + (n413 & 0xFF00FF);
                                final int n416 = (n415 & 0x1000100) + (n414 - n415 & 0x10000);
                                array7[n381] = (n414 - n416 | n416 - (n416 >>> 8));
                            }
                            else if (n == 0) {
                                final int n417 = this.anIntArray6212[n380];
                                final int n418 = (((n417 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n417 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n417 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n419 = array7[n381];
                                final int n420 = n418 + n419;
                                final int n421 = (n418 & 0xFF00FF) + (n419 & 0xFF00FF);
                                final int n422 = (n421 & 0x1000100) + (n420 - n421 & 0x10000);
                                array7[n381] = (n420 - n422 | n422 - (n422 >>> 8));
                            }
                            else if (n == 3) {
                                final int n423 = this.anIntArray6212[n380];
                                final int anInt5494 = Class332_Sub3.anInt5432;
                                final int n424 = n423 + anInt5494;
                                final int n425 = (n423 & 0xFF00FF) + (anInt5494 & 0xFF00FF);
                                final int n426 = (n425 & 0x1000100) + (n424 - n425 & 0x10000);
                                final int n427 = n424 - n426 | n426 - (n426 >>> 8);
                                final int n428 = array7[n381];
                                final int n429 = n427 + n428;
                                final int n430 = (n427 & 0xFF00FF) + (n428 & 0xFF00FF);
                                final int n431 = (n430 & 0x1000100) + (n429 - n430 & 0x10000);
                                array7[n381] = (n429 - n431 | n431 - (n431 >>> 8));
                            }
                            else if (n == 2) {
                                final int n432 = this.anIntArray6212[n380];
                                final int n433 = ((((n432 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n432 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n434 = array7[n381];
                                final int n435 = n433 + n434;
                                final int n436 = (n433 & 0xFF00FF) + (n434 & 0xFF00FF);
                                final int n437 = (n436 & 0x1000100) + (n435 - n436 & 0x10000);
                                array7[n381] = (n435 - n437 | n437 - (n437 >>> 8));
                            }
                        }
                        n377 += Class332_Sub3.anInt5444;
                        ++anInt5491;
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 < 0) {
            for (int anInt5495 = Class332_Sub3.anInt5431; anInt5495 < 0; ++anInt5495) {
                int anInt5496 = Class332_Sub3.anInt5451;
                int n438 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                int n439 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                int anInt5497 = Class332_Sub3.anInt5448;
                if (n438 < 0) {
                    final int n440 = (Class332_Sub3.anInt5444 - 1 - n438) / Class332_Sub3.anInt5444;
                    anInt5497 += n440;
                    n438 += Class332_Sub3.anInt5444 * n440;
                    n439 += Class332_Sub3.anInt5436 * n440;
                    anInt5496 += n440;
                }
                final int n441;
                if ((n441 = (1 + n438 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5497) {
                    anInt5497 = n441;
                }
                final int n442;
                if ((n442 = n439 - (super.anInt5454 << 12)) >= 0) {
                    final int n443 = (Class332_Sub3.anInt5436 - n442) / Class332_Sub3.anInt5436;
                    anInt5497 += n443;
                    n438 += Class332_Sub3.anInt5444 * n443;
                    n439 += Class332_Sub3.anInt5436 * n443;
                    anInt5496 += n443;
                }
                final int n444;
                if ((n444 = (n439 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5497) {
                    anInt5497 = n444;
                }
                while (anInt5497 < 0) {
                    int n445 = (n439 >> 12) * super.anInt5433 + (n438 >> 12);
                    final int n446 = anInt5496++;
                    final int[] array8 = anIntArray4504;
                    if (n2 == 0) {
                        if (n == 1) {
                            array8[n446] = this.anIntArray6212[n445];
                        }
                        else if (n == 0) {
                            final int n447 = this.anIntArray6212[n445++];
                            array8[n446] = (((n447 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n447 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n447 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                        }
                        else if (n == 3) {
                            final int n448 = this.anIntArray6212[n445++];
                            final int anInt5498 = Class332_Sub3.anInt5432;
                            final int n449 = n448 + anInt5498;
                            final int n450 = (n448 & 0xFF00FF) + (anInt5498 & 0xFF00FF);
                            final int n451 = (n450 & 0x1000100) + (n449 - n450 & 0x10000);
                            array8[n446] = (n449 - n451 | n451 - (n451 >>> 8));
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n452 = this.anIntArray6212[n445];
                            array8[n446] = ((((n452 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n452 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                        }
                    }
                    else if (n2 == 1) {
                        if (n == 1) {
                            final int n453 = this.anIntArray6212[n445];
                            final int n454 = n453 >>> 24;
                            final int n455 = 256 - n454;
                            final int n456 = array8[n446];
                            array8[n446] = ((n453 & 0xFF00FF) * n454 + (n456 & 0xFF00FF) * n455 & 0xFF00FF00) + ((n453 & 0xFF00) * n454 + (n456 & 0xFF00) * n455 & 0xFF0000) >> 8;
                        }
                        else if (n == 0) {
                            final int n457 = this.anIntArray6212[n445];
                            final int n458 = (n457 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                            final int n459 = 256 - n458;
                            if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                final int n460 = array8[n446];
                                array8[n446] = ((n457 & 0xFF00FF) * n458 + (n460 & 0xFF00FF) * n459 & 0xFF00FF00) + ((n457 & 0xFF00) * n458 + (n460 & 0xFF00) * n459 & 0xFF0000) >> 8;
                            }
                            else if (n458 != 255) {
                                final int n461 = (((n457 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n457 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n457 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n462 = array8[n446];
                                array8[n446] = ((n461 & 0xFF00FF) * n458 + (n462 & 0xFF00FF) * n459 & 0xFF00FF00) + ((n461 & 0xFF00) * n458 + (n462 & 0xFF00) * n459 & 0xFF0000) >> 8;
                            }
                            else {
                                array8[n446] = (((n457 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n457 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n457 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                        }
                        else if (n == 3) {
                            final int n463 = this.anIntArray6212[n445];
                            final int anInt5499 = Class332_Sub3.anInt5432;
                            final int n464 = n463 + anInt5499;
                            final int n465 = (n463 & 0xFF00FF) + (anInt5499 & 0xFF00FF);
                            final int n466 = (n465 & 0x1000100) + (n464 - n465 & 0x10000);
                            int n467 = n464 - n466 | n466 - (n466 >>> 8);
                            final int n468 = (n463 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                            final int n469 = 256 - n468;
                            if (n468 != 255) {
                                final int n470 = n467;
                                final int n471 = array8[n446];
                                n467 = ((n470 & 0xFF00FF) * n468 + (n471 & 0xFF00FF) * n469 & 0xFF00FF00) + ((n470 & 0xFF00) * n468 + (n471 & 0xFF00) * n469 & 0xFF0000) >> 8;
                            }
                            array8[n446] = n467;
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n472 = this.anIntArray6212[n445];
                            final int n473 = n472 >>> 24;
                            final int n474 = 256 - n473;
                            final int n475 = ((((n472 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n472 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            final int n476 = array8[n446];
                            array8[n446] = ((n475 & 0xFF00FF) * n473 + (n476 & 0xFF00FF) * n474 & 0xFF00FF00) + ((n475 & 0xFF00) * n473 + (n476 & 0xFF00) * n474 & 0xFF0000) >> 8;
                        }
                    }
                    else {
                        if (n2 != 2) {
                            throw new IllegalArgumentException();
                        }
                        if (n == 1) {
                            final int n477 = this.anIntArray6212[n445];
                            final int n478 = array8[n446];
                            final int n479 = n477 + n478;
                            final int n480 = (n477 & 0xFF00FF) + (n478 & 0xFF00FF);
                            final int n481 = (n480 & 0x1000100) + (n479 - n480 & 0x10000);
                            array8[n446] = (n479 - n481 | n481 - (n481 >>> 8));
                        }
                        else if (n == 0) {
                            final int n482 = this.anIntArray6212[n445];
                            final int n483 = (((n482 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n482 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n482 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            final int n484 = array8[n446];
                            final int n485 = n483 + n484;
                            final int n486 = (n483 & 0xFF00FF) + (n484 & 0xFF00FF);
                            final int n487 = (n486 & 0x1000100) + (n485 - n486 & 0x10000);
                            array8[n446] = (n485 - n487 | n487 - (n487 >>> 8));
                        }
                        else if (n == 3) {
                            final int n488 = this.anIntArray6212[n445];
                            final int anInt5500 = Class332_Sub3.anInt5432;
                            final int n489 = n488 + anInt5500;
                            final int n490 = (n488 & 0xFF00FF) + (anInt5500 & 0xFF00FF);
                            final int n491 = (n490 & 0x1000100) + (n489 - n490 & 0x10000);
                            final int n492 = n489 - n491 | n491 - (n491 >>> 8);
                            final int n493 = array8[n446];
                            final int n494 = n492 + n493;
                            final int n495 = (n492 & 0xFF00FF) + (n493 & 0xFF00FF);
                            final int n496 = (n495 & 0x1000100) + (n494 - n495 & 0x10000);
                            array8[n446] = (n494 - n496 | n496 - (n496 >>> 8));
                        }
                        else if (n == 2) {
                            final int n497 = this.anIntArray6212[n445];
                            final int n498 = ((((n497 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n497 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            final int n499 = array8[n446];
                            final int n500 = n498 + n499;
                            final int n501 = (n498 & 0xFF00FF) + (n499 & 0xFF00FF);
                            final int n502 = (n501 & 0x1000100) + (n500 - n501 & 0x10000);
                            array8[n446] = (n500 - n502 | n502 - (n502 >>> 8));
                        }
                    }
                    n438 += Class332_Sub3.anInt5444;
                    n439 += Class332_Sub3.anInt5436;
                    ++anInt5497;
                }
                Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441;
                Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428;
                Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438;
            }
        }
        else {
            for (int anInt5501 = Class332_Sub3.anInt5431; anInt5501 < 0; ++anInt5501) {
                int anInt5502 = Class332_Sub3.anInt5451;
                int n503 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                int n504 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                int anInt5503 = Class332_Sub3.anInt5448;
                if (n503 < 0) {
                    final int n505 = (Class332_Sub3.anInt5444 - 1 - n503) / Class332_Sub3.anInt5444;
                    anInt5503 += n505;
                    n503 += Class332_Sub3.anInt5444 * n505;
                    n504 += Class332_Sub3.anInt5436 * n505;
                    anInt5502 += n505;
                }
                final int n506;
                if ((n506 = (1 + n503 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5503) {
                    anInt5503 = n506;
                }
                if (n504 < 0) {
                    final int n507 = (Class332_Sub3.anInt5436 - 1 - n504) / Class332_Sub3.anInt5436;
                    anInt5503 += n507;
                    n503 += Class332_Sub3.anInt5444 * n507;
                    n504 += Class332_Sub3.anInt5436 * n507;
                    anInt5502 += n507;
                }
                final int n508;
                if ((n508 = (1 + n504 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5503) {
                    anInt5503 = n508;
                }
                while (anInt5503 < 0) {
                    int n509 = (n504 >> 12) * super.anInt5433 + (n503 >> 12);
                    final int n510 = anInt5502++;
                    final int[] array9 = anIntArray4504;
                    if (n2 == 0) {
                        if (n == 1) {
                            array9[n510] = this.anIntArray6212[n509];
                        }
                        else if (n == 0) {
                            final int n511 = this.anIntArray6212[n509++];
                            array9[n510] = (((n511 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n511 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n511 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                        }
                        else if (n == 3) {
                            final int n512 = this.anIntArray6212[n509++];
                            final int anInt5504 = Class332_Sub3.anInt5432;
                            final int n513 = n512 + anInt5504;
                            final int n514 = (n512 & 0xFF00FF) + (anInt5504 & 0xFF00FF);
                            final int n515 = (n514 & 0x1000100) + (n513 - n514 & 0x10000);
                            array9[n510] = (n513 - n515 | n515 - (n515 >>> 8));
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n516 = this.anIntArray6212[n509];
                            array9[n510] = ((((n516 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n516 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                        }
                    }
                    else if (n2 == 1) {
                        if (n == 1) {
                            final int n517 = this.anIntArray6212[n509];
                            final int n518 = n517 >>> 24;
                            final int n519 = 256 - n518;
                            final int n520 = array9[n510];
                            array9[n510] = ((n517 & 0xFF00FF) * n518 + (n520 & 0xFF00FF) * n519 & 0xFF00FF00) + ((n517 & 0xFF00) * n518 + (n520 & 0xFF00) * n519 & 0xFF0000) >> 8;
                        }
                        else if (n == 0) {
                            final int n521 = this.anIntArray6212[n509];
                            final int n522 = (n521 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                            final int n523 = 256 - n522;
                            if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                final int n524 = array9[n510];
                                array9[n510] = ((n521 & 0xFF00FF) * n522 + (n524 & 0xFF00FF) * n523 & 0xFF00FF00) + ((n521 & 0xFF00) * n522 + (n524 & 0xFF00) * n523 & 0xFF0000) >> 8;
                            }
                            else if (n522 != 255) {
                                final int n525 = (((n521 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n521 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n521 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n526 = array9[n510];
                                array9[n510] = ((n525 & 0xFF00FF) * n522 + (n526 & 0xFF00FF) * n523 & 0xFF00FF00) + ((n525 & 0xFF00) * n522 + (n526 & 0xFF00) * n523 & 0xFF0000) >> 8;
                            }
                            else {
                                array9[n510] = (((n521 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n521 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n521 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                        }
                        else if (n == 3) {
                            final int n527 = this.anIntArray6212[n509];
                            final int anInt5505 = Class332_Sub3.anInt5432;
                            final int n528 = n527 + anInt5505;
                            final int n529 = (n527 & 0xFF00FF) + (anInt5505 & 0xFF00FF);
                            final int n530 = (n529 & 0x1000100) + (n528 - n529 & 0x10000);
                            int n531 = n528 - n530 | n530 - (n530 >>> 8);
                            final int n532 = (n527 >>> 24) * Class332_Sub3.anInt5449 >> 8;
                            final int n533 = 256 - n532;
                            if (n532 != 255) {
                                final int n534 = n531;
                                final int n535 = array9[n510];
                                n531 = ((n534 & 0xFF00FF) * n532 + (n535 & 0xFF00FF) * n533 & 0xFF00FF00) + ((n534 & 0xFF00) * n532 + (n535 & 0xFF00) * n533 & 0xFF0000) >> 8;
                            }
                            array9[n510] = n531;
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n536 = this.anIntArray6212[n509];
                            final int n537 = n536 >>> 24;
                            final int n538 = 256 - n537;
                            final int n539 = ((((n536 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n536 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            final int n540 = array9[n510];
                            array9[n510] = ((n539 & 0xFF00FF) * n537 + (n540 & 0xFF00FF) * n538 & 0xFF00FF00) + ((n539 & 0xFF00) * n537 + (n540 & 0xFF00) * n538 & 0xFF0000) >> 8;
                        }
                    }
                    else {
                        if (n2 != 2) {
                            throw new IllegalArgumentException();
                        }
                        if (n == 1) {
                            final int n541 = this.anIntArray6212[n509];
                            final int n542 = array9[n510];
                            final int n543 = n541 + n542;
                            final int n544 = (n541 & 0xFF00FF) + (n542 & 0xFF00FF);
                            final int n545 = (n544 & 0x1000100) + (n543 - n544 & 0x10000);
                            array9[n510] = (n543 - n545 | n545 - (n545 >>> 8));
                        }
                        else if (n == 0) {
                            final int n546 = this.anIntArray6212[n509];
                            final int n547 = (((n546 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n546 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n546 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            final int n548 = array9[n510];
                            final int n549 = n547 + n548;
                            final int n550 = (n547 & 0xFF00FF) + (n548 & 0xFF00FF);
                            final int n551 = (n550 & 0x1000100) + (n549 - n550 & 0x10000);
                            array9[n510] = (n549 - n551 | n551 - (n551 >>> 8));
                        }
                        else if (n == 3) {
                            final int n552 = this.anIntArray6212[n509];
                            final int anInt5506 = Class332_Sub3.anInt5432;
                            final int n553 = n552 + anInt5506;
                            final int n554 = (n552 & 0xFF00FF) + (anInt5506 & 0xFF00FF);
                            final int n555 = (n554 & 0x1000100) + (n553 - n554 & 0x10000);
                            final int n556 = n553 - n555 | n555 - (n555 >>> 8);
                            final int n557 = array9[n510];
                            final int n558 = n556 + n557;
                            final int n559 = (n556 & 0xFF00FF) + (n557 & 0xFF00FF);
                            final int n560 = (n559 & 0x1000100) + (n558 - n559 & 0x10000);
                            array9[n510] = (n558 - n560 | n560 - (n560 >>> 8));
                        }
                        else if (n == 2) {
                            final int n561 = this.anIntArray6212[n509];
                            final int n562 = ((((n561 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n561 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            final int n563 = array9[n510];
                            final int n564 = n562 + n563;
                            final int n565 = (n562 & 0xFF00FF) + (n563 & 0xFF00FF);
                            final int n566 = (n565 & 0x1000100) + (n564 - n565 & 0x10000);
                            array9[n510] = (n564 - n566 | n566 - (n566 >>> 8));
                        }
                    }
                    n503 += Class332_Sub3.anInt5444;
                    n504 += Class332_Sub3.anInt5436;
                    ++anInt5503;
                }
                Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441;
                Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428;
                Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438;
            }
        }
    }
    
    @Override
    final void method3745(int n, int n2, int n3, int n4, final int n5, int n6, final int n7, final int n8) {
        if (super.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        if (n3 <= 0 || n4 <= 0) {
            return;
        }
        int n9 = 0;
        int n10 = 0;
        final int anInt4505 = super.aHa_Sub2_5434.anInt4505;
        final int n11 = super.anInt5446 + super.anInt5433 + super.anInt5455;
        final int n12 = super.anInt5439 + super.anInt5454 + super.anInt5447;
        final int n13 = (n11 << 16) / n3;
        final int n14 = (n12 << 16) / n4;
        if (super.anInt5446 > 0) {
            final int n15 = ((super.anInt5446 << 16) + n13 - 1) / n13;
            n += n15;
            n9 += n15 * n13 - (super.anInt5446 << 16);
        }
        if (super.anInt5439 > 0) {
            final int n16 = ((super.anInt5439 << 16) + n14 - 1) / n14;
            n2 += n16;
            n10 += n16 * n14 - (super.anInt5439 << 16);
        }
        if (super.anInt5433 < n11) {
            n3 = ((super.anInt5433 << 16) - n9 + n13 - 1) / n13;
        }
        if (super.anInt5454 < n12) {
            n4 = ((super.anInt5454 << 16) - n10 + n14 - 1) / n14;
        }
        int n17 = n + n2 * anInt4505;
        int n18 = anInt4505 - n3;
        if (n2 + n4 > super.aHa_Sub2_5434.anInt4492) {
            n4 -= n2 + n4 - super.aHa_Sub2_5434.anInt4492;
        }
        if (n2 < super.aHa_Sub2_5434.anInt4495) {
            final int n19 = super.aHa_Sub2_5434.anInt4495 - n2;
            n4 -= n19;
            n17 += n19 * anInt4505;
            n10 += n14 * n19;
        }
        if (n + n3 > super.aHa_Sub2_5434.anInt4507) {
            final int n20 = n + n3 - super.aHa_Sub2_5434.anInt4507;
            n3 -= n20;
            n18 += n20;
        }
        if (n < super.aHa_Sub2_5434.anInt4509) {
            final int n21 = super.aHa_Sub2_5434.anInt4509 - n;
            n3 -= n21;
            n17 += n21;
            n9 += n13 * n21;
            n18 += n21;
        }
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (n7 == 0) {
            if (n5 == 1) {
                final int n22 = n9;
                for (int i = -n4; i < 0; ++i) {
                    final int n23 = (n10 >> 16) * super.anInt5433;
                    for (int j = -n3; j < 0; ++j) {
                        anIntArray4504[n17++] = this.anIntArray6212[(n9 >> 16) + n23];
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n22;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n24 = (n6 & 0xFF0000) >> 16;
                final int n25 = (n6 & 0xFF00) >> 8;
                final int n26 = n6 & 0xFF;
                final int n27 = n9;
                for (int k = -n4; k < 0; ++k) {
                    final int n28 = (n10 >> 16) * super.anInt5433;
                    for (int l = -n3; l < 0; ++l) {
                        final int n29 = this.anIntArray6212[(n9 >> 16) + n28];
                        anIntArray4504[n17++] = (((n29 & 0xFF0000) * n24 & 0xFF000000) | ((n29 & 0xFF00) * n25 & 0xFF0000) | ((n29 & 0xFF) * n26 & 0xFF00)) >>> 8;
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n27;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 3) {
                final int n30 = n9;
                for (int n31 = -n4; n31 < 0; ++n31) {
                    final int n32 = (n10 >> 16) * super.anInt5433;
                    for (int n33 = -n3; n33 < 0; ++n33) {
                        final int n34 = this.anIntArray6212[(n9 >> 16) + n32];
                        final int n35 = n34 + n6;
                        final int n36 = (n34 & 0xFF00FF) + (n6 & 0xFF00FF);
                        final int n37 = (n36 & 0x1000100) + (n35 - n36 & 0x10000);
                        anIntArray4504[n17++] = (n35 - n37 | n37 - (n37 >>> 8));
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n30;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 2) {
                final int n38 = n6 >>> 24;
                final int n39 = 256 - n38;
                n6 = (((n6 & 0xFF00FF) * n39 & 0xFF00FF00) | ((n6 & 0xFF00) * n39 & 0xFF0000)) >>> 8;
                final int n40 = n9;
                for (int n41 = -n4; n41 < 0; ++n41) {
                    final int n42 = (n10 >> 16) * super.anInt5433;
                    for (int n43 = -n3; n43 < 0; ++n43) {
                        final int n44 = this.anIntArray6212[(n9 >> 16) + n42];
                        anIntArray4504[n17++] = ((((n44 & 0xFF00FF) * n38 & 0xFF00FF00) | ((n44 & 0xFF00) * n38 & 0xFF0000)) >>> 8) + n6;
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n40;
                    n17 += n18;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
        else if (n7 == 1) {
            if (n5 == 1) {
                final int n45 = n9;
                for (int n46 = -n4; n46 < 0; ++n46) {
                    final int n47 = (n10 >> 16) * super.anInt5433;
                    for (int n48 = -n3; n48 < 0; ++n48) {
                        final int n49 = this.anIntArray6212[(n9 >> 16) + n47];
                        final int n50 = n49 >>> 24;
                        final int n51 = 256 - n50;
                        final int n52 = anIntArray4504[n17];
                        anIntArray4504[n17++] = (((n49 & 0xFF00FF) * n50 + (n52 & 0xFF00FF) * n51 & 0xFF00FF00) >> 8) + (((n49 & 0xFF00FF00) >>> 8) * n50 + ((n52 & 0xFF00FF00) >>> 8) * n51 & 0xFF00FF00);
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n45;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n53 = n9;
                if ((n6 & 0xFFFFFF) == 0xFFFFFF) {
                    for (int n54 = -n4; n54 < 0; ++n54) {
                        final int n55 = (n10 >> 16) * super.anInt5433;
                        for (int n56 = -n3; n56 < 0; ++n56) {
                            final int n57 = this.anIntArray6212[(n9 >> 16) + n55];
                            final int n58 = (n57 >>> 24) * (n6 >>> 24) >> 8;
                            final int n59 = 256 - n58;
                            final int n60 = anIntArray4504[n17];
                            anIntArray4504[n17++] = ((n57 & 0xFF00FF) * n58 + (n60 & 0xFF00FF) * n59 & 0xFF00FF00) + ((n57 & 0xFF00) * n58 + (n60 & 0xFF00) * n59 & 0xFF0000) >> 8;
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n53;
                        n17 += n18;
                    }
                    return;
                }
                final int n61 = (n6 & 0xFF0000) >> 16;
                final int n62 = (n6 & 0xFF00) >> 8;
                final int n63 = n6 & 0xFF;
                for (int n64 = -n4; n64 < 0; ++n64) {
                    final int n65 = (n10 >> 16) * super.anInt5433;
                    for (int n66 = -n3; n66 < 0; ++n66) {
                        final int n67 = this.anIntArray6212[(n9 >> 16) + n65];
                        final int n68 = (n67 >>> 24) * (n6 >>> 24) >> 8;
                        final int n69 = 256 - n68;
                        if (n68 != 255) {
                            final int n70 = (((n67 & 0xFF0000) * n61 & 0xFF000000) | ((n67 & 0xFF00) * n62 & 0xFF0000) | ((n67 & 0xFF) * n63 & 0xFF00)) >>> 8;
                            final int n71 = anIntArray4504[n17];
                            anIntArray4504[n17++] = ((n70 & 0xFF00FF) * n68 + (n71 & 0xFF00FF) * n69 & 0xFF00FF00) + ((n70 & 0xFF00) * n68 + (n71 & 0xFF00) * n69 & 0xFF0000) >> 8;
                        }
                        else {
                            anIntArray4504[n17++] = (((n67 & 0xFF0000) * n61 & 0xFF000000) | ((n67 & 0xFF00) * n62 & 0xFF0000) | ((n67 & 0xFF) * n63 & 0xFF00)) >>> 8;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n53;
                    n17 += n18;
                }
            }
            else {
                if (n5 == 3) {
                    final int n72 = n9;
                    for (int n73 = -n4; n73 < 0; ++n73) {
                        final int n74 = (n10 >> 16) * super.anInt5433;
                        for (int n75 = -n3; n75 < 0; ++n75) {
                            final int n76 = this.anIntArray6212[(n9 >> 16) + n74];
                            final int n77 = n76 + n6;
                            final int n78 = (n76 & 0xFF00FF) + (n6 & 0xFF00FF);
                            final int n79 = (n78 & 0x1000100) + (n77 - n78 & 0x10000);
                            int n80 = n77 - n79 | n79 - (n79 >>> 8);
                            final int n81 = (n80 >>> 24) * (n6 >>> 24) >> 8;
                            final int n82 = 256 - n81;
                            if (n81 != 255) {
                                final int n83 = n80;
                                final int n84 = anIntArray4504[n17];
                                n80 = ((n83 & 0xFF00FF) * n81 + (n84 & 0xFF00FF) * n82 & 0xFF00FF00) + ((n83 & 0xFF00) * n81 + (n84 & 0xFF00) * n82 & 0xFF0000) >> 8;
                            }
                            anIntArray4504[n17++] = n80;
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n72;
                        n17 += n18;
                    }
                    return;
                }
                if (n5 == 2) {
                    final int n85 = n6 >>> 24;
                    final int n86 = 256 - n85;
                    n6 = (((n6 & 0xFF00FF) * n86 & 0xFF00FF00) | ((n6 & 0xFF00) * n86 & 0xFF0000)) >>> 8;
                    final int n87 = n9;
                    for (int n88 = -n4; n88 < 0; ++n88) {
                        final int n89 = (n10 >> 16) * super.anInt5433;
                        for (int n90 = -n3; n90 < 0; ++n90) {
                            final int n91 = this.anIntArray6212[(n9 >> 16) + n89];
                            final int n92 = n91 >>> 24;
                            final int n93 = 256 - n92;
                            final int n94 = ((((n91 & 0xFF00FF) * n85 & 0xFF00FF00) | ((n91 & 0xFF00) * n85 & 0xFF0000)) >>> 8) + n6;
                            final int n95 = anIntArray4504[n17];
                            anIntArray4504[n17++] = ((n94 & 0xFF00FF) * n92 + (n95 & 0xFF00FF) * n93 & 0xFF00FF00) + ((n94 & 0xFF00) * n92 + (n95 & 0xFF00) * n93 & 0xFF0000) >> 8;
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n87;
                        n17 += n18;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
        }
        else {
            if (n7 != 2) {
                throw new IllegalArgumentException();
            }
            if (n5 == 1) {
                final int n96 = n9;
                for (int n97 = -n4; n97 < 0; ++n97) {
                    final int n98 = (n10 >> 16) * super.anInt5433;
                    for (int n99 = -n3; n99 < 0; ++n99) {
                        final int n100 = this.anIntArray6212[(n9 >> 16) + n98];
                        if (n100 != 0) {
                            final int n101 = anIntArray4504[n17];
                            final int n102 = n100 + n101;
                            final int n103 = (n100 & 0xFF00FF) + (n101 & 0xFF00FF);
                            final int n104 = (n103 & 0x1000100) + (n102 - n103 & 0x10000);
                            anIntArray4504[n17++] = (n102 - n104 | n104 - (n104 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n96;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n105 = n9;
                final int n106 = (n6 & 0xFF0000) >> 16;
                final int n107 = (n6 & 0xFF00) >> 8;
                final int n108 = n6 & 0xFF;
                for (int n109 = -n4; n109 < 0; ++n109) {
                    final int n110 = (n10 >> 16) * super.anInt5433;
                    for (int n111 = -n3; n111 < 0; ++n111) {
                        final int n112 = this.anIntArray6212[(n9 >> 16) + n110];
                        if (n112 != 0) {
                            final int n113 = (((n112 & 0xFF0000) * n106 & 0xFF000000) | ((n112 & 0xFF00) * n107 & 0xFF0000) | ((n112 & 0xFF) * n108 & 0xFF00)) >>> 8;
                            final int n114 = anIntArray4504[n17];
                            final int n115 = n113 + n114;
                            final int n116 = (n113 & 0xFF00FF) + (n114 & 0xFF00FF);
                            final int n117 = (n116 & 0x1000100) + (n115 - n116 & 0x10000);
                            anIntArray4504[n17++] = (n115 - n117 | n117 - (n117 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n105;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 3) {
                final int n118 = n9;
                for (int n119 = -n4; n119 < 0; ++n119) {
                    final int n120 = (n10 >> 16) * super.anInt5433;
                    for (int n121 = -n3; n121 < 0; ++n121) {
                        final int n122 = this.anIntArray6212[(n9 >> 16) + n120];
                        final int n123 = n122 + n6;
                        final int n124 = (n122 & 0xFF00FF) + (n6 & 0xFF00FF);
                        final int n125 = (n124 & 0x1000100) + (n123 - n124 & 0x10000);
                        final int n126 = n123 - n125 | n125 - (n125 >>> 8);
                        final int n127 = anIntArray4504[n17];
                        final int n128 = n126 + n127;
                        final int n129 = (n126 & 0xFF00FF) + (n127 & 0xFF00FF);
                        final int n130 = (n129 & 0x1000100) + (n128 - n129 & 0x10000);
                        anIntArray4504[n17++] = (n128 - n130 | n130 - (n130 >>> 8));
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n118;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 2) {
                final int n131 = n6 >>> 24;
                final int n132 = 256 - n131;
                n6 = (((n6 & 0xFF00FF) * n132 & 0xFF00FF00) | ((n6 & 0xFF00) * n132 & 0xFF0000)) >>> 8;
                final int n133 = n9;
                for (int n134 = -n4; n134 < 0; ++n134) {
                    final int n135 = (n10 >> 16) * super.anInt5433;
                    for (int n136 = -n3; n136 < 0; ++n136) {
                        final int n137 = this.anIntArray6212[(n9 >> 16) + n135];
                        if (n137 != 0) {
                            final int n138 = ((((n137 & 0xFF00FF) * n131 & 0xFF00FF00) | ((n137 & 0xFF00) * n131 & 0xFF0000)) >>> 8) + n6;
                            final int n139 = anIntArray4504[n17];
                            final int n140 = n138 + n139;
                            final int n141 = (n138 & 0xFF00FF) + (n139 & 0xFF00FF);
                            final int n142 = (n141 & 0x1000100) + (n140 - n141 & 0x10000);
                            anIntArray4504[n17++] = (n140 - n142 | n142 - (n142 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n133;
                    n17 += n18;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }
    
    Class332_Sub3_Sub1(final ha_Sub2 ha_Sub2, final int[] anIntArray6212, int n, int n2, final int n3, final int n4, final boolean b) {
        super(ha_Sub2, n3, n4);
        if (b) {
            this.anIntArray6212 = new int[n3 * n4];
        }
        else {
            this.anIntArray6212 = anIntArray6212;
        }
        n2 -= super.anInt5433;
        int n5 = 0;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                this.anIntArray6212[n5++] = anIntArray6212[n++];
            }
            n += n2;
        }
    }
    
    @Override
    final void method3757(int n, int n2, final int n3, int n4, int n5, final int n6, int n7, final int n8, final int n9) {
        if (n4 <= 0 || n5 <= 0) {
            return;
        }
        int n10 = 0;
        int n11 = 0;
        final int n12 = super.anInt5446 + super.anInt5433 + super.anInt5455;
        final int n13 = super.anInt5439 + super.anInt5454 + super.anInt5447;
        final int n14 = (n12 << 16) / n4;
        final int n15 = (n13 << 16) / n5;
        if (super.anInt5446 > 0) {
            final int n16 = ((super.anInt5446 << 16) + n14 - 1) / n14;
            n += n16;
            n10 += n16 * n14 - (super.anInt5446 << 16);
        }
        if (super.anInt5439 > 0) {
            final int n17 = ((super.anInt5439 << 16) + n15 - 1) / n15;
            n2 += n17;
            n11 += n17 * n15 - (super.anInt5439 << 16);
        }
        if (super.anInt5433 < n12) {
            n4 = ((super.anInt5433 << 16) - n10 + n14 - 1) / n14;
        }
        if (super.anInt5454 < n13) {
            n5 = ((super.anInt5454 << 16) - n11 + n15 - 1) / n15;
        }
        int n18 = n + n2 * super.aHa_Sub2_5434.anInt4505;
        int n19 = super.aHa_Sub2_5434.anInt4505 - n4;
        if (n2 + n5 > super.aHa_Sub2_5434.anInt4492) {
            n5 -= n2 + n5 - super.aHa_Sub2_5434.anInt4492;
        }
        if (n2 < super.aHa_Sub2_5434.anInt4495) {
            final int n20 = super.aHa_Sub2_5434.anInt4495 - n2;
            n5 -= n20;
            n18 += n20 * super.aHa_Sub2_5434.anInt4505;
            n11 += n15 * n20;
        }
        if (n + n4 > super.aHa_Sub2_5434.anInt4507) {
            final int n21 = n + n4 - super.aHa_Sub2_5434.anInt4507;
            n4 -= n21;
            n19 += n21;
        }
        if (n < super.aHa_Sub2_5434.anInt4509) {
            final int n22 = super.aHa_Sub2_5434.anInt4509 - n;
            n4 -= n22;
            n18 += n22;
            n10 += n14 * n22;
            n19 += n22;
        }
        final float[] aFloatArray4487 = super.aHa_Sub2_5434.aFloatArray4487;
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (n8 == 0) {
            if (n6 == 1) {
                final int n23 = n10;
                for (int i = -n5; i < 0; ++i) {
                    final int n24 = (n11 >> 16) * super.anInt5433;
                    for (int j = -n4; j < 0; ++j) {
                        if (n3 < aFloatArray4487[n18]) {
                            anIntArray4504[n18] = this.anIntArray6212[(n10 >> 16) + n24];
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n23;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n25 = (n7 & 0xFF0000) >> 16;
                final int n26 = (n7 & 0xFF00) >> 8;
                final int n27 = n7 & 0xFF;
                final int n28 = n10;
                for (int k = -n5; k < 0; ++k) {
                    final int n29 = (n11 >> 16) * super.anInt5433;
                    for (int l = -n4; l < 0; ++l) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n30 = this.anIntArray6212[(n10 >> 16) + n29];
                            anIntArray4504[n18] = (((n30 & 0xFF0000) * n25 & 0xFF000000) | ((n30 & 0xFF00) * n26 & 0xFF0000) | ((n30 & 0xFF) * n27 & 0xFF00)) >>> 8;
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n28;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 3) {
                final int n31 = n10;
                for (int n32 = -n5; n32 < 0; ++n32) {
                    final int n33 = (n11 >> 16) * super.anInt5433;
                    for (int n34 = -n4; n34 < 0; ++n34) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n35 = this.anIntArray6212[(n10 >> 16) + n33];
                            final int n36 = n35 + n7;
                            final int n37 = (n35 & 0xFF00FF) + (n7 & 0xFF00FF);
                            final int n38 = (n37 & 0x1000100) + (n36 - n37 & 0x10000);
                            anIntArray4504[n18] = (n36 - n38 | n38 - (n38 >>> 8));
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n31;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 2) {
                final int n39 = n7 >>> 24;
                final int n40 = 256 - n39;
                n7 = (((n7 & 0xFF00FF) * n40 & 0xFF00FF00) | ((n7 & 0xFF00) * n40 & 0xFF0000)) >>> 8;
                final int n41 = n10;
                for (int n42 = -n5; n42 < 0; ++n42) {
                    final int n43 = (n11 >> 16) * super.anInt5433;
                    for (int n44 = -n4; n44 < 0; ++n44) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n45 = this.anIntArray6212[(n10 >> 16) + n43];
                            anIntArray4504[n18] = ((((n45 & 0xFF00FF) * n39 & 0xFF00FF00) | ((n45 & 0xFF00) * n39 & 0xFF0000)) >>> 8) + n7;
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n41;
                    n18 += n19;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
        else if (n8 == 1) {
            if (n6 == 1) {
                final int n46 = n10;
                for (int n47 = -n5; n47 < 0; ++n47) {
                    final int n48 = (n11 >> 16) * super.anInt5433;
                    for (int n49 = -n4; n49 < 0; ++n49) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n50 = this.anIntArray6212[(n10 >> 16) + n48];
                            final int n51 = n50 >>> 24;
                            final int n52 = 256 - n51;
                            final int n53 = anIntArray4504[n18];
                            anIntArray4504[n18] = (((n50 & 0xFF00FF) * n51 + (n53 & 0xFF00FF) * n52 & 0xFF00FF00) >> 8) + (((n50 & 0xFF00FF00) >>> 8) * n51 + ((n53 & 0xFF00FF00) >>> 8) * n52 & 0xFF00FF00);
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n46;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n54 = n10;
                if ((n7 & 0xFFFFFF) == 0xFFFFFF) {
                    for (int n55 = -n5; n55 < 0; ++n55) {
                        final int n56 = (n11 >> 16) * super.anInt5433;
                        for (int n57 = -n4; n57 < 0; ++n57) {
                            if (n3 < aFloatArray4487[n18]) {
                                final int n58 = this.anIntArray6212[(n10 >> 16) + n56];
                                final int n59 = (n58 >>> 24) * (n7 >>> 24) >> 8;
                                final int n60 = 256 - n59;
                                final int n61 = anIntArray4504[n18];
                                anIntArray4504[n18] = ((n58 & 0xFF00FF) * n59 + (n61 & 0xFF00FF) * n60 & 0xFF00FF00) + ((n58 & 0xFF00) * n59 + (n61 & 0xFF00) * n60 & 0xFF0000) >> 8;
                                aFloatArray4487[n18] = n3;
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n54;
                        n18 += n19;
                    }
                    return;
                }
                final int n62 = (n7 & 0xFF0000) >> 16;
                final int n63 = (n7 & 0xFF00) >> 8;
                final int n64 = n7 & 0xFF;
                for (int n65 = -n5; n65 < 0; ++n65) {
                    final int n66 = (n11 >> 16) * super.anInt5433;
                    for (int n67 = -n4; n67 < 0; ++n67) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n68 = this.anIntArray6212[(n10 >> 16) + n66];
                            final int n69 = (n68 >>> 24) * (n7 >>> 24) >> 8;
                            final int n70 = 256 - n69;
                            if (n69 != 255) {
                                final int n71 = (((n68 & 0xFF0000) * n62 & 0xFF000000) | ((n68 & 0xFF00) * n63 & 0xFF0000) | ((n68 & 0xFF) * n64 & 0xFF00)) >>> 8;
                                final int n72 = anIntArray4504[n18];
                                anIntArray4504[n18] = ((n71 & 0xFF00FF) * n69 + (n72 & 0xFF00FF) * n70 & 0xFF00FF00) + ((n71 & 0xFF00) * n69 + (n72 & 0xFF00) * n70 & 0xFF0000) >> 8;
                                aFloatArray4487[n18] = n3;
                            }
                            else {
                                anIntArray4504[n18] = (((n68 & 0xFF0000) * n62 & 0xFF000000) | ((n68 & 0xFF00) * n63 & 0xFF0000) | ((n68 & 0xFF) * n64 & 0xFF00)) >>> 8;
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n54;
                    n18 += n19;
                }
            }
            else {
                if (n6 == 3) {
                    final int n73 = n10;
                    for (int n74 = -n5; n74 < 0; ++n74) {
                        final int n75 = (n11 >> 16) * super.anInt5433;
                        for (int n76 = -n4; n76 < 0; ++n76) {
                            if (n3 < aFloatArray4487[n18]) {
                                final int n77 = this.anIntArray6212[(n10 >> 16) + n75];
                                final int n78 = n77 + n7;
                                final int n79 = (n77 & 0xFF00FF) + (n7 & 0xFF00FF);
                                final int n80 = (n79 & 0x1000100) + (n78 - n79 & 0x10000);
                                int n81 = n78 - n80 | n80 - (n80 >>> 8);
                                final int n82 = (n81 >>> 24) * (n7 >>> 24) >> 8;
                                final int n83 = 256 - n82;
                                if (n82 != 255) {
                                    final int n84 = n81;
                                    final int n85 = anIntArray4504[n18];
                                    n81 = ((n84 & 0xFF00FF) * n82 + (n85 & 0xFF00FF) * n83 & 0xFF00FF00) + ((n84 & 0xFF00) * n82 + (n85 & 0xFF00) * n83 & 0xFF0000) >> 8;
                                }
                                anIntArray4504[n18] = n81;
                                aFloatArray4487[n18] = n3;
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n73;
                        n18 += n19;
                    }
                    return;
                }
                if (n6 == 2) {
                    final int n86 = n7 >>> 24;
                    final int n87 = 256 - n86;
                    n7 = (((n7 & 0xFF00FF) * n87 & 0xFF00FF00) | ((n7 & 0xFF00) * n87 & 0xFF0000)) >>> 8;
                    final int n88 = n10;
                    for (int n89 = -n5; n89 < 0; ++n89) {
                        final int n90 = (n11 >> 16) * super.anInt5433;
                        for (int n91 = -n4; n91 < 0; ++n91) {
                            if (n3 < aFloatArray4487[n18]) {
                                final int n92 = this.anIntArray6212[(n10 >> 16) + n90];
                                final int n93 = n92 >>> 24;
                                final int n94 = 256 - n93;
                                final int n95 = ((((n92 & 0xFF00FF) * n86 & 0xFF00FF00) | ((n92 & 0xFF00) * n86 & 0xFF0000)) >>> 8) + n7;
                                final int n96 = anIntArray4504[n18];
                                anIntArray4504[n18] = ((n95 & 0xFF00FF) * n93 + (n96 & 0xFF00FF) * n94 & 0xFF00FF00) + ((n95 & 0xFF00) * n93 + (n96 & 0xFF00) * n94 & 0xFF0000) >> 8;
                                aFloatArray4487[n18] = n3;
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n88;
                        n18 += n19;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
        }
        else {
            if (n8 != 2) {
                throw new IllegalArgumentException();
            }
            if (n6 == 1) {
                final int n97 = n10;
                for (int n98 = -n5; n98 < 0; ++n98) {
                    final int n99 = (n11 >> 16) * super.anInt5433;
                    for (int n100 = -n4; n100 < 0; ++n100) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n101 = this.anIntArray6212[(n10 >> 16) + n99];
                            if (n101 != 0) {
                                final int n102 = anIntArray4504[n18];
                                final int n103 = n101 + n102;
                                final int n104 = (n101 & 0xFF00FF) + (n102 & 0xFF00FF);
                                final int n105 = (n104 & 0x1000100) + (n103 - n104 & 0x10000);
                                anIntArray4504[n18] = (n103 - n105 | n105 - (n105 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n97;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n106 = n10;
                final int n107 = (n7 & 0xFF0000) >> 16;
                final int n108 = (n7 & 0xFF00) >> 8;
                final int n109 = n7 & 0xFF;
                for (int n110 = -n5; n110 < 0; ++n110) {
                    final int n111 = (n11 >> 16) * super.anInt5433;
                    for (int n112 = -n4; n112 < 0; ++n112) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n113 = this.anIntArray6212[(n10 >> 16) + n111];
                            if (n113 != 0) {
                                final int n114 = (((n113 & 0xFF0000) * n107 & 0xFF000000) | ((n113 & 0xFF00) * n108 & 0xFF0000) | ((n113 & 0xFF) * n109 & 0xFF00)) >>> 8;
                                final int n115 = anIntArray4504[n18];
                                final int n116 = n114 + n115;
                                final int n117 = (n114 & 0xFF00FF) + (n115 & 0xFF00FF);
                                final int n118 = (n117 & 0x1000100) + (n116 - n117 & 0x10000);
                                anIntArray4504[n18] = (n116 - n118 | n118 - (n118 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n106;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 3) {
                final int n119 = n10;
                for (int n120 = -n5; n120 < 0; ++n120) {
                    final int n121 = (n11 >> 16) * super.anInt5433;
                    for (int n122 = -n4; n122 < 0; ++n122) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n123 = this.anIntArray6212[(n10 >> 16) + n121];
                            final int n124 = n123 + n7;
                            final int n125 = (n123 & 0xFF00FF) + (n7 & 0xFF00FF);
                            final int n126 = (n125 & 0x1000100) + (n124 - n125 & 0x10000);
                            final int n127 = n124 - n126 | n126 - (n126 >>> 8);
                            final int n128 = anIntArray4504[n18];
                            final int n129 = n127 + n128;
                            final int n130 = (n127 & 0xFF00FF) + (n128 & 0xFF00FF);
                            final int n131 = (n130 & 0x1000100) + (n129 - n130 & 0x10000);
                            anIntArray4504[n18] = (n129 - n131 | n131 - (n131 >>> 8));
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n119;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 2) {
                final int n132 = n7 >>> 24;
                final int n133 = 256 - n132;
                n7 = (((n7 & 0xFF00FF) * n133 & 0xFF00FF00) | ((n7 & 0xFF00) * n133 & 0xFF0000)) >>> 8;
                final int n134 = n10;
                for (int n135 = -n5; n135 < 0; ++n135) {
                    final int n136 = (n11 >> 16) * super.anInt5433;
                    for (int n137 = -n4; n137 < 0; ++n137) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n138 = this.anIntArray6212[(n10 >> 16) + n136];
                            if (n138 != 0) {
                                final int n139 = ((((n138 & 0xFF00FF) * n132 & 0xFF00FF00) | ((n138 & 0xFF00) * n132 & 0xFF0000)) >>> 8) + n7;
                                final int n140 = anIntArray4504[n18];
                                final int n141 = n139 + n140;
                                final int n142 = (n139 & 0xFF00FF) + (n140 & 0xFF00FF);
                                final int n143 = (n142 & 0x1000100) + (n141 - n142 & 0x10000);
                                anIntArray4504[n18] = (n141 - n143 | n143 - (n143 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n134;
                    n18 += n19;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }
    
    Class332_Sub3_Sub1(final ha_Sub2 ha_Sub2, final int[] anIntArray6212, final int n, final int n2) {
        super(ha_Sub2, n, n2);
        this.anIntArray6212 = anIntArray6212;
    }
    
    @Override
    final void method3736(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        for (int i = 0; i < n4; ++i) {
            final int n7 = (n2 + i) * n3 + n;
            final int n8 = (n6 + i) * n3 + n5;
            for (int j = 0; j < n3; ++j) {
                this.anIntArray6212[n7 + j] = (anIntArray4504[n8 + j] & 0xFFFFFF);
            }
        }
    }
    
    @Override
    final void method3742(final int n, final int n2, final int n3) {
        if (n3 == 0) {
            final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
            for (int i = 0; i < super.anInt5454; ++i) {
                final int n4 = i * super.anInt5433;
                final int n5 = (n2 + i) * super.aHa_Sub2_5434.anInt4505 + n;
                for (int j = 0; j < super.anInt5433; ++j) {
                    this.anIntArray6212[n4 + j] = ((this.anIntArray6212[n4 + j] & 0xFFFFFF) | (anIntArray4504[n5 + j] << 8 & 0xFF000000));
                }
            }
        }
        else if (n3 == 1) {
            final int[] anIntArray4505 = super.aHa_Sub2_5434.anIntArray4504;
            for (int k = 0; k < super.anInt5454; ++k) {
                final int n6 = k * super.anInt5433;
                final int n7 = (n2 + k) * super.aHa_Sub2_5434.anInt4505 + n;
                for (int l = 0; l < super.anInt5433; ++l) {
                    this.anIntArray6212[n6 + l] = ((this.anIntArray6212[n6 + l] & 0xFFFFFF) | (anIntArray4505[n7 + l] << 16 & 0xFF000000));
                }
            }
        }
        else if (n3 == 2) {
            final int[] anIntArray4506 = super.aHa_Sub2_5434.anIntArray4504;
            for (int n8 = 0; n8 < super.anInt5454; ++n8) {
                final int n9 = n8 * super.anInt5433;
                final int n10 = (n2 + n8) * super.aHa_Sub2_5434.anInt4505 + n;
                for (int n11 = 0; n11 < super.anInt5433; ++n11) {
                    this.anIntArray6212[n9 + n11] = ((this.anIntArray6212[n9 + n11] & 0xFFFFFF) | (anIntArray4506[n10 + n11] << 24 & 0xFF000000));
                }
            }
        }
        else if (n3 == 3) {
            final int[] anIntArray4507 = super.aHa_Sub2_5434.anIntArray4504;
            for (int n12 = 0; n12 < super.anInt5454; ++n12) {
                final int n13 = n12 * super.anInt5433;
                final int n14 = (n2 + n12) * super.aHa_Sub2_5434.anInt4505 + n;
                for (int n15 = 0; n15 < super.anInt5433; ++n15) {
                    this.anIntArray6212[n13 + n15] = ((this.anIntArray6212[n13 + n15] & 0xFFFFFF) | ((anIntArray4507[n14 + n15] != 0) ? -16777216 : 0));
                }
            }
        }
    }
    
    @Override
    final void method3748(int anInt4509, int anInt4510, final int n, int n2, final int n3) {
        if (super.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        final int anInt4511 = super.aHa_Sub2_5434.anInt4505;
        anInt4509 += super.anInt5446;
        anInt4510 += super.anInt5439;
        int i = anInt4510 * anInt4511 + anInt4509;
        int n4 = 0;
        int anInt4512 = super.anInt5454;
        int anInt4513 = super.anInt5433;
        int n5 = anInt4511 - anInt4513;
        int n6 = 0;
        if (anInt4510 < super.aHa_Sub2_5434.anInt4495) {
            final int n7 = super.aHa_Sub2_5434.anInt4495 - anInt4510;
            anInt4512 -= n7;
            anInt4510 = super.aHa_Sub2_5434.anInt4495;
            n4 += n7 * anInt4513;
            i += n7 * anInt4511;
        }
        if (anInt4510 + anInt4512 > super.aHa_Sub2_5434.anInt4492) {
            anInt4512 -= anInt4510 + anInt4512 - super.aHa_Sub2_5434.anInt4492;
        }
        if (anInt4509 < super.aHa_Sub2_5434.anInt4509) {
            final int n8 = super.aHa_Sub2_5434.anInt4509 - anInt4509;
            anInt4513 -= n8;
            anInt4509 = super.aHa_Sub2_5434.anInt4509;
            n4 += n8;
            i += n8;
            n6 += n8;
            n5 += n8;
        }
        if (anInt4509 + anInt4513 > super.aHa_Sub2_5434.anInt4507) {
            final int n9 = anInt4509 + anInt4513 - super.aHa_Sub2_5434.anInt4507;
            anInt4513 -= n9;
            n6 += n9;
            n5 += n9;
        }
        if (anInt4513 <= 0 || anInt4512 <= 0) {
            return;
        }
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (n3 == 0) {
            if (n == 1) {
                for (int j = -anInt4512; j < 0; ++j) {
                    int n10;
                    for (n10 = i + anInt4513 - 3; i < n10; anIntArray4504[i++] = this.anIntArray6212[n4++], anIntArray4504[i++] = this.anIntArray6212[n4++], anIntArray4504[i++] = this.anIntArray6212[n4++], anIntArray4504[i++] = this.anIntArray6212[n4++]) {}
                    for (n10 += 3; i < n10; anIntArray4504[i++] = this.anIntArray6212[n4++]) {}
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 0) {
                final int n11 = (n2 & 0xFF0000) >> 16;
                final int n12 = (n2 & 0xFF00) >> 8;
                final int n13 = n2 & 0xFF;
                for (int k = -anInt4512; k < 0; ++k) {
                    for (int l = -anInt4513; l < 0; ++l) {
                        final int n14 = this.anIntArray6212[n4++];
                        anIntArray4504[i++] = (((n14 & 0xFF0000) * n11 & 0xFF000000) | ((n14 & 0xFF00) * n12 & 0xFF0000) | ((n14 & 0xFF) * n13 & 0xFF00)) >>> 8;
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 3) {
                for (int n15 = -anInt4512; n15 < 0; ++n15) {
                    for (int n16 = -anInt4513; n16 < 0; ++n16) {
                        final int n17 = this.anIntArray6212[n4++];
                        final int n18 = n17 + n2;
                        final int n19 = (n17 & 0xFF00FF) + (n2 & 0xFF00FF);
                        final int n20 = (n19 & 0x1000100) + (n18 - n19 & 0x10000);
                        anIntArray4504[i++] = (n18 - n20 | n20 - (n20 >>> 8));
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 2) {
                final int n21 = n2 >>> 24;
                final int n22 = 256 - n21;
                n2 = (((n2 & 0xFF00FF) * n22 & 0xFF00FF00) | ((n2 & 0xFF00) * n22 & 0xFF0000)) >>> 8;
                for (int n23 = -anInt4512; n23 < 0; ++n23) {
                    for (int n24 = -anInt4513; n24 < 0; ++n24) {
                        final int n25 = this.anIntArray6212[n4++];
                        anIntArray4504[i++] = ((((n25 & 0xFF00FF) * n21 & 0xFF00FF00) | ((n25 & 0xFF00) * n21 & 0xFF0000)) >>> 8) + n2;
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
        else if (n3 == 1) {
            if (n == 1) {
                for (int n26 = -anInt4512; n26 < 0; ++n26) {
                    for (int n27 = -anInt4513; n27 < 0; ++n27) {
                        final int n28 = this.anIntArray6212[n4++];
                        final int n29 = n28 >>> 24;
                        final int n30 = 256 - n29;
                        final int n31 = anIntArray4504[i];
                        anIntArray4504[i++] = (((n28 & 0xFF00FF) * n29 + (n31 & 0xFF00FF) * n30 & 0xFF00FF00) >> 8) + (((n28 & 0xFF00FF00) >>> 8) * n29 + ((n31 & 0xFF00FF00) >>> 8) * n30 & 0xFF00FF00);
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 0) {
                if ((n2 & 0xFFFFFF) == 0xFFFFFF) {
                    for (int n32 = -anInt4512; n32 < 0; ++n32) {
                        for (int n33 = -anInt4513; n33 < 0; ++n33) {
                            final int n34 = this.anIntArray6212[n4++];
                            final int n35 = (n34 >>> 24) * (n2 >>> 24) >> 8;
                            final int n36 = 256 - n35;
                            final int n37 = anIntArray4504[i];
                            anIntArray4504[i++] = ((n34 & 0xFF00FF) * n35 + (n37 & 0xFF00FF) * n36 & 0xFF00FF00) + ((n34 & 0xFF00) * n35 + (n37 & 0xFF00) * n36 & 0xFF0000) >> 8;
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                final int n38 = (n2 & 0xFF0000) >> 16;
                final int n39 = (n2 & 0xFF00) >> 8;
                final int n40 = n2 & 0xFF;
                for (int n41 = -anInt4512; n41 < 0; ++n41) {
                    for (int n42 = -anInt4513; n42 < 0; ++n42) {
                        final int n43 = this.anIntArray6212[n4++];
                        final int n44 = (n43 >>> 24) * (n2 >>> 24) >> 8;
                        final int n45 = 256 - n44;
                        if (n44 != 255) {
                            final int n46 = (((n43 & 0xFF0000) * n38 & 0xFF000000) | ((n43 & 0xFF00) * n39 & 0xFF0000) | ((n43 & 0xFF) * n40 & 0xFF00)) >>> 8;
                            final int n47 = anIntArray4504[i];
                            anIntArray4504[i++] = ((n46 & 0xFF00FF) * n44 + (n47 & 0xFF00FF) * n45 & 0xFF00FF00) + ((n46 & 0xFF00) * n44 + (n47 & 0xFF00) * n45 & 0xFF0000) >> 8;
                        }
                        else {
                            anIntArray4504[i++] = (((n43 & 0xFF0000) * n38 & 0xFF000000) | ((n43 & 0xFF00) * n39 & 0xFF0000) | ((n43 & 0xFF) * n40 & 0xFF00)) >>> 8;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
            }
            else {
                if (n == 3) {
                    for (int n48 = -anInt4512; n48 < 0; ++n48) {
                        for (int n49 = -anInt4513; n49 < 0; ++n49) {
                            final int n50 = this.anIntArray6212[n4++];
                            final int n51 = n50 + n2;
                            final int n52 = (n50 & 0xFF00FF) + (n2 & 0xFF00FF);
                            final int n53 = (n52 & 0x1000100) + (n51 - n52 & 0x10000);
                            int n54 = n51 - n53 | n53 - (n53 >>> 8);
                            final int n55 = (n54 >>> 24) * (n2 >>> 24) >> 8;
                            final int n56 = 256 - n55;
                            if (n55 != 255) {
                                final int n57 = n54;
                                final int n58 = anIntArray4504[i];
                                n54 = ((n57 & 0xFF00FF) * n55 + (n58 & 0xFF00FF) * n56 & 0xFF00FF00) + ((n57 & 0xFF00) * n55 + (n58 & 0xFF00) * n56 & 0xFF0000) >> 8;
                            }
                            anIntArray4504[i++] = n54;
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                if (n == 2) {
                    final int n59 = n2 >>> 24;
                    final int n60 = 256 - n59;
                    n2 = (((n2 & 0xFF00FF) * n60 & 0xFF00FF00) | ((n2 & 0xFF00) * n60 & 0xFF0000)) >>> 8;
                    for (int n61 = -anInt4512; n61 < 0; ++n61) {
                        for (int n62 = -anInt4513; n62 < 0; ++n62) {
                            final int n63 = this.anIntArray6212[n4++];
                            final int n64 = n63 >>> 24;
                            final int n65 = 256 - n64;
                            final int n66 = ((((n63 & 0xFF00FF) * n59 & 0xFF00FF00) | ((n63 & 0xFF00) * n59 & 0xFF0000)) >>> 8) + n2;
                            final int n67 = anIntArray4504[i];
                            anIntArray4504[i++] = ((n66 & 0xFF00FF) * n64 + (n67 & 0xFF00FF) * n65 & 0xFF00FF00) + ((n66 & 0xFF00) * n64 + (n67 & 0xFF00) * n65 & 0xFF0000) >> 8;
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                throw new IllegalArgumentException();
            }
        }
        else {
            if (n3 != 2) {
                throw new IllegalArgumentException();
            }
            if (n == 1) {
                for (int n68 = -anInt4512; n68 < 0; ++n68) {
                    for (int n69 = -anInt4513; n69 < 0; ++n69) {
                        final int n70 = this.anIntArray6212[n4++];
                        if (n70 != 0) {
                            final int n71 = anIntArray4504[i];
                            final int n72 = n70 + n71;
                            final int n73 = (n70 & 0xFF00FF) + (n71 & 0xFF00FF);
                            final int n74 = (n73 & 0x1000100) + (n72 - n73 & 0x10000);
                            anIntArray4504[i++] = (n72 - n74 | n74 - (n74 >>> 8));
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 0) {
                final int n75 = (n2 & 0xFF0000) >> 16;
                final int n76 = (n2 & 0xFF00) >> 8;
                final int n77 = n2 & 0xFF;
                for (int n78 = -anInt4512; n78 < 0; ++n78) {
                    for (int n79 = -anInt4513; n79 < 0; ++n79) {
                        final int n80 = this.anIntArray6212[n4++];
                        if (n80 != 0) {
                            final int n81 = (((n80 & 0xFF0000) * n75 & 0xFF000000) | ((n80 & 0xFF00) * n76 & 0xFF0000) | ((n80 & 0xFF) * n77 & 0xFF00)) >>> 8;
                            final int n82 = anIntArray4504[i];
                            final int n83 = n81 + n82;
                            final int n84 = (n81 & 0xFF00FF) + (n82 & 0xFF00FF);
                            final int n85 = (n84 & 0x1000100) + (n83 - n84 & 0x10000);
                            anIntArray4504[i++] = (n83 - n85 | n85 - (n85 >>> 8));
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 3) {
                for (int n86 = -anInt4512; n86 < 0; ++n86) {
                    for (int n87 = -anInt4513; n87 < 0; ++n87) {
                        final int n88 = this.anIntArray6212[n4++];
                        final int n89 = n88 + n2;
                        final int n90 = (n88 & 0xFF00FF) + (n2 & 0xFF00FF);
                        final int n91 = (n90 & 0x1000100) + (n89 - n90 & 0x10000);
                        final int n92 = n89 - n91 | n91 - (n91 >>> 8);
                        final int n93 = anIntArray4504[i];
                        final int n94 = n92 + n93;
                        final int n95 = (n92 & 0xFF00FF) + (n93 & 0xFF00FF);
                        final int n96 = (n95 & 0x1000100) + (n94 - n95 & 0x10000);
                        anIntArray4504[i++] = (n94 - n96 | n96 - (n96 >>> 8));
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 2) {
                final int n97 = n2 >>> 24;
                final int n98 = 256 - n97;
                n2 = (((n2 & 0xFF00FF) * n98 & 0xFF00FF00) | ((n2 & 0xFF00) * n98 & 0xFF0000)) >>> 8;
                for (int n99 = -anInt4512; n99 < 0; ++n99) {
                    for (int n100 = -anInt4513; n100 < 0; ++n100) {
                        final int n101 = this.anIntArray6212[n4++];
                        if (n101 != 0) {
                            final int n102 = ((((n101 & 0xFF00FF) * n97 & 0xFF00FF00) | ((n101 & 0xFF00) * n97 & 0xFF0000)) >>> 8) + n2;
                            final int n103 = anIntArray4504[i];
                            final int n104 = n102 + n103;
                            final int n105 = (n102 & 0xFF00FF) + (n103 & 0xFF00FF);
                            final int n106 = (n105 & 0x1000100) + (n104 - n105 & 0x10000);
                            anIntArray4504[i++] = (n104 - n106 | n106 - (n106 >>> 8));
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }
    
    Class332_Sub3_Sub1(final ha_Sub2 ha_Sub2, final int n, final int n2) {
        super(ha_Sub2, n, n2);
        this.anIntArray6212 = new int[n * n2];
    }
    
    @Override
    final void method3758(final int[] array, final int[] array2, final int n, final int n2) {
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        if (Class332_Sub3.anInt5444 == 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int i = Class332_Sub3.anInt5431; i < 0; ++i, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n3 = i + n2;
                    if (n3 >= 0) {
                        if (n3 >= array.length) {
                            break;
                        }
                        int anInt5451 = Class332_Sub3.anInt5451;
                        int anInt5452 = Class332_Sub3.anInt5450;
                        int anInt5453 = Class332_Sub3.anInt5443;
                        int j = Class332_Sub3.anInt5448;
                        if (anInt5452 >= 0 && anInt5453 >= 0 && anInt5452 - (super.anInt5433 << 12) < 0 && anInt5453 - (super.anInt5454 << 12) < 0) {
                            final int n4 = array[n3] - n;
                            int n5 = -array2[n3];
                            final int n6 = n4 - (anInt5451 - Class332_Sub3.anInt5451);
                            if (n6 > 0) {
                                anInt5451 += n6;
                                j += n6;
                                anInt5452 += Class332_Sub3.anInt5444 * n6;
                                anInt5453 += Class332_Sub3.anInt5436 * n6;
                            }
                            else {
                                n5 -= n6;
                            }
                            if (j < n5) {
                                j = n5;
                            }
                            while (j < 0) {
                                final int n7 = this.anIntArray6212[(anInt5453 >> 12) * super.anInt5433 + (anInt5452 >> 12)];
                                final int n8 = n7 >>> 24;
                                final int n9 = 256 - n8;
                                final int n10 = anIntArray4504[anInt5451];
                                anIntArray4504[anInt5451++] = ((n7 & 0xFF00FF) * n8 + (n10 & 0xFF00FF) * n9 & 0xFF00FF00) + ((n7 & 0xFF00) * n8 + (n10 & 0xFF00) * n9 & 0xFF0000) >> 8;
                                ++j;
                            }
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int k = Class332_Sub3.anInt5431; k < 0; ++k, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n11 = k + n2;
                    if (n11 >= 0) {
                        if (n11 >= array.length) {
                            break;
                        }
                        int anInt5454 = Class332_Sub3.anInt5451;
                        int anInt5455 = Class332_Sub3.anInt5450;
                        int n12 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int l = Class332_Sub3.anInt5448;
                        if (anInt5455 >= 0 && anInt5455 - (super.anInt5433 << 12) < 0) {
                            final int n13;
                            if ((n13 = n12 - (super.anInt5454 << 12)) >= 0) {
                                final int n14 = (Class332_Sub3.anInt5436 - n13) / Class332_Sub3.anInt5436;
                                l += n14;
                                n12 += Class332_Sub3.anInt5436 * n14;
                                anInt5454 += n14;
                            }
                            final int n15;
                            if ((n15 = (n12 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > l) {
                                l = n15;
                            }
                            final int n16 = array[n11] - n;
                            int n17 = -array2[n11];
                            final int n18 = n16 - (anInt5454 - Class332_Sub3.anInt5451);
                            if (n18 > 0) {
                                anInt5454 += n18;
                                l += n18;
                                anInt5455 += Class332_Sub3.anInt5444 * n18;
                                n12 += Class332_Sub3.anInt5436 * n18;
                            }
                            else {
                                n17 -= n18;
                            }
                            if (l < n17) {
                                l = n17;
                            }
                            while (l < 0) {
                                final int n19 = this.anIntArray6212[(n12 >> 12) * super.anInt5433 + (anInt5455 >> 12)];
                                final int n20 = n19 >>> 24;
                                final int n21 = 256 - n20;
                                final int n22 = anIntArray4504[anInt5454];
                                anIntArray4504[anInt5454++] = ((n19 & 0xFF00FF) * n20 + (n22 & 0xFF00FF) * n21 & 0xFF00FF00) + ((n19 & 0xFF00) * n20 + (n22 & 0xFF00) * n21 & 0xFF0000) >> 8;
                                n12 += Class332_Sub3.anInt5436;
                                ++l;
                            }
                        }
                    }
                }
            }
            else {
                for (int anInt5456 = Class332_Sub3.anInt5431; anInt5456 < 0; ++anInt5456, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n23 = anInt5456 + n2;
                    if (n23 >= 0) {
                        if (n23 >= array.length) {
                            break;
                        }
                        int anInt5457 = Class332_Sub3.anInt5451;
                        int anInt5458 = Class332_Sub3.anInt5450;
                        int n24 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5459 = Class332_Sub3.anInt5448;
                        if (anInt5458 >= 0 && anInt5458 - (super.anInt5433 << 12) < 0) {
                            if (n24 < 0) {
                                final int n25 = (Class332_Sub3.anInt5436 - 1 - n24) / Class332_Sub3.anInt5436;
                                anInt5459 += n25;
                                n24 += Class332_Sub3.anInt5436 * n25;
                                anInt5457 += n25;
                            }
                            final int n26;
                            if ((n26 = (1 + n24 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5459) {
                                anInt5459 = n26;
                            }
                            final int n27 = array[n23] - n;
                            int n28 = -array2[n23];
                            final int n29 = n27 - (anInt5457 - Class332_Sub3.anInt5451);
                            if (n29 > 0) {
                                anInt5457 += n29;
                                anInt5459 += n29;
                                anInt5458 += Class332_Sub3.anInt5444 * n29;
                                n24 += Class332_Sub3.anInt5436 * n29;
                            }
                            else {
                                n28 -= n29;
                            }
                            if (anInt5459 < n28) {
                                anInt5459 = n28;
                            }
                            while (anInt5459 < 0) {
                                final int n30 = this.anIntArray6212[(n24 >> 12) * super.anInt5433 + (anInt5458 >> 12)];
                                final int n31 = n30 >>> 24;
                                final int n32 = 256 - n31;
                                final int n33 = anIntArray4504[anInt5457];
                                anIntArray4504[anInt5457++] = ((n30 & 0xFF00FF) * n31 + (n33 & 0xFF00FF) * n32 & 0xFF00FF00) + ((n30 & 0xFF00) * n31 + (n33 & 0xFF00) * n32 & 0xFF0000) >> 8;
                                n24 += Class332_Sub3.anInt5436;
                                ++anInt5459;
                            }
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5444 < 0) {
            if (Class332_Sub3.anInt5436 == 0) {
                for (int anInt5460 = Class332_Sub3.anInt5431; anInt5460 < 0; ++anInt5460, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n34 = anInt5460 + n2;
                    if (n34 >= 0) {
                        if (n34 >= array.length) {
                            break;
                        }
                        int anInt5461 = Class332_Sub3.anInt5451;
                        int n35 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int anInt5462 = Class332_Sub3.anInt5443;
                        int anInt5463 = Class332_Sub3.anInt5448;
                        if (anInt5462 >= 0 && anInt5462 - (super.anInt5454 << 12) < 0) {
                            final int n36;
                            if ((n36 = n35 - (super.anInt5433 << 12)) >= 0) {
                                final int n37 = (Class332_Sub3.anInt5444 - n36) / Class332_Sub3.anInt5444;
                                anInt5463 += n37;
                                n35 += Class332_Sub3.anInt5444 * n37;
                                anInt5461 += n37;
                            }
                            final int n38;
                            if ((n38 = (n35 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5463) {
                                anInt5463 = n38;
                            }
                            final int n39 = array[n34] - n;
                            int n40 = -array2[n34];
                            final int n41 = n39 - (anInt5461 - Class332_Sub3.anInt5451);
                            if (n41 > 0) {
                                anInt5461 += n41;
                                anInt5463 += n41;
                                n35 += Class332_Sub3.anInt5444 * n41;
                                anInt5462 += Class332_Sub3.anInt5436 * n41;
                            }
                            else {
                                n40 -= n41;
                            }
                            if (anInt5463 < n40) {
                                anInt5463 = n40;
                            }
                            while (anInt5463 < 0) {
                                final int n42 = this.anIntArray6212[(anInt5462 >> 12) * super.anInt5433 + (n35 >> 12)];
                                final int n43 = n42 >>> 24;
                                final int n44 = 256 - n43;
                                final int n45 = anIntArray4504[anInt5461];
                                anIntArray4504[anInt5461++] = ((n42 & 0xFF00FF) * n43 + (n45 & 0xFF00FF) * n44 & 0xFF00FF00) + ((n42 & 0xFF00) * n43 + (n45 & 0xFF00) * n44 & 0xFF0000) >> 8;
                                n35 += Class332_Sub3.anInt5444;
                                ++anInt5463;
                            }
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int anInt5464 = Class332_Sub3.anInt5431; anInt5464 < 0; ++anInt5464, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n46 = anInt5464 + n2;
                    if (n46 >= 0) {
                        if (n46 >= array.length) {
                            break;
                        }
                        int anInt5465 = Class332_Sub3.anInt5451;
                        int n47 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int n48 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5466 = Class332_Sub3.anInt5448;
                        final int n49;
                        if ((n49 = n47 - (super.anInt5433 << 12)) >= 0) {
                            final int n50 = (Class332_Sub3.anInt5444 - n49) / Class332_Sub3.anInt5444;
                            anInt5466 += n50;
                            n47 += Class332_Sub3.anInt5444 * n50;
                            n48 += Class332_Sub3.anInt5436 * n50;
                            anInt5465 += n50;
                        }
                        final int n51;
                        if ((n51 = (n47 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5466) {
                            anInt5466 = n51;
                        }
                        final int n52;
                        if ((n52 = n48 - (super.anInt5454 << 12)) >= 0) {
                            final int n53 = (Class332_Sub3.anInt5436 - n52) / Class332_Sub3.anInt5436;
                            anInt5466 += n53;
                            n47 += Class332_Sub3.anInt5444 * n53;
                            n48 += Class332_Sub3.anInt5436 * n53;
                            anInt5465 += n53;
                        }
                        final int n54;
                        if ((n54 = (n48 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5466) {
                            anInt5466 = n54;
                        }
                        final int n55 = array[n46] - n;
                        int n56 = -array2[n46];
                        final int n57 = n55 - (anInt5465 - Class332_Sub3.anInt5451);
                        if (n57 > 0) {
                            anInt5465 += n57;
                            anInt5466 += n57;
                            n47 += Class332_Sub3.anInt5444 * n57;
                            n48 += Class332_Sub3.anInt5436 * n57;
                        }
                        else {
                            n56 -= n57;
                        }
                        if (anInt5466 < n56) {
                            anInt5466 = n56;
                        }
                        while (anInt5466 < 0) {
                            final int n58 = this.anIntArray6212[(n48 >> 12) * super.anInt5433 + (n47 >> 12)];
                            final int n59 = n58 >>> 24;
                            final int n60 = 256 - n59;
                            final int n61 = anIntArray4504[anInt5465];
                            anIntArray4504[anInt5465++] = ((n58 & 0xFF00FF) * n59 + (n61 & 0xFF00FF) * n60 & 0xFF00FF00) + ((n58 & 0xFF00) * n59 + (n61 & 0xFF00) * n60 & 0xFF0000) >> 8;
                            n47 += Class332_Sub3.anInt5444;
                            n48 += Class332_Sub3.anInt5436;
                            ++anInt5466;
                        }
                    }
                }
            }
            else {
                for (int anInt5467 = Class332_Sub3.anInt5431; anInt5467 < 0; ++anInt5467, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n62 = anInt5467 + n2;
                    if (n62 >= 0) {
                        if (n62 >= array.length) {
                            break;
                        }
                        int anInt5468 = Class332_Sub3.anInt5451;
                        int n63 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int n64 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5469 = Class332_Sub3.anInt5448;
                        final int n65;
                        if ((n65 = n63 - (super.anInt5433 << 12)) >= 0) {
                            final int n66 = (Class332_Sub3.anInt5444 - n65) / Class332_Sub3.anInt5444;
                            anInt5469 += n66;
                            n63 += Class332_Sub3.anInt5444 * n66;
                            n64 += Class332_Sub3.anInt5436 * n66;
                            anInt5468 += n66;
                        }
                        final int n67;
                        if ((n67 = (n63 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5469) {
                            anInt5469 = n67;
                        }
                        if (n64 < 0) {
                            final int n68 = (Class332_Sub3.anInt5436 - 1 - n64) / Class332_Sub3.anInt5436;
                            anInt5469 += n68;
                            n63 += Class332_Sub3.anInt5444 * n68;
                            n64 += Class332_Sub3.anInt5436 * n68;
                            anInt5468 += n68;
                        }
                        final int n69;
                        if ((n69 = (1 + n64 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5469) {
                            anInt5469 = n69;
                        }
                        final int n70 = array[n62] - n;
                        int n71 = -array2[n62];
                        final int n72 = n70 - (anInt5468 - Class332_Sub3.anInt5451);
                        if (n72 > 0) {
                            anInt5468 += n72;
                            anInt5469 += n72;
                            n63 += Class332_Sub3.anInt5444 * n72;
                            n64 += Class332_Sub3.anInt5436 * n72;
                        }
                        else {
                            n71 -= n72;
                        }
                        if (anInt5469 < n71) {
                            anInt5469 = n71;
                        }
                        while (anInt5469 < 0) {
                            final int n73 = this.anIntArray6212[(n64 >> 12) * super.anInt5433 + (n63 >> 12)];
                            final int n74 = n73 >>> 24;
                            final int n75 = 256 - n74;
                            final int n76 = anIntArray4504[anInt5468];
                            anIntArray4504[anInt5468++] = ((n73 & 0xFF00FF) * n74 + (n76 & 0xFF00FF) * n75 & 0xFF00FF00) + ((n73 & 0xFF00) * n74 + (n76 & 0xFF00) * n75 & 0xFF0000) >> 8;
                            n63 += Class332_Sub3.anInt5444;
                            n64 += Class332_Sub3.anInt5436;
                            ++anInt5469;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 == 0) {
            for (int anInt5470 = Class332_Sub3.anInt5431; anInt5470 < 0; ++anInt5470, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n77 = anInt5470 + n2;
                if (n77 >= 0) {
                    if (n77 >= array.length) {
                        break;
                    }
                    int anInt5471 = Class332_Sub3.anInt5451;
                    int n78 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int anInt5472 = Class332_Sub3.anInt5443;
                    int anInt5473 = Class332_Sub3.anInt5448;
                    if (anInt5472 >= 0 && anInt5472 - (super.anInt5454 << 12) < 0) {
                        if (n78 < 0) {
                            final int n79 = (Class332_Sub3.anInt5444 - 1 - n78) / Class332_Sub3.anInt5444;
                            anInt5473 += n79;
                            n78 += Class332_Sub3.anInt5444 * n79;
                            anInt5471 += n79;
                        }
                        final int n80;
                        if ((n80 = (1 + n78 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5473) {
                            anInt5473 = n80;
                        }
                        final int n81 = array[n77] - n;
                        int n82 = -array2[n77];
                        final int n83 = n81 - (anInt5471 - Class332_Sub3.anInt5451);
                        if (n83 > 0) {
                            anInt5471 += n83;
                            anInt5473 += n83;
                            n78 += Class332_Sub3.anInt5444 * n83;
                            anInt5472 += Class332_Sub3.anInt5436 * n83;
                        }
                        else {
                            n82 -= n83;
                        }
                        if (anInt5473 < n82) {
                            anInt5473 = n82;
                        }
                        while (anInt5473 < 0) {
                            final int n84 = this.anIntArray6212[(anInt5472 >> 12) * super.anInt5433 + (n78 >> 12)];
                            final int n85 = n84 >>> 24;
                            final int n86 = 256 - n85;
                            final int n87 = anIntArray4504[anInt5471];
                            anIntArray4504[anInt5471++] = ((n84 & 0xFF00FF) * n85 + (n87 & 0xFF00FF) * n86 & 0xFF00FF00) + ((n84 & 0xFF00) * n85 + (n87 & 0xFF00) * n86 & 0xFF0000) >> 8;
                            n78 += Class332_Sub3.anInt5444;
                            ++anInt5473;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 < 0) {
            for (int anInt5474 = Class332_Sub3.anInt5431; anInt5474 < 0; ++anInt5474, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n88 = anInt5474 + n2;
                if (n88 >= 0) {
                    if (n88 >= array.length) {
                        break;
                    }
                    int anInt5475 = Class332_Sub3.anInt5451;
                    int n89 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n90 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5476 = Class332_Sub3.anInt5448;
                    if (n89 < 0) {
                        final int n91 = (Class332_Sub3.anInt5444 - 1 - n89) / Class332_Sub3.anInt5444;
                        anInt5476 += n91;
                        n89 += Class332_Sub3.anInt5444 * n91;
                        n90 += Class332_Sub3.anInt5436 * n91;
                        anInt5475 += n91;
                    }
                    final int n92;
                    if ((n92 = (1 + n89 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5476) {
                        anInt5476 = n92;
                    }
                    final int n93;
                    if ((n93 = n90 - (super.anInt5454 << 12)) >= 0) {
                        final int n94 = (Class332_Sub3.anInt5436 - n93) / Class332_Sub3.anInt5436;
                        anInt5476 += n94;
                        n89 += Class332_Sub3.anInt5444 * n94;
                        n90 += Class332_Sub3.anInt5436 * n94;
                        anInt5475 += n94;
                    }
                    final int n95;
                    if ((n95 = (n90 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5476) {
                        anInt5476 = n95;
                    }
                    final int n96 = array[n88] - n;
                    int n97 = -array2[n88];
                    final int n98 = n96 - (anInt5475 - Class332_Sub3.anInt5451);
                    if (n98 > 0) {
                        anInt5475 += n98;
                        anInt5476 += n98;
                        n89 += Class332_Sub3.anInt5444 * n98;
                        n90 += Class332_Sub3.anInt5436 * n98;
                    }
                    else {
                        n97 -= n98;
                    }
                    if (anInt5476 < n97) {
                        anInt5476 = n97;
                    }
                    while (anInt5476 < 0) {
                        final int n99 = this.anIntArray6212[(n90 >> 12) * super.anInt5433 + (n89 >> 12)];
                        final int n100 = n99 >>> 24;
                        final int n101 = 256 - n100;
                        final int n102 = anIntArray4504[anInt5475];
                        anIntArray4504[anInt5475++] = ((n99 & 0xFF00FF) * n100 + (n102 & 0xFF00FF) * n101 & 0xFF00FF00) + ((n99 & 0xFF00) * n100 + (n102 & 0xFF00) * n101 & 0xFF0000) >> 8;
                        n89 += Class332_Sub3.anInt5444;
                        n90 += Class332_Sub3.anInt5436;
                        ++anInt5476;
                    }
                }
            }
        }
        else {
            for (int anInt5477 = Class332_Sub3.anInt5431; anInt5477 < 0; ++anInt5477, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n103 = anInt5477 + n2;
                if (n103 >= 0) {
                    if (n103 >= array.length) {
                        break;
                    }
                    int anInt5478 = Class332_Sub3.anInt5451;
                    int n104 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n105 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5479 = Class332_Sub3.anInt5448;
                    if (n104 < 0) {
                        final int n106 = (Class332_Sub3.anInt5444 - 1 - n104) / Class332_Sub3.anInt5444;
                        anInt5479 += n106;
                        n104 += Class332_Sub3.anInt5444 * n106;
                        n105 += Class332_Sub3.anInt5436 * n106;
                        anInt5478 += n106;
                    }
                    final int n107;
                    if ((n107 = (1 + n104 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5479) {
                        anInt5479 = n107;
                    }
                    if (n105 < 0) {
                        final int n108 = (Class332_Sub3.anInt5436 - 1 - n105) / Class332_Sub3.anInt5436;
                        anInt5479 += n108;
                        n104 += Class332_Sub3.anInt5444 * n108;
                        n105 += Class332_Sub3.anInt5436 * n108;
                        anInt5478 += n108;
                    }
                    final int n109;
                    if ((n109 = (1 + n105 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5479) {
                        anInt5479 = n109;
                    }
                    final int n110 = array[n103] - n;
                    int n111 = -array2[n103];
                    final int n112 = n110 - (anInt5478 - Class332_Sub3.anInt5451);
                    if (n112 > 0) {
                        anInt5478 += n112;
                        anInt5479 += n112;
                        n104 += Class332_Sub3.anInt5444 * n112;
                        n105 += Class332_Sub3.anInt5436 * n112;
                    }
                    else {
                        n111 -= n112;
                    }
                    if (anInt5479 < n111) {
                        anInt5479 = n111;
                    }
                    while (anInt5479 < 0) {
                        final int n113 = this.anIntArray6212[(n105 >> 12) * super.anInt5433 + (n104 >> 12)];
                        final int n114 = n113 >>> 24;
                        final int n115 = 256 - n114;
                        final int n116 = anIntArray4504[anInt5478];
                        anIntArray4504[anInt5478++] = ((n113 & 0xFF00FF) * n114 + (n116 & 0xFF00FF) * n115 & 0xFF00FF00) + ((n113 & 0xFF00) * n114 + (n116 & 0xFF00) * n115 & 0xFF0000) >> 8;
                        n104 += Class332_Sub3.anInt5444;
                        n105 += Class332_Sub3.anInt5436;
                        ++anInt5479;
                    }
                }
            }
        }
    }
}

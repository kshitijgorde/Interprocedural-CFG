// 
// Decompiled by Procyon v0.5.30
// 

final class Class332_Sub3_Sub2 extends Class332_Sub3
{
    int[] anIntArray6213;
    
    Class332_Sub3_Sub2(final ha_Sub2 ha_Sub2, final int[] anIntArray6213, final int n, final int n2) {
        super(ha_Sub2, n, n2);
        this.anIntArray6213 = anIntArray6213;
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
                    for (n10 = i + anInt4513 - 3; i < n10; anIntArray4504[i++] = this.anIntArray6213[n4++], anIntArray4504[i++] = this.anIntArray6213[n4++], anIntArray4504[i++] = this.anIntArray6213[n4++], anIntArray4504[i++] = this.anIntArray6213[n4++]) {}
                    for (n10 += 3; i < n10; anIntArray4504[i++] = this.anIntArray6213[n4++]) {}
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
                        final int n14 = this.anIntArray6213[n4++];
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
                        final int n17 = this.anIntArray6213[n4++];
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
                        final int n25 = this.anIntArray6213[n4++];
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
                    int n27 = i + anInt4513 - 3;
                    while (i < n27) {
                        final int n28 = this.anIntArray6213[n4++];
                        if (n28 != 0) {
                            anIntArray4504[i++] = n28;
                        }
                        else {
                            ++i;
                        }
                        final int n29 = this.anIntArray6213[n4++];
                        if (n29 != 0) {
                            anIntArray4504[i++] = n29;
                        }
                        else {
                            ++i;
                        }
                        final int n30 = this.anIntArray6213[n4++];
                        if (n30 != 0) {
                            anIntArray4504[i++] = n30;
                        }
                        else {
                            ++i;
                        }
                        final int n31 = this.anIntArray6213[n4++];
                        if (n31 != 0) {
                            anIntArray4504[i++] = n31;
                        }
                        else {
                            ++i;
                        }
                    }
                    n27 += 3;
                    while (i < n27) {
                        final int n32 = this.anIntArray6213[n4++];
                        if (n32 != 0) {
                            anIntArray4504[i++] = n32;
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
                if ((n2 & 0xFFFFFF) == 0xFFFFFF) {
                    final int n33 = n2 >>> 24;
                    final int n34 = 256 - n33;
                    for (int n35 = -anInt4512; n35 < 0; ++n35) {
                        for (int n36 = -anInt4513; n36 < 0; ++n36) {
                            final int n37 = this.anIntArray6213[n4++];
                            if (n37 != 0) {
                                final int n38 = anIntArray4504[i];
                                anIntArray4504[i++] = ((n37 & 0xFF00FF) * n33 + (n38 & 0xFF00FF) * n34 & 0xFF00FF00) + ((n37 & 0xFF00) * n33 + (n38 & 0xFF00) * n34 & 0xFF0000) >> 8;
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
                final int n39 = (n2 & 0xFF0000) >> 16;
                final int n40 = (n2 & 0xFF00) >> 8;
                final int n41 = n2 & 0xFF;
                final int n42 = n2 >>> 24;
                final int n43 = 256 - n42;
                for (int n44 = -anInt4512; n44 < 0; ++n44) {
                    for (int n45 = -anInt4513; n45 < 0; ++n45) {
                        final int n46 = this.anIntArray6213[n4++];
                        if (n46 != 0) {
                            if (n42 != 255) {
                                final int n47 = (((n46 & 0xFF0000) * n39 & 0xFF000000) | ((n46 & 0xFF00) * n40 & 0xFF0000) | ((n46 & 0xFF) * n41 & 0xFF00)) >>> 8;
                                final int n48 = anIntArray4504[i];
                                anIntArray4504[i++] = ((n47 & 0xFF00FF) * n42 + (n48 & 0xFF00FF) * n43 & 0xFF00FF00) + ((n47 & 0xFF00) * n42 + (n48 & 0xFF00) * n43 & 0xFF0000) >> 8;
                            }
                            else {
                                anIntArray4504[i++] = (((n46 & 0xFF0000) * n39 & 0xFF000000) | ((n46 & 0xFF00) * n40 & 0xFF0000) | ((n46 & 0xFF) * n41 & 0xFF00)) >>> 8;
                            }
                        }
                        else {
                            ++i;
                        }
                    }
                    i += n5;
                    n4 += n6;
                }
            }
            else {
                if (n == 3) {
                    final int n49 = n2 >>> 24;
                    final int n50 = 256 - n49;
                    for (int n51 = -anInt4512; n51 < 0; ++n51) {
                        for (int n52 = -anInt4513; n52 < 0; ++n52) {
                            final int n53 = this.anIntArray6213[n4++];
                            final int n54 = n53 + n2;
                            final int n55 = (n53 & 0xFF00FF) + (n2 & 0xFF00FF);
                            final int n56 = (n55 & 0x1000100) + (n54 - n55 & 0x10000);
                            int n57 = n54 - n56 | n56 - (n56 >>> 8);
                            if (n53 == 0 && n49 != 255) {
                                final int n58 = n57;
                                final int n59 = anIntArray4504[i];
                                n57 = ((n58 & 0xFF00FF) * n49 + (n59 & 0xFF00FF) * n50 & 0xFF00FF00) + ((n58 & 0xFF00) * n49 + (n59 & 0xFF00) * n50 & 0xFF0000) >> 8;
                            }
                            anIntArray4504[i++] = n57;
                        }
                        i += n5;
                        n4 += n6;
                    }
                    return;
                }
                if (n == 2) {
                    final int n60 = n2 >>> 24;
                    final int n61 = 256 - n60;
                    n2 = (((n2 & 0xFF00FF) * n61 & 0xFF00FF00) | ((n2 & 0xFF00) * n61 & 0xFF0000)) >>> 8;
                    for (int n62 = -anInt4512; n62 < 0; ++n62) {
                        for (int n63 = -anInt4513; n63 < 0; ++n63) {
                            final int n64 = this.anIntArray6213[n4++];
                            if (n64 != 0) {
                                anIntArray4504[i++] = ((((n64 & 0xFF00FF) * n60 & 0xFF00FF00) | ((n64 & 0xFF00) * n60 & 0xFF0000)) >>> 8) + n2;
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
        else {
            if (n3 != 2) {
                throw new IllegalArgumentException();
            }
            if (n == 1) {
                for (int n65 = -anInt4512; n65 < 0; ++n65) {
                    for (int n66 = -anInt4513; n66 < 0; ++n66) {
                        final int n67 = this.anIntArray6213[n4++];
                        if (n67 != 0) {
                            final int n68 = anIntArray4504[i];
                            final int n69 = n67 + n68;
                            final int n70 = (n67 & 0xFF00FF) + (n68 & 0xFF00FF);
                            final int n71 = (n70 & 0x1000100) + (n69 - n70 & 0x10000);
                            anIntArray4504[i++] = (n69 - n71 | n71 - (n71 >>> 8));
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
                final int n72 = (n2 & 0xFF0000) >> 16;
                final int n73 = (n2 & 0xFF00) >> 8;
                final int n74 = n2 & 0xFF;
                for (int n75 = -anInt4512; n75 < 0; ++n75) {
                    for (int n76 = -anInt4513; n76 < 0; ++n76) {
                        final int n77 = this.anIntArray6213[n4++];
                        if (n77 != 0) {
                            final int n78 = (((n77 & 0xFF0000) * n72 & 0xFF000000) | ((n77 & 0xFF00) * n73 & 0xFF0000) | ((n77 & 0xFF) * n74 & 0xFF00)) >>> 8;
                            final int n79 = anIntArray4504[i];
                            final int n80 = n78 + n79;
                            final int n81 = (n78 & 0xFF00FF) + (n79 & 0xFF00FF);
                            final int n82 = (n81 & 0x1000100) + (n80 - n81 & 0x10000);
                            anIntArray4504[i++] = (n80 - n82 | n82 - (n82 >>> 8));
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
                for (int n83 = -anInt4512; n83 < 0; ++n83) {
                    for (int n84 = -anInt4513; n84 < 0; ++n84) {
                        final int n85 = this.anIntArray6213[n4++];
                        final int n86 = n85 + n2;
                        final int n87 = (n85 & 0xFF00FF) + (n2 & 0xFF00FF);
                        final int n88 = (n87 & 0x1000100) + (n86 - n87 & 0x10000);
                        final int n89 = n86 - n88 | n88 - (n88 >>> 8);
                        final int n90 = anIntArray4504[i];
                        final int n91 = n89 + n90;
                        final int n92 = (n89 & 0xFF00FF) + (n90 & 0xFF00FF);
                        final int n93 = (n92 & 0x1000100) + (n91 - n92 & 0x10000);
                        anIntArray4504[i++] = (n91 - n93 | n93 - (n93 >>> 8));
                    }
                    i += n5;
                    n4 += n6;
                }
                return;
            }
            if (n == 2) {
                final int n94 = n2 >>> 24;
                final int n95 = 256 - n94;
                n2 = (((n2 & 0xFF00FF) * n95 & 0xFF00FF00) | ((n2 & 0xFF00) * n95 & 0xFF0000)) >>> 8;
                for (int n96 = -anInt4512; n96 < 0; ++n96) {
                    for (int n97 = -anInt4513; n97 < 0; ++n97) {
                        final int n98 = this.anIntArray6213[n4++];
                        if (n98 != 0) {
                            final int n99 = ((((n98 & 0xFF00FF) * n94 & 0xFF00FF00) | ((n98 & 0xFF00) * n94 & 0xFF0000)) >>> 8) + n2;
                            final int n100 = anIntArray4504[i];
                            final int n101 = n99 + n100;
                            final int n102 = (n99 & 0xFF00FF) + (n100 & 0xFF00FF);
                            final int n103 = (n102 & 0x1000100) + (n101 - n102 & 0x10000);
                            anIntArray4504[i++] = (n101 - n103 | n103 - (n103 >>> 8));
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
    
    Class332_Sub3_Sub2(final ha_Sub2 ha_Sub2, final int n, final int n2) {
        super(ha_Sub2, n, n2);
        this.anIntArray6213 = new int[n * n2];
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
                            int n4 = anInt5451++;
                            final int[] array = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array[n4] = this.anIntArray6213[n3];
                                }
                                else if (n == 0) {
                                    final int n5 = this.anIntArray6213[n3++];
                                    array[n4] = (((n5 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n5 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n5 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n6 = this.anIntArray6213[n3++];
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
                                    final int n10 = this.anIntArray6213[n3];
                                    array[n4] = ((((n10 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n10 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n11 = this.anIntArray6213[n3];
                                    if (n11 != 0) {
                                        array[n4] = n11;
                                    }
                                }
                                else if (n == 0) {
                                    final int n12 = this.anIntArray6213[n3];
                                    if (n12 != 0) {
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n13 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n14 = 256 - n13;
                                            final int n15 = array[n4];
                                            array[n4] = ((n12 & 0xFF00FF) * n13 + (n15 & 0xFF00FF) * n14 & 0xFF00FF00) + ((n12 & 0xFF00) * n13 + (n15 & 0xFF00) * n14 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n16 = (((n12 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n12 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n12 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n17 = array[n4];
                                            array[n4] = ((n16 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n17 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n16 & 0xFF00) * Class332_Sub3.anInt5449 + (n17 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array[n4] = (((n12 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n12 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n12 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final int n18 = this.anIntArray6213[n3];
                                    final int anInt5455 = Class332_Sub3.anInt5432;
                                    final int n19 = n18 + anInt5455;
                                    final int n20 = (n18 & 0xFF00FF) + (anInt5455 & 0xFF00FF);
                                    final int n21 = (n20 & 0x1000100) + (n19 - n20 & 0x10000);
                                    int n22 = n19 - n21 | n21 - (n21 >>> 8);
                                    if (n18 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n23 = n22;
                                        final int n24 = array[n4];
                                        n22 = ((n23 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n24 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n23 & 0xFF00) * Class332_Sub3.anInt5449 + (n24 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array[n4] = n22;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n25 = this.anIntArray6213[n3];
                                    if (n25 != 0) {
                                        array[n4++] = ((((n25 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n25 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n26 = this.anIntArray6213[n3];
                                    if (n26 != 0) {
                                        final int n27 = array[n4];
                                        final int n28 = n26 + n27;
                                        final int n29 = (n26 & 0xFF00FF) + (n27 & 0xFF00FF);
                                        final int n30 = (n29 & 0x1000100) + (n28 - n29 & 0x10000);
                                        array[n4] = (n28 - n30 | n30 - (n30 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final int n31 = this.anIntArray6213[n3];
                                    if (n31 != 0) {
                                        final int n32 = (((n31 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n31 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n31 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n33 = array[n4];
                                        final int n34 = n32 + n33;
                                        final int n35 = (n32 & 0xFF00FF) + (n33 & 0xFF00FF);
                                        final int n36 = (n35 & 0x1000100) + (n34 - n35 & 0x10000);
                                        array[n4] = (n34 - n36 | n36 - (n36 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final int n37 = this.anIntArray6213[n3];
                                    final int anInt5456 = Class332_Sub3.anInt5432;
                                    final int n38 = n37 + anInt5456;
                                    final int n39 = (n37 & 0xFF00FF) + (anInt5456 & 0xFF00FF);
                                    final int n40 = (n39 & 0x1000100) + (n38 - n39 & 0x10000);
                                    final int n41 = n38 - n40 | n40 - (n40 >>> 8);
                                    final int n42 = array[n4];
                                    final int n43 = n41 + n42;
                                    final int n44 = (n41 & 0xFF00FF) + (n42 & 0xFF00FF);
                                    final int n45 = (n44 & 0x1000100) + (n43 - n44 & 0x10000);
                                    array[n4] = (n43 - n45 | n45 - (n45 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n46 = this.anIntArray6213[n3];
                                    if (n46 != 0) {
                                        final int n47 = ((((n46 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n46 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n48 = array[n4];
                                        final int n49 = n47 + n48;
                                        final int n50 = (n47 & 0xFF00FF) + (n48 & 0xFF00FF);
                                        final int n51 = (n50 & 0x1000100) + (n49 - n50 & 0x10000);
                                        array[n4] = (n49 - n51 | n51 - (n51 >>> 8));
                                    }
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
                    int n52 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int l = Class332_Sub3.anInt5448;
                    if (anInt5458 >= 0 && anInt5458 - (super.anInt5433 << 12) < 0) {
                        final int n53;
                        if ((n53 = n52 - (super.anInt5454 << 12)) >= 0) {
                            final int n54 = (Class332_Sub3.anInt5436 - n53) / Class332_Sub3.anInt5436;
                            l += n54;
                            n52 += Class332_Sub3.anInt5436 * n54;
                            anInt5457 += n54;
                        }
                        final int n55;
                        if ((n55 = (n52 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > l) {
                            l = n55;
                        }
                        while (l < 0) {
                            int n56 = (n52 >> 12) * super.anInt5433 + (anInt5458 >> 12);
                            int n57 = anInt5457++;
                            final int[] array2 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array2[n57] = this.anIntArray6213[n56];
                                }
                                else if (n == 0) {
                                    final int n58 = this.anIntArray6213[n56++];
                                    array2[n57] = (((n58 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n58 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n58 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n59 = this.anIntArray6213[n56++];
                                    final int anInt5459 = Class332_Sub3.anInt5432;
                                    final int n60 = n59 + anInt5459;
                                    final int n61 = (n59 & 0xFF00FF) + (anInt5459 & 0xFF00FF);
                                    final int n62 = (n61 & 0x1000100) + (n60 - n61 & 0x10000);
                                    array2[n57] = (n60 - n62 | n62 - (n62 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n63 = this.anIntArray6213[n56];
                                    array2[n57] = ((((n63 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n63 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n64 = this.anIntArray6213[n56];
                                    if (n64 != 0) {
                                        array2[n57] = n64;
                                    }
                                }
                                else if (n == 0) {
                                    final int n65 = this.anIntArray6213[n56];
                                    if (n65 != 0) {
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n66 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n67 = 256 - n66;
                                            final int n68 = array2[n57];
                                            array2[n57] = ((n65 & 0xFF00FF) * n66 + (n68 & 0xFF00FF) * n67 & 0xFF00FF00) + ((n65 & 0xFF00) * n66 + (n68 & 0xFF00) * n67 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n69 = (((n65 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n65 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n65 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n70 = array2[n57];
                                            array2[n57] = ((n69 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n70 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n69 & 0xFF00) * Class332_Sub3.anInt5449 + (n70 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array2[n57] = (((n65 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n65 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n65 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final int n71 = this.anIntArray6213[n56];
                                    final int anInt5460 = Class332_Sub3.anInt5432;
                                    final int n72 = n71 + anInt5460;
                                    final int n73 = (n71 & 0xFF00FF) + (anInt5460 & 0xFF00FF);
                                    final int n74 = (n73 & 0x1000100) + (n72 - n73 & 0x10000);
                                    int n75 = n72 - n74 | n74 - (n74 >>> 8);
                                    if (n71 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n76 = n75;
                                        final int n77 = array2[n57];
                                        n75 = ((n76 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n77 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n76 & 0xFF00) * Class332_Sub3.anInt5449 + (n77 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array2[n57] = n75;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n78 = this.anIntArray6213[n56];
                                    if (n78 != 0) {
                                        array2[n57++] = ((((n78 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n78 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n79 = this.anIntArray6213[n56];
                                    if (n79 != 0) {
                                        final int n80 = array2[n57];
                                        final int n81 = n79 + n80;
                                        final int n82 = (n79 & 0xFF00FF) + (n80 & 0xFF00FF);
                                        final int n83 = (n82 & 0x1000100) + (n81 - n82 & 0x10000);
                                        array2[n57] = (n81 - n83 | n83 - (n83 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final int n84 = this.anIntArray6213[n56];
                                    if (n84 != 0) {
                                        final int n85 = (((n84 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n84 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n84 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n86 = array2[n57];
                                        final int n87 = n85 + n86;
                                        final int n88 = (n85 & 0xFF00FF) + (n86 & 0xFF00FF);
                                        final int n89 = (n88 & 0x1000100) + (n87 - n88 & 0x10000);
                                        array2[n57] = (n87 - n89 | n89 - (n89 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final int n90 = this.anIntArray6213[n56];
                                    final int anInt5461 = Class332_Sub3.anInt5432;
                                    final int n91 = n90 + anInt5461;
                                    final int n92 = (n90 & 0xFF00FF) + (anInt5461 & 0xFF00FF);
                                    final int n93 = (n92 & 0x1000100) + (n91 - n92 & 0x10000);
                                    final int n94 = n91 - n93 | n93 - (n93 >>> 8);
                                    final int n95 = array2[n57];
                                    final int n96 = n94 + n95;
                                    final int n97 = (n94 & 0xFF00FF) + (n95 & 0xFF00FF);
                                    final int n98 = (n97 & 0x1000100) + (n96 - n97 & 0x10000);
                                    array2[n57] = (n96 - n98 | n98 - (n98 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n99 = this.anIntArray6213[n56];
                                    if (n99 != 0) {
                                        final int n100 = ((((n99 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n99 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n101 = array2[n57];
                                        final int n102 = n100 + n101;
                                        final int n103 = (n100 & 0xFF00FF) + (n101 & 0xFF00FF);
                                        final int n104 = (n103 & 0x1000100) + (n102 - n103 & 0x10000);
                                        array2[n57] = (n102 - n104 | n104 - (n104 >>> 8));
                                    }
                                }
                            }
                            n52 += Class332_Sub3.anInt5436;
                            ++l;
                        }
                    }
                }
            }
            else {
                for (int anInt5462 = Class332_Sub3.anInt5431; anInt5462 < 0; ++anInt5462, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5463 = Class332_Sub3.anInt5451;
                    final int anInt5464 = Class332_Sub3.anInt5450;
                    int n105 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5465 = Class332_Sub3.anInt5448;
                    if (anInt5464 >= 0 && anInt5464 - (super.anInt5433 << 12) < 0) {
                        if (n105 < 0) {
                            final int n106 = (Class332_Sub3.anInt5436 - 1 - n105) / Class332_Sub3.anInt5436;
                            anInt5465 += n106;
                            n105 += Class332_Sub3.anInt5436 * n106;
                            anInt5463 += n106;
                        }
                        final int n107;
                        if ((n107 = (1 + n105 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5465) {
                            anInt5465 = n107;
                        }
                        while (anInt5465 < 0) {
                            int n108 = (n105 >> 12) * super.anInt5433 + (anInt5464 >> 12);
                            int n109 = anInt5463++;
                            final int[] array3 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array3[n109] = this.anIntArray6213[n108];
                                }
                                else if (n == 0) {
                                    final int n110 = this.anIntArray6213[n108++];
                                    array3[n109] = (((n110 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n110 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n110 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n111 = this.anIntArray6213[n108++];
                                    final int anInt5466 = Class332_Sub3.anInt5432;
                                    final int n112 = n111 + anInt5466;
                                    final int n113 = (n111 & 0xFF00FF) + (anInt5466 & 0xFF00FF);
                                    final int n114 = (n113 & 0x1000100) + (n112 - n113 & 0x10000);
                                    array3[n109] = (n112 - n114 | n114 - (n114 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n115 = this.anIntArray6213[n108];
                                    array3[n109] = ((((n115 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n115 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n116 = this.anIntArray6213[n108];
                                    if (n116 != 0) {
                                        array3[n109] = n116;
                                    }
                                }
                                else if (n == 0) {
                                    final int n117 = this.anIntArray6213[n108];
                                    if (n117 != 0) {
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n118 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n119 = 256 - n118;
                                            final int n120 = array3[n109];
                                            array3[n109] = ((n117 & 0xFF00FF) * n118 + (n120 & 0xFF00FF) * n119 & 0xFF00FF00) + ((n117 & 0xFF00) * n118 + (n120 & 0xFF00) * n119 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n121 = (((n117 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n117 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n117 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n122 = array3[n109];
                                            array3[n109] = ((n121 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n122 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n121 & 0xFF00) * Class332_Sub3.anInt5449 + (n122 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array3[n109] = (((n117 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n117 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n117 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final int n123 = this.anIntArray6213[n108];
                                    final int anInt5467 = Class332_Sub3.anInt5432;
                                    final int n124 = n123 + anInt5467;
                                    final int n125 = (n123 & 0xFF00FF) + (anInt5467 & 0xFF00FF);
                                    final int n126 = (n125 & 0x1000100) + (n124 - n125 & 0x10000);
                                    int n127 = n124 - n126 | n126 - (n126 >>> 8);
                                    if (n123 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n128 = n127;
                                        final int n129 = array3[n109];
                                        n127 = ((n128 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n129 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n128 & 0xFF00) * Class332_Sub3.anInt5449 + (n129 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array3[n109] = n127;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n130 = this.anIntArray6213[n108];
                                    if (n130 != 0) {
                                        array3[n109++] = ((((n130 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n130 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n131 = this.anIntArray6213[n108];
                                    if (n131 != 0) {
                                        final int n132 = array3[n109];
                                        final int n133 = n131 + n132;
                                        final int n134 = (n131 & 0xFF00FF) + (n132 & 0xFF00FF);
                                        final int n135 = (n134 & 0x1000100) + (n133 - n134 & 0x10000);
                                        array3[n109] = (n133 - n135 | n135 - (n135 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final int n136 = this.anIntArray6213[n108];
                                    if (n136 != 0) {
                                        final int n137 = (((n136 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n136 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n136 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n138 = array3[n109];
                                        final int n139 = n137 + n138;
                                        final int n140 = (n137 & 0xFF00FF) + (n138 & 0xFF00FF);
                                        final int n141 = (n140 & 0x1000100) + (n139 - n140 & 0x10000);
                                        array3[n109] = (n139 - n141 | n141 - (n141 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final int n142 = this.anIntArray6213[n108];
                                    final int anInt5468 = Class332_Sub3.anInt5432;
                                    final int n143 = n142 + anInt5468;
                                    final int n144 = (n142 & 0xFF00FF) + (anInt5468 & 0xFF00FF);
                                    final int n145 = (n144 & 0x1000100) + (n143 - n144 & 0x10000);
                                    final int n146 = n143 - n145 | n145 - (n145 >>> 8);
                                    final int n147 = array3[n109];
                                    final int n148 = n146 + n147;
                                    final int n149 = (n146 & 0xFF00FF) + (n147 & 0xFF00FF);
                                    final int n150 = (n149 & 0x1000100) + (n148 - n149 & 0x10000);
                                    array3[n109] = (n148 - n150 | n150 - (n150 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n151 = this.anIntArray6213[n108];
                                    if (n151 != 0) {
                                        final int n152 = ((((n151 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n151 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n153 = array3[n109];
                                        final int n154 = n152 + n153;
                                        final int n155 = (n152 & 0xFF00FF) + (n153 & 0xFF00FF);
                                        final int n156 = (n155 & 0x1000100) + (n154 - n155 & 0x10000);
                                        array3[n109] = (n154 - n156 | n156 - (n156 >>> 8));
                                    }
                                }
                            }
                            n105 += Class332_Sub3.anInt5436;
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
                    int n157 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    final int anInt5471 = Class332_Sub3.anInt5443;
                    int anInt5472 = Class332_Sub3.anInt5448;
                    if (anInt5471 >= 0 && anInt5471 - (super.anInt5454 << 12) < 0) {
                        final int n158;
                        if ((n158 = n157 - (super.anInt5433 << 12)) >= 0) {
                            final int n159 = (Class332_Sub3.anInt5444 - n158) / Class332_Sub3.anInt5444;
                            anInt5472 += n159;
                            n157 += Class332_Sub3.anInt5444 * n159;
                            anInt5470 += n159;
                        }
                        final int n160;
                        if ((n160 = (n157 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5472) {
                            anInt5472 = n160;
                        }
                        while (anInt5472 < 0) {
                            int n161 = (anInt5471 >> 12) * super.anInt5433 + (n157 >> 12);
                            int n162 = anInt5470++;
                            final int[] array4 = anIntArray4504;
                            if (n2 == 0) {
                                if (n == 1) {
                                    array4[n162] = this.anIntArray6213[n161];
                                }
                                else if (n == 0) {
                                    final int n163 = this.anIntArray6213[n161++];
                                    array4[n162] = (((n163 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n163 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n163 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                                else if (n == 3) {
                                    final int n164 = this.anIntArray6213[n161++];
                                    final int anInt5473 = Class332_Sub3.anInt5432;
                                    final int n165 = n164 + anInt5473;
                                    final int n166 = (n164 & 0xFF00FF) + (anInt5473 & 0xFF00FF);
                                    final int n167 = (n166 & 0x1000100) + (n165 - n166 & 0x10000);
                                    array4[n162] = (n165 - n167 | n167 - (n167 >>> 8));
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n168 = this.anIntArray6213[n161];
                                    array4[n162] = ((((n168 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n168 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                            else if (n2 == 1) {
                                if (n == 1) {
                                    final int n169 = this.anIntArray6213[n161];
                                    if (n169 != 0) {
                                        array4[n162] = n169;
                                    }
                                }
                                else if (n == 0) {
                                    final int n170 = this.anIntArray6213[n161];
                                    if (n170 != 0) {
                                        if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                            final int n171 = Class332_Sub3.anInt5432 >>> 24;
                                            final int n172 = 256 - n171;
                                            final int n173 = array4[n162];
                                            array4[n162] = ((n170 & 0xFF00FF) * n171 + (n173 & 0xFF00FF) * n172 & 0xFF00FF00) + ((n170 & 0xFF00) * n171 + (n173 & 0xFF00) * n172 & 0xFF0000) >> 8;
                                        }
                                        else if (Class332_Sub3.anInt5449 != 255) {
                                            final int n174 = (((n170 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n170 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n170 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                            final int n175 = array4[n162];
                                            array4[n162] = ((n174 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n175 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n174 & 0xFF00) * Class332_Sub3.anInt5449 + (n175 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                        }
                                        else {
                                            array4[n162] = (((n170 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n170 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n170 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        }
                                    }
                                }
                                else if (n == 3) {
                                    final int n176 = this.anIntArray6213[n161];
                                    final int anInt5474 = Class332_Sub3.anInt5432;
                                    final int n177 = n176 + anInt5474;
                                    final int n178 = (n176 & 0xFF00FF) + (anInt5474 & 0xFF00FF);
                                    final int n179 = (n178 & 0x1000100) + (n177 - n178 & 0x10000);
                                    int n180 = n177 - n179 | n179 - (n179 >>> 8);
                                    if (n176 == 0 && Class332_Sub3.anInt5449 != 255) {
                                        final int n181 = n180;
                                        final int n182 = array4[n162];
                                        n180 = ((n181 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n182 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n181 & 0xFF00) * Class332_Sub3.anInt5449 + (n182 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    array4[n162] = n180;
                                }
                                else {
                                    if (n != 2) {
                                        throw new IllegalArgumentException();
                                    }
                                    final int n183 = this.anIntArray6213[n161];
                                    if (n183 != 0) {
                                        array4[n162++] = ((((n183 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n183 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    }
                                }
                            }
                            else {
                                if (n2 != 2) {
                                    throw new IllegalArgumentException();
                                }
                                if (n == 1) {
                                    final int n184 = this.anIntArray6213[n161];
                                    if (n184 != 0) {
                                        final int n185 = array4[n162];
                                        final int n186 = n184 + n185;
                                        final int n187 = (n184 & 0xFF00FF) + (n185 & 0xFF00FF);
                                        final int n188 = (n187 & 0x1000100) + (n186 - n187 & 0x10000);
                                        array4[n162] = (n186 - n188 | n188 - (n188 >>> 8));
                                    }
                                }
                                else if (n == 0) {
                                    final int n189 = this.anIntArray6213[n161];
                                    if (n189 != 0) {
                                        final int n190 = (((n189 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n189 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n189 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n191 = array4[n162];
                                        final int n192 = n190 + n191;
                                        final int n193 = (n190 & 0xFF00FF) + (n191 & 0xFF00FF);
                                        final int n194 = (n193 & 0x1000100) + (n192 - n193 & 0x10000);
                                        array4[n162] = (n192 - n194 | n194 - (n194 >>> 8));
                                    }
                                }
                                else if (n == 3) {
                                    final int n195 = this.anIntArray6213[n161];
                                    final int anInt5475 = Class332_Sub3.anInt5432;
                                    final int n196 = n195 + anInt5475;
                                    final int n197 = (n195 & 0xFF00FF) + (anInt5475 & 0xFF00FF);
                                    final int n198 = (n197 & 0x1000100) + (n196 - n197 & 0x10000);
                                    final int n199 = n196 - n198 | n198 - (n198 >>> 8);
                                    final int n200 = array4[n162];
                                    final int n201 = n199 + n200;
                                    final int n202 = (n199 & 0xFF00FF) + (n200 & 0xFF00FF);
                                    final int n203 = (n202 & 0x1000100) + (n201 - n202 & 0x10000);
                                    array4[n162] = (n201 - n203 | n203 - (n203 >>> 8));
                                }
                                else if (n == 2) {
                                    final int n204 = this.anIntArray6213[n161];
                                    if (n204 != 0) {
                                        final int n205 = ((((n204 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n204 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                        final int n206 = array4[n162];
                                        final int n207 = n205 + n206;
                                        final int n208 = (n205 & 0xFF00FF) + (n206 & 0xFF00FF);
                                        final int n209 = (n208 & 0x1000100) + (n207 - n208 & 0x10000);
                                        array4[n162] = (n207 - n209 | n209 - (n209 >>> 8));
                                    }
                                }
                            }
                            n157 += Class332_Sub3.anInt5444;
                            ++anInt5472;
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int anInt5476 = Class332_Sub3.anInt5431; anInt5476 < 0; ++anInt5476, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5477 = Class332_Sub3.anInt5451;
                    int n210 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n211 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5478 = Class332_Sub3.anInt5448;
                    final int n212;
                    if ((n212 = n210 - (super.anInt5433 << 12)) >= 0) {
                        final int n213 = (Class332_Sub3.anInt5444 - n212) / Class332_Sub3.anInt5444;
                        anInt5478 += n213;
                        n210 += Class332_Sub3.anInt5444 * n213;
                        n211 += Class332_Sub3.anInt5436 * n213;
                        anInt5477 += n213;
                    }
                    final int n214;
                    if ((n214 = (n210 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5478) {
                        anInt5478 = n214;
                    }
                    final int n215;
                    if ((n215 = n211 - (super.anInt5454 << 12)) >= 0) {
                        final int n216 = (Class332_Sub3.anInt5436 - n215) / Class332_Sub3.anInt5436;
                        anInt5478 += n216;
                        n210 += Class332_Sub3.anInt5444 * n216;
                        n211 += Class332_Sub3.anInt5436 * n216;
                        anInt5477 += n216;
                    }
                    final int n217;
                    if ((n217 = (n211 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5478) {
                        anInt5478 = n217;
                    }
                    while (anInt5478 < 0) {
                        int n218 = (n211 >> 12) * super.anInt5433 + (n210 >> 12);
                        int n219 = anInt5477++;
                        final int[] array5 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array5[n219] = this.anIntArray6213[n218];
                            }
                            else if (n == 0) {
                                final int n220 = this.anIntArray6213[n218++];
                                array5[n219] = (((n220 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n220 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n220 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n221 = this.anIntArray6213[n218++];
                                final int anInt5479 = Class332_Sub3.anInt5432;
                                final int n222 = n221 + anInt5479;
                                final int n223 = (n221 & 0xFF00FF) + (anInt5479 & 0xFF00FF);
                                final int n224 = (n223 & 0x1000100) + (n222 - n223 & 0x10000);
                                array5[n219] = (n222 - n224 | n224 - (n224 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n225 = this.anIntArray6213[n218];
                                array5[n219] = ((((n225 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n225 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final int n226 = this.anIntArray6213[n218];
                                if (n226 != 0) {
                                    array5[n219] = n226;
                                }
                            }
                            else if (n == 0) {
                                final int n227 = this.anIntArray6213[n218];
                                if (n227 != 0) {
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n228 = Class332_Sub3.anInt5432 >>> 24;
                                        final int n229 = 256 - n228;
                                        final int n230 = array5[n219];
                                        array5[n219] = ((n227 & 0xFF00FF) * n228 + (n230 & 0xFF00FF) * n229 & 0xFF00FF00) + ((n227 & 0xFF00) * n228 + (n230 & 0xFF00) * n229 & 0xFF0000) >> 8;
                                    }
                                    else if (Class332_Sub3.anInt5449 != 255) {
                                        final int n231 = (((n227 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n227 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n227 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n232 = array5[n219];
                                        array5[n219] = ((n231 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n232 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n231 & 0xFF00) * Class332_Sub3.anInt5449 + (n232 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array5[n219] = (((n227 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n227 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n227 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                            }
                            else if (n == 3) {
                                final int n233 = this.anIntArray6213[n218];
                                final int anInt5480 = Class332_Sub3.anInt5432;
                                final int n234 = n233 + anInt5480;
                                final int n235 = (n233 & 0xFF00FF) + (anInt5480 & 0xFF00FF);
                                final int n236 = (n235 & 0x1000100) + (n234 - n235 & 0x10000);
                                int n237 = n234 - n236 | n236 - (n236 >>> 8);
                                if (n233 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n238 = n237;
                                    final int n239 = array5[n219];
                                    n237 = ((n238 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n239 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n238 & 0xFF00) * Class332_Sub3.anInt5449 + (n239 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array5[n219] = n237;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n240 = this.anIntArray6213[n218];
                                if (n240 != 0) {
                                    array5[n219++] = ((((n240 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n240 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final int n241 = this.anIntArray6213[n218];
                                if (n241 != 0) {
                                    final int n242 = array5[n219];
                                    final int n243 = n241 + n242;
                                    final int n244 = (n241 & 0xFF00FF) + (n242 & 0xFF00FF);
                                    final int n245 = (n244 & 0x1000100) + (n243 - n244 & 0x10000);
                                    array5[n219] = (n243 - n245 | n245 - (n245 >>> 8));
                                }
                            }
                            else if (n == 0) {
                                final int n246 = this.anIntArray6213[n218];
                                if (n246 != 0) {
                                    final int n247 = (((n246 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n246 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n246 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n248 = array5[n219];
                                    final int n249 = n247 + n248;
                                    final int n250 = (n247 & 0xFF00FF) + (n248 & 0xFF00FF);
                                    final int n251 = (n250 & 0x1000100) + (n249 - n250 & 0x10000);
                                    array5[n219] = (n249 - n251 | n251 - (n251 >>> 8));
                                }
                            }
                            else if (n == 3) {
                                final int n252 = this.anIntArray6213[n218];
                                final int anInt5481 = Class332_Sub3.anInt5432;
                                final int n253 = n252 + anInt5481;
                                final int n254 = (n252 & 0xFF00FF) + (anInt5481 & 0xFF00FF);
                                final int n255 = (n254 & 0x1000100) + (n253 - n254 & 0x10000);
                                final int n256 = n253 - n255 | n255 - (n255 >>> 8);
                                final int n257 = array5[n219];
                                final int n258 = n256 + n257;
                                final int n259 = (n256 & 0xFF00FF) + (n257 & 0xFF00FF);
                                final int n260 = (n259 & 0x1000100) + (n258 - n259 & 0x10000);
                                array5[n219] = (n258 - n260 | n260 - (n260 >>> 8));
                            }
                            else if (n == 2) {
                                final int n261 = this.anIntArray6213[n218];
                                if (n261 != 0) {
                                    final int n262 = ((((n261 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n261 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n263 = array5[n219];
                                    final int n264 = n262 + n263;
                                    final int n265 = (n262 & 0xFF00FF) + (n263 & 0xFF00FF);
                                    final int n266 = (n265 & 0x1000100) + (n264 - n265 & 0x10000);
                                    array5[n219] = (n264 - n266 | n266 - (n266 >>> 8));
                                }
                            }
                        }
                        n210 += Class332_Sub3.anInt5444;
                        n211 += Class332_Sub3.anInt5436;
                        ++anInt5478;
                    }
                }
            }
            else {
                for (int anInt5482 = Class332_Sub3.anInt5431; anInt5482 < 0; ++anInt5482, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    int anInt5483 = Class332_Sub3.anInt5451;
                    int n267 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n268 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5484 = Class332_Sub3.anInt5448;
                    final int n269;
                    if ((n269 = n267 - (super.anInt5433 << 12)) >= 0) {
                        final int n270 = (Class332_Sub3.anInt5444 - n269) / Class332_Sub3.anInt5444;
                        anInt5484 += n270;
                        n267 += Class332_Sub3.anInt5444 * n270;
                        n268 += Class332_Sub3.anInt5436 * n270;
                        anInt5483 += n270;
                    }
                    final int n271;
                    if ((n271 = (n267 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5484) {
                        anInt5484 = n271;
                    }
                    if (n268 < 0) {
                        final int n272 = (Class332_Sub3.anInt5436 - 1 - n268) / Class332_Sub3.anInt5436;
                        anInt5484 += n272;
                        n267 += Class332_Sub3.anInt5444 * n272;
                        n268 += Class332_Sub3.anInt5436 * n272;
                        anInt5483 += n272;
                    }
                    final int n273;
                    if ((n273 = (1 + n268 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5484) {
                        anInt5484 = n273;
                    }
                    while (anInt5484 < 0) {
                        int n274 = (n268 >> 12) * super.anInt5433 + (n267 >> 12);
                        int n275 = anInt5483++;
                        final int[] array6 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array6[n275] = this.anIntArray6213[n274];
                            }
                            else if (n == 0) {
                                final int n276 = this.anIntArray6213[n274++];
                                array6[n275] = (((n276 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n276 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n276 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n277 = this.anIntArray6213[n274++];
                                final int anInt5485 = Class332_Sub3.anInt5432;
                                final int n278 = n277 + anInt5485;
                                final int n279 = (n277 & 0xFF00FF) + (anInt5485 & 0xFF00FF);
                                final int n280 = (n279 & 0x1000100) + (n278 - n279 & 0x10000);
                                array6[n275] = (n278 - n280 | n280 - (n280 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n281 = this.anIntArray6213[n274];
                                array6[n275] = ((((n281 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n281 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final int n282 = this.anIntArray6213[n274];
                                if (n282 != 0) {
                                    array6[n275] = n282;
                                }
                            }
                            else if (n == 0) {
                                final int n283 = this.anIntArray6213[n274];
                                if (n283 != 0) {
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n284 = Class332_Sub3.anInt5432 >>> 24;
                                        final int n285 = 256 - n284;
                                        final int n286 = array6[n275];
                                        array6[n275] = ((n283 & 0xFF00FF) * n284 + (n286 & 0xFF00FF) * n285 & 0xFF00FF00) + ((n283 & 0xFF00) * n284 + (n286 & 0xFF00) * n285 & 0xFF0000) >> 8;
                                    }
                                    else if (Class332_Sub3.anInt5449 != 255) {
                                        final int n287 = (((n283 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n283 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n283 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n288 = array6[n275];
                                        array6[n275] = ((n287 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n288 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n287 & 0xFF00) * Class332_Sub3.anInt5449 + (n288 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array6[n275] = (((n283 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n283 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n283 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                            }
                            else if (n == 3) {
                                final int n289 = this.anIntArray6213[n274];
                                final int anInt5486 = Class332_Sub3.anInt5432;
                                final int n290 = n289 + anInt5486;
                                final int n291 = (n289 & 0xFF00FF) + (anInt5486 & 0xFF00FF);
                                final int n292 = (n291 & 0x1000100) + (n290 - n291 & 0x10000);
                                int n293 = n290 - n292 | n292 - (n292 >>> 8);
                                if (n289 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n294 = n293;
                                    final int n295 = array6[n275];
                                    n293 = ((n294 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n295 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n294 & 0xFF00) * Class332_Sub3.anInt5449 + (n295 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array6[n275] = n293;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n296 = this.anIntArray6213[n274];
                                if (n296 != 0) {
                                    array6[n275++] = ((((n296 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n296 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final int n297 = this.anIntArray6213[n274];
                                if (n297 != 0) {
                                    final int n298 = array6[n275];
                                    final int n299 = n297 + n298;
                                    final int n300 = (n297 & 0xFF00FF) + (n298 & 0xFF00FF);
                                    final int n301 = (n300 & 0x1000100) + (n299 - n300 & 0x10000);
                                    array6[n275] = (n299 - n301 | n301 - (n301 >>> 8));
                                }
                            }
                            else if (n == 0) {
                                final int n302 = this.anIntArray6213[n274];
                                if (n302 != 0) {
                                    final int n303 = (((n302 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n302 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n302 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n304 = array6[n275];
                                    final int n305 = n303 + n304;
                                    final int n306 = (n303 & 0xFF00FF) + (n304 & 0xFF00FF);
                                    final int n307 = (n306 & 0x1000100) + (n305 - n306 & 0x10000);
                                    array6[n275] = (n305 - n307 | n307 - (n307 >>> 8));
                                }
                            }
                            else if (n == 3) {
                                final int n308 = this.anIntArray6213[n274];
                                final int anInt5487 = Class332_Sub3.anInt5432;
                                final int n309 = n308 + anInt5487;
                                final int n310 = (n308 & 0xFF00FF) + (anInt5487 & 0xFF00FF);
                                final int n311 = (n310 & 0x1000100) + (n309 - n310 & 0x10000);
                                final int n312 = n309 - n311 | n311 - (n311 >>> 8);
                                final int n313 = array6[n275];
                                final int n314 = n312 + n313;
                                final int n315 = (n312 & 0xFF00FF) + (n313 & 0xFF00FF);
                                final int n316 = (n315 & 0x1000100) + (n314 - n315 & 0x10000);
                                array6[n275] = (n314 - n316 | n316 - (n316 >>> 8));
                            }
                            else if (n == 2) {
                                final int n317 = this.anIntArray6213[n274];
                                if (n317 != 0) {
                                    final int n318 = ((((n317 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n317 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n319 = array6[n275];
                                    final int n320 = n318 + n319;
                                    final int n321 = (n318 & 0xFF00FF) + (n319 & 0xFF00FF);
                                    final int n322 = (n321 & 0x1000100) + (n320 - n321 & 0x10000);
                                    array6[n275] = (n320 - n322 | n322 - (n322 >>> 8));
                                }
                            }
                        }
                        n267 += Class332_Sub3.anInt5444;
                        n268 += Class332_Sub3.anInt5436;
                        ++anInt5484;
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 == 0) {
            for (int anInt5488 = Class332_Sub3.anInt5431; anInt5488 < 0; ++anInt5488, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                int anInt5489 = Class332_Sub3.anInt5451;
                int n323 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                final int anInt5490 = Class332_Sub3.anInt5443;
                int anInt5491 = Class332_Sub3.anInt5448;
                if (anInt5490 >= 0 && anInt5490 - (super.anInt5454 << 12) < 0) {
                    if (n323 < 0) {
                        final int n324 = (Class332_Sub3.anInt5444 - 1 - n323) / Class332_Sub3.anInt5444;
                        anInt5491 += n324;
                        n323 += Class332_Sub3.anInt5444 * n324;
                        anInt5489 += n324;
                    }
                    final int n325;
                    if ((n325 = (1 + n323 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5491) {
                        anInt5491 = n325;
                    }
                    while (anInt5491 < 0) {
                        int n326 = (anInt5490 >> 12) * super.anInt5433 + (n323 >> 12);
                        int n327 = anInt5489++;
                        final int[] array7 = anIntArray4504;
                        if (n2 == 0) {
                            if (n == 1) {
                                array7[n327] = this.anIntArray6213[n326];
                            }
                            else if (n == 0) {
                                final int n328 = this.anIntArray6213[n326++];
                                array7[n327] = (((n328 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n328 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n328 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                            }
                            else if (n == 3) {
                                final int n329 = this.anIntArray6213[n326++];
                                final int anInt5492 = Class332_Sub3.anInt5432;
                                final int n330 = n329 + anInt5492;
                                final int n331 = (n329 & 0xFF00FF) + (anInt5492 & 0xFF00FF);
                                final int n332 = (n331 & 0x1000100) + (n330 - n331 & 0x10000);
                                array7[n327] = (n330 - n332 | n332 - (n332 >>> 8));
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n333 = this.anIntArray6213[n326];
                                array7[n327] = ((((n333 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n333 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                        else if (n2 == 1) {
                            if (n == 1) {
                                final int n334 = this.anIntArray6213[n326];
                                if (n334 != 0) {
                                    array7[n327] = n334;
                                }
                            }
                            else if (n == 0) {
                                final int n335 = this.anIntArray6213[n326];
                                if (n335 != 0) {
                                    if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                        final int n336 = Class332_Sub3.anInt5432 >>> 24;
                                        final int n337 = 256 - n336;
                                        final int n338 = array7[n327];
                                        array7[n327] = ((n335 & 0xFF00FF) * n336 + (n338 & 0xFF00FF) * n337 & 0xFF00FF00) + ((n335 & 0xFF00) * n336 + (n338 & 0xFF00) * n337 & 0xFF0000) >> 8;
                                    }
                                    else if (Class332_Sub3.anInt5449 != 255) {
                                        final int n339 = (((n335 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n335 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n335 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                        final int n340 = array7[n327];
                                        array7[n327] = ((n339 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n340 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n339 & 0xFF00) * Class332_Sub3.anInt5449 + (n340 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                    }
                                    else {
                                        array7[n327] = (((n335 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n335 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n335 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    }
                                }
                            }
                            else if (n == 3) {
                                final int n341 = this.anIntArray6213[n326];
                                final int anInt5493 = Class332_Sub3.anInt5432;
                                final int n342 = n341 + anInt5493;
                                final int n343 = (n341 & 0xFF00FF) + (anInt5493 & 0xFF00FF);
                                final int n344 = (n343 & 0x1000100) + (n342 - n343 & 0x10000);
                                int n345 = n342 - n344 | n344 - (n344 >>> 8);
                                if (n341 == 0 && Class332_Sub3.anInt5449 != 255) {
                                    final int n346 = n345;
                                    final int n347 = array7[n327];
                                    n345 = ((n346 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n347 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n346 & 0xFF00) * Class332_Sub3.anInt5449 + (n347 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                array7[n327] = n345;
                            }
                            else {
                                if (n != 2) {
                                    throw new IllegalArgumentException();
                                }
                                final int n348 = this.anIntArray6213[n326];
                                if (n348 != 0) {
                                    array7[n327++] = ((((n348 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n348 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                }
                            }
                        }
                        else {
                            if (n2 != 2) {
                                throw new IllegalArgumentException();
                            }
                            if (n == 1) {
                                final int n349 = this.anIntArray6213[n326];
                                if (n349 != 0) {
                                    final int n350 = array7[n327];
                                    final int n351 = n349 + n350;
                                    final int n352 = (n349 & 0xFF00FF) + (n350 & 0xFF00FF);
                                    final int n353 = (n352 & 0x1000100) + (n351 - n352 & 0x10000);
                                    array7[n327] = (n351 - n353 | n353 - (n353 >>> 8));
                                }
                            }
                            else if (n == 0) {
                                final int n354 = this.anIntArray6213[n326];
                                if (n354 != 0) {
                                    final int n355 = (((n354 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n354 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n354 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n356 = array7[n327];
                                    final int n357 = n355 + n356;
                                    final int n358 = (n355 & 0xFF00FF) + (n356 & 0xFF00FF);
                                    final int n359 = (n358 & 0x1000100) + (n357 - n358 & 0x10000);
                                    array7[n327] = (n357 - n359 | n359 - (n359 >>> 8));
                                }
                            }
                            else if (n == 3) {
                                final int n360 = this.anIntArray6213[n326];
                                final int anInt5494 = Class332_Sub3.anInt5432;
                                final int n361 = n360 + anInt5494;
                                final int n362 = (n360 & 0xFF00FF) + (anInt5494 & 0xFF00FF);
                                final int n363 = (n362 & 0x1000100) + (n361 - n362 & 0x10000);
                                final int n364 = n361 - n363 | n363 - (n363 >>> 8);
                                final int n365 = array7[n327];
                                final int n366 = n364 + n365;
                                final int n367 = (n364 & 0xFF00FF) + (n365 & 0xFF00FF);
                                final int n368 = (n367 & 0x1000100) + (n366 - n367 & 0x10000);
                                array7[n327] = (n366 - n368 | n368 - (n368 >>> 8));
                            }
                            else if (n == 2) {
                                final int n369 = this.anIntArray6213[n326];
                                if (n369 != 0) {
                                    final int n370 = ((((n369 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n369 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                    final int n371 = array7[n327];
                                    final int n372 = n370 + n371;
                                    final int n373 = (n370 & 0xFF00FF) + (n371 & 0xFF00FF);
                                    final int n374 = (n373 & 0x1000100) + (n372 - n373 & 0x10000);
                                    array7[n327] = (n372 - n374 | n374 - (n374 >>> 8));
                                }
                            }
                        }
                        n323 += Class332_Sub3.anInt5444;
                        ++anInt5491;
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 < 0) {
            for (int anInt5495 = Class332_Sub3.anInt5431; anInt5495 < 0; ++anInt5495) {
                int anInt5496 = Class332_Sub3.anInt5451;
                int n375 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                int n376 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                int anInt5497 = Class332_Sub3.anInt5448;
                if (n375 < 0) {
                    final int n377 = (Class332_Sub3.anInt5444 - 1 - n375) / Class332_Sub3.anInt5444;
                    anInt5497 += n377;
                    n375 += Class332_Sub3.anInt5444 * n377;
                    n376 += Class332_Sub3.anInt5436 * n377;
                    anInt5496 += n377;
                }
                final int n378;
                if ((n378 = (1 + n375 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5497) {
                    anInt5497 = n378;
                }
                final int n379;
                if ((n379 = n376 - (super.anInt5454 << 12)) >= 0) {
                    final int n380 = (Class332_Sub3.anInt5436 - n379) / Class332_Sub3.anInt5436;
                    anInt5497 += n380;
                    n375 += Class332_Sub3.anInt5444 * n380;
                    n376 += Class332_Sub3.anInt5436 * n380;
                    anInt5496 += n380;
                }
                final int n381;
                if ((n381 = (n376 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5497) {
                    anInt5497 = n381;
                }
                while (anInt5497 < 0) {
                    int n382 = (n376 >> 12) * super.anInt5433 + (n375 >> 12);
                    int n383 = anInt5496++;
                    final int[] array8 = anIntArray4504;
                    if (n2 == 0) {
                        if (n == 1) {
                            array8[n383] = this.anIntArray6213[n382];
                        }
                        else if (n == 0) {
                            final int n384 = this.anIntArray6213[n382++];
                            array8[n383] = (((n384 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n384 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n384 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                        }
                        else if (n == 3) {
                            final int n385 = this.anIntArray6213[n382++];
                            final int anInt5498 = Class332_Sub3.anInt5432;
                            final int n386 = n385 + anInt5498;
                            final int n387 = (n385 & 0xFF00FF) + (anInt5498 & 0xFF00FF);
                            final int n388 = (n387 & 0x1000100) + (n386 - n387 & 0x10000);
                            array8[n383] = (n386 - n388 | n388 - (n388 >>> 8));
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n389 = this.anIntArray6213[n382];
                            array8[n383] = ((((n389 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n389 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                        }
                    }
                    else if (n2 == 1) {
                        if (n == 1) {
                            final int n390 = this.anIntArray6213[n382];
                            if (n390 != 0) {
                                array8[n383] = n390;
                            }
                        }
                        else if (n == 0) {
                            final int n391 = this.anIntArray6213[n382];
                            if (n391 != 0) {
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n392 = Class332_Sub3.anInt5432 >>> 24;
                                    final int n393 = 256 - n392;
                                    final int n394 = array8[n383];
                                    array8[n383] = ((n391 & 0xFF00FF) * n392 + (n394 & 0xFF00FF) * n393 & 0xFF00FF00) + ((n391 & 0xFF00) * n392 + (n394 & 0xFF00) * n393 & 0xFF0000) >> 8;
                                }
                                else if (Class332_Sub3.anInt5449 != 255) {
                                    final int n395 = (((n391 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n391 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n391 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n396 = array8[n383];
                                    array8[n383] = ((n395 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n396 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n395 & 0xFF00) * Class332_Sub3.anInt5449 + (n396 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                else {
                                    array8[n383] = (((n391 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n391 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n391 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                        }
                        else if (n == 3) {
                            final int n397 = this.anIntArray6213[n382];
                            final int anInt5499 = Class332_Sub3.anInt5432;
                            final int n398 = n397 + anInt5499;
                            final int n399 = (n397 & 0xFF00FF) + (anInt5499 & 0xFF00FF);
                            final int n400 = (n399 & 0x1000100) + (n398 - n399 & 0x10000);
                            int n401 = n398 - n400 | n400 - (n400 >>> 8);
                            if (n397 == 0 && Class332_Sub3.anInt5449 != 255) {
                                final int n402 = n401;
                                final int n403 = array8[n383];
                                n401 = ((n402 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n403 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n402 & 0xFF00) * Class332_Sub3.anInt5449 + (n403 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                            }
                            array8[n383] = n401;
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n404 = this.anIntArray6213[n382];
                            if (n404 != 0) {
                                array8[n383++] = ((((n404 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n404 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                    }
                    else {
                        if (n2 != 2) {
                            throw new IllegalArgumentException();
                        }
                        if (n == 1) {
                            final int n405 = this.anIntArray6213[n382];
                            if (n405 != 0) {
                                final int n406 = array8[n383];
                                final int n407 = n405 + n406;
                                final int n408 = (n405 & 0xFF00FF) + (n406 & 0xFF00FF);
                                final int n409 = (n408 & 0x1000100) + (n407 - n408 & 0x10000);
                                array8[n383] = (n407 - n409 | n409 - (n409 >>> 8));
                            }
                        }
                        else if (n == 0) {
                            final int n410 = this.anIntArray6213[n382];
                            if (n410 != 0) {
                                final int n411 = (((n410 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n410 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n410 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n412 = array8[n383];
                                final int n413 = n411 + n412;
                                final int n414 = (n411 & 0xFF00FF) + (n412 & 0xFF00FF);
                                final int n415 = (n414 & 0x1000100) + (n413 - n414 & 0x10000);
                                array8[n383] = (n413 - n415 | n415 - (n415 >>> 8));
                            }
                        }
                        else if (n == 3) {
                            final int n416 = this.anIntArray6213[n382];
                            final int anInt5500 = Class332_Sub3.anInt5432;
                            final int n417 = n416 + anInt5500;
                            final int n418 = (n416 & 0xFF00FF) + (anInt5500 & 0xFF00FF);
                            final int n419 = (n418 & 0x1000100) + (n417 - n418 & 0x10000);
                            final int n420 = n417 - n419 | n419 - (n419 >>> 8);
                            final int n421 = array8[n383];
                            final int n422 = n420 + n421;
                            final int n423 = (n420 & 0xFF00FF) + (n421 & 0xFF00FF);
                            final int n424 = (n423 & 0x1000100) + (n422 - n423 & 0x10000);
                            array8[n383] = (n422 - n424 | n424 - (n424 >>> 8));
                        }
                        else if (n == 2) {
                            final int n425 = this.anIntArray6213[n382];
                            if (n425 != 0) {
                                final int n426 = ((((n425 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n425 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n427 = array8[n383];
                                final int n428 = n426 + n427;
                                final int n429 = (n426 & 0xFF00FF) + (n427 & 0xFF00FF);
                                final int n430 = (n429 & 0x1000100) + (n428 - n429 & 0x10000);
                                array8[n383] = (n428 - n430 | n430 - (n430 >>> 8));
                            }
                        }
                    }
                    n375 += Class332_Sub3.anInt5444;
                    n376 += Class332_Sub3.anInt5436;
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
                int n431 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                int n432 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                int anInt5503 = Class332_Sub3.anInt5448;
                if (n431 < 0) {
                    final int n433 = (Class332_Sub3.anInt5444 - 1 - n431) / Class332_Sub3.anInt5444;
                    anInt5503 += n433;
                    n431 += Class332_Sub3.anInt5444 * n433;
                    n432 += Class332_Sub3.anInt5436 * n433;
                    anInt5502 += n433;
                }
                final int n434;
                if ((n434 = (1 + n431 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5503) {
                    anInt5503 = n434;
                }
                if (n432 < 0) {
                    final int n435 = (Class332_Sub3.anInt5436 - 1 - n432) / Class332_Sub3.anInt5436;
                    anInt5503 += n435;
                    n431 += Class332_Sub3.anInt5444 * n435;
                    n432 += Class332_Sub3.anInt5436 * n435;
                    anInt5502 += n435;
                }
                final int n436;
                if ((n436 = (1 + n432 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5503) {
                    anInt5503 = n436;
                }
                while (anInt5503 < 0) {
                    int n437 = (n432 >> 12) * super.anInt5433 + (n431 >> 12);
                    int n438 = anInt5502++;
                    final int[] array9 = anIntArray4504;
                    if (n2 == 0) {
                        if (n == 1) {
                            array9[n438] = this.anIntArray6213[n437];
                        }
                        else if (n == 0) {
                            final int n439 = this.anIntArray6213[n437++];
                            array9[n438] = (((n439 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n439 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n439 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                        }
                        else if (n == 3) {
                            final int n440 = this.anIntArray6213[n437++];
                            final int anInt5504 = Class332_Sub3.anInt5432;
                            final int n441 = n440 + anInt5504;
                            final int n442 = (n440 & 0xFF00FF) + (anInt5504 & 0xFF00FF);
                            final int n443 = (n442 & 0x1000100) + (n441 - n442 & 0x10000);
                            array9[n438] = (n441 - n443 | n443 - (n443 >>> 8));
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n444 = this.anIntArray6213[n437];
                            array9[n438] = ((((n444 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n444 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                        }
                    }
                    else if (n2 == 1) {
                        if (n == 1) {
                            final int n445 = this.anIntArray6213[n437];
                            if (n445 != 0) {
                                array9[n438] = n445;
                            }
                        }
                        else if (n == 0) {
                            final int n446 = this.anIntArray6213[n437];
                            if (n446 != 0) {
                                if ((Class332_Sub3.anInt5432 & 0xFFFFFF) == 0xFFFFFF) {
                                    final int n447 = Class332_Sub3.anInt5432 >>> 24;
                                    final int n448 = 256 - n447;
                                    final int n449 = array9[n438];
                                    array9[n438] = ((n446 & 0xFF00FF) * n447 + (n449 & 0xFF00FF) * n448 & 0xFF00FF00) + ((n446 & 0xFF00) * n447 + (n449 & 0xFF00) * n448 & 0xFF0000) >> 8;
                                }
                                else if (Class332_Sub3.anInt5449 != 255) {
                                    final int n450 = (((n446 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n446 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n446 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                    final int n451 = array9[n438];
                                    array9[n438] = ((n450 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n451 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n450 & 0xFF00) * Class332_Sub3.anInt5449 + (n451 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                                }
                                else {
                                    array9[n438] = (((n446 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n446 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n446 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                }
                            }
                        }
                        else if (n == 3) {
                            final int n452 = this.anIntArray6213[n437];
                            final int anInt5505 = Class332_Sub3.anInt5432;
                            final int n453 = n452 + anInt5505;
                            final int n454 = (n452 & 0xFF00FF) + (anInt5505 & 0xFF00FF);
                            final int n455 = (n454 & 0x1000100) + (n453 - n454 & 0x10000);
                            int n456 = n453 - n455 | n455 - (n455 >>> 8);
                            if (n452 == 0 && Class332_Sub3.anInt5449 != 255) {
                                final int n457 = n456;
                                final int n458 = array9[n438];
                                n456 = ((n457 & 0xFF00FF) * Class332_Sub3.anInt5449 + (n458 & 0xFF00FF) * Class332_Sub3.anInt5442 & 0xFF00FF00) + ((n457 & 0xFF00) * Class332_Sub3.anInt5449 + (n458 & 0xFF00) * Class332_Sub3.anInt5442 & 0xFF0000) >> 8;
                            }
                            array9[n438] = n456;
                        }
                        else {
                            if (n != 2) {
                                throw new IllegalArgumentException();
                            }
                            final int n459 = this.anIntArray6213[n437];
                            if (n459 != 0) {
                                array9[n438++] = ((((n459 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n459 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                            }
                        }
                    }
                    else {
                        if (n2 != 2) {
                            throw new IllegalArgumentException();
                        }
                        if (n == 1) {
                            final int n460 = this.anIntArray6213[n437];
                            if (n460 != 0) {
                                final int n461 = array9[n438];
                                final int n462 = n460 + n461;
                                final int n463 = (n460 & 0xFF00FF) + (n461 & 0xFF00FF);
                                final int n464 = (n463 & 0x1000100) + (n462 - n463 & 0x10000);
                                array9[n438] = (n462 - n464 | n464 - (n464 >>> 8));
                            }
                        }
                        else if (n == 0) {
                            final int n465 = this.anIntArray6213[n437];
                            if (n465 != 0) {
                                final int n466 = (((n465 & 0xFF0000) * Class332_Sub3.anInt5435 & 0xFF000000) | ((n465 & 0xFF00) * Class332_Sub3.anInt5457 & 0xFF0000) | ((n465 & 0xFF) * Class332_Sub3.anInt5427 & 0xFF00)) >>> 8;
                                final int n467 = array9[n438];
                                final int n468 = n466 + n467;
                                final int n469 = (n466 & 0xFF00FF) + (n467 & 0xFF00FF);
                                final int n470 = (n469 & 0x1000100) + (n468 - n469 & 0x10000);
                                array9[n438] = (n468 - n470 | n470 - (n470 >>> 8));
                            }
                        }
                        else if (n == 3) {
                            final int n471 = this.anIntArray6213[n437];
                            final int anInt5506 = Class332_Sub3.anInt5432;
                            final int n472 = n471 + anInt5506;
                            final int n473 = (n471 & 0xFF00FF) + (anInt5506 & 0xFF00FF);
                            final int n474 = (n473 & 0x1000100) + (n472 - n473 & 0x10000);
                            final int n475 = n472 - n474 | n474 - (n474 >>> 8);
                            final int n476 = array9[n438];
                            final int n477 = n475 + n476;
                            final int n478 = (n475 & 0xFF00FF) + (n476 & 0xFF00FF);
                            final int n479 = (n478 & 0x1000100) + (n477 - n478 & 0x10000);
                            array9[n438] = (n477 - n479 | n479 - (n479 >>> 8));
                        }
                        else if (n == 2) {
                            final int n480 = this.anIntArray6213[n437];
                            if (n480 != 0) {
                                final int n481 = ((((n480 & 0xFF00FF) * Class332_Sub3.anInt5449 & 0xFF00FF00) | ((n480 & 0xFF00) * Class332_Sub3.anInt5449 & 0xFF0000)) >>> 8) + Class332_Sub3.anInt5440;
                                final int n482 = array9[n438];
                                final int n483 = n481 + n482;
                                final int n484 = (n481 & 0xFF00FF) + (n482 & 0xFF00FF);
                                final int n485 = (n484 & 0x1000100) + (n483 - n484 & 0x10000);
                                array9[n438] = (n483 - n485 | n485 - (n485 >>> 8));
                            }
                        }
                    }
                    n431 += Class332_Sub3.anInt5444;
                    n432 += Class332_Sub3.anInt5436;
                    ++anInt5503;
                }
                Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441;
                Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428;
                Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438;
            }
        }
    }
    
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
                    final int n18 = this.anIntArray6213[n3++];
                    if (n18 != 0) {
                        anIntArray3557[n6++] = n18;
                    }
                    else {
                        ++n6;
                    }
                }
                n3 += n17 + n5;
                n6 += n17 + n4;
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
                        anIntArray4504[n17++] = this.anIntArray6213[(n9 >> 16) + n23];
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
                        final int n29 = this.anIntArray6213[(n9 >> 16) + n28];
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
                        final int n34 = this.anIntArray6213[(n9 >> 16) + n32];
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
                        final int n44 = this.anIntArray6213[(n9 >> 16) + n42];
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
                        final int n49 = this.anIntArray6213[(n9 >> 16) + n47];
                        if (n49 != 0) {
                            anIntArray4504[n17++] = n49;
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n45;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n50 = n9;
                if ((n6 & 0xFFFFFF) == 0xFFFFFF) {
                    final int n51 = n6 >>> 24;
                    final int n52 = 256 - n51;
                    for (int n53 = -n4; n53 < 0; ++n53) {
                        final int n54 = (n10 >> 16) * super.anInt5433;
                        for (int n55 = -n3; n55 < 0; ++n55) {
                            final int n56 = this.anIntArray6213[(n9 >> 16) + n54];
                            if (n56 != 0) {
                                final int n57 = anIntArray4504[n17];
                                anIntArray4504[n17++] = ((n56 & 0xFF00FF) * n51 + (n57 & 0xFF00FF) * n52 & 0xFF00FF00) + ((n56 & 0xFF00) * n51 + (n57 & 0xFF00) * n52 & 0xFF0000) >> 8;
                            }
                            else {
                                ++n17;
                            }
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n50;
                        n17 += n18;
                    }
                    return;
                }
                final int n58 = (n6 & 0xFF0000) >> 16;
                final int n59 = (n6 & 0xFF00) >> 8;
                final int n60 = n6 & 0xFF;
                final int n61 = n6 >>> 24;
                final int n62 = 256 - n61;
                for (int n63 = -n4; n63 < 0; ++n63) {
                    final int n64 = (n10 >> 16) * super.anInt5433;
                    for (int n65 = -n3; n65 < 0; ++n65) {
                        final int n66 = this.anIntArray6213[(n9 >> 16) + n64];
                        if (n66 != 0) {
                            if (n61 != 255) {
                                final int n67 = (((n66 & 0xFF0000) * n58 & 0xFF000000) | ((n66 & 0xFF00) * n59 & 0xFF0000) | ((n66 & 0xFF) * n60 & 0xFF00)) >>> 8;
                                final int n68 = anIntArray4504[n17];
                                anIntArray4504[n17++] = ((n67 & 0xFF00FF) * n61 + (n68 & 0xFF00FF) * n62 & 0xFF00FF00) + ((n67 & 0xFF00) * n61 + (n68 & 0xFF00) * n62 & 0xFF0000) >> 8;
                            }
                            else {
                                anIntArray4504[n17++] = (((n66 & 0xFF0000) * n58 & 0xFF000000) | ((n66 & 0xFF00) * n59 & 0xFF0000) | ((n66 & 0xFF) * n60 & 0xFF00)) >>> 8;
                            }
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n50;
                    n17 += n18;
                }
            }
            else {
                if (n5 == 3) {
                    final int n69 = n9;
                    final int n70 = n6 >>> 24;
                    final int n71 = 256 - n70;
                    for (int n72 = -n4; n72 < 0; ++n72) {
                        final int n73 = (n10 >> 16) * super.anInt5433;
                        for (int n74 = -n3; n74 < 0; ++n74) {
                            final int n75 = this.anIntArray6213[(n9 >> 16) + n73];
                            final int n76 = n75 + n6;
                            final int n77 = (n75 & 0xFF00FF) + (n6 & 0xFF00FF);
                            final int n78 = (n77 & 0x1000100) + (n76 - n77 & 0x10000);
                            int n79 = n76 - n78 | n78 - (n78 >>> 8);
                            if (n75 == 0 && n70 != 255) {
                                final int n80 = n79;
                                final int n81 = anIntArray4504[n17];
                                n79 = ((n80 & 0xFF00FF) * n70 + (n81 & 0xFF00FF) * n71 & 0xFF00FF00) + ((n80 & 0xFF00) * n70 + (n81 & 0xFF00) * n71 & 0xFF0000) >> 8;
                            }
                            anIntArray4504[n17++] = n79;
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n69;
                        n17 += n18;
                    }
                    return;
                }
                if (n5 == 2) {
                    final int n82 = n6 >>> 24;
                    final int n83 = 256 - n82;
                    n6 = (((n6 & 0xFF00FF) * n83 & 0xFF00FF00) | ((n6 & 0xFF00) * n83 & 0xFF0000)) >>> 8;
                    final int n84 = n9;
                    for (int n85 = -n4; n85 < 0; ++n85) {
                        final int n86 = (n10 >> 16) * super.anInt5433;
                        for (int n87 = -n3; n87 < 0; ++n87) {
                            final int n88 = this.anIntArray6213[(n9 >> 16) + n86];
                            if (n88 != 0) {
                                anIntArray4504[n17++] = ((((n88 & 0xFF00FF) * n82 & 0xFF00FF00) | ((n88 & 0xFF00) * n82 & 0xFF0000)) >>> 8) + n6;
                            }
                            else {
                                ++n17;
                            }
                            n9 += n13;
                        }
                        n10 += n14;
                        n9 = n84;
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
                final int n89 = n9;
                for (int n90 = -n4; n90 < 0; ++n90) {
                    final int n91 = (n10 >> 16) * super.anInt5433;
                    for (int n92 = -n3; n92 < 0; ++n92) {
                        final int n93 = this.anIntArray6213[(n9 >> 16) + n91];
                        if (n93 != 0) {
                            final int n94 = anIntArray4504[n17];
                            final int n95 = n93 + n94;
                            final int n96 = (n93 & 0xFF00FF) + (n94 & 0xFF00FF);
                            final int n97 = (n96 & 0x1000100) + (n95 - n96 & 0x10000);
                            anIntArray4504[n17++] = (n95 - n97 | n97 - (n97 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n89;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 0) {
                final int n98 = n9;
                final int n99 = (n6 & 0xFF0000) >> 16;
                final int n100 = (n6 & 0xFF00) >> 8;
                final int n101 = n6 & 0xFF;
                for (int n102 = -n4; n102 < 0; ++n102) {
                    final int n103 = (n10 >> 16) * super.anInt5433;
                    for (int n104 = -n3; n104 < 0; ++n104) {
                        final int n105 = this.anIntArray6213[(n9 >> 16) + n103];
                        if (n105 != 0) {
                            final int n106 = (((n105 & 0xFF0000) * n99 & 0xFF000000) | ((n105 & 0xFF00) * n100 & 0xFF0000) | ((n105 & 0xFF) * n101 & 0xFF00)) >>> 8;
                            final int n107 = anIntArray4504[n17];
                            final int n108 = n106 + n107;
                            final int n109 = (n106 & 0xFF00FF) + (n107 & 0xFF00FF);
                            final int n110 = (n109 & 0x1000100) + (n108 - n109 & 0x10000);
                            anIntArray4504[n17++] = (n108 - n110 | n110 - (n110 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n98;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 3) {
                final int n111 = n9;
                for (int n112 = -n4; n112 < 0; ++n112) {
                    final int n113 = (n10 >> 16) * super.anInt5433;
                    for (int n114 = -n3; n114 < 0; ++n114) {
                        final int n115 = this.anIntArray6213[(n9 >> 16) + n113];
                        final int n116 = n115 + n6;
                        final int n117 = (n115 & 0xFF00FF) + (n6 & 0xFF00FF);
                        final int n118 = (n117 & 0x1000100) + (n116 - n117 & 0x10000);
                        final int n119 = n116 - n118 | n118 - (n118 >>> 8);
                        final int n120 = anIntArray4504[n17];
                        final int n121 = n119 + n120;
                        final int n122 = (n119 & 0xFF00FF) + (n120 & 0xFF00FF);
                        final int n123 = (n122 & 0x1000100) + (n121 - n122 & 0x10000);
                        anIntArray4504[n17++] = (n121 - n123 | n123 - (n123 >>> 8));
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n111;
                    n17 += n18;
                }
                return;
            }
            if (n5 == 2) {
                final int n124 = n6 >>> 24;
                final int n125 = 256 - n124;
                n6 = (((n6 & 0xFF00FF) * n125 & 0xFF00FF00) | ((n6 & 0xFF00) * n125 & 0xFF0000)) >>> 8;
                final int n126 = n9;
                for (int n127 = -n4; n127 < 0; ++n127) {
                    final int n128 = (n10 >> 16) * super.anInt5433;
                    for (int n129 = -n3; n129 < 0; ++n129) {
                        final int n130 = this.anIntArray6213[(n9 >> 16) + n128];
                        if (n130 != 0) {
                            final int n131 = ((((n130 & 0xFF00FF) * n124 & 0xFF00FF00) | ((n130 & 0xFF00) * n124 & 0xFF0000)) >>> 8) + n6;
                            final int n132 = anIntArray4504[n17];
                            final int n133 = n131 + n132;
                            final int n134 = (n131 & 0xFF00FF) + (n132 & 0xFF00FF);
                            final int n135 = (n134 & 0x1000100) + (n133 - n134 & 0x10000);
                            anIntArray4504[n17++] = (n133 - n135 | n135 - (n135 >>> 8));
                        }
                        else {
                            ++n17;
                        }
                        n9 += n13;
                    }
                    n10 += n14;
                    n9 = n126;
                    n17 += n18;
                }
                return;
            }
            throw new IllegalArgumentException();
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
                            anIntArray4504[n18] = this.anIntArray6213[(n10 >> 16) + n24];
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
                            final int n30 = this.anIntArray6213[(n10 >> 16) + n29];
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
                            final int n35 = this.anIntArray6213[(n10 >> 16) + n33];
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
                            final int n45 = this.anIntArray6213[(n10 >> 16) + n43];
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
                            final int n50 = this.anIntArray6213[(n10 >> 16) + n48];
                            if (n50 != 0) {
                                anIntArray4504[n18] = n50;
                                aFloatArray4487[n18] = n3;
                            }
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
                final int n51 = n10;
                if ((n7 & 0xFFFFFF) == 0xFFFFFF) {
                    final int n52 = n7 >>> 24;
                    final int n53 = 256 - n52;
                    for (int n54 = -n5; n54 < 0; ++n54) {
                        final int n55 = (n11 >> 16) * super.anInt5433;
                        for (int n56 = -n4; n56 < 0; ++n56) {
                            if (n3 < aFloatArray4487[n18]) {
                                final int n57 = this.anIntArray6213[(n10 >> 16) + n55];
                                if (n57 != 0) {
                                    final int n58 = anIntArray4504[n18];
                                    anIntArray4504[n18] = ((n57 & 0xFF00FF) * n52 + (n58 & 0xFF00FF) * n53 & 0xFF00FF00) + ((n57 & 0xFF00) * n52 + (n58 & 0xFF00) * n53 & 0xFF0000) >> 8;
                                    aFloatArray4487[n18] = n3;
                                }
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n51;
                        n18 += n19;
                    }
                    return;
                }
                final int n59 = (n7 & 0xFF0000) >> 16;
                final int n60 = (n7 & 0xFF00) >> 8;
                final int n61 = n7 & 0xFF;
                final int n62 = n7 >>> 24;
                final int n63 = 256 - n62;
                for (int n64 = -n5; n64 < 0; ++n64) {
                    final int n65 = (n11 >> 16) * super.anInt5433;
                    for (int n66 = -n4; n66 < 0; ++n66) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n67 = this.anIntArray6213[(n10 >> 16) + n65];
                            if (n67 != 0) {
                                if (n62 != 255) {
                                    final int n68 = (((n67 & 0xFF0000) * n59 & 0xFF000000) | ((n67 & 0xFF00) * n60 & 0xFF0000) | ((n67 & 0xFF) * n61 & 0xFF00)) >>> 8;
                                    final int n69 = anIntArray4504[n18];
                                    anIntArray4504[n18] = ((n68 & 0xFF00FF) * n62 + (n69 & 0xFF00FF) * n63 & 0xFF00FF00) + ((n68 & 0xFF00) * n62 + (n69 & 0xFF00) * n63 & 0xFF0000) >> 8;
                                    aFloatArray4487[n18] = n3;
                                }
                                else {
                                    anIntArray4504[n18] = (((n67 & 0xFF0000) * n59 & 0xFF000000) | ((n67 & 0xFF00) * n60 & 0xFF0000) | ((n67 & 0xFF) * n61 & 0xFF00)) >>> 8;
                                    aFloatArray4487[n18] = n3;
                                }
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n51;
                    n18 += n19;
                }
            }
            else {
                if (n6 == 3) {
                    final int n70 = n10;
                    final int n71 = n7 >>> 24;
                    final int n72 = 256 - n71;
                    for (int n73 = -n5; n73 < 0; ++n73) {
                        final int n74 = (n11 >> 16) * super.anInt5433;
                        for (int n75 = -n4; n75 < 0; ++n75) {
                            if (n3 < aFloatArray4487[n18]) {
                                final int n76 = this.anIntArray6213[(n10 >> 16) + n74];
                                final int n77 = n76 + n7;
                                final int n78 = (n76 & 0xFF00FF) + (n7 & 0xFF00FF);
                                final int n79 = (n78 & 0x1000100) + (n77 - n78 & 0x10000);
                                int n80 = n77 - n79 | n79 - (n79 >>> 8);
                                if (n76 == 0 && n71 != 255) {
                                    final int n81 = n80;
                                    final int n82 = anIntArray4504[n18];
                                    n80 = ((n81 & 0xFF00FF) * n71 + (n82 & 0xFF00FF) * n72 & 0xFF00FF00) + ((n81 & 0xFF00) * n71 + (n82 & 0xFF00) * n72 & 0xFF0000) >> 8;
                                }
                                anIntArray4504[n18] = n80;
                                aFloatArray4487[n18] = n3;
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n70;
                        n18 += n19;
                    }
                    return;
                }
                if (n6 == 2) {
                    final int n83 = n7 >>> 24;
                    final int n84 = 256 - n83;
                    n7 = (((n7 & 0xFF00FF) * n84 & 0xFF00FF00) | ((n7 & 0xFF00) * n84 & 0xFF0000)) >>> 8;
                    final int n85 = n10;
                    for (int n86 = -n5; n86 < 0; ++n86) {
                        final int n87 = (n11 >> 16) * super.anInt5433;
                        for (int n88 = -n4; n88 < 0; ++n88) {
                            if (n3 < aFloatArray4487[n18]) {
                                final int n89 = this.anIntArray6213[(n10 >> 16) + n87];
                                if (n89 != 0) {
                                    anIntArray4504[n18] = ((((n89 & 0xFF00FF) * n83 & 0xFF00FF00) | ((n89 & 0xFF00) * n83 & 0xFF0000)) >>> 8) + n7;
                                    aFloatArray4487[n18] = n3;
                                }
                            }
                            n10 += n14;
                            ++n18;
                        }
                        n11 += n15;
                        n10 = n85;
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
                final int n90 = n10;
                for (int n91 = -n5; n91 < 0; ++n91) {
                    final int n92 = (n11 >> 16) * super.anInt5433;
                    for (int n93 = -n4; n93 < 0; ++n93) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n94 = this.anIntArray6213[(n10 >> 16) + n92];
                            if (n94 != 0) {
                                final int n95 = anIntArray4504[n18];
                                final int n96 = n94 + n95;
                                final int n97 = (n94 & 0xFF00FF) + (n95 & 0xFF00FF);
                                final int n98 = (n97 & 0x1000100) + (n96 - n97 & 0x10000);
                                anIntArray4504[n18] = (n96 - n98 | n98 - (n98 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n90;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 0) {
                final int n99 = n10;
                final int n100 = (n7 & 0xFF0000) >> 16;
                final int n101 = (n7 & 0xFF00) >> 8;
                final int n102 = n7 & 0xFF;
                for (int n103 = -n5; n103 < 0; ++n103) {
                    final int n104 = (n11 >> 16) * super.anInt5433;
                    for (int n105 = -n4; n105 < 0; ++n105) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n106 = this.anIntArray6213[(n10 >> 16) + n104];
                            if (n106 != 0) {
                                final int n107 = (((n106 & 0xFF0000) * n100 & 0xFF000000) | ((n106 & 0xFF00) * n101 & 0xFF0000) | ((n106 & 0xFF) * n102 & 0xFF00)) >>> 8;
                                final int n108 = anIntArray4504[n18];
                                final int n109 = n107 + n108;
                                final int n110 = (n107 & 0xFF00FF) + (n108 & 0xFF00FF);
                                final int n111 = (n110 & 0x1000100) + (n109 - n110 & 0x10000);
                                anIntArray4504[n18] = (n109 - n111 | n111 - (n111 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n99;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 3) {
                final int n112 = n10;
                for (int n113 = -n5; n113 < 0; ++n113) {
                    final int n114 = (n11 >> 16) * super.anInt5433;
                    for (int n115 = -n4; n115 < 0; ++n115) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n116 = this.anIntArray6213[(n10 >> 16) + n114];
                            final int n117 = n116 + n7;
                            final int n118 = (n116 & 0xFF00FF) + (n7 & 0xFF00FF);
                            final int n119 = (n118 & 0x1000100) + (n117 - n118 & 0x10000);
                            final int n120 = n117 - n119 | n119 - (n119 >>> 8);
                            final int n121 = anIntArray4504[n18];
                            final int n122 = n120 + n121;
                            final int n123 = (n120 & 0xFF00FF) + (n121 & 0xFF00FF);
                            final int n124 = (n123 & 0x1000100) + (n122 - n123 & 0x10000);
                            anIntArray4504[n18] = (n122 - n124 | n124 - (n124 >>> 8));
                            aFloatArray4487[n18] = n3;
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n112;
                    n18 += n19;
                }
                return;
            }
            if (n6 == 2) {
                final int n125 = n7 >>> 24;
                final int n126 = 256 - n125;
                n7 = (((n7 & 0xFF00FF) * n126 & 0xFF00FF00) | ((n7 & 0xFF00) * n126 & 0xFF0000)) >>> 8;
                final int n127 = n10;
                for (int n128 = -n5; n128 < 0; ++n128) {
                    final int n129 = (n11 >> 16) * super.anInt5433;
                    for (int n130 = -n4; n130 < 0; ++n130) {
                        if (n3 < aFloatArray4487[n18]) {
                            final int n131 = this.anIntArray6213[(n10 >> 16) + n129];
                            if (n131 != 0) {
                                final int n132 = ((((n131 & 0xFF00FF) * n125 & 0xFF00FF00) | ((n131 & 0xFF00) * n125 & 0xFF0000)) >>> 8) + n7;
                                final int n133 = anIntArray4504[n18];
                                final int n134 = n132 + n133;
                                final int n135 = (n132 & 0xFF00FF) + (n133 & 0xFF00FF);
                                final int n136 = (n135 & 0x1000100) + (n134 - n135 & 0x10000);
                                anIntArray4504[n18] = (n134 - n136 | n136 - (n136 >>> 8));
                                aFloatArray4487[n18] = n3;
                            }
                        }
                        n10 += n14;
                        ++n18;
                    }
                    n11 += n15;
                    n10 = n127;
                    n18 += n19;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
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
                                final int n7 = this.anIntArray6213[(anInt5453 >> 12) * super.anInt5433 + (anInt5452 >> 12)];
                                if (n7 != 0) {
                                    anIntArray4504[anInt5451++] = n7;
                                }
                                else {
                                    ++anInt5451;
                                }
                                ++j;
                            }
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int k = Class332_Sub3.anInt5431; k < 0; ++k, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n8 = k + n2;
                    if (n8 >= 0) {
                        if (n8 >= array.length) {
                            break;
                        }
                        int anInt5454 = Class332_Sub3.anInt5451;
                        int anInt5455 = Class332_Sub3.anInt5450;
                        int n9 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int l = Class332_Sub3.anInt5448;
                        if (anInt5455 >= 0 && anInt5455 - (super.anInt5433 << 12) < 0) {
                            final int n10;
                            if ((n10 = n9 - (super.anInt5454 << 12)) >= 0) {
                                final int n11 = (Class332_Sub3.anInt5436 - n10) / Class332_Sub3.anInt5436;
                                l += n11;
                                n9 += Class332_Sub3.anInt5436 * n11;
                                anInt5454 += n11;
                            }
                            final int n12;
                            if ((n12 = (n9 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > l) {
                                l = n12;
                            }
                            final int n13 = array[n8] - n;
                            int n14 = -array2[n8];
                            final int n15 = n13 - (anInt5454 - Class332_Sub3.anInt5451);
                            if (n15 > 0) {
                                anInt5454 += n15;
                                l += n15;
                                anInt5455 += Class332_Sub3.anInt5444 * n15;
                                n9 += Class332_Sub3.anInt5436 * n15;
                            }
                            else {
                                n14 -= n15;
                            }
                            if (l < n14) {
                                l = n14;
                            }
                            while (l < 0) {
                                final int n16 = this.anIntArray6213[(n9 >> 12) * super.anInt5433 + (anInt5455 >> 12)];
                                if (n16 != 0) {
                                    anIntArray4504[anInt5454++] = n16;
                                }
                                else {
                                    ++anInt5454;
                                }
                                n9 += Class332_Sub3.anInt5436;
                                ++l;
                            }
                        }
                    }
                }
            }
            else {
                for (int anInt5456 = Class332_Sub3.anInt5431; anInt5456 < 0; ++anInt5456, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n17 = anInt5456 + n2;
                    if (n17 >= 0) {
                        if (n17 >= array.length) {
                            break;
                        }
                        int anInt5457 = Class332_Sub3.anInt5451;
                        int anInt5458 = Class332_Sub3.anInt5450;
                        int n18 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5459 = Class332_Sub3.anInt5448;
                        if (anInt5458 >= 0 && anInt5458 - (super.anInt5433 << 12) < 0) {
                            if (n18 < 0) {
                                final int n19 = (Class332_Sub3.anInt5436 - 1 - n18) / Class332_Sub3.anInt5436;
                                anInt5459 += n19;
                                n18 += Class332_Sub3.anInt5436 * n19;
                                anInt5457 += n19;
                            }
                            final int n20;
                            if ((n20 = (1 + n18 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5459) {
                                anInt5459 = n20;
                            }
                            final int n21 = array[n17] - n;
                            int n22 = -array2[n17];
                            final int n23 = n21 - (anInt5457 - Class332_Sub3.anInt5451);
                            if (n23 > 0) {
                                anInt5457 += n23;
                                anInt5459 += n23;
                                anInt5458 += Class332_Sub3.anInt5444 * n23;
                                n18 += Class332_Sub3.anInt5436 * n23;
                            }
                            else {
                                n22 -= n23;
                            }
                            if (anInt5459 < n22) {
                                anInt5459 = n22;
                            }
                            while (anInt5459 < 0) {
                                final int n24 = this.anIntArray6213[(n18 >> 12) * super.anInt5433 + (anInt5458 >> 12)];
                                if (n24 != 0) {
                                    anIntArray4504[anInt5457++] = n24;
                                }
                                else {
                                    ++anInt5457;
                                }
                                n18 += Class332_Sub3.anInt5436;
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
                    final int n25 = anInt5460 + n2;
                    if (n25 >= 0) {
                        if (n25 >= array.length) {
                            break;
                        }
                        int anInt5461 = Class332_Sub3.anInt5451;
                        int n26 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int anInt5462 = Class332_Sub3.anInt5443;
                        int anInt5463 = Class332_Sub3.anInt5448;
                        if (anInt5462 >= 0 && anInt5462 - (super.anInt5454 << 12) < 0) {
                            final int n27;
                            if ((n27 = n26 - (super.anInt5433 << 12)) >= 0) {
                                final int n28 = (Class332_Sub3.anInt5444 - n27) / Class332_Sub3.anInt5444;
                                anInt5463 += n28;
                                n26 += Class332_Sub3.anInt5444 * n28;
                                anInt5461 += n28;
                            }
                            final int n29;
                            if ((n29 = (n26 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5463) {
                                anInt5463 = n29;
                            }
                            final int n30 = array[n25] - n;
                            int n31 = -array2[n25];
                            final int n32 = n30 - (anInt5461 - Class332_Sub3.anInt5451);
                            if (n32 > 0) {
                                anInt5461 += n32;
                                anInt5463 += n32;
                                n26 += Class332_Sub3.anInt5444 * n32;
                                anInt5462 += Class332_Sub3.anInt5436 * n32;
                            }
                            else {
                                n31 -= n32;
                            }
                            if (anInt5463 < n31) {
                                anInt5463 = n31;
                            }
                            while (anInt5463 < 0) {
                                final int n33 = this.anIntArray6213[(anInt5462 >> 12) * super.anInt5433 + (n26 >> 12)];
                                if (n33 != 0) {
                                    anIntArray4504[anInt5461++] = n33;
                                }
                                else {
                                    ++anInt5461;
                                }
                                n26 += Class332_Sub3.anInt5444;
                                ++anInt5463;
                            }
                        }
                    }
                }
            }
            else if (Class332_Sub3.anInt5436 < 0) {
                for (int anInt5464 = Class332_Sub3.anInt5431; anInt5464 < 0; ++anInt5464, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n34 = anInt5464 + n2;
                    if (n34 >= 0) {
                        if (n34 >= array.length) {
                            break;
                        }
                        int anInt5465 = Class332_Sub3.anInt5451;
                        int n35 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int n36 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5466 = Class332_Sub3.anInt5448;
                        final int n37;
                        if ((n37 = n35 - (super.anInt5433 << 12)) >= 0) {
                            final int n38 = (Class332_Sub3.anInt5444 - n37) / Class332_Sub3.anInt5444;
                            anInt5466 += n38;
                            n35 += Class332_Sub3.anInt5444 * n38;
                            n36 += Class332_Sub3.anInt5436 * n38;
                            anInt5465 += n38;
                        }
                        final int n39;
                        if ((n39 = (n35 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5466) {
                            anInt5466 = n39;
                        }
                        final int n40;
                        if ((n40 = n36 - (super.anInt5454 << 12)) >= 0) {
                            final int n41 = (Class332_Sub3.anInt5436 - n40) / Class332_Sub3.anInt5436;
                            anInt5466 += n41;
                            n35 += Class332_Sub3.anInt5444 * n41;
                            n36 += Class332_Sub3.anInt5436 * n41;
                            anInt5465 += n41;
                        }
                        final int n42;
                        if ((n42 = (n36 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5466) {
                            anInt5466 = n42;
                        }
                        final int n43 = array[n34] - n;
                        int n44 = -array2[n34];
                        final int n45 = n43 - (anInt5465 - Class332_Sub3.anInt5451);
                        if (n45 > 0) {
                            anInt5465 += n45;
                            anInt5466 += n45;
                            n35 += Class332_Sub3.anInt5444 * n45;
                            n36 += Class332_Sub3.anInt5436 * n45;
                        }
                        else {
                            n44 -= n45;
                        }
                        if (anInt5466 < n44) {
                            anInt5466 = n44;
                        }
                        while (anInt5466 < 0) {
                            final int n46 = this.anIntArray6213[(n36 >> 12) * super.anInt5433 + (n35 >> 12)];
                            if (n46 != 0) {
                                anIntArray4504[anInt5465++] = n46;
                            }
                            else {
                                ++anInt5465;
                            }
                            n35 += Class332_Sub3.anInt5444;
                            n36 += Class332_Sub3.anInt5436;
                            ++anInt5466;
                        }
                    }
                }
            }
            else {
                for (int anInt5467 = Class332_Sub3.anInt5431; anInt5467 < 0; ++anInt5467, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                    final int n47 = anInt5467 + n2;
                    if (n47 >= 0) {
                        if (n47 >= array.length) {
                            break;
                        }
                        int anInt5468 = Class332_Sub3.anInt5451;
                        int n48 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                        int n49 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                        int anInt5469 = Class332_Sub3.anInt5448;
                        final int n50;
                        if ((n50 = n48 - (super.anInt5433 << 12)) >= 0) {
                            final int n51 = (Class332_Sub3.anInt5444 - n50) / Class332_Sub3.anInt5444;
                            anInt5469 += n51;
                            n48 += Class332_Sub3.anInt5444 * n51;
                            n49 += Class332_Sub3.anInt5436 * n51;
                            anInt5468 += n51;
                        }
                        final int n52;
                        if ((n52 = (n48 - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5469) {
                            anInt5469 = n52;
                        }
                        if (n49 < 0) {
                            final int n53 = (Class332_Sub3.anInt5436 - 1 - n49) / Class332_Sub3.anInt5436;
                            anInt5469 += n53;
                            n48 += Class332_Sub3.anInt5444 * n53;
                            n49 += Class332_Sub3.anInt5436 * n53;
                            anInt5468 += n53;
                        }
                        final int n54;
                        if ((n54 = (1 + n49 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5469) {
                            anInt5469 = n54;
                        }
                        final int n55 = array[n47] - n;
                        int n56 = -array2[n47];
                        final int n57 = n55 - (anInt5468 - Class332_Sub3.anInt5451);
                        if (n57 > 0) {
                            anInt5468 += n57;
                            anInt5469 += n57;
                            n48 += Class332_Sub3.anInt5444 * n57;
                            n49 += Class332_Sub3.anInt5436 * n57;
                        }
                        else {
                            n56 -= n57;
                        }
                        if (anInt5469 < n56) {
                            anInt5469 = n56;
                        }
                        while (anInt5469 < 0) {
                            final int n58 = this.anIntArray6213[(n49 >> 12) * super.anInt5433 + (n48 >> 12)];
                            if (n58 != 0) {
                                anIntArray4504[anInt5468++] = n58;
                            }
                            else {
                                ++anInt5468;
                            }
                            n48 += Class332_Sub3.anInt5444;
                            n49 += Class332_Sub3.anInt5436;
                            ++anInt5469;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 == 0) {
            for (int anInt5470 = Class332_Sub3.anInt5431; anInt5470 < 0; ++anInt5470, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n59 = anInt5470 + n2;
                if (n59 >= 0) {
                    if (n59 >= array.length) {
                        break;
                    }
                    int anInt5471 = Class332_Sub3.anInt5451;
                    int n60 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int anInt5472 = Class332_Sub3.anInt5443;
                    int anInt5473 = Class332_Sub3.anInt5448;
                    if (anInt5472 >= 0 && anInt5472 - (super.anInt5454 << 12) < 0) {
                        if (n60 < 0) {
                            final int n61 = (Class332_Sub3.anInt5444 - 1 - n60) / Class332_Sub3.anInt5444;
                            anInt5473 += n61;
                            n60 += Class332_Sub3.anInt5444 * n61;
                            anInt5471 += n61;
                        }
                        final int n62;
                        if ((n62 = (1 + n60 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5473) {
                            anInt5473 = n62;
                        }
                        final int n63 = array[n59] - n;
                        int n64 = -array2[n59];
                        final int n65 = n63 - (anInt5471 - Class332_Sub3.anInt5451);
                        if (n65 > 0) {
                            anInt5471 += n65;
                            anInt5473 += n65;
                            n60 += Class332_Sub3.anInt5444 * n65;
                            anInt5472 += Class332_Sub3.anInt5436 * n65;
                        }
                        else {
                            n64 -= n65;
                        }
                        if (anInt5473 < n64) {
                            anInt5473 = n64;
                        }
                        while (anInt5473 < 0) {
                            final int n66 = this.anIntArray6213[(anInt5472 >> 12) * super.anInt5433 + (n60 >> 12)];
                            if (n66 != 0) {
                                anIntArray4504[anInt5471++] = n66;
                            }
                            else {
                                ++anInt5471;
                            }
                            n60 += Class332_Sub3.anInt5444;
                            ++anInt5473;
                        }
                    }
                }
            }
        }
        else if (Class332_Sub3.anInt5436 < 0) {
            for (int anInt5474 = Class332_Sub3.anInt5431; anInt5474 < 0; ++anInt5474, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n67 = anInt5474 + n2;
                if (n67 >= 0) {
                    if (n67 >= array.length) {
                        break;
                    }
                    int anInt5475 = Class332_Sub3.anInt5451;
                    int n68 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n69 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5476 = Class332_Sub3.anInt5448;
                    if (n68 < 0) {
                        final int n70 = (Class332_Sub3.anInt5444 - 1 - n68) / Class332_Sub3.anInt5444;
                        anInt5476 += n70;
                        n68 += Class332_Sub3.anInt5444 * n70;
                        n69 += Class332_Sub3.anInt5436 * n70;
                        anInt5475 += n70;
                    }
                    final int n71;
                    if ((n71 = (1 + n68 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5476) {
                        anInt5476 = n71;
                    }
                    final int n72;
                    if ((n72 = n69 - (super.anInt5454 << 12)) >= 0) {
                        final int n73 = (Class332_Sub3.anInt5436 - n72) / Class332_Sub3.anInt5436;
                        anInt5476 += n73;
                        n68 += Class332_Sub3.anInt5444 * n73;
                        n69 += Class332_Sub3.anInt5436 * n73;
                        anInt5475 += n73;
                    }
                    final int n74;
                    if ((n74 = (n69 - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5476) {
                        anInt5476 = n74;
                    }
                    final int n75 = array[n67] - n;
                    int n76 = -array2[n67];
                    final int n77 = n75 - (anInt5475 - Class332_Sub3.anInt5451);
                    if (n77 > 0) {
                        anInt5475 += n77;
                        anInt5476 += n77;
                        n68 += Class332_Sub3.anInt5444 * n77;
                        n69 += Class332_Sub3.anInt5436 * n77;
                    }
                    else {
                        n76 -= n77;
                    }
                    if (anInt5476 < n76) {
                        anInt5476 = n76;
                    }
                    while (anInt5476 < 0) {
                        final int n78 = this.anIntArray6213[(n69 >> 12) * super.anInt5433 + (n68 >> 12)];
                        if (n78 != 0) {
                            anIntArray4504[anInt5475++] = n78;
                        }
                        else {
                            ++anInt5475;
                        }
                        n68 += Class332_Sub3.anInt5444;
                        n69 += Class332_Sub3.anInt5436;
                        ++anInt5476;
                    }
                }
            }
        }
        else {
            for (int anInt5477 = Class332_Sub3.anInt5431; anInt5477 < 0; ++anInt5477, Class332_Sub3.anInt5450 += Class332_Sub3.anInt5441, Class332_Sub3.anInt5443 += Class332_Sub3.anInt5428, Class332_Sub3.anInt5451 += Class332_Sub3.anInt5438) {
                final int n79 = anInt5477 + n2;
                if (n79 >= 0) {
                    if (n79 >= array.length) {
                        break;
                    }
                    int anInt5478 = Class332_Sub3.anInt5451;
                    int n80 = Class332_Sub3.anInt5450 + Class332_Sub3.anInt5425;
                    int n81 = Class332_Sub3.anInt5443 + Class332_Sub3.anInt5456;
                    int anInt5479 = Class332_Sub3.anInt5448;
                    if (n80 < 0) {
                        final int n82 = (Class332_Sub3.anInt5444 - 1 - n80) / Class332_Sub3.anInt5444;
                        anInt5479 += n82;
                        n80 += Class332_Sub3.anInt5444 * n82;
                        n81 += Class332_Sub3.anInt5436 * n82;
                        anInt5478 += n82;
                    }
                    final int n83;
                    if ((n83 = (1 + n80 - (super.anInt5433 << 12) - Class332_Sub3.anInt5444) / Class332_Sub3.anInt5444) > anInt5479) {
                        anInt5479 = n83;
                    }
                    if (n81 < 0) {
                        final int n84 = (Class332_Sub3.anInt5436 - 1 - n81) / Class332_Sub3.anInt5436;
                        anInt5479 += n84;
                        n80 += Class332_Sub3.anInt5444 * n84;
                        n81 += Class332_Sub3.anInt5436 * n84;
                        anInt5478 += n84;
                    }
                    final int n85;
                    if ((n85 = (1 + n81 - (super.anInt5454 << 12) - Class332_Sub3.anInt5436) / Class332_Sub3.anInt5436) > anInt5479) {
                        anInt5479 = n85;
                    }
                    final int n86 = array[n79] - n;
                    int n87 = -array2[n79];
                    final int n88 = n86 - (anInt5478 - Class332_Sub3.anInt5451);
                    if (n88 > 0) {
                        anInt5478 += n88;
                        anInt5479 += n88;
                        n80 += Class332_Sub3.anInt5444 * n88;
                        n81 += Class332_Sub3.anInt5436 * n88;
                    }
                    else {
                        n87 -= n88;
                    }
                    if (anInt5479 < n87) {
                        anInt5479 = n87;
                    }
                    while (anInt5479 < 0) {
                        final int n89 = this.anIntArray6213[(n81 >> 12) * super.anInt5433 + (n80 >> 12)];
                        if (n89 != 0) {
                            anIntArray4504[anInt5478++] = n89;
                        }
                        else {
                            ++anInt5478;
                        }
                        n80 += Class332_Sub3.anInt5444;
                        n81 += Class332_Sub3.anInt5436;
                        ++anInt5479;
                    }
                }
            }
        }
    }
    
    @Override
    final void method3736(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int[] anIntArray4504 = super.aHa_Sub2_5434.anIntArray4504;
        for (int i = 0; i < n4; ++i) {
            final int n7 = (n2 + i) * super.anInt5433 + n;
            final int n8 = (n6 + i) * super.aHa_Sub2_5434.anInt4505 + n5;
            for (int j = 0; j < n3; ++j) {
                this.anIntArray6213[n7 + j] = anIntArray4504[n8 + j];
            }
        }
    }
    
    Class332_Sub3_Sub2(final ha_Sub2 ha_Sub2, final int[] anIntArray6213, int n, int n2, final int n3, final int n4, final boolean b) {
        super(ha_Sub2, n3, n4);
        if (b) {
            this.anIntArray6213 = new int[n3 * n4];
        }
        else {
            this.anIntArray6213 = anIntArray6213;
        }
        n2 -= super.anInt5433;
        int n5 = 0;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n6 = anIntArray6213[n++];
                if (n6 >>> 24 == 255) {
                    this.anIntArray6213[n5++] = (((n6 & 0xFFFFFF) == 0x0) ? -16777215 : n6);
                }
                else {
                    this.anIntArray6213[n5++] = 0;
                }
            }
            n += n2;
        }
    }
    
    @Override
    final void method3742(final int n, final int n2, final int n3) {
        throw new IllegalStateException("Can't capture alpha into a java_sprite_24");
    }
}

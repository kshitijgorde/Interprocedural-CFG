// 
// Decompiled by Procyon v0.5.30
// 

final class Class146_Sub1 extends Class146
{
    private int[] anIntArray4747;
    private int[] anIntArray4748;
    private int[] anIntArray4749;
    private short[] aShortArray4750;
    private int[] anIntArray4751;
    private Class235 aClass235_4752;
    private short aShort4753;
    private boolean aBoolean4754;
    private boolean aBoolean4755;
    private Class146_Sub1 aClass146_Sub1_4756;
    private int anInt4757;
    private int[] anIntArray4758;
    private Class146_Sub1 aClass146_Sub1_4759;
    private int[] anIntArray4760;
    private int anInt4761;
    private int[] anIntArray4762;
    private int[] anIntArray4763;
    private Class146_Sub1 aClass146_Sub1_4764;
    private boolean aBoolean4765;
    private int[] anIntArray4766;
    private int[] anIntArray4767;
    private Class12 aClass12_4768;
    private Class146_Sub1 aClass146_Sub1_4769;
    private short aShort4770;
    private int[] anIntArray4771;
    private byte[] aByteArray4772;
    private Class146_Sub1 aClass146_Sub1_4773;
    private short aShort4774;
    private Class146_Sub1 aClass146_Sub1_4775;
    private int anInt4776;
    private Class274[] aClass274Array4777;
    private boolean aBoolean4778;
    private Class146_Sub1 aClass146_Sub1_4779;
    private int anInt4780;
    private int anInt4781;
    private short[] aShortArray4782;
    private Class146_Sub1 aClass146_Sub1_4783;
    private int anInt4784;
    private Class146_Sub1 aClass146_Sub1_4785;
    private int anInt4786;
    private Class35[] aClass35Array4787;
    private int[] anIntArray4788;
    private Class274[] aClass274Array4789;
    private short[] aShortArray4790;
    private int[] anIntArray4791;
    private int anInt4792;
    private byte[] aByteArray4793;
    private short aShort4794;
    private short[] aShortArray4795;
    private int[][] anIntArrayArray4796;
    private ha_Sub2 aHa_Sub2_4797;
    private float[][] aFloatArrayArray4798;
    private boolean aBoolean4799;
    private short[] aShortArray4800;
    private short aShort4801;
    private boolean aBoolean4802;
    private int[] anIntArray4803;
    private short[] aShortArray4804;
    private int anInt4805;
    private float[][] aFloatArrayArray4806;
    private Class235 aClass235_4807;
    private short aShort4808;
    private short[] aShortArray4809;
    static int anInt4810;
    private int[] anIntArray4811;
    private int[] anIntArray4812;
    private Class87[] aClass87Array4813;
    private int anInt4814;
    private boolean aBoolean4815;
    private int anInt4816;
    private int[] anIntArray4817;
    private int[] anIntArray4818;
    private Class111_Sub2 aClass111_Sub2_4819;
    private int[] anIntArray4820;
    private Class329[] aClass329Array4821;
    private byte[] aByteArray4822;
    private Class296[] aClass296Array4823;
    private int[] anIntArray4824;
    static int anInt4825;
    private short aShort4826;
    private Class170[] aClass170Array4827;
    private int[] anIntArray4828;
    private int[] anIntArray4829;
    private static int anInt4830;
    private Class146_Sub1 aClass146_Sub1_4831;
    private short[] aShortArray4832;
    private int[][] anIntArrayArray4833;
    private int[][] anIntArrayArray4834;
    private short aShort4835;
    private int[] anIntArray4836;
    
    private final void method2346() {
        if (!this.aBoolean4778) {
            int n = 0;
            int n2 = 0;
            int n3 = 32767;
            int n4 = 32767;
            int n5 = 32767;
            int n6 = -32768;
            int n7 = -32768;
            int n8 = -32768;
            for (int i = 0; i < this.anInt4814; ++i) {
                final int n9 = this.anIntArray4748[i];
                final int n10 = this.anIntArray4788[i];
                final int n11 = this.anIntArray4762[i];
                if (n9 < n3) {
                    n3 = n9;
                }
                if (n9 > n6) {
                    n6 = n9;
                }
                if (n10 < n4) {
                    n4 = n10;
                }
                if (n10 > n7) {
                    n7 = n10;
                }
                if (n11 < n5) {
                    n5 = n11;
                }
                if (n11 > n8) {
                    n8 = n11;
                }
                final int n12 = n9 * n9 + n11 * n11;
                if (n12 > n) {
                    n = n12;
                }
                final int n13 = n12 + n10 * n10;
                if (n13 > n2) {
                    n2 = n13;
                }
            }
            this.aShort4794 = (short)n3;
            this.aShort4801 = (short)n6;
            this.aShort4770 = (short)n4;
            this.aShort4808 = (short)n7;
            this.aShort4774 = (short)n5;
            this.aShort4753 = (short)n8;
            this.aShort4835 = (short)(Math.sqrt(n) + 0.99);
            this.aShort4826 = (short)(Math.sqrt(n2) + 0.99);
            this.aBoolean4778 = true;
        }
    }
    
    @Override
    final void method2337(final int n, final int n2, final int n3, final int n4) {
        if ((this.anInt4816 & 0x80000) != 0x80000) {
            throw new IllegalStateException("FMT");
        }
        for (int i = 0; i < this.anInt4780; ++i) {
            final int n5 = this.aShortArray4800[i] & 0xFFFF;
            int n6 = n5 >> 10 & 0x3F;
            int n7 = n5 >> 7 & 0x7;
            int n8 = n5 & 0x7F;
            if (n != -1) {
                n6 += (n - n6) * n4 >> 7;
            }
            if (n2 != -1) {
                n7 += (n2 - n7) * n4 >> 7;
            }
            if (n3 != -1) {
                n8 += (n3 - n8) * n4 >> 7;
            }
            this.aShortArray4800[i] = (short)(n6 << 10 | n7 << 7 | n8);
        }
        if (this.aClass170Array4827 != null) {
            for (int j = 0; j < this.anInt4776; ++j) {
                final Class170 class170 = this.aClass170Array4827[j];
                final Class329 class171 = this.aClass329Array4821[j];
                class171.anInt2770 = ((class171.anInt2770 & 0xFF000000) | (Class221.anIntArray1665[Class111_Sub2.method2117(this.aShortArray4800[class170.anInt1315] & 0xFFFF, 72)] & 0xFFFFFF));
            }
        }
        if (this.anInt4784 == 2) {
            this.anInt4784 = 1;
        }
    }
    
    @Override
    final void I(final int n, final int[] array, int anInt4792, int anInt4793, int anInt4794, final boolean b, final int n2, final int[] array2) {
        final int length = array.length;
        if (n == 0) {
            anInt4792 <<= 4;
            anInt4793 <<= 4;
            anInt4794 <<= 4;
            if (!this.aBoolean4815) {
                for (int i = 0; i < this.anInt4786; ++i) {
                    final int[] anIntArray4748 = this.anIntArray4748;
                    final int n3 = i;
                    anIntArray4748[n3] <<= 4;
                    final int[] anIntArray4749 = this.anIntArray4788;
                    final int n4 = i;
                    anIntArray4749[n4] <<= 4;
                    final int[] anIntArray4750 = this.anIntArray4762;
                    final int n5 = i;
                    anIntArray4750[n5] <<= 4;
                }
                this.aBoolean4815 = true;
            }
            int n6 = 0;
            this.anInt4792 = 0;
            this.anInt4781 = 0;
            this.anInt4757 = 0;
            for (final int n7 : array) {
                if (n7 < this.anIntArrayArray4834.length) {
                    final int[] array3 = this.anIntArrayArray4834[n7];
                    for (int k = 0; k < array3.length; ++k) {
                        final int n8 = array3[k];
                        if (this.aShortArray4790 == null || (n2 & this.aShortArray4790[n8]) != 0x0) {
                            this.anInt4792 += this.anIntArray4748[n8];
                            this.anInt4781 += this.anIntArray4788[n8];
                            this.anInt4757 += this.anIntArray4762[n8];
                            ++n6;
                        }
                    }
                }
            }
            if (n6 > 0) {
                this.anInt4792 = this.anInt4792 / n6 + anInt4792;
                this.anInt4781 = this.anInt4781 / n6 + anInt4793;
                this.anInt4757 = this.anInt4757 / n6 + anInt4794;
                this.aBoolean4799 = true;
            }
            else {
                this.anInt4792 = anInt4792;
                this.anInt4781 = anInt4793;
                this.anInt4757 = anInt4794;
            }
        }
        else if (n == 1) {
            if (array2 != null) {
                final int n9 = array2[0] * anInt4792 + array2[1] * anInt4793 + array2[2] * anInt4794 + 8192 >> 14;
                final int n10 = array2[3] * anInt4792 + array2[4] * anInt4793 + array2[5] * anInt4794 + 8192 >> 14;
                final int n11 = array2[6] * anInt4792 + array2[7] * anInt4793 + array2[8] * anInt4794 + 8192 >> 14;
                anInt4792 = n9;
                anInt4793 = n10;
                anInt4794 = n11;
            }
            anInt4792 <<= 4;
            anInt4793 <<= 4;
            anInt4794 <<= 4;
            if (!this.aBoolean4815) {
                for (int l = 0; l < this.anInt4786; ++l) {
                    final int[] anIntArray4751 = this.anIntArray4748;
                    final int n12 = l;
                    anIntArray4751[n12] <<= 4;
                    final int[] anIntArray4752 = this.anIntArray4788;
                    final int n13 = l;
                    anIntArray4752[n13] <<= 4;
                    final int[] anIntArray4753 = this.anIntArray4762;
                    final int n14 = l;
                    anIntArray4753[n14] <<= 4;
                }
                this.aBoolean4815 = true;
            }
            for (final int n16 : array) {
                if (n16 < this.anIntArrayArray4834.length) {
                    final int[] array4 = this.anIntArrayArray4834[n16];
                    for (int n17 = 0; n17 < array4.length; ++n17) {
                        final int n18 = array4[n17];
                        if (this.aShortArray4790 == null || (n2 & this.aShortArray4790[n18]) != 0x0) {
                            final int[] anIntArray4754 = this.anIntArray4748;
                            final int n19 = n18;
                            anIntArray4754[n19] += anInt4792;
                            final int[] anIntArray4755 = this.anIntArray4788;
                            final int n20 = n18;
                            anIntArray4755[n20] += anInt4793;
                            final int[] anIntArray4756 = this.anIntArray4762;
                            final int n21 = n18;
                            anIntArray4756[n21] += anInt4794;
                        }
                    }
                }
            }
        }
        else if (n == 2) {
            if (array2 != null) {
                if (!this.aBoolean4815) {
                    for (int n22 = 0; n22 < this.anInt4786; ++n22) {
                        final int[] anIntArray4757 = this.anIntArray4748;
                        final int n23 = n22;
                        anIntArray4757[n23] <<= 4;
                        final int[] anIntArray4758 = this.anIntArray4788;
                        final int n24 = n22;
                        anIntArray4758[n24] <<= 4;
                        final int[] anIntArray4759 = this.anIntArray4762;
                        final int n25 = n22;
                        anIntArray4759[n25] <<= 4;
                    }
                    this.aBoolean4815 = true;
                }
                final int n26 = array2[9] << 4;
                final int n27 = array2[10] << 4;
                final int n28 = array2[11] << 4;
                final int n29 = array2[12] << 4;
                final int n30 = array2[13] << 4;
                final int n31 = array2[14] << 4;
                if (this.aBoolean4799) {
                    final int n32 = array2[0] * this.anInt4792 + array2[3] * this.anInt4781 + array2[6] * this.anInt4757 + 8192 >> 14;
                    final int n33 = array2[1] * this.anInt4792 + array2[4] * this.anInt4781 + array2[7] * this.anInt4757 + 8192 >> 14;
                    final int n34 = array2[2] * this.anInt4792 + array2[5] * this.anInt4781 + array2[8] * this.anInt4757 + 8192 >> 14;
                    final int anInt4795 = n32 + n29;
                    final int anInt4796 = n33 + n30;
                    final int anInt4797 = n34 + n31;
                    this.anInt4792 = anInt4795;
                    this.anInt4781 = anInt4796;
                    this.anInt4757 = anInt4797;
                    this.aBoolean4799 = false;
                }
                final int[] array5 = new int[9];
                final int n35 = Class284_Sub2_Sub2.anIntArray6202[anInt4792];
                final int n36 = Class284_Sub2_Sub2.anIntArray6200[anInt4792];
                final int n37 = Class284_Sub2_Sub2.anIntArray6202[anInt4793];
                final int n38 = Class284_Sub2_Sub2.anIntArray6200[anInt4793];
                final int n39 = Class284_Sub2_Sub2.anIntArray6202[anInt4794];
                final int n40 = Class284_Sub2_Sub2.anIntArray6200[anInt4794];
                final int n41 = n36 * n39 + 8192 >> 14;
                final int n42 = n36 * n40 + 8192 >> 14;
                array5[0] = n37 * n39 + n38 * n42 + 8192 >> 14;
                array5[1] = -n37 * n40 + n38 * n41 + 8192 >> 14;
                array5[2] = n38 * n35 + 8192 >> 14;
                array5[3] = n35 * n40 + 8192 >> 14;
                array5[4] = n35 * n39 + 8192 >> 14;
                array5[5] = -n36;
                array5[6] = -n38 * n39 + n37 * n42 + 8192 >> 14;
                array5[7] = n38 * n40 + n37 * n41 + 8192 >> 14;
                array5[8] = n37 * n35 + 8192 >> 14;
                final int n43 = array5[0] * -this.anInt4792 + array5[1] * -this.anInt4781 + array5[2] * -this.anInt4757 + 8192 >> 14;
                final int n44 = array5[3] * -this.anInt4792 + array5[4] * -this.anInt4781 + array5[5] * -this.anInt4757 + 8192 >> 14;
                final int n45 = array5[6] * -this.anInt4792 + array5[7] * -this.anInt4781 + array5[8] * -this.anInt4757 + 8192 >> 14;
                final int n46 = n43 + this.anInt4792;
                final int n47 = n44 + this.anInt4781;
                final int n48 = n45 + this.anInt4757;
                final int[] array6 = new int[9];
                for (int n49 = 0; n49 < 3; ++n49) {
                    for (int n50 = 0; n50 < 3; ++n50) {
                        int n51 = 0;
                        for (int n52 = 0; n52 < 3; ++n52) {
                            n51 += array5[n49 * 3 + n52] * array2[n50 * 3 + n52];
                        }
                        array6[n49 * 3 + n50] = n51 + 8192 >> 14;
                    }
                }
                final int n53 = array5[0] * n29 + array5[1] * n30 + array5[2] * n31 + 8192 >> 14;
                final int n54 = array5[3] * n29 + array5[4] * n30 + array5[5] * n31 + 8192 >> 14;
                final int n55 = array5[6] * n29 + array5[7] * n30 + array5[8] * n31 + 8192 >> 14;
                final int n56 = n53 + n46;
                final int n57 = n54 + n47;
                final int n58 = n55 + n48;
                final int[] array7 = new int[9];
                for (int n59 = 0; n59 < 3; ++n59) {
                    for (int n60 = 0; n60 < 3; ++n60) {
                        int n61 = 0;
                        for (int n62 = 0; n62 < 3; ++n62) {
                            n61 += array2[n59 * 3 + n62] * array6[n60 + n62 * 3];
                        }
                        array7[n59 * 3 + n60] = n61 + 8192 >> 14;
                    }
                }
                final int n63 = array2[0] * n56 + array2[1] * n57 + array2[2] * n58 + 8192 >> 14;
                final int n64 = array2[3] * n56 + array2[4] * n57 + array2[5] * n58 + 8192 >> 14;
                final int n65 = array2[6] * n56 + array2[7] * n57 + array2[8] * n58 + 8192 >> 14;
                final int n66 = n63 + n26;
                final int n67 = n64 + n27;
                final int n68 = n65 + n28;
                for (final int n70 : array) {
                    if (n70 < this.anIntArrayArray4834.length) {
                        final int[] array8 = this.anIntArrayArray4834[n70];
                        for (int n71 = 0; n71 < array8.length; ++n71) {
                            final int n72 = array8[n71];
                            if (this.aShortArray4790 == null || (n2 & this.aShortArray4790[n72]) != 0x0) {
                                final int n73 = array7[0] * this.anIntArray4748[n72] + array7[1] * this.anIntArray4788[n72] + array7[2] * this.anIntArray4762[n72] + 8192 >> 14;
                                final int n74 = array7[3] * this.anIntArray4748[n72] + array7[4] * this.anIntArray4788[n72] + array7[5] * this.anIntArray4762[n72] + 8192 >> 14;
                                final int n75 = array7[6] * this.anIntArray4748[n72] + array7[7] * this.anIntArray4788[n72] + array7[8] * this.anIntArray4762[n72] + 8192 >> 14;
                                final int n76 = n73 + n66;
                                final int n77 = n74 + n67;
                                final int n78 = n75 + n68;
                                this.anIntArray4748[n72] = n76;
                                this.anIntArray4788[n72] = n77;
                                this.anIntArray4762[n72] = n78;
                            }
                        }
                    }
                }
            }
            else {
                for (final int n80 : array) {
                    if (n80 < this.anIntArrayArray4834.length) {
                        final int[] array9 = this.anIntArrayArray4834[n80];
                        for (int n81 = 0; n81 < array9.length; ++n81) {
                            final int n82 = array9[n81];
                            if (this.aShortArray4790 == null || (n2 & this.aShortArray4790[n82]) != 0x0) {
                                final int[] anIntArray4760 = this.anIntArray4748;
                                final int n83 = n82;
                                anIntArray4760[n83] -= this.anInt4792;
                                final int[] anIntArray4761 = this.anIntArray4788;
                                final int n84 = n82;
                                anIntArray4761[n84] -= this.anInt4781;
                                final int[] anIntArray4762 = this.anIntArray4762;
                                final int n85 = n82;
                                anIntArray4762[n85] -= this.anInt4757;
                                if (anInt4794 != 0) {
                                    final int n86 = Class284_Sub2_Sub2.anIntArray6200[anInt4794];
                                    final int n87 = Class284_Sub2_Sub2.anIntArray6202[anInt4794];
                                    final int n88 = this.anIntArray4788[n82] * n86 + this.anIntArray4748[n82] * n87 + 16383 >> 14;
                                    this.anIntArray4788[n82] = this.anIntArray4788[n82] * n87 - this.anIntArray4748[n82] * n86 + 16383 >> 14;
                                    this.anIntArray4748[n82] = n88;
                                }
                                if (anInt4792 != 0) {
                                    final int n89 = Class284_Sub2_Sub2.anIntArray6200[anInt4792];
                                    final int n90 = Class284_Sub2_Sub2.anIntArray6202[anInt4792];
                                    final int n91 = this.anIntArray4788[n82] * n90 - this.anIntArray4762[n82] * n89 + 16383 >> 14;
                                    this.anIntArray4762[n82] = this.anIntArray4788[n82] * n89 + this.anIntArray4762[n82] * n90 + 16383 >> 14;
                                    this.anIntArray4788[n82] = n91;
                                }
                                if (anInt4793 != 0) {
                                    final int n92 = Class284_Sub2_Sub2.anIntArray6200[anInt4793];
                                    final int n93 = Class284_Sub2_Sub2.anIntArray6202[anInt4793];
                                    final int n94 = this.anIntArray4762[n82] * n92 + this.anIntArray4748[n82] * n93 + 16383 >> 14;
                                    this.anIntArray4762[n82] = this.anIntArray4762[n82] * n93 - this.anIntArray4748[n82] * n92 + 16383 >> 14;
                                    this.anIntArray4748[n82] = n94;
                                }
                                final int[] anIntArray4763 = this.anIntArray4748;
                                final int n95 = n82;
                                anIntArray4763[n95] += this.anInt4792;
                                final int[] anIntArray4764 = this.anIntArray4788;
                                final int n96 = n82;
                                anIntArray4764[n96] += this.anInt4781;
                                final int[] anIntArray4765 = this.anIntArray4762;
                                final int n97 = n82;
                                anIntArray4765[n97] += this.anInt4757;
                            }
                        }
                    }
                }
            }
        }
        else if (n == 3) {
            if (array2 != null) {
                if (!this.aBoolean4815) {
                    for (int n98 = 0; n98 < this.anInt4786; ++n98) {
                        final int[] anIntArray4766 = this.anIntArray4748;
                        final int n99 = n98;
                        anIntArray4766[n99] <<= 4;
                        final int[] anIntArray4767 = this.anIntArray4788;
                        final int n100 = n98;
                        anIntArray4767[n100] <<= 4;
                        final int[] anIntArray4768 = this.anIntArray4762;
                        final int n101 = n98;
                        anIntArray4768[n101] <<= 4;
                    }
                    this.aBoolean4815 = true;
                }
                final int n102 = array2[9] << 4;
                final int n103 = array2[10] << 4;
                final int n104 = array2[11] << 4;
                final int n105 = array2[12] << 4;
                final int n106 = array2[13] << 4;
                final int n107 = array2[14] << 4;
                if (this.aBoolean4799) {
                    final int n108 = array2[0] * this.anInt4792 + array2[3] * this.anInt4781 + array2[6] * this.anInt4757 + 8192 >> 14;
                    final int n109 = array2[1] * this.anInt4792 + array2[4] * this.anInt4781 + array2[7] * this.anInt4757 + 8192 >> 14;
                    final int n110 = array2[2] * this.anInt4792 + array2[5] * this.anInt4781 + array2[8] * this.anInt4757 + 8192 >> 14;
                    final int anInt4798 = n108 + n105;
                    final int anInt4799 = n109 + n106;
                    final int anInt4800 = n110 + n107;
                    this.anInt4792 = anInt4798;
                    this.anInt4781 = anInt4799;
                    this.anInt4757 = anInt4800;
                    this.aBoolean4799 = false;
                }
                final int n111 = anInt4792 << 15 >> 7;
                final int n112 = anInt4793 << 15 >> 7;
                final int n113 = anInt4794 << 15 >> 7;
                final int n114 = n111 * -this.anInt4792 + 8192 >> 14;
                final int n115 = n112 * -this.anInt4781 + 8192 >> 14;
                final int n116 = n113 * -this.anInt4757 + 8192 >> 14;
                final int n117 = n114 + this.anInt4792;
                final int n118 = n115 + this.anInt4781;
                final int n119 = n116 + this.anInt4757;
                final int[] array10 = { n111 * array2[0] + 8192 >> 14, n111 * array2[3] + 8192 >> 14, n111 * array2[6] + 8192 >> 14, n112 * array2[1] + 8192 >> 14, n112 * array2[4] + 8192 >> 14, n112 * array2[7] + 8192 >> 14, n113 * array2[2] + 8192 >> 14, n113 * array2[5] + 8192 >> 14, n113 * array2[8] + 8192 >> 14 };
                final int n120 = n111 * n105 + 8192 >> 14;
                final int n121 = n112 * n106 + 8192 >> 14;
                final int n122 = n113 * n107 + 8192 >> 14;
                final int n123 = n120 + n117;
                final int n124 = n121 + n118;
                final int n125 = n122 + n119;
                final int[] array11 = new int[9];
                for (int n126 = 0; n126 < 3; ++n126) {
                    for (int n127 = 0; n127 < 3; ++n127) {
                        int n128 = 0;
                        for (int n129 = 0; n129 < 3; ++n129) {
                            n128 += array2[n126 * 3 + n129] * array10[n127 + n129 * 3];
                        }
                        array11[n126 * 3 + n127] = n128 + 8192 >> 14;
                    }
                }
                final int n130 = array2[0] * n123 + array2[1] * n124 + array2[2] * n125 + 8192 >> 14;
                final int n131 = array2[3] * n123 + array2[4] * n124 + array2[5] * n125 + 8192 >> 14;
                final int n132 = array2[6] * n123 + array2[7] * n124 + array2[8] * n125 + 8192 >> 14;
                final int n133 = n130 + n102;
                final int n134 = n131 + n103;
                final int n135 = n132 + n104;
                for (final int n137 : array) {
                    if (n137 < this.anIntArrayArray4834.length) {
                        final int[] array12 = this.anIntArrayArray4834[n137];
                        for (int n138 = 0; n138 < array12.length; ++n138) {
                            final int n139 = array12[n138];
                            if (this.aShortArray4790 == null || (n2 & this.aShortArray4790[n139]) != 0x0) {
                                final int n140 = array11[0] * this.anIntArray4748[n139] + array11[1] * this.anIntArray4788[n139] + array11[2] * this.anIntArray4762[n139] + 8192 >> 14;
                                final int n141 = array11[3] * this.anIntArray4748[n139] + array11[4] * this.anIntArray4788[n139] + array11[5] * this.anIntArray4762[n139] + 8192 >> 14;
                                final int n142 = array11[6] * this.anIntArray4748[n139] + array11[7] * this.anIntArray4788[n139] + array11[8] * this.anIntArray4762[n139] + 8192 >> 14;
                                final int n143 = n140 + n133;
                                final int n144 = n141 + n134;
                                final int n145 = n142 + n135;
                                this.anIntArray4748[n139] = n143;
                                this.anIntArray4788[n139] = n144;
                                this.anIntArray4762[n139] = n145;
                            }
                        }
                    }
                }
            }
            else {
                for (final int n147 : array) {
                    if (n147 < this.anIntArrayArray4834.length) {
                        final int[] array13 = this.anIntArrayArray4834[n147];
                        for (int n148 = 0; n148 < array13.length; ++n148) {
                            final int n149 = array13[n148];
                            if (this.aShortArray4790 == null || (n2 & this.aShortArray4790[n149]) != 0x0) {
                                final int[] anIntArray4769 = this.anIntArray4748;
                                final int n150 = n149;
                                anIntArray4769[n150] -= this.anInt4792;
                                final int[] anIntArray4770 = this.anIntArray4788;
                                final int n151 = n149;
                                anIntArray4770[n151] -= this.anInt4781;
                                final int[] anIntArray4771 = this.anIntArray4762;
                                final int n152 = n149;
                                anIntArray4771[n152] -= this.anInt4757;
                                this.anIntArray4748[n149] = this.anIntArray4748[n149] * anInt4792 / 128;
                                this.anIntArray4788[n149] = this.anIntArray4788[n149] * anInt4793 / 128;
                                this.anIntArray4762[n149] = this.anIntArray4762[n149] * anInt4794 / 128;
                                final int[] anIntArray4772 = this.anIntArray4748;
                                final int n153 = n149;
                                anIntArray4772[n153] += this.anInt4792;
                                final int[] anIntArray4773 = this.anIntArray4788;
                                final int n154 = n149;
                                anIntArray4773[n154] += this.anInt4781;
                                final int[] anIntArray4774 = this.anIntArray4762;
                                final int n155 = n149;
                                anIntArray4774[n155] += this.anInt4757;
                            }
                        }
                    }
                }
            }
        }
        else if (n == 5) {
            if (this.anIntArrayArray4833 != null && this.aByteArray4822 != null) {
                for (final int n157 : array) {
                    if (n157 < this.anIntArrayArray4833.length) {
                        final int[] array14 = this.anIntArrayArray4833[n157];
                        for (int n158 = 0; n158 < array14.length; ++n158) {
                            final int n159 = array14[n158];
                            if (this.aShortArray4795 == null || (n2 & this.aShortArray4795[n159]) != 0x0) {
                                int n160 = (this.aByteArray4822[n159] & 0xFF) + anInt4792 * 8;
                                if (n160 < 0) {
                                    n160 = 0;
                                }
                                else if (n160 > 255) {
                                    n160 = 255;
                                }
                                this.aByteArray4822[n159] = (byte)n160;
                            }
                        }
                    }
                }
                if (this.aClass170Array4827 != null) {
                    for (int n161 = 0; n161 < this.anInt4776; ++n161) {
                        final Class170 class170 = this.aClass170Array4827[n161];
                        final Class329 class171 = this.aClass329Array4821[n161];
                        class171.anInt2770 = ((class171.anInt2770 & 0xFFFFFF) | 255 - (this.aByteArray4822[class170.anInt1315] & 0xFF) << 24);
                    }
                }
            }
        }
        else if (n == 7) {
            if (this.anIntArrayArray4833 != null) {
                for (final int n163 : array) {
                    if (n163 < this.anIntArrayArray4833.length) {
                        final int[] array15 = this.anIntArrayArray4833[n163];
                        for (int n164 = 0; n164 < array15.length; ++n164) {
                            final int n165 = array15[n164];
                            if (this.aShortArray4795 == null || (n2 & this.aShortArray4795[n165]) != 0x0) {
                                final int n166 = this.aShortArray4800[n165] & 0xFFFF;
                                final int n167 = n166 >> 10 & 0x3F;
                                final int n168 = n166 >> 7 & 0x7;
                                final int n169 = n166 & 0x7F;
                                final int n170 = n167 + anInt4792 & 0x3F;
                                int n171 = n168 + anInt4793;
                                if (n171 < 0) {
                                    n171 = 0;
                                }
                                else if (n171 > 7) {
                                    n171 = 7;
                                }
                                int n172 = n169 + anInt4794;
                                if (n172 < 0) {
                                    n172 = 0;
                                }
                                else if (n172 > 127) {
                                    n172 = 127;
                                }
                                this.aShortArray4800[n165] = (short)(n170 << 10 | n171 << 7 | n172);
                            }
                        }
                        this.aBoolean4754 = true;
                    }
                }
                if (this.aClass170Array4827 != null) {
                    for (int n173 = 0; n173 < this.anInt4776; ++n173) {
                        final Class170 class172 = this.aClass170Array4827[n173];
                        final Class329 class173 = this.aClass329Array4821[n173];
                        class173.anInt2770 = ((class173.anInt2770 & 0xFF000000) | (Class221.anIntArray1665[Class111_Sub2.method2117(this.aShortArray4800[class172.anInt1315] & 0xFFFF, -83) & 0xFFFF] & 0xFFFFFF));
                    }
                }
            }
        }
        else if (n == 8) {
            if (this.anIntArrayArray4796 != null) {
                for (final int n175 : array) {
                    if (n175 < this.anIntArrayArray4796.length) {
                        final int[] array16 = this.anIntArrayArray4796[n175];
                        for (int n176 = 0; n176 < array16.length; ++n176) {
                            final Class329 class175;
                            final Class329 class174 = class175 = this.aClass329Array4821[array16[n176]];
                            class175.anInt2762 += anInt4792;
                            final Class329 class176 = class174;
                            class176.anInt2772 += anInt4793;
                        }
                    }
                }
            }
        }
        else if (n == 10) {
            if (this.anIntArrayArray4796 != null) {
                for (final int n178 : array) {
                    if (n178 < this.anIntArrayArray4796.length) {
                        final int[] array17 = this.anIntArrayArray4796[n178];
                        for (int n179 = 0; n179 < array17.length; ++n179) {
                            final Class329 class177 = this.aClass329Array4821[array17[n179]];
                            class177.anInt2763 = class177.anInt2763 * anInt4792 >> 7;
                            class177.anInt2768 = class177.anInt2768 * anInt4793 >> 7;
                        }
                    }
                }
            }
        }
        else if (n == 9 && this.anIntArrayArray4796 != null) {
            for (final int n181 : array) {
                if (n181 < this.anIntArrayArray4796.length) {
                    final int[] array18 = this.anIntArrayArray4796[n181];
                    for (int n182 = 0; n182 < array18.length; ++n182) {
                        final Class329 class178 = this.aClass329Array4821[array18[n182]];
                        class178.anInt2769 = (class178.anInt2769 + anInt4792 & 0x3FFF);
                    }
                }
            }
        }
    }
    
    @Override
    final void O(final int n, final int n2, final int n3) {
        if (n != 128 && (this.anInt4816 & 0x1) != 0x1) {
            throw new IllegalStateException();
        }
        if (n2 != 128 && (this.anInt4816 & 0x2) != 0x2) {
            throw new IllegalStateException();
        }
        if (n3 != 128 && (this.anInt4816 & 0x4) != 0x4) {
            throw new IllegalStateException();
        }
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                this.anIntArray4748[i] = this.anIntArray4748[i] * n >> 7;
                this.anIntArray4788[i] = this.anIntArray4788[i] * n2 >> 7;
                this.anIntArray4762[i] = this.anIntArray4762[i] * n3 >> 7;
            }
            this.aBoolean4778 = false;
        }
    }
    
    @Override
    final void k(final int n) {
        if ((this.anInt4816 & 0xD) != 0xD) {
            throw new IllegalStateException();
        }
        if (this.aClass274Array4789 != null) {
            if (n == 4096) {
                this.method2350();
            }
            else if (n == 8192) {
                this.method2369();
            }
            else if (n == 12288) {
                this.method2375();
            }
            else {
                final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
                final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
                synchronized (this) {
                    for (int i = 0; i < this.anInt4814; ++i) {
                        final int n4 = this.anIntArray4762[i] * n2 + this.anIntArray4748[i] * n3 >> 14;
                        this.anIntArray4762[i] = this.anIntArray4762[i] * n3 - this.anIntArray4748[i] * n2 >> 14;
                        this.anIntArray4748[i] = n4;
                        if (this.aClass274Array4789[i] != null) {
                            final int anInt2044 = this.aClass274Array4789[i].anInt2042 * n2 + this.aClass274Array4789[i].anInt2044 * n3 >> 14;
                            this.aClass274Array4789[i].anInt2042 = this.aClass274Array4789[i].anInt2042 * n3 - this.aClass274Array4789[i].anInt2044 * n2 >> 14;
                            this.aClass274Array4789[i].anInt2044 = anInt2044;
                        }
                    }
                    if (this.aClass296Array4823 != null) {
                        for (int j = 0; j < this.anInt4780; ++j) {
                            if (this.aClass296Array4823[j] != null) {
                                final int anInt2045 = this.aClass296Array4823[j].anInt2413 * n2 + this.aClass296Array4823[j].anInt2411 * n3 >> 14;
                                this.aClass296Array4823[j].anInt2413 = this.aClass296Array4823[j].anInt2413 * n3 - this.aClass296Array4823[j].anInt2411 * n2 >> 14;
                                this.aClass296Array4823[j].anInt2411 = anInt2045;
                            }
                        }
                    }
                    for (int k = this.anInt4814; k < this.anInt4786; ++k) {
                        final int n5 = this.anIntArray4762[k] * n2 + this.anIntArray4748[k] * n3 >> 14;
                        this.anIntArray4762[k] = this.anIntArray4762[k] * n3 - this.anIntArray4748[k] * n2 >> 14;
                        this.anIntArray4748[k] = n5;
                    }
                    this.anInt4784 = 0;
                    this.aBoolean4778 = false;
                }
            }
        }
        else {
            this.a(n);
        }
    }
    
    @Override
    final void LA(final int anInt4805) {
        if ((this.anInt4816 & 0x2000) != 0x2000) {
            throw new IllegalStateException();
        }
        this.anInt4805 = anInt4805;
        this.anInt4784 = 0;
    }
    
    private final void method2347() {
        this.aClass274Array4789 = new Class274[this.anInt4814];
        for (int i = 0; i < this.anInt4814; ++i) {
            this.aClass274Array4789[i] = new Class274();
        }
        for (int j = 0; j < this.anInt4780; ++j) {
            final short n = this.aShortArray4782[j];
            final short n2 = this.aShortArray4750[j];
            final short n3 = this.aShortArray4832[j];
            final int n4 = this.anIntArray4748[n2] - this.anIntArray4748[n];
            final int n5 = this.anIntArray4788[n2] - this.anIntArray4788[n];
            final int n6 = this.anIntArray4762[n2] - this.anIntArray4762[n];
            final int n7 = this.anIntArray4748[n3] - this.anIntArray4748[n];
            final int n8 = this.anIntArray4788[n3] - this.anIntArray4788[n];
            final int n9 = this.anIntArray4762[n3] - this.anIntArray4762[n];
            int n10;
            int n11;
            int n12;
            for (n10 = n5 * n9 - n8 * n6, n11 = n6 * n7 - n9 * n4, n12 = n4 * n8 - n7 * n5; n10 > 8192 || n11 > 8192 || n12 > 8192 || n10 < -8192 || n11 < -8192 || n12 < -8192; n10 >>= 1, n11 >>= 1, n12 >>= 1) {}
            int n13 = (int)Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
            if (n13 <= 0) {
                n13 = 1;
            }
            final int anInt2411 = n10 * 256 / n13;
            final int anInt2412 = n11 * 256 / n13;
            final int anInt2413 = n12 * 256 / n13;
            byte b;
            if (this.aByteArray4793 == null) {
                b = 0;
            }
            else {
                b = this.aByteArray4793[j];
            }
            if (b == 0) {
                final Class274 class275;
                final Class274 class274 = class275 = this.aClass274Array4789[n];
                class275.anInt2044 += anInt2411;
                final Class274 class276 = class274;
                class276.anInt2043 += anInt2412;
                final Class274 class277 = class274;
                class277.anInt2042 += anInt2413;
                final Class274 class278 = class274;
                ++class278.anInt2045;
                final Class274 class280;
                final Class274 class279 = class280 = this.aClass274Array4789[n2];
                class280.anInt2044 += anInt2411;
                final Class274 class281 = class279;
                class281.anInt2043 += anInt2412;
                final Class274 class282 = class279;
                class282.anInt2042 += anInt2413;
                final Class274 class283 = class279;
                ++class283.anInt2045;
                final Class274 class285;
                final Class274 class284 = class285 = this.aClass274Array4789[n3];
                class285.anInt2044 += anInt2411;
                final Class274 class286 = class284;
                class286.anInt2043 += anInt2412;
                final Class274 class287 = class284;
                class287.anInt2042 += anInt2413;
                final Class274 class288 = class284;
                ++class288.anInt2045;
            }
            else if (b == 1) {
                if (this.aClass296Array4823 == null) {
                    this.aClass296Array4823 = new Class296[this.anInt4780];
                }
                final Class296[] aClass296Array4823 = this.aClass296Array4823;
                final int n14 = j;
                final Class296 class289 = new Class296();
                aClass296Array4823[n14] = class289;
                final Class296 class290 = class289;
                class290.anInt2411 = anInt2411;
                class290.anInt2412 = anInt2412;
                class290.anInt2413 = anInt2413;
            }
        }
    }
    
    @Override
    final int ma() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4826;
    }
    
    @Override
    final int G() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4753;
    }
    
    private final void method2348(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n, final int n2) {
        if (this.anInt4814 >= 1) {
            this.aClass111_Sub2_4819 = (Class111_Sub2)class111;
            final Class111_Sub2 aClass111_Sub2_4513 = this.aHa_Sub2_4797.aClass111_Sub2_4513;
            if (!this.aBoolean4778) {
                this.method2346();
            }
            boolean b = false;
            if (this.aClass111_Sub2_4819.aFloat4700 == 16384.0f && this.aClass111_Sub2_4819.aFloat4699 == 0.0f && this.aClass111_Sub2_4819.aFloat4690 == 0.0f && this.aClass111_Sub2_4819.aFloat4692 == 0.0f && this.aClass111_Sub2_4819.aFloat4688 == 16384.0f && this.aClass111_Sub2_4819.aFloat4696 == 0.0f && this.aClass111_Sub2_4819.aFloat4693 == 0.0f && this.aClass111_Sub2_4819.aFloat4698 == 0.0f && this.aClass111_Sub2_4819.aFloat4694 == 16384.0f) {
                b = true;
            }
            final float n3 = aClass111_Sub2_4513.aFloat4689 + aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4697 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4691 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4689;
            final float n4 = b ? aClass111_Sub2_4513.aFloat4698 : (aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4699 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4688 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4698);
            final int n5 = (int)(n3 + this.aShort4770 * n4);
            final int n6 = (int)(n3 + this.aShort4808 * n4);
            short n7;
            short n8;
            if (n5 > n6) {
                n7 = (short)(n6 - this.aShort4835);
                n8 = (short)(n5 + this.aShort4835);
            }
            else {
                n7 = (short)(n5 - this.aShort4835);
                n8 = (short)(n6 + this.aShort4835);
            }
            if (n7 < this.aHa_Sub2_4797.anInt4484 && n8 > this.aHa_Sub2_4797.anInt4502) {
                final float n9 = aClass111_Sub2_4513.aFloat4697 + aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4697 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4691 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4689;
                final float n10 = b ? aClass111_Sub2_4513.aFloat4699 : (aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4699 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4688 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4698);
                final int n11 = (int)(n9 + this.aShort4770 * n10);
                final int n12 = (int)(n9 + this.aShort4808 * n10);
                int n13;
                int n14;
                if (n11 > n12) {
                    n13 = (n12 - this.aShort4835) * this.aHa_Sub2_4797.anInt4514;
                    n14 = (n11 + this.aShort4835) * this.aHa_Sub2_4797.anInt4514;
                }
                else {
                    n13 = (n11 - this.aShort4835) * this.aHa_Sub2_4797.anInt4514;
                    n14 = (n12 + this.aShort4835) * this.aHa_Sub2_4797.anInt4514;
                }
                if (n == -1) {
                    if (n13 / n8 >= this.aHa_Sub2_4797.anInt4517 || n14 / n8 <= this.aHa_Sub2_4797.anInt4486) {
                        return;
                    }
                }
                else if (n13 / n >= this.aHa_Sub2_4797.anInt4517 || n14 / n <= this.aHa_Sub2_4797.anInt4486) {
                    return;
                }
                final float n15 = aClass111_Sub2_4513.aFloat4691 + aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4697 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4691 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4689;
                final float n16 = b ? aClass111_Sub2_4513.aFloat4688 : (aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4699 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4688 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4698);
                final int n17 = (int)(n15 + this.aShort4770 * n16);
                final int n18 = (int)(n15 + this.aShort4808 * n16);
                int n19;
                int n20;
                if (n17 > n18) {
                    n19 = (n18 - this.aShort4835) * this.aHa_Sub2_4797.anInt4490;
                    n20 = (n17 + this.aShort4835) * this.aHa_Sub2_4797.anInt4490;
                }
                else {
                    n19 = (n17 - this.aShort4835) * this.aHa_Sub2_4797.anInt4490;
                    n20 = (n18 + this.aShort4835) * this.aHa_Sub2_4797.anInt4490;
                }
                if (n == -1) {
                    if (n19 / n8 >= this.aHa_Sub2_4797.anInt4506 || n20 / n8 <= this.aHa_Sub2_4797.anInt4518) {
                        return;
                    }
                }
                else if (n19 / n >= this.aHa_Sub2_4797.anInt4506 || n20 / n <= this.aHa_Sub2_4797.anInt4518) {
                    return;
                }
                float aFloat4700;
                float aFloat4701;
                float aFloat4702;
                float aFloat4703;
                float aFloat4704;
                float aFloat4705;
                if (b) {
                    aFloat4700 = aClass111_Sub2_4513.aFloat4700;
                    aFloat4701 = aClass111_Sub2_4513.aFloat4692;
                    aFloat4702 = aClass111_Sub2_4513.aFloat4693;
                    aFloat4703 = aClass111_Sub2_4513.aFloat4690;
                    aFloat4704 = aClass111_Sub2_4513.aFloat4696;
                    aFloat4705 = aClass111_Sub2_4513.aFloat4694;
                }
                else {
                    aFloat4700 = aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4700 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4692 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4693;
                    aFloat4701 = aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4700 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4692 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4693;
                    aFloat4702 = aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4700 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4692 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4693;
                    aFloat4703 = aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4690 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4696 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4694;
                    aFloat4704 = aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4690 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4696 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4694;
                    aFloat4705 = aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4690 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4696 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4694;
                }
                if (this.aHa_Sub2_4797.anInt4508 > 1) {
                    synchronized (this) {
                        while (this.aBoolean4765) {
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        this.aBoolean4765 = true;
                    }
                }
                this.method2351(Thread.currentThread());
                if ((n2 & 0x2) != 0x0) {
                    this.aClass12_4768.method213(true);
                }
                else {
                    this.aClass12_4768.method213(false);
                }
                boolean b2 = false;
                final boolean b3 = n7 <= this.aHa_Sub2_4797.anInt4502 || this.aClass87Array4813 != null || this.aClass35Array4787 != null;
                this.aClass235_4807.anInt1783 = this.aClass12_4768.anInt141;
                this.aClass235_4807.anInt1771 = this.aClass12_4768.anInt136;
                this.aClass235_4807.anInt1779 = this.aClass12_4768.anInt128;
                final int anInt4514 = this.aHa_Sub2_4797.anInt4514;
                final int anInt4515 = this.aHa_Sub2_4797.anInt4490;
                final int anInt4516 = this.aHa_Sub2_4797.anInt4502;
                if (n == -1) {
                    for (int i = 0; i < this.anInt4786; ++i) {
                        final int n21 = this.anIntArray4748[i];
                        final int n22 = this.anIntArray4788[i];
                        final int n23 = this.anIntArray4762[i];
                        final float n24 = n9 + aFloat4700 * n21 + n10 * n22 + aFloat4703 * n23;
                        final float n25 = n15 + aFloat4701 * n21 + n16 * n22 + aFloat4704 * n23;
                        final float n26 = n3 + aFloat4702 * n21 + n4 * n22 + aFloat4705 * n23;
                        this.anIntArray4766[i] = (int)n26;
                        if (n26 >= anInt4516) {
                            this.anIntArray4771[i] = this.aClass235_4807.anInt1771 + (int)(n24 * anInt4514 / n26);
                            this.anIntArray4812[i] = this.aClass235_4807.anInt1779 + (int)(n25 * anInt4515 / n26);
                        }
                        else {
                            this.anIntArray4771[i] = -5000;
                            b2 = true;
                        }
                        if (b3) {
                            this.anIntArray4828[i] = (int)n24;
                            this.anIntArray4763[i] = (int)n25;
                            this.anIntArray4829[i] = (int)n26;
                        }
                        if (this.aClass235_4807.aBoolean1759) {
                            this.anIntArray4817[i] = (int)(this.aClass111_Sub2_4819.aFloat4691 + (this.aClass111_Sub2_4819.aFloat4692 * n21 + this.aClass111_Sub2_4819.aFloat4688 * n22 + this.aClass111_Sub2_4819.aFloat4696 * n23));
                        }
                    }
                    if (this.aClass170Array4827 != null) {
                        for (int j = 0; j < this.anInt4776; ++j) {
                            final Class170 class112 = this.aClass170Array4827[j];
                            final Class329 class113 = this.aClass329Array4821[j];
                            final short n27 = this.aShortArray4782[class112.anInt1315];
                            final short n28 = this.aShortArray4750[class112.anInt1315];
                            final short n29 = this.aShortArray4832[class112.anInt1315];
                            final int n30 = (this.anIntArray4748[n27] + this.anIntArray4748[n28] + this.anIntArray4748[n29]) / 3;
                            final int n31 = (this.anIntArray4788[n27] + this.anIntArray4788[n28] + this.anIntArray4788[n29]) / 3;
                            final int n32 = (this.anIntArray4762[n27] + this.anIntArray4762[n28] + this.anIntArray4762[n29]) / 3;
                            final float n33 = class113.anInt2762 + (n9 + aFloat4700 * n30 + n10 * n31 + aFloat4703 * n32);
                            final float n34 = class113.anInt2772 + (n15 + aFloat4701 * n30 + n16 * n31 + aFloat4704 * n32);
                            final float n35 = n3 + aFloat4702 * n30 + n4 * n31 + aFloat4705 * n32;
                            if (n35 > this.aHa_Sub2_4797.anInt4502) {
                                class113.anInt2773 = this.aHa_Sub2_4797.anInt4510 + (int)(n33 * anInt4514 / n35);
                                class113.anInt2767 = this.aHa_Sub2_4797.anInt4511 + (int)(n34 * anInt4515 / n35);
                                class113.anInt2764 = (int)n35 - class112.anInt1316;
                                class113.anInt2766 = (int)(class113.anInt2763 * class112.aShort1317 * anInt4514 / (n35 * 128.0f));
                                class113.anInt2760 = (int)(class113.anInt2768 * class112.aShort1309 * anInt4515 / (n35 * 128.0f));
                            }
                            else {
                                final Class329 class114 = class113;
                                final Class329 class115 = class113;
                                final boolean b4 = false;
                                class115.anInt2760 = (b4 ? 1 : 0);
                                class114.anInt2766 = (b4 ? 1 : 0);
                            }
                        }
                    }
                }
                else {
                    for (int k = 0; k < this.anInt4786; ++k) {
                        final int n36 = this.anIntArray4748[k];
                        final int n37 = this.anIntArray4788[k];
                        final int n38 = this.anIntArray4762[k];
                        final float n39 = n9 + aFloat4700 * n36 + n10 * n37 + aFloat4703 * n38;
                        final float n40 = n15 + aFloat4701 * n36 + n16 * n37 + aFloat4704 * n38;
                        this.anIntArray4766[k] = (int)(n3 + aFloat4702 * n36 + n4 * n37 + aFloat4705 * n38);
                        this.anIntArray4771[k] = this.aClass235_4807.anInt1771 + (int)(n39 * anInt4514 / n);
                        this.anIntArray4812[k] = this.aClass235_4807.anInt1779 + (int)(n40 * anInt4515 / n);
                        if (b3) {
                            this.anIntArray4828[k] = (int)n39;
                            this.anIntArray4763[k] = (int)n40;
                            this.anIntArray4829[k] = n;
                        }
                        if (this.aClass235_4807.aBoolean1759) {
                            this.anIntArray4817[k] = (int)(this.aClass111_Sub2_4819.aFloat4691 + (this.aClass111_Sub2_4819.aFloat4692 * n36 + this.aClass111_Sub2_4819.aFloat4688 * n37 + this.aClass111_Sub2_4819.aFloat4696 * n38));
                        }
                    }
                    if (this.aClass170Array4827 != null) {
                        for (int l = 0; l < this.anInt4776; ++l) {
                            final Class170 class116 = this.aClass170Array4827[l];
                            final Class329 class117 = this.aClass329Array4821[l];
                            final short n41 = this.aShortArray4782[class116.anInt1315];
                            final short n42 = this.aShortArray4750[class116.anInt1315];
                            final short n43 = this.aShortArray4832[class116.anInt1315];
                            final int n44 = (this.anIntArray4748[n41] + this.anIntArray4748[n42] + this.anIntArray4748[n43]) / 3;
                            final int n45 = (this.anIntArray4788[n41] + this.anIntArray4788[n42] + this.anIntArray4788[n43]) / 3;
                            final int n46 = (this.anIntArray4762[n41] + this.anIntArray4762[n42] + this.anIntArray4762[n43]) / 3;
                            final float n47 = n9 + aFloat4700 * n44 + n10 * n45 + aFloat4703 * n46;
                            final float n48 = n15 + aFloat4701 * n44 + n16 * n45 + aFloat4704 * n46;
                            final float n49 = n3 + aFloat4702 * n44 + n4 * n45 + aFloat4705 * n46;
                            class117.anInt2773 = this.aHa_Sub2_4797.anInt4510 + (int)(n47 * anInt4514 / n);
                            class117.anInt2767 = this.aHa_Sub2_4797.anInt4511 + (int)(n48 * anInt4515 / n);
                            class117.anInt2764 = n - class116.anInt1316;
                            class117.anInt2766 = class117.anInt2763 * class116.aShort1317 * anInt4514 / (n << 7);
                            class117.anInt2760 = class117.anInt2768 * class116.aShort1309 * anInt4515 / (n << 7);
                        }
                    }
                }
                if (class246_Sub6 != null) {
                    boolean b5 = false;
                    boolean b6 = true;
                    final int n50 = this.aShort4794 + this.aShort4801 >> 1;
                    final int n51 = this.aShort4774 + this.aShort4753 >> 1;
                    final int n52 = n50;
                    final short aShort4770 = this.aShort4770;
                    final int n53 = n51;
                    final float n54 = n9 + aFloat4700 * n52 + n10 * aShort4770 + aFloat4703 * n53;
                    final float n55 = n15 + aFloat4701 * n52 + n16 * aShort4770 + aFloat4704 * n53;
                    final float n56 = n3 + aFloat4702 * n52 + n4 * aShort4770 + aFloat4705 * n53;
                    if (n56 >= anInt4516) {
                        int n57 = (int)n56;
                        if (n != -1) {
                            n57 = n;
                        }
                        class246_Sub6.anInt5111 = this.aHa_Sub2_4797.anInt4510 + (int)(n54 * anInt4514 / n57);
                        class246_Sub6.anInt5113 = this.aHa_Sub2_4797.anInt4511 + (int)(n55 * anInt4515 / n57);
                    }
                    else {
                        b5 = true;
                    }
                    final int n58 = n50;
                    final short aShort4771 = this.aShort4808;
                    final int n59 = n51;
                    final float n60 = n9 + aFloat4700 * n58 + n10 * aShort4771 + aFloat4703 * n59;
                    final float n61 = n15 + aFloat4701 * n58 + n16 * aShort4771 + aFloat4704 * n59;
                    final float n62 = n3 + aFloat4702 * n58 + n4 * aShort4771 + aFloat4705 * n59;
                    if (n62 >= anInt4516) {
                        int n63 = (int)n62;
                        if (n != -1) {
                            n63 = n;
                        }
                        class246_Sub6.anInt5110 = this.aHa_Sub2_4797.anInt4510 + (int)(n60 * anInt4514 / n63);
                        class246_Sub6.anInt5112 = this.aHa_Sub2_4797.anInt4511 + (int)(n61 * anInt4515 / n63);
                    }
                    else {
                        b5 = true;
                    }
                    if (b5) {
                        if (n56 < anInt4516 && n62 < anInt4516) {
                            b6 = false;
                        }
                        else if (n56 < anInt4516) {
                            final float n64 = (n62 - this.aHa_Sub2_4797.anInt4502) / (n62 - n56);
                            final int n65 = (int)(n60 + (n60 - n54) * n64);
                            final int n66 = (int)(n61 + (n61 - n55) * n64);
                            int n67 = anInt4516;
                            if (n != -1) {
                                n67 = n;
                            }
                            class246_Sub6.anInt5111 = this.aHa_Sub2_4797.anInt4510 + n65 * anInt4514 / n67;
                            class246_Sub6.anInt5113 = this.aHa_Sub2_4797.anInt4511 + n66 * anInt4515 / n67;
                        }
                        else if (n62 < anInt4516) {
                            final float n68 = (n56 - anInt4516) / (n56 - n62);
                            final int n69 = (int)(n54 + (n54 - n60) * n68);
                            final int n70 = (int)(n55 + (n55 - n61) * n68);
                            int n71 = anInt4516;
                            if (n != -1) {
                                n71 = n;
                            }
                            class246_Sub6.anInt5111 = this.aHa_Sub2_4797.anInt4510 + n69 * anInt4514 / n71;
                            class246_Sub6.anInt5113 = this.aHa_Sub2_4797.anInt4511 + n70 * anInt4515 / n71;
                        }
                    }
                    if (b6) {
                        if (n56 > n62) {
                            int n72 = (int)n56;
                            if (n != -1) {
                                n72 = n;
                            }
                            class246_Sub6.anInt5109 = this.aHa_Sub2_4797.anInt4510 + (int)((n54 + this.aShort4835) * anInt4514 / n72) - class246_Sub6.anInt5111;
                        }
                        else {
                            int n73 = (int)n62;
                            if (n != -1) {
                                n73 = n;
                            }
                            class246_Sub6.anInt5109 = this.aHa_Sub2_4797.anInt4510 + (int)((n60 + this.aShort4835) * anInt4514 / n73) - class246_Sub6.anInt5110;
                        }
                        class246_Sub6.aBoolean5114 = true;
                    }
                }
                this.method2354(true);
                this.aClass12_4768.aBoolean134 = ((n2 & 0x1) == 0x0);
                this.aClass12_4768.aBoolean130 = false;
                try {
                    this.method2359(b2, (this.aClass235_4807.aBoolean1758 && n8 > this.aClass235_4807.anInt1761) || this.aClass235_4807.aBoolean1759, n7, n8 - n7);
                }
                catch (Exception ex2) {}
                if (this.aClass170Array4827 != null) {
                    for (int n74 = 0; n74 < this.anInt4780; ++n74) {
                        this.anIntArray4758[n74] = -1;
                    }
                }
                this.aClass12_4768 = null;
                if (this.aHa_Sub2_4797.anInt4508 > 1) {
                    synchronized (this) {
                        this.aBoolean4765 = false;
                        this.notifyAll();
                    }
                }
            }
        }
    }
    
    @Override
    final Class87[] method2320() {
        return this.aClass87Array4813;
    }
    
    private final int method2349(final int n, int n2) {
        n2 = n2 * (n & 0x7F) >> 7;
        if (n2 < 2) {
            n2 = 2;
        }
        else if (n2 > 126) {
            n2 = 126;
        }
        return (n & 0xFF80) + n2;
    }
    
    private final void method2350() {
        synchronized (this) {
            for (int i = 0; i < this.anInt4814; ++i) {
                final int n = this.anIntArray4748[i];
                this.anIntArray4748[i] = this.anIntArray4762[i];
                this.anIntArray4762[i] = -n;
                if (this.aClass274Array4789[i] != null) {
                    final int anInt2044 = this.aClass274Array4789[i].anInt2044;
                    this.aClass274Array4789[i].anInt2044 = this.aClass274Array4789[i].anInt2042;
                    this.aClass274Array4789[i].anInt2042 = -anInt2044;
                }
            }
            if (this.aClass296Array4823 != null) {
                for (int j = 0; j < this.anInt4780; ++j) {
                    if (this.aClass296Array4823[j] != null) {
                        final int anInt2045 = this.aClass296Array4823[j].anInt2411;
                        this.aClass296Array4823[j].anInt2411 = this.aClass296Array4823[j].anInt2413;
                        this.aClass296Array4823[j].anInt2413 = -anInt2045;
                    }
                }
            }
            for (int k = this.anInt4814; k < this.anInt4786; ++k) {
                final int n2 = this.anIntArray4748[k];
                this.anIntArray4748[k] = this.anIntArray4762[k];
                this.anIntArray4762[k] = -n2;
            }
            this.anInt4784 = 0;
            this.aBoolean4778 = false;
        }
    }
    
    private final void method2351(final Thread thread) {
        final Class235 method1921 = this.aHa_Sub2_4797.method1921(thread);
        this.aClass12_4768 = method1921.aClass12_1767;
        if (method1921 != this.aClass235_4807) {
            this.aClass235_4807 = method1921;
            this.anIntArray4817 = this.aClass235_4807.anIntArray1794;
            this.anIntArray4828 = this.aClass235_4807.anIntArray1786;
            this.anIntArray4763 = this.aClass235_4807.anIntArray1765;
            this.anIntArray4829 = this.aClass235_4807.anIntArray1780;
            this.anIntArray4771 = this.aClass235_4807.anIntArray1796;
            this.anIntArray4812 = this.aClass235_4807.anIntArray1775;
            this.anIntArray4766 = this.aClass235_4807.anIntArray1766;
            this.anIntArray4767 = this.aClass235_4807.anIntArray1797;
            this.anIntArray4818 = this.aClass235_4807.anIntArray1791;
            this.anIntArray4747 = this.aClass235_4807.anIntArray1792;
            this.anIntArray4751 = this.aClass235_4807.anIntArray1789;
            this.anIntArray4811 = this.aClass235_4807.anIntArray1770;
            this.anIntArray4824 = this.aClass235_4807.anIntArray1777;
            this.anIntArray4749 = this.aClass235_4807.anIntArray1768;
            this.anIntArray4803 = this.aClass235_4807.anIntArray1782;
            this.anIntArray4820 = this.aClass235_4807.anIntArray1785;
            this.anIntArray4758 = this.aClass235_4807.anIntArray1773;
        }
    }
    
    @Override
    final void FA(final int n) {
        if ((this.anInt4816 & 0x6) != 0x6) {
            throw new IllegalStateException();
        }
        final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
        final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                final int n4 = this.anIntArray4788[i] * n3 - this.anIntArray4762[i] * n2 >> 14;
                this.anIntArray4762[i] = this.anIntArray4788[i] * n2 + this.anIntArray4762[i] * n3 >> 14;
                this.anIntArray4788[i] = n4;
            }
            this.method2364();
        }
    }
    
    @Override
    final int da() {
        return this.anInt4805;
    }
    
    @Override
    final void method2329(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n, final int n2) {
        this.method2348(class111, class246_Sub6, n, n2);
    }
    
    @Override
    final boolean r() {
        return this.aBoolean4802;
    }
    
    private final void method2352() {
        for (int i = 0; i < this.anInt4780; ++i) {
            if (((this.aShortArray4804 != null) ? this.aShortArray4804[i] : -1) == -1) {
                final int n = this.aShortArray4800[i] & 0xFFFF;
                final short method2117 = Class111_Sub2.method2117((n & 0xFFFFFF80) | (n & 0x7F) * this.anInt4761 >> 7, 98);
                if (this.anIntArray4836[i] == -1) {
                    final int n2 = this.anIntArray4791[i] & 0xFFFE0000;
                    this.anIntArray4791[i] = (n2 | Class246_Sub7.method3132(n2 >> 17, (byte)118, method2117));
                }
                else if (this.anIntArray4836[i] != -2) {
                    final int n3 = this.anIntArray4791[i] & 0xFFFE0000;
                    this.anIntArray4791[i] = (n3 | Class246_Sub7.method3132(n3 >> 17, (byte)118, method2117));
                    final int n4 = this.anIntArray4760[i] & 0xFFFE0000;
                    this.anIntArray4760[i] = (n4 | Class246_Sub7.method3132(n4 >> 17, (byte)118, method2117));
                    final int n5 = this.anIntArray4836[i] & 0xFFFE0000;
                    this.anIntArray4836[i] = (n5 | Class246_Sub7.method3132(n5 >> 17, (byte)118, method2117));
                }
            }
        }
        this.anInt4784 = 2;
    }
    
    private final void method2353() {
        if (this.anInt4784 == 0 && this.aClass274Array4789 == null) {
            if (this.aHa_Sub2_4797.anInt4508 > 1) {
                synchronized (this) {
                    this.method2347();
                }
            }
            else {
                this.method2347();
            }
        }
    }
    
    @Override
    final int fa() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4770;
    }
    
    @Override
    final int V() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4794;
    }
    
    @Override
    final void s(final int n) {
        if (this.aHa_Sub2_4797.anInt4508 > 1) {
            synchronized (this) {
                if ((this.anInt4816 & 0x10000) == 0x10000 && (n & 0x10000) == 0x0) {
                    this.method2354(true);
                }
                this.anInt4816 = n;
            }
        }
        else {
            if ((this.anInt4816 & 0x10000) == 0x10000 && (n & 0x10000) == 0x0) {
                this.method2354(true);
            }
            this.anInt4816 = n;
        }
    }
    
    @Override
    final void method2331(final Class111 class111, final int n, final boolean b) {
        if (this.aShortArray4790 != null) {
            final int[] array = new int[3];
            for (int i = 0; i < this.anInt4814; ++i) {
                if ((n & this.aShortArray4790[i]) != 0x0) {
                    if (b) {
                        class111.method2096(this.anIntArray4748[i], this.anIntArray4788[i], this.anIntArray4762[i], array);
                    }
                    else {
                        class111.method2103(this.anIntArray4748[i], this.anIntArray4788[i], this.anIntArray4762[i], array);
                    }
                    this.anIntArray4748[i] = array[0];
                    this.anIntArray4788[i] = array[1];
                    this.anIntArray4762[i] = array[2];
                }
            }
        }
    }
    
    @Override
    final void p(final int n, final int n2, final s s, final s s2, final int n3, final int n4, final int n5) {
        if (n == 3) {
            if ((this.anInt4816 & 0x7) != 0x7) {
                throw new IllegalStateException();
            }
        }
        else if ((this.anInt4816 & 0x2) != 0x2) {
            throw new IllegalStateException();
        }
        if (!this.aBoolean4778) {
            this.method2346();
        }
        final int n6 = n3 + this.aShort4794;
        final int n7 = n3 + this.aShort4801;
        final int n8 = n5 + this.aShort4774;
        final int n9 = n5 + this.aShort4753;
        if (n == 4 || (n6 >= 0 && n7 + s.anInt2206 >> s.anInt2200 < s.anInt2203 && n8 >= 0 && n9 + s.anInt2206 >> s.anInt2200 < s.anInt2204)) {
            final int[][] anIntArrayArray2201 = ((s_Sub3)s).anIntArrayArray2201;
            int[][] anIntArrayArray2202 = null;
            if (s2 != null) {
                anIntArrayArray2202 = ((s_Sub3)s2).anIntArrayArray2201;
            }
            if (n == 4 || n == 5) {
                if (s2 == null || n6 < 0 || n7 + s2.anInt2206 >> s2.anInt2200 >= s2.anInt2203 || n8 < 0 || n9 + s2.anInt2206 >> s2.anInt2200 >= s2.anInt2204) {
                    return;
                }
            }
            else {
                final int n10 = n6 >> s.anInt2200;
                final int n11 = n7 + (s.anInt2206 - 1) >> s.anInt2200;
                final int n12 = n8 >> s.anInt2200;
                final int n13 = n9 + (s.anInt2206 - 1) >> s.anInt2200;
                if (anIntArrayArray2201[n10][n12] == n4 && anIntArrayArray2201[n11][n12] == n4 && anIntArrayArray2201[n10][n13] == n4 && anIntArrayArray2201[n11][n13] == n4) {
                    return;
                }
            }
            synchronized (this) {
                if (n == 1) {
                    final int n14 = s.anInt2206 - 1;
                    for (int i = 0; i < this.anInt4814; ++i) {
                        final int n15 = this.anIntArray4748[i] + n3;
                        final int n16 = this.anIntArray4762[i] + n5;
                        final int n17 = n15 & n14;
                        final int n18 = n16 & n14;
                        final int n19 = n15 >> s.anInt2200;
                        final int n20 = n16 >> s.anInt2200;
                        this.anIntArray4788[i] = this.anIntArray4788[i] + ((anIntArrayArray2201[n19][n20] * (s.anInt2206 - n17) + anIntArrayArray2201[n19 + 1][n20] * n17 >> s.anInt2200) * (s.anInt2206 - n18) + (anIntArrayArray2201[n19][n20 + 1] * (s.anInt2206 - n17) + anIntArrayArray2201[n19 + 1][n20 + 1] * n17 >> s.anInt2200) * n18 >> s.anInt2200) - n4;
                    }
                    for (int j = this.anInt4814; j < this.anInt4786; ++j) {
                        final int n21 = this.anIntArray4748[j] + n3;
                        final int n22 = this.anIntArray4762[j] + n5;
                        final int n23 = n21 & n14;
                        final int n24 = n22 & n14;
                        final int n25 = n21 >> s.anInt2200;
                        final int n26 = n22 >> s.anInt2200;
                        if (n25 >= 0 && n25 < anIntArrayArray2201.length - 1 && n26 >= 0 && n26 < anIntArrayArray2201[0].length - 1) {
                            this.anIntArray4788[j] = this.anIntArray4788[j] + ((anIntArrayArray2201[n25][n26] * (s.anInt2206 - n23) + anIntArrayArray2201[n25 + 1][n26] * n23 >> s.anInt2200) * (s.anInt2206 - n24) + (anIntArrayArray2201[n25][n26 + 1] * (s.anInt2206 - n23) + anIntArrayArray2201[n25 + 1][n26 + 1] * n23 >> s.anInt2200) * n24 >> s.anInt2200) - n4;
                        }
                    }
                }
                else if (n == 2) {
                    final int n27 = s.anInt2206 - 1;
                    for (int k = 0; k < this.anInt4814; ++k) {
                        final int n28 = (this.anIntArray4788[k] << 16) / this.aShort4770;
                        if (n28 < n2) {
                            final int n29 = this.anIntArray4748[k] + n3;
                            final int n30 = this.anIntArray4762[k] + n5;
                            final int n31 = n29 & n27;
                            final int n32 = n30 & n27;
                            final int n33 = n29 >> s.anInt2200;
                            final int n34 = n30 >> s.anInt2200;
                            this.anIntArray4788[k] += (((anIntArrayArray2201[n33][n34] * (s.anInt2206 - n31) + anIntArrayArray2201[n33 + 1][n34] * n31 >> s.anInt2200) * (s.anInt2206 - n32) + (anIntArrayArray2201[n33][n34 + 1] * (s.anInt2206 - n31) + anIntArrayArray2201[n33 + 1][n34 + 1] * n31 >> s.anInt2200) * n32 >> s.anInt2200) - n4) * (n2 - n28) / n2;
                        }
                        else {
                            this.anIntArray4788[k] = this.anIntArray4788[k];
                        }
                    }
                    for (int l = this.anInt4814; l < this.anInt4786; ++l) {
                        final int n35 = (this.anIntArray4788[l] << 16) / this.aShort4770;
                        if (n35 < n2) {
                            final int n36 = this.anIntArray4748[l] + n3;
                            final int n37 = this.anIntArray4762[l] + n5;
                            final int n38 = n36 & n27;
                            final int n39 = n37 & n27;
                            final int n40 = n36 >> s.anInt2200;
                            final int n41 = n37 >> s.anInt2200;
                            if (n40 >= 0 && n40 < s.anInt2203 - 1 && n41 >= 0 && n41 < s.anInt2204 - 1) {
                                this.anIntArray4788[l] += (((anIntArrayArray2201[n40][n41] * (s.anInt2206 - n38) + anIntArrayArray2201[n40 + 1][n41] * n38 >> s.anInt2200) * (s.anInt2206 - n39) + (anIntArrayArray2201[n40][n41 + 1] * (s.anInt2206 - n38) + anIntArrayArray2201[n40 + 1][n41 + 1] * n38 >> s.anInt2200) * n39 >> s.anInt2200) - n4) * (n2 - n35) / n2;
                            }
                        }
                        else {
                            this.anIntArray4788[l] = this.anIntArray4788[l];
                        }
                    }
                }
                else if (n == 3) {
                    final int n42 = (n2 & 0xFF) * 4;
                    final int n43 = (n2 >> 8 & 0xFF) * 4;
                    final int n44 = (n2 >> 16 & 0xFF) << 6;
                    final int n45 = (n2 >> 24 & 0xFF) << 6;
                    if (n3 - (n42 >> 1) < 0 || n3 + (n42 >> 1) + s.anInt2206 >= s.anInt2203 << s.anInt2200 || n5 - (n43 >> 1) < 0 || n5 + (n43 >> 1) + s.anInt2206 >= s.anInt2204 << s.anInt2200) {
                        return;
                    }
                    this.method2336(n43, n42, s, n4, n3, n5, n44, 2, n45);
                }
                else if (n == 4) {
                    final int n46 = s2.anInt2206 - 1;
                    final short n47 = (short)(this.aShort4808 - this.aShort4770);
                    for (int n48 = 0; n48 < this.anInt4814; ++n48) {
                        final int n49 = this.anIntArray4748[n48] + n3;
                        final int n50 = this.anIntArray4762[n48] + n5;
                        final int n51 = n49 & n46;
                        final int n52 = n50 & n46;
                        final int n53 = n49 >> s2.anInt2200;
                        final int n54 = n50 >> s2.anInt2200;
                        this.anIntArray4788[n48] = this.anIntArray4788[n48] + (((anIntArrayArray2202[n53][n54] * (s2.anInt2206 - n51) + anIntArrayArray2202[n53 + 1][n54] * n51 >> s2.anInt2200) * (s2.anInt2206 - n52) + (anIntArrayArray2202[n53][n54 + 1] * (s2.anInt2206 - n51) + anIntArrayArray2202[n53 + 1][n54 + 1] * n51 >> s2.anInt2200) * n52 >> s2.anInt2200) - n4) + n47;
                    }
                    for (int anInt4814 = this.anInt4814; anInt4814 < this.anInt4786; ++anInt4814) {
                        final int n55 = this.anIntArray4748[anInt4814] + n3;
                        final int n56 = this.anIntArray4762[anInt4814] + n5;
                        final int n57 = n55 & n46;
                        final int n58 = n56 & n46;
                        final int n59 = n55 >> s2.anInt2200;
                        final int n60 = n56 >> s2.anInt2200;
                        if (n59 >= 0 && n59 < s2.anInt2203 - 1 && n60 >= 0 && n60 < s2.anInt2204 - 1) {
                            this.anIntArray4788[anInt4814] = this.anIntArray4788[anInt4814] + (((anIntArrayArray2202[n59][n60] * (s2.anInt2206 - n57) + anIntArrayArray2202[n59 + 1][n60] * n57 >> s2.anInt2200) * (s2.anInt2206 - n58) + (anIntArrayArray2202[n59][n60 + 1] * (s2.anInt2206 - n57) + anIntArrayArray2202[n59 + 1][n60 + 1] * n57 >> s2.anInt2200) * n58 >> s2.anInt2200) - n4) + n47;
                        }
                    }
                }
                else if (n == 5) {
                    final int n61 = s2.anInt2206 - 1;
                    final short n62 = (short)(this.aShort4808 - this.aShort4770);
                    for (int n63 = 0; n63 < this.anInt4814; ++n63) {
                        final int n64 = this.anIntArray4748[n63] + n3;
                        final int n65 = this.anIntArray4762[n63] + n5;
                        final int n66 = n64 & n61;
                        final int n67 = n65 & n61;
                        final int n68 = n64 >> s.anInt2200;
                        final int n69 = n65 >> s.anInt2200;
                        final int n70 = (anIntArrayArray2201[n68][n69] * (s.anInt2206 - n66) + anIntArrayArray2201[n68 + 1][n69] * n66 >> s.anInt2200) * (s.anInt2206 - n67) + (anIntArrayArray2201[n68][n69 + 1] * (s.anInt2206 - n66) + anIntArrayArray2201[n68 + 1][n69 + 1] * n66 >> s.anInt2200) * n67 >> s.anInt2200;
                        this.anIntArray4788[n63] = ((this.anIntArray4788[n63] << 8) / n62 * (n70 - ((anIntArrayArray2202[n68][n69] * (s2.anInt2206 - n66) + anIntArrayArray2202[n68 + 1][n69] * n66 >> s2.anInt2200) * (s2.anInt2206 - n67) + (anIntArrayArray2202[n68][n69 + 1] * (s2.anInt2206 - n66) + anIntArrayArray2202[n68 + 1][n69 + 1] * n66 >> s2.anInt2200) * n67 >> s2.anInt2200) - n2) >> 8) - (n4 - n70);
                    }
                    for (int anInt4815 = this.anInt4814; anInt4815 < this.anInt4786; ++anInt4815) {
                        final int n71 = this.anIntArray4748[anInt4815] + n3;
                        final int n72 = this.anIntArray4762[anInt4815] + n5;
                        final int n73 = n71 & n61;
                        final int n74 = n72 & n61;
                        final int n75 = n71 >> s.anInt2200;
                        final int n76 = n72 >> s.anInt2200;
                        if (n75 >= 0 && n75 < s.anInt2203 - 1 && n75 < s2.anInt2203 - 1 && n76 >= 0 && n76 < s.anInt2204 - 1 && n76 < s2.anInt2204 - 1) {
                            final int n77 = (anIntArrayArray2201[n75][n76] * (s.anInt2206 - n73) + anIntArrayArray2201[n75 + 1][n76] * n73 >> s.anInt2200) * (s.anInt2206 - n74) + (anIntArrayArray2201[n75][n76 + 1] * (s.anInt2206 - n73) + anIntArrayArray2201[n75 + 1][n76 + 1] * n73 >> s.anInt2200) * n74 >> s.anInt2200;
                            this.anIntArray4788[anInt4815] = ((this.anIntArray4788[anInt4815] << 8) / n62 * (n77 - ((anIntArrayArray2202[n75][n76] * (s2.anInt2206 - n73) + anIntArrayArray2202[n75 + 1][n76] * n73 >> s2.anInt2200) * (s2.anInt2206 - n74) + (anIntArrayArray2202[n75][n76 + 1] * (s2.anInt2206 - n73) + anIntArrayArray2202[n75 + 1][n76 + 1] * n73 >> s2.anInt2200) * n74 >> s2.anInt2200) - n2) >> 8) - (n4 - n77);
                        }
                    }
                }
                this.aBoolean4778 = false;
            }
        }
    }
    
    private final void method2354(final boolean b) {
        if (this.aHa_Sub2_4797.anInt4508 > 1) {
            synchronized (this) {
                this.method2363(b);
            }
        }
        else {
            this.method2363(b);
        }
    }
    
    private final void method2355(final Thread thread) {
        final Class235 method1921 = this.aHa_Sub2_4797.method1921(thread);
        if (method1921 != this.aClass235_4752) {
            this.aClass235_4752 = method1921;
            this.aClass146_Sub1_4764 = this.aClass235_4752.aClass146_Sub1_1774;
            this.aClass146_Sub1_4773 = this.aClass235_4752.aClass146_Sub1_1790;
            this.aClass146_Sub1_4779 = this.aClass235_4752.aClass146_Sub1_1778;
            this.aClass146_Sub1_4775 = this.aClass235_4752.aClass146_Sub1_1769;
            this.aClass146_Sub1_4759 = this.aClass235_4752.aClass146_Sub1_1776;
            this.aClass146_Sub1_4756 = this.aClass235_4752.aClass146_Sub1_1800;
            this.aClass146_Sub1_4783 = this.aClass235_4752.aClass146_Sub1_1787;
            this.aClass146_Sub1_4785 = this.aClass235_4752.aClass146_Sub1_1793;
            this.aClass146_Sub1_4831 = this.aClass235_4752.aClass146_Sub1_1798;
            this.aClass146_Sub1_4769 = this.aClass235_4752.aClass146_Sub1_1772;
        }
    }
    
    private final int method2356(int n) {
        if (n < 2) {
            n = 2;
        }
        else if (n > 126) {
            n = 126;
        }
        return n;
    }
    
    @Override
    final boolean F() {
        return this.aBoolean4755;
    }
    
    private final void method2357() {
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                final int n = this.anIntArray4748[i];
                this.anIntArray4748[i] = this.anIntArray4762[i];
                this.anIntArray4762[i] = -n;
            }
            this.method2364();
        }
    }
    
    @Override
    final boolean method2339(final int n, final int n2, final Class111 class111, final boolean b, final int n3) {
        return this.method2372(n, n2, class111, b, n3, -1);
    }
    
    @Override
    final int RA() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4801;
    }
    
    private final boolean method2358(final int n) {
        return this.anIntArray4758 != null && this.anIntArray4758[n] != -1;
    }
    
    private final void method2359(final boolean b, final boolean b2, final int n, final int n2) {
        if (this.aClass170Array4827 != null) {
            for (int i = 0; i < this.anInt4776; ++i) {
                this.anIntArray4758[this.aClass170Array4827[i].anInt1315] = i;
            }
        }
        if (this.aBoolean4755 || this.aClass170Array4827 != null) {
            if ((this.anInt4816 & 0x100) == 0x0 && this.aShortArray4809 != null) {
                for (int j = 0; j < this.anInt4780; ++j) {
                    this.method2371(this.aShortArray4809[j], b, b2);
                }
            }
            else {
                for (int k = 0; k < this.anInt4780; ++k) {
                    if (!this.method2362(k) && !this.method2358(k)) {
                        this.method2371(k, b, b2);
                    }
                }
                if (this.aByteArray4772 == null) {
                    for (int l = 0; l < this.anInt4780; ++l) {
                        if (this.method2362(l) || this.method2358(l)) {
                            this.method2371(l, b, b2);
                        }
                    }
                }
                else {
                    for (byte b3 = 0; b3 < 12; ++b3) {
                        for (int n3 = 0; n3 < this.anInt4780; ++n3) {
                            if (this.aByteArray4772[n3] == b3 && (this.method2362(n3) || this.method2358(n3))) {
                                this.method2371(n3, b, b2);
                            }
                        }
                    }
                }
            }
        }
        else {
            for (int n4 = 0; n4 < this.anInt4780; ++n4) {
                this.method2371(n4, b, b2);
            }
        }
    }
    
    @Override
    final int na() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4835;
    }
    
    @Override
    final void method2325(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n) {
        this.method2348(class111, class246_Sub6, -1, n);
    }
    
    @Override
    final int HA() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4774;
    }
    
    private final int method2360(final int n, final short n2, final int n3) {
        int n4 = Class208.anIntArray1579[this.method2349(n, n3)];
        final Class238 method11 = this.aHa_Sub2_4797.aD938.method11(n2 & 0xFFFF, -28755);
        final int n5 = method11.aByte1830 & 0xFF;
        if (n5 != 0) {
            final int n6 = 131586 * n3;
            if (n5 == 256) {
                n4 = n6;
            }
            else {
                final int n7 = n5;
                final int n8 = 256 - n5;
                n4 = ((n6 & 0xFF00FF) * n7 + (n4 & 0xFF00FF) * n8 & 0xFF00FF00) + ((n6 & 0xFF00) * n7 + (n4 & 0xFF00) * n8 & 0xFF0000) >> 8;
            }
        }
        int n9 = method11.aByte1829 & 0xFF;
        if (n9 != 0) {
            n9 += 256;
            int n10 = ((n4 & 0xFF0000) >> 16) * n9;
            if (n10 > 65535) {
                n10 = 65535;
            }
            int n11 = ((n4 & 0xFF00) >> 8) * n9;
            if (n11 > 65535) {
                n11 = 65535;
            }
            int n12 = (n4 & 0xFF) * n9;
            if (n12 > 65535) {
                n12 = 65535;
            }
            n4 = (n10 << 8 & 0xFF0000) + (n11 & 0xFF00) + (n12 >> 8);
        }
        return n4;
    }
    
    private final boolean method2361(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        return (n2 >= n3 || n2 >= n4 || n2 >= n5) && (n2 <= n3 || n2 <= n4 || n2 <= n5) && (n >= n6 || n >= n7 || n >= n8) && (n <= n6 || n <= n7 || n <= n8);
    }
    
    @Override
    final Class146 method2341(final byte b, final int n, final boolean b2) {
        this.method2355(Thread.currentThread());
        Class146_Sub1 class146_Sub1;
        Class146_Sub1 class146_Sub2;
        if (b == 1) {
            class146_Sub1 = this.aClass146_Sub1_4756;
            class146_Sub2 = this.aClass146_Sub1_4764;
        }
        else if (b == 2) {
            class146_Sub1 = this.aClass146_Sub1_4783;
            class146_Sub2 = this.aClass146_Sub1_4773;
        }
        else if (b == 3) {
            class146_Sub1 = this.aClass146_Sub1_4785;
            class146_Sub2 = this.aClass146_Sub1_4779;
        }
        else if (b == 4) {
            class146_Sub1 = this.aClass146_Sub1_4831;
            class146_Sub2 = this.aClass146_Sub1_4775;
        }
        else if (b == 5) {
            class146_Sub1 = this.aClass146_Sub1_4769;
            class146_Sub2 = this.aClass146_Sub1_4759;
        }
        else {
            class146_Sub1 = (class146_Sub2 = new Class146_Sub1(this.aHa_Sub2_4797));
        }
        return this.method2367(class146_Sub2, class146_Sub1, n, b != 0, b2);
    }
    
    private final boolean method2362(final int n) {
        return this.aByteArray4822 != null && this.aByteArray4822[n] != 0;
    }
    
    private final void method2363(final boolean b) {
        if (this.anInt4784 == 1) {
            this.method2366();
        }
        else if (this.anInt4784 == 2) {
            if ((this.anInt4816 & 0x97098) == 0x0 && this.aFloatArrayArray4798 == null) {
                this.aShortArray4800 = null;
            }
            if (b) {
                this.aByteArray4793 = null;
            }
        }
        else {
            this.method2353();
            final int anInt4481 = this.aHa_Sub2_4797.anInt4481;
            final int anInt4482 = this.aHa_Sub2_4797.anInt4515;
            final int anInt4483 = this.aHa_Sub2_4797.anInt4500;
            final int n = this.aHa_Sub2_4797.anInt4516 >> 8;
            final int n2 = this.aHa_Sub2_4797.anInt4501 * 768 / this.anInt4805;
            final int n3 = this.aHa_Sub2_4797.anInt4489 * 768 / this.anInt4805;
            if (this.anIntArray4791 == null) {
                this.anIntArray4791 = new int[this.anInt4780];
                this.anIntArray4760 = new int[this.anInt4780];
                this.anIntArray4836 = new int[this.anInt4780];
            }
            for (int i = 0; i < this.anInt4780; ++i) {
                int n4;
                if (this.aByteArray4793 == null) {
                    n4 = 0;
                }
                else {
                    n4 = this.aByteArray4793[i];
                }
                byte b2;
                if (this.aByteArray4822 == null) {
                    b2 = 0;
                }
                else {
                    b2 = this.aByteArray4822[i];
                }
                short n5;
                if (this.aShortArray4804 == null) {
                    n5 = -1;
                }
                else {
                    n5 = this.aShortArray4804[i];
                }
                if (b2 == -2) {
                    n4 = 3;
                }
                if (b2 == -1) {
                    n4 = 2;
                }
                if (n5 == -1) {
                    if (n4 == 0) {
                        final int n6 = this.aShortArray4800[i] & 0xFFFF;
                        final short method2117 = Class111_Sub2.method2117((n6 & 0xFFFFFF80) | (n6 & 0x7F) * this.anInt4761 >> 7, 76);
                        Class274 class274;
                        if (this.aClass274Array4777 != null && this.aClass274Array4777[this.aShortArray4782[i]] != null) {
                            class274 = this.aClass274Array4777[this.aShortArray4782[i]];
                        }
                        else {
                            class274 = this.aClass274Array4789[this.aShortArray4782[i]];
                        }
                        final int n7 = (anInt4481 * class274.anInt2044 + anInt4482 * class274.anInt2043 + anInt4483 * class274.anInt2042) / class274.anInt2045 >> 16;
                        final int n8 = (n >> 1) + (((n7 > 256) ? n2 : n3) * n7 >> 17);
                        this.anIntArray4791[i] = (n8 << 17 | Class246_Sub7.method3132(n8, (byte)118, method2117));
                        Class274 class275;
                        if (this.aClass274Array4777 != null && this.aClass274Array4777[this.aShortArray4750[i]] != null) {
                            class275 = this.aClass274Array4777[this.aShortArray4750[i]];
                        }
                        else {
                            class275 = this.aClass274Array4789[this.aShortArray4750[i]];
                        }
                        final int n9 = (anInt4481 * class275.anInt2044 + anInt4482 * class275.anInt2043 + anInt4483 * class275.anInt2042) / class275.anInt2045 >> 16;
                        final int n10 = (n >> 1) + (((n9 > 256) ? n2 : n3) * n9 >> 17);
                        this.anIntArray4760[i] = (n10 << 17 | Class246_Sub7.method3132(n10, (byte)118, method2117));
                        Class274 class276;
                        if (this.aClass274Array4777 != null && this.aClass274Array4777[this.aShortArray4832[i]] != null) {
                            class276 = this.aClass274Array4777[this.aShortArray4832[i]];
                        }
                        else {
                            class276 = this.aClass274Array4789[this.aShortArray4832[i]];
                        }
                        final int n11 = (anInt4481 * class276.anInt2044 + anInt4482 * class276.anInt2043 + anInt4483 * class276.anInt2042) / class276.anInt2045 >> 16;
                        final int n12 = (n >> 1) + (((n11 > 256) ? n2 : n3) * n11 >> 17);
                        this.anIntArray4836[i] = (n12 << 17 | Class246_Sub7.method3132(n12, (byte)118, method2117));
                    }
                    else if (n4 == 1) {
                        final int n13 = this.aShortArray4800[i] & 0xFFFF;
                        final short method2118 = Class111_Sub2.method2117((n13 & 0xFFFFFF80) | (n13 & 0x7F) * this.anInt4761 >> 7, -97);
                        final Class296 class277 = this.aClass296Array4823[i];
                        final int n14 = anInt4481 * class277.anInt2411 + anInt4482 * class277.anInt2412 + anInt4483 * class277.anInt2413 >> 16;
                        final int n15 = (n >> 1) + (((n14 > 256) ? n2 : n3) * n14 >> 17);
                        this.anIntArray4791[i] = (n15 << 17 | Class246_Sub7.method3132(n15, (byte)118, method2118));
                        this.anIntArray4836[i] = -1;
                    }
                    else if (n4 == 3) {
                        this.anIntArray4791[i] = 128;
                        this.anIntArray4836[i] = -1;
                    }
                    else {
                        this.anIntArray4836[i] = -2;
                    }
                }
                else {
                    final int n16 = this.aShortArray4800[i] & 0xFFFF;
                    if (n4 == 0) {
                        Class274 class278;
                        if (this.aClass274Array4777 != null && this.aClass274Array4777[this.aShortArray4782[i]] != null) {
                            class278 = this.aClass274Array4777[this.aShortArray4782[i]];
                        }
                        else {
                            class278 = this.aClass274Array4789[this.aShortArray4782[i]];
                        }
                        final int n17 = (anInt4481 * class278.anInt2044 + anInt4482 * class278.anInt2043 + anInt4483 * class278.anInt2042) / class278.anInt2045 >> 16;
                        final int method2119 = this.method2356((n >> 2) + (((n17 > 256) ? n2 : n3) * n17 >> 18));
                        this.anIntArray4791[i] = (method2119 << 24 | this.method2360(n16, n5, method2119));
                        Class274 class279;
                        if (this.aClass274Array4777 != null && this.aClass274Array4777[this.aShortArray4750[i]] != null) {
                            class279 = this.aClass274Array4777[this.aShortArray4750[i]];
                        }
                        else {
                            class279 = this.aClass274Array4789[this.aShortArray4750[i]];
                        }
                        final int n18 = (anInt4481 * class279.anInt2044 + anInt4482 * class279.anInt2043 + anInt4483 * class279.anInt2042) / class279.anInt2045 >> 16;
                        final int method2120 = this.method2356((n >> 2) + (((n18 > 256) ? n2 : n3) * n18 >> 18));
                        this.anIntArray4760[i] = (method2120 << 24 | this.method2360(n16, n5, method2120));
                        Class274 class280;
                        if (this.aClass274Array4777 != null && this.aClass274Array4777[this.aShortArray4832[i]] != null) {
                            class280 = this.aClass274Array4777[this.aShortArray4832[i]];
                        }
                        else {
                            class280 = this.aClass274Array4789[this.aShortArray4832[i]];
                        }
                        final int n19 = (anInt4481 * class280.anInt2044 + anInt4482 * class280.anInt2043 + anInt4483 * class280.anInt2042) / class280.anInt2045 >> 16;
                        final int method2121 = this.method2356((n >> 2) + (((n19 > 256) ? n2 : n3) * n19 >> 18));
                        this.anIntArray4836[i] = (method2121 << 24 | this.method2360(n16, n5, method2121));
                    }
                    else if (n4 == 1) {
                        final Class296 class281 = this.aClass296Array4823[i];
                        final int n20 = anInt4481 * class281.anInt2411 + anInt4482 * class281.anInt2412 + anInt4483 * class281.anInt2413 >> 16;
                        final int method2122 = this.method2356((n >> 2) + (((n20 > 256) ? n2 : n3) * n20 >> 18));
                        this.anIntArray4791[i] = (method2122 << 24 | this.method2360(n16, n5, method2122));
                        this.anIntArray4836[i] = -1;
                    }
                    else {
                        this.anIntArray4836[i] = -2;
                    }
                }
            }
            this.aClass274Array4789 = null;
            this.aClass274Array4777 = null;
            this.aClass296Array4823 = null;
            if ((this.anInt4816 & 0x97098) == 0x0 && this.aFloatArrayArray4798 == null) {
                this.aShortArray4800 = null;
            }
            if (b) {
                this.aByteArray4793 = null;
            }
            this.anInt4784 = 2;
        }
    }
    
    @Override
    final void wa() {
        if (this.aBoolean4815) {
            for (int i = 0; i < this.anInt4786; ++i) {
                this.anIntArray4748[i] = this.anIntArray4748[i] + 7 >> 4;
                this.anIntArray4788[i] = this.anIntArray4788[i] + 7 >> 4;
                this.anIntArray4762[i] = this.anIntArray4762[i] + 7 >> 4;
            }
            this.aBoolean4815 = false;
        }
        if (this.aBoolean4754) {
            this.method2366();
            this.aBoolean4754 = false;
        }
        this.aBoolean4778 = false;
    }
    
    @Override
    final void a(final int n) {
        if ((this.anInt4816 & 0x5) != 0x5) {
            throw new IllegalStateException();
        }
        if (n == 4096) {
            this.method2357();
        }
        else if (n == 8192) {
            this.method2370();
        }
        else if (n == 12288) {
            this.method2374();
        }
        else {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            synchronized (this) {
                for (int i = 0; i < this.anInt4786; ++i) {
                    final int n4 = this.anIntArray4762[i] * n2 + this.anIntArray4748[i] * n3 >> 14;
                    this.anIntArray4762[i] = this.anIntArray4762[i] * n3 - this.anIntArray4748[i] * n2 >> 14;
                    this.anIntArray4748[i] = n4;
                }
                this.method2364();
            }
        }
    }
    
    @Override
    final void P(final int n, final int anInt4792, final int anInt4793, final int anInt4794) {
        if (n == 0) {
            int n2 = 0;
            this.anInt4792 = 0;
            this.anInt4781 = 0;
            this.anInt4757 = 0;
            for (int i = 0; i < this.anInt4786; ++i) {
                this.anInt4792 += this.anIntArray4748[i];
                this.anInt4781 += this.anIntArray4788[i];
                this.anInt4757 += this.anIntArray4762[i];
                ++n2;
            }
            if (n2 > 0) {
                this.anInt4792 = this.anInt4792 / n2 + anInt4792;
                this.anInt4781 = this.anInt4781 / n2 + anInt4793;
                this.anInt4757 = this.anInt4757 / n2 + anInt4794;
            }
            else {
                this.anInt4792 = anInt4792;
                this.anInt4781 = anInt4793;
                this.anInt4757 = anInt4794;
            }
        }
        else if (n == 1) {
            for (int j = 0; j < this.anInt4786; ++j) {
                final int[] anIntArray4748 = this.anIntArray4748;
                final int n3 = j;
                anIntArray4748[n3] += anInt4792;
                final int[] anIntArray4749 = this.anIntArray4788;
                final int n4 = j;
                anIntArray4749[n4] += anInt4793;
                final int[] anIntArray4750 = this.anIntArray4762;
                final int n5 = j;
                anIntArray4750[n5] += anInt4794;
            }
        }
        else if (n == 2) {
            for (int k = 0; k < this.anInt4786; ++k) {
                final int[] anIntArray4751 = this.anIntArray4748;
                final int n6 = k;
                anIntArray4751[n6] -= this.anInt4792;
                final int[] anIntArray4752 = this.anIntArray4788;
                final int n7 = k;
                anIntArray4752[n7] -= this.anInt4781;
                final int[] anIntArray4753 = this.anIntArray4762;
                final int n8 = k;
                anIntArray4753[n8] -= this.anInt4757;
                if (anInt4794 != 0) {
                    final int n9 = Class284_Sub2_Sub2.anIntArray6200[anInt4794];
                    final int n10 = Class284_Sub2_Sub2.anIntArray6202[anInt4794];
                    final int n11 = this.anIntArray4788[k] * n9 + this.anIntArray4748[k] * n10 + 16383 >> 14;
                    this.anIntArray4788[k] = this.anIntArray4788[k] * n10 - this.anIntArray4748[k] * n9 + 16383 >> 14;
                    this.anIntArray4748[k] = n11;
                }
                if (anInt4792 != 0) {
                    final int n12 = Class284_Sub2_Sub2.anIntArray6200[anInt4792];
                    final int n13 = Class284_Sub2_Sub2.anIntArray6202[anInt4792];
                    final int n14 = this.anIntArray4788[k] * n13 - this.anIntArray4762[k] * n12 + 16383 >> 14;
                    this.anIntArray4762[k] = this.anIntArray4788[k] * n12 + this.anIntArray4762[k] * n13 + 16383 >> 14;
                    this.anIntArray4788[k] = n14;
                }
                if (anInt4793 != 0) {
                    final int n15 = Class284_Sub2_Sub2.anIntArray6200[anInt4793];
                    final int n16 = Class284_Sub2_Sub2.anIntArray6202[anInt4793];
                    final int n17 = this.anIntArray4762[k] * n15 + this.anIntArray4748[k] * n16 + 16383 >> 14;
                    this.anIntArray4762[k] = this.anIntArray4762[k] * n16 - this.anIntArray4748[k] * n15 + 16383 >> 14;
                    this.anIntArray4748[k] = n17;
                }
                final int[] anIntArray4754 = this.anIntArray4748;
                final int n18 = k;
                anIntArray4754[n18] += this.anInt4792;
                final int[] anIntArray4755 = this.anIntArray4788;
                final int n19 = k;
                anIntArray4755[n19] += this.anInt4781;
                final int[] anIntArray4756 = this.anIntArray4762;
                final int n20 = k;
                anIntArray4756[n20] += this.anInt4757;
            }
        }
        else if (n == 3) {
            for (int l = 0; l < this.anInt4786; ++l) {
                final int[] anIntArray4757 = this.anIntArray4748;
                final int n21 = l;
                anIntArray4757[n21] -= this.anInt4792;
                final int[] anIntArray4758 = this.anIntArray4788;
                final int n22 = l;
                anIntArray4758[n22] -= this.anInt4781;
                final int[] anIntArray4759 = this.anIntArray4762;
                final int n23 = l;
                anIntArray4759[n23] -= this.anInt4757;
                this.anIntArray4748[l] = this.anIntArray4748[l] * anInt4792 / 128;
                this.anIntArray4788[l] = this.anIntArray4788[l] * anInt4793 / 128;
                this.anIntArray4762[l] = this.anIntArray4762[l] * anInt4794 / 128;
                final int[] anIntArray4760 = this.anIntArray4748;
                final int n24 = l;
                anIntArray4760[n24] += this.anInt4792;
                final int[] anIntArray4761 = this.anIntArray4788;
                final int n25 = l;
                anIntArray4761[n25] += this.anInt4781;
                final int[] anIntArray4762 = this.anIntArray4762;
                final int n26 = l;
                anIntArray4762[n26] += this.anInt4757;
            }
        }
        else if (n == 5) {
            for (int n27 = 0; n27 < this.anInt4780; ++n27) {
                int n28 = (this.aByteArray4822[n27] & 0xFF) + anInt4792 * 8;
                if (n28 < 0) {
                    n28 = 0;
                }
                else if (n28 > 255) {
                    n28 = 255;
                }
                this.aByteArray4822[n27] = (byte)n28;
            }
            if (this.aClass170Array4827 != null) {
                for (int n29 = 0; n29 < this.anInt4776; ++n29) {
                    final Class170 class170 = this.aClass170Array4827[n29];
                    final Class329 class171 = this.aClass329Array4821[n29];
                    class171.anInt2770 = ((class171.anInt2770 & 0xFFFFFF) | 255 - (this.aByteArray4822[class170.anInt1315] & 0xFF) << 24);
                }
            }
        }
        else if (n == 7) {
            for (int n30 = 0; n30 < this.anInt4780; ++n30) {
                final int n31 = this.aShortArray4800[n30] & 0xFFFF;
                final int n32 = n31 >> 10 & 0x3F;
                final int n33 = n31 >> 7 & 0x7;
                final int n34 = n31 & 0x7F;
                final int n35 = n32 + anInt4792 & 0x3F;
                int n36 = n33 + anInt4793;
                if (n36 < 0) {
                    n36 = 0;
                }
                else if (n36 > 7) {
                    n36 = 7;
                }
                int n37 = n34 + anInt4794;
                if (n37 < 0) {
                    n37 = 0;
                }
                else if (n37 > 127) {
                    n37 = 127;
                }
                this.aShortArray4800[n30] = (short)(n35 << 10 | n36 << 7 | n37);
            }
            this.aBoolean4754 = true;
            if (this.aClass170Array4827 != null) {
                for (int n38 = 0; n38 < this.anInt4776; ++n38) {
                    final Class170 class172 = this.aClass170Array4827[n38];
                    final Class329 class173 = this.aClass329Array4821[n38];
                    class173.anInt2770 = ((class173.anInt2770 & 0xFF000000) | (Class221.anIntArray1665[Class111_Sub2.method2117(this.aShortArray4800[class172.anInt1315] & 0xFFFF, 83) & 0xFFFF] & 0xFFFFFF));
                }
            }
        }
        else if (n == 8) {
            for (int n39 = 0; n39 < this.anInt4776; ++n39) {
                final Class329 class175;
                final Class329 class174 = class175 = this.aClass329Array4821[n39];
                class175.anInt2762 += anInt4792;
                final Class329 class176 = class174;
                class176.anInt2772 += anInt4793;
            }
        }
        else if (n == 10) {
            for (int n40 = 0; n40 < this.anInt4776; ++n40) {
                final Class329 class177 = this.aClass329Array4821[n40];
                class177.anInt2763 = class177.anInt2763 * anInt4792 >> 7;
                class177.anInt2768 = class177.anInt2768 * anInt4793 >> 7;
            }
        }
        else if (n == 9) {
            for (int n41 = 0; n41 < this.anInt4776; ++n41) {
                final Class329 class178 = this.aClass329Array4821[n41];
                class178.anInt2769 = (class178.anInt2769 + anInt4792 & 0x3FFF);
            }
        }
    }
    
    @Override
    final void ia(final short n, final short n2) {
        for (int i = 0; i < this.anInt4780; ++i) {
            if (this.aShortArray4800[i] == n) {
                this.aShortArray4800[i] = n2;
            }
        }
        if (this.aClass170Array4827 != null) {
            for (int j = 0; j < this.anInt4776; ++j) {
                final Class170 class170 = this.aClass170Array4827[j];
                final Class329 class171 = this.aClass329Array4821[j];
                class171.anInt2770 = ((class171.anInt2770 & 0xFF000000) | (Class221.anIntArray1665[Class111_Sub2.method2117(this.aShortArray4800[class170.anInt1315], -54) & 0xFFFF] & 0xFFFFFF));
            }
        }
        if (this.anInt4784 == 2) {
            this.anInt4784 = 1;
        }
    }
    
    private final void method2364() {
        this.aClass274Array4789 = null;
        this.aClass274Array4777 = null;
        this.aClass296Array4823 = null;
        this.aBoolean4778 = false;
    }
    
    @Override
    final void method2332(final Class146 class146, final int n, final int n2, final int n3, final boolean b) {
        final Class146_Sub1 class146_Sub1 = (Class146_Sub1)class146;
        if ((this.anInt4816 & 0x10000) != 0x10000) {
            throw new IllegalStateException("");
        }
        if ((class146_Sub1.anInt4816 & 0x10000) != 0x10000) {
            throw new IllegalStateException("");
        }
        this.method2351(Thread.currentThread());
        this.method2346();
        this.method2353();
        class146_Sub1.method2346();
        class146_Sub1.method2353();
        ++Class146_Sub1.anInt4830;
        int n4 = 0;
        final int[] anIntArray4748 = class146_Sub1.anIntArray4748;
        final int anInt4814 = class146_Sub1.anInt4814;
        for (int i = 0; i < this.anInt4814; ++i) {
            final Class274 class147 = this.aClass274Array4789[i];
            if (class147.anInt2045 != 0) {
                final int n5 = this.anIntArray4788[i] - n2;
                if (n5 >= class146_Sub1.aShort4770 && n5 <= class146_Sub1.aShort4808) {
                    final int n6 = this.anIntArray4748[i] - n;
                    if (n6 >= class146_Sub1.aShort4794 && n6 <= class146_Sub1.aShort4801) {
                        final int n7 = this.anIntArray4762[i] - n3;
                        if (n7 >= class146_Sub1.aShort4774 && n7 <= class146_Sub1.aShort4753) {
                            for (int j = 0; j < anInt4814; ++j) {
                                final Class274 class148 = class146_Sub1.aClass274Array4789[j];
                                if (n6 == anIntArray4748[j] && n7 == class146_Sub1.anIntArray4762[j] && n5 == class146_Sub1.anIntArray4788[j] && class148.anInt2045 != 0) {
                                    if (this.aClass274Array4777 == null) {
                                        this.aClass274Array4777 = new Class274[this.anInt4814];
                                    }
                                    if (class146_Sub1.aClass274Array4777 == null) {
                                        class146_Sub1.aClass274Array4777 = new Class274[anInt4814];
                                    }
                                    Class274 class149 = this.aClass274Array4777[i];
                                    if (class149 == null) {
                                        final Class274[] aClass274Array4777 = this.aClass274Array4777;
                                        final int n8 = i;
                                        final Class274 class150 = new Class274(class147);
                                        aClass274Array4777[n8] = class150;
                                        class149 = class150;
                                    }
                                    Class274 class151 = class146_Sub1.aClass274Array4777[j];
                                    if (class151 == null) {
                                        final Class274[] aClass274Array4778 = class146_Sub1.aClass274Array4777;
                                        final int n9 = j;
                                        final Class274 class152 = new Class274(class148);
                                        aClass274Array4778[n9] = class152;
                                        class151 = class152;
                                    }
                                    final Class274 class153 = class149;
                                    class153.anInt2044 += class148.anInt2044;
                                    final Class274 class154 = class149;
                                    class154.anInt2043 += class148.anInt2043;
                                    final Class274 class155 = class149;
                                    class155.anInt2042 += class148.anInt2042;
                                    final Class274 class156 = class149;
                                    class156.anInt2045 += class148.anInt2045;
                                    final Class274 class157 = class151;
                                    class157.anInt2044 += class147.anInt2044;
                                    final Class274 class158 = class151;
                                    class158.anInt2043 += class147.anInt2043;
                                    final Class274 class159 = class151;
                                    class159.anInt2042 += class147.anInt2042;
                                    final Class274 class160 = class151;
                                    class160.anInt2045 += class147.anInt2045;
                                    ++n4;
                                    this.anIntArray4803[i] = Class146_Sub1.anInt4830;
                                    this.anIntArray4820[j] = Class146_Sub1.anInt4830;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (n4 >= 3 && b) {
            for (int k = 0; k < this.anInt4780; ++k) {
                if (this.anIntArray4803[this.aShortArray4782[k]] == Class146_Sub1.anInt4830 && this.anIntArray4803[this.aShortArray4750[k]] == Class146_Sub1.anInt4830 && this.anIntArray4803[this.aShortArray4832[k]] == Class146_Sub1.anInt4830) {
                    if (this.aByteArray4793 == null) {
                        this.aByteArray4793 = new byte[this.anInt4780];
                    }
                    this.aByteArray4793[k] = 2;
                }
            }
            for (int l = 0; l < class146_Sub1.anInt4780; ++l) {
                if (this.anIntArray4820[class146_Sub1.aShortArray4782[l]] == Class146_Sub1.anInt4830 && this.anIntArray4820[class146_Sub1.aShortArray4750[l]] == Class146_Sub1.anInt4830 && this.anIntArray4820[class146_Sub1.aShortArray4832[l]] == Class146_Sub1.anInt4830) {
                    if (class146_Sub1.aByteArray4793 == null) {
                        class146_Sub1.aByteArray4793 = new byte[class146_Sub1.anInt4780];
                    }
                    class146_Sub1.aByteArray4793[l] = 2;
                }
            }
        }
    }
    
    @Override
    final int WA() {
        return this.anInt4761;
    }
    
    private final void method2365(final int n) {
        final short n2 = this.aShortArray4782[n];
        final short n3 = this.aShortArray4750[n];
        final short n4 = this.aShortArray4832[n];
        if (this.aShortArray4804 == null || this.aShortArray4804[n] == -1) {
            if (this.aByteArray4822 == null) {
                this.aClass12_4768.anInt137 = 0;
            }
            else {
                this.aClass12_4768.anInt137 = (this.aByteArray4822[n] & 0xFF);
            }
            if (this.anIntArray4836[n] == -1) {
                this.aClass12_4768.method208(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]);
            }
            else {
                this.aClass12_4768.method216(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], this.anIntArray4791[n] & 0xFFFF, this.anIntArray4760[n] & 0xFFFF, this.anIntArray4836[n] & 0xFFFF);
            }
        }
        else {
            int n5 = -16777216;
            if (this.aByteArray4822 != null) {
                n5 = 255 - (this.aByteArray4822[n] & 0xFF) << 24;
            }
            if (this.anIntArray4836[n] == -1) {
                final int n6 = n5 | (this.anIntArray4791[n] & 0xFFFFFF);
                this.aClass12_4768.method212(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n6, n6, n6, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
            }
            else {
                this.aClass12_4768.method212(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n5 | (this.anIntArray4791[n] & 0xFFFFFF), n5 | (this.anIntArray4760[n] & 0xFFFFFF), n5 | (this.anIntArray4836[n] & 0xFFFFFF), this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
            }
        }
    }
    
    @Override
    final boolean NA() {
        if (this.anIntArrayArray4834 == null) {
            return false;
        }
        this.anInt4792 = 0;
        this.anInt4781 = 0;
        this.anInt4757 = 0;
        return true;
    }
    
    @Override
    final void method2342() {
        if (this.aHa_Sub2_4797.anInt4508 > 1) {
            synchronized (this) {
                while (super.aBoolean1181) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                super.aBoolean1181 = true;
            }
        }
    }
    
    private final void method2366() {
        if (this.anInt4784 == 0) {
            this.method2354(false);
        }
        else if (this.aHa_Sub2_4797.anInt4508 > 1) {
            synchronized (this) {
                this.method2352();
            }
        }
        else {
            this.method2352();
        }
    }
    
    private final Class146 method2367(final Class146_Sub1 class146_Sub1, final Class146_Sub1 class146_Sub2, final int anInt4816, final boolean b, final boolean b2) {
        class146_Sub1.aBoolean4778 = this.aBoolean4778;
        if (this.aBoolean4778) {
            class146_Sub1.aShort4801 = this.aShort4801;
            class146_Sub1.aShort4808 = this.aShort4808;
            class146_Sub1.aShort4753 = this.aShort4753;
            class146_Sub1.aShort4794 = this.aShort4794;
            class146_Sub1.aShort4770 = this.aShort4770;
            class146_Sub1.aShort4774 = this.aShort4774;
            class146_Sub1.aShort4835 = this.aShort4835;
            class146_Sub1.aShort4826 = this.aShort4826;
        }
        class146_Sub1.anInt4761 = this.anInt4761;
        class146_Sub1.anInt4805 = this.anInt4805;
        class146_Sub1.anInt4786 = this.anInt4786;
        class146_Sub1.anInt4814 = this.anInt4814;
        class146_Sub1.anInt4780 = this.anInt4780;
        class146_Sub1.anInt4776 = this.anInt4776;
        if ((anInt4816 & 0x100) != 0x0) {
            class146_Sub1.aBoolean4755 = true;
        }
        else {
            class146_Sub1.aBoolean4755 = this.aBoolean4755;
        }
        class146_Sub1.aBoolean4802 = this.aBoolean4802;
        final boolean b3 = (anInt4816 & 0x7) == 0x7 | (anInt4816 & 0x20) != 0x0;
        final boolean b4 = b3 || (anInt4816 & 0x1) != 0x0;
        final boolean b5 = b3 || (anInt4816 & 0x2) != 0x0;
        final boolean b6 = b3 || (anInt4816 & 0x4) != 0x0 || (anInt4816 & 0x10) != 0x0;
        if (b4 || b5 || b6) {
            if (b4) {
                if (class146_Sub2.anIntArray4748 == null || class146_Sub2.anIntArray4748.length < this.anInt4786) {
                    final int[] array = new int[this.anInt4786];
                    class146_Sub2.anIntArray4748 = array;
                    class146_Sub1.anIntArray4748 = array;
                }
                else {
                    class146_Sub1.anIntArray4748 = class146_Sub2.anIntArray4748;
                }
                for (int i = 0; i < this.anInt4786; ++i) {
                    class146_Sub1.anIntArray4748[i] = this.anIntArray4748[i];
                }
            }
            else {
                class146_Sub1.anIntArray4748 = this.anIntArray4748;
            }
            if (b5) {
                if (class146_Sub2.anIntArray4788 == null || class146_Sub2.anIntArray4788.length < this.anInt4786) {
                    final int[] array2 = new int[this.anInt4786];
                    class146_Sub2.anIntArray4788 = array2;
                    class146_Sub1.anIntArray4788 = array2;
                }
                else {
                    class146_Sub1.anIntArray4788 = class146_Sub2.anIntArray4788;
                }
                for (int j = 0; j < this.anInt4786; ++j) {
                    class146_Sub1.anIntArray4788[j] = this.anIntArray4788[j];
                }
            }
            else {
                class146_Sub1.anIntArray4788 = this.anIntArray4788;
            }
            if (b6) {
                if (class146_Sub2.anIntArray4762 == null || class146_Sub2.anIntArray4762.length < this.anInt4786) {
                    final int[] array3 = new int[this.anInt4786];
                    class146_Sub2.anIntArray4762 = array3;
                    class146_Sub1.anIntArray4762 = array3;
                }
                else {
                    class146_Sub1.anIntArray4762 = class146_Sub2.anIntArray4762;
                }
                for (int k = 0; k < this.anInt4786; ++k) {
                    class146_Sub1.anIntArray4762[k] = this.anIntArray4762[k];
                }
            }
            else {
                class146_Sub1.anIntArray4762 = this.anIntArray4762;
            }
        }
        else {
            class146_Sub1.anIntArray4748 = this.anIntArray4748;
            class146_Sub1.anIntArray4788 = this.anIntArray4788;
            class146_Sub1.anIntArray4762 = this.anIntArray4762;
        }
        if ((anInt4816 & 0x84080) != 0x0) {
            if (class146_Sub2.aShortArray4800 == null || class146_Sub2.aShortArray4800.length < this.anInt4780) {
                final short[] array4 = new short[this.anInt4780];
                class146_Sub2.aShortArray4800 = array4;
                class146_Sub1.aShortArray4800 = array4;
            }
            else {
                class146_Sub1.aShortArray4800 = class146_Sub2.aShortArray4800;
            }
            for (int l = 0; l < this.anInt4780; ++l) {
                class146_Sub1.aShortArray4800[l] = this.aShortArray4800[l];
            }
        }
        else {
            class146_Sub1.aShortArray4800 = this.aShortArray4800;
        }
        if ((anInt4816 & 0x97018) != 0x0) {
            class146_Sub1.anInt4784 = 0;
            final int[] anIntArray4791 = null;
            class146_Sub1.anIntArray4836 = anIntArray4791;
            class146_Sub1.anIntArray4760 = anIntArray4791;
            class146_Sub1.anIntArray4791 = anIntArray4791;
        }
        else if ((anInt4816 & 0x80) != 0x0) {
            if (b2) {
                this.method2354(false);
            }
            if (this.anIntArray4791 != null) {
                if (class146_Sub2.anIntArray4791 == null || class146_Sub2.anIntArray4791.length < this.anInt4780) {
                    final int anInt4817 = this.anInt4780;
                    final int[] array5 = new int[anInt4817];
                    class146_Sub2.anIntArray4791 = array5;
                    class146_Sub1.anIntArray4791 = array5;
                    final int[] array6 = new int[anInt4817];
                    class146_Sub2.anIntArray4760 = array6;
                    class146_Sub1.anIntArray4760 = array6;
                    final int[] array7 = new int[anInt4817];
                    class146_Sub2.anIntArray4836 = array7;
                    class146_Sub1.anIntArray4836 = array7;
                }
                else {
                    class146_Sub1.anIntArray4791 = class146_Sub2.anIntArray4791;
                    class146_Sub1.anIntArray4760 = class146_Sub2.anIntArray4760;
                    class146_Sub1.anIntArray4836 = class146_Sub2.anIntArray4836;
                }
                for (int n = 0; n < this.anInt4780; ++n) {
                    class146_Sub1.anIntArray4791[n] = this.anIntArray4791[n];
                    class146_Sub1.anIntArray4760[n] = this.anIntArray4760[n];
                    class146_Sub1.anIntArray4836[n] = this.anIntArray4836[n];
                }
            }
            class146_Sub1.anInt4784 = this.anInt4784;
        }
        else {
            if (b2) {
                this.method2354(false);
            }
            class146_Sub1.anIntArray4791 = this.anIntArray4791;
            class146_Sub1.anIntArray4760 = this.anIntArray4760;
            class146_Sub1.anIntArray4836 = this.anIntArray4836;
            class146_Sub1.anInt4784 = this.anInt4784;
        }
        if ((anInt4816 & 0x100) != 0x0) {
            if (class146_Sub2.aByteArray4822 == null || class146_Sub2.aByteArray4822.length < this.anInt4780) {
                final byte[] array8 = new byte[this.anInt4780];
                class146_Sub2.aByteArray4822 = array8;
                class146_Sub1.aByteArray4822 = array8;
            }
            else {
                class146_Sub1.aByteArray4822 = class146_Sub2.aByteArray4822;
            }
            if (this.aByteArray4822 != null) {
                for (int n2 = 0; n2 < this.anInt4780; ++n2) {
                    class146_Sub1.aByteArray4822[n2] = this.aByteArray4822[n2];
                }
            }
            else {
                for (int n3 = 0; n3 < this.anInt4780; ++n3) {
                    class146_Sub1.aByteArray4822[n3] = 0;
                }
            }
        }
        else {
            class146_Sub1.aByteArray4822 = this.aByteArray4822;
        }
        if ((anInt4816 & 0x8) != 0x0 || (anInt4816 & 0x10) != 0x0) {
            if (class146_Sub2.aClass274Array4789 == null || class146_Sub2.aClass274Array4789.length < this.anInt4814) {
                final Class274[] array9 = new Class274[this.anInt4814];
                class146_Sub2.aClass274Array4789 = array9;
                class146_Sub1.aClass274Array4789 = array9;
            }
            else {
                class146_Sub1.aClass274Array4789 = class146_Sub2.aClass274Array4789;
            }
            if (this.aClass274Array4789 != null) {
                for (int n4 = 0; n4 < this.anInt4814; ++n4) {
                    class146_Sub1.aClass274Array4789[n4] = new Class274(this.aClass274Array4789[n4]);
                }
            }
            else {
                class146_Sub1.aClass274Array4789 = null;
            }
            if (this.aClass296Array4823 != null) {
                if (class146_Sub2.aClass296Array4823 == null || class146_Sub2.aClass296Array4823.length < this.anInt4780) {
                    final Class296[] array10 = new Class296[this.anInt4780];
                    class146_Sub2.aClass296Array4823 = array10;
                    class146_Sub1.aClass296Array4823 = array10;
                }
                else {
                    class146_Sub1.aClass296Array4823 = class146_Sub2.aClass296Array4823;
                }
                for (int n5 = 0; n5 < this.anInt4780; ++n5) {
                    class146_Sub1.aClass296Array4823[n5] = ((this.aClass296Array4823[n5] != null) ? new Class296(this.aClass296Array4823[n5]) : null);
                }
            }
            else {
                class146_Sub1.aClass296Array4823 = null;
            }
        }
        else {
            if (b2) {
                this.method2353();
            }
            class146_Sub1.aClass274Array4789 = this.aClass274Array4789;
            class146_Sub1.aClass296Array4823 = this.aClass296Array4823;
        }
        if ((anInt4816 & 0x8000) != 0x0) {
            if (this.aShortArray4804 == null) {
                class146_Sub1.aShortArray4804 = null;
            }
            else {
                if (class146_Sub2.aShortArray4804 == null || class146_Sub2.aShortArray4804.length < this.anInt4780) {
                    final short[] array11 = new short[this.anInt4780];
                    class146_Sub2.aShortArray4804 = array11;
                    class146_Sub1.aShortArray4804 = array11;
                }
                else {
                    class146_Sub1.aShortArray4804 = class146_Sub2.aShortArray4804;
                }
                for (int n6 = 0; n6 < this.anInt4780; ++n6) {
                    class146_Sub1.aShortArray4804[n6] = this.aShortArray4804[n6];
                }
            }
        }
        else {
            class146_Sub1.aShortArray4804 = this.aShortArray4804;
        }
        if ((anInt4816 & 0x10000) != 0x0) {
            if (this.aByteArray4793 == null) {
                class146_Sub1.aByteArray4793 = null;
            }
            else {
                if (class146_Sub2.aByteArray4793 == null || class146_Sub2.aByteArray4793.length < this.anInt4780) {
                    final byte[] array12 = new byte[b ? (this.anInt4780 + 100) : this.anInt4780];
                    class146_Sub2.aByteArray4793 = array12;
                    class146_Sub1.aByteArray4793 = array12;
                }
                else {
                    class146_Sub1.aByteArray4793 = class146_Sub2.aByteArray4793;
                }
                for (int n7 = 0; n7 < this.anInt4780; ++n7) {
                    class146_Sub1.aByteArray4793[n7] = this.aByteArray4793[n7];
                }
            }
        }
        else {
            class146_Sub1.aByteArray4793 = this.aByteArray4793;
        }
        if ((anInt4816 & 0xC580) != 0x0) {
            if (class146_Sub2.aClass329Array4821 == null || class146_Sub2.aClass329Array4821.length < this.anInt4776) {
                final Class329[] array13 = new Class329[this.anInt4776];
                class146_Sub2.aClass329Array4821 = array13;
                class146_Sub1.aClass329Array4821 = array13;
                for (int n8 = 0; n8 < this.anInt4776; ++n8) {
                    class146_Sub1.aClass329Array4821[n8] = this.aClass329Array4821[n8].method3710(28889);
                }
            }
            else {
                class146_Sub1.aClass329Array4821 = class146_Sub2.aClass329Array4821;
                for (int n9 = 0; n9 < this.anInt4776; ++n9) {
                    class146_Sub1.aClass329Array4821[n9].method3709(this.aClass329Array4821[n9], 52);
                }
            }
        }
        else {
            class146_Sub1.aClass329Array4821 = this.aClass329Array4821;
        }
        if (this.aFloatArrayArray4798 != null && (anInt4816 & 0x10) != 0x0) {
            if (class146_Sub2.aFloatArrayArray4798 == null || class146_Sub2.aFloatArrayArray4798.length < this.anInt4780) {
                final float[][] array14 = new float[b ? (this.anInt4780 + 100) : this.anInt4780][3];
                class146_Sub2.aFloatArrayArray4798 = array14;
                class146_Sub1.aFloatArrayArray4798 = array14;
            }
            else {
                class146_Sub1.aFloatArrayArray4798 = class146_Sub2.aFloatArrayArray4798;
            }
            for (int n10 = 0; n10 < this.anInt4780; ++n10) {
                if (this.aFloatArrayArray4798[n10] != null) {
                    class146_Sub1.aFloatArrayArray4798[n10][0] = this.aFloatArrayArray4798[n10][0];
                    class146_Sub1.aFloatArrayArray4798[n10][1] = this.aFloatArrayArray4798[n10][1];
                    class146_Sub1.aFloatArrayArray4798[n10][2] = this.aFloatArrayArray4798[n10][2];
                }
            }
            if (class146_Sub2.aFloatArrayArray4806 == null || class146_Sub2.aFloatArrayArray4806.length < this.anInt4780) {
                final float[][] array15 = new float[b ? (this.anInt4780 + 100) : this.anInt4780][3];
                class146_Sub2.aFloatArrayArray4806 = array15;
                class146_Sub1.aFloatArrayArray4806 = array15;
            }
            else {
                class146_Sub1.aFloatArrayArray4806 = class146_Sub2.aFloatArrayArray4806;
            }
            for (int n11 = 0; n11 < this.anInt4780; ++n11) {
                if (this.aFloatArrayArray4806[n11] != null) {
                    class146_Sub1.aFloatArrayArray4806[n11][0] = this.aFloatArrayArray4806[n11][0];
                    class146_Sub1.aFloatArrayArray4806[n11][1] = this.aFloatArrayArray4806[n11][1];
                    class146_Sub1.aFloatArrayArray4806[n11][2] = this.aFloatArrayArray4806[n11][2];
                }
            }
        }
        else {
            class146_Sub1.aFloatArrayArray4798 = this.aFloatArrayArray4798;
            class146_Sub1.aFloatArrayArray4806 = this.aFloatArrayArray4806;
        }
        class146_Sub1.anIntArrayArray4834 = this.anIntArrayArray4834;
        class146_Sub1.anIntArrayArray4833 = this.anIntArrayArray4833;
        class146_Sub1.anIntArrayArray4796 = this.anIntArrayArray4796;
        class146_Sub1.aShortArray4790 = this.aShortArray4790;
        class146_Sub1.aShortArray4795 = this.aShortArray4795;
        class146_Sub1.aByteArray4772 = this.aByteArray4772;
        class146_Sub1.aShortArray4782 = this.aShortArray4782;
        class146_Sub1.aShortArray4750 = this.aShortArray4750;
        class146_Sub1.aShortArray4832 = this.aShortArray4832;
        class146_Sub1.aClass87Array4813 = this.aClass87Array4813;
        class146_Sub1.aClass35Array4787 = this.aClass35Array4787;
        class146_Sub1.aClass170Array4827 = this.aClass170Array4827;
        class146_Sub1.aShortArray4809 = this.aShortArray4809;
        class146_Sub1.anInt4816 = anInt4816;
        return class146_Sub1;
    }
    
    @Override
    final void method2344(final int n, final int[] array, int anInt4792, int anInt4793, int anInt4794, final int n2, final boolean b) {
        final int length = array.length;
        if (n == 0) {
            anInt4792 <<= 4;
            anInt4793 <<= 4;
            anInt4794 <<= 4;
            if (!this.aBoolean4815) {
                for (int i = 0; i < this.anInt4786; ++i) {
                    final int[] anIntArray4748 = this.anIntArray4748;
                    final int n3 = i;
                    anIntArray4748[n3] <<= 4;
                    final int[] anIntArray4749 = this.anIntArray4788;
                    final int n4 = i;
                    anIntArray4749[n4] <<= 4;
                    final int[] anIntArray4750 = this.anIntArray4762;
                    final int n5 = i;
                    anIntArray4750[n5] <<= 4;
                }
                this.aBoolean4815 = true;
            }
            int n6 = 0;
            this.anInt4792 = 0;
            this.anInt4781 = 0;
            this.anInt4757 = 0;
            for (final int n7 : array) {
                if (n7 < this.anIntArrayArray4834.length) {
                    final int[] array2 = this.anIntArrayArray4834[n7];
                    for (int k = 0; k < array2.length; ++k) {
                        final int n8 = array2[k];
                        this.anInt4792 += this.anIntArray4748[n8];
                        this.anInt4781 += this.anIntArray4788[n8];
                        this.anInt4757 += this.anIntArray4762[n8];
                        ++n6;
                    }
                }
            }
            if (n6 > 0) {
                this.anInt4792 = this.anInt4792 / n6 + anInt4792;
                this.anInt4781 = this.anInt4781 / n6 + anInt4793;
                this.anInt4757 = this.anInt4757 / n6 + anInt4794;
            }
            else {
                this.anInt4792 = anInt4792;
                this.anInt4781 = anInt4793;
                this.anInt4757 = anInt4794;
            }
        }
        else if (n == 1) {
            anInt4792 <<= 4;
            anInt4793 <<= 4;
            anInt4794 <<= 4;
            if (!this.aBoolean4815) {
                for (int l = 0; l < this.anInt4786; ++l) {
                    final int[] anIntArray4751 = this.anIntArray4748;
                    final int n9 = l;
                    anIntArray4751[n9] <<= 4;
                    final int[] anIntArray4752 = this.anIntArray4788;
                    final int n10 = l;
                    anIntArray4752[n10] <<= 4;
                    final int[] anIntArray4753 = this.anIntArray4762;
                    final int n11 = l;
                    anIntArray4753[n11] <<= 4;
                }
                this.aBoolean4815 = true;
            }
            for (final int n13 : array) {
                if (n13 < this.anIntArrayArray4834.length) {
                    final int[] array3 = this.anIntArrayArray4834[n13];
                    for (int n14 = 0; n14 < array3.length; ++n14) {
                        final int n15 = array3[n14];
                        final int[] anIntArray4754 = this.anIntArray4748;
                        final int n16 = n15;
                        anIntArray4754[n16] += anInt4792;
                        final int[] anIntArray4755 = this.anIntArray4788;
                        final int n17 = n15;
                        anIntArray4755[n17] += anInt4793;
                        final int[] anIntArray4756 = this.anIntArray4762;
                        final int n18 = n15;
                        anIntArray4756[n18] += anInt4794;
                    }
                }
            }
        }
        else if (n == 2) {
            for (final int n20 : array) {
                if (n20 < this.anIntArrayArray4834.length) {
                    final int[] array4 = this.anIntArrayArray4834[n20];
                    if ((n2 & 0x1) == 0x0) {
                        for (int n21 = 0; n21 < array4.length; ++n21) {
                            final int n22 = array4[n21];
                            final int[] anIntArray4757 = this.anIntArray4748;
                            final int n23 = n22;
                            anIntArray4757[n23] -= this.anInt4792;
                            final int[] anIntArray4758 = this.anIntArray4788;
                            final int n24 = n22;
                            anIntArray4758[n24] -= this.anInt4781;
                            final int[] anIntArray4759 = this.anIntArray4762;
                            final int n25 = n22;
                            anIntArray4759[n25] -= this.anInt4757;
                            if (anInt4794 != 0) {
                                final int n26 = Class284_Sub2_Sub2.anIntArray6200[anInt4794];
                                final int n27 = Class284_Sub2_Sub2.anIntArray6202[anInt4794];
                                final int n28 = this.anIntArray4788[n22] * n26 + this.anIntArray4748[n22] * n27 + 16383 >> 14;
                                this.anIntArray4788[n22] = this.anIntArray4788[n22] * n27 - this.anIntArray4748[n22] * n26 + 16383 >> 14;
                                this.anIntArray4748[n22] = n28;
                            }
                            if (anInt4792 != 0) {
                                final int n29 = Class284_Sub2_Sub2.anIntArray6200[anInt4792];
                                final int n30 = Class284_Sub2_Sub2.anIntArray6202[anInt4792];
                                final int n31 = this.anIntArray4788[n22] * n30 - this.anIntArray4762[n22] * n29 + 16383 >> 14;
                                this.anIntArray4762[n22] = this.anIntArray4788[n22] * n29 + this.anIntArray4762[n22] * n30 + 16383 >> 14;
                                this.anIntArray4788[n22] = n31;
                            }
                            if (anInt4793 != 0) {
                                final int n32 = Class284_Sub2_Sub2.anIntArray6200[anInt4793];
                                final int n33 = Class284_Sub2_Sub2.anIntArray6202[anInt4793];
                                final int n34 = this.anIntArray4762[n22] * n32 + this.anIntArray4748[n22] * n33 + 16383 >> 14;
                                this.anIntArray4762[n22] = this.anIntArray4762[n22] * n33 - this.anIntArray4748[n22] * n32 + 16383 >> 14;
                                this.anIntArray4748[n22] = n34;
                            }
                            final int[] anIntArray4760 = this.anIntArray4748;
                            final int n35 = n22;
                            anIntArray4760[n35] += this.anInt4792;
                            final int[] anIntArray4761 = this.anIntArray4788;
                            final int n36 = n22;
                            anIntArray4761[n36] += this.anInt4781;
                            final int[] anIntArray4762 = this.anIntArray4762;
                            final int n37 = n22;
                            anIntArray4762[n37] += this.anInt4757;
                        }
                    }
                    else {
                        for (int n38 = 0; n38 < array4.length; ++n38) {
                            final int n39 = array4[n38];
                            final int[] anIntArray4763 = this.anIntArray4748;
                            final int n40 = n39;
                            anIntArray4763[n40] -= this.anInt4792;
                            final int[] anIntArray4764 = this.anIntArray4788;
                            final int n41 = n39;
                            anIntArray4764[n41] -= this.anInt4781;
                            final int[] anIntArray4765 = this.anIntArray4762;
                            final int n42 = n39;
                            anIntArray4765[n42] -= this.anInt4757;
                            if (anInt4792 != 0) {
                                final int n43 = Class284_Sub2_Sub2.anIntArray6200[anInt4792];
                                final int n44 = Class284_Sub2_Sub2.anIntArray6202[anInt4792];
                                final int n45 = this.anIntArray4788[n39] * n44 - this.anIntArray4762[n39] * n43 + 16383 >> 14;
                                this.anIntArray4762[n39] = this.anIntArray4788[n39] * n43 + this.anIntArray4762[n39] * n44 + 16383 >> 14;
                                this.anIntArray4788[n39] = n45;
                            }
                            if (anInt4794 != 0) {
                                final int n46 = Class284_Sub2_Sub2.anIntArray6200[anInt4794];
                                final int n47 = Class284_Sub2_Sub2.anIntArray6202[anInt4794];
                                final int n48 = this.anIntArray4788[n39] * n46 + this.anIntArray4748[n39] * n47 + 16383 >> 14;
                                this.anIntArray4788[n39] = this.anIntArray4788[n39] * n47 - this.anIntArray4748[n39] * n46 + 16383 >> 14;
                                this.anIntArray4748[n39] = n48;
                            }
                            if (anInt4793 != 0) {
                                final int n49 = Class284_Sub2_Sub2.anIntArray6200[anInt4793];
                                final int n50 = Class284_Sub2_Sub2.anIntArray6202[anInt4793];
                                final int n51 = this.anIntArray4762[n39] * n49 + this.anIntArray4748[n39] * n50 + 16383 >> 14;
                                this.anIntArray4762[n39] = this.anIntArray4762[n39] * n50 - this.anIntArray4748[n39] * n49 + 16383 >> 14;
                                this.anIntArray4748[n39] = n51;
                            }
                            final int[] anIntArray4766 = this.anIntArray4748;
                            final int n52 = n39;
                            anIntArray4766[n52] += this.anInt4792;
                            final int[] anIntArray4767 = this.anIntArray4788;
                            final int n53 = n39;
                            anIntArray4767[n53] += this.anInt4781;
                            final int[] anIntArray4768 = this.anIntArray4762;
                            final int n54 = n39;
                            anIntArray4768[n54] += this.anInt4757;
                        }
                    }
                }
            }
        }
        else if (n == 3) {
            for (final int n56 : array) {
                if (n56 < this.anIntArrayArray4834.length) {
                    final int[] array5 = this.anIntArrayArray4834[n56];
                    for (int n57 = 0; n57 < array5.length; ++n57) {
                        final int n58 = array5[n57];
                        final int[] anIntArray4769 = this.anIntArray4748;
                        final int n59 = n58;
                        anIntArray4769[n59] -= this.anInt4792;
                        final int[] anIntArray4770 = this.anIntArray4788;
                        final int n60 = n58;
                        anIntArray4770[n60] -= this.anInt4781;
                        final int[] anIntArray4771 = this.anIntArray4762;
                        final int n61 = n58;
                        anIntArray4771[n61] -= this.anInt4757;
                        this.anIntArray4748[n58] = this.anIntArray4748[n58] * anInt4792 / 128;
                        this.anIntArray4788[n58] = this.anIntArray4788[n58] * anInt4793 / 128;
                        this.anIntArray4762[n58] = this.anIntArray4762[n58] * anInt4794 / 128;
                        final int[] anIntArray4772 = this.anIntArray4748;
                        final int n62 = n58;
                        anIntArray4772[n62] += this.anInt4792;
                        final int[] anIntArray4773 = this.anIntArray4788;
                        final int n63 = n58;
                        anIntArray4773[n63] += this.anInt4781;
                        final int[] anIntArray4774 = this.anIntArray4762;
                        final int n64 = n58;
                        anIntArray4774[n64] += this.anInt4757;
                    }
                }
            }
        }
        else if (n == 5) {
            if (this.anIntArrayArray4833 != null && this.aByteArray4822 != null) {
                for (final int n66 : array) {
                    if (n66 < this.anIntArrayArray4833.length) {
                        final int[] array6 = this.anIntArrayArray4833[n66];
                        for (int n67 = 0; n67 < array6.length; ++n67) {
                            final int n68 = array6[n67];
                            int n69 = (this.aByteArray4822[n68] & 0xFF) + anInt4792 * 8;
                            if (n69 < 0) {
                                n69 = 0;
                            }
                            else if (n69 > 255) {
                                n69 = 255;
                            }
                            this.aByteArray4822[n68] = (byte)n69;
                        }
                    }
                }
                if (this.aClass170Array4827 != null) {
                    for (int n70 = 0; n70 < this.anInt4776; ++n70) {
                        final Class170 class170 = this.aClass170Array4827[n70];
                        final Class329 class171 = this.aClass329Array4821[n70];
                        class171.anInt2770 = ((class171.anInt2770 & 0xFFFFFF) | 255 - (this.aByteArray4822[class170.anInt1315] & 0xFF) << 24);
                    }
                }
            }
        }
        else if (n == 7) {
            if (this.anIntArrayArray4833 != null) {
                for (final int n72 : array) {
                    if (n72 < this.anIntArrayArray4833.length) {
                        final int[] array7 = this.anIntArrayArray4833[n72];
                        for (int n73 = 0; n73 < array7.length; ++n73) {
                            final int n74 = array7[n73];
                            final int n75 = this.aShortArray4800[n74] & 0xFFFF;
                            final int n76 = n75 >> 10 & 0x3F;
                            final int n77 = n75 >> 7 & 0x7;
                            final int n78 = n75 & 0x7F;
                            final int n79 = n76 + anInt4792 & 0x3F;
                            int n80 = n77 + anInt4793;
                            if (n80 < 0) {
                                n80 = 0;
                            }
                            else if (n80 > 7) {
                                n80 = 7;
                            }
                            int n81 = n78 + anInt4794;
                            if (n81 < 0) {
                                n81 = 0;
                            }
                            else if (n81 > 127) {
                                n81 = 127;
                            }
                            this.aShortArray4800[n74] = (short)(n79 << 10 | n80 << 7 | n81);
                        }
                        this.aBoolean4754 = true;
                    }
                }
                if (this.aClass170Array4827 != null) {
                    for (int n82 = 0; n82 < this.anInt4776; ++n82) {
                        final Class170 class172 = this.aClass170Array4827[n82];
                        final Class329 class173 = this.aClass329Array4821[n82];
                        class173.anInt2770 = ((class173.anInt2770 & 0xFF000000) | (Class221.anIntArray1665[Class111_Sub2.method2117(this.aShortArray4800[class172.anInt1315] & 0xFFFF, 92) & 0xFFFF] & 0xFFFFFF));
                    }
                }
            }
        }
        else if (n == 8) {
            if (this.anIntArrayArray4796 != null) {
                for (final int n84 : array) {
                    if (n84 < this.anIntArrayArray4796.length) {
                        final int[] array8 = this.anIntArrayArray4796[n84];
                        for (int n85 = 0; n85 < array8.length; ++n85) {
                            final Class329 class175;
                            final Class329 class174 = class175 = this.aClass329Array4821[array8[n85]];
                            class175.anInt2762 += anInt4792;
                            final Class329 class176 = class174;
                            class176.anInt2772 += anInt4793;
                        }
                    }
                }
            }
        }
        else if (n == 10) {
            if (this.anIntArrayArray4796 != null) {
                for (final int n87 : array) {
                    if (n87 < this.anIntArrayArray4796.length) {
                        final int[] array9 = this.anIntArrayArray4796[n87];
                        for (int n88 = 0; n88 < array9.length; ++n88) {
                            final Class329 class177 = this.aClass329Array4821[array9[n88]];
                            class177.anInt2763 = class177.anInt2763 * anInt4792 >> 7;
                            class177.anInt2768 = class177.anInt2768 * anInt4793 >> 7;
                        }
                    }
                }
            }
        }
        else if (n == 9 && this.anIntArrayArray4796 != null) {
            for (final int n90 : array) {
                if (n90 < this.anIntArrayArray4796.length) {
                    final int[] array10 = this.anIntArrayArray4796[n90];
                    for (int n91 = 0; n91 < array10.length; ++n91) {
                        final Class329 class178 = this.aClass329Array4821[array10[n91]];
                        class178.anInt2769 = (class178.anInt2769 + anInt4792 & 0x3FFF);
                    }
                }
            }
        }
    }
    
    @Override
    final boolean method2333(final int n, final int n2, final Class111 class111, final boolean b, final int n3, final int n4) {
        return this.method2372(n, n2, class111, b, n3, n4);
    }
    
    private final void method2368(final int n) {
        int n2 = 0;
        final int anInt4502 = this.aHa_Sub2_4797.anInt4502;
        final short n3 = this.aShortArray4782[n];
        final short n4 = this.aShortArray4750[n];
        final short n5 = this.aShortArray4832[n];
        final int n6 = this.anIntArray4829[n3];
        final int n7 = this.anIntArray4829[n4];
        final int n8 = this.anIntArray4829[n5];
        if (this.aByteArray4822 == null) {
            this.aClass12_4768.anInt137 = 0;
        }
        else {
            this.aClass12_4768.anInt137 = (this.aByteArray4822[n] & 0xFF);
        }
        if (n6 >= anInt4502) {
            this.anIntArray4751[n2] = this.anIntArray4771[n3];
            this.anIntArray4811[n2] = this.anIntArray4812[n3];
            this.anIntArray4824[n2] = this.anIntArray4766[n3];
            this.anIntArray4749[n2++] = (this.anIntArray4791[n] & 0xFFFF);
        }
        else {
            final int n9 = this.anIntArray4828[n3];
            final int n10 = this.anIntArray4763[n3];
            final int n11 = this.anIntArray4791[n] & 0xFFFF;
            if (n8 >= anInt4502) {
                final int n12 = (anInt4502 - n6) * (65536 / (n8 - n6));
                this.anIntArray4751[n2] = this.aClass235_4807.anInt1771 + (n9 + ((this.anIntArray4828[n5] - n9) * n12 >> 16)) * this.aHa_Sub2_4797.anInt4514 / anInt4502;
                this.anIntArray4811[n2] = this.aClass235_4807.anInt1779 + (n10 + ((this.anIntArray4763[n5] - n10) * n12 >> 16)) * this.aHa_Sub2_4797.anInt4490 / anInt4502;
                this.anIntArray4824[n2] = anInt4502;
                this.anIntArray4749[n2++] = n11 + (((this.anIntArray4836[n] & 0xFFFF) - n11) * n12 >> 16);
            }
            if (n7 >= anInt4502) {
                final int n13 = (anInt4502 - n6) * (65536 / (n7 - n6));
                this.anIntArray4751[n2] = this.aClass235_4807.anInt1771 + (n9 + ((this.anIntArray4828[n4] - n9) * n13 >> 16)) * this.aHa_Sub2_4797.anInt4514 / anInt4502;
                this.anIntArray4811[n2] = this.aClass235_4807.anInt1779 + (n10 + ((this.anIntArray4763[n4] - n10) * n13 >> 16)) * this.aHa_Sub2_4797.anInt4490 / anInt4502;
                this.anIntArray4824[n2] = anInt4502;
                this.anIntArray4749[n2++] = n11 + (((this.anIntArray4760[n] & 0xFFFF) - n11) * n13 >> 16);
            }
        }
        if (n7 >= anInt4502) {
            this.anIntArray4751[n2] = this.anIntArray4771[n4];
            this.anIntArray4811[n2] = this.anIntArray4812[n4];
            this.anIntArray4824[n2] = this.anIntArray4766[n4];
            this.anIntArray4749[n2++] = (this.anIntArray4760[n] & 0xFFFF);
        }
        else {
            final int n14 = this.anIntArray4828[n4];
            final int n15 = this.anIntArray4763[n4];
            final int n16 = this.anIntArray4760[n] & 0xFFFF;
            if (n6 >= anInt4502) {
                final int n17 = (anInt4502 - n7) * (65536 / (n6 - n7));
                this.anIntArray4751[n2] = this.aClass235_4807.anInt1771 + (n14 + ((this.anIntArray4828[n3] - n14) * n17 >> 16)) * this.aHa_Sub2_4797.anInt4514 / anInt4502;
                this.anIntArray4811[n2] = this.aClass235_4807.anInt1779 + (n15 + ((this.anIntArray4763[n3] - n15) * n17 >> 16)) * this.aHa_Sub2_4797.anInt4490 / anInt4502;
                this.anIntArray4824[n2] = anInt4502;
                this.anIntArray4749[n2++] = n16 + (((this.anIntArray4791[n] & 0xFFFF) - n16) * n17 >> 16);
            }
            if (n8 >= anInt4502) {
                final int n18 = (anInt4502 - n7) * (65536 / (n8 - n7));
                this.anIntArray4751[n2] = this.aClass235_4807.anInt1771 + (n14 + ((this.anIntArray4828[n5] - n14) * n18 >> 16)) * this.aHa_Sub2_4797.anInt4514 / anInt4502;
                this.anIntArray4811[n2] = this.aClass235_4807.anInt1779 + (n15 + ((this.anIntArray4763[n5] - n15) * n18 >> 16)) * this.aHa_Sub2_4797.anInt4490 / anInt4502;
                this.anIntArray4824[n2] = anInt4502;
                this.anIntArray4749[n2++] = n16 + (((this.anIntArray4836[n] & 0xFFFF) - n16) * n18 >> 16);
            }
        }
        if (n8 >= anInt4502) {
            this.anIntArray4751[n2] = this.anIntArray4771[n5];
            this.anIntArray4811[n2] = this.anIntArray4812[n5];
            this.anIntArray4824[n2] = this.anIntArray4766[n5];
            this.anIntArray4749[n2++] = (this.anIntArray4836[n] & 0xFFFF);
        }
        else {
            final int n19 = this.anIntArray4828[n5];
            final int n20 = this.anIntArray4763[n5];
            final int n21 = this.anIntArray4836[n] & 0xFFFF;
            if (n7 >= anInt4502) {
                final int n22 = (anInt4502 - n8) * (65536 / (n7 - n8));
                this.anIntArray4751[n2] = this.aClass235_4807.anInt1771 + (n19 + ((this.anIntArray4828[n4] - n19) * n22 >> 16)) * this.aHa_Sub2_4797.anInt4514 / anInt4502;
                this.anIntArray4811[n2] = this.aClass235_4807.anInt1779 + (n20 + ((this.anIntArray4763[n4] - n20) * n22 >> 16)) * this.aHa_Sub2_4797.anInt4490 / anInt4502;
                this.anIntArray4824[n2] = anInt4502;
                this.anIntArray4749[n2++] = n21 + (((this.anIntArray4760[n] & 0xFFFF) - n21) * n22 >> 16);
            }
            if (n6 >= anInt4502) {
                final int n23 = (anInt4502 - n8) * (65536 / (n6 - n8));
                this.anIntArray4751[n2] = this.aClass235_4807.anInt1771 + (n19 + ((this.anIntArray4828[n3] - n19) * n23 >> 16)) * this.aHa_Sub2_4797.anInt4514 / anInt4502;
                this.anIntArray4811[n2] = this.aClass235_4807.anInt1779 + (n20 + ((this.anIntArray4763[n3] - n20) * n23 >> 16)) * this.aHa_Sub2_4797.anInt4490 / anInt4502;
                this.anIntArray4824[n2] = anInt4502;
                this.anIntArray4749[n2++] = n21 + (((this.anIntArray4791[n] & 0xFFFF) - n21) * n23 >> 16);
            }
        }
        final int n24 = this.anIntArray4751[0];
        final int n25 = this.anIntArray4751[1];
        final int n26 = this.anIntArray4751[2];
        final int n27 = this.anIntArray4811[0];
        final int n28 = this.anIntArray4811[1];
        final int n29 = this.anIntArray4811[2];
        final int n30 = this.anIntArray4824[0];
        final int n31 = this.anIntArray4824[1];
        final int n32 = this.anIntArray4824[2];
        this.aClass12_4768.aBoolean135 = false;
        if (n2 == 3) {
            if (n24 < 0 || n25 < 0 || n26 < 0 || n24 > this.aClass235_4807.anInt1783 || n25 > this.aClass235_4807.anInt1783 || n26 > this.aClass235_4807.anInt1783) {
                this.aClass12_4768.aBoolean135 = true;
            }
            if (this.aShortArray4804 == null || this.aShortArray4804[n] == -1) {
                if (this.anIntArray4836[n] == -1) {
                    this.aClass12_4768.method208(n27, n28, n29, n24, n25, n26, n30, n31, n32, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]);
                }
                else {
                    this.aClass12_4768.method216(n27, n28, n29, n24, n25, n26, n30, n31, n32, this.anIntArray4749[0], this.anIntArray4749[1], this.anIntArray4749[2]);
                }
            }
            else {
                int n33 = -16777216;
                if (this.aByteArray4822 != null) {
                    n33 = 255 - (this.aByteArray4822[n] & 0xFF) << 24;
                }
                final int n34 = n33 | (this.anIntArray4791[n] & 0xFFFFFF);
                if (this.anIntArray4836[n] == -1) {
                    this.aClass12_4768.method212(n27, n28, n29, n24, n25, n26, n30, n31, n32, this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n34, n34, n34, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
                }
                else {
                    this.aClass12_4768.method212(n27, n28, n29, n24, n25, n26, n30, n31, n32, this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n34, n34, n34, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
                }
            }
        }
        if (n2 == 4) {
            if (n24 < 0 || n25 < 0 || n26 < 0 || n24 > this.aClass235_4807.anInt1783 || n25 > this.aClass235_4807.anInt1783 || n26 > this.aClass235_4807.anInt1783 || this.anIntArray4751[3] < 0 || this.anIntArray4751[3] > this.aClass235_4807.anInt1783) {
                this.aClass12_4768.aBoolean135 = true;
            }
            if (this.aShortArray4804 == null || this.aShortArray4804[n] == -1) {
                if (this.anIntArray4836[n] == -1) {
                    final int n35 = Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF];
                    this.aClass12_4768.method208(n27, n28, n29, n24, n25, n26, n30, n31, n32, n35);
                    this.aClass12_4768.method208(n27, n29, this.anIntArray4811[3], n24, n26, this.anIntArray4751[3], n30, n31, this.anIntArray4824[3], n35);
                }
                else {
                    this.aClass12_4768.method216(n27, n28, n29, n24, n25, n26, n30, n31, n32, this.anIntArray4749[0], this.anIntArray4749[1], this.anIntArray4749[2]);
                    this.aClass12_4768.method216(n27, n29, this.anIntArray4811[3], n24, n26, this.anIntArray4751[3], n30, n31, this.anIntArray4824[3], this.anIntArray4749[0], this.anIntArray4749[2], this.anIntArray4749[3]);
                }
            }
            else {
                int n36 = -16777216;
                if (this.aByteArray4822 != null) {
                    n36 = 255 - (this.aByteArray4822[n] & 0xFF) << 24;
                }
                final int n37 = n36 | (this.anIntArray4791[n] & 0xFFFFFF);
                if (this.anIntArray4836[n] == -1) {
                    this.aClass12_4768.method212(n27, n28, n29, n24, n25, n26, n30, n31, n32, this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n37, n37, n37, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
                    this.aClass12_4768.method212(n27, n29, this.anIntArray4811[3], n24, n26, this.anIntArray4751[3], n30, n32, this.anIntArray4824[3], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n37, n37, n37, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
                }
                else {
                    this.aClass12_4768.method212(n27, n28, n29, n24, n25, n26, n30, n31, n32, this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n37, n37, n37, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
                    this.aClass12_4768.method212(n27, n29, this.anIntArray4811[3], n24, n26, this.anIntArray4751[3], n30, n32, this.anIntArray4824[3], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n37, n37, n37, this.aClass235_4807.anInt1763, 0, 0, 0, this.aShortArray4804[n]);
                }
            }
        }
    }
    
    @Override
    final void v() {
        if ((this.anInt4816 & 0x10) != 0x10) {
            throw new IllegalStateException();
        }
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                this.anIntArray4762[i] = -this.anIntArray4762[i];
            }
            if (this.aClass274Array4789 != null) {
                for (int j = 0; j < this.anInt4814; ++j) {
                    if (this.aClass274Array4789[j] != null) {
                        this.aClass274Array4789[j].anInt2042 = -this.aClass274Array4789[j].anInt2042;
                    }
                }
            }
            if (this.aClass274Array4777 != null) {
                for (int k = 0; k < this.anInt4814; ++k) {
                    if (this.aClass274Array4777[k] != null) {
                        this.aClass274Array4777[k].anInt2042 = -this.aClass274Array4777[k].anInt2042;
                    }
                }
            }
            if (this.aClass296Array4823 != null) {
                for (int l = 0; l < this.anInt4780; ++l) {
                    if (this.aClass296Array4823[l] != null) {
                        this.aClass296Array4823[l].anInt2413 = -this.aClass296Array4823[l].anInt2413;
                    }
                }
            }
            final short[] aShortArray4782 = this.aShortArray4782;
            this.aShortArray4782 = this.aShortArray4832;
            this.aShortArray4832 = aShortArray4782;
            if (this.aFloatArrayArray4798 != null) {
                for (int n = 0; n < this.anInt4780; ++n) {
                    if (this.aFloatArrayArray4798[n] != null) {
                        final float n2 = this.aFloatArrayArray4798[n][0];
                        this.aFloatArrayArray4798[n][0] = this.aFloatArrayArray4798[n][2];
                        this.aFloatArrayArray4798[n][2] = n2;
                    }
                    if (this.aFloatArrayArray4806[n] != null) {
                        final float n3 = this.aFloatArrayArray4806[n][0];
                        this.aFloatArrayArray4806[n][0] = this.aFloatArrayArray4806[n][2];
                        this.aFloatArrayArray4806[n][2] = n3;
                    }
                }
            }
            this.aBoolean4778 = false;
            this.anInt4784 = 0;
        }
    }
    
    private final void method2369() {
        synchronized (this) {
            for (int i = 0; i < this.anInt4814; ++i) {
                this.anIntArray4748[i] = -this.anIntArray4748[i];
                this.anIntArray4762[i] = -this.anIntArray4762[i];
                if (this.aClass274Array4789[i] != null) {
                    this.aClass274Array4789[i].anInt2044 = -this.aClass274Array4789[i].anInt2044;
                    this.aClass274Array4789[i].anInt2042 = -this.aClass274Array4789[i].anInt2042;
                }
            }
            if (this.aClass296Array4823 != null) {
                for (int j = 0; j < this.anInt4780; ++j) {
                    if (this.aClass296Array4823[j] != null) {
                        this.aClass296Array4823[j].anInt2411 = -this.aClass296Array4823[j].anInt2411;
                        this.aClass296Array4823[j].anInt2413 = -this.aClass296Array4823[j].anInt2413;
                    }
                }
            }
            for (int k = this.anInt4814; k < this.anInt4786; ++k) {
                this.anIntArray4748[k] = -this.anIntArray4748[k];
                this.anIntArray4762[k] = -this.anIntArray4762[k];
            }
            this.anInt4784 = 0;
            this.aBoolean4778 = false;
        }
    }
    
    @Override
    final void method2343(final Class111 class111) {
        final Class111_Sub2 class111_Sub2 = (Class111_Sub2)class111;
        if (this.aClass87Array4813 != null) {
            for (int i = 0; i < this.aClass87Array4813.length; ++i) {
                Class87 aClass87_657;
                final Class87 class112 = aClass87_657 = this.aClass87Array4813[i];
                if (class112.aClass87_657 != null) {
                    aClass87_657 = class112.aClass87_657;
                }
                aClass87_657.anInt670 = (int)(class111_Sub2.aFloat4697 + (class111_Sub2.aFloat4700 * this.anIntArray4748[class112.anInt666] + class111_Sub2.aFloat4699 * this.anIntArray4788[class112.anInt666] + class111_Sub2.aFloat4690 * this.anIntArray4762[class112.anInt666]));
                aClass87_657.anInt668 = (int)(class111_Sub2.aFloat4691 + (class111_Sub2.aFloat4692 * this.anIntArray4748[class112.anInt666] + class111_Sub2.aFloat4688 * this.anIntArray4788[class112.anInt666] + class111_Sub2.aFloat4696 * this.anIntArray4762[class112.anInt666]));
                aClass87_657.anInt671 = (int)(class111_Sub2.aFloat4689 + (class111_Sub2.aFloat4693 * this.anIntArray4748[class112.anInt666] + class111_Sub2.aFloat4698 * this.anIntArray4788[class112.anInt666] + class111_Sub2.aFloat4694 * this.anIntArray4762[class112.anInt666]));
                aClass87_657.anInt663 = (int)(class111_Sub2.aFloat4697 + (class111_Sub2.aFloat4700 * this.anIntArray4748[class112.anInt661] + class111_Sub2.aFloat4699 * this.anIntArray4788[class112.anInt661] + class111_Sub2.aFloat4690 * this.anIntArray4762[class112.anInt661]));
                aClass87_657.anInt664 = (int)(class111_Sub2.aFloat4691 + (class111_Sub2.aFloat4692 * this.anIntArray4748[class112.anInt661] + class111_Sub2.aFloat4688 * this.anIntArray4788[class112.anInt661] + class111_Sub2.aFloat4696 * this.anIntArray4762[class112.anInt661]));
                aClass87_657.anInt656 = (int)(class111_Sub2.aFloat4689 + (class111_Sub2.aFloat4693 * this.anIntArray4748[class112.anInt661] + class111_Sub2.aFloat4698 * this.anIntArray4788[class112.anInt661] + class111_Sub2.aFloat4694 * this.anIntArray4762[class112.anInt661]));
                aClass87_657.anInt659 = (int)(class111_Sub2.aFloat4697 + (class111_Sub2.aFloat4700 * this.anIntArray4748[class112.anInt674] + class111_Sub2.aFloat4699 * this.anIntArray4788[class112.anInt674] + class111_Sub2.aFloat4690 * this.anIntArray4762[class112.anInt674]));
                aClass87_657.anInt669 = (int)(class111_Sub2.aFloat4691 + (class111_Sub2.aFloat4692 * this.anIntArray4748[class112.anInt674] + class111_Sub2.aFloat4688 * this.anIntArray4788[class112.anInt674] + class111_Sub2.aFloat4696 * this.anIntArray4762[class112.anInt674]));
                aClass87_657.anInt662 = (int)(class111_Sub2.aFloat4689 + (class111_Sub2.aFloat4693 * this.anIntArray4748[class112.anInt674] + class111_Sub2.aFloat4698 * this.anIntArray4788[class112.anInt674] + class111_Sub2.aFloat4694 * this.anIntArray4762[class112.anInt674]));
            }
        }
        if (this.aClass35Array4787 != null) {
            for (int j = 0; j < this.aClass35Array4787.length; ++j) {
                Class35 aClass35_328;
                final Class35 class113 = aClass35_328 = this.aClass35Array4787[j];
                if (class113.aClass35_328 != null) {
                    aClass35_328 = class113.aClass35_328;
                }
                if (class113.aClass111_334 != null) {
                    class113.aClass111_334.method2092(class111_Sub2);
                }
                else {
                    class113.aClass111_334 = class111_Sub2.method2102();
                }
                aClass35_328.anInt331 = (int)(class111_Sub2.aFloat4697 + (class111_Sub2.aFloat4700 * this.anIntArray4748[class113.anInt327] + class111_Sub2.aFloat4699 * this.anIntArray4788[class113.anInt327] + class111_Sub2.aFloat4690 * this.anIntArray4762[class113.anInt327]));
                aClass35_328.anInt330 = (int)(class111_Sub2.aFloat4691 + (class111_Sub2.aFloat4692 * this.anIntArray4748[class113.anInt327] + class111_Sub2.aFloat4688 * this.anIntArray4788[class113.anInt327] + class111_Sub2.aFloat4696 * this.anIntArray4762[class113.anInt327]));
                aClass35_328.anInt337 = (int)(class111_Sub2.aFloat4689 + (class111_Sub2.aFloat4693 * this.anIntArray4748[class113.anInt327] + class111_Sub2.aFloat4698 * this.anIntArray4788[class113.anInt327] + class111_Sub2.aFloat4694 * this.anIntArray4762[class113.anInt327]));
            }
        }
    }
    
    private final void method2370() {
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                this.anIntArray4748[i] = -this.anIntArray4748[i];
                this.anIntArray4762[i] = -this.anIntArray4762[i];
            }
            this.method2364();
        }
    }
    
    @Override
    final r ba(final r r) {
        return null;
    }
    
    @Override
    final boolean method2324() {
        if (this.aShortArray4804 == null) {
            return true;
        }
        for (int i = 0; i < this.aShortArray4804.length; ++i) {
            if (this.aShortArray4804[i] != -1 && !this.aHa_Sub2_4797.method1918(this.aShortArray4804[i])) {
                return false;
            }
        }
        return true;
    }
    
    private final void method2371(final int n, final boolean b, final boolean b2) {
        if (this.anIntArray4836[n] != -2) {
            final short n2 = this.aShortArray4782[n];
            final short n3 = this.aShortArray4750[n];
            final short n4 = this.aShortArray4832[n];
            final int n5 = this.anIntArray4771[n2];
            final int n6 = this.anIntArray4771[n3];
            final int n7 = this.anIntArray4771[n4];
            if (b && (n5 == -5000 || n6 == -5000 || n7 == -5000)) {
                final int n8 = this.anIntArray4828[n2];
                final int n9 = this.anIntArray4828[n3];
                final int n10 = this.anIntArray4828[n4];
                final int n11 = this.anIntArray4763[n2];
                final int n12 = this.anIntArray4763[n3];
                final int n13 = this.anIntArray4763[n4];
                final int n14 = this.anIntArray4829[n2];
                final int n15 = this.anIntArray4829[n3];
                final int n16 = this.anIntArray4829[n4];
                final int n17 = n8 - n9;
                final int n18 = n10 - n9;
                final int n19 = n11 - n12;
                final int n20 = n13 - n12;
                final int n21 = n14 - n15;
                final int n22 = n16 - n15;
                if (n9 * (n19 * n22 - n21 * n20) + n12 * (n21 * n18 - n17 * n22) + n15 * (n17 * n20 - n19 * n18) > 0) {
                    this.method2368(n);
                }
            }
            else if (this.anIntArray4758[n] != -1 || (n5 - n6) * (this.anIntArray4812[n4] - this.anIntArray4812[n3]) - (this.anIntArray4812[n2] - this.anIntArray4812[n3]) * (n7 - n6) > 0) {
                if (n5 < 0 || n6 < 0 || n7 < 0 || n5 > this.aClass235_4807.anInt1783 || n6 > this.aClass235_4807.anInt1783 || n7 > this.aClass235_4807.anInt1783) {
                    this.aClass12_4768.aBoolean135 = true;
                }
                else {
                    this.aClass12_4768.aBoolean135 = false;
                }
                if (b2) {
                    final int n23 = this.anIntArray4758[n];
                    if (n23 == -1 || !this.aClass170Array4827[n23].aBoolean1314) {
                        this.method2373(n);
                    }
                }
                else {
                    final int n24 = this.anIntArray4758[n];
                    if (n24 != -1) {
                        final Class170 class170 = this.aClass170Array4827[n24];
                        final Class329 class171 = this.aClass329Array4821[n24];
                        if (!class170.aBoolean1314) {
                            this.method2365(n);
                        }
                        this.aHa_Sub2_4797.method1923(class171.anInt2773, class171.anInt2767, class171.anInt2764, class171.anInt2766, class171.anInt2760, class171.anInt2769, class170.aShort1310 & 0xFFFF, class171.anInt2770, class170.aByte1308, class170.aByte1312);
                    }
                    else {
                        this.method2365(n);
                    }
                }
            }
        }
    }
    
    private final boolean method2372(final int n, final int n2, final Class111 class111, final boolean b, final int n3, final int n4) {
        this.aClass111_Sub2_4819 = (Class111_Sub2)class111;
        final Class111_Sub2 aClass111_Sub2_4513 = this.aHa_Sub2_4797.aClass111_Sub2_4513;
        final float n5 = aClass111_Sub2_4513.aFloat4697 + (aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4697 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4691 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4689);
        final float n6 = aClass111_Sub2_4513.aFloat4691 + (aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4697 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4691 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4689);
        final float n7 = aClass111_Sub2_4513.aFloat4689 + (aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4697 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4691 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4689);
        final float n8 = aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4700 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4692 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4693;
        final float n9 = aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4699 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4688 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4698;
        final float n10 = aClass111_Sub2_4513.aFloat4700 * this.aClass111_Sub2_4819.aFloat4690 + aClass111_Sub2_4513.aFloat4699 * this.aClass111_Sub2_4819.aFloat4696 + aClass111_Sub2_4513.aFloat4690 * this.aClass111_Sub2_4819.aFloat4694;
        final float n11 = aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4700 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4692 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4693;
        final float n12 = aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4699 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4688 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4698;
        final float n13 = aClass111_Sub2_4513.aFloat4692 * this.aClass111_Sub2_4819.aFloat4690 + aClass111_Sub2_4513.aFloat4688 * this.aClass111_Sub2_4819.aFloat4696 + aClass111_Sub2_4513.aFloat4696 * this.aClass111_Sub2_4819.aFloat4694;
        final float n14 = aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4700 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4692 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4693;
        final float n15 = aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4699 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4688 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4698;
        final float n16 = aClass111_Sub2_4513.aFloat4693 * this.aClass111_Sub2_4819.aFloat4690 + aClass111_Sub2_4513.aFloat4698 * this.aClass111_Sub2_4819.aFloat4696 + aClass111_Sub2_4513.aFloat4694 * this.aClass111_Sub2_4819.aFloat4694;
        boolean b2 = false;
        final int anInt4510 = this.aHa_Sub2_4797.anInt4510;
        final int anInt4511 = this.aHa_Sub2_4797.anInt4511;
        final int anInt4512 = this.aHa_Sub2_4797.anInt4514;
        final int anInt4513 = this.aHa_Sub2_4797.anInt4490;
        int n17 = Integer.MAX_VALUE;
        int n18 = Integer.MIN_VALUE;
        int n19 = Integer.MAX_VALUE;
        int n20 = Integer.MIN_VALUE;
        this.method2351(Thread.currentThread());
        if (!this.aBoolean4778) {
            this.method2346();
        }
        final int n21 = this.aShort4801 - this.aShort4794 >> 1;
        final int n22 = this.aShort4808 - this.aShort4770 >> 1;
        final int n23 = this.aShort4753 - this.aShort4774 >> 1;
        final short n24 = (short)(this.aShort4794 + n21);
        final short n25 = (short)(this.aShort4770 + n22);
        final short n26 = (short)(this.aShort4774 + n23);
        final int n27 = n24 - (n21 << n3);
        final int n28 = n25 - (n22 << n3);
        final int n29 = n26 - (n23 << n3);
        final int n30 = n24 + (n21 << n3);
        final int n31 = n25 + (n22 << n3);
        final int n32 = n26 + (n23 << n3);
        this.anIntArray4767[0] = n27;
        this.anIntArray4818[0] = n28;
        this.anIntArray4747[0] = n29;
        this.anIntArray4767[1] = n30;
        this.anIntArray4818[1] = n28;
        this.anIntArray4747[1] = n29;
        this.anIntArray4767[2] = n27;
        this.anIntArray4818[2] = n31;
        this.anIntArray4747[2] = n29;
        this.anIntArray4767[3] = n30;
        this.anIntArray4818[3] = n31;
        this.anIntArray4747[3] = n29;
        this.anIntArray4767[4] = n27;
        this.anIntArray4818[4] = n28;
        this.anIntArray4747[4] = n32;
        this.anIntArray4767[5] = n30;
        this.anIntArray4818[5] = n28;
        this.anIntArray4747[5] = n32;
        this.anIntArray4767[6] = n27;
        this.anIntArray4818[6] = n31;
        this.anIntArray4747[6] = n32;
        this.anIntArray4767[7] = n30;
        this.anIntArray4818[7] = n31;
        this.anIntArray4747[7] = n32;
        for (int i = 0; i < 8; ++i) {
            final int n33 = this.anIntArray4767[i];
            final int n34 = this.anIntArray4818[i];
            final int n35 = this.anIntArray4747[i];
            final float n36 = n5 + (n8 * n33 + n9 * n34 + n10 * n35);
            final float n37 = n6 + (n11 * n33 + n12 * n34 + n13 * n35);
            float n38 = n7 + (n14 * n33 + n15 * n34 + n16 * n35);
            if (n38 >= this.aHa_Sub2_4797.anInt4502) {
                if (n4 > 0) {
                    n38 = n4;
                }
                final int n39 = anInt4510 + (int)(n36 * anInt4512 / n38);
                final int n40 = anInt4511 + (int)(n37 * anInt4513 / n38);
                if (n39 < n17) {
                    n17 = n39;
                }
                if (n39 > n18) {
                    n18 = n39;
                }
                if (n40 < n19) {
                    n19 = n40;
                }
                if (n40 > n20) {
                    n20 = n40;
                }
                b2 = true;
            }
        }
        if (b2 && n > n17 && n < n18 && n2 > n19 && n2 < n20) {
            if (b) {
                return true;
            }
            for (int j = 0; j < this.anInt4786; ++j) {
                final int n41 = this.anIntArray4748[j];
                final int n42 = this.anIntArray4788[j];
                final int n43 = this.anIntArray4762[j];
                final float n44 = n5 + (n8 * n41 + n9 * n42 + n10 * n43);
                final float n45 = n6 + (n11 * n41 + n12 * n42 + n13 * n43);
                float n46 = n7 + (n14 * n41 + n15 * n42 + n16 * n43);
                if (n46 >= this.aHa_Sub2_4797.anInt4502) {
                    if (n4 > 0) {
                        n46 = n4;
                    }
                    this.anIntArray4771[j] = anInt4510 + (int)(n44 * anInt4512 / n46);
                    this.anIntArray4812[j] = anInt4511 + (int)(n45 * anInt4513 / n46);
                }
                else {
                    this.anIntArray4771[j] = -999999;
                }
            }
            for (int k = 0; k < this.anInt4780; ++k) {
                if (this.anIntArray4771[this.aShortArray4782[k]] != -999999 && this.anIntArray4771[this.aShortArray4750[k]] != -999999 && this.anIntArray4771[this.aShortArray4832[k]] != -999999 && this.method2361(n, n2, this.anIntArray4812[this.aShortArray4782[k]], this.anIntArray4812[this.aShortArray4750[k]], this.anIntArray4812[this.aShortArray4832[k]], this.anIntArray4771[this.aShortArray4782[k]], this.anIntArray4771[this.aShortArray4750[k]], this.anIntArray4771[this.aShortArray4832[k]])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    final int ua() {
        return this.anInt4816;
    }
    
    @Override
    final void H(final int n, final int n2, final int n3) {
        if (n != 0 && (this.anInt4816 & 0x1) != 0x1) {
            throw new IllegalStateException();
        }
        if (n2 != 0 && (this.anInt4816 & 0x2) != 0x2) {
            throw new IllegalStateException();
        }
        if (n3 != 0 && (this.anInt4816 & 0x4) != 0x4) {
            throw new IllegalStateException();
        }
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                final int[] anIntArray4748 = this.anIntArray4748;
                final int n4 = i;
                anIntArray4748[n4] += n;
                final int[] anIntArray4749 = this.anIntArray4788;
                final int n5 = i;
                anIntArray4749[n5] += n2;
                final int[] anIntArray4750 = this.anIntArray4762;
                final int n6 = i;
                anIntArray4750[n6] += n3;
            }
        }
    }
    
    private final void method2373(final int n) {
        if (!this.aClass235_4807.aBoolean1759) {
            final short n2 = this.aShortArray4782[n];
            final short n3 = this.aShortArray4750[n];
            final short n4 = this.aShortArray4832[n];
            int n5 = this.anIntArray4766[n2] - this.aClass235_4807.anInt1761;
            if (n5 > 255) {
                n5 = 255;
            }
            else if (n5 < 0) {
                n5 = 0;
            }
            int n6 = this.anIntArray4766[n3] - this.aClass235_4807.anInt1761;
            if (n6 > 255) {
                n6 = 255;
            }
            else if (n6 < 0) {
                n6 = 0;
            }
            int n7 = this.anIntArray4766[n4] - this.aClass235_4807.anInt1761;
            if (n7 > 255) {
                n7 = 255;
            }
            else if (n7 < 0) {
                n7 = 0;
            }
            final int n8 = n5 + n6 + n7;
            if (n8 != 765) {
                if (n8 == 0) {
                    this.method2365(n);
                }
                else {
                    if (this.aByteArray4822 == null) {
                        this.aClass12_4768.anInt137 = 0;
                    }
                    else {
                        this.aClass12_4768.anInt137 = (this.aByteArray4822[n] & 0xFF);
                    }
                    if (this.aShortArray4804 == null || this.aShortArray4804[n] == -1) {
                        if (this.anIntArray4836[n] == -1) {
                            this.aClass12_4768.method206(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], Class98_Sub46_Sub5.method1544(n5 << 24 | this.aClass235_4807.anInt1763, (byte)123, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n6 << 24 | this.aClass235_4807.anInt1763, (byte)120, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n7 << 24 | this.aClass235_4807.anInt1763, (byte)120, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]));
                        }
                        else {
                            this.aClass12_4768.method206(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], Class98_Sub46_Sub5.method1544(n5 << 24 | this.aClass235_4807.anInt1763, (byte)123, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n6 << 24 | this.aClass235_4807.anInt1763, (byte)105, Class221.anIntArray1665[this.anIntArray4760[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n7 << 24 | this.aClass235_4807.anInt1763, (byte)127, Class221.anIntArray1665[this.anIntArray4836[n] & 0xFFFF]));
                        }
                    }
                    else {
                        int n9 = -16777216;
                        if (this.aByteArray4822 != null) {
                            n9 = 255 - (this.aByteArray4822[n] & 0xFF) << 24;
                        }
                        if (this.anIntArray4836[n] == -1) {
                            final int n10 = n9 | (this.anIntArray4791[n] & 0xFFFFFF);
                            this.aClass12_4768.method212(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n10, n10, n10, this.aClass235_4807.anInt1763, n5, n6, n7, this.aShortArray4804[n]);
                        }
                        else {
                            this.aClass12_4768.method212(this.anIntArray4812[n2], this.anIntArray4812[n3], this.anIntArray4812[n4], this.anIntArray4771[n2], this.anIntArray4771[n3], this.anIntArray4771[n4], this.anIntArray4766[n2], this.anIntArray4766[n3], this.anIntArray4766[n4], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n9 | (this.anIntArray4791[n] & 0xFFFFFF), n9 | (this.anIntArray4760[n] & 0xFFFFFF), n9 | (this.anIntArray4836[n] & 0xFFFFFF), this.aClass235_4807.anInt1763, n5, n6, n7, this.aShortArray4804[n]);
                        }
                    }
                }
            }
        }
        else {
            final short n11 = this.aShortArray4782[n];
            final short n12 = this.aShortArray4750[n];
            final short n13 = this.aShortArray4832[n];
            int n14 = 0;
            int n15 = 0;
            int n16 = 0;
            if (this.anIntArray4817[n11] > this.aClass235_4807.anInt1757) {
                n14 = 255;
            }
            else if (this.anIntArray4817[n11] > this.aClass235_4807.anInt1754) {
                n14 = (this.aClass235_4807.anInt1754 - this.anIntArray4817[n11]) * 255 / (this.aClass235_4807.anInt1754 - this.aClass235_4807.anInt1757);
            }
            if (this.anIntArray4817[n12] > this.aClass235_4807.anInt1757) {
                n15 = 255;
            }
            else if (this.anIntArray4817[n12] > this.aClass235_4807.anInt1754) {
                n15 = (this.aClass235_4807.anInt1754 - this.anIntArray4817[n12]) * 255 / (this.aClass235_4807.anInt1754 - this.aClass235_4807.anInt1757);
            }
            if (this.anIntArray4817[n13] > this.aClass235_4807.anInt1757) {
                n16 = 255;
            }
            else if (this.anIntArray4817[n13] > this.aClass235_4807.anInt1754) {
                n16 = (this.aClass235_4807.anInt1754 - this.anIntArray4817[n13]) * 255 / (this.aClass235_4807.anInt1754 - this.aClass235_4807.anInt1757);
            }
            if (this.aByteArray4822 == null) {
                this.aClass12_4768.anInt137 = 0;
            }
            else {
                this.aClass12_4768.anInt137 = (this.aByteArray4822[n] & 0xFF);
            }
            if (this.aShortArray4804 == null || this.aShortArray4804[n] == -1) {
                if (this.anIntArray4836[n] == -1) {
                    this.aClass12_4768.method206(this.anIntArray4812[n11], this.anIntArray4812[n12], this.anIntArray4812[n13], this.anIntArray4771[n11], this.anIntArray4771[n12], this.anIntArray4771[n13], this.anIntArray4766[n11], this.anIntArray4766[n12], this.anIntArray4766[n13], Class98_Sub46_Sub5.method1544(n14 << 24 | this.aClass235_4807.anInt1763, (byte)124, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n15 << 24 | this.aClass235_4807.anInt1763, (byte)127, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n16 << 24 | this.aClass235_4807.anInt1763, (byte)109, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]));
                }
                else {
                    this.aClass12_4768.method206(this.anIntArray4812[n11], this.anIntArray4812[n12], this.anIntArray4812[n13], this.anIntArray4771[n11], this.anIntArray4771[n12], this.anIntArray4771[n13], this.anIntArray4766[n11], this.anIntArray4766[n12], this.anIntArray4766[n13], Class98_Sub46_Sub5.method1544(n14 << 24 | this.aClass235_4807.anInt1763, (byte)127, Class221.anIntArray1665[this.anIntArray4791[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n15 << 24 | this.aClass235_4807.anInt1763, (byte)107, Class221.anIntArray1665[this.anIntArray4760[n] & 0xFFFF]), Class98_Sub46_Sub5.method1544(n16 << 24 | this.aClass235_4807.anInt1763, (byte)113, Class221.anIntArray1665[this.anIntArray4836[n] & 0xFFFF]));
                }
            }
            else {
                int n17 = -16777216;
                if (this.aByteArray4822 != null) {
                    n17 = 255 - (this.aByteArray4822[n] & 0xFF) << 24;
                }
                if (this.anIntArray4836[n] == -1) {
                    final int n18 = n17 | (this.anIntArray4791[n] & 0xFFFFFF);
                    this.aClass12_4768.method212(this.anIntArray4812[n11], this.anIntArray4812[n12], this.anIntArray4812[n13], this.anIntArray4771[n11], this.anIntArray4771[n12], this.anIntArray4771[n13], this.anIntArray4766[n11], this.anIntArray4766[n12], this.anIntArray4766[n13], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n18, n18, n18, this.aClass235_4807.anInt1763, n14, n15, n16, this.aShortArray4804[n]);
                }
                else {
                    this.aClass12_4768.method212(this.anIntArray4812[n11], this.anIntArray4812[n12], this.anIntArray4812[n13], this.anIntArray4771[n11], this.anIntArray4771[n12], this.anIntArray4771[n13], this.anIntArray4766[n11], this.anIntArray4766[n12], this.anIntArray4766[n13], this.aFloatArrayArray4798[n][0], this.aFloatArrayArray4798[n][1], this.aFloatArrayArray4798[n][2], this.aFloatArrayArray4806[n][0], this.aFloatArrayArray4806[n][1], this.aFloatArrayArray4806[n][2], n17 | (this.anIntArray4791[n] & 0xFFFFFF), n17 | (this.anIntArray4760[n] & 0xFFFFFF), n17 | (this.anIntArray4836[n] & 0xFFFFFF), this.aClass235_4807.anInt1763, n14, n15, n16, this.aShortArray4804[n]);
                }
            }
        }
    }
    
    @Override
    final int EA() {
        if (!this.aBoolean4778) {
            this.method2346();
        }
        return this.aShort4808;
    }
    
    @Override
    final void method2327() {
        if (this.aHa_Sub2_4797.anInt4508 > 1) {
            synchronized (this) {
                super.aBoolean1181 = false;
                this.notifyAll();
            }
        }
    }
    
    @Override
    final void VA(final int n) {
        if ((this.anInt4816 & 0x3) != 0x3) {
            throw new IllegalStateException();
        }
        final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
        final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                final int n4 = this.anIntArray4788[i] * n2 + this.anIntArray4748[i] * n3 >> 14;
                this.anIntArray4788[i] = this.anIntArray4788[i] * n3 - this.anIntArray4748[i] * n2 >> 14;
                this.anIntArray4748[i] = n4;
            }
            this.method2364();
        }
    }
    
    private final void method2374() {
        synchronized (this) {
            for (int i = 0; i < this.anInt4786; ++i) {
                final int n = this.anIntArray4762[i];
                this.anIntArray4762[i] = this.anIntArray4748[i];
                this.anIntArray4748[i] = -n;
            }
            this.method2364();
        }
    }
    
    @Override
    final void aa(final short n, final short n2) {
        if (this.aShortArray4804 != null) {
            if (!this.aBoolean4802 && n2 >= 0) {
                final Class238 method11 = this.aHa_Sub2_4797.aD938.method11(n2 & 0xFFFF, -28755);
                if (method11.aByte1823 != 0 || method11.aByte1837 != 0) {
                    this.aBoolean4802 = true;
                }
            }
            for (int i = 0; i < this.anInt4780; ++i) {
                if (this.aShortArray4804[i] == n) {
                    this.aShortArray4804[i] = n2;
                }
            }
        }
    }
    
    @Override
    final Class35[] method2322() {
        return this.aClass35Array4787;
    }
    
    private final void method2375() {
        synchronized (this) {
            for (int i = 0; i < this.anInt4814; ++i) {
                final int n = this.anIntArray4762[i];
                this.anIntArray4762[i] = this.anIntArray4748[i];
                this.anIntArray4748[i] = -n;
                if (this.aClass274Array4789[i] != null) {
                    final int anInt2042 = this.aClass274Array4789[i].anInt2042;
                    this.aClass274Array4789[i].anInt2042 = this.aClass274Array4789[i].anInt2044;
                    this.aClass274Array4789[i].anInt2044 = -anInt2042;
                }
            }
            if (this.aClass296Array4823 != null) {
                for (int j = 0; j < this.anInt4780; ++j) {
                    if (this.aClass296Array4823[j] != null) {
                        final int anInt2043 = this.aClass296Array4823[j].anInt2413;
                        this.aClass296Array4823[j].anInt2413 = this.aClass296Array4823[j].anInt2411;
                        this.aClass296Array4823[j].anInt2411 = -anInt2043;
                    }
                }
            }
            for (int k = this.anInt4814; k < this.anInt4786; ++k) {
                final int n2 = this.anIntArray4762[k];
                this.anIntArray4762[k] = this.anIntArray4748[k];
                this.anIntArray4748[k] = -n2;
            }
            this.anInt4784 = 0;
            this.aBoolean4778 = false;
        }
    }
    
    @Override
    final void C(final int anInt4761) {
        if ((this.anInt4816 & 0x1000) != 0x1000) {
            throw new IllegalStateException();
        }
        this.anInt4761 = anInt4761;
        this.anInt4784 = 0;
    }
    
    Class146_Sub1(final ha_Sub2 aHa_Sub2_4797) {
        this.aBoolean4755 = false;
        this.anInt4780 = 0;
        this.aBoolean4802 = false;
        this.aBoolean4778 = false;
        this.anInt4786 = 0;
        this.anInt4814 = 0;
        this.anInt4784 = 0;
        this.aBoolean4815 = false;
        this.aBoolean4765 = false;
        this.aBoolean4799 = false;
        this.aHa_Sub2_4797 = aHa_Sub2_4797;
    }
    
    @Override
    final void method2326() {
    }
    
    Class146_Sub1(final ha_Sub2 aHa_Sub2_4797, final Class178 class178, final int anInt4816, final int anInt4817, final int anInt4818, final int n) {
        this.aBoolean4755 = false;
        this.anInt4780 = 0;
        this.aBoolean4802 = false;
        this.aBoolean4778 = false;
        this.anInt4786 = 0;
        this.anInt4814 = 0;
        this.anInt4784 = 0;
        this.aBoolean4815 = false;
        this.aBoolean4765 = false;
        this.aBoolean4799 = false;
        this.aHa_Sub2_4797 = aHa_Sub2_4797;
        this.anInt4816 = anInt4816;
        this.anInt4761 = anInt4817;
        this.anInt4805 = anInt4818;
        final d ad938 = this.aHa_Sub2_4797.aD938;
        this.anInt4786 = class178.anInt1407;
        this.anInt4814 = class178.anInt1406;
        this.anIntArray4748 = class178.anIntArray1416;
        this.anIntArray4788 = class178.anIntArray1400;
        this.anIntArray4762 = class178.anIntArray1418;
        this.anInt4780 = class178.anInt1391;
        this.aShortArray4782 = class178.aShortArray1393;
        this.aShortArray4750 = class178.aShortArray1410;
        this.aShortArray4832 = class178.aShortArray1392;
        this.aByteArray4772 = class178.aByteArray1402;
        this.aShortArray4800 = class178.aShortArray1415;
        this.aByteArray4822 = class178.aByteArray1411;
        this.aShortArray4795 = class178.aShortArray1394;
        this.aByteArray4793 = class178.aByteArray1414;
        this.aClass87Array4813 = class178.aClass87Array1413;
        this.aClass35Array4787 = class178.aClass35Array1398;
        this.aShortArray4790 = class178.aShortArray1408;
        final int[] array = new int[this.anInt4780];
        for (int i = 0; i < this.anInt4780; ++i) {
            array[i] = i;
        }
        final long[] array2 = new long[this.anInt4780];
        final boolean b = (this.anInt4816 & 0x100) != 0x0;
        for (int j = 0; j < this.anInt4780; ++j) {
            final int n2 = array[j];
            Class238 method11 = null;
            int n3 = 0;
            final int n4 = 0;
            byte aByte1820 = 0;
            byte aByte1821 = 0;
            if (class178.aClass106Array1419 != null) {
                boolean b2 = false;
                for (int k = 0; k < class178.aClass106Array1419.length; ++k) {
                    final Class106 class179 = class178.aClass106Array1419[k];
                    if (n2 == class179.anInt906) {
                        final Class177 method12 = Class67.method689(class179.anInt905, (byte)(-102));
                        if (method12.aBoolean1377) {
                            b2 = true;
                        }
                        if (method12.anInt1373 != -1 && ad938.method11(method12.anInt1373, -28755).anInt1818 == 2) {
                            this.aBoolean4755 = true;
                        }
                    }
                }
                if (b2) {
                    array2[j] = Long.MAX_VALUE;
                }
            }
            int n5 = -1;
            if (class178.aShortArray1409 != null) {
                n5 = class178.aShortArray1409[n2];
                if (n5 != -1) {
                    method11 = ad938.method11(n5 & 0xFFFF, -28755);
                    if ((n & 0x40) == 0x0 || !method11.aBoolean1825) {
                        aByte1820 = method11.aByte1820;
                        aByte1821 = method11.aByte1816;
                    }
                    else {
                        n5 = -1;
                    }
                }
            }
            final boolean b3 = (this.aByteArray4822 != null && this.aByteArray4822[n2] != 0) || (method11 != null && method11.anInt1818 == 2);
            if ((b || b3) && this.aByteArray4772 != null) {
                n3 += this.aByteArray4772[n2] << 17;
            }
            if (b3) {
                n3 += 65536;
            }
            array2[j] = (n3 + ((aByte1820 & 0xFF) << 8) + (aByte1821 & 0xFF) << 32) + (n4 + ((n5 & 0xFFFF) << 16) + (j & 0xFFFF));
            this.aBoolean4755 |= b3;
        }
        Class90.method882(array, array2, (byte)118);
        if (class178.aClass106Array1419 != null) {
            this.anInt4776 = class178.aClass106Array1419.length;
            this.aClass170Array4827 = new Class170[this.anInt4776];
            this.aClass329Array4821 = new Class329[this.anInt4776];
            for (int l = 0; l < class178.aClass106Array1419.length; ++l) {
                final Class106 class180 = class178.aClass106Array1419[l];
                final Class177 method13 = Class67.method689(class180.anInt905, (byte)(-94));
                final int n6 = (Class221.anIntArray1665[class178.aShortArray1415[class180.anInt906] & 0xFFFF] & 0xFFFFFF) | 255 - ((class178.aByteArray1411 != null) ? (class178.aByteArray1411[class180.anInt906] & 0xFF) : 0) << 24;
                this.aClass170Array4827[l] = new Class170(class180.anInt906, class178.aShortArray1393[class180.anInt906], class178.aShortArray1410[class180.anInt906], class178.aShortArray1392[class180.anInt906], method13.anInt1374, method13.anInt1380, method13.anInt1373, method13.anInt1384, method13.anInt1379, method13.aBoolean1377, class180.anInt907);
                this.aClass329Array4821[l] = new Class329(n6);
            }
        }
        this.aFloatArrayArray4798 = new float[this.anInt4780][];
        this.aFloatArrayArray4806 = new float[this.anInt4780][];
        final Class250 method14 = Class224_Sub2.method2836(array, true, class178, this.anInt4780);
        final float[] aFloatArray1799 = this.aHa_Sub2_4797.method1921(Thread.currentThread()).aFloatArray1799;
        boolean b4 = false;
        for (int n7 = 0; n7 < this.anInt4780; ++n7) {
            final int n8 = array[n7];
            int n9;
            if (class178.aByteArray1420 == null) {
                n9 = -1;
            }
            else {
                n9 = class178.aByteArray1420[n8];
            }
            int n10 = (class178.aShortArray1409 == null) ? -1 : class178.aShortArray1409[n8];
            if (n10 != -1 && (n & 0x40) != 0x0 && ad938.method11(n10 & 0xFFFF, -28755).aBoolean1825) {
                n10 = -1;
            }
            if (n10 != -1) {
                b4 = true;
                final float[][] aFloatArrayArray4798 = this.aFloatArrayArray4798;
                final int n11 = n8;
                final float[] array3 = new float[3];
                aFloatArrayArray4798[n11] = array3;
                final float[] array4 = array3;
                final float[][] aFloatArrayArray4799 = this.aFloatArrayArray4806;
                final int n12 = n8;
                final float[] array5 = new float[3];
                aFloatArrayArray4799[n12] = array5;
                final float[] array6 = array5;
                if (n9 == -1) {
                    array4[0] = 0.0f;
                    array6[0] = 1.0f;
                    array6[1] = (array4[1] = 1.0f);
                    array6[2] = (array4[2] = 0.0f);
                }
                else {
                    final int n13 = n9 & 0xFF;
                    final byte b5 = class178.aByteArray1388[n13];
                    if (b5 == 0) {
                        final short n14 = this.aShortArray4782[n8];
                        final short n15 = this.aShortArray4750[n8];
                        final short n16 = this.aShortArray4832[n8];
                        final short n17 = class178.aShortArray1403[n13];
                        final short n18 = class178.aShortArray1421[n13];
                        final short n19 = class178.aShortArray1385[n13];
                        final float n20 = this.anIntArray4748[n17];
                        final float n21 = this.anIntArray4788[n17];
                        final float n22 = this.anIntArray4762[n17];
                        final float n23 = this.anIntArray4748[n18] - n20;
                        final float n24 = this.anIntArray4788[n18] - n21;
                        final float n25 = this.anIntArray4762[n18] - n22;
                        final float n26 = this.anIntArray4748[n19] - n20;
                        final float n27 = this.anIntArray4788[n19] - n21;
                        final float n28 = this.anIntArray4762[n19] - n22;
                        final float n29 = this.anIntArray4748[n14] - n20;
                        final float n30 = this.anIntArray4788[n14] - n21;
                        final float n31 = this.anIntArray4762[n14] - n22;
                        final float n32 = this.anIntArray4748[n15] - n20;
                        final float n33 = this.anIntArray4788[n15] - n21;
                        final float n34 = this.anIntArray4762[n15] - n22;
                        final float n35 = this.anIntArray4748[n16] - n20;
                        final float n36 = this.anIntArray4788[n16] - n21;
                        final float n37 = this.anIntArray4762[n16] - n22;
                        final float n38 = n24 * n28 - n25 * n27;
                        final float n39 = n25 * n26 - n23 * n28;
                        final float n40 = n23 * n27 - n24 * n26;
                        final float n41 = n27 * n40 - n28 * n39;
                        final float n42 = n28 * n38 - n26 * n40;
                        final float n43 = n26 * n39 - n27 * n38;
                        final float n44 = 1.0f / (n41 * n23 + n42 * n24 + n43 * n25);
                        array4[0] = (n41 * n29 + n42 * n30 + n43 * n31) * n44;
                        array4[1] = (n41 * n32 + n42 * n33 + n43 * n34) * n44;
                        array4[2] = (n41 * n35 + n42 * n36 + n43 * n37) * n44;
                        final float n45 = n24 * n40 - n25 * n39;
                        final float n46 = n25 * n38 - n23 * n40;
                        final float n47 = n23 * n39 - n24 * n38;
                        final float n48 = 1.0f / (n45 * n26 + n46 * n27 + n47 * n28);
                        array6[0] = (n45 * n29 + n46 * n30 + n47 * n31) * n48;
                        array6[1] = (n45 * n32 + n46 * n33 + n47 * n34) * n48;
                        array6[2] = (n45 * n35 + n46 * n36 + n47 * n37) * n48;
                    }
                    else {
                        final short n49 = this.aShortArray4782[n8];
                        final short n50 = this.aShortArray4750[n8];
                        final short n51 = this.aShortArray4832[n8];
                        final int n52 = method14.anIntArray1911[n13];
                        final int n53 = method14.anIntArray1915[n13];
                        final int n54 = method14.anIntArray1912[n13];
                        final float[] array7 = method14.aFloatArrayArray1910[n13];
                        final byte b6 = class178.aByteArray1399[n13];
                        final float n55 = class178.anIntArray1412[n13] / 256.0f;
                        if (b5 == 1) {
                            final float n56 = class178.anIntArray1390[n13] / 1024.0f;
                            Class98_Sub37.method1460(this.anIntArray4748[n49], n54, 8, this.anIntArray4762[n49], n53, this.anIntArray4788[n49], array7, n56, b6, n52, n55, aFloatArray1799);
                            array4[0] = aFloatArray1799[0];
                            array6[0] = aFloatArray1799[1];
                            Class98_Sub37.method1460(this.anIntArray4748[n50], n54, 8, this.anIntArray4762[n50], n53, this.anIntArray4788[n50], array7, n56, b6, n52, n55, aFloatArray1799);
                            array4[1] = aFloatArray1799[0];
                            array6[1] = aFloatArray1799[1];
                            Class98_Sub37.method1460(this.anIntArray4748[n51], n54, 8, this.anIntArray4762[n51], n53, this.anIntArray4788[n51], array7, n56, b6, n52, n55, aFloatArray1799);
                            array4[2] = aFloatArray1799[0];
                            array6[2] = aFloatArray1799[1];
                            final float n57 = n56 / 2.0f;
                            if ((b6 & 0x1) == 0x0) {
                                if (array4[1] - array4[0] > n57) {
                                    final float[] array8 = array4;
                                    final int n58 = 1;
                                    array8[n58] -= n56;
                                }
                                else if (array4[0] - array4[1] > n57) {
                                    final float[] array9 = array4;
                                    final int n59 = 1;
                                    array9[n59] += n56;
                                }
                                if (array4[2] - array4[0] > n57) {
                                    final float[] array10 = array4;
                                    final int n60 = 2;
                                    array10[n60] -= n56;
                                }
                                else if (array4[0] - array4[2] > n57) {
                                    final float[] array11 = array4;
                                    final int n61 = 2;
                                    array11[n61] += n56;
                                }
                            }
                            else {
                                if (array6[1] - array6[0] > n57) {
                                    final float[] array12 = array6;
                                    final int n62 = 1;
                                    array12[n62] -= n56;
                                }
                                else if (array6[0] - array6[1] > n57) {
                                    final float[] array13 = array6;
                                    final int n63 = 1;
                                    array13[n63] += n56;
                                }
                                if (array6[2] - array6[0] > n57) {
                                    final float[] array14 = array6;
                                    final int n64 = 2;
                                    array14[n64] -= n56;
                                }
                                else if (array6[0] - array6[2] > n57) {
                                    final float[] array15 = array6;
                                    final int n65 = 2;
                                    array15[n65] += n56;
                                }
                            }
                        }
                        else if (b5 == 2) {
                            final float n66 = class178.anIntArray1397[n13] / 256.0f;
                            final float n67 = class178.anIntArray1386[n13] / 256.0f;
                            final int n68 = this.anIntArray4748[n50] - this.anIntArray4748[n49];
                            final int n69 = this.anIntArray4788[n50] - this.anIntArray4788[n49];
                            final int n70 = this.anIntArray4762[n50] - this.anIntArray4762[n49];
                            final int n71 = this.anIntArray4748[n51] - this.anIntArray4748[n49];
                            final int n72 = this.anIntArray4788[n51] - this.anIntArray4788[n49];
                            final int n73 = this.anIntArray4762[n51] - this.anIntArray4762[n49];
                            final int n74 = n69 * n73 - n72 * n70;
                            final int n75 = n70 * n71 - n73 * n68;
                            final int n76 = n68 * n72 - n71 * n69;
                            final int method15 = Class69.method696((n74 * array7[0] + n75 * array7[1] + n76 * array7[2]) / (64.0f / class178.anIntArray1389[n13]), (n74 * array7[6] + n75 * array7[7] + n76 * array7[8]) / (64.0f / class178.anIntArray1390[n13]), (byte)(-63), (n74 * array7[3] + n75 * array7[4] + n76 * array7[5]) / (64.0f / class178.anIntArray1404[n13]));
                            Class48_Sub1_Sub1.method461(this.anIntArray4788[n49], n67, b6, n53, n55, 112, n66, array7, method15, aFloatArray1799, n54, this.anIntArray4762[n49], n52, this.anIntArray4748[n49]);
                            array4[0] = aFloatArray1799[0];
                            array6[0] = aFloatArray1799[1];
                            Class48_Sub1_Sub1.method461(this.anIntArray4788[n50], n67, b6, n53, n55, 121, n66, array7, method15, aFloatArray1799, n54, this.anIntArray4762[n50], n52, this.anIntArray4748[n50]);
                            array4[1] = aFloatArray1799[0];
                            array6[1] = aFloatArray1799[1];
                            Class48_Sub1_Sub1.method461(this.anIntArray4788[n51], n67, b6, n53, n55, 124, n66, array7, method15, aFloatArray1799, n54, this.anIntArray4762[n51], n52, this.anIntArray4748[n51]);
                            array4[2] = aFloatArray1799[0];
                            array6[2] = aFloatArray1799[1];
                        }
                        else if (b5 == 3) {
                            Class243.method2943(aFloatArray1799, this.anIntArray4788[n49], b6, n54, array7, this.anIntArray4762[n49], n53, n55, this.anIntArray4748[n49], n52, 105);
                            array4[0] = aFloatArray1799[0];
                            array6[0] = aFloatArray1799[1];
                            Class243.method2943(aFloatArray1799, this.anIntArray4788[n50], b6, n54, array7, this.anIntArray4762[n50], n53, n55, this.anIntArray4748[n50], n52, 103);
                            array4[1] = aFloatArray1799[0];
                            array6[1] = aFloatArray1799[1];
                            Class243.method2943(aFloatArray1799, this.anIntArray4788[n51], b6, n54, array7, this.anIntArray4762[n51], n53, n55, this.anIntArray4748[n51], n52, 127);
                            array4[2] = aFloatArray1799[0];
                            array6[2] = aFloatArray1799[1];
                            if ((b6 & 0x1) == 0x0) {
                                if (array4[1] - array4[0] > 0.5f) {
                                    final float[] array16 = array4;
                                    final int n77 = 1;
                                    --array16[n77];
                                }
                                else if (array4[0] - array4[1] > 0.5f) {
                                    final float[] array17 = array4;
                                    final int n78 = 1;
                                    ++array17[n78];
                                }
                                if (array4[2] - array4[0] > 0.5f) {
                                    final float[] array18 = array4;
                                    final int n79 = 2;
                                    --array18[n79];
                                }
                                else if (array4[0] - array4[2] > 0.5f) {
                                    final float[] array19 = array4;
                                    final int n80 = 2;
                                    ++array19[n80];
                                }
                            }
                            else {
                                if (array6[1] - array6[0] > 0.5f) {
                                    final float[] array20 = array6;
                                    final int n81 = 1;
                                    --array20[n81];
                                }
                                else if (array6[0] - array6[1] > 0.5f) {
                                    final float[] array21 = array6;
                                    final int n82 = 1;
                                    ++array21[n82];
                                }
                                if (array6[2] - array6[0] > 0.5f) {
                                    final float[] array22 = array6;
                                    final int n83 = 2;
                                    --array22[n83];
                                }
                                else if (array6[0] - array6[2] > 0.5f) {
                                    final float[] array23 = array6;
                                    final int n84 = 2;
                                    ++array23[n84];
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!b4) {
            final float[][] array24 = null;
            this.aFloatArrayArray4806 = array24;
            this.aFloatArrayArray4798 = array24;
        }
        if (class178.anIntArray1417 != null && (this.anInt4816 & 0x20) != 0x0) {
            this.anIntArrayArray4834 = class178.method2595(19, true);
        }
        if (class178.anIntArray1395 != null && (this.anInt4816 & 0x180) != 0x0) {
            this.anIntArrayArray4833 = class178.method2591((byte)122);
        }
        if (class178.aClass106Array1419 != null && (this.anInt4816 & 0x400) != 0x0) {
            this.anIntArrayArray4796 = class178.method2596(21517);
        }
        if (class178.aShortArray1409 != null) {
            this.aShortArray4804 = new short[this.anInt4780];
            boolean b7 = false;
            for (int n85 = 0; n85 < this.anInt4780; ++n85) {
                final short n86 = class178.aShortArray1409[n85];
                if (n86 != -1) {
                    final Class238 method16 = this.aHa_Sub2_4797.aD938.method11(n86, -28755);
                    if ((n & 0x40) == 0x0 || !method16.aBoolean1825) {
                        this.aShortArray4804[n85] = n86;
                        b7 = true;
                        if (method16.anInt1818 == 2) {
                            this.aBoolean4755 = true;
                        }
                        if (method16.aByte1823 != 0 || method16.aByte1837 != 0) {
                            this.aBoolean4802 = true;
                        }
                    }
                    else {
                        this.aShortArray4804[n85] = -1;
                    }
                }
                else {
                    this.aShortArray4804[n85] = -1;
                }
            }
            if (!b7) {
                this.aShortArray4804 = null;
            }
        }
        else {
            this.aShortArray4804 = null;
        }
        if (this.aBoolean4755 || this.aClass170Array4827 != null) {
            this.aShortArray4809 = new short[this.anInt4780];
            for (int n87 = 0; n87 < this.anInt4780; ++n87) {
                this.aShortArray4809[n87] = (short)array[n87];
            }
        }
    }
    
    static {
        Class146_Sub1.anInt4830 = 0;
        Class146_Sub1.anInt4810 = 4096;
        Class146_Sub1.anInt4825 = 4096;
    }
}

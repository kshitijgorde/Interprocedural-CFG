// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub7 extends Class98
{
    Class377 aClass377_3849;
    byte[] aByteArray3850;
    
    final void method983() {
        this.aClass377_3849 = null;
    }
    
    final void method984() {
        Label_0408: {
            if (this.aClass377_3849 == null) {
                this.aClass377_3849 = new Class377(16);
                final int[] array = new int[16];
                final int[] array2 = new int[16];
                array[9] = (array2[9] = 128);
                final Class173 class173 = new Class173(this.aByteArray3850);
                for (int method2558 = class173.method2558(), i = 0; i < method2558; ++i) {
                    class173.method2555(i);
                    class173.method2556(i);
                    class173.method2552(i);
                }
                while (true) {
                    final int method2559 = class173.method2551();
                    while (class173.anIntArray1343[method2559] == class173.anIntArray1343[method2559]) {
                        class173.method2555(method2559);
                        final int method2560 = class173.method2559(method2559);
                        if (method2560 == 1) {
                            class173.method2553();
                            class173.method2552(method2559);
                            if (!class173.method2550()) {
                                break;
                            }
                            break Label_0408;
                        }
                        else {
                            final int n = method2560 & 0xF0;
                            if (n == 176) {
                                final int n2 = method2560 & 0xF;
                                final int n3 = method2560 >> 8 & 0x7F;
                                final int n4 = method2560 >> 16 & 0x7F;
                                if (n3 == 0) {
                                    array[n2] = (array[n2] & 0xFFE03FFF) + (n4 << 14);
                                }
                                if (n3 == 32) {
                                    array[n2] = (array[n2] & 0xFFFFC07F) + (n4 << 7);
                                }
                            }
                            if (n == 192) {
                                final int n5 = method2560 & 0xF;
                                array2[n5] = array[n5] + (method2560 >> 8 & 0x7F);
                            }
                            if (n == 144) {
                                final int n6 = method2560 & 0xF;
                                final int n7 = method2560 >> 8 & 0x7F;
                                if ((method2560 >> 16 & 0x7F) > 0) {
                                    final int n8 = array2[n6];
                                    Class98_Sub14 class98_Sub14 = (Class98_Sub14)this.aClass377_3849.method3990(n8, -1);
                                    if (class98_Sub14 == null) {
                                        class98_Sub14 = new Class98_Sub14(new byte[128]);
                                        this.aClass377_3849.method3996(class98_Sub14, n8, -1);
                                    }
                                    class98_Sub14.aByteArray3914[n7] = 1;
                                }
                            }
                            class173.method2556(method2559);
                            class173.method2552(method2559);
                        }
                    }
                }
            }
        }
    }
    
    private Class98_Sub7(final Class98_Sub22 class98_Sub22) {
        class98_Sub22.anInt3991 = class98_Sub22.aByteArray3992.length - 3;
        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-111));
        final int short1 = class98_Sub22.readShort((byte)127);
        int n = 14 + unsignedByte * 10;
        class98_Sub22.anInt3991 = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        for (int i = 0; i < unsignedByte; ++i) {
            int n10 = -1;
            while (true) {
                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-122));
                if (unsignedByte2 != n10) {
                    ++n;
                }
                n10 = (unsignedByte2 & 0xF);
                if (unsignedByte2 == 7) {
                    break;
                }
                if (unsignedByte2 == 23) {
                    ++n2;
                }
                else if (n10 == 0) {
                    ++n4;
                }
                else if (n10 == 1) {
                    ++n5;
                }
                else if (n10 == 2) {
                    ++n3;
                }
                else if (n10 == 3) {
                    ++n6;
                }
                else if (n10 == 4) {
                    ++n7;
                }
                else if (n10 == 5) {
                    ++n8;
                }
                else {
                    if (n10 != 6) {
                        throw new RuntimeException();
                    }
                    ++n9;
                }
            }
        }
        final int n11 = n + 5 * n2 + 2 * (n4 + n5 + n3 + n6 + n8) + (n7 + n9);
        final int anInt3991 = class98_Sub22.anInt3991;
        for (int n12 = unsignedByte + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9, j = 0; j < n12; ++j) {
            class98_Sub22.method1240((byte)(-20));
        }
        final int n13 = n11 + (class98_Sub22.anInt3991 - anInt3991);
        int anInt3992 = class98_Sub22.anInt3991;
        int n14 = 0;
        int n15 = 0;
        int n16 = 0;
        int n17 = 0;
        int n18 = 0;
        int n19 = 0;
        int n20 = 0;
        int n21 = 0;
        int n22 = 0;
        int n23 = 0;
        int n24 = 0;
        int n25 = 0;
        int n26 = 0;
        for (int k = 0; k < n3; ++k) {
            n26 = (n26 + class98_Sub22.readUnsignedByte((byte)(-122)) & 0x7F);
            if (n26 == 0 || n26 == 32) {
                ++n9;
            }
            else if (n26 == 1) {
                ++n14;
            }
            else if (n26 == 33) {
                ++n15;
            }
            else if (n26 == 7) {
                ++n16;
            }
            else if (n26 == 39) {
                ++n17;
            }
            else if (n26 == 10) {
                ++n18;
            }
            else if (n26 == 42) {
                ++n19;
            }
            else if (n26 == 99) {
                ++n20;
            }
            else if (n26 == 98) {
                ++n21;
            }
            else if (n26 == 101) {
                ++n22;
            }
            else if (n26 == 100) {
                ++n23;
            }
            else if (n26 == 64 || n26 == 65 || n26 == 120 || n26 == 121 || n26 == 123) {
                ++n24;
            }
            else {
                ++n25;
            }
        }
        int n27 = 0;
        int anInt3993 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n24;
        int anInt3994 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n8;
        int anInt3995 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n7;
        int anInt3996 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n6;
        int anInt3997 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n14;
        int anInt3998 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n16;
        int anInt3999 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n18;
        int anInt4000 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n4 + n5 + n8;
        int anInt4001 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n4;
        int anInt4002 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n25;
        int anInt4003 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n5;
        int anInt4004 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n15;
        int anInt4005 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n17;
        int anInt4006 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n19;
        int anInt4007 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n9;
        int anInt4008 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n6;
        int anInt4009 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n20;
        int anInt4010 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n21;
        int anInt4011 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n22;
        int anInt4012 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n23;
        int anInt4013 = class98_Sub22.anInt3991;
        class98_Sub22.anInt3991 += n2 * 3;
        this.aByteArray3850 = new byte[n13];
        final Class98_Sub22 class98_Sub23 = new Class98_Sub22(this.aByteArray3850);
        class98_Sub23.writeInt(1571862888, 1297377380);
        class98_Sub23.writeInt(1571862888, 6);
        class98_Sub23.writeShort((unsignedByte > 1) ? 1 : 0, 1571862888);
        class98_Sub23.writeShort(unsignedByte, 1571862888);
        class98_Sub23.writeShort(short1, 1571862888);
        class98_Sub22.anInt3991 = anInt3991;
        int n28 = 0;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        int n29 = 0;
        byte b4 = 0;
        byte b5 = 0;
        final int[] array = new int[128];
        byte b6 = 0;
        for (int l = 0; l < unsignedByte; ++l) {
            class98_Sub23.writeInt(1571862888, 1297379947);
            final Class98_Sub22 class98_Sub24 = class98_Sub23;
            class98_Sub24.anInt3991 += 4;
            final int anInt4014 = class98_Sub23.anInt3991;
            int n30 = -1;
            while (true) {
                class98_Sub23.method1199(class98_Sub22.method1240((byte)(-20)), false);
                final int n31 = class98_Sub22.aByteArray3992[n27++] & 0xFF;
                final boolean b7 = n31 != n30;
                n30 = (n31 & 0xF);
                if (n31 == 7) {
                    if (b7) {
                        class98_Sub23.method1194(255, 96);
                    }
                    class98_Sub23.method1194(47, -102);
                    class98_Sub23.method1194(0, 69);
                    class98_Sub23.method1233((byte)(-74), class98_Sub23.anInt3991 - anInt4014);
                    break;
                }
                if (n31 == 23) {
                    if (b7) {
                        class98_Sub23.method1194(255, -109);
                    }
                    class98_Sub23.method1194(81, 114);
                    class98_Sub23.method1194(3, -100);
                    class98_Sub23.method1194(class98_Sub22.aByteArray3992[anInt4013++], -42);
                    class98_Sub23.method1194(class98_Sub22.aByteArray3992[anInt4013++], 40);
                    class98_Sub23.method1194(class98_Sub22.aByteArray3992[anInt4013++], -79);
                }
                else {
                    n28 ^= n31 >> 4;
                    if (n30 == 0) {
                        if (b7) {
                            class98_Sub23.method1194(144 + n28, 60);
                        }
                        b += class98_Sub22.aByteArray3992[anInt4000++];
                        b2 += class98_Sub22.aByteArray3992[anInt4001++];
                        class98_Sub23.method1194(b & 0x7F, 83);
                        class98_Sub23.method1194(b2 & 0x7F, -114);
                    }
                    else if (n30 == 1) {
                        if (b7) {
                            class98_Sub23.method1194(128 + n28, -39);
                        }
                        b += class98_Sub22.aByteArray3992[anInt4000++];
                        b3 += class98_Sub22.aByteArray3992[anInt4003++];
                        class98_Sub23.method1194(b & 0x7F, 102);
                        class98_Sub23.method1194(b3 & 0x7F, -46);
                    }
                    else if (n30 == 2) {
                        if (b7) {
                            class98_Sub23.method1194(176 + n28, 63);
                        }
                        b6 = (byte)(b6 + class98_Sub22.aByteArray3992[anInt3992++] & 0x7F);
                        class98_Sub23.method1194(b6, 70);
                        byte b8;
                        if (b6 == 0 || b6 == 32) {
                            b8 = class98_Sub22.aByteArray3992[anInt4007++];
                        }
                        else if (b6 == 1) {
                            b8 = class98_Sub22.aByteArray3992[anInt3997++];
                        }
                        else if (b6 == 33) {
                            b8 = class98_Sub22.aByteArray3992[anInt4004++];
                        }
                        else if (b6 == 7) {
                            b8 = class98_Sub22.aByteArray3992[anInt3998++];
                        }
                        else if (b6 == 39) {
                            b8 = class98_Sub22.aByteArray3992[anInt4005++];
                        }
                        else if (b6 == 10) {
                            b8 = class98_Sub22.aByteArray3992[anInt3999++];
                        }
                        else if (b6 == 42) {
                            b8 = class98_Sub22.aByteArray3992[anInt4006++];
                        }
                        else if (b6 == 99) {
                            b8 = class98_Sub22.aByteArray3992[anInt4009++];
                        }
                        else if (b6 == 98) {
                            b8 = class98_Sub22.aByteArray3992[anInt4010++];
                        }
                        else if (b6 == 101) {
                            b8 = class98_Sub22.aByteArray3992[anInt4011++];
                        }
                        else if (b6 == 100) {
                            b8 = class98_Sub22.aByteArray3992[anInt4012++];
                        }
                        else if (b6 == 64 || b6 == 65 || b6 == 120 || b6 == 121 || b6 == 123) {
                            b8 = class98_Sub22.aByteArray3992[anInt3993++];
                        }
                        else {
                            b8 = class98_Sub22.aByteArray3992[anInt4002++];
                        }
                        class98_Sub23.method1194((array[b6] += b8) & 0x7F, -65);
                    }
                    else if (n30 == 3) {
                        if (b7) {
                            class98_Sub23.method1194(224 + n28, -78);
                        }
                        n29 = n29 + class98_Sub22.aByteArray3992[anInt4008++] + (class98_Sub22.aByteArray3992[anInt3996++] << 7);
                        class98_Sub23.method1194(n29 & 0x7F, 47);
                        class98_Sub23.method1194(n29 >> 7 & 0x7F, -86);
                    }
                    else if (n30 == 4) {
                        if (b7) {
                            class98_Sub23.method1194(208 + n28, 119);
                        }
                        b4 += class98_Sub22.aByteArray3992[anInt3995++];
                        class98_Sub23.method1194(b4 & 0x7F, 49);
                    }
                    else if (n30 == 5) {
                        if (b7) {
                            class98_Sub23.method1194(160 + n28, -63);
                        }
                        b += class98_Sub22.aByteArray3992[anInt4000++];
                        b5 += class98_Sub22.aByteArray3992[anInt3994++];
                        class98_Sub23.method1194(b & 0x7F, 101);
                        class98_Sub23.method1194(b5 & 0x7F, -52);
                    }
                    else {
                        if (n30 != 6) {
                            throw new RuntimeException();
                        }
                        if (b7) {
                            class98_Sub23.method1194(192 + n28, -80);
                        }
                        class98_Sub23.method1194(class98_Sub22.aByteArray3992[anInt4007++], 99);
                    }
                }
            }
        }
    }
    
    static final Class98_Sub7 method985(final Class207 class207, final int n, final int n2) {
        final byte[] method2745 = class207.method2745(n2, n, false);
        if (method2745 == null) {
            return null;
        }
        return new Class98_Sub7(new Class98_Sub22(method2745));
    }
}

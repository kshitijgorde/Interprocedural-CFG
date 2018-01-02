// 
// Decompiled by Procyon v0.5.30
// 

final class Class7
{
    private static short[] aShortArray92;
    Class98_Sub1 aClass98_Sub1_93;
    short[] aShortArray94;
    boolean aBoolean95;
    private static short[] aShortArray96;
    private static byte[] aByteArray97;
    private static short[] aShortArray98;
    byte[] aByteArray99;
    int anInt100;
    private static short[] aShortArray101;
    boolean aBoolean102;
    private static short[] aShortArray103;
    boolean aBoolean104;
    short[] aShortArray105;
    short[] aShortArray106;
    short[] aShortArray107;
    short[] aShortArray108;
    
    public static void method183() {
        Class7.aShortArray103 = null;
        Class7.aShortArray101 = null;
        Class7.aShortArray96 = null;
        Class7.aShortArray92 = null;
        Class7.aShortArray98 = null;
        Class7.aByteArray97 = null;
    }
    
    Class7(final byte[] array, final Class98_Sub1 aClass98_Sub1_93) {
        this.aClass98_Sub1_93 = null;
        this.anInt100 = 0;
        this.aBoolean102 = false;
        this.aBoolean95 = false;
        this.aBoolean104 = false;
        this.aClass98_Sub1_93 = aClass98_Sub1_93;
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            final Class98_Sub22 class98_Sub23 = new Class98_Sub22(array);
            class98_Sub22.readUnsignedByte((byte)89);
            final Class98_Sub22 class98_Sub24 = class98_Sub22;
            class98_Sub24.anInt3991 += 2;
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-128));
            int anInt100 = 0;
            int n = -1;
            int n2 = -1;
            class98_Sub23.anInt3991 = class98_Sub22.anInt3991 + unsignedByte;
            for (int i = 0; i < unsignedByte; ++i) {
                final int n3 = this.aClass98_Sub1_93.anIntArray3812[i];
                if (n3 == 0) {
                    n = i;
                }
                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-7));
                if (unsignedByte2 > 0) {
                    if (n3 == 0) {
                        n2 = i;
                    }
                    Class7.aShortArray103[anInt100] = (short)i;
                    short n4 = 0;
                    if (n3 == 3 || n3 == 10) {
                        n4 = 128;
                    }
                    if ((unsignedByte2 & 0x1) != 0x0) {
                        Class7.aShortArray101[anInt100] = (short)class98_Sub23.method1239(-80);
                    }
                    else {
                        Class7.aShortArray101[anInt100] = n4;
                    }
                    if ((unsignedByte2 & 0x2) != 0x0) {
                        Class7.aShortArray96[anInt100] = (short)class98_Sub23.method1239(-76);
                    }
                    else {
                        Class7.aShortArray96[anInt100] = n4;
                    }
                    if ((unsignedByte2 & 0x4) != 0x0) {
                        Class7.aShortArray92[anInt100] = (short)class98_Sub23.method1239(-82);
                    }
                    else {
                        Class7.aShortArray92[anInt100] = n4;
                    }
                    Class7.aByteArray97[anInt100] = (byte)(unsignedByte2 >>> 3 & 0x3);
                    if (n3 == 2 || n3 == 9) {
                        Class7.aShortArray101[anInt100] = (short)(Class7.aShortArray101[anInt100] << 2 & 0x3FFF);
                        Class7.aShortArray96[anInt100] = (short)(Class7.aShortArray96[anInt100] << 2 & 0x3FFF);
                        Class7.aShortArray92[anInt100] = (short)(Class7.aShortArray92[anInt100] << 2 & 0x3FFF);
                    }
                    Class7.aShortArray98[anInt100] = -1;
                    if (n3 == 1 || n3 == 2 || n3 == 3) {
                        if (n > n2) {
                            Class7.aShortArray98[anInt100] = (short)n;
                            n2 = n;
                        }
                    }
                    else if (n3 == 5) {
                        this.aBoolean102 = true;
                    }
                    else if (n3 == 7) {
                        this.aBoolean104 = true;
                    }
                    else if (n3 == 9 || n3 == 10 || n3 == 8) {
                        this.aBoolean95 = true;
                    }
                    ++anInt100;
                }
            }
            if (class98_Sub23.anInt3991 != array.length) {
                throw new RuntimeException();
            }
            this.anInt100 = anInt100;
            this.aShortArray108 = new short[anInt100];
            this.aShortArray94 = new short[anInt100];
            this.aShortArray105 = new short[anInt100];
            this.aShortArray106 = new short[anInt100];
            this.aShortArray107 = new short[anInt100];
            this.aByteArray99 = new byte[anInt100];
            for (int j = 0; j < anInt100; ++j) {
                this.aShortArray108[j] = Class7.aShortArray103[j];
                this.aShortArray94[j] = Class7.aShortArray101[j];
                this.aShortArray105[j] = Class7.aShortArray96[j];
                this.aShortArray106[j] = Class7.aShortArray92[j];
                this.aShortArray107[j] = Class7.aShortArray98[j];
                this.aByteArray99[j] = Class7.aByteArray97[j];
            }
        }
        catch (Exception ex) {
            this.anInt100 = 0;
            this.aBoolean102 = false;
            this.aBoolean104 = false;
        }
    }
    
    static {
        Class7.aShortArray92 = new short[500];
        Class7.aShortArray101 = new short[500];
        Class7.aByteArray97 = new byte[500];
        Class7.aShortArray103 = new short[500];
        Class7.aShortArray98 = new short[500];
        Class7.aShortArray96 = new short[500];
    }
}

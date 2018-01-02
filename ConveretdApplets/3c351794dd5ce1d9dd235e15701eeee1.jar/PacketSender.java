import java.io.OutputStream;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class PacketSender implements Runnable
{
    private byte[] aByteArray2568;
    private IOException anIOException2569;
    private int anInt2570;
    private Thread aThread2571;
    private int anInt2572;
    private OutputStream anOutputStream2573;
    private int anInt2574;
    static boolean aBoolean2575;
    
    final void method3603(final boolean b) {
        try {
            this.anOutputStream2573 = new OutputStream_Sub2();
            if (!b) {
                this.aByteArray2568 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "st.E(" + b + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            while (true) {
                Label_0091: {
                    int n;
                    synchronized (this) {
                        while (this.anIOException2569 == null) {
                            if (~this.anInt2574 < ~this.anInt2572) {
                                n = -this.anInt2574 + (this.anInt2570 + this.anInt2572);
                            }
                            else {
                                n = this.anInt2572 - this.anInt2574;
                            }
                            if (~n < -1) {
                                break Label_0091;
                            }
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        return;
                    }
                    try {
                        if (n + this.anInt2574 > this.anInt2570) {
                            final int n2 = -this.anInt2574 + this.anInt2570;
                            this.anOutputStream2573.write(this.aByteArray2568, this.anInt2574, n2);
                            this.anOutputStream2573.write(this.aByteArray2568, 0, n - n2);
                        }
                        else {
                            this.anOutputStream2573.write(this.aByteArray2568, this.anInt2574, n);
                        }
                    }
                    catch (IOException anIOException2569) {
                        synchronized (this) {
                            this.anIOException2569 = anIOException2569;
                            return;
                        }
                        synchronized (this) {
                            this.anInt2574 = (this.anInt2574 - -n) % this.anInt2570;
                        }
                        continue;
                    }
                }
            }
        }
        catch (RuntimeException ex2) {}
    }
    
    static final void method3604(final int n, final byte b, final int n2, final Class98_Sub46_Sub8 class98_Sub46_Sub8) {
        try {
            if (class98_Sub46_Sub8 != null && Class33.aClass148_315.aClass98_1198 != class98_Sub46_Sub8) {
                final int anInt5995 = class98_Sub46_Sub8.anInt5995;
                final int anInt5996 = class98_Sub46_Sub8.anInt5993;
                int anInt5997 = class98_Sub46_Sub8.anInt5990;
                final int n3 = (int)class98_Sub46_Sub8.aLong5987;
                if (~anInt5997 <= -2001) {
                    anInt5997 -= 2000;
                }
                final long aLong5987 = class98_Sub46_Sub8.aLong5987;
                if (anInt5997 == 1002) {
                    Class55.anInt440 = 2;
                    Class98_Sub49.anInt4286 = n;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class366.anInt3117 = n2;
                    final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                    if (class98_Sub39 != null) {
                        Class141 class141 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504;
                        if (class141.anIntArray1109 != null) {
                            class141 = class141.method2300(Class75.aClass140_584, (byte)45);
                        }
                        if (class141 != null) {
                            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class246_Sub3_Sub4_Sub2_Sub1.aClass171_6506, Class331.aClass117_2811);
                            method3023.aClass98_Sub22_Sub1_3865.writeShort(class141.anInt1097, 1571862888);
                            Class98_Sub10_Sub29.sendPacket(false, method3023);
                        }
                    }
                }
                if (anInt5997 == 51) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub2 != null) {
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class55.anInt440 = 2;
                        Class98_Sub49.anInt4286 = n;
                        ++Class98_Sub23.anInt4001;
                        Class366.anInt3117 = n2;
                        final Class98_Sub11 method3024 = Class246_Sub3_Sub4.method3023(b ^ 0x14A, Class121.aClass171_1001, Class331.aClass117_2811);
                        method3024.aClass98_Sub22_Sub1_3865.writeLEShortA(n3, 128);
                        method3024.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)107);
                        Class98_Sub10_Sub29.sendPacket(false, method3024);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub2.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub2.method3034(0));
                    }
                }
                if (anInt5997 == 45) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub3 != null) {
                        Class366.anInt3117 = n2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        ++Class366.anInt3111;
                        Class55.anInt440 = 2;
                        Class98_Sub49.anInt4286 = n;
                        final Class98_Sub11 method3025 = Class246_Sub3_Sub4.method3023(260, Class277.aClass171_2051, Class331.aClass117_2811);
                        method3025.aClass98_Sub22_Sub1_3865.writeByteS(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, -102);
                        method3025.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3025);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub3.method3034(0), -2, b - 78, class246_Sub3_Sub4_Sub2_Sub3.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub3.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub3.method3034(0));
                    }
                }
                if (~anInt5997 == 0xFFFFFFC5) {
                    Class98_Sub49.anInt4286 = n;
                    Class366.anInt3117 = n2;
                    Class55.anInt440 = 2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    final Class98_Sub11 method3026 = Class246_Sub3_Sub4.method3023(260, Exception_Sub1.aClass171_46, Class331.aClass117_2811);
                    method3026.aClass98_Sub22_Sub1_3865.method1200((byte)127, Class187.anInt1450);
                    method3026.aClass98_Sub22_Sub1_3865.writeLEShortA(aa_Sub2.anInt3562 + anInt5996, 128);
                    method3026.aClass98_Sub22_Sub1_3865.writeLEShortA(n3, 128);
                    method3026.aClass98_Sub22_Sub1_3865.method1194(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, 66);
                    method3026.aClass98_Sub22_Sub1_3865.writeLEShortA(Class376.anInt3173, 128);
                    method3026.aClass98_Sub22_Sub1_3865.writeLEShort(Class272.anInt2038 + anInt5995, 17624);
                    method3026.aClass98_Sub22_Sub1_3865.writeLEShortA(Class310.anInt2652, 128);
                    Class98_Sub10_Sub29.sendPacket(false, method3026);
                    Class44.method427(-19181, anInt5996, anInt5995);
                }
                if (anInt5997 == 1011 || anInt5997 == 1003 || anInt5997 == 1001 || ~anInt5997 == 0xFFFFFC0D || anInt5997 == 1004) {
                    Class162.method2518(-1004, anInt5997, anInt5995, n3);
                }
                if (anInt5997 == 2) {
                    Class55.anInt440 = 2;
                    Class98_Sub49.anInt4286 = n;
                    Class366.anInt3117 = n2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    final Class98_Sub11 method3027 = Class246_Sub3_Sub4.method3023(b + 182, Class284_Sub2_Sub2.aClass171_6198, Class331.aClass117_2811);
                    method3027.aClass98_Sub22_Sub1_3865.writeLEShort(Class272.anInt2038 + anInt5995, 17624);
                    method3027.aClass98_Sub22_Sub1_3865.writeLEShort(n3, 17624);
                    method3027.aClass98_Sub22_Sub1_3865.writeShortA(anInt5996 + aa_Sub2.anInt3562, (byte)126);
                    method3027.aClass98_Sub22_Sub1_3865.method1244(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)112);
                    Class98_Sub10_Sub29.sendPacket(false, method3027);
                    Class44.method427(-19181, anInt5996, anInt5995);
                }
                if (~anInt5997 == 0xFFFFFFCD) {
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class366.anInt3117 = n2;
                    Class98_Sub49.anInt4286 = n;
                    Class55.anInt440 = 2;
                    final Class98_Sub11 method3028 = Class246_Sub3_Sub4.method3023(260, Class134.aClass171_3466, Class331.aClass117_2811);
                    method3028.aClass98_Sub22_Sub1_3865.writeLEInt(Class187.anInt1450, 1046032984);
                    method3028.aClass98_Sub22_Sub1_3865.writeLEShortA(Class310.anInt2652, 128);
                    method3028.aClass98_Sub22_Sub1_3865.writeShort(Class376.anInt3173, 1571862888);
                    method3028.aClass98_Sub22_Sub1_3865.writeShort(Class272.anInt2038 + anInt5995, b ^ 0x5DB0B926);
                    method3028.aClass98_Sub22_Sub1_3865.writeByteS(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, b - 112);
                    method3028.aClass98_Sub22_Sub1_3865.writeShortA((int)(aLong5987 >>> 1575185440) & Integer.MAX_VALUE, (byte)126);
                    method3028.aClass98_Sub22_Sub1_3865.writeLEShortA(aa_Sub2.anInt3562 + anInt5996, b + 50);
                    Class98_Sub10_Sub29.sendPacket(false, method3028);
                    Class67.method688(-23, aLong5987, anInt5995, anInt5996);
                }
                if (anInt5997 == 11) {
                    final Class98_Sub39 class98_Sub40 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                    if (class98_Sub40 != null) {
                        Class98_Sub49.anInt4286 = n;
                        final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub40.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class366.anInt3117 = n2;
                        Class55.anInt440 = 2;
                        final Class98_Sub11 method3029 = Class246_Sub3_Sub4.method3023(b + 182, Class98_Sub45.aClass171_4256, Class331.aClass117_2811);
                        method3029.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                        method3029.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)35);
                        Class98_Sub10_Sub29.sendPacket(false, method3029);
                        Class76_Sub2.requestFlag(0, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0), -2, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0], true, aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0));
                    }
                }
                if (~anInt5997 == 0xFFFFFFEB) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub4 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub4 != null) {
                        Class366.anInt3117 = n2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class55.anInt440 = 2;
                        Class98_Sub49.anInt4286 = n;
                        ++Class98_Sub46_Sub16.anInt6044;
                        final Class98_Sub11 method3030 = Class246_Sub3_Sub4.method3023(b + 182, Class64_Sub8.aClass171_3661, Class331.aClass117_2811);
                        method3030.aClass98_Sub22_Sub1_3865.writeLEShort(n3, 17624);
                        method3030.aClass98_Sub22_Sub1_3865.writeByteS(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, -37);
                        Class98_Sub10_Sub29.sendPacket(false, method3030);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub4.method3034(0), -2, b - 78, class246_Sub3_Sub4_Sub2_Sub4.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub4.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub4.method3034(b ^ 0x4E));
                    }
                }
                if (anInt5997 == 5) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub5 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub5 != null) {
                        Class55.anInt440 = 2;
                        ++client.anInt3548;
                        Class366.anInt3117 = n2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class98_Sub49.anInt4286 = n;
                        final Class98_Sub11 method3031 = Class246_Sub3_Sub4.method3023(260, Class160.aClass171_1259, Class331.aClass117_2811);
                        method3031.aClass98_Sub22_Sub1_3865.writeLEShortA(n3, 128);
                        method3031.aClass98_Sub22_Sub1_3865.writeShortA(Class310.anInt2652, (byte)126);
                        method3031.aClass98_Sub22_Sub1_3865.writeLEInt(Class187.anInt1450, 1046032984);
                        method3031.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)(-128));
                        method3031.aClass98_Sub22_Sub1_3865.writeShortA(Class376.anInt3173, (byte)126);
                        Class98_Sub10_Sub29.sendPacket(false, method3031);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub5.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub5.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub5.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub5.method3034(b ^ 0x4E));
                    }
                }
                if (anInt5997 == 19) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub6 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub6 != null) {
                        Class98_Sub49.anInt4286 = n;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class55.anInt440 = 2;
                        Class366.anInt3117 = n2;
                        final Class98_Sub11 method3032 = Class246_Sub3_Sub4.method3023(260, Class21_Sub4.aClass171_5398, Class331.aClass117_2811);
                        method3032.aClass98_Sub22_Sub1_3865.writeShortA(n3, (byte)126);
                        method3032.aClass98_Sub22_Sub1_3865.method1244(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)112);
                        Class98_Sub10_Sub29.sendPacket(false, method3032);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub6.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub6.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub6.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub6.method3034(b - 78));
                    }
                }
                if (~anInt5997 == 0xFFFFFFE1) {
                    Class98_Sub49.anInt4286 = n;
                    Class55.anInt440 = 2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class366.anInt3117 = n2;
                    final Class98_Sub11 method3033 = Class246_Sub3_Sub4.method3023(b ^ 0x14A, Class49.aClass171_413, Class331.aClass117_2811);
                    method3033.aClass98_Sub22_Sub1_3865.writeLEShort(anInt5996 + aa_Sub2.anInt3562, 17624);
                    method3033.aClass98_Sub22_Sub1_3865.writeLEShortA(n3, 128);
                    method3033.aClass98_Sub22_Sub1_3865.writeLEShort(Class272.anInt2038 + anInt5995, b ^ 0x4496);
                    method3033.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)126);
                    Class98_Sub10_Sub29.sendPacket(false, method3033);
                    Class44.method427(b - 19259, anInt5996, anInt5995);
                }
                if (anInt5997 == 47) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub7 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub7 != null) {
                        Class366.anInt3117 = n2;
                        ++Class65.anInt498;
                        Class98_Sub49.anInt4286 = n;
                        Class55.anInt440 = 2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        final Class98_Sub11 method3034 = Class246_Sub3_Sub4.method3023(260, Class302.aClass171_2520, Class331.aClass117_2811);
                        method3034.aClass98_Sub22_Sub1_3865.writeByteS(Class219.aClass77_1641.method779(82, b + 5425) ? 1 : 0, -52);
                        method3034.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3034);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub7.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub7.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub7.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub7.method3034(0));
                    }
                }
                if (~anInt5997 == 0xFFFFFFEF) {
                    Class366.anInt3117 = n2;
                    Class98_Sub49.anInt4286 = n;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class55.anInt440 = 2;
                    final Class98_Sub11 method3035 = Class246_Sub3_Sub4.method3023(b + 182, Class196.aClass171_1508, Class331.aClass117_2811);
                    method3035.aClass98_Sub22_Sub1_3865.writeLEShort(anInt5995 - -Class272.anInt2038, 17624);
                    method3035.aClass98_Sub22_Sub1_3865.writeLEShort(anInt5996 + aa_Sub2.anInt3562, b + 17546);
                    method3035.aClass98_Sub22_Sub1_3865.writeShortA((int)(aLong5987 >>> -855876128) & Integer.MAX_VALUE, (byte)126);
                    method3035.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)74);
                    Class98_Sub10_Sub29.sendPacket(false, method3035);
                    Class67.method688(-23, aLong5987, anInt5995, anInt5996);
                }
                if (~anInt5997 == 0xFFFFFFFC) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub8 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                    if (class246_Sub3_Sub4_Sub2_Sub8 != null) {
                        Class98_Sub49.anInt4286 = n;
                        Class55.anInt440 = 2;
                        Class366.anInt3117 = n2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        ++Class98_Sub43.anInt4242;
                        final Class98_Sub11 method3036 = Class246_Sub3_Sub4.method3023(260, OutputStream_Sub1.aClass171_34, Class331.aClass117_2811);
                        method3036.aClass98_Sub22_Sub1_3865.method1244(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)112);
                        method3036.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3036);
                        Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub8.method3034(0), -2, b - 78, class246_Sub3_Sub4_Sub2_Sub8.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub8.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub8.method3034(0));
                    }
                }
                if (anInt5997 == 8) {
                    Class55.anInt440 = 2;
                    Class98_Sub49.anInt4286 = n;
                    Class366.anInt3117 = n2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    final Class98_Sub11 method3037 = Class246_Sub3_Sub4.method3023(260, Class263.aClass171_1964, Class331.aClass117_2811);
                    method3037.aClass98_Sub22_Sub1_3865.writeShortA(anInt5995 + Class272.anInt2038, (byte)126);
                    method3037.aClass98_Sub22_Sub1_3865.writeLEShortA(Integer.MAX_VALUE & (int)(aLong5987 >>> -769820512), 128);
                    method3037.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)(-124));
                    method3037.aClass98_Sub22_Sub1_3865.writeLEShortA(aa_Sub2.anInt3562 + anInt5996, 128);
                    Class98_Sub10_Sub29.sendPacket(false, method3037);
                    Class67.method688(-23, aLong5987, anInt5995, anInt5996);
                }
                if (anInt5997 == 17) {
                    final Class98_Sub39 class98_Sub41 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                    if (class98_Sub41 != null) {
                        final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4188 = class98_Sub41.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        Class366.anInt3117 = n2;
                        Class55.anInt440 = 2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class98_Sub49.anInt4286 = n;
                        final Class98_Sub11 method3038 = Class246_Sub3_Sub4.method3023(260, Class299_Sub2.aClass171_5296, Class331.aClass117_2811);
                        method3038.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                        method3038.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, b ^ 0x1531) ? 1 : 0, (byte)37);
                        Class98_Sub10_Sub29.sendPacket(false, method3038);
                        Class76_Sub2.requestFlag(0, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4188.method3034(0), -2, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4188.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4188.anIntArray6437[0], true, aClass246_Sub3_Sub4_Sub2_Sub1_4188.method3034(0));
                    }
                }
                if (~anInt5997 == 0xFFFFFFF6 && OutputStream_Sub1.aClass293_33 == null) {
                    Class149.method2435(anInt5995, b ^ 0x1F, anInt5996);
                    Class341.method3812(1, OutputStream_Sub1.aClass293_33 = Class246_Sub9.method3139((byte)72, anInt5996, anInt5995));
                }
                if (anInt5997 == 59) {
                    final Class293 method3039 = Class246_Sub9.method3139((byte)72, anInt5996, anInt5995);
                    if (method3039 != null) {
                        Class172.method2542(false, method3039);
                    }
                }
                if (anInt5997 == 13) {
                    Class366.anInt3117 = n2;
                    Class98_Sub49.anInt4286 = n;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class55.anInt440 = 2;
                    final Class98_Sub11 method3040 = Class246_Sub3_Sub4.method3023(260, Class222.aClass171_1669, Class331.aClass117_2811);
                    method3040.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, b ^ 0x1531) ? 1 : 0, (byte)32);
                    method3040.aClass98_Sub22_Sub1_3865.writeShortA(anInt5995 - -Class272.anInt2038, (byte)126);
                    method3040.aClass98_Sub22_Sub1_3865.writeLEShort(n3, 17624);
                    method3040.aClass98_Sub22_Sub1_3865.writeLEShortA(anInt5996 - -aa_Sub2.anInt3562, 128);
                    Class98_Sub10_Sub29.sendPacket(false, method3040);
                    Class44.method427(-19181, anInt5996, anInt5995);
                }
                if (~anInt5997 == 0xFFFFFFCE || anInt5997 == 1006) {
                    Class303.method3557(n3, anInt5995, class98_Sub46_Sub8.aString5992, -124, anInt5996);
                }
                if (anInt5997 == 48) {
                    final Class98_Sub39 class98_Sub42 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, b - 79);
                    if (class98_Sub42 != null) {
                        Class366.anInt3117 = n2;
                        Class55.anInt440 = 2;
                        final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4189 = class98_Sub42.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        Class98_Sub49.anInt4286 = n;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        final Class98_Sub11 method3041 = Class246_Sub3_Sub4.method3023(260, Class82.aClass171_625, Class331.aClass117_2811);
                        method3041.aClass98_Sub22_Sub1_3865.method1200((byte)125, Class187.anInt1450);
                        method3041.aClass98_Sub22_Sub1_3865.writeLEShort(Class310.anInt2652, b + 17546);
                        method3041.aClass98_Sub22_Sub1_3865.writeShort(n3, b ^ 0x5DB0B926);
                        method3041.aClass98_Sub22_Sub1_3865.writeByteS(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, -53);
                        method3041.aClass98_Sub22_Sub1_3865.writeLEShort(Class376.anInt3173, b + 17546);
                        Class98_Sub10_Sub29.sendPacket(false, method3041);
                        Class76_Sub2.requestFlag(0, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4189.method3034(0), -2, b ^ 0x4E, aClass246_Sub3_Sub4_Sub2_Sub1_4189.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4189.anIntArray6437[0], true, aClass246_Sub3_Sub4_Sub2_Sub1_4189.method3034(0));
                    }
                }
                if (~anInt5997 == 0xFFFFFC0E) {
                    Class98_Sub49.anInt4286 = n;
                    Class366.anInt3117 = n2;
                    Class55.anInt440 = 2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    final Class98_Sub11 method3042 = Class246_Sub3_Sub4.method3023(260, aa_Sub1.aClass171_3559, Class331.aClass117_2811);
                    method3042.aClass98_Sub22_Sub1_3865.writeShort(n3, b + 1571862810);
                    Class98_Sub10_Sub29.sendPacket(false, method3042);
                }
                if (b != 78) {
                    method3604(-42, (byte)33, 80, null);
                }
                if (~anInt5997 == 0xFFFFFFC6) {
                    ++client.anInt3548;
                    Class55.anInt440 = 2;
                    Class366.anInt3117 = n2;
                    Class98_Sub49.anInt4286 = n;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    final Class98_Sub11 method3043 = Class246_Sub3_Sub4.method3023(b ^ 0x14A, Class160.aClass171_1259, Class331.aClass117_2811);
                    method3043.aClass98_Sub22_Sub1_3865.writeLEShortA(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6369, b ^ 0xCE);
                    method3043.aClass98_Sub22_Sub1_3865.writeShortA(Class310.anInt2652, (byte)126);
                    method3043.aClass98_Sub22_Sub1_3865.writeLEInt(Class187.anInt1450, 1046032984);
                    method3043.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, b ^ 0x1531) ? 1 : 0, (byte)(-72));
                    method3043.aClass98_Sub22_Sub1_3865.writeShortA(Class376.anInt3173, (byte)126);
                    Class98_Sub10_Sub29.sendPacket(false, method3043);
                }
                if (anInt5997 == 15) {
                    Class366.anInt3117 = n2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class55.anInt440 = 2;
                    Class98_Sub49.anInt4286 = n;
                    final Class98_Sub11 method3044 = Class246_Sub3_Sub4.method3023(260, Class250.aClass171_1913, Class331.aClass117_2811);
                    method3044.aClass98_Sub22_Sub1_3865.writeLEShort(Integer.MAX_VALUE & (int)(aLong5987 >>> 933731040), 17624);
                    method3044.aClass98_Sub22_Sub1_3865.method1194(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, -108);
                    method3044.aClass98_Sub22_Sub1_3865.writeLEShortA(anInt5995 + Class272.anInt2038, b ^ 0xCE);
                    method3044.aClass98_Sub22_Sub1_3865.writeShortA(anInt5996 + aa_Sub2.anInt3562, (byte)126);
                    Class98_Sub10_Sub29.sendPacket(false, method3044);
                    Class67.method688(b - 101, aLong5987, anInt5995, anInt5996);
                }
                if (~anInt5997 == 0xFFFFFFF9) {
                    if (~Class282.anInt2125 >= -1 || !Class219.aClass77_1641.method779(82, b ^ 0x1531) || !Class219.aClass77_1641.method779(81, b + 5425)) {
                        final Class98_Sub11 method3045 = Class50.method486(0, 0, 1, -4, b ^ 0x4E, anInt5996, anInt5995, true, 1);
                        if (~n3 != 0xFFFFFFFE) {
                            Class55.anInt440 = 1;
                            Class98_Sub10_Sub32.anInt5720 = 0;
                            Class98_Sub49.anInt4286 = n;
                            Class366.anInt3117 = n2;
                        }
                        Class76_Sub2.requestFlag(0, 0, 1, -4, b ^ 0x4E, anInt5996, anInt5995, true, 1);
                        Class98_Sub10_Sub29.sendPacket(false, method3045);
                    }
                    else {
                        Class351.method3846(aa_Sub2.anInt3562 - -anInt5996, Class272.anInt2038 - -anInt5995, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, 32);
                    }
                }
                if (~anInt5997 == 0xFFFFFFFB) {
                    Class55.anInt440 = 2;
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class366.anInt3117 = n2;
                    Class98_Sub49.anInt4286 = n;
                    final Class98_Sub11 method3046 = Class246_Sub3_Sub4.method3023(260, Class283.aClass171_2146, Class331.aClass117_2811);
                    method3046.aClass98_Sub22_Sub1_3865.writeShort(aa_Sub2.anInt3562 + anInt5996, b ^ 0x5DB0B926);
                    method3046.aClass98_Sub22_Sub1_3865.writeLEShort((int)(aLong5987 >>> 2020731040) & Integer.MAX_VALUE, 17624);
                    method3046.aClass98_Sub22_Sub1_3865.writeShort(Class272.anInt2038 + anInt5995, 1571862888);
                    method3046.aClass98_Sub22_Sub1_3865.writeByteS(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, b ^ 0xFFFFFF91);
                    Class98_Sub10_Sub29.sendPacket(false, method3046);
                    Class67.method688(-23, aLong5987, anInt5995, anInt5996);
                }
                if (~anInt5997 == 0xFFFFFFC3) {
                    if (Class282.anInt2125 > 0 && Class219.aClass77_1641.method779(82, 5503) && Class219.aClass77_1641.method779(81, b + 5425)) {
                        Class351.method3846(anInt5996 + aa_Sub2.anInt3562, anInt5995 + Class272.anInt2038, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, -67);
                    }
                    else {
                        Class98_Sub49.anInt4286 = n;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class55.anInt440 = 1;
                        Class366.anInt3117 = n2;
                        final Class98_Sub11 method3047 = Class246_Sub3_Sub4.method3023(260, Class335.aClass171_2812, Class331.aClass117_2811);
                        method3047.aClass98_Sub22_Sub1_3865.writeShortA(anInt5996 + aa_Sub2.anInt3562, (byte)126);
                        method3047.aClass98_Sub22_Sub1_3865.writeShortA(Class272.anInt2038 + anInt5995, (byte)126);
                        Class98_Sub10_Sub29.sendPacket(false, method3047);
                    }
                }
                if (~anInt5997 == 0xFFFFFC10) {
                    Class98_Sub10_Sub32.anInt5720 = 0;
                    Class98_Sub49.anInt4286 = n;
                    Class55.anInt440 = 2;
                    Class366.anInt3117 = n2;
                    final Class98_Sub11 method3048 = Class246_Sub3_Sub4.method3023(260, Class255.aClass171_3206, Class331.aClass117_2811);
                    method3048.aClass98_Sub22_Sub1_3865.writeShortA(anInt5995 + Class272.anInt2038, (byte)126);
                    method3048.aClass98_Sub22_Sub1_3865.writeLEShortA(Integer.MAX_VALUE & (int)(aLong5987 >>> -1233322400), b ^ 0xCE);
                    method3048.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)(-120));
                    method3048.aClass98_Sub22_Sub1_3865.writeLEShortA(aa_Sub2.anInt3562 + anInt5996, 128);
                    Class98_Sub10_Sub29.sendPacket(false, method3048);
                    Class67.method688(b - 101, aLong5987, anInt5995, anInt5996);
                }
                if (~anInt5997 == 0xFFFFFFEA) {
                    final Class293 method3049 = Class246_Sub9.method3139((byte)72, anInt5996, anInt5995);
                    if (method3049 != null) {
                        Class98_Sub10_Sub32.method1098((byte)96);
                        final Class98_Sub49 method3050 = client.method116(method3049);
                        Class98_Sub5_Sub2.method970(method3050.anInt4285, method3049, method3050.method1668(-1), -6838);
                        Class287_Sub2.aString3272 = Class170.method2538(b - 79, method3049);
                        if (Class287_Sub2.aString3272 == null) {
                            Class287_Sub2.aString3272 = "Null";
                        }
                        Class246_Sub3_Sub3.aString6156 = method3049.aString2224 + "<col=ffffff>";
                    }
                }
                else {
                    if (anInt5997 == 10) {
                        final Class98_Sub39 class98_Sub43 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                        if (class98_Sub43 != null) {
                            Class98_Sub10_Sub32.anInt5720 = 0;
                            Class366.anInt3117 = n2;
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4190 = class98_Sub43.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            Class55.anInt440 = 2;
                            Class98_Sub49.anInt4286 = n;
                            final Class98_Sub11 method3051 = Class246_Sub3_Sub4.method3023(260, Class334.aClass171_3470, Class331.aClass117_2811);
                            method3051.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)(-109));
                            method3051.aClass98_Sub22_Sub1_3865.writeLEShort(n3, b + 17546);
                            Class98_Sub10_Sub29.sendPacket(false, method3051);
                            Class76_Sub2.requestFlag(0, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4190.method3034(0), -2, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4190.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4190.anIntArray6437[0], true, aClass246_Sub3_Sub4_Sub2_Sub1_4190.method3034(0));
                        }
                    }
                    if (anInt5997 == 46) {
                        Class55.anInt440 = 1;
                        Class366.anInt3117 = n2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class98_Sub49.anInt4286 = n;
                        final Class98_Sub11 method3052 = Class246_Sub3_Sub4.method3023(260, Class267.aClass171_2000, Class331.aClass117_2811);
                        method3052.aClass98_Sub22_Sub1_3865.method1232(Class187.anInt1450, (byte)94);
                        method3052.aClass98_Sub22_Sub1_3865.writeLEShortA(Class310.anInt2652, 128);
                        method3052.aClass98_Sub22_Sub1_3865.writeShort(Class272.anInt2038 + anInt5995, b + 1571862810);
                        method3052.aClass98_Sub22_Sub1_3865.writeShort(Class376.anInt3173, 1571862888);
                        method3052.aClass98_Sub22_Sub1_3865.writeShort(aa_Sub2.anInt3562 - -anInt5996, 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3052);
                        Class76_Sub2.requestFlag(0, 0, 1, -4, 0, anInt5996, anInt5995, true, 1);
                    }
                    if (~anInt5997 == 0xFFFFFFED) {
                        Class98_Sub49.anInt4286 = n;
                        Class55.anInt440 = 2;
                        Class366.anInt3117 = n2;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        final Class98_Sub11 method3053 = Class246_Sub3_Sub4.method3023(b + 182, Class98_Sub22_Sub2.aClass171_5792, Class331.aClass117_2811);
                        method3053.aClass98_Sub22_Sub1_3865.writeLEShortA(Class272.anInt2038 + anInt5995, 128);
                        method3053.aClass98_Sub22_Sub1_3865.writeLEShortA(n3, 128);
                        method3053.aClass98_Sub22_Sub1_3865.writeLEShortA(aa_Sub2.anInt3562 + anInt5996, 128);
                        method3053.aClass98_Sub22_Sub1_3865.method1194(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, 110);
                        Class98_Sub10_Sub29.sendPacket(false, method3053);
                        Class44.method427(-19181, anInt5996, anInt5995);
                    }
                    if (anInt5997 == 1008) {
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class366.anInt3117 = n2;
                        Class55.anInt440 = 2;
                        Class98_Sub49.anInt4286 = n;
                        final Class98_Sub11 method3054 = Class246_Sub3_Sub4.method3023(b + 182, Class98_Sub50.aClass171_4290, Class331.aClass117_2811);
                        method3054.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                        Class98_Sub10_Sub29.sendPacket(false, method3054);
                    }
                    if (anInt5997 == 22) {
                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub9 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                        if (class246_Sub3_Sub4_Sub2_Sub9 != null) {
                            Class98_Sub10_Sub32.anInt5720 = 0;
                            Class55.anInt440 = 2;
                            Class366.anInt3117 = n2;
                            Class98_Sub49.anInt4286 = n;
                            final Class98_Sub11 method3055 = Class246_Sub3_Sub4.method3023(260, Class98_Sub37.aClass171_4168, Class331.aClass117_2811);
                            method3055.aClass98_Sub22_Sub1_3865.writeLEShortA(n3, 128);
                            method3055.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, b ^ 0x1531) ? 1 : 0, (byte)122);
                            Class98_Sub10_Sub29.sendPacket(false, method3055);
                            Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub9.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub9.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub9.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub9.method3034(0));
                        }
                    }
                    if (anInt5997 == 44) {
                        final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub10 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n3];
                        if (class246_Sub3_Sub4_Sub2_Sub10 != null) {
                            Class98_Sub10_Sub32.anInt5720 = 0;
                            Class366.anInt3117 = n2;
                            Class98_Sub49.anInt4286 = n;
                            Class55.anInt440 = 2;
                            final Class98_Sub11 method3056 = Class246_Sub3_Sub4.method3023(260, Class198.aClass171_1521, Class331.aClass117_2811);
                            method3056.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                            method3056.aClass98_Sub22_Sub1_3865.method1194(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, -91);
                            Class98_Sub10_Sub29.sendPacket(false, method3056);
                            Class76_Sub2.requestFlag(0, 0, class246_Sub3_Sub4_Sub2_Sub10.method3034(0), -2, 0, class246_Sub3_Sub4_Sub2_Sub10.anIntArray6438[0], class246_Sub3_Sub4_Sub2_Sub10.anIntArray6437[0], true, class246_Sub3_Sub4_Sub2_Sub10.method3034(0));
                        }
                    }
                    if (~anInt5997 == 0xFFFFFFE8) {
                        Class98_Sub49.anInt4286 = n;
                        Class98_Sub10_Sub32.anInt5720 = 0;
                        Class366.anInt3117 = n2;
                        Class55.anInt440 = 2;
                        final Class98_Sub11 method3057 = Class246_Sub3_Sub4.method3023(260, Class284_Sub2.aClass171_5180, Class331.aClass117_2811);
                        method3057.aClass98_Sub22_Sub1_3865.writeLEShortA(anInt5996 - -aa_Sub2.anInt3562, b ^ 0xCE);
                        method3057.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)101);
                        method3057.aClass98_Sub22_Sub1_3865.writeShortA(n3, (byte)126);
                        method3057.aClass98_Sub22_Sub1_3865.writeLEShortA(anInt5995 - -Class272.anInt2038, 128);
                        Class98_Sub10_Sub29.sendPacket(false, method3057);
                        Class44.method427(-19181, anInt5996, anInt5995);
                    }
                    if (anInt5997 == 12) {
                        final Class98_Sub39 class98_Sub44 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, -1);
                        if (class98_Sub44 != null) {
                            Class98_Sub10_Sub32.anInt5720 = 0;
                            Class55.anInt440 = 2;
                            Class98_Sub49.anInt4286 = n;
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4191 = class98_Sub44.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            Class366.anInt3117 = n2;
                            final Class98_Sub11 method3058 = Class246_Sub3_Sub4.method3023(b + 182, Class41.aClass171_371, Class331.aClass117_2811);
                            method3058.aClass98_Sub22_Sub1_3865.writeShort(n3, b ^ 0x5DB0B926);
                            method3058.aClass98_Sub22_Sub1_3865.method1231(Class219.aClass77_1641.method779(82, b + 5425) ? 1 : 0, (byte)(-99));
                            Class98_Sub10_Sub29.sendPacket(false, method3058);
                            Class76_Sub2.requestFlag(0, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4191.method3034(0), -2, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4191.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4191.anIntArray6437[0], true, aClass246_Sub3_Sub4_Sub2_Sub1_4191.method3034(b ^ 0x4E));
                        }
                    }
                    if (~anInt5997 == 0xFFFFFFE6) {
                        final Class98_Sub39 class98_Sub45 = (Class98_Sub39)Class260.aClass377_3254.method3990(n3, b ^ 0xFFFFFFB1);
                        if (class98_Sub45 != null) {
                            Class366.anInt3117 = n2;
                            Class55.anInt440 = 2;
                            Class98_Sub49.anInt4286 = n;
                            Class98_Sub10_Sub32.anInt5720 = 0;
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4192 = class98_Sub45.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            final Class98_Sub11 method3059 = Class246_Sub3_Sub4.method3023(260, Class104.aClass171_901, Class331.aClass117_2811);
                            method3059.aClass98_Sub22_Sub1_3865.writeShort(n3, 1571862888);
                            method3059.aClass98_Sub22_Sub1_3865.method1244(Class219.aClass77_1641.method779(82, 5503) ? 1 : 0, (byte)112);
                            Class98_Sub10_Sub29.sendPacket(false, method3059);
                            Class76_Sub2.requestFlag(0, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4192.method3034(b - 78), -2, 0, aClass246_Sub3_Sub4_Sub2_Sub1_4192.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4192.anIntArray6437[0], true, aClass246_Sub3_Sub4_Sub2_Sub1_4192.method3034(0));
                        }
                    }
                    if (Class98_Sub10_Sub9.aBoolean5585) {
                        Class98_Sub10_Sub32.method1098((byte)(-30));
                    }
                    if (Class77.aClass293_593 != null && ~Class42_Sub3.anInt5365 == -1) {
                        Class341.method3812(b - 77, Class77.aClass293_593);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "st.A(" + n + ',' + b + ',' + n2 + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3605(final int n, final int n2, final byte[] array, final int n3) throws IOException {
        try {
            if (n2 < 0 || n < 0 || n2 + n > array.length) {
                throw new IOException();
            }
            synchronized (this) {
                if (this.anIOException2569 != null) {
                    throw new IOException(this.anIOException2569.toString());
                }
                int n4;
                if (this.anInt2574 <= this.anInt2572) {
                    n4 = this.anInt2570 - this.anInt2572 - (-this.anInt2574 + 1);
                }
                else {
                    n4 = -1 + this.anInt2574 + -this.anInt2572;
                }
                if (n4 < n2) {
                    throw new IOException("");
                }
                if (n3 != -5) {
                    this.method3603(true);
                }
                if (n2 + this.anInt2572 > this.anInt2570) {
                    final int n5 = this.anInt2570 - this.anInt2572;
                    Class236.method2894(array, n, this.aByteArray2568, this.anInt2572, n5);
                    Class236.method2894(array, n5 + n, this.aByteArray2568, 0, -n5 + n2);
                }
                else {
                    Class236.method2894(array, n, this.aByteArray2568, this.anInt2572, n2);
                }
                this.anInt2572 = (n2 + this.anInt2572) % this.anInt2570;
                this.notifyAll();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "st.B(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    final void method3606(final byte b) {
        try {
            synchronized (this) {
                if (this.anIOException2569 == null) {
                    this.anIOException2569 = new IOException("");
                }
                if (b > -113) {
                    return;
                }
                this.notifyAll();
            }
            try {
                this.aThread2571.join();
            }
            catch (InterruptedException ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "st.D(" + b + ')');
        }
    }
    
    static final int method3607(final int n, final String s) {
        try {
            return PlayerUpdate.method2859(10, true, s, -21972);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "st.C(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    PacketSender(final OutputStream anOutputStream2573, final int n) {
        this.anInt2574 = 0;
        this.anInt2572 = 0;
        try {
            this.anOutputStream2573 = anOutputStream2573;
            this.anInt2570 = 1 + n;
            this.aByteArray2568 = new byte[this.anInt2570];
            (this.aThread2571 = new Thread(this)).setDaemon(true);
            this.aThread2571.start();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "st.<init>(" + ((anOutputStream2573 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        PacketSender.aBoolean2575 = false;
    }
}

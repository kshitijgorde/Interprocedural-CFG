// 
// Decompiled by Procyon v0.5.30
// 

final class Class173
{
    private static byte[] aByteArray1336;
    private Class98_Sub22 aClass98_Sub22_1337;
    private int[] anIntArray1338;
    private long aLong1339;
    int anInt1340;
    private int anInt1341;
    private int[] anIntArray1342;
    int[] anIntArray1343;
    private int[] anIntArray1344;
    
    final void method2545() {
        this.aClass98_Sub22_1337.aByteArray3992 = null;
        this.anIntArray1338 = null;
        this.anIntArray1344 = null;
        this.anIntArray1343 = null;
        this.anIntArray1342 = null;
    }
    
    final boolean method2546() {
        return this.aClass98_Sub22_1337.aByteArray3992 != null;
    }
    
    final long method2547(final int n) {
        return this.aLong1339 + n * this.anInt1341;
    }
    
    final void method2548(final long aLong1339) {
        this.aLong1339 = aLong1339;
        for (int length = this.anIntArray1344.length, i = 0; i < length; ++i) {
            this.anIntArray1343[i] = 0;
            this.anIntArray1342[i] = 0;
            this.aClass98_Sub22_1337.anInt3991 = this.anIntArray1338[i];
            this.method2556(i);
            this.anIntArray1344[i] = this.aClass98_Sub22_1337.anInt3991;
        }
    }
    
    private final int method2549(final int n) {
        final byte b = this.aClass98_Sub22_1337.aByteArray3992[this.aClass98_Sub22_1337.anInt3991];
        int n2;
        if (b < 0) {
            n2 = (b & 0xFF);
            this.anIntArray1342[n] = n2;
            final Class98_Sub22 aClass98_Sub22_1337 = this.aClass98_Sub22_1337;
            ++aClass98_Sub22_1337.anInt3991;
        }
        else {
            n2 = this.anIntArray1342[n];
        }
        if (n2 == 240 || n2 == 247) {
            final int method1240 = this.aClass98_Sub22_1337.method1240((byte)(-20));
            if (n2 == 247 && method1240 > 0) {
                final int n3 = this.aClass98_Sub22_1337.aByteArray3992[this.aClass98_Sub22_1337.anInt3991] & 0xFF;
                if ((n3 >= 241 && n3 <= 243) || n3 == 246 || n3 == 248 || (n3 >= 250 && n3 <= 252) || n3 == 254) {
                    final Class98_Sub22 aClass98_Sub22_1338 = this.aClass98_Sub22_1337;
                    ++aClass98_Sub22_1338.anInt3991;
                    this.anIntArray1342[n] = n3;
                    return this.method2560(n, n3);
                }
            }
            final Class98_Sub22 aClass98_Sub22_1339 = this.aClass98_Sub22_1337;
            aClass98_Sub22_1339.anInt3991 += method1240;
            return 0;
        }
        return this.method2560(n, n2);
    }
    
    final boolean method2550() {
        for (int length = this.anIntArray1344.length, i = 0; i < length; ++i) {
            if (this.anIntArray1344[i] >= 0) {
                return false;
            }
        }
        return true;
    }
    
    final int method2551() {
        final int length = this.anIntArray1344.length;
        int n = -1;
        int n2 = Integer.MAX_VALUE;
        for (int i = 0; i < length; ++i) {
            if (this.anIntArray1344[i] >= 0 && this.anIntArray1343[i] < n2) {
                n = i;
                n2 = this.anIntArray1343[i];
            }
        }
        return n;
    }
    
    final void method2552(final int n) {
        this.anIntArray1344[n] = this.aClass98_Sub22_1337.anInt3991;
    }
    
    final void method2553() {
        this.aClass98_Sub22_1337.anInt3991 = -1;
    }
    
    public static void method2554() {
        Class173.aByteArray1336 = null;
    }
    
    final void method2555(final int n) {
        this.aClass98_Sub22_1337.anInt3991 = this.anIntArray1344[n];
    }
    
    final void method2556(final int n) {
        final int method1240 = this.aClass98_Sub22_1337.method1240((byte)(-20));
        final int[] anIntArray1343 = this.anIntArray1343;
        anIntArray1343[n] += method1240;
    }
    
    final void method2557(final byte[] aByteArray3992) {
        this.aClass98_Sub22_1337.aByteArray3992 = aByteArray3992;
        this.aClass98_Sub22_1337.anInt3991 = 10;
        final int short1 = this.aClass98_Sub22_1337.readShort((byte)127);
        this.anInt1340 = this.aClass98_Sub22_1337.readShort((byte)127);
        this.anInt1341 = 500000;
        this.anIntArray1338 = new int[short1];
        int i = 0;
        while (i < short1) {
            final int int1 = this.aClass98_Sub22_1337.readInt(-2);
            final int int2 = this.aClass98_Sub22_1337.readInt(-2);
            if (int1 == 1297379947) {
                this.anIntArray1338[i] = this.aClass98_Sub22_1337.anInt3991;
                ++i;
            }
            final Class98_Sub22 aClass98_Sub22_1337 = this.aClass98_Sub22_1337;
            aClass98_Sub22_1337.anInt3991 += int2;
        }
        this.aLong1339 = 0L;
        this.anIntArray1344 = new int[short1];
        for (int j = 0; j < short1; ++j) {
            this.anIntArray1344[j] = this.anIntArray1338[j];
        }
        this.anIntArray1343 = new int[short1];
        this.anIntArray1342 = new int[short1];
    }
    
    final int method2558() {
        return this.anIntArray1344.length;
    }
    
    final int method2559(final int n) {
        return this.method2549(n);
    }
    
    private final int method2560(final int n, final int n2) {
        if (n2 != 255) {
            final byte b = Class173.aByteArray1336[n2 - 128];
            int n3 = n2;
            if (b >= 1) {
                n3 |= this.aClass98_Sub22_1337.readUnsignedByte((byte)72) << 8;
            }
            if (b >= 2) {
                n3 |= this.aClass98_Sub22_1337.readUnsignedByte((byte)17) << 16;
            }
            return n3;
        }
        final int unsignedByte = this.aClass98_Sub22_1337.readUnsignedByte((byte)(-108));
        int method1240 = this.aClass98_Sub22_1337.method1240((byte)(-20));
        if (unsignedByte == 47) {
            final Class98_Sub22 aClass98_Sub22_1337 = this.aClass98_Sub22_1337;
            aClass98_Sub22_1337.anInt3991 += method1240;
            return 1;
        }
        if (unsignedByte == 81) {
            final int method1241 = this.aClass98_Sub22_1337.method1186(-124);
            method1240 -= 3;
            this.aLong1339 += this.anIntArray1343[n] * (this.anInt1341 - method1241);
            this.anInt1341 = method1241;
            final Class98_Sub22 aClass98_Sub22_1338 = this.aClass98_Sub22_1337;
            aClass98_Sub22_1338.anInt3991 += method1240;
            return 2;
        }
        final Class98_Sub22 aClass98_Sub22_1339 = this.aClass98_Sub22_1337;
        aClass98_Sub22_1339.anInt3991 += method1240;
        return 3;
    }
    
    public Class173() {
        this.aClass98_Sub22_1337 = new Class98_Sub22(null);
    }
    
    Class173(final byte[] array) {
        this.aClass98_Sub22_1337 = new Class98_Sub22(null);
        this.method2557(array);
    }
    
    static {
        Class173.aByteArray1336 = new byte[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}

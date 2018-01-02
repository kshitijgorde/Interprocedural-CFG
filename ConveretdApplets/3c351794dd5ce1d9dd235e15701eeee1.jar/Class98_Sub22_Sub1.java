// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub22_Sub1 extends Class98_Sub22
{
    static int anInt5789;
    private int anInt5790;
    private Class117 aClass117_5791;
    
    final void method1251(final int n, final int n2, final byte[] array, final boolean b) {
        try {
            for (int n3 = 0; ~n3 > ~n2; ++n3) {
                array[n3 - -n] = (byte)(super.aByteArray3992[super.anInt3991++] + -this.aClass117_5791.method2167(123));
            }
            if (!b) {
                this.method1261(true, -96);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.H(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method1252(final byte b, final Class117 aClass117_5791) {
        try {
            if (b >= -82) {
                this.method1255(-35);
            }
            this.aClass117_5791 = aClass117_5791;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.I(" + b + ',' + ((aClass117_5791 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method1253(final boolean b, final int n) {
        try {
            if (n != -31553) {
                Class98_Sub22_Sub1.anInt5789 = -49;
            }
            final int anInt4261 = Class98_Sub46.anInt4261;
            if (~anInt4261 != -1) {
                if (~anInt4261 == 0xFFFFFFFE) {
                    return Class98_Sub46_Sub13_Sub2.anInt6309;
                }
                if (~anInt4261 != 0xFFFFFFFD) {
                    return 0;
                }
                if (!client.aBoolean3553) {
                    return 0;
                }
            }
            if (!b) {
                return Class98_Sub46_Sub13_Sub2.anInt6309;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.C(" + b + ',' + n + ')');
        }
    }
    
    final void method1254(final byte b) {
        try {
            if (b != 120) {
                this.anInt5790 = -99;
            }
            super.anInt3991 = (this.anInt5790 + 7) / 8;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.G(" + b + ')');
        }
    }
    
    final int method1255(final int n) {
        try {
            if (n != 0) {
                this.aClass117_5791 = null;
            }
            final int n2 = super.aByteArray3992[super.anInt3991++] - this.aClass117_5791.method2167(121) & 0xFF;
            if (~n2 > -129) {
                return n2;
            }
            return (n2 - 128 << 1485705704) - -(0xFF & super.aByteArray3992[super.anInt3991++] - this.aClass117_5791.method2167(n ^ 0x6A));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.E(" + n + ')');
        }
    }
    
    final void method1256(final int n) {
        try {
            this.anInt5790 = 8 * super.anInt3991;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.K(" + n + ')');
        }
    }
    
    final int readBits(final byte b, int n) {
        try {
            int n2 = this.anInt5790 >> 212764035;
            int n3 = 8 + -(this.anInt5790 & 0x7);
            this.anInt5790 += n;
            int n4 = 0;
            while (~n3 > ~n) {
                n4 += (super.aByteArray3992[n2++] & Class79.anIntArray604[n3]) << n - n3;
                n -= n3;
                n3 = 8;
            }
            int n5;
            if (n == n3) {
                n5 = n4 + (Class79.anIntArray604[n3] & super.aByteArray3992[n2]);
            }
            else {
                n5 = n4 + (super.aByteArray3992[n2] >> n3 - n & Class79.anIntArray604[n]);
            }
            return n5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.B(" + b + ',' + n + ')');
        }
    }
    
    final int method1258(final int n, final int n2) {
        try {
            return 8 * n - this.anInt5790;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.J(" + n + ',' + n2 + ')');
        }
    }
    
    Class98_Sub22_Sub1(final int n) {
        super(n);
    }
    
    final void method1259(final int[] array, final int n) {
        try {
            if (n == 255) {
                this.aClass117_5791 = new Class117(array);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.F(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final boolean method1260(final byte b) {
        try {
            if (b != 54) {
                this.aClass117_5791 = null;
            }
            return (0xFF & super.aByteArray3992[super.anInt3991] + -this.aClass117_5791.method2168(b - 51)) >= 128;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.D(" + b + ')');
        }
    }
    
    final void method1261(final boolean b, final int n) {
        try {
            super.aByteArray3992[super.anInt3991++] = (byte)(n + this.aClass117_5791.method2167(84));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bi.A(" + b + ',' + n + ')');
        }
    }
}

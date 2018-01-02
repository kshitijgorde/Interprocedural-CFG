// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub49 extends Class98
{
    static Class100 aClass100_4283;
    int anInt4284;
    int anInt4285;
    static int anInt4286;
    
    static final int method1662(int n, final int n2, int i) {
        try {
            if (n2 != -1) {
                Class98_Sub49.anInt4286 = -117;
            }
            int n3 = 1;
            while (i > 1) {
                if ((i & 0x1) != 0x0) {
                    n3 *= n;
                }
                i >>= 1;
                n *= n;
            }
            if (i == 1) {
                return n * n3;
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.B(" + n + ',' + n2 + ',' + i + ')');
        }
    }
    
    final int method1663(final int n) {
        try {
            if (n != 1) {
                return 24;
            }
            return (0x1DF9B4 & this.anInt4284) >> 682065522;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.F(" + n + ')');
        }
    }
    
    final boolean method1664(final int n) {
        try {
            if (n != -1) {
                this.method1664(-109);
            }
            return ~((this.anInt4284 & 0x325CE0) >> 548331733) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.H(" + n + ')');
        }
    }
    
    public static void method1665(final byte b) {
        try {
            if (b != 116) {
                method1665((byte)30);
            }
            Class98_Sub49.aClass100_4283 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.C(" + b + ')');
        }
    }
    
    final boolean method1666(final byte b, final int n) {
        try {
            return b == -72 && (0x1 & this.anInt4284 >> 1 + n) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.G(" + b + ',' + n + ')');
        }
    }
    
    final boolean method1667(final byte b) {
        try {
            return ~(0x1 & this.anInt4284 >> -1566073674) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.A(" + b + ')');
        }
    }
    
    final int method1668(final int n) {
        try {
            if (n != -1) {
                this.method1669(-124);
            }
            return aa_Sub3.method157(this.anInt4284, (byte)64);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.D(" + n + ')');
        }
    }
    
    final boolean method1669(final int n) {
        try {
            return n == 1964468 && ~(0x1 & this.anInt4284) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.E(" + n + ')');
        }
    }
    
    Class98_Sub49(final int anInt4284, final int anInt4285) {
        try {
            this.anInt4285 = anInt4285;
            this.anInt4284 = anInt4284;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "waa.<init>(" + anInt4284 + ',' + anInt4285 + ')');
        }
    }
    
    static {
        Class98_Sub49.anInt4286 = 0;
        Class98_Sub49.aClass100_4283 = new Class100(64);
    }
}

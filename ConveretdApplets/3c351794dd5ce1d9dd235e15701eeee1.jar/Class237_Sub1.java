import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class237_Sub1 extends Class237
{
    private int[] anIntArray5040;
    private byte[][] aByteArrayArray5041;
    private int anInt5042;
    private Class98_Sub22 aClass98_Sub22_5043;
    static boolean aBoolean5044;
    private int anInt5045;
    private Class207 aClass207_5046;
    static int anInt5047;
    private Class98_Sub22 aClass98_Sub22_5048;
    
    Class237_Sub1(final int n, final Class207 aClass207_5046, final int anInt5042) {
        super(n);
        this.aByteArrayArray5041 = new byte[10][];
        this.aClass98_Sub22_5043 = new Class98_Sub22(null);
        this.aClass98_Sub22_5048 = new Class98_Sub22(null);
        try {
            this.anInt5042 = anInt5042;
            this.aClass207_5046 = aClass207_5046;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dha.<init>(" + n + ',' + ((aClass207_5046 != null) ? "{...}" : "null") + ',' + anInt5042 + ')');
        }
    }
    
    @Override
    final int method2899(final int n, final byte[] aByteArray3992) throws IOException {
        try {
            if (this.anIntArray5040 == null) {
                if (!this.aClass207_5046.method2751(0, this.anInt5042, -6329)) {
                    return 0;
                }
                final byte[] method2745 = this.aClass207_5046.method2745(0, this.anInt5042, false);
                if (method2745 == null) {
                    throw new IllegalStateException("");
                }
                this.aClass98_Sub22_5048.aByteArray3992 = method2745;
                this.aClass98_Sub22_5048.anInt3991 = 0;
                final int n2 = method2745.length >> -830071903;
                this.anIntArray5040 = new int[n2];
                for (int n3 = 0; ~n3 > ~n2; ++n3) {
                    this.anIntArray5040[n3] = this.aClass98_Sub22_5048.readShort((byte)127);
                }
            }
            if (~this.anIntArray5040.length >= ~this.anInt5045) {
                return -1;
            }
            this.method2916((byte)44);
            this.aClass98_Sub22_5048.anInt3991 = 0;
            this.aClass98_Sub22_5048.aByteArray3992 = aByteArray3992;
            if (n <= 43) {
                this.anIntArray5040 = null;
            }
            while (this.aClass98_Sub22_5048.anInt3991 < this.aClass98_Sub22_5048.aByteArray3992.length) {
                if (this.aClass98_Sub22_5043.aByteArray3992 == null) {
                    if (this.aByteArrayArray5041[0] == null) {
                        this.aClass98_Sub22_5048.aByteArray3992 = null;
                        return this.aClass98_Sub22_5048.anInt3991;
                    }
                    this.aClass98_Sub22_5043.aByteArray3992 = this.aByteArrayArray5041[0];
                }
                final int n4 = -this.aClass98_Sub22_5048.anInt3991 + this.aClass98_Sub22_5048.aByteArray3992.length;
                final int n5 = -this.aClass98_Sub22_5043.anInt3991 + this.aClass98_Sub22_5043.aByteArray3992.length;
                if (~n4 > ~n5) {
                    this.aClass98_Sub22_5043.method1190(this.aClass98_Sub22_5048.aByteArray3992, true, n4, this.aClass98_Sub22_5048.anInt3991);
                    this.aClass98_Sub22_5048.aByteArray3992 = null;
                    return aByteArray3992.length;
                }
                this.aClass98_Sub22_5048.method1217(this.aClass98_Sub22_5043.aByteArray3992, n5, -1, this.aClass98_Sub22_5043.anInt3991);
                this.aClass98_Sub22_5043.aByteArray3992 = null;
                this.aClass98_Sub22_5043.anInt3991 = 0;
                ++this.anInt5045;
                for (int i = 0; i < 9; ++i) {
                    this.aByteArrayArray5041[i] = this.aByteArrayArray5041[1 + i];
                }
                this.aByteArrayArray5041[9] = null;
                if (this.anInt5045 >= this.anIntArray5040.length) {
                    this.aClass98_Sub22_5048.aByteArray3992 = null;
                    return this.aClass98_Sub22_5048.anInt3991;
                }
            }
            this.aClass98_Sub22_5048.aByteArray3992 = null;
            return aByteArray3992.length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dha.O(" + n + ',' + ((aByteArray3992 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class332 method2915(final int n, final Class207 class207, final byte b) {
        try {
            if (b != -89) {
                Class237_Sub1.aBoolean5044 = true;
            }
            Class332 class208 = (Class332)Class135.aClass79_1054.method802(-123, n);
            if (class208 == null) {
                Label_0068: {
                    if (!Class98_Sub5_Sub2.aBoolean5535) {
                        class208 = Class271.method3277(class207.method2733(n, 58), 1);
                        if (!client.aBoolean3553) {
                            break Label_0068;
                        }
                    }
                    class208 = Class265.aHa1974.method1758(Class324.method3683(class207, n), true);
                }
                Class135.aClass79_1054.method805(n, class208, (byte)(-80));
            }
            return class208;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dha.R(" + n + ',' + ((class207 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method2916(final byte b) {
        try {
            if (this.anIntArray5040 != null) {
                if (b != 44) {
                    Class237_Sub1.anInt5047 = -49;
                }
                for (int i = 0; i < 10; ++i) {
                    if (this.anInt5045 + i >= this.anIntArray5040.length) {
                        break;
                    }
                    if (this.aByteArrayArray5041[i] == null && this.aClass207_5046.method2751(0, this.anIntArray5040[this.anInt5045 + i], -6329)) {
                        this.aByteArrayArray5041[i] = this.aClass207_5046.method2745(0, this.anIntArray5040[i + this.anInt5045], false);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dha.S(" + b + ')');
        }
    }
    
    static {
        Class237_Sub1.aBoolean5044 = false;
        Class237_Sub1.anInt5047 = 0;
    }
}

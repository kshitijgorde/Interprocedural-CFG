import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class42_Sub1_Sub1 extends Class42_Sub1
{
    int anInt6204;
    float aFloat6205;
    static Class207 aClass207_6206;
    int anInt6207;
    static int anInt6208;
    float aFloat6209;
    static int anInt6210;
    boolean aBoolean6211;
    
    public static void method386(final int n) {
        try {
            if (n == -16573) {
                Class42_Sub1_Sub1.aClass207_6206 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.K(" + n + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int anInt6207, final int anInt6208, final int n, final int n2, final int[] array) {
        super(ha_Sub1, 3553, 6408, n, n2);
        try {
            this.anInt6207 = anInt6207;
            this.method379(this.anInt6204 = anInt6208, array, 0, 3656, true, 0, 0, anInt6207, 0);
            this.aFloat6205 = anInt6207 / n;
            this.aFloat6209 = anInt6208 / n2;
            this.method383(this.aBoolean6211 = false, 10242, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + anInt6207 + ',' + anInt6208 + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int anInt6207, final int anInt6208, final int n2, final int n3) {
        super(ha_Sub1, 3553, n, n2, n3);
        try {
            this.aBoolean6211 = false;
            this.anInt6204 = anInt6208;
            this.anInt6207 = anInt6207;
            this.aFloat6205 = anInt6207 / n2;
            this.aFloat6209 = anInt6208 / n3;
            this.method383(false, 10242, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt6207 + ',' + anInt6208 + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method387(final boolean b) {
        try {
            if (Class264.aFileOutputStream1969 != null) {
                try {
                    Class264.aFileOutputStream1969.close();
                }
                catch (IOException ex2) {}
            }
            if (!b) {
                method387(true);
            }
            Class264.aFileOutputStream1969 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.J(" + b + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int n3, final int anInt6207, final int anInt6208, final boolean b) {
        super(ha_Sub1, n, n2, n3, anInt6207, anInt6208);
        try {
            this.anInt6204 = anInt6208;
            Label_0071: {
                if (super.anInt3226 == 34037) {
                    this.aFloat6209 = anInt6208;
                    this.aFloat6205 = anInt6207;
                    this.aBoolean6211 = false;
                    if (!client.aBoolean3553) {
                        break Label_0071;
                    }
                }
                this.aBoolean6211 = true;
                final float n4 = 1.0f;
                this.aFloat6209 = n4;
                this.aFloat6205 = n4;
            }
            this.anInt6207 = anInt6207;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt6207 + ',' + anInt6208 + ',' + b + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int anInt6207, final int anInt6208, final boolean b, final byte[] array, final int n3) {
        super(ha_Sub1, n, n2, anInt6207, anInt6208, b, array, n3, true);
        try {
            Label_0070: {
                if (super.anInt3226 == 34037) {
                    this.aBoolean6211 = false;
                    this.aFloat6205 = anInt6207;
                    this.aFloat6209 = anInt6208;
                    if (!client.aBoolean3553) {
                        break Label_0070;
                    }
                }
                final float n4 = 1.0f;
                this.aFloat6209 = n4;
                this.aFloat6205 = n4;
                this.aBoolean6211 = true;
            }
            this.anInt6207 = anInt6207;
            this.anInt6204 = anInt6208;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt6207 + ',' + anInt6208 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int anInt6207, final int anInt6208) {
        super(ha_Sub1, n, n2, anInt6207, anInt6208);
        try {
            this.anInt6207 = anInt6207;
            Label_0069: {
                if (super.anInt3226 != 34037) {
                    final float n3 = 1.0f;
                    this.aFloat6209 = n3;
                    this.aFloat6205 = n3;
                    this.aBoolean6211 = true;
                    if (!client.aBoolean3553) {
                        break Label_0069;
                    }
                }
                this.aBoolean6211 = false;
                this.aFloat6209 = anInt6208;
                this.aFloat6205 = anInt6207;
            }
            this.anInt6204 = anInt6208;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt6207 + ',' + anInt6208 + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int n2, final int anInt6207, final int anInt6208, final int n3, final int n4, final boolean b) {
        super(ha_Sub1, 3553, n, n2, n3, n4);
        try {
            this.aFloat6209 = anInt6208 / n4;
            this.anInt6204 = anInt6208;
            this.aFloat6205 = anInt6207 / n3;
            this.anInt6207 = anInt6207;
            this.method383(this.aBoolean6211 = false, 10242, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt6207 + ',' + anInt6208 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int anInt6207, final int anInt6208, final int n2, final int n3, final byte[] array, final int n4) {
        super(ha_Sub1, 3553, n, n2, n3);
        try {
            this.anInt6204 = anInt6208;
            this.method378(this.anInt6207 = anInt6207, n4, true, 0, array, 0, (byte)(-80), 0, 0, anInt6208);
            this.aBoolean6211 = false;
            this.aFloat6209 = anInt6208 / n3;
            this.aFloat6205 = anInt6207 / n2;
            this.method383(false, 10242, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt6207 + ',' + anInt6208 + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    Class42_Sub1_Sub1(final ha_Sub1 ha_Sub1, final int n, final int anInt6207, final int anInt6208, final boolean b, final int[] array, final int n2, final int n3) {
        super(ha_Sub1, n, 6408, anInt6207, anInt6208, b, array, n2, n3, true);
        try {
            this.anInt6207 = anInt6207;
            Label_0079: {
                if (~super.anInt3226 == 0xFFFF7B0A) {
                    this.aBoolean6211 = false;
                    this.aFloat6209 = anInt6208;
                    this.aFloat6205 = anInt6207;
                    if (!client.aBoolean3553) {
                        break Label_0079;
                    }
                }
                final float n4 = 1.0f;
                this.aFloat6209 = n4;
                this.aFloat6205 = n4;
                this.aBoolean6211 = true;
            }
            this.anInt6204 = anInt6208;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iaa.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt6207 + ',' + anInt6208 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class42_Sub1_Sub1.anInt6210 = 0;
    }
}

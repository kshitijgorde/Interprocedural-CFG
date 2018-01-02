// 
// Decompiled by Procyon v0.5.30
// 

final class Class354
{
    private boolean aBoolean3010;
    int anInt3011;
    int anInt3012;
    private boolean aBoolean3013;
    static Class98_Sub9 aClass98_Sub9_3014;
    int anInt3015;
    String aString3016;
    
    final Class143 method3870(final int n, final Class88 class88) {
        try {
            return class88.method863(this.aString3016, false, this.aBoolean3013 ? this.anInt3012 : this.anInt3015, this.aBoolean3010);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "via.E(" + n + ',' + ((class88 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3871(final int n) {
        try {
            Class354.aClass98_Sub9_3014 = null;
            if (n != 43594) {
                method3872((byte)(-4));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "via.A(" + n + ')');
        }
    }
    
    static final Class277 method3872(final byte b) {
        try {
            if (b != 83) {
                Class354.aClass98_Sub9_3014 = null;
            }
            try {
                return (Class277)Class.forName("Class277_Sub1").newInstance();
            }
            catch (Throwable t) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "via.D(" + b + ')');
        }
    }
    
    final boolean method3873(final int n, final Class354 class354) {
        try {
            if (n < 103) {
                this.method3873(-66, null);
            }
            return class354 != null && this.anInt3011 == class354.anInt3011 && this.aString3016.equals(class354.aString3016);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "via.C(" + n + ',' + ((class354 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3874(final int n) {
        try {
            if (!this.aBoolean3013) {
                this.aBoolean3013 = true;
                this.aBoolean3010 = true;
            }
            else if (!this.aBoolean3010) {
                this.aBoolean3013 = false;
            }
            else {
                this.aBoolean3010 = false;
            }
            if (n != 0) {
                this.method3873(9, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "via.B(" + n + ')');
        }
    }
    
    public Class354() {
        this.aBoolean3010 = false;
        this.aBoolean3013 = true;
        this.anInt3012 = 443;
        this.anInt3015 = 43594;
    }
    
    static {
        Class354.aClass98_Sub9_3014 = new Class98_Sub9(0, 0);
    }
}

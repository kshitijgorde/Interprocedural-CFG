import jagdx.IDirect3DBaseTexture;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class26
{
    boolean aBoolean269;
    ha_Sub3_Sub1 aHa_Sub3_Sub1_270;
    Class162 aClass162_271;
    Class200 aClass200_272;
    Class164 aClass164_273;
    
    abstract IDirect3DBaseTexture method293(final byte p0);
    
    Class26(final ha_Sub3_Sub1 aHa_Sub3_Sub1_270, final Class164 aClass164_273, final Class162 aClass162_271, final boolean aBoolean269, final int n) {
        this.aClass200_272 = Class284_Sub1_Sub1.aClass200_6187;
        try {
            this.aBoolean269 = aBoolean269;
            this.aClass162_271 = aClass162_271;
            this.aHa_Sub3_Sub1_270 = aHa_Sub3_Sub1_270;
            this.aClass164_273 = aClass164_273;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    void method4(final byte b, final Class200 aClass200_272) {
        try {
            this.aClass200_272 = aClass200_272;
            if (b != -81) {
                this.aHa_Sub3_Sub1_270 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}

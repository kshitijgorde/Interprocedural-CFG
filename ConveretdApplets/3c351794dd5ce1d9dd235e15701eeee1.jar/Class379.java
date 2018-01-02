// 
// Decompiled by Procyon v0.5.30
// 

final class Class379
{
    static boolean aBoolean3192;
    int anInt3193;
    int anInt3194;
    int anInt3195;
    static int anInt3196;
    int anInt3197;
    static Class246_Sub3[] aClass246_Sub3Array3198;
    
    public static void method4007(final boolean b) {
        try {
            Class379.aClass246_Sub3Array3198 = null;
            if (!b) {
                Class379.anInt3196 = -58;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ww.C(" + b + ')');
        }
    }
    
    final void method4008(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            if (b <= 54) {
                this.method4008((byte)108, null);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-108));
                if (unsignedByte == 0) {
                    break;
                }
                this.method4009(unsignedByte, class98_Sub22, 116);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ww.B(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method4009(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n2 <= 88) {
                this.method4009(-8, null, -88);
            }
            if (~n == 0xFFFFFFFE) {
                this.anInt3195 = class98_Sub22.readUnsignedByte((byte)21);
            }
            else if (n != 2) {
                if (~n != 0xFFFFFFFC) {
                    if (n == 4) {
                        this.anInt3193 = class98_Sub22.readUShort(false);
                    }
                }
                else {
                    this.anInt3194 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt3197 = class98_Sub22.readShort((byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ww.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    public Class379() {
        this.anInt3193 = 0;
        this.anInt3197 = 2048;
        this.anInt3194 = 2048;
        this.anInt3195 = 0;
    }
    
    static {
        Class379.anInt3196 = 0;
    }
}

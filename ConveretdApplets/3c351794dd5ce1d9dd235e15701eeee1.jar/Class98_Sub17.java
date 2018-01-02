// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub17 extends Class98
{
    static boolean aBoolean3942;
    static int anInt3943;
    static boolean aBoolean3944;
    
    abstract int method1151(final int p0);
    
    abstract int method1152(final int p0);
    
    static final String method1153(final byte b) {
        try {
            if (b >= -28) {
                return null;
            }
            String s = "www";
            if (Class64_Sub29.aClass196_3720 == Class146.aClass196_1182) {
                s = "www-wtrc";
            }
            else if (Class246_Sub3_Sub4_Sub2_Sub2.aClass196_6543 == Class64_Sub29.aClass196_3720) {
                s = "www-wtqa";
            }
            else if (Class64_Sub29.aClass196_3720 == Class207.aClass196_1578) {
                s = "www-wtwip";
            }
            String string = "";
            if (Class89.aString716 != null) {
                string = "/p=" + Class89.aString716;
            }
            return "http://" + s + "." + Class4.aClass279_86.aString2098 + ".com/l=" + Class374.anInt3159 + "/a=" + Class98_Sub10_Sub15.anInt5619 + string + "/";
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hfa.J(" + b + ')');
        }
    }
    
    abstract long method1154(final boolean p0);
    
    abstract int method1155(final int p0);
    
    abstract int method1156(final int p0);
    
    static {
        Class98_Sub17.aBoolean3942 = true;
        Class98_Sub17.anInt3943 = 0;
        Class98_Sub17.aBoolean3944 = false;
    }
}

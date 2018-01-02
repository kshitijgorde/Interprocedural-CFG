// 
// Decompiled by Procyon v0.5.30
// 

final class Class370
{
    int[] anIntArray3133;
    static IncomingOpcode aClass58_3134;
    static int[] anIntArray3135;
    static Class257 aClass257_3136;
    int anInt3137;
    int[] anIntArray3138;
    static int anInt3139;
    static int anInt3140;
    
    public static void method3956(final int n) {
        try {
            Class370.anIntArray3135 = null;
            Class370.aClass257_3136 = null;
            if (n <= -118) {
                Class370.aClass58_3134 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wf.A(" + n + ')');
        }
    }
    
    Class370(final int anInt3137) {
        try {
            this.anInt3137 = anInt3137;
            this.anIntArray3133 = new int[this.anInt3137];
            this.anIntArray3138 = new int[this.anInt3137];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wf.<init>(" + anInt3137 + ')');
        }
    }
    
    static {
        Class370.anIntArray3135 = new int[] { 8, 11, 4, 6, 9, 7, 10, 0 };
        Class370.aClass58_3134 = new IncomingOpcode(0, 12);
    }
}

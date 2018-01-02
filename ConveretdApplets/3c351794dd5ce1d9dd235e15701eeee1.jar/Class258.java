// 
// Decompiled by Procyon v0.5.30
// 

final class Class258
{
    static Class155[] aClass155Array1951;
    static int anInt1952;
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qc.toString()");
        }
    }
    
    static final Class119_Sub2 method3203(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            if (b >= -91) {
                Class258.aClass155Array1951 = null;
            }
            return new Class119_Sub2(class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.method1186(-126), class98_Sub22.method1186(-123), class98_Sub22.readUnsignedByte((byte)(-7)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qc.A(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3204(final byte b) {
        try {
            Class258.aClass155Array1951 = null;
            if (b != -18) {
                Class258.aClass155Array1951 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qc.B(" + b + ')');
        }
    }
    
    static {
        Class258.anInt1952 = 0;
    }
}

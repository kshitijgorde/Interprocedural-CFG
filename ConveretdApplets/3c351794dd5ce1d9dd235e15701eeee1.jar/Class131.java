import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class131
{
    String aString1032;
    String aString1033;
    String aString1034;
    int anInt1035;
    int anInt1036;
    int anInt1037;
    static Applet anApplet1038;
    int anInt1039;
    int anInt1040;
    String aString1041;
    String aString1042;
    
    final void method2232(final int anInt1036, final String aString1041, final String aString1042, final String aString1043, final int anInt1037, final String aString1044, final int anInt1038, final String aString1045, final int n) {
        try {
            this.anInt1037 = Class14.method226(n ^ 0xFFFF868F);
            this.aString1032 = aString1043;
            this.anInt1035 = anInt1037;
            this.aString1041 = aString1041;
            this.aString1034 = aString1044;
            this.anInt1039 = Class215.anInt1614;
            this.anInt1036 = anInt1036;
            if (n != -30991) {
                this.anInt1036 = -72;
            }
            this.anInt1040 = anInt1038;
            this.aString1042 = aString1045;
            this.aString1033 = aString1042;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "it.B(" + anInt1036 + ',' + ((aString1041 != null) ? "{...}" : "null") + ',' + ((aString1042 != null) ? "{...}" : "null") + ',' + ((aString1043 != null) ? "{...}" : "null") + ',' + anInt1037 + ',' + ((aString1044 != null) ? "{...}" : "null") + ',' + anInt1038 + ',' + ((aString1045 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2233(final int n) {
        try {
            Class131.anApplet1038 = null;
            if (n != -11535) {
                Class131.anApplet1038 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "it.A(" + n + ')');
        }
    }
    
    Class131(final int anInt1040, final int anInt1041, final String aString1033, final String aString1034, final String aString1035, final String aString1036, final int anInt1042, final String aString1037) {
        try {
            this.anInt1037 = Class14.method226(120);
            this.anInt1036 = anInt1042;
            this.anInt1039 = Class215.anInt1614;
            this.anInt1040 = anInt1040;
            this.aString1033 = aString1033;
            this.aString1041 = aString1037;
            this.aString1032 = aString1036;
            this.aString1042 = aString1035;
            this.aString1034 = aString1034;
            this.anInt1035 = anInt1041;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "it.<init>(" + anInt1040 + ',' + anInt1041 + ',' + ((aString1033 != null) ? "{...}" : "null") + ',' + ((aString1034 != null) ? "{...}" : "null") + ',' + ((aString1035 != null) ? "{...}" : "null") + ',' + ((aString1036 != null) ? "{...}" : "null") + ',' + anInt1042 + ',' + ((aString1037 != null) ? "{...}" : "null") + ')');
        }
    }
}

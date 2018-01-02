// 
// Decompiled by Procyon v0.5.30
// 

final class Class62
{
    int anInt483;
    int anInt484;
    long aLong485;
    static Class164 aClass164_486;
    static Class264 aClass264_487;
    String aString488;
    String aString489;
    static int anInt490;
    
    public static void method543(final int n) {
        try {
            Class62.aClass164_486 = null;
            Class62.aClass264_487 = null;
            if (n != 0) {
                Class62.aClass264_487 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eca.A(" + n + ')');
        }
    }
    
    static final void method544(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        try {
            if (Class85.method837(n3, 48)) {
                Label_0078: {
                    if (Class64_Sub13.aClass293ArrayArray3674[n3] == null) {
                        client.method104(Class159.aClass293ArrayArray1252[n3], -1, n12, n2, n4, n6, n7, n11, n8, n9, n, n10);
                        if (!client.aBoolean3553) {
                            break Label_0078;
                        }
                    }
                    client.method104(Class64_Sub13.aClass293ArrayArray3674[n3], -1, n12, n2, n4, n6, n7, n11, n8, n9, n, n10);
                }
                if (n5 != 0) {
                    method543(-60);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eca.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ',' + n12 + ')');
        }
    }
    
    Class62(final int anInt484, final String aString489, final int anInt485, final String aString490, final long aLong485) {
        try {
            this.aString488 = aString490;
            this.aString489 = aString489;
            this.anInt484 = anInt484;
            this.aLong485 = aLong485;
            this.anInt483 = anInt485;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eca.<init>(" + anInt484 + ',' + ((aString489 != null) ? "{...}" : "null") + ',' + anInt485 + ',' + ((aString490 != null) ? "{...}" : "null") + ',' + aLong485 + ')');
        }
    }
    
    static {
        Class62.aClass164_486 = new Class164(4);
        Class62.anInt490 = 0;
    }
}

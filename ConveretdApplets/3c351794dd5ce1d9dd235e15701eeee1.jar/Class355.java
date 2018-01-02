// 
// Decompiled by Procyon v0.5.30
// 

final class Class355
{
    static int anInt3017;
    
    static final Class98_Sub44 method3875(final int n, final Class207 class207, final int n2) {
        try {
            if (n2 > -20) {
                Class355.anInt3017 = -75;
            }
            final byte[] method2733 = class207.method2733(n, -119);
            if (method2733 == null) {
                return null;
            }
            return new Class98_Sub44(method2733);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vk.A(" + n + ',' + ((class207 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
}

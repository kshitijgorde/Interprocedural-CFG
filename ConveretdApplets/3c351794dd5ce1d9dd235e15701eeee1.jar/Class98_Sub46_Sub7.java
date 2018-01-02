// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub7 extends Class98_Sub46
{
    byte[] aByteArray5981;
    private static char[] aCharArray5982;
    
    Class98_Sub46_Sub7(final byte[] aByteArray5981) {
        try {
            this.aByteArray5981 = aByteArray5981;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dn.<init>(" + ((aByteArray5981 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1551(final byte b) {
        try {
            Class98_Sub46_Sub7.aCharArray5982 = null;
            if (b != -28) {
                Class98_Sub46_Sub7.aCharArray5982 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dn.A(" + b + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub7.aCharArray5982 = new char[64];
        for (int n = 0; ~n > -27; ++n) {
            Class98_Sub46_Sub7.aCharArray5982[n] = (char)(n + 65);
        }
        for (int n2 = 26; ~n2 > -53; ++n2) {
            Class98_Sub46_Sub7.aCharArray5982[n2] = (char)(-26 + n2 + 97);
        }
        for (int n3 = 52; ~n3 > -63; ++n3) {
            Class98_Sub46_Sub7.aCharArray5982[n3] = (char)(48 + (n3 - 52));
        }
        Class98_Sub46_Sub7.aCharArray5982[62] = '*';
        Class98_Sub46_Sub7.aCharArray5982[63] = '-';
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

final class Class30
{
    static final boolean method304(final int n, final byte[] array) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            if (class98_Sub22.readUnsignedByte((byte)(-100)) != 2) {
                return false;
            }
            if (~class98_Sub22.readUnsignedByte((byte)(-123)) == 0xFFFFFFFE) {
                ha_Sub1.method1853(2, class98_Sub22);
            }
            Class291.method3415(104, class98_Sub22);
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cca.A(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
}

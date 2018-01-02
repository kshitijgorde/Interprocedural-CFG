// 
// Decompiled by Procyon v0.5.30
// 

final class Class284_Sub2_Sub1 extends Class284_Sub2
{
    private byte[] aByteArray6195;
    
    final byte[] method3377(final int n, final int n2, final int n3, final int n4) {
        try {
            this.aByteArray6195 = new byte[2 * n * (n4 * n3)];
            if (n2 != 20283) {
                return null;
            }
            this.method3361((byte)(-81), n4, n3, n);
            return this.aByteArray6195;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "in.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method3375(final int n, final int n2, final byte b) {
        try {
            int n3 = n * 2;
            final int n4 = 0xFF & b;
            this.aByteArray6195[n3++] = (byte)(n4 * 3 >> -320652859);
            this.aByteArray6195[n3] = (byte)(n4 * 3 >> 451584293);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "in.L(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    public Class284_Sub2_Sub1() {
        super(8, 5, 8, 8, 2, 0.1f, 0.55f, 3.0f);
    }
}

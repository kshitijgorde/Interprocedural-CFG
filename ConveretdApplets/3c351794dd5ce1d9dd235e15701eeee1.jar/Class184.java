// 
// Decompiled by Procyon v0.5.30
// 

final class Class184
{
    static final boolean method2626(final r r, final int n, final int n2, final int n3, final boolean[] array) {
        boolean b = false;
        if (Class78.aSArray594 != Class81.aSArray618) {
            final int method3417 = Class98_Sub46_Sub2_Sub2.aSArray6298[n].method3417(n2, n3, true);
            for (int i = 0; i <= n; ++i) {
                final s s = Class98_Sub46_Sub2_Sub2.aSArray6298[i];
                if (s != null) {
                    final int n4 = method3417 - s.method3417(n2, n3, true);
                    if (array == null || (array[i] = s.method3418(r, n2, n4, n3, 0, false))) {
                        s.CA(r, n2, n4, n3, 0, false);
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    static final boolean method2627(final int n, final char c) {
        try {
            return (c >= ' ' && c <= '~') || (~c <= -161 && ~c >= -256) || (n == 376 && (c == '\u20ac' || c == '\u0152' || c == '\u2014' || c == '\u0153' || c == '\u0178'));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mea.B(" + n + ',' + c + ')');
        }
    }
}

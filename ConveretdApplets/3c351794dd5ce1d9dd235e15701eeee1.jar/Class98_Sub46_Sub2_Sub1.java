import java.lang.ref.SoftReference;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub2_Sub1 extends Class98_Sub46_Sub2
{
    private SoftReference aSoftReference6293;
    
    @Override
    final Object method1533(final boolean b) {
        Object value;
        try {
            if (!b) {
                this.aSoftReference6293 = null;
            }
            value = this.aSoftReference6293.get();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return value;
    }
    
    Class98_Sub46_Sub2_Sub1(final Object o, final int n) {
        super(n);
        try {
            this.aSoftReference6293 = new SoftReference((T)o);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method1536(final int n) {
        boolean b;
        try {
            if (n <= 116) {
                return true;
            }
            b = true;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
}

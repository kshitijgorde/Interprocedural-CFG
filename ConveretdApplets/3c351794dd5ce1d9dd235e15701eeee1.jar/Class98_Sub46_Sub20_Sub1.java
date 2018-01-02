import java.lang.ref.SoftReference;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub20_Sub1 extends Class98_Sub46_Sub20
{
    private SoftReference aSoftReference6314;
    
    @Override
    final boolean method1638(final int n) {
        boolean b;
        try {
            if (n != 896) {
                return true;
            }
            b = true;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    Class98_Sub46_Sub20_Sub1(final Interface20 interface20, final Object o, final int n) {
        super(interface20, n);
        try {
            this.aSoftReference6314 = new SoftReference((T)o);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Object method1635(final int n) {
        Object value;
        try {
            value = this.aSoftReference6314.get();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return value;
    }
}

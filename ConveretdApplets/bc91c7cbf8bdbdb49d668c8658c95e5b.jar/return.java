// 
// Decompiled by Procyon v0.5.30
// 

public abstract class return
{
    public static final float i(final float n, final float n2, final float n3, final float n4, final float n5) {
        return (float)(n5 * (Math.pow(n4, 2.0) * 3.141592653589793 / 4.0 * (1.0 / Math.sqrt(1.0 - Math.pow(Math.pow(n4 / n3, 2.0), 2.0))) * Math.sqrt(2.0f * n / n2)));
    }
    
    public static final float _(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return i(n - n2, n3, n4, n5, n6);
    }
    
    public static final float a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        return i(n - n2, public.a(n, n4, n3), n5, n6, n7);
    }
    
    public static final float b(final float n, final float n2) {
        return n * ((float)Math.pow(n2, 2.0) * 3.1415927f / 4.0f);
    }
    
    public static final float a(final float n, final float n2) {
        return n / n2;
    }
    
    public static final float b(final float n, final float n2, final float n3) {
        return n * (float)Math.sqrt(n3 / n2);
    }
}

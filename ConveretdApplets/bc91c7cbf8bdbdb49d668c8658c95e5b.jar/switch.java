// 
// Decompiled by Procyon v0.5.30
// 

public abstract class switch
{
    public static final float _(final float n, final float n2) {
        return n / (n2 * n2 * 3.1415927f / 4.0f);
    }
    
    public static final float a(final float n, final float n2, final float n3, final float n4, final float n5) {
        final float n6 = (float)Math.pow(n4, 2.0);
        return (float)Math.pow((n - n2) / (((float)Math.pow((n5 - 1.0f) / 2.0f * n6 + 1.0f, n5 / (n5 - 1.0f)) - 1.0f) * n3 / (n5 * n6)), 0.5);
    }
    
    public static final float a(final float n, final float n2, final float n3, final float n4) {
        return (float)(Math.pow(n4 * n2 * n3, 0.5) * n);
    }
    
    public static final float _(final float n, final float n2, final float n3) {
        return (float)Math.pow(2.0f * (n - n2) / n3, 0.5);
    }
    
    public static final float a(final float n, final float n2) {
        return (float)Math.pow(2.0f * n / n2, 0.5);
    }
    
    public static final float b(final float n, final float n2, final float n3, final float n4, final float n5) {
        final float n6 = (float)Math.pow(n4, 2.0);
        final float n7 = n5 / (n5 - 1.0f);
        return (float)Math.pow((n - n2) / ((1.0f / ((float)Math.pow(((n5 - 1.0f) * n6 + 2.0f) / ((n5 + 1.0f) * n6), n7) * (float)Math.pow(2.0f * n5 / (n5 + 1.0f) * n6 - (n5 - 1.0f) / (n5 + 1.0f), 1.0f / (n5 - 1.0f))) * (float)Math.pow(1.0f + (n5 - 1.0f) * n6 / 2.0f, n7) - 1.0f) * (n3 / (n5 * n6))), 0.5);
    }
    
    public static final float _(final float n, final float n2, final float n3, final float n4, final float n5) {
        return (float)Math.pow((n - n2) / ((1.0f + (float)Math.pow(n4, 2.0) / 4.0f + (2.0f - n5) / 24.0f * (float)Math.pow(n4, 4.0)) * n3 / 2.0f), 0.5);
    }
    
    public static final float h(final float n, final float n2, final float n3, final float n4, final float n5) {
        return (float)Math.pow((n - n2) / ((float)(1.8394 - 0.7717 / (float)Math.pow(n4, 2.0) + 0.1642 / (float)Math.pow(n4, 4.0) + 0.0352 / (float)Math.pow(n4, 6.0)) * n3 / 2.0f), 0.5);
    }
}

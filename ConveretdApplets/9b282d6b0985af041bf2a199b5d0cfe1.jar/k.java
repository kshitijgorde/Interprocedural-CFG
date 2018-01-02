// 
// Decompiled by Procyon v0.5.30
// 

abstract class k extends j
{
    protected static final int[] d;
    
    Object a(final String s) {
        if (s.equals("Viewpoint")) {
            return bn.a((float[])super.a(s));
        }
        return super.a(s);
    }
    
    abstract float a(final float p0);
    
    abstract float b(final float p0);
    
    static {
        d = new int[] { -16777216 };
    }
    
    abstract void a(final float[] p0);
}

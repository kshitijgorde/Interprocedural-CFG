// 
// Decompiled by Procyon v0.5.30
// 

abstract class g extends f
{
    abstract void a(final float[] p0);
    
    abstract float a(final float p0);
    
    abstract float b(final float p0);
    
    Object a(final String s) {
        if (s.equals("Viewpoint")) {
            return ba.a((float[])super.a(s));
        }
        return super.a(s);
    }
}

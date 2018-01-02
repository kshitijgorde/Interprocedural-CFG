// 
// Decompiled by Procyon v0.5.30
// 

abstract class i extends h
{
    abstract void a(final float[] p0);
    
    abstract float a(final float p0);
    
    abstract float b(final float p0);
    
    abstract float c(final float p0);
    
    abstract float d(final float p0);
    
    Object getProperty(final String s) {
        if (s.equals("ptrz")) {
            return bj.a((float[])super.getProperty(s));
        }
        return super.getProperty(s);
    }
}

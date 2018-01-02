// 
// Decompiled by Procyon v0.5.30
// 

abstract class Dewarp extends Filter
{
    abstract void limitViewpoint(final float[] p0);
    
    abstract float getvFOVMin(final float p0);
    
    abstract float getvFOVMax(final float p0);
    
    abstract float gethFOVMin(final float p0);
    
    abstract float gethFOVMax(final float p0);
    
    Object getProperty(final String key) {
        if (key.equals("ptrz")) {
            return Util.copy((float[])super.getProperty(key));
        }
        return super.getProperty(key);
    }
}

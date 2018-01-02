// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public interface Force
{
    void init(final ForceSimulator p0);
    
    int getParameterCount();
    
    float getParameter(final int p0);
    
    float getMinValue(final int p0);
    
    float getMaxValue(final int p0);
    
    String getParameterName(final int p0);
    
    void setParameter(final int p0, final float p1);
    
    void setMinValue(final int p0, final float p1);
    
    void setMaxValue(final int p0, final float p1);
    
    boolean isSpringForce();
    
    boolean isItemForce();
    
    void getForce(final ForceItem p0);
    
    void getForce(final Spring p0);
}

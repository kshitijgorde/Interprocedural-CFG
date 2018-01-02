// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.common;

public interface IAttributeSet
{
    int getAttributeCount();
    
    IAttribute getAttributeAt(final int p0);
    
    IAttribute getAttributeByName(final String p0);
    
    IAttribute createAttribute(final String p0, final Object p1);
    
    IAttribute setAttribute(final String p0, final Object p1);
    
    IAttribute createStringAttribute(final String p0, final String p1);
    
    IAttribute setStringAttribute(final String p0, final String p1);
    
    void removeAttribute(final IAttribute p0);
    
    void clear();
}

// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.api;

public interface IGenericView
{
    void addListener(final IGenericViewListener p0);
    
    IGenericViewListener removeListener(final IGenericViewListener p0);
    
    void destroy();
}

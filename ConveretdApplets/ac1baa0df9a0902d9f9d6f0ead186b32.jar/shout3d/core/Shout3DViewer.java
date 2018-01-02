// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.awt.Component;

public interface Shout3DViewer extends CoreShout3DViewer
{
    boolean addRoute(final Field p0, final Field p1) throws Shout3DException;
    
    boolean deleteRoute(final Field p0, final Field p1);
    
    void setBilinearFiltering(final boolean p0);
    
    d a();
    
    void a(final Bindable p0);
    
    Component b();
    
    boolean isRouted(final Field p0, final Field p1);
    
    void setAntiAliased(final boolean p0);
    
    void setLoadResourcesInSeparateThread(final boolean p0);
    
    boolean isBilinearFiltering();
    
    void clearResourceCaches();
    
    boolean isAntiAliased();
    
    boolean isLoadResourcesInSeparateThread();
}

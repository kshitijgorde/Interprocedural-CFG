// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public interface RenderObserver
{
    void onPreRender(final Renderer p0, final Object p1);
    
    void onPostRender(final Renderer p0, final Object p1);
}

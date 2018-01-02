// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public interface Renderer
{
    void render(final Node p0);
    
    void removeRenderObserver(final RenderObserver p0);
    
    void addRenderObserver(final RenderObserver p0, final Object p1);
}

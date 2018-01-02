import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public interface Painter extends Runnable
{
    void setModel(final Object3d p0);
    
    void paint(final Graphics p0);
    
    void run();
}

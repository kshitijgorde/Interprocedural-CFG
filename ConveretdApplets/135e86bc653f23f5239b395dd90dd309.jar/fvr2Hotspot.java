import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public interface fvr2Hotspot
{
    void attachApplet(final fvr2 p0);
    
    boolean isMouseOver(final int p0, final int p1, final int p2, final int p3);
    
    boolean isInFrame(final int p0, final int p1);
    
    void mouseOver();
    
    void mouseOut();
    
    void mouseDown();
    
    void mouseUp();
    
    void paint(final Graphics p0);
}

import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

interface Drawable
{
    boolean isShowing();
    
    Rectangle rect();
    
    Rectangle fullRect();
    
    void paint(final Graphics p0);
    
    void paintBubble(final Graphics p0);
    
    void dragTo(final int p0, final int p1);
    
    void mouseDown(final int p0, final int p1);
}

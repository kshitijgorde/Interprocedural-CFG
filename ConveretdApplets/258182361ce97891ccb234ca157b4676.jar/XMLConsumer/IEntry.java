// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import hhapplet.IActionSink;

public interface IEntry
{
    int getPrevSpan();
    
    boolean isMainEntry();
    
    void select(final boolean p0);
    
    String getName();
    
    void highLight(final boolean p0);
    
    void action(final IActionSink p0);
    
    int getNextSpan();
    
    void display(final Graphics p0, final int p1, final int p2, final Color p3, final Image p4);
    
    int getWidth(final Graphics p0);
}

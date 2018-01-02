// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public interface Module extends MouseListener, MouseMotionListener
{
    void paint(final Graphics p0);
    
    void changedButton(final Hashtable p0);
    
    void pressedButton(final PanelButton p0);
    
    boolean isPaintable();
    
    boolean isEnabled();
    
    void newMap(final MapProjection p0);
    
    String name();
    
    String queryParam(final String p0);
    
    boolean setParam(final String p0, final String p1);
    
    boolean call(final String p0);
}

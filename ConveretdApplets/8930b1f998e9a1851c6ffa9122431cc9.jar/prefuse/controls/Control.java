// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

public interface Control extends EventListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener
{
    public static final int LEFT_MOUSE_BUTTON = 16;
    public static final int MIDDLE_MOUSE_BUTTON = 8;
    public static final int RIGHT_MOUSE_BUTTON = 4;
    
    boolean isEnabled();
    
    void setEnabled(final boolean p0);
    
    void itemDragged(final VisualItem p0, final MouseEvent p1);
    
    void itemMoved(final VisualItem p0, final MouseEvent p1);
    
    void itemWheelMoved(final VisualItem p0, final MouseWheelEvent p1);
    
    void itemClicked(final VisualItem p0, final MouseEvent p1);
    
    void itemPressed(final VisualItem p0, final MouseEvent p1);
    
    void itemReleased(final VisualItem p0, final MouseEvent p1);
    
    void itemEntered(final VisualItem p0, final MouseEvent p1);
    
    void itemExited(final VisualItem p0, final MouseEvent p1);
    
    void itemKeyPressed(final VisualItem p0, final KeyEvent p1);
    
    void itemKeyReleased(final VisualItem p0, final KeyEvent p1);
    
    void itemKeyTyped(final VisualItem p0, final KeyEvent p1);
    
    void mouseEntered(final MouseEvent p0);
    
    void mouseExited(final MouseEvent p0);
    
    void mousePressed(final MouseEvent p0);
    
    void mouseReleased(final MouseEvent p0);
    
    void mouseClicked(final MouseEvent p0);
    
    void mouseDragged(final MouseEvent p0);
    
    void mouseMoved(final MouseEvent p0);
    
    void mouseWheelMoved(final MouseWheelEvent p0);
    
    void keyPressed(final KeyEvent p0);
    
    void keyReleased(final KeyEvent p0);
    
    void keyTyped(final KeyEvent p0);
}

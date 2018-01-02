// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import prefuse.visual.VisualItem;
import prefuse.Display;
import java.awt.Cursor;
import java.awt.event.InputEvent;
import prefuse.util.ui.UILib;
import java.awt.event.MouseEvent;

public class PanControl extends ControlAdapter
{
    private boolean m_panOverItem;
    private int m_xDown;
    private int m_yDown;
    private int m_button;
    
    public PanControl() {
        this(16, false);
    }
    
    public PanControl(final boolean b) {
        this(16, b);
    }
    
    public PanControl(final int n) {
        this(n, false);
    }
    
    public PanControl(final int button, final boolean panOverItem) {
        this.m_button = button;
        this.m_panOverItem = panOverItem;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.m_button)) {
            mouseEvent.getComponent().setCursor(Cursor.getPredefinedCursor(13));
            this.m_xDown = mouseEvent.getX();
            this.m_yDown = mouseEvent.getY();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.m_button)) {
            final Display display = (Display)mouseEvent.getComponent();
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            display.pan(x - this.m_xDown, y - this.m_yDown);
            this.m_xDown = x;
            this.m_yDown = y;
            display.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.m_button)) {
            mouseEvent.getComponent().setCursor(Cursor.getDefaultCursor());
            this.m_xDown = -1;
            this.m_yDown = -1;
        }
    }
    
    public void itemPressed(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_panOverItem) {
            this.mousePressed(mouseEvent);
        }
    }
    
    public void itemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_panOverItem) {
            this.mouseDragged(mouseEvent);
        }
    }
    
    public void itemReleased(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_panOverItem) {
            this.mouseReleased(mouseEvent);
        }
    }
}

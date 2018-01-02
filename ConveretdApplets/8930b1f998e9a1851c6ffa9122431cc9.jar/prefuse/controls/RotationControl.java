// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.geom.Point2D;
import java.awt.Cursor;
import prefuse.Display;
import java.awt.event.InputEvent;
import prefuse.util.ui.UILib;
import java.awt.event.MouseEvent;
import java.awt.Point;

public class RotationControl extends ControlAdapter
{
    private Point down;
    private double baseAngle;
    private int m_button;
    
    public RotationControl() {
        this(16);
    }
    
    public RotationControl(final int button) {
        this.down = new Point();
        this.baseAngle = 0.0;
        this.m_button = button;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.m_button)) {
            ((Display)mouseEvent.getComponent()).setCursor(Cursor.getPredefinedCursor(11));
            this.down.setLocation(mouseEvent.getPoint());
            this.baseAngle = Double.NaN;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.m_button)) {
            final double atan2 = Math.atan2(mouseEvent.getY() - this.down.y, mouseEvent.getX() - this.down.x);
            if (!Double.isNaN(this.baseAngle)) {
                ((Display)mouseEvent.getComponent()).rotate(this.down, atan2 - this.baseAngle);
            }
            this.baseAngle = atan2;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.m_button)) {
            mouseEvent.getComponent().setCursor(Cursor.getDefaultCursor());
        }
    }
}

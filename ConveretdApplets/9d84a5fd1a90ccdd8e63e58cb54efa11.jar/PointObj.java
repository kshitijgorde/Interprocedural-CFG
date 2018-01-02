import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class PointObj extends DrawObj implements MouseListener
{
    protected int x;
    protected int y;
    Map m_map;
    
    public PointObj(final Map parent) {
        this.x = 0;
        this.y = 0;
        this.m_map = parent;
        super.g = parent.getGraphics();
        this.addEvent();
    }
    
    public String getSubmitValue() {
        final String strRetVal = Integer.toString(this.x) + "," + Integer.toString(this.y);
        return strRetVal;
    }
    
    public void destroyEvent() {
        this.m_map.removeMouseListener(this);
    }
    
    public void addEvent() {
        this.m_map.addMouseListener(this);
    }
    
    public void mouseReleased(final MouseEvent evt) {
        this.x = evt.getPoint().x;
        this.y = evt.getPoint().y;
        this.m_map.redraw();
        this.m_map.commitOperation();
    }
    
    public void draw() {
        if (super.g == null) {
            super.g = this.m_map.getGraphics();
        }
        if (super.g != null) {
            super.g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
            super.g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
        }
    }
    
    public void reset_object() {
        this.x = 0;
        this.y = 0;
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
    
    public void resize(final Point p) {
    }
    
    public void mousePressed(final MouseEvent evt) {
    }
}

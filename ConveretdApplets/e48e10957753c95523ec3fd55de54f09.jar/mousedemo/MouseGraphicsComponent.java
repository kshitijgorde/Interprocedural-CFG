// 
// Decompiled by Procyon v0.5.30
// 

package mousedemo;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

class MouseGraphicsComponent extends JComponent implements MouseMotionListener
{
    private int _lastMovedX;
    private int _lastMovedY;
    ArrayList<Point> _points;
    
    public MouseGraphicsComponent() {
        this._lastMovedX = 0;
        this._lastMovedY = 0;
        this._points = new ArrayList<Point>(1000);
        this.setPreferredSize(new Dimension(200, 200));
        this.addMouseMotionListener(this);
    }
    
    public void paintComponent(final Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        final String coords = "x=" + this._lastMovedX + ", y=" + this._lastMovedY;
        g.drawString(coords, this._lastMovedX, this._lastMovedY);
        if (this._points.size() > 1) {
            Point previousPoint = this._points.get(0);
            for (int i = 1; i < this._points.size(); ++i) {
                final Point thisPoint = this._points.get(i);
                g.drawLine(previousPoint.x, previousPoint.y, thisPoint.x, thisPoint.y);
                previousPoint = thisPoint;
            }
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
        this._lastMovedX = e.getX();
        this._lastMovedY = e.getY();
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent e) {
        this._points.add(e.getPoint());
        this.repaint();
    }
    
    public void clear() {
        this._points.clear();
        this.repaint();
    }
}

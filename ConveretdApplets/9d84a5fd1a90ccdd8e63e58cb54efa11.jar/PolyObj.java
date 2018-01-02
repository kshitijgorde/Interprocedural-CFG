import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolyObj extends DrawObj implements MouseMotionListener, MouseListener, KeyListener
{
    protected Point pAnchor;
    protected Point pMoveAnchor;
    protected Polygon m_poly;
    protected Map m_map;
    Rosa2000 m_applet;
    
    public PolyObj(final Map parent, final Rosa2000 applet) {
        this.m_poly = new Polygon();
        this.m_applet = applet;
        this.m_map = parent;
        super.g = parent.getGraphics();
        this.addEvent();
    }
    
    public String getSubmitValue() {
        String strRetVal = new String();
        for (int i = 0; i < this.m_poly.npoints; ++i) {
            strRetVal = strRetVal + Integer.toString(this.m_poly.xpoints[i]) + "," + Integer.toString(this.m_poly.ypoints[i]);
            if (i < this.m_poly.npoints - 1) {
                strRetVal += ";";
            }
        }
        return strRetVal;
    }
    
    public void draw() {
        if (super.g != null && this.m_poly.npoints > 0) {
            final Color colorCurrent = super.g.getColor();
            super.g.setColor(this.getColor());
            super.g.drawPolyline(this.m_poly.xpoints, this.m_poly.ypoints, this.m_poly.npoints);
            super.g.setColor(colorCurrent);
            this.drawLine();
        }
    }
    
    public void drawLine() {
        if (super.g != null && this.m_poly.npoints > 0) {
            final Color colorCurrent = super.g.getColor();
            super.g.setColor(this.getColor());
            super.g.drawLine(this.pAnchor.x, this.pAnchor.y, this.pMoveAnchor.x, this.pMoveAnchor.y);
            super.g.setColor(colorCurrent);
        }
    }
    
    public void initPaint() {
        final Graphics g = this.m_map.getGraphics();
        if (g != null) {
            this.m_map.redraw();
        }
    }
    
    public void mouseMoved(final MouseEvent evt) {
        final Graphics g = this.m_map.getGraphics();
        if (g != null && this.m_poly.npoints > 0) {
            this.pMoveAnchor = evt.getPoint();
            this.initPaint();
        }
    }
    
    public void mousePressed(final MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            this.endProcessing();
        }
        else {
            this.m_poly.addPoint(evt.getX(), evt.getY());
            this.pAnchor = evt.getPoint();
            this.pMoveAnchor = this.pAnchor;
            this.m_map.redraw();
        }
    }
    
    public void destroyEvent() {
        this.m_map.removeMouseListener(this);
        this.m_map.removeMouseMotionListener(this);
        this.m_applet.removeKeyListener(this);
    }
    
    public void addEvent() {
        this.m_map.addMouseListener(this);
        this.m_map.addMouseMotionListener(this);
        this.m_applet.addKeyListener(this);
    }
    
    public void endProcessing() {
        this.destroyEvent();
        this.m_map.commitOperation();
    }
    
    public void mouseReleased(final MouseEvent evt) {
    }
    
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 10 || e.getKeyCode() == 32) {
            this.endProcessing();
        }
        else if (e.getKeyCode() == 27) {
            this.m_poly = new Polygon();
            this.endProcessing();
        }
    }
    
    public void reset_object() {
        this.m_poly = new Polygon();
    }
    
    public void resize(final Point p) {
    }
    
    public void mouseDragged(final MouseEvent evt) {
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
    
    public void keyReleased(final KeyEvent e) {
    }
    
    public void keyTyped(final KeyEvent e) {
    }
}

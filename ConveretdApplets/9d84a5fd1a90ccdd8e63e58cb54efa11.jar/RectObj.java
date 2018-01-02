import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class RectObj extends DrawObj implements MouseMotionListener, MouseListener
{
    protected final int knDeltaInit = 2;
    protected Point pAnchor;
    protected Point pMoveAnchor;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean bInit;
    Map m_map;
    protected Image offScreenImage;
    protected Graphics offScreenGraphics;
    
    public RectObj(final Map parent) {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.bInit = false;
        this.offScreenImage = null;
        this.offScreenGraphics = null;
        this.m_map = parent;
        super.g = parent.getGraphics();
        this.addEvent();
    }
    
    public String getSubmitValue() {
        final String strRetVal = Integer.toString(this.x) + "," + Integer.toString(this.y) + ";" + Integer.toString(this.x + this.width) + "," + Integer.toString(this.y + this.height);
        return strRetVal;
    }
    
    public void setAnchor(final Point pt) {
        this.pAnchor = pt;
        this.x = this.pAnchor.x;
        this.y = this.pAnchor.y;
        super.color = Color.black;
    }
    
    public void draw() {
        if (super.g == null) {
            super.g = this.m_map.getGraphics();
        }
        if (super.g != null) {
            final Color colorCurrent = super.g.getColor();
            super.g.setColor(Color.black);
            super.g.drawRect(this.x, this.y, this.width, this.height);
            super.g.setColor(colorCurrent);
        }
    }
    
    public void resize(final Point p) {
        final Graphics g = this.m_map.getGraphics();
        if (g != null) {
            if (Rosa2000.isDebugMode()) {
                System.out.println(" Undraw X= " + this.x + "y:" + this.y + "width: " + this.width + "height: " + this.height);
            }
            this.computeNewRect(p);
            if (Rosa2000.isDebugMode()) {
                System.out.println(" draw X= " + this.x + "y:" + this.y + "width: " + this.width + "height: " + this.height);
            }
            this.m_map.redraw();
        }
    }
    
    public void initPaint() {
        final Graphics g = this.m_map.getGraphics();
        if (g != null) {
            if (Rosa2000.isDebugMode()) {
                System.out.println("InitPaint Draw X= " + this.x + "y:" + this.y + "width: " + this.width + "height: " + this.height);
            }
            this.m_map.redraw();
            this.bInit = true;
        }
    }
    
    void computeNewRect(final Point p) {
        if (p.x < this.pAnchor.x) {
            this.x = p.x;
            this.width = this.pAnchor.x - this.x;
        }
        else {
            this.x = this.pAnchor.x;
            this.width = p.x - this.x;
        }
        if (p.y < this.pAnchor.y) {
            this.y = p.y;
            this.height = this.pAnchor.y - this.y;
        }
        else {
            this.y = this.pAnchor.y;
            this.height = p.y - this.y;
        }
    }
    
    public void mouseDragged(final MouseEvent evt) {
        if (!this.bInit) {
            this.computeNewRect(evt.getPoint());
            if (this.width >= 2 || this.height >= 2) {
                this.initPaint();
            }
        }
        else {
            this.resize(evt.getPoint());
        }
    }
    
    public void destroyEvent() {
        this.m_map.removeMouseListener(this);
        this.m_map.removeMouseMotionListener(this);
    }
    
    public void addEvent() {
        this.m_map.addMouseListener(this);
        this.m_map.addMouseMotionListener(this);
    }
    
    public void mousePressed(final MouseEvent evt) {
        if (Rosa2000.isDebugMode()) {
            System.out.println("Anchor  X= " + evt.getX() + "y:" + evt.getY());
        }
        this.setAnchor(evt.getPoint());
    }
    
    public void mouseReleased(final MouseEvent evt) {
        this.m_map.commitOperation();
    }
    
    public void reset_object() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }
    
    public void mouseMoved(final MouseEvent evt) {
    }
    
    public void mouseEntered(final MouseEvent evt) {
    }
    
    public void mouseExited(final MouseEvent evt) {
    }
    
    public void mouseClicked(final MouseEvent evt) {
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import imaging.math3D.ViewWindow;
import imaging.math3D.Rectangle3D;
import imaging.util.PolygonArea;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public abstract class AbstractCanvas extends Canvas implements MouseListener, MouseMotionListener, KeyListener
{
    private static final long serialVersionUID = -227251011901908825L;
    protected BufferedImage orgImage;
    protected BufferedImage image;
    protected double m_zoom;
    protected ArrayList<PolygonArea> polygonAreas;
    protected PolygonArea currentArea;
    protected boolean pointSelected;
    protected int mouseX;
    protected int mouseY;
    protected boolean drawing;
    public static final int ROTATELEFT = 361;
    public static final int ROTATERIGHT = 362;
    protected Rectangle3D textureBounds;
    protected ViewWindow viewWindow;
    protected boolean drawCurve;
    
    protected abstract void render();
    
    public abstract BufferedImage getImage();
    
    public abstract void unloadImage();
    
    public abstract void setDrawMode(final boolean p0);
    
    public abstract void applyTexture(final BufferedImage p0);
    
    public abstract void deleteSelectedPoint();
    
    public abstract void clearDraw();
    
    public abstract void clearAll();
    
    public abstract void zoomTexture(final double p0);
    
    public abstract void applyZoom(final String p0);
    
    public abstract void tranformTexture(final int p0);
    
    public AbstractCanvas() {
        this(924, 768);
    }
    
    public AbstractCanvas(final int width, final int height) {
        this.orgImage = null;
        this.image = null;
        this.m_zoom = 1.0;
        this.polygonAreas = new ArrayList<PolygonArea>();
        this.drawing = false;
        this.textureBounds = new Rectangle3D();
        this.setSize(width, height);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    
    public AbstractCanvas(final AbstractCanvas toCopy) {
        this.orgImage = null;
        this.image = null;
        this.m_zoom = 1.0;
        this.polygonAreas = new ArrayList<PolygonArea>();
        this.drawing = false;
        this.textureBounds = new Rectangle3D();
        this.setViewWindow(toCopy.getViewWindow());
        this.setTextureBounds(toCopy.getTextureBounds());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    
    @Override
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    @Override
    public void paint(final Graphics g) {
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, this);
        }
        else {
            g.setColor(Color.gray);
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
    
    protected boolean hitsPoint(final int pointX, final int pointY, final int x, final int y) {
        return this.distance(pointX, pointY, x, y) <= 5.0;
    }
    
    protected double distance(final double x1, final double y1, final double x2, final double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    public void loadImage(final BufferedImage pImage) {
        this.orgImage = pImage;
        this.image = new BufferedImage(this.orgImage.getColorModel(), this.orgImage.getRaster(), this.orgImage.isAlphaPremultiplied(), null);
        this.setSize(this.image.getWidth(), this.image.getHeight());
    }
    
    protected void finishPolygon() {
        this.currentArea = null;
        this.pointSelected = false;
    }
    
    public Rectangle3D getTextureBounds() {
        return this.textureBounds;
    }
    
    public void setTextureBounds(final Rectangle3D textureBounds) {
        this.textureBounds = textureBounds;
    }
    
    public void setLineType() {
        this.drawCurve = false;
    }
    
    public void setCurveType() {
        this.drawCurve = true;
    }
    
    public ViewWindow getViewWindow() {
        return this.viewWindow;
    }
    
    public void setViewWindow(final ViewWindow viewWindow) {
        this.viewWindow = viewWindow;
    }
    
    @Override
    public void mouseMoved(final MouseEvent mev) {
        this.mouseX = mev.getX();
        this.mouseY = mev.getY();
        if (this.m_zoom != 1.0) {
            final double mooz = 1.0 / this.m_zoom;
            this.mouseX *= (int)mooz;
            this.mouseY *= (int)mooz;
        }
        if (this.currentArea != null && this.drawing) {
            this.render();
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent kev) {
        System.out.println("Key : " + kev.getKeyCode() + " is typed.");
    }
    
    @Override
    public void keyReleased(final KeyEvent ke) {
        System.out.println("Key : " + ke.getKeyCode() + " is Released.");
    }
}

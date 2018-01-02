// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

import java.awt.event.ActionEvent;
import gravity.gameObjects.GameObject;
import java.awt.event.MouseMotionListener;
import gravity.ILauncher;
import java.awt.image.ImageObserver;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import java.awt.Color;
import gravity.tools.LayoutValidator;
import java.awt.Label;
import java.awt.event.MouseListener;
import gravity.tools.Vector2D;
import java.awt.Graphics;
import java.awt.Image;
import gravity.tools.DoubleGraphics;
import gravity.gameObjects.AngleIndicator;
import gravity.ConstantsLibrary;
import java.awt.event.ActionListener;
import java.awt.Canvas;

public class CosmosCanvas extends Canvas implements ActionListener
{
    private Cosmos _cosmos;
    private ConstantsLibrary _cl;
    private AngleIndicator _angleIndicator;
    private GravField _gravField;
    private DoubleGraphics _d;
    private Image _layer;
    private Graphics _layerGraphics;
    private DoubleGraphics _dg;
    private Image _screen;
    private Graphics _screenGraphics;
    private DoubleGraphics _dgz;
    private Image _zoomOutBox;
    private Graphics _zoomOutGraphics;
    private Vector2D _zoomOutSize;
    private boolean _zoomingOut;
    private Vector2D _lastClick;
    private MouseListener _mouseListener;
    
    public CosmosCanvas(final Cosmos cosmos, final ConstantsLibrary cl, final AngleIndicator angleIndicator, final Label label, final LayoutValidator layoutValidator) throws AssertionException {
        this._d = null;
        this._layer = null;
        this._layerGraphics = null;
        this._dg = null;
        this._screen = null;
        this._screenGraphics = null;
        this._dgz = null;
        this._zoomOutBox = null;
        this._zoomOutGraphics = null;
        this._zoomOutSize = new Vector2D(0.0, 0.0);
        this._zoomingOut = false;
        this._lastClick = new Vector2D(0.0, 0.0);
        this._gravField = new GravField(this, cl, label, layoutValidator);
        this.setCosmos(cosmos, true);
        this._cl = cl;
        this._angleIndicator = angleIndicator;
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        Assert.assert(this._cl != null && this._angleIndicator != null);
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.prepareBuffer();
        this._cosmos.paint(this._dg);
        this._cosmos.paintZoomOut(this._dgz, false);
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this._screen == null) {
            this.paint(graphics);
            return;
        }
        try {
            this._cosmos.update(this._dg);
            this._layerGraphics.drawImage(this._screen, 0, 0, this);
            if (this._angleIndicator.isVisible()) {
                this._angleIndicator.draw(this._d);
            }
            try {
                this._cosmos.paintZoomOut(this._dgz, true);
                if (this._zoomingOut) {
                    this._layerGraphics.drawImage(this._zoomOutBox, (int)(this.getSize().width - this._zoomOutSize.x), 0, this);
                }
            }
            catch (Exception ex) {
                this._zoomingOut = false;
            }
            graphics.drawImage(this._layer, 0, 0, this);
        }
        catch (NullPointerException ex2) {
            this.refresh();
        }
    }
    
    private void prepareBuffer() {
        this._layer = this.createImage(this.getSize().width, this.getSize().height);
        this._layerGraphics = this._layer.getGraphics();
        this._screen = this.createImage(this.getSize().width, this.getSize().height);
        (this._screenGraphics = this._screen.getGraphics()).setColor(Color.white);
        this._cl.VISIBLEORIGIN = new Vector2D(-this.getOrigin().x / this.getZoomFactor().x, -this.getOrigin().y / this.getZoomFactor().y);
        this._cl.VISIBLEDIMENSION = new Vector2D(this.getSize().width * 1.0 / this.getZoomFactor().x, this.getSize().height * 1.0 / this.getZoomFactor().y);
        this._gravField.draw(this._screenGraphics);
        try {
            this._d = new DoubleGraphics(this._layerGraphics, this.getZoomFactor(), this.getOrigin());
            this._dg = new DoubleGraphics(this._screenGraphics, this.getZoomFactor(), this.getOrigin());
        }
        catch (AssertionException ex) {}
        this._zoomOutSize = new Vector2D(Math.rint(this.getSize().width / 6), Math.rint(this.getSize().height / 6));
        this._zoomOutBox = this.createImage((int)this._zoomOutSize.x, (int)this._zoomOutSize.y);
        this._zoomOutGraphics = this._zoomOutBox.getGraphics();
        this.resetZoomOutBox();
        try {
            (this._dgz = new DoubleGraphics(this._zoomOutGraphics, new Vector2D(this.getZoomFactor().x / 18.0, this.getZoomFactor().y / 18.0), new Vector2D((this.getSize().width + this.getOrigin().x) / 18.0, (this.getSize().height + this.getOrigin().y) / 18.0))).setColor(Color.black);
        }
        catch (AssertionException ex2) {}
    }
    
    public void resetZoomOutBox() {
        if (this._zoomOutGraphics == null) {
            return;
        }
        this._zoomOutGraphics.setColor(Color.white);
        this._zoomOutGraphics.fillRect(0, 0, (int)this._zoomOutSize.x, (int)this._zoomOutSize.y);
        this._zoomOutGraphics.setColor(Color.black);
        this._zoomOutGraphics.drawRect((int)(this._zoomOutSize.x / 3.0), (int)(this._zoomOutSize.y / 3.0), (int)(this._zoomOutSize.x / 3.0), (int)(this._zoomOutSize.y / 3.0));
    }
    
    public void reset(final boolean b) {
        this._zoomingOut = false;
        if (b) {
            this.repaint();
        }
        else {
            this.refresh();
        }
    }
    
    public void refresh() {
        this._screen = null;
        this.repaint();
    }
    
    public boolean isInsideZoomOutBox(final Vector2D vector2D) {
        return this._zoomingOut && vector2D.x >= this.getSize().width - this._zoomOutSize.x && vector2D.x < this.getSize().width && vector2D.y >= 0.0 && vector2D.y < this._zoomOutSize.y;
    }
    
    public Vector2D getZoomFactor() {
        float n = (int)((float)Math.min(this.getSize().width * 1.0 / this._cl.GRAVITYDIMENSION.x, this.getSize().height * 1.0 / this._cl.GRAVITYDIMENSION.y) * 2.0f) / 2.0f;
        if (n < 0.5f) {
            n = 0.5f;
        }
        return new Vector2D(n, n);
    }
    
    public Vector2D getOrigin() {
        final Vector2D vector2D = new Vector2D();
        vector2D.x = (this.getSize().width * 1.0 - this._cl.GRAVITYDIMENSION.x * this.getZoomFactor().x) / 2.0;
        vector2D.y = (this.getSize().height * 1.0 - this._cl.GRAVITYDIMENSION.y * this.getZoomFactor().y) / 2.0;
        return vector2D;
    }
    
    public void setCosmos(final Cosmos cosmos, final boolean b) throws AssertionException {
        if (this._cosmos != null) {
            this._cosmos.removeActionListener(this);
        }
        Assert.assert(cosmos != null);
        (this._cosmos = cosmos).addActionListener(this);
        if (b) {
            this._gravField.abortDrawing();
        }
    }
    
    public Cosmos getCosmos() {
        return this._cosmos;
    }
    
    public void addMouseListeners(final ILauncher launcher, final AngleIndicator angleIndicator) {
        if (this._mouseListener != null) {
            this.removeMouseListener(this._mouseListener);
            this.removeMouseMotionListener((MouseMotionListener)this._mouseListener);
        }
        try {
            this.addMouseListener(this._mouseListener = new CosmosCanvasMouseAdapter(launcher, this, angleIndicator));
            this.addMouseMotionListener((MouseMotionListener)this._mouseListener);
            this._angleIndicator = angleIndicator;
        }
        catch (AssertionException ex) {}
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("zoomOut") && !this._zoomingOut) {
            this.resetZoomOutBox();
            this._zoomingOut = true;
            this._cosmos.paintZoomOut(this._dgz, false);
            this.repaint();
        }
        if (actionEvent.getActionCommand().equals("update")) {
            this.repaint();
        }
        if (actionEvent.getActionCommand().equals("repaint")) {
            this.refresh();
        }
    }
    
    public boolean isDoubleBuffered() {
        return true;
    }
    
    protected void finalize() {
        this._gravField.abortDrawing();
        if (this._cosmos != null) {
            this._cosmos.removeActionListener(this);
        }
    }
}

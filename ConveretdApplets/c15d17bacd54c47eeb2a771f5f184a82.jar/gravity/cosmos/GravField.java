// 
// Decompiled by Procyon v0.5.30
// 

package gravity.cosmos;

import java.awt.Component;
import java.awt.Color;
import gravity.tools.Vector2D;
import java.awt.image.ImageObserver;
import gravity.tools.AssertionException;
import gravity.tools.Assert;
import java.awt.Image;
import java.awt.Graphics;
import gravity.tools.DoubleGraphics;
import gravity.tools.LayoutValidator;
import java.awt.Label;
import gravity.ConstantsLibrary;

public class GravField implements Runnable
{
    private CosmosCanvas _canvas;
    private ConstantsLibrary _cl;
    private Label _percent;
    private LayoutValidator _lv;
    private Thread _gravFieldDrawing;
    private boolean _stillDrawing;
    private boolean _abortAcknowledged;
    private DoubleGraphics _gfd;
    private Graphics _gravFieldGraphics;
    private Image _gravField;
    private boolean _gravFieldDrawn;
    
    public GravField(final CosmosCanvas canvas, final ConstantsLibrary cl, final Label percent, final LayoutValidator lv) throws AssertionException {
        this._gravFieldDrawing = null;
        this._stillDrawing = false;
        this._abortAcknowledged = true;
        this._gfd = null;
        this._gravFieldGraphics = null;
        this._gravField = null;
        this._gravFieldDrawn = false;
        this._canvas = canvas;
        this._cl = cl;
        this._percent = percent;
        this._lv = lv;
        Assert.assert(this._canvas != null && this._cl != null && this._percent != null && this._lv != null);
    }
    
    public synchronized void draw(final Graphics graphics) {
        if (!this._cl.ISGRAVFIELD) {
            if (!this._percent.getText().equals("")) {
                this._percent.setText("");
            }
            return;
        }
        if ((!this._gravFieldDrawn && !this._stillDrawing) || this._gravField == null || this._gravField.getWidth(this._canvas) != this._canvas.getSize().width || this._gravField.getHeight(this._canvas) != this._canvas.getSize().height) {
            this.abortDrawing();
            this._stillDrawing = true;
            this._abortAcknowledged = false;
            this._gravField = this._canvas.createImage(this._canvas.getSize().width, this._canvas.getSize().height);
            this._gravFieldGraphics = this._gravField.getGraphics();
            try {
                this._gfd = new DoubleGraphics(this._gravFieldGraphics, this._canvas.getZoomFactor(), this._canvas.getOrigin());
            }
            catch (AssertionException ex) {}
            this._gravFieldDrawing = null;
        }
        if (this._gravFieldDrawn) {
            try {
                graphics.drawImage(this._gravField, 0, 0, this._canvas);
            }
            catch (NullPointerException ex2) {
                this._gravFieldDrawn = false;
            }
        }
        else if (this._gravFieldDrawing == null) {
            (this._gravFieldDrawing = new Thread(this)).start();
        }
    }
    
    private boolean forceToColor(final double n, final double n2) {
        Vector2D forceOnVector2D = this._canvas.getCosmos().forceOnVector2D(new Vector2D(n, n2));
        if (forceOnVector2D.equals(new Vector2D(0.0, 0.0))) {
            forceOnVector2D = new Vector2D(1.0E-10, 0.0);
        }
        int n3 = (int)(Math.log(Math.pow(forceOnVector2D.x, 2.0) + Math.pow(forceOnVector2D.y, 2.0)) / Math.log(2.0) * 4.0 / 3.0 * 16.0);
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 > 511) {
            n3 = 511;
        }
        if (n3 > 255) {
            this._gfd.setColor(new Color(n3 - 256, n3 - 256, 255));
            return true;
        }
        this._gfd.setColor(new Color(0, 0, n3));
        return false;
    }
    
    public void run() {
        double n = this._cl.VISIBLEORIGIN.x;
        double n2 = 2.0;
        while (this._stillDrawing) {
            double y = this._cl.VISIBLEORIGIN.y;
            while (y <= this._cl.VISIBLEORIGIN.y + this._cl.VISIBLEDIMENSION.y + 0.8 && this._stillDrawing) {
                if (this.forceToColor(n, y)) {
                    for (double n3 = -0.25; n3 < 0.5; n3 += 0.5) {
                        for (double n4 = -0.25; n4 < 0.5; n4 += 0.5) {
                            this.forceToColor(n + n4 * 0.8 * n2, y + n3 * 0.8 * n2);
                            this._gfd.drawGravityPoint(n + n4 * 0.8 * n2, y + n3 * 0.8 * n2, 0.8 * n2);
                        }
                    }
                }
                else {
                    this._gfd.drawGravityPoint(n, y, 0.8 * n2);
                }
                y += 0.8 * n2;
                double n5 = (n - this._cl.VISIBLEORIGIN.x) / this._cl.VISIBLEDIMENSION.x * 50.0;
                if (this._canvas.getZoomFactor().x > 1.25) {
                    if (n2 <= 1.0) {
                        n5 += 50.0;
                    }
                }
                else {
                    n5 *= 2.0;
                }
                if (this._cl.ISGRAVFIELD) {
                    this._percent.setText("" + Math.min((int)n5, 99) + "%");
                    this._lv.validate(this._percent);
                }
                try {
                    Thread.sleep(1L);
                }
                catch (InterruptedException ex) {}
            }
            n += 0.8 * n2;
            if (n > this._cl.VISIBLEORIGIN.x + this._cl.VISIBLEDIMENSION.x + 0.8) {
                if (n2 <= 1.0 || this._canvas.getZoomFactor().x < 1.25) {
                    this._stillDrawing = false;
                    this._gravFieldDrawing = null;
                    this._percent.setText("");
                }
                if (n2 > 1.0) {
                    n2 = 1.0;
                    n = this._cl.VISIBLEORIGIN.x;
                    this._gravFieldDrawn = true;
                }
                this._canvas.refresh();
            }
        }
        this._abortAcknowledged = true;
    }
    
    public void abortDrawing() {
        this._gravFieldDrawn = false;
        if (this._stillDrawing) {
            this._stillDrawing = false;
            while (!this._abortAcknowledged) {
                try {
                    new Thread();
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
}

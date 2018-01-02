// 
// Decompiled by Procyon v0.5.30
// 

package appletviewer;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Canvas;

public class ZoomBar extends Canvas
{
    private Image _$6357;
    private Image _$6366;
    private Image _$6375;
    private Image _$6383;
    private Image _$6393;
    private Image _$6402;
    private MediaTracker _$1958;
    private String _$6410;
    private int _$6418;
    private boolean _$6427;
    
    public ZoomBar(final Image $6366, final Image $6367, final Image $6368, final Image $6369, final Image $6370, final Image $6371) {
        this._$6366 = $6366;
        this._$6375 = $6367;
        this._$6357 = $6368;
        this._$6393 = $6369;
        this._$6383 = $6370;
        this._$6402 = $6371;
        this._$6418 = 15;
        this._$6427 = false;
        (this._$1958 = new MediaTracker(this)).addImage(this._$6366, 0);
        this._$1958.addImage(this._$6375, 0);
        this._$1958.addImage(this._$6357, 0);
        this._$1958.addImage(this._$6393, 0);
        this._$1958.addImage(this._$6383, 0);
        this._$1958.addImage(this._$6402, 0);
    }
    
    public void paint(final Graphics graphics) {
        try {
            this._$1958.waitForAll();
        }
        catch (Exception ex) {}
        graphics.drawImage(this._$6366, 0, 0, this);
        graphics.drawImage(this._$6375, 39, 0, this);
        graphics.drawImage(this._$6357, 196, 0, this);
        graphics.drawImage(this._$6393, 234, 0, this);
        graphics.drawImage(this._$6383, 291, 0, this);
        graphics.clearRect(0, 23, 320, 5);
        if (this._$6427) {
            graphics.drawImage(this._$6402, this._$6418, 23, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setZoomPoint(final int $6418) {
        this._$6418 = $6418;
    }
    
    public void dspZoomPointer(final boolean $6427) {
        this._$6427 = $6427;
    }
}

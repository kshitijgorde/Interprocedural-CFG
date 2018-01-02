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

class ZoomBar extends Canvas
{
    private Image _$4227;
    private Image _$4236;
    private Image _$4245;
    private MediaTracker _$3224;
    
    public ZoomBar(final Image $4236, final Image $4237, final Image $4238) {
        this._$4236 = $4236;
        this._$4245 = $4237;
        this._$4227 = $4238;
        (this._$3224 = new MediaTracker(this)).addImage(this._$4236, 0);
        this._$3224.addImage(this._$4245, 0);
        this._$3224.addImage(this._$4227, 0);
    }
    
    public void paint(final Graphics graphics) {
        try {
            this._$3224.waitForAll();
        }
        catch (Exception ex) {}
        graphics.drawImage(this._$4236, 0, 0, this);
        graphics.drawImage(this._$4245, 38, 0, this);
        graphics.drawImage(this._$4227, 282, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}

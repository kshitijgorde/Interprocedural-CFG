// 
// Decompiled by Procyon v0.5.30
// 

package cameracontrol;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

class ImageCanvas extends Canvas
{
    private Image _$3721;
    private Image _$3727;
    private Image _$3733;
    private Image _$3741;
    private Image _$3749;
    private Image _$3757;
    private Image _$3765;
    
    public ImageCanvas(final Image image, final Image $3727) {
        this._$3721 = image;
        this._$3727 = $3727;
        this._$3765 = image;
    }
    
    public ImageCanvas(final Image $3765, final Image image, final Image $3766, final Image $3767) {
        this._$3721 = $3765;
        this._$3727 = image;
        this._$3733 = $3765;
        this._$3741 = image;
        this._$3749 = $3766;
        this._$3757 = $3767;
        this._$3765 = $3765;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this._$3765, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void imagechang(final boolean b) {
        if (!b) {
            this._$3765 = this._$3721;
        }
        else {
            this._$3765 = this._$3727;
        }
    }
    
    public void imageselect(final boolean b) {
        if (!b) {
            this._$3765 = this._$3733;
            this._$3721 = this._$3733;
            this._$3727 = this._$3741;
        }
        else {
            this._$3765 = this._$3749;
            this._$3721 = this._$3749;
            this._$3727 = this._$3757;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    private Image _$1555;
    private Image _$1556;
    private Image _$1557;
    private Image _$1558;
    private Image _$1559;
    private Image _$1560;
    private Image _$1285;
    private Color _$1495;
    
    public ImageButton(final Image image, final Image $1556, final Color $1557) {
        this._$1495 = $1557;
        while (true) {
            if (image.getWidth(null) != -1) {
                if ($1556.getWidth(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this._$1555 = image;
        this._$1556 = $1556;
        this._$1285 = image;
    }
    
    public ImageButton(final Image $1285, final Image image, final Image $1286, final Image $1287, final Color $1288) {
        this._$1495 = $1288;
        while (true) {
            if ($1285.getWidth(null) != -1 && image.getWidth(null) != -1 && $1286.getWidth(null) != -1) {
                if ($1287.getWidth(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this._$1555 = $1285;
        this._$1556 = image;
        this._$1557 = $1285;
        this._$1558 = image;
        this._$1559 = $1286;
        this._$1560 = $1287;
        this._$1285 = $1285;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this._$1495);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(this._$1285, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void imageSelect(final boolean b) {
        if (!b) {
            this._$1285 = this._$1558;
            this._$1555 = this._$1557;
            this._$1556 = this._$1558;
        }
        else {
            this._$1285 = this._$1560;
            this._$1555 = this._$1559;
            this._$1556 = this._$1560;
        }
    }
    
    public void reloadIcon(final Color $1495) {
        this.setBackground(this._$1495 = $1495);
        this.repaint();
    }
    
    public void ImCanvas_mouseDsp(final boolean b) {
        if (!b) {
            this._$1285 = this._$1555;
        }
        else {
            this._$1285 = this._$1556;
        }
        this.repaint();
    }
    
    public void setImage(final Image $1285, final Image $1286, final Image $1287) {
        this._$1285 = $1285;
        this._$1555 = $1286;
        this._$1556 = $1287;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
}

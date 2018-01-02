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
    private Image _$3133;
    private Image _$3139;
    private Image _$3145;
    private Image _$3153;
    private Image _$3161;
    private Image _$3169;
    private Image _$874;
    private Color _$3182;
    
    public ImageButton(final Image image, final Image $3139, final Color $3140) {
        this._$3182 = $3140;
        while (true) {
            if (image.getWidth(null) != -1) {
                if ($3139.getWidth(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this._$3133 = image;
        this._$3139 = $3139;
        this._$874 = image;
    }
    
    public ImageButton(final Image $874, final Image image, final Image $875, final Image $876, final Color $877) {
        this._$3182 = $877;
        while (true) {
            if ($874.getWidth(null) != -1 && image.getWidth(null) != -1 && $875.getWidth(null) != -1) {
                if ($876.getWidth(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this._$3133 = $874;
        this._$3139 = image;
        this._$3145 = $874;
        this._$3153 = image;
        this._$3161 = $875;
        this._$3169 = $876;
        this._$874 = $874;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this._$3182);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(this._$874, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void imageSelect(final boolean b) {
        if (!b) {
            this._$874 = this._$3153;
            this._$3133 = this._$3145;
            this._$3139 = this._$3153;
        }
        else {
            this._$874 = this._$3169;
            this._$3133 = this._$3161;
            this._$3139 = this._$3169;
        }
    }
    
    public void reloadIcon(final Color $3182) {
        this.setBackground(this._$3182 = $3182);
        this.repaint();
    }
    
    public void ImCanvas_mouseDsp(final boolean b) {
        if (!b) {
            this._$874 = this._$3133;
        }
        else {
            this._$874 = this._$3139;
        }
        this.repaint();
    }
    
    public void setImage(final Image $874, final Image $875, final Image $876) {
        this._$874 = $874;
        this._$3133 = $875;
        this._$3139 = $876;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
}

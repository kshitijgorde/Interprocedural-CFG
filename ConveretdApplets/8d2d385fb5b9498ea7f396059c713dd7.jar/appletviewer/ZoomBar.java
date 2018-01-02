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
    private Image imageTele;
    private Image imageWide;
    private Image imageBar;
    private Image imageETele;
    private Image imageEBar;
    private Image zPointer;
    private MediaTracker mt;
    private String zoomMode;
    private int zoomPoint;
    private boolean dspPointerFlg;
    
    public ZoomBar(final Image iW, final Image iB, final Image iT, final Image iEB, final Image iET, final Image zP) {
        this.imageWide = iW;
        this.imageBar = iB;
        this.imageTele = iT;
        this.imageEBar = iEB;
        this.imageETele = iET;
        this.zPointer = zP;
        this.zoomPoint = 15;
        this.dspPointerFlg = false;
        (this.mt = new MediaTracker(this)).addImage(this.imageWide, 0);
        this.mt.addImage(this.imageBar, 0);
        this.mt.addImage(this.imageTele, 0);
        this.mt.addImage(this.imageEBar, 0);
        this.mt.addImage(this.imageETele, 0);
        this.mt.addImage(this.zPointer, 0);
    }
    
    public void paint(final Graphics g) {
        try {
            this.mt.waitForAll();
        }
        catch (Exception ex) {}
        g.drawImage(this.imageWide, 0, 0, this);
        g.drawImage(this.imageBar, 39, 0, this);
        g.drawImage(this.imageTele, 196, 0, this);
        g.drawImage(this.imageEBar, 234, 0, this);
        g.drawImage(this.imageETele, 291, 0, this);
        g.clearRect(0, 23, 320, 5);
        if (this.dspPointerFlg) {
            g.drawImage(this.zPointer, this.zoomPoint, 23, this);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void setZoomPoint(final int point) {
        this.zoomPoint = point;
    }
    
    public void dspZoomPointer(final boolean flg) {
        this.dspPointerFlg = flg;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Panel;

final class PictureFrame extends Panel implements MouseListener
{
    private Viewer _$4305;
    
    public PictureFrame(final Viewer $4305) {
        this._$4305 = $4305;
        this.addMouseListener(this);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this._$4305.controlGUI.dzoomCanceled();
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(this._$4305.curDCur);
        if (this._$4305.controler != null) {
            this._$4305.controler.toFront();
        }
    }
    
    public final void paint(final Graphics graphics) {
        graphics.fillRoundRect(0, 0, this.getSize().width, this.getSize().height, 14, 14);
        if (this._$4305.controlGUI.controlBtnPush) {
            if (this._$4305.imageThread.digitalZoom) {
                graphics.drawImage(this._$4305.panTiltG, this.getSize().width - 26, 2, 15, 15, null);
            }
            else {
                graphics.drawImage(this._$4305.panTilt, this.getSize().width - 26, 2, 15, 15, null);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
}

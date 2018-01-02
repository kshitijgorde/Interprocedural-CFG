// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Container;

final class VolumeControl extends Container
{
    private Image _$4588;
    private Image _$4589;
    private Image _$4590;
    private Image _$4591;
    private MediaTracker _$4372;
    private int _$4592;
    private Color _$1495;
    private int _$4593;
    private boolean _$4594;
    private boolean _$4595;
    private Viewer _$4305;
    
    public VolumeControl(final Image $4591, final Image $4592, final Image $4593, final Color $4594, final Viewer $4595) {
        this._$4592 = 41;
        this._$4593 = 0;
        this._$4594 = false;
        this._$4595 = false;
        this._$4305 = $4595;
        this._$1495 = $4594;
        this._$4591 = $4591;
        this._$4588 = $4592;
        this._$4589 = $4593;
        (this._$4372 = new MediaTracker(this)).addImage(this._$4591, 0);
        this._$4372.addImage(this._$4588, 1);
        this._$4372.addImage(this._$4589, 2);
        try {
            this._$4372.waitForAll();
        }
        catch (Exception ex) {}
        this._$4591 = this._$4591;
        this._$4588 = this._$4588;
        this._$4589 = this._$4589;
        this._$4590 = this._$4588;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    final int x = mouseEvent.getX();
                    if (x >= 4 && x <= 80) {
                        VolumeControl.this.bar_mouseDsp(true);
                        VolumeControl.this.barPos_change(x - 4);
                        VolumeControl.this._$4595 = true;
                    }
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (VolumeControl.this._$4595) {
                    VolumeControl.this.bar_mouseDsp(false);
                    VolumeControl.this._$4595 = false;
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumeControl.this._$4305.curDCur.equals(VolumeControl.this._$4305.curD)) {
                    VolumeControl.this.setCursor(VolumeControl.this._$4305.curH);
                }
                else {
                    VolumeControl.this.setCursor(VolumeControl.this._$4305.curDCur);
                }
                if (VolumeControl.this._$4305.controler != null) {
                    VolumeControl.this._$4305.controler.toFront();
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    int x = mouseEvent.getX();
                    if (x <= 4) {
                        x = 4;
                    }
                    if (x >= 80) {
                        x = 80;
                    }
                    VolumeControl.this.barPos_change(x - 4);
                }
            }
        });
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this._$4591, 0, 5, this);
        graphics.drawImage(this._$4590, this._$4592, 1, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void barPos_change(final int $4592) {
        this._$4593 = this._$4592;
        this._$4592 = $4592;
        this.repaint();
    }
    
    public void bar_mouseDsp(final boolean b) {
        if (b) {
            this._$4590 = this._$4589;
        }
        else {
            this._$4590 = this._$4588;
        }
        this.repaint();
    }
    
    public void reloadIcon(final Color $1495) {
        this._$1495 = $1495;
        this._$4591 = this._$4591;
        this._$4589 = this._$4589;
        this._$4588 = this._$4588;
    }
}

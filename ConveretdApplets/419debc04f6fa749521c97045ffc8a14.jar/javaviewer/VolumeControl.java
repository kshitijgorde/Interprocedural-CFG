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
    private Image _$11170;
    private Image _$11178;
    private Image _$11185;
    private Image _$11189;
    private MediaTracker _$10615;
    private int _$11195;
    private Color _$3182;
    private int _$11206;
    private boolean _$11215;
    private boolean _$11224;
    private Viewer _$1008;
    
    public VolumeControl(final Image $11189, final Image $11190, final Image $11191, final Color $11192, final Viewer $11193) {
        this._$11195 = 41;
        this._$11206 = 0;
        this._$11215 = false;
        this._$11224 = false;
        this._$1008 = $11193;
        this._$3182 = $11192;
        this._$11189 = $11189;
        this._$11170 = $11190;
        this._$11178 = $11191;
        (this._$10615 = new MediaTracker(this)).addImage(this._$11189, 0);
        this._$10615.addImage(this._$11170, 1);
        this._$10615.addImage(this._$11178, 2);
        try {
            this._$10615.waitForAll();
        }
        catch (Exception ex) {}
        this._$11189 = this._$11189;
        this._$11170 = this._$11170;
        this._$11178 = this._$11178;
        this._$11185 = this._$11170;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
                    final int x = mouseEvent.getX();
                    if (x >= 4 && x <= 80) {
                        VolumeControl.this.bar_mouseDsp(true);
                        VolumeControl.this.barPos_change(x - 4);
                        VolumeControl.this._$11224 = true;
                    }
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (VolumeControl.this._$11224) {
                    VolumeControl.this.bar_mouseDsp(false);
                    VolumeControl.this._$11224 = false;
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                if (VolumeControl.this._$1008.curDCur.equals(VolumeControl.this._$1008.curD)) {
                    VolumeControl.this.setCursor(VolumeControl.this._$1008.curH);
                }
                else {
                    VolumeControl.this.setCursor(VolumeControl.this._$1008.curDCur);
                }
                if (VolumeControl.this._$1008.controler != null) {
                    VolumeControl.this._$1008.controler.toFront();
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
        graphics.drawImage(this._$11189, 0, 5, this);
        graphics.drawImage(this._$11185, this._$11195, 1, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void barPos_change(final int $11195) {
        this._$11206 = this._$11195;
        this._$11195 = $11195;
        this.repaint();
    }
    
    public void bar_mouseDsp(final boolean b) {
        if (b) {
            this._$11185 = this._$11178;
        }
        else {
            this._$11185 = this._$11170;
        }
        this.repaint();
    }
    
    public void reloadIcon(final Color $3182) {
        this._$3182 = $3182;
        this._$11189 = this._$11189;
        this._$11178 = this._$11178;
        this._$11170 = this._$11170;
    }
}

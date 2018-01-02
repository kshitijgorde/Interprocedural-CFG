// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class ZoomControler extends Canvas implements MouseListener
{
    protected double zoomRatio;
    protected double digitalZoomRatio;
    int opticalZoomBarWidth;
    int opticalZoomBarHeight;
    int opticalZoomSideWidth;
    int opticalZoomSideHeight;
    int digitalZoomBarWidth;
    int digitalZoomBarHeight;
    int digitalZoomSideWidth;
    int digitalZoomSideHeight;
    int zoomIndexWidth;
    int zoomIndexHeight;
    int totalLength;
    int fullLength;
    int height;
    double opticalZoomBarRatio;
    double opticalZoomSideRatio;
    double digitalZoomBarRatio;
    double digitalZoomSideRatio;
    double zoomIndexRatio;
    private int _$5093;
    private int _$5094;
    private int _$5095;
    private int _$5096;
    protected boolean zooming;
    private int _$4404;
    private int _$4405;
    private int _$5098;
    private Viewer _$4305;
    final int opticalMin = 0;
    final int opticalMax = 16384;
    final int digitalMin = 16384;
    final int digitalMax = 31424;
    int gapBP;
    
    public ZoomControler(final Viewer $4305) {
        this.zoomRatio = 0.0;
        this.digitalZoomRatio = 0.0;
        this._$4404 = 0;
        this._$4405 = 0;
        this._$5098 = 0;
        this.gapBP = 2;
        this._$4305 = $4305;
        this.setBackground($4305.pictFrameColor);
        while (true) {
            if ($4305.opticalZoomBar.getWidth(this) != -1) {
                if ($4305.opticalZoomBar.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($4305.opticalZoomWide.getWidth(this) != -1) {
                if ($4305.opticalZoomWide.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($4305.opticalZoomTele.getWidth(this) != -1) {
                if ($4305.opticalZoomTele.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($4305.digitalZoomBar.getWidth(this) != -1) {
                if ($4305.digitalZoomBar.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($4305.digitalZoomTele.getWidth(this) != -1) {
                if ($4305.digitalZoomTele.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($4305.zoomIndex.getWidth(this) != -1) {
                if ($4305.zoomIndex.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        this.opticalZoomBarWidth = $4305.opticalZoomBar.getWidth(this);
        this.opticalZoomBarHeight = $4305.opticalZoomBar.getHeight(this);
        this.opticalZoomSideWidth = $4305.opticalZoomWide.getWidth(this);
        this.opticalZoomSideHeight = $4305.opticalZoomWide.getHeight(this);
        this.digitalZoomBarWidth = $4305.digitalZoomBar.getWidth(this);
        this.digitalZoomBarHeight = $4305.digitalZoomBar.getHeight(this);
        this.digitalZoomSideWidth = $4305.digitalZoomTele.getWidth(this);
        this.digitalZoomSideHeight = $4305.digitalZoomTele.getHeight(this);
        this.zoomIndexWidth = $4305.zoomIndex.getWidth(this);
        this.zoomIndexHeight = $4305.zoomIndex.getHeight(this);
        this.totalLength = this.opticalZoomBarWidth + this.opticalZoomSideWidth * 2 + this.gapBP * 2;
        if ($4305.camera.getZoomType().equals("full")) {
            this.totalLength = this.totalLength + this.digitalZoomBarWidth + this.digitalZoomSideWidth + this.gapBP * 2;
            this.fullLength = this.totalLength;
        }
        else {
            this.fullLength = this.totalLength + this.digitalZoomBarWidth + this.digitalZoomSideWidth + this.gapBP * 2;
        }
        this.height = this.zoomIndexHeight + this.opticalZoomSideHeight;
        this._$5098 = (int)((this.opticalZoomSideHeight - this.opticalZoomBarHeight) / 2.0);
        this.opticalZoomBarRatio = this.opticalZoomBarWidth / this.totalLength;
        this.opticalZoomSideRatio = this.opticalZoomSideWidth / this.totalLength;
        this.digitalZoomBarRatio = this.digitalZoomBarWidth / this.totalLength;
        this.digitalZoomSideRatio = this.digitalZoomSideWidth / this.totalLength;
        this.zoomIndexRatio = this.zoomIndexWidth / this.totalLength;
        this.addMouseListener(this);
    }
    
    public void setColor(final Color background) {
        this.setBackground(background);
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this._$4305.curDCur.equals(this._$4305.curD) && !this._$4305.imageThread.digitalZoom) {
            this.setCursor(this._$4305.curH);
        }
        else {
            this.setCursor(this._$4305.curDCur);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (this._$4305.imageThread.digitalZoom) {
                return;
            }
            this.zoomClicked(mouseEvent);
        }
    }
    
    public void paint(final Graphics graphics) {
        this._$4404 = 0;
        this._$4405 = 0;
        final int n = this.opticalZoomSideWidth + this.gapBP;
        final int n2 = n + this.opticalZoomBarWidth;
        final int n3 = n2 + this.opticalZoomSideWidth + this.gapBP * 2;
        final int n4 = n3 + this.digitalZoomBarWidth;
        final double n5 = this.getWidth() / this.totalLength;
        final double n6 = 1.0;
        this._$5093 = (int)(n * n6);
        this._$5094 = (int)(n2 * n6);
        this._$5095 = (int)(n3 * n6);
        this._$5096 = (int)(n4 * n6);
        final double n7 = this.getWidth() / this.totalLength;
        final double n8 = 1.0;
        this.setSize(this.getWidth(), this.getWidth() * this.height / this.totalLength);
        graphics.setColor(this._$4305.pictFrameColor);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(this._$4305.opticalZoomWide, this._$4404, this._$4405, (int)(this.opticalZoomSideWidth * n8), (int)(this.opticalZoomSideHeight * n8), this);
        graphics.drawImage(this._$4305.opticalZoomBar, this._$4404 + this._$5093, this._$4405 + this._$5098, (int)(this.opticalZoomBarWidth * n8), (int)(this.opticalZoomBarHeight * n8), this);
        graphics.drawImage(this._$4305.opticalZoomTele, this._$4404 + this._$5094 + this.gapBP, this._$4405, (int)(this.opticalZoomSideWidth * n8), (int)(this.opticalZoomSideHeight * n8), this);
        if (this._$4305.camera.getZoomType().equals("full")) {
            graphics.drawImage(this._$4305.digitalZoomBar, this._$4404 + this._$5095, this._$4405 + this._$5098, (int)(this.digitalZoomBarWidth * n8), (int)(this.digitalZoomBarHeight * n8), this);
            graphics.drawImage(this._$4305.digitalZoomTele, this._$4404 + this._$5096 + this.gapBP, this._$4405, (int)(this.digitalZoomSideWidth * n8), (int)(this.digitalZoomSideHeight * n8), this);
        }
        int n9;
        if (this.zoomRatio == 0.0) {
            n9 = this._$4404 + (int)(this.opticalZoomSideWidth * n8 / 2.0) + 1;
            graphics.drawImage(this._$4305.zoomIndex, this._$4404 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$4405 + (int)(this.digitalZoomSideHeight * n8) + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else if (this.zoomRatio == 1.0) {
            n9 = this._$4404 + this._$5094 + this.gapBP + (int)(this.opticalZoomSideWidth * n8 / 2.0) + 1;
            graphics.drawImage(this._$4305.zoomIndex, this._$4404 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$4405 + (int)(this.digitalZoomSideHeight * n8) + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else if (this.zoomRatio > 0.0) {
            n9 = this._$4404 + this._$5093 + (int)(this.opticalZoomBarWidth * n8 * this.zoomRatio) + 1;
            graphics.drawImage(this._$4305.zoomIndex, this._$4404 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$4405 + (int)(this.opticalZoomBarHeight * n8) + this._$5098 + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else if (this.digitalZoomRatio == 1.0) {
            n9 = this._$4404 + this._$5096 + this.gapBP + (int)(this.digitalZoomSideWidth * n8 / 2.0) + 1;
            graphics.drawImage(this._$4305.zoomIndex, this._$4404 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$4405 + (int)(this.digitalZoomSideHeight * n8) + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else {
            n9 = this._$4404 + this._$5095 + (int)(this.digitalZoomBarWidth * n8 * this.digitalZoomRatio) + 1;
            graphics.drawImage(this._$4305.zoomIndex, this._$4404 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$4405 + (int)(this.opticalZoomBarHeight * n8) + this._$5098 + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        this._$4305.logger.print("zoomIndexPos " + n9);
        this.zooming = false;
    }
    
    public double getRatio() {
        return this.height / this.fullLength;
    }
    
    public void zoomClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this._$4305.logger.print("zoomClicked x=" + x + "/y=" + y);
        this._$4305.logger.print("zoomClicked ob=" + this._$5093 + "/oe=" + this._$5094);
        this._$4305.logger.print("zoomClicked db=" + this._$5095 + "/de=" + this._$5096);
        this._$4305.logger.print("zoomClicked orgY=" + this._$4405 + "/Height=" + this.opticalZoomBarHeight);
        if (y >= this._$4405 && y < this._$4405 + this.opticalZoomBarHeight) {
            if (x >= this._$5093 && x <= this._$5094) {
                this.zoomRatio = (x - this._$5093) / (this._$5094 - this._$5093);
                this.digitalZoomRatio = -1.0;
                this.zooming = true;
            }
            else if (x >= this._$5095 && x <= this._$5096) {
                this.digitalZoomRatio = (x - this._$5095) / (this._$5096 - this._$5095);
                this.zoomRatio = -1.0;
                this.zooming = true;
            }
        }
        if (y >= this._$4405 && y < this._$4405 + this.opticalZoomSideHeight) {
            if (x >= 0 && x <= this._$5093) {
                this.zoomRatio = 0.0;
                this.digitalZoomRatio = -1.0;
                this.zooming = true;
            }
            else if (x >= this._$5094 && x <= this._$5095) {
                this.zoomRatio = 1.0;
                this.digitalZoomRatio = -1.0;
                this.zooming = true;
            }
            else if (x >= this._$5096) {
                this.digitalZoomRatio = 1.0;
                this.zoomRatio = -1.0;
                this.zooming = true;
            }
        }
        int n;
        if (this.zoomRatio >= 0.0) {
            n = (int)(16384.0 * this.zoomRatio + 0.0);
        }
        else {
            n = (int)(15040.0 * this.digitalZoomRatio + 16384.0);
        }
        this._$686("/command/ptzf.cgi", "AbsoluteZoom=" + Integer.toHexString(n), false);
        this._$4305.controlGUI.setSelectedPresetPosition(0);
        this._$4305.logger.print("zoomClicked " + this.zoomRatio + "/" + this.digitalZoomRatio + "-" + n);
    }
    
    public boolean getZooming() {
        return this.zooming;
    }
    
    public void resetZooming() {
        this.zooming = false;
    }
    
    public void setZoomIndex(final int n) {
        if (n <= 16384) {
            this.zoomRatio = (n - 0) / 16384.0;
            this.digitalZoomRatio = -1.0;
        }
        else {
            this.digitalZoomRatio = (n - 16384) / 15040.0;
            this.zoomRatio = -1.0;
        }
        this._$4305.logger.print("zoomChanged " + this.zoomRatio + "/" + this.digitalZoomRatio + "-" + n);
        this.repaint();
    }
    
    public int getWidth() {
        return this.totalLength;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    private void _$686(final String s, final String s2, final boolean b) {
        while (!this._$4305.commandSend.ready) {
            try {
                Thread.sleep(0L, 1);
            }
            catch (Exception ex) {}
        }
        synchronized (this._$4305.commandSend) {
            this._$4305.commandSend.setCommand(s, s2);
            this._$4305.commandSend.notify();
        }
    }
}

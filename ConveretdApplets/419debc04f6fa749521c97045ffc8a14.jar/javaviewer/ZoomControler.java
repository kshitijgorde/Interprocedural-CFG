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
    private int _$170426;
    private int _$170442;
    private int _$211417;
    private int _$211433;
    protected boolean zooming;
    private int _$169343;
    private int _$169347;
    private int _$170463;
    private Viewer _$1008;
    final int opticalMin = 0;
    final int opticalMax = 16384;
    final int digitalMin = 16384;
    final int digitalMax = 31424;
    int gapBP;
    
    public ZoomControler(final Viewer $1008) {
        this.zoomRatio = 0.0;
        this.digitalZoomRatio = 0.0;
        this._$169343 = 0;
        this._$169347 = 0;
        this._$170463 = 0;
        this.gapBP = 2;
        this._$1008 = $1008;
        this.setBackground($1008.pictFrameColor);
        while (true) {
            if ($1008.opticalZoomBar.getWidth(this) != -1) {
                if ($1008.opticalZoomBar.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($1008.opticalZoomWide.getWidth(this) != -1) {
                if ($1008.opticalZoomWide.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($1008.opticalZoomTele.getWidth(this) != -1) {
                if ($1008.opticalZoomTele.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($1008.digitalZoomBar.getWidth(this) != -1) {
                if ($1008.digitalZoomBar.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($1008.digitalZoomTele.getWidth(this) != -1) {
                if ($1008.digitalZoomTele.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        while (true) {
            if ($1008.zoomIndex.getWidth(this) != -1) {
                if ($1008.zoomIndex.getHeight(this) == -1) {
                    continue;
                }
                break;
            }
        }
        this.opticalZoomBarWidth = $1008.opticalZoomBar.getWidth(this);
        this.opticalZoomBarHeight = $1008.opticalZoomBar.getHeight(this);
        this.opticalZoomSideWidth = $1008.opticalZoomWide.getWidth(this);
        this.opticalZoomSideHeight = $1008.opticalZoomWide.getHeight(this);
        this.digitalZoomBarWidth = $1008.digitalZoomBar.getWidth(this);
        this.digitalZoomBarHeight = $1008.digitalZoomBar.getHeight(this);
        this.digitalZoomSideWidth = $1008.digitalZoomTele.getWidth(this);
        this.digitalZoomSideHeight = $1008.digitalZoomTele.getHeight(this);
        this.zoomIndexWidth = $1008.zoomIndex.getWidth(this);
        this.zoomIndexHeight = $1008.zoomIndex.getHeight(this);
        this.totalLength = this.opticalZoomBarWidth + this.opticalZoomSideWidth * 2 + this.gapBP * 2;
        if ($1008.camera.getZoomType().equals("full")) {
            this.totalLength = this.totalLength + this.digitalZoomBarWidth + this.digitalZoomSideWidth + this.gapBP * 2;
            this.fullLength = this.totalLength;
        }
        else {
            this.fullLength = this.totalLength + this.digitalZoomBarWidth + this.digitalZoomSideWidth + this.gapBP * 2;
        }
        this.height = this.zoomIndexHeight + this.opticalZoomSideHeight;
        this._$170463 = (int)((this.opticalZoomSideHeight - this.opticalZoomBarHeight) / 2.0);
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
        if (this._$1008.curDCur.equals(this._$1008.curD) && !this._$1008.imageThread.digitalZoom) {
            this.setCursor(this._$1008.curH);
        }
        else {
            this.setCursor(this._$1008.curDCur);
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
            if (this._$1008.imageThread.digitalZoom) {
                return;
            }
            this.zoomClicked(mouseEvent);
        }
    }
    
    public void paint(final Graphics graphics) {
        this._$169343 = 0;
        this._$169347 = 0;
        final int n = this.opticalZoomSideWidth + this.gapBP;
        final int n2 = n + this.opticalZoomBarWidth;
        final int n3 = n2 + this.opticalZoomSideWidth + this.gapBP * 2;
        final int n4 = n3 + this.digitalZoomBarWidth;
        final double n5 = this.getWidth() / this.totalLength;
        final double n6 = 1.0;
        this._$170426 = (int)(n * n6);
        this._$170442 = (int)(n2 * n6);
        this._$211417 = (int)(n3 * n6);
        this._$211433 = (int)(n4 * n6);
        final double n7 = this.getWidth() / this.totalLength;
        final double n8 = 1.0;
        this.setSize(this.getWidth(), this.getWidth() * this.height / this.totalLength);
        graphics.setColor(this._$1008.pictFrameColor);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(this._$1008.opticalZoomWide, this._$169343, this._$169347, (int)(this.opticalZoomSideWidth * n8), (int)(this.opticalZoomSideHeight * n8), this);
        graphics.drawImage(this._$1008.opticalZoomBar, this._$169343 + this._$170426, this._$169347 + this._$170463, (int)(this.opticalZoomBarWidth * n8), (int)(this.opticalZoomBarHeight * n8), this);
        graphics.drawImage(this._$1008.opticalZoomTele, this._$169343 + this._$170442 + this.gapBP, this._$169347, (int)(this.opticalZoomSideWidth * n8), (int)(this.opticalZoomSideHeight * n8), this);
        if (this._$1008.camera.getZoomType().equals("full")) {
            graphics.drawImage(this._$1008.digitalZoomBar, this._$169343 + this._$211417, this._$169347 + this._$170463, (int)(this.digitalZoomBarWidth * n8), (int)(this.digitalZoomBarHeight * n8), this);
            graphics.drawImage(this._$1008.digitalZoomTele, this._$169343 + this._$211433 + this.gapBP, this._$169347, (int)(this.digitalZoomSideWidth * n8), (int)(this.digitalZoomSideHeight * n8), this);
        }
        int n9;
        if (this.zoomRatio == 0.0) {
            n9 = this._$169343 + (int)(this.opticalZoomSideWidth * n8 / 2) + 1;
            graphics.drawImage(this._$1008.zoomIndex, this._$169343 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$169347 + (int)(this.digitalZoomSideHeight * n8) + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else if (this.zoomRatio == 1.0) {
            n9 = this._$169343 + this._$170442 + this.gapBP + (int)(this.opticalZoomSideWidth * n8 / 2) + 1;
            graphics.drawImage(this._$1008.zoomIndex, this._$169343 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$169347 + (int)(this.digitalZoomSideHeight * n8) + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else if (this.zoomRatio > 0) {
            n9 = this._$169343 + this._$170426 + (int)(this.opticalZoomBarWidth * n8 * this.zoomRatio) + 1;
            graphics.drawImage(this._$1008.zoomIndex, this._$169343 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$169347 + (int)(this.opticalZoomBarHeight * n8) + this._$170463 + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else if (this.digitalZoomRatio == 1.0) {
            n9 = this._$169343 + this._$211433 + this.gapBP + (int)(this.digitalZoomSideWidth * n8 / 2) + 1;
            graphics.drawImage(this._$1008.zoomIndex, this._$169343 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$169347 + (int)(this.digitalZoomSideHeight * n8) + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        else {
            n9 = this._$169343 + this._$211417 + (int)(this.digitalZoomBarWidth * n8 * this.digitalZoomRatio) + 1;
            graphics.drawImage(this._$1008.zoomIndex, this._$169343 + (int)(n9 - this.zoomIndexWidth * n8 / 2.0), this._$169347 + (int)(this.opticalZoomBarHeight * n8) + this._$170463 + 1, (int)(this.zoomIndexWidth * n8), (int)(this.zoomIndexHeight * n8), this);
        }
        this._$1008.logger.print("zoomIndexPos ".concat(String.valueOf(String.valueOf(n9))));
        this.zooming = false;
    }
    
    public double getRatio() {
        return this.height / this.fullLength;
    }
    
    public void zoomClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("zoomClicked x=").append(x).append("/y=").append(y))));
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("zoomClicked ob=").append(this._$170426).append("/oe=").append(this._$170442))));
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("zoomClicked db=").append(this._$211417).append("/de=").append(this._$211433))));
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("zoomClicked orgY=").append(this._$169347).append("/Height=").append(this.opticalZoomBarHeight))));
        if (y >= this._$169347 && y < this._$169347 + this.opticalZoomBarHeight) {
            if (x >= this._$170426 && x <= this._$170442) {
                this.zoomRatio = (x - this._$170426) / (this._$170442 - this._$170426);
                this.digitalZoomRatio = -1.0;
                this.zooming = true;
            }
            else if (x >= this._$211417 && x <= this._$211433) {
                this.digitalZoomRatio = (x - this._$211417) / (this._$211433 - this._$211417);
                this.zoomRatio = -1.0;
                this.zooming = true;
            }
        }
        if (y >= this._$169347 && y < this._$169347 + this.opticalZoomSideHeight) {
            if (x >= 0 && x <= this._$170426) {
                this.zoomRatio = 0.0;
                this.digitalZoomRatio = -1.0;
                this.zooming = true;
            }
            else if (x >= this._$170442 && x <= this._$211417) {
                this.zoomRatio = 1.0;
                this.digitalZoomRatio = -1.0;
                this.zooming = true;
            }
            else if (x >= this._$211433) {
                this.digitalZoomRatio = 1.0;
                this.zoomRatio = -1.0;
                this.zooming = true;
            }
        }
        int n;
        if (this.zoomRatio >= 0.0) {
            n = (int)(16384 * this.zoomRatio + 0);
        }
        else {
            n = (int)(15040 * this.digitalZoomRatio + 16384);
        }
        this._$5281("/command/ptzf.cgi", "AbsoluteZoom=".concat(String.valueOf(String.valueOf(Integer.toHexString(n)))), false);
        this._$1008.controlGUI.setSelectedPresetPosition(0);
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("zoomClicked ").append(this.zoomRatio).append("/").append(this.digitalZoomRatio).append("-").append(n))));
    }
    
    public boolean getZooming() {
        return this.zooming;
    }
    
    public void resetZooming() {
        this.zooming = false;
    }
    
    public void setZoomIndex(final int n) {
        if (n <= 16384) {
            this.zoomRatio = (n - 0) / 16384;
            this.digitalZoomRatio = -1.0;
        }
        else {
            this.digitalZoomRatio = (n - 16384) / 15040;
            this.zoomRatio = -1.0;
        }
        this._$1008.logger.print(String.valueOf(String.valueOf(new StringBuffer("zoomChanged ").append(this.zoomRatio).append("/").append(this.digitalZoomRatio).append("-").append(n))));
        this.repaint();
    }
    
    public int getWidth() {
        return this.totalLength;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    private void _$5281(final String s, final String s2, final boolean b) {
        while (!this._$1008.commandSend.ready) {
            try {
                Thread.sleep(0L, 1);
            }
            catch (Exception ex) {}
        }
        synchronized (this._$1008.commandSend) {
            this._$1008.commandSend.setCommand(s, s2);
            this._$1008.commandSend.notify();
        }
        // monitorexit(this._$1008.commandSend)
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zx.peripheral;

import java.awt.image.ImageObserver;
import com.tn.zx.ZxExpansionPort;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import com.tn.zx.ZxIoHandler;

public class ZxPrinter implements ZxIoHandler, ZxPrinterConverter
{
    private int ivX;
    private Component ivTarget;
    private Graphics ivTargetGraphics;
    private Color ivColorInk;
    private Color ivColorPaper;
    private Image ivPaperImage;
    private Graphics ivPaperGraphics;
    private ZxExpansionPort ivConnectedSpectrum;
    public static final int PAPER_LENGTH = 512;
    
    public ZxPrinter() {
        this.ivX = 0;
        this.ivColorPaper = Color.gray;
        this.ivColorInk = Color.black;
    }
    
    public void connectToZXSpectrum(final ZxExpansionPort pZXSpectrum) {
        this.ivConnectedSpectrum = pZXSpectrum;
        for (int i = 0; i < 256; ++i) {
            if ((i & 0x4) == 0x0) {
                pZXSpectrum.addIOHandler(this, i);
            }
        }
    }
    
    public void disconnectFromZXSpectrum() {
        if (this.ivConnectedSpectrum != null) {
            this.ivConnectedSpectrum.removeIOHandler(this);
            this.ivConnectedSpectrum = null;
        }
    }
    
    private Graphics getPaperGraphics() {
        if (this.ivPaperGraphics == null) {
            this.ivPaperGraphics = this.getPaperImage().getGraphics();
        }
        return this.ivPaperGraphics;
    }
    
    private Image getPaperImage() {
        if (this.ivPaperImage == null) {
            this.ivPaperImage = this.ivTarget.createImage(256, 512);
        }
        return this.ivPaperImage;
    }
    
    public ZxPrinterStatus getStatus() {
        return new ZxPrinterStatus();
    }
    
    @Override
    public int readIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x4) == 0x0) {
            return this.readPortFB();
        }
        return pValue;
    }
    
    @Override
    public void readOpcode1(final int pAddress) {
    }
    
    @Override
    public int readOpcode2(final int pAddress, final int pOpcode) {
        return pOpcode;
    }
    
    private int readPortFB() {
        int result = 60;
        ++this.ivX;
        if (this.ivX >= 260) {
            this.ivX = 0;
            this.scrollPaperImage();
            this.refresh();
        }
        if (this.ivX >= 256) {
            result |= 0x80;
        }
        else {
            result |= 0x1;
        }
        return result;
    }
    
    @Override
    public void refresh() {
        final int sw = 256;
        final int sh = 512;
        final int dw = this.ivTarget.getWidth();
        final int dh = this.ivTarget.getHeight();
        final int dx = (dw - sw) / 2;
        final int dy = dh - sh;
        if (this.ivTargetGraphics != null) {
            this.ivTargetGraphics.drawImage(this.ivPaperImage, dx, dy, null);
        }
    }
    
    private void scrollPaperImage() {
        this.getPaperGraphics().copyArea(0, 0, 256, 512, 0, -1);
    }
    
    public void setStatus(final ZxPrinterStatus pStatus) {
    }
    
    @Override
    public void setTarget(final Component pComponent) {
        this.ivTarget = pComponent;
        this.ivTargetGraphics = pComponent.getGraphics();
    }
    
    @Override
    public void writeIO(final int pAddress, final int pValue) {
        if ((pAddress & 0x4) == 0x0) {
            this.writePortFB(pValue);
        }
    }
    
    private void writePortFB(final int pValue) {
        if ((pValue & 0x4) == 0x0 && this.ivX < 256) {
            if ((pValue & 0x80) != 0x0) {
                this.getPaperGraphics().setColor(this.ivColorInk);
            }
            else {
                this.getPaperGraphics().setColor(this.ivColorPaper);
            }
            this.getPaperGraphics().fillRect(this.ivX, 511, 1, 1);
        }
    }
}

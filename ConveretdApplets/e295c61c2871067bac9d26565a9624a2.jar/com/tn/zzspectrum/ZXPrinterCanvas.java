// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zzspectrum;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import com.tn.zx.peripheral.ZxPrinterConverter;
import java.awt.event.ComponentListener;
import java.awt.Canvas;

public class ZXPrinterCanvas extends Canvas implements ComponentListener
{
    ZxPrinterConverter ivPrinterConverter;
    
    public ZXPrinterCanvas() {
        this.addComponentListener(this);
    }
    
    @Override
    public void componentHidden(final ComponentEvent e) {
    }
    
    @Override
    public void componentMoved(final ComponentEvent e) {
    }
    
    @Override
    public void componentResized(final ComponentEvent e) {
    }
    
    @Override
    public void componentShown(final ComponentEvent e) {
    }
    
    public ZxPrinterConverter getPrinterConverter() {
        return this.ivPrinterConverter;
    }
    
    @Override
    public void paint(final Graphics pGraphics) {
        super.paint(pGraphics);
        if (this.ivPrinterConverter != null) {
            this.ivPrinterConverter.setTarget(this);
            this.ivPrinterConverter.refresh();
        }
    }
    
    public void setPrinterConverter(final ZxPrinterConverter pPrinterConverter) {
        (this.ivPrinterConverter = pPrinterConverter).setTarget(this);
    }
}

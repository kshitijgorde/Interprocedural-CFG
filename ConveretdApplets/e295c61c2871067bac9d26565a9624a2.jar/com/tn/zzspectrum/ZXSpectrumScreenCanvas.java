// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zzspectrum;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import com.tn.zxspectrum.ZxSpectrumScreenConverter;
import java.awt.event.ComponentListener;
import java.awt.Canvas;

public class ZXSpectrumScreenCanvas extends Canvas implements ComponentListener
{
    ZxSpectrumScreenConverter ivScreenConverter;
    private boolean ivSupportResize;
    
    public ZXSpectrumScreenCanvas() {
        this.ivSupportResize = true;
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
        if (this.ivSupportResize && this.ivScreenConverter != null) {
            this.ivScreenConverter.setTargetWindow(0, 0, this.getSize().width, this.getSize().height);
        }
    }
    
    @Override
    public void componentShown(final ComponentEvent e) {
        if (this.ivScreenConverter != null) {
            this.ivScreenConverter.setTargetWindow(0, 0, this.getSize().width, this.getSize().height);
        }
    }
    
    public ZxSpectrumScreenConverter getScreenConverter() {
        return this.ivScreenConverter;
    }
    
    @Override
    public void paint(final Graphics pGraphics) {
        super.paint(pGraphics);
        if (this.ivScreenConverter != null) {
            this.ivScreenConverter.refresh();
        }
    }
    
    public void setScreenConverter(final ZxSpectrumScreenConverter pScreenConverter) {
        (this.ivScreenConverter = pScreenConverter).setTargetGraphics(this.getGraphics());
        this.ivScreenConverter.setTargetWindow(0, 0, this.getSize().width, this.getSize().height);
    }
    
    public void setSupportResize(final boolean pSupportResize) {
        this.ivSupportResize = pSupportResize;
    }
}

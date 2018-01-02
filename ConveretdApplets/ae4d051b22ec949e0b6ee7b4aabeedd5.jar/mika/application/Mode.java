// 
// Decompiled by Procyon v0.5.30
// 

package mika.application;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

public abstract class Mode extends Panel
{
    int m_iBufferCnt;
    Image[] m_buffer;
    int m_currentPage;
    Color m_colorBackground;
    ModeCommunicationObject m_modeCommunicationObject;
    
    public Mode() {
        this.m_iBufferCnt = 1;
        this.m_currentPage = 0;
        this.m_colorBackground = new Color(0.25f, 0.25f, 0.25f);
        this.m_modeCommunicationObject = null;
    }
    
    public abstract void init();
    
    public abstract boolean isInitDone();
    
    public abstract void repositionElements();
    
    public void destroy() {
    }
    
    public abstract int getWidth();
    
    public abstract int getHeight();
    
    public void createOffscreenBuffer(final int iBufferCnt) {
        this.m_iBufferCnt = iBufferCnt;
        this.m_buffer = new Image[this.m_iBufferCnt];
        for (int i = 0; i < this.m_iBufferCnt; ++i) {
            this.m_buffer[i] = this.createImage(this.getWidth(), this.getHeight());
            final Graphics graphics = this.m_buffer[i].getGraphics();
            graphics.setColor(this.m_colorBackground);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
    
    public Image getOffscreenBuffer() {
        final int n = (this.m_currentPage + 1) % this.m_iBufferCnt;
        if (this.m_buffer != null) {
            return this.m_buffer[n];
        }
        return null;
    }
    
    public Image getScreenBuffer() {
        if (this.m_buffer != null) {
            return this.m_buffer[this.m_currentPage];
        }
        return null;
    }
    
    public void swapBuffer() {
        this.m_currentPage = (this.m_currentPage + 1) % this.m_iBufferCnt;
    }
    
    public ModeCommunicationObject getModeCommunicationObject() {
        return this.m_modeCommunicationObject;
    }
    
    public void setModeCommunicationObject(final ModeCommunicationObject modeCommunicationObject) {
        this.m_modeCommunicationObject = modeCommunicationObject;
    }
}

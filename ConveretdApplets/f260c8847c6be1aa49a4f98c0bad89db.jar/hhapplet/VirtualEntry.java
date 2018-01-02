// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import XMLConsumer.IEntry;

class VirtualEntry implements IEntry
{
    private int m_nPos;
    private IEntry m_realEntry;
    private boolean m_bHighLight;
    private boolean m_bSelect;
    private boolean m_bNeedAction;
    private IActionSink m_accepter;
    
    public int getPrevSpan() {
        if (this.m_realEntry != null) {
            return this.m_realEntry.getPrevSpan();
        }
        return 0;
    }
    
    public VirtualEntry(final int nPos) {
        this.m_nPos = nPos;
    }
    
    public boolean isMainEntry() {
        return this.m_realEntry == null || this.m_realEntry.isMainEntry();
    }
    
    public void select(final boolean bSelect) {
        if (this.m_realEntry != null) {
            this.m_realEntry.select(bSelect);
            return;
        }
        this.m_bSelect = bSelect;
    }
    
    public String getName() {
        if (this.m_realEntry != null) {
            return this.m_realEntry.getName();
        }
        return "";
    }
    
    public void highLight(final boolean bHighLight) {
        if (this.m_realEntry != null) {
            this.m_realEntry.highLight(bHighLight);
            return;
        }
        this.m_bHighLight = bHighLight;
    }
    
    public void action(final IActionSink accepter) {
        if (this.m_realEntry != null) {
            this.m_realEntry.action(accepter);
            return;
        }
        this.m_bNeedAction = true;
        this.m_accepter = accepter;
    }
    
    public int getNextSpan() {
        if (this.m_realEntry != null) {
            return this.m_realEntry.getNextSpan();
        }
        return 0;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final Color color, final Image image) {
        if (this.m_realEntry != null) {
            this.m_realEntry.display(graphics, n, n2, color, image);
        }
    }
    
    public int getWidth(final Graphics graphics) {
        if (this.m_realEntry != null) {
            return this.m_realEntry.getWidth(graphics);
        }
        return 0;
    }
    
    public void fillReal(final IEntry realEntry) {
        (this.m_realEntry = realEntry).select(this.m_bSelect);
        realEntry.highLight(this.m_bHighLight);
        if (this.m_bNeedAction) {
            realEntry.action(this.m_accepter);
        }
    }
    
    public int getPos() {
        return this.m_nPos;
    }
}

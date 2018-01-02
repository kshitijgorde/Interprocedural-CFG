// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import XMLConsumer.IEntry;
import java.util.Vector;

class RealItemBlock
{
    private Vector m_block;
    private int m_nTop;
    private int m_nNum;
    private String m_sFirstKey;
    private String m_sLastKey;
    private int m_nWidth;
    private boolean m_bNeedCalWidth;
    
    public int getNum() {
        return this.m_nNum;
    }
    
    public RealItemBlock(final int nTop, final BlockContainer blockContainer) {
        this.m_block = blockContainer.getBlock();
        this.m_sFirstKey = blockContainer.getFirstKey();
        this.m_sLastKey = blockContainer.getLastKey();
        this.m_nTop = nTop;
        this.m_nNum = this.m_block.size();
        this.m_bNeedCalWidth = true;
    }
    
    public IEntry getEntry(final int n) {
        final int n2 = n - this.m_nTop;
        if (n2 < 0 || n2 >= this.m_nNum) {
            return null;
        }
        return (IEntry)this.m_block.elementAt(n2);
    }
    
    public int getTop() {
        return this.m_nTop;
    }
    
    public void posChange(final int n) {
        this.m_nTop += n;
    }
    
    protected int calWidth(final Graphics graphics) {
        int n = 0;
        for (int i = 0; i < this.m_block.size(); ++i) {
            final int width = this.m_block.elementAt(i).getWidth(graphics);
            if (width > n) {
                n = width;
            }
        }
        return n;
    }
    
    public int getClosestEntryId(final String s) {
        if (Language.compare(this.m_sFirstKey, s) > 0) {
            return -1;
        }
        if (Language.compare(this.m_sLastKey, s) < 0) {
            return this.m_nNum;
        }
        int n = 0;
        int i = 0;
        IEntry entry;
        do {
            entry = this.m_block.elementAt(i);
            if (Language.compare(entry.getName(), s) >= 0 && entry.isMainEntry()) {
                break;
            }
            n = i;
            i += entry.getNextSpan() + 1;
        } while (i < this.m_block.size());
        int n2;
        if (Language.compare(entry.getName(), s) == 0) {
            n2 = i;
        }
        else if (entry.getName().length() > s.length() && Language.compare(entry.getName().substring(0, s.length()), s) == 0) {
            n2 = i;
        }
        else {
            n2 = n;
        }
        return n2;
    }
    
    public String getLastKey() {
        return this.m_sLastKey;
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final int n3, final Color color, final Image image) {
        final int n4 = n - this.m_nTop;
        final int n5 = (n4 > 0) ? n4 : 0;
        for (int n6 = (n2 + n4 < this.m_nNum) ? (n2 + n4) : this.m_nNum, i = n5; i < n6; ++i) {
            ((IEntry)this.m_block.elementAt(i)).display(graphics, i - n4, n3, color, image);
        }
    }
    
    public String getFirstKey() {
        return this.m_sFirstKey;
    }
    
    public int getWidth(final Graphics graphics) {
        if (this.m_bNeedCalWidth) {
            this.m_nWidth = this.calWidth(graphics);
            this.m_bNeedCalWidth = false;
        }
        return this.m_nWidth;
    }
}

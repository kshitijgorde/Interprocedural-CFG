// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;
import XMLConsumer.TocEntry;
import java.util.Hashtable;
import java.util.Vector;
import XMLConsumer.ITocEntryContainer;

class TocBlock implements ITocEntryContainer
{
    private Vector m_vEntries;
    private int m_nTop;
    private int m_nHeight;
    private int m_nWidth;
    private boolean m_bNeedCalWidth;
    private boolean m_bVisible;
    private Hashtable m_hashSubBlock;
    private TocEntry m_teParent;
    private IActionSink m_accepter;
    
    public void insertTocBlock(final TocEntry parentEntry, final TocBlock tocBlock) {
        if (parentEntry != null) {
            final int index = parentEntry.getIndex();
            tocBlock.setParentEntry(parentEntry);
            if (this.m_hashSubBlock == null) {
                this.m_hashSubBlock = new Hashtable();
            }
            this.m_hashSubBlock.put(new Integer(index), tocBlock);
            final boolean visible = parentEntry.isOpen() && this.isEntryVisible(parentEntry);
            tocBlock.setVisible(visible);
            tocBlock.setTop(this.getPosition(index) + 1);
            if (visible) {
                this.propagateHeightChange(tocBlock, tocBlock.getHeight());
            }
        }
    }
    
    public TocBlock(final IActionSink accepter, final Vector vEntries) {
        this.m_vEntries = vEntries;
        this.m_nWidth = 0;
        this.m_nTop = 0;
        this.m_bVisible = true;
        this.m_accepter = accepter;
        this.m_bNeedCalWidth = true;
        final Enumeration<TocEntry> elements = this.m_vEntries.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().setContainer(this);
        }
        this.calPosOffset();
    }
    
    public TocEntry getEntry(final int n) {
        final int n2 = n - this.m_nTop;
        if (n2 >= 0 && n2 < this.m_nHeight) {
            final int firstIndex = this.getFirstIndex(n2, 1);
            if (firstIndex != -1) {
                return (TocEntry)this.m_vEntries.elementAt(firstIndex);
            }
        }
        return null;
    }
    
    public void setVisible(final boolean bVisible) {
        boolean b = false;
        if (this.m_bVisible != bVisible) {
            b = true;
        }
        this.m_bVisible = bVisible;
        if (b) {
            this.propagateVisibility();
        }
    }
    
    public TocEntry getEntryByIdx(final int n) {
        if (n >= 0 && n < this.m_vEntries.size()) {
            return this.m_vEntries.elementAt(n);
        }
        return null;
    }
    
    public int getTop() {
        return this.m_nTop;
    }
    
    public void setTop(final int nTop) {
        final int n = nTop - this.m_nTop;
        this.m_nTop = nTop;
        if (this.m_hashSubBlock != null) {
            final Enumeration<Integer> keys = this.m_hashSubBlock.keys();
            while (keys.hasMoreElements()) {
                final TocBlock tocBlock = this.m_hashSubBlock.get(keys.nextElement());
                tocBlock.setTop(tocBlock.getTop() + n);
            }
        }
    }
    
    protected void updateHeightChange(final int n, final int n2) {
        for (int i = n; i < this.m_vEntries.size(); ++i) {
            final TocEntry tocEntry = this.m_vEntries.elementAt(i);
            tocEntry.updatePosOffset(n2);
            if (tocEntry.isStubEntry()) {
                final TocBlock subBlock = this.getSubBlock(i);
                if (subBlock != null) {
                    subBlock.setTop(subBlock.getTop() + n2);
                }
            }
        }
    }
    
    public TocBlock getParentBlock() {
        final TocEntry parentEntry = this.getParentEntry();
        if (parentEntry != null) {
            return (TocBlock)parentEntry.getContainer();
        }
        return null;
    }
    
    public TocBlock getSubBlock(final int n) {
        if (this.m_hashSubBlock != null) {
            final TocBlock value = this.m_hashSubBlock.get(new Integer(n));
            if (value != null && value instanceof TocBlock) {
                return value;
            }
        }
        return null;
    }
    
    public void calPosOffset() {
        this.m_nHeight = 0;
        this.m_nHeight = this.calPosOffsetWithEntry(this.m_vEntries.elementAt(0), -1, this.m_bVisible) + 1;
    }
    
    public void setNeedCalWidth() {
        this.m_bNeedCalWidth = true;
    }
    
    protected int calPosOffsetWithEntry(TocEntry nextSibling, int calOffSpringPosOff, final boolean b) {
        while (nextSibling != null) {
            calOffSpringPosOff = this.calOffSpringPosOff(nextSibling, calOffSpringPosOff, b);
            nextSibling = nextSibling.getNextSibling();
        }
        return calOffSpringPosOff;
    }
    
    public TocEntry getParentEntry() {
        return this.m_teParent;
    }
    
    public void setParentEntry(final TocEntry teParent) {
        this.m_teParent = teParent;
    }
    
    protected int calWidth(final Graphics graphics) {
        return this.getOffSpringMaxWidth(graphics, this.m_vEntries.elementAt(0));
    }
    
    protected int getOffSpringMaxWidth(final Graphics graphics, TocEntry nextSibling) {
        int n = 0;
        while (nextSibling != null) {
            int n2 = nextSibling.getWidth(graphics);
            if (n2 > n) {
                n = n2;
            }
            if (nextSibling.isOpen()) {
                n2 = this.getOffSpringMaxWidth(graphics, nextSibling.getFirstChild());
            }
            if (n2 > n) {
                n = n2;
            }
            nextSibling = nextSibling.getNextSibling();
        }
        return n;
    }
    
    public boolean isVisible() {
        return this.m_bVisible;
    }
    
    public int getFirstIndex(final int n, final int n2) {
        final int n3 = -1;
        if (this.m_vEntries.size() > 0) {
            final TocEntry tocEntry = this.m_vEntries.elementAt(0);
            if (this.isEntryVisible(tocEntry)) {
                return this.getFirstVisibleEntryIndex(tocEntry, n, n2);
            }
        }
        return n3;
    }
    
    protected int calOffSpringPosOff(final TocEntry tocEntry, int posOffset, final boolean visible) {
        if (visible && !tocEntry.isStubEntry()) {
            tocEntry.setPosOffset(++posOffset);
        }
        else {
            tocEntry.setPosOffset(posOffset);
            if (tocEntry.isStubEntry()) {
                if (!tocEntry.isStubFilled()) {
                    if (visible && this.m_accepter != null) {
                        tocEntry.action(this.m_accepter);
                    }
                }
                else {
                    final TocBlock subBlock = this.getSubBlock(tocEntry.getIndex());
                    if (subBlock != null) {
                        subBlock.setTop(this.m_nTop + posOffset + 1);
                        subBlock.setVisible(visible);
                        subBlock.calPosOffset();
                        posOffset += subBlock.getHeight();
                    }
                }
            }
        }
        if (tocEntry.getFirstChild() != null) {
            if (tocEntry.isOpen()) {
                posOffset = this.calPosOffsetWithEntry(tocEntry.getFirstChild(), posOffset, visible);
            }
            else {
                posOffset = this.calPosOffsetWithEntry(tocEntry.getFirstChild(), posOffset, false);
            }
        }
        return posOffset;
    }
    
    public int getPosition(final int n) {
        return this.m_nTop + this.m_vEntries.elementAt(n).getPosOffset();
    }
    
    protected void propagateHeightChange(final TocBlock tocBlock, final int n) {
        if (this.m_hashSubBlock != null && this.m_hashSubBlock.contains(tocBlock)) {
            final Enumeration<Integer> keys = this.m_hashSubBlock.keys();
            while (keys.hasMoreElements()) {
                final Integer n2 = keys.nextElement();
                if (this.m_hashSubBlock.get(n2) == tocBlock) {
                    this.updateHeightChange(n2 + 1, n);
                    break;
                }
            }
        }
        this.m_nHeight += n;
        if (this.getParentBlock() != null) {
            this.getParentBlock().propagateHeightChange(this, n);
        }
    }
    
    protected void propagateVisibility() {
        if (this.m_hashSubBlock != null) {
            final Enumeration<Integer> keys = (Enumeration<Integer>)this.m_hashSubBlock.keys();
            while (keys.hasMoreElements()) {
                final Integer n = keys.nextElement();
                final TocBlock tocBlock = this.m_hashSubBlock.get(n);
                if (((TocEntry)this.m_vEntries.elementAt(n)).isOpen()) {
                    tocBlock.setVisible(this.m_bVisible);
                }
            }
        }
    }
    
    public int getHeight() {
        return this.m_nHeight;
    }
    
    public int getCount() {
        return this.m_vEntries.size();
    }
    
    public void callPosOffsetByEntry(final TocEntry tocEntry) {
        final TocEntry nextOut = tocEntry.getNextOut();
        int n = this.m_vEntries.size() - 1;
        if (nextOut != null) {
            n = nextOut.getIndex() - 1;
        }
        if (n >= 0 && n != tocEntry.getIndex()) {
            final TocEntry tocEntry2 = this.m_vEntries.elementAt(n);
            int posOffset = tocEntry2.getPosOffset();
            if (tocEntry2.isStubEntry() && tocEntry2.isStubFilled()) {
                final TocBlock subBlock = this.getSubBlock(tocEntry2.getIndex());
                if (subBlock != null) {
                    posOffset += subBlock.getHeight();
                }
            }
            final int n2 = this.calOffSpringPosOff(tocEntry, tocEntry.getPosOffset() - 1, true) - posOffset;
            if (nextOut != null) {
                this.updateHeightChange(nextOut.getIndex(), n2);
            }
            this.m_nHeight += n2;
        }
    }
    
    public void display(final Graphics graphics, final int n, final int n2, final int n3, final Color color, final Image image) {
        final int n4 = n - this.m_nTop;
        final int firstIndex = this.getFirstIndex((n4 > 0) ? n4 : 0, n2);
        if (firstIndex != -1) {
            int i = firstIndex;
            while (i < this.m_vEntries.size()) {
                final TocEntry tocEntry = this.m_vEntries.elementAt(i);
                if (tocEntry.getPosOffset() >= n2 + n4) {
                    break;
                }
                if (this.isEntryVisible(tocEntry)) {
                    if (!tocEntry.isStubEntry()) {
                        tocEntry.display(graphics, tocEntry.getPosOffset() - n4, n3, color, image);
                    }
                    ++i;
                }
                else {
                    final TocEntry directParent = tocEntry.getDirectParent();
                    if (directParent == null) {
                        break;
                    }
                    final TocEntry nextOut = directParent.getNextOut();
                    if (nextOut == null) {
                        break;
                    }
                    i = nextOut.getIndex();
                }
            }
        }
    }
    
    public int getWidth(final Graphics graphics) {
        if (this.isVisible()) {
            if (this.m_bNeedCalWidth) {
                this.m_bNeedCalWidth = false;
                this.m_nWidth = this.calWidth(graphics);
            }
            return this.m_nWidth;
        }
        return 0;
    }
    
    public int countRightSibiling(int n) {
        int n2 = 0;
        while (n >= 0 && n < this.m_vEntries.size()) {
            final TocEntry tocEntry = this.m_vEntries.elementAt(n);
            if (tocEntry.getNextSpan() == -1) {
                break;
            }
            ++n2;
            n += tocEntry.getNextSpan() + 1;
        }
        return n2;
    }
    
    private int getFirstVisibleEntryIndex(TocEntry tocEntry, final int n, final int n2) {
        while (true) {
            TocEntry tocEntry2 = tocEntry.getNextSibling();
            if (tocEntry.isStubEntry()) {
                tocEntry = tocEntry2;
                if (tocEntry == null) {
                    return -1;
                }
                continue;
            }
            else {
                while (tocEntry2 != null && tocEntry2.isStubEntry()) {
                    tocEntry2 = tocEntry2.getNextSibling();
                }
                if (tocEntry.getPosOffset() < n) {
                    if (tocEntry2 == null || tocEntry2.getPosOffset() > n) {
                        if (tocEntry2 != null && tocEntry2.getPosOffset() < n + n2) {
                            if (tocEntry.getFirstChild() != null) {
                                final int firstVisibleEntryIndex = this.getFirstVisibleEntryIndex(tocEntry.getFirstChild(), n, n2);
                                if (firstVisibleEntryIndex != -1) {
                                    return firstVisibleEntryIndex;
                                }
                                tocEntry = tocEntry2;
                            }
                            else {
                                tocEntry = tocEntry2;
                            }
                        }
                        else {
                            if (tocEntry.getFirstChild() != null) {
                                return this.getFirstVisibleEntryIndex(tocEntry.getFirstChild(), n, n2);
                            }
                            return -1;
                        }
                    }
                    else {
                        tocEntry = tocEntry2;
                    }
                }
                else {
                    if (tocEntry.getPosOffset() < n + n2) {
                        return tocEntry.getIndex();
                    }
                    return -1;
                }
            }
        }
    }
    
    private boolean isEntryVisible(final TocEntry tocEntry) {
        final TocEntry directParent = tocEntry.getDirectParent();
        if (directParent != null) {
            return directParent.isOpen() && this.isEntryVisible(directParent);
        }
        return this.m_bVisible;
    }
}

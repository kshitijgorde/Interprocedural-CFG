// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.util.Enumeration;
import XMLConsumer.IEntry;
import java.util.Vector;

class BlockContainer
{
    private Vector m_block;
    private int m_nNeeded;
    private int m_nConsumed;
    private int m_nCurrent;
    private String m_sFirstKey;
    private String m_sLastKey;
    
    public Vector getBlock() {
        return this.m_block;
    }
    
    public BlockContainer(final int nNeeded) {
        this.m_nNeeded = nNeeded;
    }
    
    public int getNeeded() {
        return this.m_nNeeded;
    }
    
    public void Insert(final IEntry entry, final int n, final boolean b) {
        if (this.m_block == null) {
            this.m_block = new Vector();
        }
        if (this.m_sFirstKey == null) {
            this.m_sFirstKey = entry.getName();
        }
        if (this.m_sLastKey == null) {
            this.m_sLastKey = entry.getName();
        }
        if (b) {
            this.m_block.addElement(entry);
        }
        else {
            this.m_block.insertElementAt(entry, 0);
        }
        if (b) {
            this.m_sLastKey = entry.getName();
        }
        else {
            this.m_sFirstKey = entry.getName();
        }
        this.m_nConsumed += n;
        ++this.m_nCurrent;
    }
    
    public void addSub(final BlockContainer blockContainer, final boolean b) {
        final Vector block = blockContainer.getBlock();
        if (this.m_block == null) {
            this.m_block = new Vector();
        }
        if (block != null) {
            if (b) {
                final Enumeration<Object> elements = block.elements();
                while (elements.hasMoreElements()) {
                    this.m_block.addElement(elements.nextElement());
                }
            }
            else {
                final Vector block2 = (Vector)block.clone();
                final Enumeration<Object> elements2 = this.m_block.elements();
                while (elements2.hasMoreElements()) {
                    block2.addElement(elements2.nextElement());
                }
                this.m_block = block2;
            }
            this.m_nCurrent += blockContainer.m_nCurrent;
            this.m_nConsumed += blockContainer.m_nConsumed;
        }
    }
    
    public String getLastKey() {
        return this.m_sLastKey;
    }
    
    public int getCurrent() {
        return this.m_nCurrent;
    }
    
    public int getConsumed() {
        return this.m_nConsumed;
    }
    
    public void appendSub(final BlockContainer blockContainer, final boolean b) {
        this.addSub(blockContainer, b);
        if (this.m_sFirstKey == null) {
            this.m_sFirstKey = blockContainer.m_sFirstKey;
        }
        if (this.m_sLastKey == null) {
            this.m_sLastKey = blockContainer.m_sLastKey;
        }
        if (b) {
            this.m_sLastKey = blockContainer.m_sLastKey;
            return;
        }
        this.m_sFirstKey = blockContainer.m_sFirstKey;
    }
    
    public String getFirstKey() {
        return this.m_sFirstKey;
    }
}

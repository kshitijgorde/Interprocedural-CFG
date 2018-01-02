// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

class UsedItem
{
    private int m_nBegin;
    private int m_nEnd;
    private UsedItem m_nNext;
    
    public int getEnd() {
        return this.m_nEnd;
    }
    
    public void setEnd(final int nEnd) {
        this.m_nEnd = nEnd;
    }
    
    public UsedItem(final int nBegin, final int nEnd) {
        this.m_nBegin = nBegin;
        this.m_nEnd = nEnd;
        this.m_nNext = null;
    }
    
    public void setNext(final UsedItem nNext) {
        this.m_nNext = nNext;
    }
    
    public UsedItem getNext() {
        return this.m_nNext;
    }
    
    public int getBegin() {
        return this.m_nBegin;
    }
    
    public void setBegin(final int nBegin) {
        this.m_nBegin = nBegin;
    }
}

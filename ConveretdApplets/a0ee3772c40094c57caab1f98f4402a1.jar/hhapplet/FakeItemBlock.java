// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

class FakeItemBlock
{
    private int m_nBegin;
    private int m_nNum;
    private String m_sKeyAfter;
    private String m_sKeyBefore;
    private int m_nTop;
    
    public void setNum(final int nNum) {
        this.m_nNum = nNum;
    }
    
    public int getNum() {
        return this.m_nNum;
    }
    
    public FakeItemBlock(final int nBegin, final int nNum, final String sKeyAfter, final String sKeyBefore, final int nTop) {
        this.m_nBegin = nBegin;
        this.m_nNum = nNum;
        this.m_sKeyAfter = sKeyAfter;
        this.m_sKeyBefore = sKeyBefore;
        this.m_nTop = nTop;
    }
    
    public int getTop() {
        return this.m_nTop;
    }
    
    public void posChange(final int n) {
        this.m_nTop += n;
    }
    
    public boolean setKeyBefore(final String sKeyBefore) {
        if (Language.compare(this.m_sKeyBefore, sKeyBefore) > 0) {
            this.m_sKeyBefore = sKeyBefore;
            return true;
        }
        return false;
    }
    
    public String getKeyBefore() {
        return this.m_sKeyBefore;
    }
    
    public boolean setKeyAfter(final String sKeyAfter) {
        if (Language.compare(this.m_sKeyAfter, sKeyAfter) < 0) {
            this.m_sKeyAfter = sKeyAfter;
            return true;
        }
        return false;
    }
    
    public String getKeyAfter() {
        return this.m_sKeyAfter;
    }
    
    public void setBegin(final int nBegin) {
        this.m_nBegin = nBegin;
    }
    
    public int getBegin() {
        return this.m_nBegin;
    }
}

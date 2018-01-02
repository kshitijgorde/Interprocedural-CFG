// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

public class PaneSetting
{
    private int m_nIdx;
    private ViewSkin m_oViewSkin;
    
    public PaneSetting(final int nIdx) {
        this.m_nIdx = 0;
        this.m_oViewSkin = null;
        this.m_nIdx = nIdx;
    }
    
    public void setViewSkin(final ViewSkin oViewSkin) {
        this.m_oViewSkin = oViewSkin;
    }
    
    public ViewSkin getViewSkin() {
        return this.m_oViewSkin;
    }
    
    public int getIndex() {
        return this.m_nIdx;
    }
}

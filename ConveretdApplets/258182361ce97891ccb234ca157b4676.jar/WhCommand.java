// 
// Decompiled by Procyon v0.5.30
// 

class WhCommand
{
    private String m_sName;
    private String m_sParam0;
    private String m_sParam1;
    
    public String getParam0() {
        return this.m_sParam0;
    }
    
    public WhCommand(final String sName, final String sParam0, final String sParam2) {
        this.m_sName = sName;
        this.m_sParam0 = sParam0;
        this.m_sParam1 = sParam2;
    }
    
    public String getParam1() {
        return this.m_sParam1;
    }
    
    public String getName() {
        return this.m_sName;
    }
}

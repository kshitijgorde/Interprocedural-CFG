// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

public class ElemExtensionScript extends ElemTemplateElement
{
    private String m_lang;
    private String m_src;
    
    public ElemExtensionScript() {
        this.m_lang = null;
        this.m_src = null;
    }
    
    public String getLang() {
        return this.m_lang;
    }
    
    public String getSrc() {
        return this.m_src;
    }
    
    public int getXSLToken() {
        return 86;
    }
    
    public void setLang(final String v) {
        this.m_lang = v;
    }
    
    public void setSrc(final String v) {
        this.m_src = v;
    }
}

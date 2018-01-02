// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

public class NamespaceAlias extends ElemTemplateElement
{
    private String m_StylesheetPrefix;
    private String m_StylesheetNamespace;
    private String m_ResultPrefix;
    private String m_ResultNamespace;
    
    public NamespaceAlias(final int docOrderNumber) {
        super.m_docOrderNumber = docOrderNumber;
    }
    
    public String getResultNamespace() {
        return this.m_ResultNamespace;
    }
    
    public String getResultPrefix() {
        return this.m_ResultPrefix;
    }
    
    public String getStylesheetNamespace() {
        return this.m_StylesheetNamespace;
    }
    
    public String getStylesheetPrefix() {
        return this.m_StylesheetPrefix;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeNamespaceAliases(this);
    }
    
    public void setResultNamespace(final String v) {
        this.m_ResultNamespace = v;
    }
    
    public void setResultPrefix(final String v) {
        this.m_ResultPrefix = v;
    }
    
    public void setStylesheetNamespace(final String v) {
        this.m_StylesheetNamespace = v;
    }
    
    public void setStylesheetPrefix(final String v) {
        this.m_StylesheetPrefix = v;
    }
}

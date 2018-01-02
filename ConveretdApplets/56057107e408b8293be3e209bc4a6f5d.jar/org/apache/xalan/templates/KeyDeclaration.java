// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPath;
import org.apache.xml.utils.QName;

public class KeyDeclaration extends ElemTemplateElement
{
    private QName m_name;
    private XPath m_matchPattern;
    private XPath m_use;
    
    public KeyDeclaration(final Stylesheet parentNode, final int docOrderNumber) {
        this.m_matchPattern = null;
        super.m_parentNode = parentNode;
        this.setUid(docOrderNumber);
    }
    
    public XPath getMatch() {
        return this.m_matchPattern;
    }
    
    public QName getName() {
        return this.m_name;
    }
    
    public XPath getUse() {
        return this.m_use;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeKeys(this);
    }
    
    public void setMatch(final XPath v) {
        this.m_matchPattern = v;
    }
    
    public void setName(final QName name) {
        this.m_name = name;
    }
    
    public void setUse(final XPath v) {
        this.m_use = v;
    }
}

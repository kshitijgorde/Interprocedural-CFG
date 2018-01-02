// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xpath.XPath;
import org.apache.xml.utils.QName;

public class KeyDeclaration extends ElemTemplateElement
{
    static final long serialVersionUID = 7724030248631137918L;
    private QName m_name;
    private XPath m_matchPattern;
    private XPath m_use;
    
    public KeyDeclaration(final Stylesheet parentNode, final int docOrderNumber) {
        this.m_matchPattern = null;
        super.m_parentNode = parentNode;
        this.setUid(docOrderNumber);
    }
    
    public void setName(final QName name) {
        this.m_name = name;
    }
    
    public QName getName() {
        return this.m_name;
    }
    
    public String getNodeName() {
        return "key";
    }
    
    public void setMatch(final XPath v) {
        this.m_matchPattern = v;
    }
    
    public XPath getMatch() {
        return this.m_matchPattern;
    }
    
    public void setUse(final XPath v) {
        this.m_use = v;
    }
    
    public XPath getUse() {
        return this.m_use;
    }
    
    public int getXSLToken() {
        return 31;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final Vector vnames = sroot.getComposeState().getVariableNames();
        if (null != this.m_matchPattern) {
            this.m_matchPattern.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
        if (null != this.m_use) {
            this.m_use.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeKeys(this);
    }
}

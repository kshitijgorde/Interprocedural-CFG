// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.utils.QName;
import org.apache.xpath.XPath;

public class ElemTemplate extends ElemTemplateElement
{
    private String m_publicId;
    private String m_systemId;
    private Stylesheet m_stylesheet;
    private XPath m_matchPattern;
    private QName m_name;
    private QName m_mode;
    private double m_priority;
    
    public ElemTemplate() {
        this.m_matchPattern = null;
        this.m_name = null;
        this.m_priority = Double.NEGATIVE_INFINITY;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        if (sourceNode != null) {
            transformer.executeChildTemplates(this, sourceNode, mode);
        }
        else {
            transformer.getMsgMgr().error(this, this, sourceNode, 5);
        }
    }
    
    public XPath getMatch() {
        return this.m_matchPattern;
    }
    
    public QName getMode() {
        return this.m_mode;
    }
    
    public QName getName() {
        return this.m_name;
    }
    
    public String getNodeName() {
        return "template";
    }
    
    public double getPriority() {
        return this.m_priority;
    }
    
    public String getPublicId() {
        return this.m_publicId;
    }
    
    public Stylesheet getStylesheet() {
        return this.m_stylesheet;
    }
    
    public StylesheetComposed getStylesheetComposed() {
        return this.m_stylesheet.getStylesheetComposed();
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_stylesheet.getStylesheetRoot();
    }
    
    public String getSystemId() {
        return this.m_systemId;
    }
    
    public int getXSLToken() {
        return 19;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeTemplates(this);
    }
    
    public void setLocaterInfo(final SourceLocator locator) {
        this.m_publicId = locator.getPublicId();
        this.m_systemId = locator.getSystemId();
        super.setLocaterInfo(locator);
    }
    
    public void setMatch(final XPath v) {
        this.m_matchPattern = v;
    }
    
    public void setMode(final QName v) {
        this.m_mode = v;
    }
    
    public void setName(final QName v) {
        this.m_name = v;
    }
    
    public void setPriority(final double v) {
        this.m_priority = v;
    }
    
    public void setStylesheet(final Stylesheet sheet) {
        this.m_stylesheet = sheet;
    }
}

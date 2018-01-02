// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import javax.xml.transform.SourceLocator;
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
    public int m_frameSize;
    int m_inArgsSize;
    private int[] m_argsQNameIDs;
    
    public ElemTemplate() {
        this.m_matchPattern = null;
        this.m_name = null;
        this.m_priority = Double.NEGATIVE_INFINITY;
    }
    
    public String getPublicId() {
        return this.m_publicId;
    }
    
    public String getSystemId() {
        return this.m_systemId;
    }
    
    public void setLocaterInfo(final SourceLocator locator) {
        this.m_publicId = locator.getPublicId();
        this.m_systemId = locator.getSystemId();
        super.setLocaterInfo(locator);
    }
    
    public StylesheetComposed getStylesheetComposed() {
        return this.m_stylesheet.getStylesheetComposed();
    }
    
    public Stylesheet getStylesheet() {
        return this.m_stylesheet;
    }
    
    public void setStylesheet(final Stylesheet sheet) {
        this.m_stylesheet = sheet;
    }
    
    public StylesheetRoot getStylesheetRoot() {
        return this.m_stylesheet.getStylesheetRoot();
    }
    
    public void setMatch(final XPath v) {
        this.m_matchPattern = v;
    }
    
    public XPath getMatch() {
        return this.m_matchPattern;
    }
    
    public void setName(final QName v) {
        this.m_name = v;
    }
    
    public QName getName() {
        return this.m_name;
    }
    
    public void setMode(final QName v) {
        this.m_mode = v;
    }
    
    public QName getMode() {
        return this.m_mode;
    }
    
    public void setPriority(final double v) {
        this.m_priority = v;
    }
    
    public double getPriority() {
        return this.m_priority;
    }
    
    public int getXSLToken() {
        return 19;
    }
    
    public String getNodeName() {
        return "template";
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_matchPattern) {
            this.m_matchPattern.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
        cstate.resetStackFrameSize();
        this.m_inArgsSize = 0;
    }
    
    public void endCompose(final StylesheetRoot sroot) throws TransformerException {
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        super.endCompose(sroot);
        this.m_frameSize = cstate.getFrameSize();
        cstate.resetStackFrameSize();
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        transformer.getStackGuard().checkForInfinateLoop();
        xctxt.pushRTFContext();
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        transformer.executeChildTemplates(this, true);
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
        xctxt.popRTFContext();
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeTemplates(this);
    }
}

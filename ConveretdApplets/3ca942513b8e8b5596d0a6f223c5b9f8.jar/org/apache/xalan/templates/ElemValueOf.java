// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.Expression;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xpath.XPathContext;
import javax.xml.transform.SourceLocator;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xpath.XPath;

public class ElemValueOf extends ElemTemplateElement
{
    static final long serialVersionUID = 3490728458007586786L;
    private XPath m_selectExpression;
    private boolean m_isDot;
    private boolean m_disableOutputEscaping;
    
    public ElemValueOf() {
        this.m_selectExpression = null;
        this.m_isDot = false;
        this.m_disableOutputEscaping = false;
    }
    
    public void setSelect(final XPath v) {
        if (null != v) {
            final String s = v.getPatternString();
            this.m_isDot = (null != s && s.equals("."));
        }
        this.m_selectExpression = v;
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public void setDisableOutputEscaping(final boolean v) {
        this.m_disableOutputEscaping = v;
    }
    
    public boolean getDisableOutputEscaping() {
        return this.m_disableOutputEscaping;
    }
    
    public int getXSLToken() {
        return 30;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final Vector vnames = sroot.getComposeState().getVariableNames();
        if (null != this.m_selectExpression) {
            this.m_selectExpression.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
    }
    
    public String getNodeName() {
        return "value-of";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        final SerializationHandler rth = transformer.getResultTreeHandler();
        while (true) {
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireTraceEvent(this);
                try {
                    xctxt.pushNamespaceContext(this);
                    final int current = xctxt.getCurrentNode();
                    xctxt.pushCurrentNodeAndExpression(current, current);
                    if (this.m_disableOutputEscaping) {
                        rth.processingInstruction("javax.xml.transform.disable-output-escaping", "");
                    }
                    try {
                        final Expression expr = this.m_selectExpression.getExpression();
                        if (transformer.getDebug()) {
                            final XObject obj = expr.execute(xctxt);
                            transformer.getTraceManager().fireSelectedEvent(current, this, "select", this.m_selectExpression, obj);
                            obj.dispatchCharactersEvents(rth);
                        }
                        else {
                            expr.executeCharsToContentHandler(xctxt, rth);
                        }
                    }
                    finally {
                        if (this.m_disableOutputEscaping) {
                            rth.processingInstruction("javax.xml.transform.enable-output-escaping", "");
                        }
                        xctxt.popNamespaceContext();
                        xctxt.popCurrentNodeAndExpression();
                    }
                }
                catch (SAXException se) {
                    throw new TransformerException(se);
                }
                catch (RuntimeException re) {
                    final TransformerException te = new TransformerException(re);
                    te.setLocator(this);
                    throw te;
                }
                finally {
                    if (transformer.getDebug()) {
                        transformer.getTraceManager().fireTraceEndEvent(this);
                    }
                }
                return;
            }
            continue;
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs) {
            this.m_selectExpression.getExpression().callVisitors(this.m_selectExpression, visitor);
        }
        super.callChildVisitors(visitor, callAttrs);
    }
}

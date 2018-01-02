// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.transformer.ResultTreeHandler;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XObject;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNodeSet;
import org.xml.sax.ContentHandler;
import org.apache.xalan.stree.SaxEventDispatch;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xpath.XPath;

public class ElemValueOf extends ElemTemplateElement
{
    private XPath m_selectExpression;
    private boolean m_isDot;
    private boolean m_disableOutputEscaping;
    
    public ElemValueOf() {
        this.m_selectExpression = null;
        this.m_isDot = false;
        this.m_disableOutputEscaping = false;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            Node child;
            XObject value;
            if (this.m_isDot && !TransformerImpl.S_DEBUG) {
                child = sourceNode;
                value = null;
            }
            else {
                value = this.m_selectExpression.execute(transformer.getXPathContext(), sourceNode, this);
                if (value.getType() == 4) {
                    final NodeIterator iterator = value.nodeset();
                    child = iterator.nextNode();
                    if (child == null) {
                        return;
                    }
                }
                else {
                    child = null;
                }
                if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectExpression, value);
                }
            }
            String s;
            if (child != null) {
                if (child.isSupported("http://xml.apache.org/xalan/features/feed-events", "1.0")) {
                    if (this.m_disableOutputEscaping) {
                        final ResultTreeHandler rth = transformer.getResultTreeHandler();
                        rth.processingInstruction("javax.xml.transform.disable-output-escaping", "");
                        ((SaxEventDispatch)child).dispatchCharactersEvent(rth);
                        rth.processingInstruction("javax.xml.transform.enable-output-escaping", "");
                    }
                    else {
                        ((SaxEventDispatch)child).dispatchCharactersEvent(transformer.getResultTreeHandler());
                    }
                    return;
                }
                s = XNodeSet.getStringFromNode(child);
            }
            else {
                s = value.str();
            }
            final int len = (s != null) ? s.length() : 0;
            if (len > 0) {
                final ResultTreeHandler rth2 = transformer.getResultTreeHandler();
                if (this.m_disableOutputEscaping) {
                    rth2.processingInstruction("javax.xml.transform.disable-output-escaping", "");
                    rth2.characters(s.toCharArray(), 0, len);
                    rth2.processingInstruction("javax.xml.transform.enable-output-escaping", "");
                }
                else {
                    rth2.characters(s.toCharArray(), 0, len);
                }
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public boolean getDisableOutputEscaping() {
        return this.m_disableOutputEscaping;
    }
    
    public String getNodeName() {
        return "value-of";
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public int getXSLToken() {
        return 30;
    }
    
    public void setDisableOutputEscaping(final boolean v) {
        this.m_disableOutputEscaping = v;
    }
    
    public void setSelect(final XPath v) {
        if (v != null) {
            final String s = v.getPatternString();
            this.m_isDot = (s != null && s.equals("."));
        }
        this.m_selectExpression = v;
    }
}

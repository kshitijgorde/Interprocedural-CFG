// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.dtm.ref.DTMNodeList;
import org.w3c.dom.NodeList;
import org.apache.xpath.axes.RTFIterator;
import org.apache.xml.utils.FastStringBuffer;
import javax.xml.transform.TransformerException;
import org.apache.xpath.Expression;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xml.dtm.ref.DTMNodeIterator;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.XMLString;

public class XRTreeFrag extends XObject implements Cloneable
{
    static final long serialVersionUID = -3201553822254911567L;
    private DTMXRTreeFrag m_DTMXRTreeFrag;
    private int m_dtmRoot;
    protected boolean m_allowRelease;
    private XMLString m_xmlStr;
    
    public XRTreeFrag(final int root, final XPathContext xctxt, final ExpressionNode parent) {
        super(null);
        this.m_dtmRoot = -1;
        this.m_allowRelease = false;
        this.m_xmlStr = null;
        this.exprSetParent(parent);
        this.initDTM(root, xctxt);
    }
    
    public XRTreeFrag(final int root, final XPathContext xctxt) {
        super(null);
        this.m_dtmRoot = -1;
        this.m_allowRelease = false;
        this.m_xmlStr = null;
        this.initDTM(root, xctxt);
    }
    
    private final void initDTM(final int root, final XPathContext xctxt) {
        this.m_dtmRoot = root;
        final DTM dtm = xctxt.getDTM(root);
        if (dtm != null) {
            this.m_DTMXRTreeFrag = xctxt.getDTMXRTreeFrag(xctxt.getDTMIdentity(dtm));
        }
    }
    
    public Object object() {
        if (this.m_DTMXRTreeFrag.getXPathContext() != null) {
            return new DTMNodeIterator(new NodeSetDTM(this.m_dtmRoot, this.m_DTMXRTreeFrag.getXPathContext().getDTMManager()));
        }
        return super.object();
    }
    
    public XRTreeFrag(final Expression expr) {
        super(expr);
        this.m_dtmRoot = -1;
        this.m_allowRelease = false;
        this.m_xmlStr = null;
    }
    
    public void allowDetachToRelease(final boolean allowRelease) {
        this.m_allowRelease = allowRelease;
    }
    
    public void detach() {
        if (this.m_allowRelease) {
            this.m_DTMXRTreeFrag.destruct();
            super.m_obj = null;
        }
    }
    
    public int getType() {
        return 5;
    }
    
    public String getTypeString() {
        return "#RTREEFRAG";
    }
    
    public double num() throws TransformerException {
        final XMLString s = this.xstr();
        return s.toDouble();
    }
    
    public boolean bool() {
        return true;
    }
    
    public XMLString xstr() {
        if (null == this.m_xmlStr) {
            this.m_xmlStr = this.m_DTMXRTreeFrag.getDTM().getStringValue(this.m_dtmRoot);
        }
        return this.m_xmlStr;
    }
    
    public void appendToFsb(final FastStringBuffer fsb) {
        final XString xstring = (XString)this.xstr();
        xstring.appendToFsb(fsb);
    }
    
    public String str() {
        final String str = this.m_DTMXRTreeFrag.getDTM().getStringValue(this.m_dtmRoot).toString();
        return (null == str) ? "" : str;
    }
    
    public int rtf() {
        return this.m_dtmRoot;
    }
    
    public DTMIterator asNodeIterator() {
        return new RTFIterator(this.m_dtmRoot, this.m_DTMXRTreeFrag.getXPathContext().getDTMManager());
    }
    
    public NodeList convertToNodeset() {
        if (super.m_obj instanceof NodeList) {
            return (NodeList)super.m_obj;
        }
        return new DTMNodeList(this.asNodeIterator());
    }
    
    public boolean equals(final XObject obj2) {
        try {
            if (4 == obj2.getType()) {
                return obj2.equals(this);
            }
            if (1 == obj2.getType()) {
                return this.bool() == obj2.bool();
            }
            if (2 == obj2.getType()) {
                return this.num() == obj2.num();
            }
            if (4 == obj2.getType()) {
                return this.xstr().equals(obj2.xstr());
            }
            if (3 == obj2.getType()) {
                return this.xstr().equals(obj2.xstr());
            }
            if (5 == obj2.getType()) {
                return this.xstr().equals(obj2.xstr());
            }
            return super.equals(obj2);
        }
        catch (TransformerException te) {
            throw new WrappedRuntimeException(te);
        }
    }
}

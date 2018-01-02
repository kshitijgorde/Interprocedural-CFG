// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import org.w3c.dom.Text;
import org.apache.xpath.NodeSet;
import org.apache.xpath.XPathException;
import org.apache.xalan.res.XSLMessages;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;
import org.w3c.dom.DocumentFragment;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import java.io.Serializable;
import org.apache.xpath.Expression;

public class XObject extends Expression implements Serializable
{
    protected Object m_obj;
    public static final int CLASS_NULL = -1;
    public static final int CLASS_UNKNOWN = 0;
    public static final int CLASS_BOOLEAN = 1;
    public static final int CLASS_NUMBER = 2;
    public static final int CLASS_STRING = 3;
    public static final int CLASS_NODESET = 4;
    public static final int CLASS_RTREEFRAG = 5;
    public static final int CLASS_UNRESOLVEDVARIABLE = 600;
    
    public XObject() {
    }
    
    public XObject(final Object obj) {
        this.m_obj = obj;
    }
    
    public boolean bool() throws TransformerException {
        this.error(18, new Object[] { this.getTypeString() });
        return false;
    }
    
    public Object castToType(final int t, final XPathContext support) throws TransformerException {
        Object result = null;
        switch (t) {
            case 3: {
                result = this.str();
                break;
            }
            case 2: {
                result = new Double(this.num());
                break;
            }
            case 4: {
                result = this.nodeset();
                break;
            }
            case 1: {
                result = new Boolean(this.bool());
                break;
            }
            case 0: {
                result = this.m_obj;
                break;
            }
            case 5: {
                result = this.rtree(support);
                break;
            }
            default: {
                this.error(21, new Object[] { this.getTypeString(), Integer.toString(t) });
                result = null;
                break;
            }
        }
        return result;
    }
    
    public static XObject create(final Object val) {
        XObject result;
        if (val instanceof XObject) {
            result = (XObject)val;
        }
        else if (val instanceof String) {
            result = new XString((String)val);
        }
        else if (val instanceof Boolean) {
            result = (val ? XBoolean.S_TRUE : XBoolean.S_FALSE);
        }
        else if (val instanceof Double) {
            result = new XNumber((double)val);
        }
        else if (val instanceof DocumentFragment) {
            result = new XRTreeFrag((DocumentFragment)val);
        }
        else if (val instanceof Node) {
            result = new XNodeSet((Node)val);
        }
        else if (val instanceof NodeIterator) {
            result = new XNodeSet((NodeIterator)val);
        }
        else {
            result = new XObject(val);
        }
        return result;
    }
    
    public boolean equals(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.equals(this);
        }
        return this.m_obj.equals(obj2.m_obj);
    }
    
    protected void error(final int msg) throws TransformerException {
        this.error(msg, null);
    }
    
    protected void error(final int msg, final Object[] args) throws TransformerException {
        final String fmsg = XSLMessages.createXPATHMessage(msg, args);
        throw new XPathException(fmsg);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return this;
    }
    
    public int getType() {
        return 0;
    }
    
    public String getTypeString() {
        return "#UNKNOWN (" + this.object().getClass().getName() + ")";
    }
    
    public boolean greaterThan(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.lessThan(this);
        }
        return this.num() > obj2.num();
    }
    
    public boolean greaterThanOrEqual(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.lessThanOrEqual(this);
        }
        return this.num() >= obj2.num();
    }
    
    public boolean lessThan(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.greaterThan(this);
        }
        return this.num() < obj2.num();
    }
    
    public boolean lessThanOrEqual(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.greaterThanOrEqual(this);
        }
        return this.num() <= obj2.num();
    }
    
    public NodeSet mutableNodeset() throws TransformerException {
        this.error(20, new Object[] { this.getTypeString() });
        return (NodeSet)this.m_obj;
    }
    
    public NodeIterator nodeset() throws TransformerException {
        this.error(19, new Object[] { this.getTypeString() });
        return null;
    }
    
    public boolean notEquals(final XObject obj2) throws TransformerException {
        if (obj2.getType() == 4) {
            return obj2.notEquals(this);
        }
        return this.equals(obj2) ^ true;
    }
    
    public double num() throws TransformerException {
        this.error(18, new Object[] { this.getTypeString() });
        return 0.0;
    }
    
    public Object object() {
        return this.m_obj;
    }
    
    public DocumentFragment rtree() {
        return null;
    }
    
    public DocumentFragment rtree(final XPathContext support) {
        DocumentFragment result = this.rtree();
        if (result == null) {
            result = support.getDOMHelper().getDOMFactory().createDocumentFragment();
            final Text textNode = support.getDOMHelper().getDOMFactory().createTextNode(this.str());
            result.appendChild(textNode);
        }
        return result;
    }
    
    public String str() {
        return this.m_obj.toString();
    }
    
    public String toString() {
        return this.str();
    }
}

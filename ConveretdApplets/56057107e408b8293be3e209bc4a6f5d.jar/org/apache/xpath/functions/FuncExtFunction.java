// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xalan.extensions.ExtensionsTable;
import org.apache.xpath.objects.XNull;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XNodeSet;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XRTreeFrag;
import org.w3c.dom.DocumentFragment;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XString;
import org.apache.xalan.extensions.ExpressionContext;
import org.apache.xpath.Expression;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import java.util.Vector;

public class FuncExtFunction extends Function
{
    String m_namespace;
    String m_extensionName;
    Object m_methodKey;
    Vector m_argVec;
    
    public FuncExtFunction(final String namespace, final String extensionName, final Object methodKey) {
        this.m_argVec = new Vector();
        this.m_namespace = namespace;
        this.m_extensionName = extensionName;
        this.m_methodKey = methodKey;
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Vector argVec = new Vector();
        for (int nArgs = this.m_argVec.size(), i = 0; i < nArgs; ++i) {
            final Expression arg = this.m_argVec.elementAt(i);
            argVec.addElement(arg.execute(xctxt));
        }
        final ExtensionsTable etable = xctxt.getExtensionsTable();
        final Object val = etable.extFunction(this.m_namespace, this.m_extensionName, argVec, this.m_methodKey, xctxt);
        XObject result;
        if (val != null) {
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
            else if (val instanceof NodeIterator) {
                result = new XNodeSet((NodeIterator)val);
            }
            else if (val instanceof Node) {
                result = new XNodeSet((Node)val);
            }
            else {
                result = new XObject(val);
            }
        }
        else {
            result = new XNull();
        }
        return result;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        this.m_argVec.addElement(arg);
    }
}

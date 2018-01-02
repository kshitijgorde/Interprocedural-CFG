// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.util.Collection;
import java.util.Vector;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathFunctionException;
import java.util.List;
import javax.xml.xpath.XPathFunction;

public class XPathFunctionImpl implements XPathFunction
{
    private ExtensionHandler m_handler;
    private String m_funcName;
    
    public XPathFunctionImpl(final ExtensionHandler handler, final String funcName) {
        this.m_handler = handler;
        this.m_funcName = funcName;
    }
    
    public Object evaluate(final List args) throws XPathFunctionException {
        final Vector argsVec = listToVector(args);
        try {
            return this.m_handler.callFunction(this.m_funcName, argsVec, null, null);
        }
        catch (TransformerException e) {
            throw new XPathFunctionException(e);
        }
    }
    
    private static Vector listToVector(final List args) {
        if (args == null) {
            return null;
        }
        if (args instanceof Vector) {
            return (Vector)args;
        }
        final Vector result = new Vector();
        result.addAll(args);
        return result;
    }
}

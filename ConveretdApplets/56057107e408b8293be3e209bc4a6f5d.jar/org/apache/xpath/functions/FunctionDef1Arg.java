// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.objects.XNodeSet;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;

public class FunctionDef1Arg extends FunctionOneArg
{
    public boolean canTraverseOutsideSubtree() {
        return super.m_arg0 != null && super.canTraverseOutsideSubtree();
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum > 1) {
            throw new WrongNumberArgsException("0 or 1");
        }
    }
    
    protected Node getArg0AsNode(final XPathContext xctxt) throws TransformerException {
        return (super.m_arg0 == null) ? xctxt.getCurrentNode() : super.m_arg0.execute(xctxt).nodeset().nextNode();
    }
    
    protected double getArg0AsNumber(final XPathContext xctxt) throws TransformerException {
        return (super.m_arg0 == null) ? XNodeSet.getNumberFromNode(xctxt.getCurrentNode()) : super.m_arg0.execute(xctxt).num();
    }
    
    protected String getArg0AsString(final XPathContext xctxt) throws TransformerException {
        return (super.m_arg0 == null) ? XNodeSet.getStringFromNode(xctxt.getCurrentNode()) : super.m_arg0.execute(xctxt).str();
    }
}

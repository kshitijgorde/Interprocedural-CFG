// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.res.XPATHMessages;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XString;
import org.apache.xml.utils.XMLString;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathContext;

public class FunctionDef1Arg extends FunctionOneArg
{
    protected int getArg0AsNode(final XPathContext xctxt) throws TransformerException {
        return (null == super.m_arg0) ? xctxt.getCurrentNode() : super.m_arg0.asNode(xctxt);
    }
    
    public boolean Arg0IsNodesetExpr() {
        return null == super.m_arg0 || super.m_arg0.isNodesetExpr();
    }
    
    protected XMLString getArg0AsString(final XPathContext xctxt) throws TransformerException {
        if (null != super.m_arg0) {
            return super.m_arg0.execute(xctxt).xstr();
        }
        final int currentNode = xctxt.getCurrentNode();
        if (-1 == currentNode) {
            return XString.EMPTYSTRING;
        }
        final DTM dtm = xctxt.getDTM(currentNode);
        return dtm.getStringValue(currentNode);
    }
    
    protected double getArg0AsNumber(final XPathContext xctxt) throws TransformerException {
        if (null != super.m_arg0) {
            return super.m_arg0.execute(xctxt).num();
        }
        final int currentNode = xctxt.getCurrentNode();
        if (-1 == currentNode) {
            return 0.0;
        }
        final DTM dtm = xctxt.getDTM(currentNode);
        final XMLString str = dtm.getStringValue(currentNode);
        return str.toDouble();
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum > 1) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("ER_ZERO_OR_ONE", null));
    }
    
    public boolean canTraverseOutsideSubtree() {
        return null != super.m_arg0 && super.canTraverseOutsideSubtree();
    }
}

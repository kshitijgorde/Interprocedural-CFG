// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import java.util.Vector;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.axes.SubContextList;
import org.apache.xpath.XPathContext;
import org.apache.xpath.compiler.Compiler;

public class FuncLast extends Function
{
    private boolean m_isTopLevel;
    
    public void postCompileStep(final Compiler compiler) {
        this.m_isTopLevel = (compiler.getLocationPathDepth() == -1);
    }
    
    public int getCountOfContextNodeList(final XPathContext xctxt) throws TransformerException {
        final SubContextList iter = this.m_isTopLevel ? null : xctxt.getSubContextList();
        if (null != iter) {
            return iter.getLastPos(xctxt);
        }
        final DTMIterator cnl = xctxt.getContextNodeList();
        int count;
        if (null != cnl) {
            count = cnl.getLength();
        }
        else {
            count = 0;
        }
        return count;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XNumber xnum = new XNumber(this.getCountOfContextNodeList(xctxt));
        return xnum;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
    }
}

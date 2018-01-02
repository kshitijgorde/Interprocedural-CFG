// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.axes.SubContextList;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xpath.XPathContext;
import org.apache.xpath.compiler.Compiler;

public class FuncPosition extends Function
{
    static final long serialVersionUID = -9092846348197271582L;
    private boolean m_isTopLevel;
    
    public void postCompileStep(final Compiler compiler) {
        this.m_isTopLevel = (compiler.getLocationPathDepth() == -1);
    }
    
    public int getPositionInContextNodeList(final XPathContext xctxt) {
        final SubContextList iter = this.m_isTopLevel ? null : xctxt.getSubContextList();
        if (null != iter) {
            final int prox = iter.getProximityPosition(xctxt);
            return prox;
        }
        DTMIterator cnl = xctxt.getContextNodeList();
        if (null != cnl) {
            int n = cnl.getCurrentNode();
            if (n == -1) {
                if (cnl.getCurrentPos() == 0) {
                    return 0;
                }
                try {
                    cnl = cnl.cloneWithReset();
                }
                catch (CloneNotSupportedException cnse) {
                    throw new WrappedRuntimeException(cnse);
                }
                final int currentNode = xctxt.getContextNode();
                while (-1 != (n = cnl.nextNode())) {
                    if (n == currentNode) {
                        break;
                    }
                }
            }
            return cnl.getCurrentPos();
        }
        return -1;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final double pos = this.getPositionInContextNodeList(xctxt);
        return new XNumber(pos);
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
    }
}

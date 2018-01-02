// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xpath.axes.LocPathIterator;
import org.apache.xpath.axes.SubContextList;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xalan.res.XSLMessages;
import org.apache.xpath.patterns.StepPattern;
import org.apache.xpath.axes.PredicatedNodeTest;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncCurrent extends Function
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final SubContextList subContextList = xctxt.getCurrentNodeList();
        int currentNode = -1;
        if (null != subContextList) {
            if (subContextList instanceof PredicatedNodeTest) {
                final LocPathIterator iter = ((PredicatedNodeTest)subContextList).getLocPathIterator();
                currentNode = iter.getCurrentContextNode();
            }
            else if (subContextList instanceof StepPattern) {
                throw new RuntimeException(XSLMessages.createMessage("ER_PROCESSOR_ERROR", null));
            }
        }
        else {
            currentNode = xctxt.getContextNode();
        }
        return new XNodeSet(currentNode, xctxt.getDTMManager());
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
    }
}

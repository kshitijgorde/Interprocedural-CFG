// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;
import javax.xml.transform.ErrorListener;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.Node;

public interface ExpressionContext
{
    Node getContextNode();
    
    NodeIterator getContextNodes();
    
    ErrorListener getErrorListener();
    
    double toNumber(final Node p0);
    
    String toString(final Node p0);
    
    XObject getVariableOrParam(final QName p0) throws TransformerException;
    
    XPathContext getXPathContext() throws TransformerException;
}

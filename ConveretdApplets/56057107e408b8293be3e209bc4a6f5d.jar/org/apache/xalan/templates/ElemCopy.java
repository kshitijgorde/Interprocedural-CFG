// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.DOMHelper;
import org.apache.xalan.transformer.ResultTreeHandler;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemCopy extends ElemUse
{
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            final short nodeType = sourceNode.getNodeType();
            if (nodeType != 9 && nodeType != 11) {
                final ResultTreeHandler rthandler = transformer.getResultTreeHandler();
                rthandler.cloneToResultTree(sourceNode, false);
                if (nodeType == 1) {
                    super.execute(transformer, sourceNode, mode);
                    rthandler.processNSDecls(sourceNode);
                    transformer.executeChildTemplates(this, sourceNode, mode);
                    final DOMHelper dhelper = transformer.getXPathContext().getDOMHelper();
                    final String ns = dhelper.getNamespaceOfNode(sourceNode);
                    final String localName = dhelper.getLocalNameOfNode(sourceNode);
                    transformer.getResultTreeHandler().endElement(ns, localName, sourceNode.getNodeName());
                }
                else if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
                }
            }
            else {
                if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
                }
                super.execute(transformer, sourceNode, mode);
                transformer.executeChildTemplates(this, sourceNode, mode);
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public String getNodeName() {
        return "copy";
    }
    
    public int getXSLToken() {
        return 9;
    }
}

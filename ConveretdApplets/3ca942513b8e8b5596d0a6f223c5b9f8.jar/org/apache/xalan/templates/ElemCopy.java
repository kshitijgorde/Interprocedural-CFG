// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.serialize.SerializerUtils;
import org.apache.xalan.transformer.ClonerToResultTree;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemCopy extends ElemUse
{
    static final long serialVersionUID = 5478580783896941384L;
    
    public int getXSLToken() {
        return 9;
    }
    
    public String getNodeName() {
        return "copy";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        try {
            final int sourceNode = xctxt.getCurrentNode();
            xctxt.pushCurrentNode(sourceNode);
            final DTM dtm = xctxt.getDTM(sourceNode);
            final short nodeType = dtm.getNodeType(sourceNode);
            if (9 != nodeType && 11 != nodeType) {
                final SerializationHandler rthandler = transformer.getSerializationHandler();
                if (transformer.getDebug()) {
                    transformer.getTraceManager().fireTraceEvent(this);
                }
                ClonerToResultTree.cloneToResultTree(sourceNode, nodeType, dtm, rthandler, false);
                if (1 == nodeType) {
                    super.execute(transformer);
                    SerializerUtils.processNSDecls(rthandler, sourceNode, nodeType, dtm);
                    transformer.executeChildTemplates(this, true);
                    final String ns = dtm.getNamespaceURI(sourceNode);
                    final String localName = dtm.getLocalName(sourceNode);
                    transformer.getResultTreeHandler().endElement(ns, localName, dtm.getNodeName(sourceNode));
                }
                if (transformer.getDebug()) {
                    transformer.getTraceManager().fireTraceEndEvent(this);
                }
            }
            else {
                if (transformer.getDebug()) {
                    transformer.getTraceManager().fireTraceEvent(this);
                }
                super.execute(transformer);
                transformer.executeChildTemplates(this, true);
                if (transformer.getDebug()) {
                    transformer.getTraceManager().fireTraceEndEvent(this);
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            xctxt.popCurrentNode();
        }
    }
}

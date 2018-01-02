// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xpath.DOMHelper;
import org.apache.xpath.XPathContext;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Comment;
import org.w3c.dom.CDATASection;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Attr;
import org.w3c.dom.Text;
import org.w3c.dom.Node;

public class ClonerToResultTree
{
    private ResultTreeHandler m_rth;
    private TransformerImpl m_transformer;
    
    public ClonerToResultTree(final TransformerImpl transformer, final ResultTreeHandler rth) {
        this.m_rth = rth;
        this.m_transformer = transformer;
    }
    
    public void cloneToResultTree(final Node node, final boolean shouldCloneAttributes) throws TransformerException {
        try {
            final boolean stripWhiteSpace = false;
            final XPathContext xctxt = this.m_transformer.getXPathContext();
            final DOMHelper dhelper = xctxt.getDOMHelper();
            switch (node.getNodeType()) {
                case 3: {
                    final Text tx = (Text)node;
                    String data = null;
                    if (stripWhiteSpace) {
                        if (!dhelper.isIgnorableWhitespace(tx)) {
                            data = tx.getData();
                            if (data != null && data.trim().length() == 0) {
                                data = null;
                            }
                        }
                    }
                    else {
                        final Node parent = node.getParentNode();
                        if (parent != null) {
                            if (parent.getNodeType() != 9) {
                                data = tx.getData();
                                if (data != null && data.length() == 0) {
                                    data = null;
                                }
                            }
                        }
                        else {
                            data = tx.getData();
                            if (data != null && data.length() == 0) {
                                data = null;
                            }
                        }
                    }
                    if (data == null) {
                        break;
                    }
                    if (dhelper.isIgnorableWhitespace(tx)) {
                        this.m_rth.ignorableWhitespace(data.toCharArray(), 0, data.length());
                        break;
                    }
                    this.m_rth.characters(data.toCharArray(), 0, data.length());
                    break;
                }
                case 2: {
                    if (!this.m_rth.isDefinedNSDecl((Attr)node)) {
                        final String ns = dhelper.getNamespaceOfNode(node);
                        final String localName = dhelper.getLocalNameOfNode(node);
                        this.m_rth.addAttribute(ns, localName, node.getNodeName(), "CDATA", ((Attr)node).getValue());
                        break;
                    }
                    break;
                }
                default: {
                    this.m_transformer.getMsgMgr().error(null, 56, new Object[] { node.getNodeName() });
                    break;
                }
                case 1: {
                    if (shouldCloneAttributes) {
                        this.m_rth.addAttributes(node);
                        this.m_rth.processNSDecls(node);
                    }
                    final String ns2 = dhelper.getNamespaceOfNode(node);
                    final String localName2 = dhelper.getLocalNameOfNode(node);
                    this.m_rth.startElement(ns2, localName2, node.getNodeName());
                    break;
                }
                case 4: {
                    this.m_rth.startCDATA();
                    final String data2 = ((CDATASection)node).getData();
                    this.m_rth.characters(data2.toCharArray(), 0, data2.length());
                    this.m_rth.endCDATA();
                    break;
                }
                case 8: {
                    this.m_rth.comment(((Comment)node).getData());
                    break;
                }
                case 5: {
                    final EntityReference er = (EntityReference)node;
                    this.m_rth.entityReference(er.getNodeName());
                    break;
                }
                case 7: {
                    final ProcessingInstruction pi = (ProcessingInstruction)node;
                    this.m_rth.processingInstruction(pi.getTarget(), pi.getData());
                }
                case 9:
                case 11: {
                    break;
                }
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
}

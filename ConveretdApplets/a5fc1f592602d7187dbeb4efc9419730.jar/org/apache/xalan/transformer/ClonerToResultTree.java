// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xml.utils.XMLString;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.xml.sax.ext.LexicalHandler;
import org.apache.xalan.serialize.SerializerUtils;
import org.xml.sax.ContentHandler;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.DTM;

public class ClonerToResultTree
{
    public static void cloneToResultTree(final int node, final int nodeType, final DTM dtm, final SerializationHandler rth, final boolean shouldCloneAttributes) throws TransformerException {
        try {
            switch (nodeType) {
                case 3: {
                    dtm.dispatchCharactersEvents(node, rth, false);
                    break;
                }
                case 9:
                case 11: {
                    break;
                }
                case 1: {
                    String ns = dtm.getNamespaceURI(node);
                    if (ns == null) {
                        ns = "";
                    }
                    final String localName = dtm.getLocalName(node);
                    rth.startElement(ns, localName, dtm.getNodeNameX(node));
                    if (shouldCloneAttributes) {
                        SerializerUtils.addAttributes(rth, node);
                        SerializerUtils.processNSDecls(rth, node, nodeType, dtm);
                    }
                    break;
                }
                case 4: {
                    rth.startCDATA();
                    dtm.dispatchCharactersEvents(node, rth, false);
                    rth.endCDATA();
                    break;
                }
                case 2: {
                    SerializerUtils.addAttribute(rth, node);
                    break;
                }
                case 13: {
                    SerializerUtils.processNSDecls(rth, node, 13, dtm);
                    break;
                }
                case 8: {
                    final XMLString xstr = dtm.getStringValue(node);
                    xstr.dispatchAsComment(rth);
                    break;
                }
                case 5: {
                    rth.entityReference(dtm.getNodeNameX(node));
                    break;
                }
                case 7: {
                    rth.processingInstruction(dtm.getNodeNameX(node), dtm.getNodeValue(node));
                    break;
                }
                default: {
                    throw new TransformerException("Can't clone node: " + dtm.getNodeName(node));
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
}

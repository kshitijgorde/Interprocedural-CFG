// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.apache.xml.serializer.NamespaceMappings;
import org.xml.sax.ContentHandler;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.XObject;
import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.xml.sax.SAXException;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.serializer.SerializationHandler;

public class SerializerUtils
{
    public static void addAttribute(final SerializationHandler handler, final int attr) throws TransformerException {
        final TransformerImpl transformer = (TransformerImpl)handler.getTransformer();
        final DTM dtm = transformer.getXPathContext().getDTM(attr);
        if (isDefinedNSDecl(handler, attr, dtm)) {
            return;
        }
        String ns = dtm.getNamespaceURI(attr);
        if (ns == null) {
            ns = "";
        }
        try {
            handler.addAttribute(ns, dtm.getLocalName(attr), dtm.getNodeName(attr), "CDATA", dtm.getNodeValue(attr), false);
        }
        catch (SAXException ex) {}
    }
    
    public static void addAttributes(final SerializationHandler handler, final int src) throws TransformerException {
        final TransformerImpl transformer = (TransformerImpl)handler.getTransformer();
        final DTM dtm = transformer.getXPathContext().getDTM(src);
        for (int node = dtm.getFirstAttribute(src); -1 != node; node = dtm.getNextAttribute(node)) {
            addAttribute(handler, node);
        }
    }
    
    public static void outputResultTreeFragment(final SerializationHandler handler, final XObject obj, final XPathContext support) throws SAXException {
        final int doc = obj.rtf();
        final DTM dtm = support.getDTM(doc);
        if (null != dtm) {
            for (int n = dtm.getFirstChild(doc); -1 != n; n = dtm.getNextSibling(n)) {
                handler.flushPending();
                if (dtm.getNodeType(n) == 1 && dtm.getNamespaceURI(n) == null) {
                    handler.startPrefixMapping("", "");
                }
                dtm.dispatchToEvents(n, handler);
            }
        }
    }
    
    public static void processNSDecls(final SerializationHandler handler, final int src, final int type, final DTM dtm) throws TransformerException {
        try {
            if (type == 1) {
                for (int namespace = dtm.getFirstNamespaceNode(src, true); -1 != namespace; namespace = dtm.getNextNamespaceNode(src, namespace, true)) {
                    final String prefix = dtm.getNodeNameX(namespace);
                    final String desturi = handler.getNamespaceURIFromPrefix(prefix);
                    final String srcURI = dtm.getNodeValue(namespace);
                    if (!srcURI.equalsIgnoreCase(desturi)) {
                        handler.startPrefixMapping(prefix, srcURI, false);
                    }
                }
            }
            else if (type == 13) {
                final String prefix2 = dtm.getNodeNameX(src);
                final String desturi2 = handler.getNamespaceURIFromPrefix(prefix2);
                final String srcURI2 = dtm.getNodeValue(src);
                if (!srcURI2.equalsIgnoreCase(desturi2)) {
                    handler.startPrefixMapping(prefix2, srcURI2, false);
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    public static boolean isDefinedNSDecl(final SerializationHandler serializer, final int attr, final DTM dtm) {
        if (13 == dtm.getNodeType(attr)) {
            final String prefix = dtm.getNodeNameX(attr);
            final String uri = serializer.getNamespaceURIFromPrefix(prefix);
            if (null != uri && uri.equals(dtm.getStringValue(attr))) {
                return true;
            }
        }
        return false;
    }
    
    public static void ensureNamespaceDeclDeclared(final SerializationHandler handler, final DTM dtm, final int namespace) throws SAXException {
        final String uri = dtm.getNodeValue(namespace);
        final String prefix = dtm.getNodeNameX(namespace);
        if (uri != null && uri.length() > 0 && null != prefix) {
            final NamespaceMappings ns = handler.getNamespaceMappings();
            if (ns != null) {
                final String foundURI = ns.lookupNamespace(prefix);
                if (null == foundURI || !foundURI.equals(uri)) {
                    handler.startPrefixMapping(prefix, uri, false);
                }
            }
        }
    }
}

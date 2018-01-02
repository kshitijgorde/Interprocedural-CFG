// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.jdom.DocType;
import org.jdom.EntityRef;
import org.jdom.Attribute;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Attr;
import org.jdom.Content;
import org.jdom.Parent;
import org.jdom.Namespace;
import org.w3c.dom.Node;
import org.jdom.Element;
import org.w3c.dom.Document;
import org.jdom.DefaultJDOMFactory;
import org.jdom.JDOMFactory;

public class DOMBuilder
{
    private static final String CVS_ID = "@(#) $RCSfile: DOMBuilder.java,v $ $Revision: 1.60 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
    private String adapterClass;
    private JDOMFactory factory;
    
    public DOMBuilder() {
        this.factory = new DefaultJDOMFactory();
    }
    
    public DOMBuilder(final String adapterClass) {
        this.factory = new DefaultJDOMFactory();
        this.adapterClass = adapterClass;
    }
    
    public void setFactory(final JDOMFactory factory) {
        this.factory = factory;
    }
    
    public JDOMFactory getFactory() {
        return this.factory;
    }
    
    public org.jdom.Document build(final Document domDocument) {
        final org.jdom.Document doc = this.factory.document(null);
        this.buildTree(domDocument, doc, null, true);
        return doc;
    }
    
    public Element build(final org.w3c.dom.Element domElement) {
        final org.jdom.Document doc = this.factory.document(null);
        this.buildTree(domElement, doc, null, true);
        return doc.getRootElement();
    }
    
    private void buildTree(final Node node, final org.jdom.Document doc, final Element current, final boolean atRoot) {
        switch (node.getNodeType()) {
            case 9: {
                final NodeList nodes = node.getChildNodes();
                for (int i = 0, size = nodes.getLength(); i < size; ++i) {
                    this.buildTree(nodes.item(i), doc, current, true);
                }
                break;
            }
            case 1: {
                final String nodeName = node.getNodeName();
                String prefix = "";
                String localName = nodeName;
                int colon = nodeName.indexOf(58);
                if (colon >= 0) {
                    prefix = nodeName.substring(0, colon);
                    localName = nodeName.substring(colon + 1);
                }
                Namespace ns = null;
                final String uri = node.getNamespaceURI();
                if (uri == null) {
                    ns = ((current == null) ? Namespace.NO_NAMESPACE : current.getNamespace(prefix));
                }
                else {
                    ns = Namespace.getNamespace(prefix, uri);
                }
                final Element element = this.factory.element(localName, ns);
                if (atRoot) {
                    doc.setRootElement(element);
                }
                else {
                    this.factory.addContent(current, element);
                }
                final NamedNodeMap attributeList = node.getAttributes();
                final int attsize = attributeList.getLength();
                for (int j = 0; j < attsize; ++j) {
                    final Attr att = (Attr)attributeList.item(j);
                    final String attname = att.getName();
                    if (attname.startsWith("xmlns")) {
                        String attPrefix = "";
                        colon = attname.indexOf(58);
                        if (colon >= 0) {
                            attPrefix = attname.substring(colon + 1);
                        }
                        final String attvalue = att.getValue();
                        final Namespace declaredNS = Namespace.getNamespace(attPrefix, attvalue);
                        if (prefix.equals(attPrefix)) {
                            element.setNamespace(declaredNS);
                        }
                        else {
                            this.factory.addNamespaceDeclaration(element, declaredNS);
                        }
                    }
                }
                for (int j = 0; j < attsize; ++j) {
                    final Attr att = (Attr)attributeList.item(j);
                    final String attname = att.getName();
                    if (!attname.startsWith("xmlns")) {
                        String attPrefix = "";
                        String attLocalName = attname;
                        colon = attname.indexOf(58);
                        if (colon >= 0) {
                            attPrefix = attname.substring(0, colon);
                            attLocalName = attname.substring(colon + 1);
                        }
                        final String attvalue2 = att.getValue();
                        Namespace attns = null;
                        if ("".equals(attPrefix)) {
                            attns = Namespace.NO_NAMESPACE;
                        }
                        else {
                            attns = element.getNamespace(attPrefix);
                        }
                        final Attribute attribute = this.factory.attribute(attLocalName, attvalue2, attns);
                        this.factory.setAttribute(element, attribute);
                    }
                }
                final NodeList children = node.getChildNodes();
                if (children != null) {
                    for (int size2 = children.getLength(), k = 0; k < size2; ++k) {
                        final Node item = children.item(k);
                        if (item != null) {
                            this.buildTree(item, doc, element, false);
                        }
                    }
                    break;
                }
                break;
            }
            case 3: {
                final String data = node.getNodeValue();
                this.factory.addContent(current, this.factory.text(data));
                break;
            }
            case 4: {
                final String cdata = node.getNodeValue();
                this.factory.addContent(current, this.factory.cdata(cdata));
                break;
            }
            case 7: {
                if (atRoot) {
                    this.factory.addContent(doc, this.factory.processingInstruction(node.getNodeName(), node.getNodeValue()));
                    break;
                }
                this.factory.addContent(current, this.factory.processingInstruction(node.getNodeName(), node.getNodeValue()));
                break;
            }
            case 8: {
                if (atRoot) {
                    this.factory.addContent(doc, this.factory.comment(node.getNodeValue()));
                    break;
                }
                this.factory.addContent(current, this.factory.comment(node.getNodeValue()));
                break;
            }
            case 5: {
                final EntityRef entity = this.factory.entityRef(node.getNodeName());
                this.factory.addContent(current, entity);
            }
            case 10: {
                final DocumentType domDocType = (DocumentType)node;
                final String publicID = domDocType.getPublicId();
                final String systemID = domDocType.getSystemId();
                final String internalDTD = domDocType.getInternalSubset();
                final DocType docType = this.factory.docType(domDocType.getName());
                docType.setPublicID(publicID);
                docType.setSystemID(systemID);
                docType.setInternalSubset(internalDTD);
                this.factory.addContent(doc, docType);
                break;
            }
        }
    }
}

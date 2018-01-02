// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.output;

import org.w3c.dom.Attr;
import org.w3c.dom.EntityReference;
import org.w3c.dom.CDATASection;
import org.jdom.EntityRef;
import org.jdom.Text;
import org.jdom.CDATA;
import org.jdom.Attribute;
import org.jdom.Namespace;
import org.jdom.adapters.DOMAdapter;
import java.util.Iterator;
import org.jdom.JDOMException;
import org.jdom.DocType;
import org.jdom.ProcessingInstruction;
import org.jdom.Comment;
import org.w3c.dom.Node;
import org.jdom.Element;
import org.jdom.Document;

public class DOMOutputter
{
    private static final String CVS_ID = "@(#) $RCSfile: DOMOutputter.java,v $ $Revision: 1.43 $ $Date: 2007/11/10 05:29:01 $ $Name: jdom_1_1_1 $";
    private static final String DEFAULT_ADAPTER_CLASS = "org.jdom.adapters.XercesDOMAdapter";
    private String adapterClass;
    private boolean forceNamespaceAware;
    
    public DOMOutputter() {
    }
    
    public DOMOutputter(final String adapterClass) {
        this.adapterClass = adapterClass;
    }
    
    public void setForceNamespaceAware(final boolean flag) {
        this.forceNamespaceAware = flag;
    }
    
    public boolean getForceNamespaceAware() {
        return this.forceNamespaceAware;
    }
    
    public org.w3c.dom.Document output(final Document document) throws JDOMException {
        final NamespaceStack namespaces = new NamespaceStack();
        org.w3c.dom.Document domDoc = null;
        try {
            final DocType dt = document.getDocType();
            domDoc = this.createDOMDocument(dt);
            for (final Object node : document.getContent()) {
                if (node instanceof Element) {
                    final Element element = (Element)node;
                    final org.w3c.dom.Element domElement = this.output(element, domDoc, namespaces);
                    final org.w3c.dom.Element root = domDoc.getDocumentElement();
                    if (root == null) {
                        domDoc.appendChild(domElement);
                    }
                    else {
                        domDoc.replaceChild(domElement, root);
                    }
                }
                else if (node instanceof Comment) {
                    final Comment comment = (Comment)node;
                    final org.w3c.dom.Comment domComment = domDoc.createComment(comment.getText());
                    domDoc.appendChild(domComment);
                }
                else if (node instanceof ProcessingInstruction) {
                    final ProcessingInstruction pi = (ProcessingInstruction)node;
                    final org.w3c.dom.ProcessingInstruction domPI = domDoc.createProcessingInstruction(pi.getTarget(), pi.getData());
                    domDoc.appendChild(domPI);
                }
                else {
                    if (node instanceof DocType) {
                        continue;
                    }
                    throw new JDOMException("Document contained top-level content with type:" + node.getClass().getName());
                }
            }
        }
        catch (Throwable e) {
            throw new JDOMException("Exception outputting Document", e);
        }
        return domDoc;
    }
    
    private org.w3c.dom.Document createDOMDocument(final DocType dt) throws JDOMException {
        Label_0070: {
            if (this.adapterClass != null) {
                try {
                    final DOMAdapter adapter = (DOMAdapter)Class.forName(this.adapterClass).newInstance();
                    return adapter.createDocument(dt);
                }
                catch (ClassNotFoundException e) {
                    break Label_0070;
                }
                catch (IllegalAccessException e2) {
                    break Label_0070;
                }
                catch (InstantiationException e3) {
                    break Label_0070;
                }
            }
            try {
                final DOMAdapter adapter = (DOMAdapter)Class.forName("org.jdom.adapters.JAXPDOMAdapter").newInstance();
                return adapter.createDocument(dt);
            }
            catch (ClassNotFoundException e) {}
            catch (IllegalAccessException e2) {}
            catch (InstantiationException ex) {}
            try {
                final DOMAdapter adapter = (DOMAdapter)Class.forName("org.jdom.adapters.XercesDOMAdapter").newInstance();
                return adapter.createDocument(dt);
            }
            catch (ClassNotFoundException e) {}
            catch (IllegalAccessException e2) {}
            catch (InstantiationException ex2) {}
        }
        throw new JDOMException("No JAXP or default parser available");
    }
    
    private org.w3c.dom.Element output(final Element element, final org.w3c.dom.Document domDoc, final NamespaceStack namespaces) throws JDOMException {
        try {
            final int previouslyDeclaredNamespaces = namespaces.size();
            org.w3c.dom.Element domElement = null;
            if (element.getNamespace() == Namespace.NO_NAMESPACE) {
                domElement = (this.forceNamespaceAware ? domDoc.createElementNS(null, element.getQualifiedName()) : domDoc.createElement(element.getQualifiedName()));
            }
            else {
                domElement = domDoc.createElementNS(element.getNamespaceURI(), element.getQualifiedName());
            }
            final Namespace ns = element.getNamespace();
            if (ns != Namespace.XML_NAMESPACE && (ns != Namespace.NO_NAMESPACE || namespaces.getURI("") != null)) {
                final String prefix = ns.getPrefix();
                final String uri = namespaces.getURI(prefix);
                if (!ns.getURI().equals(uri)) {
                    namespaces.push(ns);
                    final String attrName = getXmlnsTagFor(ns);
                    domElement.setAttribute(attrName, ns.getURI());
                }
            }
            for (final Namespace additional : element.getAdditionalNamespaces()) {
                final String prefix2 = additional.getPrefix();
                final String uri2 = namespaces.getURI(prefix2);
                if (!additional.getURI().equals(uri2)) {
                    final String attrName2 = getXmlnsTagFor(additional);
                    domElement.setAttribute(attrName2, additional.getURI());
                    namespaces.push(additional);
                }
            }
            for (final Attribute attribute : element.getAttributes()) {
                domElement.setAttributeNode(this.output(attribute, domDoc));
                final Namespace ns2 = attribute.getNamespace();
                if (ns2 != Namespace.NO_NAMESPACE && ns2 != Namespace.XML_NAMESPACE) {
                    final String prefix3 = ns2.getPrefix();
                    final String uri3 = namespaces.getURI(prefix3);
                    if (!ns2.getURI().equals(uri3)) {
                        final String attrName3 = getXmlnsTagFor(ns2);
                        domElement.setAttribute(attrName3, ns2.getURI());
                        namespaces.push(ns2);
                    }
                }
                if (attribute.getNamespace() == Namespace.NO_NAMESPACE) {
                    if (this.forceNamespaceAware) {
                        domElement.setAttributeNS(null, attribute.getQualifiedName(), attribute.getValue());
                    }
                    else {
                        domElement.setAttribute(attribute.getQualifiedName(), attribute.getValue());
                    }
                }
                else {
                    domElement.setAttributeNS(attribute.getNamespaceURI(), attribute.getQualifiedName(), attribute.getValue());
                }
            }
            for (final Object node : element.getContent()) {
                if (node instanceof Element) {
                    final Element e = (Element)node;
                    final org.w3c.dom.Element domElt = this.output(e, domDoc, namespaces);
                    domElement.appendChild(domElt);
                }
                else if (node instanceof String) {
                    final String str = (String)node;
                    final org.w3c.dom.Text domText = domDoc.createTextNode(str);
                    domElement.appendChild(domText);
                }
                else if (node instanceof CDATA) {
                    final CDATA cdata = (CDATA)node;
                    final CDATASection domCdata = domDoc.createCDATASection(cdata.getText());
                    domElement.appendChild(domCdata);
                }
                else if (node instanceof Text) {
                    final Text text = (Text)node;
                    final org.w3c.dom.Text domText = domDoc.createTextNode(text.getText());
                    domElement.appendChild(domText);
                }
                else if (node instanceof Comment) {
                    final Comment comment = (Comment)node;
                    final org.w3c.dom.Comment domComment = domDoc.createComment(comment.getText());
                    domElement.appendChild(domComment);
                }
                else if (node instanceof ProcessingInstruction) {
                    final ProcessingInstruction pi = (ProcessingInstruction)node;
                    final org.w3c.dom.ProcessingInstruction domPI = domDoc.createProcessingInstruction(pi.getTarget(), pi.getData());
                    domElement.appendChild(domPI);
                }
                else {
                    if (!(node instanceof EntityRef)) {
                        throw new JDOMException("Element contained content with type:" + node.getClass().getName());
                    }
                    final EntityRef entity = (EntityRef)node;
                    final EntityReference domEntity = domDoc.createEntityReference(entity.getName());
                    domElement.appendChild(domEntity);
                }
            }
            while (namespaces.size() > previouslyDeclaredNamespaces) {
                namespaces.pop();
            }
            return domElement;
        }
        catch (Exception e2) {
            throw new JDOMException("Exception outputting Element " + element.getQualifiedName(), e2);
        }
    }
    
    private Attr output(final Attribute attribute, final org.w3c.dom.Document domDoc) throws JDOMException {
        Attr domAttr = null;
        try {
            if (attribute.getNamespace() == Namespace.NO_NAMESPACE) {
                if (this.forceNamespaceAware) {
                    domAttr = domDoc.createAttributeNS(null, attribute.getQualifiedName());
                }
                else {
                    domAttr = domDoc.createAttribute(attribute.getQualifiedName());
                }
            }
            else {
                domAttr = domDoc.createAttributeNS(attribute.getNamespaceURI(), attribute.getQualifiedName());
            }
            domAttr.setValue(attribute.getValue());
        }
        catch (Exception e) {
            throw new JDOMException("Exception outputting Attribute " + attribute.getQualifiedName(), e);
        }
        return domAttr;
    }
    
    private static String getXmlnsTagFor(final Namespace ns) {
        String attrName = "xmlns";
        if (!ns.getPrefix().equals("")) {
            attrName += ":";
            attrName += ns.getPrefix();
        }
        return attrName;
    }
}

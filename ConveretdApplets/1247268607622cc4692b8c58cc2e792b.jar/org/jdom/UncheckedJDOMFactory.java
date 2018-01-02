// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.ArrayList;
import java.util.Map;

public class UncheckedJDOMFactory implements JDOMFactory
{
    public Element element(final String name, Namespace namespace) {
        final Element e = new Element();
        e.name = name;
        if (namespace == null) {
            namespace = Namespace.NO_NAMESPACE;
        }
        e.namespace = namespace;
        return e;
    }
    
    public Element element(final String name) {
        final Element e = new Element();
        e.name = name;
        e.namespace = Namespace.NO_NAMESPACE;
        return e;
    }
    
    public Element element(final String name, final String uri) {
        return this.element(name, Namespace.getNamespace("", uri));
    }
    
    public Element element(final String name, final String prefix, final String uri) {
        return this.element(name, Namespace.getNamespace(prefix, uri));
    }
    
    public Attribute attribute(final String name, final String value, Namespace namespace) {
        final Attribute a = new Attribute();
        a.name = name;
        a.value = value;
        if (namespace == null) {
            namespace = Namespace.NO_NAMESPACE;
        }
        a.namespace = namespace;
        return a;
    }
    
    public Attribute attribute(final String name, final String value, final int type, Namespace namespace) {
        final Attribute a = new Attribute();
        a.name = name;
        a.type = type;
        a.value = value;
        if (namespace == null) {
            namespace = Namespace.NO_NAMESPACE;
        }
        a.namespace = namespace;
        return a;
    }
    
    public Attribute attribute(final String name, final String value) {
        final Attribute a = new Attribute();
        a.name = name;
        a.value = value;
        a.namespace = Namespace.NO_NAMESPACE;
        return a;
    }
    
    public Attribute attribute(final String name, final String value, final int type) {
        final Attribute a = new Attribute();
        a.name = name;
        a.type = type;
        a.value = value;
        a.namespace = Namespace.NO_NAMESPACE;
        return a;
    }
    
    public Text text(final String str) {
        final Text t = new Text();
        t.value = str;
        return t;
    }
    
    public CDATA cdata(final String str) {
        final CDATA c = new CDATA();
        c.value = str;
        return c;
    }
    
    public Comment comment(final String str) {
        final Comment c = new Comment();
        c.text = str;
        return c;
    }
    
    public ProcessingInstruction processingInstruction(final String target, final Map data) {
        final ProcessingInstruction p = new ProcessingInstruction();
        p.target = target;
        p.setData(data);
        return p;
    }
    
    public ProcessingInstruction processingInstruction(final String target, final String data) {
        final ProcessingInstruction p = new ProcessingInstruction();
        p.target = target;
        p.setData(data);
        return p;
    }
    
    public EntityRef entityRef(final String name) {
        final EntityRef e = new EntityRef();
        e.name = name;
        return e;
    }
    
    public EntityRef entityRef(final String name, final String systemID) {
        final EntityRef e = new EntityRef();
        e.name = name;
        e.systemID = systemID;
        return e;
    }
    
    public EntityRef entityRef(final String name, final String publicID, final String systemID) {
        final EntityRef e = new EntityRef();
        e.name = name;
        e.publicID = publicID;
        e.systemID = systemID;
        return e;
    }
    
    public DocType docType(final String elementName, final String publicID, final String systemID) {
        final DocType d = new DocType();
        d.elementName = elementName;
        d.publicID = publicID;
        d.systemID = systemID;
        return d;
    }
    
    public DocType docType(final String elementName, final String systemID) {
        return this.docType(elementName, null, systemID);
    }
    
    public DocType docType(final String elementName) {
        return this.docType(elementName, null, null);
    }
    
    public Document document(final Element rootElement, final DocType docType, final String baseURI) {
        final Document d = new Document();
        if (docType != null) {
            this.addContent(d, docType);
        }
        if (rootElement != null) {
            this.addContent(d, rootElement);
        }
        if (baseURI != null) {
            d.baseURI = baseURI;
        }
        return d;
    }
    
    public Document document(final Element rootElement, final DocType docType) {
        return this.document(rootElement, docType, null);
    }
    
    public Document document(final Element rootElement) {
        return this.document(rootElement, null, null);
    }
    
    public void addContent(final Parent parent, final Content child) {
        if (parent instanceof Element) {
            final Element elt = (Element)parent;
            elt.content.uncheckedAddContent(child);
        }
        else {
            final Document doc = (Document)parent;
            doc.content.uncheckedAddContent(child);
        }
    }
    
    public void setAttribute(final Element parent, final Attribute a) {
        parent.attributes.uncheckedAddAttribute(a);
    }
    
    public void addNamespaceDeclaration(final Element parent, final Namespace additional) {
        if (parent.additionalNamespaces == null) {
            parent.additionalNamespaces = new ArrayList(5);
        }
        parent.additionalNamespaces.add(additional);
    }
}

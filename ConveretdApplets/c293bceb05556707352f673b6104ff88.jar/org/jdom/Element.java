// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import com.sun.java.util.collections.Iterator;
import com.sun.java.util.collections.ListIterator;
import org.jdom.filter.ElementFilter;
import com.sun.java.util.collections.Collection;
import org.jdom.filter.Filter;
import com.sun.java.util.collections.Collections;
import com.sun.java.util.collections.ArrayList;
import com.sun.java.util.collections.List;
import java.io.Serializable;

public class Element implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: Element.java,v $ $Revision: 1.115 $ $Date: 2002/03/28 11:08:12 $ $Name: jdom_1_0_b8 $";
    private static final int INITIAL_ARRAY_SIZE = 5;
    protected String name;
    protected transient Namespace namespace;
    protected transient List additionalNamespaces;
    protected Object parent;
    protected AttributeList attributes;
    protected ContentList content;
    
    protected Element() {
        this.attributes = new AttributeList(this);
        this.content = new ContentList(this);
    }
    
    public Element(final String name, final Namespace namespace) {
        this.attributes = new AttributeList(this);
        this.content = new ContentList(this);
        this.setName(name);
        this.setNamespace(namespace);
    }
    
    public Element(final String name) {
        this(name, (Namespace)null);
    }
    
    public Element(final String name, final String uri) {
        this(name, Namespace.getNamespace("", uri));
    }
    
    public Element(final String name, final String prefix, final String uri) {
        this(name, Namespace.getNamespace(prefix, uri));
    }
    
    public String getName() {
        return this.name;
    }
    
    public Element setName(final String name) {
        final String reason = Verifier.checkElementName(name);
        if (reason != null) {
            throw new IllegalNameException(name, "element", reason);
        }
        this.name = name;
        return this;
    }
    
    public Namespace getNamespace() {
        return this.namespace;
    }
    
    public Element setNamespace(Namespace namespace) {
        if (namespace == null) {
            namespace = Namespace.NO_NAMESPACE;
        }
        this.namespace = namespace;
        return this;
    }
    
    public String getNamespacePrefix() {
        return this.namespace.getPrefix();
    }
    
    public String getNamespaceURI() {
        return this.namespace.getURI();
    }
    
    public Namespace getNamespace(final String prefix) {
        if (prefix == null) {
            return null;
        }
        if (prefix.equals(this.getNamespacePrefix())) {
            return this.getNamespace();
        }
        if (this.additionalNamespaces != null) {
            for (int i = 0; i < this.additionalNamespaces.size(); ++i) {
                final Namespace ns = (Namespace)this.additionalNamespaces.get(i);
                if (prefix.equals(ns.getPrefix())) {
                    return ns;
                }
            }
        }
        if (this.parent instanceof Element) {
            return ((Element)this.parent).getNamespace(prefix);
        }
        return null;
    }
    
    public String getQualifiedName() {
        if (this.namespace.getPrefix().equals("")) {
            return this.getName();
        }
        return this.namespace.getPrefix() + ":" + this.name;
    }
    
    public void addNamespaceDeclaration(final Namespace additional) {
        final String reason = Verifier.checkNamespaceCollision(additional, this);
        if (reason != null) {
            throw new IllegalAddException(this, additional, reason);
        }
        if (this.additionalNamespaces == null) {
            this.additionalNamespaces = new ArrayList(5);
        }
        this.additionalNamespaces.add(additional);
    }
    
    public void removeNamespaceDeclaration(final Namespace additionalNamespace) {
        if (this.additionalNamespaces == null) {
            return;
        }
        this.additionalNamespaces.remove(additionalNamespace);
    }
    
    public List getAdditionalNamespaces() {
        if (this.additionalNamespaces == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(this.additionalNamespaces);
    }
    
    public Element getParent() {
        if (this.parent instanceof Element) {
            return (Element)this.parent;
        }
        return null;
    }
    
    protected Element setParent(final Element parent) {
        this.parent = parent;
        return this;
    }
    
    public Element detach() {
        if (this.parent instanceof Element) {
            ((Element)this.parent).removeContent(this);
        }
        else if (this.parent instanceof Document) {
            ((Document)this.parent).detachRootElement();
        }
        return this;
    }
    
    public boolean isRootElement() {
        return this.parent instanceof Document;
    }
    
    protected Element setDocument(final Document document) {
        this.parent = document;
        return this;
    }
    
    public Document getDocument() {
        if (this.parent instanceof Document) {
            return (Document)this.parent;
        }
        if (this.parent instanceof Element) {
            return ((Element)this.parent).getDocument();
        }
        return null;
    }
    
    public String getText() {
        if (this.content.size() == 0) {
            return "";
        }
        if (this.content.size() == 1) {
            final Object obj = this.content.get(0);
            if (obj instanceof Text) {
                return ((Text)obj).getText();
            }
            if (obj instanceof CDATA) {
                return ((CDATA)obj).getText();
            }
            return "";
        }
        else {
            final StringBuffer textContent = new StringBuffer();
            boolean hasText = false;
            for (int i = 0; i < this.content.size(); ++i) {
                final Object obj2 = this.content.get(i);
                if (obj2 instanceof Text) {
                    textContent.append(((Text)obj2).getText());
                    hasText = true;
                }
                else if (obj2 instanceof CDATA) {
                    textContent.append(((CDATA)obj2).getText());
                    hasText = true;
                }
            }
            if (!hasText) {
                return "";
            }
            return textContent.toString();
        }
    }
    
    public String getTextTrim() {
        return this.getText().trim();
    }
    
    public String getTextNormalize() {
        return Text.normalizeString(this.getText());
    }
    
    public String getChildText(final String name) {
        final Element child = this.getChild(name);
        if (child == null) {
            return null;
        }
        return child.getText();
    }
    
    public String getChildTextTrim(final String name) {
        final Element child = this.getChild(name);
        if (child == null) {
            return null;
        }
        return child.getTextTrim();
    }
    
    public String getChildTextNormalize(final String name) {
        final Element child = this.getChild(name);
        if (child == null) {
            return null;
        }
        return child.getTextNormalize();
    }
    
    public String getChildText(final String name, final Namespace ns) {
        final Element child = this.getChild(name, ns);
        if (child == null) {
            return null;
        }
        return child.getText();
    }
    
    public String getChildTextTrim(final String name, final Namespace ns) {
        final Element child = this.getChild(name, ns);
        if (child == null) {
            return null;
        }
        return child.getTextTrim();
    }
    
    public String getChildTextNormalize(final String name, final Namespace ns) {
        final Element child = this.getChild(name, ns);
        if (child == null) {
            return null;
        }
        return child.getTextNormalize();
    }
    
    public Element setText(final String text) {
        this.content.clear();
        if (text != null) {
            this.addContent(new Text(text));
        }
        return this;
    }
    
    public List getContent() {
        return this.content;
    }
    
    public List getContent(final Filter filter) {
        return this.content.getView(filter);
    }
    
    public Element setContent(final List newContent) {
        this.content.clearAndSet(newContent);
        return this;
    }
    
    public boolean hasChildren() {
        for (int i = 0; i < this.content.size(); ++i) {
            if (this.content.get(i) instanceof Element) {
                return true;
            }
        }
        return false;
    }
    
    public List getChildren() {
        return this.content.getView(new ElementFilter());
    }
    
    public Element setChildren(final List children) {
        final List list = this.content.getView(new ElementFilter());
        final int size = list.size();
        try {
            list.addAll(children);
        }
        catch (RuntimeException exception) {
            this.removeRange(list, size, list.size());
            throw exception;
        }
        this.removeRange(list, 0, size);
        return this;
    }
    
    private void removeRange(final List list, final int start, final int end) {
        final ListIterator i = list.listIterator(start);
        for (int j = 0; j < end - start; ++j) {
            i.next();
            i.remove();
        }
    }
    
    public List getChildren(final String name) {
        return this.getChildren(name, Namespace.NO_NAMESPACE);
    }
    
    public List getChildren(final String name, final Namespace ns) {
        return this.content.getView(new ElementFilter(name, ns));
    }
    
    public Element getChild(final String name, final Namespace ns) {
        final List elements = this.content.getView(new ElementFilter(name, ns));
        final Iterator i = elements.iterator();
        if (i.hasNext()) {
            return (Element)i.next();
        }
        return null;
    }
    
    public Element getChild(final String name) {
        return this.getChild(name, Namespace.NO_NAMESPACE);
    }
    
    public Element addContent(final String str) {
        return this.addContent(new Text(str));
    }
    
    public Element addContent(final Text text) {
        this.content.add(text);
        return this;
    }
    
    public Element addContent(final CDATA cdata) {
        this.content.add(cdata);
        return this;
    }
    
    public Element addContent(final Element element) {
        this.content.add(element);
        return this;
    }
    
    public Element addContent(final ProcessingInstruction pi) {
        this.content.add(pi);
        return this;
    }
    
    public Element addContent(final EntityRef entity) {
        this.content.add(entity);
        return this;
    }
    
    public Element addContent(final Comment comment) {
        this.content.add(comment);
        return this;
    }
    
    public boolean isAncestor(final Element element) {
        for (Object p = this.parent; p instanceof Element; p = ((Element)p).getParent()) {
            if (p == element) {
                return true;
            }
        }
        return false;
    }
    
    public boolean removeChild(final String name) {
        return this.removeChild(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeChild(final String name, final Namespace ns) {
        final List old = this.content.getView(new ElementFilter(name, ns));
        final Iterator i = old.iterator();
        if (i.hasNext()) {
            i.next();
            i.remove();
            return true;
        }
        return false;
    }
    
    public boolean removeChildren(final String name) {
        return this.removeChildren(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeChildren(final String name, final Namespace ns) {
        boolean deletedSome = false;
        final List old = this.content.getView(new ElementFilter(name, ns));
        final Iterator i = old.iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
            deletedSome = true;
        }
        return deletedSome;
    }
    
    public boolean removeChildren() {
        boolean deletedSome = false;
        final List old = this.content.getView(new ElementFilter());
        final Iterator i = old.iterator();
        while (i.hasNext()) {
            i.next();
            i.remove();
            deletedSome = true;
        }
        return deletedSome;
    }
    
    public List getAttributes() {
        return this.attributes;
    }
    
    public Attribute getAttribute(final String name) {
        return (Attribute)this.attributes.get(name, Namespace.NO_NAMESPACE);
    }
    
    public Attribute getAttribute(final String name, final Namespace ns) {
        return (Attribute)this.attributes.get(name, ns);
    }
    
    public String getAttributeValue(final String name) {
        final Attribute attribute = (Attribute)this.attributes.get(name, Namespace.NO_NAMESPACE);
        return (attribute == null) ? null : attribute.getValue();
    }
    
    public String getAttributeValue(final String name, final Namespace ns, final String def) {
        final Attribute attribute = (Attribute)this.attributes.get(name, ns);
        return (attribute == null) ? def : attribute.getValue();
    }
    
    public String getAttributeValue(final String name, final String def) {
        final Attribute attribute = (Attribute)this.attributes.get(name, Namespace.NO_NAMESPACE);
        return (attribute == null) ? def : attribute.getValue();
    }
    
    public String getAttributeValue(final String name, final Namespace ns) {
        final Attribute attribute = (Attribute)this.attributes.get(name, ns);
        return (attribute == null) ? null : attribute.getValue();
    }
    
    public Element setAttributes(final List newAttributes) {
        this.attributes.clearAndSet(newAttributes);
        return this;
    }
    
    public Element setAttribute(final String name, final String value) {
        return this.setAttribute(new Attribute(name, value));
    }
    
    public Element setAttribute(final String name, final String value, final Namespace ns) {
        return this.setAttribute(new Attribute(name, value, ns));
    }
    
    public Element setAttribute(final Attribute attribute) {
        this.attributes.add(attribute);
        return this;
    }
    
    public boolean removeAttribute(final String name) {
        return this.attributes.remove(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeAttribute(final String name, final Namespace ns) {
        return this.attributes.remove(name, ns);
    }
    
    public boolean removeAttribute(final Attribute attribute) {
        return this.attributes.remove(attribute);
    }
    
    public boolean removeContent(final Element element) {
        return this.content.remove(element);
    }
    
    public boolean removeContent(final ProcessingInstruction pi) {
        return this.content.remove(pi);
    }
    
    public boolean removeContent(final Comment comment) {
        return this.content.remove(comment);
    }
    
    public boolean removeContent(final CDATA cdata) {
        return this.content.remove(cdata);
    }
    
    public boolean removeContent(final Text text) {
        return this.content.remove(text);
    }
    
    public boolean removeContent(final EntityRef entity) {
        return this.content.remove(entity);
    }
    
    public String toString() {
        final StringBuffer stringForm = new StringBuffer(64).append("[Element: <").append(this.getQualifiedName());
        final String nsuri = this.getNamespaceURI();
        if (!nsuri.equals("")) {
            stringForm.append(" [Namespace: ").append(nsuri).append("]");
        }
        stringForm.append("/>]");
        return stringForm.toString();
    }
    
    public final boolean equals(final Object ob) {
        return this == ob;
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() {
        Element element = null;
        try {
            element = (Element)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        element.parent = null;
        element.content = new ContentList(element);
        element.attributes = new AttributeList(element);
        if (this.attributes != null) {
            for (int i = 0; i < this.attributes.size(); ++i) {
                final Object obj = this.attributes.get(i);
                final Attribute attribute = (Attribute)((Attribute)obj).clone();
                element.attributes.add(attribute);
            }
        }
        if (this.content != null) {
            for (int i = 0; i < this.content.size(); ++i) {
                final Object obj = this.content.get(i);
                if (obj instanceof Element) {
                    final Element elt = (Element)((Element)obj).clone();
                    element.content.add(elt);
                }
                else if (obj instanceof Text) {
                    final Text text = (Text)((Text)obj).clone();
                    element.content.add(text);
                }
                else if (obj instanceof Comment) {
                    final Comment comment = (Comment)((Comment)obj).clone();
                    element.content.add(comment);
                }
                else if (obj instanceof CDATA) {
                    final CDATA cdata = (CDATA)((CDATA)obj).clone();
                    element.content.add(cdata);
                }
                else if (obj instanceof ProcessingInstruction) {
                    final ProcessingInstruction pi = (ProcessingInstruction)((ProcessingInstruction)obj).clone();
                    element.content.add(pi);
                }
                else if (obj instanceof EntityRef) {
                    final EntityRef entity = (EntityRef)((EntityRef)obj).clone();
                    element.content.add(entity);
                }
            }
        }
        if (this.additionalNamespaces != null) {
            (element.additionalNamespaces = new ArrayList()).addAll(this.additionalNamespaces);
        }
        return element;
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.namespace.getPrefix());
        out.writeObject(this.namespace.getURI());
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.namespace = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
    }
}

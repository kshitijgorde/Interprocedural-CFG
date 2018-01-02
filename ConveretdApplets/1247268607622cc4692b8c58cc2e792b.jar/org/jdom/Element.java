// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import org.jdom.filter.ElementFilter;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import org.jdom.filter.Filter;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Element extends Content implements Parent
{
    private static final String CVS_ID = "@(#) $RCSfile: Element.java,v $ $Revision: 1.159 $ $Date: 2007/11/14 05:02:08 $ $Name: jdom_1_1_1 $";
    private static final int INITIAL_ARRAY_SIZE = 5;
    protected String name;
    protected transient Namespace namespace;
    protected transient List additionalNamespaces;
    AttributeList attributes;
    ContentList content;
    
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
        if ("xml".equals(prefix)) {
            return Namespace.XML_NAMESPACE;
        }
        if (prefix.equals(this.getNamespacePrefix())) {
            return this.getNamespace();
        }
        if (this.additionalNamespaces != null) {
            for (int i = 0; i < this.additionalNamespaces.size(); ++i) {
                final Namespace ns = this.additionalNamespaces.get(i);
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
        if ("".equals(this.namespace.getPrefix())) {
            return this.getName();
        }
        return this.namespace.getPrefix() + ':' + this.name;
    }
    
    public void addNamespaceDeclaration(final Namespace additionalNamespace) {
        final String reason = Verifier.checkNamespaceCollision(additionalNamespace, this);
        if (reason != null) {
            throw new IllegalAddException(this, additionalNamespace, reason);
        }
        if (this.additionalNamespaces == null) {
            this.additionalNamespaces = new ArrayList(5);
        }
        this.additionalNamespaces.add(additionalNamespace);
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
        return Collections.unmodifiableList((List<?>)this.additionalNamespaces);
    }
    
    public String getValue() {
        final StringBuffer buffer = new StringBuffer();
        for (final Content child : this.getContent()) {
            if (child instanceof Element || child instanceof Text) {
                buffer.append(child.getValue());
            }
        }
        return buffer.toString();
    }
    
    public boolean isRootElement() {
        return this.parent instanceof Document;
    }
    
    public int getContentSize() {
        return this.content.size();
    }
    
    public int indexOf(final Content child) {
        return this.content.indexOf(child);
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
    
    public List removeContent() {
        final List old = new ArrayList(this.content);
        this.content.clear();
        return old;
    }
    
    public List removeContent(final Filter filter) {
        final List old = new ArrayList();
        final Iterator iter = this.content.getView(filter).iterator();
        while (iter.hasNext()) {
            final Content child = iter.next();
            old.add(child);
            iter.remove();
        }
        return old;
    }
    
    public Element setContent(final Collection newContent) {
        this.content.clearAndSet(newContent);
        return this;
    }
    
    public Element setContent(final int index, final Content child) {
        this.content.set(index, child);
        return this;
    }
    
    public Parent setContent(final int index, final Collection newContent) {
        this.content.remove(index);
        this.content.addAll(index, newContent);
        return this;
    }
    
    public Element addContent(final String str) {
        return this.addContent(new Text(str));
    }
    
    public Element addContent(final Content child) {
        this.content.add(child);
        return this;
    }
    
    public Element addContent(final Collection newContent) {
        this.content.addAll(newContent);
        return this;
    }
    
    public Element addContent(final int index, final Content child) {
        this.content.add(index, child);
        return this;
    }
    
    public Element addContent(final int index, final Collection newContent) {
        this.content.addAll(index, newContent);
        return this;
    }
    
    public List cloneContent() {
        final int size = this.getContentSize();
        final List list = new ArrayList(size);
        for (int i = 0; i < size; ++i) {
            final Content child = this.getContent(i);
            list.add(child.clone());
        }
        return list;
    }
    
    public Content getContent(final int index) {
        return (Content)this.content.get(index);
    }
    
    public boolean removeContent(final Content child) {
        return this.content.remove(child);
    }
    
    public Content removeContent(final int index) {
        return (Content)this.content.remove(index);
    }
    
    public Element setContent(final Content child) {
        this.content.clear();
        this.content.add(child);
        return this;
    }
    
    public boolean isAncestor(final Element element) {
        for (Parent p = element.getParent(); p instanceof Element; p = p.getParent()) {
            if (p == this) {
                return true;
            }
        }
        return false;
    }
    
    public List getAttributes() {
        return this.attributes;
    }
    
    public Attribute getAttribute(final String name) {
        return this.getAttribute(name, Namespace.NO_NAMESPACE);
    }
    
    public Attribute getAttribute(final String name, final Namespace ns) {
        return (Attribute)this.attributes.get(name, ns);
    }
    
    public String getAttributeValue(final String name) {
        return this.getAttributeValue(name, Namespace.NO_NAMESPACE);
    }
    
    public String getAttributeValue(final String name, final String def) {
        return this.getAttributeValue(name, Namespace.NO_NAMESPACE, def);
    }
    
    public String getAttributeValue(final String name, final Namespace ns) {
        return this.getAttributeValue(name, ns, null);
    }
    
    public String getAttributeValue(final String name, final Namespace ns, final String def) {
        final Attribute attribute = (Attribute)this.attributes.get(name, ns);
        if (attribute == null) {
            return def;
        }
        return attribute.getValue();
    }
    
    public Element setAttributes(final Collection newAttributes) {
        this.attributes.clearAndSet(newAttributes);
        return this;
    }
    
    public Element setAttributes(final List newAttributes) {
        return this.setAttributes((Collection)newAttributes);
    }
    
    public Element setAttribute(final String name, final String value) {
        final Attribute attribute = this.getAttribute(name);
        if (attribute == null) {
            final Attribute newAttribute = new Attribute(name, value);
            this.setAttribute(newAttribute);
        }
        else {
            attribute.setValue(value);
        }
        return this;
    }
    
    public Element setAttribute(final String name, final String value, final Namespace ns) {
        final Attribute attribute = this.getAttribute(name, ns);
        if (attribute == null) {
            final Attribute newAttribute = new Attribute(name, value, ns);
            this.setAttribute(newAttribute);
        }
        else {
            attribute.setValue(value);
        }
        return this;
    }
    
    public Element setAttribute(final Attribute attribute) {
        this.attributes.add(attribute);
        return this;
    }
    
    public boolean removeAttribute(final String name) {
        return this.removeAttribute(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeAttribute(final String name, final Namespace ns) {
        return this.attributes.remove(name, ns);
    }
    
    public boolean removeAttribute(final Attribute attribute) {
        return this.attributes.remove(attribute);
    }
    
    public String toString() {
        final StringBuffer stringForm = new StringBuffer(64).append("[Element: <").append(this.getQualifiedName());
        final String nsuri = this.getNamespaceURI();
        if (!"".equals(nsuri)) {
            stringForm.append(" [Namespace: ").append(nsuri).append("]");
        }
        stringForm.append("/>]");
        return stringForm.toString();
    }
    
    public Object clone() {
        final Element element = (Element)super.clone();
        element.content = new ContentList(element);
        element.attributes = new AttributeList(element);
        if (this.attributes != null) {
            for (int i = 0; i < this.attributes.size(); ++i) {
                final Attribute attribute = (Attribute)this.attributes.get(i);
                element.attributes.add(attribute.clone());
            }
        }
        if (this.additionalNamespaces != null) {
            element.additionalNamespaces = new ArrayList(this.additionalNamespaces);
        }
        if (this.content != null) {
            for (int i = 0; i < this.content.size(); ++i) {
                final Content c = (Content)this.content.get(i);
                element.content.add(c.clone());
            }
        }
        return element;
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.namespace.getPrefix());
        out.writeObject(this.namespace.getURI());
        if (this.additionalNamespaces == null) {
            out.write(0);
        }
        else {
            final int size = this.additionalNamespaces.size();
            out.write(size);
            for (int i = 0; i < size; ++i) {
                final Namespace additional = this.additionalNamespaces.get(i);
                out.writeObject(additional.getPrefix());
                out.writeObject(additional.getURI());
            }
        }
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.namespace = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
        final int size = in.read();
        if (size != 0) {
            this.additionalNamespaces = new ArrayList(size);
            for (int i = 0; i < size; ++i) {
                final Namespace additional = Namespace.getNamespace((String)in.readObject(), (String)in.readObject());
                this.additionalNamespaces.add(additional);
            }
        }
    }
    
    public Iterator getDescendants() {
        return new DescendantIterator(this);
    }
    
    public Iterator getDescendants(final Filter filter) {
        final Iterator iterator = new DescendantIterator(this);
        return new FilterIterator(iterator, filter);
    }
    
    public List getChildren() {
        return this.content.getView(new ElementFilter());
    }
    
    public List getChildren(final String name) {
        return this.getChildren(name, Namespace.NO_NAMESPACE);
    }
    
    public List getChildren(final String name, final Namespace ns) {
        return this.content.getView(new ElementFilter(name, ns));
    }
    
    public Element getChild(final String name, final Namespace ns) {
        final List elements = this.content.getView(new ElementFilter(name, ns));
        final Iterator iter = elements.iterator();
        if (iter.hasNext()) {
            return iter.next();
        }
        return null;
    }
    
    public Element getChild(final String name) {
        return this.getChild(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeChild(final String name) {
        return this.removeChild(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeChild(final String name, final Namespace ns) {
        final Filter filter = new ElementFilter(name, ns);
        final List old = this.content.getView(filter);
        final Iterator iter = old.iterator();
        if (iter.hasNext()) {
            iter.next();
            iter.remove();
            return true;
        }
        return false;
    }
    
    public boolean removeChildren(final String name) {
        return this.removeChildren(name, Namespace.NO_NAMESPACE);
    }
    
    public boolean removeChildren(final String name, final Namespace ns) {
        boolean deletedSome = false;
        final Filter filter = new ElementFilter(name, ns);
        final List old = this.content.getView(filter);
        final Iterator iter = old.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
            deletedSome = true;
        }
        return deletedSome;
    }
}

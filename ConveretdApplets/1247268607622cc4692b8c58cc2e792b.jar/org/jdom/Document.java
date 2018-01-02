// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.Iterator;
import org.jdom.filter.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;

public class Document implements Parent
{
    private static final String CVS_ID = "@(#) $RCSfile: Document.java,v $ $Revision: 1.85 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    ContentList content;
    protected String baseURI;
    private HashMap propertyMap;
    
    public Document() {
        this.content = new ContentList(this);
        this.baseURI = null;
        this.propertyMap = null;
    }
    
    public Document(final Element rootElement, final DocType docType, final String baseURI) {
        this.content = new ContentList(this);
        this.baseURI = null;
        this.propertyMap = null;
        if (rootElement != null) {
            this.setRootElement(rootElement);
        }
        if (docType != null) {
            this.setDocType(docType);
        }
        if (baseURI != null) {
            this.setBaseURI(baseURI);
        }
    }
    
    public Document(final Element rootElement, final DocType docType) {
        this(rootElement, docType, null);
    }
    
    public Document(final Element rootElement) {
        this(rootElement, null, null);
    }
    
    public Document(final List content) {
        this.content = new ContentList(this);
        this.baseURI = null;
        this.propertyMap = null;
        this.setContent(content);
    }
    
    public int getContentSize() {
        return this.content.size();
    }
    
    public int indexOf(final Content child) {
        return this.content.indexOf(child);
    }
    
    public boolean hasRootElement() {
        return this.content.indexOfFirstElement() >= 0;
    }
    
    public Element getRootElement() {
        final int index = this.content.indexOfFirstElement();
        if (index < 0) {
            throw new IllegalStateException("Root element not set");
        }
        return (Element)this.content.get(index);
    }
    
    public Document setRootElement(final Element rootElement) {
        final int index = this.content.indexOfFirstElement();
        if (index < 0) {
            this.content.add(rootElement);
        }
        else {
            this.content.set(index, rootElement);
        }
        return this;
    }
    
    public Element detachRootElement() {
        final int index = this.content.indexOfFirstElement();
        if (index < 0) {
            return null;
        }
        return (Element)this.removeContent(index);
    }
    
    public DocType getDocType() {
        final int index = this.content.indexOfDocType();
        if (index < 0) {
            return null;
        }
        return (DocType)this.content.get(index);
    }
    
    public Document setDocType(final DocType docType) {
        if (docType == null) {
            final int docTypeIndex = this.content.indexOfDocType();
            if (docTypeIndex >= 0) {
                this.content.remove(docTypeIndex);
            }
            return this;
        }
        if (docType.getParent() != null) {
            throw new IllegalAddException(docType, "The DocType already is attached to a document");
        }
        final int docTypeIndex = this.content.indexOfDocType();
        if (docTypeIndex < 0) {
            this.content.add(0, docType);
        }
        else {
            this.content.set(docTypeIndex, docType);
        }
        return this;
    }
    
    public Document addContent(final Content child) {
        this.content.add(child);
        return this;
    }
    
    public Document addContent(final Collection c) {
        this.content.addAll(c);
        return this;
    }
    
    public Document addContent(final int index, final Content child) {
        this.content.add(index, child);
        return this;
    }
    
    public Document addContent(final int index, final Collection c) {
        this.content.addAll(index, c);
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
    
    public List getContent() {
        if (!this.hasRootElement()) {
            throw new IllegalStateException("Root element not set");
        }
        return this.content;
    }
    
    public List getContent(final Filter filter) {
        if (!this.hasRootElement()) {
            throw new IllegalStateException("Root element not set");
        }
        return this.content.getView(filter);
    }
    
    public List removeContent() {
        final List old = new ArrayList(this.content);
        this.content.clear();
        return old;
    }
    
    public List removeContent(final Filter filter) {
        final List old = new ArrayList();
        final Iterator itr = this.content.getView(filter).iterator();
        while (itr.hasNext()) {
            final Content child = itr.next();
            old.add(child);
            itr.remove();
        }
        return old;
    }
    
    public Document setContent(final Collection newContent) {
        this.content.clearAndSet(newContent);
        return this;
    }
    
    public final void setBaseURI(final String uri) {
        this.baseURI = uri;
    }
    
    public final String getBaseURI() {
        return this.baseURI;
    }
    
    public Document setContent(final int index, final Content child) {
        this.content.set(index, child);
        return this;
    }
    
    public Document setContent(final int index, final Collection collection) {
        this.content.remove(index);
        this.content.addAll(index, collection);
        return this;
    }
    
    public boolean removeContent(final Content child) {
        return this.content.remove(child);
    }
    
    public Content removeContent(final int index) {
        return (Content)this.content.remove(index);
    }
    
    public Document setContent(final Content child) {
        this.content.clear();
        this.content.add(child);
        return this;
    }
    
    public String toString() {
        final StringBuffer stringForm = new StringBuffer().append("[Document: ");
        final DocType docType = this.getDocType();
        if (docType != null) {
            stringForm.append(docType.toString()).append(", ");
        }
        else {
            stringForm.append(" No DOCTYPE declaration, ");
        }
        final Element rootElement = this.getRootElement();
        if (rootElement != null) {
            stringForm.append("Root is ").append(rootElement.toString());
        }
        else {
            stringForm.append(" No root element");
        }
        stringForm.append("]");
        return stringForm.toString();
    }
    
    public final boolean equals(final Object ob) {
        return ob == this;
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
    
    public Object clone() {
        Document doc = null;
        try {
            doc = (Document)super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        doc.content = new ContentList(doc);
        for (int i = 0; i < this.content.size(); ++i) {
            final Object obj = this.content.get(i);
            if (obj instanceof Element) {
                final Element element = (Element)((Element)obj).clone();
                doc.content.add(element);
            }
            else if (obj instanceof Comment) {
                final Comment comment = (Comment)((Comment)obj).clone();
                doc.content.add(comment);
            }
            else if (obj instanceof ProcessingInstruction) {
                final ProcessingInstruction pi = (ProcessingInstruction)((ProcessingInstruction)obj).clone();
                doc.content.add(pi);
            }
            else if (obj instanceof DocType) {
                final DocType dt = (DocType)((DocType)obj).clone();
                doc.content.add(dt);
            }
        }
        return doc;
    }
    
    public Iterator getDescendants() {
        return new DescendantIterator(this);
    }
    
    public Iterator getDescendants(final Filter filter) {
        return new FilterIterator(new DescendantIterator(this), filter);
    }
    
    public Parent getParent() {
        return null;
    }
    
    public Document getDocument() {
        return this;
    }
    
    public void setProperty(final String id, final Object value) {
        if (this.propertyMap == null) {
            this.propertyMap = new HashMap();
        }
        this.propertyMap.put(id, value);
    }
    
    public Object getProperty(final String id) {
        if (this.propertyMap == null) {
            return null;
        }
        return this.propertyMap.get(id);
    }
}

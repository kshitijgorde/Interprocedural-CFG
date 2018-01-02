// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import com.sun.java.util.collections.Collection;
import org.jdom.filter.Filter;
import com.sun.java.util.collections.List;
import java.io.Serializable;

public class Document implements Serializable, Cloneable
{
    private static final String CVS_ID = "@(#) $RCSfile: Document.java,v $ $Revision: 1.55 $ $Date: 2002/03/28 11:08:12 $ $Name: jdom_1_0_b8 $";
    protected ContentList content;
    protected DocType docType;
    
    public Document() {
        this.content = new ContentList(this);
    }
    
    public Document(final Element rootElement, final DocType docType) {
        this.content = new ContentList(this);
        if (rootElement != null) {
            this.setRootElement(rootElement);
        }
        this.setDocType(docType);
    }
    
    public Document(final Element rootElement) {
        this(rootElement, null);
    }
    
    public Document(final List newContent, final DocType docType) {
        this.content = new ContentList(this);
        this.setContent(newContent);
        this.setDocType(docType);
    }
    
    public Document(final List content) {
        this(content, null);
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
    
    private Object removeContent(final int index) {
        return this.content.remove(index);
    }
    
    public DocType getDocType() {
        return this.docType;
    }
    
    public Document setDocType(final DocType docType) {
        if (docType != null) {
            if (docType.getDocument() != null) {
                throw new IllegalAddException(this, docType, "The docType already is attached to a document");
            }
            docType.setDocument(this);
        }
        if (this.docType != null) {
            this.docType.setDocument(null);
        }
        this.docType = docType;
        return this;
    }
    
    public Document addContent(final ProcessingInstruction pi) {
        this.content.add(pi);
        return this;
    }
    
    public Document addContent(final Comment comment) {
        this.content.add(comment);
        return this;
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
    
    public Document setContent(final List newContent) {
        this.content.clearAndSet(newContent);
        return this;
    }
    
    public boolean removeContent(final ProcessingInstruction pi) {
        return this.content.remove(pi);
    }
    
    public boolean removeContent(final Comment comment) {
        return this.content.remove(comment);
    }
    
    public String toString() {
        final StringBuffer stringForm = new StringBuffer().append("[Document: ");
        if (this.docType != null) {
            stringForm.append(this.docType.toString()).append(", ");
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
        if (this.docType != null) {
            doc.docType = (DocType)this.docType.clone();
        }
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
        }
        return doc;
    }
}

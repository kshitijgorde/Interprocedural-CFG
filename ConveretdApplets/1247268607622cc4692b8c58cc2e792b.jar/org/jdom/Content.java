// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.io.Serializable;

public abstract class Content implements Cloneable, Serializable
{
    protected Parent parent;
    
    protected Content() {
        this.parent = null;
    }
    
    public Content detach() {
        if (this.parent != null) {
            this.parent.removeContent(this);
        }
        return this;
    }
    
    public Parent getParent() {
        return this.parent;
    }
    
    public Element getParentElement() {
        final Parent parent = this.getParent();
        return (Element)((parent instanceof Element) ? parent : null);
    }
    
    protected Content setParent(final Parent parent) {
        this.parent = parent;
        return this;
    }
    
    public Document getDocument() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.getDocument();
    }
    
    public abstract String getValue();
    
    public Object clone() {
        try {
            final Content c = (Content)super.clone();
            c.parent = null;
            return c;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public final boolean equals(final Object ob) {
        return ob == this;
    }
    
    public final int hashCode() {
        return super.hashCode();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.io.Serializable;

public class SimpleAttributeSet implements MutableAttributeSet, Serializable, Cloneable
{
    public static final AttributeSet EMPTY;
    private transient Hashtable table;
    
    static {
        EMPTY = new EmptyAttributeSet();
    }
    
    public SimpleAttributeSet() {
        this.table = new Hashtable(3);
    }
    
    private SimpleAttributeSet(final Hashtable table) {
        this.table = new Hashtable(3);
        this.table = table;
    }
    
    public SimpleAttributeSet(final AttributeSet set) {
        this.table = new Hashtable(3);
        this.addAttributes(set);
    }
    
    public void addAttribute(final Object o, final Object o2) {
        this.table.put(o, o2);
    }
    
    public void addAttributes(final AttributeSet set) {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final Object nextElement = attributeNames.nextElement();
            this.addAttribute(nextElement, set.getAttribute(nextElement));
        }
    }
    
    public Object clone() {
        SimpleAttributeSet set;
        try {
            set = (SimpleAttributeSet)super.clone();
            set.table = (Hashtable)this.table.clone();
        }
        catch (CloneNotSupportedException ex) {
            set = null;
        }
        return set;
    }
    
    public boolean containsAttribute(final Object o, final Object o2) {
        return o2.equals(this.getAttribute(o));
    }
    
    public boolean containsAttributes(final AttributeSet set) {
        boolean equals = true;
        Object nextElement;
        for (Enumeration attributeNames = set.getAttributeNames(); equals && attributeNames.hasMoreElements(); equals = set.getAttribute(nextElement).equals(this.getAttribute(nextElement))) {
            nextElement = attributeNames.nextElement();
        }
        return equals;
    }
    
    public AttributeSet copyAttributes() {
        return (AttributeSet)this.clone();
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof AttributeSet && this.isEqual((AttributeSet)o));
    }
    
    public Object getAttribute(final Object o) {
        Object o2 = this.table.get(o);
        if (o2 == null) {
            final AttributeSet resolveParent = this.getResolveParent();
            if (resolveParent != null) {
                o2 = resolveParent.getAttribute(o);
            }
        }
        return o2;
    }
    
    public int getAttributeCount() {
        return this.table.size();
    }
    
    public Enumeration getAttributeNames() {
        return this.table.keys();
    }
    
    public AttributeSet getResolveParent() {
        return this.table.get(StyleConstants.ResolveAttribute);
    }
    
    public int hashCode() {
        return this.table.hashCode();
    }
    
    public boolean isDefined(final Object o) {
        return this.table.containsKey(o);
    }
    
    public boolean isEmpty() {
        return this.table.isEmpty();
    }
    
    public boolean isEqual(final AttributeSet set) {
        return this.getAttributeCount() == set.getAttributeCount() && this.containsAttributes(set);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.table = new Hashtable(3);
        StyleContext.readAttributeSet(objectInputStream, this);
    }
    
    public void removeAttribute(final Object o) {
        this.table.remove(o);
    }
    
    public void removeAttributes(final Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            this.removeAttribute(enumeration.nextElement());
        }
    }
    
    public void removeAttributes(final AttributeSet set) {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final Object nextElement = attributeNames.nextElement();
            if (set.getAttribute(nextElement).equals(this.getAttribute(nextElement))) {
                this.removeAttribute(nextElement);
            }
        }
    }
    
    public void setResolveParent(final AttributeSet set) {
        this.addAttribute(StyleConstants.ResolveAttribute, set);
    }
    
    public String toString() {
        String s = "";
        final Enumeration attributeNames = this.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final Object nextElement = attributeNames.nextElement();
            final Object attribute = this.getAttribute(nextElement);
            if (attribute instanceof AttributeSet) {
                s = String.valueOf(s) + nextElement + "=**AttributeSet** ";
            }
            else {
                s = String.valueOf(s) + nextElement + "=" + attribute + " ";
            }
        }
        return s;
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        StyleContext.writeAttributeSet(objectOutputStream, this);
    }
    
    static class EmptyAttributeSet implements AttributeSet, Serializable
    {
        public boolean containsAttribute(final Object o, final Object o2) {
            return false;
        }
        
        public boolean containsAttributes(final AttributeSet set) {
            return set.getAttributeCount() == 0;
        }
        
        public AttributeSet copyAttributes() {
            return this;
        }
        
        public boolean equals(final Object o) {
            return this == o || (o instanceof AttributeSet && ((AttributeSet)o).getAttributeCount() == 0);
        }
        
        public Object getAttribute(final Object o) {
            return null;
        }
        
        public int getAttributeCount() {
            return 0;
        }
        
        public Enumeration getAttributeNames() {
            return DefaultMutableTreeNode.EMPTY_ENUMERATION;
        }
        
        public AttributeSet getResolveParent() {
            return null;
        }
        
        public boolean isDefined(final Object o) {
            return false;
        }
        
        public boolean isEqual(final AttributeSet set) {
            return set.getAttributeCount() == 0;
        }
    }
}

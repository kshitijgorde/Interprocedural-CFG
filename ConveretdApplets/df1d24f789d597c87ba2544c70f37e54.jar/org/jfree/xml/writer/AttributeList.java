// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.writer;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class AttributeList
{
    private List entryList;
    
    public AttributeList() {
        this.entryList = new ArrayList();
    }
    
    public Iterator keys() {
        return new AttributeIterator(this.entryList.iterator());
    }
    
    public synchronized void setAttribute(final String s, final String s2) {
        final AttributeEntry attributeEntry = new AttributeEntry(s, s2);
        final int index = this.entryList.indexOf(attributeEntry);
        if (index != -1) {
            this.entryList.remove(index);
        }
        this.entryList.add(attributeEntry);
    }
    
    public synchronized String getAttribute(final String s) {
        return this.getAttribute(s, null);
    }
    
    public synchronized String getAttribute(final String s, final String s2) {
        for (int i = 0; i < this.entryList.size(); ++i) {
            final AttributeEntry attributeEntry = this.entryList.get(i);
            if (attributeEntry.getName().equals(s)) {
                return attributeEntry.getValue();
            }
        }
        return s2;
    }
    
    public synchronized void removeAttribute(final String s) {
        for (int i = 0; i < this.entryList.size(); ++i) {
            final AttributeEntry attributeEntry = this.entryList.get(i);
            if (attributeEntry.getName().equals(s)) {
                this.entryList.remove(attributeEntry);
                return;
            }
        }
    }
    
    private static class AttributeIterator implements Iterator
    {
        private Iterator backend;
        
        public AttributeIterator(final Iterator backend) {
            if (backend == null) {
                throw new NullPointerException();
            }
            this.backend = backend;
        }
        
        public boolean hasNext() {
            return this.backend.hasNext();
        }
        
        public Object next() {
            final AttributeEntry attributeEntry = this.backend.next();
            if (attributeEntry != null) {
                return attributeEntry.getName();
            }
            return attributeEntry;
        }
        
        public void remove() {
            this.backend.remove();
        }
    }
    
    private static class AttributeEntry
    {
        private String name;
        private String value;
        
        public AttributeEntry(final String name, final String value) {
            if (name == null) {
                throw new NullPointerException("Name must not be null. [" + name + ", " + value + "]");
            }
            if (value == null) {
                throw new NullPointerException("Value must not be null. [" + name + ", " + value + "]");
            }
            this.name = name;
            this.value = value;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public boolean equals(final Object o) {
            return this == o || (o instanceof AttributeEntry && this.name.equals(((AttributeEntry)o).name));
        }
        
        public int hashCode() {
            return this.name.hashCode();
        }
    }
}

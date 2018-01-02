// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import com.sun.java.util.collections.Iterator;
import com.sun.java.util.collections.Collection;
import com.sun.java.util.collections.ArrayList;
import java.io.Serializable;
import com.sun.java.util.collections.List;
import com.sun.java.util.collections.AbstractList;

class AttributeList extends AbstractList implements List, Serializable
{
    private static final String CVS_ID = "@(#) $RCSfile: AttributeList.java,v $ $Revision: 1.8 $ $Date: 2002/03/20 10:07:54 $ $Name: jdom_1_0_b8 $";
    private static final int INITIAL_ARRAY_SIZE = 3;
    protected ArrayList list;
    protected Element parent;
    
    private AttributeList() {
    }
    
    public AttributeList(final Element parent) {
        this.parent = parent;
    }
    
    public boolean add(final Object obj) {
        if (obj instanceof Attribute) {
            final Attribute attribute = (Attribute)obj;
            final int duplicate = this.indexOfDuplicate(attribute);
            if (duplicate < 0) {
                this.add(this.size(), attribute);
            }
            else {
                this.set(duplicate, attribute);
            }
            return true;
        }
        if (obj == null) {
            throw new IllegalAddException("Cannot add null attribute");
        }
        throw new IllegalAddException("Class " + obj.getClass().getName() + " is not an attribute");
    }
    
    public void add(final int index, final Object obj) {
        if (obj instanceof Attribute) {
            final Attribute attribute = (Attribute)obj;
            final int duplicate = this.indexOfDuplicate(attribute);
            if (duplicate >= 0) {
                throw new IllegalAddException("Cannot add duplicate attribute");
            }
            this.add(index, attribute);
            ++super.modCount;
        }
        else {
            if (obj == null) {
                throw new IllegalAddException("Cannot add null attribute");
            }
            throw new IllegalAddException("Class " + obj.getClass().getName() + " is not an attribute");
        }
    }
    
    protected void add(final int index, final Attribute attribute) {
        if (attribute.getParent() != null) {
            throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"");
        }
        final String reason = Verifier.checkNamespaceCollision(attribute, this.parent);
        if (reason != null) {
            throw new IllegalAddException(this.parent, attribute, reason);
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(3);
        }
        this.list.add(index, attribute);
        attribute.setParent(this.parent);
    }
    
    public boolean addAll(final Collection collection) {
        return this.addAll(this.size(), collection);
    }
    
    public boolean addAll(final int index, final Collection collection) {
        if (this.list == null && index != 0) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        if (collection == null || collection.size() == 0) {
            return false;
        }
        int count = 0;
        try {
            for (final Object obj : collection) {
                this.add(index + count, obj);
                ++count;
            }
        }
        catch (RuntimeException exception) {
            for (int j = 0; j < count; ++j) {
                this.remove(index + j);
            }
            throw exception;
        }
        return true;
    }
    
    public void clear() {
        if (this.list != null) {
            for (int i = 0; i < this.list.size(); ++i) {
                final Attribute attribute = (Attribute)this.list.get(i);
                attribute.setParent(null);
            }
            this.list = null;
        }
        ++super.modCount;
    }
    
    public void clearAndSet(final Collection collection) {
        final ArrayList old = this.list;
        this.list = null;
        if (collection != null && collection.size() != 0) {
            this.ensureCapacity(collection.size());
            try {
                this.addAll(0, collection);
            }
            catch (RuntimeException exception) {
                this.list = old;
                throw exception;
            }
        }
        if (old != null) {
            for (int i = 0; i < old.size(); ++i) {
                final Attribute attribute = (Attribute)old.get(i);
                attribute.setParent(null);
            }
        }
    }
    
    protected void ensureCapacity(final int minCapacity) {
        if (this.list == null) {
            this.list = new ArrayList(minCapacity);
        }
        else {
            this.list.ensureCapacity(minCapacity);
        }
    }
    
    public Object get(final int index) {
        if (this.list == null) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        return this.list.get(index);
    }
    
    protected Object get(final String name, final Namespace namespace) {
        final int index = this.indexOf(name, namespace);
        if (index < 0) {
            return null;
        }
        return this.list.get(index);
    }
    
    protected int indexOf(final String name, final Namespace namespace) {
        final String uri = namespace.getURI();
        if (this.list != null) {
            for (int i = 0; i < this.list.size(); ++i) {
                final Attribute old = (Attribute)this.list.get(i);
                final String oldURI = old.getNamespaceURI();
                final String oldName = old.getName();
                if (oldURI.equals(uri) && oldName.equals(name)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public Object remove(final int index) {
        if (this.list == null) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        final Attribute old = (Attribute)this.list.get(index);
        old.setParent(null);
        ++super.modCount;
        this.list.remove(index);
        return old;
    }
    
    protected boolean remove(final String name, final Namespace namespace) {
        final int index = this.indexOf(name, namespace);
        if (index < 0) {
            return false;
        }
        this.remove(index);
        return true;
    }
    
    public Object set(final int index, final Object obj) {
        if (obj instanceof Attribute) {
            final Attribute attribute = (Attribute)obj;
            final int duplicate = this.indexOfDuplicate(attribute);
            if (duplicate >= 0 && duplicate != index) {
                throw new IllegalAddException("Cannot set duplicate attribute");
            }
            return this.set(index, attribute);
        }
        else {
            if (obj == null) {
                throw new IllegalAddException("Cannot add null attribute");
            }
            throw new IllegalAddException("Class " + obj.getClass().getName() + " is not an attribute");
        }
    }
    
    protected Object set(final int index, final Attribute attribute) {
        if (this.list == null) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        if (attribute.getParent() != null) {
            throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"");
        }
        final String reason = Verifier.checkNamespaceCollision(attribute, this.parent);
        if (reason != null) {
            throw new IllegalAddException(this.parent, attribute, reason);
        }
        final Attribute old = (Attribute)this.list.get(index);
        old.setParent(null);
        this.list.set(index, attribute);
        return old;
    }
    
    private int indexOfDuplicate(final Attribute attribute) {
        int duplicate = -1;
        final String name = attribute.getName();
        final Namespace namespace = attribute.getNamespace();
        duplicate = this.indexOf(name, namespace);
        return duplicate;
    }
    
    public int size() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }
    
    public String toString() {
        if (this.list != null && this.list.size() > 0) {
            return this.list.toString();
        }
        return "[]";
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.List;
import org.jdom.filter.Filter;
import java.util.Iterator;
import java.util.Collection;
import java.io.Serializable;
import java.util.AbstractList;

final class ContentList extends AbstractList implements Serializable
{
    private static final String CVS_ID = "@(#) $RCSfile: ContentList.java,v $ $Revision: 1.42 $ $Date: 2007/11/10 05:28:58 $ $Name: jdom_1_1_1 $";
    private static final long serialVersionUID = 1L;
    private static final int INITIAL_ARRAY_SIZE = 5;
    private Content[] elementData;
    private int size;
    private Parent parent;
    
    ContentList(final Parent parent) {
        this.parent = parent;
    }
    
    final void uncheckedAddContent(final Content c) {
        c.parent = this.parent;
        this.ensureCapacity(this.size + 1);
        this.elementData[this.size++] = c;
        ++this.modCount;
    }
    
    public void add(final int index, Object obj) {
        if (obj == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (obj instanceof String) {
            obj = new Text(obj.toString());
        }
        if (obj instanceof Content) {
            this.add(index, (Content)obj);
            return;
        }
        throw new IllegalAddException("Class " + obj.getClass().getName() + " is of unrecognized type and cannot be added");
    }
    
    private void documentCanContain(final int index, final Content child) throws IllegalAddException {
        if (child instanceof Element) {
            if (this.indexOfFirstElement() >= 0) {
                throw new IllegalAddException("Cannot add a second root element, only one is allowed");
            }
            if (this.indexOfDocType() > index) {
                throw new IllegalAddException("A root element cannot be added before the DocType");
            }
        }
        if (child instanceof DocType) {
            if (this.indexOfDocType() >= 0) {
                throw new IllegalAddException("Cannot add a second doctype, only one is allowed");
            }
            final int firstElt = this.indexOfFirstElement();
            if (firstElt != -1 && firstElt < index) {
                throw new IllegalAddException("A DocType cannot be added after the root element");
            }
        }
        if (child instanceof CDATA) {
            throw new IllegalAddException("A CDATA is not allowed at the document root");
        }
        if (child instanceof Text) {
            throw new IllegalAddException("A Text is not allowed at the document root");
        }
        if (child instanceof EntityRef) {
            throw new IllegalAddException("An EntityRef is not allowed at the document root");
        }
    }
    
    private static void elementCanContain(final int index, final Content child) throws IllegalAddException {
        if (child instanceof DocType) {
            throw new IllegalAddException("A DocType is not allowed except at the document level");
        }
    }
    
    void add(final int index, final Content child) {
        if (child == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (this.parent instanceof Document) {
            this.documentCanContain(index, child);
        }
        else {
            elementCanContain(index, child);
        }
        if (child.getParent() != null) {
            final Parent p = child.getParent();
            if (p instanceof Document) {
                throw new IllegalAddException((Element)child, "The Content already has an existing parent document");
            }
            throw new IllegalAddException("The Content already has an existing parent \"" + ((Element)p).getQualifiedName() + "\"");
        }
        else {
            if (child == this.parent) {
                throw new IllegalAddException("The Element cannot be added to itself");
            }
            if (this.parent instanceof Element && child instanceof Element && ((Element)child).isAncestor((Element)this.parent)) {
                throw new IllegalAddException("The Element cannot be added as a descendent of itself");
            }
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            child.setParent(this.parent);
            this.ensureCapacity(this.size + 1);
            if (index == this.size) {
                this.elementData[this.size++] = child;
            }
            else {
                System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
                this.elementData[index] = child;
                ++this.size;
            }
            ++this.modCount;
        }
    }
    
    public boolean addAll(final Collection collection) {
        return this.addAll(this.size(), collection);
    }
    
    public boolean addAll(final int index, final Collection collection) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        if (collection == null || collection.size() == 0) {
            return false;
        }
        this.ensureCapacity(this.size() + collection.size());
        int count = 0;
        try {
            for (final Object obj : collection) {
                this.add(index + count, obj);
                ++count;
            }
        }
        catch (RuntimeException exception) {
            for (int j = 0; j < count; ++j) {
                this.remove(index);
            }
            throw exception;
        }
        return true;
    }
    
    public void clear() {
        if (this.elementData != null) {
            for (int i = 0; i < this.size; ++i) {
                final Content obj = this.elementData[i];
                removeParent(obj);
            }
            this.elementData = null;
            this.size = 0;
        }
        ++this.modCount;
    }
    
    void clearAndSet(final Collection collection) {
        final Content[] old = this.elementData;
        final int oldSize = this.size;
        this.elementData = null;
        this.size = 0;
        if (collection != null && collection.size() != 0) {
            this.ensureCapacity(collection.size());
            try {
                this.addAll(0, collection);
            }
            catch (RuntimeException exception) {
                this.elementData = old;
                this.size = oldSize;
                throw exception;
            }
        }
        if (old != null) {
            for (int i = 0; i < oldSize; ++i) {
                removeParent(old[i]);
            }
        }
        ++this.modCount;
    }
    
    void ensureCapacity(final int minCapacity) {
        if (this.elementData == null) {
            this.elementData = new Content[Math.max(minCapacity, 5)];
        }
        else {
            final int oldCapacity = this.elementData.length;
            if (minCapacity > oldCapacity) {
                final Object[] oldData = this.elementData;
                int newCapacity = oldCapacity * 3 / 2 + 1;
                if (newCapacity < minCapacity) {
                    newCapacity = minCapacity;
                }
                System.arraycopy(oldData, 0, this.elementData = new Content[newCapacity], 0, this.size);
            }
        }
    }
    
    public Object get(final int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        return this.elementData[index];
    }
    
    List getView(final Filter filter) {
        return new FilterList(filter);
    }
    
    int indexOfFirstElement() {
        if (this.elementData != null) {
            for (int i = 0; i < this.size; ++i) {
                if (this.elementData[i] instanceof Element) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    int indexOfDocType() {
        if (this.elementData != null) {
            for (int i = 0; i < this.size; ++i) {
                if (this.elementData[i] instanceof DocType) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public Object remove(final int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        final Content old = this.elementData[index];
        removeParent(old);
        final int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
        }
        this.elementData[--this.size] = null;
        ++this.modCount;
        return old;
    }
    
    private static void removeParent(final Content c) {
        c.setParent(null);
    }
    
    public Object set(final int index, final Object obj) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        if (obj instanceof Element && this.parent instanceof Document) {
            final int root = this.indexOfFirstElement();
            if (root >= 0 && root != index) {
                throw new IllegalAddException("Cannot add a second root element, only one is allowed");
            }
        }
        if (obj instanceof DocType && this.parent instanceof Document) {
            final int docTypeIndex = this.indexOfDocType();
            if (docTypeIndex >= 0 && docTypeIndex != index) {
                throw new IllegalAddException("Cannot add a second doctype, only one is allowed");
            }
        }
        final Object old = this.remove(index);
        try {
            this.add(index, obj);
        }
        catch (RuntimeException exception) {
            this.add(index, old);
            throw exception;
        }
        return old;
    }
    
    public int size() {
        return this.size;
    }
    
    public String toString() {
        return super.toString();
    }
    
    private int getModCount() {
        return this.modCount;
    }
    
    class FilterList extends AbstractList implements Serializable
    {
        Filter filter;
        int count;
        int expected;
        
        FilterList(final Filter filter) {
            this.count = 0;
            this.expected = -1;
            this.filter = filter;
        }
        
        public void add(final int index, final Object obj) {
            if (this.filter.matches(obj)) {
                final int adjusted = this.getAdjustedIndex(index);
                ContentList.this.add(adjusted, obj);
                ++this.expected;
                ++this.count;
                return;
            }
            throw new IllegalAddException("Filter won't allow the " + obj.getClass().getName() + " '" + obj + "' to be added to the list");
        }
        
        public Object get(final int index) {
            final int adjusted = this.getAdjustedIndex(index);
            return ContentList.this.get(adjusted);
        }
        
        public Iterator iterator() {
            return new FilterListIterator(this.filter, 0);
        }
        
        public ListIterator listIterator() {
            return new FilterListIterator(this.filter, 0);
        }
        
        public ListIterator listIterator(final int index) {
            return new FilterListIterator(this.filter, index);
        }
        
        public Object remove(final int index) {
            final int adjusted = this.getAdjustedIndex(index);
            Object old = ContentList.this.get(adjusted);
            if (this.filter.matches(old)) {
                old = ContentList.this.remove(adjusted);
                ++this.expected;
                --this.count;
                return old;
            }
            throw new IllegalAddException("Filter won't allow the " + old.getClass().getName() + " '" + old + "' (index " + index + ") to be removed");
        }
        
        public Object set(final int index, final Object obj) {
            Object old = null;
            if (!this.filter.matches(obj)) {
                throw new IllegalAddException("Filter won't allow index " + index + " to be set to " + obj.getClass().getName());
            }
            final int adjusted = this.getAdjustedIndex(index);
            old = ContentList.this.get(adjusted);
            if (!this.filter.matches(old)) {
                throw new IllegalAddException("Filter won't allow the " + old.getClass().getName() + " '" + old + "' (index " + index + ") to be removed");
            }
            old = ContentList.this.set(adjusted, obj);
            this.expected += 2;
            return old;
        }
        
        public int size() {
            if (this.expected == ContentList.this.getModCount()) {
                return this.count;
            }
            this.count = 0;
            for (int i = 0; i < ContentList.this.size(); ++i) {
                final Object obj = ContentList.this.elementData[i];
                if (this.filter.matches(obj)) {
                    ++this.count;
                }
            }
            this.expected = ContentList.this.getModCount();
            return this.count;
        }
        
        private final int getAdjustedIndex(final int index) {
            int adjusted = 0;
            for (int i = 0; i < ContentList.this.size; ++i) {
                final Object obj = ContentList.this.elementData[i];
                if (this.filter.matches(obj)) {
                    if (index == adjusted) {
                        return i;
                    }
                    ++adjusted;
                }
            }
            if (index == adjusted) {
                return ContentList.this.size;
            }
            return ContentList.this.size + 1;
        }
    }
    
    class FilterListIterator implements ListIterator
    {
        Filter filter;
        private boolean forward;
        private boolean canremove;
        private boolean canset;
        private int cursor;
        private int tmpcursor;
        private int index;
        private int expected;
        private int fsize;
        
        FilterListIterator(final Filter filter, final int start) {
            this.forward = false;
            this.canremove = false;
            this.canset = false;
            this.cursor = -1;
            this.tmpcursor = -1;
            this.index = -1;
            this.expected = -1;
            this.fsize = 0;
            this.filter = filter;
            this.expected = ContentList.this.getModCount();
            this.forward = false;
            if (start < 0) {
                throw new IndexOutOfBoundsException("Index: " + start);
            }
            this.fsize = 0;
            for (int i = 0; i < ContentList.this.size(); ++i) {
                if (filter.matches(ContentList.this.get(i))) {
                    if (start == this.fsize) {
                        this.cursor = i;
                        this.index = this.fsize;
                    }
                    ++this.fsize;
                }
            }
            if (start > this.fsize) {
                throw new IndexOutOfBoundsException("Index: " + start + " Size: " + this.fsize);
            }
            if (this.cursor == -1) {
                this.cursor = ContentList.this.size();
                this.index = this.fsize;
            }
        }
        
        public boolean hasNext() {
            return this.nextIndex() < this.fsize;
        }
        
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("next() is beyond the end of the Iterator");
            }
            this.index = this.nextIndex();
            this.cursor = this.tmpcursor;
            this.forward = true;
            this.canremove = true;
            this.canset = true;
            return ContentList.this.get(this.cursor);
        }
        
        public boolean hasPrevious() {
            return this.previousIndex() >= 0;
        }
        
        public Object previous() {
            if (!this.hasPrevious()) {
                throw new NoSuchElementException("previous() is before the start of the Iterator");
            }
            this.index = this.previousIndex();
            this.cursor = this.tmpcursor;
            this.forward = false;
            this.canremove = true;
            this.canset = true;
            return ContentList.this.get(this.cursor);
        }
        
        public int nextIndex() {
            this.checkConcurrentModification();
            if (this.forward) {
                for (int i = this.cursor + 1; i < ContentList.this.size(); ++i) {
                    if (this.filter.matches(ContentList.this.get(i))) {
                        this.tmpcursor = i;
                        return this.index + 1;
                    }
                }
                this.tmpcursor = ContentList.this.size();
                return this.index + 1;
            }
            this.tmpcursor = this.cursor;
            return this.index;
        }
        
        public int previousIndex() {
            this.checkConcurrentModification();
            if (!this.forward) {
                for (int i = this.cursor - 1; i >= 0; --i) {
                    if (this.filter.matches(ContentList.this.get(i))) {
                        this.tmpcursor = i;
                        return this.index - 1;
                    }
                }
                this.tmpcursor = -1;
                return this.index - 1;
            }
            this.tmpcursor = this.cursor;
            return this.index;
        }
        
        public void add(final Object obj) {
            this.nextIndex();
            ContentList.this.add(this.tmpcursor, obj);
            this.forward = true;
            this.expected = ContentList.this.getModCount();
            final boolean b = false;
            this.canset = b;
            this.canremove = b;
            this.index = this.nextIndex();
            this.cursor = this.tmpcursor;
            ++this.fsize;
        }
        
        public void remove() {
            if (!this.canremove) {
                throw new IllegalStateException("Can not remove an element unless either next() or previous() has been called since the last remove()");
            }
            this.nextIndex();
            ContentList.this.remove(this.cursor);
            this.cursor = this.tmpcursor - 1;
            this.expected = ContentList.this.getModCount();
            this.forward = false;
            this.canremove = false;
            this.canset = false;
            --this.fsize;
        }
        
        public void set(final Object obj) {
            if (!this.canset) {
                throw new IllegalStateException("Can not set an element unless either next() or previous() has been called since the last remove() or set()");
            }
            this.checkConcurrentModification();
            if (!this.filter.matches(obj)) {
                throw new IllegalAddException("Filter won't allow index " + this.index + " to be set to " + obj.getClass().getName());
            }
            ContentList.this.set(this.cursor, obj);
            this.expected = ContentList.this.getModCount();
        }
        
        private void checkConcurrentModification() {
            if (this.expected != ContentList.this.getModCount()) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

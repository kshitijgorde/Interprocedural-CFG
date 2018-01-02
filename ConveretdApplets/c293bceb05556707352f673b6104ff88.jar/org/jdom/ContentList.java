// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import com.sun.java.util.collections.ConcurrentModificationException;
import com.sun.java.util.collections.NoSuchElementException;
import com.sun.java.util.collections.ListIterator;
import com.sun.java.util.collections.List;
import org.jdom.filter.Filter;
import com.sun.java.util.collections.Iterator;
import com.sun.java.util.collections.Collection;
import com.sun.java.util.collections.ArrayList;
import java.io.Serializable;
import com.sun.java.util.collections.AbstractList;

class ContentList extends AbstractList implements Serializable
{
    private static final String CVS_ID = "@(#) $RCSfile: ContentList.java,v $ $Revision: 1.11 $ $Date: 2002/03/16 12:15:40 $ $Name: jdom_1_0_b8 $";
    private static final int INITIAL_ARRAY_SIZE = 5;
    private static final int CREATE = 0;
    private static final int HASPREV = 1;
    private static final int HASNEXT = 2;
    private static final int PREV = 3;
    private static final int NEXT = 4;
    private static final int ADD = 5;
    private static final int REMOVE = 6;
    protected ArrayList list;
    protected Object parent;
    
    private ContentList() {
    }
    
    protected ContentList(final Document document) {
        this.parent = document;
        this.ensureCapacity(5);
    }
    
    protected ContentList(final Element parent) {
        this.parent = parent;
        this.ensureCapacity(5);
    }
    
    public void add(final int index, final Object obj) {
        if (obj instanceof Element) {
            this.add(index, (Element)obj);
        }
        else if (obj instanceof Text) {
            this.add(index, (Text)obj);
        }
        else if (obj instanceof Comment) {
            this.add(index, (Comment)obj);
        }
        else if (obj instanceof ProcessingInstruction) {
            this.add(index, (ProcessingInstruction)obj);
        }
        else if (obj instanceof CDATA) {
            this.add(index, (CDATA)obj);
        }
        else if (obj instanceof EntityRef) {
            this.add(index, (EntityRef)obj);
        }
        else {
            if (obj == null) {
                throw new IllegalAddException("Cannot add null object");
            }
            throw new IllegalAddException("Class " + obj.getClass().getName() + " is of unrecognized type and cannot be added");
        }
    }
    
    protected void add(final int index, final Element element) {
        if (element == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (element.getParent() != null) {
            throw new IllegalAddException("The element already has an existing parent \"" + element.getParent().getQualifiedName() + "\"");
        }
        if (element == this.parent) {
            throw new IllegalAddException("The element cannot be added to itself");
        }
        if (this.parent instanceof Element && ((Element)this.parent).isAncestor(element)) {
            throw new IllegalAddException("The element cannot be added as a descendent of itself");
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(5);
        }
        if (this.parent instanceof Document) {
            if (this.indexOfFirstElement() >= 0) {
                throw new IllegalAddException("Cannot add a second root element, only one is allowed");
            }
            element.setDocument((Document)this.parent);
        }
        else {
            element.setParent((Element)this.parent);
        }
        this.list.add(index, element);
        ++super.modCount;
    }
    
    protected void add(final int index, final Comment comment) {
        if (comment == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (comment.getParent() != null) {
            throw new IllegalAddException("The comment already has an existing parent \"" + comment.getParent().getQualifiedName() + "\"");
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(5);
        }
        if (this.parent instanceof Document) {
            comment.setDocument((Document)this.parent);
        }
        else {
            comment.setParent((Element)this.parent);
        }
        this.list.add(index, comment);
        ++super.modCount;
    }
    
    protected void add(final int index, final ProcessingInstruction pi) {
        if (pi == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (pi.getParent() != null) {
            throw new IllegalAddException("The PI already has an existing parent \"" + pi.getParent().getQualifiedName() + "\"");
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(5);
        }
        if (this.parent instanceof Document) {
            pi.setDocument((Document)this.parent);
        }
        else {
            pi.setParent((Element)this.parent);
        }
        this.list.add(index, pi);
        ++super.modCount;
    }
    
    protected void add(final int index, final CDATA cdata) {
        if (cdata == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (this.parent instanceof Document) {
            throw new IllegalAddException("A CDATA is not allowed at the document root");
        }
        if (cdata.getParent() != null) {
            throw new IllegalAddException("The CDATA already has an existing parent \"" + cdata.getParent().getQualifiedName() + "\"");
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(5);
        }
        this.list.add(index, cdata);
        cdata.setParent((Element)this.parent);
        ++super.modCount;
    }
    
    protected void add(final int index, final Text text) {
        if (text == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (this.parent instanceof Document) {
            throw new IllegalAddException("A Text not allowed at the document root");
        }
        if (text.getParent() != null) {
            throw new IllegalAddException("The Text already has an existing parent \"" + text.getParent().getQualifiedName() + "\"");
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(5);
        }
        this.list.add(index, text);
        text.setParent((Element)this.parent);
        ++super.modCount;
    }
    
    protected void add(final int index, final EntityRef entity) {
        if (entity == null) {
            throw new IllegalAddException("Cannot add null object");
        }
        if (this.parent instanceof Document) {
            throw new IllegalAddException("An EntityRef is not allowed at the document root");
        }
        if (entity.getParent() != null) {
            throw new IllegalAddException("The EntityRef already has an existing parent \"" + entity.getParent().getQualifiedName() + "\"");
        }
        if (this.list == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
            }
            this.ensureCapacity(5);
        }
        this.list.add(index, entity);
        entity.setParent((Element)this.parent);
        ++super.modCount;
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
    
    protected void clearAndSet(final Collection collection) {
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
                this.removeParent(old.get(i));
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
    
    protected List getView(final Filter filter) {
        return new FilterList(filter);
    }
    
    protected int indexOfFirstElement() {
        if (this.list != null) {
            for (int i = 0; i < this.list.size(); ++i) {
                if (this.list.get(i) instanceof Element) {
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
        final Object old = this.list.get(index);
        this.removeParent(old);
        this.list.remove(index);
        ++super.modCount;
        return old;
    }
    
    private void removeParent(final Object obj) {
        if (obj instanceof Element) {
            final Element element = (Element)obj;
            element.setParent(null);
        }
        else if (obj instanceof Text) {
            final Text text = (Text)obj;
            text.setParent(null);
        }
        else if (obj instanceof Comment) {
            final Comment comment = (Comment)obj;
            comment.setParent(null);
        }
        else if (obj instanceof ProcessingInstruction) {
            final ProcessingInstruction pi = (ProcessingInstruction)obj;
            pi.setParent(null);
        }
        else if (obj instanceof CDATA) {
            final CDATA cdata = (CDATA)obj;
            cdata.setParent(null);
        }
        else {
            if (!(obj instanceof EntityRef)) {
                throw new IllegalArgumentException("Object '" + obj + "' unknown");
            }
            final EntityRef entity = (EntityRef)obj;
            entity.setParent(null);
        }
    }
    
    public Object set(final int index, final Object obj) {
        if (this.list == null) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + this.size());
        }
        if (obj instanceof Element && this.parent instanceof Document) {
            final int root = this.indexOfFirstElement();
            if (root >= 0 && root != index) {
                throw new IllegalAddException("Cannot add a second root element, only one is allowed");
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
    
    private int getModCount() {
        return super.modCount;
    }
    
    class FilterList extends AbstractList
    {
        protected Filter filter;
        int count;
        int expected;
        
        FilterList(final Filter filter) {
            this.count = 0;
            this.expected = -1;
            this.filter = filter;
        }
        
        public void add(final int index, final Object obj) {
            if (this.filter.canAdd(obj)) {
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
            if (this.filter.canRemove(old)) {
                old = ContentList.this.remove(adjusted);
                ++this.expected;
                --this.count;
                return old;
            }
            throw new IllegalAddException("Filter won't allow the " + old.getClass().getName() + " '" + old + "' (index " + index + ") to be removed");
        }
        
        public Object set(final int index, final Object obj) {
            Object old = null;
            if (!this.filter.canAdd(obj)) {
                throw new IllegalAddException("Filter won't allow index " + index + " to be set to " + obj.getClass().getName());
            }
            final int adjusted = this.getAdjustedIndex(index);
            old = ContentList.this.get(adjusted);
            if (!this.filter.canRemove(old)) {
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
                final Object obj = ContentList.this.list.get(i);
                if (this.filter.matches(obj)) {
                    ++this.count;
                }
            }
            this.expected = ContentList.this.getModCount();
            return this.count;
        }
        
        private final int getAdjustedIndex(final int index) {
            int adjusted = 0;
            for (int i = 0; i < ContentList.this.list.size(); ++i) {
                final Object obj = ContentList.this.list.get(i);
                if (this.filter.matches(obj)) {
                    if (index == adjusted) {
                        return i;
                    }
                    ++adjusted;
                }
            }
            if (index == adjusted) {
                return ContentList.this.list.size();
            }
            return ContentList.this.list.size() + 1;
        }
    }
    
    class FilterListIterator implements ListIterator
    {
        Filter filter;
        int lastOperation;
        int initialCursor;
        int cursor;
        int last;
        int expected;
        
        FilterListIterator(final Filter filter, final int start) {
            this.filter = filter;
            this.initialCursor = this.initializeCursor(start);
            this.last = -1;
            this.expected = ContentList.this.getModCount();
            this.lastOperation = 0;
        }
        
        public boolean hasNext() {
            this.checkConcurrentModification();
            switch (this.lastOperation) {
                case 0: {
                    this.cursor = this.initialCursor;
                    break;
                }
                case 3: {
                    this.cursor = this.last;
                    break;
                }
                case 4:
                case 5: {
                    this.cursor = this.moveForward(this.last + 1);
                    break;
                }
                case 6: {
                    this.cursor = this.moveForward(this.last);
                    break;
                }
                case 1: {
                    this.cursor = this.moveForward(this.cursor + 1);
                    break;
                }
                case 2: {
                    break;
                }
                default: {
                    throw new IllegalStateException("Unknown operation");
                }
            }
            if (this.lastOperation != 0) {
                this.lastOperation = 2;
            }
            return this.cursor < ContentList.this.size();
        }
        
        public Object next() {
            this.checkConcurrentModification();
            if (this.hasNext()) {
                this.last = this.cursor;
                this.lastOperation = 4;
                return ContentList.this.get(this.last);
            }
            this.last = ContentList.this.size();
            throw new NoSuchElementException();
        }
        
        public boolean hasPrevious() {
            this.checkConcurrentModification();
            switch (this.lastOperation) {
                case 0: {
                    this.cursor = this.initialCursor;
                    if (this.cursor >= ContentList.this.size()) {
                        this.cursor = this.moveBackward(this.initialCursor);
                        break;
                    }
                    break;
                }
                case 3:
                case 6: {
                    this.cursor = this.moveBackward(this.last - 1);
                    break;
                }
                case 2: {
                    this.cursor = this.moveBackward(this.cursor - 1);
                    break;
                }
                case 4:
                case 5: {
                    this.cursor = this.last;
                    break;
                }
                case 1: {
                    break;
                }
                default: {
                    throw new IllegalStateException("Unknown operation");
                }
            }
            if (this.lastOperation != 0) {
                this.lastOperation = 1;
            }
            return this.cursor >= 0;
        }
        
        public Object previous() {
            this.checkConcurrentModification();
            if (this.hasPrevious()) {
                this.last = this.cursor;
                this.lastOperation = 3;
                return ContentList.this.get(this.last);
            }
            this.last = -1;
            throw new NoSuchElementException();
        }
        
        public int nextIndex() {
            this.checkConcurrentModification();
            this.hasNext();
            int count = 0;
            for (int i = 0; i < ContentList.this.size(); ++i) {
                if (this.filter.matches(ContentList.this.get(i))) {
                    if (i == this.cursor) {
                        return count;
                    }
                    ++count;
                }
            }
            this.expected = ContentList.this.getModCount();
            return count;
        }
        
        public int previousIndex() {
            this.checkConcurrentModification();
            if (this.hasPrevious()) {
                int count = 0;
                for (int i = 0; i < ContentList.this.size(); ++i) {
                    if (this.filter.matches(ContentList.this.get(i))) {
                        if (i == this.cursor) {
                            return count;
                        }
                        ++count;
                    }
                }
            }
            return -1;
        }
        
        public void add(final Object obj) {
            this.checkConcurrentModification();
            if (this.filter.canAdd(obj)) {
                ++this.last;
                ContentList.this.add(this.last, obj);
                this.expected = ContentList.this.getModCount();
                this.lastOperation = 5;
                return;
            }
            throw new IllegalAddException("Filter won't allow add of " + obj.getClass().getName());
        }
        
        public void remove() {
            this.checkConcurrentModification();
            if (this.last < 0 || this.lastOperation == 6) {
                throw new IllegalStateException("no preceeding call to prev() or next()");
            }
            if (this.lastOperation == 5) {
                throw new IllegalStateException("cannot call remove() after add()");
            }
            final Object old = ContentList.this.get(this.last);
            if (this.filter.canRemove(old)) {
                ContentList.this.remove(this.last);
                this.expected = ContentList.this.getModCount();
                this.lastOperation = 6;
                return;
            }
            throw new IllegalAddException("Filter won't allow " + old.getClass().getName() + " (index " + this.last + ") to be removed");
        }
        
        public void set(final Object obj) {
            this.checkConcurrentModification();
            if (this.lastOperation == 5 || this.lastOperation == 6) {
                throw new IllegalStateException("cannot call set() after add() or remove()");
            }
            if (this.last < 0) {
                throw new IllegalStateException("no preceeding call to prev() or next()");
            }
            if (!this.filter.canAdd(obj)) {
                throw new IllegalAddException("Filter won't allow index " + this.last + " to be set to " + obj.getClass().getName());
            }
            final Object old = ContentList.this.get(this.last);
            if (!this.filter.canRemove(old)) {
                throw new IllegalAddException("Filter won't allow " + old.getClass().getName() + " (index " + this.last + ") to be removed");
            }
            ContentList.this.set(this.last, obj);
            this.expected = ContentList.this.getModCount();
        }
        
        private int initializeCursor(final int start) {
            if (start < 0) {
                throw new IndexOutOfBoundsException("Index: " + start);
            }
            int count = 0;
            for (int i = 0; i < ContentList.this.size(); ++i) {
                final Object obj = ContentList.this.get(i);
                if (this.filter.matches(obj)) {
                    if (start == count) {
                        return i;
                    }
                    ++count;
                }
            }
            if (start > count) {
                throw new IndexOutOfBoundsException("Index: " + start + " Size: " + count);
            }
            return ContentList.this.size();
        }
        
        private int moveForward(int start) {
            if (start < 0) {
                start = 0;
            }
            for (int i = start; i < ContentList.this.size(); ++i) {
                final Object obj = ContentList.this.get(i);
                if (this.filter.matches(obj)) {
                    return i;
                }
            }
            return ContentList.this.size();
        }
        
        private int moveBackward(int start) {
            if (start >= ContentList.this.size()) {
                start = ContentList.this.size() - 1;
            }
            for (int i = start; i >= 0; --i) {
                final Object obj = ContentList.this.get(i);
                if (this.filter.matches(obj)) {
                    return i;
                }
            }
            return -1;
        }
        
        private void checkConcurrentModification() {
            if (this.expected != ContentList.this.getModCount()) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

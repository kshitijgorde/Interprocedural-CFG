// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Comparator;

public abstract class AbstractTreeMap implements IntSortedMap
{
    protected static final boolean RED = false;
    protected static final boolean BLACK = true;
    protected static final Entry NIL;
    protected LiteralComparator cmp;
    protected Entry root;
    protected boolean allowDuplicates;
    protected int size;
    protected int unique;
    protected int modCount;
    protected int lastOrder;
    
    public AbstractTreeMap(final LiteralComparator literalComparator, final boolean allowDuplicates) {
        this.cmp = null;
        this.root = AbstractTreeMap.NIL;
        this.size = 0;
        this.unique = 0;
        this.modCount = 0;
        this.lastOrder = 0;
        this.cmp = ((literalComparator == null) ? DefaultLiteralComparator.getInstance() : literalComparator);
        this.allowDuplicates = allowDuplicates;
    }
    
    public boolean isAllowDuplicates() {
        return this.allowDuplicates;
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return this.root == AbstractTreeMap.NIL;
    }
    
    public Comparator comparator() {
        return this.cmp;
    }
    
    public void clear() {
        ++this.modCount;
        this.size = 0;
        this.root = AbstractTreeMap.NIL;
    }
    
    public int getMinimum() {
        return this.minimum(this.root).getValue();
    }
    
    public int getMaximum() {
        return this.maximum(this.root).getValue();
    }
    
    public int getMedian() {
        Entry entry = this.minimum(this.root);
        for (int i = 0; i < this.size / 2; ++i, entry = this.successor(entry)) {}
        return entry.getValue();
    }
    
    public int getUniqueCount() {
        return this.unique;
    }
    
    public boolean containsValue(final int n) {
        return this.root != AbstractTreeMap.NIL && this.containsValue(this.root, n);
    }
    
    private boolean containsValue(final Entry entry, final int n) {
        return entry.val == n || (entry.left != AbstractTreeMap.NIL && this.containsValue(entry.left, n)) || (entry.right != AbstractTreeMap.NIL && this.containsValue(entry.right, n));
    }
    
    public IntIterator valueIterator(final boolean b) {
        return new ValueIterator(new EntryIterator(!b));
    }
    
    protected void incrementSize(final boolean b) {
        ++this.size;
        ++this.modCount;
        if (b) {
            ++this.unique;
        }
    }
    
    protected void decrementSize(final boolean b) {
        --this.size;
        ++this.modCount;
        if (b) {
            --this.unique;
        }
    }
    
    protected abstract int compare(final Entry p0, final Entry p1);
    
    protected Entry find(final Entry entry) {
        Entry entry2 = this.root;
        while (entry2 != AbstractTreeMap.NIL) {
            final int compare = this.compare(entry, entry2);
            if (compare == 0) {
                return entry2;
            }
            if (compare < 0) {
                entry2 = entry2.left;
            }
            else {
                entry2 = entry2.right;
            }
        }
        return entry2;
    }
    
    protected Entry findPredecessor(final Entry entry) {
        Entry entry2 = this.root;
        while (entry2 != AbstractTreeMap.NIL) {
            if (this.compare(entry, entry2) > 0) {
                if (entry2.right == AbstractTreeMap.NIL) {
                    return entry2;
                }
                entry2 = entry2.right;
            }
            else {
                if (entry2.left == AbstractTreeMap.NIL) {
                    Entry entry3 = entry2.p;
                    for (Entry entry4 = entry2; entry3 != AbstractTreeMap.NIL && entry4 == entry3.left; entry4 = entry3, entry3 = entry3.p) {}
                    return entry3;
                }
                entry2 = entry2.left;
            }
        }
        return entry2;
    }
    
    protected Entry findCeiling(final Entry entry) {
        Entry entry2 = this.root;
        while (entry2 != AbstractTreeMap.NIL) {
            final int compare = this.compare(entry, entry2);
            if (compare == 0) {
                return entry2;
            }
            if (compare < 0) {
                if (entry2.left == AbstractTreeMap.NIL) {
                    return entry2;
                }
                entry2 = entry2.left;
            }
            else {
                if (entry2.right == AbstractTreeMap.NIL) {
                    Entry entry3 = entry2.p;
                    for (Entry entry4 = entry2; entry3 != AbstractTreeMap.NIL && entry4 == entry3.right; entry4 = entry3, entry3 = entry3.p) {}
                    return entry3;
                }
                entry2 = entry2.right;
            }
        }
        return entry2;
    }
    
    protected Entry minimum(Entry left) {
        while (left.left != AbstractTreeMap.NIL) {
            left = left.left;
        }
        return left;
    }
    
    protected Entry maximum(Entry right) {
        while (right.right != AbstractTreeMap.NIL) {
            right = right.right;
        }
        return right;
    }
    
    protected Entry successor(Entry entry) {
        if (entry.right != AbstractTreeMap.NIL) {
            return this.minimum(entry.right);
        }
        Entry entry2;
        for (entry2 = entry.p; entry2 != AbstractTreeMap.NIL && entry == entry2.right; entry = entry2, entry2 = entry2.p) {}
        return entry2;
    }
    
    protected Entry predecessor(Entry entry) {
        if (entry.left != AbstractTreeMap.NIL) {
            return this.maximum(entry.left);
        }
        Entry entry2;
        for (entry2 = entry.p; entry2 != AbstractTreeMap.NIL && entry == entry2.left; entry = entry2, entry2 = entry2.p) {}
        return entry2;
    }
    
    protected void rotateLeft(final Entry entry) {
        final Entry right = entry.right;
        entry.right = right.left;
        if (right.left != AbstractTreeMap.NIL) {
            right.left.p = entry;
        }
        right.p = entry.p;
        if (entry.p == AbstractTreeMap.NIL) {
            this.root = right;
        }
        else if (entry.p.left == entry) {
            entry.p.left = right;
        }
        else {
            entry.p.right = right;
        }
        right.left = entry;
        entry.p = right;
    }
    
    protected void rotateRight(final Entry entry) {
        final Entry left = entry.left;
        entry.left = left.right;
        if (left.right != AbstractTreeMap.NIL) {
            left.right.p = entry;
        }
        left.p = entry.p;
        if (entry.p == AbstractTreeMap.NIL) {
            this.root = left;
        }
        else if (entry.p.right == entry) {
            entry.p.right = left;
        }
        else {
            entry.p.left = left;
        }
        left.right = entry;
        entry.p = left;
    }
    
    protected void fixUpInsert(Entry entry) {
        entry.color = false;
        while (entry != AbstractTreeMap.NIL && entry != this.root && !entry.p.color) {
            if (entry.p == entry.p.p.left) {
                final Entry right = entry.p.p.right;
                if (!right.color) {
                    entry.p.color = true;
                    right.color = true;
                    entry.p.p.color = false;
                    entry = entry.p.p;
                }
                else {
                    if (entry == entry.p.right) {
                        entry = entry.p;
                        this.rotateLeft(entry);
                    }
                    entry.p.color = true;
                    entry.p.p.color = false;
                    if (entry.p.p == AbstractTreeMap.NIL) {
                        continue;
                    }
                    this.rotateRight(entry.p.p);
                }
            }
            else {
                final Entry left = entry.p.p.left;
                if (!left.color) {
                    entry.p.color = true;
                    left.color = true;
                    entry.p.p.color = false;
                    entry = entry.p.p;
                }
                else {
                    if (entry == entry.p.left) {
                        entry = entry.p;
                        this.rotateRight(entry);
                    }
                    entry.p.color = true;
                    entry.p.p.color = false;
                    if (entry.p.p == AbstractTreeMap.NIL) {
                        continue;
                    }
                    this.rotateLeft(entry.p.p);
                }
            }
        }
        this.root.color = true;
    }
    
    protected void fixUpRemove(Entry entry) {
        while (entry != this.root && entry.color) {
            if (entry == entry.p.left) {
                Entry entry2 = entry.p.right;
                if (!entry2.color) {
                    entry2.color = true;
                    entry.p.color = false;
                    this.rotateLeft(entry.p);
                    entry2 = entry.p.right;
                }
                if (entry2.left.color && entry2.right.color) {
                    entry2.color = false;
                    entry = entry.p;
                }
                else {
                    if (entry2.right.color) {
                        entry2.left.color = true;
                        entry2.color = false;
                        this.rotateRight(entry2);
                        entry2 = entry.p.right;
                    }
                    entry2.color = entry.p.color;
                    entry.p.color = true;
                    entry2.right.color = true;
                    this.rotateLeft(entry.p);
                    entry = this.root;
                }
            }
            else {
                Entry entry3 = entry.p.left;
                if (!entry3.color) {
                    entry3.color = true;
                    entry.p.color = false;
                    this.rotateRight(entry.p);
                    entry3 = entry.p.left;
                }
                if (entry3.right.color && entry3.left.color) {
                    entry3.color = false;
                    entry = entry.p;
                }
                else {
                    if (entry3.left.color) {
                        entry3.right.color = true;
                        entry3.color = false;
                        this.rotateLeft(entry3);
                        entry3 = entry.p.left;
                    }
                    entry3.color = entry.p.color;
                    entry.p.color = true;
                    entry3.left.color = true;
                    this.rotateRight(entry.p);
                    entry = this.root;
                }
            }
        }
        entry.color = true;
    }
    
    protected void remove(final Entry entry) {
        final boolean b = !entry.keyEquals(entry.left) && !entry.keyEquals(entry.right) && !entry.keyEquals(entry.p);
        final Entry entry2 = (entry.left != AbstractTreeMap.NIL && entry.right != AbstractTreeMap.NIL) ? this.successor(entry) : entry;
        final Entry right = (entry2.left != AbstractTreeMap.NIL) ? entry2.left : entry2.right;
        right.p = entry2.p;
        if (entry2.p == AbstractTreeMap.NIL) {
            this.root = right;
        }
        else if (entry2 == entry2.p.left) {
            entry2.p.left = right;
        }
        else {
            entry2.p.right = right;
        }
        if (entry2 != entry) {
            entry.copyFields(entry2);
        }
        if (entry2.color) {
            this.fixUpRemove(right);
        }
        this.decrementSize(b);
    }
    
    static {
        NIL = new Entry(Integer.MIN_VALUE);
        final Entry nil = AbstractTreeMap.NIL;
        final Entry nil2 = AbstractTreeMap.NIL;
        final Entry nil3 = AbstractTreeMap.NIL;
        final Entry nil4 = AbstractTreeMap.NIL;
        nil3.p = nil4;
        nil2.right = nil4;
        nil.left = nil4;
    }
    
    protected class ValueIterator extends IntIterator
    {
        EntryIterator m_iter;
        
        public ValueIterator(final EntryIterator iter) {
            this.m_iter = iter;
        }
        
        public boolean hasNext() {
            return this.m_iter.hasNext();
        }
        
        public int nextInt() {
            return this.m_iter.nextEntry().val;
        }
        
        public void remove() {
            this.m_iter.remove();
        }
    }
    
    protected class EntryIterator extends AbstractLiteralIterator
    {
        private int expectedModCount;
        private Entry lastReturned;
        private boolean reverse;
        Entry next;
        Entry end;
        
        EntryIterator(final boolean b) {
            this.expectedModCount = AbstractTreeMap.this.modCount;
            this.lastReturned = AbstractTreeMap.NIL;
            this.reverse = false;
            this.next = (b ? AbstractTreeMap.this.maximum(AbstractTreeMap.this.root) : AbstractTreeMap.this.minimum(AbstractTreeMap.this.root));
            this.end = AbstractTreeMap.NIL;
        }
        
        EntryIterator(final Entry next, final Entry end) {
            this.expectedModCount = AbstractTreeMap.this.modCount;
            this.lastReturned = AbstractTreeMap.NIL;
            this.reverse = false;
            this.next = next;
            this.end = end;
            this.reverse = (next == AbstractTreeMap.NIL || (end != AbstractTreeMap.NIL && AbstractTreeMap.this.compare(next, end) > 0));
        }
        
        public boolean hasNext() {
            return this.next != this.end;
        }
        
        final Entry nextEntry() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            if (AbstractTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            this.lastReturned = this.next;
            this.next = (this.reverse ? AbstractTreeMap.this.predecessor(this.next) : AbstractTreeMap.this.successor(this.next));
            if (this.lastReturned == AbstractTreeMap.NIL) {
                System.err.println("Encountered NIL in iteration!");
            }
            return this.lastReturned;
        }
        
        public Object next() {
            return this.nextEntry();
        }
        
        public void remove() {
            if (this.lastReturned == AbstractTreeMap.NIL) {
                throw new IllegalStateException();
            }
            if (AbstractTreeMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (this.lastReturned.left != AbstractTreeMap.NIL && this.lastReturned.right != AbstractTreeMap.NIL) {
                this.next = this.lastReturned;
            }
            AbstractTreeMap.this.remove(this.lastReturned);
            ++this.expectedModCount;
            this.lastReturned = AbstractTreeMap.NIL;
        }
    }
    
    public static class Entry
    {
        int val;
        int order;
        Entry left;
        Entry right;
        Entry p;
        boolean color;
        
        public Entry(final int val) {
            this.left = null;
            this.right = null;
            this.color = true;
            this.val = val;
        }
        
        public Entry(final int val, final Entry p3, final int order) {
            this.left = null;
            this.right = null;
            this.color = true;
            this.val = val;
            this.p = p3;
            this.order = order;
            this.left = AbstractTreeMap.NIL;
            this.right = AbstractTreeMap.NIL;
        }
        
        public int getIntKey() {
            throw new UnsupportedOperationException("Unsupported");
        }
        
        public long getLongKey() {
            throw new UnsupportedOperationException("Unsupported");
        }
        
        public float getFloatKey() {
            throw new UnsupportedOperationException("Unsupported");
        }
        
        public double getDoubleKey() {
            throw new UnsupportedOperationException("Unsupported");
        }
        
        public Object getKey() {
            return null;
        }
        
        public int getValue() {
            return this.val;
        }
        
        public int getOrder() {
            return this.order;
        }
        
        public int setValue(final int val) {
            final int val2 = this.val;
            this.val = val;
            return val2;
        }
        
        public boolean keyEquals(final Entry entry) {
            final Object key = this.getKey();
            return (key == null) ? (key == entry.getKey()) : key.equals(entry.getKey());
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            final Entry entry = (Entry)o;
            return this.val == entry.val && this.getKey() == entry.getKey();
        }
        
        public int hashCode() {
            return this.getKey().hashCode() ^ this.val;
        }
        
        public String toString() {
            return this.getKey() + "=" + this.val;
        }
        
        public void copyFields(final Entry entry) {
            this.val = entry.val;
            this.order = entry.order;
        }
    }
    
    protected class KeyIterator extends EntryIterator
    {
        public KeyIterator() {
            super(false);
        }
        
        public KeyIterator(final Entry entry, final Entry entry2) {
            super(entry, entry2);
        }
        
        public Object next() {
            return this.nextEntry().getKey();
        }
    }
}

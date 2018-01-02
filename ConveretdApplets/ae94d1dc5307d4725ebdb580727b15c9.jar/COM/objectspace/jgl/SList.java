// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.io.Serializable;
import java.util.Enumeration;

public class SList implements Sequence
{
    SListNode myHead;
    SListNode myTail;
    int myLength;
    
    public SList() {
    }
    
    public SList(int n) {
        while (--n >= 0) {
            this.add(null);
        }
    }
    
    public SList(int n, final Object o) {
        while (--n >= 0) {
            this.add(o);
        }
    }
    
    public SList(final SList list) {
        this.copy(list);
    }
    
    public synchronized Object clone() {
        return new SList(this);
    }
    
    public boolean equals(final Object o) {
        return o instanceof SList && this.equals((SList)o);
    }
    
    public synchronized boolean equals(final SList list) {
        synchronized (list) {
            return Comparing.equal(this, list);
        }
    }
    
    public synchronized int hashCode() {
        return Hashing.orderedHash(this);
    }
    
    public synchronized String toString() {
        return Printing.toString(this, "SList");
    }
    
    public synchronized SListIterator begin() {
        return new SListIterator(this, this.myHead);
    }
    
    public synchronized SListIterator end() {
        return new SListIterator(this, null);
    }
    
    public synchronized Enumeration elements() {
        return new SListIterator(this, this.myHead);
    }
    
    public ForwardIterator start() {
        return this.begin();
    }
    
    public ForwardIterator finish() {
        return this.end();
    }
    
    public synchronized void copy(final SList list) {
        if (this == list) {
            return;
        }
        synchronized (list) {
            this.myHead = null;
            this.myTail = null;
            for (SListNode sListNode = list.myHead; sListNode != null; sListNode = sListNode.next) {
                final SListNode myTail = new SListNode();
                myTail.object = sListNode.object;
                if (this.myTail == null) {
                    this.myHead = myTail;
                }
                else {
                    this.myTail.next = myTail;
                }
                this.myTail = myTail;
            }
            this.myLength = list.myLength;
        }
    }
    
    public boolean isEmpty() {
        return this.myLength == 0;
    }
    
    public int size() {
        return this.myLength;
    }
    
    public int maxSize() {
        return Allocator.maxSize();
    }
    
    public synchronized Object at(int n) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        SListNode sListNode = this.myHead;
        while (n-- > 0) {
            sListNode = sListNode.next;
        }
        return sListNode.object;
    }
    
    public synchronized void put(int n, final Object object) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        SListNode sListNode = this.myHead;
        while (n-- > 0) {
            sListNode = sListNode.next;
        }
        sListNode.object = object;
    }
    
    public synchronized SListIterator insert(final SListIterator sListIterator, final Object o) {
        return new SListIterator(this, this.insert(sListIterator.myNode, o));
    }
    
    public synchronized void insert(final int n, final Object o) {
        if (n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        this.insert(this.nodeAt(n), o);
    }
    
    public synchronized void insert(final SListIterator sListIterator, final int n, final Object o) {
        this.insert(sListIterator.myNode, n, o);
    }
    
    public synchronized void insert(final int n, final int n2, final Object o) {
        if (n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        this.insert(this.nodeAt(n), n2, o);
    }
    
    public synchronized void insert(final SListIterator sListIterator, final InputIterator inputIterator, final InputIterator inputIterator2) {
        final InputIterator inputIterator3 = (InputIterator)inputIterator.clone();
        while (!inputIterator3.equals(inputIterator2)) {
            this.insert(sListIterator, inputIterator3.nextElement());
        }
    }
    
    public synchronized Object back() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("SList is empty");
        }
        return this.myTail.object;
    }
    
    public synchronized Object front() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("SList is empty");
        }
        return this.myHead.object;
    }
    
    public synchronized void clear() {
        this.myHead = null;
        this.myTail = null;
        this.myLength = 0;
    }
    
    public synchronized Object popBack() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("SList is empty");
        }
        SListNode myTail = null;
        for (SListNode sListNode = this.myHead; sListNode != this.myTail; sListNode = sListNode.next) {
            myTail = sListNode;
        }
        Object o;
        if (myTail == null) {
            o = this.myHead.object;
            this.myHead = null;
        }
        else {
            o = myTail.next.object;
            myTail.next = null;
        }
        this.myTail = myTail;
        --this.myLength;
        return o;
    }
    
    public synchronized void pushFront(final Object object) {
        final SListNode sListNode = new SListNode();
        sListNode.object = object;
        sListNode.next = this.myHead;
        this.myHead = sListNode;
        if (++this.myLength == 1) {
            this.myTail = sListNode;
        }
    }
    
    public synchronized Object popFront() {
        if (this.myLength == 0) {
            throw new InvalidOperationException("Slist is empty");
        }
        final Object object = this.myHead.object;
        this.myHead = this.myHead.next;
        if (--this.myLength == 0) {
            this.myTail = null;
        }
        return object;
    }
    
    public synchronized Object add(final Object object) {
        final SListNode myTail = new SListNode();
        myTail.object = object;
        if (++this.myLength == 1) {
            this.myHead = myTail;
        }
        else {
            this.myTail.next = myTail;
        }
        this.myTail = myTail;
        return null;
    }
    
    public void pushBack(final Object o) {
        this.add(o);
    }
    
    public synchronized void swap(final SList list) {
        synchronized (list) {
            final SListNode myHead = this.myHead;
            this.myHead = list.myHead;
            list.myHead = myHead;
            final SListNode myTail = this.myTail;
            this.myTail = list.myTail;
            list.myTail = myTail;
            final int myLength = this.myLength;
            this.myLength = list.myLength;
            list.myLength = myLength;
        }
    }
    
    public synchronized int remove(final Object o) {
        return this.remove(this.myHead, null, o, this.myLength);
    }
    
    public synchronized int remove(final Object o, final int n) {
        return this.remove(this.myHead, null, o, n);
    }
    
    public synchronized int remove(final Enumeration enumeration, final Enumeration enumeration2, final Object o) {
        if (!(enumeration instanceof SListIterator) || !(enumeration2 instanceof SListIterator)) {
            throw new IllegalArgumentException("Enumeration not a SListIterator");
        }
        if (((SListIterator)enumeration).mySList != this || ((SListIterator)enumeration2).mySList != this) {
            throw new IllegalArgumentException("Enumeration not for this SList");
        }
        return this.remove(((SListIterator)enumeration).myNode, ((SListIterator)enumeration2).myNode, o, this.myLength);
    }
    
    public synchronized int remove(final int n, final int n2, final Object o) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return this.remove(this.nodeAt(n), this.nodeAt(n2 + 1), o, this.myLength);
    }
    
    public synchronized int replace(final Object o, final Object o2) {
        return Replacing.replace(this, o, o2);
    }
    
    public synchronized int replace(final SListIterator sListIterator, final SListIterator sListIterator2, final Object o, final Object o2) {
        return Replacing.replace(sListIterator, sListIterator2, o, o2);
    }
    
    public synchronized int replace(final int n, final int n2, final Object o, final Object o2) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return Replacing.replace(this.iteratorAt(n), this.iteratorAt(n2 + 1), o, o2);
    }
    
    public synchronized int count(final Object o) {
        return Counting.count(this, o);
    }
    
    public synchronized int count(final SListIterator sListIterator, final SListIterator sListIterator2, final Object o) {
        return Counting.count(sListIterator, sListIterator2, o);
    }
    
    public synchronized int count(final int n, final int n2, final Object o) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return Counting.count(this.iteratorAt(n), this.iteratorAt(n2 + 1), o);
    }
    
    public synchronized SListIterator find(final Object o) {
        return (SListIterator)Finding.find(this, o);
    }
    
    public synchronized int indexOf(final Object o) {
        final SListIterator sListIterator = (SListIterator)Finding.find(this, o);
        return (sListIterator.myNode == null) ? -1 : sListIterator.index();
    }
    
    public synchronized SListIterator find(final SListIterator sListIterator, final SListIterator sListIterator2, final Object o) {
        return (SListIterator)Finding.find(sListIterator, sListIterator2, o);
    }
    
    public synchronized int indexOf(final int n, final int n2, final Object o) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        final SListIterator iterator = this.iteratorAt(n2 + 1);
        final SListIterator sListIterator = (SListIterator)Finding.find(this.iteratorAt(n), iterator, o);
        return (sListIterator.myNode == iterator.myNode) ? -1 : sListIterator.index();
    }
    
    public synchronized boolean contains(final Object o) {
        return !this.find(o).equals(this.end());
    }
    
    public synchronized Object remove(final Enumeration enumeration) {
        if (!(enumeration instanceof SListIterator)) {
            throw new IllegalArgumentException("Enumeration not a SListIterator");
        }
        if (((SListIterator)enumeration).mySList != this) {
            throw new IllegalArgumentException("Enumeration not for this SList");
        }
        final Object value = ((SListIterator)enumeration).get();
        this.remove(((SListIterator)enumeration).myNode);
        return value;
    }
    
    public synchronized Object remove(final int n) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        return this.remove(this.nodeAt(n));
    }
    
    public synchronized int remove(final Enumeration enumeration, final Enumeration enumeration2) {
        if (!(enumeration instanceof SListIterator) || !(enumeration2 instanceof SListIterator)) {
            throw new IllegalArgumentException("Enumeration not a SListIterator");
        }
        if (((SListIterator)enumeration).mySList != this || ((SListIterator)enumeration2).mySList != this) {
            throw new IllegalArgumentException("Enumeration not for this SList");
        }
        return this.remove(((SListIterator)enumeration).myNode, ((SListIterator)enumeration2).myNode);
    }
    
    public synchronized int remove(final int n, final int n2) {
        if (n2 < n) {
            return 0;
        }
        this.checkRange(n, n2);
        return this.remove(this.nodeAt(n), this.nodeAt(n2 + 1));
    }
    
    public synchronized void splice(final SListIterator sListIterator, final SList list) {
        this.splice(sListIterator.myNode, list);
    }
    
    public synchronized void splice(final int n, final SList list) {
        if (n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        this.splice(this.nodeAt(n), list);
    }
    
    public synchronized void splice(final SListIterator sListIterator, final SList list, final SListIterator sListIterator2) {
        this.splice(sListIterator.myNode, list, sListIterator2.myNode);
    }
    
    public synchronized void splice(final int n, final SList list, final int n2) {
        if (n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        if (n2 < 0 || n2 >= list.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n2))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(list.myLength - 1)));
        }
        this.splice(this.nodeAt(n), list, list.nodeAt(n2));
    }
    
    public synchronized void splice(final SListIterator sListIterator, final SList list, final SListIterator sListIterator2, final SListIterator sListIterator3) {
        this.splice(sListIterator.myNode, list, sListIterator2.myNode, sListIterator3.myNode);
    }
    
    public synchronized void splice(final int n, final SList list, final int n2, final int n3) {
        if (n > this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to insert at index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength)));
        }
        if (n2 < 0 || n2 >= list.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n2))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(list.myLength - 1)));
        }
        if (n3 < 0 || n3 >= list.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n3))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(list.myLength - 1)));
        }
        this.splice(this.nodeAt(n), list, list.nodeAt(n2), list.nodeAt(n3 + 1));
    }
    
    private Object remove(final SListNode sListNode) {
        SListNode myTail = null;
        for (SListNode sListNode2 = this.myHead; sListNode2 != sListNode; sListNode2 = sListNode2.next) {
            myTail = sListNode2;
        }
        if (myTail == null) {
            this.myHead = sListNode.next;
        }
        else {
            myTail.next = sListNode.next;
        }
        if (sListNode == this.myTail) {
            this.myTail = myTail;
        }
        --this.myLength;
        return sListNode.object;
    }
    
    private int remove(SListNode next, final SListNode sListNode) {
        SListNode myTail = null;
        for (SListNode sListNode2 = this.myHead; sListNode2 != next; sListNode2 = sListNode2.next) {
            myTail = sListNode2;
        }
        if (myTail == null) {
            this.myHead = sListNode;
        }
        else {
            myTail.next = sListNode;
        }
        if (sListNode == null) {
            this.myTail = myTail;
        }
        int n;
        for (n = 0; next != sListNode; next = next.next, ++n) {}
        this.myLength -= n;
        return n;
    }
    
    private void splice(final SListNode next, final SList list) {
        synchronized (list) {
            if (this == list || list.myLength == 0) {
                return;
            }
            SListNode sListNode = null;
            for (SListNode sListNode2 = this.myHead; sListNode2 != next; sListNode2 = sListNode2.next) {
                sListNode = sListNode2;
            }
            if (sListNode == null) {
                this.myHead = list.myHead;
            }
            else {
                sListNode.next = list.myHead;
            }
            if (next == null) {
                this.myTail = list.myTail;
            }
            else {
                list.myTail.next = next;
            }
            this.myLength += list.myLength;
            list.clear();
        }
    }
    
    private void splice(final SListNode next, final SList list, final SListNode myTail) {
        synchronized (list) {
            if (next == myTail || next == myTail.next) {
                return;
            }
            list.remove(myTail);
            SListNode sListNode = null;
            for (SListNode sListNode2 = this.myHead; sListNode2 != next; sListNode2 = sListNode2.next) {
                sListNode = sListNode2;
            }
            if (sListNode == null) {
                this.myHead = myTail;
            }
            else {
                sListNode.next = myTail;
            }
            if (next == null) {
                this.myTail = myTail;
            }
            myTail.next = next;
            ++this.myLength;
        }
    }
    
    private void splice(final SListNode next, final SList list, final SListNode sListNode, final SListNode sListNode2) {
        if (sListNode == sListNode2) {
            return;
        }
        synchronized (list) {
            if (list == this) {
                SListNode next2 = sListNode;
                if (next == sListNode || next == sListNode2) {
                    return;
                }
                while (next2 != sListNode2) {
                    if (next == next2) {
                        throw new InvalidOperationException("Tried to splice into an overlapping area.");
                    }
                    next2 = next2.next;
                }
            }
            list.remove(sListNode, sListNode2);
            SListNode sListNode3 = null;
            for (SListNode sListNode4 = this.myHead; sListNode4 != next; sListNode4 = sListNode4.next) {
                sListNode3 = sListNode4;
            }
            if (sListNode3 == null) {
                this.myHead = sListNode;
            }
            else {
                sListNode3.next = sListNode;
            }
            SListNode next3;
            int n;
            for (next3 = sListNode, n = 1; next3.next != sListNode2; next3 = next3.next, ++n) {}
            if (next == null) {
                this.myTail = next3;
            }
            next3.next = next;
            this.myLength += n;
        }
    }
    
    private SListNode insert(final SListNode next, final Object object) {
        SListNode sListNode = null;
        for (SListNode sListNode2 = this.myHead; sListNode2 != next; sListNode2 = sListNode2.next) {
            sListNode = sListNode2;
        }
        final SListNode sListNode3 = new SListNode();
        sListNode3.object = object;
        if (sListNode == null) {
            this.myHead = sListNode3;
        }
        else {
            sListNode.next = sListNode3;
        }
        sListNode3.next = next;
        ++this.myLength;
        return sListNode3;
    }
    
    private void insert(final SListNode next, int n, final Object object) {
        SListNode sListNode = null;
        SListNode sListNode2 = null;
        this.myLength += n;
        for (SListNode sListNode3 = this.myHead; sListNode3 != next; sListNode3 = sListNode3.next) {
            sListNode = sListNode3;
        }
        while (--n >= 0) {
            sListNode2 = new SListNode();
            sListNode2.object = object;
            if (sListNode == null) {
                this.myHead = sListNode2;
            }
            else {
                sListNode.next = sListNode2;
            }
            sListNode = sListNode2;
        }
        sListNode2.next = next;
    }
    
    private int remove(final SListNode sListNode, final SListNode sListNode2, final Object o, int n) {
        if (n <= 0) {
            return 0;
        }
        int n2 = 0;
        SListNode myTail = null;
        SListNode sListNode3;
        for (sListNode3 = this.myHead; sListNode3 != sListNode; sListNode3 = sListNode3.next) {
            myTail = sListNode3;
        }
        while (n > 0 && sListNode3 != sListNode2) {
            if (sListNode3.object.equals(o)) {
                if (myTail == null) {
                    this.myHead = sListNode3.next;
                }
                else {
                    myTail.next = sListNode3.next;
                }
                if (sListNode3 == this.myTail) {
                    this.myTail = myTail;
                }
                ++n2;
                --n;
            }
            else {
                myTail = sListNode3;
            }
            sListNode3 = sListNode3.next;
        }
        this.myLength -= n2;
        return n2;
    }
    
    private SListIterator iteratorAt(int n) {
        SListNode sListNode = this.myHead;
        while (n-- > 0) {
            sListNode = sListNode.next;
        }
        return new SListIterator(this, sListNode);
    }
    
    private SListNode nodeAt(int n) {
        SListNode sListNode = this.myHead;
        while (n-- > 0) {
            sListNode = sListNode.next;
        }
        return sListNode;
    }
    
    private void checkRange(final int n, final int n2) {
        if (n < 0 || n >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
        if (n2 < 0 || n2 >= this.myLength) {
            throw new IndexOutOfBoundsException(String.valueOf(String.valueOf(String.valueOf("Attempt to access index ").concat(String.valueOf(n2))).concat(String.valueOf(" when valid range is 0.."))).concat(String.valueOf(this.myLength - 1)));
        }
    }
    
    final class SListNode implements Serializable
    {
        public SListNode next;
        public Object object;
    }
}

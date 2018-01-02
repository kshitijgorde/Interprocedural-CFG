// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import jfig.utils.ShellSort;
import java.awt.Point;
import java.util.Enumeration;

public final class FigObjectList implements Enumeration
{
    ListNode root;
    ListNode otmp;
    String name;
    ListNode enumerationNode;
    
    public final ListNode get_root() {
        return this.root;
    }
    
    public final ListNode get_first() {
        if (this.root.next != this.root) {
            return this.root.next;
        }
        return null;
    }
    
    public final ListNode get_last() {
        if (this.root.prev != this.root) {
            return this.root.prev;
        }
        return null;
    }
    
    public final ListNode get_next(final ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        if (listNode.next != this.root) {
            return listNode.next;
        }
        return null;
    }
    
    public final ListNode get_prev(final ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        if (listNode.prev != this.root) {
            return listNode.prev;
        }
        return null;
    }
    
    public final void append(final FigObject figObject) {
        final ListNode listNode = new ListNode(figObject, this.root.prev, this.root);
        this.root.prev.next = listNode;
        this.root.prev = listNode;
    }
    
    public final void prepend(final FigObject figObject) {
        final ListNode listNode = new ListNode(figObject, this.root, this.root.next);
        this.root.next.prev = listNode;
        this.root.next = listNode;
    }
    
    public final void insert(final FigObject figObject) {
        final int layer = figObject.getLayer();
        this.otmp = this.root.next;
        while (this.otmp != this.root) {
            if (this.otmp.get_obj().getLayer() <= layer) {
                final ListNode listNode = new ListNode(figObject, this.otmp.prev, this.otmp);
                this.otmp.prev.next = listNode;
                this.otmp.prev = listNode;
                return;
            }
            this.otmp = this.otmp.next;
        }
        final ListNode listNode2 = new ListNode(figObject, this.root.prev, this.root);
        this.root.prev.next = listNode2;
        this.root.prev = listNode2;
    }
    
    public final boolean delete(final ListNode listNode) {
        this.otmp = this.root.next;
        while (this.otmp != this.root) {
            if (this.otmp == listNode) {
                this.otmp.prev.next = this.otmp.next;
                this.otmp.next.prev = this.otmp.prev;
                this.otmp = null;
                return true;
            }
            this.otmp = this.otmp.next;
        }
        return false;
    }
    
    public final boolean delete(final FigObject figObject) {
        this.otmp = this.root.next;
        while (this.otmp != this.root) {
            if (this.otmp.get_obj() == figObject) {
                this.otmp.prev.next = this.otmp.next;
                this.otmp.next.prev = this.otmp.prev;
                this.otmp = null;
                return true;
            }
            this.otmp = this.otmp.next;
        }
        return false;
    }
    
    public final boolean deleteAllSLOW() {
        this.otmp = this.root.next;
        while (this.otmp != this.root) {
            this.otmp.prev.next = this.otmp.next;
            this.otmp.next.prev = this.otmp.prev;
            this.otmp = this.otmp.next;
        }
        return true;
    }
    
    public final boolean deleteAll() {
        this.root.next = this.root;
        this.root.prev = this.root;
        return true;
    }
    
    public final FigObjectList copy() {
        final FigObjectList list = new FigObjectList();
        this.otmp = this.root.next;
        while (this.otmp != this.root) {
            list.append(this.otmp.obj);
            this.otmp = this.otmp.next;
        }
        return list;
    }
    
    public final ListNode search(final Point point) {
        return this.search(point, this.root.next);
    }
    
    public final ListNode search(final Point point, final ListNode otmp) {
        double n = 1.0E10;
        ListNode otmp2 = null;
        this.otmp = otmp;
        while (this.otmp != this.root) {
            final double minDistance = this.otmp.obj.minDistance(point);
            if (minDistance < n) {
                n = minDistance;
                otmp2 = this.otmp;
            }
            if (minDistance == 0.0) {
                return this.otmp;
            }
            this.otmp = this.otmp.next;
        }
        return otmp2;
    }
    
    public ListNode search(final Point point, ListNode next, final int n) {
        double n2 = n;
        ListNode otmp = null;
        if (next == null) {
            next = this.root.next;
        }
        this.otmp = next;
        while (this.otmp != this.root) {
            final double minDistance = this.otmp.obj.minDistance(point);
            if (minDistance < n2) {
                n2 = minDistance;
                otmp = this.otmp;
            }
            if (minDistance == 0.0) {
                return this.otmp;
            }
            this.otmp = this.otmp.next;
        }
        return otmp;
    }
    
    public ListNode search(final Point point, final int n) {
        return this.search(point, this.root.next, n);
    }
    
    public void print() {
        System.out.println(this.name + ".print():");
        this.otmp = this.root.next;
        while (this.otmp != this.root) {
            System.out.println(this.otmp.get_obj().toString());
            this.otmp = this.otmp.next;
        }
        System.out.println(this.name + " ok");
    }
    
    public boolean empty() {
        return this.root.next == this.root;
    }
    
    public Enumeration elements_old() {
        this.enumerationNode = this.root;
        return this;
    }
    
    public boolean hasMoreElements() {
        return this.enumerationNode.get_next() != this.root;
    }
    
    public Object nextElement() {
        if (this.enumerationNode.get_next() == this.root) {
            return null;
        }
        this.enumerationNode = this.enumerationNode.get_next();
        return this.enumerationNode.get_obj();
    }
    
    public Enumeration elements() {
        if (this == null) {
            throw null;
        }
        return new ListEnumerator();
    }
    
    public Enumeration reverseElements() {
        if (this == null) {
            throw null;
        }
        return (Enumeration)new FigObjectList.ReverseListEnumerator(this);
    }
    
    public void sort() {
        synchronized (this) {
            int n = 0;
            this.otmp = this.root.next;
            while (this.otmp != this.root) {
                ++n;
                this.otmp = this.otmp.next;
            }
            if (n < 2) {
                // monitorexit(this)
                return;
            }
            final double[] array = new double[n];
            final ListNode[] array2 = new ListNode[n];
            this.otmp = this.root.next;
            for (int i = 0; i < n; ++i) {
                array2[i] = this.otmp;
                array[i] = this.otmp.get_obj().getLayer();
                this.otmp = this.otmp.next;
            }
            ShellSort.shellSort(array, (Object[])array2);
            this.deleteAll();
            for (int j = 0; j < n; ++j) {
                array2[j].next = this.root.next;
                this.root.next.prev = array2[j];
                this.root.next = array2[j];
                array2[j].prev = this.root;
            }
        }
    }
    
    public FigObjectList() {
        this.enumerationNode = this.root;
        this.root = new ListNode();
        this.root.next = this.root;
        this.root.prev = this.root;
        this.root.obj = null;
        this.name = "unnamed Object list";
    }
    
    public FigObjectList(final String name) {
        this();
        this.name = name;
    }
    
    class ListEnumerator implements Enumeration
    {
        ListNode cursor;
        
        public Object nextElement() {
            if (this.cursor.get_next() == null) {
                return null;
            }
            if (this.cursor.get_next() == FigObjectList.this.root) {
                return null;
            }
            this.cursor = this.cursor.get_next();
            return this.cursor.get_obj();
        }
        
        public boolean hasMoreElements() {
            return this.cursor.get_next() != null && this.cursor.get_next() != FigObjectList.this.root;
        }
        
        public ListEnumerator() {
            this.cursor = null;
            this.cursor = FigObjectList.this.root;
        }
    }
}

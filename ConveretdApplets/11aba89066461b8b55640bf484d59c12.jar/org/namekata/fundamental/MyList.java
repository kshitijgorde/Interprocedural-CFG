// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class MyList
{
    private Object value;
    private MyList next;
    private int count;
    
    public MyList() {
        this.value = null;
        this.next = null;
        this.count = 0;
    }
    
    public MyList(final Object obj) {
        this.value = null;
        this.next = null;
        this.count = 0;
        ++this.count;
        this.value = obj;
    }
    
    public void add(final Object obj) {
        if (this.value == null) {
            ++this.count;
            this.value = obj;
            return;
        }
        final MyList n = new MyList(obj);
        MyList prev;
        for (prev = this; prev.next != null; prev = prev.next) {}
        prev.next = n;
        ++this.count;
    }
    
    public void append(MyList m) {
        if (m == null) {
            return;
        }
        if (m.count == 0) {
            return;
        }
        final MyList tm = m;
        while (m != null && m.value != null) {
            this.add(m.value);
            m = m.next;
        }
    }
    
    public boolean remove(final Object obj) {
        if (this.value == null) {
            return false;
        }
        if (((MyOrder)this.value).equals(obj)) {
            if (this.next == null) {
                this.value = null;
                --this.count;
                return true;
            }
            this.value = this.next.value;
            this.next = this.next.next;
            --this.count;
            return true;
        }
        else {
            MyList prev = this;
            MyList p = this;
            boolean found = false;
            while (!(found = ((MyOrder)p.value).equals(obj)) && p.next != null) {
                prev = p;
                p = p.next;
            }
            if (found) {
                prev.next = p.next;
                --this.count;
                return true;
            }
            return false;
        }
    }
    
    public MyList getNext() {
        return this.next;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    @Override
    public String toString() {
        String ret = "";
        if (this.value == null) {
            return ret;
        }
        MyList p;
        for (p = this; p.next != null; p = p.next) {
            ret = String.valueOf(ret) + p.value.toString() + ", ";
        }
        ret = String.valueOf(ret) + p.value.toString();
        return ret;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class MyOrderedList
{
    private boolean increasing;
    private Object value;
    private MyOrderedList next;
    private int count;
    
    public MyOrderedList() {
        this.increasing = true;
        this.value = null;
        this.next = null;
        this.count = 0;
    }
    
    public MyOrderedList(final boolean inc) {
        this.increasing = true;
        this.value = null;
        this.next = null;
        this.count = 0;
        this.increasing = inc;
    }
    
    public MyOrderedList(final Object v) {
        this.increasing = true;
        this.value = null;
        this.next = null;
        this.count = 0;
        this.value = v;
        ++this.count;
    }
    
    public MyOrderedList(final Object v, final boolean inc) {
        this.increasing = true;
        this.value = null;
        this.next = null;
        this.count = 0;
        this.increasing = inc;
        this.value = v;
        ++this.count;
    }
    
    public boolean isIncreasing() {
        return this.increasing;
    }
    
    public int add(final Object v) {
        int ret = 0;
        if (this.value == null) {
            this.value = v;
            ++this.count;
            return ret;
        }
        final MyOrderedList x = new MyOrderedList(v);
        MyOrderedList prev = this;
        MyOrderedList p;
        for (p = this; p != null; p = p.next) {
            if (this.increasing) {
                if (!((MyOrder)p.value).isLess(v)) {
                    break;
                }
            }
            else if (!((MyOrder)p.value).isGreater(v)) {
                break;
            }
            ++ret;
            prev = p;
        }
        if (p == null) {
            prev.next = x;
        }
        else if (ret == 0) {
            x.value = this.value;
            x.next = this.next;
            this.value = v;
            this.next = x;
        }
        else {
            x.next = prev.next;
            prev.next = x;
        }
        ++this.count;
        return ret;
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
            MyOrderedList prev = this;
            MyOrderedList p = this;
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
    
    public Object removeAt(int ind) {
        Object ret = null;
        if (ind != 0) {
            MyOrderedList prev = this;
            while (ind-- > 0) {
                prev = prev.next;
            }
            final MyOrderedList n = prev.next;
            ret = prev.value;
            if (n == null) {
                prev.value = null;
                prev.next = null;
            }
            else {
                prev.value = n.value;
                prev.next = n.next;
            }
            --this.count;
            return ret;
        }
        if (this == null) {
            return ret;
        }
        if (this.next == null) {
            ret = this.value;
            this.value = null;
        }
        else {
            ret = this.value;
            this.value = this.next.value;
            this.next = this.next.next;
        }
        --this.count;
        return ret;
    }
    
    public void reviseAt(final Object v, int ind) {
        if (ind != 0) {
            MyOrderedList p = this;
            while (ind-- > 0) {
                p = p.next;
            }
            p.value = v;
            return;
        }
        if (this == null) {
            return;
        }
        this.value = v;
    }
    
    public Object getElementAt(int ind) {
        if (ind != 0) {
            MyOrderedList p = this;
            while (ind-- > 0) {
                p = p.next;
            }
            return p.value;
        }
        if (this == null) {
            return null;
        }
        return this.value;
    }
    
    public MyOrderedList getNext() {
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
        MyOrderedList p;
        for (p = this; p.next != null; p = p.next) {
            ret = String.valueOf(ret) + p.value.toString() + ", ";
        }
        ret = String.valueOf(ret) + p.value.toString();
        return ret;
    }
}

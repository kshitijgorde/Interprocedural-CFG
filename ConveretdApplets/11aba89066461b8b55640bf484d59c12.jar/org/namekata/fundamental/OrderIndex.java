// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

public class OrderIndex
{
    private OrderIndexElement[] elements;
    private int len;
    
    private void init(final int len) {
        this.elements = new OrderIndexElement[len];
        for (int i = 0; i < len; ++i) {
            this.elements[i] = new OrderIndexElement();
        }
    }
    
    public OrderIndex(final Object[] objs, final boolean inc) {
        this.elements = null;
        this.len = 0;
        this.init(this.len = objs.length);
        final MyOrderedList myOrderedList = new MyOrderedList(inc);
        for (int i = 0; i < this.len; ++i) {
            myOrderedList.add(objs[i]);
        }
        this.compute(objs, myOrderedList);
    }
    
    public OrderIndex(final MyList myList, final boolean inc) {
        this.elements = null;
        this.len = 0;
        this.init(this.len = myList.getCount());
        final Object[] objs = new Object[this.len];
        final MyOrderedList myOrderedList = new MyOrderedList(inc);
        MyList p = myList;
        int i = 0;
        while (p != null) {
            final Object val = p.getValue();
            myOrderedList.add(val);
            objs[i++] = val;
            p = p.getNext();
        }
        this.compute(objs, myOrderedList);
    }
    
    public OrderIndex(final MyOrderedList myOrderedList) {
        this.elements = null;
        this.len = 0;
        this.init(this.len = myOrderedList.getCount());
        final Object[] objs = new Object[this.len];
        MyOrderedList p = myOrderedList;
        int i = 0;
        while (p != null) {
            objs[i++] = p.getValue();
            p = p.getNext();
        }
        this.compute(objs, myOrderedList);
    }
    
    public OrderIndexElement[] getOrderIndex() {
        return this.elements;
    }
    
    public int getLength() {
        return this.len;
    }
    
    private void compute(final Object[] objs, final MyOrderedList myOrderedList) {
        MyOrderedList p = myOrderedList;
        final boolean[] determined = new boolean[this.len];
        for (int i = 0; i < this.len; ++i) {
            determined[i] = false;
        }
        int j = 0;
        while (p != null) {
            final Object o = p.getValue();
            for (int k = 0; k < this.len; ++k) {
                if (!determined[k] && ((MyOrder)o).equals(objs[k])) {
                    this.elements[j].index = k;
                    this.elements[j].value = o;
                    ++j;
                    determined[k] = true;
                    break;
                }
            }
            p = p.getNext();
        }
        Object val = null;
        j = 1;
        for (int k = this.len - 1; k >= 0; --k) {
            final Object o2 = this.elements[k].value;
            if (val != null && ((MyOrder)val).equals(o2)) {
                this.elements[k].count = j;
            }
            else {
                val = o2;
                j = 1;
                this.elements[k].count = j;
            }
            ++j;
        }
    }
}

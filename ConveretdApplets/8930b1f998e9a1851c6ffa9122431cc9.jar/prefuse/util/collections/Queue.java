// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import java.util.HashMap;
import java.util.LinkedList;

public class Queue
{
    private LinkedList m_list;
    private HashMap m_map;
    
    public Queue() {
        this.m_list = new LinkedList();
        this.m_map = new HashMap();
    }
    
    public void clear() {
        this.m_list.clear();
        this.m_map.clear();
    }
    
    public boolean isEmpty() {
        return this.m_list.isEmpty();
    }
    
    public void add(final Object o, final int n) {
        this.m_list.add(o);
        this.visit(o, n);
    }
    
    public int getDepth(final Object o) {
        final Integer n = this.m_map.get(o);
        return (n == null) ? -1 : n;
    }
    
    public void visit(final Object o, final int n) {
        this.m_map.put(o, new Integer(n));
    }
    
    public Object removeFirst() {
        return this.m_list.removeFirst();
    }
    
    public Object removeLast() {
        return this.m_list.removeLast();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.tp.graph;

public class GraphObject
{
    public static final String Anonymous = "anonymous";
    protected final String id;
    protected final T type;
    protected Graph graph;
    private static final Mutex lock;
    
    static {
        lock = Mutex.instance();
    }
    
    GraphObject(final String id, final T type) {
        this.graph = null;
        this.id = id;
        this.type = type;
    }
    
    GraphObject(final GraphObject g) {
        this.graph = null;
        this.id = g.id;
        this.type = g.type;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String name() {
        return this.id;
    }
    
    void setGraph(final Graph g) {
        this.graph = g;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.type.name()) + " " + this.id;
    }
    
    public T type() {
        return this.type;
    }
    
    public boolean type(final T type) {
        return this.type == type;
    }
    
    protected void lock(final Mutex.LM mode) {
        GraphObject.lock.lock(mode);
    }
    
    protected void unlock(final Mutex.LM mode) {
        GraphObject.lock.unlock(mode);
    }
    
    protected void added(final Graph g) {
    }
    
    protected void removed(final Graph g) {
    }
    
    public enum T
    {
        Node("Node", 0), 
        Interface("Interface", 1), 
        Network("Network", 2), 
        Link("Link", 3), 
        Host("Host", 4), 
        ObjectGroup("ObjectGroup", 5), 
        AddressRange("AddressRange", 6), 
        None("None", 7);
        
        private T(final String s, final int n) {
        }
    }
}

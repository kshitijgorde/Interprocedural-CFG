// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

public class Destination
{
    private String qualifier;
    private Type type;
    
    public Destination(final Type type, final String qualifier) {
        this.qualifier = qualifier;
        this.type = type;
    }
    
    public Destination(final Type type) {
        this.type = type;
    }
    
    public Destination() {
    }
    
    public String getLocal() {
        return System.getProperty("cornerstone.server.rmiQueue");
    }
    
    public void setLocal() {
        System.setProperty("cornerstone.server.rmiQueue", this.getValue());
    }
    
    public String getValue() {
        if (this.qualifier != null) {
            return String.valueOf(this.type.getId()) + "." + this.qualifier;
        }
        return this.type.getId();
    }
    
    @Override
    public String toString() {
        return this.getValue();
    }
    
    public enum Type
    {
        ps("ps", 0, "queue.ps.rmi.request"), 
        rs("rs", 1, "queue.rs.rmi.request");
        
        private String id;
        
        private Type(final String s, final int n, final String id) {
            this.id = id;
        }
        
        public String getId() {
            return this.id;
        }
    }
}

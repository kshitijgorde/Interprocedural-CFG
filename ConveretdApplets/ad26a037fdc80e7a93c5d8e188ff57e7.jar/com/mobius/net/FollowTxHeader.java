// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

import com.mobius.utility.Guid;

public final class FollowTxHeader
{
    private Guid guid;
    public static final String HEADER_NAME = "FollowTX";
    public static final String PARAMETER_NAME = "_addFollowTx";
    public static final String SESSION_ATTR_NAME = "addFollowTx";
    public static final String APPLET_PARAMETER_NAME = "addFollowTx";
    public static final boolean DEFAULT_ADD_HEADER = false;
    private static boolean addFollowTx;
    
    public FollowTxHeader() {
        this.guid = new Guid();
    }
    
    public boolean equals(final Object o) {
        return this.guid.equals(o);
    }
    
    public int hashCode() {
        return this.guid.hashCode();
    }
    
    public String toString() {
        return "{" + this.guid.toString() + "}";
    }
    
    public static synchronized boolean isAddFollowTx() {
        return FollowTxHeader.addFollowTx;
    }
    
    public static synchronized void setAddFollowTx(final boolean addFollowTx) {
        FollowTxHeader.addFollowTx = addFollowTx;
    }
    
    static {
        FollowTxHeader.addFollowTx = false;
    }
}

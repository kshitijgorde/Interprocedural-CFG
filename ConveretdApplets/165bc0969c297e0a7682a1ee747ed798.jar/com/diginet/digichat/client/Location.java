// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.k;

public abstract class Location extends k
{
    protected h hUser;
    
    public Location(final h hUser, final int n, final String s) {
        super(n, s);
        this.hUser = hUser;
        this.j(61);
    }
    
    public abstract void initBuddy(final Buddy p0);
    
    public abstract void checkBuddy(final Buddy p0);
    
    public abstract void getProf(final BuddyProf p0);
    
    public abstract void callPriv(final Buddy p0, final bf p1);
    
    public abstract void callInv(final Buddy p0);
    
    public abstract void callNote(final Buddy p0);
    
    public abstract void closeMess(final Buddy p0);
    
    public void closeBuddy(final Buddy buddy) {
    }
    
    public void clearBuddies() {
    }
}

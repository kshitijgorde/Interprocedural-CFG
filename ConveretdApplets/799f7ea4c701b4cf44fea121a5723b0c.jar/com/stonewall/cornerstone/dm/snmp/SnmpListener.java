// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

public interface SnmpListener
{
    void requestFailed(final MibObject p0, final int p1);
    
    void requestSucceeded(final MibObject p0, final int p1);
    
    void trapReceived(final Trap p0);
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

class DefaultSnmpListener implements SnmpListener
{
    public static DefaultSnmpListener Instance;
    
    static {
        DefaultSnmpListener.Instance = new DefaultSnmpListener();
    }
    
    @Override
    public void requestFailed(final MibObject obj, final int ctag) {
    }
    
    @Override
    public void requestSucceeded(final MibObject obj, final int ctag) {
    }
    
    @Override
    public void trapReceived(final Trap t) {
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.www;

import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.entity.PolicyServer;

public class RMIClient extends com.stonewall.cornerstone.rmi.RMIClient
{
    public RMIClient() {
        super(new PolicyServer());
    }
    
    @Override
    public boolean isLocal() {
        return false;
    }
}

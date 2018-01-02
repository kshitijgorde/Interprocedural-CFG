// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

public class ConfigurationRequest extends Request
{
    public ConfigurationRequest(final String serialNo) {
        super(null, true, "~.ps.nbi.core.PolicyServerNBI", "getConfiguration", new Object[] { serialNo });
    }
}

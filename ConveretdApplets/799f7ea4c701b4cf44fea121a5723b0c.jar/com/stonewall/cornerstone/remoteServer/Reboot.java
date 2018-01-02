// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

public class Reboot extends Request
{
    public Reboot(final String id) {
        super(id, false, "~.remoteServer.ServerManagement", "restartServer", null);
    }
}

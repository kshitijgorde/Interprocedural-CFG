// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import netscape.security.PrivilegeManager;

public class c2
{
    public static boolean a() {
        try {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
            PrivilegeManager.enablePrivilege("UniversalFileAccess");
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}

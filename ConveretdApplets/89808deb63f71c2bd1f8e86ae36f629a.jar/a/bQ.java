// 
// Decompiled by Procyon v0.5.30
// 

package a;

import netscape.security.PrivilegeManager;

public final class bQ
{
    public static boolean q() {
        try {
            if (cu.q == 0) {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
                PrivilegeManager.enablePrivilege("UniversalFileAccess");
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}

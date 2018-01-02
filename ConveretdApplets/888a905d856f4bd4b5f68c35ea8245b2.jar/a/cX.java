// 
// Decompiled by Procyon v0.5.30
// 

package a;

import netscape.security.PrivilegeManager;

public final class cX
{
    public static boolean q() {
        try {
            if (dN.q == 0) {
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

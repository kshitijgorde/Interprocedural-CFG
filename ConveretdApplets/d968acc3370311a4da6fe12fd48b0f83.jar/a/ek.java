// 
// Decompiled by Procyon v0.5.30
// 

package a;

import netscape.security.PrivilegeManager;

public final class ek
{
    public static boolean q() {
        try {
            if (a.q == 0) {
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

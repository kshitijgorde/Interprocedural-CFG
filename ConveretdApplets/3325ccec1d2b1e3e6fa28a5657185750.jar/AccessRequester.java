import netscape.security.PrivilegeManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class AccessRequester
{
    public static void cc() {
        try {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
        }
        catch (Exception ex) {}
    }
}

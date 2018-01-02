// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.filetransfer;

import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;

public class ce
{
    public static boolean a() {
        try {
            PolicyEngine.assertPermission(PermissionID.NETIO);
            PolicyEngine.assertPermission(PermissionID.FILEIO);
            System.getProperty("browser.version");
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}

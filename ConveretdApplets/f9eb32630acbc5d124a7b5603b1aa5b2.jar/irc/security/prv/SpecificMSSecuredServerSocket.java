// 
// Decompiled by Procyon v0.5.30
// 

package irc.security.prv;

import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

public class SpecificMSSecuredServerSocket extends ServerSocket
{
    public SpecificMSSecuredServerSocket(final int n) throws IOException {
        super(n);
    }
    
    public Socket accept() throws IOException {
        try {
            PolicyEngine.assertPermission(PermissionID.NETIO);
            return super.accept();
        }
        catch (Throwable t) {
            return super.accept();
        }
    }
}

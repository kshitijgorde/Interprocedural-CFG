// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import mindbright.security.RSAPublicKey;
import java.io.IOException;

public interface SSHAuthenticator
{
    String getUsername(final SSHClientUser p0) throws IOException;
    
    String getPassword(final SSHClientUser p0) throws IOException;
    
    String getChallengeResponse(final SSHClientUser p0, final String p1) throws IOException;
    
    int[] getAuthTypes(final SSHClientUser p0);
    
    int getCipher(final SSHClientUser p0);
    
    SSHRSAKeyFile getIdentityFile(final SSHClientUser p0) throws IOException;
    
    String getIdentityPassword(final SSHClientUser p0) throws IOException;
    
    boolean verifyKnownHosts(final RSAPublicKey p0) throws IOException;
}

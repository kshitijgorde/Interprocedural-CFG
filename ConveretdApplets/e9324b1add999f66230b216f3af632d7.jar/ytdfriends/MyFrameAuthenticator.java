// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import java.net.PasswordAuthentication;
import java.net.Authenticator;

class MyFrameAuthenticator extends Authenticator
{
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("yatedo", new String("keepWALKINGyatedo").toCharArray());
    }
}

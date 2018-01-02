// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.io;

import java.net.PasswordAuthentication;
import java.net.Authenticator;

public class PasswordAuthenticator extends Authenticator
{
    private String m_username;
    private String m_password;
    private PasswordAuthentication m_auth;
    
    PasswordAuthenticator(final String username, final String password) {
        this.m_password = password;
        this.m_username = username;
    }
    
    String getPassword() {
        return this.m_password;
    }
    
    void setPassword(final String password) {
        this.m_password = password;
        this.m_auth = null;
    }
    
    String getUsername() {
        return this.m_username;
    }
    
    void setUsername(final String username) {
        this.m_username = username;
        this.m_auth = null;
    }
    
    protected PasswordAuthentication getPasswordAuthentication() {
        if (this.m_auth == null) {
            this.m_auth = new PasswordAuthentication(this.m_username, this.m_password.toCharArray());
        }
        return this.m_auth;
    }
    
    public static void setAuthentication(final String s, final String s2) {
        Authenticator.setDefault(new PasswordAuthenticator(s, s2));
    }
}

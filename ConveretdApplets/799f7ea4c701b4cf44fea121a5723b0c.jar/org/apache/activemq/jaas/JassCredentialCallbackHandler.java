// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

public class JassCredentialCallbackHandler implements CallbackHandler
{
    private final String username;
    private final String password;
    
    public JassCredentialCallbackHandler(final String username, final String password) {
        this.username = username;
        this.password = password;
    }
    
    @Override
    public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; ++i) {
            final Callback callback = callbacks[i];
            if (callback instanceof PasswordCallback) {
                final PasswordCallback passwordCallback = (PasswordCallback)callback;
                if (this.password == null) {
                    passwordCallback.setPassword(null);
                }
                else {
                    passwordCallback.setPassword(this.password.toCharArray());
                }
            }
            else if (callback instanceof NameCallback) {
                final NameCallback nameCallback = (NameCallback)callback;
                if (this.username == null) {
                    nameCallback.setName(null);
                }
                else {
                    nameCallback.setName(this.username);
                }
            }
        }
    }
}

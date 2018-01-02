// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import java.io.IOException;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.callback.Callback;
import java.security.cert.X509Certificate;
import javax.security.auth.callback.CallbackHandler;

public class JaasCertificateCallbackHandler implements CallbackHandler
{
    final X509Certificate[] certificates;
    
    public JaasCertificateCallbackHandler(final X509Certificate[] certs) {
        this.certificates = certs;
    }
    
    @Override
    public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; ++i) {
            final Callback callback = callbacks[i];
            if (!(callback instanceof CertificateCallback)) {
                throw new UnsupportedCallbackException(callback);
            }
            final CertificateCallback certCallback = (CertificateCallback)callback;
            certCallback.setCertificates(this.certificates);
        }
    }
}

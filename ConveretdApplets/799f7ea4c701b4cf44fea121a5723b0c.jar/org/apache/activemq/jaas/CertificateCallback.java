// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import java.security.cert.X509Certificate;
import javax.security.auth.callback.Callback;

public class CertificateCallback implements Callback
{
    X509Certificate[] certificates;
    
    public void setCertificates(final X509Certificate[] certs) {
        this.certificates = certs;
    }
    
    public X509Certificate[] getCertificates() {
        return this.certificates;
    }
}

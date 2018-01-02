// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.contrib.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.KeyStoreException;
import com.sun.net.ssl.TrustManager;
import java.security.NoSuchAlgorithmException;
import com.sun.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import com.sun.net.ssl.X509TrustManager;

public class EasyX509TrustManager implements X509TrustManager
{
    private X509TrustManager standardTrustManager;
    
    public EasyX509TrustManager(final KeyStore keystore) throws NoSuchAlgorithmException, KeyStoreException {
        this.standardTrustManager = null;
        final TrustManagerFactory factory = TrustManagerFactory.getInstance("SunX509");
        factory.init(keystore);
        final TrustManager[] trustmanagers = factory.getTrustManagers();
        if (trustmanagers.length == 0) {
            throw new NoSuchAlgorithmException("SunX509 trust manager not supported");
        }
        this.standardTrustManager = (X509TrustManager)trustmanagers[0];
    }
    
    public X509Certificate[] getAcceptedIssuers() {
        return this.standardTrustManager.getAcceptedIssuers();
    }
    
    public boolean isClientTrusted(final X509Certificate[] certificates) {
        return this.standardTrustManager.isClientTrusted(certificates);
    }
    
    public boolean isServerTrusted(final X509Certificate[] certificates) {
        if (certificates != null && certificates.length == 1) {
            final X509Certificate certificate = certificates[0];
            try {
                certificate.checkValidity();
            }
            catch (CertificateException e) {
                return false;
            }
            return true;
        }
        return this.standardTrustManager.isServerTrusted(certificates);
    }
}

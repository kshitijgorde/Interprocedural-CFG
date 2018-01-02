// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.https;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.apache.activemq.transport.http.HttpTransportFactory;
import java.net.URI;
import org.apache.activemq.transport.http.HttpTransportServer;

public class HttpsTransportServer extends HttpTransportServer
{
    private String keyPassword;
    private String keyStorePassword;
    private String keyStore;
    private String keyStoreType;
    private String secureRandomCertficateAlgorithm;
    private String trustCertificateAlgorithm;
    private String keyCertificateAlgorithm;
    private String protocol;
    
    public HttpsTransportServer(final URI uri, final HttpsTransportFactory factory) {
        super(uri, factory);
        this.keyPassword = System.getProperty("javax.net.ssl.keyPassword");
        this.keyStorePassword = System.getProperty("javax.net.ssl.keyStorePassword");
        this.keyStore = System.getProperty("javax.net.ssl.keyStore");
    }
    
    public void doStart() throws Exception {
        final SslSocketConnector sslConnector = new SslSocketConnector();
        sslConnector.setKeystore(this.keyStore);
        sslConnector.setPassword(this.keyStorePassword);
        if (this.keyPassword == null) {
            sslConnector.setKeyPassword(this.keyStorePassword);
        }
        if (this.keyStoreType != null) {
            sslConnector.setKeystoreType(this.keyStoreType);
        }
        if (this.secureRandomCertficateAlgorithm != null) {
            sslConnector.setSecureRandomAlgorithm(this.secureRandomCertficateAlgorithm);
        }
        if (this.keyCertificateAlgorithm != null) {
            sslConnector.setSslKeyManagerFactoryAlgorithm(this.keyCertificateAlgorithm);
        }
        if (this.trustCertificateAlgorithm != null) {
            sslConnector.setSslTrustManagerFactoryAlgorithm(this.trustCertificateAlgorithm);
        }
        if (this.protocol != null) {
            sslConnector.setProtocol(this.protocol);
        }
        this.setConnector((Connector)sslConnector);
        super.doStart();
    }
    
    public String getKeyStore() {
        return this.keyStore;
    }
    
    public void setKeyStore(final String keyStore) {
        this.keyStore = keyStore;
    }
    
    public String getKeyPassword() {
        return this.keyPassword;
    }
    
    public void setKeyPassword(final String keyPassword) {
        this.keyPassword = keyPassword;
    }
    
    public String getKeyStoreType() {
        return this.keyStoreType;
    }
    
    public void setKeyStoreType(final String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }
    
    public String getKeyStorePassword() {
        return this.keyStorePassword;
    }
    
    public void setKeyStorePassword(final String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }
    
    public String getProtocol() {
        return this.protocol;
    }
    
    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }
    
    public String getSecureRandomCertficateAlgorithm() {
        return this.secureRandomCertficateAlgorithm;
    }
    
    public void setSecureRandomCertficateAlgorithm(final String secureRandomCertficateAlgorithm) {
        this.secureRandomCertficateAlgorithm = secureRandomCertficateAlgorithm;
    }
    
    public String getKeyCertificateAlgorithm() {
        return this.keyCertificateAlgorithm;
    }
    
    public void setKeyCertificateAlgorithm(final String keyCertificateAlgorithm) {
        this.keyCertificateAlgorithm = keyCertificateAlgorithm;
    }
    
    public String getTrustCertificateAlgorithm() {
        return this.trustCertificateAlgorithm;
    }
    
    public void setTrustCertificateAlgorithm(final String trustCertificateAlgorithm) {
        this.trustCertificateAlgorithm = trustCertificateAlgorithm;
    }
}

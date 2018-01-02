// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import org.apache.commons.logging.LogFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import javax.net.ssl.KeyManagerFactory;
import java.io.InputStream;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyStore;
import javax.jms.JMSException;
import org.apache.activemq.util.JMSExceptionSupport;
import org.apache.activemq.broker.SslContext;
import org.apache.activemq.transport.tcp.SslTransportFactory;
import org.apache.activemq.transport.Transport;
import java.net.URI;
import java.security.SecureRandom;
import javax.net.ssl.TrustManager;
import javax.net.ssl.KeyManager;
import org.apache.commons.logging.Log;

public class ActiveMQSslConnectionFactory extends ActiveMQConnectionFactory
{
    private static final Log LOG;
    protected KeyManager[] keyManager;
    protected TrustManager[] trustManager;
    protected SecureRandom secureRandom;
    protected String trustStore;
    protected String trustStorePassword;
    protected String keyStore;
    protected String keyStorePassword;
    
    public ActiveMQSslConnectionFactory() {
    }
    
    public ActiveMQSslConnectionFactory(final String brokerURL) {
        super(brokerURL);
    }
    
    public ActiveMQSslConnectionFactory(final URI brokerURL) {
        super(brokerURL);
    }
    
    public void setKeyAndTrustManagers(final KeyManager[] km, final TrustManager[] tm, final SecureRandom random) {
        this.keyManager = km;
        this.trustManager = tm;
        this.secureRandom = random;
    }
    
    @Override
    protected Transport createTransport() throws JMSException {
        if (!this.brokerURL.getScheme().equals("ssl")) {
            return super.createTransport();
        }
        try {
            if (this.keyManager == null || this.trustManager == null) {
                this.trustManager = this.createTrustManager();
                this.keyManager = this.createKeyManager();
            }
            final SslTransportFactory sslFactory = new SslTransportFactory();
            final SslContext ctx = new SslContext(this.keyManager, this.trustManager, this.secureRandom);
            SslContext.setCurrentSslContext(ctx);
            return sslFactory.doConnect(this.brokerURL);
        }
        catch (Exception e) {
            throw JMSExceptionSupport.create("Could not create Transport. Reason: " + e, e);
        }
    }
    
    protected TrustManager[] createTrustManager() throws Exception {
        TrustManager[] trustStoreManagers = null;
        final KeyStore trustedCertStore = KeyStore.getInstance("jks");
        final InputStream tsStream = this.getUrlOrResourceAsStream(this.trustStore);
        trustedCertStore.load(tsStream, this.trustStorePassword.toCharArray());
        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustedCertStore);
        trustStoreManagers = tmf.getTrustManagers();
        return trustStoreManagers;
    }
    
    protected KeyManager[] createKeyManager() throws Exception {
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        final KeyStore ks = KeyStore.getInstance("jks");
        KeyManager[] keystoreManagers = null;
        final byte[] sslCert = this.loadClientCredential(this.keyStore);
        if (sslCert != null && sslCert.length > 0) {
            final ByteArrayInputStream bin = new ByteArrayInputStream(sslCert);
            ks.load(bin, this.keyStorePassword.toCharArray());
            kmf.init(ks, this.keyStorePassword.toCharArray());
            keystoreManagers = kmf.getKeyManagers();
        }
        return keystoreManagers;
    }
    
    protected byte[] loadClientCredential(final String fileName) throws IOException {
        if (fileName == null) {
            return null;
        }
        final InputStream in = this.getUrlOrResourceAsStream(fileName);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buf = new byte[512];
        for (int i = in.read(buf); i > 0; i = in.read(buf)) {
            out.write(buf, 0, i);
        }
        in.close();
        return out.toByteArray();
    }
    
    protected InputStream getUrlOrResourceAsStream(final String urlOrResource) throws IOException {
        InputStream ins = null;
        try {
            final URL url = new URL(urlOrResource);
            ins = url.openStream();
        }
        catch (MalformedURLException ignore) {
            ins = null;
        }
        if (ins == null) {
            ins = this.getClass().getClassLoader().getResourceAsStream(urlOrResource);
        }
        if (ins == null) {
            throw new IOException("Could not load resource: " + urlOrResource);
        }
        return ins;
    }
    
    public String getTrustStore() {
        return this.trustStore;
    }
    
    public void setTrustStore(final String trustStore) {
        this.trustStore = trustStore;
        this.trustManager = null;
    }
    
    public String getTrustStorePassword() {
        return this.trustStorePassword;
    }
    
    public void setTrustStorePassword(final String trustStorePassword) {
        this.trustStorePassword = trustStorePassword;
    }
    
    public String getKeyStore() {
        return this.keyStore;
    }
    
    public void setKeyStore(final String keyStore) {
        this.keyStore = keyStore;
        this.keyManager = null;
    }
    
    public String getKeyStorePassword() {
        return this.keyStorePassword;
    }
    
    public void setKeyStorePassword(final String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }
    
    static {
        LOG = LogFactory.getLog((Class)ActiveMQSslConnectionFactory.class);
    }
}

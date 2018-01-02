// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls;

import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import anon.crypto.MyRSAPrivateKey;
import anon.crypto.MyDSAPrivateKey;
import anon.crypto.IMyPrivateKey;
import anon.crypto.JAPCertificate;
import java.net.ServerSocket;

public class TinyTLSServer extends ServerSocket
{
    private JAPCertificate m_Certificate;
    private IMyPrivateKey m_PrivateKey;
    private MyDSAPrivateKey m_DSSKey;
    private MyRSAPrivateKey m_RSAKey;
    private JAPCertificate m_DSSCertificate;
    private JAPCertificate m_RSACertificate;
    private TinyTLSServerSocket tls;
    
    public TinyTLSServer(final int n) throws IOException {
        super(n);
        this.m_Certificate = null;
        this.m_PrivateKey = null;
        this.m_DSSKey = null;
        this.m_RSAKey = null;
        this.m_DSSCertificate = null;
        this.m_RSACertificate = null;
        this.tls = null;
    }
    
    public TinyTLSServer(final int n, final int n2, final InetAddress inetAddress) throws IOException {
        super(n, n2, inetAddress);
        this.m_Certificate = null;
        this.m_PrivateKey = null;
        this.m_DSSKey = null;
        this.m_RSAKey = null;
        this.m_DSSCertificate = null;
        this.m_RSACertificate = null;
        this.tls = null;
    }
    
    public void setDSSParameters(final JAPCertificate dssCertificate, final MyDSAPrivateKey dssKey) {
        this.m_DSSCertificate = dssCertificate;
        this.m_DSSKey = dssKey;
    }
    
    public void setRSAParameters(final JAPCertificate rsaCertificate, final MyRSAPrivateKey rsaKey) {
        this.m_RSACertificate = rsaCertificate;
        this.m_RSAKey = rsaKey;
    }
    
    public Socket accept() throws IOException {
        return this.accept(0L);
    }
    
    public Socket accept(final long n) throws IOException {
        (this.tls = new TinyTLSServerSocket(super.accept(), n)).setDSSParameters(this.m_DSSCertificate, this.m_DSSKey);
        this.tls.setRSAParameters(this.m_RSACertificate, this.m_RSAKey);
        return this.tls;
    }
}

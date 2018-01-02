// 
// Decompiled by Procyon v0.5.30
// 

package pclient.sec;

import java.security.cert.Certificate;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import com.pchat.sc.ByteUtil;
import java.util.Date;
import pclient.shd.ClientUtil;
import java.io.OutputStream;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateExpiredException;
import java.io.IOException;
import pclient.shd.Config;
import pclient.shd.SessionEnclosure;
import pclient.shd.NetStreamListener;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import javax.net.ssl.SSLSocket;
import pclient.shd.SockStream;

public class SecStream implements SockStream, Runnable
{
    private SSLSocket clientSock;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    private boolean userDisconnect;
    private boolean isSockConnected;
    private NetStreamListener netListener;
    private SecOut outThread;
    protected SessionEnclosure chatModel;
    private long connectTime;
    
    public SecStream() {
        this.clientSock = null;
        this.userDisconnect = false;
        this.isSockConnected = false;
        this.chatModel = null;
        this.connectTime = 0L;
    }
    
    public void setInit(final Config config, final SessionEnclosure chatModel) {
        this.chatModel = chatModel;
    }
    
    public boolean isConnected() {
        return this.isSockConnected;
    }
    
    public boolean isTunnel() {
        return false;
    }
    
    public void connect(final String s, final int n) throws IOException {
        this.connectTime = System.currentTimeMillis();
        try {
            this.connectSocket(s, n);
        }
        catch (CertificateExpiredException ex) {
            final String s2 = "Certificate Expired";
            this.chatModel.paraConf.get("Msg.Cer.Exp", s2);
            this.chatModel.chatView.vwInfo("SC892. " + s2);
            ex.printStackTrace();
            throw new IOException("Err SEC853. Connection failed.");
        }
        catch (CertificateNotYetValidException ex2) {
            final String s3 = "Certificate is not yet valid";
            this.chatModel.paraConf.get("Msg.Cer.Yet", s3);
            this.chatModel.chatView.vwInfo("SC893. " + s3);
            ex2.printStackTrace();
            throw new IOException("Err SEC825. Connection failed.");
        }
        catch (CertPathValidatorException ex3) {
            ex3.printStackTrace();
            throw new IOException("Err SEC865. Connection failed.");
        }
        catch (Exception ex4) {
            System.out.println("SC874. Error occurred during secure connection.");
            ex4.printStackTrace();
            throw new IOException("Err SEC873. Connection failed.");
        }
        this.isSockConnected = true;
        new Thread(this).start();
        (this.outThread = new SecOut(this, this.outStream)).start();
        this.chatModel.paraConf.printer().print("Secure connection done.");
    }
    
    public long getConnTime() {
        return this.connectTime;
    }
    
    public void send(final byte[] array) throws IOException {
        if (this.outThread != null) {
            this.outThread.send(array);
        }
    }
    
    public void setListener(final NetStreamListener netListener) {
        this.netListener = netListener;
    }
    
    public Object secSession() {
        if (this.clientSock == null) {
            return null;
        }
        return this.clientSock.getSession();
    }
    
    public void disconnect() {
        this.userDisconnect = true;
        this.isSockConnected = false;
        if (this.outThread != null) {
            this.outThread.disconnect();
        }
        this.closeSock();
    }
    
    protected void closeSock() {
        if (this.clientSock == null) {
            return;
        }
        try {
            this.clientSock.close();
        }
        catch (Exception ex) {
            System.out.println("error closing sec conn");
            ex.printStackTrace();
        }
        this.clientSock = null;
    }
    
    public void run() {
        final byte[] array = new byte[256];
        int n = 0;
        try {
            while (this.isConnected()) {
                final int read = this.inStream.read(array);
                if (read < 0) {
                    System.out.println("sec-stream ended.");
                    break;
                }
                if (read == 0) {
                    System.out.println("IO error #82975.");
                    ClientUtil.doze(600);
                }
                else {
                    if (!this.isConnected()) {
                        continue;
                    }
                    try {
                        this.doIncoming(array, read);
                    }
                    catch (Exception ex) {
                        System.out.println("Err-sec53963");
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex2) {
            if (!this.userDisconnect) {
                n = 2;
                System.out.println("Err83472");
                ex2.printStackTrace();
            }
        }
        System.out.println(new Date() + " in-secstream end. " + this.getConnTime());
        this.isSockConnected = false;
        this.outThread.stopIt();
        this.errorNotify(n);
    }
    
    private void doIncoming(final byte[] array, final int n) {
        final byte[] duplicate = ByteUtil.duplicate(array, 0, n);
        if (this.netListener != null) {
            this.netListener.notifyIncoming(duplicate);
        }
    }
    
    protected void errorNotify(final int n) {
        if (this.userDisconnect) {
            return;
        }
        if (this.netListener != null) {
            this.netListener.notifyDisconnection(n);
        }
    }
    
    private void connectSocket(final String s, final int n) throws Exception {
        System.out.println("init secure " + n + " " + s + " " + new Date());
        final SSLSocket clientSock = (SSLSocket)((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket(s, n);
        System.out.println("secure socket," + new Date());
        clientSock.setEnabledCipherSuites(clientSock.getSupportedCipherSuites());
        this.chatModel.paraConf.printer().print("clientAuth? " + clientSock.getNeedClientAuth());
        this.chatModel.paraConf.printer().print("clientMode? " + clientSock.getUseClientMode());
        clientSock.startHandshake();
        final SSLSession session = clientSock.getSession();
        System.out.println("cipher=" + session.getCipherSuite());
        session.getPeerCertificates();
        System.out.println("init done, secure," + new Date());
        this.clientSock = clientSock;
        this.inStream = new DataInputStream(this.clientSock.getInputStream());
        this.outStream = new DataOutputStream(this.clientSock.getOutputStream());
    }
    
    public static String getCertificates(final Certificate[] array) {
        return SecCert.getCertificates(array);
    }
}

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.io.ObjectOutputStream;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import java.security.SecureRandom;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.cert.CertificateFactory;
import java.io.InputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123HttpsInit
{
    private URL vncProxyHub;
    private URL vncProxyHubHttps;
    private boolean initTypeHttps;
    private String browser;
    
    VNC123HttpsInit(final URL codeBase, final String appletKeyStore, final String initType, final String browser) throws Exception {
        this.vncProxyHub = null;
        this.vncProxyHubHttps = null;
        this.initTypeHttps = false;
        this.browser = null;
        this.vncProxyHub = new URL(codeBase + "VNC123HubInit");
        this.vncProxyHubHttps = new URL("https", codeBase.getHost(), String.valueOf(codeBase.getFile()) + "VNC123HubInit");
        this.browser = browser;
        if (initType.equalsIgnoreCase("https")) {
            this.initTypeHttps = true;
        }
        if (this.initTypeHttps) {
            final SSLContext sc = SSLContext.getInstance("TLS");
            final KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(appletKeyStore), new String("applet").toCharArray());
            CertificateFactory.getInstance("X.509");
            final TrustManagerFactory tmfactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmfactory.init(ks);
            final TrustManager[] trustmanagers = tmfactory.getTrustManagers();
            for (int i = 0; i < trustmanagers.length; ++i) {
                if (trustmanagers[i] instanceof X509TrustManager) {
                    trustmanagers[i] = trustmanagers[i];
                }
            }
            sc.init(null, trustmanagers, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }
    }
    
    public VNC123DataInit comSession(final String sessionId, final String action, final String hashCode, final byte[] key) throws Exception {
        HttpsURLConnection conHttps = null;
        HttpURLConnection conHttp = null;
        OutputStream outstream = null;
        InputStream instr = null;
        if (this.initTypeHttps) {
            conHttps = this.getVNC123HubConnectionHttps();
            outstream = conHttps.getOutputStream();
        }
        else {
            conHttp = this.getVNC123HubConnection();
            outstream = conHttp.getOutputStream();
        }
        final ObjectOutputStream oos = new ObjectOutputStream(outstream);
        VNC123DataInit objDataInit = null;
        objDataInit = new VNC123DataInit(sessionId, action, hashCode, key);
        if (action.compareToIgnoreCase("VIEWER") == 0 || action.compareToIgnoreCase("SERVER") == 0) {
            objDataInit.localHostname = InetAddress.getLocalHost().getHostName();
            objDataInit.localIPLan = InetAddress.getLocalHost().toString();
            objDataInit.localJavaVendor = System.getProperty("java.vendor");
            objDataInit.localJavaVersion = System.getProperty("java.version");
            objDataInit.localOSArch = System.getProperty("os.arch");
            objDataInit.localOSName = System.getProperty("os.name");
            objDataInit.localOSVersion = System.getProperty("os.version");
            objDataInit.localUserName = System.getProperty("user.name");
            objDataInit.localBrowser = this.browser;
        }
        oos.writeObject(objDataInit);
        oos.flush();
        oos.close();
        if (this.initTypeHttps) {
            instr = conHttps.getInputStream();
        }
        else {
            instr = conHttp.getInputStream();
        }
        final ObjectInputStream inputFromServlet = new ObjectInputStream(instr);
        objDataInit = (VNC123DataInit)inputFromServlet.readObject();
        inputFromServlet.close();
        instr.close();
        return objDataInit;
    }
    
    private HttpsURLConnection getVNC123HubConnectionHttps() throws Exception {
        final HttpsURLConnection con = (HttpsURLConnection)this.vncProxyHubHttps.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/x-java-serialized-object");
        return con;
    }
    
    private HttpURLConnection getVNC123HubConnection() throws Exception {
        final HttpURLConnection con = (HttpURLConnection)this.vncProxyHub.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/x-java-serialized-object");
        return con;
    }
}

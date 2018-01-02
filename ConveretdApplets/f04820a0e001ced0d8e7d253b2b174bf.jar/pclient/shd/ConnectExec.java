// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.GenericResponse;
import java.util.Date;
import java.io.IOException;
import com.pchat.sc.StringUtil;

public class ConnectExec extends Thread implements NetStreamListener
{
    private SessionEnclosure chatSession;
    private ConnData connData;
    protected SockStream sockStream;
    protected boolean secureConn;
    protected boolean tunnelConn;
    protected boolean keepGoing;
    
    public ConnectExec(final SessionEnclosure chatSession, final ConnData connData) {
        this.sockStream = null;
        this.secureConn = false;
        this.tunnelConn = false;
        this.keepGoing = true;
        this.chatSession = chatSession;
        this.connData = connData;
    }
    
    public long getConnTime() {
        if (this.sockStream != null) {
            return this.sockStream.getConnTime();
        }
        return 0L;
    }
    
    protected void discontinue() {
        this.keepGoing = false;
        if (this.sockStream != null) {
            this.sockStream.disconnect();
            this.sockStream = null;
        }
    }
    
    public void notifyDisconnection(final int n) {
        if (this.keepGoing) {
            this.chatSession.notifyDisconnection(n);
        }
    }
    
    public void notifyIncoming(final byte[] array) {
        if (this.keepGoing) {
            this.chatSession.notifyIncoming(array);
        }
    }
    
    public Object secSession() {
        if (this.sockStream == null) {
            return null;
        }
        return this.sockStream.secSession();
    }
    
    public void send(final byte[] array) {
        if (this.sockStream == null) {
            System.err.println("Alert stream879275.");
            return;
        }
        if (array == null) {
            this.chatSession.sessionData.setError();
            return;
        }
        this.chatSession.paraConf.printer().print("sending len=" + array.length);
        this.chatSession.paraConf.printer().print("sending=" + StringUtil.showByte(array));
        try {
            this.sockStream.send(array);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            if (this.keepGoing) {
                this.chatSession.setDisconnect(null);
            }
        }
    }
    
    public void run() {
        if (!this.keepGoing) {
            System.out.println("Para-C98329.");
            return;
        }
        final GenericResponse connectServer = this.connectServer();
        if (!this.keepGoing) {
            System.out.println(new Date() + " Para-Qt65325.");
            this.discontinue();
            return;
        }
        this.chatSession.connectedLogin(this.connData, connectServer, this);
        System.out.println(new Date() + " bg-job done");
    }
    
    private GenericResponse connectServer() {
        final GenericResponse genericResponse = new GenericResponse();
        genericResponse.successful = false;
        final String host = this.chatSession.paraConf.getApplet().getCodeBase().getHost();
        this.secureConn = false;
        this.tunnelConn = false;
        if (this.chatSession.paraConf.isSecure()) {
            final int secPort = ClientUtil.getSecPort(this.chatSession.paraConf);
            this.sockStream = this.createSecure(host, secPort, this.chatSession.paraConf, this.chatSession);
            if (this.sockStream != null) {
                System.out.println(new Date() + " connected to: " + secPort);
                genericResponse.successful = true;
                this.secureConn = true;
                return genericResponse;
            }
            this.chatSession.chatView.vwInfo("  " + this.chatSession.paraConf.get("Msg.Conn.SecFailed", "Secure connection did not go through."));
            if (this.chatSession.paraConf.getBool("Net.SecTunnel", true)) {
                if (this.chatSession.paraConf.getBool("Op.Tun", true)) {
                    this.chatSession.chatView.vwInfo(this.chatSession.paraConf.get("Msg.Conn.Try", "Trying alternative."));
                }
                this.sockStream = this.createHyper(host, this.chatSession.paraConf, this.chatSession, true);
                if (this.sockStream != null) {
                    System.out.println("https tun  OK.");
                    genericResponse.successful = true;
                    this.secureConn = true;
                    this.tunnelConn = true;
                    return genericResponse;
                }
            }
            if (this.chatSession.paraConf.isSecOnly()) {
                return genericResponse;
            }
        }
        if (!this.chatSession.paraConf.getBool("Net.TunnelOnly", false)) {
            final int port = ClientUtil.getPort(this.chatSession.paraConf);
            this.sockStream = this.createTCP(host, port, this.chatSession.paraConf, this.chatSession);
            if (this.sockStream != null) {
                System.out.println(new Date() + " Connected to " + port);
                genericResponse.successful = true;
                return genericResponse;
            }
            this.chatSession.chatView.vwInfo("  " + this.chatSession.paraConf.get("Msg.Conn.TcpFailed", "Regular connection did not go through."));
        }
        if (!this.chatSession.paraConf.getBool("Net.EnableTunnel", true)) {
            return genericResponse;
        }
        if (this.chatSession.paraConf.getBool("Op.Tun", true)) {
            this.chatSession.chatView.vwInfo("  " + this.chatSession.paraConf.get("Msg.Conn.Try", "Trying alternative..."));
        }
        this.sockStream = this.createHyper(host, this.chatSession.paraConf, this.chatSession, false);
        if (this.sockStream != null) {
            genericResponse.successful = true;
            this.tunnelConn = true;
        }
        return genericResponse;
    }
    
    private SockStream createSecure(final String s, final int n, final Config config, final SessionEnclosure sessionEnclosure) {
        SockStream sockStream;
        try {
            sockStream = (SockStream)Class.forName("pclient.sec.SecStream").newInstance();
        }
        catch (Exception ex) {
            System.out.println("Error CN243. Low Memory or no connection." + ex);
            ex.printStackTrace();
            return null;
        }
        sockStream.setInit(config, sessionEnclosure);
        sockStream.setListener(this);
        try {
            sockStream.connect(s, n);
        }
        catch (Exception ex2) {
            sockStream = null;
            System.out.println("cannot connect to " + n);
            ex2.printStackTrace();
        }
        return sockStream;
    }
    
    private SockStream createTCP(final String s, final int n, final Config config, final SessionEnclosure sessionEnclosure) {
        RegStream regStream = new RegStream();
        regStream.setInit(config, sessionEnclosure);
        regStream.setListener(this);
        try {
            regStream.connect(s, n);
        }
        catch (Exception ex) {
            regStream = null;
            System.out.println("cannot connect to " + n);
            ex.printStackTrace();
        }
        return regStream;
    }
    
    private HyperStream createHyper(final String s, final Config config, final SessionEnclosure sessionEnclosure, final boolean b) {
        HyperStream hyperStream = new HyperStream();
        if (b) {
            hyperStream.setSecure();
        }
        hyperStream.setInit(config, sessionEnclosure);
        hyperStream.setListener(this);
        int n;
        if (b) {
            n = config.getInt("Net.SecTunPort", 443);
        }
        else {
            n = config.getInt("Net.HTTP", 8080);
        }
        System.out.println("tun." + n);
        try {
            hyperStream.connect(s, n);
        }
        catch (Exception ex) {
            hyperStream = null;
            System.out.println("tunnel failed." + n + " sec=" + b);
            ex.printStackTrace();
        }
        return hyperStream;
    }
}

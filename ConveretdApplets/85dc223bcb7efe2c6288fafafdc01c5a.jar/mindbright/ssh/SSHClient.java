// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.net.SocketException;
import mindbright.terminal.Terminal;
import mindbright.security.MessageDigest;
import mindbright.security.RSAPrivateKey;
import mindbright.security.RSACipher;
import mindbright.security.SecureRandom;
import java.math.BigInteger;
import mindbright.security.PrivateKey;
import mindbright.security.PublicKey;
import mindbright.security.KeyPair;
import mindbright.security.RSAPublicKey;
import mindbright.security.Cipher;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.net.Socket;
import java.util.Vector;
import java.net.InetAddress;

public class SSHClient extends SSH
{
    protected Thread myThread;
    protected KeepAliveThread keepAliveThread;
    protected InetAddress serverAddr;
    protected InetAddress serverRealAddr;
    protected InetAddress localAddr;
    protected String srvVersionStr;
    protected int srvVersionMajor;
    protected int srvVersionMinor;
    protected Vector localForwards;
    protected Vector remoteForwards;
    protected String commandLine;
    protected SSHChannelController controller;
    protected SSHConsole console;
    protected SSHAuthenticator authenticator;
    protected SSHClientUser user;
    protected SSHInteractor interactor;
    protected Socket sshSocket;
    protected BufferedInputStream sshIn;
    protected BufferedOutputStream sshOut;
    protected boolean gracefulExit;
    protected boolean isConnected;
    protected boolean isOpened;
    boolean usedOTP;
    protected int refCount;
    protected boolean havePORTFtp;
    protected int firstFTPPort;
    protected boolean activateTunnels;
    private static final int CANNOT_CHOOSE_PIN = 0;
    private static final int USER_SELECTABLE = 1;
    private static final int MUST_CHOOSE_PIN = 2;
    
    public SSHClient(final SSHAuthenticator authenticator, final SSHClientUser user) {
        this.serverRealAddr = null;
        this.havePORTFtp = false;
        this.firstFTPPort = 0;
        this.activateTunnels = true;
        this.user = user;
        this.authenticator = authenticator;
        this.interactor = user.getInteractor();
        this.srvVersionStr = null;
        this.refCount = 0;
        this.usedOTP = false;
        try {
            this.localAddr = InetAddress.getByName("0.0.0.0");
        }
        catch (UnknownHostException e) {
            if (this.interactor != null) {
                this.interactor.alert("FATAL: Could not create local InetAddress: " + e.getMessage());
            }
        }
        this.clearAllForwards();
    }
    
    public void setConsole(final SSHConsole console) {
        this.console = console;
        if (this.controller != null) {
            this.controller.console = console;
        }
    }
    
    public SSHConsole getConsole() {
        return this.console;
    }
    
    public InetAddress getServerAddr() {
        return this.serverAddr;
    }
    
    public InetAddress getServerRealAddr() {
        if (this.serverRealAddr == null) {
            return this.serverAddr;
        }
        return this.serverRealAddr;
    }
    
    public void setServerRealAddr(final InetAddress realAddr) {
        this.serverRealAddr = realAddr;
    }
    
    public InetAddress getLocalAddr() {
        return this.localAddr;
    }
    
    public void setLocalAddr(final String addr) throws UnknownHostException {
        this.localAddr = InetAddress.getByName(addr);
    }
    
    public String getServerVersion() {
        return this.srvVersionStr;
    }
    
    public void addLocalPortForward(final int localPort, final String remoteHost, final int remotePort, final String plugin) throws IOException {
        this.addLocalPortForward(this.localAddr.getHostAddress(), localPort, remoteHost, remotePort, plugin);
    }
    
    public void addLocalPortForward(final String localHost, final int localPort, final String remoteHost, final int remotePort, final String plugin) throws IOException {
        this.delLocalPortForward(localHost, localPort);
        this.localForwards.addElement(new LocalForward(localHost, localPort, remoteHost, remotePort, plugin));
        if (this.isOpened) {
            try {
                this.requestLocalPortForward(localHost, localPort, remoteHost, remotePort, plugin);
            }
            catch (IOException e) {
                this.delLocalPortForward(localHost, localPort);
                throw e;
            }
        }
    }
    
    public void delLocalPortForward(final String localHost, final int port) {
        if (port == -1) {
            if (this.isOpened) {
                this.controller.killListenChannels();
            }
            this.localForwards = new Vector();
        }
        else {
            int i = 0;
            while (i < this.localForwards.size()) {
                final LocalForward fwd = this.localForwards.elementAt(i);
                if (fwd.localPort == port && fwd.localHost.equals(localHost)) {
                    this.localForwards.removeElementAt(i);
                    if (this.isOpened) {
                        this.controller.killListenChannel(fwd.localHost, fwd.localPort);
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
    }
    
    public void addRemotePortForward(final int remotePort, final String localHost, final int localPort, final String plugin) {
        this.delRemotePortForward(remotePort);
        this.remoteForwards.addElement(new RemoteForward(remotePort, localHost, localPort, plugin));
    }
    
    public void delRemotePortForward(final int port) {
        if (port == -1) {
            this.remoteForwards = new Vector();
        }
        else {
            for (int i = 0; i < this.remoteForwards.size(); ++i) {
                final RemoteForward fwd = this.remoteForwards.elementAt(i);
                if (fwd.remotePort == port) {
                    this.remoteForwards.removeElementAt(i);
                    break;
                }
            }
        }
    }
    
    public void delRemotePortForward(final String plugin) {
        for (int i = 0; i < this.remoteForwards.size(); ++i) {
            final RemoteForward fwd = this.remoteForwards.elementAt(i);
            if (fwd.plugin.equals(plugin)) {
                this.remoteForwards.removeElementAt(i);
                --i;
            }
        }
    }
    
    public void clearAllForwards() {
        this.localForwards = new Vector();
        this.remoteForwards = new Vector();
    }
    
    public void startExitMonitor() {
        this.startExitMonitor(0L);
    }
    
    public void startExitMonitor(final long msTimeout) {
        new Thread(new ExitMonitor(this, msTimeout)).start();
    }
    
    public synchronized int addRef() {
        return ++this.refCount;
    }
    
    public void forcedDisconnect() {
        if (this.controller != null) {
            this.controller.sendDisconnect("exit");
        }
        else if (this.interactor != null) {
            this.interactor.disconnected(this, false);
        }
    }
    
    public synchronized int delRef() {
        final int refCount = this.refCount - 1;
        this.refCount = refCount;
        if (refCount <= 0) {
            this.forcedDisconnect();
            this.waitForExit(2000L);
        }
        return this.refCount;
    }
    
    public void waitForExit(final long msTimeout) {
        try {
            this.controller.waitForExit(msTimeout);
        }
        catch (InterruptedException e) {
            if (this.interactor != null) {
                this.interactor.alert("Error when shutting down SSHClient: " + e.getMessage());
            }
            this.controller.killAll();
        }
        try {
            if (this.sshSocket != null) {
                this.sshSocket.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public void doSingleCommand(final String commandLine, final boolean background, final long msTimeout) throws IOException {
        this.commandLine = commandLine;
        this.bootSSH(false);
        if (background) {
            this.startExitMonitor(msTimeout);
        }
        else {
            this.waitForExit(msTimeout);
        }
    }
    
    public void bootSSH(final boolean haveCnxWatch) throws IOException {
        try {
            this.myThread = Thread.currentThread();
            if (this.interactor != null) {
                this.interactor.startNewSession(this);
            }
            final String serverAddrStr = this.user.getSrvHost();
            if (this.interactor != null) {
                this.interactor.sessionStarted(this);
            }
            this.sshSocket = this.user.getProxyConnection();
            if (this.sshSocket == null) {
                this.serverAddr = InetAddress.getByName(serverAddrStr);
                if (this.user.wantPrivileged()) {
                    int p = 1023;
                    while (p > 512) {
                        try {
                            this.sshSocket = new Socket(this.serverAddr, this.user.getSrvPort(), this.localAddr, p);
                        }
                        catch (IOException e) {
                            if (e.getMessage().toLowerCase().indexOf("use") == -1) {
                                throw e;
                            }
                            --p;
                            continue;
                        }
                        break;
                    }
                    if (p == 512) {
                        throw new IOException("No available privileged ports");
                    }
                }
                else {
                    this.sshSocket = new Socket(this.serverAddr, this.user.getSrvPort());
                }
            }
            else {
                this.serverAddr = this.sshSocket.getInetAddress();
                if (this.interactor != null) {
                    this.interactor.report("Connecting through proxy at " + this.serverAddr.getHostAddress() + ":" + this.sshSocket.getPort());
                }
            }
            this.sshIn = new BufferedInputStream(this.sshSocket.getInputStream(), 8192);
            this.sshOut = new BufferedOutputStream(this.sshSocket.getOutputStream());
            this.negotiateVersion();
            this.isConnected = true;
            if (this.interactor != null) {
                this.interactor.connected(this);
            }
            final String userName = this.authenticator.getUsername(this.user);
            this.receiveServerData();
            this.initiatePlugins();
            this.cipherType = this.authenticator.getCipher(this.user);
            if (!this.isCipherSupported(this.cipherType)) {
                throw new IOException("Sorry, server does not support the '" + SSH.getCipherName(this.authenticator.getCipher(this.user)) + "' cipher.");
            }
            this.generateSessionId();
            this.generateSessionKey();
            this.initClientCipher();
            this.sendSessionKey(this.cipherType);
            this.authenticateUser(userName);
            this.controller = new SSHChannelController(this, this.sshIn, this.sshOut, this.sndCipher, this.rcvCipher, this.console, haveCnxWatch);
            this.initiateSession();
            if (this.console != null) {
                this.console.serverConnect(this.controller, this.sndCipher);
            }
            this.isOpened = true;
            if (this.interactor != null) {
                this.interactor.open(this);
            }
            this.setAliveInterval(this.user.getAliveInterval());
            this.controller.start();
        }
        catch (IOException e2) {
            if (this.sshSocket != null) {
                this.sshSocket.close();
            }
            this.disconnect(false);
            if (this.controller != null) {
                this.controller.killListenChannels();
            }
            this.controller = null;
            throw e2;
        }
    }
    
    protected void disconnect(final boolean graceful) {
        if (!this.isConnected) {
            return;
        }
        this.isConnected = false;
        this.isOpened = false;
        this.gracefulExit = graceful;
        this.srvVersionStr = null;
        this.setAliveInterval(0);
        if (this.interactor != null) {
            this.interactor.disconnected(this, graceful);
        }
    }
    
    void negotiateVersion() throws IOException {
        byte[] buf = new byte[256];
        final int len = this.sshIn.read(buf);
        this.srvVersionStr = new String(buf, 0, len);
        try {
            int l = this.srvVersionStr.indexOf(45);
            int r = this.srvVersionStr.indexOf(46);
            this.srvVersionMajor = Integer.parseInt(this.srvVersionStr.substring(l + 1, r));
            l = r;
            r = this.srvVersionStr.indexOf(45, l);
            if (r == -1) {
                this.srvVersionMinor = Integer.parseInt(this.srvVersionStr.substring(l + 1));
            }
            else {
                this.srvVersionMinor = Integer.parseInt(this.srvVersionStr.substring(l + 1, r));
            }
        }
        catch (Throwable t) {
            throw new IOException("Server version string invalid: " + this.srvVersionStr);
        }
        if (this.srvVersionMajor > 1) {
            throw new IOException("MindTerm do not support SSHv2 yet, enable SSHv1 compatibility in server");
        }
        if (this.srvVersionMajor < 1 || this.srvVersionMinor < 5) {
            throw new IOException("Server's protocol version (" + this.srvVersionMajor + "-" + this.srvVersionMinor + ") is too old, please upgrade");
        }
        this.srvVersionStr = this.srvVersionStr.trim();
        String verStr = SSH.getVersionId(true);
        verStr += "\n";
        buf = verStr.getBytes();
        this.sshOut.write(buf);
        this.sshOut.flush();
    }
    
    void receiveServerData() throws IOException {
        final SSHPduInputStream pdu = new SSHPduInputStream(2, null);
        pdu.readFrom(this.sshIn);
        pdu.read(this.srvCookie = new byte[8], 0, 8);
        int bits = pdu.readInt();
        BigInteger e = pdu.readBigInteger();
        BigInteger n = pdu.readBigInteger();
        this.srvServerKey = new KeyPair(new RSAPublicKey(e, n), null);
        bits = pdu.readInt();
        e = pdu.readBigInteger();
        n = pdu.readBigInteger();
        this.srvHostKey = new KeyPair(new RSAPublicKey(e, n), null);
        final int keyLenDiff = Math.abs(((RSAPublicKey)this.srvServerKey.getPublic()).bitLength() - ((RSAPublicKey)this.srvHostKey.getPublic()).bitLength());
        if (keyLenDiff < 24) {
            throw new IOException("Invalid server keys, difference in sizes must be at least 24 bits");
        }
        if (!this.authenticator.verifyKnownHosts((RSAPublicKey)this.srvHostKey.getPublic())) {
            throw new IOException("Verification of known hosts failed");
        }
        this.protocolFlags = pdu.readInt();
        this.supportedCiphers = pdu.readInt();
        this.supportedAuthTypes = pdu.readInt();
        if ((this.supportedAuthTypes & 0x10000) != 0x0) {
            this.supportedAuthTypes = ((this.supportedAuthTypes & 0xFFFF) | 0x100);
        }
    }
    
    void generateSessionKey() {
        final SecureRandom rand = SSH.secureRandom();
        rand.nextBytes(this.sessionKey = new byte[32]);
        rand.startUpdater();
    }
    
    void sendSessionKey(final int cipherType) throws IOException {
        final byte[] key = new byte[this.sessionKey.length + 1];
        key[0] = 0;
        System.arraycopy(this.sessionKey, 0, key, 1, this.sessionKey.length);
        for (int i = 0; i < this.sessionId.length; ++i) {
            final byte[] array = key;
            final int n = i + 1;
            array[n] ^= this.sessionId[i];
        }
        BigInteger encKey = new BigInteger(key);
        if (((RSAPublicKey)this.srvServerKey.getPublic()).bitLength() < ((RSAPublicKey)this.srvHostKey.getPublic()).bitLength()) {
            RSACipher rsa = new RSACipher(this.srvServerKey);
            BigInteger padded = RSACipher.doPad(encKey, ((RSAPublicKey)this.srvServerKey.getPublic()).bitLength(), SSH.secureRandom());
            encKey = rsa.doPublic(padded);
            rsa = new RSACipher(this.srvHostKey);
            padded = RSACipher.doPad(encKey, ((RSAPublicKey)this.srvHostKey.getPublic()).bitLength(), SSH.secureRandom());
            encKey = rsa.doPublic(padded);
        }
        else {
            RSACipher rsa = new RSACipher(this.srvHostKey);
            BigInteger padded = RSACipher.doPad(encKey, ((RSAPublicKey)this.srvHostKey.getPublic()).bitLength(), SSH.secureRandom());
            encKey = rsa.doPublic(padded);
            rsa = new RSACipher(this.srvServerKey);
            padded = RSACipher.doPad(encKey, ((RSAPublicKey)this.srvServerKey.getPublic()).bitLength(), SSH.secureRandom());
            encKey = rsa.doPublic(padded);
        }
        final SSHPduOutputStream pdu = new SSHPduOutputStream(3, null);
        pdu.writeByte((byte)cipherType);
        pdu.write(this.srvCookie, 0, this.srvCookie.length);
        pdu.writeBigInteger(encKey);
        pdu.writeInt(this.protocolFlags);
        pdu.writeTo(this.sshOut);
        if (!this.isSuccess()) {
            throw new IOException("Error while sending session key!");
        }
    }
    
    void authenticateUser(final String userName) throws IOException {
        this.usedOTP = false;
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(4, this.sndCipher);
        outpdu.writeString(userName);
        outpdu.writeTo(this.sshOut);
        if (this.isSuccess()) {
            if (this.interactor != null) {
                this.interactor.report("Authenticated directly by server, no other authentication required");
            }
            return;
        }
        final int[] authType = this.authenticator.getAuthTypes(this.user);
        int i = 0;
        while (i < authType.length) {
            try {
                if (!this.isAuthTypeSupported(authType[i])) {
                    throw new AuthFailException("Server does not support '" + SSHClient.authTypeDesc[authType[i]] + "'");
                }
                switch (authType[i]) {
                    case 2: {
                        this.doRSAAuth(false, userName);
                        break;
                    }
                    case 3: {
                        this.doPasswdAuth(userName);
                        break;
                    }
                    case 4: {
                        this.doRSAAuth(true, userName);
                        break;
                    }
                    case 5: {
                        this.doTISAuth(userName);
                        break;
                    }
                    case 1: {
                        this.doRhostsAuth(userName);
                        break;
                    }
                    case 8: {
                        this.doSDIAuth(userName);
                        this.usedOTP = true;
                        break;
                    }
                    default: {
                        throw new IOException("We do not support selected authentication type " + SSHClient.authTypeDesc[authType[i]]);
                    }
                }
                return;
            }
            catch (AuthFailException e) {
                if (i == authType.length - 1) {
                    throw e;
                }
                if (this.interactor != null) {
                    this.interactor.report("Authenticating with " + SSHClient.authTypeDesc[authType[i]] + " failed, " + e.getMessage());
                }
                ++i;
                continue;
            }
            break;
        }
    }
    
    void doPasswdAuth(final String userName) throws IOException {
        final String password = this.authenticator.getPassword(this.user);
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(9, this.sndCipher);
        outpdu.writeString(password);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess()) {
            throw new AuthFailException("Permission denied");
        }
    }
    
    void doRhostsAuth(final String userName) throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(5, this.sndCipher);
        outpdu.writeString(userName);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess()) {
            throw new AuthFailException("Permission denied");
        }
    }
    
    void doTISAuth(final String userName) throws IOException {
        SSHPduOutputStream outpdu = new SSHPduOutputStream(39, this.sndCipher);
        outpdu.writeTo(this.sshOut);
        final SSHPduInputStream inpdu = new SSHPduInputStream(-1, this.rcvCipher);
        inpdu.readFrom(this.sshIn);
        if (inpdu.type == 15) {
            throw new AuthFailException("TIS authentication server not reachable or user unknown");
        }
        if (inpdu.type != 40) {
            throw new IOException("Protocol error, expected TIS challenge but got " + inpdu.type);
        }
        final String prompt = inpdu.readString();
        final String response = this.authenticator.getChallengeResponse(this.user, prompt);
        outpdu = new SSHPduOutputStream(41, this.sndCipher);
        outpdu.writeString(response);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess()) {
            throw new AuthFailException("Permission denied");
        }
    }
    
    void doRSAAuth(final boolean rhosts, final String userName) throws IOException {
        final SSHRSAKeyFile keyFile = this.authenticator.getIdentityFile(this.user);
        final RSAPublicKey pubKey = keyFile.getPublic();
        SSHPduOutputStream outpdu;
        if (rhosts) {
            outpdu = new SSHPduOutputStream(35, this.sndCipher);
            outpdu.writeString(userName);
            outpdu.writeInt(pubKey.bitLength());
            outpdu.writeBigInteger(pubKey.getE());
            outpdu.writeBigInteger(pubKey.getN());
        }
        else {
            outpdu = new SSHPduOutputStream(6, this.sndCipher);
            outpdu.writeBigInteger(pubKey.getN());
        }
        outpdu.writeTo(this.sshOut);
        final SSHPduInputStream inpdu = new SSHPduInputStream(-1, this.rcvCipher);
        inpdu.readFrom(this.sshIn);
        if (inpdu.type == 15) {
            throw new AuthFailException("Server refused our key" + (rhosts ? " or rhosts" : ""));
        }
        if (inpdu.type != 7) {
            throw new IOException("Protocol error, expected RSA-challenge but got " + inpdu.type);
        }
        final BigInteger challenge = inpdu.readBigInteger();
        RSAPrivateKey privKey = keyFile.getPrivate("");
        if (privKey == null) {
            privKey = keyFile.getPrivate(this.authenticator.getIdentityPassword(this.user));
        }
        else if (this.interactor != null) {
            this.interactor.report("Authenticated with password-less rsa-key '" + keyFile.getComment() + "'");
        }
        if (privKey == null) {
            throw new AuthFailException("Invalid password for key-file '" + keyFile.getComment() + "'");
        }
        this.rsaChallengeResponse(privKey, challenge);
    }
    
    void doSDIAuth(final String userName) throws IOException {
        String password = this.authenticator.getChallengeResponse(this.user, userName + "'s SDI token passcode: ");
        SSHPduOutputStream outpdu = new SSHPduOutputStream(16, this.sndCipher);
        outpdu.writeString(password);
        outpdu.writeTo(this.sshOut);
        SSHPduInputStream inpdu = new SSHPduInputStream(-1, this.rcvCipher);
        inpdu.readFrom(this.sshIn);
        Label_0462: {
            switch (inpdu.type) {
                case 14: {
                    this.interactor.report("SDI authentication accepted.");
                    break;
                }
                case 15: {
                    throw new AuthFailException("SDI authentication failed.");
                }
                case 66: {
                    password = this.interactor.promptPassword("Next token required: ");
                    outpdu = new SSHPduOutputStream(67, this.sndCipher);
                    outpdu.writeString(password);
                    outpdu.writeTo(this.sshOut);
                    if (!this.isSuccess()) {
                        throw new AuthFailException("Permission denied");
                    }
                    break;
                }
                case 68: {
                    if (!this.interactor.askConfirmation("New PIN required, do you want to continue?", false)) {
                        throw new AuthFailException("New PIN not wanted");
                    }
                    final String type = inpdu.readString();
                    final String size = inpdu.readString();
                    final int userSelect = inpdu.readInt();
                    switch (userSelect) {
                        case 0: {
                            break Label_0462;
                        }
                        case 1:
                        case 2: {
                            String pwdChk;
                            do {
                                password = this.interactor.promptPassword("Please enter new PIN containing " + size + " " + type);
                                pwdChk = this.interactor.promptPassword("Please enter new PIN again");
                            } while (!password.equals(pwdChk));
                            outpdu = new SSHPduOutputStream(71, this.sndCipher);
                            outpdu.writeString(password);
                            outpdu.writeTo(this.sshOut);
                            inpdu = new SSHPduInputStream(-1, this.rcvCipher);
                            inpdu.readFrom(this.sshIn);
                            if (inpdu.type != 69) {
                                throw new AuthFailException("PIN rejected by server");
                            }
                            throw new AuthFailException("New PIN accepted, Wait for the code on your token to change");
                        }
                        default: {
                            throw new AuthFailException("Invalid response from server");
                        }
                    }
                    break;
                }
                default: {
                    throw new AuthFailException("Permission denied");
                }
            }
        }
    }
    
    void rsaChallengeResponse(final RSAPrivateKey privKey, BigInteger challenge) throws IOException {
        final RSACipher rsa = new RSACipher(new KeyPair(null, privKey));
        challenge = rsa.doPrivate(challenge);
        challenge = RSACipher.stripPad(challenge);
        byte[] response = challenge.toByteArray();
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            if (response[0] == 0) {
                md5.update(response, 1, 32);
            }
            else {
                md5.update(response, 0, 32);
            }
            md5.update(this.sessionId);
            response = md5.digest();
        }
        catch (Exception e) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(8, this.sndCipher);
        outpdu.write(response, 0, response.length);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess()) {
            throw new AuthFailException("Permission denied");
        }
    }
    
    void initiateSession() throws IOException {
        if (this.user.wantPTY()) {
            this.requestPTY();
        }
        final int maxPktSz = this.user.getMaxPacketSz();
        if (maxPktSz > 0) {
            this.requestMaxPacketSz(maxPktSz);
        }
        if (this.user.wantX11Forward()) {
            this.requestX11Forward();
        }
        if (this.activateTunnels) {
            this.initiateTunnels();
        }
        if (this.commandLine != null) {
            this.requestCommand(this.commandLine);
        }
        else {
            this.requestShell();
        }
    }
    
    void initiatePlugins() {
        SSHProtocolPlugin.initiateAll(this);
    }
    
    void initiateTunnels() throws IOException {
        for (int i = 0; i < this.localForwards.size(); ++i) {
            final LocalForward fwd = this.localForwards.elementAt(i);
            this.requestLocalPortForward(fwd.localHost, fwd.localPort, fwd.remoteHost, fwd.remotePort, fwd.plugin);
        }
        for (int i = 0; i < this.remoteForwards.size(); ++i) {
            final RemoteForward fwd2 = this.remoteForwards.elementAt(i);
            this.requestRemotePortForward(fwd2.remotePort, fwd2.localHost, fwd2.localPort, fwd2.plugin);
        }
    }
    
    void requestCompression(final int level) throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(37, this.sndCipher);
        outpdu.writeInt(level);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess() && this.interactor != null) {
            this.interactor.report("Error requesting compression level: " + level);
        }
    }
    
    void requestMaxPacketSz(final int sz) throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(38, this.sndCipher);
        outpdu.writeInt(sz);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess() && this.interactor != null) {
            this.interactor.report("Error requesting max packet size: " + sz);
        }
    }
    
    void requestX11Forward() throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(34, this.sndCipher);
        outpdu.writeString("MIT-MAGIC-COOKIE-1");
        outpdu.writeString("112233445566778899aabbccddeeff00");
        outpdu.writeInt(0);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess() && this.interactor != null) {
            this.interactor.report("Error requesting X11 forward");
        }
    }
    
    void requestPTY() throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(10, this.sndCipher);
        Terminal myTerminal = null;
        if (this.console != null) {
            myTerminal = this.console.getTerminal();
        }
        if (myTerminal != null) {
            outpdu.writeString(myTerminal.terminalType());
            outpdu.writeInt(myTerminal.rows());
            outpdu.writeInt(myTerminal.cols());
            outpdu.writeInt(myTerminal.vpixels());
            outpdu.writeInt(myTerminal.hpixels());
        }
        else {
            outpdu.writeString("");
            outpdu.writeInt(0);
            outpdu.writeInt(0);
            outpdu.writeInt(0);
            outpdu.writeInt(0);
        }
        outpdu.writeByte(0);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess() && this.interactor != null) {
            this.interactor.report("Error requesting PTY");
        }
    }
    
    void requestLocalPortForward(final String localHost, final int localPort, final String remoteHost, final int remotePort, final String plugin) throws IOException {
        this.controller.newListenChannel(localHost, localPort, remoteHost, remotePort, plugin);
    }
    
    void requestRemotePortForward(final int remotePort, final String localHost, final int localPort, final String plugin) throws IOException {
        try {
            SSHProtocolPlugin.getPlugin(plugin).remoteListener(remotePort, localHost, localPort, this.controller);
        }
        catch (NoClassDefFoundError e) {
            throw new IOException("Plugins not available");
        }
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(28, this.sndCipher);
        outpdu.writeInt(remotePort);
        outpdu.writeString(localHost);
        outpdu.writeInt(localPort);
        outpdu.writeTo(this.sshOut);
        if (!this.isSuccess() && this.interactor != null) {
            this.interactor.report("Error requesting remote port forward: " + plugin + "/" + remotePort + ":" + localHost + ":" + localPort);
        }
    }
    
    void requestCommand(final String command) throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(13, this.sndCipher);
        outpdu.writeString(command);
        outpdu.writeTo(this.sshOut);
    }
    
    void requestShell() throws IOException {
        final SSHPduOutputStream outpdu = new SSHPduOutputStream(12, this.sndCipher);
        outpdu.writeTo(this.sshOut);
    }
    
    boolean isSuccess() throws IOException {
        boolean success = false;
        SSHPduInputStream inpdu = null;
        inpdu = new SSHPduInputStream(-1, this.rcvCipher);
        inpdu.readFrom(this.sshIn);
        if (inpdu.type == 14) {
            success = true;
        }
        else if (inpdu.type == 15) {
            success = false;
        }
        else {
            if (inpdu.type == 1) {
                throw new IOException("Server disconnected: " + inpdu.readString());
            }
            throw new IOException("Protocol error: got " + inpdu.type + " when expecting success/failure");
        }
        return success;
    }
    
    void setInteractive() {
        try {
            this.sshSocket.setTcpNoDelay(true);
        }
        catch (SocketException e) {
            if (this.interactor != null) {
                this.interactor.report("Error setting interactive mode: " + e.getMessage());
            }
        }
    }
    
    void setAliveInterval(final int i) {
        if (i == 0) {
            if (this.keepAliveThread != null && this.keepAliveThread.isAlive()) {
                this.keepAliveThread = null;
            }
        }
        else if (this.keepAliveThread != null) {
            this.keepAliveThread.setInterval(i);
        }
        else {
            (this.keepAliveThread = new KeepAliveThread(i)).start();
        }
    }
    
    public boolean isOpened() {
        return this.isOpened;
    }
    
    public boolean isConnected() {
        return this.isConnected;
    }
    
    void stdinWriteChar(final char c) throws IOException {
        this.stdinWriteString(String.valueOf(c));
    }
    
    void stdinWriteString(final String str) throws IOException {
        this.stdinWriteString(str.getBytes(), 0, str.length());
    }
    
    void stdinWriteString(final byte[] str) throws IOException {
        this.stdinWriteString(str, 0, str.length);
    }
    
    void stdinWriteString(final byte[] str, final int off, final int len) throws IOException {
        if (this.isOpened && this.controller != null) {
            final SSHPduOutputStream stdinPdu = new SSHPduOutputStream(16, this.sndCipher);
            stdinPdu.writeInt(len);
            stdinPdu.write(str, off, len);
            this.controller.transmit(stdinPdu);
        }
    }
    
    void signalWindowChanged(final int rows, final int cols, final int vpixels, final int hpixels) {
        if (this.isOpened && this.controller != null) {
            try {
                final SSHPduOutputStream pdu = new SSHPduOutputStream(11, this.sndCipher);
                pdu.writeInt(rows);
                pdu.writeInt(cols);
                pdu.writeInt(vpixels);
                pdu.writeInt(hpixels);
                this.controller.transmit(pdu);
            }
            catch (Exception ex) {
                if (this.interactor != null) {
                    this.interactor.alert("Error when sending sigWinch: " + ex.toString());
                }
            }
        }
    }
    
    public static class AuthFailException extends IOException
    {
        public AuthFailException(final String msg) {
            super(msg);
        }
    }
    
    public static class ExitMonitor implements Runnable
    {
        SSHClient client;
        long msTimeout;
        
        public ExitMonitor(final SSHClient client, final long msTimeout) {
            this.msTimeout = msTimeout;
            this.client = client;
        }
        
        public ExitMonitor(final SSHClient client) {
            this(client, 0L);
        }
        
        public void run() {
            this.client.waitForExit(this.msTimeout);
            if (!this.client.gracefulExit) {
                this.client.disconnect(false);
            }
        }
    }
    
    private class KeepAliveThread extends Thread
    {
        int interval;
        
        public KeepAliveThread(final int i) {
            this.interval = i;
        }
        
        public synchronized void setInterval(final int i) {
            this.interval = i;
        }
        
        public void run() {
            while (true) {
                try {
                    while (true) {
                        final int i;
                        synchronized (this) {
                            i = this.interval;
                        }
                        Thread.sleep(1000 * i);
                        if (SSHClient.this.controller != null) {
                            final SSHPduOutputStream ignmsg = new SSHPduOutputStream(36, SSHClient.this.controller.sndCipher);
                            ignmsg.writeString("heartbeat");
                            SSHClient.this.controller.transmit(ignmsg);
                        }
                    }
                }
                catch (Exception e) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static class LocalForward
    {
        protected String localHost;
        protected int localPort;
        protected String remoteHost;
        protected int remotePort;
        protected String plugin;
        
        public LocalForward(final String localHost, final int localPort, final String remoteHost, final int remotePort, final String plugin) {
            this.localHost = localHost;
            this.localPort = localPort;
            this.remoteHost = remoteHost;
            this.remotePort = remotePort;
            this.plugin = plugin;
        }
    }
    
    public static class RemoteForward
    {
        protected int remotePort;
        protected String localHost;
        protected int localPort;
        protected String plugin;
        
        public RemoteForward(final int remotePort, final String localHost, final int localPort, final String plugin) {
            this.remotePort = remotePort;
            this.localHost = localHost;
            this.localPort = localPort;
            this.plugin = plugin;
        }
    }
}

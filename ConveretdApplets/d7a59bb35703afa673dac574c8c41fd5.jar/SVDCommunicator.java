import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.LayoutManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.text.DateFormat;
import java.util.Calendar;
import netscape.javascript.JSObject;
import java.awt.Component;
import java.awt.Graphics;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SVDCommunicator extends Applet implements Runnable, MouseMotionListener, MouseListener, ActionListener
{
    String sParamSite;
    String sParamLanguage;
    String sParamInvited;
    String sParamPort;
    String sParamUsername;
    String sServerHandle;
    String sServerID;
    int nServerPort;
    int nSessionHandle;
    int nSessionID;
    RX cipher;
    String sExportKey;
    String sServerKey;
    String sServerFactor;
    String jsURLBase;
    String jsURLSearch;
    String jsBrowserName;
    String jsBrowserVersion;
    String jsOS;
    String jsTitle;
    String jsReferrer;
    int jsScreenWidth;
    int jsScreenHeight;
    int jsColorDepth;
    String sLastMessage;
    Date dateType;
    Date dateLast;
    boolean bIsWriting;
    boolean bOperatorPaused;
    boolean bEmailVisible;
    SVDText svdText;
    Image img00;
    Image img01;
    Image img02;
    Image img03;
    Image img04;
    Image img05;
    Image img06;
    Image img07;
    Image img08;
    Image img09;
    Image img10;
    Image img11;
    Image imgStatus;
    Image imgSend;
    Image imgVideoOff;
    static Image imgFrame;
    Color clStatus;
    Font fnStatus;
    Font fnEmail;
    FontMetrics fmStatus;
    String sStatus;
    Rectangle rcVideoArea;
    Rectangle rcVideoImage;
    int nLastVideoWidth;
    int nAppWidth;
    int nAppHeight;
    Toolkit toolkit;
    SVDVideoPanel wndVideo;
    TextField txtEmail;
    static boolean bConnectionOK;
    boolean bIsConnecting;
    boolean bAllowReconnect;
    Socket netSocket;
    DataInputStream netInput;
    DataOutputStream netOutput;
    DataOutputStream netSharp;
    DataOutputStream netText;
    Thread thrInput;
    Thread thrReconnect;
    Thread thrCheckConnection;
    Thread thrSendIsTyping;
    int nReconnectTimer;
    
    public void SVDCommunicator() {
    }
    
    public void init() {
        this.loadDefaults();
        this.loadParameters();
        this.scanSession();
        this.getJSInfo();
        this.loadGraphics();
    }
    
    public void start() {
        this.bOperatorPaused = false;
        this.bAllowReconnect = true;
        this.connectSVD();
        (this.thrReconnect = new Thread(this, "SVD_RECONNECT")).start();
        (this.thrInput = new Thread(this, "SVD_INPUT")).start();
        (this.thrCheckConnection = new Thread(this, "SVD_CHECKCONNECTION")).start();
        (this.thrSendIsTyping = new Thread(this, "SVD_SENDISTYPING")).start();
    }
    
    public void run() {
        while (Thread.currentThread() == this.thrSendIsTyping) {
            this.sendIsTyping();
        }
        while (Thread.currentThread() == this.thrCheckConnection) {
            this.checkConnection();
        }
        while (Thread.currentThread() == this.thrReconnect) {
            this.reconnectSVD();
        }
        while (Thread.currentThread() == this.thrInput) {
            this.processServerMessages();
        }
    }
    
    public void stop() {
        this.thrInput.stop();
        this.thrReconnect.stop();
        this.thrCheckConnection.stop();
        this.thrSendIsTyping.stop();
        this.netOutput = null;
        this.netSharp = null;
        this.netText = null;
        if (this.netSocket != null) {
            try {
                this.netSocket.close();
            }
            catch (IOException ex) {}
        }
        this.netSocket = null;
    }
    
    public void paint(final Graphics g) {
        this.drawBackground(g);
        this.drawStatus(null, g);
        if (this.bEmailVisible) {
            g.setColor(Color.white);
            g.setFont(this.fnStatus);
            g.drawString("Cancel", this.nAppWidth - this.fmStatus.stringWidth("Cancel") - 10, 279);
            g.drawLine(this.nAppWidth - this.fmStatus.stringWidth("Cancel") - 10, 280, this.nAppWidth - 10, 280);
            g.drawString("OK", this.nAppWidth - this.fmStatus.stringWidth("OK") - 58, 279);
            g.drawLine(this.nAppWidth - this.fmStatus.stringWidth("OK") - 58, 280, this.nAppWidth - 58, 280);
            g.drawString("Email:", 335, 279);
        }
        else {
            g.setColor(Color.white);
            g.setFont(this.fnStatus);
            g.drawString("Save this conversation", this.nAppWidth - this.fmStatus.stringWidth("Save this conversation") - 10, 279);
            g.drawLine(this.nAppWidth - this.fmStatus.stringWidth("Save this conversation") - 10, 280, this.nAppWidth - 10, 280);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void loadDefaults() {
        SVDCommunicator.bConnectionOK = false;
        this.bIsConnecting = false;
        this.bEmailVisible = false;
        this.nReconnectTimer = 0;
        this.sStatus = "";
        (this.cipher = new RX()).initialize();
        final int nKeyLen = this.cipher.getPublicKey().length();
        this.sExportKey = ((nKeyLen < 10) ? "00" : ((nKeyLen < 100) ? "0" : ""));
        this.sExportKey = this.sExportKey + String.valueOf(nKeyLen) + this.cipher.getPublicKey() + this.cipher.getFactor();
        this.nLastVideoWidth = 0;
        this.add(this.svdText = new SVDText(this));
        this.svdText.setBounds(4, 4, 216, 254);
        this.svdText.txtAlt.setEnabled(false);
        this.add(this.txtEmail = new TextField(""));
        this.txtEmail.setBounds(370, 265, 115, 18);
        this.txtEmail.setVisible(false);
        this.txtEmail.addActionListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void loadParameters() {
        this.sParamSite = this.getParameter("site");
        this.sParamLanguage = this.getParameter("lang");
        this.sParamInvited = this.getParameter("invited");
        this.sParamPort = this.getParameter("port");
        this.sParamUsername = this.getParameter("user");
        if (this.sParamSite == null) {
            this.sParamSite = "Missing applet parameter";
        }
        if (this.sParamLanguage == null) {
            this.sParamLanguage = "eng";
        }
        if (this.sParamInvited == null) {
            this.sParamInvited = "no";
        }
        if (this.sParamPort == null) {
            this.sParamPort = "80";
        }
        if (this.sParamUsername == null) {
            this.sParamUsername = "Visitor";
        }
        else if (this.sParamUsername.equals("")) {
            this.sParamUsername = "Visitor";
        }
        try {
            this.nServerPort = Integer.parseInt(this.sParamPort);
        }
        catch (NumberFormatException nfe) {
            this.nServerPort = 80;
        }
    }
    
    public void scanSession() {
        this.sServerHandle = this.getParameter("handle");
        this.sServerID = this.getParameter("id");
        if (this.sServerHandle != null && this.sServerID != null && !this.sServerHandle.equals("0") && !this.sServerID.equals("0")) {
            try {
                this.nSessionHandle = Integer.parseInt(this.sServerHandle);
                this.nSessionID = Integer.parseInt(this.sServerID);
            }
            catch (NumberFormatException nfe) {
                final boolean b = false;
                this.nSessionID = (b ? 1 : 0);
                this.nSessionHandle = (b ? 1 : 0);
            }
            return;
        }
        String sCookie;
        try {
            final JSObject jsWindow = JSObject.getWindow((Applet)this);
            if (jsWindow == null) {
                final boolean b2 = false;
                this.nSessionID = (b2 ? 1 : 0);
                this.nSessionHandle = (b2 ? 1 : 0);
                return;
            }
            final JSObject jsDocument = (JSObject)jsWindow.getMember("document");
            if (jsDocument == null) {
                final boolean b3 = false;
                this.nSessionID = (b3 ? 1 : 0);
                this.nSessionHandle = (b3 ? 1 : 0);
                return;
            }
            jsDocument.setMember("cookie", (Object)"Muie la server");
            sCookie = (String)jsDocument.getMember("cookie");
        }
        catch (Exception e) {
            final boolean b4 = false;
            this.nSessionID = (b4 ? 1 : 0);
            this.nSessionHandle = (b4 ? 1 : 0);
            return;
        }
        if (sCookie == null) {
            final boolean b5 = false;
            this.nSessionID = (b5 ? 1 : 0);
            this.nSessionHandle = (b5 ? 1 : 0);
            return;
        }
        if (sCookie.indexOf("SVDServerAssignedHandle") != -1 && sCookie.indexOf("SVDServerAssignedId") != -1) {
            int nTemp = sCookie.indexOf(";", sCookie.indexOf("SVDServerAssignedHandle"));
            if (nTemp != -1) {
                this.sServerHandle = sCookie.substring(sCookie.indexOf("SVDServerAssignedHandle") + 24, nTemp);
            }
            else {
                this.sServerHandle = sCookie.substring(sCookie.indexOf("SVDServerAssignedHandle") + 24);
            }
            nTemp = sCookie.indexOf(";", sCookie.indexOf("SVDServerAssignedId"));
            if (nTemp != -1) {
                this.sServerID = sCookie.substring(sCookie.indexOf("SVDServerAssignedId") + 20, nTemp);
            }
            else {
                this.sServerID = sCookie.substring(sCookie.indexOf("SVDServerAssignedId") + 20);
            }
            try {
                this.nSessionHandle = Integer.parseInt(this.sServerHandle);
                this.nSessionID = Integer.parseInt(this.sServerID);
            }
            catch (NumberFormatException nfe2) {
                final boolean b6 = false;
                this.nSessionID = (b6 ? 1 : 0);
                this.nSessionHandle = (b6 ? 1 : 0);
            }
            return;
        }
        final boolean b7 = false;
        this.nSessionID = (b7 ? 1 : 0);
        this.nSessionHandle = (b7 ? 1 : 0);
    }
    
    public void processServerMessages() {
        if (SVDCommunicator.bConnectionOK) {
            final SVDMessage msg = new SVDMessage(this);
            if (msg.readHeader(this.netInput)) {
                if (msg.readMessage(this.netInput)) {
                    switch (msg.btCommand) {
                        case 4: {
                            this.msgVideoFrame(msg);
                            break;
                        }
                        case 26: {
                            this.msgRegistration(msg);
                            break;
                        }
                        case 104: {
                            this.msgEncryptionKey(msg);
                            break;
                        }
                        case 5: {
                            this.msgChat(msg);
                            break;
                        }
                        case 101: {
                            this.msgIsTyping(msg);
                            break;
                        }
                        case 35: {
                            this.msgNoOperatorOnline(msg);
                            break;
                        }
                        case 36: {
                            this.msgAlreadyInSession(msg);
                            break;
                        }
                        case 38: {
                            this.msgOperatorDisconnected(msg);
                            break;
                        }
                        case 100: {
                            this.msgDisconnectedByOperator(msg);
                            break;
                        }
                    }
                }
                else {
                    this.setConnectionStatus(false);
                }
            }
            else {
                this.setConnectionStatus(false);
            }
        }
        else {
            synchronized (this) {
                try {
                    this.wait(1000L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public synchronized void reconnectSVD() {
        if (this.bAllowReconnect) {
            this.nReconnectTimer = (this.nReconnectTimer + 1) % 6;
            if (this.bIsConnecting) {
                this.drawStatus("Connecting ... ", this.getGraphics());
            }
            else if (!SVDCommunicator.bConnectionOK) {
                if (this.nReconnectTimer != 0) {
                    this.drawStatus("Connection lost. Attempting to reconnect in " + (6 - this.nReconnectTimer) + " seconds ...", this.getGraphics());
                }
                else {
                    this.connectSVD();
                }
            }
        }
        else {
            this.drawStatus("Disconnected from the server.", this.getGraphics());
        }
        try {
            this.wait(1000L);
        }
        catch (InterruptedException ex) {}
    }
    
    public synchronized void checkConnection() {
        if (SVDCommunicator.bConnectionOK) {
            final byte[] btSharp = { 35 };
            try {
                this.netSharp.write(btSharp, 0, 1);
                this.netSharp.flush();
            }
            catch (IOException ioe) {}
            catch (NullPointerException ex) {}
        }
        try {
            this.wait(10000L);
        }
        catch (InterruptedException ex2) {}
    }
    
    public synchronized void sendIsTyping() {
        try {
            this.wait(1000L);
        }
        catch (InterruptedException ie) {}
        catch (IllegalMonitorStateException ex) {}
        if (this.bIsWriting) {
            final Calendar calendar = Calendar.getInstance();
            this.dateType = calendar.getTime();
            if (this.dateType.getTime() - this.dateLast.getTime() > 5000L) {
                this.bIsWriting = false;
                final SVDMessage msg = new SVDMessage(this);
                msg.btCommand = 101;
                msg.btReserved = 110;
                msg.idSesiune = this.nSessionHandle;
                msg.idSender = this.nSessionID;
                msg.idDest = 0;
                msg.nSizeOfMessage = 20;
                msg.writeHeader();
                msg.sendMessage(this.netText);
            }
        }
    }
    
    public boolean connectSVD() {
        this.bIsConnecting = true;
        this.drawStatus("Connecting ...", this.getGraphics());
        try {
            this.netSocket = new Socket(this.getCodeBase().getHost(), this.nServerPort);
        }
        catch (IOException ioe) {
            return this.setConnectionStatus(false);
        }
        catch (SecurityException se) {
            return this.setConnectionStatus(false);
        }
        if (this.netSocket == null) {
            return this.setConnectionStatus(false);
        }
        try {
            this.netInput = new DataInputStream(this.netSocket.getInputStream());
            this.netOutput = new DataOutputStream(this.netSocket.getOutputStream());
            this.netSharp = new DataOutputStream(this.netSocket.getOutputStream());
            this.netText = new DataOutputStream(this.netSocket.getOutputStream());
        }
        catch (IOException ioe) {
            return this.setConnectionStatus(false);
        }
        if (this.netInput == null || this.netOutput == null) {
            return this.setConnectionStatus(false);
        }
        final SVDMessage msg = new SVDMessage(this);
        msg.btCommand = 12;
        msg.btReserved = (byte)(this.sParamInvited.equalsIgnoreCase("yes") ? 121 : 120);
        msg.idSesiune = this.nSessionHandle;
        msg.idSender = this.nSessionID;
        msg.idDest = 0;
        msg.nSizeOfMessage = 32 + this.sParamSite.length() + this.jsURLBase.length() + this.jsURLSearch.length() + this.jsOS.length() + this.jsBrowserName.length() + this.jsBrowserVersion.length() + this.jsTitle.length() + this.jsReferrer.length() + this.sExportKey.length() + 9;
        msg.writeHeader();
        msg.writeInt(this.jsColorDepth);
        msg.writeInt(this.jsScreenWidth);
        msg.writeInt(this.jsScreenHeight);
        msg.writeString(this.sParamSite);
        msg.writeString(this.jsURLBase);
        msg.writeString(this.jsURLSearch);
        msg.writeString(this.jsOS);
        msg.writeString(this.jsBrowserName);
        msg.writeString(this.jsBrowserVersion);
        msg.writeString(this.jsTitle);
        msg.writeString(this.jsReferrer);
        msg.writeString(this.sExportKey);
        this.bIsConnecting = false;
        return this.setConnectionStatus(msg.sendMessage(this.netOutput));
    }
    
    public boolean setConnectionStatus(final boolean bState) {
        if (!bState) {
            this.nReconnectTimer = 0;
        }
        if (bState) {
            SVDCommunicator.bConnectionOK = true;
        }
        else {
            this.svdText.txtAlt.txt.setText("");
            this.svdText.txtAlt.setEnabled(false);
            SVDCommunicator.bConnectionOK = false;
            this.nLastVideoWidth = 100;
            SVDCommunicator.imgFrame = this.imgVideoOff;
            this.wndVideo.setBounds(349, 81, 100, 100);
            this.wndVideo.repaint();
            this.bIsConnecting = false;
            this.netInput = null;
            this.netOutput = null;
            this.netSharp = null;
            this.netText = null;
            if (this.netSocket != null) {
                try {
                    this.netSocket.close();
                }
                catch (IOException ex) {}
                this.netSocket = null;
            }
        }
        return SVDCommunicator.bConnectionOK;
    }
    
    public void sendTextMessage(final String sMessage, final boolean bIsEmail) {
        this.bIsWriting = false;
        final SVDMessage msg = new SVDMessage(this);
        msg.btCommand = 24;
        msg.btReserved = 120;
        msg.idSesiune = this.nSessionHandle;
        msg.idSender = this.nSessionID;
        msg.idDest = 0;
        msg.nSizeOfMessage = 20 + sMessage.length() + 1 + (bIsEmail ? 1 : 0);
        msg.writeHeader();
        if (bIsEmail) {
            msg.writeByte((byte)0);
        }
        msg.writeString(sMessage);
        msg.sendMessage(this.netText);
    }
    
    public void msgRegistration(final SVDMessage msg) {
        this.nSessionHandle = msg.idSender;
        this.nSessionID = msg.idDest;
        this.sServerHandle = String.valueOf(this.nSessionHandle);
        this.sServerID = String.valueOf(this.nSessionID);
        try {
            JSObject jsWindow = null;
            jsWindow = JSObject.getWindow((Applet)this);
            if (jsWindow != null) {
                JSObject jsDocument = null;
                jsDocument = (JSObject)jsWindow.getMember("document");
                if (jsDocument != null) {
                    jsDocument.setMember("cookie", (Object)("SVDServerAssignedHandle=" + this.sServerHandle));
                    jsDocument.setMember("cookie", (Object)("SVDServerAssignedId=" + this.sServerID));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void msgEncryptionKey(final SVDMessage msg) {
        final String sKey = new String(msg.btMessage, 0, msg.nSizeOfMessage - 20);
        if (sKey == null) {
            this.setConnectionStatus(false);
            return;
        }
        if (sKey.length() > 3) {
            int nKeyLen;
            try {
                nKeyLen = Integer.parseInt(sKey.substring(0, 3));
            }
            catch (NumberFormatException nfe) {
                this.setConnectionStatus(false);
                return;
            }
            try {
                this.sServerKey = sKey.substring(3, nKeyLen + 3);
                this.sServerFactor = sKey.substring(3 + nKeyLen, sKey.length() - 1);
                this.svdText.txtAlt.setEnabled(true);
            }
            catch (IndexOutOfBoundsException iobe) {
                this.setConnectionStatus(false);
            }
            return;
        }
        this.setConnectionStatus(false);
    }
    
    public void msgChat(final SVDMessage msg) {
        String sMessage = new String(msg.btMessage, 0, msg.nSizeOfMessage - 20 - 1);
        try {
            sMessage = this.cipher.decrypt(sMessage, this.cipher.getPrivateKey(), this.cipher.getFactor());
        }
        catch (Exception e) {
            return;
        }
        sMessage = sMessage.substring(0, sMessage.length() - 2);
        this.svdText.txtMain.addText(sMessage, true);
        if (msg.btReserved == 121) {
            this.bOperatorPaused = true;
        }
        else if (msg.btReserved == 110) {
            this.bOperatorPaused = false;
        }
        Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        this.drawStatus(this.sLastMessage = "Last message received at " + DateFormat.getTimeInstance().format(date), this.getGraphics());
    }
    
    public void msgIsTyping(final SVDMessage msg) {
        final String sOperatorName = new String(msg.btMessage);
        if (msg.btReserved == 121) {
            this.drawStatus(sOperatorName + " is typing a message ...", this.getGraphics());
        }
        else {
            this.drawStatus(this.sLastMessage, this.getGraphics());
        }
    }
    
    public void msgNoOperatorOnline(final SVDMessage msg) {
        this.svdText.txtMain.addText("There is no opertator online at this time. Please check back later.", true);
        this.setConnectionStatus(this.bAllowReconnect = false);
    }
    
    public void msgAlreadyInSession(final SVDMessage msg) {
        this.svdText.txtMain.addText("You already have an active session.", true);
        this.setConnectionStatus(this.bAllowReconnect = false);
    }
    
    public void msgOperatorDisconnected(final SVDMessage msg) {
        this.svdText.txtMain.addText("The operator is no longer online. Please check back later.", true);
        this.setConnectionStatus(this.bAllowReconnect = false);
    }
    
    public void msgDisconnectedByOperator(final SVDMessage msg) {
        this.svdText.txtMain.addText("You have been disconnected by the operator.", true);
        this.setConnectionStatus(this.bAllowReconnect = false);
    }
    
    public void msgVideoFrame(final SVDMessage msg) {
        final int nVideoSize = msg.nSizeOfMessage - 20;
        final int nVideoWidth = msg.idSender;
        final int nVideoHeight = msg.idDest;
        final Image imgVideo = this.toolkit.createImage(msg.btMessage, 0, nVideoSize);
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(imgVideo, 0);
        try {
            tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (imgVideo != null) {
            if (nVideoWidth != this.nLastVideoWidth) {
                this.nLastVideoWidth = nVideoWidth;
                SVDCommunicator.imgFrame = imgVideo;
                this.wndVideo.setBounds(239 + (320 - nVideoWidth) / 2, 11 + (240 - nVideoHeight) / 2, nVideoWidth, nVideoHeight);
            }
            try {
                final Graphics grWndVideo = this.wndVideo.getGraphics();
                grWndVideo.drawImage(imgVideo, 0, 0, this);
            }
            catch (NullPointerException ex2) {}
        }
        final SVDMessage msgVideoOK = new SVDMessage(this);
        msgVideoOK.btCommand = 25;
        msgVideoOK.btReserved = 120;
        msgVideoOK.nSizeOfMessage = 20;
        msgVideoOK.idSesiune = this.nSessionHandle;
        msgVideoOK.idSender = this.nSessionID;
        msgVideoOK.idDest = 0;
        msgVideoOK.writeHeader();
        if (!msgVideoOK.sendMessage(this.netOutput)) {
            this.setConnectionStatus(false);
        }
    }
    
    public void getJSInfo() {
        final Toolkit tk = Toolkit.getDefaultToolkit();
        final Dimension dim = tk.getScreenSize();
        this.jsColorDepth = tk.getColorModel().getPixelSize();
        this.jsScreenWidth = dim.width;
        this.jsScreenHeight = dim.height;
        try {
            JSObject jsWindow = null;
            jsWindow = JSObject.getWindow((Applet)this);
            if (jsWindow == null) {
                final String jsURLBase = "(N/A)";
                this.jsReferrer = jsURLBase;
                this.jsTitle = jsURLBase;
                this.jsOS = jsURLBase;
                this.jsBrowserVersion = jsURLBase;
                this.jsBrowserName = jsURLBase;
                this.jsURLSearch = jsURLBase;
                this.jsURLBase = jsURLBase;
                return;
            }
            final JSObject jsLocation = (JSObject)jsWindow.getMember("location");
            if (jsLocation != null) {
                this.jsURLBase = (String)jsLocation.getMember("protocol") + "//" + jsLocation.getMember("host") + jsLocation.getMember("pathname");
                this.jsURLSearch = (String)jsLocation.getMember("search");
            }
            else {
                final String s = "(N/A)";
                this.jsURLSearch = s;
                this.jsURLBase = s;
            }
            final JSObject jsNavigator = (JSObject)jsWindow.getMember("navigator");
            if (jsNavigator != null) {
                this.jsBrowserName = (String)jsNavigator.getMember("appName");
                this.jsBrowserVersion = (String)jsNavigator.getMember("appVersion");
                this.jsOS = (String)jsNavigator.getMember("platform");
            }
            else {
                final String jsBrowserName = "(N/A)";
                this.jsOS = jsBrowserName;
                this.jsBrowserVersion = jsBrowserName;
                this.jsBrowserName = jsBrowserName;
            }
            final JSObject jsDocument = (JSObject)jsWindow.getMember("document");
            if (jsDocument != null) {
                this.jsTitle = (String)jsDocument.getMember("title");
                this.jsReferrer = (String)jsDocument.getMember("referrer");
            }
            else {
                final String s2 = "(N/A)";
                this.jsReferrer = s2;
                this.jsTitle = s2;
            }
        }
        catch (Exception e) {
            final String jsURLBase2 = "(N/A)";
            this.jsReferrer = jsURLBase2;
            this.jsTitle = jsURLBase2;
            this.jsOS = jsURLBase2;
            this.jsBrowserVersion = jsURLBase2;
            this.jsBrowserName = jsURLBase2;
            this.jsURLSearch = jsURLBase2;
            this.jsURLBase = jsURLBase2;
            return;
        }
        if (this.jsURLBase == null) {
            this.jsURLBase = "(N/A)";
        }
        if (this.jsURLSearch == null) {
            this.jsURLSearch = "(N/A)";
        }
        if (this.jsBrowserName == null) {
            this.jsBrowserName = "(N/A)";
        }
        if (this.jsBrowserVersion == null) {
            this.jsBrowserVersion = "(N/A)";
        }
        if (this.jsOS == null) {
            this.jsOS = "(N/A)";
        }
        if (this.jsTitle == null) {
            this.jsTitle = "(N/A)";
        }
        if (this.jsReferrer == null) {
            this.jsReferrer = "(N/A)";
        }
    }
    
    public void loadGraphics() {
        this.setBackground(Color.white);
        final Color cl1 = new Color(10466506);
        final Color cl2 = new Color(12044247);
        final Color cl3 = new Color(13424611);
        final Color cl4 = new Color(14870767);
        final Color cl5 = new Color(16119801);
        final Color cl6 = new Color(12964575);
        final Color cl7 = new Color(12372954);
        final Color cl8 = new Color(11058127);
        final Color cl9 = new Color(11649748);
        final Color cl10 = new Color(11978454);
        final Color cl11 = new Color(15199474);
        final Color cl12 = new Color(16382972);
        final Color cl13 = new Color(15265267);
        final Color cl14 = new Color(12241369);
        this.img00 = this.createImage(7, 7);
        Graphics g = this.img00.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 7, 7);
        g.setColor(cl2);
        g.fillRect(4, 2, 1, 1);
        g.fillRect(2, 4, 1, 1);
        g.setColor(cl3);
        g.fillRect(3, 3, 1, 1);
        g.setColor(cl4);
        g.fillRect(5, 2, 1, 1);
        g.fillRect(2, 5, 1, 1);
        g.setColor(cl5);
        g.fillRect(6, 2, 1, 1);
        g.fillRect(2, 6, 1, 1);
        g.setColor(Color.white);
        g.fillRect(3, 4, 4, 3);
        g.fillRect(4, 3, 3, 1);
        this.img01 = this.createImage(223, 4);
        g = this.img01.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 223, 4);
        g.setColor(Color.white);
        g.fillRect(0, 2, 210, 2);
        g.fillRect(210, 3, 3, 1);
        g.setColor(cl5);
        g.fillRect(210, 2, 1, 1);
        g.setColor(cl4);
        g.fillRect(211, 2, 1, 1);
        g.setColor(cl2);
        g.fillRect(212, 2, 1, 1);
        g.setColor(cl3);
        g.fillRect(213, 3, 1, 1);
        this.img02 = this.createImage(10, 222);
        g = this.img02.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 10, 222);
        g.setColor(Color.white);
        g.fillRect(0, 0, 2, 217);
        g.setColor(cl10);
        g.fillRect(1, 0, 1, 1);
        g.setColor(cl11);
        g.fillRect(1, 1, 1, 1);
        g.setColor(cl12);
        g.fillRect(1, 2, 1, 1);
        g.setColor(cl5);
        g.fillRect(1, 213, 1, 1);
        g.setColor(cl11);
        g.fillRect(1, 214, 1, 1);
        g.setColor(cl10);
        g.fillRect(1, 215, 1, 1);
        g.setColor(cl3);
        g.fillRect(0, 216, 1, 1);
        g.setColor(cl1);
        g.fillRect(1, 216, 1, 1);
        this.img03 = this.createImage(10, 49);
        g = this.img03.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 10, 49);
        g.setColor(Color.white);
        g.fillRect(0, 2, 2, 30);
        g.setColor(cl3);
        g.fillRect(0, 1, 1, 1);
        g.fillRect(0, 32, 1, 1);
        g.setColor(cl10);
        g.fillRect(1, 2, 1, 1);
        g.fillRect(1, 31, 1, 1);
        g.setColor(cl11);
        g.fillRect(1, 3, 1, 1);
        g.fillRect(1, 30, 1, 1);
        g.setColor(cl12);
        g.fillRect(1, 4, 1, 1);
        g.fillRect(1, 29, 1, 1);
        this.img04 = this.createImage(220, 17);
        g = this.img04.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 220, 17);
        g.setColor(Color.white);
        g.fillRect(4, 0, 216, 2);
        g.setColor(cl3);
        g.fillRect(3, 0, 1, 1);
        g.setColor(cl2);
        g.fillRect(4, 1, 1, 1);
        g.fillRect(219, 1, 1, 1);
        g.setColor(cl4);
        g.fillRect(5, 1, 1, 1);
        g.fillRect(218, 1, 1, 1);
        g.setColor(cl5);
        g.fillRect(6, 1, 1, 1);
        g.fillRect(217, 1, 1, 1);
        this.img05 = this.createImage(3, 251);
        g = this.img05.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 3, 251);
        g.setColor(Color.white);
        g.fillRect(2, 0, 1, 251);
        g.setColor(cl12);
        g.fillRect(2, 210, 1, 1);
        g.fillRect(2, 223, 1, 1);
        g.fillRect(2, 248, 1, 1);
        g.setColor(cl13);
        g.fillRect(2, 211, 1, 1);
        g.fillRect(2, 222, 1, 1);
        g.fillRect(2, 249, 1, 1);
        g.setColor(cl2);
        g.fillRect(2, 212, 1, 1);
        g.fillRect(2, 221, 1, 1);
        g.fillRect(2, 250, 1, 1);
        g.setColor(cl1);
        g.fillRect(2, 213, 1, 8);
        this.img06 = this.createImage(217, 8);
        g = this.img06.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 217, 8);
        g.setColor(Color.white);
        g.fillRect(1, 0, 216, 2);
        g.fillRect(1, 6, 216, 2);
        g.setColor(cl3);
        g.fillRect(0, 0, 1, 1);
        g.fillRect(0, 7, 1, 1);
        g.setColor(cl2);
        g.fillRect(1, 1, 1, 1);
        g.fillRect(1, 6, 1, 1);
        g.fillRect(216, 1, 1, 1);
        g.fillRect(216, 6, 1, 1);
        g.setColor(cl4);
        g.fillRect(2, 1, 1, 1);
        g.fillRect(2, 6, 1, 1);
        g.fillRect(215, 1, 1, 1);
        g.fillRect(215, 6, 1, 1);
        g.setColor(cl5);
        g.fillRect(3, 1, 1, 1);
        g.fillRect(3, 6, 1, 1);
        g.fillRect(214, 1, 1, 1);
        g.fillRect(214, 6, 1, 1);
        this.img07 = this.createImage(340, 11);
        g = this.img07.getGraphics();
        g.setColor(cl6);
        g.fillRect(0, 2, 340, 9);
        g.setColor(cl1);
        g.fillRect(0, 0, 340, 2);
        g.fillRect(0, 3, 1, 1);
        g.fillRect(0, 2, 2, 1);
        g.fillRect(336, 2, 4, 1);
        g.fillRect(337, 3, 3, 1);
        g.fillRect(338, 4, 2, 7);
        g.setColor(cl14);
        g.fillRect(3, 2, 1, 1);
        g.fillRect(334, 2, 1, 1);
        g.setColor(cl7);
        g.fillRect(0, 5, 1, 1);
        g.fillRect(337, 5, 1, 1);
        g.setColor(cl8);
        g.fillRect(0, 4, 1, 1);
        g.fillRect(2, 2, 1, 1);
        g.fillRect(335, 2, 1, 1);
        g.fillRect(337, 4, 1, 1);
        g.setColor(cl9);
        g.fillRect(1, 3, 1, 1);
        g.fillRect(336, 3, 1, 1);
        this.img08 = this.createImage(340, 24);
        g = this.img08.getGraphics();
        g.setColor(cl6);
        g.fillRect(0, 0, 340, 9);
        g.setColor(cl1);
        g.fillRect(0, 9, 340, 15);
        g.fillRect(0, 8, 2, 1);
        g.fillRect(0, 7, 1, 1);
        g.fillRect(336, 8, 4, 1);
        g.fillRect(337, 7, 3, 1);
        g.fillRect(338, 0, 2, 7);
        g.setColor(cl7);
        g.fillRect(0, 5, 1, 1);
        g.fillRect(3, 8, 1, 1);
        g.fillRect(337, 5, 1, 1);
        g.fillRect(334, 8, 1, 1);
        g.setColor(cl8);
        g.fillRect(0, 6, 1, 1);
        g.fillRect(2, 8, 1, 1);
        g.fillRect(337, 6, 1, 1);
        g.fillRect(335, 8, 1, 1);
        g.setColor(cl9);
        g.fillRect(1, 7, 1, 1);
        g.fillRect(336, 7, 1, 1);
        this.img09 = this.createImage(9, 240);
        g = this.img09.getGraphics();
        g.setColor(cl6);
        g.fillRect(0, 0, 9, 240);
        this.img10 = this.createImage(11, 240);
        g = this.img10.getGraphics();
        g.setColor(cl6);
        g.fillRect(0, 0, 9, 240);
        g.setColor(cl1);
        g.fillRect(9, 0, 2, 240);
        this.img11 = this.createImage(570, 10);
        g = this.img11.getGraphics();
        g.setColor(cl1);
        g.fillRect(0, 0, 570, 10);
        this.clStatus = new Color(cl1.getRGB());
        this.imgStatus = this.createImage(330, 20);
        this.fnStatus = new Font("Dialog", 1, 11);
        this.fmStatus = Toolkit.getDefaultToolkit().getFontMetrics(this.fnStatus);
        this.nAppWidth = 570;
        this.nAppHeight = 285;
        this.fnEmail = new Font("Dialog", 0, 11);
        this.txtEmail.setFont(this.fnEmail);
        URL urlImg = null;
        try {
            urlImg = new URL(this.getCodeBase().toString() + this.sParamLanguage + "/");
        }
        catch (MalformedURLException ex) {}
        if (urlImg != null) {
            this.imgVideoOff = this.getImage(urlImg, "VideoOff.gif");
            this.imgSend = this.getImage(urlImg, "return2.gif");
        }
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.imgVideoOff, 0);
        try {
            tracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        SVDCommunicator.imgFrame = this.imgVideoOff;
        this.toolkit = Toolkit.getDefaultToolkit();
        this.setLayout(null);
        (this.wndVideo = new SVDVideoPanel()).setLayout(null);
        this.wndVideo.setBackground(Color.white);
        this.wndVideo.setBounds(349, 81, 100, 100);
        this.add(this.wndVideo);
    }
    
    public void drawBackground(final Graphics g) {
        g.drawImage(this.img00, 0, 0, this);
        g.drawImage(this.img01, 7, 0, this);
        g.drawImage(this.img02, 220, 4, this);
        g.drawImage(this.img03, 220, 226, this);
        g.drawImage(this.img04, 0, 258, this);
        g.drawImage(this.img05, 0, 7, this);
        g.drawImage(this.img06, 3, 220, this);
        g.drawImage(this.img07, 230, 0, this);
        g.drawImage(this.img08, 230, 251, this);
        g.drawImage(this.img09, 230, 11, this);
        g.drawImage(this.img10, 559, 11, this);
        g.drawImage(this.img11, 0, 275, this);
    }
    
    public void drawStatus(final String sNewStatus, final Graphics g) {
        if (sNewStatus != null) {
            this.sStatus = sNewStatus;
        }
        final Graphics grImage = this.imgStatus.getGraphics();
        if (grImage != null) {
            grImage.setColor(this.clStatus);
            grImage.fillRect(0, 0, 330, 20);
            grImage.setColor(Color.white);
            grImage.setFont(this.fnStatus);
            grImage.drawString(this.sStatus, 5, 14);
            g.drawImage(this.imgStatus, 0, 265, this);
        }
    }
    
    public void mouseMoved(final MouseEvent me) {
        if (this.bEmailVisible) {
            if (((me.getX() > this.nAppWidth - this.fmStatus.stringWidth("Cancel") - 10 && me.getX() < this.nAppWidth - 10) || (me.getX() > this.nAppWidth - this.fmStatus.stringWidth("OK") - 58 && me.getX() < this.nAppWidth - 58)) && me.getY() > 267 && me.getY() < 290) {
                this.setCursor(this.svdText.crHand);
            }
            else {
                this.setCursor(this.svdText.crNormal);
            }
        }
        else if (me.getX() > this.nAppWidth - this.fmStatus.stringWidth("Save this conversation") - 10 && me.getX() < this.nAppWidth - 10 && me.getY() > 267 && me.getY() < 290) {
            this.setCursor(this.svdText.crHand);
        }
        else {
            this.setCursor(this.svdText.crNormal);
        }
    }
    
    public void mousePressed(final MouseEvent me) {
        if (this.bEmailVisible) {
            if (me.getX() > this.nAppWidth - this.fmStatus.stringWidth("Cancel") - 10 && me.getX() < this.nAppWidth - 10 && me.getY() > 267 && me.getY() < 290) {
                this.bEmailVisible = false;
                this.setCursor(this.svdText.crNormal);
                this.txtEmail.setVisible(false);
                this.repaint(330, 260, 240, 25);
                this.svdText.txtAlt.requestFocus();
                return;
            }
            if (me.getX() > this.nAppWidth - this.fmStatus.stringWidth("OK") - 58 && me.getX() < this.nAppWidth - 58 && me.getY() > 267 && me.getY() < 290) {
                this.bEmailVisible = false;
                this.txtEmail.setVisible(false);
                this.repaint(330, 260, 240, 25);
                if (this.txtEmail.getText().length() > 0) {
                    this.sendTextMessage(this.txtEmail.getText(), true);
                    this.svdText.txtMain.addText("A request to email this conversation to " + this.txtEmail.getText() + " has been sent to the operator.", true);
                }
                this.svdText.txtAlt.requestFocus();
            }
        }
        else if (me.getX() > this.nAppWidth - this.fmStatus.stringWidth("Save this conversation") - 10 && me.getX() < this.nAppWidth - 10 && me.getY() > 267 && me.getY() < 290) {
            this.bEmailVisible = true;
            this.setCursor(this.svdText.crNormal);
            this.txtEmail.setText("");
            this.txtEmail.setVisible(true);
            this.txtEmail.requestFocus();
            this.repaint(330, 260, 240, 25);
        }
    }
    
    public void actionPerformed(final ActionEvent ae) {
        if (ae.getSource() == this.txtEmail) {
            this.bEmailVisible = false;
            this.setCursor(this.svdText.crNormal);
            this.txtEmail.setVisible(false);
            this.repaint(330, 260, 240, 25);
            if (this.txtEmail.getText().length() > 0) {
                this.sendTextMessage(this.txtEmail.getText(), true);
                this.svdText.txtMain.addText("A request to email this conversation to " + this.txtEmail.getText() + " has been sent to the operator.", true);
            }
            this.svdText.txtAlt.requestFocus();
        }
    }
    
    public void mouseReleased(final MouseEvent me) {
    }
    
    public void mouseClicked(final MouseEvent me) {
    }
    
    public void mouseDragged(final MouseEvent me) {
    }
    
    public void mouseExited(final MouseEvent me) {
    }
    
    public void mouseEntered(final MouseEvent me) {
    }
}

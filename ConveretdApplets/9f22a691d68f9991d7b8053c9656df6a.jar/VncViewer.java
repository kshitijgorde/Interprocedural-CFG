import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.Choice;
import java.awt.Insets;
import java.awt.Font;
import java.io.IOException;
import java.io.EOFException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.NoRouteToHostException;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VncViewer extends Applet implements Runnable, WindowListener
{
    boolean inAnApplet;
    boolean inSeparateFrame;
    String[] mainArgs;
    RfbProto rfb;
    Thread rfbThread;
    Frame vncFrame;
    Container vncContainer;
    ScrollPane desktopScrollPane;
    GridBagLayout gridbag;
    ButtonPanel buttonPanel;
    Label connStatusLabel;
    VncCanvas vc;
    OptionsFrame options;
    ClipboardFrame clipboard;
    RecordingFrame rec;
    Object recordingSync;
    String sessionFileName;
    boolean recordingActive;
    boolean recordingStatusChanged;
    String cursorUpdatesDef;
    String eightBitColorsDef;
    String socketFactory;
    String host;
    int port;
    String passwordParam;
    boolean showControls;
    boolean offerRelogin;
    boolean showOfflineDesktop;
    int deferScreenUpdates;
    int deferCursorUpdates;
    int deferUpdateRequests;
    int debugStatsExcludeUpdates;
    int debugStatsMeasureUpdates;
    public static Applet refApplet;
    int[] encodingsSaved;
    int nEncodingsSaved;
    
    public VncViewer() {
        this.inAnApplet = true;
        this.inSeparateFrame = false;
    }
    
    public static void main(final String[] mainArgs) {
        final VncViewer vncViewer = new VncViewer();
        vncViewer.mainArgs = mainArgs;
        vncViewer.inAnApplet = false;
        vncViewer.inSeparateFrame = true;
        vncViewer.init();
        vncViewer.start();
    }
    
    public void init() {
        this.readParameters();
        VncViewer.refApplet = this;
        if (this.inSeparateFrame) {
            this.vncFrame = new Frame("TightVNC");
            if (!this.inAnApplet) {
                this.vncFrame.add("Center", this);
            }
            this.vncContainer = this.vncFrame;
        }
        else {
            this.vncContainer = this;
        }
        this.recordingSync = new Object();
        this.options = new OptionsFrame(this);
        this.clipboard = new ClipboardFrame(this);
        if (RecordingFrame.checkSecurity()) {
            this.rec = new RecordingFrame(this);
        }
        this.sessionFileName = null;
        this.recordingActive = false;
        this.recordingStatusChanged = false;
        this.cursorUpdatesDef = null;
        this.eightBitColorsDef = null;
        if (this.inSeparateFrame) {
            this.vncFrame.addWindowListener(this);
        }
        (this.rfbThread = new Thread(this)).start();
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void run() {
        this.gridbag = new GridBagLayout();
        this.vncContainer.setLayout(this.gridbag);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 18;
        if (this.showControls) {
            this.buttonPanel = new ButtonPanel(this);
            this.gridbag.setConstraints(this.buttonPanel, gridBagConstraints);
            this.vncContainer.add(this.buttonPanel);
        }
        try {
            this.connectAndAuthenticate();
            this.doProtocolInitialisation();
            if (this.options.autoScale && this.inSeparateFrame) {
                Dimension screenSize;
                try {
                    screenSize = this.vncContainer.getToolkit().getScreenSize();
                }
                catch (Exception ex7) {
                    screenSize = new Dimension(0, 0);
                }
                this.createCanvas(screenSize.width - 32, screenSize.height - 32);
            }
            else {
                this.createCanvas(0, 0);
            }
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            if (this.inSeparateFrame) {
                final Panel panel = new Panel();
                panel.setLayout(new FlowLayout(0, 0, 0));
                panel.add(this.vc);
                this.desktopScrollPane = new ScrollPane(0);
                gridBagConstraints.fill = 1;
                this.gridbag.setConstraints(this.desktopScrollPane, gridBagConstraints);
                this.desktopScrollPane.add(panel);
                this.vncFrame.add(this.desktopScrollPane);
                this.vncFrame.setTitle(this.rfb.desktopName);
                this.vncFrame.pack();
                this.vc.resizeDesktopFrame();
            }
            else {
                this.gridbag.setConstraints(this.vc, gridBagConstraints);
                this.add(this.vc);
                this.validate();
            }
            if (this.showControls) {
                this.buttonPanel.enableButtons();
            }
            this.moveFocusToDesktop();
            this.processNormalProtocol();
        }
        catch (NoRouteToHostException ex) {
            this.fatalError("Network error: no route to server: " + this.host, ex);
        }
        catch (UnknownHostException ex2) {
            this.fatalError("Network error: server name unknown: " + this.host, ex2);
        }
        catch (ConnectException ex3) {
            this.fatalError("Network error: could not connect to server: " + this.host + ":" + this.port, ex3);
        }
        catch (EOFException ex4) {
            if (this.showOfflineDesktop) {
                ex4.printStackTrace();
                System.out.println("Network error: remote side closed connection");
                if (this.vc != null) {
                    this.vc.enableInput(false);
                }
                if (this.inSeparateFrame) {
                    this.vncFrame.setTitle(this.rfb.desktopName + " [disconnected]");
                }
                if (this.rfb != null && !this.rfb.closed()) {
                    this.rfb.close();
                }
                if (this.showControls && this.buttonPanel != null) {
                    this.buttonPanel.disableButtonsOnDisconnect();
                    if (this.inSeparateFrame) {
                        this.vncFrame.pack();
                    }
                    else {
                        this.validate();
                    }
                }
            }
            else {
                this.fatalError("Network error: remote side closed connection", ex4);
            }
        }
        catch (IOException ex5) {
            final String message = ex5.getMessage();
            if (message != null && message.length() != 0) {
                this.fatalError("Network Error: " + message, ex5);
            }
            else {
                this.fatalError(ex5.toString(), ex5);
            }
        }
        catch (Exception ex6) {
            final String message2 = ex6.getMessage();
            if (message2 != null && message2.length() != 0) {
                this.fatalError("Error: " + message2, ex6);
            }
            else {
                this.fatalError(ex6.toString(), ex6);
            }
        }
    }
    
    void createCanvas(final int n, final int n2) throws IOException {
        this.vc = null;
        try {
            Class.forName("java.awt.Graphics2D");
            this.vc = (VncCanvas)Class.forName("VncCanvas2").getConstructor(this.getClass(), Integer.TYPE, Integer.TYPE).newInstance(this, new Integer(n), new Integer(n2));
        }
        catch (Exception ex) {
            System.out.println("Warning: Java 2D API is not available");
        }
        if (this.vc == null) {
            this.vc = new VncCanvas(this, n, n2);
        }
    }
    
    void processNormalProtocol() throws Exception {
        try {
            this.vc.processNormalProtocol();
        }
        catch (Exception ex) {
            if (this.rfbThread != null) {
                throw ex;
            }
            System.out.println("Ignoring RFB socket exceptions because applet is stopping");
        }
    }
    
    void connectAndAuthenticate() throws Exception {
        this.showConnectionStatus("Initializing...");
        if (this.inSeparateFrame) {
            this.vncFrame.pack();
            this.vncFrame.show();
        }
        else {
            this.validate();
        }
        this.showConnectionStatus("Connecting to account " + this.port + "...");
        this.rfb = new RfbProto(this.host, this.port, this);
        this.showConnectionStatus("Connected to server");
        this.rfb.readVersionMsg();
        this.showConnectionStatus("VPS server supports protocol version " + this.rfb.serverMajor + "." + this.rfb.serverMinor);
        this.rfb.writeVersionMsg();
        final int negotiateSecurity = this.rfb.negotiateSecurity();
        int negotiateAuthenticationTight;
        if (negotiateSecurity == 16) {
            this.rfb.setupTunneling();
            negotiateAuthenticationTight = this.rfb.negotiateAuthenticationTight();
        }
        else {
            negotiateAuthenticationTight = negotiateSecurity;
        }
        switch (negotiateAuthenticationTight) {
            case 1: {
                this.showConnectionStatus("No authentication needed");
                this.rfb.authenticateNone();
                break;
            }
            case 2: {
                this.showConnectionStatus("Performing VPS authentication");
                if (this.passwordParam != null) {
                    this.rfb.authenticateVNC(this.passwordParam);
                    break;
                }
                this.rfb.authenticateVNC(this.askPassword());
                break;
            }
            default: {
                throw new Exception("Unknown authentication scheme " + negotiateAuthenticationTight);
            }
        }
    }
    
    void showConnectionStatus(final String s) {
        if (s == null) {
            if (this.vncContainer.isAncestorOf(this.connStatusLabel)) {
                this.vncContainer.remove(this.connStatusLabel);
            }
            return;
        }
        System.out.println(s);
        if (this.connStatusLabel == null) {
            (this.connStatusLabel = new Label("Status: " + s)).setFont(new Font("Helvetica", 0, 12));
        }
        else {
            this.connStatusLabel.setText("Status: " + s);
        }
        if (!this.vncContainer.isAncestorOf(this.connStatusLabel)) {
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 18;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.insets = new Insets(20, 30, 20, 30);
            this.gridbag.setConstraints(this.connStatusLabel, gridBagConstraints);
            this.vncContainer.add(this.connStatusLabel);
        }
        if (this.inSeparateFrame) {
            this.vncFrame.pack();
        }
        else {
            this.validate();
        }
    }
    
    String askPassword() throws Exception {
        this.showConnectionStatus(null);
        final AuthPanel authPanel = new AuthPanel(this);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 18;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 50;
        this.gridbag.setConstraints(authPanel, gridBagConstraints);
        this.vncContainer.add(authPanel);
        if (this.inSeparateFrame) {
            this.vncFrame.pack();
        }
        else {
            this.validate();
        }
        authPanel.moveFocusToDefaultField();
        final String password = authPanel.getPassword();
        this.vncContainer.remove(authPanel);
        return password;
    }
    
    void doProtocolInitialisation() throws IOException {
        this.rfb.writeClientInit();
        this.rfb.readServerInit();
        System.out.println("Desktop name is " + this.rfb.desktopName);
        System.out.println("Desktop size is " + this.rfb.framebufferWidth + " x " + this.rfb.framebufferHeight);
        this.setEncodings();
        this.showConnectionStatus(null);
    }
    
    void setEncodings() {
        this.setEncodings(false);
    }
    
    void autoSelectEncodings() {
        this.setEncodings(true);
    }
    
    void setEncodings(final boolean b) {
        if (this.options == null || this.rfb == null || !this.rfb.inNormalProtocol) {
            return;
        }
        int preferredEncoding = this.options.preferredEncoding;
        if (preferredEncoding == -1) {
            final long kbitsPerSecond = this.rfb.kbitsPerSecond();
            if (this.nEncodingsSaved < 1) {
                System.out.println("Using Tight/ZRLE encodings");
                preferredEncoding = 7;
            }
            else if (kbitsPerSecond > 2000L && this.encodingsSaved[0] != 5) {
                System.out.println("Throughput " + kbitsPerSecond + " kbit/s - changing to Hextile encoding");
                preferredEncoding = 5;
            }
            else if (kbitsPerSecond < 1000L && this.encodingsSaved[0] != 7) {
                System.out.println("Throughput " + kbitsPerSecond + " kbit/s - changing to Tight/ZRLE encodings");
                preferredEncoding = 7;
            }
            else {
                if (b) {
                    return;
                }
                preferredEncoding = this.encodingsSaved[0];
            }
        }
        else if (b) {
            return;
        }
        final int[] encodingsSaved = new int[20];
        int nEncodingsSaved = 0;
        encodingsSaved[nEncodingsSaved++] = preferredEncoding;
        if (this.options.useCopyRect) {
            encodingsSaved[nEncodingsSaved++] = 1;
        }
        if (preferredEncoding != 7) {
            encodingsSaved[nEncodingsSaved++] = 7;
        }
        if (preferredEncoding != 16) {
            encodingsSaved[nEncodingsSaved++] = 16;
        }
        if (preferredEncoding != 5) {
            encodingsSaved[nEncodingsSaved++] = 5;
        }
        if (preferredEncoding != 6) {
            encodingsSaved[nEncodingsSaved++] = 6;
        }
        if (preferredEncoding != 4) {
            encodingsSaved[nEncodingsSaved++] = 4;
        }
        if (preferredEncoding != 2) {
            encodingsSaved[nEncodingsSaved++] = 2;
        }
        if (this.options.compressLevel >= 0 && this.options.compressLevel <= 9) {
            encodingsSaved[nEncodingsSaved++] = -256 + this.options.compressLevel;
        }
        if (this.options.jpegQuality >= 0 && this.options.jpegQuality <= 9) {
            encodingsSaved[nEncodingsSaved++] = -32 + this.options.jpegQuality;
        }
        if (this.options.requestCursorUpdates) {
            encodingsSaved[nEncodingsSaved++] = -240;
            encodingsSaved[nEncodingsSaved++] = -239;
            if (!this.options.ignoreCursorUpdates) {
                encodingsSaved[nEncodingsSaved++] = -232;
            }
        }
        encodingsSaved[nEncodingsSaved++] = -224;
        encodingsSaved[nEncodingsSaved++] = -223;
        boolean b2 = false;
        if (nEncodingsSaved != this.nEncodingsSaved) {
            b2 = true;
        }
        else {
            for (int i = 0; i < nEncodingsSaved; ++i) {
                if (encodingsSaved[i] != this.encodingsSaved[i]) {
                    b2 = true;
                    break;
                }
            }
        }
        if (b2) {
            try {
                this.rfb.writeSetEncodings(encodingsSaved, nEncodingsSaved);
                if (this.vc != null) {
                    this.vc.softCursorFree();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.encodingsSaved = encodingsSaved;
            this.nEncodingsSaved = nEncodingsSaved;
        }
    }
    
    void setCutText(final String s) {
        try {
            if (this.rfb != null && this.rfb.inNormalProtocol) {
                this.rfb.writeClientCutText(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void setRecordingStatus(final String sessionFileName) {
        synchronized (this.recordingSync) {
            this.sessionFileName = sessionFileName;
            this.recordingStatusChanged = true;
        }
    }
    
    boolean checkRecordingStatus() throws IOException {
        synchronized (this.recordingSync) {
            if (this.recordingStatusChanged) {
                this.recordingStatusChanged = false;
                if (this.sessionFileName != null) {
                    this.startRecording();
                    return true;
                }
                this.stopRecording();
            }
        }
        return false;
    }
    
    protected void startRecording() throws IOException {
        synchronized (this.recordingSync) {
            if (!this.recordingActive) {
                final Choice[] choices = this.options.choices;
                this.options.getClass();
                this.cursorUpdatesDef = choices[3].getSelectedItem();
                final Choice[] choices2 = this.options.choices;
                this.options.getClass();
                this.eightBitColorsDef = choices2[5].getSelectedItem();
                final Choice[] choices3 = this.options.choices;
                this.options.getClass();
                choices3[3].select("Disable");
                final Choice[] choices4 = this.options.choices;
                this.options.getClass();
                choices4[3].setEnabled(false);
                this.options.setEncodings();
                final Choice[] choices5 = this.options.choices;
                this.options.getClass();
                choices5[5].select("No");
                final Choice[] choices6 = this.options.choices;
                this.options.getClass();
                choices6[5].setEnabled(false);
                this.options.setColorFormat();
            }
            else {
                this.rfb.closeSession();
            }
            System.out.println("Recording the session in " + this.sessionFileName);
            this.rfb.startSession(this.sessionFileName);
            this.recordingActive = true;
        }
    }
    
    protected void stopRecording() throws IOException {
        synchronized (this.recordingSync) {
            if (this.recordingActive) {
                final Choice[] choices = this.options.choices;
                this.options.getClass();
                choices[3].select(this.cursorUpdatesDef);
                final Choice[] choices2 = this.options.choices;
                this.options.getClass();
                choices2[3].setEnabled(true);
                this.options.setEncodings();
                final Choice[] choices3 = this.options.choices;
                this.options.getClass();
                choices3[5].select(this.eightBitColorsDef);
                final Choice[] choices4 = this.options.choices;
                this.options.getClass();
                choices4[5].setEnabled(true);
                this.options.setColorFormat();
                this.rfb.closeSession();
                System.out.println("Session recording stopped.");
            }
            this.sessionFileName = null;
            this.recordingActive = false;
        }
    }
    
    void readParameters() {
        this.host = this.readParameter("HOST", !this.inAnApplet);
        if (this.host == null) {
            this.host = this.getCodeBase().getHost();
            if (this.host.equals("")) {
                this.fatalError("HOST parameter not specified");
            }
        }
        this.port = this.readIntParameter("PORT", 5900);
        this.readPasswordParameters();
        if (this.inAnApplet) {
            final String parameter = this.readParameter("Open New Window", false);
            if (parameter != null && parameter.equalsIgnoreCase("Yes")) {
                this.inSeparateFrame = true;
            }
        }
        this.showControls = true;
        final String parameter2 = this.readParameter("Show Controls", false);
        if (parameter2 != null && parameter2.equalsIgnoreCase("No")) {
            this.showControls = false;
        }
        this.offerRelogin = true;
        final String parameter3 = this.readParameter("Offer Relogin", false);
        if (parameter3 != null && parameter3.equalsIgnoreCase("No")) {
            this.offerRelogin = false;
        }
        this.showOfflineDesktop = false;
        final String parameter4 = this.readParameter("Show Offline Desktop", false);
        if (parameter4 != null && parameter4.equalsIgnoreCase("Yes")) {
            this.showOfflineDesktop = true;
        }
        this.deferScreenUpdates = this.readIntParameter("Defer screen updates", 20);
        this.deferCursorUpdates = this.readIntParameter("Defer cursor updates", 10);
        this.deferUpdateRequests = this.readIntParameter("Defer update requests", 0);
        this.debugStatsExcludeUpdates = this.readIntParameter("DEBUG_XU", 0);
        this.debugStatsMeasureUpdates = this.readIntParameter("DEBUG_CU", 0);
        this.socketFactory = this.readParameter("SocketFactory", false);
    }
    
    private void readPasswordParameters() {
        final String parameter = this.readParameter("ENCPASSWORD", false);
        if (parameter == null) {
            this.passwordParam = this.readParameter("PASSWORD", false);
        }
        else {
            final byte[] array = { 0, 0, 0, 0, 0, 0, 0, 0 };
            int n = parameter.length() / 2;
            if (n > 8) {
                n = 8;
            }
            for (int i = 0; i < n; ++i) {
                array[i] = (byte)(Object)new Integer(Integer.parseInt(parameter.substring(i * 2, i * 2 + 2), 16));
            }
            new DesCipher(new byte[] { 23, 82, 107, 6, 35, 78, 88, 7 }).decrypt(array, 0, array, 0);
            this.passwordParam = new String(array);
        }
    }
    
    public String readParameter(final String s, final boolean b) {
        if (this.inAnApplet) {
            final String parameter = this.getParameter(s);
            if (parameter == null && b) {
                this.fatalError(s + " parameter not specified");
            }
            return parameter;
        }
        for (int i = 0; i < this.mainArgs.length; i += 2) {
            if (this.mainArgs[i].equalsIgnoreCase(s)) {
                try {
                    return this.mainArgs[i + 1];
                }
                catch (Exception ex) {
                    if (b) {
                        this.fatalError(s + " parameter not specified");
                    }
                    return null;
                }
            }
        }
        if (b) {
            this.fatalError(s + " parameter not specified");
        }
        return null;
    }
    
    int readIntParameter(final String s, final int n) {
        final String parameter = this.readParameter(s, false);
        int int1 = n;
        if (parameter != null) {
            try {
                int1 = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        return int1;
    }
    
    void moveFocusToDesktop() {
        if (this.vncContainer != null && this.vc != null && this.vncContainer.isAncestorOf(this.vc)) {
            this.vc.requestFocus();
        }
    }
    
    public synchronized void disconnect() {
        System.out.println("Disconnecting");
        if (this.vc != null) {
            final double n = Math.round(this.vc.statNumUpdates / ((System.currentTimeMillis() - this.vc.statStartTime) / 1000.0) * 100.0) / 100.0;
            final int statNumPixelRects = this.vc.statNumPixelRects;
            System.out.println("Updates received: " + this.vc.statNumUpdates + " (" + statNumPixelRects + " rectangles + " + (this.vc.statNumTotalRects - this.vc.statNumPixelRects) + " pseudo), " + n + " updates/sec");
            System.out.println("Rectangles: Tight=" + this.vc.statNumRectsTight + "(JPEG=" + this.vc.statNumRectsTightJPEG + ") ZRLE=" + this.vc.statNumRectsZRLE + " Hextile=" + this.vc.statNumRectsHextile + " Raw=" + this.vc.statNumRectsRaw + " CopyRect=" + this.vc.statNumRectsCopy + " other=" + (statNumPixelRects - this.vc.statNumRectsTight - this.vc.statNumRectsZRLE - this.vc.statNumRectsHextile - this.vc.statNumRectsRaw - this.vc.statNumRectsCopy));
            final int statNumBytesDecoded = this.vc.statNumBytesDecoded;
            final int statNumBytesEncoded = this.vc.statNumBytesEncoded;
            if (statNumBytesEncoded > 0) {
                System.out.println("Pixel data: " + this.vc.statNumBytesDecoded + " bytes, " + this.vc.statNumBytesEncoded + " compressed, ratio " + Math.round(statNumBytesDecoded / statNumBytesEncoded * 1000.0) / 1000.0);
            }
        }
        if (this.rfb != null && !this.rfb.closed()) {
            this.rfb.close();
        }
        this.options.dispose();
        this.clipboard.dispose();
        if (this.rec != null) {
            this.rec.dispose();
        }
        if (this.inAnApplet) {
            this.showMessage("Disconnected");
        }
        else {
            System.exit(0);
        }
    }
    
    public synchronized void fatalError(final String s) {
        System.out.println(s);
        if (this.inAnApplet) {
            Thread.currentThread().stop();
        }
        else {
            System.exit(1);
        }
    }
    
    public synchronized void fatalError(final String s, final Exception ex) {
        if (this.rfb != null && this.rfb.closed()) {
            System.out.println("RFB thread finished");
            return;
        }
        System.out.println(s);
        ex.printStackTrace();
        if (this.rfb != null) {
            this.rfb.close();
        }
        if (this.inAnApplet) {
            this.showMessage(s);
        }
        else {
            System.exit(1);
        }
    }
    
    void showMessage(final String s) {
        this.vncContainer.removeAll();
        final Label label = new Label(s, 1);
        label.setFont(new Font("Helvetica", 0, 12));
        if (this.offerRelogin) {
            final Panel panel = new Panel(new GridLayout(0, 1));
            final Panel panel2 = new Panel(new FlowLayout(0));
            panel2.add(panel);
            this.vncContainer.setLayout(new FlowLayout(0, 30, 16));
            this.vncContainer.add(panel2);
            final Panel panel3 = new Panel(new FlowLayout(1));
            panel3.add(label);
            panel.add(panel3);
            panel.add(new ReloginPanel(this));
        }
        else {
            this.vncContainer.setLayout(new FlowLayout(0, 30, 30));
            this.vncContainer.add(label);
        }
        if (this.inSeparateFrame) {
            this.vncFrame.pack();
        }
        else {
            this.validate();
        }
    }
    
    public void stop() {
        System.out.println("Stopping applet");
        this.rfbThread = null;
    }
    
    public void destroy() {
        System.out.println("Destroying applet");
        this.vncContainer.removeAll();
        this.options.dispose();
        this.clipboard.dispose();
        if (this.rec != null) {
            this.rec.dispose();
        }
        if (this.rfb != null && !this.rfb.closed()) {
            this.rfb.close();
        }
        if (this.inSeparateFrame) {
            this.vncFrame.dispose();
        }
    }
    
    public void enableInput(final boolean b) {
        this.vc.enableInput(b);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        System.out.println("Closing window");
        if (this.rfb != null) {
            this.disconnect();
        }
        this.vncContainer.hide();
        if (!this.inAnApplet) {
            System.exit(0);
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
}

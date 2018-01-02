import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Choice;
import java.io.IOException;
import java.io.EOFException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.net.NoRouteToHostException;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.Container;
import java.awt.Frame;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedBoolean;
import java.awt.event.WindowListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class VncViewer extends Applet implements Runnable, WindowListener
{
    private static final long serialVersionUID = 3394782728011210534L;
    boolean inAnApplet;
    boolean inSeparateFrame;
    String[] mainArgs;
    RfbProto rfb;
    Thread rfbThread;
    final SynchronizedBoolean rfbThreadRunning;
    Frame vncFrame;
    Container vncContainer;
    ScrollPane desktopScrollPane;
    GridBagLayout gridbag;
    ButtonPanel buttonPanel;
    AuthPanel authenticator;
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
    String encPasswordParam;
    boolean showControls;
    boolean offerRelogin;
    boolean showOfflineDesktop;
    int deferScreenUpdates;
    int deferCursorUpdates;
    int deferUpdateRequests;
    private Vector m_listeners;
    
    public VncViewer() {
        this.inAnApplet = true;
        this.inSeparateFrame = false;
        this.rfbThreadRunning = new SynchronizedBoolean(false);
        this.m_listeners = new Vector();
    }
    
    public static void main(final String[] argv) {
        final VncViewer v = new VncViewer();
        v.mainArgs = argv;
        v.inAnApplet = false;
        v.inSeparateFrame = true;
        v.init();
        v.start();
    }
    
    public void init() {
        this.checkMsSecurity();
        this.readParameters();
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
        this.initConnectionItems();
        (this.rfbThread = new Thread(null, this, "VncViewer.rfbThread")).start();
    }
    
    public void initConnectionItems() {
        this.recordingSync = new Object();
        this.options = new OptionsFrame(this);
        this.clipboard = new ClipboardFrame(this);
        this.authenticator = new AuthPanel();
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
    }
    
    public void update(final Graphics g) {
    }
    
    public void run() {
        this.rfbThreadRunning.set(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 0;
        gbc.anchor = 18;
        this.gridbag = new GridBagLayout();
        this.vncContainer.setLayout(this.gridbag);
        while (this.rfbThreadRunning.get()) {
            try {
                this.connectAndAuthenticate();
                this.doProtocolInitialisation();
                final int cCount = this.vncContainer.getComponentCount();
                System.out.println("Component count = " + cCount + "\n");
                final Component[] ca = this.vncContainer.getComponents();
                for (int i = 0; i < ca.length; ++i) {
                    System.out.println("Compentent: " + ca[i].getName());
                }
                if (this.vc != null) {
                    this.vncContainer.removeAll();
                    gbc = new GridBagConstraints();
                    gbc.gridwidth = 0;
                    gbc.anchor = 18;
                    this.gridbag = new GridBagLayout();
                    this.vncContainer.setLayout(this.gridbag);
                }
                if (this.buttonPanel != null) {
                    this.vncContainer.remove(this.buttonPanel);
                }
                this.buttonPanel = new ButtonPanel(this);
                if (this.showControls) {
                    this.gridbag.setConstraints(this.buttonPanel, gbc);
                    this.vncContainer.add(this.buttonPanel);
                }
                this.vc = null;
                this.vc = new VncCanvas(this);
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                this.gridbag.setConstraints(this.vc, gbc);
                this.add(this.vc);
                this.validate();
                if (this.showControls) {
                    this.buttonPanel.enableButtons();
                }
                this.moveFocusToDesktop();
                this.vc.processNormalProtocol();
            }
            catch (NoRouteToHostException e) {
                this.fatalError("Network error: no route to server: " + this.host, e);
            }
            catch (UnknownHostException e2) {
                this.fatalError("Network error: server name unknown: " + this.host, e2);
            }
            catch (ConnectException e3) {
                this.fatalError("Network error: could not connect to server: " + this.host + ":" + this.port, e3);
            }
            catch (EOFException e4) {
                if (this.showOfflineDesktop) {
                    e4.printStackTrace();
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
                    this.fatalError("Network error: remote side closed connection", e4);
                }
            }
            catch (IOException e5) {
                final String str = e5.getMessage();
                if (str != null && str.length() != 0) {
                    this.fatalError("Network Error: " + str, e5);
                }
                else {
                    this.fatalError(e5.toString(), e5);
                }
            }
            catch (Exception e6) {
                final String str = e6.getMessage();
                if (str != null && str.length() != 0) {
                    this.fatalError("Error: " + str, e6);
                }
                else {
                    this.fatalError(e6.toString(), e6);
                }
            }
            finally {
                if (Thread.interrupted()) {
                    this.rfbThreadRunning.set(false);
                }
                else {
                    System.out.println("Lost Connection... Trying to reconnect: " + this.host + ":" + this.port + "\n");
                    try {
                        Thread.sleep(5000L);
                        if (this.vc != null) {
                            this.vc.enableInput(false);
                        }
                        if (this.rfb != null && !this.rfb.closed()) {
                            this.rfb.close();
                        }
                        this.rfb = null;
                    }
                    catch (InterruptedException e7) {
                        System.out.println("rfbThread interrupted... flagging for termination");
                        e7.printStackTrace();
                        this.rfbThreadRunning.set(false);
                    }
                }
            }
            break;
        }
        System.out.println("rfbThreadRunning is false. Exiting reconnection loop.\n");
    }
    
    void connectAndAuthenticate() throws Exception {
        if (this.encPasswordParam != null) {
            final byte[] pw = { 0, 0, 0, 0, 0, 0, 0, 0 };
            int len = this.encPasswordParam.length() / 2;
            if (len > 8) {
                len = 8;
            }
            for (int i = 0; i < len; ++i) {
                final String hex = this.encPasswordParam.substring(i * 2, i * 2 + 2);
                final Integer x = new Integer(Integer.parseInt(hex, 16));
                pw[i] = (byte)(Object)x;
            }
            final byte[] key = { 23, 82, 107, 6, 35, 78, 88, 7 };
            final DesCipher des = new DesCipher(key);
            des.decrypt(pw, 0, pw, 0);
            this.passwordParam = new String(pw);
        }
        if (this.passwordParam == null) {
            final GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = 0;
            gbc.anchor = 18;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.ipadx = 100;
            gbc.ipady = 50;
            this.gridbag.setConstraints(this.authenticator, gbc);
            this.vncContainer.add(this.authenticator);
            if (this.inSeparateFrame) {
                this.vncFrame.pack();
                this.vncFrame.setVisible(true);
            }
            else {
                this.validate();
                this.authenticator.moveFocusToPasswordField();
            }
            while (true) {
                synchronized (this.authenticator) {
                    try {
                        this.authenticator.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                if (this.tryAuthenticate(this.authenticator.password.getText())) {
                    break;
                }
                this.authenticator.retry();
            }
            this.vncContainer.remove(this.authenticator);
            return;
        }
        if (this.inSeparateFrame) {
            this.vncFrame.pack();
            this.vncFrame.setVisible(true);
        }
        else {
            this.validate();
        }
        if (!this.tryAuthenticate(this.passwordParam)) {
            throw new Exception("VNC authentication failed");
        }
    }
    
    boolean tryAuthenticate(String pw) throws Exception {
        (this.rfb = new RfbProto(this.host, this.port, this)).readVersionMsg();
        System.out.println("RFB server supports protocol version " + this.rfb.serverMajor + "." + this.rfb.serverMinor);
        this.rfb.writeVersionMsg();
        final int authScheme = this.rfb.readAuthScheme();
        switch (authScheme) {
            case 1: {
                System.out.println("No authentication needed");
                return true;
            }
            case 2: {
                final byte[] challenge = new byte[16];
                this.rfb.is.readFully(challenge);
                if (pw.length() > 8) {
                    pw = pw.substring(0, 8);
                }
                final int firstZero = pw.indexOf(0);
                if (firstZero != -1) {
                    pw = pw.substring(0, firstZero);
                }
                final byte[] key = { 0, 0, 0, 0, 0, 0, 0, 0 };
                System.arraycopy(pw.getBytes(), 0, key, 0, pw.length());
                final DesCipher des = new DesCipher(key);
                des.encrypt(challenge, 0, challenge, 0);
                des.encrypt(challenge, 8, challenge, 8);
                this.rfb.write(challenge, 0, challenge.length);
                final int authResult = this.rfb.is.readInt();
                switch (authResult) {
                    case 0: {
                        System.out.println("VNC authentication succeeded");
                        return true;
                    }
                    case 1: {
                        System.out.println("VNC authentication failed");
                        return false;
                    }
                    case 2: {
                        throw new Exception("VNC authentication failed - too many tries");
                    }
                    default: {
                        throw new Exception("Unknown VNC authentication result " + authResult);
                    }
                }
                break;
            }
            default: {
                throw new Exception("Unknown VNC authentication scheme " + authScheme);
            }
        }
    }
    
    void doProtocolInitialisation() throws IOException {
        this.rfb.writeClientInit();
        this.rfb.readServerInit();
        System.out.println("Desktop name is " + this.rfb.desktopName);
        System.out.println("Desktop size is " + this.rfb.framebufferWidth + " x " + this.rfb.framebufferHeight);
        this.setEncodings();
    }
    
    void setEncodings() {
        try {
            if (this.rfb != null && this.rfb.inNormalProtocol) {
                this.rfb.writeSetEncodings(this.options.encodings, this.options.nEncodings);
                if (this.vc != null) {
                    this.vc.softCursorFree();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void setCutText(final String text) {
        try {
            if (this.rfb != null && this.rfb.inNormalProtocol) {
                this.rfb.writeClientCutText(text);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void setRecordingStatus(final String fname) {
        synchronized (this.recordingSync) {
            this.sessionFileName = fname;
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
    
    public void readParameters() {
        this.host = this.readParameter("HOST", !this.inAnApplet);
        if (this.host == null) {
            this.host = this.getCodeBase().getHost();
            if (this.host.equals("")) {
                this.fatalError("HOST parameter not specified");
            }
        }
        String str = this.readParameter("PORT", true);
        this.port = Integer.parseInt(str);
        if (this.inAnApplet) {
            str = this.readParameter("Open New Window", false);
            if (str != null && str.equalsIgnoreCase("Yes")) {
                this.inSeparateFrame = true;
            }
        }
        this.encPasswordParam = this.readParameter("ENCPASSWORD", false);
        if (this.encPasswordParam == null) {
            this.passwordParam = this.readParameter("PASSWORD", false);
        }
        this.showControls = true;
        str = this.readParameter("Show Controls", false);
        if (str != null && str.equalsIgnoreCase("No")) {
            this.showControls = false;
        }
        this.offerRelogin = true;
        str = this.readParameter("Offer Relogin", false);
        if (str != null && str.equalsIgnoreCase("No")) {
            this.offerRelogin = false;
        }
        this.showOfflineDesktop = false;
        str = this.readParameter("Show Offline Desktop", false);
        if (str != null && str.equalsIgnoreCase("Yes")) {
            this.showOfflineDesktop = true;
        }
        this.deferScreenUpdates = this.readIntParameter("Defer screen updates", 20);
        this.deferCursorUpdates = this.readIntParameter("Defer cursor updates", 10);
        this.deferUpdateRequests = this.readIntParameter("Defer update requests", 50);
        this.socketFactory = this.readParameter("SocketFactory", false);
    }
    
    public String readParameter(final String name, final boolean required) {
        if (this.inAnApplet) {
            final String s = this.getParameter(name);
            if (s == null && required) {
                this.fatalError(name + " parameter not specified");
            }
            return s;
        }
        for (int i = 0; i < this.mainArgs.length; i += 2) {
            if (this.mainArgs[i].equalsIgnoreCase(name)) {
                try {
                    return this.mainArgs[i + 1];
                }
                catch (Exception e) {
                    if (required) {
                        this.fatalError(name + " parameter not specified");
                    }
                    return null;
                }
            }
        }
        if (required) {
            this.fatalError(name + " parameter not specified");
        }
        return null;
    }
    
    int readIntParameter(final String name, final int defaultValue) {
        final String str = this.readParameter(name, false);
        int result = defaultValue;
        if (str != null) {
            try {
                result = Integer.parseInt(str);
            }
            catch (NumberFormatException ex) {}
        }
        return result;
    }
    
    void moveFocusToDesktop() {
        if (this.vncContainer != null) {
            if (this.vc != null && this.vncContainer.isAncestorOf(this.vc)) {
                this.vc.requestFocus();
            }
            else if (this.vncContainer.isAncestorOf(this.authenticator)) {
                this.authenticator.moveFocusToPasswordField();
            }
        }
    }
    
    public synchronized void disconnect() {
        System.out.println("Disconnect");
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
    
    public synchronized void fatalError(final String str) {
        this.fatalError(str, null);
    }
    
    public synchronized void fatalError(final String str, final Exception e) {
        if (this.rfb != null) {
            if (this.rfb.closed()) {
                System.out.println("RFB thread finished");
                return;
            }
            this.rfb.close();
        }
        System.out.println(str);
        if (e != null) {
            e.printStackTrace();
        }
        if (this.inAnApplet) {}
    }
    
    void showMessage(final String msg) {
        this.vncContainer.removeAll();
        final Label errLabel = new Label(msg, 1);
        errLabel.setFont(new Font("Helvetica", 0, 12));
        if (this.offerRelogin) {
            final Panel gridPanel = new Panel(new GridLayout(0, 1));
            final Panel outerPanel = new Panel(new FlowLayout(0));
            outerPanel.add(gridPanel);
            this.vncContainer.setLayout(new FlowLayout(0, 30, 16));
            this.vncContainer.add(outerPanel);
            final Panel textPanel = new Panel(new FlowLayout(1));
            textPanel.add(errLabel);
            gridPanel.add(textPanel);
            gridPanel.add(new ReloginPanel(this));
        }
        else {
            this.vncContainer.setLayout(new FlowLayout(0, 30, 30));
            this.vncContainer.add(errLabel);
        }
        if (this.inSeparateFrame) {
            this.vncFrame.pack();
        }
        else {
            this.validate();
        }
    }
    
    public void destroy() {
        this.checkMsSecurity();
        System.out.println("Destroying applet");
        this.rfbThreadRunning.set(false);
        if (this.rfbThread != null) {
            try {
                this.rfbThread.interrupt();
            }
            catch (Throwable t) {}
        }
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
    
    public void windowClosing(final WindowEvent evt) {
        System.out.println("Closing window");
        this.rfbThreadRunning.set(false);
        if (this.rfbThread != null) {
            try {
                this.rfbThread.interrupt();
            }
            catch (Throwable t) {}
        }
        if (this.rfb != null) {
            this.disconnect();
        }
        this.vncFrame.dispose();
        if (!this.inAnApplet) {
            System.exit(0);
        }
    }
    
    public void windowActivated(final WindowEvent evt) {
        if (this.vncFrame.isAncestorOf(this.authenticator)) {
            this.authenticator.moveFocusToPasswordField();
        }
    }
    
    public void windowDeactivated(final WindowEvent evt) {
    }
    
    public void windowOpened(final WindowEvent evt) {
    }
    
    public void windowClosed(final WindowEvent evt) {
    }
    
    public void windowIconified(final WindowEvent evt) {
    }
    
    public void windowDeiconified(final WindowEvent evt) {
    }
    
    public void handleDisconnect() {
        try {
            this.moveFocusToDesktop();
            this.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error in event handler");
        }
    }
    
    public void handleOptions() {
        try {
            this.moveFocusToDesktop();
            this.options.setVisible(!this.options.isVisible());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error in event handler");
        }
    }
    
    public void handleClipboard() {
        try {
            this.moveFocusToDesktop();
            this.clipboard.setVisible(!this.clipboard.isVisible());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error in event handler");
        }
    }
    
    public void handleRecord() {
        try {
            this.moveFocusToDesktop();
            this.rec.setVisible(!this.rec.isVisible());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error in event handler");
        }
    }
    
    public void handleSendControlAltDel() {
        try {
            this.moveFocusToDesktop();
            System.err.println("sending control-alt-delete event");
            System.err.flush();
            final int modifiers = 10;
            final KeyEvent ctrlAltDelEvent = new KeyEvent(this, 401, 0L, 10, 127, '\u007f');
            this.vc.processLocalKeyEvent(ctrlAltDelEvent);
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(500L);
                        final KeyEvent ctrlAltDelEvent = new KeyEvent(VncViewer.this, 402, 0L, 10, 127, '\u007f');
                        VncViewer.this.vc.processLocalKeyEvent(ctrlAltDelEvent);
                    }
                    catch (InterruptedException ex) {
                        VncViewer.this.fatalError("failed to send control-alt-del, interrupted, aborting....", ex);
                    }
                }
            }.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("error in event handler");
        }
    }
    
    public void handleRefresh() {
        try {
            this.moveFocusToDesktop();
            try {
                this.rfb.writeFramebufferUpdateRequest(0, 0, this.rfb.framebufferWidth, this.rfb.framebufferHeight, false);
            }
            catch (IOException e) {
                this.fatalError("refresh request failed", e);
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            System.err.println("error in event handler");
        }
    }
    
    private void checkMsSecurity() {
        final String vendor = System.getProperty("java.vendor");
        if (!"Sun Microsystems Inc.".equals(vendor)) {
            try {
                final Class pe = Class.forName("com.ms.security.PolicyEngine");
                if (pe != null) {
                    final Class permid = Class.forName("com.ms.security.PermissionID");
                    final String[] names = { "SYSTEM", "NETIO", "PROPERTY", "THREAD", "FILEIO", "USERFILEIO" };
                    for (int i = 0; i < names.length; ++i) {
                        final Object perm = permid.getField(names[i]).get(null);
                        pe.getMethod("assertPermission", permid).invoke(null, perm);
                    }
                    System.err.println("SUCCESSfully asserted MS JVM permissions");
                    System.err.flush();
                }
            }
            catch (Throwable e) {
                System.err.println("Microsoft JVM permissions not asserted.");
                e.printStackTrace(System.err);
                System.err.flush();
            }
        }
    }
    
    public void start() {
        this.checkMsSecurity();
        super.start();
    }
    
    public void stop() {
        this.checkMsSecurity();
        super.stop();
    }
    
    public void addResizeListener(final ResizeListener listener) {
        this.m_listeners.addElement(listener);
    }
    
    public void removeResizeListener(final ResizeListener listener) {
        this.m_listeners.removeElement(listener);
    }
    
    void fireResizeEvent(final int width, final int height) {
        final ResizeListener[] listener;
        synchronized (this.m_listeners) {
            listener = new ResizeListener[this.m_listeners.size()];
            this.m_listeners.copyInto(listener);
        }
        for (int i = 0; i < listener.length; ++i) {
            listener[i].DoResize(width, height);
        }
    }
}

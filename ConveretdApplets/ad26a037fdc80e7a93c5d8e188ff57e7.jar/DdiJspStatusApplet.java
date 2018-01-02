import netscape.javascript.JSObject;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ActionEvent;
import com.mobius.internet.InternetUtility;
import com.mobius.net.UrlEncodedBuf;
import com.mobius.net.StringBasedTransaction;
import com.mobius.net.ServerConnection;
import com.mobius.net.FollowTxHeader;
import com.mobius.net.ServerCommunicationException;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.awt.event.ContainerListener;
import com.mobius.applet.MobiusAppletEventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.mobius.applet.AppletLogging;
import java.util.Properties;
import com.mobius.threading.EdtRunner;
import com.mobius.applet.AppletParams;
import com.mobius.awt.DdiLabel;
import com.mobius.awt.DdiImageViewer;
import com.mobius.awt.DdiImageButton;
import com.mobius.awt.DdiAboutDialog;
import java.awt.Frame;
import com.mobius.applet.INeedLoggingFlags;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DdiJspStatusApplet extends Applet implements INeedLoggingFlags
{
    private static final long serialVersionUID = -3348642182428450186L;
    private boolean isIE;
    protected String serverURL;
    protected static final int DEFAULT_POLL_INTERVAL = 10;
    protected int pollInterval;
    private static final boolean DEFAULT_SHOW_ABOUT = true;
    private static final boolean DEFAULT_LOAD_EVENT_QUEUE = false;
    private boolean showAbout;
    private boolean loadEventQueue;
    protected String aboutTitle;
    protected String aboutWait;
    protected String okButtonLabel;
    protected String staticInfoMessage;
    protected String paramName_type;
    protected String typeValue_status;
    protected String typeValue_about;
    protected String typeValue_stop;
    protected Frame aboutFrame;
    protected DdiAboutDialog aboutDialog;
    protected transient StatusThread statusThread;
    private DdiImageButton stopButton;
    private DdiImageViewer statusImage;
    private DdiLabel statusLabel;
    private DdiImageButton aboutButton;
    private transient AppletParams params;
    private boolean useEDT;
    private String requestName;
    private boolean canStop;
    
    public DdiJspStatusApplet() {
        this.pollInterval = 10;
        this.showAbout = true;
        this.loadEventQueue = false;
        this.aboutFrame = new Frame();
        this.aboutDialog = null;
        this.statusThread = null;
        this.stopButton = new DdiImageButton();
        this.statusImage = new DdiImageViewer();
        this.statusLabel = new DdiLabel();
        this.aboutButton = new DdiImageButton();
        this.useEDT = false;
        this.params = new AppletParams(this);
        this.useEDT = EdtRunner.isAbleToUseEDT();
    }
    
    public void setLoggingFlags(final Properties properties) {
        AppletLogging.loadTracingPreferences(properties);
        AppletLogging.logger.SetDateFormatter(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        AppletLogging.logger.EnableTypeID(true);
    }
    
    public synchronized void init() {
        this.getParams();
        try {
            final String parameter = this.getParameter("isIE");
            boolean booleanValue = false;
            if (parameter != null) {
                booleanValue = Boolean.valueOf(parameter);
            }
            new MobiusAppletEventQueue(this, booleanValue);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            try {
                this.addContainerListener(new StatusAppletContainerAdapter());
                this.addKeyListener(new StatusAppletKeyAdapter());
            }
            catch (Exception ex) {
                this.handleException(ex);
            }
        }
        catch (Exception ex2) {
            this.handleException(ex2);
        }
        if (this.useEDT) {
            EdtRunner.runMethodOnEDTandWait(this, "edtInit");
        }
        else {
            this.stdInit();
        }
    }
    
    public void edtInit() {
        this.stdInit();
    }
    
    private void stdInit() {
        try {
            this.setLayout(null);
            this.setBackground(Color.lightGray);
            this.setSize(600, 34);
        }
        catch (Exception ex) {
            this.handleException(ex);
        }
        try {
            this.add(this.stopButton);
            this.stopButton.setBounds(5, 3, 28, 28);
            this.stopButton.setButtonColor(Color.lightGray);
            this.stopButton.setEnabled(false);
        }
        catch (Exception ex2) {
            this.handleException(ex2);
        }
        try {
            this.add(this.statusImage);
            this.statusImage.setBounds(36, 4, 45, 26);
            this.statusImage.setStyle(1);
        }
        catch (Exception ex3) {
            this.handleException(ex3);
        }
        try {
            this.add(this.statusLabel);
            this.statusLabel.setBounds(85, 4, 480, 26);
        }
        catch (Exception ex4) {
            this.handleException(ex4);
        }
        try {
            this.add(this.aboutButton);
            this.aboutButton.setBounds(570, 3, 28, 28);
            this.aboutButton.setButtonColor(Color.lightGray);
            this.aboutButton.setEnabled(this.showAbout);
        }
        catch (Exception ex5) {
            this.handleException(ex5);
        }
        this.stopButton.setEnabledImage("stop.gif", true);
        this.statusImage.setImage("bloading.gif");
        this.aboutButton.setEnabledImage("gbulbon.gif", true);
        try {
            final ButtonAction buttonAction = new ButtonAction();
            this.stopButton.addActionListener(buttonAction);
            this.aboutButton.addActionListener(buttonAction);
            this.setVisible(true);
            this.requestFocus();
        }
        catch (Exception ex6) {
            this.handleException(ex6);
        }
    }
    
    public synchronized void start() {
        final Applet applet = this.getAppletContext().getApplet("TreeView");
        if (applet != null) {
            final Class<? extends Applet> class1 = applet.getClass();
            try {
                this.setLoggingFlags((Properties)class1.getMethod("getLoggingFlags", (Class[])null).invoke(applet, (Object[])null));
            }
            catch (Exception ex) {
                System.out.println("Can't get getLoggingFlags method.");
            }
        }
        AppletLogging.logger.Write(AppletLogging.flowtrace, this, "***Start Status Applet***");
    }
    
    public synchronized void destroy() {
        AppletLogging.logger.Write(AppletLogging.flowtrace, this, "***Destroy Status Applet***");
        this.serverURL = null;
        this.aboutTitle = null;
        this.aboutWait = null;
        this.okButtonLabel = null;
        this.staticInfoMessage = null;
        this.paramName_type = null;
        this.typeValue_status = null;
        this.typeValue_stop = null;
        this.typeValue_about = null;
        this.aboutFrame = null;
        this.aboutDialog = null;
        this.statusThread = null;
        this.stopButton = null;
        this.statusImage = null;
        this.statusLabel = null;
        this.aboutButton = null;
        final EventQueue systemEventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        if (null != systemEventQueue && systemEventQueue instanceof MobiusAppletEventQueue) {
            ((MobiusAppletEventQueue)systemEventQueue).pop();
        }
    }
    
    public synchronized void stop() {
        AppletLogging.logger.Write(AppletLogging.flowtrace, this, "***Stop Status Applet***");
        if (this.statusThread != null) {
            this.statusThread.kill();
            this.statusThread = null;
        }
        this.statusImage.flushImage();
    }
    
    public synchronized void setStaticInfoMessage(final String staticInfoMessage) {
        this.staticInfoMessage = staticInfoMessage;
        if (this.useEDT) {
            this.showMessage("Using the EDT for setStaticInfoMessage");
            EdtRunner.runMethodOnEDT(this, "edtSetStaticInfoMessage");
        }
        else {
            this.stdSetStaticInfoMessage();
        }
    }
    
    public void edtSetStaticInfoMessage() {
        this.stdSetStaticInfoMessage();
    }
    
    private void stdSetStaticInfoMessage() {
        this.statusLabel.setText(this.staticInfoMessage);
    }
    
    public synchronized void displayStatus(final String requestName, final boolean canStop) {
        this.requestName = requestName;
        this.canStop = canStop;
        if (this.useEDT) {
            this.showMessage("Using the EDT for displayStatus");
            EdtRunner.runMethodOnEDTandWait(this, "edtDisplayStatus");
        }
        else {
            this.stdDisplayStatus();
        }
    }
    
    public void edtDisplayStatus() {
        this.stdDisplayStatus();
    }
    
    private void stdDisplayStatus() {
        try {
            this.staticInfoMessage = null;
            this.statusImage.setAnimated(true);
            this.statusLabel.setText(this.requestName);
            if (this.canStop) {
                this.stopButton.setEnabled(true);
            }
            if (this.statusThread == null || this.statusThread.isKilled()) {
                this.statusThread = new StatusThread();
            }
            this.statusThread.newRequest();
        }
        catch (Exception ex) {
            this.handleException(ex);
        }
    }
    
    public synchronized void hideStatus() {
        if (this.useEDT) {
            this.showMessage("Using the EDT for hideStatus");
            EdtRunner.runMethodOnEDT(this, "edtHideStatus");
        }
        else {
            this.stdHideStatus();
        }
    }
    
    public void edtHideStatus() {
        this.stdHideStatus();
    }
    
    private void stdHideStatus() {
        try {
            this.stopButton.setEnabled(false);
            this.statusImage.setAnimated(false);
            if (this.staticInfoMessage == null) {
                this.statusLabel.setText("");
            }
            else {
                this.statusLabel.setText(this.staticInfoMessage);
            }
            if (this.statusThread != null) {
                this.statusThread.cancelRequest();
            }
        }
        catch (Exception ex) {
            this.handleException(ex);
        }
    }
    
    private void postCancelRequest() {
        try {
            this.showMessage(this.sendMessage(this.typeValue_stop));
        }
        catch (ServerCommunicationException ex) {
            this.handleException(ex);
        }
    }
    
    private void ShowAboutDialog() {
        if (this.aboutDialog == null) {
            this.aboutDialog = new DdiAboutDialog(this.aboutFrame, this.aboutTitle, this.okButtonLabel);
        }
        if (this.aboutDialog.isVisible()) {
            this.aboutDialog.setVisible(false);
        }
        this.aboutDialog.setText(this.aboutWait);
        this.aboutDialog.setVisible(true);
        final String aboutText = this.getAboutText();
        if (aboutText != null) {
            this.aboutDialog.setText(aboutText);
        }
    }
    
    private String getAboutText() {
        String sendMessage = "";
        try {
            sendMessage = this.sendMessage(this.typeValue_about);
        }
        catch (ServerCommunicationException ex) {
            this.handleException(ex);
        }
        return sendMessage;
    }
    
    private void handleException(final Exception ex) {
        AppletLogging.logger.Write(AppletLogging.defecttrace, this, "Exception: " + ex.toString());
        AppletLogging.logger.Write(AppletLogging.defecttrace, this, ex);
    }
    
    private void showMessage(final String s) {
        AppletLogging.logger.Write(AppletLogging.defecttrace, this, s);
    }
    
    private void getParams() {
        this.isIE = this.params.getBoolean("isIE", false);
        this.serverURL = this.params.getString("serverURL");
        this.paramName_type = this.params.getString("paramName_type");
        this.typeValue_stop = this.params.getString("typeValue_stop");
        this.typeValue_status = this.params.getString("typeValue_status");
        this.typeValue_about = this.params.getString("typeValue_about");
        this.pollInterval = this.params.getInt("pollInterval", 10);
        this.showAbout = (this.params.getInt("showAbout", 1) == 1);
        this.loadEventQueue = this.params.getBoolean("loadEventQueue", false);
        this.aboutTitle = this.params.getString("aboutTitle");
        this.aboutWait = this.params.getString("aboutWait");
        this.okButtonLabel = this.params.getString("aboutOK");
        FollowTxHeader.setAddFollowTx(this.params.getBoolean("addFollowTx", false));
    }
    
    private String sendMessage(final String s) throws ServerCommunicationException {
        final StringBasedTransaction stringBasedTransaction = new StringBasedTransaction(new ServerConnection(this.serverURL, "application/x-www-form-urlencoded"));
        final UrlEncodedBuf urlEncodedBuf = new UrlEncodedBuf();
        urlEncodedBuf.addPair(this.paramName_type, s);
        final String string = urlEncodedBuf.toString();
        AppletLogging.logger.Write(AppletLogging.commtrace, this, "Sending:", string);
        final String sendAndReceive = stringBasedTransaction.sendAndReceive(string);
        AppletLogging.logger.Write(AppletLogging.commtrace, this, "Received:", sendAndReceive);
        return InternetUtility.CGIUnEscapeString(sendAndReceive);
    }
    
    class ButtonAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == DdiJspStatusApplet.this.stopButton) {
                DdiJspStatusApplet.this.hideStatus();
                DdiJspStatusApplet.this.postCancelRequest();
            }
            else if (source == DdiJspStatusApplet.this.aboutButton) {
                DdiJspStatusApplet.this.ShowAboutDialog();
            }
        }
    }
    
    final class StatusThread extends Thread
    {
        protected int state;
        protected boolean canSuspend;
        private static final int STATE_KILLED = -1;
        private static final int STATE_CREATED = 0;
        private static final int STATE_ACTIVE = 1;
        private static final int STATE_RESET = 2;
        private static final int STATE_SUSPENDED = 3;
        
        StatusThread() {
            this.state = 0;
            this.canSuspend = false;
            try {
                final String property = System.getProperty("browser");
                if (property == null) {
                    return;
                }
                if (property.indexOf("ActiveX") != -1) {
                    this.canSuspend = true;
                }
                else if (property.indexOf("Netscape") != -1) {
                    this.canSuspend = false;
                }
            }
            catch (SecurityException ex2) {}
            catch (Exception ex) {
                DdiJspStatusApplet.this.handleException(ex);
            }
        }
        
        public synchronized void newRequest() {
            try {
                switch (this.state) {
                    case 0: {
                        this.state = 1;
                        this.start();
                        try {
                            this.wait();
                        }
                        catch (InterruptedException ex2) {}
                    }
                    case 1: {
                        this.state = 2;
                        this.notifyAll();
                        try {
                            this.wait();
                        }
                        catch (InterruptedException ex3) {}
                        break;
                    }
                    case 3: {
                        this.state = 2;
                        if (this.canSuspend) {
                            this.resume();
                        }
                        this.notifyAll();
                        try {
                            this.wait();
                        }
                        catch (InterruptedException ex4) {}
                        break;
                    }
                }
            }
            catch (Exception ex) {
                DdiJspStatusApplet.this.handleException(ex);
            }
        }
        
        public synchronized void cancelRequest() {
            try {
                switch (this.state) {
                    case -1:
                    case 0:
                    case 3: {}
                    case 1:
                    case 2: {
                        this.state = 3;
                        this.notifyAll();
                        try {
                            this.wait();
                        }
                        catch (InterruptedException ex2) {}
                        if (this.canSuspend) {
                            this.suspend();
                            break;
                        }
                        break;
                    }
                }
            }
            catch (Exception ex) {
                DdiJspStatusApplet.this.handleException(ex);
            }
        }
        
        public synchronized void kill() {
            try {
                if (this.isAlive()) {
                    switch (this.state) {
                        case -1:
                        case 0: {}
                        case 1:
                        case 2: {
                            this.state = -1;
                            this.notifyAll();
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex2) {}
                            break;
                        }
                        case 3: {
                            this.state = -1;
                            if (this.canSuspend) {
                                this.resume();
                            }
                            this.notifyAll();
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex3) {}
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) {
                DdiJspStatusApplet.this.handleException(ex);
            }
        }
        
        public synchronized boolean isKilled() {
            return !this.isAlive() || this.state == -1;
        }
        
        public void run() {
            int n = 1;
        Label_0005:
            while (true) {
                break Label_0005;
                while (true) {
                    try {
                        while (true) {
                            synchronized (this) {
                                if (n != 0) {
                                    this.notifyAll();
                                    try {
                                        this.wait(DdiJspStatusApplet.this.pollInterval * 1000);
                                    }
                                    catch (InterruptedException ex2) {}
                                }
                                n = 1;
                                switch (this.state) {
                                    case 2: {
                                        this.state = 1;
                                        continue;
                                    }
                                    case 3: {
                                        continue;
                                    }
                                    case -1: {
                                        this.notifyAll();
                                        return;
                                    }
                                }
                            }
                            AppletLogging.logger.Write(AppletLogging.commtrace, this, "Sending:", new String(DdiJspStatusApplet.this.paramName_type + "=" + DdiJspStatusApplet.this.typeValue_status));
                            final String access$700 = DdiJspStatusApplet.this.sendMessage(DdiJspStatusApplet.this.typeValue_status);
                            AppletLogging.logger.Write(AppletLogging.commtrace, this, "Received:", access$700);
                            synchronized (this) {
                                if (this.state != 1) {
                                    n = 0;
                                }
                                else {
                                    if (access$700 == null || access$700.length() == 0) {
                                        continue;
                                    }
                                    DdiJspStatusApplet.this.statusLabel.setText(access$700);
                                }
                            }
                        }
                    }
                    catch (ServerCommunicationException ex) {
                        DdiJspStatusApplet.this.handleException(ex);
                        continue;
                    }
                    continue Label_0005;
                }
                break;
            }
        }
    }
    
    private class StatusAppletContainerAdapter extends ContainerAdapter
    {
        public void componentAdded(final ContainerEvent containerEvent) {
            containerEvent.getChild().addKeyListener(new StatusAppletKeyAdapter());
            if (containerEvent.getChild() instanceof Container) {
                ((Container)containerEvent.getChild()).addContainerListener(new StatusAppletContainerAdapter());
            }
            super.componentAdded(containerEvent);
        }
    }
    
    private class StatusAppletKeyAdapter extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            if ((keyEvent.isControlDown() || keyEvent.isAltDown()) && keyEvent.getKeyCode() != 17 && keyEvent.getKeyCode() != 16 && keyEvent.getKeyCode() != 18) {
                new JavaScriptKeyPressedRunner(keyEvent, DdiJspStatusApplet.this).start();
            }
            super.keyPressed(keyEvent);
        }
    }
    
    private class JavaScriptKeyPressedRunner extends Thread
    {
        KeyEvent keyEvent;
        private JSObject win;
        
        JavaScriptKeyPressedRunner(final KeyEvent keyEvent, final Applet applet) {
            this.win = JSObject.getWindow(applet);
            this.keyEvent = keyEvent;
        }
        
        public void run() {
            try {
                this.win.call("javaKeyHandler", new Object[] { new Integer(this.keyEvent.getID() - 400), new Integer(this.keyEvent.getKeyCode()), new Integer(this.keyEvent.getKeyChar()), new Boolean(this.keyEvent.isAltDown()), new Boolean(this.keyEvent.isControlDown()), new Boolean(this.keyEvent.isShiftDown()) });
            }
            catch (Exception ex) {
                DdiJspStatusApplet.this.handleException(ex);
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Scrollbar;
import gjt.ImageButtonEvent;
import Prettyfe.PrettyLayout8;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Image;
import gjt.DualImageTextButton;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import gjt.ImageCanvas;
import Network.Demultiplexer;
import java.awt.Panel;
import java.awt.CardLayout;
import java.net.Socket;
import gjt.DialogClient;

public class MudFrame extends WPPanel implements DialogClient, TextDisplayer
{
    public static boolean connecting;
    public static boolean connected;
    public static semaphore lockstop;
    public static semaphore remotelock;
    public static final boolean d8x6 = true;
    public static final boolean d7x5 = false;
    public static final boolean d6x4 = false;
    ScrolledDisplay defaultDisplay;
    boolean history;
    static boolean mainloaded;
    private final int dispcol = 80;
    private final int disprow = 24;
    private final int dispfont = 12;
    boolean showshield;
    boolean showcompass;
    boolean playclick;
    boolean coloaded;
    boolean cotoshow;
    boolean shieldloaded;
    boolean shieldtoshow;
    boolean shieldadded;
    boolean compassadded;
    static int connections;
    Connect remote_connect;
    InputHandler inputHandler;
    public static Socket remote;
    MudBox mudbox;
    CardLayout cl;
    Panel central;
    int focat;
    Demultiplexer demultiplex;
    ColourSaver coloursaver;
    ImageCanvas Cbl;
    ImageCanvas Lbl;
    boolean playsounds;
    boolean prompt_received;
    boolean ILretain;
    boolean logshow;
    char ESC;
    String codebase;
    URL cbURL;
    MudApplet mainapplet;
    TextField tf3;
    TextField tf4;
    Button logon;
    public static Color febackground;
    public static Color feforeground;
    DualImageTextButton connect_button;
    DualImageTextButton options_button;
    DualImageTextButton log_button;
    DualImageTextButton viewlog_button;
    HealthPanel hp;
    Panel co;
    Image mainImage;
    Image sim;
    Image litButton;
    Image unlitButton;
    Image dim;
    Image slim;
    
    void lbutton() {
        if (this.mainapplet.userinput && this.tf3.getText().equals("")) {
            this.tf3.requestFocus();
            return;
        }
        if (this.mainapplet.userinput && this.tf4.getText().equals("")) {
            this.tf4.requestFocus();
            return;
        }
        this.mainapplet.user2 = this.tf3.getText();
        this.mainapplet.pass2 = this.tf4.getText();
        this.cl.show(this.central, "display");
        this.logshow = false;
        MudFrame.connecting = true;
        this.mainapplet.showStatus("");
        this.do_connect();
    }
    
    void prompted() {
        this.prompt_received = true;
        this.inputHandler.demerge();
        if (this.showshield && this.hp == null) {
            (this.hp = new HealthPanel(this, this.getImage("shield.jpg"), new ShieldLayout())).hide();
            this.add("SH", this.hp);
            this.shieldadded = true;
        }
        if (this.showcompass) {
            if (!this.compassadded) {
                this.add("CO", this.co);
                this.compassadded = true;
            }
            this.co.show();
        }
        if (this.showshield) {
            if (!this.shieldadded) {
                this.add("SH", this.hp);
                this.shieldadded = true;
            }
            this.mudbox.decoder.showfes();
        }
        this.requestFocus();
        this.repaint();
        this.mainapplet.showStatus("Playing Mud II");
    }
    
    public int getHostPort() {
        return this.mainapplet.getHostPort();
    }
    
    public void showMessage(final String s) {
        this.mainapplet.showStatus(s);
    }
    
    public void option() {
        DlgObj.showOptions(this);
    }
    
    public void nooption() {
        TDlgObj.showText(this, "You must log on to", "Mud II before you can", "alter user options.");
    }
    
    public void jbInit() throws Exception {
        (this.inputHandler = new InputHandler(this)).merge();
        this.setPaper(this.mainImage);
        this.setBackground(MudFrame.febackground);
        this.central.setLayout(this.cl);
        this.central.add("display", this.defaultDisplay);
        if (this.mainapplet.userinput) {
            final GridBagLayout gbl = new GridBagLayout();
            final GridBagConstraints gbc = new GridBagConstraints();
            final Panel passwd = new Panel();
            passwd.setLayout(gbl);
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.insets = new Insets(0, 10, 10, 10);
            gbc.anchor = 17;
            final Label l1 = new Label("Mud II user number:");
            l1.setFont(new Font("Dialog", 1, 14));
            l1.setBackground(MudFrame.febackground);
            l1.setForeground(MudFrame.feforeground);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbl.setConstraints(l1, gbc);
            passwd.add(l1);
            this.tf3 = new FocusTextField(40, this);
            gbc.gridy = 1;
            gbl.setConstraints(this.tf3, gbc);
            passwd.add(this.tf3);
            gbc.gridy = 2;
            final Label l2 = new Label("Mud II password:");
            l2.setFont(new Font("Dialog", 1, 14));
            l2.setBackground(MudFrame.febackground);
            l2.setForeground(MudFrame.feforeground);
            gbl.setConstraints(l2, gbc);
            passwd.add(l2);
            (this.tf4 = new FocusTextField(40, this)).setEchoCharacter('*');
            gbc.gridy = 3;
            gbl.setConstraints(this.tf4, gbc);
            passwd.add(this.tf4);
            this.logon.setFont(new Font("Dialog", 1, 14));
            this.logon.setBackground(MudFrame.feforeground);
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridy = 4;
            gbc.anchor = 10;
            gbl.setConstraints(this.logon, gbc);
            passwd.add(this.logon);
            this.central.add("Logon", passwd);
            this.cl.show(this.central, "Logon");
            this.logshow = true;
            this.lbutton();
        }
        else {
            this.cl.show(this.central, "display");
            this.logshow = false;
        }
        this.add("Central", this.central);
        this.defaultDisplay.scroll.setBackground(MudFrame.feforeground);
        this.defaultDisplay.scroll.setForeground(MudFrame.febackground);
        this.add("Sc", this.defaultDisplay.scroll);
        this.add("Input", this.inputHandler.getInputLine());
        this.litButton = this.getImage("button_lit.jpg");
        this.unlitButton = this.getImage("button_unlit.jpg");
        (this.options_button = new DualImageTextButton(this.unlitButton, this.litButton, "Options", this)).setNoBorder(true);
        this.add("OB", this.options_button);
        (this.connect_button = new DualImageTextButton(this.unlitButton, this.litButton, "Connect", this)).setNoBorder(true);
        this.add("CB", this.connect_button);
        (this.log_button = new DualImageTextButton(this.unlitButton, this.litButton, "Log game", this)).setNoBorder(true);
        this.add("LB", this.log_button);
        (this.viewlog_button = new DualImageTextButton(this.unlitButton, this.litButton, "View Log", this)).setNoBorder(true);
        this.add("VB", this.viewlog_button);
        DoCodes.setDefault(Color.white, MudFrame.febackground);
        this.inputHandler.getInputLine().setBackground(MudFrame.febackground);
        this.inputHandler.getInputLine().setForeground(MudFrame.feforeground);
        this.dim = this.getImage("disconnect.jpg");
        (this.Cbl = new ImageCanvas(this.dim)).hide();
        this.add("CBL", this.Cbl);
    }
    
    public void doILretain() {
        this.inputHandler.ILselecting = this.ILretain;
        this.inputHandler.aselected = this.ILretain;
    }
    
    void load() {
        if (this.coloursaver != null) {
            this.coloursaver.LoadDefault();
        }
        else {
            this.defaultDisplay.addString("You need to be logged in first.\n");
        }
    }
    
    public synchronized boolean imageUpdate(final Image image, final int infoFlags, final int x, final int y, final int width, final int height) {
        if ((infoFlags & 0x20) != 0x0) {
            if (image == this.mainImage) {
                System.out.println("Main image loaded");
                MudFrame.mainloaded = true;
                this.mainapplet.adone.setText("Main graphic loaded, almost done...");
                this.mainapplet.adone2.setText("You haven't lived, ");
                this.mainapplet.adone2.validate();
                this.mainapplet.adone3.setText("until you have died in....");
                this.mainapplet.adone3.validate();
                this.mainapplet.p1.validate();
                if (this.mainapplet.showing) {
                    this.mainapplet.validate();
                }
            }
            return false;
        }
        return true;
    }
    
    public void dialogreturned(final Frame d) {
    }
    
    void save() {
        if (this.coloursaver != null) {
            this.coloursaver.SaveColours();
        }
        else {
            this.defaultDisplay.addString("You need to be logged in first.\n");
        }
    }
    
    Image getImage(final String s) {
        this.mainapplet.showStatus("Loading graphic...." + s + "...");
        final Image im = this.mainapplet.getImage(this.cbURL, "gifs/" + s);
        this.prepareImage(im, -1, -1, this);
        this.mainapplet.glabel.setText(s);
        return im;
    }
    
    Image getCImage(final String s) {
        return this.getImage(s);
    }
    
    Image getSImage(final String s) {
        this.mainapplet.showStatus("Loading graphic...." + s + "...");
        final Image im = this.mainapplet.getImage(this.cbURL, "gifs/" + s);
        this.mainapplet.glabel.setText(s);
        return im;
    }
    
    void StopThreads() {
        System.out.println("<" + Thread.currentThread() + ">" + "Stop threads called");
        final Stopper stopper = new Stopper(this.demultiplex, this.mudbox, this.coloursaver, this);
        ((Thread)stopper).start();
    }
    
    public boolean keyDown(final Event e, final int key) {
        return !this.logshow && this.inputHandler.keyDown(e, key);
    }
    
    public void requestFocus() {
        if (this.logshow) {
            this.nextOne();
        }
        else {
            this.inputHandler.inputLine.requestFocus();
        }
    }
    
    void cbutton() {
        if (MudFrame.connecting) {
            this.defaultDisplay.addString("\n\r\n\rPlease be patient, still processing...\n\r\n\r");
            return;
        }
        if (!MudFrame.connected) {
            if (this.mainapplet.userinput) {
                if (this.logshow) {
                    this.lbutton();
                }
                else {
                    this.logshow = true;
                    this.Cbl.show();
                    this.cl.show(this.central, "Logon");
                }
            }
            else {
                MudFrame.connected = true;
                MudFrame.connecting = true;
                this.mainapplet.showStatus("");
                this.Cbl.show();
                this.do_connect();
            }
        }
        else {
            this.Cbl.hide();
            MudFrame.connecting = true;
            this.mainapplet.showStatus("");
            this.stop_connect();
        }
        this.repaint();
    }
    
    void do_connect() {
        synchronized (MudFrame.lockstop) {
            if (MudFrame.lockstop.is_set()) {
                System.out.println("Still teminating previous connection, can't re-connect until disconnection is complete!");
                this.defaultDisplay.addString("Still teminating previous connection, can't re-connect until disconnection is complete!/n");
                // monitorexit(MudFrame.lockstop)
                return;
            }
        }
        // monitorexit(MudFrame.lockstop)
        ++MudFrame.connections;
        this.remote_connect = new Connect(this, this.mainapplet.user1, this.mainapplet.pass1, this.mainapplet.user2, this.mainapplet.pass2);
        this.defaultDisplay.addString("Attempting to log in\n");
        this.remote_connect.doconnect();
        this.Cbl.show();
    }
    
    void stop_connect() {
        this.defaultDisplay.addString("Attempting to disconnect from Mud II...\n");
        this.noconnect();
    }
    
    public void dialogDismissed(final Dialog d) {
    }
    
    public MudFrame(final MudApplet a, final String cb) {
        this.history = false;
        this.showshield = true;
        this.showcompass = true;
        this.playclick = true;
        this.coloaded = false;
        this.cotoshow = false;
        this.shieldloaded = false;
        this.shieldtoshow = false;
        this.shieldadded = false;
        this.compassadded = false;
        this.cl = new CardLayout();
        this.central = new Panel();
        this.focat = 0;
        this.playsounds = true;
        this.prompt_received = false;
        this.ILretain = false;
        this.ESC = '\u001b';
        this.logon = new Button("Logon");
        try {
            this.mainapplet = a;
            this.codebase = cb;
            this.cbURL = new URL(cb);
            (this.defaultDisplay = new ScrolledDisplay(80, 24, 500)).fixFont(true);
            this.defaultDisplay.setFontSize(12);
            this.setLayout(new PrettyLayout8());
            this.mainImage = this.getImage("a8x6.jpg");
            this.jbInit();
            final Image sImage = this.getSImage("shield.jpg");
            this.sim = sImage;
            this.hp = new HealthPanel(this, sImage, new ShieldLayout());
            this.co = (Panel)new Compas8(this);
            this.prepareImage(this.sim, -1, -1, this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void do_www() {
        if (MudFrame.connected && this.coloursaver != null) {
            if (this.coloursaver != null) {
                this.coloursaver.send();
            }
        }
        else {
            TDlgObj.showText(this, "You need to be logged", "in before you can ", "retrieve the game log.");
        }
    }
    
    public String getHost() {
        String host = this.mainapplet.getParameter("Host", "None");
        if (host.equals("None")) {
            try {
                host = this.mainapplet.getDocumentBase().getHost();
            }
            catch (Exception e) {
                host = "localhost";
            }
        }
        return host;
    }
    
    void nextOne() {
        if (!this.mainapplet.userinput) {
            return;
        }
        if (this.focat == 0) {
            this.tf3.requestFocus();
            this.focat = 1;
        }
        else {
            this.tf4.requestFocus();
            this.focat = 0;
        }
    }
    
    public void tdaddString(final String s) {
    }
    
    static {
        MudFrame.connecting = false;
        MudFrame.connected = false;
        MudFrame.lockstop = new semaphore();
        MudFrame.remotelock = new semaphore();
        MudFrame.mainloaded = false;
        MudFrame.connections = 0;
        MudFrame.febackground = new Color(57, 1, 57);
        MudFrame.feforeground = new Color(233, 221, 180);
    }
    
    public void didconnect() {
        if (this.defaultDisplay != null) {
            this.defaultDisplay.cls();
        }
        this.Cbl.show();
        this.mudbox.go();
        MudFrame.connected = true;
        this.requestFocus();
        MudFrame.connecting = false;
        this.mainapplet.showStatus("Connected");
    }
    
    public void dispose() {
        this.hide();
    }
    
    public boolean action(final Event e, final Object o) {
        if (this.logshow && (e.target instanceof TextField || e.target instanceof Button)) {
            this.lbutton();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (this.logshow && (event.target instanceof TextField || event.target == this.logon)) {
            return super.handleEvent(event);
        }
        if (event.id == 1004) {
            if (this.logshow) {
                this.nextOne();
            }
            else {
                this.inputHandler.inputLine.requestFocus();
            }
        }
        if (event instanceof ImageButtonEvent) {
            final ImageButtonEvent ibevent = (ImageButtonEvent)event;
            if (ibevent.isActivated()) {
                if (event.target == this.connect_button) {
                    this.cbutton();
                }
                else if (event.target == this.viewlog_button) {
                    this.do_www();
                }
                else if (event.target == this.log_button) {
                    this.do_history();
                }
                else if (event.target == this.options_button) {
                    if (MudFrame.connected) {
                        this.option();
                    }
                    else {
                        this.nooption();
                    }
                }
                if (this.logshow) {
                    this.nextOne();
                }
                else {
                    this.inputHandler.inputLine.requestFocus();
                }
                return true;
            }
        }
        if (event.target instanceof Scrollbar) {
            return this.defaultDisplay.handleEvent(event);
        }
        return super.handleEvent(event);
    }
    
    void do_history() {
        if (MudFrame.connected) {
            if (this.history) {
                this.history = false;
                this.Lbl.hide();
                if (this.coloursaver != null) {
                    this.coloursaver.logstop();
                }
                System.out.println("Stop log");
            }
            else {
                this.history = true;
                if (this.Lbl == null) {
                    this.slim = this.getImage("stoplog.jpg");
                    this.add("LBL", this.Lbl = new ImageCanvas(this.slim));
                    this.validate();
                }
                this.Lbl.show();
                if (this.coloursaver != null) {
                    this.coloursaver.save();
                }
                System.out.println("Save log");
            }
        }
        else {
            this.history = false;
            TDlgObj.showText(this, "You need to log on", "before you can start to", "save your game log.");
        }
    }
    
    public void noconnect() {
        MudFrame.connected = false;
        this.Cbl.hide();
        if (this.history) {
            if (this.coloursaver != null) {
                this.coloursaver.logstop();
            }
            this.history = false;
            this.Lbl.hide();
        }
        if (this.mudbox != null && this.mudbox.decoder != null) {
            this.mudbox.decoder.hidefes();
        }
        if (this.co != null) {
            this.co.hide();
        }
        MudFrame.lockstop.set_to(false);
        MudFrame.connecting = false;
        MudFrame.connected = false;
    }
}

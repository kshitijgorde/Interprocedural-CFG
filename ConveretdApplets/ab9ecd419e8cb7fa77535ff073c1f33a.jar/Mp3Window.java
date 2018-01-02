import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import powersoft.powerj.event.AWTEvent;
import java.awt.Event;
import java.awt.Window;
import java.awt.Container;
import java.awt.LayoutManager;
import powersoft.powerj.ui.ResizePercentLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import powersoft.powerj.ui.PictureBox;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class Mp3Window extends Frame implements WindowListener, ActionListener
{
    protected boolean __mainForm;
    protected Label label_titelangabe;
    protected Label label_nowplaying;
    protected Button cb_play;
    protected Button cb_stop;
    protected Button cb_SelectTitle;
    protected PictureBox pictb_1;
    private Startupform startBild;
    private MPlayer spieler;
    private File datei;
    private boolean firsttime;
    private MediaTracker loader;
    private Image mittelBild;
    
    public Rectangle DURectangle(final int n, final int n2, final int n3, final int n4) {
        final String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final double n5 = (fontMetrics != null) ? (fontMetrics.stringWidth(s) / s.length()) : 0.0;
        final double n6 = (fontMetrics != null) ? (fontMetrics.getHeight() / 2.0) : 0.0;
        return new Rectangle((int)Math.round(n5 * n / 4.0), (int)Math.round(n6 * n2 / 4.0), (int)Math.round(n5 * n3 / 4.0), (int)Math.round(n6 * n4 / 4.0));
    }
    
    public void DUPositionComponent(final Component component, final int n, final int n2, final int n3, final int n4, final Insets insets) {
        final Rectangle duRectangle = this.DURectangle(n, n2, n3, n4);
        if (component != this && insets != null) {
            final Rectangle rectangle = duRectangle;
            rectangle.x += insets.left;
            final Rectangle rectangle2 = duRectangle;
            rectangle2.y += insets.top;
        }
        if (component == this && insets != null && insets.bottom > 0) {
            final Rectangle rectangle3 = duRectangle;
            rectangle3.height += insets.bottom;
        }
        component.setBounds(duRectangle);
    }
    
    public void setMainForm(final boolean _mainForm) {
        this.__mainForm = _mainForm;
    }
    
    public boolean isMainForm() {
        return this.__mainForm;
    }
    
    private boolean createTheForm() throws Exception {
        this.setTitle("RUBBERFRESH´s JAVA MP3 PLAYER");
        final Container contentPane = this.getContentPane();
        final Font font = new Font("Dialog", 0, 12);
        this.setFont(font);
        final boolean b = true;
        this.addNotify();
        final Insets insets = (Insets)contentPane.getInsets().clone();
        contentPane.setBackground(Color.black);
        contentPane.setForeground(Color.white);
        final ResizePercentLayout layout = new ResizePercentLayout();
        contentPane.setLayout(layout);
        contentPane.add(this.label_titelangabe);
        contentPane.add(this.label_nowplaying);
        contentPane.add(this.cb_play);
        contentPane.add(this.cb_stop);
        contentPane.add(this.cb_SelectTitle);
        contentPane.add(this.pictb_1);
        this.DUPositionComponent(this, 245, 104, 234, 80, insets);
        this.addWindowListener(this);
        try {
            this.DUPositionComponent(this.label_titelangabe, 10, 10, 180, 10, insets);
            this.label_titelangabe.setText("nothing...");
            this.label_titelangabe.setAlignment(0);
            this.label_titelangabe.setFont(font);
            this.label_titelangabe.setBackground(Color.black);
            this.label_titelangabe.setForeground(Color.white);
            this.label_titelangabe.setEnabled(true);
            this.label_titelangabe.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.DUPositionComponent(this.label_nowplaying, 10, 0, 70, 10, insets);
            this.label_nowplaying.setText("Title:");
            this.label_nowplaying.setAlignment(0);
            this.label_nowplaying.setFont(font);
            this.label_nowplaying.setBackground(Color.black);
            this.label_nowplaying.setForeground(Color.white);
            this.label_nowplaying.setEnabled(true);
            this.label_nowplaying.setVisible(true);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.cb_play.addActionListener(this);
            this.DUPositionComponent(this.cb_play, 130, 50, 40, 10, insets);
            this.cb_play.setLabel("PLAY");
            this.cb_play.setFont(new Font("Dialog", 1, 12));
            this.cb_play.setBackground(Color.lightGray);
            this.cb_play.setForeground(Color.black);
            this.cb_play.setEnabled(true);
            this.cb_play.setVisible(true);
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.cb_stop.addActionListener(this);
            this.DUPositionComponent(this.cb_stop, 180, 50, 40, 10, insets);
            this.cb_stop.setLabel("STOP");
            this.cb_stop.setFont(new Font("Dialog", 1, 12));
            this.cb_stop.setBackground(Color.lightGray);
            this.cb_stop.setForeground(Color.black);
            this.cb_stop.setEnabled(true);
            this.cb_stop.setVisible(true);
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.cb_SelectTitle.addActionListener(this);
            this.DUPositionComponent(this.cb_SelectTitle, 10, 50, 70, 10, insets);
            this.cb_SelectTitle.setLabel("SELECT TITLE");
            this.cb_SelectTitle.setFont(new Font("Dialog", 1, 12));
            this.cb_SelectTitle.setBackground(Color.lightGray);
            this.cb_SelectTitle.setForeground(Color.black);
            this.cb_SelectTitle.setEnabled(true);
            this.cb_SelectTitle.setVisible(true);
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
        try {
            this.DUPositionComponent(this.pictb_1, 10, 20, 210, 28, insets);
            this.pictb_1.setFont(font);
            this.pictb_1.setBackground(Color.black);
            this.pictb_1.setForeground(Color.white);
            this.pictb_1.setEnabled(true);
            this.pictb_1.setVisible(true);
            final Rectangle duRectangle = this.DURectangle(0, 0, 210, 28);
            this.pictb_1.setSize(duRectangle.width, duRectangle.height);
            this.pictb_1.setImagePosition(1);
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
        layout.setResizePercent(this.label_titelangabe, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.label_nowplaying, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.cb_play, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.cb_stop, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.cb_SelectTitle, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.pictb_1, new Rectangle(0, 0, 0, 0));
        this.setResizable(true);
        return b;
    }
    
    public boolean create() throws Exception {
        final boolean b = true && this.createTheForm();
        this.setVisible(true);
        return b;
    }
    
    public synchronized boolean destroy() {
        if (this instanceof Window) {
            this.dispose();
        }
        else {
            this.removeNotify();
        }
        if (this.isMainForm()) {
            System.gc();
            System.runFinalization();
            System.exit(0);
        }
        return true;
    }
    
    private boolean defaultHandleEvent(final Event awtEvent) {
        this.defaultProcessEvent(new AWTEvent(awtEvent));
        return false;
    }
    
    private void defaultProcessEvent(final java.awt.AWTEvent awtEvent) {
        super.processEvent(awtEvent);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.Mp3Window_windowClosing(windowEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.WindowListener", "windowClosing", windowEvent);
        }
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.Mp3Window_windowActivated(windowEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.WindowListener", "windowActivated", windowEvent);
        }
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.cb_play) {
            this.cb_play_actionPerformed(actionEvent);
        }
        else if (source == this.cb_stop) {
            this.cb_stop_actionPerformed(actionEvent);
        }
        else if (source == this.cb_SelectTitle) {
            this.cb_SelectTitle_actionPerformed(actionEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.ActionListener", "actionPerformed", actionEvent);
        }
    }
    
    public Mp3Window() {
        this.__mainForm = false;
        this.label_titelangabe = new Label();
        this.label_nowplaying = new Label();
        this.cb_play = new Button();
        this.cb_stop = new Button();
        this.cb_SelectTitle = new Button();
        this.pictb_1 = new PictureBox();
        this.firsttime = true;
        this.startBild = new Startupform(this);
        try {
            this.startBild.create();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.spieler = null;
        this.datei = null;
    }
    
    public void processEvent(final java.awt.AWTEvent awtEvent) {
        this.defaultProcessEvent(awtEvent);
    }
    
    private void unhandledEvent(final String s, final String s2, final Object o) {
    }
    
    public Container getContentPane() {
        return this;
    }
    
    private void Mp3Window_windowActivated(final WindowEvent windowEvent) {
        if (this.firsttime) {
            this.startBild.dispose();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(screenSize.width / 2 - this.getSize().width / 2, screenSize.height / 2 - this.getSize().height / 2);
            this.mittelBild = Toolkit.getDefaultToolkit().getImage("javamp32.jpg");
            (this.loader = new MediaTracker(this)).addImage(this.mittelBild, 0);
            try {
                this.loader.waitForAll();
            }
            catch (InterruptedException ex) {}
            this.pictb_1.setImage(this.mittelBild);
            this.setResizable(false);
            this.firsttime = false;
        }
    }
    
    private boolean Mp3Window_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.destroy();
        return false;
    }
    
    private void cb_play_actionPerformed(final ActionEvent actionEvent) {
        if (this.spieler == null) {
            this.makePlayer();
        }
        if (this.spieler != null) {
            this.cb_play.setEnabled(false);
            this.spieler.play();
            this.label_titelangabe.setText("Playing: " + this.datei.getName());
        }
        else {
            this.label_titelangabe.setText("Choose File for playing...");
        }
    }
    
    private void cb_stop_actionPerformed(final ActionEvent actionEvent) {
        if (this.spieler != null) {
            this.spieler.stop();
            this.cb_play.setEnabled(true);
            this.label_titelangabe.setText(this.datei.getName());
            this.spieler = null;
            System.gc();
        }
    }
    
    private void cb_SelectTitle_actionPerformed(final ActionEvent actionEvent) {
        System.out.println("Starte Auswahlfunktion...");
        final dateiAuswahl dateiAuswahl = new dateiAuswahl(this);
        try {
            dateiAuswahl.create();
        }
        catch (Exception ex) {}
        if (!dateiAuswahl.abbruch()) {
            this.datei = dateiAuswahl.getSelectedFile();
            this.makePlayer();
        }
        dateiAuswahl.dispose();
        System.gc();
    }
    
    private void makePlayer() {
        if (this.datei != null) {
            if (this.spieler != null) {
                this.spieler.stop();
                this.spieler = null;
            }
            try {
                this.spieler = new MPlayer(this.datei);
            }
            catch (MPlayerException ex) {
                System.out.println(ex.toString());
                this.label_titelangabe.setText("no valid MPEG-File choosed...");
                return;
            }
            this.label_titelangabe.setText(this.datei.getName());
            this.cb_play.setEnabled(true);
        }
        else {
            this.label_titelangabe.setText("No File choosed...");
        }
    }
}

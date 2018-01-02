import java.awt.Frame;
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
import java.io.File;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class dateiAuswahl extends Dialog implements WindowListener, ActionListener
{
    protected boolean __mainForm;
    protected Label label_ueberschrift;
    protected Button cb_ok;
    protected Button cb_abbruch;
    private File datei;
    private DirectoryTree jtree_1;
    private boolean abbruchflag;
    
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
        this.setTitle("Datei-Auswahl");
        final Container contentPane = this.getContentPane();
        final Font font = new Font("Dialog", 0, 12);
        this.setFont(font);
        final boolean b = true;
        this.addNotify();
        final Insets insets = (Insets)contentPane.getInsets().clone();
        contentPane.setBackground(Color.lightGray);
        contentPane.setForeground(Color.black);
        final ResizePercentLayout layout = new ResizePercentLayout();
        contentPane.setLayout(layout);
        contentPane.add(this.label_ueberschrift);
        contentPane.add(this.cb_ok);
        contentPane.add(this.cb_abbruch);
        this.DUPositionComponent(this, 287, 84, 272, 192, insets);
        this.addWindowListener(this);
        try {
            this.DUPositionComponent(this.label_ueberschrift, 7, 6, 240, 8, insets);
            this.label_ueberschrift.setText("Bitte Datei ausw\u00e4hlen und mit OK best\u00e4tigen");
            this.label_ueberschrift.setAlignment(0);
            this.label_ueberschrift.setFont(font);
            this.label_ueberschrift.setBackground(Color.lightGray);
            this.label_ueberschrift.setForeground(Color.black);
            this.label_ueberschrift.setEnabled(true);
            this.label_ueberschrift.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.DUPositionComponent(this.cb_ok, 33, 155, 80, 14, insets);
            this.cb_ok.addActionListener(this);
            this.cb_ok.setLabel("OK");
            this.cb_ok.setFont(font);
            this.cb_ok.setBackground(Color.lightGray);
            this.cb_ok.setForeground(Color.black);
            this.cb_ok.setEnabled(true);
            this.cb_ok.setVisible(true);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.cb_abbruch.addActionListener(this);
            this.DUPositionComponent(this.cb_abbruch, 147, 155, 80, 14, insets);
            this.cb_abbruch.setLabel("Abbrechen");
            this.cb_abbruch.setFont(font);
            this.cb_abbruch.setBackground(Color.lightGray);
            this.cb_abbruch.setForeground(Color.black);
            this.cb_abbruch.setEnabled(true);
            this.cb_abbruch.setVisible(true);
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        layout.setResizePercent(this.label_ueberschrift, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.cb_ok, new Rectangle(0, 0, 0, 0));
        layout.setResizePercent(this.cb_abbruch, new Rectangle(0, 0, 0, 0));
        this.setResizable(false);
        return b;
    }
    
    public boolean create(final boolean visible) throws Exception {
        final boolean b = true && this.createTheForm();
        this.setVisible(visible);
        return b;
    }
    
    public boolean create() throws Exception {
        return this.create(true);
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
            this.dateiAuswahl_windowClosing(windowEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.WindowListener", "windowClosing", windowEvent);
        }
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.cb_abbruch) {
            this.cb_abbruch_actionPerformed(actionEvent);
        }
        if (source == this.cb_ok) {
            this.cb_ok_actionPerformed(actionEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.ActionListener", "actionPerformed", actionEvent);
        }
    }
    
    public dateiAuswahl(final Frame frame) {
        super(frame, true);
        this.__mainForm = false;
        this.label_ueberschrift = new Label();
        this.cb_ok = new Button();
        this.cb_abbruch = new Button();
        this.abbruchflag = false;
        this.datei = null;
        final Insets insets = (Insets)this.getContentPane().getInsets().clone();
        final Font font = new Font("Dialog", 0, 12);
        final ResizePercentLayout resizePercentLayout = new ResizePercentLayout();
        this.jtree_1 = new DirectoryTree();
        try {
            this.DUPositionComponent(this.jtree_1, 10, 30, 247, 127, insets);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        resizePercentLayout.setResizePercent(this.jtree_1, new Rectangle(0, 0, 0, 0));
        this.getContentPane().add(this.jtree_1);
    }
    
    public void processEvent(final java.awt.AWTEvent awtEvent) {
        this.defaultProcessEvent(awtEvent);
    }
    
    private void unhandledEvent(final String s, final String s2, final Object o) {
    }
    
    public Container getContentPane() {
        return this;
    }
    
    public boolean abbruch() {
        return this.abbruchflag;
    }
    
    public File getSelectedFile() {
        return this.datei;
    }
    
    private boolean dateiAuswahl_windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        this.destroy();
        return false;
    }
    
    private void cb_abbruch_actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
        this.abbruchflag = true;
        this.destroy();
    }
    
    private void cb_ok_actionPerformed(final ActionEvent actionEvent) {
        this.datei = new File(this.jtree_1.getFileString());
        if (this.datei.isDirectory()) {
            this.datei = null;
            return;
        }
        this.setVisible(false);
        this.destroy();
        System.gc();
    }
    
    public void breakfromoutside() {
        this.cb_ok_actionPerformed(new ActionEvent(this.jtree_1, 1, "got File"));
    }
}

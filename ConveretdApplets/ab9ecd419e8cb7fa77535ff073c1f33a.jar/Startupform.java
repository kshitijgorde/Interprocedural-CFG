import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Frame;
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
import powersoft.powerj.ui.PictureBox;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class Startupform extends Dialog implements Runnable, WindowListener
{
    protected boolean __mainForm;
    protected PictureBox pictb_1;
    MediaTracker loader;
    Image startPicture;
    
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
        this.setTitle("JAVA MP3");
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
        contentPane.add(this.pictb_1);
        this.DUPositionComponent(this, 148, 121, 267, 146, insets);
        this.addWindowListener(this);
        try {
            this.DUPositionComponent(this.pictb_1, 0, 0, 272, 139, insets);
            this.pictb_1.setFont(font);
            this.pictb_1.setBackground(Color.lightGray);
            this.pictb_1.setForeground(Color.black);
            this.pictb_1.setEnabled(true);
            this.pictb_1.setVisible(true);
            final Rectangle duRectangle = this.DURectangle(0, 0, 272, 139);
            this.pictb_1.setSize(duRectangle.width, duRectangle.height);
            this.pictb_1.setImagePosition(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        layout.setResizePercent(this.pictb_1, new Rectangle(0, 0, 0, 0));
        this.setResizable(true);
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
            this.Startupform_windowClosing(windowEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.WindowListener", "windowClosing", windowEvent);
        }
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this) {
            this.Startupform_windowActivated(windowEvent);
        }
        else {
            this.unhandledEvent("java.awt.event.WindowListener", "windowActivated", windowEvent);
        }
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public Startupform(final Frame frame) {
        super(frame, true);
        this.__mainForm = false;
        this.pictb_1 = new PictureBox();
        this.startPicture = Toolkit.getDefaultToolkit().getImage("javamp3.jpg");
        (this.loader = new MediaTracker(this)).addImage(this.startPicture, 0);
        try {
            this.loader.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println(ex);
        }
        this.pictb_1.setImage(this.startPicture);
        this.setResizable(false);
    }
    
    public void processEvent(final java.awt.AWTEvent awtEvent) {
        this.defaultProcessEvent(awtEvent);
    }
    
    private void unhandledEvent(final String s, final String s2, final Object o) {
    }
    
    public Container getContentPane() {
        return this;
    }
    
    private void Startupform_windowActivated(final WindowEvent windowEvent) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2, screenSize.height / 2 - this.getSize().height / 2);
        new Thread(this).start();
    }
    
    private boolean Startupform_windowClosing(final WindowEvent windowEvent) {
        return false;
    }
    
    public void run() {
        try {
            Thread.currentThread();
            Thread.sleep(1500L);
        }
        catch (InterruptedException ex) {}
        this.setVisible(false);
        this.destroy();
    }
}

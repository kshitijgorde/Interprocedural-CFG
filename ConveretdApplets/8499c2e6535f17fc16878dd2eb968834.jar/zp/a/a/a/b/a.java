// 
// Decompiled by Procyon v0.5.30
// 

package zp.a.a.a.b;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.MenuItem;
import java.applet.AppletContext;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.Panel;

public abstract class a extends Panel implements Runnable, ActionListener
{
    private String[] for;
    private PopupMenu if;
    protected boolean void;
    protected boolean long;
    private Thread e;
    private boolean new;
    private int a;
    public static String d;
    public static String c;
    public static String b;
    public static String null;
    public static String goto;
    public static String char;
    public static String case;
    public static String byte;
    public static final String f = "splashscreen.gif";
    public AppletContext do;
    public boolean int;
    public boolean try;
    public zp.a.a.a.c.a else;
    
    public a() {
        this.for = new String[] { "about VRXStudios", "about iSeeMedia" };
        this.else = null;
        this.case();
        this.enableEvents(56L);
    }
    
    public void char() {
        if (this.e == null) {
            this.else();
            this.new = false;
            this.a = 10;
            (this.e = new Thread(this)).start();
        }
        else {
            this.repaint();
            this.requestFocus();
        }
    }
    
    public void do() {
        if (this.e != null) {
            this.new = true;
            while (this.e != null) {
                Thread.yield();
            }
            this.int();
        }
    }
    
    public void run() {
        try {
            while (!this.new) {
                if (this.a()) {
                    Thread.yield();
                    this.a = 10;
                }
                else {
                    Thread.sleep(this.a);
                    if (this.a >= 75) {
                        continue;
                    }
                    this.a += 5;
                }
            }
        }
        catch (Throwable t) {}
        finally {
            this.e = null;
        }
    }
    
    public static void a(final String d, final String c, final String b, final String null, final String goto1, final String char1, final String case1, final String byte1) {
        a.d = d;
        a.c = c;
        a.b = b;
        a.null = null;
        a.goto = goto1;
        a.char = char1;
        a.case = case1;
        a.byte = byte1;
    }
    
    protected abstract boolean a();
    
    protected abstract void else();
    
    protected abstract void int();
    
    public void for() {
        this.int = true;
        if (this.else != null) {
            this.else.if(1, 3);
        }
        this.try = true;
    }
    
    public void if() {
        this.int = false;
        if (this.else != null) {
            this.else.if(1, 4);
        }
        this.try = true;
    }
    
    public void a(final int n) {
    }
    
    public void byte() {
    }
    
    public void try() {
    }
    
    public void long() {
    }
    
    public boolean goto() {
        final String property = System.getProperty("java.version");
        if (property.startsWith("1.1") && zp.a.a.a.d.a.new) {
            int intValue = 5;
            final String substring = property.substring(property.lastIndexOf(".") + 1, property.lastIndexOf(".") + 2);
            try {
                intValue = Integer.valueOf(substring);
            }
            catch (NumberFormatException ex) {}
            return intValue > 7;
        }
        return true;
    }
    
    private void case() {
        (this.if = new PopupMenu("")).addSeparator();
        for (int i = 0; i < this.for.length; ++i) {
            final MenuItem menuItem = new MenuItem(this.for[i]);
            menuItem.addActionListener(this);
            this.if.add(menuItem);
        }
        this.if.addSeparator();
        this.add(this.if);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.if.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            this.void = false;
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    this.void = true;
                    break;
                }
                case 502: {
                    this.void = false;
                }
                case 504: {}
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("reset")) {
            this.new();
        }
        try {
            if (actionCommand.equals("about VRXStudios")) {
                this.do.showDocument(new URL("http://www.vrxstudios.com"), "VRX");
            }
            if (actionCommand.equals("about iSeeMedia")) {
                this.do.showDocument(new URL("http://www.iseemedia.com"), "iSee");
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    public abstract void if(final int p0);
    
    public abstract void new();
    
    static {
        a.d = " ";
        a.c = " ";
        a.b = " ";
        a.null = " ";
        a.goto = " ";
        a.char = " ";
        a.case = " ";
        a.byte = " ";
    }
}

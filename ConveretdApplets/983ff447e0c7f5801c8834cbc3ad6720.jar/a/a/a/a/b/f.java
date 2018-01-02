// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.b;

import java.awt.Rectangle;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.AWTEventMulticaster;
import java.awt.Cursor;
import java.awt.MediaTracker;
import a.a.a.a.e.a;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Image;
import a.a.a.a.c.a.c;
import java.awt.Component;

public class f extends Component implements c, h
{
    public static final int a = 0;
    public static final int g = 1;
    public static final int for = -1;
    public static final int try = 0;
    public static final int f = 1;
    public static final String goto = "                      ";
    private int e;
    private String else;
    private int new;
    private int d;
    private boolean void;
    private Image long;
    private Image null;
    private Image h;
    private Dimension b;
    private int do;
    private int if;
    private int char;
    private int case;
    private ActionListener i;
    private a c;
    private a.a.a.a.b.a int;
    private MediaTracker byte;
    
    public f(final a a, final String s, final int n, final int n2, final int n3, final int n4) {
        this(a, s, n, n2, n3, n4, null, false);
    }
    
    public f(final a a, final String s, final int n, final int n2, final int n3, final int n4, final a.a.a.a.b.a a2) {
        this(a, s, n, n2, n3, n4, a2, false);
    }
    
    public f(final a c, final String else1, final int n, final int n2, final int new1, final int d, final a.a.a.a.b.a int1, final boolean void1) {
        this.e = 1;
        this.c = c;
        this.else = else1;
        this.new = new1;
        this.d = d;
        this.int = int1;
        this.void = void1;
        this.b = new Dimension(n, n2);
        ((a.a.a.a.c.a.a)this.c.byte("statemachine")).if(this);
        this.new();
        if (this.new == 0) {
            this.d = this.int();
        }
        else if (this.d == 1) {
            this.d = 0;
        }
        this.setCursor((Cursor)this.c.byte("cursor/default"));
        this.enableEvents(48L);
    }
    
    public void if(final ActionListener actionListener) {
        this.i = AWTEventMulticaster.add(this.i, actionListener);
        this.enableEvents(16L);
    }
    
    public void do() {
        this.long.flush();
        this.null.flush();
        this.h.flush();
    }
    
    public a.a.a.a.b.a for() {
        return this.int;
    }
    
    public Dimension getMinimumSize() {
        return this.b;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    protected int int() {
        switch (((a.a.a.a.c.a.a)this.c.byte("statemachine")).a(this.else)) {
            case -1: {
                return -1;
            }
            case 1: {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void if() {
        this.repaint();
    }
    
    public void a(final int n) {
    }
    
    public void a() {
    }
    
    private void new() {
        this.byte = new MediaTracker(this);
        this.long = (Image)this.c.byte("images/button/" + this.else + ".up");
        if (this.long == null) {
            this.long = (Image)this.c.byte("images/button/" + this.else);
        }
        this.byte.addImage(this.long, 0);
        this.null = (Image)this.c.byte("images/button/" + this.else + ".down");
        if (this.null == null) {
            this.null = this.long;
        }
        else {
            this.byte.addImage(this.null, 0);
        }
        this.h = (Image)this.c.byte("images/button/" + this.else + ".gray");
        if (this.h == null) {
            this.h = this.long;
        }
        else {
            this.byte.addImage(this.h, 0);
        }
        new Thread(new b(new e(this.byte, 0, this)), "ImageLoader").start();
    }
    
    public void paint(final Graphics graphics) {
        if (this.byte.statusID(0, false) == 8) {
            switch (this.d) {
                case -1: {
                    graphics.drawImage(this.h, 0, 0, this);
                    break;
                }
                case 0: {
                    graphics.drawImage(this.long, 0, 0, this);
                    break;
                }
                case 1: {
                    graphics.drawImage(this.null, 0, 0, this);
                    break;
                }
            }
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                if (this.int != null) {
                    this.int.a();
                }
                if (this.i != null) {
                    this.i.actionPerformed(new ActionEvent(this, 1001, this.else));
                }
                if (this.void) {
                    final int x = mouseEvent.getX();
                    this.do = x;
                    this.char = x;
                    final int y = mouseEvent.getY();
                    this.if = y;
                    this.case = y;
                }
                if (this.new == 1) {
                    if (this.d != -1) {
                        this.d = 1;
                        this.repaint();
                    }
                }
                else {
                    this.d = this.int();
                    this.repaint();
                }
                this.try();
                break;
            }
            case 502: {
                if (this.new == 1) {
                    if (this.d == 1) {
                        this.d = 0;
                        this.repaint();
                    }
                }
                else {
                    this.d = this.int();
                    this.repaint();
                }
                this.try();
                break;
            }
            case 504: {
                this.repaint();
                this.try();
                break;
            }
            case 505: {
                ((Applet)this.c.byte("applet")).getAppletContext().showStatus("                      ");
                if (this.new != 1) {
                    this.d = this.int();
                    this.repaint();
                    break;
                }
                if (this.d != -1) {
                    this.d = 0;
                    this.repaint();
                    break;
                }
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                if (!this.void) {
                    break;
                }
                this.char = mouseEvent.getX();
                this.case = mouseEvent.getY();
                final int n = this.char - this.do;
                final int n2 = this.case - this.if;
                if (Math.abs(n) + Math.abs(n2) > this.e) {
                    final Rectangle bounds = this.getBounds();
                    final Dimension size = this.getParent().getSize();
                    final int n3 = n + bounds.x;
                    final int n4 = n2 + bounds.y;
                    final Dimension size2 = this.getSize();
                    final int n5 = (n3 > 0) ? n3 : 0;
                    final int n6 = (n4 > 0) ? n4 : 0;
                    this.setBounds((n5 + size2.width < size.width) ? n5 : (size.width - size2.width), (n6 + size2.height < size.height) ? n6 : (size.height - size2.height), size2.width, size2.height);
                    this.repaint();
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public void a(final ActionListener actionListener) {
        this.i = AWTEventMulticaster.remove(this.i, actionListener);
    }
    
    public void setEnabled(final boolean enabled) {
        if (!enabled) {
            this.d = -1;
        }
        else if (this.new == 1) {
            this.d = 0;
        }
        else {
            this.d = this.int();
        }
        this.repaint();
        super.setEnabled(enabled);
    }
    
    protected void try() {
        String s = null;
        if (this.new == 1) {
            s = this.c.a("resource/button.hint.action." + this.else);
        }
        else if (this.else.equals("toolbar") || this.else.equals("hotspots") || this.else.equals("magnifier")) {
            switch (this.int()) {
                case 1: {
                    s = this.c.a("resource/button.hint.state." + this.else + ".down");
                    break;
                }
                case 0: {
                    s = this.c.a("resource/button.hint.state." + this.else + ".up");
                    break;
                }
                default: {
                    s = "                             ";
                    break;
                }
            }
        }
        else {
            s = this.c.a("resource/button.hint.state." + this.else);
        }
        if (s != null) {
            ((Applet)this.c.byte("applet")).getAppletContext().showStatus(s);
        }
    }
    
    public void a(final a.a.a.a.c.a.b b) {
        if (this.new == 0) {
            final int d = this.d;
            this.d = this.int();
            if (this.d != d) {
                this.repaint();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}

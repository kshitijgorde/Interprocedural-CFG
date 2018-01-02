import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import netscape.javascript.JSObject;
import java.awt.Scrollbar;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Applet
{
    Panel c;
    p case;
    Scrollbar b;
    Scrollbar null;
    j void;
    g d;
    protected boolean byte;
    protected JSObject long;
    public int goto;
    private int if;
    private int try;
    public boolean[] e;
    public byte new;
    public byte int;
    private boolean for;
    private boolean else;
    protected boolean a;
    protected boolean do;
    private String char;
    
    public h() {
        this.e = new boolean[] { true, false, true, false, true, false, true, false };
        this.char = System.getProperty("java.version", "1.1.5");
        if (this.char.compareTo("1.1.4") < 0) {
            this.byte = false;
        }
        else {
            this.byte = true;
        }
    }
    
    public byte if(final String s) {
        if (s.equalsIgnoreCase("true")) {
            return 1;
        }
        if (s.equalsIgnoreCase("false")) {
            return 0;
        }
        return 2;
    }
    
    public void a(final int if1, final int try1, final Color color) {
        this.new = this.if(this.a("vscroll", "auto"));
        this.int = this.if(this.a("hscroll", "false"));
        this.if = if1;
        this.try = try1;
        (this.c = new Panel()).setLayout(new BorderLayout());
        this.case = new p(this.byte, (DCQuoteTable)this, this.a("allowColumnResize", true));
        this.b = new Scrollbar(1);
        this.null = new Scrollbar(0);
        this.b.setLineIncrement(10);
        this.null.setLineIncrement(4);
        this.setLayout(new BorderLayout());
        if (this.do) {
            this.add("North", this.d = new g(this.byte, this));
            this.d.a();
        }
        if (this.int != 0) {
            this.c.add("North", this.null);
            this.else = true;
        }
        this.add("Center", this.case);
        if (this.new != 0) {
            this.add("East", this.b);
            this.for = true;
        }
        this.add("South", this.c);
        if (this.a) {
            this.void = new j(this.byte, this);
            this.c.add("South", this.void);
            this.void.a();
        }
        this.validate();
        try {
            this.long = JSObject.getWindow((Applet)this);
        }
        catch (Exception ex) {
            System.out.println("JSException " + ex);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof Scrollbar) {
            if (event.target == this.null) {
                this.case.for(this.null.getValue());
                this.case.a(true);
            }
            else {
                final int value = this.b.getValue();
                synchronized (this) {
                    this.case.do(value);
                    this.case.a(false);
                }
            }
            return true;
        }
        return false;
    }
    
    public void a(final n[] array) {
        this.case.a(array);
    }
    
    public void for() {
        this.validate();
        this.case.for();
        this.int();
        this.a();
        this.case.a(true);
        if (this.a) {
            this.void.repaint();
        }
        if (this.do) {
            this.d.repaint();
        }
        this.repaint();
    }
    
    public void if() {
        this.case.a(true);
    }
    
    public synchronized void a(final n n) {
        this.case.a(n);
    }
    
    public JSObject do() {
        return this.long;
    }
    
    public void int() {
        if (this.new != 0) {
            final int i = ((DCQuoteTable)this).I;
            final int n = this.case.int().height - this.case.size().height + i + f.a();
            if (this.new == 1 || n > 0) {
                if (!this.for) {
                    this.add("East", this.b);
                    this.validate();
                    this.for = true;
                }
                final int n2 = this.case.size().height - i - f.a();
                if (this.byte) {
                    this.b.setValues(this.b.getValue(), n2, 0, n + n2);
                }
                else {
                    this.b.setValues(this.b.getValue(), n2, 0, n);
                }
                this.b.setPageIncrement(Math.max(n2 - 10, 10));
                this.case.do(this.b.getValue());
            }
            else if (this.for) {
                this.remove(this.b);
                this.validate();
                this.for = false;
                this.case.do(0);
            }
        }
    }
    
    public void a() {
        if (this.int != 0) {
            final int n = this.case.int().width - this.case.size().width;
            if (this.int == 1 || n > 0) {
                if (!this.else) {
                    this.c.add("North", this.null);
                    this.c.validate();
                    this.validate();
                    this.else = true;
                }
                final int n2 = this.case.size().width - this.goto;
                if (this.byte) {
                    this.null.setValues(this.null.getValue(), n2, 0, n + n2);
                }
                else {
                    this.null.setValues(this.null.getValue(), n2, 0, n);
                }
                this.null.setPageIncrement(Math.max(n2 - 10, 10));
                this.case.for(this.null.getValue());
            }
            else if (this.else) {
                this.c.remove(this.null);
                this.c.validate();
                this.validate();
                this.else = false;
                this.case.for(0);
            }
        }
    }
    
    protected Color a(final String s) {
        int n;
        int n2;
        int n3;
        if (s.indexOf(",") != -1) {
            final Vector if1 = c.if(s, ",");
            n = Integer.decode(if1.elementAt(0));
            n2 = Integer.decode(if1.elementAt(1));
            n3 = Integer.decode(if1.elementAt(2));
        }
        else {
            n = Integer.decode("0x" + s.substring(0, 2));
            n2 = Integer.decode("0x" + s.substring(2, 4));
            n3 = Integer.decode("0x" + s.substring(4, 6));
        }
        return new Color(n, n2, n3);
    }
    
    protected Color a(final String s, final Color color) {
        this.char = this.getParameter(s);
        if (this.char != null) {
            return this.a(this.char);
        }
        return color;
    }
    
    protected boolean a(final String s, final boolean b) {
        this.char = this.getParameter(s);
        if (this.char != null) {
            if (this.char.equalsIgnoreCase("yes") || this.char.equalsIgnoreCase("on") || this.char.equalsIgnoreCase("true")) {
                this.char = "true";
            }
            return Boolean.valueOf(this.char);
        }
        return b;
    }
    
    protected byte a(final String s, final byte b) {
        this.char = this.getParameter(s);
        if (this.char != null) {
            return Byte.parseByte(this.char);
        }
        return b;
    }
    
    protected int a(final String s, final int n) {
        this.char = this.getParameter(s);
        if (this.char != null) {
            return Integer.parseInt(this.char);
        }
        return n;
    }
    
    protected String a(final String s, final String s2) {
        this.char = this.getParameter(s);
        if (this.char != null) {
            try {
                this.char = new String(this.char.getBytes("UTF8"), "UTF8");
            }
            catch (UnsupportedEncodingException ex) {
                this.char = null;
            }
            return this.char;
        }
        return s2;
    }
    
    public void a(final int n) {
        this.case.if(n);
    }
}

import java.awt.Component;
import java.util.Random;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Container;
import java.net.URL;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class iabIASYScom extends Applet implements Runnable
{
    String[] T;
    String[] I;
    String[] C;
    String[] L;
    protected int a;
    protected int b;
    protected Vector c;
    protected int d;
    protected boolean e;
    protected Color f;
    protected Color g;
    protected Font h;
    protected Color i;
    protected Font j;
    protected Color k;
    protected Font l;
    protected String m;
    protected String n;
    protected boolean o;
    protected int a2;
    protected boolean a3;
    protected byte p;
    protected boolean q;
    protected Image r;
    protected Image s;
    protected Image t;
    protected Image u;
    protected Thread v;
    protected MediaTracker w;
    protected Rectangle x;
    protected int z;
    protected Frame a1;
    
    public String getAppletInfo() {
        return "interActive Banner (c) 1999 IA, Inc.";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.o && n2 >= this.size().height - 14 && n >= this.size().width - 43) {
            if (this.v == null) {
                this.start();
                this.m1();
                this.repaint();
            }
            else if (n <= this.size().width - 29) {
                this.stop();
                this.m1(-1);
                this.start();
            }
            else if (n <= this.size().width - 15) {
                this.stop();
                this.m1(1);
                this.start();
            }
            else if (this.v != null) {
                this.stop();
                this.m1("paused");
            }
        }
        else if (this.x == null) {
            try {
                this.getAppletContext().showDocument(new URL("http", "iasys.com", "/_iabanner"));
            }
            catch (Throwable t) {}
        }
        else {
            try {
                this.getAppletContext().showDocument(new URL(this.L[this.a]), this.m);
            }
            catch (Throwable t2) {}
        }
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        byte p3 = -1;
        final int width = this.size().width;
        if (this.o && n2 >= this.size().height - 14) {
            if (n >= width - 43 && n < width - 29) {
                p3 = 0;
            }
            else if (n >= width - 29 && n < width - 15) {
                p3 = 1;
            }
            else if (n >= width - 15) {
                p3 = 2;
            }
        }
        if (p3 != this.p) {
            this.p = p3;
            if (!this.q) {
                this.m1();
                this.repaint();
            }
        }
        this.a1.setCursor((this.x == null || this.x.inside(n, n2) || (n > 1 && n < this.z) || (this.o && n2 >= this.size().height - 13 && n >= this.size().width - 42)) ? 12 : 0);
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.o) {
            this.p = -1;
            if (!this.q) {
                this.m1();
                this.repaint();
            }
        }
        return false;
    }
    
    public void run() {
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        if (parent != null && parent instanceof Frame) {
            this.a1 = (Frame)parent;
        }
        final int width = this.size().width;
        final int height = this.size().height;
        Graphics graphics = null;
        if (this.u == null) {
            this.u = this.createImage(width, height);
            graphics = this.u.getGraphics();
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, width - 1, height - 1);
        }
        if (this.getParameter("(c)") != null && this.getParameter("(c)").equalsIgnoreCase("www.iasys.com")) {
            Label_0867: {
                if (this.x != null) {
                    break Label_0867;
                }
                graphics.drawString("Please wait...", (width - graphics.getFontMetrics().stringWidth("Please wait...")) / 2, (height + graphics.getFontMetrics().getHeight()) / 2);
                this.paint(this.getGraphics());
                this.x = new Rectangle();
                this.f = ((this.getParameter("titlecolor") == null) ? Color.black : new Color(Integer.parseInt(this.getParameter("titlecolor").substring(1), 16)));
                this.g = ((this.getParameter("txtbgcolor") == null) ? Color.orange : new Color(Integer.parseInt(this.getParameter("txtbgcolor").substring(1), 16)));
                this.i = ((this.getParameter("copycolor") == null) ? Color.black : new Color(Integer.parseInt(this.getParameter("copycolor").substring(1), 16)));
                this.k = ((this.getParameter("linkcolor") == null) ? Color.blue : new Color(Integer.parseInt(this.getParameter("linkcolor").substring(1), 16)));
                this.h = this.m1("titlefont", "Helvetica", 1, 12);
                this.j = this.m1("copyfont", "Helvetica", 0, 12);
                this.l = this.m1("linkfont", "Helvetica", 1, 11);
                this.m = ((this.getParameter("linktarget") == null) ? "_self" : this.getParameter("linktarget"));
                this.n = ((this.getParameter("linktext") == null) ? "Click here" : this.getParameter("linktext"));
                this.o = (this.getParameter("shownav") == null || this.getParameter("shownav").equalsIgnoreCase("1"));
                this.a2 = ((this.getParameter("delay") == null) ? 10000 : Integer.parseInt(this.getParameter("delay")));
                this.a3 = (this.getParameter("border") == null || this.getParameter("border").equalsIgnoreCase("1"));
                if (this.getParameter("next") != null && this.getParameter("next").equalsIgnoreCase("random")) {
                    this.b = 1;
                }
                else {
                    this.b = 0;
                }
                int n = 0;
                while (this.getParameter("banner" + n++) != null) {}
                --n;
                this.T = new String[n];
                this.I = new String[n];
                this.C = new String[n];
                this.L = new String[n];
                for (int i = 0; i < this.T.length; ++i) {
                    try {
                        final String parameter = this.getParameter("banner" + i);
                        final int n2 = 0;
                        final int index = parameter.indexOf("|", n2);
                        this.T[i] = parameter.substring(n2, index);
                        final int n3 = index + 1;
                        final int index2 = parameter.indexOf("|", n3);
                        this.I[i] = parameter.substring(n3, index2);
                        final int n4 = index2 + 1;
                        final int index3 = parameter.indexOf("|", n4);
                        this.C[i] = parameter.substring(n4, index3);
                        this.L[i] = parameter.substring(index3 + 1);
                    }
                    catch (Throwable t) {}
                }
                this.a = -1;
                this.c = new Vector();
                this.d = 0;
                this.e = false;
                try {
                    this.r = this.getImage(this.getCodeBase(), "navigation.gif");
                }
                catch (Throwable t2) {
                    this.r = null;
                }
                this.m1(1);
                if (this.r == null) {
                    break Label_0867;
                }
                this.w.addImage(this.r, 0);
                try {
                    while (true) {
                        this.w.waitForAll();
                        this.s = this.t;
                        this.m1();
                        this.repaint();
                        Thread.sleep(this.a2);
                        this.m1(1);
                    }
                }
                catch (Throwable t3) {
                    return;
                }
            }
        }
        this.m1(graphics, "Please add <param name=(c) value=www.iasys.com> to the parameter list.", new Rectangle(2, 2, width - 4, height - 4));
    }
    
    public void start() {
        this.stop();
        (this.v = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.v != null) {
            this.v.stop();
        }
        this.v = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.u != null) {
            graphics.drawImage(this.u, 0, 0, null);
        }
    }
    
    protected boolean m1(final Graphics graphics, final String s, final Rectangle rectangle) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " \n");
        int n = rectangle.x;
        int n2 = rectangle.y + graphics.getFontMetrics().getHeight();
        while (n2 <= rectangle.y + rectangle.height && stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equalsIgnoreCase("<br>")) {
                n2 += graphics.getFontMetrics().getHeight();
                n = rectangle.x;
            }
            else {
                final String string = nextToken + " ";
                if (n + graphics.getFontMetrics().stringWidth(string) > rectangle.x + rectangle.width) {
                    n2 += graphics.getFontMetrics().getHeight();
                    graphics.drawString(string, rectangle.x, n2);
                    n = rectangle.x + graphics.getFontMetrics().stringWidth(string);
                }
                else {
                    graphics.drawString(string, n, n2);
                    n += graphics.getFontMetrics().stringWidth(string);
                }
            }
        }
        return true;
    }
    
    protected Font m1(final String s, final String s2, final int n, final int n2) {
        final String parameter = this.getParameter(s);
        try {
            final int n3 = 0;
            final int index = parameter.indexOf("|", n3);
            final String substring = parameter.substring(n3, index);
            final int n4 = index + 1;
            final int index2 = parameter.indexOf("|", n4);
            final String trim = parameter.substring(n4, index2).trim();
            int n5 = 0;
            if (trim.indexOf("italic") != -1) {
                n5 |= 0x2;
            }
            if (trim.indexOf("bold") != -1) {
                n5 |= 0x1;
            }
            int int1 = n2;
            if (!s.equalsIgnoreCase("linkfont")) {
                int1 = Integer.parseInt(parameter.substring(index2 + 1).trim());
            }
            return new Font(substring, n5, int1);
        }
        catch (Throwable t) {
            return new Font(s2, n, n2);
        }
    }
    
    protected void m1() {
        final int a3 = this.a3 ? 1 : 0;
        final int n = this.a3 ? 2 : 0;
        final Graphics graphics = this.u.getGraphics();
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.setColor(this.g);
        graphics.fillRect(0, 0, width, height);
        int z = 0;
        Image s = this.s;
        if (s != null) {
            z = s.getWidth(null) * (height - n) / s.getHeight(null);
        }
        if (s != null && (this.w.statusAll(true) & 0x4) == 0x0) {
            graphics.drawImage(s, a3, a3, z, height - n, Color.white, null);
        }
        else {
            z = 0;
            s = null;
        }
        int n2 = z;
        if (this.a3 && s != null) {
            ++n2;
            graphics.setColor(Color.black);
            graphics.drawLine(n2, 0, n2, height);
        }
        graphics.setFont(this.h);
        final int x = n2 + graphics.getFontMetrics().charWidth(' ');
        final int height2 = graphics.getFontMetrics().getHeight();
        graphics.setColor(this.f);
        graphics.drawString(this.T[this.a], x, height2);
        graphics.setFont(this.j);
        graphics.setColor(this.i);
        this.m1(graphics, this.C[this.a], new Rectangle(x, height2, width - x, height));
        graphics.setFont(this.l);
        graphics.setColor(this.k);
        graphics.drawString(this.n, x, height - 3);
        final int n3 = x + graphics.getFontMetrics().stringWidth(this.n);
        graphics.drawLine(x, height - n, n3, height - n);
        final Rectangle x2 = this.x;
        x2.x = x;
        x2.y = height - n - graphics.getFontMetrics().getHeight();
        x2.width = n3 - x;
        x2.height = graphics.getFontMetrics().getHeight();
        graphics.setColor(Color.black);
        if (this.o) {
            if (this.r != null) {
                graphics.drawImage(this.r, width - 43, height - 14, Color.white, null);
            }
            else {
                final int n4 = height - 1;
                graphics.drawString("<<", width - 43, n4);
                graphics.drawString(">>", width - 29, n4);
                graphics.drawString(" ||", width - 15, n4);
            }
            final byte p = this.p;
            if (p != -1) {
                graphics.setXORMode(Color.white);
                graphics.fillRect(width - ((p == 0) ? 43 : ((p == 1) ? 29 : 15)), this.size().height - 14, 15, 13);
                graphics.setPaintMode();
            }
        }
        if (this.a3) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, width - 1, height - 1);
            this.q = false;
        }
        this.z = z;
    }
    
    protected void m1(final String s) {
        if (this.o) {
            this.q = true;
            final Graphics graphics = this.u.getGraphics();
            final int n = this.size().width - 43;
            final int n2 = this.size().height - 14;
            graphics.setColor(Color.white);
            graphics.fillRect(n, n2, 42, 13);
            graphics.setFont(new Font("Helvetica", 2, 11));
            graphics.setColor(Color.black);
            graphics.drawString(s, n + (42 - graphics.getFontMetrics().stringWidth(s)) / 2, this.size().height - 2);
            graphics.drawRect(n, n2, 42, 13);
            this.paint(this.getGraphics());
        }
    }
    
    protected void m1(final int n) {
        boolean e = this.e;
        int d = this.d;
        final Vector c = this.c;
        int n2 = this.a;
        final Image t = this.t;
        if (n == -1) {
            if (e) {
                --d;
            }
            else {
                e = true;
                d = c.size() - 2;
            }
            d = Math.max(0, d);
            if (c.size() > 0) {
                n2 = c.elementAt(d);
            }
        }
        else if (n == 1) {
            if (e) {
                if (++d >= c.size()) {
                    e = false;
                }
                else {
                    n2 = c.elementAt(d);
                }
            }
            if (!e) {
                if (this.b == 1) {
                    n2 = Math.max(Math.min(Math.abs(new Random().nextInt() % this.T.length), this.T.length - 1), 0);
                    c.addElement(new Integer(n2));
                }
                else if (this.b == 0) {
                    if (++n2 >= this.T.length) {
                        n2 = 0;
                    }
                    c.addElement(new Integer(n2));
                }
            }
        }
        final int min = Math.min(Math.max(0, n2), this.T.length - 1);
        Image image;
        try {
            image = this.getImage(this.getCodeBase(), this.I[min]);
        }
        catch (Throwable t2) {
            image = null;
        }
        this.w = new MediaTracker(this);
        if (image != null) {
            this.w.addImage(image, 0);
            try {
                if (image.getWidth(null) == -1 && this.s != null) {
                    this.m1("loading");
                }
                this.w.waitForAll();
                final int n3 = this.size().height - 2;
                (this.w = new MediaTracker(this)).addImage(image, 0, image.getWidth(null) * n3 / image.getHeight(null), n3);
            }
            catch (Throwable t3) {}
        }
        this.e = e;
        this.d = d;
        this.a = min;
        this.t = image;
    }
    
    public iabIASYScom() {
        this.T = new String[0];
        this.I = new String[0];
        this.C = new String[0];
        this.L = new String[0];
        this.f = Color.black;
        this.g = Color.orange;
        this.i = Color.black;
        this.k = Color.blue;
        this.m = "_self";
        this.n = "Click here";
        this.o = true;
        this.a2 = 10000;
        this.a3 = true;
        this.p = -1;
        this.q = false;
    }
}

import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import netscape.javascript.JSObject;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.Vector;
import menu_v3_0.g;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import menu_v3_0.c;
import java.awt.Color;
import menu_v3_0.a.a.a;
import menu_v3_0.e;
import java.applet.AudioClip;
import java.awt.event.MouseEvent;
import menu_v3_0.f;
import java.awt.Panel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import menu_v3_0.a.a.b;
import menu_v3_0.MenuCanvasListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Menu3_0 extends Applet implements Runnable, MenuCanvasListener, b, MouseListener, MouseMotionListener
{
    private final int n = 0;
    private final int s = 5;
    private final int t = 10;
    private String e;
    private String a;
    private String q;
    private String do;
    private String r;
    private byte[] z;
    private Panel int;
    private f else;
    private menu_v3_0.b try;
    private MouseEvent D;
    private String void;
    private String g;
    private String long;
    private String h;
    private String new;
    private Applet i;
    private AudioClip B;
    private boolean k;
    private e j;
    private e p;
    private a o;
    private a if;
    private int for;
    private int case;
    private String E;
    private String C;
    private int l;
    private Color f;
    private Color byte;
    private Color u;
    private Color goto;
    private Color c;
    private Color b;
    private Color m;
    private int d;
    private int char;
    private int A;
    private int w;
    private Thread v;
    private boolean null;
    
    public synchronized void init() {
        if (this.null) {
            return;
        }
        System.out.println(this.a);
        final String q = this.q;
        final String parameter = this.getParameter("Author");
        if (parameter == null || q == null) {
            return;
        }
        if (!q.replace('i', 'I').toUpperCase().equals(parameter.replace('i', 'I').toUpperCase())) {
            return;
        }
        if (!this.e.equals(new String(this.z))) {
            return;
        }
        this.else = new f(this.getParameter("LoadingMessage"), menu_v3_0.c.a(this.getParameter("LoadingBGColor"), Color.black), menu_v3_0.c.a(this.getParameter("LoadingTextColor"), Color.white), menu_v3_0.c.a(this.getParameter("DisplayProgressBar"), true), menu_v3_0.c.a(this.getParameter("ProgressBarColor"), new Color(12632268)), menu_v3_0.c.a(this.getParameter("ProgressBarHeight"), 12));
        this.setLayout(new BorderLayout());
        this.int.add("Center", this.else);
        this.add("Center", this.int);
        (this.v = new Thread(this)).start();
        this.null = true;
    }
    
    public synchronized void start() {
    }
    
    public synchronized void stop() {
    }
    
    public synchronized void destroy() {
        if (this.v != null) {
            this.v = null;
        }
        if (this.else != null) {
            this.else.removeMouseListener(this);
            this.else.removeMouseMotionListener(this);
        }
        if (this.o != null) {
            this.o.for();
        }
        if (this.if != null) {
            this.if.for();
        }
    }
    
    private AudioClip if(final String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        return this.getAudioClip(this.getCodeBase(), s);
    }
    
    private int for(String s) {
        if (s != null) {
            s = s.replace('i', 'I');
            s = s.toUpperCase();
            if (s.equals("ALWAYS")) {
                return 10;
            }
            if (s.equals("NEVER")) {
                return 0;
            }
            if (s.equals("AS_NEEDED")) {
                return 5;
            }
        }
        return 5;
    }
    
    private void do() {
        this.new = this.getParameter("ImageAppletName");
        if (this.new == null || this.new.equals("")) {
            this.new = null;
        }
        this.else.if = menu_v3_0.c.a(this.getParameter("AutoCloseMenus"), this.else.if);
        this.else.case = menu_v3_0.c.a(this.getParameter("DisplayBar"), this.else.case);
        this.else.q = menu_v3_0.c.a(this.getParameter("DisplayConnectionLines"), this.else.q);
        this.k = menu_v3_0.c.a(this.getParameter("OverwriteDefaultImage"), this.k);
        this.void = this.getParameter("MouseButton2Target");
        if (this.void == null || this.void.equals("")) {
            this.void = null;
        }
        this.long = this.getParameter("MouseButton3Target");
        if (this.long == null || this.long.equals("")) {
            this.long = null;
        }
        this.g = this.getParameter("MouseButton2Target2");
        if (this.g == null || this.g.equals("")) {
            this.g = null;
        }
        this.h = this.getParameter("MouseButton3Target2");
        if (this.h == null || this.h.equals("")) {
            this.h = null;
        }
        this.else.D = menu_v3_0.c.a(this.getParameter("BGColor"), this.else.D);
        this.else.L = menu_v3_0.c.a(this.getParameter("TextColor"), this.else.L);
        this.else.char = menu_v3_0.c.a(this.getParameter("BarColor"), this.else.char);
        this.else.int = menu_v3_0.c.a(this.getParameter("BarTextColor"), this.else.int);
        this.else.new = menu_v3_0.c.a(this.getParameter("ActiveTextColor"), this.else.new);
        this.else.long = menu_v3_0.c.a(this.getParameter("TextShadowColor"), this.else.long);
        this.else.do = menu_v3_0.c.a(this.getParameter("ActiveTextShadowColor"), this.else.do);
        this.else.u = menu_v3_0.c.a(this.getParameter("BarTextShadowColor"), this.else.u);
        this.else.R = menu_v3_0.c.a(this.getParameter("LineColor"), this.else.R);
        this.else.w = menu_v3_0.c.a(this.getParameter("ConnectionLinesColor"), this.else.w);
        this.else.goto = menu_v3_0.c.a(this.getParameter("ConnectionRectangleColor"), this.else.goto);
        this.else.e = menu_v3_0.c.a(this.getParameter("ConnectionRectangleSymbolColor"), this.else.e);
        this.else.a = menu_v3_0.c.a(this.getParameter("TopMargin"), this.else.a);
        this.else.H = menu_v3_0.c.a(this.getParameter("LeftMargin"), this.else.H);
        this.else.K = menu_v3_0.c.a(this.getParameter("TopBarExtension"), this.else.K);
        this.else.A = menu_v3_0.c.a(this.getParameter("LeftBarExtension"), this.else.A);
        this.else.c = menu_v3_0.c.a(this.getParameter("RightBarExtension"), this.else.c);
        this.else.t = menu_v3_0.c.a(this.getParameter("BottomBarExtension"), this.else.t);
        this.else.d = menu_v3_0.c.a(this.getParameter("TopTopicMargin"), this.else.d);
        this.else.r = menu_v3_0.c.a(this.getParameter("BottomTopicMargin"), this.else.r);
        this.else.T = menu_v3_0.c.a(this.getParameter("LeftTextMargin"), this.else.T);
        this.else.v = menu_v3_0.c.a(this.getParameter("LayerIndent"), this.else.v);
        this.else.m = menu_v3_0.c.a(this.getParameter("ReservedSymbolWidth"), this.else.m);
        this.else.else = menu_v3_0.c.a(this.getParameter("ConnectionRectangleWidth"), this.else.else);
        this.else.else = Math.max(this.else.else, 7);
        this.else.z = menu_v3_0.c.a(this.getParameter("ConnectionRectangleHeight"), this.else.z);
        this.else.z = Math.max(this.else.z, 7);
        this.else.E = menu_v3_0.c.a(this.getParameter("ConnectionRectangleRightMargin"), this.else.E);
        if (this.try != null) {
            this.try.if = menu_v3_0.c.a(this.getParameter("ArrowLeftMargin"), this.try.if);
            this.try.try = menu_v3_0.c.a(this.getParameter("ArrowRightMargin"), this.try.try);
            this.try.goto = menu_v3_0.c.a(this.getParameter("ArrowBGColor"), this.else.D);
        }
        this.case = this.for(this.getParameter("VScrollBar"));
        this.for = this.for(this.getParameter("HScrollBar"));
        this.C = this.getParameter("VScrollBarPosition");
        if (this.C != null) {
            this.C = this.C.replace('i', 'I');
            this.C = this.C.toUpperCase();
        }
        else {
            this.C = "";
        }
        this.E = this.getParameter("HScrollBarPosition");
        if (this.E != null) {
            this.E = this.E.replace('i', 'I');
            this.E = this.E.toUpperCase();
        }
        else {
            this.E = "";
        }
        this.l = menu_v3_0.c.a(this.getParameter("ScrollBarWidth"), this.l);
        this.char = menu_v3_0.c.a(this.getParameter("VScrollBarLeftMargin"), this.char);
        this.w = menu_v3_0.c.a(this.getParameter("VScrollBarRightMargin"), this.w);
        this.d = menu_v3_0.c.a(this.getParameter("HScrollBarTopMargin"), this.d);
        this.A = menu_v3_0.c.a(this.getParameter("HScrollBarBottomMargin"), this.A);
        this.f = menu_v3_0.c.a(this.getParameter("ScrollBarBGColor"), this.f);
        this.byte = menu_v3_0.c.a(this.getParameter("ScrollBarButtonColor"), this.byte);
        this.u = menu_v3_0.c.a(this.getParameter("ScrollBarSliderColor"), this.u);
        this.goto = menu_v3_0.c.a(this.getParameter("ScrollBarLineColor"), this.goto);
        this.c = menu_v3_0.c.a(this.getParameter("ScrollBarArrowColor"), this.c);
        this.b = menu_v3_0.c.a(this.getParameter("ScrollBarActiveArrowColor"), this.b);
        this.m = menu_v3_0.c.a(this.getParameter("ScrollBarUnusedSpaceColor"), this.m);
        final String parameter = this.getParameter("OpenSound");
        final String parameter2 = this.getParameter("CloseSound");
        final String parameter3 = this.getParameter("LoadSound");
        this.else.k = ((parameter == null || parameter.equals("")) ? null : this.getAudioClip(this.getCodeBase(), parameter));
        if (parameter != null && parameter.equals(parameter2)) {
            this.else.for = this.else.k;
        }
        else {
            this.else.for = ((parameter2 == null || parameter2.equals("")) ? null : this.getAudioClip(this.getCodeBase(), parameter2));
        }
        if (parameter != null && parameter.equals(parameter3)) {
            this.B = this.else.k;
            return;
        }
        if (parameter2 != null && parameter2.equals(parameter3)) {
            this.B = this.else.for;
            return;
        }
        this.B = ((parameter3 == null || parameter3.equals("")) ? null : this.getAudioClip(this.getCodeBase(), parameter3));
    }
    
    private Image a(final MediaTracker mediaTracker, final String s, final int n) {
        Image image = null;
        if (mediaTracker != null && s != null && !s.equals("")) {
            image = this.getImage(this.getCodeBase(), s);
            if (image == null) {
                return image;
            }
            mediaTracker.addImage(image, n);
            final Image image2 = this.createImage(1, 1);
            final Graphics graphics = image2.getGraphics();
            graphics.setClip(0, 0, 1, 1);
            graphics.drawImage(image, 0, 0, this);
            try {
                mediaTracker.waitForID(n);
            }
            catch (InterruptedException ex) {
                return null;
            }
            if (image2 != null) {
                image2.flush();
            }
        }
        return image;
    }
    
    public void run() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Thread currentThread = Thread.currentThread();
        int width = 0;
        final String[] array = { null };
        int n = 0;
        currentThread.setPriority(5);
        this.else.if(n);
        array[0] = this.getParameter("Symbols");
        final Vector a = menu_v3_0.c.a(array, this.getCodeBase(), false);
        final int n2 = 90 / (a.size() + 8);
        int i;
        for (i = 0; i < a.size(); ++i) {
            final g g = a.elementAt(i);
            g.if = this.a(mediaTracker, g.do, i);
            if (currentThread != this.v) {
                return;
            }
            n += n2;
            this.else.if(n);
        }
        final Image a2 = this.a(mediaTracker, this.getParameter("BGImage"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n3 = n + n2;
        this.else.if(n3);
        final Image a3 = this.a(mediaTracker, this.getParameter("OpenFolderSymbol"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n4 = n3 + n2;
        this.else.if(n4);
        final Image a4 = this.a(mediaTracker, this.getParameter("ClosedFolderSymbol"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n5 = n4 + n2;
        this.else.if(n5);
        final Image a5 = this.a(mediaTracker, this.getParameter("BarFolderSymbol"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n6 = n5 + n2;
        this.else.if(n6);
        final Image a6 = this.a(mediaTracker, this.getParameter("LoadedDocumentSymbol"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n7 = n6 + n2;
        this.else.if(n7);
        final Image a7 = this.a(mediaTracker, this.getParameter("DocumentSymbol"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n8 = n7 + n2;
        this.else.if(n8);
        final Image a8 = this.a(mediaTracker, this.getParameter("BarDocumentSymbol"), ++i);
        if (currentThread != this.v) {
            return;
        }
        final int n9 = n8 + n2;
        this.else.if(n9);
        final Image a9 = this.a(mediaTracker, this.getParameter("ArrowImage"), ++i);
        if (currentThread != this.v) {
            return;
        }
        this.else.if(n9 + n2);
        String parameter = this.getParameter("DefaultTarget");
        if (parameter == null || parameter.equals("")) {
            parameter = null;
        }
        String parameter2 = this.getParameter("DefaultTarget2");
        if (parameter2 == null || parameter2.equals("")) {
            parameter2 = null;
        }
        array[0] = this.getParameter("Fonts");
        final Vector a10 = menu_v3_0.c.a(array);
        final String parameter3 = this.getParameter("MenuFile");
        if (parameter3 != null && parameter3.length() > 0) {
            array[0] = menu_v3_0.c.a(this.getCodeBase(), parameter3);
        }
        else {
            array[0] = this.getParameter("Menu");
        }
        final Vector a11 = menu_v3_0.c.a(array, null, a, a10, a3, a4, a5, a7, a6, a8, parameter, parameter2);
        this.else.if(100);
        if (menu_v3_0.c.a(this.getParameter("DisplayArrow"), true)) {
            final Color a12 = menu_v3_0.c.a(this.getParameter("ArrowColor"), null);
            int n10 = menu_v3_0.c.a(this.getParameter("ArrowWidth"), -1);
            int n11 = menu_v3_0.c.a(this.getParameter("ArrowHeight"), -1);
            if (n10 < 0 || n11 < 0) {
                if (a9 == null) {
                    final FontMetrics fontMetrics = this.getFontMetrics(a10.elementAt(0).do);
                    n10 = fontMetrics.charWidth('W');
                    n11 = fontMetrics.getAscent();
                }
                else {
                    n10 = a9.getWidth(this);
                    n11 = a9.getHeight(this);
                }
            }
            this.try = new menu_v3_0.b(a9, a12, n10, n11, a2);
            width = this.try.getPreferredSize().width;
        }
        if (currentThread != this.v) {
            return;
        }
        this.do();
        if (this.try != null) {
            this.int.add("West", this.try);
            this.int.doLayout();
        }
        if (currentThread != this.v) {
            return;
        }
        this.else.a(a11, a, a10, a2, width, a3, a4, a5, a7, a6, a8, this);
        this.else.addMouseListener(this);
        this.else.addMouseMotionListener(this);
    }
    
    public synchronized void a(final a a, final long n) {
        if (a == this.o) {
            this.else.do((int)n);
            return;
        }
        if (a == this.if) {
            this.else.a((int)n);
        }
    }
    
    public void a() {
    }
    
    public void if() {
    }
    
    public synchronized void a(final Dimension dimension) {
        final Dimension size = this.getSize();
        boolean b = false;
        boolean b2 = false;
        Dimension preferredSize;
        if (this.try != null) {
            preferredSize = this.try.getPreferredSize();
        }
        else {
            preferredSize = new Dimension(0, 0);
        }
        int n = size.width - preferredSize.width;
        int height = size.height;
        if ((n < dimension.width && this.for != 0) || this.for == 10) {
            b2 = true;
            height -= this.l + this.d + this.A;
            if ((height < dimension.height && this.case != 0) || this.case == 10) {
                b = true;
                n -= this.l + this.char + this.w;
            }
        }
        else if ((height < dimension.height && this.case != 0) || this.case == 10) {
            b = true;
            n -= this.l + this.char + this.w;
            if ((n < dimension.width && this.for != 0) || this.for == 10) {
                b2 = true;
                height -= this.l + this.d + this.A;
            }
        }
        if (b) {
            if (this.o == null) {
                (this.o = new a(0, this.l)).do(this.char);
                this.o.try(this.w);
                this.o.a(this.byte, this.u, this.goto, this.f, this.c, this.b, this.m);
                if (this.C.equals("LEFT")) {
                    this.add("West", this.o);
                }
                else {
                    this.add("East", this.o);
                }
                this.o.a(this);
            }
            this.o.a(height);
            this.o.a((long)dimension.height);
            this.o.for(height);
            this.o.if((long)this.else.do());
        }
        else if (this.case != 10 && this.o != null) {
            this.o.for();
            this.remove(this.o);
            this.o = null;
            this.else.do(0);
        }
        if (b2) {
            if (this.if == null) {
                (this.if = new a(10, this.l)).if(this.d);
                this.if.int(this.A);
                this.if.a(this.byte, this.u, this.goto, this.f, this.c, this.b, this.m);
                if (this.E.equals("TOP")) {
                    this.add("North", this.if);
                }
                else {
                    this.add("South", this.if);
                }
                this.if.a(this);
            }
            if (b) {
                if (this.C.equals("LEFT")) {
                    this.if.new(this.l + this.char + this.w);
                }
                this.if.a(size.width - this.l - this.char - this.w);
            }
            else {
                this.if.new(0);
                this.if.a(size.width);
            }
            this.if.a((long)dimension.width);
            this.if.for(n);
            final int n2 = (int)this.if.a();
            if (n2 > 0 && dimension.width - n2 < n) {
                int n3 = dimension.width - n;
                if (n3 < 0) {
                    n3 = 0;
                }
                this.if.if((long)n3);
                this.else.a(n3);
            }
        }
        else if (this.for != 10 && this.if != null) {
            this.if.for();
            this.remove(this.if);
            this.if = null;
            this.else.a(0);
        }
        if (this.D != null && this.try != null) {
            final MouseEvent d = this.D;
            if (this.else.if(d.getPoint().x, d.getPoint().y) == null) {
                this.try.a(-1);
            }
        }
        this.validate();
    }
    
    private void do(final String s) {
        try {
            if (this.i == null && this.new != null) {
                this.i = this.getAppletContext().getApplet(this.new);
            }
            if (this.i == null || !(this.i instanceof DisplayImage3_0)) {
                this.i = null;
                return;
            }
            if (s == null) {
                ((DisplayImage3_0)this.i).a();
                return;
            }
            ((DisplayImage3_0)this.i).a(s);
        }
        catch (Exception ex) {
            this.i = null;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent d) {
        final e do1 = this.else.do(d.getPoint().x, d.getPoint().y);
        this.D = d;
        if (do1 != null) {
            if (this.try != null) {
                this.try.a(d.getPoint().y);
            }
            this.do(do1.long);
            this.showStatus(do1.if);
            return;
        }
        if (this.try != null) {
            this.try.a(-1);
        }
        if (this.j == null || !this.k) {
            this.do(null);
        }
        else {
            this.do(this.j.long);
        }
        this.showStatus("");
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent d) {
        this.D = d;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.D = null;
        this.else.a();
        this.showStatus("");
        if (this.try != null) {
            this.try.a();
        }
        if (this.j == null || !this.k) {
            this.do(null);
            return;
        }
        this.do(this.j.long);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.p = this.else.if(mouseEvent.getPoint().x, mouseEvent.getPoint().y);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        String s = null;
        String s2 = null;
        if (this.p != null && this.p == this.else.if(mouseEvent.getPoint().x, mouseEvent.getPoint().y)) {
            final int modifiers = mouseEvent.getModifiers();
            if ((modifiers & 0x10) == 0x10) {
                s = this.p.g;
                s2 = this.p.try;
            }
            else if (this.void != null && (modifiers & 0x8) == 0x8) {
                s = this.void;
                s2 = this.g;
            }
            else if (this.long != null && (modifiers & 0x4) == 0x4) {
                s = this.long;
                s2 = this.h;
            }
            if (s == null) {
                s = this.p.g;
            }
            if (s2 == null) {
                s2 = this.p.try;
            }
            if ((modifiers & 0x10) == 0x10) {
                this.else.a(mouseEvent.getPoint().x, mouseEvent.getPoint().y, s, s2);
                this.a(mouseEvent.getPoint().y, s, s2, this.p, true);
            }
            else {
                this.a(mouseEvent.getPoint().y, s, s2, this.p, false);
            }
        }
        this.p = null;
    }
    
    private void a(final String s) {
        if (s != null) {
            new a(JSObject.getWindow((Applet)this), s).start();
        }
    }
    
    private void a(final int n, final String s, final String s2, final e e, final boolean b) {
        if (e != null && ((e.f != null && s != null) || (e.int != null && s2 != null))) {
            if (b) {
                this.j = e;
            }
            final AppletContext appletContext = this.getAppletContext();
            try {
                if (e.f != null && s != null) {
                    appletContext.showDocument(new URL(this.getCodeBase(), e.f), s);
                }
                if (e.int != null && s2 != null) {
                    appletContext.showDocument(new URL(this.getCodeBase(), e.int), s2);
                }
            }
            catch (MalformedURLException ex) {}
            if (this.B != null) {
                this.B.play();
            }
        }
        if (e != null && e.a != null) {
            if (b) {
                this.j = e;
            }
            this.a(e.a);
        }
        if (b && this.else.for() < n) {
            if (this.try != null) {
                this.try.a();
            }
            if (this.j == null || !this.k) {
                this.do(null);
                return;
            }
            this.do(this.j.long);
        }
    }
    
    public Menu3_0() {
        this.e = "(c) exsys GbR - software@exsys.net";
        this.a = "JTM - Java Tree Menu v3.0 - (c) exsys GbR Emden - www.exsys.net";
        this.q = "JTM - Java Tree Menu - (c) exsys GbR Emden - www.exsys.net";
        this.do = "by Raul Molino Garcia";
        this.r = "unregistered";
        this.z = new byte[] { 40, 99, 41, 32, 101, 120, 115, 121, 115, 32, 71, 98, 82, 32, 45, 32, 115, 111, 102, 116, 119, 97, 114, 101, 64, 101, 120, 115, 121, 115, 46, 110, 101, 116 };
        this.int = new Panel(new BorderLayout());
        this.k = true;
        this.for = 5;
        this.case = 5;
        this.E = "BOTTOM";
        this.C = "RIGHT";
        this.l = 9;
        this.null = false;
    }
}

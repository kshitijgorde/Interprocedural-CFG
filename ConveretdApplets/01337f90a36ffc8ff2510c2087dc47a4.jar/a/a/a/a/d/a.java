// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.d;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.StringTokenizer;
import a.a.a.a.b.e;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Container;
import java.util.Date;
import java.awt.Frame;
import a.a.a.a.a.b;
import a.a.a.a.b.d;
import a.a.a.a.b.f;
import java.awt.Image;
import a.a.a.a.c.a.c;
import java.applet.Applet;

public abstract class a extends Applet implements c
{
    protected a.a.a.a.e.a z;
    protected a.a.a.a.c.a.a r;
    protected Image t;
    protected f C;
    protected d F;
    protected b B;
    private Frame s;
    protected boolean n;
    protected boolean E;
    protected int w;
    protected int v;
    protected int A;
    protected int m;
    protected Image D;
    protected Date o;
    protected String q;
    protected int u;
    protected int p;
    protected int l;
    
    public abstract void Add_hotspot(final String p0);
    
    public void CenterZoomIn() {
        final a.a.a.a.b.a a = (a.a.a.a.b.a)this.z.case("command/action/zoomin");
        if (a != null) {
            a.a();
        }
    }
    
    public void CenterZoomOut() {
        final a.a.a.a.b.a a = (a.a.a.a.b.a)this.z.case("command/action/zoomout");
        if (a != null) {
            a.a();
        }
    }
    
    public void char() {
        this.s = null;
        if (this.B != null) {
            this.B.k();
            this.requestFocus();
        }
    }
    
    private void c() {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        if (container != null) {
            this.s = (Frame)container;
        }
    }
    
    public void HideHotSpots() {
        if (this.r != null) {
            this.r.try(0);
        }
    }
    
    public void Reset() {
        final a.a.a.a.b.a a = (a.a.a.a.b.a)this.z.case("command/action/reset");
        if (a != null) {
            a.a();
        }
    }
    
    public void ResetApplet() {
        if (this.B != null) {
            this.B.x();
            this.remove(this.B);
            this.B = null;
            System.gc();
        }
        this.c();
        this.case();
    }
    
    public abstract void Set_autospin(final String p0);
    
    public void Set_backgroundColor(final String s) {
        if (this.B != null) {
            this.B.long(s);
        }
    }
    
    public void Set_backgroundImage(final String s) {
        if (this.B != null) {
            this.B.else(s);
        }
    }
    
    public void Set_displayHeight(final String s) {
        if (this.B != null) {
            this.B.null(s);
        }
    }
    
    public void Set_displayWidth(final String s) {
        if (this.B != null) {
            this.B.null(s);
        }
    }
    
    public void do(final String s) {
        if (this.B != null) {
            this.B.void(s);
        }
    }
    
    public void int(final String s) {
        if (this.B != null) {
            this.B.try(s);
        }
    }
    
    public abstract void Set_file(final String p0);
    
    public void Set_foregroundFrame(final String s) {
        if (this.B != null) {
            this.B.new(s);
        }
    }
    
    public abstract void Set_initialView(final float p0, final float p1, final float p2, final float p3);
    
    public abstract void a(final int p0, final float p1, final float p2, final float p3, final float p4);
    
    public void Set_leftMargin(final String s) {
        if (this.B != null) {
            this.B.c(s);
        }
    }
    
    public abstract void Set_minZoomAngle(final String p0);
    
    public void Set_showHotspots(final String s) {
        if (s != null && s.equalsIgnoreCase("true")) {
            this.ShowHotSpots();
        }
    }
    
    public void Set_topMargin(final String s) {
        if (this.B != null) {
            this.B.char(s);
        }
    }
    
    public void ShowHotSpots() {
        if (this.r != null) {
            this.r.try(1);
        }
    }
    
    public void ShowInfo() {
        final a.a.a.a.b.a a = (a.a.a.a.b.a)this.z.case("command/action/info");
        if (a != null) {
            a.a();
        }
    }
    
    protected void goto() {
        if (this.B != null) {
            return;
        }
        this.add(this.B = this.long());
        this.z.a("player", this.B);
    }
    
    private void void() {
        this.D = (Image)this.z.case(new StringBuffer("images/button/fill").toString());
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.D, 0);
        new Thread(new a.a.a.a.b.b(new e(mediaTracker, 0)), "ImageLoader").start();
        this.for("load fill image from resource");
        final Dimension size = this.getSize();
        this.C = new f(this.z, "toolbar", this.z.char("resource/button.toolbar.width"), this.z.char("resource/button.height"), 0, 0, (a.a.a.a.b.a)this.z.case("command/state/toolbar"));
        if (this.n) {
            this.B.add(this.C);
        }
        else {
            this.add(this.C);
        }
        this.w = 0;
        this.F = new d();
        boolean booleanValue = (boolean)this.z.case("param/rotationcontrol");
        if (this.null().equals("d3") || this.null().equals("pano")) {
            if ((float)this.z.case("param/" + this.null() + ".autospin") == 0.0) {
                booleanValue = false;
            }
        }
        else {
            booleanValue = false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.z.a("resource/" + this.q + ".toolbar.buttons"), ":");
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (!trim.equals("separator")) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(trim, ",");
                final String trim2 = stringTokenizer2.nextToken().trim();
                final String trim3 = stringTokenizer2.nextToken().trim();
                final String trim4 = stringTokenizer2.nextToken().trim();
                if (!booleanValue) {
                    if (trim2.equals("rotateleft") || trim2.equals("pause")) {
                        continue;
                    }
                    if (trim2.equals("rotateright")) {
                        continue;
                    }
                }
                boolean b;
                if (trim3.equals("state")) {
                    b = false;
                }
                else {
                    b = true;
                }
                int n;
                if (trim4.equals("down")) {
                    n = 1;
                }
                else if (trim4.equals("up")) {
                    n = 0;
                }
                else {
                    n = -1;
                }
                a.a.a.a.b.a a;
                if (b) {
                    a = (a.a.a.a.b.a)this.z.case("command/action/" + trim2);
                }
                else {
                    a = (a.a.a.a.b.a)this.z.case("command/state/" + trim2);
                }
                final f f = new f(this.z, trim2, this.z.char("resource/button.width"), this.z.char("resource/button.height"), b ? 1 : 0, n, a);
                this.F.a(f);
                if (this.n) {
                    this.B.add(f);
                }
                else {
                    this.add(f);
                }
                ++this.w;
            }
        }
        if (this.n) {
            Point point = (Point)this.z.case("param/logopos");
            if (point == null) {
                point = new Point(0, size.height - this.m);
            }
            this.C.setBounds(point.x, point.y, this.v, this.m);
            Rectangle rectangle = (Rectangle)this.z.case("param/toolbarbounds");
            if (rectangle == null) {
                rectangle = new Rectangle(this.v, size.height - this.m, this.w * this.A, this.m);
            }
            this.F.a(rectangle);
            this.F.a(this.b());
        }
        else {
            this.C.setBounds(0, size.height - this.m, this.v, this.m);
            this.F.a(this.v, size.height - this.m, this.w * this.A, this.m);
            this.F.a(true);
        }
    }
    
    protected abstract b long();
    
    public void destroy() {
        this.if("destroy() ");
    }
    
    public String getAppletInfo() {
        return this.z.a("resource/info.product") + " version " + this.z.a("resource/info.product.version") + ", " + this.z.a("resource/info.copyright");
    }
    
    public boolean b() {
        if (!this.n) {
            return true;
        }
        switch (this.r.char()) {
            case 1: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected abstract String null();
    
    private String else() {
        String s = this.z.a("param/variant");
        if (s != null) {
            s = s.trim();
        }
        return s;
    }
    
    public void init() {
        this.if("init() ");
        this.setLayout(null);
        this.requestFocus();
    }
    
    public void invalidate() {
        super.invalidate();
        this.t = null;
    }
    
    public void for(final String s) {
        final Date o = new Date();
        final long n = o.getTime() - this.o.getTime();
        this.o = o;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.t == null) {
            this.t = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.t.getGraphics();
        graphics2.setClip(0, 0, size.width, size.height);
        if (!this.n && this.D != null) {
            graphics2.drawImage(this.D, this.v + this.w * this.A, size.height - this.m, size.width - this.w * this.A - this.v, this.m, this);
        }
        super.paint(graphics2);
        graphics.drawImage(this.t, 0, 0, null);
        graphics2.dispose();
    }
    
    public void a(final String s) {
        final a.a.a.a.b.a a = (a.a.a.a.b.a)this.z.case("command/action/" + s);
        if (a != null) {
            a.a();
        }
    }
    
    public void pressStateButton(final String s) {
        final a.a.a.a.b.a a = (a.a.a.a.b.a)this.z.case("command/state/" + s);
        if (a != null) {
            a.a();
        }
    }
    
    public void case() {
        this.if("start() ");
        final Dimension size = this.getSize();
        this.z.a("applet", this);
        this.for("Construct directory service");
        this.setBackground((Color)this.z.case("param/backgroundColor"));
        final String else1 = this.else();
        if (else1 != null) {
            this.q = String.valueOf(else1) + "." + this.null();
        }
        else {
            this.q = this.null();
        }
        this.goto();
        this.for("construct player");
        this.n = false;
        final String parameter = this.getParameter("toolbarstyle");
        if (parameter == null) {
            if (this.z.case("param/logopos") != null || this.z.case("param/toolbarbounds") != null) {
                this.n = true;
            }
        }
        else if (parameter.equals("floating")) {
            this.n = true;
        }
        this.v = this.z.char("resource/button.toolbar.width");
        this.A = this.z.char("resource/button.width");
        this.m = this.z.char("resource/button.height");
        if (this.n) {
            this.B.setBounds(0, 0, size.width, size.height);
        }
        else {
            this.B.setBounds(0, 0, size.width, size.height - this.m);
        }
        this.B.y();
        this.for("player start()");
        if (this.r != null) {
            this.r = null;
        }
        (this.r = (a.a.a.a.c.a.a)this.z.case("statemachine")).if(this);
        this.for("construct state machine");
        final String trim = this.z.a("resource/" + this.q + ".default.mode").trim();
        this.u = 2;
        if (trim.equals("zoomout")) {
            this.u = 3;
        }
        else if (trim.equals("pan")) {
            this.u = 4;
        }
        else if (trim.equals("rotate")) {
            this.u = 5;
        }
        this.l = 0;
        final String trim2 = this.z.a("resource/" + this.q + ".default.state.magnifier").trim();
        if (trim2.equals("on")) {
            this.l = 1;
        }
        else if (trim2.equals("disabled")) {
            this.l = -1;
        }
        this.r.if(this.u);
        this.r.int(this.l);
        this.E = false;
        if (this.n) {
            final String parameter2 = this.getParameter("toolbar");
            if (parameter2 == null) {
                this.r.do(0);
            }
            else {
                final String lowerCase = parameter2.trim().toLowerCase();
                if (lowerCase.equals("on")) {
                    this.r.do(1);
                }
                else if (lowerCase.equals("off")) {
                    this.r.do(0);
                }
                else {
                    this.r.do(-1);
                    this.E = true;
                }
            }
        }
        else {
            this.r.do(-1);
        }
        this.r.int();
        this.for("initialize statemachine");
        this.for("construct toolbar");
        System.out.println(this.getAppletInfo());
    }
    
    public void if(final String s) {
    }
    
    public void showStatus(final String s) {
        this.getAppletContext().showStatus(s);
    }
    
    public void start() {
        this.if("start() ");
        this.o = new Date();
        final Dimension size = this.getSize();
        (this.z = new a.a.a.a.e.a()).a("applet", this);
        this.for("Construct directory service");
        this.setBackground((Color)this.z.case("param/backgroundColor"));
        (this.r = (a.a.a.a.c.a.a)this.z.case("statemachine")).if(this);
        this.for("construct state machine");
        final String else1 = this.else();
        if (else1 != null) {
            this.q = String.valueOf(else1) + "." + this.null();
        }
        else {
            this.q = this.null();
        }
        this.goto();
        this.for("construct player");
        this.n = false;
        final String parameter = this.getParameter("toolbarstyle");
        if (parameter == null) {
            if (this.z.case("param/logopos") != null || this.z.case("param/toolbarbounds") != null) {
                this.n = true;
            }
        }
        else if (parameter.equals("floating")) {
            this.n = true;
        }
        this.v = this.z.char("resource/button.toolbar.width");
        this.A = this.z.char("resource/button.width");
        this.m = this.z.char("resource/button.height");
        if (this.n) {
            this.B.setBounds(0, 0, size.width, size.height);
        }
        else {
            this.B.setBounds(0, 0, size.width, size.height - this.m);
        }
        this.B.y();
        this.for("player start()");
        final String trim = this.z.a("resource/" + this.q + ".default.mode").trim();
        this.u = 2;
        if (trim.equals("zoomout")) {
            this.u = 3;
        }
        else if (trim.equals("pan")) {
            this.u = 4;
        }
        else if (trim.equals("rotate")) {
            this.u = 5;
        }
        this.l = 0;
        final String trim2 = this.z.a("resource/" + this.q + ".default.state.magnifier").trim();
        if (trim2.equals("on")) {
            this.l = 1;
        }
        else if (trim2.equals("disabled")) {
            this.l = -1;
        }
        this.r.if(this.u);
        this.r.int(this.l);
        this.E = false;
        if (this.n) {
            final String parameter2 = this.getParameter("toolbar");
            if (parameter2 == null) {
                this.r.do(0);
            }
            else {
                final String lowerCase = parameter2.trim().toLowerCase();
                if (lowerCase.equals("on")) {
                    this.r.do(1);
                }
                else if (lowerCase.equals("off")) {
                    this.r.do(0);
                }
                else {
                    this.r.do(-1);
                    this.E = true;
                }
            }
        }
        else {
            this.r.do(-1);
        }
        this.r.int();
        this.for("initialize statemachine");
        if (!this.E) {
            this.void();
        }
        this.for("construct toolbar");
        System.out.println(this.getAppletInfo());
    }
    
    public void a(final a.a.a.a.c.a.b b) {
        final a.a.a.a.c.a.a a = (a.a.a.a.c.a.a)b.getSource();
        if (this.n) {
            final boolean b2 = a.char() == 1;
            if (this.F != null) {
                this.F.a(b2);
            }
        }
        this.repaint();
    }
    
    public void stop() {
        this.if("stop() ");
        if (this.C != null) {
            this.C.do();
            this.C = null;
        }
        if (this.F != null) {
            this.F.if();
            this.F = null;
        }
        if (this.D != null) {
            this.D.flush();
            this.D = null;
        }
        this.z.int("images/toolbar");
        if (this.B != null) {
            this.B.m();
            this.z.int("player");
            this.B = null;
        }
        this.z.int("statemachine");
        this.r = null;
        this.z.a();
        this.z = null;
        this.removeAll();
        System.gc();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}

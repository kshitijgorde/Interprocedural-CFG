// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.FontMetrics;
import com.daysofwonder.util.K;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.util.Enumeration;
import com.daysofwonder.util.t;
import com.daysofwonder.util.B;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Point;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.a;
import com.daysofwonder.b.b;
import java.awt.Font;
import java.awt.Dimension;

public abstract class ap
{
    protected Dimension D;
    protected static Font E;
    protected static Font F;
    protected static Font G;
    protected static Font H;
    protected static Font I;
    protected static Font J;
    protected static Font K;
    protected static Font L;
    protected b M;
    protected b N;
    protected b O;
    protected a P;
    protected Dimension Q;
    protected am R;
    protected int S;
    protected int T;
    protected int U;
    protected UIProperties V;
    protected UIProperties W;
    protected boolean X;
    protected boolean Y;
    protected com.daysofwonder.applet.a Z;
    protected aH aa;
    protected Point ab;
    protected Point ac;
    protected Point ad;
    protected boolean ae;
    protected ac af;
    protected int ag;
    protected Hashtable ah;
    protected Vector[] ai;
    protected am aj;
    protected Object ak;
    protected String al;
    protected aE am;
    protected am an;
    protected boolean ao;
    protected Vector ap;
    protected String aq;
    protected String ar;
    protected Color as;
    protected Color at;
    protected Color au;
    protected Color av;
    protected boolean aw;
    protected boolean ax;
    protected Vector ay;
    protected String az;
    protected String aA;
    protected Color aB;
    protected Color aC;
    protected Color aD;
    protected Color aE;
    protected String aF;
    protected boolean aG;
    protected boolean aH;
    protected boolean aI;
    protected Thread aJ;
    protected int aK;
    protected boolean aL;
    protected boolean aM;
    protected w aN;
    protected int aO;
    protected Component aP;
    protected boolean aQ;
    protected am aR;
    protected B aS;
    protected B aT;
    private int a;
    private int b;
    private Hashtable c;
    protected com.daysofwonder.util.a aU;
    protected com.daysofwonder.util.a aV;
    private am d;
    private String e;
    private Point f;
    private int g;
    private int h;
    
    public ap(final int n, final int n2) {
        this.S = -1;
        this.T = 1;
        this.U = -1;
        this.ag = 0;
        this.ah = new Hashtable();
        this.ai = new Vector[10];
        this.aH = true;
        this.aO = 15;
        this.aQ = true;
        this.a = 0;
        this.b = 0;
        this.g = 0;
        this.h = 0;
        this.D = new Dimension(n, n2);
        for (int i = 0; i < 10; ++i) {
            this.ai[i] = new Vector();
        }
    }
    
    public void d(final boolean aq) {
        this.aQ = aq;
    }
    
    public void a(final Component ap) {
        this.aP = ap;
    }
    
    public Component p() {
        return this.aP;
    }
    
    public void q() {
        if (com.daysofwonder.applet.ap.E == null) {
            com.daysofwonder.applet.ap.E = new Font("dialog", 0, 12);
            com.daysofwonder.applet.ap.H = new Font("dialog", 0, 10);
            com.daysofwonder.applet.ap.G = new Font("dialog", 1, 10);
            com.daysofwonder.applet.ap.I = new Font("dialog", 0, 11);
            com.daysofwonder.applet.ap.F = new Font("dialog", 1, 11);
            com.daysofwonder.applet.ap.J = new Font("dialog", 1, 12);
            com.daysofwonder.applet.ap.K = new Font("dialog", 1, 14);
            com.daysofwonder.applet.ap.L = new Font("dialog", 1, 18);
        }
        final Enumeration a = this.W.a();
        while (a.hasMoreElements()) {
            final String s = a.nextElement();
            if (s.endsWith(".control")) {
                t.a("loading control: " + s);
                final am a2 = this.a(s);
                if (a2 == null) {
                    continue;
                }
                this.ai[a2.m()].addElement(a2);
            }
        }
        if (this.aQ) {
            final int a3 = this.W.a("fps", 35);
            (this.aN = new w()).a((a3 > 0) ? (1000 / a3) : 100);
            this.aN.a(true, a3);
            this.aN.a();
        }
        if (this.Y) {
            t.a("Creating tooltip show timer");
            this.aU = new com.daysofwonder.util.a(com.daysofwonder.util.a.a, 1000L, false);
            this.aT = new M(this);
            this.aU.a(this.aT);
            this.aV = new com.daysofwonder.util.a(com.daysofwonder.util.a.a, 10000L, false);
            this.aS = new ah(this);
            this.aV.a(this.aS);
        }
        t.a("is running...");
        this.ae = true;
        if (this.aQ) {
            (this.aJ = new Thread(new av(this))).start();
            this.a(this.D);
        }
    }
    
    public void b() {
        this.q();
    }
    
    public w r() {
        return this.aN;
    }
    
    public am a(final String s, final String s2) {
        return this.b(s, s2, this.W, this.V);
    }
    
    public am a(final String s) {
        return this.b(s, ".control", this.W, this.V);
    }
    
    public am b(final String s, final String s2, final UIProperties uiProperties, final UIProperties uiProperties2) {
        return this.a(s, s2, uiProperties, uiProperties2, false);
    }
    
    public am a(final String s, final String s2, final UIProperties uiProperties, final UIProperties uiProperties2, final boolean b) {
        am a = null;
        try {
            final String substring = s.substring(0, s.indexOf(s2));
            final String b2 = uiProperties.b(s);
            if (b2.equalsIgnoreCase("tooltip")) {
                return null;
            }
            if (b2.equalsIgnoreCase("button")) {
                a = new al(this, substring, uiProperties, uiProperties2, b);
            }
            else if (b2.equalsIgnoreCase("alertbutton")) {
                a = new al(this, substring, uiProperties, uiProperties2, true);
            }
            else if (b2.equalsIgnoreCase("statebutton")) {
                a = new r(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("help")) {
                a = new U(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("chat")) {
                a = new h(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("text")) {
                a = new l(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("oldtext")) {
                a = new ab(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("background")) {
                a = new aD(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("solidbackground")) {
                a = new ax(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("textbutton")) {
                a = new Q(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("radio")) {
                a = new E(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("combobox")) {
                a = new aB(this, substring, uiProperties, uiProperties2);
            }
            else if (b2.equalsIgnoreCase("htmltext")) {
                a = new q(this, substring, uiProperties, uiProperties2);
            }
            else {
                a = this.a(b2, substring, uiProperties, uiProperties2);
            }
            if (a != null && "false".equalsIgnoreCase(uiProperties.b(substring + ".visible"))) {
                a.a(false);
            }
            if (a.q() != null) {
                this.c(a);
            }
        }
        catch (Exception ex) {
            t.a(ex);
        }
        return a;
    }
    
    public abstract am a(final String p0, final String p1, final UIProperties p2, final UIProperties p3);
    
    private void c(final am am) {
        if (am.r() != null) {
            final Vector r = am.r();
            for (int i = 0; i < r.size(); ++i) {
                Vector<am> vector = this.ah.get(r.elementAt(i));
                if (vector == null) {
                    vector = new Vector<am>();
                    this.ah.put(r.elementAt(i), vector);
                }
                vector.addElement(am);
            }
        }
        else if (am.q() != null) {
            Vector<am> vector2 = this.ah.get(am.q());
            if (vector2 == null) {
                vector2 = new Vector<am>();
                this.ah.put(am.q(), vector2);
            }
            vector2.addElement(am);
        }
    }
    
    public Dimension s() {
        return this.D;
    }
    
    public abstract void a(final Object p0, final String p1, final InputEvent p2);
    
    public synchronized void b(final a p) {
        if (!this.ae) {
            return;
        }
        if (!this.aQ) {
            this.P = p;
        }
        this.a(this.aP.getSize());
        if (this.P != null) {
            final Shape f = p.f();
            final Shape f2 = this.P.f();
            this.P.a(f);
            this.t();
            this.a(f);
            if (!this.aQ) {
                this.h(p);
                if (this.am != null) {
                    final z a = y.a(p, this);
                    final am b = this.b(this.ad);
                    boolean a2 = false;
                    if (b != null) {
                        a2 = b.a(this.ad, this.ak, this.aj, this.al);
                    }
                    this.am.a(a, null, this.ab, this.ac, this.ad, 1, a2);
                    a.c();
                }
            }
            this.P.a(f2);
            this.d(p);
        }
        if (!this.aQ) {
            this.P = null;
        }
    }
    
    public synchronized void c(final a a) {
        this.b(a);
    }
    
    public void t() {
        if (this.N != null) {
            this.P.a(this.N, 0, 0, null);
        }
    }
    
    public void a(final Shape shape) {
        Rectangle bounds = null;
        if (shape != null) {
            bounds = shape.getBounds();
        }
        int n = 0;
        for (int i = 0; i < 10; ++i) {
            if (i == 9 && this.ao) {
                this.e(this.P);
            }
            else if (i == 9 && this.aw) {
                this.f(this.P);
            }
            else {
                final int size = this.ai[i].size();
                final Vector vector = this.ai[i];
                for (int j = 0; j < size; ++j) {
                    final am am = vector.elementAt(j);
                    if (bounds == null || (bounds != null && bounds.intersects(am.p()))) {
                        if (this.R != null && am == this.R && this.R.d_()) {
                            n = 1;
                        }
                        else if (am.h() && (am.i() == -1 || this.F() == am.i())) {
                            am.a(this.P);
                        }
                    }
                }
                if (n != 0 && this.R != null) {
                    if (this.R.h() && (this.R.i() == -1 || this.F() == this.R.i())) {
                        this.R.a(this.P);
                    }
                    n = 0;
                }
            }
        }
    }
    
    public void u() {
        this.aH = true;
        for (int i = 0; i < 10; ++i) {
            final int size = this.ai[i].size();
            final Vector vector = this.ai[i];
            for (int j = 0; j < size; ++j) {
                vector.elementAt(j).s();
            }
        }
    }
    
    public void v() {
        this.aH = false;
        for (int i = 0; i < 10; ++i) {
            final int size = this.ai[i].size();
            final Vector vector = this.ai[i];
            for (int j = 0; j < size; ++j) {
                vector.elementAt(j).t();
            }
        }
    }
    
    public void d(final a a) {
        if (this.aQ) {
            if (this.M != null && a != null) {
                a.a(this.M, 0, 0, null);
            }
            if (y.a) {
                Toolkit.getDefaultToolkit().sync();
            }
        }
    }
    
    public synchronized void a(final Dimension q) {
        if (!this.ae) {
            return;
        }
        if (this.aP == null || (this.aP != null && !this.aP.isVisible())) {
            return;
        }
        if ((this.aQ && this.P == null && this.O != null) || this.Q == null || q.width != this.Q.width || q.height != this.Q.height) {
            final Dimension q2 = this.Q;
            if (q.width == 0 || q.height == 0) {
                return;
            }
            this.Q = q;
            if (this.aQ && this.P != null) {
                this.P.g();
                this.M.c();
            }
            this.N = this.O;
            if (this.aQ && this.O != null) {
                final Image image = this.aP.createImage(q.width, q.height);
                if (image == null) {
                    t.a("Preparing, but peer component is not yet displayable");
                    this.Q = null;
                    return;
                }
                this.N = new com.daysofwonder.b.a.a(image);
                final a a = this.N.a();
                if (this.O != null) {
                    final int a2 = this.O.a(null);
                    final int b = this.O.b(null);
                    for (int i = 0; i < q.width; i += a2) {
                        for (int j = 0; j < q.width; j += b) {
                            a.a(this.O, i, j, i + a2, j + b, 0, 0, a2, b, null);
                        }
                    }
                }
                a.g();
                this.M = new com.daysofwonder.b.a.a(this.aP.createImage(q.width, q.height));
                if (this.M == null) {
                    return;
                }
                (this.P = this.M.a()).a(com.daysofwonder.applet.ap.E);
            }
            for (int k = 0; k < 10; ++k) {
                final int size = this.ai[k].size();
                final Vector vector = this.ai[k];
                for (int l = 0; l < size; ++l) {
                    vector.elementAt(l).a(q2, q);
                }
            }
            t.a("NOTIFICATION");
            this.notifyAll();
        }
        if (this.aQ) {
            this.P.a(Color.black);
        }
    }
    
    public synchronized void g() {
        this.aP = null;
        t.a("inside View shutdowning");
        this.ae = false;
        if (this.aN != null) {
            this.aN.b();
        }
        for (int i = 0; i < 10; ++i) {
            final int size = this.ai[i].size();
            final Vector vector = this.ai[i];
            for (int j = 0; j < size; ++j) {
                vector.elementAt(j).a();
            }
            vector.removeAllElements();
        }
        if (this.M != null) {
            this.M.c();
        }
        if (this.aQ && this.N != null) {
            this.N.c();
        }
        if (this.P != null) {
            this.P.g();
        }
        if (this.Z != null) {
            this.Z.b();
            this.Z = null;
        }
        if (this.Y) {
            t.a("release timers...");
            this.aU.b(this.aT);
            this.aU.b();
            this.aV.b(this.aS);
            this.aV.b();
        }
        this.P = null;
    }
    
    public b b(final Rectangle rectangle) {
        if (this.aQ) {
            final com.daysofwonder.b.a.a a = new com.daysofwonder.b.a.a(this.aP.createImage(rectangle.width, rectangle.height));
            final a a2 = a.a();
            a2.a(this.N, 0, 0, rectangle.width, rectangle.height, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, null);
            a2.g();
            return a;
        }
        return null;
    }
    
    public b c(final Rectangle rectangle) {
        b b = null;
        if (this.aQ) {
            b = new com.daysofwonder.b.a.a(this.aP.createImage(rectangle.width, rectangle.height));
            final a a = b.a();
            a.a(this.M, 0, 0, rectangle.width, rectangle.height, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, null);
            a.g();
        }
        return b;
    }
    
    public void a(final a a, final int n, final int n2, final int n3, final int n4) {
        if (this.aQ) {
            a.a(this.M, 0, 0, n3, n4, n, n2, n + n3, n2 + n4, null);
        }
    }
    
    public void w() {
        if (this.aQ && this.P == null) {
            synchronized (this) {
                while (this.P == null) {
                    try {
                        t.a("waitInit before");
                        this.wait();
                        t.a("waitInit release");
                    }
                    catch (InterruptedException ex) {
                        t.a("waitInit interrupt");
                    }
                }
                t.a("waitInit fBackGraphics != null");
            }
        }
    }
    
    public void a(final KeyEvent keyEvent) {
        keyEvent.getKeyChar();
        keyEvent.getKeyCode();
        if (this.aR != null) {
            this.aR.b(keyEvent);
        }
    }
    
    public void b(final KeyEvent keyEvent) {
        this.a(null, "back", keyEvent);
    }
    
    public void c(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27) {
            this.b(keyEvent);
        }
        if (keyEvent.getKeyCode() == 9) {
            am am = null;
            final int n = (this.aR != null) ? this.aR.m() : 0;
            int n2 = 0;
            int n3 = -1;
            int n4 = -1;
            final Vector vector = this.ai[n];
            for (int size = vector.size(), i = 0; i < size; ++i) {
                final am am2 = vector.elementAt(i);
                if (n2 == 0 && am2 == this.aR) {
                    t.a("View.keyPressed. found hasFocus");
                    n2 = 1;
                    n4 = i;
                }
                else if (n2 != 0 && am2.h() && am2.w() && (am2.i() == -1 || this.F() == am2.i())) {
                    am = am2;
                    n3 = i;
                    break;
                }
            }
            if (n3 == -1 && n4 != -1) {
                for (int j = 0; j < n4; ++j) {
                    final am am3 = vector.elementAt(j);
                    if (am3.h() && am3.w() && (am3.i() == -1 || this.F() == am3.i())) {
                        am = am3;
                        break;
                    }
                }
            }
            this.a(am);
        }
        else if (this.aR != null) {
            this.aR.a(keyEvent);
        }
    }
    
    public synchronized void c(final MouseEvent mouseEvent) {
        final am b = this.b(mouseEvent.getPoint());
        if (b != null) {
            b.b(mouseEvent);
        }
    }
    
    public synchronized void a(final MouseEvent mouseEvent) {
        if (this.Z != null) {
            this.Z.b();
            this.d = null;
            this.e = null;
            this.Z = null;
        }
        if (this.Y && this.X && this.ai[4].size() > 0) {
            this.d = null;
            this.W();
            this.aU.b();
            this.aV.b();
        }
        final Point point = mouseEvent.getPoint();
        this.ab = point;
        this.ad = point;
        this.ac = point;
        int ag = this.ag;
        int n = 9;
        if (this.ai[9].size() > 0) {
            t.a("Layer Alert present");
            n = (ag = 9);
        }
        for (int i = n; i >= ag; --i) {
            final Vector vector = this.ai[i];
            for (int size = vector.size(), j = 0; j < size; ++j) {
                final am am = vector.elementAt(j);
                if (am.h() && am.n().contains(this.ab) && (am.i() == -1 || this.F() == am.i()) && am.a(mouseEvent)) {
                    if (am.w()) {
                        this.a(am);
                    }
                    return;
                }
            }
        }
    }
    
    public synchronized void d(final MouseEvent mouseEvent) {
        final z q = this.Q();
        final Point ac = this.ac;
        this.ac = this.ad;
        this.ad = mouseEvent.getPoint();
        this.aO = 15;
        if (!this.ad.equals(this.ac)) {
            this.aK = 0;
            if (this.aa != null) {
                if (q != null) {
                    this.aa.a(q, mouseEvent, this.ab, this.ac, this.ad, 0);
                }
                if (this.aa.a(mouseEvent, this.ab, this.ac, this.ad)) {
                    if (q != null) {
                        this.aa.a(q, mouseEvent, this.ab, this.ac, this.ad, 1);
                    }
                }
                else {
                    if (q != null) {
                        this.aa.a(q, mouseEvent, this.ab, this.ac, this.ad, 0);
                    }
                    if (this.aa.b(mouseEvent, this.ad)) {
                        this.aa.a(mouseEvent);
                    }
                    this.aa = null;
                }
            }
            final am b = this.b(this.ad);
            if (this.aa == null && this.aj == null && b != null && b.b(this.ad) && (Math.abs(this.ad.x - this.ab.x) > 2 || Math.abs(this.ad.y - this.ab.y) > 2)) {
                this.aj = b;
                this.ak = this.aj.c(this.ab);
                this.al = this.aj.d(this.ab);
                this.am = this.aj.a(q, this.ab, this.ak, this.al);
                if (this.ak == null || this.aj == null || this.am == null) {
                    this.ak = null;
                    this.aj = null;
                    this.am = null;
                    return;
                }
                this.aP.setCursor(Cursor.getPredefinedCursor(12));
                if (this.aQ) {
                    this.aj.a(this.P);
                }
                this.am.a(mouseEvent, this.ab, this.ac, this.ad);
                if (this.aQ) {
                    this.am.a(q, mouseEvent, this.ab, this.ac, this.ad, 1, this.aj.a(this.ad, this.ak, this.aj, this.al));
                    this.aL = true;
                }
            }
            else if (this.aa == null && this.aj != null && this.am != null) {
                final boolean b2 = b != null && b.a(this.ad, this.ak, this.aj, this.al);
                if (!b2) {
                    this.aP.setCursor(Cursor.getPredefinedCursor(13));
                }
                else {
                    this.aP.setCursor(Cursor.getPredefinedCursor(0));
                }
                if (this.aQ) {
                    if (this.aL) {
                        this.am.a(q, mouseEvent, this.ab, this.ac, this.ad, 0, b2);
                        this.aL = false;
                    }
                    else if (this.aM) {
                        this.am.a(q, mouseEvent, this.ab, this.ac, this.ac, 0, b2);
                        this.aM = false;
                    }
                }
                this.am.a(mouseEvent, this.ab, this.ac, this.ad);
                if (b != null && this.an == null && (b2 || b.c_())) {
                    b.a(q, this.ak, this.al, this.aj, this.ad, 1, b2, false);
                    this.an = b;
                    this.T = 0;
                }
                else if (this.an != null && this.an != b) {
                    this.an.a(q, this.ak, this.al, this.aj, this.ad, 0, false, false);
                    this.T = 1;
                    if (b != null && b.a(this.ad, this.ak, this.aj, this.al)) {
                        b.a(q, this.ak, this.al, this.aj, this.ad, 1, true, false);
                        this.T = 0;
                        this.an = b;
                    }
                    else {
                        this.an = null;
                    }
                }
                else if (this.an != null) {
                    this.an.a(q, this.ak, this.al, this.aj, this.ad, this.T, b2, true);
                    this.T = ((this.T != 1) ? 1 : 0);
                }
                if (this.aQ) {
                    this.am.a(q, mouseEvent, this.ab, this.ac, this.ad, 1, b2);
                    this.aL = true;
                }
            }
            if (q != null) {
                this.a(q);
            }
        }
        if (q != null) {
            q.c();
        }
    }
    
    public synchronized void b(final MouseEvent mouseEvent) {
        final z q = this.Q();
        if (!mouseEvent.getPoint().equals(this.ac)) {
            this.ac = this.ad;
            this.ad = mouseEvent.getPoint();
        }
        if (this.aa != null) {
            this.aa.a(mouseEvent, this.ab, this.ac, this.ad);
            if (this.aa.b(mouseEvent, this.ad)) {
                this.aa.a(mouseEvent);
            }
            if (q != null) {
                this.aa.a(q, mouseEvent, this.ab, this.ac, this.ad, 1);
            }
            this.aa = null;
            this.a(q);
        }
        else {
            this.aP.setCursor(Cursor.getPredefinedCursor(0));
            if (this.aj != null && this.am != null) {
                if (q != null) {
                    this.am.a(q, mouseEvent, this.ab, this.ac, this.ad, 0, true);
                }
                this.am.a(mouseEvent, this.ab, this.ac, this.ad);
                this.am.a();
                final am b = this.b(this.ad);
                if (this.an != null && this.an != b) {
                    this.an.a(q, this.ak, this.al, this.aj, this.ad, 0, false, false);
                }
                if (b != null && b.a(this.ad, this.ak, this.aj, this.al)) {
                    b.a(q, this.ak, this.al, this.aj, this.ad);
                }
                else if (b == null || !b.a(this.ad, this.ak, this.aj, this.al)) {
                    this.aj.a(q, this.ak, this.al, this.ab, this.ad, this.am);
                }
                this.aj = null;
                this.ak = null;
                this.al = null;
                this.am = null;
                this.an = null;
                this.T = 0;
                if (this.aQ) {
                    this.A();
                }
            }
        }
        if (q != null) {
            q.c();
        }
    }
    
    public void x() {
        final am a = this.a(this.d, this.e, this.f);
        if (a != null) {
            this.b(a);
        }
    }
    
    public void y() {
        this.W();
    }
    
    public am a(final am am, final String s, final Point point) {
        return null;
    }
    
    public synchronized void e(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.Y && this.X) {
            if (this.d == null) {
                this.d = this.a(point);
                this.f = point;
                this.e = ((this.d != null) ? this.d.a(point) : null);
                if (this.d != null && this.e != null) {
                    this.aU.a();
                }
            }
            else if (!this.d.o().contains(point)) {
                this.aU.b();
                this.aV.b();
                this.y();
                this.d = null;
                this.e = null;
                this.f = null;
            }
            else {
                final am a;
                if ((a = this.a(point)) != this.d) {
                    if (a != null && a.m() != 4) {
                        this.aU.b();
                        this.aV.b();
                        this.y();
                        this.d = a;
                        this.f = point;
                        this.e = ((this.d != null) ? this.d.a(point) : null);
                        if (this.d != null && this.e != null) {
                            this.aU.a();
                        }
                    }
                }
                else {
                    final am a2;
                    if ((a2 = this.a(point)) == this.d && this.d.a(point) != this.e && a2 != null && a2.m() != 4) {
                        this.aU.b();
                        this.aV.b();
                        this.y();
                        this.d = a2;
                        this.f = point;
                        this.e = ((this.d != null) ? this.d.a(point) : null);
                        if (this.d != null && this.e != null) {
                            this.aU.a();
                        }
                    }
                }
            }
        }
        else if (this.X) {
            if (mouseEvent.getComponent() != this.aP) {
                final Point location = mouseEvent.getComponent().getLocation();
                point.translate(location.x, location.y);
            }
            if (this.d == null) {
                if (this.Z != null) {
                    this.Z.b();
                    this.Z = null;
                }
                this.d = this.a(point);
                if (this.d != null) {
                    (this.Z = new com.daysofwonder.applet.a(this.d.a(point), mouseEvent.getComponent(), com.daysofwonder.applet.ap.E)).a(point.x, point.y, this.d.p());
                }
            }
            else if (!this.d.o().contains(point)) {
                if (this.Z != null) {
                    this.Z.b();
                    this.Z = null;
                }
                this.d = null;
            }
            else {
                final am a3;
                if ((a3 = this.a(point)) != this.d) {
                    if (this.Z != null) {
                        this.Z.b();
                        this.Z = null;
                    }
                    this.d = a3;
                    if (this.d != null) {
                        (this.Z = new com.daysofwonder.applet.a(this.d.a(point), mouseEvent.getComponent(), com.daysofwonder.applet.ap.E)).a(point.x, point.y, this.d.p());
                    }
                }
            }
        }
        final Point point2 = mouseEvent.getPoint();
        if (this.R == null && (this.R = this.a(point2)) != null) {
            this.R.c(mouseEvent);
            if (this.R.b(point2)) {
                this.aP.setCursor(Cursor.getPredefinedCursor(12));
            }
            else {
                this.aP.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
        else if (this.R != null) {
            if (!this.R.o().contains(point2)) {
                this.R.d(mouseEvent);
                if (this.aP != null) {
                    this.aP.setCursor(Cursor.getPredefinedCursor(0));
                }
                this.R = null;
            }
            else {
                final am a4;
                if ((a4 = this.a(point2)) != this.R && a4 != null) {
                    if (this.R != null) {
                        this.R.d(mouseEvent);
                    }
                    (this.R = a4).c(mouseEvent);
                    if (this.R.b(point2) && this.aP != null) {
                        this.aP.setCursor(Cursor.getPredefinedCursor(12));
                    }
                    else if (this.aP != null) {
                        this.aP.setCursor(Cursor.getPredefinedCursor(0));
                    }
                }
                else if (this.R != null) {
                    this.R.e(mouseEvent);
                    if (this.R.b(point2) && this.aP != null) {
                        this.aP.setCursor(Cursor.getPredefinedCursor(12));
                    }
                    else if (this.aP != null) {
                        this.aP.setCursor(Cursor.getPredefinedCursor(0));
                    }
                }
            }
        }
    }
    
    public synchronized boolean a(final aH aa, final MouseEvent mouseEvent) {
        this.aa = aa;
        if (this.aa != null) {
            final z q = this.Q();
            final Point point = mouseEvent.getPoint();
            this.ab = point;
            this.ad = point;
            this.ac = point;
            if (this.aa.a(mouseEvent, this.ab) && this.aQ) {
                this.aa.a(q, mouseEvent, this.ab, this.ab, this.ab, 1);
                this.a(q);
            }
            else if (this.aQ) {
                this.aa = null;
            }
            if (q != null) {
                q.c();
            }
        }
        return this.aa != null;
    }
    
    public am a(final Point point) {
        am am = null;
        int n = 10;
        while (--n >= 0) {
            final Vector vector = this.ai[n];
            if (vector == null) {
                continue;
            }
            if (n == 4) {
                continue;
            }
            for (int i = vector.size() - 1; i >= 0; --i) {
                final am am2 = vector.elementAt(i);
                if (am2.o().contains(point)) {
                    boolean b = false;
                    if (am2.k() instanceof Component) {
                        if (((Component)am2.k()).isVisible()) {
                            b = true;
                        }
                    }
                    else if (am2.h()) {
                        b = true;
                    }
                    if (b && (am == null || (am != null && com.daysofwonder.applet.aL.a(am.o(), am2.o())))) {
                        am = am2;
                    }
                }
            }
            if (am != null) {
                break;
            }
        }
        return am;
    }
    
    public am b(final String s) {
        int n = 10;
        while (--n >= 0) {
            final Vector vector = this.ai[n];
            for (int size = vector.size(), i = 0; i < size; ++i) {
                final am am = vector.elementAt(i);
                if (am.l().equals(s)) {
                    return am;
                }
            }
        }
        return null;
    }
    
    protected boolean z() {
        return this.ao || this.aw;
    }
    
    private am b(final Point point) {
        int n = 0;
        int n2 = 9;
        if (this.ai[9].size() > 0) {
            n2 = (n = 9);
        }
        am am = null;
        for (int i = n2; i >= n; --i) {
            final Vector vector = this.ai[i];
            for (int size = vector.size(), j = 0; j < size; ++j) {
                final am am2 = vector.elementAt(j);
                if (am2.h() && am2.n().contains(point) && (am2.i() == -1 || this.F() == am2.i())) {
                    am = am2;
                }
            }
            if (am != null) {
                break;
            }
        }
        return am;
    }
    
    public boolean a(final String s, final String s2, final boolean b, final int u) {
        this.U = u;
        if (b) {
            new Thread(new V(this, s, s2)).start();
            return this.ax;
        }
        return this.b(s, s2);
    }
    
    public boolean b(final String aa, final String az) {
        if (this.ao || this.aw) {
            return false;
        }
        this.aw = true;
        this.aA = aa;
        this.az = az;
        this.aB = com.daysofwonder.applet.aL.b(this.W, "yesno.foreground.color");
        this.aC = com.daysofwonder.applet.aL.b(this.W, "yesno.background.color");
        this.aE = com.daysofwonder.applet.aL.b(this.W, "yesno.title.background.color");
        this.aD = com.daysofwonder.applet.aL.b(this.W, "yesno.title.foreground.color");
        this.ai[9] = new Vector();
        this.A();
        this.C();
        return this.ax;
    }
    
    public void A() {
        if (this.aQ && this.aP != null) {
            this.aP.repaint();
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        if (this.aQ && this.aP != null) {
            this.aP.repaint(n, n2, n3, n4);
        }
    }
    
    public boolean B() {
        return this.aQ;
    }
    
    public synchronized void C() {
        try {
            while (this.aw) {
                this.wait();
            }
        }
        catch (InterruptedException ex) {
            this.ax = false;
        }
    }
    
    public synchronized void D() {
        try {
            while (this.ao) {
                this.wait();
            }
        }
        catch (InterruptedException ex) {
            this.ao = false;
        }
    }
    
    public void b(final int n, final Object[] array) {
        this.c(n, array);
        this.A();
        this.D();
    }
    
    protected void c(final int n, final Object[] array) {
        if (this.ao || this.aw) {
            return;
        }
        this.ao = true;
        this.ar = this.V.b("alert.title." + n);
        this.aq = this.a(this.V.b("alert.text." + n), array);
        System.out.println("ALERT ENGAGED");
        this.as = com.daysofwonder.applet.aL.b(this.W, "alert.foreground.color");
        this.at = com.daysofwonder.applet.aL.b(this.W, "alert.background.color");
        this.av = com.daysofwonder.applet.aL.b(this.W, "alert.title.background.color");
        this.au = com.daysofwonder.applet.aL.b(this.W, "alert.title.foreground.color");
        this.ai[9] = new Vector();
    }
    
    public synchronized void E() {
        this.U = -1;
        this.aF = null;
        if (this.ao) {
            this.ao = false;
        }
        if (this.aw) {
            this.aw = false;
        }
        if (this.aG) {
            this.aG = false;
        }
        this.ai[9] = new Vector();
        this.ai[8] = new Vector();
        this.notify();
        this.A();
    }
    
    public synchronized int F() {
        return this.S;
    }
    
    public synchronized void d(final Rectangle rectangle) {
        if (this.aQ) {
            this.b(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    
    public synchronized void b(final int n, final int n2, final int n3, final int n4) {
        if (this.aQ) {
            if (!this.ae) {
                return;
            }
            final a a = this.a();
            a.b(n, n2, n3, n4);
            final Shape f = this.P.f();
            this.P.b(n, n2, n3, n4);
            this.a(f);
            this.P.a(f);
            this.d(a);
            a.g();
        }
    }
    
    public synchronized void G() {
        if (this.aQ) {
            if (!this.ae) {
                return;
            }
            final a a = this.a();
            this.a((Shape)null);
            this.d(a);
            a.g();
        }
    }
    
    public synchronized void a(a a, final int n) {
        try {
            if (!this.ae) {
                return;
            }
            if (a == null) {
                a = this.a();
            }
            if (a != null) {
                boolean b = false;
                for (int i = 0; i < 10; ++i) {
                    final int size = this.ai[i].size();
                    final Vector vector = this.ai[i];
                    for (int j = 0; j < size; ++j) {
                        b |= vector.elementAt(j).a(this.P, n);
                    }
                }
                if (b && this.aQ) {
                    this.d(a);
                    a.g();
                }
            }
        }
        catch (Exception ex) {
            t.e("Critical: exception in wobbling: " + ex.getMessage());
            t.a(ex);
        }
    }
    
    public void e(final a a) {
        final FontMetrics d = a.d();
        final Dimension size = this.aP.getSize();
        int n = d.stringWidth(this.aq) + 40;
        K a2;
        if (n > size.width * 2 / 3) {
            a2 = com.daysofwonder.applet.aL.a(this.aq, size.width * 2 / 3, d, false);
            n = size.width * 2 / 3;
        }
        else {
            a2 = new K();
            a2.c(this.aq);
        }
        final int n2 = (a2.c() + 1) * d.getHeight() + 60;
        final int n3 = (size.width - n) / 2;
        final int n4 = (size.height - n2) / 2;
        a.a(this.at);
        a.d(n3, n4, n, n2);
        a.a(this.av);
        a.d(n3 + 2, n4 + 2, n - 4, d.getHeight() + 4);
        a.a(this.as);
        a.c(n3 + 2, n4 + 2, n - 4, n2 - 4);
        a.a(this.au);
        com.daysofwonder.applet.aL.a(a, new Rectangle(n3 + 2, n4 + 2, n - 4, d.getHeight() + 2), this.ar, this.N(), 1);
        a.a(this.as);
        a.c(n3 + 2, n4 + 2, n - 4, d.getHeight() + 4);
        int n5 = n4 + 12 + 2 * d.getHeight();
        final int height = d.getHeight();
        final com.daysofwonder.util.y e = a2.e();
        while (e.a()) {
            a.a((String)e.b(), n3 + 20, n5);
            n5 += height;
        }
        if (this.ap == null) {
            System.out.println("ALERT contruction of controls");
            this.ap = new Vector();
            final Enumeration a3 = this.W.a();
            while (a3.hasMoreElements()) {
                final String s = a3.nextElement();
                if (s.endsWith(".alert_control")) {
                    System.out.println("ALERT constructing " + s);
                    this.ap.addElement(this.a(s, ".alert_control"));
                }
            }
        }
        this.ai[9] = this.ap;
        final int size2 = this.ap.size();
        if (size2 > 0) {
            int n6 = 0;
            for (int i = 0; i < size2; ++i) {
                n6 += ((am)this.ap.elementAt(i)).p().width;
            }
            final int n7 = (n - n6) / (size2 + 1);
            int n8 = n3 + n7;
            for (int j = 0; j < size2; ++j) {
                final am am = this.ap.elementAt(j);
                final Rectangle p = am.p();
                am.a(n8, n4 + n2 - p.height - 5);
                n8 += n7 + p.width;
                am.a(a);
            }
        }
    }
    
    public void f(final a a) {
        final FontMetrics d = a.d();
        final Dimension size = this.aP.getSize();
        int n = d.stringWidth(this.az) + 40;
        K a2;
        if (n > size.width * 2 / 3) {
            a2 = com.daysofwonder.applet.aL.a(this.az, size.width * 2 / 3, d, false);
            n = size.width * 2 / 3;
        }
        else {
            a2 = new K();
            a2.c(this.az);
        }
        final int n2 = (a2.c() + 1) * d.getHeight() + 60;
        final int n3 = (size.width - n) / 2;
        final int n4 = (size.height - n2) / 2;
        a.a(this.aC);
        a.d(n3, n4, n, n2);
        a.a(this.aE);
        a.d(n3 + 2, n4 + 2, n - 4, d.getHeight() + 4);
        a.a(this.aB);
        a.c(n3 + 2, n4 + 2, n - 4, n2 - 4);
        a.a(this.aD);
        com.daysofwonder.applet.aL.a(a, new Rectangle(n3 + 2, n4 + 2, n - 4, d.getHeight() + 2), this.aA, this.N(), 1);
        a.a(this.aB);
        a.c(n3 + 2, n4 + 2, n - 4, d.getHeight() + 4);
        int n5 = n4 + 12 + 2 * d.getHeight();
        final int height = d.getHeight();
        final com.daysofwonder.util.y e = a2.e();
        while (e.a()) {
            a.a((String)e.b(), n3 + 20, n5);
            n5 += height;
        }
        if (this.ay == null) {
            System.out.println("yesno contruction of controls");
            this.ay = new Vector();
            final Enumeration a3 = this.W.a();
            while (a3.hasMoreElements()) {
                final String s = a3.nextElement();
                if (s.endsWith(".yesno_control")) {
                    this.ay.addElement(this.a(s, ".yesno_control"));
                }
            }
        }
        this.ai[9] = this.ay;
        final int size2 = this.ay.size();
        if (size2 > 0) {
            int n6 = 0;
            for (int i = 0; i < size2; ++i) {
                n6 += ((am)this.ay.elementAt(i)).p().width;
            }
            final int n7 = (n - n6) / (size2 + 1);
            int n8 = n3 + n7;
            for (int j = 0; j < size2; ++j) {
                final am am = this.ay.elementAt(j);
                final Rectangle p = am.p();
                am.a(n8, n4 + n2 - p.height - 5);
                n8 += n7 + p.width;
                am.a(a);
            }
        }
    }
    
    public synchronized boolean H() {
        return this.ae;
    }
    
    public Font I() {
        return com.daysofwonder.applet.ap.E;
    }
    
    public Font J() {
        return com.daysofwonder.applet.ap.F;
    }
    
    public Font K() {
        return com.daysofwonder.applet.ap.G;
    }
    
    public Font L() {
        return com.daysofwonder.applet.ap.I;
    }
    
    public Font M() {
        return com.daysofwonder.applet.ap.H;
    }
    
    public Font N() {
        return com.daysofwonder.applet.ap.J;
    }
    
    public Font O() {
        return com.daysofwonder.applet.ap.L;
    }
    
    public b a(final int n, final int n2) {
        return new com.daysofwonder.b.a.a(this.aP.createImage(n, n2));
    }
    
    public z P() {
        if (this.aQ) {
            return new z(new com.daysofwonder.b.a.b(this.aP.getGraphics()), this, true);
        }
        return null;
    }
    
    public z Q() {
        if (this.aQ) {
            return new z(new com.daysofwonder.b.a.b(this.aP.getGraphics()), this, true);
        }
        return null;
    }
    
    public z g(final a a) {
        return new z(a, this, false);
    }
    
    public void a(final z z) {
        if (z != null) {
            z.f();
        }
        if (y.a) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    public String a(final String s, final Object[] array) {
        return s;
    }
    
    public UIProperties R() {
        return this.V;
    }
    
    public void a(final a a) {
    }
    
    public Rectangle a(final a a, final Rectangle rectangle) {
        return rectangle;
    }
    
    public b c(final String s) {
        b a = null;
        if (this.c != null) {
            a = this.c.get(s);
        }
        if (a == null) {
            a = y.a(s);
        }
        return a;
    }
    
    public synchronized void S() {
        this.aI = true;
    }
    
    public synchronized boolean T() {
        if (this.aI) {
            t.a("Animation is aborted...");
            this.aI = false;
            return true;
        }
        return false;
    }
    
    public void h(final a a) {
        if (this.h++ % 5 == 0) {
            this.a(a, this.g++);
        }
        if (this.aa == null && this.an != null && this.an.c_() && this.aK++ > this.aO) {
            final z g = this.g(a);
            this.an.a(g, this.ak, this.al, this.aj, this.ad, 0, false, false);
            this.aO = 3;
            g.c();
        }
    }
    
    public a U() {
        return this.P;
    }
    
    public b V() {
        return this.M;
    }
    
    public void a(final am ar) {
        if (this.aR != null) {
            this.aR.b(ar);
        }
        final am ar2 = this.aR;
        this.aR = ar;
        if (this.aR != null && this.aR.w()) {
            this.aR.a(ar2);
        }
    }
    
    public void b(final am am) {
        am.c(4);
        this.ai[4].removeAllElements();
        this.ai[4].addElement(am);
        this.G();
    }
    
    public void W() {
        for (int i = 0; i < this.ai[4].size(); ++i) {
            ((am)this.ai[4].elementAt(i)).c(0);
        }
        this.ai[4].removeAllElements();
        this.G();
    }
    
    public void a(final L l, final int n, final int n2, final int n3, final int n4) {
        l.a(n, n2, n3, n4);
        this.ai[5].removeAllElements();
        this.ai[5].addElement(l);
    }
    
    public void X() {
        this.ai[5].removeAllElements();
        this.A();
    }
    
    public L Y() {
        return new L(this, "", this.W, this.V);
    }
    
    public void a(final String s, final boolean b) {
        final Vector<am> vector = this.ah.get(s);
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).a(b);
            }
        }
    }
    
    public void e(final boolean x) {
        this.X = x;
    }
    
    public void f(final boolean y) {
        this.Y = y;
    }
    
    public boolean i() {
        return false;
    }
    
    private a a() {
        if (this.aP instanceof s) {
            return ((s)this.aP).a();
        }
        if (this.aP instanceof aw) {
            return ((aw)this.aP).c();
        }
        throw new RuntimeException("Bad ViewPanel");
    }
}

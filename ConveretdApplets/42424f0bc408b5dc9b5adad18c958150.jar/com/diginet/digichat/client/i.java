// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.filetransfer.ab;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Font;
import java.io.DataOutput;
import java.awt.Color;
import com.diginet.digichat.common.k;
import com.diginet.digichat.common.a4;
import com.diginet.digichat.util.l;
import com.diginet.digichat.common.e;
import com.diginet.digichat.util.ap;
import com.diginet.digichat.awt.ah;
import com.esial.util.d;
import java.net.MalformedURLException;
import java.awt.Button;
import java.awt.Component;
import java.awt.Event;
import com.diginet.digichat.common.x;
import com.diginet.digichat.common.ax;
import java.io.IOException;
import java.io.DataInput;
import com.diginet.digichat.network.v;
import com.diginet.digichat.common.a2;
import java.awt.MenuItem;
import java.awt.Frame;
import com.diginet.digichat.util.o;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import com.diginet.digichat.util.n;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.awt.Image;
import com.diginet.digichat.common.j;

public abstract class i extends j implements Runnable
{
    protected static final String[] a;
    protected static final String[] b;
    public static Image c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    protected int i;
    protected long j;
    public int k;
    protected long l;
    protected boolean m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    protected DataInputStream q;
    protected DataOutputStream r;
    protected String s;
    protected String t;
    protected ao u;
    protected Thread v;
    protected Socket w;
    public y x;
    protected n y;
    public n z;
    public n aa;
    public n ab;
    public n ac;
    public n ad;
    public n ae;
    public n af;
    public n ag;
    public n ah;
    public n ai;
    public n aj;
    public n ak;
    public n al;
    public n am;
    public n an;
    public Vector ao;
    public Vector ap;
    public Vector aq;
    public long ar;
    public long as;
    public long at;
    public long au;
    protected URL av;
    protected boolean aw;
    protected boolean ax;
    public MediaTracker ay;
    protected AudioClip[] az;
    protected o a0;
    protected Frame a1;
    protected int a2;
    protected int a3;
    protected int a4;
    protected String a5;
    public boolean a6;
    public boolean a7;
    protected String a8;
    public boolean a9;
    private MenuItem ba;
    private MenuItem bb;
    public String bc;
    public String bd;
    public String be;
    public String bf;
    public String bg;
    public String bh;
    public String bi;
    public String bj;
    public URL bk;
    public URL bl;
    public URL bm;
    public int bn;
    public int bo;
    public int bp;
    public int bq;
    public int br;
    public int bs;
    public int bt;
    public int bu;
    public int bv;
    public int bw;
    public int bx;
    public int by;
    public int bz;
    public int b0;
    public int b1;
    public int b2;
    public int b3;
    public boolean b4;
    public boolean b5;
    public boolean b6;
    public boolean b7;
    public boolean b8;
    public boolean b9;
    public a2 ca;
    
    public boolean h() throws IOException {
        this.w.setSoTimeout(5000);
        final v v = new v(this.q);
        if (v.b() == 67587) {
            this.l = v.b(0, 0);
            this.w.setSoTimeout(0);
            final v v2 = new v(67587, 1);
            v2.a(0, 0, 288233675760336897L);
            this.ad(v2);
            return true;
        }
        return false;
    }
    
    public String b(String s) {
        if (s != null && this.b5) {
            this.af.a(false);
            try {
                synchronized (this.af) {
                    for (int i = 0; i < this.af.b(); ++i) {
                        s = ((ax)this.af.c(i)).a(s);
                    }
                }
                // monitorexit(this.af)
            }
            finally {
                this.af.a();
            }
            this.ag.a(false);
            try {
                synchronized (this.ag) {
                    for (int j = 0; j < this.ag.b(); ++j) {
                        s = ((ax)this.ag.c(j)).a(s);
                    }
                }
                // monitorexit(this.ag)
            }
            finally {
                this.ag.a();
            }
        }
        return s;
    }
    
    protected abstract void d();
    
    public void a(final String a5, final String s, final String s2, final boolean b) {
        this.a5 = a5;
        final v v = new v(67330, 1);
        v.k = -1;
        v.j = -1;
        v.l = this.q();
        v.a(0, 0, -999);
        v.a(0, 0, a5);
        v.a(0, 1, s);
        v.a(0, 0, new x(s2));
        if (!b) {
            v.f(0, 58);
        }
        this.ad(v);
    }
    
    public void i() {
        new cc(this.x.b(), this).setVisible(true);
    }
    
    public void a(final String a5, final String s, final boolean b) {
        this.a5 = a5;
        final v v = new v(67330, 1);
        v.k = -1;
        v.j = -1;
        v.l = this.q();
        v.a(0, 0, -999);
        v.a(0, 0, a5);
        v.a(0, 1, s);
        if (!b) {
            v.f(0, 58);
        }
        this.ad(v);
    }
    
    public abstract void b(final v p0);
    
    public void f(final int n) {
        final bc bc = (bc)this.ab.d(n);
        if (bc != null) {
            this.a(bc);
        }
        else {
            System.err.println("no such room! " + n);
        }
    }
    
    public void a(final bc bc) {
        if (bc.b && bc.h != null) {
            new at(this.x.b(), this, bc).setVisible(true);
        }
        if (!bc.b || bc.h == null) {
            final v v = new v(66049, 1);
            v.a(0, 0, this.q());
            v.a(0, 1, bc.q());
            v.k = -1;
            v.j = -1;
            this.ad(v);
        }
    }
    
    public long j() {
        return this.j;
    }
    
    public int k() {
        return this.k;
    }
    
    public boolean l() {
        return this.m;
    }
    
    public boolean m() {
        return this.n;
    }
    
    public boolean n() {
        return this.p;
    }
    
    public int g(final int n) {
        int n2 = 0;
        this.aa.a(false);
        try {
            synchronized (this.aa) {
                for (int i = 0; i < this.aa.b(); ++i) {
                    final aw aw = (aw)this.aa.c(i);
                    if (aw.b == n && (!aw.i(23) || this.i(24))) {
                        ++n2;
                    }
                }
            }
            // monitorexit(this.aa)
        }
        finally {
            this.aa.a();
        }
        return n2;
    }
    
    public boolean a(final Event event) {
        if (event.target == this.ba) {
            this.o();
        }
        else if (this.am.a(event.target)) {
            this.a((URL)this.an.c(this.am.b(event.target)), "_blank");
        }
        else if (event.target == this.bb) {
            this.c(0);
        }
        return true;
    }
    
    public Image a(final String s, final boolean b) {
        try {
            MediaTracker ay;
            if (this.ay == null) {
                final Button button;
                ay = new MediaTracker(button);
                button = new Button();
            }
            else {
                ay = this.ay;
            }
            final MediaTracker mediaTracker = ay;
            URL url;
            if (b) {
                url = new URL(this.av, "Resources/" + this.s + "/" + s);
            }
            else {
                url = new URL(this.av, "Resources/" + s);
            }
            Image a = this.a(url);
            if (a != null) {
                final int n = this.i++;
                mediaTracker.addImage(a, n);
                try {
                    mediaTracker.waitForID(n);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorID(n)) {
                    a = null;
                }
            }
            return a;
        }
        catch (MalformedURLException ex2) {
            return null;
        }
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return null;
    }
    
    public Image a(final URL url) {
        return null;
    }
    
    public abstract void a(final String p0, final String p1, final x p2, final String p3, final int p4) throws IOException;
    
    public void o() {
        this.g = true;
        if (this.r != null) {
            final v v = new v(65794, 1);
            v.a(0, 0, this.q());
            v.a(0, 0, this.bf);
            v.k = -1;
            v.j = -1;
            this.ad(v);
            super.b = -999;
            if (this.x != null) {
                final Frame b = this.x.b();
                this.x.a();
                if (b != null) {
                    b.dispose();
                }
            }
        }
        this.aw = true;
        this.ac(new v(66561, 0));
        this.d();
        this.e = false;
        this.f = false;
        if (this.bm != null) {
            this.a(this.bm, "_self");
        }
    }
    
    public void a(final ay ay, final aw aw) {
    }
    
    public void a(final ay ay) {
    }
    
    public void a(final byte[] array) {
    }
    
    public abstract void a(final URL p0, final String p1);
    
    public void a(final int n) {
    }
    
    protected void r(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            final aw aw = (aw)this.aa.d(v.a(i, 0));
            if (aw != null) {
                this.a(aw, v.a(i, 1), v.i(), v.h());
            }
        }
    }
    
    protected abstract void a(final aw p0, final int p1, final long p2, final int p3);
    
    protected void s(final v v) {
        if (this.bl != null) {
            this.a(this.bl, "_blank");
        }
        else {
            new ah(new Frame(), com.esial.util.d.a("You have been kicked"), v.c(0, 0), this).setVisible(true);
        }
        this.aw = true;
        this.ac(new v(66561, 0));
        this.d();
    }
    
    protected void t(final v v) {
        final String[] array = new String[v.c()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = v.c(i, 0);
        }
        new ah(this.x.b(), com.esial.util.d.a("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected void u(final v v) {
        String s = null;
        final int a = v.a(0, 1);
        switch (v.a(0, 0)) {
            case 2: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because your account has been disabled."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 4: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 3: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, com.diginet.digichat.common.e.a(), com.diginet.digichat.common.e.a(this.l) });
                break;
            }
            case 5: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because you have been banned from this %1 site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 6: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because no site with the specified ID could be found."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 8: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 10: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because the Server license has expired."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 9: {
                s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because the Server license has been exceeded."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 0: {
                if (a == 65793) {
                    s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, this.r() });
                }
                else {
                    s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { v.c(0, 0) });
                }
                break;
            }
            case 1:
            case 7: {
                if (a == 65793) {
                    s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                }
                break;
            }
        }
        if (s == null) {
            s = com.esial.util.d.a("Your request could not be fulfilled.");
        }
        if (a == 65793) {
            this.aw = true;
            this.ac(new v(66561, 0));
            this.d();
        }
        new ah(this.a1, com.esial.util.d.a("Note"), s, this).setVisible(true);
    }
    
    protected void v(final v v) {
        if (!this.aw) {
            new ah((this.x != null) ? this.x.b() : null, com.esial.util.d.a("Alert"), new String[] { v.c(0, 0), v.c(0, 1) }, this).setVisible(true);
        }
    }
    
    protected void w(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            new ah((this.x != null) ? this.x.b() : null, v.c(i, 0), v.c(i, 1), this).setVisible(true);
        }
    }
    
    protected void x(final v v) {
        new ah(this.x.b(), com.esial.util.d.a("Inactivity Timeout"), com.diginet.digichat.util.ap.a(com.esial.util.d.a("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(v.a(0, 0)) }), this).setVisible(true);
        this.aw = true;
        this.ac(new v(66561, 0));
        this.d();
    }
    
    protected void y(final v v) {
        final long a = v.a(-1);
        if (this.as == -999L && v.e(-1, 60)) {
            this.b5 = true;
        }
        n n;
        if (v.e(-1, 59)) {
            if ("Admin".equals(this.s)) {
                this.as = a;
                n = this.af;
            }
            else {
                n = this.ag;
            }
            this.at = a;
        }
        else {
            n = this.af;
            this.as = a;
        }
        if (v.a(this.at | this.as, 62) || v.a(this.at | this.as, 61)) {
            this.b5 = true;
        }
        n.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a2 = v.a(i, 0);
                ax ax = (ax)n.d(a2);
                if (v.e(i, 63)) {
                    if (ax != null) {
                        n.f(a2);
                    }
                }
                else {
                    if (ax == null) {
                        ax = new ax(a2, v.c(i, 0));
                        n.a(ax);
                    }
                    else {
                        ax.d(v.c(i, 0));
                    }
                    ax.a = v.c(i, 1);
                    ax.a(v.a(i));
                }
            }
        }
        finally {
            n.a();
        }
    }
    
    protected void z(final v v) {
        this.t = v.c(0, 0);
    }
    
    protected void aa(final v v) {
        bc bc = null;
        k k = null;
        this.ab.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bc bc2 = (bc)this.ab.d(a);
                if (v.e(i, 63) || v.c(i, 0) == null) {
                    if (bc2 != null) {
                        this.ab.f(a);
                        if (this.x != null) {
                            this.x.a(bc2);
                        }
                    }
                    if (a == super.b) {
                        k = bc2;
                    }
                }
                else {
                    if (bc2 == null) {
                        String c = v.c(i, 0);
                        if (c == null) {
                            System.err.println("null room name received.");
                            c = new String("no name");
                        }
                        bc2 = new bc(a, this.b(c));
                        this.ab.a(bc2);
                    }
                    else {
                        bc2.d(this.b(v.c(i, 0)));
                    }
                    bc2.d = v.a(i, 1);
                    bc2.e = v.a(i, 2);
                    bc2.f = v.a(i, 3);
                    bc2.c = v.a(i, 4);
                    bc2.a = this.b(v.c(i, 1));
                    bc2.b = this.b(v.c(i, 2));
                    final x h = bc2.h;
                    bc2.h = v.d(i, 0);
                    if (bc2.h != null && !bc2.h.equals(h)) {
                        bc2.b = true;
                    }
                    bc2.a(v.a(i));
                    if (v.e(i, 62)) {
                        this.a2 = a;
                    }
                    if (this.x != null) {
                        this.x.c(bc2);
                    }
                    if (this.a5 != null && bc2.r().equals(this.a5)) {
                        this.a5 = null;
                        bc = bc2;
                        bc.b = false;
                    }
                }
            }
            if (this.a2 == -999) {
                this.a2 = v.a(0, 0);
            }
        }
        finally {
            this.ab.a();
        }
        if (bc != null) {
            this.a(bc);
            this.x.a(true);
        }
        else if (k != null) {
            final a4 a2 = (a4)this.ab.d(this.a2);
            this.f(this.a2);
            new ah(this.x.b(), DigiChatAppletAbstract.OEM_DigiChat, com.diginet.digichat.util.ap.a(com.esial.util.d.a("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.b(k.r()), this.b(a2.r()) }), this).setVisible(true);
        }
    }
    
    protected void ab(final v v) {
        this.al.a(true);
        try {
            a2 ca = null;
            a2 ca2 = null;
            for (int i = 0; i < v.c(); ++i) {
                int n = v.a(i, 0);
                a2 a2 = (a2)this.al.d(n);
                if (v.e(i, 63)) {
                    this.al.f(n);
                }
                else {
                    if (a2 == null) {
                        a2 = new a2(n, v.c(i, 0));
                        n = this.al.a(a2, n);
                        v.a(i, 0, n);
                    }
                    else {
                        a2.d(v.c(i, 0));
                    }
                    a2.c = new Color(v.a(i, 1));
                    a2.d = new Color(v.a(i, 2));
                    a2.e = new Color(v.a(i, 3));
                    a2.f = new Color(v.a(i, 4));
                    a2.i = new Color(v.a(i, 5));
                    a2.j = new Color(v.a(i, 6));
                    a2.k = new Color(v.a(i, 7));
                    a2.l = new Color(v.a(i, 8));
                    a2.m = new Color(v.a(i, 9));
                    a2.n = new Color(v.a(i, 10));
                    a2.o = new Color(v.a(i, 11));
                    a2.p = v.c(i, 1);
                    a2.r = v.a(i, 12);
                    a2.q = v.a(i, 13);
                    a2.d();
                    a2.a(v.a(i));
                    a2.a(v.c(i, 2));
                    a2.v = v.a(i, 14);
                    a2.g = new Color(v.a(i, 15));
                    a2.h = new Color(v.a(i, 16));
                    if (a2.i(62)) {
                        ca = (a2)a2.clone();
                    }
                    if (n == DigiChatAppletAbstract.preferredTheme) {
                        ca2 = (a2)a2.clone();
                    }
                }
            }
            if (ca2 != null) {
                this.ca = ca2;
            }
            else if (ca != null) {
                this.ca = ca;
            }
            if (this.x != null) {
                this.x.d();
            }
            if (this.y != null) {
                for (int j = 0; j < this.y.b(); ++j) {
                    ((z)this.y.c(j)).a();
                }
            }
        }
        finally {
            this.al.a();
        }
    }
    
    public synchronized void ac(final v v) {
        this.a0.a(v);
        this.notify();
        this.notify();
    }
    
    public abstract void c();
    
    public void run() {
        this.a0.c();
        this.u.start();
        try {
            while (!this.aw || !this.a0.a()) {
                while (this.a0.a() && !this.aw) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.b((v)this.a0.b());
            }
        }
        catch (Exception ex) {
            if (!this.aw) {}
        }
        finally {
            this.aw = true;
            this.d();
        }
    }
    
    public void a() throws IOException {
    }
    
    public void ad(final v v) {
        try {
            v.a(this.r);
            this.r.flush();
        }
        catch (IOException ex) {
            if (!this.aw) {
                this.d();
            }
        }
    }
    
    public void a(final String s, final int k, final int j) {
        final v v = new v(66305, 1);
        v.a(0, 0, s);
        v.a(0, 0, this.q());
        v.k = k;
        v.j = j;
        this.ad(v);
    }
    
    public void c(final String s) {
        this.a(s, -1, super.b);
        this.j = System.currentTimeMillis();
    }
    
    public abstract void a(final ay p0, final j p1);
    
    public void a(final Font font) {
        this.ca.p = font.getName();
        this.ca.r = font.getStyle();
        this.ca.q = font.getSize();
        this.ca.d();
        if (this.x != null) {
            this.x.d();
        }
        if (this.y != null) {
            for (int i = 0; i < this.y.b(); ++i) {
                ((z)this.y.c(i)).a();
            }
        }
    }
    
    public void b(final boolean ax) {
        this.ax = ax;
        this.aa.a(false);
        try {
            synchronized (this.aa) {
                for (int i = 0; i < this.aa.b(); ++i) {
                    final aw aw = (aw)this.aa.c(i);
                    if (this.ax || aw.b == super.b) {
                        this.x.a(aw, true);
                    }
                    else {
                        this.x.a(aw);
                    }
                }
            }
            // monitorexit(this.aa)
        }
        finally {
            this.aa.a();
        }
    }
    
    public void a(final boolean b) {
    }
    
    public void c(final boolean m) {
        this.m = m;
    }
    
    public void d(final boolean n) {
        this.n = n;
    }
    
    public void e(final boolean p) {
        this.p = p;
    }
    
    public void a(final Frame frame) {
        final Menu menu = new Menu(DigiChatAppletAbstract.OEM_DigiChat);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.am.b(); ++i) {
            menu.add((MenuItem)this.am.d(i));
        }
        if (this.am.b() >= 1) {
            menu.addSeparator();
        }
        if (!DigiChatAppletAbstract.lite) {
            menu.add(this.bb = new MenuItem(com.esial.util.d.a("Settings")));
        }
        if (!this.d) {
            menu.add(this.ba = new MenuItem(com.esial.util.d.a("Logout")));
        }
        menuBar.add(menu);
        frame.setMenuBar(menuBar);
    }
    
    public void c(final int n) {
    }
    
    public void p() {
        final String a = com.diginet.digichat.util.ap.a(com.esial.util.d.a("You have been disconnected from %1 for flooding."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
        final v v = new v(50400771, 1);
        v.k = this.q();
        v.a(0, 0, a);
        this.ad(v);
    }
    
    public abstract boolean a(final ab p0);
    
    public abstract void a(final j p0);
    
    public abstract int b(final ab p0);
    
    public abstract void d(final int p0);
    
    public abstract void e(final int p0);
    
    public abstract void a(final int p0, final j p1);
    
    public i() {
        super(-999, null);
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = 1000;
        this.j = 0L;
        this.k = 250;
        this.m = true;
        this.n = true;
        this.o = false;
        this.p = false;
        this.w = null;
        this.y = new n();
        this.z = new n();
        this.aa = new n();
        this.ab = new n();
        this.ac = new n();
        this.ad = new n();
        this.ae = new n();
        this.af = new n();
        this.ag = new n();
        this.ah = new n();
        this.ai = new n();
        this.aj = new n();
        this.ak = new n();
        this.al = new n();
        this.am = new n();
        this.an = new n();
        this.ao = new Vector();
        this.ap = new Vector();
        this.aq = new Vector();
        this.as = -999L;
        this.aw = true;
        this.ax = false;
        this.a0 = new o();
        this.a2 = -999;
        this.a3 = -999;
        this.a4 = -999;
        this.a6 = false;
        this.a7 = true;
        this.a9 = false;
        this.bn = -999;
        this.bo = 2;
        this.bp = -999;
        this.bq = -999;
        this.br = -999;
        this.bs = 0;
        this.bt = 8;
        this.bu = 3;
        this.bv = 4;
        this.bw = 9;
        this.bx = 1;
        this.by = 0;
        this.bz = 7;
        this.b0 = 2;
        this.b1 = 0;
        this.b2 = 0;
        this.b3 = 0;
        this.b4 = false;
        this.b5 = false;
        this.b6 = false;
        this.b7 = false;
        this.b8 = false;
        this.b9 = false;
        this.ca = com.diginet.digichat.common.a2.a;
    }
    
    static {
        a = new String[] { null, null, null, null, null, null, null, null, null };
        b = new String[] { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
    }
}

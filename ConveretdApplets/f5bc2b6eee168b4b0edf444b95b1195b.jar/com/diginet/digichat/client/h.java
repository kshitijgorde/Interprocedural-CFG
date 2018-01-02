// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Component;
import java.awt.Button;
import com.diginet.digichat.util.filetransfer.ag;
import java.util.Hashtable;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Font;
import java.io.DataOutput;
import com.diginet.digichat.client.spell.ch;
import java.awt.Color;
import com.diginet.digichat.common.DigiItem;
import com.diginet.digichat.util.i;
import com.diginet.digichat.common.d;
import com.diginet.digichat.util.StringSubst;
import java.net.MalformedURLException;
import com.diginet.digichat.awt.am;
import com.esial.util.LanguageSupport;
import com.diginet.digichat.common.a6;
import java.awt.Event;
import com.diginet.digichat.common.v;
import com.diginet.digichat.common.bg;
import com.diginet.digichat.common.a0;
import java.io.IOException;
import java.io.DataInput;
import com.diginet.digichat.network.t;
import com.diginet.digichat.common.Theme;
import java.awt.MenuItem;
import java.awt.Frame;
import com.diginet.digichat.util.l;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import com.diginet.digichat.common.a1;
import com.diginet.digichat.util.k;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.awt.Image;
import com.diginet.digichat.common.User;

public abstract class h extends User implements Runnable
{
    protected static final String[] a;
    protected static final String[] b;
    public static Image c;
    public static Image d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    protected int k;
    protected long l;
    public int m;
    public int n;
    public int o;
    public int p;
    protected long q;
    protected boolean r;
    protected boolean s;
    protected boolean t;
    protected boolean u;
    protected boolean v;
    protected DataInputStream w;
    protected DataOutputStream x;
    protected String y;
    protected String z;
    protected String aa;
    protected String ab;
    protected au ac;
    protected Thread ad;
    protected Socket ae;
    public x af;
    protected k ag;
    protected k ah;
    public k ai;
    public k aj;
    public k ak;
    public k al;
    public k am;
    public k an;
    public k ao;
    public k ap;
    public k aq;
    public k ar;
    public k as;
    public k at;
    public k au;
    public k av;
    public k aw;
    public k ax;
    public k ay;
    public k az;
    public a1 a0;
    public a1 a1;
    public Vector a2;
    public Vector a3;
    public Vector a4;
    public long[] a5;
    public long[] a6;
    public long[] a7;
    public long[] a8;
    public long[] a9;
    protected URL ba;
    protected boolean bb;
    protected boolean bc;
    public MediaTracker bd;
    protected AudioClip[] be;
    protected l bf;
    protected Frame bg;
    protected int bh;
    protected int bi;
    protected int bj;
    protected int bk;
    protected String bl;
    public boolean bm;
    public boolean bn;
    protected String bo;
    public boolean bp;
    public boolean bq;
    private MenuItem br;
    private MenuItem bs;
    public String bt;
    public String bu;
    public String bv;
    public String bw;
    public String bx;
    public String by;
    public String bz;
    public String b0;
    public String b1;
    public String b2;
    public String b3;
    public String b4;
    public String b5;
    public String b6;
    public URL b7;
    public URL b8;
    public URL b9;
    public URL ca;
    public URL cb;
    public int cc;
    public int cd;
    public int ce;
    public int cf;
    public int cg;
    public int ch;
    public int ci;
    public int cj;
    public int ck;
    public int cl;
    public int cm;
    public int cn;
    public int co;
    public int cp;
    public int cq;
    public int cr;
    public int cs;
    public int ct;
    public int cu;
    public boolean cv;
    public boolean cw;
    public boolean cx;
    public boolean cy;
    public boolean cz;
    public boolean c0;
    public boolean c1;
    public boolean c2;
    public boolean c3;
    public boolean c4;
    public boolean c5;
    public boolean c6;
    public boolean c7;
    public boolean c8;
    public boolean c9;
    public boolean da;
    public boolean db;
    public boolean dc;
    public boolean dd;
    public boolean de;
    public Theme df;
    
    public final boolean n() throws IOException {
        this.ae.setSoTimeout(5000);
        final t t = new t(this.w, false);
        if (t.b() == 67587) {
            this.q = t.b(0, 0);
            final byte[] d = t.d();
            com.diginet.digichat.network.t.b = new byte[d.length];
            for (int i = 0; i < d.length; ++i) {
                com.diginet.digichat.network.t.b[i] = (byte)(d[i] ^ com.diginet.digichat.network.t.a);
            }
            this.ae.setSoTimeout(0);
            final t t2 = new t(67587, 1);
            t2.a(0, 0, 360569446240092178L);
            this.ap(t2);
            return true;
        }
        return false;
    }
    
    public final String c(String s) {
        if (s != null && this.cw) {
            this.ap.a(false);
            try {
                synchronized (this.ap) {
                    for (int i = 0; i < this.ap.b(); ++i) {
                        s = ((a0)this.ap.c(i)).a(s);
                    }
                }
                // monitorexit(this.ap)
            }
            finally {
                this.ap.a();
            }
            this.aq.a(false);
            try {
                synchronized (this.aq) {
                    for (int j = 0; j < this.aq.b(); ++j) {
                        s = ((a0)this.aq.c(j)).a(s);
                    }
                }
                // monitorexit(this.aq)
            }
            finally {
                this.aq.a();
            }
        }
        return s;
    }
    
    protected abstract void d();
    
    public final void a(final bg bg) {
        new c1(this.af.b(), this, bg).setVisible(true);
    }
    
    public final void a(final String s, final int n, final int n2, final boolean b) {
        final t t = new t(33621776, 1);
        t.m = -1;
        t.l = -1;
        t.n = this.x();
        t.a(0, 0, -999);
        t.a(0, 1, n);
        t.a(0, 2, n2);
        t.a(0, 0, s);
        if (!b) {
            t.f(0, 58);
        }
        this.ap(t);
    }
    
    public final void a(final String bl, final String s, final String s2, final boolean b, final int n) {
        this.bl = bl;
        final t t = new t(67330, 1);
        t.m = -1;
        t.l = -1;
        t.n = this.x();
        t.a(0, 0, -999);
        t.a(0, 5, n);
        t.a(0, 0, bl);
        t.a(0, 1, s);
        t.a(0, 0, new v(s2));
        if (!b) {
            t.f(0, 58);
        }
        this.ap(t);
    }
    
    public final void n(final int n) {
        new cw(this.af.b(), this, n).setVisible(true);
    }
    
    public final void a(final String bl, final String s, final boolean b, final int n) {
        this.bl = bl;
        final t t = new t(67330, 1);
        t.m = -1;
        t.l = -1;
        t.n = this.x();
        t.a(0, 0, -999);
        t.a(0, 5, n);
        t.a(0, 0, bl);
        t.a(0, 1, s);
        if (!b) {
            t.f(0, 58);
        }
        this.ap(t);
    }
    
    public abstract void b(final t p0);
    
    public final void o(final int n) {
        final bf bf = (bf)this.al.d(n);
        if (bf != null) {
            this.a(bf);
        }
        else {
            System.err.println("no such room! " + n);
        }
    }
    
    public final void a(final bf bf) {
        if ((bf.u(48) && (this.b4 == null || (this.b4.length() == 0 && this.b5 == null) || this.b5.length() == 0)) || (bf.c && bf.k != null)) {
            new ax(this.af.b(), this, bf).setVisible(true);
        }
        if (bf.u(48)) {
            if (this.b4 == null || (this.b4.length() == 0 && this.b5 == null) || this.b5.length() == 0) {
                return;
            }
            final t t = new t(66049, 1);
            t.a(0, 0, this.x());
            t.a(0, 1, bf.x());
            t.a(0, 0, this.b4);
            t.a(0, 0, new v(this.b5));
            t.m = -1;
            t.l = -1;
            this.ap(t);
        }
        else if (!bf.c || bf.k == null) {
            final t t2 = new t(66049, 1);
            t2.a(0, 0, this.x());
            t2.a(0, 1, bf.x());
            t2.m = -1;
            t2.l = -1;
            this.ap(t2);
        }
    }
    
    public final long o() {
        return this.l;
    }
    
    public final int p() {
        return this.m;
    }
    
    public boolean e() {
        return false;
    }
    
    public final boolean q() {
        return this.r;
    }
    
    public final boolean r() {
        return this.s;
    }
    
    public final boolean s() {
        return this.u;
    }
    
    public final boolean t() {
        return this.c1;
    }
    
    public final int p(final int n) {
        int n2 = 0;
        this.aj.a(false);
        try {
            synchronized (this.aj) {
                for (int i = 0; i < this.aj.b(); ++i) {
                    final User2 user2 = (User2)this.aj.c(i);
                    if (user2.b == n && (!user2.u(23) || this.u(24))) {
                        ++n2;
                    }
                }
            }
            // monitorexit(this.aj)
        }
        finally {
            this.aj.a();
        }
        return n2;
    }
    
    public final void b(final boolean b) {
        this.af.b(b);
    }
    
    public final void a(final long n) {
        this.af.a(n);
    }
    
    public final void d(final String s) {
        this.af.a(s);
    }
    
    public boolean a(final Event event) {
        if (event.target == this.br) {
            this.h();
        }
        else if (this.aw.a(event.target)) {
            String s = (String)this.ay.c(this.aw.b(event.target));
            if (s == null) {
                s = "_blank";
            }
            this.a((URL)this.ax.c(this.aw.b(event.target)), s);
        }
        else if (event.target == this.bs) {
            this.c(0);
        }
        return true;
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return null;
    }
    
    public Image a(final URL url) {
        return null;
    }
    
    public abstract Image a(final String p0, final boolean p1);
    
    public abstract void a(final String p0, final String p1, final v p2, final String p3, final int p4) throws IOException;
    
    public void h() {
        this.i = true;
        this.bb = true;
        if (this.x != null) {
            final t t = new t(65794, 1);
            t.a(0, 0, this.x());
            t.a(0, 0, this.bx);
            t.m = -1;
            t.l = -1;
            this.ap(t);
            super.b = -999;
            if (this.af != null) {
                final Frame b = this.af.b();
                this.af.a();
                if (b != null) {
                    b.dispose();
                }
            }
        }
        this.ao(new t(66561, 0));
        this.d();
        this.g = false;
        this.h = false;
        if (this.b9 != null) {
            this.a(this.b9, "_self");
        }
    }
    
    public final void a(final ChatMessage chatMessage, final User2 user2) {
    }
    
    public final void a(final ChatMessage chatMessage) {
    }
    
    public final void a(final byte[] array) {
    }
    
    public final void a(final a6 a6, final a6 a7) {
        this.az.a(new Integer[] { new Integer(this.o), new Integer(this.p) }, a6.x());
        final Integer[] array = (Integer[])this.az.d(a7.x());
        if (array == null) {
            this.o = -1;
            this.p = -1;
        }
        else {
            this.o = array[0];
            this.p = array[1];
        }
    }
    
    public abstract void a(final URL p0, final String p1);
    
    protected final void a(final String s, final int m, final int l) {
        if (!this.u(76)) {
            return;
        }
        if (s.equals("")) {
            return;
        }
        final t t = new t(50400781, 1);
        t.n = this.x();
        t.m = m;
        t.l = l;
        t.a(0, 0, -999);
        t.a(0, 0, s);
        this.ap(t);
    }
    
    public final void b(final ChatMessage chatMessage) {
        if (this.af == null) {
            return;
        }
        if (this.c8) {
            if (chatMessage.r) {
                if (this.a1 == null) {
                    this.a1 = new a1(-999, "answer.gif");
                    this.a1.a = this.a(this.a1.getName(), true, 40);
                }
                chatMessage.h = this.a1.a;
            }
            else {
                if (this.a0 == null) {
                    this.a0 = new a1(-999, "question.gif");
                    this.a0.a = this.a(this.a0.getName(), true, 40);
                }
                chatMessage.h = this.a0.a;
            }
        }
        chatMessage.n = ((this.aj.d(chatMessage.f) == null) ? chatMessage.n : ((User)this.aj.d(chatMessage.f)).c);
        this.af.a(chatMessage);
    }
    
    protected final void y(final t t) {
        final byte[] d = t.d();
        if (d != null && d[0] == 3) {
            for (int c = t.c(), i = 0; i < c; ++i) {
                try {
                    if (t.a(i, 0) > this.o) {
                        this.o = t.a(i, 0);
                        final ChatMessage chatMessage = new ChatMessage(this.c(t.c(i, 0)), this.c(t.c(i, 1)), false, (a1)this.ai.d(t.a(i, 1)), t.a(i, 2), t.e(i, 0), t.e(i, 2));
                        chatMessage.a(t.b(i, 6), t.a(i, 3));
                        this.b(chatMessage);
                    }
                }
                catch (NullPointerException ex) {}
            }
        }
    }
    
    protected void z(final t t) {
        for (int i = 0; i < t.c(); ++i) {
            final User2 user2 = (User2)this.aj.d(t.a(i, 0));
            if (user2 != null) {
                this.a(user2, t.a(i, 1), t.b(i, 2), t.i(), t.h());
            }
        }
    }
    
    protected abstract void a(final User2 p0, final int p1, final long p2, final long p3, final int p4);
    
    protected final void aa(final t t) {
        this.b4 = null;
        this.b5 = null;
        new am(new Frame(), LanguageSupport.translate("Unauthorized"), LanguageSupport.translate("You have entered an incorrect login for the room."), this).setVisible(true);
    }
    
    protected final void ab(final t t) {
        if (this.b8 != null) {
            this.a(this.b8, "_blank");
        }
        else {
            new am(new Frame(), LanguageSupport.translate("You have been kicked"), t.c(0, 0), this).setVisible(true);
        }
        this.bb = true;
        this.ao(new t(66561, 0));
        this.d();
    }
    
    protected void ac(final t t) {
        final String[] array = new String[t.c()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = t.c(i, 0);
        }
        new am(this.af.b(), LanguageSupport.translate("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected final void ad(final t t) {
        final User user = (User)this.aj.d(t.n);
        String s = LanguageSupport.translate("Pushed") + ": " + t.c(0, 0);
        if (user.u(76)) {
            try {
                this.a(new URL(t.c(0, 0)), "_new");
            }
            catch (MalformedURLException ex) {
                s = LanguageSupport.translate("Pushed Malformed URL") + ": " + t.c(0, 0);
            }
            final t t2 = new t(66305, 1);
            t2.n = t.n;
            t2.m = t.m;
            t2.l = t.l;
            t2.a(0, 0, t.n);
            t2.a(0, 0, s);
            this.i(t2);
        }
    }
    
    protected final void ae(final t t) {
        String s = null;
        final int a = t.a(0, 1);
        switch (t.a(0, 0)) {
            case 2: {
                s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because your account has been disabled."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 4: {
                s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 3: {
                s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, com.diginet.digichat.common.d.a(), com.diginet.digichat.common.d.a(this.q) });
                break;
            }
            case 5: {
                s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because you have been banned from this %1 site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 6: {
                s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because no site with the specified ID could be found."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 8: {
                s = StringSubst.Substitute(LanguageSupport.translate("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 10: {
                s = StringSubst.Substitute(LanguageSupport.translate("An error has occured, you may not connect to %1. Please contact your chat administrator") + "." + LanguageSupport.translate("Error") + " : " + "2702", new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 9: {
                s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because the Server license has been exceeded."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 0: {
                if (a == 65793) {
                    s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, this.getName() });
                }
                else {
                    s = StringSubst.Substitute(LanguageSupport.translate("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { t.c(0, 0) });
                }
                break;
            }
            case 1:
            case 7: {
                if (a == 65793) {
                    s = StringSubst.Substitute(LanguageSupport.translate("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                }
                break;
            }
        }
        if (s == null) {
            s = LanguageSupport.translate("Your request could not be fulfilled.");
        }
        if (a == 65793) {
            this.bb = true;
            this.ao(new t(66561, 0));
            this.d();
        }
        new am(this.bg, LanguageSupport.translate("Note"), s, this).setVisible(true);
    }
    
    protected final void af(final t t) {
        if (!this.bb) {
            new am((this.af != null) ? this.af.b() : null, LanguageSupport.translate("Alert"), new String[] { t.c(0, 0), t.c(0, 1) }, this).setVisible(true);
        }
    }
    
    protected final void ag(final t t) {
        for (int i = 0; i < t.c(); ++i) {
            new am((this.af != null) ? this.af.b() : null, t.c(i, 0), t.c(i, 1), this).setVisible(true);
        }
    }
    
    protected abstract void i(final t p0);
    
    protected final void ah(final t t) {
        new am(this.af.b(), LanguageSupport.translate("Inactivity Timeout"), StringSubst.Substitute(LanguageSupport.translate("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(t.a(0, 0)) }), this).setVisible(true);
        this.bb = true;
        this.ao(new t(66561, 0));
        this.d();
    }
    
    protected final void ai(final t t) {
        final long[] a = t.a(-1);
        if (this.a6[0] == -999L && t.e(-1, 60)) {
            this.cw = true;
        }
        k k;
        if (t.e(-1, 59)) {
            if ("Admin".equals(this.y)) {
                this.a6 = a;
                k = this.ap;
            }
            else {
                k = this.aq;
            }
            this.a7 = a;
        }
        else {
            k = this.ap;
            this.a6 = a;
        }
        if (t.a(this.a7[0] | this.a6[0], 62) || t.a(this.a7[0] | this.a6[0], 61)) {
            this.cw = true;
        }
        k.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a2 = t.a(i, 0);
                a0 a3 = (a0)k.d(a2);
                if (t.e(i, 63)) {
                    if (a3 != null) {
                        k.f(a2);
                    }
                }
                else {
                    if (a3 == null) {
                        a3 = new a0(a2, t.c(i, 0));
                        k.a(a3);
                    }
                    else {
                        a3.g(t.c(i, 0));
                    }
                    a3.a = t.c(i, 1);
                    a3.b(t.a(i));
                }
            }
        }
        finally {
            k.a();
        }
    }
    
    protected final void aj(final t t) {
        final int a = t.a(0, 0);
        if (a == this.x()) {
            return;
        }
        if (this.aj.d(a) == null) {
            return;
        }
        final ad ad = (ad)this.ag.d(a);
        if (this.j && this.de && this.db) {
            ad.a(t.c(0, 0));
        }
    }
    
    protected final void ak(final t t) {
        final int a = t.a(0, 0);
        if (a == this.x()) {
            return;
        }
        if (this.aj.d(a) == null) {
            return;
        }
        final ad ad = (ad)this.ag.d(a);
        this.q(a);
    }
    
    protected final void al(final t t) {
        this.z = t.c(0, 0);
    }
    
    protected void p(final t t) {
        bf bf = null;
        DigiItem digiItem = null;
        this.al.a(true);
        try {
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                bf bf2 = (bf)this.al.d(a);
                if (t.e(i, 63) || t.c(i, 0) == null) {
                    if (bf2 != null) {
                        this.al.f(a);
                        if (this.af != null) {
                            this.af.a(bf2);
                        }
                    }
                    if (a == super.b) {
                        digiItem = bf2;
                    }
                }
                else {
                    if (bf2 == null) {
                        String c = t.c(i, 0);
                        if (c == null) {
                            System.err.println("null room name received.");
                            c = new String("no name");
                        }
                        bf2 = new bf(a, this.c(c));
                        this.al.a(bf2);
                    }
                    else {
                        bf2.g(this.c(t.c(i, 0)));
                    }
                    bf2.d = t.a(i, 1);
                    bf2.f = t.a(i, 2);
                    bf2.g = t.a(i, 3);
                    bf2.c = t.a(i, 4);
                    bf2.e = t.a(i, 5);
                    bf2.i = t.b(i, 6);
                    bf2.j = ((t.b(i, 8) == Long.MIN_VALUE) ? Long.MIN_VALUE : (t.b(i, 8) + System.currentTimeMillis()));
                    bf2.a = this.c(t.c(i, 1));
                    bf2.b = this.c(t.c(i, 2));
                    final v k = bf2.k;
                    bf2.k = t.d(i, 0);
                    if (bf2.k != null && !bf2.k.equals(k)) {
                        bf2.c = true;
                    }
                    if (bf2.u(45) != t.e(i, 45)) {
                        this.o = -1;
                        this.p = -1;
                    }
                    bf2.b(t.a(i));
                    if (t.e(i, 62)) {
                        this.bh = a;
                    }
                    if (t.e(i, 54)) {
                        this.bk = a;
                    }
                    final long j = bf2.i;
                    if (j > 0L) {
                        bf2.i = -999L;
                    }
                    else if (j < 0L && j != -999L) {
                        bf2.i = Long.MIN_VALUE;
                    }
                    if (a == super.b) {
                        if (this.c4) {
                            if (bf2.u(53)) {
                                this.d("Closed");
                            }
                            else if (j > 0L) {
                                this.a(j);
                            }
                            else if (j < 0L && j != -999L) {
                                this.d("Open");
                            }
                        }
                        if (bf2.u(53)) {
                            this.b(false);
                        }
                        else {
                            this.b(true);
                        }
                    }
                    if (this.af != null) {
                        this.af.c(bf2);
                    }
                    if (this.bl != null && bf2.getName().equals(this.bl)) {
                        this.bl = null;
                        bf = bf2;
                        bf.c = false;
                    }
                }
            }
            if (this.bh == -999) {
                this.bh = t.a(0, 0);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.al.a();
        }
        if (bf != null) {
            this.a(bf);
            this.af.a(true);
        }
        else if (digiItem != null) {
            final a6 a2 = (a6)this.al.d(this.bh);
            this.o(this.bh);
            new am(this.af.b(), DigiChatAppletAbstract.OEM_DigiChat, StringSubst.Substitute(LanguageSupport.translate("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.c(digiItem.getName()), this.c(a2.getName()) }), this).setVisible(true);
        }
    }
    
    protected final void am(final t t) {
        this.ak.a(true);
        try {
            this.ak.c();
            for (int i = 0; i < t.c(); ++i) {
                final int a = t.a(i, 0);
                bg bg = (bg)this.ak.d(a);
                if (t.e(i, 63) || t.c(i, 0) == null) {
                    if (bg != null) {
                        this.ak.f(a);
                    }
                }
                else {
                    if (bg == null) {
                        String c = t.c(i, 0);
                        if (c == null) {
                            System.err.println("null category name received.");
                            c = new String("no name");
                        }
                        bg = new bg(a, this.c(c));
                        this.ak.a(bg);
                    }
                    else {
                        bg.g(this.c(t.c(i, 0)));
                    }
                    bg.b = t.a(i, 1);
                    bg.e = t.a(i, 2);
                    bg.a(t.c(i, 1));
                    bg.b(t.a(i));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.ak.a();
            if (this.af != null) {
                this.af.a((bg)this.ak.d(1000));
            }
        }
    }
    
    protected final void an(final t t) {
        this.av.a(true);
        try {
            Theme df = null;
            Theme df2 = null;
            for (int i = 0; i < t.c(); ++i) {
                int n = t.a(i, 0);
                Theme theme = (Theme)this.av.d(n);
                if (t.e(i, 63)) {
                    this.av.f(n);
                }
                else {
                    if (theme == null) {
                        theme = new Theme(n, t.c(i, 0));
                        n = this.av.a(theme, n);
                        t.a(i, 0, n);
                    }
                    else {
                        theme.g(t.c(i, 0));
                    }
                    theme.outerBackground = new Color(t.a(i, 1));
                    theme.innerBackground = new Color(t.a(i, 2));
                    theme.helpText = new Color(t.a(i, 3));
                    theme.helpBackground = new Color(t.a(i, 4));
                    theme.tabsText = new Color(t.a(i, 5));
                    theme.tabsBackground = new Color(t.a(i, 6));
                    theme.normalMessages = new Color(t.a(i, 7));
                    theme.flaggedMessages = new Color(t.a(i, 8));
                    theme.normalBackground = new Color(t.a(i, 9));
                    theme.privateMessages = new Color(t.a(i, 10));
                    theme.privateBackground = new Color(t.a(i, 11));
                    theme.fontName = t.c(i, 1);
                    theme.fontStyle = t.a(i, 12);
                    theme.fontSize = t.a(i, 13);
                    theme.resetFont();
                    theme.b(t.a(i));
                    theme.setDirectory(t.c(i, 2));
                    theme.roundedCorners = t.a(i, 14);
                    theme.inputText = new Color(t.a(i, 15));
                    theme.inputBackground = new Color(t.a(i, 16));
                    if (theme.u(62)) {
                        df = (Theme)theme.clone();
                    }
                    if (n == DigiChatAppletAbstract.preferredTheme) {
                        df2 = (Theme)theme.clone();
                    }
                }
            }
            if (df2 != null) {
                this.df = df2;
            }
            else if (df != null) {
                this.df = df;
            }
            if (this.af != null) {
                this.af.d();
            }
            if (this.ag != null) {
                for (int j = 0; j < this.ag.b(); ++j) {
                    ((ad)this.ag.c(j)).b();
                }
            }
        }
        finally {
            this.av.a();
        }
    }
    
    public final synchronized void ao(final t t) {
        this.bf.a(t);
        this.notify();
        this.notify();
    }
    
    public abstract void c();
    
    public final void run() {
        this.bf.c();
        this.ac.start();
        try {
            while (!this.bb || !this.bf.a()) {
                while (this.bf.a() && !this.bb) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.b((t)this.bf.b());
            }
        }
        catch (Exception ex) {
            if (!this.bb) {}
        }
        finally {
            this.bb = true;
            this.d();
        }
    }
    
    public void a() throws IOException {
    }
    
    public final ch u() {
        return null;
    }
    
    protected final void v() {
        final t t = new t(50400786, 1);
        t.a(0, 0, this.o + 1);
        t.n = this.x();
        this.ap(t);
    }
    
    protected final void q(final int m) {
        final t t = new t(50400782, 1);
        t.m = m;
        t.n = this.x();
        t.a(0, 0, this.x());
        t.a(0, 0, this.aa);
        this.ap(t);
    }
    
    protected final void r(final int m) {
        final t t = new t(50400783, 1);
        t.m = m;
        t.n = this.x();
        t.a(0, 0, this.x());
        this.ap(t);
    }
    
    public final void ap(final t t) {
        try {
            t.a(this.x);
            this.x.flush();
        }
        catch (IOException ex) {
            if (!this.bb) {
                this.d();
            }
        }
    }
    
    public final void b(final String s, final int m, final int l) {
        if (s.length() > 5 && (s.substring(0, 5).equalsIgnoreCase("push:") || s.substring(0, 6).equalsIgnoreCase("push :")) && this.u(76)) {
            this.a(s.substring(s.indexOf(":") + 1).trim(), m, l);
            return;
        }
        final t t = new t(66305, 1);
        t.a(0, 0, s);
        t.a(0, 0, this.x());
        t.m = m;
        t.l = l;
        this.ap(t);
    }
    
    public final void e(final String s) {
        this.b(s, -1, super.b);
        this.l = System.currentTimeMillis();
    }
    
    public abstract void a(final ChatMessage p0, final User p1);
    
    public final void b(final User user) {
        if (this.b3 != null && this.b3.length() > 0) {
            try {
                this.a(new URL(this.b3 + "?NAME1=" + this.getName() + "&NAME2=" + user.getName()), "_blank");
            }
            catch (MalformedURLException ex) {}
        }
        else {
            final t t = new t(67074, 1);
            t.a(0, 0, this.x());
            t.m = user.x();
            this.ap(t);
        }
    }
    
    public final void a(final Font font) {
        this.df.fontName = font.getName();
        this.df.fontStyle = font.getStyle();
        this.df.fontSize = font.getSize();
        this.df.resetFont();
        if (this.af != null) {
            this.af.d();
        }
        if (this.ag != null) {
            for (int i = 0; i < this.ag.b(); ++i) {
                ((ad)this.ag.c(i)).b();
            }
        }
    }
    
    public final void c(final boolean bc) {
        this.bc = bc;
        this.aj.a(false);
        try {
            synchronized (this.aj) {
                for (int i = 0; i < this.aj.b(); ++i) {
                    final User2 user2 = (User2)this.aj.c(i);
                    if (this.bc || user2.b == super.b) {
                        this.af.a(user2, true);
                    }
                    else {
                        this.af.a(user2);
                    }
                }
            }
            // monitorexit(this.aj)
        }
        finally {
            this.aj.a();
        }
    }
    
    public void a(final boolean b) {
    }
    
    public final void d(final boolean r) {
        this.r = r;
    }
    
    public final void e(final boolean s) {
        this.s = s;
    }
    
    public final void f(final boolean u) {
        this.u = u;
    }
    
    public final void g(final boolean v) {
        this.v = v;
    }
    
    public final void h(final boolean c1) {
        this.c1 = c1;
    }
    
    public final void i(final boolean c2) {
        this.c2 = c2;
    }
    
    public final void a(final Frame frame) {
        Menu menu;
        if (this.b0 != null && !DigiChatAppletAbstract.OEM_DigiChat.equals("DigiChat")) {
            menu = new Menu(this.b0);
        }
        else {
            menu = new Menu(DigiChatAppletAbstract.OEM_DigiChat);
        }
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.aw.b(); ++i) {
            menu.add((MenuItem)this.aw.d(i));
        }
        if (this.aw.b() >= 1) {
            menu.addSeparator();
        }
        this.a(menu);
        if (!DigiChatAppletAbstract.lite) {
            menu.add(this.bs = new MenuItem(LanguageSupport.translate("Settings")));
        }
        if (!this.f) {
            menu.add(this.br = new MenuItem(LanguageSupport.translate("Logout")));
        }
        menuBar.add(menu);
        frame.setMenuBar(menuBar);
    }
    
    public void c(final int n) {
    }
    
    public void k() {
    }
    
    public final void w() {
        final String substitute = StringSubst.Substitute(LanguageSupport.translate("You have been disconnected from %1 for flooding."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
        final t t = new t(50400771, 1);
        t.m = this.x();
        t.a(0, 0, substitute);
        this.ap(t);
    }
    
    public final void f(final String s) {
        this.a(this.x(), s);
    }
    
    public final void a(final int n, final String s) {
        try {
            this.ah.a(true);
            Hashtable hashtable = (Hashtable)this.ah.d(n);
            if (hashtable == null) {
                this.cu = 0;
                hashtable = new Hashtable<Integer, Object[]>();
            }
            else {
                this.cu = hashtable.size();
            }
            hashtable.put(new Integer(this.cu), new Object[] { new Long(System.currentTimeMillis()), s });
            this.ah.a(hashtable, n);
            if (this.cs != 0 && this.ct != 0) {
                final Object[] array = hashtable.get(new Integer(this.cu - this.cs + 1));
                if (array != null && System.currentTimeMillis() - (long)array[0] < this.ct * 1000) {
                    this.w();
                }
            }
            if (this.cr != 0) {
                int n2 = 1;
                for (int i = this.cu - 1; i > this.cu - this.cr; --i) {
                    final Object[] array2 = hashtable.get(new Integer(i));
                    if (array2 == null) {
                        break;
                    }
                    (long)array2[0];
                    if (((String)array2[1]).equals(s)) {
                        ++n2;
                    }
                }
                if (n2 >= this.cr) {
                    this.w();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.ah.a();
        }
    }
    
    protected final void b(final int m, final String s) {
        final t t = new t(50400779, 1);
        t.a(0, 0, this.x());
        t.a(0, 0, s);
        t.m = m;
        t.l = -1;
        this.ap(t);
    }
    
    protected final void s(final int m) {
        final t t = new t(50400780, 1);
        t.a(0, 0, this.x());
        t.m = m;
        t.l = -1;
        this.ap(t);
    }
    
    public abstract boolean a(final ag p0);
    
    public abstract void a(final User p0);
    
    public abstract int b(final ag p0);
    
    public abstract void d(final int p0);
    
    public abstract void e(final int p0);
    
    public abstract void a(final int p0, final User p1);
    
    public abstract void a(final Menu p0);
    
    public h() {
        super(-999, null);
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = 1000;
        this.l = 0L;
        this.m = 250;
        this.n = 0;
        this.o = -1;
        this.p = -1;
        this.r = true;
        this.s = true;
        this.t = false;
        this.u = false;
        this.v = false;
        this.ae = null;
        this.ag = new k();
        this.ah = new k();
        this.ai = new k();
        this.aj = new k();
        this.ak = new k();
        this.al = new k();
        this.am = new k();
        this.an = new k();
        this.ao = new k();
        this.ap = new k();
        this.aq = new k();
        this.ar = new k();
        this.as = new k();
        this.at = new k();
        this.au = new k();
        this.av = new k();
        this.aw = new k();
        this.ax = new k();
        this.ay = new k();
        this.az = new k();
        this.a2 = new Vector();
        this.a3 = new Vector();
        this.a4 = new Vector();
        this.a5 = new long[2];
        this.a6 = new long[] { -999L, -999L };
        this.a7 = new long[2];
        this.a8 = new long[2];
        this.a9 = new long[2];
        this.bb = true;
        this.bc = false;
        this.bd = new MediaTracker(new Button());
        this.bf = new l();
        this.bh = -999;
        this.bi = -999;
        this.bj = -999;
        this.bk = -999;
        this.bm = false;
        this.bn = true;
        this.bp = false;
        this.bq = false;
        this.cc = -999;
        this.cd = 2;
        this.ce = -999;
        this.cf = -999;
        this.cg = -999;
        this.ch = -999;
        this.ci = 0;
        this.cj = 8;
        this.ck = 3;
        this.cl = 4;
        this.cm = 9;
        this.cn = 1;
        this.co = 0;
        this.cp = 7;
        this.cq = 2;
        this.cr = 0;
        this.cs = 0;
        this.ct = 0;
        this.cu = 0;
        this.cv = false;
        this.cw = false;
        this.cx = false;
        this.cy = false;
        this.cz = false;
        this.c0 = false;
        this.c1 = false;
        this.c2 = false;
        this.c3 = false;
        this.c4 = false;
        this.c5 = false;
        this.c6 = false;
        this.c7 = false;
        this.c8 = false;
        this.c9 = false;
        this.da = false;
        this.db = false;
        this.dc = false;
        this.dd = false;
        this.de = false;
        this.df = Theme.defaultTheme;
    }
    
    static {
        a = new String[] { null, null, null, null, null, null, null, null, null };
        b = new String[] { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
    }
}

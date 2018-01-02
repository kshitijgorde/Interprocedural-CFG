// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.util.filetransfer.bw;
import com.diginet.digichat.util.filetransfer.bu;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import com.diginet.digichat.common.a4;
import com.diginet.digichat.common.bt;
import com.diginet.digichat.util.l;
import com.diginet.digichat.common.bb;
import com.diginet.digichat.common.e;
import java.awt.Component;
import com.diginet.digichat.common.bd;
import com.diginet.digichat.common.j;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import com.diginet.digichat.awt.ah;
import com.diginet.digichat.util.ap;
import com.esial.util.d;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import com.diginet.digichat.util.tunnel3.aj;
import com.diginet.digichat.common.b;
import com.diginet.digichat.util.ch;
import java.io.IOException;
import com.diginet.digichat.common.x;
import java.net.MalformedURLException;
import java.awt.Image;
import java.applet.AudioClip;
import java.net.URL;
import com.diginet.digichat.network.v;
import java.awt.Frame;
import java.util.Enumeration;
import com.diginet.digichat.util.filetransfer.ab;
import java.awt.Dialog;
import java.util.Hashtable;

public abstract class h extends i
{
    private ac a;
    private Hashtable b;
    private Hashtable c;
    
    protected void d() {
        super.z.c();
        super.aa.c();
        super.ab.c();
        super.ac.c();
        super.ad.c();
        super.ae.c();
        super.af.c();
        super.ah.c();
        super.ai.c();
        super.aj.c();
        if (super.y != null) {
            for (int i = 0; i < super.y.b(); ++i) {
                ((z)super.y.c(i)).dispose();
            }
        }
        final Enumeration<Dialog> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement = elements.nextElement();
            if (nextElement instanceof Dialog) {
                nextElement.dispose();
            }
        }
        final Enumeration<ab> elements2 = this.b.elements();
        while (elements2.hasMoreElements()) {
            final ab nextElement2 = elements2.nextElement();
            if (nextElement2 instanceof ab) {
                nextElement2.e();
            }
        }
        this.c();
        if (super.x != null) {
            final Frame b = super.x.b();
            super.x.a();
            super.x = null;
            if (this.a != null) {
                this.a.setVisible(false);
            }
            this.a = null;
            if (b != null) {
                b.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        if (super.w != null) {
            try {
                super.q.close();
                super.r.close();
                super.w.close();
            }
            catch (Exception ex2) {}
        }
        super.w = null;
        super.r = null;
        super.q = null;
        super.aw = true;
    }
    
    public void b(final v v) {
        switch (v.b()) {
            case 67585: {
                this.u(v);
                break;
            }
            case 65794: {
                this.d(v);
                break;
            }
            case 66049: {
                this.r(v);
                break;
            }
            case 66305: {
                this.h(v);
                break;
            }
            case 66308: {
                this.e(v);
                break;
            }
            case 66306: {
                this.g(v);
                break;
            }
            case 66307: {
                this.w(v);
                break;
            }
            case 66561: {
                this.v(v);
                break;
            }
            case 66816:
            case 50400771: {
                this.s(v);
                break;
            }
            case 66817: {
                this.x(v);
                break;
            }
            case 67073: {
                this.i(v);
                break;
            }
            case 67074: {
                this.f(v);
                break;
            }
            case 67329: {
                this.j(v);
                break;
            }
            case 67330: {
                this.aa(v);
                break;
            }
            case 67331: {
                this.l(v);
                break;
            }
            case 67333: {
                this.y(v);
                break;
            }
            case 67334: {
                this.n(v);
                break;
            }
            case 67584: {
                this.c(v);
                break;
            }
            case 67586: {
                this.a(v);
                break;
            }
            case 67843: {
                this.m(v);
                break;
            }
            case 68608: {
                this.t(v);
                break;
            }
            case 67338: {
                this.z(v);
                break;
            }
            case 67341: {
                this.ab(v);
                break;
            }
            case 33621775: {
                this.k(v);
                break;
            }
            case 50400768: {
                this.o(v);
                break;
            }
            case 50400769: {
                this.p(v);
                break;
            }
            case 50400770: {
                this.q(v);
                break;
            }
        }
    }
    
    public boolean e() {
        return super.o;
    }
    
    public abstract AudioClip b(final URL p0);
    
    public Image a(final String s, final boolean b, final int n) {
        return this.a(s, b, n, true);
    }
    
    public Image a(final String s, final boolean b, final int n, final boolean b2) {
        try {
            URL url;
            if (b) {
                url = new URL(super.av, "Resources/" + super.s + "/" + s);
            }
            else {
                url = new URL(super.av, "Resources/" + s);
            }
            final Image a = this.a(url);
            if (a != null) {
                super.ay.addImage(a, b2 ? n : super.i);
                super.ay.statusID(b2 ? n : super.i, true);
                if (!b2) {
                    ++super.i;
                }
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public abstract Image a(final URL p0);
    
    public void f() {
    }
    
    public AudioClip[] c(final URL url) throws MalformedURLException {
        final AudioClip[] array = new AudioClip[com.diginet.digichat.client.i.a.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.b(new URL(url, "Sounds/" + com.diginet.digichat.client.i.b[i]));
        }
        return array;
    }
    
    public abstract void a(final String p0, final String p1, final x p2, final String p3, final int p4) throws IOException;
    
    public void a(final String s, final String s2, final x x, final URL av, final int n, final String s3) throws IOException {
        try {
            this.a();
            super.ax = false;
            super.af.c();
            String s4;
            if (DigiChatAppletAbstract.altHost != null) {
                s4 = DigiChatAppletAbstract.altHost;
            }
            else {
                s4 = av.getHost();
            }
            super.av = av;
            this.d(s);
            Label_0092: {
                if (super.az == null) {
                    if (ch.c < 66048) {
                        if (ch.f) {
                            break Label_0092;
                        }
                    }
                    try {
                        super.az = this.c(super.av);
                    }
                    catch (Throwable t) {
                        super.az = null;
                    }
                }
            }
            int n2;
            if (ch.e && ch.c < 65792 && ch.b == 1) {
                n2 = 2;
            }
            else {
                n2 = 256;
            }
            boolean b = false;
            if (DigiChatAppletAbstract.preferredPort != 0) {
                try {
                    if (DigiChatAppletAbstract.preferredPort == com.diginet.digichat.common.b.c) {
                        if (com.diginet.digichat.common.b.d == "") {
                            throw new Exception("HttpServlet address not specified");
                        }
                        super.w = new aj(com.diginet.digichat.common.b.d);
                    }
                    else {
                        super.w = new Socket(s4, DigiChatAppletAbstract.preferredPort);
                    }
                    super.r = new DataOutputStream(new BufferedOutputStream(super.w.getOutputStream(), 256));
                    if (DigiChatAppletAbstract.preferredPort == com.diginet.digichat.common.b.c) {
                        super.q = new DataInputStream(super.w.getInputStream());
                    }
                    else {
                        super.q = new DataInputStream(new BufferedInputStream(super.w.getInputStream(), n2));
                    }
                    b = this.h();
                }
                catch (Exception ex8) {}
            }
            if (!b) {
                for (int n3 = 0; !b && n3 < com.diginet.digichat.common.b.a.size(); ++n3) {
                    final int a = com.diginet.digichat.common.b.a(n3);
                    try {
                        if (a != DigiChatAppletAbstract.preferredPort) {
                            if (a == com.diginet.digichat.common.b.c) {
                                if (com.diginet.digichat.common.b.d == "") {
                                    throw new Exception("HttpServlet address not specified");
                                }
                                super.w = new aj(com.diginet.digichat.common.b.d);
                            }
                            else {
                                super.w = new Socket(s4, a);
                            }
                            super.r = new DataOutputStream(new BufferedOutputStream(super.w.getOutputStream(), 256));
                            if (com.diginet.digichat.common.b.a(n3) == com.diginet.digichat.common.b.c) {
                                super.q = new DataInputStream(super.w.getInputStream());
                            }
                            else {
                                super.q = new DataInputStream(new BufferedInputStream(super.w.getInputStream(), n2));
                            }
                            b = this.h();
                        }
                    }
                    catch (Exception ex) {
                        if (n3 == com.diginet.digichat.common.b.a.size() - 1) {
                            throw ex;
                        }
                    }
                }
            }
            if (b) {
                if (super.u != null && super.u.isAlive()) {
                    super.u.suspend();
                }
                if (super.v != null && super.v.isAlive()) {
                    super.v.suspend();
                }
                super.u = new ao(this);
                super.v = new Thread(this, "Decoder");
                super.aw = false;
                super.v.start();
                final v v = new v(65793, 1);
                v.a(0, 0, n);
                v.a(0, 1, super.a4);
                v.a(0, 0, s);
                v.a(0, 1, "en");
                v.a(0, 2, s3);
                v.a(0, 3, s2);
                v.a(0, 0, x);
                if (!this.l() && super.a4 != -999) {
                    v.f(0, 2);
                }
                if (this.i(23)) {
                    v.f(0, 23);
                }
                if (this.n()) {
                    v.f(0, 3);
                }
                if (super.h && (!ch.e || ch.b != 1)) {
                    v.f(0, 21);
                    v.f(0, 20);
                }
                this.ad(v);
            }
            else {
                System.err.println("acknowledge() failed!!!");
            }
        }
        catch (UnknownHostException ex2) {
            this.c();
            new ah(super.a1, com.diginet.digichat.util.ap.a(com.esial.util.d.a("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.c();
            new ah(super.a1, com.diginet.digichat.util.ap.a(com.esial.util.d.a("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.c();
            new ah(super.a1, com.diginet.digichat.util.ap.a(com.esial.util.d.a("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.c();
            new ah(super.a1, com.diginet.digichat.util.ap.a(com.esial.util.d.a("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            ex6.printStackTrace();
            this.c();
            new ah(super.a1, com.diginet.digichat.util.ap.a(com.esial.util.d.a("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            ex7.printStackTrace();
            this.c();
            new ah(super.a1, com.diginet.digichat.util.ap.a(com.esial.util.d.a("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex7, this).setVisible(true);
        }
        super.e = false;
        super.f = false;
        super.g = false;
    }
    
    public void a(final URL url, final String s) {
    }
    
    public void a(final int n) {
        if (super.az != null && n > 0 && n <= super.az.length && super.az[n - 1] != null) {
            try {
                super.az[n - 1].stop();
                super.az[n - 1].play();
            }
            catch (Throwable t) {}
        }
    }
    
    public void a(final String s, final j j, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.a(s, j, n, n2, b, false, n3, n4, null);
    }
    
    public void a(final String s, final j j, final int n, final int n2, final boolean b, final boolean b2, final long n3, final int n4, final byte[] a) {
        if (super.x != null) {
            int q = j.q();
            if (q == this.q()) {
                q = n;
            }
            j.r();
            final ay ay = new ay(this.b(s), j, n != -1 && n != -3 && n != -2 && !b, (bd)super.z.d(j.a));
            ay.a(n == -3 || n == -2);
            ay.a(n3, n4);
            if (b2) {
                ay.g = a[0];
            }
            else {
                ay.a = a;
            }
            if (ay.p) {
                final a5 a2 = (a5)super.y.d(q);
                if (a2 != null) {
                    a2.a(ay);
                }
                else if (super.a9) {
                    this.a(ay, j);
                }
                else {
                    super.x.a(ay);
                }
            }
            else {
                ay.n = j.c;
                if (n == -3 || n == -2) {
                    this.a(ay);
                }
                else {
                    super.x.a(ay);
                }
            }
        }
    }
    
    protected void c(final v v) {
        this.h(v.k);
        this.a(v.a(0));
        super.s = v.c(0, 0);
        com.diginet.digichat.client.i.a[0] = com.esial.util.d.a("Bass");
        com.diginet.digichat.client.i.a[1] = com.esial.util.d.a("Bell");
        com.diginet.digichat.client.i.a[2] = com.esial.util.d.a("Castanet");
        com.diginet.digichat.client.i.a[3] = com.esial.util.d.a("Chime");
        com.diginet.digichat.client.i.a[4] = com.esial.util.d.a("Conga");
        com.diginet.digichat.client.i.a[5] = com.esial.util.d.a("Cow Bell");
        com.diginet.digichat.client.i.a[6] = com.esial.util.d.a("Double Bell");
        com.diginet.digichat.client.i.a[7] = com.esial.util.d.a("Drum Roll");
        com.diginet.digichat.client.i.a[8] = com.esial.util.d.a("Harp");
        if (!"Admin".equals(super.s)) {
            if (super.ca.i()) {
                super.ca.z = this.a(super.ca.f() + "background.gif", true);
            }
            if (super.ca.j()) {
                super.ca.aa = this.a(super.ca.f() + "chatbackground.gif", true);
            }
            if (DigiChatAppletAbstract.embedded) {
                (super.x = new be(this, null)).setVisible(false);
                DigiChatAppletAbstract.login.setVisible(false);
                DigiChatAppletAbstract.applet.add(super.x.c());
                ((be)super.x).validate();
            }
            else {
                super.x = (y)new bs(this).a();
            }
            this.f();
        }
    }
    
    protected void a(final v v) {
        super.e = true;
        super.f = true;
        super.b = -999;
        this.f();
        if (super.z.d(super.bn) == null) {
            this.b(super.a3);
        }
        else {
            this.b(super.bn);
        }
        final bc bc = (bc)super.ab.d(super.a4);
        final bc bc2 = (bc)super.ab.d(super.a2);
        if (bc == null || (bc.h == null && bc2.h != null)) {
            this.a(bc2);
        }
        else {
            this.a(bc);
        }
        if (super.x != null) {
            ((aq)super.x).e().b();
        }
    }
    
    protected void g() {
        if (!com.diginet.digichat.common.e.a) {
            return;
        }
        if (!super.e && !super.f) {
            if (super.x != null) {
                ((aq)super.x).e().b();
            }
            int b;
            if (super.ab.d(super.a4) == null) {
                b = super.a2;
            }
            else {
                b = super.a4;
            }
            super.f = true;
            final bc bc = (bc)super.ab.d(b);
            if (bc != null && bc.b && bc.h != null) {
                super.b = -999;
                new at(super.x.b(), this, bc).setVisible(true);
            }
            super.b = b;
        }
    }
    
    protected void a(final aw aw, final int n, final long n2, final int n3) {
        final int b = aw.b;
        final bc bc = (bc)super.ab.d(n);
        final bc bc2 = (bc)super.ab.d(aw.b);
        this.i(26);
        this.g();
        if (aw.q() == this.q()) {
            if (bc2 != null) {
                bc2.a = false;
            }
            bc.a = true;
            super.b = n;
            if (super.x != null) {
                if (!super.ax) {
                    this.b(false);
                }
                super.x.b(bc);
                if (!super.x.isVisible()) {
                    super.x.setVisible(true);
                }
                super.x.c().validate();
            }
        }
        if (bc != null) {
            bc.c = this.g(bc.q());
            if (bc2 != null && bc != null) {
                if (bc2.q() != bc.q() && (!aw.i(23) || this.i(24))) {
                    final bc bc3 = bc;
                    ++bc3.c;
                }
            }
            else if (bc2 == null && bc != null && (!aw.i(23) || this.i(24))) {
                final bc bc4 = bc;
                ++bc4.c;
            }
            if (super.x != null) {
                super.x.c(bc);
            }
        }
        if (bc2 != null) {
            bc2.c = this.g(bc2.q());
            if (bc2 != null && bc != null && bc2.q() != bc.q() && (!aw.i(23) || this.i(24))) {
                final bc bc5 = bc2;
                --bc5.c;
            }
            if (super.x != null) {
                super.x.c(bc2);
            }
        }
        if (n == super.b && aw.b != n) {
            if (bc != null && !bc.i(57) && (!aw.i(23) || this.i(24))) {
                final ay ay = new ay(com.diginet.digichat.util.ap.a(com.esial.util.d.a("(This user has entered %1)"), new String[] { this.b(bc.r()) }), aw, false, (bd)super.z.d(aw.a));
                ay.a(n2, n3);
                this.a(super.bt);
                if (super.x != null) {
                    super.x.a(ay);
                }
            }
        }
        else if (aw.b == super.b && n != super.b && bc2 != null && bc != null && !bc2.i(57) && (!aw.i(23) || this.i(24))) {
            final ay ay2 = new ay(com.diginet.digichat.util.ap.a(com.esial.util.d.a("(This user has moved to %1)"), new String[] { this.b(bc.r()) }), aw, false, (bd)super.z.d(aw.a));
            ay2.a(n2, n3);
            this.a(super.bx);
            if (super.x != null) {
                super.x.a(ay2);
            }
        }
        aw.b = n;
        if (super.x != null) {
            if (super.ax || n == super.b) {
                super.x.a(aw, true);
            }
            else if (!super.ax && b == super.b) {
                super.x.a(aw);
            }
        }
    }
    
    protected void d(final v v) {
        this.i(26);
        this.g();
        for (int i = 0; i < v.c(); ++i) {
            final aw aw = (aw)super.aa.d(v.a(i, 0));
            if (aw != null) {
                final bc bc = (bc)super.ab.d(aw.b);
                final av av = new av(aw.q(), aw.r());
                av.a = false;
                if (bc != null) {
                    if (!aw.i(23) || this.i(24)) {
                        final bc bc2 = bc;
                        --bc2.c;
                    }
                    if (super.x != null) {
                        super.x.c(bc);
                    }
                    if (aw.b == super.b && !bc.i(57) && (!aw.i(23) || this.i(24))) {
                        final String c = v.c(i, 0);
                        String s;
                        if (c == null) {
                            s = com.diginet.digichat.util.ap.a(com.esial.util.d.a("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        else {
                            s = this.b(c) + " " + com.diginet.digichat.util.ap.a(com.esial.util.d.a("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        final ay ay = new ay(s, aw, false, (bd)super.z.d(aw.a));
                        ay.a(v.i(), v.h());
                        this.a(super.bx);
                        if (super.x != null) {
                            super.x.a(ay);
                        }
                    }
                }
                if (super.x != null) {
                    super.x.a(aw);
                    ((aq)super.x).a(av, false, false);
                }
                final a5 a5 = (a5)super.y.d(aw.q());
                if (a5 != null) {
                    a5.dispose();
                }
                super.aa.a(true);
                try {
                    super.aa.c(aw);
                }
                finally {
                    super.aa.a();
                }
            }
        }
    }
    
    protected void e(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            if (v.d() != null) {
                this.a(v.d());
            }
            j j = (j)super.aa.d(v.a(i, 0));
            if (j == null && this.n() && v.e(0, 3)) {
                j = new j(-999, v.c(0, 1));
                j.a = v.a(0, 1);
            }
            if (j == null) {
                j = new j(-999, "Guest");
                j.a = super.a3;
            }
            if (j != null) {
                int n = j.q();
                if (n == this.q()) {
                    n = v.k;
                }
                if (!j.d) {
                    switch (v.k) {
                        case -3:
                        case -2:
                        case -1: {
                            if (j.c) {
                                this.a(super.bv);
                            }
                            else {
                                this.a(super.bu);
                            }
                            break;
                        }
                        default: {
                            if (super.y.a(n)) {
                                this.a(super.bu);
                            }
                            else {
                                this.a(super.bw);
                            }
                            break;
                        }
                    }
                    if (v.d() != null) {
                        this.a(v.c(i, 0), j, v.k, v.j, true, false, v.i(), v.h(), v.d());
                    }
                    else {
                        this.a(v.c(i, 0), j, v.k, v.j, true, v.i(), v.h());
                    }
                }
            }
        }
    }
    
    protected void f(final v v) {
        final j j = (j)super.aa.d(v.a(0, 0));
        if (j != null) {
            final v v2 = new v(67073, 1);
            v2.k = j.q();
            v2.a(0, 0, this.q());
            if (!super.b4 || j.i(39)) {
                v2.a(0, 1, super.bq);
                if (super.bc != null && super.bc.length() > 0) {
                    v2.a(0, 0, super.bc);
                }
                if (super.bg != null && super.bg.length() > 0) {
                    v2.a(0, 1, super.bg);
                }
                if (super.bd != null && super.bd.length() > 0) {
                    v2.a(0, 2, super.bd);
                }
                if (super.be != null && super.be.length() > 0) {
                    v2.a(0, 3, super.be);
                }
                if (super.br != -999) {
                    v2.f(0, super.br);
                }
            }
            this.ad(v2);
        }
    }
    
    protected void g(final v v) {
        this.i(26);
        if (super.x != null) {
            for (int i = 0; i < v.c(); ++i) {
                final ay ay = new ay(this.b(v.c(i, 1)), this.b(v.c(i, 0)), false, (bd)super.z.d(v.a(i, 0)), -999, false, false);
                ay.a(v.i(), v.h());
                super.x.a(ay);
                if (v.k == this.q()) {
                    this.a(super.bw);
                }
                else {
                    this.a(super.bu);
                }
            }
        }
    }
    
    protected void h(final v v) {
        this.g();
        for (int i = 0; i < v.c(); ++i) {
            j j = (j)super.aa.d(v.a(i, 0));
            if (j == null && this.n() && v.e(0, 3)) {
                j = new j(-999, v.c(0, 1));
                j.a = v.a(0, 1);
            }
            if (j != null) {
                int n = j.q();
                if (n == this.q()) {
                    n = v.k;
                }
                if (v.k != this.q() && v.e(0, 20)) {
                    return;
                }
                if (!j.d) {
                    if (!v.m) {
                        switch (v.k) {
                            case -3:
                            case -2:
                            case -1: {
                                if (j.c) {
                                    this.a(super.bv);
                                }
                                else {
                                    this.a(super.bu);
                                }
                                break;
                            }
                            default: {
                                if (super.y.a(n)) {
                                    this.a(super.bu);
                                }
                                else {
                                    this.a(super.bw);
                                }
                                break;
                            }
                        }
                    }
                    if (v.d() != null) {
                        this.a(v.c(i, 0), j, v.k, v.j, false, v.e(0, 20), v.i(), v.h(), v.d());
                    }
                    else {
                        this.a(v.c(i, 0), j, v.k, v.j, false, v.i(), v.h());
                    }
                }
            }
        }
    }
    
    protected void i(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            final j j = (j)super.aa.d(v.a(i, 0));
            if (j != null) {
                if (super.b7) {
                    final String c = v.c(i, 2);
                    if (c != null) {
                        try {
                            this.a(new URL(c), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new a9(super.x.b(), this, j, v, i);
                }
            }
        }
    }
    
    protected void j(final v v) {
        super.au = v.a(-1);
        super.ac.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bb bb = (bb)super.ac.d(a);
                if (v.e(i, 63)) {
                    if (bb != null) {
                        super.ac.f(a);
                    }
                }
                else {
                    if (bb == null) {
                        bb = new bb(a, v.c(i, 0));
                        super.ac.a(bb);
                    }
                    else {
                        bb.d(v.c(i, 0));
                    }
                    bb.d = v.a(i, 1);
                    bb.e = v.a(i, 2);
                    bb.b = v.c(i, 1);
                    bb.a = v.c(i, 2);
                    bb.c = v.c(i, 3);
                    bb.a(v.a(i));
                    bb.f = this.a("banners/" + bb.a, true, 30);
                    if (super.x != null) {
                        ((aq)super.x).a(bb);
                    }
                }
            }
        }
        finally {
            super.ac.a();
        }
    }
    
    protected void k(final v v) {
        for (int c = v.c(), i = 0; i < c; ++i) {
            final bt bt = new bt(v.a(i, 0), v.c(i, 0));
            bt.a(v.a(i));
            bt.c = v.c(i, 1);
            if (!bt.i(63)) {
                bt.b = this.a("emoticons/" + bt.c, true, 50);
            }
            com.diginet.digichat.common.bt.a(bt);
        }
    }
    
    protected void l(final v v) {
        super.z.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bd bd = (bd)super.z.d(a);
                if (v.e(i, 63)) {
                    if (bd != null) {
                        super.z.f(a);
                    }
                }
                else {
                    if (bd == null) {
                        bd = new bd(a, v.c(i, 0));
                        super.z.a(bd);
                    }
                    else {
                        bd.d(v.c(i, 0));
                    }
                    bd.a = this.a("userIcons/" + bd.r(), true, 40);
                    bd.a(v.a(i));
                    if (v.e(i, 62)) {
                        super.a3 = a;
                    }
                }
            }
        }
        finally {
            super.z.a();
        }
    }
    
    protected void m(final v v) {
        super.bi = v.c(0, 0);
        super.bj = v.c(0, 1);
        super.ar = v.a(0);
        super.by = v.a(0, 0);
        super.bz = v.a(0, 1);
        super.b0 = v.a(0, 2);
        super.b1 = v.a(0, 3);
        super.b2 = v.a(0, 4);
        super.b3 = v.a(0, 5);
        if (super.x != null) {
            ((aq)super.x).a(super.bi);
            ((aq)super.x).e().b((super.bz > 0) ? super.bz : 7);
        }
    }
    
    protected void n(final v v) {
        this.g();
        super.aa.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                aw aw = (aw)super.aa.d(a);
                if (v.e(i, 63)) {
                    if (aw != null) {
                        final bc bc = (bc)super.ab.d(v.a(i, 2));
                        if (bc != null) {
                            if (!aw.i(23) || this.i(24)) {
                                final bc bc2 = bc;
                                --bc2.c;
                            }
                            if (super.x != null) {
                                super.x.c(bc);
                            }
                        }
                        final av av = new av(aw.q(), aw.r());
                        av.a = false;
                        super.aa.f(a);
                        if (super.x != null) {
                            super.x.a(aw);
                            ((aq)super.x).a(av, false, false);
                        }
                        final a5 a2 = (a5)super.y.d(aw.q());
                        if (a2 != null) {
                            a2.dispose();
                        }
                    }
                }
                else {
                    final String b = this.b(v.c(i, 0));
                    if (aw == null) {
                        aw = new aw(a, b);
                        if (!aw.i(23) || this.i(24)) {
                            super.aa.a(aw);
                        }
                    }
                    else {
                        aw.d(b);
                    }
                    aw.a = v.a(i, 1);
                    aw.a = (bd)super.z.d(aw.a);
                    aw.b = v.a(i, 2);
                    aw.a(v.a(i));
                    final String c = v.c(i, 1);
                    final String c2 = v.c(i, 2);
                    if (c != null) {
                        aw.e = c;
                    }
                    if (c2 != null) {
                        aw.f = c2;
                    }
                    if (a == this.q()) {
                        this.d(aw.r());
                        if (aw.a != -999) {
                            super.bn = aw.a;
                        }
                    }
                    final av av2 = new av(aw.q(), aw.r());
                    av2.a = true;
                    av2.a = aw.a;
                    av2.a = (bd)super.z.d(aw.a);
                    av2.b = aw.b;
                    av2.a(aw.s());
                    if (super.x != null) {
                        super.x.a(aw, false);
                        ((aq)super.x).a(av2, false, false);
                    }
                }
            }
        }
        finally {
            super.aa.a();
        }
    }
    
    public void a(final ay ay, final j j) {
        final int q = j.q();
        if (((a4)super.ab.d(j.b)).a() && j.i(59) && !super.a6) {
            return;
        }
        z z = (z)super.y.d(q);
        if (z == null) {
            z = new a5(super.x.b(), this, q);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = screenSize.width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.y.b() % 4) {
                case 0: {
                    n3 = 0;
                    n4 = 0;
                    break;
                }
                case 1: {
                    n3 = 0;
                    n4 = n + 10;
                    break;
                }
                case 2: {
                    n3 = n2 + 10;
                    n4 = 0;
                    break;
                }
                default: {
                    n3 = n2 + 10;
                    n4 = n + 10;
                    break;
                }
            }
            if (n > 400) {
                n = 400;
            }
            if (n2 > 300) {
                n2 = 300;
            }
            z.reshape(2 + n4, 10 + n3, n, n2);
            super.y.a(z, q);
        }
        if (ay != null) {
            z.a(ay);
        }
        z.setVisible(true);
    }
    
    public void b(final int n) {
        final v v = new v(67334, 1);
        v.a(0, 0, this.q());
        v.a(0, 1, n);
        v.a(0, 0, this.r());
        v.k = -1;
        v.j = -1;
        this.ad(v);
    }
    
    public void a(final boolean o) {
        super.o = o;
    }
    
    public void c(final int n) {
        if (super.x.b() != null) {
            super.x.b().setCursor(3);
        }
        if (this.a == null) {
            this.a = new ac(super.x.b(), this);
        }
        if (n >= 0) {
            this.a.a(n);
        }
        if (this.a.isVisible()) {
            this.a.toFront();
        }
        else {
            this.a.setVisible(true);
        }
        if (super.x.b() != null) {
            super.x.b().setCursor(0);
        }
    }
    
    public boolean a(final ab ab) {
        if (this.c.get(ab.c()) == null) {
            this.c.put(ab.c(), ab);
            return true;
        }
        return false;
    }
    
    public void a(final j j) {
        this.c.remove(j);
    }
    
    public int b(final ab ab) {
        this.a(ab.c());
        if (super.aa.d(ab.c().q()) == null) {
            ab.e();
        }
        final byte b = (byte)new Random().nextInt();
        final byte b2 = (b < 0) ? ((byte)(-b)) : b;
        final v v = new v(50400768, 1);
        v.a(0, 0, this.q());
        v.a(0, 1, b2);
        v.a(0, 0, ab.a());
        v.a(0, 1, ab.b());
        v.k = ab.c().q();
        v.j = -1;
        this.ad(v);
        final v v2 = new v(66305, 1);
        v2.a(0, 0, "[File Transfer Requested: file name " + ab.a() + ", size " + this.a(ab.b()) + ". Please click this message to accept.]");
        v2.a(0, 0, this.q());
        v2.f(0, 20);
        v2.a(new byte[] { b2 });
        v2.k = ab.c().q();
        v2.j = -1;
        this.ad(v2);
        this.b.put(new Integer(b2), ab);
        return b2;
    }
    
    protected void o(final v v) {
        this.b.put(new Integer(v.a(0, 1)), new bu((j)super.aa.d(v.a(0, 0)), v.c(0, 0), v.c(0, 1), v.a(0, 1)));
    }
    
    protected void p(final v v) {
        synchronized (this.b) {
            final ab remove = this.b.remove(new Integer(v.a(0, 1)));
            if (remove instanceof ab) {
                remove.e();
            }
        }
        // monitorexit(this.b)
    }
    
    public void d(final int n) {
        synchronized (this.b) {
            final bu value = this.b.get(new Integer(n));
            if (value == null) {
                // monitorexit(this.b)
                return;
            }
            if (value instanceof bu) {
                if (super.aa.d(value.a.q()) == null) {
                    this.b.remove(new Integer(n));
                }
                else {
                    this.b.put(new Integer(n), com.diginet.digichat.util.filetransfer.bw.a(this, value));
                }
            }
        }
        // monitorexit(this.b)
    }
    
    public void a(final int n, final j j) {
        final v v = new v(50400770, 1);
        v.a(0, 0, this.q());
        v.a(0, 1, n);
        v.k = j.q();
        v.j = -1;
        this.ad(v);
    }
    
    protected void q(final v v) {
        final ab ab = this.b.get(new Integer(v.a(0, 1)));
        if (ab == null) {
            return;
        }
        ab.f();
    }
    
    public void e(final int n) {
        final ab ab = this.b.remove(new Integer(n));
        if (ab == null) {
            return;
        }
        final v v = new v(50400769, 1);
        v.a(0, 0, this.q());
        v.a(0, 1, n);
        v.k = ab.c().q();
        v.j = -1;
        this.ad(v);
        final v v2 = new v(66305, 1);
        v2.a(0, 0, "[File Transfer Cancelled: file name " + ab.a() + ", size " + this.a(ab.b()) + ".]");
        v2.a(0, 0, this.q());
        v2.f(0, 20);
        v2.a(new byte[] { (byte)n });
        v2.k = ab.c().q();
        v2.j = -1;
        this.ad(v2);
    }
    
    public String a(final String s) {
        if (s.length() < 4) {
            return s + " bytes";
        }
        try {
            double n = Long.parseLong(s) / 1024L;
            boolean b = false;
            if (n > 1024.0) {
                n /= 1024.0;
                b = true;
            }
            String s2 = Math.floor(n * 1000.0 + 5.0) / 1000.0 + "";
            final int index = s2.indexOf(46);
            final int n2 = s2.length() - 1;
            if (index < 0) {
                s2 += ".00";
            }
            else if (index == 0) {
                s2 = "0" + s2;
            }
            else if (index == n2) {
                s2 += "00";
            }
            else if (index == n2 - 1) {
                s2 += "0";
            }
            else if (index + 2 < n2) {
                s2 = s2.substring(0, index + 3);
            }
            return s2 + (b ? " MB" : " KB");
        }
        catch (NumberFormatException ex) {
            return s + " bytes";
        }
    }
    
    public h() {
        this.b = new Hashtable();
        this.c = new Hashtable();
    }
}

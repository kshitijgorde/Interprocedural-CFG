// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import VT_6_1_0_11.ca;
import java.util.List;
import java.awt.Cursor;
import java.io.IOException;
import VT_6_1_0_11.dv;
import VT_6_1_0_11.bW;
import javax.swing.SwingUtilities;
import VT_6_1_0_11.an;
import VT_6_1_0_11.be;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import VT_6_1_0_11.aT;
import VT_6_1_0_11.h;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import com.hw.client.util.c;
import VT_6_1_0_11.cP;
import VT_6_1_0_11.bs;
import javax.swing.JLabel;
import javax.swing.JButton;
import VT_6_1_0_11.bR;
import java.util.Vector;
import VT_6_1_0_11.B;
import java.io.InputStream;
import VT_6_1_0_11.du;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import VT_6_1_0_11.cf;

public final class a implements cf, ActionListener, KeyListener
{
    private static Color a;
    private static Color b;
    private int c;
    private du d;
    private InputStream e;
    private g f;
    private boolean g;
    private B h;
    private boolean i;
    private Vector j;
    private Vector k;
    private Vector l;
    private bR m;
    private B n;
    private int o;
    private long p;
    private byte[] q;
    private f r;
    private boolean s;
    private boolean t;
    private boolean w;
    private boolean x;
    private JButton y;
    private JButton z;
    private JLabel A;
    private bs B;
    
    public a(final g f) {
        this.p = 0L;
        this.q = new byte[10240];
        this.f = f;
        this.r = new f();
        this.s = false;
        this.t = false;
        this.w = false;
        this.x = false;
        this.k = new Vector();
        this.j = new Vector();
        this.l = new Vector();
        (this.y = new cP()).setIcon(com.hw.client.util.c.a("/images/voicedirect/btn_raise.gif"));
        this.y.setRolloverIcon(com.hw.client.util.c.a("/images/voicedirect/btn_raise_rollover.gif"));
        this.y.setDisabledIcon(com.hw.client.util.c.a("/images/voicedirect/btn_raise_disabled.gif"));
        this.y.setPressedIcon(com.hw.client.util.c.a("/images/voicedirect/btn_raise_pressed.gif"));
        this.y.setToolTipText(f.e("btn_raise_hand"));
        this.y.setActionCommand("ACTION_RAISE_HAND");
        com.hw.client.util.c.a(this.y);
        VT_6_1_0_11.h.a(this.y);
        (this.z = new cP()).setIcon(com.hw.client.util.c.a("/images/voicedirect/btn_lower.gif"));
        this.z.setRolloverIcon(com.hw.client.util.c.a("/images/voicedirect/btn_lower_rollover.gif"));
        this.z.setDisabledIcon(com.hw.client.util.c.a("/images/voicedirect/btn_lower_disabled.gif"));
        this.z.setPressedIcon(com.hw.client.util.c.a("/images/voicedirect/btn_lower_pressed.gif"));
        this.z.setToolTipText(f.e("btn_cancel"));
        this.z.setActionCommand("ACTION_LOWER_HAND");
        com.hw.client.util.c.a(this.z);
        VT_6_1_0_11.h.a(this.z);
        this.y.addActionListener(this);
        this.z.addActionListener(this);
        this.A = new aT("<html>", 0);
        this.c(1);
    }
    
    public final JButton a() {
        return this.y;
    }
    
    public final JButton b() {
        return this.z;
    }
    
    public final JComponent c() {
        return this.A;
    }
    
    public final void d() {
        (this.d = this.f.m()).a(this);
        this.i = false;
        this.c(3);
    }
    
    public final void a(final B h) {
        this.h = h;
    }
    
    private void a(final String s, final Color foreground) {
        this.A.setForeground(foreground);
        this.A.setText("<html><center>" + s + "</center></html>");
    }
    
    private void a(final boolean enabled, final boolean enabled2) {
        this.y.setEnabled(enabled);
        this.z.setEnabled(enabled2);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals("ACTION_RAISE_HAND")) {
            this.n();
            return;
        }
        if (actionCommand.equals("ACTION_LOWER_HAND")) {
            this.o();
        }
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.n();
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.o();
        }
    }
    
    private void n() {
        if (!this.f.a("request_floor")) {
            return;
        }
        if (this.c == 3) {
            this.f.a(new be((byte)1));
            this.c(4);
        }
    }
    
    private void o() {
        this.i = false;
        if (this.c == 4) {
            this.f.a(new be((byte)3));
            this.c(3);
            return;
        }
        if (this.c == 5) {
            this.l();
        }
    }
    
    private void c(final int c) {
        final int c2 = this.c;
        this.c = c;
        if (c == 1) {
            this.a("", Color.black);
            this.a(false, false);
            return;
        }
        if (c == 3) {
            if (this.f.a("request_floor")) {
                this.a(this.f.e("vd_msg_click_to_speak"), Color.blue.darker());
                this.a(true, false);
                return;
            }
            this.a(this.f.e("vd_msg_audio_playback_only"), Color.black);
            this.a(false, false);
        }
        else {
            if (c == 4) {
                this.a(this.f.e("vd_msg_waiting"), Color.red.darker());
                this.a(false, true);
                return;
            }
            if (c == 5) {
                this.a(this.f.e("vd_msg_speaking"), Color.green.darker().darker());
                this.a(false, true);
                return;
            }
            if (c == 2) {
                if (c2 == 4 || c2 == 5) {
                    this.f.a(new be((byte)3));
                }
                this.a(this.f.e("vd_msg_audio_unavailable"), Color.black);
                this.a(false, false);
            }
        }
    }
    
    public final void a(final an an) {
        if (an.c() == 5) {
            final be be;
            if ((be = (be)an).a() == 2) {
                if (this.c == 4) {
                    this.i = true;
                    this.p();
                    return;
                }
                if (this.c == 3) {
                    this.f.a(new be((byte)3));
                    return;
                }
                if (this.c == 5) {
                    return;
                }
            }
            else if (be.a() == 3) {
                this.i = false;
                if (this.c == 4) {
                    this.f.a(new be((byte)3));
                    this.c(3);
                    return;
                }
                if (this.c != 3 && this.c == 5) {
                    SwingUtilities.invokeLater(new b(this));
                }
            }
            return;
        }
        if (an.c() == 7) {
            final bW bw;
            final B b = (bw = (bW)an).b();
            final byte[] a = bw.a();
            com.hw.client.util.a.b("AudioComponents.processConfEvent: received AudioEvent: ah=" + b + ", b=" + a);
            if (b != null) {
                if (this.m != null) {
                    this.m.a();
                }
                this.m = new bR(102400);
                this.n = b;
                this.j.addElement(this.m);
                this.k.addElement(this.n);
                this.l.addElement(new Integer(bw.d()));
                this.p();
                return;
            }
            if (a == null) {
                if (this.m != null) {
                    this.m.a();
                }
                this.m = null;
                return;
            }
            if (!this.s) {
                try {
                    this.s = this.r.a(a, 0, a.length);
                    if (this.s) {
                        this.m.a(this.r.a(this.n));
                        com.hw.client.util.a.d("AudioComponents.processConfEvent: just synchronized granulePos=" + this.r.a());
                    }
                    return;
                }
                catch (IOException ex) {
                    com.hw.client.util.a.b("AudioComponents.processConfEvent: IOException", ex);
                    return;
                }
            }
            this.m.a(a);
        }
    }
    
    private void p() {
        SwingUtilities.invokeLater(new com.wimba.clients.voicedirect.c(this));
    }
    
    public final void a(final long n) {
    }
    
    public final void e() {
        this.f.setCursor(new Cursor(3));
        this.f.a(new bW(this.h));
        this.p = 0L;
        this.e = this.d.h();
        this.c(5);
        this.x = true;
    }
    
    public final void b(final long p) {
        this.q();
        try {
            final double n = this.g ? 0.2 : 0.8;
            if (this.x && p - this.p >= n * this.h.g() && this.e.available() > 0) {
                int i = this.e.available();
                while (i > 0) {
                    int n2;
                    int read;
                    for (n2 = 0; n2 < i && n2 < this.q.length; n2 += read) {
                        if ((read = this.e.read(this.q, n2, this.q.length - n2)) <= 0) {
                            throw new IOException("AudioComponents.recordProgress: recordStream.read returned -1 although available>0");
                        }
                    }
                    i -= n2;
                    if (n2 > 0) {
                        final byte[] array = new byte[n2];
                        System.arraycopy(this.q, 0, array, 0, n2);
                        this.g = true;
                        this.f.a(new bW(array, (int)p));
                    }
                }
                this.p = p;
            }
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("AudioComponents.recordProgress: IOException", ex);
        }
    }
    
    public final void c(final long n) {
        this.q();
        this.x = false;
        com.hw.client.util.a.c("AudioComponents.recordStopped, samples=" + n);
        this.w = false;
        this.a(0);
        try {
            int i = 0;
            while (i >= 0) {
                int n2;
                for (n2 = 0; (i = this.e.read(this.q, n2, this.q.length - n2)) > 0; n2 += i) {}
                if (n2 > 0) {
                    final byte[] array = new byte[n2];
                    System.arraycopy(this.q, 0, array, 0, n2);
                    this.f.a(new bW(array, (int)n));
                }
            }
            this.f.a(new bW());
            this.f.a(new be((byte)3));
            this.c(3);
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("AudioComponents.recordStopped: IOException", ex);
        }
    }
    
    public final void a(final int value) {
        this.m().b().setValue(value);
    }
    
    public final void b(final int value) {
        this.m().b().setValue(value);
    }
    
    public final void a(final List list) {
    }
    
    public final void f() {
    }
    
    public final void g() {
    }
    
    public final void d(final long n) {
    }
    
    public final void h() {
    }
    
    public final void e(final long n) {
        this.q();
        this.f.d(this.o + 1);
        com.hw.client.util.a.c("playStopped samples=" + n);
        this.t = false;
        this.b(0);
        this.p();
    }
    
    public final void f(final long n) {
    }
    
    public final void i() {
    }
    
    public final void a(final int n, final String s) {
        com.hw.client.util.a.d("AudioComponents.audioDeviceError");
        if (this.w) {
            this.f.a(new be((byte)3));
        }
        this.c(2);
        this.q();
    }
    
    public final void j() {
        this.c(2);
    }
    
    public final void k() {
    }
    
    public final void l() {
        this.d.k();
        this.q();
    }
    
    private void q() {
        this.f.setCursor(null);
    }
    
    public final bs m() {
        if (this.B == null) {
            final bs b;
            (b = new bs(0, 100, 0)).a(com.wimba.clients.voicedirect.a.b);
            b.b(com.wimba.clients.voicedirect.a.a);
            b.c(com.wimba.clients.voicedirect.a.b);
            b.a(false);
            b.a(5);
            b.b(false);
            b.setForeground(Color.red);
            this.B = b;
        }
        return this.B;
    }
    
    static void a(a a) {
        if ((a = a).t || a.w) {
            return;
        }
        if (a.j.size() > 0) {
            final bR br = a.j.elementAt(0);
            final B b = a.k.elementAt(0);
            a.o = a.l.elementAt(0);
            a.f.d(a.o);
            a.j.removeElementAt(0);
            a.k.removeElementAt(0);
            a.l.removeElementAt(0);
            a.d.a(br, b);
            a.t = true;
            a.d.a(0L);
            return;
        }
        if (a.i) {
            a.w = true;
            a.g = false;
            a.d.a(a.h);
        }
    }
    
    static {
        com.wimba.clients.voicedirect.a.a = ca.a("#FFFFCC", Color.red);
        com.wimba.clients.voicedirect.a.b = ca.a("#FF9900", Color.yellow);
    }
}

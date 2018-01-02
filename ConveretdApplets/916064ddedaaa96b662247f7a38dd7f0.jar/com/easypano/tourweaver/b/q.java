// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.a.e;
import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import com.easypano.tourweaver.f.d;
import com.easypano.tourweaver.PlayerListener;
import com.easypano.tourweaver.f.ab;

public class q extends n implements ab, PlayerListener
{
    v F;
    d G;
    Image H;
    String I;
    Image J;
    Image K;
    boolean L;
    boolean M;
    boolean N;
    int O;
    int P;
    int Q;
    boolean R;
    int S;
    int T;
    int U;
    PlayerListener V;
    boolean W;
    Image X;
    Graphics Y;
    long Z;
    String ab;
    private static String[] bb;
    
    public q() {
        this(new v());
        this.addCamera(new d());
        this.F.a(this.G);
        this.G.a(q.bb[0]);
        this.b(2);
    }
    
    public void showHotspot(final String s) {
        final v f = this.F;
        if (!com.easypano.tourweaver.b.f.u) {
            if (f == null) {
                return;
            }
            final v f2 = this.F;
        }
        f.c(s);
    }
    
    public void destroy() {
        super.destroy();
        this.J = null;
        this.H = null;
        this.X = null;
        final Graphics y = this.Y;
        if (!f.u) {
            if (y == null) {
                return;
            }
            final Graphics y2 = this.Y;
        }
        y.dispose();
        this.Y = null;
    }
    
    public q(final s s) {
        super(s);
        this.F = null;
        this.H = null;
        this.I = "";
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = true;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.W = true;
        this.X = null;
        this.Y = null;
        this.Z = 0L;
        this.ab = q.bb[4];
        if (!f.u) {
            if (!(s instanceof v)) {
                throw new IllegalArgumentException(q.bb[3]);
            }
            this.F = (v)s;
        }
    }
    
    public void addAttributes(final String s, final String s2) {
        super.addAttributes(s, s2);
        String s3 = s;
        if (!f.u) {
            if (!s.equals(q.bb[2])) {
                return;
            }
            s3 = s2;
        }
        if (s3 != null) {
            this.L = s2.equals(q.bb[1]);
        }
    }
    
    public void setFullScreen(final boolean m) {
        this.M = m;
        this.F.a(m);
        this.a(this.F.z, this.F.y);
        Container parent = this;
        if (!f.u) {
            if (super.d == null) {
                return;
            }
            parent = this.getParent();
        }
        final Container container = parent;
        if (m) {
            container.setBackground(super.d);
        }
    }
    
    public boolean isFullScreen() {
        return this.M;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        super.processMouseEvent(mouseEvent);
        int n2;
        int id;
        final int n = id = (n2 = (super.r ? 1 : 0));
        if (!u) {
            if (n != 0) {
                return;
            }
            n2 = (id = mouseEvent.getID());
        }
        final int n3 = 501;
        q q = null;
        Label_0071: {
            int m = 0;
            Label_0054: {
                if (!u) {
                    if (id != n3) {
                        return;
                    }
                    m = (n2 = mouseEvent.getClickCount());
                    if (u) {
                        break Label_0054;
                    }
                }
                if (n2 != n3) {
                    return;
                }
                q = this;
                if (u) {
                    break Label_0071;
                }
                m = (this.M ? 1 : 0);
            }
            if (m == 0) {
                this.b(com.easypano.tourweaver.b.q.bb[5]);
                if (!u) {
                    return;
                }
            }
            q = this;
        }
        q.b(com.easypano.tourweaver.b.q.bb[6]);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final boolean u = f.u;
        super.setBounds(n, n2, n3, n4);
        final v f = this.F;
        q q = null;
        Label_0070: {
            Label_0064: {
                if (!u) {
                    if (f.h() != 0) {
                        q = this;
                        if (u) {
                            break Label_0070;
                        }
                        if (this.F.l() != 0) {
                            break Label_0064;
                        }
                    }
                    this.F.b(n3, n4);
                    final v f2 = this.F;
                }
                f.a(n3, n4);
            }
            this.X = null;
            q = this;
        }
        q.G.a(n, n2, n3, n4);
    }
    
    public void setEnabled(final boolean enabled) {
        final boolean u = f.u;
        super.setEnabled(enabled);
        final v f = this.F;
        if (!u) {
            if (f == null) {
                return;
            }
            final v f2 = this.F;
        }
        boolean b = enabled;
        if (!u) {
            if (!enabled) {
                b = true;
            }
            else {
                b = false;
            }
        }
        f.b(b);
    }
    
    protected Graphics n() {
        final Container parent = this.getParent();
        if (!f.u) {
            if (parent == null) {
                return null;
            }
            this.getParent();
        }
        return parent.getGraphics();
    }
    
    private void o() {
        final com.easypano.tourweaver.b.d a = this.F.A;
        if (f.u || a != null) {
            final Rectangle bounds = a.getBounds();
            this.playTo(super.A.width / 2 - bounds.x, super.A.height / 2 - bounds.y, super.D);
        }
    }
    
    public void play() {
        final boolean u = f.u;
        boolean b2;
        final boolean b = b2 = this.R;
        if (!u) {
            if (b) {
                return;
            }
            final boolean moveXYZ;
            b2 = (moveXYZ = this.moveXYZ(this.S, this.T, this.U));
        }
        q q = null;
        Label_0060: {
            if (!u) {
                if (!b) {
                    this.R = true;
                    this.N = false;
                }
                q = this;
                if (u) {
                    break Label_0060;
                }
                b2 = this.N;
            }
            if (!b2) {
                return;
            }
            q = this;
        }
        final Rectangle bounds = q.x.getBounds();
        int abs;
        final int n = abs = Math.abs(bounds.x - this.O);
        int n3;
        final int n2 = n3 = Math.abs(2 * this.S);
        if (!u) {
            if (n <= n2) {
                this.S = 0;
            }
            final int abs2;
            abs = (abs2 = Math.abs(bounds.y - this.P));
            final int abs3;
            n3 = (abs3 = Math.abs(2 * this.T));
        }
        int n5 = 0;
        int t = 0;
        int n4 = 0;
        Label_0170: {
            if (!u) {
                if (n <= n2) {
                    this.T = 0;
                }
                n4 = (abs = (t = (n5 = Math.abs(super.D - this.Q))));
                if (u) {
                    break Label_0170;
                }
                n3 = Math.abs(2 * this.U);
            }
            if (abs <= n3) {
                this.U = 0;
            }
            t = (n4 = (n5 = this.S));
        }
        if (!u) {
            if (n4 != 0) {
                return;
            }
            n5 = (t = this.T);
        }
        q q2 = null;
        Label_0205: {
            if (!u) {
                if (t != 0) {
                    return;
                }
                q2 = this;
                if (u) {
                    break Label_0205;
                }
                n5 = this.U;
            }
            if (n5 != 0) {
                return;
            }
            this.R = true;
            q2 = this;
        }
        q2.N = false;
    }
    
    public void goToView(final int n, final int n2, final int n3) {
        final Rectangle bounds = this.getBounds();
        this.playTo(bounds.width / 2 - n, bounds.height / 2 - n2, n3);
    }
    
    public void playTo(final int o, final int p3, final int q) {
        final boolean u = f.u;
        this.O = o;
        this.P = p3;
        this.Q = q;
        this.N = true;
        final Rectangle bounds = super.x.getBounds();
        final int n = o - bounds.x;
        final int n2 = p3 - bounds.y;
        final int n3 = q - super.D;
        int n4 = n / 10;
        int n5 = n2 / 10;
        int n6 = n3 / 10;
        int n9;
        final int n8;
        final int n7 = n8 = (n9 = n);
        int n12;
        final int n11;
        final int n10 = n11 = (n12 = 10);
        final int n13;
        final int n14;
        if (!u) {
            if (n7 < n10) {
                n13 = (n9 = n);
                n14 = (n12 = -10);
                if (!u) {
                    if (n13 > n14) {
                        n4 = n;
                    }
                }
            }
        }
        final int n15;
        final int n16;
        if (!u) {
            if (n7 < n10) {
                n15 = n2;
                n16 = -10;
                if (!u) {
                    if (n15 > n16) {
                        n5 = n2;
                    }
                }
            }
        }
        Label_0165: {
            int n17 = 0;
            Label_0163: {
                if (!u) {
                    if (n13 >= n14) {
                        break Label_0165;
                    }
                    n17 = (n9 = n3);
                    if (u) {
                        break Label_0163;
                    }
                }
                if (n15 <= n16) {
                    break Label_0165;
                }
                n17 = n3;
            }
            n6 = n17;
        }
        this.autoPan(n4, n5, n6);
    }
    
    public boolean moveXYZ(final int n, final int n2, final int n3) {
        final boolean u = f.u;
        int d = 0;
        final boolean b = false;
        int n4 = 0;
        final boolean l;
        int n5;
        final boolean b2 = (n5 = ((l = this.L) ? 1 : 0)) != 0;
        Label_0076: {
            if (!u) {
                if (!b2) {
                    n5 = n;
                    boolean b3 = n != 0;
                    boolean b4 = n != 0;
                    if (!u) {
                        if (n != 0) {
                            d = (this.d(n) ? 1 : 0);
                        }
                        n5 = n2;
                        b3 = (n2 != 0);
                        b4 = (n2 != 0);
                    }
                    if (!u) {
                        if (b4) {
                            n4 = (this.e(n2) ? 1 : 0);
                        }
                        n5 = n3;
                        b3 = (n3 != 0);
                    }
                    if (u) {
                        break Label_0076;
                    }
                    if (b3) {
                        n4 = (this.c(n3) ? 1 : 0);
                    }
                }
                n5 = d;
            }
        }
        final boolean b5;
        if (!u) {
            if (n5 == 0) {
                b5 = b;
                if (!u) {
                    if (!b5) {
                        final int n6 = n4;
                        if (!u) {
                            if (n6 != 0) {}
                        }
                    }
                }
            }
        }
        return b5;
    }
    
    public void autoPan(final int s, final int t, final int u) {
        this.R = false;
        this.S = s;
        this.T = t;
        this.U = u;
    }
    
    public void stop() {
        this.R = true;
    }
    
    public void moviePaused(final String s) {
    }
    
    private void a(final Image image, final Image image2) {
        final boolean u = f.u;
        Image image3 = image;
        if (!u) {
            if (image == null) {
                return;
            }
            this.F.a(image);
            this.F.b(image2);
            image3 = image;
        }
        final int width = image3.getWidth(this);
        final int height = image.getHeight(this);
        int n2;
        final int n = n2 = (this.L ? 1 : 0);
        Label_0181: {
            if (!u) {
                if (n == 0) {
                    this.F.b(width, height);
                    this.F.a(width, height);
                    if (!u) {
                        break Label_0181;
                    }
                }
                n2 = this.getBounds().width;
            }
            int n3 = n2;
            int height2 = this.getBounds().height;
            final double n4 = width / height;
            final double n5 = dcmpl(n4, n3 / height2);
            Label_0159: {
                if (!u) {
                    if (n5 > 0) {
                        height2 = (int)(n3 / n4 + 0.5);
                        if (!u) {
                            break Label_0159;
                        }
                    }
                    final int n6 = (int)(height2 * n4 + 0.5);
                }
                n3 = (int)n5;
            }
            this.F.b(n3, height2);
            this.F.a(n3, height2);
        }
        this.F.a(this.F.m().width / width, this.F.m().height / height);
        super.y.setValue((super.y.getMaximum() - super.y.getMinimum()) / 2);
        super.z.setValue((super.z.getMaximum() - super.z.getMinimum()) / 2);
        this.doLayout();
    }
    
    public void setPlayerListener(final PlayerListener v) {
        this.V = v;
    }
    
    public void render() {
        final boolean u = f.u;
        System.currentTimeMillis();
        final int n = this.G.n() ? 1 : 0;
        final d g;
        Label_0209: {
            Label_0201: {
                if (!u) {
                    Label_0189: {
                        if (n != 0) {
                            this.setEnabled(true);
                            this.F.f();
                            this.F.a((com.easypano.tourweaver.b.d)null);
                            final Vector s = this.G.s();
                            final Enumeration<a> elements = s.elements();
                            while (true) {
                                while (elements.hasMoreElements()) {
                                    final a a = elements.nextElement();
                                    a.a(super.f);
                                    this.a(a);
                                    if (!u) {
                                        if (u) {
                                            break;
                                        }
                                        continue;
                                    }
                                    else {
                                        this.a(this.G.r(), (Image)null);
                                        this.F.d(this.I);
                                        this.o();
                                        this.G.b(false);
                                        final boolean b;
                                        final boolean w = b = (((this.V != null) ? 1 : 0) != 0);
                                        this.W = w;
                                        if (u) {
                                            break Label_0201;
                                        }
                                        if (w) {
                                            this.V.sceneSwitched("");
                                            this.W = false;
                                        }
                                        break Label_0189;
                                    }
                                }
                                s.removeAllElements();
                                continue;
                            }
                        }
                    }
                    g = this.G;
                    if (u) {
                        break Label_0209;
                    }
                    g.p();
                }
            }
            if (n < 1) {
                return;
            }
            final d g2 = this.G;
        }
        this.F.b(g.r());
        this.c();
    }
    
    public Graphics getGraphicsMe() {
        return this.Y;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public long getRenderTime() {
        return this.Z;
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        final Rectangle bounds = this.getBounds();
        q q = this;
        if (!u) {
            if (this.X == null) {
                this.X = this.createImage(bounds.width, bounds.height);
                this.Y = this.X.getGraphics();
            }
            q = this;
        }
        final Color d = q.d;
        q q2 = null;
        Label_0156: {
            if (!u) {
                if (d != null) {
                    this.Y.setColor(super.d);
                    this.Y.fillRect(0, 0, bounds.width, bounds.height);
                }
                super.paint(this.Y);
                q2 = this;
                if (u) {
                    break Label_0156;
                }
                final Color a = super.a;
            }
            if (d != null) {
                this.Y.setColor(super.a);
                this.Y.drawRect(0, 0, this.getBounds().width - 1, this.getBounds().height - 1);
            }
            graphics.drawImage(this.X, 0, 0, this);
            q2 = this;
        }
        q2.Z = System.currentTimeMillis();
    }
    
    public double getPreFPS() {
        return 0.0;
    }
    
    public double getAverFPS() {
        return 0.0;
    }
    
    public void reCountFps() {
    }
    
    public void addCamera(final d g) {
        (this.G = g).a(this);
    }
    
    public d getCamera() {
        return this.G;
    }
    
    public void takePictureOfSelf() {
        this.G.a(this.X);
    }
    
    public void takeEmptyPicture() {
        this.G.a(this.p());
    }
    
    private Image p() {
        final Image image2;
        final Image image = image2 = this.createImage(this.getBounds().width, this.getBounds().height);
        if (!f.u && image2 == null) {
            return null;
        }
        final Graphics graphics = image2.getGraphics();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        return image;
    }
    
    public void takeEmptyPicture(final d d) {
        final boolean u = f.u;
        d d2 = d;
        if (!u) {
            if (d == null) {
                return;
            }
            d2 = d;
        }
        final Image r = d2.r();
        final Image p = this.p();
        final Rectangle bounds = this.getBounds();
        final int width = r.getWidth(this);
        final int height = r.getHeight(this);
        final boolean l = this.L;
        Label_0129: {
            if (!u) {
                if (!l) {
                    p.getGraphics().drawImage(r, (bounds.width - width) / 2, (bounds.height - height) / 2, com.easypano.tourweaver.a.e.f);
                    if (!u) {
                        break Label_0129;
                    }
                }
                p.getGraphics().drawImage(r, 0, 0, bounds.width, bounds.height, this);
            }
        }
        d.a(p);
    }
    
    public void movieStoped(final String s) {
    }
    
    public void takePictureOfSelf(final d d) {
        d d2 = d;
        if (!f.u) {
            if (d == null) {
                return;
            }
            d2 = d;
        }
        d2.a(this.X);
    }
    
    public void sceneSwitching(final String s) {
        this.I = null;
        this.F.d(null);
    }
    
    public void sceneSwitched(final String i) {
        this.I = i;
        this.F.d(i);
        this.o();
        this.c();
    }
    
    public void movieSwitching(final String s) {
    }
    
    public void mapSwitching(final String s) {
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        int n7;
        final int n6 = n7 = (n & 0x20);
        if (!f.u) {
            if (n6 != 32) {
                n7 = (true ? 1 : 0);
            }
            else {
                n7 = (false ? 1 : 0);
            }
        }
        return n7 != 0;
    }
    
    public void setType(final String ab) {
        this.ab = ab;
    }
    
    public String getType() {
        return this.ab;
    }
    
    public void mapSwitched(final String s) {
    }
    
    static {
        final String[] bb = new String[7];
        final int n = 0;
        final char[] charArray = "Kr=\u0019MKv?;".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '&';
                            break;
                        }
                        case 1: {
                            c2 = '\u0013';
                            break;
                        }
                        case 2: {
                            c2 = 'M';
                            break;
                        }
                        case 3: {
                            c2 = 'Z';
                            break;
                        }
                        default: {
                            c2 = ',';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        bb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "dv>.jOg".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '&';
                            break;
                        }
                        case 1: {
                            c4 = '\u0013';
                            break;
                        }
                        case 2: {
                            c4 = 'M';
                            break;
                        }
                        case 3: {
                            c4 = 'Z';
                            break;
                        }
                        default: {
                            c4 = ',';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        bb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "Uz7?aIw(".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '&';
                            break;
                        }
                        case 1: {
                            c6 = '\u0013';
                            break;
                        }
                        case 2: {
                            c6 = 'M';
                            break;
                        }
                        case 3: {
                            c6 = 'Z';
                            break;
                        }
                        default: {
                            c6 = ',';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        bb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "kr=zzOv:?^\u0006~8)X\u0006q(zxq^,*hIp87IHg".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '&';
                            break;
                        }
                        case 1: {
                            c8 = '\u0013';
                            break;
                        }
                        case 2: {
                            c8 = 'M';
                            break;
                        }
                        case 3: {
                            c8 = 'Z';
                            break;
                        }
                        default: {
                            c8 = ',';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        bb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "kr=\fECd((".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '&';
                            break;
                        }
                        case 1: {
                            c10 = '\u0013';
                            break;
                        }
                        case 2: {
                            c10 = 'M';
                            break;
                        }
                        case 3: {
                            c10 = 'Z';
                            break;
                        }
                        default: {
                            c10 = ',';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        bb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "@f!6_Ea(?B\u000e`9(EHtm7MVe$?[Cad".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '&';
                            break;
                        }
                        case 1: {
                            c12 = '\u0013';
                            break;
                        }
                        case 2: {
                            c12 = 'M';
                            break;
                        }
                        case 3: {
                            c12 = 'Z';
                            break;
                        }
                        default: {
                            c12 = ',';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        bb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "Ck$.JS\u007f!)OTv(4\u0004Ug?3BA3 ;\\Pz(-IT:".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '&';
                            break;
                        }
                        case 1: {
                            c14 = '\u0013';
                            break;
                        }
                        case 2: {
                            c14 = 'M';
                            break;
                        }
                        case 3: {
                            c14 = 'Z';
                            break;
                        }
                        default: {
                            c14 = ',';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 <= n28) {
                bb[n25] = new String(charArray7).intern();
                q.bb = bb;
                return;
            }
            continue;
        }
    }
}

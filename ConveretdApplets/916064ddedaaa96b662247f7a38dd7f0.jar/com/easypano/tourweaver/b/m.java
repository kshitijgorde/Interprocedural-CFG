// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.d.e;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Adjustable;

public class m extends f implements Adjustable, MouseListener, Runnable
{
    int v;
    int w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    Image D;
    String E;
    Image F;
    String G;
    b H;
    b I;
    Rectangle J;
    Rectangle K;
    AdjustmentListener L;
    int M;
    boolean N;
    Thread O;
    int P;
    int Q;
    int R;
    int S;
    boolean T;
    int U;
    int V;
    int W;
    boolean X;
    int Y;
    int Z;
    private static String[] ab;
    
    public m() {
        this(0);
    }
    
    public Image h() {
        return this.D;
    }
    
    public void destroy() {
        super.destroy();
        this.D = null;
        this.F = null;
    }
    
    public m(final int n) {
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 16;
        this.D = null;
        this.E = "";
        this.F = null;
        this.G = "";
        this.H = null;
        this.I = null;
        this.J = new Rectangle();
        this.K = new Rectangle();
        this.L = null;
        this.M = 20;
        this.N = false;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.S = 0;
        this.T = false;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = true;
        this.Y = 0;
        this.Z = 0;
        this.c(n);
        this.H = new b();
        this.I = new b();
    }
    
    public void run() {
        final boolean u = f.u;
        while (!this.N && !Thread.interrupted()) {
            this.setValue(this.z + this.M);
            try {
                Thread.sleep(100L);
                continue;
            }
            catch (Exception ex) {
                if (!u) {
                    continue;
                }
            }
            break;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        int m = -20;
        Object o = this;
        Label_0086: {
            if (!u) {
                Label_0063: {
                    switch (this.A) {
                        case 0: {
                            final Rectangle rectangle = (Rectangle)(o = this.getBounds());
                            if (u) {
                                break Label_0086;
                            }
                            if (rectangle.width >= 62) {
                                break;
                            }
                            m = -100;
                            if (u) {
                                break Label_0063;
                            }
                            break;
                        }
                        case 1: {
                            final Rectangle rectangle2 = (Rectangle)(o = this.getBounds());
                            if (u) {
                                break Label_0086;
                            }
                            if (rectangle2.height < 62) {
                                m = -100;
                                break;
                            }
                            break;
                        }
                    }
                }
                o = mouseEvent.getSource();
            }
        }
        Label_0108: {
            if (o == this.H) {
                this.M = m;
                if (!u) {
                    break Label_0108;
                }
            }
            this.M = -m;
        }
        final Thread o2 = this.O;
        Label_0162: {
            if (!u) {
                if (o2 != null) {
                    final Thread o3 = this.O;
                    if (u) {
                        break Label_0162;
                    }
                    if (o3.isAlive()) {
                        return;
                    }
                }
                this.N = false;
                (this.O = new Thread(this)).setPriority(5);
                final Thread o4 = this.O;
            }
        }
        o2.start();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final Thread o = this.O;
        if (!f.u) {
            if (o == null) {
                return;
            }
            this.N = true;
            final Thread o2 = this.O;
        }
        o.interrupt();
    }
    
    public int l() {
        return this.C;
    }
    
    public void b(final int c) {
        this.C = c;
    }
    
    public void setBounds(final int n, final int n2, int n3, int n4) {
        final boolean u = f.u;
        final Rectangle bounds = this.getBounds();
        m m = null;
        Label_0246: {
            final int n5;
            Label_0078: {
                if (!u) {
                    if (n == bounds.x) {
                        if (u) {
                            break Label_0078;
                        }
                        if (n2 == bounds.y) {
                            n5 = n3;
                            if (u) {
                                break Label_0078;
                            }
                            if (n5 == bounds.width) {
                                final int n6 = n4;
                                if (u) {
                                    break Label_0078;
                                }
                                if (n6 == bounds.height) {
                                    return;
                                }
                            }
                        }
                    }
                    m = this;
                    if (u) {
                        break Label_0246;
                    }
                    final int a = this.A;
                }
            }
            Label_0168: {
                switch (n5) {
                    case 0: {
                        final int n7 = n3;
                        final int n8 = this.C * 2;
                        if (!u && n7 < n8) {
                            n3 = this.C * 2 + 2;
                            goto Label_0128;
                        }
                        if (n7 < n8) {
                            this.V = 20;
                            this.X = false;
                            if (!u) {
                                break;
                            }
                        }
                        this.X = true;
                        this.V = this.Y;
                        if (u) {
                            break Label_0168;
                        }
                        break;
                    }
                    case 1: {
                        final int n9 = n4;
                        final int n10 = this.C * 2;
                        if (!u && n9 < n10) {
                            this.W = 20;
                            n4 = this.C * 2 + 2;
                            goto Label_0200;
                        }
                        if (n9 < n10) {
                            this.W = 20;
                            this.X = false;
                            if (!u) {
                                break;
                            }
                        }
                        this.W = this.Z;
                        this.X = true;
                        break;
                    }
                }
            }
            super.setBounds(n, n2, n3, n4);
            m = this;
        }
        m.m();
    }
    
    private void m() {
        final boolean u = f.u;
        final Rectangle bounds = this.getBounds();
        this.K.setBounds(0, 0, bounds.width, bounds.height);
        b b2;
        final b b = b2 = this.H;
        if (!u) {
            m m = null;
            Label_0151: {
                if (b != null) {
                    this.H.setBounds(0, 0, this.C, this.C);
                    this.add(this.H);
                    m = this;
                    if (u) {
                        break Label_0151;
                    }
                    Label_0125: {
                        switch (this.A) {
                            case 0: {
                                this.K.setBounds(this.C, 0, bounds.width - this.C, this.C);
                                if (u) {
                                    break Label_0125;
                                }
                                break;
                            }
                            case 1: {
                                this.K.setBounds(0, this.C, this.C, bounds.height - this.C);
                                break;
                            }
                        }
                    }
                }
                m = this;
            }
            final b i;
            b2 = (i = m.I);
        }
        if (!u) {
            if (b != null && !u) {
                Label_0252: {
                    switch (this.A) {
                        case 0: {
                            this.I.setBounds(bounds.width - this.C, 0, this.C, this.C);
                            this.K.setBounds(this.C, 0, bounds.width - this.C * 2, this.C);
                            if (u) {
                                break Label_0252;
                            }
                            break;
                        }
                        case 1: {
                            this.I.setBounds(0, bounds.height - this.C, this.C, this.C);
                            this.K.setBounds(0, this.C, this.C, bounds.height - this.C * 2);
                            break;
                        }
                    }
                }
                this.add(this.I);
                goto Label_0312;
            }
            this.H.addMouseListener(this);
            b2 = this.I;
        }
        b2.addMouseListener(this);
    }
    
    public void c(final int a) {
        this.d(a);
        this.A = a;
    }
    
    private void d(final int n) {
        switch (n) {
            case 0:
            case 1: {
                if (f.u) {
                    break;
                }
                return;
            }
        }
        throw new IllegalArgumentException(m.ab[0]);
    }
    
    public void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.L = AWTEventMulticaster.add(this.L, adjustmentListener);
    }
    
    public int getBlockIncrement() {
        return this.y;
    }
    
    public int getMaximum() {
        return this.v;
    }
    
    public int getMinimum() {
        return this.w;
    }
    
    public int getOrientation() {
        return this.A;
    }
    
    public int getUnitIncrement() {
        return this.x;
    }
    
    public int getValue() {
        return this.z;
    }
    
    public int getVisibleAmount() {
        return this.B;
    }
    
    public void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        this.L = AWTEventMulticaster.remove(this.L, adjustmentListener);
    }
    
    public void setBlockIncrement(final int y) {
        this.y = y;
    }
    
    public void setMaximum(final int v) {
        this.v = v;
    }
    
    public void setMinimum(final int w) {
        this.w = w;
    }
    
    public void setUnitIncrement(final int x) {
        this.x = x;
    }
    
    public void setValue(final int n) {
        this.a(n, this.B, this.w, this.v);
    }
    
    public void setVisibleAmount(final int n) {
        this.a(this.z, n, this.w, this.v);
    }
    
    public void a(int z, final int n, final int w, final int v) {
        final boolean u = f.u;
        int z2 = w;
        int n2 = w;
        int n3 = w;
        int n4 = w;
        int n5 = v;
        int n6 = v;
        int n7 = v;
        int n8 = v;
        if (!u) {
            if (w >= v) {
                throw new IllegalArgumentException(m.ab[9]);
            }
            n3 = (n4 = (n2 = (z2 = z)));
            n5 = w;
            n6 = w;
            n7 = w;
            n8 = w;
        }
        if (!u) {
            if (n4 < n8) {
                z = w;
            }
            n2 = (n3 = (z2 = z));
            n5 = v;
            n6 = v;
            n7 = v;
        }
        if (!u) {
            if (n3 > n7) {
                z = v;
            }
            z2 = n;
            n2 = n;
            n5 = (n6 = v - w);
        }
        m m = null;
        Label_0137: {
            if (!u) {
                if (n2 > n6) {
                    throw new IllegalArgumentException(com.easypano.tourweaver.b.m.ab[8]);
                }
                m = this;
                if (u) {
                    break Label_0137;
                }
                z2 = this.z;
                n5 = z;
            }
            if (z2 != n5) {
                this.a(new AdjustmentEvent(this, 601, 601, z));
            }
            this.z = z;
            this.B = n;
            this.w = w;
            this.v = v;
            m = this;
        }
        m.x = n;
    }
    
    public void e(int w) {
        final boolean u = f.u;
        final int n = w;
        final int w2 = this.w;
        Label_0038: {
            int v = 0;
            Label_0037: {
                if (!u) {
                    if (n < w2) {
                        w = this.w;
                    }
                    final int n2;
                    v = (n2 = w);
                    if (u) {
                        break Label_0037;
                    }
                    final int v2 = this.v;
                }
                if (n <= w2) {
                    break Label_0038;
                }
                v = this.v;
            }
            w = v;
        }
        this.z = w;
        this.c();
    }
    
    private void n() {
        final boolean u = f.u;
        final int a = this.A;
        Label_0116: {
            if (!u) {
                switch (a) {
                    case 0: {
                        final int n = (this.z - this.w) / (this.v - this.w) * (this.K.width - this.V);
                        break;
                    }
                    case 1: {
                        break Label_0116;
                    }
                }
            }
            int n2 = a;
            m m = this;
            if (!u) {
                if (this.H != null) {
                    n2 += this.C;
                }
                m = this;
            }
            m.J.setBounds(n2, 0, this.V, this.C);
            if (!u) {
                return;
            }
        }
        int n3 = (this.z - this.w) / (this.v - this.w) * (this.K.height - this.W);
        m i = this;
        if (!u) {
            if (this.H != null) {
                n3 += this.C;
            }
            i = this;
        }
        i.J.setBounds(0, n3, this.C, this.W);
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        this.n();
        final Image d = this.D;
        m m = null;
        Label_0118: {
            if (!u) {
                if (d != null) {
                    graphics.drawImage(this.D, this.K.x, this.K.y, this.K.width, this.K.height, this);
                }
                m = this;
                if (u) {
                    break Label_0118;
                }
                final Image f = this.F;
            }
            if (d != null) {
                m = this;
                if (u) {
                    break Label_0118;
                }
                if (this.X) {
                    graphics.drawImage(this.F, this.J.x, this.J.y, this.J.width, this.J.height, this);
                }
            }
            m = this;
        }
        m.paint(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        final boolean a = this.a(mouseEvent, super.p);
        super.processMouseEvent(mouseEvent);
        int modifiers;
        final boolean b = (modifiers = (a ? 1 : 0)) != 0;
        if (!u) {
            if (b) {
                return;
            }
            modifiers = mouseEvent.getModifiers();
        }
        final int n = modifiers;
        final int id = mouseEvent.getID();
        final int n2 = 501;
        if (!u) {
            Label_0336: {
                if (id == n2) {
                    final int n3 = n & 0x10;
                    if (u) {
                        break Label_0336;
                    }
                    if (n3 != 0) {
                        final int x = mouseEvent.getX();
                        final int y = mouseEvent.getY();
                        int a2;
                        final int n4 = a2 = this.A;
                        Label_0327: {
                            Label_0220: {
                                if (!u) {
                                    switch (n4) {
                                        case 0: {
                                            final int n5;
                                            a2 = (n5 = x);
                                            break;
                                        }
                                        case 1: {
                                            break Label_0220;
                                        }
                                    }
                                }
                                final int x2 = this.J.x;
                                if (!u) {
                                    if (n4 < x2) {
                                        this.setValue(this.z - this.x);
                                    }
                                    a2 = x;
                                    final int n6 = this.J.x + this.J.width;
                                }
                                if (a2 > x2) {
                                    this.setValue(this.z + this.x);
                                    if (!u) {
                                        break Label_0327;
                                    }
                                }
                                this.P = x;
                                this.Q = y;
                                this.R = this.J.x;
                                this.S = this.J.y;
                                this.T = true;
                                if (!u) {
                                    break Label_0327;
                                }
                            }
                            final int n7 = y;
                            final int y2 = this.J.y;
                            if (!u) {
                                if (n7 < y2) {
                                    this.setValue(this.z - this.x);
                                }
                                final int n8 = this.J.y + this.J.height;
                            }
                            if (n7 > y2) {
                                this.setValue(this.z + this.x);
                                if (!u) {
                                    break Label_0327;
                                }
                            }
                            this.P = x;
                            this.Q = y;
                            this.R = this.J.x;
                            this.S = this.J.y;
                            this.T = true;
                        }
                        if (!u) {
                            return;
                        }
                    }
                }
                mouseEvent.getID();
            }
        }
        if (id == n2) {
            this.T = false;
        }
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        super.processMouseMotionEvent(mouseEvent);
        mouseEvent.getModifiers();
        final int id = mouseEvent.getID();
        final int n = 506;
        if (u || id == n) {
            final int n2 = id & n;
            if (!u) {
                if (n2 == 0) {
                    return;
                }
                this.U = this.z;
                mouseEvent.getX();
            }
            final int n3 = n2;
            final int y = mouseEvent.getY();
            int a;
            final int n4 = a = this.A;
            Label_0182: {
                if (!u) {
                    switch (n4) {
                        case 0: {
                            final boolean t;
                            a = ((t = this.T) ? 1 : 0);
                            break;
                        }
                        case 1: {
                            break Label_0182;
                        }
                    }
                }
                if (!u) {
                    if (n4 == 0) {
                        return;
                    }
                    a = this.R + n3 - this.P;
                }
                int n5 = a;
                if (this.H != null) {
                    n5 -= this.C;
                }
                this.setValue(n5 * (this.v - this.w) / (this.K.width - this.J.width));
                if (!u) {
                    return;
                }
            }
            int t2;
            final int n6 = t2 = (this.T ? 1 : 0);
            if (!u) {
                if (n6 == 0) {
                    return;
                }
                t2 = this.S + y - this.Q;
            }
            int n7 = t2;
            if (this.H != null) {
                n7 -= this.C;
            }
            final int value = n7;
            if (!u) {
                if (value < 0) {
                    n7 = 0;
                }
                final int n8 = n7 * (this.v - this.w) / (this.K.height - this.J.height);
            }
            this.setValue(value);
        }
    }
    
    private void a(final AdjustmentEvent adjustmentEvent) {
        final AdjustmentListener l = this.L;
        if (!f.u) {
            if (l == null) {
                return;
            }
            final AdjustmentListener i = this.L;
        }
        l.adjustmentValueChanged(adjustmentEvent);
    }
    
    public void addAttributes(final String s, final String g) {
        final boolean u = f.u;
        String s2 = s;
        String s3 = s;
        if (!u) {
            if (s == null) {
                return;
            }
            s2 = g;
            s3 = g;
        }
        if (!u) {
            if (s3 == null) {
                return;
            }
            s2 = s;
        }
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = s2.equals(m.ab[4]));
        if (!u) {
            if (b) {
                if (g.equals(m.ab[5])) {
                    this.c(0);
                    this.m();
                    if (!u) {
                        return;
                    }
                }
                this.c(1);
                this.m();
                if (!u) {
                    return;
                }
            }
            final boolean b3;
            equals = (b3 = (b2 = s.equals(m.ab[7])));
        }
        if (!u) {
            if (b) {
                this.setName(g);
                if (!u) {
                    return;
                }
            }
            b2 = (equals = s.equals(m.ab[3]));
        }
        if (!u) {
            if (equals) {
                this.E = g;
                if (!u) {
                    return;
                }
            }
            b2 = s.equals(m.ab[6]);
        }
        if (b2) {
            this.G = g;
        }
    }
    
    public void a(final e e) {
        final boolean u = f.u;
        e e2 = e;
        if (!u) {
            if (!(e instanceof b)) {
                return;
            }
            e2 = e;
        }
        final String name;
        final String s = name = e2.getName();
        if (!u && name == null) {
            return;
        }
        final boolean equals = name.equals(m.ab[1]);
        Label_0080: {
            if (!u) {
                if (equals) {
                    this.H = (b)e;
                    if (!u) {
                        break Label_0080;
                    }
                }
                s.equals(m.ab[2]);
            }
            if (equals) {
                this.I = (b)e;
            }
        }
        this.m();
        super.a(e);
    }
    
    public void a(final Image image, final String s) {
        final boolean u = f.u;
        if (s == null || image == null) {
            return;
        }
        int n2;
        int a;
        final int n = a = (n2 = (s.equals(this.E) ? 1 : 0));
        if (!u) {
            if (n != 0) {
                System.out.println();
                this.D = image;
                this.c();
            }
            final boolean b;
            a = ((b = ((n2 = (s.equals(this.G) ? 1 : 0)) != 0)) ? 1 : 0);
        }
        Label_0164: {
            if (!u) {
                if (n == 0) {
                    break Label_0164;
                }
                this.F = image;
                this.V = this.F.getWidth(this);
                this.Y = this.V;
                n2 = (a = this.A);
            }
            m m = null;
            Label_0161: {
                Label_0139: {
                    if (!u) {
                        if (a == 0) {
                            final boolean b2 = (n2 = (this.X ? 1 : 0)) != 0;
                            if (u) {
                                break Label_0139;
                            }
                            if (!b2) {
                                this.V = 10;
                            }
                        }
                        this.W = this.F.getHeight(this);
                        this.Z = this.W;
                        m = this;
                        if (u) {
                            break Label_0161;
                        }
                        n2 = this.A;
                    }
                }
                if (n2 == 1) {
                    m = this;
                    if (u) {
                        break Label_0161;
                    }
                    if (!this.X) {
                        this.W = 10;
                    }
                }
                m = this;
            }
            m.c();
        }
        super.a(image, s);
    }
    
    static {
        final String[] ab = new String[10];
        final int n = 0;
        final char[] charArray = "c\u007fohbXlrdcB-ik,xZUn~Cajom^-o~,{\u007fick\r".toCharArray();
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
                            c2 = ',';
                            break;
                        }
                        case 1: {
                            c2 = '\r';
                            break;
                        }
                        case 2: {
                            c2 = '\u0006';
                            break;
                        }
                        case 3: {
                            c2 = '\r';
                            break;
                        }
                        default: {
                            c2 = '\f';
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
        ab[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "_ntb`@osyxCc7".toCharArray();
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
                            c4 = ',';
                            break;
                        }
                        case 1: {
                            c4 = '\r';
                            break;
                        }
                        case 2: {
                            c4 = '\u0006';
                            break;
                        }
                        case 3: {
                            c4 = '\r';
                            break;
                        }
                        default: {
                            c4 = '\f';
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
        ab[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "_ntb`@osyxCc4".toCharArray();
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
                            c6 = ',';
                            break;
                        }
                        case 1: {
                            c6 = '\r';
                            break;
                        }
                        case 2: {
                            c6 = '\u0006';
                            break;
                        }
                        case 3: {
                            c6 = '\r';
                            break;
                        }
                        default: {
                            c6 = '\f';
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
        ab[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "NjO`k".toCharArray();
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
                            c8 = ',';
                            break;
                        }
                        case 1: {
                            c8 = '\r';
                            break;
                        }
                        case 2: {
                            c8 = '\u0006';
                            break;
                        }
                        case 3: {
                            c8 = '\r';
                            break;
                        }
                        default: {
                            c8 = '\f';
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
        ab[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "Xtvh".toCharArray();
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
                            c10 = ',';
                            break;
                        }
                        case 1: {
                            c10 = '\r';
                            break;
                        }
                        case 2: {
                            c10 = '\u0006';
                            break;
                        }
                        case 3: {
                            c10 = '\r';
                            break;
                        }
                        default: {
                            c10 = '\f';
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
        ab[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "Dog\u007f".toCharArray();
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
                            c12 = ',';
                            break;
                        }
                        case 1: {
                            c12 = '\r';
                            break;
                        }
                        case 2: {
                            c12 = '\u0006';
                            break;
                        }
                        case 3: {
                            c12 = '\r';
                            break;
                        }
                        default: {
                            c12 = '\f';
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
        ab[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "NltDaK".toCharArray();
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
                            c14 = ',';
                            break;
                        }
                        case 1: {
                            c14 = '\r';
                            break;
                        }
                        case 2: {
                            c14 = '\u0006';
                            break;
                        }
                        case 3: {
                            c14 = '\r';
                            break;
                        }
                        default: {
                            c14 = '\f';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        ab[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "Blkh".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = ',';
                            break;
                        }
                        case 1: {
                            c16 = '\r';
                            break;
                        }
                        case 2: {
                            c16 = '\u0006';
                            break;
                        }
                        case 3: {
                            c16 = '\r';
                            break;
                        }
                        default: {
                            c16 = '\f';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        ab[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "{\u007fick\fb`-X{^e\u007fc@adl~\f}g\u007fmA~<-zE~oo`I-elb\u000by&3,Al~-!\f`oc".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = ',';
                            break;
                        }
                        case 1: {
                            c18 = '\r';
                            break;
                        }
                        case 2: {
                            c18 = '\u0006';
                            break;
                        }
                        case 3: {
                            c18 = '\r';
                            break;
                        }
                        default: {
                            c18 = '\f';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        ab[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "{\u007fick\fb`-X{^e\u007fc@adl~\f}g\u007fmA~<-aEc&nmB*r-2\u0011-klt\f,".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = ',';
                            break;
                        }
                        case 1: {
                            c20 = '\r';
                            break;
                        }
                        case 2: {
                            c20 = '\u0006';
                            break;
                        }
                        case 3: {
                            c20 = '\r';
                            break;
                        }
                        default: {
                            c20 = '\f';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 <= n40) {
                ab[n37] = new String(charArray10).intern();
                m.ab = ab;
                return;
            }
            continue;
        }
    }
}

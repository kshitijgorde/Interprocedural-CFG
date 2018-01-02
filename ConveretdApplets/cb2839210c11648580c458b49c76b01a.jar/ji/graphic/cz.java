// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.image.ImageObserver;
import ji.v1event.af;
import ji.document.ad;
import ji.v1event.d7;
import ji.v1event.a9;
import ji.util.i;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import ji.v1event.a6;
import ji.util.e;
import ji.util.d;
import java.awt.SystemColor;
import ji.v1event.a2;
import java.awt.Component;
import ji.v1event.ah;
import java.awt.event.MouseAdapter;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import ji.v1event.ak;
import ji.v1base.bz;

public class cz extends bz implements ak
{
    private boolean a;
    private boolean b;
    private String c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private Color j;
    private boolean k;
    private boolean l;
    private Color m;
    private int n;
    private boolean o;
    private Color p;
    private Graphics q;
    private Thread r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private Image x;
    private int y;
    private int z;
    private Color aa;
    private MouseAdapter ab;
    private int ac;
    private String ad;
    private ah ae;
    private Component af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private a2 al;
    private boolean am;
    
    public cz(final Component af, final String ad, final ah ae, final boolean am) {
        super(ad);
        this.a = false;
        this.b = false;
        this.c = "";
        this.d = 100;
        this.e = 20;
        this.f = true;
        this.g = true;
        this.h = false;
        this.i = 0;
        this.j = SystemColor.textHighlight;
        this.k = false;
        this.l = false;
        this.m = null;
        this.n = 0;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = 0;
        this.z = 0;
        this.aa = null;
        this.ab = null;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = false;
        this.ah = false;
        this.ai = false;
        this.aj = false;
        this.ak = false;
        this.al = null;
        this.am = true;
        this.af = af;
        this.ad = ad;
        this.ae = ae;
        this.am = am;
        try {
            this.h();
        }
        catch (Exception ex) {}
    }
    
    public final void f(final boolean b) {
        this.h = (b && ji.util.d.a7);
    }
    
    private final void h() throws Exception {
        ji.util.e.a(this);
        this.setSize(this.d, this.e);
        this.setBorderStyle(0);
        this.d(false);
        this.a(new a6(this, 1, ""));
        this.addMouseListener(this.ab = new uk());
        if (ji.util.e.g != null) {
            this.aa = ji.util.e.g;
        }
        else {
            this.aa = SystemColor.controlShadow;
        }
        (this.al = new a2("jiImageHandlerPaints", true, 50, this.ad)).b(this);
        this.aj = (ji.util.d.ax(this.ad) && ji.util.d.dp());
    }
    
    public final void a(final int n) {
        this.n = n;
        if (this.n == 2) {
            this.c = "";
        }
        if (this.n == 1) {
            this.i = 0;
        }
        this.k = true;
        this.l = true;
        this.repaint();
    }
    
    public final void a(final a6 a6) {
        if (ji.util.d.b()) {
            return;
        }
        boolean b = true;
        try {
            if (this.b) {
                return;
            }
            final String e = a6.e();
            this.b = true;
            if (!ji.util.d.a4) {
                if (this.c != null && this.n != 2) {
                    if (this.c.equals(e)) {
                        b = false;
                    }
                    if (!ji.util.d.by(e) && e.toLowerCase().startsWith("null")) {
                        b = false;
                    }
                }
            }
            else if (this.c != null && this.n != 2 && !e.equals("jiCopyRight")) {
                b = false;
            }
            if (b || a6.d() == 4) {
                if (this.n != 2 && (a6.d() != 4 || this.n == 1)) {
                    this.c = this.e(e);
                    this.k = true;
                    try {
                        if (a6.d() == 8) {
                            this.i();
                        }
                        else {
                            this.repaint();
                        }
                    }
                    catch (Exception ex) {}
                }
                else {
                    final int c = ji.util.d.c(e, 0);
                    if (c == 0) {
                        this.l = true;
                    }
                    if (this.i != c) {
                        this.i = c;
                        this.i();
                    }
                }
            }
        }
        catch (Exception ex2) {}
        finally {
            this.b = false;
        }
    }
    
    private void i() {
        ji.util.e.b(new t2(this), this.ad);
    }
    
    private final String e(final String s) {
        return ji.util.d.b(s, "\n", ", ");
    }
    
    public final void update(final Graphics graphics) {
        if (this.a()) {
            super.update(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public final void setBounds(final Rectangle bounds) {
        super.setBounds(bounds);
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
    }
    
    public final Dimension getMinimumSize() {
        return new Dimension(this.d, this.e);
    }
    
    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    private final void j() {
        if (this.m == null) {
            if (this.p != null) {
                this.m = this.p;
            }
            else {
                ji.util.e.a(this);
                this.m = this.getBackground();
            }
        }
    }
    
    private final void c(final Graphics graphics) {
        try {
            if (graphics != null && this.n != 2) {
                this.j();
                final int n = this.getSize().height - this.k() + 1;
                if (n > 0) {
                    graphics.setColor(this.m);
                    if (this.am) {
                        d6.b(graphics, 1, 0, this.getSize().width - 1, n + 1);
                    }
                    else {
                        d6.b(graphics, 1, 1, this.getSize().width - 1, n);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void d(final Graphics graphics) {
        try {
            if (graphics != null) {
                int height;
                if (ji.util.i.c(7) && ji.util.e.t() && ji.util.i.c(1)) {
                    if (this.n != 2) {
                        return;
                    }
                    height = this.getSize().height;
                }
                else {
                    height = this.getSize().height - this.k() + 1;
                }
                this.j();
                if (height > 0) {
                    graphics.setColor(this.m);
                    if (ji.util.i.c(7) && ji.util.e.t() && ji.util.i.c(1)) {
                        d6.b(graphics, 0, 0, this.getSize().width, height + 1);
                    }
                    else {
                        d6.b(graphics, 1, height, this.getSize().width - 1, this.k() - 1);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private int k() {
        if (this.n != 2) {
            return 3;
        }
        if (ji.util.i.c(7) && ji.util.e.t() && ji.util.i.c(1)) {
            return this.getSize().height;
        }
        return this.getSize().height - 1;
    }
    
    public final void a(final a9 a9) {
        if (a9.d() instanceof d7) {
            switch (((d7)a9.d()).d()) {
                case 57: {
                    this.i();
                    break;
                }
                case 58: {
                    this.a(this.getGraphics());
                    break;
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (graphics != null && this.q == null) {
            this.q = graphics.create();
        }
        if (ji.util.d.b()) {
            return;
        }
        if (this.aj) {
            if (!this.ak) {
                this.al.a(new a9(this, new d7(this, 58, false), true));
            }
        }
        else {
            this.a(graphics);
        }
    }
    
    public void a(final Graphics graphics) {
        if (ji.util.d.b()) {
            return;
        }
        if (this.a()) {
            super.paint(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        if (ji.util.d.b()) {
            return;
        }
        if (this.aj) {
            if (!this.ak) {
                this.al.a(new a9(this, new d7(this, 57, false), true));
            }
        }
        else {
            this.b(graphics);
        }
    }
    
    public void b(final Graphics graphics) {
        if (ji.util.d.b()) {
            return;
        }
        if (!this.ag) {
            try {
                this.ag = true;
                if (graphics != null) {
                    if (ji.document.ad.a && ji.util.d.av(this.ad) && this.q == null) {
                        this.q = graphics.create();
                    }
                    final Dimension size = this.getSize();
                    super.paintComponent(graphics);
                    if (this.h && ((ji.util.d.by() && ji.util.d.bz()) || ji.util.d.b0()) && this.n != 2) {
                        if (this.x == null) {
                            this.x = ji.util.d.a(1, this, null, this.ad);
                            this.y = this.x.getWidth(null);
                            this.z = this.x.getHeight(null);
                        }
                        this.ac = size.width - this.y - 5;
                        d6.a(graphics, this.x, this.ac, 1, null);
                        graphics.setColor(this.aa);
                        d6.a(graphics, this.ac -= 3, 0, this.y + 7, size.width - this.ac);
                        graphics.setClip(0, 0, this.ac - 1, size.height - 1);
                    }
                    if (ji.document.ad.a && ji.util.d.av(this.ad) && this.q == null) {
                        this.q = graphics.create();
                    }
                    this.c(graphics);
                    this.k = false;
                    if (this.l) {
                        this.d(graphics);
                        this.l = false;
                    }
                    this.f(graphics);
                    graphics.setColor(this.getForeground());
                    if (this.n != 2) {
                        if (ji.util.d.a4) {
                            if (this.g && ji.util.d.a6 != null && !ji.util.d.a6.toLowerCase().startsWith("null")) {
                                d6.a(graphics, ji.util.d.a6, 5, size.height / 2 + this.c().top + this.l() / 4);
                            }
                        }
                        else {
                            int n = ji.util.d.by(this.c) ? 0 : 1;
                            if (n != 0) {
                                if (this.c.toLowerCase().equals("jicopyright")) {
                                    n = 0;
                                }
                                if (this.c.toLowerCase().startsWith("null")) {
                                    n = 0;
                                }
                            }
                            if (n != 0) {
                                d6.a(graphics, this.c, 5, size.height / 2 + this.c().top + this.l() / 4);
                            }
                        }
                    }
                    this.e(graphics);
                }
            }
            catch (Exception ex) {}
            finally {
                this.ag = false;
            }
        }
    }
    
    private final void e(final Graphics graphics) {
        if (!this.ah) {
            try {
                this.ah = true;
                if (ji.util.d.ax(this.ad) && ji.util.d.dp() && graphics != null) {
                    final Dimension size = this.getSize();
                    Color color;
                    if (ji.util.e.g != null) {
                        color = ji.util.e.g;
                    }
                    else {
                        color = SystemColor.controlShadow;
                    }
                    graphics.setColor(color);
                    d6.a(graphics, 0, 0, size.width + 2, size.height + 2);
                }
            }
            catch (Exception ex) {}
            finally {
                this.ah = false;
            }
        }
    }
    
    private final void f(final Graphics graphics) {
        try {
            this.g(graphics);
        }
        catch (Exception ex) {}
    }
    
    private final void g(final Graphics graphics) {
        if (!this.ai) {
            try {
                this.ai = true;
                boolean b = true;
                if (ji.util.i.c(7) && ji.util.e.t() && ji.util.i.c(1)) {
                    if (this.n != 2) {
                        return;
                    }
                    b = false;
                }
                final Dimension size = this.getSize();
                this.w = size.height - this.k();
                final int n = size.width - 2;
                if (this.i > 0) {
                    this.u = 0;
                    this.t = (int)Math.round((this.getSize().width - 2) * this.i / 100.0);
                    graphics.setColor(this.j);
                    final int n2 = 3;
                    final int n3 = 1;
                    this.v = this.k() - 1;
                    for (int i = b ? 1 : 0; i < this.t; i += n2 + n3) {
                        d6.b(graphics, i, this.w, n2, this.v);
                    }
                    graphics.setColor(this.m);
                    for (int j = b ? 1 : 0; j < this.t; j += n2 + n3) {
                        d6.b(graphics, j + n2, this.w, n3, this.v);
                    }
                    graphics.setColor(this.m);
                    d6.b(graphics, this.t + 1, this.w, n - this.t, this.k() - 1);
                    this.u = this.t;
                    this.e(graphics);
                }
                else {
                    graphics.setColor(this.m);
                    d6.b(graphics, 1, this.w, this.getSize().width - 2, this.k() - 1);
                }
            }
            catch (Exception ex) {}
            finally {
                this.ai = false;
            }
        }
    }
    
    private final int l() {
        return this.getFontMetrics(this.getFont()).getHeight();
    }
    
    public final void setBackground(final Color color) {
        try {
            super.setBackground(color);
            this.p = color;
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public final Font getFont() {
        return super.getFont();
    }
    
    public final void setFont(final Font font) {
        super.setFont(font);
        this.l();
    }
    
    public final void setBorderStyle(final int borderStyle) {
        super.setBorderStyle(borderStyle);
    }
    
    public final void d(final String s) {
        if (!ji.util.d.a4 && this.c != s && this.n != 2) {
            if (!ji.util.d.by(s) && s.toLowerCase().startsWith("null")) {
                return;
            }
            this.c = this.e(s);
            this.k = true;
            this.repaint();
        }
    }
    
    public void releaseResources() {
        try {
            if (!this.o) {
                try {
                    if (this.al != null) {
                        this.al.a(this);
                        this.al.g();
                        this.al = null;
                    }
                }
                catch (Exception ex) {}
                this.ae = null;
                this.af = null;
                this.i = 0;
                this.o = true;
                this.removeMouseListener(this.ab);
                this.ab = null;
                super.releaseResources();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void finalize() {
    }
    
    class uk extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            try {
                if (ji.util.d.by() && ji.util.d.bz()) {
                    ji.util.d.a(cz.this.af, cz.this.ad, cz.this.ae);
                }
            }
            catch (Exception ex) {}
        }
    }
}

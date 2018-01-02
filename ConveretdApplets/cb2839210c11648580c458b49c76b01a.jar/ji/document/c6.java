// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.v1event.fz;
import java.awt.Container;
import ji.v1event.a6;
import java.util.Vector;
import ji.net.a0;
import ji.image.dx;
import ji.filter.ck;
import ji.io.ac;
import ji.v1event.b;
import ji.v1event.ae;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Dimension;
import ji.image.o7;
import java.awt.event.MouseMotionListener;
import ji.v1event.ag;
import ji.annotate.df;
import java.awt.event.ActionEvent;
import ji.image.dv;
import java.awt.SystemColor;
import java.awt.LayoutManager;
import ji.util.e;
import ji.v1event.c9;
import ji.v1event.ah;
import java.awt.Component;
import ji.io.h;
import ji.util.d;
import ji.v1event.af;
import java.awt.Color;
import ji.awt.bb;
import ji.awt.c;
import ji.graphic.jiImageButton;
import java.awt.Rectangle;
import ji.graphic.cz;
import ji.graphic.by;
import ji.v1event.cx;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import ji.v1base.jiPanel;

public class c6 extends jiPanel implements AdjustmentListener, ActionListener, MouseListener, cx
{
    private c7 a;
    private final int b = 20;
    private final int c = 500;
    protected by d;
    protected by e;
    private cz f;
    protected boolean g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private Rectangle w;
    private int x;
    private boolean y;
    private jiImageButton z;
    private c aa;
    private boolean ab;
    private boolean ac;
    private bb ad;
    private long ae;
    private int af;
    private ad ag;
    private String ah;
    private boolean ai;
    private long aj;
    private boolean ak;
    private Color al;
    private boolean am;
    private boolean an;
    private String ao;
    
    public c6(final ad ag, final af af, final String ah) {
        super(ah);
        this.a = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = true;
        this.h = -1;
        this.i = 120;
        this.j = 120;
        this.k = this.i;
        this.l = this.j;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = new Rectangle(0, 0, 0, 0);
        this.x = 17;
        this.y = false;
        this.z = null;
        this.ab = true;
        this.ac = false;
        this.ad = null;
        this.ae = 0L;
        this.af = 0;
        this.ag = null;
        this.ah = null;
        this.ai = false;
        this.aj = 0L;
        this.ak = false;
        this.al = null;
        this.am = false;
        this.an = true;
        this.ao = null;
        if (ji.util.d.dv()) {
            ji.io.h.e(ah, "jiThumb1");
        }
        this.ag = ag;
        this.ah = ah;
        this.setAllowClearInside(false);
        this.d = new by(1, 1, ah);
        this.e = new by(0, 1, ah);
        (this.f = new cz(this.ag, ah, ag, false)).a(this.ag.gs());
        (this.a = new c7(this, ag, af, ah)).v(this.n);
        if (ji.util.d.dv()) {
            ji.io.h.e(ah, "jiThumb2");
        }
        try {
            this.ba();
            if (ji.util.d.dv()) {
                ji.io.h.e(ah, "jiThumb3");
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void a(final Object[] array, final boolean b) {
        try {
            this.a.a(array, b);
        }
        catch (Exception ex) {}
    }
    
    public final Object[] a() {
        try {
            return this.a.e();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void a(final boolean am) {
        try {
            this.am = am;
            if (this.a != null) {
                this.a.a(am);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void ba() throws Exception {
        try {
            if (ji.util.d.dv()) {
                ji.io.h.e(this.ah, "jiThumb - jb1");
            }
            this.setAcceptFocus(true);
            ji.util.e.a(this);
            ji.util.e.a(this.a);
            this.setLayout(null);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.ah, "jiThumb - jb2");
            }
            super.setBorderStyle(0);
            ji.util.e.a(this.d);
            ji.util.e.a(this.e);
            this.d.setUnitIncrement(1);
            this.d.setBlockIncrement(1);
            this.d.c(true);
            this.e.setUnitIncrement(1);
            this.e.setBlockIncrement(1);
            this.e.c(true);
            this.d.b(true);
            this.e.b(true);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.ah, "jiThumb - jb3");
            }
            this.d.setValue(0);
            this.e.setValue(0);
            this.d.setLocation(-50, 0);
            this.e.setLocation(0, -50);
            this.d.setVisible(false);
            this.d.setBackground(SystemColor.control);
            this.e.setVisible(false);
            this.e.setBackground(SystemColor.control);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.ah, "jiThumb - jb4");
            }
            this.add(this.a);
            this.f.setBorderStyle(6);
            this.f.f(true);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.ah, "jiThumb - jb5");
            }
            final boolean b = false;
            if (!ji.util.d.b()) {
                (this.z = new jiImageButton("printabort", 1, 14, 14, false, true, false, false, 2, "141", "141", 2, false, null, null, b, null, -1, this.ah)).addActionListener(this);
                this.z.addMouseListener(this);
                this.d.addAdjustmentListener(this);
                this.e.addAdjustmentListener(this);
                this.p = false;
                this.d.setVisible(this.p);
                this.add(this.d);
                this.o = true;
                this.r = false;
                this.e.setVisible(this.r);
                this.add(this.e);
                this.q = true;
                this.t = false;
                this.f.setVisible(this.t);
                this.add(this.f);
                this.s = true;
                this.v = false;
                this.z.setVisible(this.v);
                this.add(this.z);
                this.u = true;
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.ah, "jiThumb - jb6");
                }
            }
            this.a.a(this.l, this.k);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.ah, "jiThumb - jb7");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b() {
        try {
            if (ji.util.d.by() && ji.util.d.bz()) {
                this.f.b(ji.util.d.ca(this.ah));
            }
        }
        catch (Exception ex) {}
    }
    
    public void b(final boolean ab) {
        if (this.ab != ab) {
            this.ab = ab;
            this.ap();
        }
    }
    
    public final boolean c() {
        return this.a != null && this.a.am();
    }
    
    public final int d() {
        if (this.a != null) {
            return this.a.h();
        }
        return ji.util.d.du;
    }
    
    public final int e() {
        if (this.a != null) {
            return this.a.i();
        }
        return ji.util.d.dv;
    }
    
    public final int f() {
        if (this.a != null) {
            return this.a.j();
        }
        return ji.util.d.dw;
    }
    
    public void a(final String s) {
        try {
            if (this.a != null) {
                this.a.a(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n) {
        if (this.a != null) {
            this.a.b(n);
        }
    }
    
    public final void b(final int n) {
        if (this.a != null) {
            this.a.c(n);
        }
    }
    
    public final void c(final int n) {
        if (this.a != null) {
            this.a.d(n);
        }
    }
    
    public final void a(final int n, final int n2, final int n3) {
        if (this.a != null) {
            this.a.a(n, n2, n3);
        }
    }
    
    public final void c(final boolean b) {
        if (this.a != null) {
            this.a.f(b);
        }
    }
    
    public final void a(final dv[] array, final int n) {
        if (this.a != null) {
            this.a.a(array, n);
        }
    }
    
    public final void g() {
        if (this.a != null) {
            this.a.l();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() instanceof jiImageButton) {
                this.y = false;
                this.ap();
                this.a(actionEvent);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ActionListener actionListener) {
        if (this.aa != null && this.aa.a(actionListener)) {
            this.aa.b(actionListener);
        }
    }
    
    public final void b(final ActionListener actionListener) {
        if (this.aa == null) {
            this.aa = new c("jiThumbs1", 2);
        }
        if (!this.aa.a(actionListener)) {
            this.aa.c(actionListener);
        }
    }
    
    protected final void a(final ActionEvent actionEvent) {
        if (this.aa != null) {
            final c aa = this.aa;
            for (int b = aa.b(), i = 0; i < b; ++i) {
                ((ActionListener)aa.b(i)).actionPerformed(actionEvent);
            }
        }
    }
    
    public final void d(final boolean b) {
        try {
            if (this.a != null) {
                this.a.y(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final df df, final c c, final Object o, final boolean b) {
        try {
            if (this.a != null) {
                this.a.a(df, c, o, b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final df h() {
        if (this.a != null) {
            return this.a.a5();
        }
        return null;
    }
    
    public final Object i() {
        if (this.a != null) {
            return this.a.a6();
        }
        return null;
    }
    
    public final c j() {
        if (this.a != null) {
            return this.a.a7();
        }
        return null;
    }
    
    public final void k() {
        try {
            if (this.a != null) {
                this.a.a8();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void l() {
        try {
            if (this.a != null) {
                this.a.k(false);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void m() {
        try {
            if (this.a != null) {
                this.a.a9();
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean n() {
        try {
            return this.a != null && this.a.f();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final boolean a(final boolean[] array) {
        try {
            return this.a == null || this.a.e(array);
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public final boolean[] o() {
        try {
            if (this.a != null) {
                return this.a.bc();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void a(final String[] array) {
        if (this.a != null) {
            this.a.a(array);
        }
    }
    
    public final void a(final String[] array, final es es) {
        if (this.a != null) {
            this.a.a(array, es);
        }
    }
    
    public final boolean p() {
        return this.a != null && this.a.ba();
    }
    
    public final void q() {
        if (this.a != null) {
            this.a.bb();
            this.a.repaint();
        }
    }
    
    public final void b(final String[] array) {
        if (this.a != null) {
            this.a.b(array);
        }
    }
    
    public final void r() {
        if (this.a != null) {
            this.a.ay();
        }
    }
    
    public void a(final String s, final int n) {
        this.a.a(s, n);
    }
    
    public void b(final String s) {
        this.a.d(s);
    }
    
    public final String[] s() {
        return this.a.a1();
    }
    
    public final String[] t() {
        return this.a.a2();
    }
    
    public final void c(final String s) {
        if (this.a != null) {
            this.a.e(s);
        }
    }
    
    public final void a(final String[] array, final Color[] array2) {
        if (this.a != null) {
            this.a.a(array, array2);
        }
    }
    
    public final void b(final String[] array, final Color[] array2) {
        if (this.a != null) {
            this.a.b(array, array2);
        }
    }
    
    public final String[] u() {
        return this.a.az();
    }
    
    public final Color[] v() {
        return this.a.a3();
    }
    
    public final String[] w() {
        return this.a.a0();
    }
    
    public final Color[] x() {
        return this.a.a4();
    }
    
    public final void e(final boolean b) {
        this.a.x(b);
    }
    
    public final void y() {
        if (this.a != null) {
            this.a.o();
        }
    }
    
    public final void f(final boolean b) {
        if (this.a != null) {
            this.a.b(b);
        }
    }
    
    public final Color getBackground() {
        if (this.al != null) {
            return this.al;
        }
        return super.getBackground();
    }
    
    public final void setBackground(final Color color) {
    }
    
    public void a(final ag ag) {
        if (this.a != null) {
            this.a.a(ag);
        }
    }
    
    public final void b(final ag ag) {
        if (this.a != null) {
            this.a.b(ag);
        }
    }
    
    public void setRightMouseEmulator(final boolean rightMouseEmulator) {
        if (this.a != null) {
            this.a.setRightMouseEmulator(rightMouseEmulator);
        }
    }
    
    public boolean isRightMouseEmulator() {
        return this.a != null && this.a.isRightMouseEmulator();
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        if (this.a != null) {
            this.a.addMouseListener(mouseListener);
        }
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        if (this.a != null) {
            this.a.removeMouseListener(mouseListener);
        }
    }
    
    public void addMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        if (this.a != null) {
            this.a.addMouseMotionListener(mouseMotionListener);
        }
    }
    
    public void removeMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        if (this.a != null) {
            this.a.removeMouseMotionListener(mouseMotionListener);
        }
    }
    
    public boolean a(final Object o) {
        return this.a != null && o instanceof c7;
    }
    
    public final boolean z() {
        try {
            return !this.a.a() || this.d.isVisible();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void d(final int n) {
        if (this.a != null) {
            this.a.g(n);
        }
    }
    
    public final void g(final boolean b) {
        if (this.a != null) {
            this.a.h(b);
        }
    }
    
    public final void a(final o7[] array) {
        if (this.a != null) {
            this.a.a(array);
        }
    }
    
    public final void aa() {
        if (this.a != null) {
            this.a.m();
        }
    }
    
    public final void h(final boolean y) {
        this.y = y;
        this.ap();
    }
    
    public final void e(final int n) {
        try {
            if (n > 0 && n <= 6 && this.z != null) {
                this.z.setImageFile(n, -1);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void f(final int n) {
        try {
            if (this.a != null) {
                this.a.f(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int ab() {
        try {
            if (this.a != null) {
                return this.a.p();
            }
        }
        catch (Exception ex) {}
        return 0;
    }
    
    public final void i(final boolean b) {
        try {
            if (this.a != null) {
                this.a.g(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final Dimension dimension) {
        if (this.j != dimension.width || this.i != dimension.height) {
            this.j = dimension.width;
            this.i = dimension.height;
            this.a.k(true);
            this.a.a(true, true);
        }
    }
    
    public final Dimension ac() {
        return new Dimension(this.j, this.i);
    }
    
    public final void ad() {
        try {
            if (this.a != null) {
                this.a.y();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void j(final boolean b) {
        try {
            if (this.a != null) {
                this.a.w(b);
            }
        }
        catch (Exception ex) {}
    }
    
    protected final boolean ae() {
        return this.g;
    }
    
    protected final void k(final boolean g) {
        this.g = g;
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (!this.m && this.g) {
            try {
                this.m = true;
                if (!this.a.g()) {
                    this.a.i(true);
                    if (!this.a.w()) {
                        this.a.z();
                        this.a.y();
                        this.a.k(false);
                        this.a.aa();
                        this.a.m(false);
                        this.a.repaint();
                    }
                }
                if (adjustmentEvent.getValue() == this.d.j()) {
                    this.a.m(true);
                    this.a.i(false);
                    if (this.h == 6) {
                        if (!this.ak()) {
                            this.a5();
                        }
                    }
                    else {
                        this.a5();
                    }
                }
                else {
                    this.a.b(-this.e.getValue(), -this.d.getValue());
                }
            }
            catch (Exception ex) {}
            finally {
                this.m = false;
            }
        }
    }
    
    protected void af() {
        if (this.a != null) {
            this.a.b(-this.e.getValue(), -this.d.getValue());
        }
    }
    
    private final Dimension a(final int n, final int n2, final Dimension dimension, final boolean b) {
        int n3 = 16;
        final boolean ab = this.a.ab();
        final Dimension dimension2 = new Dimension(0, 0);
        try {
            if (n2 > 0) {
                if (n != 6) {
                    this.l = this.j;
                    this.k = this.i;
                }
                else {
                    this.l = dimension.width / 2 - 6 - n3;
                    this.k = dimension.height - this.x - 12;
                }
                this.a.a(this.l, this.k);
                if (n == 3 || n == 1) {
                    if (this.e.isVisible()) {
                        this.e.setVisible(false);
                    }
                    dimension2.height = n2 * (this.k + 6) + 6;
                    final int width = this.getSize().width;
                    int n4;
                    if (dimension2.height <= this.getSize().height) {
                        n4 = Math.max((width - 6) / (this.l + 6), 1);
                        if (b) {
                            n4 = 1;
                        }
                        dimension2.width = n4 * (this.l + 6) + 6;
                        dimension2.height = (dimension2.height + (this.l + 6) / 2) / n4;
                        if (this.d.isVisible()) {
                            this.d.setVisible(false);
                        }
                    }
                    else {
                        n4 = Math.max((width - 6 - n3) / (this.l + 6), 1);
                        if (b) {
                            n4 = 1;
                        }
                        dimension2.width = n4 * (this.l + 6) + 6 + n3;
                        if (this.d.isVisible() != ab) {
                            this.d.setVisible(ab);
                        }
                    }
                    int n5 = n2 / n4;
                    if (n5 * n4 < n2) {
                        ++n5;
                    }
                    dimension2.height = n5 * (this.k + 6) + 6;
                }
                else if (n == 2 || n == 4) {
                    if (this.d.isVisible()) {
                        this.d.setVisible(false);
                    }
                    dimension2.width = n2 * (this.l + 6) + 6;
                    final int height = this.getSize().height;
                    int n6;
                    if (dimension2.width <= this.getSize().width) {
                        n6 = Math.max((height - 6) / (this.l + 6), 1);
                        if (b) {
                            n6 = 1;
                        }
                        dimension2.height = n6 * (this.k + 6) + 6;
                        if (this.e.isVisible()) {
                            this.e.setVisible(false);
                        }
                    }
                    else {
                        n6 = Math.max((height - 6 - n3) / (this.l + 6), 1);
                        if (b) {
                            n6 = 1;
                        }
                        dimension2.height = n6 * (this.k + 6) + 6 + n3;
                        if (this.e.isVisible() != ab) {
                            this.e.setVisible(ab);
                        }
                    }
                    int n7 = n2 / n6;
                    if (n7 * n6 < n2) {
                        ++n7;
                    }
                    dimension2.width = n7 * (this.l + 6) + 6;
                }
                else if (n == 5 || n == 6) {
                    int n8;
                    if (n == 6) {
                        n8 = 2;
                    }
                    else {
                        n8 = (dimension.width - 6 - n3) / (this.l + 6);
                        if (n8 * ((dimension.height - 6) / (this.k + 6)) >= n2) {
                            n8 = (dimension.width - 6) / (this.l + 6);
                            n3 = 0;
                        }
                    }
                    dimension2.height = n2 / n8 * (this.k + 6) + 3;
                    dimension2.width = dimension.width - n3 + 3;
                    if (n8 * (dimension2.height / (this.k + 6)) < n2) {
                        final Dimension dimension3 = dimension2;
                        dimension3.height += this.k + 6;
                    }
                    if (dimension2.height <= this.getSize().height) {
                        if (this.d.isVisible()) {
                            this.d.setVisible(false);
                        }
                    }
                    else if (this.d.isVisible() != ab) {
                        this.d.setVisible(ab);
                    }
                }
                if (this.t) {
                    final Dimension dimension4 = dimension2;
                    dimension4.height += this.x;
                }
            }
        }
        catch (Exception ex) {}
        if (this.d.isVisible()) {
            ji.util.e.a(this.d);
        }
        return dimension2;
    }
    
    public final void l(final boolean b) {
        this.a.d(b);
    }
    
    public final void setVisible(final boolean visible) {
        boolean cp = false;
        if (this.ag != null) {
            cp = this.ag.cp();
        }
        if (!cp && this.isVisible() != visible) {
            super.setVisible(visible);
            if (visible) {}
        }
    }
    
    protected final boolean ag() {
        return this.p || this.r;
    }
    
    protected final boolean a(int value, int value2) {
        boolean b = false;
        try {
            switch (this.h) {
                case 1:
                case 3:
                case 5:
                case 6: {
                    if (value2 < this.d.getMinimum()) {
                        value2 = this.d.getMinimum();
                    }
                    if (value2 > this.d.getMaximum()) {
                        value2 = this.d.getMaximum();
                    }
                    if (this.d.getValue() == value2) {
                        break;
                    }
                    this.d.setValue(value2);
                    if (this.d.getValue() == value2) {
                        b = true;
                        break;
                    }
                    break;
                }
                case 2:
                case 4: {
                    if (value < this.e.getMinimum()) {
                        value = this.e.getMinimum();
                    }
                    if (value > this.e.getMaximum()) {
                        value = this.e.getMaximum();
                    }
                    if (this.e.getValue() == value) {
                        break;
                    }
                    this.e.setValue(value);
                    if (this.e.getValue() == value) {
                        b = true;
                        break;
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    protected final void ah() {
        try {
            if (this.a.a()) {
                int n = this.a.ae();
                if (this.ak) {
                    n = this.a.ac();
                }
                final Dimension a = this.a(this.a.bf(), n, this.getSize(), false);
                if (!this.a.au().equals(a)) {
                    this.a.c(a.width, a.height);
                }
                if (this.a.au().height > this.getSize().height) {
                    this.d.a(this.d.getValue(), Math.max(this.getSize().height, 1), 0, a.height - this.getSize().height + 6);
                }
                this.d.setUnitIncrement(this.k + 6);
                if (this.h == 6) {
                    this.d.setBlockIncrement(this.k + 6);
                }
                else {
                    this.d.setBlockIncrement(this.getSize().height);
                }
                if (this.a.au().width > this.getSize().width) {
                    this.e.a(this.e.getValue(), Math.max(this.getSize().width, 1), 0, a.width - this.getSize().width + 6);
                }
                this.e.setUnitIncrement(this.l + 6);
                this.e.setBlockIncrement(2 * (this.l + 6));
                this.bb();
            }
            else {
                if (this.d != null) {
                    this.d.setVisible(false);
                }
                if (this.e != null) {
                    this.e.setVisible(false);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void bb() {
        if (this.a.au().height <= this.getSize().height) {
            if (this.p) {
                this.p = false;
                if (this.d.isVisible() != this.p) {
                    this.d.setVisible(this.p);
                }
            }
        }
        else if (!this.p) {
            this.p = true;
            if (this.d.isVisible() != this.p) {
                this.d.setVisible(this.p);
            }
        }
        if (this.a.au().width <= this.getSize().width) {
            if (this.r) {
                this.r = false;
                if (this.e.isVisible() != this.r) {
                    this.e.setVisible(this.r);
                }
            }
        }
        else if (!this.r && this.h != 6) {
            this.r = true;
            if (this.e.isVisible() != this.r) {
                this.e.setVisible(this.r);
            }
        }
    }
    
    public final void ai() {
        this.d.setValue(0);
        this.e.setValue(0);
        this.a.b(0, 0);
    }
    
    public final void g(final int n) {
        if (!this.a.b()) {
            this.a.s(n);
        }
    }
    
    public final void h(final int n) {
        if (this.a.b()) {
            this.a.q(n);
        }
    }
    
    public final boolean aj() {
        return this.a.b();
    }
    
    public final boolean ak() {
        return this.a.be();
    }
    
    public final void al() {
        this.af = 1;
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2, n3, n4);
    }
    
    public final void am() {
        try {
            this.a.repaint();
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        try {
            if (!new Rectangle(n, n2, n3, n4).equals(this.w)) {
                this.a.y();
                super.setBounds(n, n2, n3, n4);
                this.aj = System.currentTimeMillis();
                this.m(true);
                this.ai();
                this.w = new Rectangle(n, n2, n3, n4);
            }
            this.ah();
            this.ap();
            this.d.repaint();
            this.e.repaint();
            this.bc();
        }
        catch (Exception ex) {}
    }
    
    private final void bc() {
        if (!this.n) {
            ji.util.d.ew();
        }
    }
    
    public final void an() {
        this.aj = 0L;
    }
    
    public final boolean ao() {
        return this.aj != 0 && System.currentTimeMillis() - this.aj < 100;
    }
    
    public final void m(final boolean ai) {
        this.ai = ai;
        if (this.ai && this.h == 6) {
            this.a.k(false);
        }
    }
    
    public final void ap() {
        try {
            final Dimension size = this.getSize();
            int max = 0;
            int max2 = 0;
            int n = 0;
            if (this.h == 5 || this.h == 6) {
                final int n2 = (int)(1.75 * this.x);
                boolean b;
                if (this.ab) {
                    b = true;
                    if (this.y) {
                        n = n2;
                        this.ac(true);
                        ji.util.e.a(this.z, 1, size.height - max2 - this.x, n2, this.x);
                    }
                    else {
                        this.ac(false);
                    }
                }
                else {
                    b = false;
                    this.ac(false);
                }
                if (b) {
                    ji.util.e.a(this.f, 1 + n, size.height - max2 - this.x, size.width - 1 - n, this.x);
                    final Dimension dimension = size;
                    dimension.height -= this.x;
                    this.ab(true);
                }
                else {
                    this.ab(false);
                }
            }
            else {
                this.ab(false);
            }
            if (this.a.a()) {
                if (this.p) {
                    max = Math.max(this.d.getMinimumSize().width, 18);
                    ji.util.e.a(this.d, size.width - max, 0, max, size.height);
                }
                if (this.r) {
                    max2 = Math.max(this.e.getMinimumSize().height, 18);
                    ji.util.e.a(this.e, 0, size.height - max2, size.width, max2);
                }
            }
            ji.util.e.a(this.a, 2, 2, size.width - max - 4, size.height - max2 - 3);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof jiImageButton) {
            if (this.z.getState() == 2) {
                this.f(String.valueOf(String.valueOf(this.z.getHelpTextRaised())).concat("..."));
            }
            else {
                this.f(String.valueOf(String.valueOf(this.z.getHelpTextInset())).concat("..."));
            }
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof jiImageButton) {
            this.f("");
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void setEnabled(final boolean enabled) {
        this.d.setEnabled(enabled);
        this.e.setEnabled(enabled);
        if (this.a != null) {
            this.a.setEnabled(enabled);
        }
    }
    
    public final boolean isEnabled() {
        return this.d.isEnabled();
    }
    
    public void n(final boolean b) {
        this.a.m(b);
    }
    
    public final boolean aq() {
        return this.a.x();
    }
    
    public final boolean o(final boolean b) {
        return this.a.l(b);
    }
    
    public final void a(final af af) {
        if (this.a != null) {
            this.a.a(af);
        }
    }
    
    public final void b(final af af) {
        this.a.b(af);
    }
    
    public final void a(final ae ae) {
        if (this.a != null) {
            this.a.a(ae);
        }
    }
    
    public final void b(final ae ae) {
        this.a.b(ae);
    }
    
    public final void a(final b b) {
        if (this.a != null) {
            this.a.a(b);
        }
    }
    
    public final void b(final b b) {
        if (this.a != null) {
            this.a.b(b);
        }
    }
    
    public final void ar() {
        this.a.t();
    }
    
    public final void as() {
        if (ji.util.d.dv()) {
            ji.io.h.e(this.ah, "jiThumbsClose 1...");
        }
        this.a.y();
        if (ji.util.d.dv()) {
            ji.io.h.e(this.ah, "jiThumbsClose 2...");
        }
        this.a.r();
        if (ji.util.d.dv()) {
            ji.io.h.e(this.ah, "jiThumbsClose 3...");
        }
        try {
            this.r = false;
            this.e.setVisible(this.r);
            this.p = false;
            this.d.setVisible(this.p);
            this.ab(false);
            this.d.setLocation(-50, 0);
            this.e.setLocation(0, -50);
            this.ai();
        }
        catch (Exception ex) {}
        super.setBorderStyle(0);
    }
    
    public final void p(final boolean visible) {
        this.a.n(visible);
        this.setVisible(visible);
    }
    
    public final void a(final Color color) {
        if (this.a != null) {
            this.a.c(color);
        }
    }
    
    public final void a(final Color[] array) {
        if (this.a != null) {
            this.a.a(array);
        }
    }
    
    public final void i(final int n) {
        this.a.i(n);
    }
    
    public final void a(final Object o, final int n) {
        this.a.a(o, n);
    }
    
    public final void a(final Object[] array, final int n) {
        this.a.a(array, n);
    }
    
    public final void a(final ac ac, final String s, final Object o, final String s2, final Object[] array, final Object[] array2, final boolean b, final ck ck, final dx dx, final c c, final a0 a0, final int n, final Object o2, final boolean b2) {
        this.a.a(ac, s, o, s2, array, array2, b, ck, dx, c, a0, n, o2, b2);
        super.setBorderStyle(2);
        this.ah();
    }
    
    public final void a(final int n, final boolean b) {
        try {
            this.a.b(n, b);
        }
        catch (Exception ex) {}
    }
    
    public final void j(final int n) {
        try {
            this.a.n(n);
        }
        catch (Exception ex) {}
    }
    
    public final void b(final boolean[] array) {
        try {
            this.a.d(array);
        }
        catch (Exception ex) {}
    }
    
    public final void at() {
        try {
            this.a.an();
        }
        catch (Exception ex) {}
    }
    
    public final void k(final int n) throws Exception {
        try {
            if (this.h == 5 || this.h == 6 || ji.util.d.bw()) {
                this.g(n);
            }
            this.a.p(n);
        }
        catch (Exception ex) {}
    }
    
    public final void l(final int n) throws Exception {
        try {
            if (this.h == 5 || this.h == 6 || ji.util.d.bw()) {
                this.h(n);
            }
            this.a.o(n);
        }
        catch (Exception ex) {}
    }
    
    public final int au() {
        try {
            return this.a.ao();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int av() {
        try {
            return this.a.ap();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final dx aw() {
        try {
            return this.a.aq();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final int m(final int n) {
        try {
            return this.a.l(n);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int n(final int n) {
        try {
            return this.a.m(n);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final dv[] ax() {
        try {
            return this.a.aj();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void ay() {
        this.g = false;
        this.a.s();
        this.g = true;
    }
    
    public final void setBorderStyle(final int borderStyle) {
        this.a.setBorderStyle(borderStyle);
    }
    
    public final void setShadowWidth(final int shadowWidth) {
        this.a.setShadowWidth(shadowWidth);
    }
    
    public final Dimension b(final int n, final int n2, int ab) {
        if (ab == -1) {
            ab = this.ab();
        }
        Dimension dimension;
        if (c7.a(ab)) {
            if (ji.util.d.di() != 0) {
                if (this.a.ae() <= 0) {
                    dimension = this.a(this.a.bf(), 1, new Dimension(n, n2), true);
                }
                else {
                    dimension = this.a(this.a.bf(), this.a.ae(), new Dimension(n, n2), true);
                }
            }
            else {
                dimension = this.a(this.a.bf(), this.a.ae(), new Dimension(n, n2), true);
            }
            final Dimension dimension2 = dimension;
            dimension2.width += this.a.q();
            final Dimension dimension3 = dimension;
            dimension3.height += this.a.q();
        }
        else {
            final int d = ji.util.d.d(this.ag);
            if (d < 3) {
                dimension = new Dimension(65, 65);
            }
            else {
                final int n3 = (d + 1) * 20 + 5;
                dimension = new Dimension(n3, n3);
            }
        }
        return dimension;
    }
    
    public final void az() {
        try {
            if (this.a != null) {
                this.a.af();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a0() {
        try {
            if (this.a != null) {
                this.a.ag();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void o(final int n) {
        if (this.a != null) {
            this.a.h(n);
        }
        try {
            if (!ji.util.d.bz() || !ji.util.d.by()) {
                this.f.b((String)null);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void q(final boolean b) {
        if (this.a != null) {
            this.a.s(b);
        }
    }
    
    public final void d(final String s) {
        try {
            if (this.a != null) {
                this.a.b(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void r(final boolean b) {
        try {
            if (this.a != null) {
                this.a.p(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final c c) {
        if (this.a != null) {
            this.a.a(c);
        }
    }
    
    public final void s(final boolean ak) {
        this.ak = ak;
        if (this.a != null) {
            this.a.q(ak);
        }
    }
    
    public final boolean a1() {
        return this.ak;
    }
    
    public void t(final boolean b) {
        if (this.a != null) {
            this.a.r(b);
        }
    }
    
    public final void u(final boolean n) {
        this.n = n;
        if (this.a != null) {
            this.a.v(n);
        }
    }
    
    public final void a(final int n, final boolean[] array) {
        if (this.a != null) {
            this.a.a(n, array);
        }
    }
    
    public final void c(final boolean[] array) {
        if (this.a != null) {
            this.a.a(array);
        }
    }
    
    public final void d(final boolean[] array) {
        if (this.a != null) {
            this.a.b(array);
        }
    }
    
    public final void e(final boolean[] array) {
        if (this.a != null) {
            this.a.c(array);
        }
    }
    
    public final void p(final int n) {
        if (this.a != null) {
            this.a.j(n);
        }
    }
    
    public final void a(final int n, final int n2, final boolean b) {
        if (this.a != null) {
            this.a.b(n, n2, b);
        }
    }
    
    public final void b(final Color color) {
        if (this.a != null) {
            this.a.a(color);
        }
    }
    
    public final void v(final boolean b) {
        if (this.a != null) {
            this.a.o(b);
        }
    }
    
    public final Dimension a2() {
        if (this.a != null) {
            return this.a.ai();
        }
        return null;
    }
    
    public final void c(final Color color) {
        if (this.a != null) {
            this.a.b(color);
        }
    }
    
    public final void w(final boolean an) {
        try {
            this.an = an;
            if (this.a != null) {
                this.a.c(an);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final Vector vector, final boolean b) {
        if (this.a != null) {
            this.a.a(vector, b);
        }
    }
    
    public final void b(final int n, final boolean b) {
        if (this.a != null) {
            this.a.a(n, b);
        }
    }
    
    public final void q(final int n) {
        if (this.a != null) {
            this.a.k(n);
        }
    }
    
    public final void x(final boolean b) {
        this.a.b(b, true);
    }
    
    public final void a(final boolean b, final boolean b2) {
        this.a.b(b, b2);
    }
    
    public final void b(final int n, final int n2, final boolean b) {
        this.a.a(n, n2, b);
    }
    
    public final void r(final int h) {
        if (this.h != h && this.h != 0) {
            this.h = h;
            this.a.u(h);
        }
    }
    
    public final void a3() {
        try {
            this.a.z();
        }
        catch (Exception ex) {}
    }
    
    public final void a4() {
        try {
            this.a.aa();
        }
        catch (Exception ex) {}
    }
    
    public final void a5() {
        this.a.at();
    }
    
    public void y(final boolean b) {
        if (this.a != null) {
            this.a.e(b);
        }
    }
    
    public boolean a6() {
        return this.a != null && this.a.k();
    }
    
    public void a7() {
        this.a.c();
    }
    
    public final boolean a8() {
        return this.a != null && this.a.n();
    }
    
    public final boolean a9() {
        return this.a.bd();
    }
    
    public final void z(final boolean b) {
        this.a.t(b);
    }
    
    private final void ab(final boolean t) {
        if (this.t != t) {
            this.t = t;
            if (this.f.isVisible() != this.t) {
                this.f.setVisible(this.t);
            }
        }
    }
    
    public final void a(final a6 a6) {
        if (a6.c() == 0) {
            a6.b(2);
        }
        if (this.t) {
            this.f.a(a6);
        }
    }
    
    private final void f(final String s) {
        this.a(new a6(this, 1, s));
    }
    
    public void releaseResources() {
        if (!this.ac) {
            try {
                this.ac = true;
                try {
                    if (this.a != null) {
                        this.remove(this.a);
                        this.a.releaseResources();
                        this.a = null;
                    }
                }
                catch (Exception ex) {}
                try {
                    if (this.d != null) {
                        this.d.removeAdjustmentListener(this);
                        this.d.releaseResources();
                        this.remove(this.d);
                        this.d = null;
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (this.e != null) {
                        this.e.removeAdjustmentListener(this);
                        this.e.releaseResources();
                        this.remove(this.e);
                        this.e = null;
                    }
                }
                catch (Exception ex3) {}
                this.ab(false);
                try {
                    if (this.s) {
                        this.remove(this.f);
                        this.s = false;
                    }
                }
                catch (Exception ex4) {}
                try {
                    if (this.f != null) {
                        this.f.b(this.ag.gs());
                        this.f.releaseResources();
                    }
                }
                catch (Exception ex5) {}
                if (this.z != null) {
                    this.ac(false);
                    this.remove(this.z);
                    this.u = false;
                    this.z.releaseResources();
                }
                ji.util.d.a(this, this.ah);
                this.ag = null;
                super.releaseResources();
            }
            catch (Exception ex6) {}
        }
    }
    
    private final void ac(final boolean v) {
        try {
            if (this.v != v) {
                this.v = v;
                if (this.z.isVisible() != this.v) {
                    this.z.setVisible(this.v);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void finalize() {
        try {
            if (ji.util.d.cz()) {
                ji.io.h.d(this.ah, "finalize thumbs ".concat(String.valueOf(String.valueOf(this))));
            }
            super.finalize();
        }
        catch (Exception ex) {}
    }
    
    public void aa(final boolean b) {
        try {
            this.a.z(b);
            this.ah();
            this.ap();
            ji.util.e.a(new xd(this), this.ah);
            this.a5();
        }
        catch (Exception ex) {}
    }
    
    public void a(final fz fz) {
        if (this.a != null) {
            this.a.a(fz);
        }
    }
    
    public Vector e(final String s) {
        if (this.a != null) {
            return this.a.f(s);
        }
        return null;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import ji.io.h;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import ji.v1base.bz;
import java.awt.Point;
import java.awt.event.MouseEvent;
import ji.v1event.oi;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.SystemColor;
import ji.util.i;
import java.awt.Graphics;
import java.awt.Insets;
import ji.util.e;
import ji.v1event.aj;
import ji.util.d;
import java.awt.Component;
import java.awt.LayoutManager;
import ji.document.ad;
import ji.awt.c;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import ji.v1event.c9;
import java.awt.Dimension;
import ji.v1base.jiPanel;

public class cv extends jiPanel
{
    Dimension a;
    Dimension b;
    f5[] c;
    int d;
    boolean e;
    c9 f;
    ActionListener g;
    MouseListener h;
    c i;
    c j;
    int k;
    int l;
    int m;
    int n;
    boolean o;
    z2 p;
    t0 q;
    boolean r;
    boolean s;
    private c t;
    private t3 u;
    private boolean v;
    private boolean w;
    private boolean x;
    private int y;
    private boolean z;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private String ad;
    static final Object ae;
    private ad af;
    final Object ag;
    private boolean ah;
    private boolean ai;
    static boolean aj;
    
    public cv(final ad af, final String ad, final Dimension dimension, final Dimension dimension2, final String id, final int n, final boolean s, final int y) {
        super(ad);
        this.a = new Dimension(0, 0);
        this.b = new Dimension(0, 0);
        this.c = null;
        this.d = 1;
        this.e = false;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 2;
        this.o = false;
        this.p = null;
        this.q = null;
        this.r = false;
        this.s = false;
        this.u = null;
        this.v = false;
        this.w = false;
        this.x = true;
        this.y = 0;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = true;
        this.ad = null;
        this.af = null;
        this.ag = new Object();
        this.ah = false;
        this.ai = false;
        try {
            this.af = af;
            this.ad = ad;
            this.setId(id);
            this.setLayout(null);
            this.setAcceptFocus(false);
            this.setBorderStyle(0);
            this.n = n;
            this.a.width = dimension.width;
            this.a.height = dimension.height;
            this.b.width = dimension2.width;
            this.b.height = dimension2.height;
            this.s = s;
            this.y = y;
            this.addMouseListener(this.u = new t3(this));
            try {
                if (n == 3 && this.q == null) {
                    this.add(this.q = new t0(ad, this));
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public final String a() {
        return this.getId();
    }
    
    public void a(final boolean ai) {
        this.ai = ai;
    }
    
    public final void a(final f5[] c, final boolean e, final int k, final ActionListener g, final c9 f, final MouseListener h, final c i, final c j, final String s) {
        this.c = c;
        this.e = e;
        this.k = k;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.a(s);
        this.ai = true;
    }
    
    public final void b() {
        try {
            if (this.d == 1) {
                final int di = ji.util.d.di();
                boolean b = false;
                this.d = 0;
                for (int i = 0; i < this.c.length; ++i) {
                    if (this.c[i] != null && this.c[i].l) {
                        if (this.c[i].ac && this.c[i].w[di]) {
                            b = true;
                        }
                        else if (b && !this.c[i].ac && this.c[i].w[di]) {
                            ++this.d;
                        }
                    }
                }
                if (this.d == 0) {
                    this.d = 1;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final String s) {
        if (this.c != null) {
            synchronized (this.ag) {
                this.p();
            }
            // monitorexit(this.ag)
        }
    }
    
    private void p() {
        try {
            synchronized (this.ag) {
                this.getInsets();
                final int di = ji.util.d.di();
                this.b();
                for (int i = 0; i < this.c.length; ++i) {
                    if (this.c[i] != null && this.c[i].x == null && !this.c[i].ac && this.c[i].w[di]) {
                        String s = null;
                        String s2 = null;
                        if (this.c[i].ad) {
                            s = (String)this.i.b(this.c[i].f);
                            s2 = (String)this.j.b(this.c[i].f);
                        }
                        this.c[i].x = new jiImageButton(this.c[i].c, this.c[i].g, this.b.width, this.b.height, this.c[i].m, this.c[i].n, true, true, this.c[i].o, this.c[i].p, this.c[i].q, this.c[i].e, this.c[i].ad, s, s2, true, this.c[i].i, this.c[i].h, this.ad);
                        if (!this.c[i].x.isSpacer()) {
                            this.c[i].x.a(this.f);
                            this.c[i].x.addMouseListener(this.h);
                            this.c[i].x.addActionListener(this.g);
                        }
                        if (this.ac) {
                            this.add(this.c[i].x);
                            this.c[i].d = true;
                        }
                        if (!this.c[i].l) {
                            this.a(this.c[i].x, -3 * this.a.width, -3 * this.a.height);
                        }
                    }
                }
                final int b = this.b(false);
                final int b2 = this.b(true);
                if (b > 0) {
                    this.l = b;
                }
                if (b2 > 0) {
                    this.m = b2;
                }
            }
            // monitorexit(this.ag)
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void a(final aj aj) {
        if (this.t != null && this.t.a(aj)) {
            this.t.b(aj);
        }
    }
    
    public final void b(final aj aj) {
        if (this.t == null) {
            this.t = new c("jiToolbar1", 2);
        }
        if (!this.t.a(aj)) {
            this.t.c(aj);
        }
    }
    
    private void a(final jiImageButton jiImageButton, final int n, final int n2) {
        ji.util.e.a(jiImageButton, n, n2);
    }
    
    public final int b(final boolean b) {
        int n = this.a.height - 1;
        int width = this.a.width;
        int n2 = this.a.height;
        if (this.c != null && this.isVisible()) {
            try {
                final Insets insets = this.getInsets();
                final Dimension size = this.getSize();
                if (!this.e) {
                    size.width = this.a.width;
                    n2 = size.height;
                }
                int n4;
                final int n3 = n4 = insets.left + 2;
                int n6;
                final int n5 = n6 = insets.top + 2;
                int width2 = 0;
                int n7 = 0;
                int n8 = 0;
                int n9 = 0;
                final int length = this.c.length;
                final int di = ji.util.d.di();
                if (this.n == 3) {
                    if (this.e) {
                        n4 += 15;
                    }
                    else {
                        n6 += 15;
                    }
                }
                for (int i = 0; i < length; ++i) {
                    if (this.c[i].c.startsWith("spacer")) {
                        this.c[i].l = false;
                    }
                    else if (this.c[i].l) {
                        break;
                    }
                }
                for (int j = 0; j < length; ++j) {
                    boolean b2 = true;
                    if (this.c[j].c.startsWith("spacer") && this.c[j].l) {
                        if (n9 != 0) {
                            b2 = false;
                        }
                        else {
                            b2 = true;
                            n9 = 1;
                        }
                    }
                    else if (this.c[j].l) {
                        n9 = 0;
                    }
                    boolean b3 = true;
                    if (b && !this.v) {
                        b3 = false;
                    }
                    if (b2 && this.c[j].l && this.c[j].w[di] && (!this.r || b)) {
                        if (!this.c[j].ac) {
                            if (this.c[j].x != null) {
                                if (this.c[j].c.startsWith("disabled")) {
                                    if (b3) {
                                        if (n4 < 3) {
                                            this.a(this.c[j].x, -3 * this.a.width, -3 * this.a.height);
                                        }
                                        else {
                                            this.a(this.c[j].x, n4, n6);
                                        }
                                    }
                                }
                                else if (b3) {
                                    this.a(this.c[j].x, n4, n6);
                                }
                                if (this.e) {
                                    if (n4 == n3) {
                                        if (!this.c[j].x.isSpacer() && !this.c[j].x.isSpacerSmall()) {
                                            n4 += this.c[j].x.getButtonSize().width;
                                        }
                                        else if (b3) {
                                            this.a(this.c[j].x, -3 * this.a.width, -3 * this.a.height);
                                        }
                                    }
                                    else if (this.c[j].l) {
                                        n4 += this.c[j].x.getButtonSize().width;
                                    }
                                }
                                else if (n6 == n5) {
                                    if (!this.c[j].x.isSpacer() && !this.c[j].x.isSpacerSmall()) {
                                        n6 += this.c[j].x.getButtonSize().height;
                                    }
                                    else if (b3) {
                                        this.a(this.c[j].x, -3 * this.a.width, -3 * this.a.height);
                                    }
                                }
                                else if (this.c[j].x.isSpacer()) {
                                    n6 += 2 * this.c[j].x.getButtonSize().height;
                                }
                                else {
                                    n6 += this.c[j].x.getButtonSize().height;
                                }
                            }
                        }
                        else if (this.e) {
                            if (n6 == n5) {
                                n4 += Math.max(size.width - n4 - (this.a.width + 10) * this.d, 0);
                            }
                        }
                        else if (n4 == n3) {
                            n6 += Math.max(n2 - n6 - (this.a.height + 10), 0);
                        }
                        if (size.width > 0) {
                            if (this.e) {
                                if (this.c[j].ac) {
                                    width2 = (this.a.width + 10) * this.d;
                                    n7 = (n8 = this.a.height);
                                }
                                else if (this.c[j].l) {
                                    width2 = this.a.width + 10;
                                    n7 = (n8 = this.a.height);
                                }
                                if (n4 + width2 > size.width) {
                                    n4 = n3;
                                    if (this.n == 3) {
                                        n4 += 15;
                                    }
                                    n6 += n7;
                                    if (j < length - 1) {
                                        try {
                                            boolean b4 = false;
                                            for (int k = j + 1; k < length; ++k) {
                                                if (this.c[k].l) {
                                                    b4 = true;
                                                    break;
                                                }
                                            }
                                            if (b4) {
                                                n += n8;
                                            }
                                        }
                                        catch (Exception ex) {
                                            ji.util.d.a(ex);
                                        }
                                    }
                                }
                            }
                            else {
                                int n10;
                                if (this.c[j].ac) {
                                    n10 = this.a.height;
                                    n8 = this.a.height;
                                }
                                else {
                                    width2 = this.a.width;
                                    n10 = this.a.width;
                                    n8 = this.a.height;
                                }
                                if (n6 + n8 > n2) {
                                    n6 = n5;
                                    if (this.n == 3) {
                                        n6 += 15;
                                    }
                                    n4 += n10;
                                    if (j < length - 1) {
                                        width += n10;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        try {
                            if (this.c[j].x != null) {
                                this.a(this.c[j].x, -3 * this.a.width, -3 * this.a.height);
                            }
                        }
                        catch (Exception ex3) {}
                    }
                }
                this.q();
            }
            catch (Exception ex2) {
                if (ji.util.d.cy()) {
                    ex2.printStackTrace();
                }
            }
        }
        if (!this.e) {
            return width - 1;
        }
        this.getBounds();
        if (this.d()) {
            return n;
        }
        return n + 1;
    }
    
    public final boolean c() {
        return this.e;
    }
    
    public final boolean d() {
        return this.y == 0;
    }
    
    public final boolean e() {
        return this.y == 3;
    }
    
    private final void q() {
        try {
            if (this.q != null) {
                final Dimension size = this.getSize();
                final int r = this.r();
                if (this.r && !this.v) {
                    if (!this.q.isVisible()) {
                        this.q.setVisible(true);
                    }
                    if (this.e) {
                        ji.util.e.a(this.q, (size.width - r) / 2, 1, r - 2, size.height - 1);
                    }
                    else {
                        ji.util.e.a(this.q, 1, (size.height - r) / 2, size.width, r - 2);
                    }
                }
                else {
                    if (!this.q.isVisible()) {
                        this.q.setVisible(true);
                    }
                    if (this.e) {
                        if (this.d()) {
                            ji.util.e.a(this.q, 1, 1, r - 2, size.height);
                        }
                        else {
                            ji.util.e.a(this.q, 1, 1, r - 2, size.height - 1);
                        }
                    }
                    else {
                        ji.util.e.a(this.q, 1, 1, size.width, r - 2);
                    }
                }
                this.q.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void repaint() {
        super.repaint();
    }
    
    private final void a(final Graphics graphics) {
        try {
            if (ji.util.i.c(2)) {
                final Dimension size = this.getSize();
                this.getBounds();
                Color color = super.getLightShade();
                if (color == null) {
                    if (ji.util.e.h != null) {
                        color = ji.util.e.h;
                    }
                    else {
                        color = SystemColor.controlLtHighlight;
                    }
                }
                Color color2 = super.getDarkShade();
                if (color2 == null) {
                    if (ji.util.e.g != null) {
                        color2 = ji.util.e.g;
                    }
                    else {
                        color2 = SystemColor.controlShadow;
                    }
                }
                if (this.q != null) {
                    final Rectangle bounds = this.q.getBounds();
                    final int width = bounds.width;
                    final int height = bounds.height;
                    if (this.e) {
                        graphics.setColor(color2);
                        graphics.drawLine(bounds.x - 1, 1, bounds.x - 1, size.height - 1);
                        graphics.setColor(color);
                        graphics.drawLine(bounds.x + width, 1, bounds.x + width, size.height - 1);
                    }
                    else {
                        graphics.setColor(color2);
                        graphics.drawLine(1, bounds.y - 1, size.width - 1, bounds.y - 1);
                        graphics.setColor(color);
                        graphics.drawLine(1, bounds.y + height, size.width - 1, bounds.y + height);
                    }
                }
                graphics.setColor(color2);
                if (this.e) {
                    graphics.drawLine(0, 0, size.width - 1, 0);
                    graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
                    graphics.drawLine(0, 0, 0, size.height - 1);
                    if (!this.d() || this.v) {
                        graphics.drawLine(0, size.height - 1, size.width - 1, size.height - 1);
                    }
                }
                else {
                    graphics.drawLine(0, 0, size.width - 1, 0);
                    graphics.drawLine(0, 0, 0, size.height - 1);
                    if (this.e() && this.v) {
                        graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
                    }
                    if (this.v) {
                        graphics.drawLine(0, size.height - 1, size.width - 1, size.height - 1);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final int r() {
        int n = 12;
        if (this.r && !this.v) {
            n *= 5;
        }
        return n;
    }
    
    public final int f() {
        if (this.r) {
            return 10;
        }
        return this.l;
    }
    
    public final int g() {
        if (this.r) {
            return 10;
        }
        return this.l;
    }
    
    public final int h() {
        if (this.r && !this.v) {
            return 10;
        }
        return this.m;
    }
    
    private final void h(final boolean r) {
        try {
            if (this.n == 3 && this.r != r) {
                this.r = r;
                if (this.c != null) {
                    this.b(false);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean i() {
        return this.r;
    }
    
    public final void c(final boolean x) {
        this.x = x;
    }
    
    public final boolean j() {
        return this.x;
    }
    
    public final void d(final boolean w) {
        this.w = w;
    }
    
    public final boolean k() {
        return this.w;
    }
    
    public final void e(final boolean b) {
        this.h(b);
    }
    
    public final void f(final boolean b) {
        this.i(b);
    }
    
    private final void i(boolean v) {
        try {
            if (this.x && !ji.util.d.q(this.ad)) {
                v = false;
            }
            if (this.n == 3 && this.v != v) {
                if (this.v = v) {
                    this.w = false;
                    ji.util.d.a((MouseMotionListener)this.u);
                }
                else {
                    if (!this.q.isEnabled()) {
                        this.q.setEnabled(true);
                    }
                    ji.util.d.b((MouseMotionListener)this.u);
                }
                if (this.c != null) {
                    this.b(false);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean l() {
        return this.v;
    }
    
    public final void setBounds(final Rectangle bounds) {
        super.setBounds(bounds);
        this.m();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.m();
    }
    
    public final void m() {
        this.s();
    }
    
    private final void s() {
        final int b = this.b(false);
        final int b2 = this.b(true);
        if (b > 0) {
            this.l = b;
        }
        if (b2 > 0) {
            this.m = b2;
        }
        this.q();
    }
    
    public final void releaseResources() {
        this.af = null;
        if (!ji.util.d.ao(this.ad)) {
            this.removeNotify();
        }
        try {
            if (this.t != null) {
                this.t.c();
                this.t = null;
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.u != null) {
                this.removeMouseListener(this.u);
                this.u.a();
                this.u = null;
            }
        }
        catch (Exception ex3) {}
        try {
            this.c = null;
            if (this.q != null) {
                this.q.releaseResources();
                this.q = null;
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        try {
            super.releaseResources();
        }
        catch (Exception ex4) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.ai) {
            this.b(this.ad);
            super.paint(graphics);
            if (!this.isSwing()) {
                this.a(graphics);
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        if (this.isSwing()) {
            super.update(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.ai) {
            this.b(this.ad);
            super.paintComponent(graphics);
            this.a(graphics);
        }
    }
    
    public final void n() {
        if (!this.ah) {
            this.ah = true;
            this.repaint();
        }
    }
    
    public void g(final boolean o) {
        this.o = o;
    }
    
    private final void b(final String s) {
        if (this.ah) {
            try {
                if (!this.o && (!this.r || this.v) && this.c != null && this.t() && this.p == null) {
                    ji.util.e.b(this.p = new z2(s, this), s);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    protected final void a(final oi oi) {
        if (this.t != null) {
            final c t = this.t;
            for (int b = t.b(), i = 0; i < b; ++i) {
                ((aj)t.b(i)).a(oi);
            }
        }
    }
    
    private final boolean a(final MouseEvent mouseEvent) {
        boolean b = false;
        try {
            final Rectangle bounds = this.q.getBounds();
            final Point locationOnScreen = this.q.getLocationOnScreen();
            bounds.x = locationOnScreen.x;
            bounds.y = locationOnScreen.x;
            final Point point = mouseEvent.getPoint();
            final Point locationOnScreen2;
            final Point point2 = locationOnScreen2 = ((Component)mouseEvent.getSource()).getLocationOnScreen();
            locationOnScreen2.x += point.x;
            final Point point3 = point2;
            point3.y += point.y;
            if (this.e) {
                if (point2.x < bounds.x || point2.x > bounds.x + bounds.width) {
                    b = true;
                }
            }
            else if (point2.y < bounds.y || point2.y > bounds.y + bounds.height) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private void b(final MouseEvent mouseEvent) {
        try {
            if (this.a(mouseEvent) && this.r && this.v && !this.w) {
                final Point locationOnScreen = ((Component)mouseEvent.getSource()).getLocationOnScreen();
                final Point point = mouseEvent.getPoint();
                final Point point2 = locationOnScreen;
                point2.x += point.x;
                final Point point3 = locationOnScreen;
                point3.y += point.y;
                final Point locationOnScreen2 = this.getLocationOnScreen();
                final Dimension size = this.getSize();
                if (!new Rectangle(locationOnScreen2.x, locationOnScreen2.y, size.width, size.height).contains(locationOnScreen)) {
                    this.a(new oi(this, 4, mouseEvent.getModifiers()));
                }
                else {
                    this.w = false;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean o() {
        return this.ab;
    }
    
    private void c(final MouseEvent mouseEvent) {
        try {
            if (this.a(mouseEvent) && !this.z && !this.aa && this.r && !this.v && ((Component)mouseEvent.getSource()).equals(this)) {
                this.a(new oi(this, 6, mouseEvent.getModifiers()));
            }
            this.z = false;
        }
        catch (Exception ex) {}
    }
    
    private final boolean t() {
        boolean b = true;
        try {
            Block_5: {
                for (int i = 0; i < this.c.length; ++i) {
                    if (this.c[i].x != null && ji.util.d.by(this.c[i].x.parentId)) {
                        break Block_5;
                    }
                }
                return b;
            }
            b = false;
        }
        catch (Exception ex) {}
        return b;
    }
    
    static {
        ae = new Object();
        cv.aj = false;
    }
    
    class t0 extends bz
    {
        adf a;
        adg b;
        adh c;
        boolean d;
        boolean e;
        boolean f;
        cv g;
        
        public t0(final String s, final cv g) {
            super(s);
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = null;
            this.g = g;
            this.setBorderStyle(2);
            this.d(true);
            this.setBorderStyle(0);
            this.addMouseListener(this.a = new adf());
            this.addFocusListener(this.b = new adg());
            this.addKeyListener(this.c = new adh());
        }
        
        public final void releaseResources() {
            this.g = null;
            try {
                if (this.a != null) {
                    this.removeMouseListener(this.a);
                    this.a = null;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.b != null) {
                    this.removeFocusListener(this.b);
                    this.b = null;
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.c != null) {
                    this.removeKeyListener(this.c);
                    this.c = null;
                }
            }
            catch (Exception ex3) {}
            super.releaseResources();
        }
        
        public void repaint() {
            super.repaint();
        }
        
        public void paint(final Graphics graphics) {
            if (this.a()) {
                super.paint(graphics);
            }
            else {
                this.paintComponent(graphics);
            }
        }
        
        public void paintComponent(final Graphics graphics) {
            try {
                final Dimension size = this.getSize();
                final Color a0 = ji.util.e.a0();
                final Color a2 = ji.util.e.a3();
                Color color;
                if (ji.util.e.g != null) {
                    color = ji.util.e.g;
                }
                else {
                    color = SystemColor.controlShadow;
                }
                Color color2;
                if (ji.util.e.h != null) {
                    color2 = ji.util.e.h;
                }
                else {
                    color2 = SystemColor.controlLtHighlight;
                }
                if (cv.this.v) {
                    color2 = color.brighter();
                }
                if (this.f) {
                    graphics.setColor(color);
                }
                else {
                    graphics.setColor(color2);
                }
                graphics.drawLine(0, 0, 0, size.height);
                graphics.drawLine(0, 0, size.width - 1, 0);
                if (this.f) {
                    graphics.setColor(color2);
                }
                else {
                    graphics.setColor(color);
                }
                graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
                if (!cv.this.d()) {
                    graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
                }
                graphics.setColor(color);
                boolean s = cv.this.s;
                boolean s2 = cv.this.s;
                if (cv.this.r && !cv.this.v) {
                    s2 = !s2;
                }
                if (cv.this.r && !cv.this.v) {
                    s = !s;
                }
                if (this.e && !cv.this.v) {
                    s = !s;
                }
                final Color black = Color.black;
                Color color3;
                Color color4;
                if (s) {
                    color3 = a2;
                    color4 = a0;
                }
                else {
                    color3 = a0;
                    color4 = a2;
                }
                final Rectangle bounds = this.getBounds();
                bounds.x = 1;
                bounds.y = 1;
                if (cv.this.e) {
                    final Rectangle rectangle = bounds;
                    rectangle.width -= 2;
                }
                else {
                    final Rectangle rectangle2 = bounds;
                    --rectangle2.width;
                }
                final Rectangle rectangle3 = bounds;
                --rectangle3.height;
                if (!cv.this.d()) {
                    if (cv.this.e) {
                        if (cv.this.r) {
                            final Rectangle rectangle4 = bounds;
                            --rectangle4.height;
                        }
                    }
                    else {
                        final Rectangle rectangle5 = bounds;
                        --rectangle5.height;
                    }
                }
                if (cv.this.r && !cv.this.v) {
                    if (cv.this.e) {
                        if (s) {
                            final Rectangle rectangle6 = bounds;
                            ++rectangle6.height;
                        }
                        else {
                            final Rectangle rectangle7 = bounds;
                            --rectangle7.y;
                            final Rectangle rectangle8 = bounds;
                            ++rectangle8.height;
                        }
                    }
                    else if (s) {
                        final Rectangle rectangle9 = bounds;
                        ++rectangle9.width;
                    }
                    else {
                        final Rectangle rectangle10 = bounds;
                        --rectangle10.x;
                        final Rectangle rectangle11 = bounds;
                        ++rectangle11.width;
                    }
                }
                if (ji.util.d.ar()) {
                    final Rectangle rectangle12 = bounds;
                    --rectangle12.width;
                    final Rectangle rectangle13 = bounds;
                    --rectangle13.height;
                    ji.util.d.a(graphics, bounds, color3, color4, cv.this.e);
                }
                else {
                    if (this.e && ji.util.d.a8()) {
                        graphics.setColor(a0);
                    }
                    else {
                        graphics.setColor(this.getBackground());
                    }
                    graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height - 1);
                }
                graphics.setColor(black);
                if (cv.this.e) {
                    final int n = size.width - 2;
                    final int n2 = size.height - 2 - 1;
                    final int n3 = 1;
                    int i = 12 - 6 * n3;
                    final int n4 = size.width / 2 - 1;
                    if (this.e) {
                        a0.brighter().brighter();
                    }
                    if (s2) {
                        int n5 = n4 - i / 2;
                        int n6 = n3 + 1;
                        while (i >= 0) {
                            graphics.drawLine(n5, n6, n5 + i, n6);
                            ++n6;
                            ++n5;
                            i -= 2;
                        }
                    }
                    else {
                        int n7 = n4 - i / 2;
                        int n8 = size.height - n3 - 3;
                        while (i >= 0) {
                            graphics.drawLine(n7, n8, n7 + i, n8);
                            --n8;
                            ++n7;
                            i -= 2;
                        }
                    }
                }
                else {
                    final int n9 = 1;
                    int j = 12 - 6 * n9;
                    final int n10 = size.height / 2 - 1;
                    if (this.e) {
                        a0.brighter().brighter();
                    }
                    if (s2) {
                        int n11 = n9 + 1;
                        int n12 = n10 - j / 2;
                        while (j >= 0) {
                            graphics.drawLine(n11, n12, n11, n12 + j);
                            ++n12;
                            ++n11;
                            j -= 2;
                        }
                    }
                    else {
                        int n13 = size.width - n9 - 3;
                        int n14 = n10 - j / 2;
                        while (j >= 0) {
                            graphics.drawLine(n13, n14, n13, n14 + j);
                            ++n14;
                            --n13;
                            j -= 2;
                        }
                    }
                }
                if (cv.this.v) {
                    graphics.setColor(color);
                    if (cv.this.e) {
                        graphics.drawLine(0, size.height - 2, size.width - 1, size.height - 2);
                    }
                    else {
                        graphics.drawLine(size.width - 2, 0, size.width - 2, size.height - 1);
                    }
                }
                if (this.d) {
                    graphics.setColor(this.getForeground());
                    ji.util.d.c(graphics, 1, 1, size.width - 3, size.height - 3);
                }
            }
            catch (Exception ex) {}
            ji.util.d.ew();
        }
        
        private final void a(final int n) {
            if (cv.this.r && !cv.this.v) {
                cv.this.a(new oi(this.g, 2, n));
            }
            else {
                cv.this.a(new oi(this.g, 1, n));
            }
        }
        
        class adf implements MouseListener
        {
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                t0.this.f = true;
                t0.this.repaint();
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                t0.this.f = false;
                t0.this.repaint();
                if (t0.this.e) {
                    if (cv.this.r && cv.this.v) {
                        if (cv.this.r) {
                            cv.this.w = false;
                            cv.this.z = true;
                            cv.this.a(new oi(t0.this.g, 2, mouseEvent.getModifiers()));
                        }
                    }
                    else {
                        t0.this.a(mouseEvent.getModifiers());
                    }
                }
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                cv.this.aa = false;
                t0.this.e = true;
                t0.this.repaint();
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                cv.this.aa = false;
                t0.this.e = false;
                t0.this.repaint();
            }
        }
        
        class adg implements FocusListener
        {
            public void focusGained(final FocusEvent focusEvent) {
                t0.this.d = true;
                t0.this.repaint();
            }
            
            public void focusLost(final FocusEvent focusEvent) {
                t0.this.d = false;
                t0.this.repaint();
            }
        }
        
        class adh implements KeyListener
        {
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    t0.this.a(keyEvent.getModifiers());
                }
            }
            
            public void keyReleased(final KeyEvent keyEvent) {
            }
            
            public void keyTyped(final KeyEvent keyEvent) {
            }
        }
    }
    
    class z2 implements Runnable
    {
        String a;
        cv b;
        
        public z2(final String a, final cv b) {
            this.a = null;
            this.b = null;
            this.a = a;
            this.b = b;
        }
        
        public void run() {
            if (ji.util.i.c(36)) {
                ji.io.h.d(this.a, "Toolbar: load resource synchronizing");
            }
            synchronized (cv.ae) {
                if (ji.util.i.c(36)) {
                    ji.io.h.d(this.a, "Toolbar: load resource synchronized");
                }
                try {
                    cv.aj = true;
                    boolean b = false;
                    for (int i = 0; i < cv.this.c.length; ++i) {
                        if (cv.this.c[i].x != null) {
                            if (ji.util.d.by(cv.this.c[i].x.parentId)) {
                                b = true;
                                break;
                            }
                            if (!cv.this.c[i].d) {
                                this.b.add(cv.this.c[i].x);
                                cv.this.c[i].d = true;
                            }
                            cv.this.c[i].x.setImageFileNow();
                            cv.this.c[i].x.setImageFileDisabledNow();
                        }
                    }
                    if (!b) {
                        cv.this.o = true;
                    }
                }
                catch (Exception ex) {}
                finally {
                    cv.this.p = null;
                    cv.aj = false;
                    this.b = null;
                }
                if (ji.util.i.c(36)) {
                    ji.io.h.d(this.a, "Toolbar: load resource end synchronized");
                }
            }
            // monitorexit(cv.ae)
        }
    }
    
    class t3 implements MouseListener, MouseMotionListener
    {
        cv a;
        
        public t3(final cv a) {
            this.a = null;
            this.a = a;
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            try {
                if (cv.this.v) {
                    cv.this.b(mouseEvent);
                }
            }
            catch (Exception ex) {}
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (cv.this.a(mouseEvent)) {}
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            try {
                cv.this.ab = true;
                cv.this.aa = false;
                cv.this.c(mouseEvent);
            }
            catch (Exception ex) {}
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            cv.this.ab = false;
            cv.this.aa = false;
            if (cv.this.a(mouseEvent)) {
                cv.this.z = false;
            }
        }
        
        public final void a() {
            this.a = null;
        }
    }
}

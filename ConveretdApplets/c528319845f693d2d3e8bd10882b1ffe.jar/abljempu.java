import ABLwidgets.utils;
import ABLwidgets.new_font;
import java.awt.Insets;
import ABLwidgets.font_metrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Event;
import java.awt.Rectangle;
import ABLwidgets.edge;
import java.awt.Color;
import java.awt.Window;
import java.awt.MenuItem;
import ABLwidgets.tab;
import java.awt.Component;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljempu extends Canvas
{
    public Component a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public tab f;
    public int g;
    public int h;
    public int i;
    public int j;
    public Component k;
    private abljema l;
    private boolean m;
    private abljemfs n;
    private String o;
    private boolean p;
    private MenuItem[] q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int[] v;
    public int w;
    private int x;
    private Window y;
    private int z;
    private int aa;
    private int ab;
    private boolean ac;
    private int ad;
    private Color ae;
    private Color af;
    private Color ag;
    private Color ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private Color am;
    private Color an;
    private Color ao;
    private Color ap;
    
    public abljempu(final abljema l, final Window y, final int n, final Component k) {
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.j = 1;
        this.m = false;
        this.p = true;
        this.z = -1;
        this.aa = -1;
        this.ab = 7;
        this.ac = false;
        this.ae = new Color(191, 191, 191);
        this.af = new Color(0, 0, 0);
        this.ag = new Color(0, 0, 127);
        this.ah = new Color(255, 255, 255);
        this.ai = 15;
        this.ak = 10;
        this.al = 10;
        this.am = new Color(223, 223, 223);
        this.an = Color.white;
        this.ao = new Color(127, 127, 127);
        this.ap = Color.black;
        this.l = l;
        this.n = new abljemfs(l, this, true);
        this.hide();
        this.y = y;
        this.k = k;
        this.setBackground(this.ae);
        y.add(this, n);
    }
    
    public abljempu(final abljema abljema, final Window window, final int n) {
        this(abljema, window, n, window);
    }
    
    public abljempu(final abljema abljema, final Window window, final Component component) {
        this(abljema, window, 0, component);
    }
    
    public void a(final MenuItem menuItem, final int n) {
        this.a(menuItem, n, 1);
    }
    
    public void a(final MenuItem menuItem) {
        this.a(menuItem, -1);
    }
    
    private void a(final MenuItem menuItem, int n, final int n2) {
        final int w = this.w;
        if (n == -1) {
            n = this.w;
        }
        else {
            n = this.c(n, this.w);
        }
        this.w += n2;
        final MenuItem[] q = new MenuItem[this.w];
        int n3 = 0;
        int i;
        for (i = 0; i < n; ++i) {
            q[n3] = this.q[i];
            ++n3;
        }
        if (n2 == 1) {
            q[n3++] = menuItem;
        }
        else if (n2 == -1) {
            ++i;
        }
        while (i < w) {
            q[n3] = this.q[i];
            ++n3;
            ++i;
        }
        this.q = q;
    }
    
    public void a() {
        this.w = 0;
        this.q = null;
    }
    
    public void a(final Color ao) {
        super.setBackground(this.ae = ao);
        if (this.k != this.y) {
            this.am = Color.black;
            this.an = ao;
            this.ao = ao;
            this.ap = Color.black;
        }
    }
    
    private void a(final Color ap, final edge edge) {
        super.setBackground(this.ae = ap);
        this.am = ap;
        this.an = ap;
        this.ao = ap;
        this.ap = ap;
        if (edge != null) {
            this.am = edge.f;
            this.an = edge.g;
            this.ao = edge.i;
            this.ap = edge.h;
        }
    }
    
    public void enable() {
        super.enable();
        this.n.b();
    }
    
    public void disable() {
        if (!this.n.c(this.m)) {
            return;
        }
        super.disable();
    }
    
    public void show() {
        super.show();
        this.n.a();
    }
    
    public void a(final Object o, final int n, final int n2) {
        this.a((Component)o, n, n2);
    }
    
    public void a(Component parent, int n, int n2) {
        for (int n3 = 0; n3 < 100 && parent != this.y; ++n3) {
            final Rectangle bounds = parent.bounds();
            n += bounds.x;
            n2 += bounds.y;
            parent = parent.getParent();
            if (parent == null) {
                n = 0;
                n2 = 0;
                break;
            }
        }
        this.a(n, n2);
    }
    
    public void a(final int r, final int s) {
        this.r = r;
        this.s = s;
        this.z = -1;
        this.aa = -1;
        this.ad = 0;
        this.reshape(r, s, 1, 1);
        super.show();
        this.requestFocus();
    }
    
    public void hide() {
        this.a((this.o == null) ? "Hide" : this.o);
        this.o = null;
    }
    
    private void a(final String o) {
        if (this.y == null) {
            super.hide();
            return;
        }
        if (!this.n.b(this.m)) {
            this.o = o;
            return;
        }
        this.p = true;
        this.ac = false;
        super.hide();
        this.repaint();
        final Event event = new Event(this, 1005, o);
        if (this.a != null) {
            this.a.action(event, event.arg);
        }
        else if (this.k == this.y) {
            this.k.requestFocus();
        }
        else if (this.k != this.y) {
            this.k.action(event, event.arg);
        }
    }
    
    public MenuItem a(final Event event) {
        final int b = this.b(event.x, event.y);
        if (b != -1) {
            return this.q[b];
        }
        return null;
    }
    
    private int b(final int n, final int n2) {
        if (this.w < 1) {
            return -1;
        }
        try {
            for (int i = 0; i < this.w; ++i) {
                if (this.v[i] - this.ai <= n2 && n2 < this.v[i] + this.x - this.ai) {
                    this.z = i;
                    this.repaint();
                    return i;
                }
            }
        }
        catch (Exception ex) {}
        return this.z = -1;
    }
    
    private int c(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    public void doLayout() {
        if (this.f == null) {
            return;
        }
        final Font o = this.f.o;
        final Rectangle bounds = this.f.getBounds();
        int n = this.f.m;
        if (n < 0) {
            n = this.f.n;
        }
        if (n < 0) {
            n = 0;
        }
        final int n2 = bounds.height - 1;
        final int n3 = n + bounds.x;
        final int n4 = n2 + bounds.y;
        if (this.isVisible() && this.t == n3 && this.u == n4 && this.getFont().equals(o)) {
            return;
        }
        this.setFont(o);
        this.a(n3, n4, 1, 1, 0);
        this.a(this.f.s, this.f.k);
        this.af = this.f.t;
        this.ah = this.f.w;
        this.ag = this.f.v;
        if (this.isVisible()) {
            this.repaint();
        }
        else {
            this.show();
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int aj) {
        this.aj = aj;
        this.b = true;
        this.c = true;
        this.a(n, n2, n3, n4);
    }
    
    public void a(final int n, final int n2, final int g, final int h) {
        this.r = n;
        this.s = n2;
        this.reshape(this.t = n, this.u = n2, this.g = g, this.h = h);
        this.d = true;
    }
    
    public boolean a(Graphics graphics) {
        boolean b = true;
        int n = 0;
        int n2 = 4;
        if (graphics == null) {
            graphics = this.getGraphics();
        }
        final font_metrics a = font_metrics.a(graphics.getFontMetrics(), this.l.fd);
        final int i = a.i;
        final int h = a.h;
        final int d = a.d;
        this.v = new int[this.w];
        this.ab = h - i - d - 1;
        if (this.k != this.y && this.a == null) {
            this.ab = 0;
        }
        else if (this.c) {
            this.ab = (d + h + i) / 4;
        }
        this.ai = h;
        this.x = d + h + i + this.ab;
        if (this.k == this.y) {
            n2 += 4;
        }
        if (this.c) {
            n2 = 4;
        }
        for (int j = 0; j < this.w; ++j) {
            if (this.q[j].getLabel().equals("-")) {
                this.v[j] = n2;
                n2 += 9;
            }
            else {
                this.v[j] = n2 + this.ai;
                n2 += this.x;
            }
            final int a2 = a.a(this.q[j].getLabel());
            if (a2 > n) {
                n = a2;
            }
        }
        this.ak = ((this.aj > 0) ? this.aj : ((this.i == 0) ? 10 : 2));
        this.al = this.ak;
        int g = n + this.ak + this.al;
        final int h2 = n2 + 2;
        if (g < this.i) {
            final int n3 = this.i - g;
            final int n4 = n3 / 2;
            this.ak += n4;
            this.al += n3 - n4;
            g += n3;
        }
        this.t = this.r;
        this.u = this.s;
        this.g = g;
        this.h = h2;
        if (this.j == 2 || this.j == 5 || this.j == 8) {
            this.t -= g / 2;
        }
        if (this.j == 3 || this.j == 6 || this.j == 9) {
            this.t -= g;
        }
        if (this.j == 4 || this.j == 5 || this.j == 6) {
            this.u -= h2 / 2;
        }
        if (this.j == 7 || this.j == 8 || this.j == 9) {
            this.u -= h2;
        }
        final Rectangle bounds = this.y.bounds();
        final Insets insets = this.y.insets();
        if (insets.top < 0) {
            insets.top = 0;
        }
        if (insets.left < 0) {
            insets.left = 0;
        }
        if (insets.bottom < 0) {
            insets.bottom = 0;
        }
        if (insets.right < 0) {
            insets.right = 0;
        }
        if (this.t + this.g > bounds.width - insets.right) {
            this.t = bounds.width - insets.right - this.g;
        }
        if (this.u + this.h > bounds.height - insets.bottom) {
            this.u = bounds.height - insets.bottom - this.h;
        }
        if (this.t < insets.left && this.t < this.r) {
            this.t = insets.left;
        }
        if (this.u < insets.top && this.u < this.s) {
            this.u = insets.top;
        }
        if (this.t + this.g > bounds.width - insets.right) {
            b = false;
        }
        if (this.u + this.h > bounds.height - insets.bottom) {
            b = false;
        }
        return b;
    }
    
    public void paint(final Graphics graphics) {
        if (this.ac) {
            this.p = false;
        }
        graphics.setFont(this.getFont());
        if (this.e) {
            this.a(graphics);
        }
        else {
            for (int size = graphics.getFont().getSize(); size > 2 && !this.a(graphics); --size) {
                graphics.setFont(new_font.a("Helvetica", 0, size));
            }
        }
        final int g = this.g;
        this.reshape(this.t, this.u, g, this.h);
        final Rectangle bounds = this.bounds();
        graphics.setColor(this.am);
        if (!this.b) {
            graphics.drawLine(0, 0, bounds.width - 1, 0);
        }
        graphics.drawLine(0, 0, 0, bounds.height - 1);
        graphics.setColor(this.an);
        if (!this.b) {
            graphics.drawLine(1, 1, bounds.width - 1, 1);
        }
        if (!this.b) {
            graphics.drawLine(1, 1, 1, bounds.height - 1);
        }
        else {
            graphics.drawLine(1, 0, 1, bounds.height);
        }
        graphics.setColor(this.ao);
        graphics.drawLine(1, bounds.height - 2, bounds.width - 2, bounds.height - 2);
        if (!this.b) {
            graphics.drawLine(bounds.width - 2, 1, bounds.width - 2, bounds.height - 2);
        }
        else {
            graphics.drawLine(bounds.width - 2, 0, bounds.width - 2, bounds.height - 1);
        }
        graphics.setColor(this.ap);
        graphics.drawLine(0, bounds.height - 1, bounds.width - 1, bounds.height - 1);
        graphics.drawLine(bounds.width - 1, 0, bounds.width - 1, bounds.height - 1);
        for (int i = 0; i < this.w; ++i) {
            final String label = this.q[i].getLabel();
            if (label.equals("-")) {
                graphics.setColor(new Color(127, 127, 127));
                graphics.drawLine(3, this.v[i], g - 4, this.v[i]);
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawLine(3, this.v[i] + 1, g - 4, this.v[i] + 1);
            }
            else if (!this.q[i].isEnabled()) {
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawString(label, this.ak + 1, this.v[i] + 1);
                graphics.setColor(new Color(127, 127, 127));
                graphics.drawString(label, this.ak, this.v[i]);
            }
            else if (this.z == i) {
                if (this.aa != i || (i == 0 && this.ad <= 5)) {
                    utils.a(graphics, this.ag, 3, this.v[i] - this.ai - this.ab / 2, g - 6, this.x);
                    graphics.setColor(this.ah);
                    graphics.drawString(label, this.ak, this.v[i]);
                    ++this.ad;
                }
            }
            else if (this.aa == i) {
                utils.a(graphics, this.ae, 3, this.v[i] - this.ai - this.ab / 2, g - 6, this.x);
                graphics.setColor(this.af);
                graphics.drawString(label, this.ak, this.v[i]);
            }
            else {
                graphics.setColor(this.af);
                graphics.drawString(label, this.ak, this.v[i]);
            }
        }
        this.aa = this.z;
    }
    
    public void update(final Graphics graphics) {
        if (this.ae != null && (this.l.cn || !this.getBackground().equals(this.ae))) {
            this.setBackground(this.ae);
        }
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.arg instanceof String && ((String)event.arg).equals("show")) {
            this.a(event.target, event.x, event.y);
            return true;
        }
        if (event.id == 201 || event.id == 1005 || event.id == 203) {
            this.m = false;
            if (!this.p && !this.d) {
                this.hide();
            }
        }
        else if (event.id == 1004) {
            if (this.y instanceof abljemf) {
                ((abljemf)this.y).d8 = true;
            }
            this.m = true;
            this.ac = true;
        }
        else if (event.id == 503 || event.id == 506) {
            if (this.isVisible()) {
                this.requestFocus();
                this.b(event.x, event.y);
                this.repaint();
            }
        }
        else if (event.id == 504) {
            if (this.isVisible()) {
                this.requestFocus();
            }
        }
        else if (event.id == 505) {
            this.z = -1;
            this.repaint();
        }
        else if (event.id == 401 && this.isVisible() && this.z >= 0) {
            if (event.key == 10 || event.key == 32) {
                event.x = this.ak;
                event.y = this.v[this.z] - this.ai;
                return this.b(event);
            }
        }
        else if (event.id == 403 && this.isVisible()) {
            if (event.key == 1004) {
                final int z = this.z;
                --this.z;
                if (this.z < 0) {
                    this.z = this.w - 1;
                }
                while (this.q[this.z].getLabel().equals("-") || !this.q[this.z].isEnabled()) {
                    --this.z;
                    if (this.z < 0) {
                        this.z = this.w - 1;
                    }
                    if (this.z == z) {
                        break;
                    }
                }
                this.repaint();
            }
            else if (event.key == 1005) {
                final int z2 = this.z;
                ++this.z;
                if (this.z >= this.w) {
                    this.z = 0;
                }
                while (this.q[this.z].getLabel().equals("-") || !this.q[this.z].isEnabled()) {
                    ++this.z;
                    if (this.z >= this.w) {
                        this.z = 0;
                    }
                    if (this.z == z2) {
                        break;
                    }
                }
                this.repaint();
            }
        }
        else {
            if (event.id == 501) {
                return true;
            }
            if (event.id == 502 && !this.p) {
                return this.b(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public void requestFocus() {
        super.requestFocus();
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.isVisible() && n == 27) {
            this.a("Escape");
            return true;
        }
        return super.keyDown(event, n);
    }
    
    private boolean b(final Event event) {
        final int b = this.b(event.x, event.y);
        if (b == -1) {
            return true;
        }
        if (!this.q[b].getLabel().equals("-") && this.q[b].isEnabled()) {
            this.a("Action");
            return this.k.action(event, event.arg);
        }
        return true;
    }
}

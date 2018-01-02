import com.daysofwonder.tt.n;
import com.daysofwonder.tt.c;
import com.daysofwonder.tt.d;
import java.awt.image.ImageObserver;
import java.util.Hashtable;
import java.awt.Image;
import com.daysofwonder.applet.y;
import com.daysofwonder.applet.aF;
import com.daysofwonder.tt.e;
import com.daysofwonder.tt.f;
import java.util.Vector;
import com.daysofwonder.tt.o;
import com.daysofwonder.applet.at;
import com.daysofwonder.applet.aG;
import com.daysofwonder.applet.ap;
import com.daysofwonder.applet.aC;
import com.daysofwonder.req.x;
import com.daysofwonder.req.H;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import com.daysofwonder.util.t;
import com.daysofwonder.applet.am;
import com.daysofwonder.applet.U;
import com.daysofwonder.applet.h;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import com.daysofwonder.applet.J;

// 
// Decompiled by Procyon v0.5.30
// 

public class S extends J implements ActionListener, KeyListener, MouseListener, MouseMotionListener
{
    protected G a;
    protected z b;
    protected boolean c;
    protected h d;
    protected U e;
    protected D f;
    protected am g;
    protected am h;
    protected am i;
    protected am j;
    protected am k;
    protected am l;
    protected am m;
    protected am n;
    protected am o;
    protected am p;
    protected am q;
    protected as r;
    
    public S() {
    }
    
    public S(final G a, final z b) {
        this.a = a;
        this.b = b;
        b.p().addMouseListener(this);
        b.p().addMouseMotionListener(this);
        b.p().addKeyListener(this);
        this.a.a(this);
    }
    
    public void c() {
        this.b.p().removeMouseListener(this);
        this.b.p().removeMouseMotionListener(this);
        this.b.p().removeKeyListener(this);
        this.f = null;
        this.b = null;
        this.a = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
    }
    
    public void a(final String s, final int n, final int n2) {
        if (this.b != null) {
            this.b.w();
        }
        if (this.d == null && this.b != null) {
            this.d = (h)this.b.b("chat");
        }
        if (this.d != null) {
            this.d.a(s, n);
            this.d.v();
        }
    }
    
    public void a(final String s, final String s2, final int n) {
        if (this.b != null) {
            this.b.w();
        }
        if (this.d == null && this.b != null) {
            this.d = (h)this.b.b("chat");
        }
        if (this.d != null && s2 != null) {
            this.d.a(s, s2);
            this.d.v();
        }
    }
    
    public void a(final int n, final Object[] array) {
        this.b.w();
        if (this.e == null) {
            this.e = (U)this.b.b("help");
        }
        if (n == 11 || n == 12 || n == 21) {
            array[0] = this.b.R().b("map." + (String)array[0]);
        }
        this.e.a(this.b.a(n, array));
        this.e.v();
    }
    
    public void d() {
        if (!this.c) {
            this.b.A();
        }
    }
    
    public void b(final int n, final Object[] array) {
        System.out.println("--> alert " + n);
        this.b.b(n, array);
    }
    
    public void a() {
        System.out.println("fireStateChange");
        this.b.w();
        if (this.g == null) {
            this.g = this.b.b("ticketchooser");
        }
        if (this.p == null) {
            this.p = this.b.b("tunnel");
        }
        if (this.q == null) {
            this.q = this.b.b("colorcardchooser");
        }
        if (this.h == null) {
            this.h = this.b.b("miniticketchooser");
        }
        if (this.i == null) {
            this.i = this.b.b("other");
        }
        if (this.e == null) {
            this.e = (U)this.b.b("help");
        }
        if (this.k == null) {
            this.k = this.b.b("uphelp");
        }
        if (this.l == null) {
            this.l = this.b.b("downhelp");
        }
        if (this.r == null) {
            this.r = (as)this.b.b("start");
        }
        if (this.d == null) {
            this.d = (h)this.b.b("chat");
        }
        if (this.m == null) {
            this.m = this.b.b("upchat");
        }
        if (this.n == null) {
            this.n = this.b.b("downchat");
        }
        if (this.o == null) {
            this.o = this.b.b("send");
        }
        if (this.r != null) {
            this.r.a((this.a.aF() && this.a.j()) || this.a.x() || this.a.y() || this.a.k());
        }
        if (this.j == null) {
            this.j = this.b.b("score");
        }
        if (this.j != null) {
            this.j.a(this.a.s());
        }
        if (this.a.w() || this.a.t()) {
            final am b = this.b.b("leave");
            if (b != null) {
                b.a(false);
            }
        }
        if (this.g != null) {
            final boolean q = this.a.q();
            final boolean b2 = this.a.l() || this.a.m() || this.a.n();
            final boolean z = this.a.z();
            t.a("res: " + q + " res2: " + b2 + " res3 " + z);
            if (q) {
                t.a("presented: " + this.a.I());
                if (this.a.I() != null && this.a.I().a() > 3) {
                    this.g.a(q);
                    this.h.a(!q);
                    this.d.a(!q);
                    this.m.a(!q);
                    this.n.a(!q);
                    this.o.a(!q);
                    if (this.b instanceof s) {
                        ((s)this.b).a(!q);
                    }
                }
                else {
                    t.a("miniticket");
                    this.h.a(q);
                    this.g.a(!q);
                }
            }
            else {
                this.g.a(q);
                this.h.a(q);
                this.d.a(!q);
                this.m.a(!q);
                this.n.a(!q);
                this.o.a(!q);
                if (this.b instanceof s) {
                    ((s)this.b).a(!q);
                }
            }
            this.p.a(b2);
            this.q.a(z && this.a.ac() != null);
            this.e.a(!q && !b2 && !z);
            this.k.a(!q && !b2 && !z);
            this.l.a(!q && !b2 && !z);
            this.i.a(!q && !b2 && !z);
        }
        this.d();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.b.c(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.b.a(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.b.b(mouseEvent);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.b.d(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.b.e(mouseEvent);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.b.c(keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.b.a(keyEvent);
    }
    
    public void b() {
        ((p)this.b.b("player")).b();
    }
    
    public void a(final H h) {
    }
    
    public void a(final x x) {
    }
    
    public void a(final int n) {
    }
    
    public void a(final aC ac, final int n) {
        if (this.b.T()) {
            t.a("TTController: animation is cancelled, bailing out");
            this.b.S();
        }
        this.b.u();
        ac.a(this.b, this.a);
        this.b.G();
        at.a(ac, n, this.b);
        this.b.v();
    }
    
    public void a(final aC ac, final int n, final boolean b, final boolean b2) {
        this.a(ac, n);
    }
    
    public void a(final o o, final int n) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            this.f.a(o, this.a.f(n));
            this.f.e();
        }
    }
    
    public void a(final o o) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            this.f.a(o);
            this.f.e();
        }
    }
    
    public void a(final Vector vector, final com.daysofwonder.util.G g) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            com.daysofwonder.util.G g2;
            if (vector != null) {
                g2 = new com.daysofwonder.util.G(vector.size());
                for (int i = 0; i < vector.size(); ++i) {
                    g2.c(g.b((int)vector.elementAt(i)));
                }
            }
            else {
                g2 = g;
            }
            this.f.a(g2);
            this.f.e();
        }
    }
    
    public void e() {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            this.f.d();
            this.f.e();
        }
    }
    
    public int a(final e e, final boolean b, final boolean b2, final byte[] array, final aF af, final int n, final int n2) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            t.a("before getImage mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            final Image a = y.a(array);
            t.a("after getImage mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            this.f.a(e, b, b2, a);
            a.flush();
            af.b(n, n2);
        }
        return n;
    }
    
    public int a(final e e, final boolean b, final boolean b2, final Hashtable hashtable, final aF af, int n, final int n2) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            t.a("before getImage mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            final Image a = y.a(hashtable.get("board0.jpg"));
            final int width = a.getWidth(null);
            final int height = a.getHeight(null);
            this.f.a(e, a, 0, 0, width, height);
            a.flush();
            System.gc();
            af.b(n++, n2);
            t.a("after getImage0 mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            final Image a2 = y.a(hashtable.get("board1.jpg"));
            this.f.a(e, a2, width, 0, a2.getWidth(null), a2.getHeight(null));
            a2.flush();
            System.gc();
            af.b(n++, n2);
            t.a("after getImage1 mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            final Image a3 = y.a(hashtable.get("board2.jpg"));
            this.f.a(e, a3, 0, height, a3.getWidth(null), a3.getHeight(null));
            a3.flush();
            System.gc();
            af.b(n++, n2);
            t.a("after getImage2 mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            final Image a4 = y.a(hashtable.get("board3.jpg"));
            this.f.a(e, a4, width, height, a4.getWidth(null), a4.getHeight(null));
            a4.flush();
            System.gc();
            af.b(n++, n2);
            t.a("after getImage3 mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        }
        return n;
    }
    
    public int a(final e e, final boolean b, final boolean b2, final Image image, final aF af, final int n, final int n2) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            this.f.a(e, b, b2, image);
            image.flush();
            af.b(n, n2);
        }
        return n;
    }
    
    public void a(final d d, final int n) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            final com.daysofwonder.util.G i = d.i();
            for (int j = 0; j < i.a(); ++j) {
                this.f.a((o)i.b(j), this.a.f(n));
            }
            this.f.e();
        }
    }
    
    public void b(final d d, final int n) {
        if (this.f == null) {
            this.f = (D)this.b.b("board");
        }
        if (this.f != null) {
            final com.daysofwonder.util.G j = d.j();
            for (int i = 0; i < j.a(); ++i) {
                this.f.a((c)j.b(i), this.a.f(n));
            }
            this.f.e();
        }
    }
    
    public aF f() {
        if (this.r == null) {
            this.r = (as)this.b.b("start");
        }
        return this.r;
    }
    
    public void a(final o o, final boolean b) {
    }
    
    public String b(final String s, final int n) {
        return s;
    }
    
    public void a(final o o, final n n, final n n2) {
        ((s)this.b).a(o, n, n2);
        this.d();
        this.a.ax();
        ((s)this.b).h();
    }
    
    public void a(final String s) {
        if (this.b != null) {
            this.a((s != null) ? 701 : 702, new Object[] { s });
        }
    }
}

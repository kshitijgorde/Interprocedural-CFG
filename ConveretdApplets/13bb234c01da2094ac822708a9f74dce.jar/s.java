import java.awt.MenuItem;
import com.daysofwonder.applet.ap;
import com.daysofwonder.applet.C;
import com.daysofwonder.tt.n;
import com.daysofwonder.tt.o;
import java.awt.event.TextEvent;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import com.daysofwonder.b.a;
import java.awt.Graphics;
import java.awt.Shape;
import com.daysofwonder.tt.d;
import com.daysofwonder.applet.H;
import com.daysofwonder.applet.U;
import com.daysofwonder.applet.h;
import com.daysofwonder.applet.al;
import java.awt.event.FocusEvent;
import com.daysofwonder.tt.i;
import com.daysofwonder.tt.c;
import com.daysofwonder.applet.am;
import java.awt.event.InputEvent;
import com.daysofwonder.applet.r;
import java.awt.Color;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.t;
import com.daysofwonder.applet.y;
import com.daysofwonder.applet.au;
import com.daysofwonder.b.b;
import java.util.LinkedHashMap;
import com.daysofwonder.applet.R;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends z implements ActionListener, TextListener, Runnable
{
    protected volatile int a;
    protected Rectangle b;
    protected Rectangle c;
    protected PopupMenu d;
    protected int e;
    protected String f;
    protected int g;
    protected int h;
    protected R i;
    protected LinkedHashMap j;
    protected Rectangle k;
    protected b l;
    private boolean aW;
    private boolean aX;
    private au aY;
    
    public s(final G g, final au ay) {
        super(g, 798, 597);
        this.a = 0;
        this.j = new ac(this);
        this.aY = ay;
    }
    
    public void a() {
        this.V = com.daysofwonder.applet.y.c(this.d());
        this.W = com.daysofwonder.applet.y.c(this.c());
    }
    
    public void b() {
        com.daysofwonder.util.t.a("TTGameView init");
        this.O = com.daysofwonder.applet.y.a("img/backgame.gif");
        this.e(true);
        this.f(true);
        this.b(false);
        if (G.ae() != null) {
            this.c(!G.ae().p);
        }
        else {
            this.c(true);
        }
        super.b();
        this.e();
        if (!this.m.aA()) {
            this.d = new PopupMenu();
            this.Z();
            this.d.addActionListener(this);
            this.aP.add(this.d);
        }
        this.b = com.daysofwonder.applet.aL.a(this.W, "smallchat.r");
        this.c = com.daysofwonder.applet.aL.a(this.W, "bigchat.r");
        this.i = new R(this.p(), com.daysofwonder.applet.aL.a(this.W, "freechat.r"));
        if (!this.m.aA()) {
            this.i.b(true);
            this.i.c(true);
            this.i.b(new Color(234, 230, 202));
            this.i.a(Color.black);
            this.i.a((ActionListener)this);
            this.i.a((TextListener)this);
            this.g = this.e;
        }
        else {
            this.i.b(false);
            this.i.c(false);
        }
        ((r)this.b("bell")).b(!this.aY.y());
        ((r)this.b("balloon")).b(!this.j());
        this.a("tunnelpay", false);
    }
    
    public String c() {
        if (com.daysofwonder.applet.y.b()) {
            return "ui-" + com.daysofwonder.applet.y.c() + ".properties";
        }
        return "ui.properties";
    }
    
    public String d() {
        if (com.daysofwonder.applet.y.d().equals("en")) {
            return "msg.properties";
        }
        return "msg-" + com.daysofwonder.applet.y.d() + ".properties";
    }
    
    public void e() {
        super.e();
        this.l = com.daysofwonder.applet.y.a("img/chat.gif");
        this.k = com.daysofwonder.applet.aL.a(this.W, "chat.r");
    }
    
    public void a(final Object o, final String s, final InputEvent inputEvent) {
        System.out.println("got acp: " + s);
        if (s != null && this.a == 1 && (s.equals("tunnelpayyes") || s.equals("tunnelpayno"))) {
            this.a = 0;
            this.a("tunnelpay", false);
            final r r = (r)this.b("tunnelpaycb");
            final boolean b = this.m.F().l() && this.m.g().t() > 0;
            final boolean e = r.e();
            final boolean equals = s.equals("tunnelpayyes");
            if (b && e && equals) {
                this.m.e(2);
            }
            if (((b && !e) || !b) && equals) {
                this.m.e(1);
            }
            else if (!equals) {
                this.m.e(0);
            }
            this.m.av();
        }
        else if (s != null && o instanceof am) {
            if (this.ao && s.equals("alert.ok")) {
                this.E();
            }
            else if (this.aw && (s.equals("yesno.yes") || s.equals("yesno.no"))) {
                this.ax = s.equals("yesno.yes");
                if (this.ax && this.U == 1) {
                    this.m.au();
                    com.daysofwonder.util.t.a("aborting animation: " + this.aH + " ab " + this.aI);
                    this.S();
                    com.daysofwonder.util.t.a("aborting animation: " + this.aH + " ab " + this.aI);
                }
                else if (this.ax && this.U == 2) {
                    this.m.G();
                }
                else if (this.U == 3) {
                    if (this.ax) {
                        ((A)this.b("board")).a(this.m.U(), this.m.V());
                        this.m.b(null, null);
                    }
                    else {
                        this.m.g().b(this.m.V());
                        this.m.b(null, null);
                    }
                }
                else if (this.U == 4) {
                    if (this.ax) {
                        this.m.e(1);
                    }
                    else {
                        this.m.e(0);
                    }
                }
                this.ax = false;
                this.U = -1;
                this.E();
            }
            else if (s.equals("magnifier")) {
                final A a = (A)this.b("board");
                if (a.g()) {
                    a.b();
                }
            }
            else if (s.equals("bell")) {
                this.aY.a(!this.aY.y());
            }
            else if (s.equals("balloon")) {
                this.c(!this.j());
                if (G.ae() != null) {
                    G.ae().p = !this.j();
                }
            }
            else if (s.equals("leave.game")) {
                com.daysofwonder.util.t.a("leave game");
                if (this.m.w() || this.m.t()) {
                    this.m.av();
                }
                else {
                    this.a(this.V.b("yesno.leave.title"), this.V.b("yesno.leave.text"), true, 1);
                }
            }
            else if (s.equals("chat.send") && this.f()) {
                if (this.g >= 0 && this.g != this.e) {
                    this.m.m(this.g);
                    this.g = this.e;
                    this.i.a("");
                    this.i.b((FocusEvent)null);
                    final al al = (al)this.b("send");
                    if (al != null) {
                        al.c(this.f());
                    }
                    this.A();
                }
                else if (this.g == this.e) {
                    final String i = this.i.i();
                    if (i != null && !this.j.containsKey(i)) {
                        this.j.put(i, null);
                        this.Z();
                    }
                    this.m.b(i);
                    this.i.a("");
                    this.i.b((FocusEvent)null);
                    this.A();
                }
            }
            else if (s.equals("chat.up")) {
                ((h)this.b("chat")).b_();
            }
            else if (s.equals("chat.down")) {
                ((h)this.b("chat")).c();
            }
            else if (s.equals("help.up")) {
                ((U)this.b("help")).b();
            }
            else if (s.equals("help.down")) {
                ((U)this.b("help")).d();
            }
            else if (s.equals("chooser.done")) {
                final f f = (f)this.b("ticketchooser");
                com.daysofwonder.util.t.a("Chooser.done: " + f.c());
                if (f.h() && f.c().size() > 0) {
                    f.b();
                    this.m.a(f.c());
                }
                else {
                    final f f2 = (f)this.b("miniticketchooser");
                    com.daysofwonder.util.t.a("miniChooser.done: " + f2.c());
                    f2.b();
                    this.m.a(f2.c());
                }
            }
            else if (s.equals("colorchooser.done")) {
                final E e2 = (E)this.b("colorcardchooser");
                com.daysofwonder.util.t.a("colorchooser.done: " + e2.b());
                if (e2.h() && e2.b().size() > 0) {
                    this.m.a((Integer)e2.b().elementAt(0));
                }
            }
            else if (s.equals("startb.action")) {
                this.b("start").a(false);
                this.m.H();
            }
            else if (s.equals("score.action")) {
                this.m.av();
                this.b("score").a(false);
            }
            else if (this.af != null) {
                this.af.a(o, s, inputEvent);
            }
        }
        else if (s != null && s.equals("other") && o instanceof H && this.b("other") != null && o instanceof H) {
            final H h = (H)o;
            final d d = (d)h.a();
            if (d != null && d.w() != 0) {
                switch (h.b()) {
                    case 1: {
                        this.m.a(d);
                        break;
                    }
                    case 2: {
                        this.m.b(d.w(), d.z());
                        break;
                    }
                    case 3: {
                        this.m.a(d.w(), d.z());
                        break;
                    }
                    case 4: {
                        this.m.p(d.w());
                        break;
                    }
                    case 5: {
                        this.m.o(d.w());
                        break;
                    }
                }
            }
        }
    }
    
    public void a(final Shape shape) {
        this.t();
        super.a(shape);
        this.a(this.c);
        this.i.d((Graphics)this.P.h());
    }
    
    public void a(final a a) {
        this.i.d((Graphics)a.h());
    }
    
    public Rectangle a(final a a, final Rectangle rectangle) {
        this.i.d((Graphics)a.h());
        return com.daysofwonder.applet.aL.b(rectangle, this.i.v());
    }
    
    public void a(final MouseEvent mouseEvent) {
        com.daysofwonder.util.t.a("MousePressed state=" + this.m.B());
        if (!this.z()) {
            final Point point = mouseEvent.getPoint();
            if (this.i.n() && this.b.contains(point) && this.d != null) {
                this.d.show(this.aP, point.x, point.y);
            }
            else if (!this.i.n() && this.c.contains(point) && this.d != null && !this.m.q()) {
                this.d.show(this.aP, point.x, point.y);
            }
            else {
                super.a(mouseEvent);
            }
        }
        else {
            super.a(mouseEvent);
        }
    }
    
    public void b(final MouseEvent mouseEvent) {
        if (!this.z()) {
            if (this.i.n() && !this.i.u().contains(mouseEvent.getPoint())) {
                this.i.b((FocusEvent)null);
            }
            if (this.aa == null) {
                if (this.m.u() || this.m.A() || this.m.m() || this.m.v() || this.m.t()) {
                    this.m.av();
                }
                else {
                    super.b(mouseEvent);
                }
                if ((!this.i.n() || !this.i.u().contains(mouseEvent.getPoint())) && this.aH) {
                    this.S();
                }
            }
            else {
                super.b(mouseEvent);
            }
        }
        else {
            super.b(mouseEvent);
        }
    }
    
    public void a(final KeyEvent keyEvent) {
        if (!this.z()) {
            final char keyChar = keyEvent.getKeyChar();
            keyEvent.getKeyCode();
            if (this.i.n() && this.i.a() && keyChar == '\n') {
                this.i.b((FocusEvent)null);
                this.A();
            }
            else if (this.i.n() && !this.i.a() && (keyChar == ':' || keyChar == '/' || Character.isLetterOrDigit(keyChar))) {
                this.i.a((FocusEvent)null);
                this.i.b(keyEvent);
                keyEvent.consume();
            }
            super.a(keyEvent);
        }
        else {
            super.a(keyEvent);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.d) {
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals("freechat")) {
                this.g = this.e;
            }
            else if (actionCommand.startsWith("h.")) {
                this.g = this.h + (Integer.parseInt(actionCommand.substring(2)) - 1);
            }
            else {
                this.g = Integer.valueOf(actionCommand);
                this.f = this.d.getItem(this.g - 1).getLabel();
            }
            if (this.g == this.e && !this.i.n()) {
                this.i.c(true);
                this.i.b(true);
                this.i.a((FocusEvent)null);
            }
            if (this.h > 0 && this.g >= this.h && this.g < this.e) {
                final int n = this.g - this.h;
                int n2 = 0;
                String s = "";
                for (final String s2 : this.j.keySet()) {
                    if (n2 == n) {
                        s = s2;
                    }
                    ++n2;
                }
                this.i.a(s);
                this.i.c(true);
                this.i.b(true);
                this.i.a((FocusEvent)null);
                this.i.e();
                this.g = this.e;
            }
            else if (this.g != this.e && this.i.n()) {
                this.i.c(false);
                this.i.b(false);
                this.i.b((FocusEvent)null);
            }
            ((al)this.b("send")).c(this.f());
            this.A();
        }
        else if (actionEvent.getSource() == this.i && this.i.n() && this.f() && this.i.a()) {
            new ab(this).start();
        }
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        final am b = this.b("send");
        b.c(this.f());
        b.v();
    }
    
    public boolean f() {
        if (this.m.aA()) {
            return false;
        }
        boolean b = false;
        if (this.i.n() && this.i.i().length() > 0) {
            b = true;
        }
        else if (!this.i.n()) {
            b = true;
        }
        return b;
    }
    
    protected void a(final Rectangle rectangle) {
        if (this.f != null) {
            this.P.a(this.f, rectangle.x, rectangle.y + rectangle.height - this.P.d().getMaxDescent() - 1);
        }
    }
    
    public void run() {
        int n = 0;
        int n2 = 8;
        while (this.H()) {
            this.r().d();
            if (n2 <= 0) {
                n2 = 8;
                ++n;
                if (this.am == null && this.aa == null && !this.aH) {
                    this.a(null, n);
                }
            }
            --n2;
        }
    }
    
    public void a(final boolean b) {
        this.i.c(b);
    }
    
    public void g() {
        for (int i = 0; i < this.r.length; ++i) {
            this.r[i].c();
            this.s[i].c();
        }
        this.E();
        super.g();
    }
    
    public void a(final o o, final n n, final n n2) {
        this.a = 1;
        ((C)this.b("tunnelpaytext")).a(this.a(this.V.b("yesno.text.2"), new Object[] { null, o, n, n2 }));
        this.a("tunnelpay", true);
        final boolean b = this.m.F().l() && this.m.g().t() > 0 && n2.g() != n2.d();
        final r r = (r)this.b("tunnelpaycb");
        r.a(b);
        r.c(b);
        r.b(b);
        this.b("tunnelpaycbtext").a(b);
    }
    
    public void h() {
        if (this.a == 1) {
            this.a = 0;
            this.a("tunnelpay", false);
            this.m.e(0);
        }
    }
    
    public boolean i() {
        return this.aW;
    }
    
    public void b(final boolean aw) {
        this.aW = aw;
    }
    
    public am a(final am am, final String s, final Point point) {
        if (this.X && this.j() && s != null) {
            final com.daysofwonder.applet.A a = new com.daysofwonder.applet.A(this, "tooltip", this.W, this.V);
            final Point e = am.e(point);
            if (e != null) {
                int x = e.x;
                if (x + a.p().width > this.D.width) {
                    x = this.D.width - 10 - a.p().width;
                }
                int y = e.y;
                if (y + a.p().height > this.D.height) {
                    y = this.D.height - 10 - a.p().height;
                }
                a.a(x, y);
                a.a(true);
                a.a(s);
                return a;
            }
        }
        return null;
    }
    
    private void Z() {
        this.d.removeAll();
        int n = 1;
        for (String s = this.V.b("chat.1"); s != null; s = this.V.b("chat." + n)) {
            final MenuItem menuItem = new MenuItem(s);
            menuItem.setActionCommand(Integer.toString(n));
            this.d.add(menuItem);
            ++n;
        }
        this.d.addSeparator();
        ++n;
        if (this.j.size() > 0) {
            this.h = n - 1;
            for (String string : this.j.keySet()) {
                if (string.length() > 20) {
                    string = string.substring(0, Math.min(string.length(), 20)) + "...";
                }
                final MenuItem menuItem2 = new MenuItem(string);
                menuItem2.setActionCommand("h." + (n - this.h));
                this.d.add(menuItem2);
                ++n;
            }
            this.d.addSeparator();
            ++n;
        }
        final MenuItem menuItem3 = new MenuItem(this.V.b("chat.freechat"));
        menuItem3.setActionCommand("freechat");
        this.d.add(menuItem3);
        if (this.g == this.e) {
            this.g = n - 1;
        }
        this.e = n - 1;
    }
    
    public boolean j() {
        return this.aX;
    }
    
    public void c(final boolean ax) {
        this.aX = ax;
    }
}

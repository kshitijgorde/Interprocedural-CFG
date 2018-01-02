import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Cursor;
import java.util.BitSet;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class ae extends Component implements ActionListener, MouseListener, MouseMotionListener
{
    public d a;
    public final Object b;
    public q c;
    public final int d;
    public int e;
    public int f;
    public Vector g;
    public Font h;
    public Color i;
    public Color j;
    public boolean k;
    public int l;
    public Vector m;
    public String n;
    public String o;
    public Vector p;
    public w q;
    public BitSet r;
    
    public ae(final d a, final q c, final int d, final int e) {
        this.b = new Object();
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = e;
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.a(1);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
    }
    
    public void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        synchronized (this.b) {
            graphics.setColor(this.j);
            graphics.fillRect(0, 0, size.width, size.height);
            final int n;
            if ((n = (this.k ? 2 : 1) * this.l + 0 * this.l) > 0) {
                graphics.setColor(this.i);
                graphics.fillRect(0, 0, size.width, n);
                graphics.fillRect(0, 0, n, size.height);
                graphics.fillRect(Math.max(0, size.width - n), 0, n, size.height);
                graphics.fillRect(0, Math.max(0, size.height - n), size.width, n);
                size.width = Math.max(0, size.width - 2 * n);
                size.height = Math.max(0, size.height - 2 * n);
                size.width = Math.max(0, size.width - 2);
                size.height = Math.max(0, size.height - 2);
            }
            if (size.width > 0 && size.height > 0 && this.r != null && this.g != null) {
                this.a.d();
                graphics.setColor(f.a(this.j));
                final int n2 = n + 1;
                int n3 = 0;
                for (int i = 0; i < this.r.size(); ++i) {
                    if (this.r.get(i)) {
                        Font font;
                        if ((font = this.g.elementAt(i).getFont()) == null) {
                            font = this.h;
                        }
                        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
                        if (n3 > 0) {
                            ++n3;
                        }
                        n3 += fontMetrics.getAscent();
                    }
                }
                int n4 = n2 + (size.height - n3) / 2;
                int n5 = 0;
                for (int j = 0; j < this.g.size(); ++j) {
                    if (this.r.get(j)) {
                        final al al = this.g.elementAt(j);
                        String a;
                        if (al instanceof am) {
                            a = ((am)al).a;
                        }
                        else {
                            a = "";
                        }
                        Font font2;
                        if ((font2 = al.getFont()) == null) {
                            font2 = this.h;
                        }
                        final FontMetrics fontMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics(font2);
                        String a2;
                        if ((a2 = this.a.d().a(a, size.width, fontMetrics2, this.a.d().a())) == null) {
                            a2 = "";
                        }
                        final int stringWidth = fontMetrics2.stringWidth(a2);
                        if (n5 != 0) {
                            ++n4;
                        }
                        graphics.setFont(font2);
                        graphics.drawString(a2, n2 + (size.width - stringWidth) / 2, n4 + fontMetrics2.getAscent());
                        n4 += fontMetrics2.getAscent();
                        n5 = 1;
                    }
                }
            }
        }
    }
    
    public s a() {
        return this.c.f().b(this.d);
    }
    
    public aa b() {
        aa f = null;
        if (this.e >= 0) {
            f = this.a().f(this.e);
        }
        return f;
    }
    
    public void a(final int n) {
        final s a = this.a();
        synchronized (this.b) {
            if ((n & 0x1) != 0x0) {
                this.h = a.c().o;
                this.i = a.c().f;
                this.l = a.c().l;
                this.f = a.g();
            }
            if ((n & 0x1) != 0x0 || (n & 0x4) != 0x0 || (n & 0x20) != 0x0 || (n & 0x40) != 0x0 || (n & 0x80) != 0x0 || (n & 0x400) != 0x0) {
                this.j = this.b(a);
                this.g = this.a(a);
                if (this.b() != null) {
                    this.k = (!a.ad() && a.z() == this.d && this.b().b.equalsIgnoreCase(a.aa()));
                }
                this.p = null;
            }
            if ((n & 0x1) != 0x0 || (n & 0x40) != 0x0 || (n & 0x80) != 0x0 || (n & 0x400) != 0x0) {
                this.m = null;
                this.n = null;
                this.o = null;
            }
            if ((n & 0x8) != 0x0) {
                if (this.q != null) {
                    this.q.c();
                }
            }
            else if (((n & 0x1) != 0x0 || (n & 0x40) != 0x0 || (n & 0x80) != 0x0 || (n & 0x400) != 0x0) && this.q != null) {
                if (this.m == null) {
                    this.m = this.a(this.a(), this.c.f().f());
                }
                final Vector m = this.m;
                if (m != null && m.size() > 0) {
                    this.q.a(m);
                }
            }
        }
    }
    
    public Vector a(final s s) {
        String f = null;
        final y b;
        if ((b = s.f.b()) != null && s.x()) {
            f = b.f;
        }
        Vector<Boolean> vector = null;
        final Vector i;
        if ((i = s.i()) != null) {
            vector = new Vector<Boolean>(i.size());
            for (int j = 0; j < i.size(); ++j) {
                vector.addElement(Boolean.FALSE);
            }
        }
        return this.a.d().a(s, i, vector, s.f(this.e), s.j(), f);
    }
    
    public Color b(final s s) {
        Object c = null;
        if (s.f.b() != null) {
            c = s.f(this.e).c(s.f.b().f);
            if (!(c instanceof Number)) {
                c = null;
            }
        }
        return s.f.a((Number)c);
    }
    
    public Vector a(final Vector vector, final s s) {
        return this.a.d().a(vector, s, s.p(), s.f(this.e));
    }
    
    public Vector a(final s s, final int n) {
        Vector<String> l = (Vector<String>)s.l();
        Vector<Boolean> vector = null;
        if (l != null) {
            vector = new Vector<Boolean>(l.size());
            for (int i = 0; i < l.size(); ++i) {
                vector.addElement(Boolean.FALSE);
            }
        }
        final String k;
        if ((k = s.k()) != null) {
            if (l == null) {
                l = new Vector<String>(1);
            }
            else {
                l = (Vector<String>)l.clone();
            }
            l.insertElementAt(k, 0);
            if (vector == null) {
                vector = new Vector<Boolean>(1);
            }
            vector.insertElementAt(Boolean.TRUE, 0);
        }
        final Vector a = this.a.d().a(s, l, vector, s.f(this.e));
        if (a != null) {
            for (int j = 0; j < a.size(); ++j) {
                a.elementAt(j).c = s.e(j);
            }
        }
        return a;
    }
    
    public String c(final s s) {
        final Object a = this.a.d().a(s, s.n(), s.f(this.e));
        String s2;
        if (a == null || a instanceof String) {
            s2 = (String)a;
        }
        else {
            this.a.c().a((Component)this.a.s, 18, new Object[] { s.n(), a.toString(), "?" });
            s2 = null;
        }
        return s2;
    }
    
    public String d(final s s) {
        final Object a = this.a.d().a(s, s.o(), s.f(this.e));
        String s2;
        if (a == null || a instanceof String) {
            s2 = (String)a;
        }
        else {
            this.a.c().a((Component)this.a.s, 18, new Object[] { s.o(), a.toString(), "?" });
            s2 = null;
        }
        return s2;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!((aw)this.c.getLayout()).c.a()) {
            this.c();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!((aw)this.c.getLayout()).c.a()) {
            this.d();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (((aw)this.c.getLayout()).c.a()) {
            this.e();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!((aw)this.c.getLayout()).c.a()) {
            this.e();
        }
    }
    
    public void c() {
        (this.q = this.f()).d();
        final Vector m;
        synchronized (this.b) {
            if (this.m == null) {
                this.m = this.a(this.a(), this.c.f().f());
            }
            m = this.m;
        }
        final boolean i = this.c.i();
        if (m != null && m.size() > 0 && !i) {
            this.q.a(this, m);
        }
    }
    
    public void d() {
        if (this.q != null) {
            this.q.b();
        }
        else {
            this.c();
        }
    }
    
    public void e() {
        if (this.q != null) {
            this.q.d();
            this.q = null;
        }
    }
    
    public w f() {
        return this.q = this.c.c();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.contains(mouseEvent.getPoint()) && this.getParent() instanceof MouseListener) {
            ((MouseListener)this.getParent()).mouseClicked(new MouseEvent(this.getParent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), this.getBounds().x + mouseEvent.getX(), this.getBounds().y + mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
        else if (mouseEvent.getSource() == this && f.a(mouseEvent, this.a.i().ad)) {
            this.a(mouseEvent.getX(), mouseEvent.getY());
        }
        else if (!this.a.i().ad && (mouseEvent.getModifiers() & 0xE) == 0x0) {
            String o;
            final String n;
            synchronized (this.b) {
                o = null;
                if (this.n == null) {
                    this.n = this.c(this.a());
                }
                if ((n = this.n) != null) {
                    if (this.o == null) {
                        this.o = this.d(this.a());
                    }
                    o = this.o;
                }
            }
            this.c.a(n, o);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.contains(mouseEvent.getPoint()) && this.getParent() instanceof MouseListener) {
            ((MouseListener)this.getParent()).mousePressed(new MouseEvent(this.getParent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), this.getBounds().x + mouseEvent.getX(), this.getBounds().y + mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
        else if (mouseEvent.getSource() == this && f.a(mouseEvent, this.a.i().ad)) {
            this.a(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.contains(mouseEvent.getPoint())) {
            ((MouseListener)this.getParent()).mouseReleased(new MouseEvent(this.getParent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), this.getBounds().x + mouseEvent.getX(), this.getBounds().y + mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
        else if (mouseEvent.getSource() == this && f.a(mouseEvent, this.a.i().ad)) {
            this.a(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    private void a(final int n, final int n2) {
        PopupMenu popupMenu = null;
        this.c.a(true);
        synchronized (this.b) {
            if (this.p == null) {
                this.p = this.a(this.p, this.a());
            }
            if (this.p != null && this.p.size() > 0) {
                this.e();
                popupMenu = new PopupMenu();
                popupMenu.setFont(this.a.i().b());
                for (int i = 0; i < this.p.size(); ++i) {
                    final ab ab = this.p.elementAt(i);
                    if (ab.a()) {
                        popupMenu.addSeparator();
                    }
                    else {
                        final MenuItem menuItem = new MenuItem(ab.b);
                        menuItem.setEnabled(ab.b());
                        popupMenu.add(menuItem);
                    }
                }
            }
        }
        if (popupMenu != null) {
            popupMenu.addActionListener(this);
            this.add(popupMenu);
            new a0(popupMenu, this, n, n2, this.c, this.a).start();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof PopupMenu) {
            final String actionCommand = actionEvent.getActionCommand();
            ab ab = null;
            synchronized (this.b) {
                if (this.p == null) {
                    this.p = this.a(this.p, this.a());
                }
                if (this.p != null) {
                    int n;
                    String b;
                    for (n = 0; n < this.p.size() && ((b = this.p.elementAt(n).b) == null || !b.equals(actionCommand)); ++n) {}
                    if (n < this.p.size()) {
                        ab = (ab)this.p.elementAt(n);
                    }
                }
            }
            if (ab != null) {
                this.c.a(ab.c, ab.d);
            }
        }
    }
    
    public final boolean a(final ae ae) {
        boolean a = false;
        if (this.d == ae.d) {
            a = this.a().a(this.e, ae.e);
        }
        return a;
    }
    
    public void a(final BitSet set) {
        final Dimension size = this.getSize();
        final int n;
        if ((n = (this.k ? 2 : 1) * this.l + 0 * this.l) > 0) {
            size.width = Math.max(0, size.width - 2 * (n + 1));
            size.height = Math.max(0, size.height - 2 * (n + 1));
        }
        if (size.width > 0 && size.height > 0 && this.g != null) {
            int n2 = 0;
            for (int i = 0; i < this.g.size(); ++i) {
                final al al = this.g.elementAt(i);
                String a;
                if (al instanceof am) {
                    a = ((am)al).a;
                }
                else {
                    a = "";
                }
                Font font;
                if ((font = al.getFont()) == null) {
                    font = this.h;
                }
                final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
                if (this.a.d().a(a, size.width, fontMetrics, this.a.d().a()) == null || n2 + ((n2 != 0) ? 1 : 0) + fontMetrics.getAscent() > size.height) {
                    set.clear(i);
                }
                else {
                    if (n2 == 0) {
                        ++n2;
                    }
                    n2 += fontMetrics.getAscent();
                }
            }
        }
        else {
            for (int j = 0; j < set.size(); ++j) {
                set.clear(j);
            }
        }
    }
    
    public void b(final BitSet r) {
        this.r = r;
    }
}

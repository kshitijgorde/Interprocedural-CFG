import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Date;
import java.net.ProtocolException;
import java.io.IOException;
import java.awt.event.AdjustmentEvent;
import java.net.MalformedURLException;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.peer.LightweightPeer;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.UnknownServiceException;
import java.awt.Component;
import java.util.Properties;
import java.util.Stack;
import java.util.Vector;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends Container implements Runnable, p, ActionListener, MouseListener, AdjustmentListener
{
    public d a;
    public final Object b;
    public Container c;
    public o d;
    public r e;
    public s f;
    public u g;
    public Panel h;
    public boolean i;
    public ac j;
    public Image k;
    public w l;
    public w m;
    public Vector n;
    public Stack o;
    public Stack p;
    public boolean q;
    public static /* synthetic */ Class r;
    
    public q(final d d, final Properties properties, final Container container) throws UnknownServiceException {
        this.b = new Object();
        this.i = false;
        this.o = new Stack();
        this.p = new Stack();
        this.q = false;
        final String property;
        if ((property = properties.getProperty("Heatmap")) == null) {
            this.a.c().a((Component)d.s, 15, new Object[] { "Heatmap" });
            throw new UnknownServiceException("Heatmap");
        }
        final String property2 = properties.getProperty("InitToken");
        String string = null;
        final String s = "KeyList1";
        final String property3;
        if ((property3 = properties.getProperty(s)) != null) {
            string = property3;
            final String substring = s.substring(0, s.length() - 1);
            String property4;
            for (int n = 2; (property4 = properties.getProperty(substring + n)) != null; ++n) {
                string = string + ";" + property4;
            }
        }
        this.a(d, properties, container, null, property, property2, string, true);
    }
    
    public q(final d d, final Properties properties, final Container container, final String s, final String s2, final String s3, final boolean b) {
        this.b = new Object();
        this.i = false;
        this.o = new Stack();
        this.p = new Stack();
        this.q = false;
        this.a(d, properties, container, null, s, s2, s3, b);
    }
    
    public q(final d d, final Properties properties, final Container container, final q q, final String s) {
        this.b = new Object();
        this.i = false;
        this.o = new Stack();
        this.p = new Stack();
        this.q = false;
        this.a(d, properties, container, q.e, q.e.a("hm"), q.e.a("inittoken"), s, q.g.f);
    }
    
    private void a(final d a, final Properties properties, final Container c, r a2, String a3, String a4, final String s, final boolean b) {
        this.a = a;
        (this.c = c).removeAll();
        this.c.setLayout(new BorderLayout());
        (this.d = new o(this.a)).setCursor(Cursor.getPredefinedCursor(3));
        this.c.add(this.d, "Center");
        if (a2 == null) {
            a2 = this.a.h().a(a3, a4, s);
        }
        else {
            a3 = a2.a("hm");
            a4 = a2.a("inittoken");
        }
        this.e = a2;
        this.f = new s(this.a, properties, a3, a4);
        final t t = new t();
        t.a("hm://" + a3 + ((a4 != null) ? (":" + a4) : "") + ((s != null) ? ("/" + s) : ""));
        this.f.a(t);
        this.g = new u(this.a, this.f, s, b);
        this.addMouseListener(this);
        new Thread(this).start();
    }
    
    public final void run() {
        this.e.a(this);
    }
    
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
    
    public Container a() {
        return this.c;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.a == null) {
            super.paint(graphics);
            return;
        }
        synchronized (this.getTreeLock()) {
            if (this.a(graphics.getClipBounds())) {
                final Dimension size = this.getSize();
                graphics.drawImage(this.k, 0, 0, size.width, size.height, 0, 0, this.k.getWidth(this), this.k.getHeight(this), this);
            }
        }
    }
    
    public boolean a(final Rectangle rectangle) {
        boolean b = false;
        int n = 0;
        final Dimension size = this.getSize();
        synchronized (this.getTreeLock()) {
            if (this.k == null || Math.abs(this.k.getWidth(this) - size.width) >= 6 || Math.abs(this.k.getHeight(this) - size.height) >= 6) {
                if (size.width > 0 && size.height > 0) {
                    n = 1;
                    this.b();
                    this.k = this.createImage(size.width, size.height);
                }
            }
            else {
                n = (size.equals(rectangle.getSize()) ? 0 : 1);
            }
        }
        if (size.width > 0 && size.height > 0) {
            b = true;
            if (n != 0) {
                final Graphics graphics = this.k.getGraphics();
                graphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                this.a(graphics);
                graphics.dispose();
            }
        }
        return b;
    }
    
    public void b() {
        synchronized (this.getTreeLock()) {
            if (this.k != null) {
                this.k.flush();
                this.k = null;
                this.a.d();
                f.b();
            }
        }
    }
    
    public final void validateTree() {
        super.validateTree();
        this.a(new Rectangle(this.getSize()));
    }
    
    private void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.a.i().c);
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(this.a.i().f);
        final int l = this.f.c().l;
        graphics.fillRect(0, 0, size.width, l);
        graphics.fillRect(0, 0, l, size.height);
        graphics.fillRect(size.width - l, 0, l, size.height);
        graphics.fillRect(0, size.height - l, size.width, l);
        graphics.setClip(0, 0, size.width, size.height);
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final Component component = this.getComponent(i);
            if (!(component instanceof ag)) {
                final Rectangle bounds = component.getBounds();
                if (bounds.width > 0 && bounds.height > 0) {
                    if (!this.f.c().z) {
                        graphics.fillRect(bounds.x - l, bounds.y - l, bounds.width + 2 * l, l);
                        graphics.fillRect(bounds.x - l, bounds.y - l, l, bounds.height + 2 * l);
                        graphics.fillRect(bounds.x + bounds.width, bounds.y - l, l, bounds.height + 2 * l);
                        graphics.fillRect(bounds.x - l, bounds.y + bounds.height, bounds.width + 2 * l, l);
                    }
                    else {
                        graphics.fillRect(0 - l, bounds.y - l, size.width + 2 * l, l);
                        graphics.fillRect(bounds.x - l, 0 - l, l, size.height + 2 * l);
                        graphics.fillRect(bounds.x + bounds.width, 0 - l, l, size.height + 2 * l);
                        graphics.fillRect(0 - l, bounds.y + bounds.height, size.width + 2 * l, l);
                    }
                }
            }
        }
        this.b(graphics);
    }
    
    private void b(final Graphics graphics) {
        for (int i = this.getComponentCount() - 1; i >= 0; --i) {
            final Component component = this.getComponent(i);
            if (component.getPeer() != null && component.getPeer() instanceof LightweightPeer) {
                if (component instanceof ae) {
                    this.a(graphics, (ae)component);
                }
                else if (component instanceof af) {
                    this.a(graphics, (af)component);
                }
                else if (component instanceof ag) {
                    this.a(graphics, (ag)component);
                }
            }
        }
    }
    
    private void a(final Graphics graphics, final ae ae) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final Rectangle bounds = ae.getBounds();
        if (bounds.width > 0 && bounds.height > 0 && bounds.intersects(clipBounds)) {
            final Graphics create = graphics.create();
            create.translate(bounds.x, bounds.y);
            create.setClip(0, 0, bounds.width, bounds.height);
            try {
                ae.a(create);
            }
            catch (Throwable t) {}
            finally {
                create.dispose();
            }
        }
    }
    
    private void a(final Graphics graphics, final af af) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final Rectangle bounds = af.getBounds();
        if (bounds.width > 0 && bounds.height > 0 && bounds.intersects(clipBounds)) {
            final Graphics create = graphics.create();
            create.translate(bounds.x, bounds.y);
            create.setClip(0, 0, bounds.width, bounds.height);
            try {
                af.a(create);
            }
            catch (Throwable t) {}
            finally {
                create.dispose();
            }
        }
    }
    
    private void a(final Graphics graphics, final ag ag) {
        if (ag.isVisible()) {
            final Rectangle clipBounds = graphics.getClipBounds();
            final Rectangle bounds = ag.getBounds();
            if (bounds.width > 0 && bounds.height > 0 && bounds.intersects(clipBounds)) {
                final Graphics create = graphics.create();
                create.translate(bounds.x, bounds.y);
                create.setClip(0, 0, bounds.width, bounds.height);
                try {
                    ag.paint(create);
                }
                catch (Throwable t) {}
                finally {
                    create.dispose();
                }
            }
        }
    }
    
    public final w c() {
        if (this.l == null) {
            this.l = new w(this.a, this.a.d().a(this), false);
        }
        return this.l;
    }
    
    public final w d() {
        if (this.m == null) {
            this.m = new w(this.a, this.a.d().a(this), true);
        }
        return this.m;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final Component component = this.getComponentAt(mouseEvent.getX(), mouseEvent.getY());
        if (component instanceof ae) {
            ((MouseListener)component).mousePressed(new MouseEvent(component, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX() - component.getBounds().x, mouseEvent.getY() - component.getBounds().y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
        else if (mouseEvent.getSource() == this && f.a(mouseEvent, this.a.i().ad)) {
            this.a(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        final Component component = this.getComponentAt(mouseEvent.getX(), mouseEvent.getY());
        if (component instanceof ae) {
            ((MouseListener)component).mouseReleased(new MouseEvent(component, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX() - component.getBounds().x, mouseEvent.getY() - component.getBounds().y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
        else if (mouseEvent.getSource() == this && f.a(mouseEvent, this.a.i().ad)) {
            this.a(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final Component component = this.getComponentAt(mouseEvent.getX(), mouseEvent.getY());
        if (component instanceof ae) {
            ((MouseListener)component).mouseClicked(new MouseEvent(component, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX() - component.getBounds().x, mouseEvent.getY() - component.getBounds().y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
        else if (mouseEvent.getSource() == this && f.a(mouseEvent, this.a.i().ad)) {
            this.a(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void a(final int n, final int n2) {
        int d;
        synchronized (this.getTreeLock()) {
            d = -1;
            for (int i = 0; i < this.getComponentCount(); ++i) {
                final Component component = this.getComponent(i);
                final Rectangle bounds = component.getBounds();
                if (bounds.y <= n2 && n2 <= bounds.y + bounds.height && bounds.width > 0 && bounds.height > 0 && component instanceof ae) {
                    d = ((ae)component).d;
                    break;
                }
            }
        }
        synchronized (this.b) {
            if (d >= 0) {
                this.n = this.f.b(d).a((Vector)null);
            }
            else {
                this.n = this.a.d().a(this.n, this.f, null, null);
            }
            if (this.n != null && this.n.size() > 0) {
                final PopupMenu popupMenu = new PopupMenu();
                popupMenu.setFont(this.a.i().b());
                for (int j = 0; j < this.n.size(); ++j) {
                    final ab ab = this.n.elementAt(j);
                    if (ab.a()) {
                        popupMenu.addSeparator();
                    }
                    else {
                        final MenuItem menuItem = new MenuItem(ab.b);
                        menuItem.setEnabled(ab.b());
                        popupMenu.add(menuItem);
                    }
                }
                popupMenu.addActionListener(this);
                this.add(popupMenu);
                popupMenu.show(this, n, n2);
            }
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof PopupMenu) {
            final String actionCommand = actionEvent.getActionCommand();
            ab ab;
            synchronized (this.b) {
                int n;
                String b;
                for (n = 0; n < this.n.size() && ((b = this.n.elementAt(n).b) == null || !b.equals(actionCommand)); ++n) {}
                ab = null;
                if (n < this.n.size()) {
                    ab = this.n.elementAt(n);
                }
            }
            if (ab != null) {
                this.a(ab.c, ab.d);
            }
        }
    }
    
    public final void a(final String s, final String s2) {
        try {
            this.g();
            this.a.d();
            final Vector k = f.k(s);
            final String s3;
            if ((s3 = k.elementAt(0)) != null) {
                if (s3.equals("hm")) {
                    final t t = (t)this.f().af().clone();
                    t.a(s);
                    this.a(k.elementAt(1), k.elementAt(2), k.elementAt(3), s2, t);
                }
                else if (s3.equals("hmc")) {
                    if (s.startsWith("hmc://backward") || s.startsWith("hmc://forward")) {
                        final t t2 = (t)this.f().af().clone();
                        if (s.startsWith("hmc://backward")) {
                            t2.g();
                        }
                        else {
                            t2.h();
                        }
                        this.a.d();
                        final Vector i = f.k(t2.a());
                        this.a(i.elementAt(1), i.elementAt(2), i.elementAt(3), "_self", t2);
                    }
                    else {
                        this.a(k.elementAt(1), k.elementAt(2), k.elementAt(3), s2);
                    }
                }
                else if (s3.equals("nav")) {
                    this.b(k.elementAt(1), k.elementAt(2), k.elementAt(3), s2);
                }
                else {
                    this.b(s, s2);
                }
            }
        }
        finally {
            if (this.a != null) {
                this.h();
            }
        }
    }
    
    private void a(final String s, final String s2, final String s3, String s4, final t t) {
        if (s != null) {
            final boolean equals = s.equals("this");
            if (s4 == null || s4.trim().length() == 0) {
                s4 = "_blank";
            }
            if (s4.equals("_self") || s4.equals("_top") || s4.equals("_parent")) {
                if (this.a.d().a(this.c) instanceof b) {
                    if (!equals) {
                        ((b)this.a.d().a(this.c)).Reset(s, s2, s3, t);
                    }
                    else {
                        ((b)this.a.d().a(this.c)).Reset(this, s3, t);
                    }
                }
                else if (!equals) {
                    this.a.s.Reset(s, s2, s3, t);
                }
                else {
                    this.a.s.Reset(this, s3, t);
                }
            }
            else if (!s4.equals("_blank")) {
                n a;
                if ((a = this.a.g().a(s4)) == null) {
                    a = new n(this.a, this.a.d().a(this.c), s4, false);
                }
                this.a.g().a(a);
                if (!equals) {
                    a.Reset(s, s2, s3, t);
                }
                else {
                    a.Reset(this, s3, t);
                }
                a.toFront();
            }
            else {
                final n n = new n(this.a, this.a.d().a(this.c), null, false);
                this.a.g().a(n);
                if (!equals) {
                    n.Reset(s, s2, s3, t);
                }
                else {
                    n.Reset(this, s3, t);
                }
                n.toFront();
            }
        }
    }
    
    private void a(final String s, final String s2, final String s3, final String s4) {
        if (s != null) {
            boolean b = true;
            if (s.equals("refabs")) {
                Integer c = null;
                if (s2 != null) {
                    try {
                        c = this.a.d().c(s2);
                    }
                    catch (Exception ex) {}
                }
                if (c != null && c > 0 && c <= this.f.f() && s3 != null && this.f.b(c - 1).e(s3) != null) {
                    b = false;
                    this.f.a(c - 1, s3);
                    this.f.t();
                    this.a(4);
                }
            }
            else if (s.equals("refrel")) {
                Integer c2 = null;
                if (s2 != null) {
                    try {
                        c2 = this.a.d().c(s2);
                    }
                    catch (Exception ex2) {}
                }
                if (c2 != null && c2 > 0 && c2 <= this.f.f() && s3 != null && this.f.b(c2 - 1).e(s3) != null) {
                    b = false;
                    this.f.b(c2 - 1, s3);
                    this.f.t();
                    this.a(4);
                }
            }
            else if (s.equals("refoff")) {
                b = false;
                this.f.ac();
                this.f.t();
                this.a(4);
            }
            if (b) {
                this.a.c().a(this, 19, new Object[] { s, s2, s3, s4 });
            }
        }
    }
    
    private void b(final String s, final String s2, final String s3, final String s4) {
        if (this.a.s instanceof Applet) {
            try {
                final Applet applet = (Applet)this.a.s;
                if (applet == null) {
                    return;
                }
                final Applet applet2 = applet.getAppletContext().getApplet("navigator");
                if (applet2 == null) {
                    return;
                }
                applet2.getClass().getMethod("ShowProcess", (q.r == null) ? (q.r = class$("java.lang.String")) : q.r, (q.r == null) ? (q.r = class$("java.lang.String")) : q.r).invoke(applet2, s, s3);
            }
            catch (Throwable t) {
                System.err.println("NavAction ERROR: " + t.getClass().getName() + " " + t.getMessage());
            }
        }
    }
    
    private void b(final String s, final String s2) {
        if (s != null) {
            try {
                this.a.a(this.a.d().a(s), s2);
            }
            catch (MalformedURLException ex) {
                this.a.c().a(this, 13, new Object[] { s, s2, ex.getMessage() });
            }
        }
    }
    
    public final void e() {
        Thread.currentThread();
        Thread.yield();
        this.e.b(this);
        this.e = null;
        if (this.l != null) {
            this.l.e();
            this.l = null;
        }
        if (this.m != null) {
            this.m.e();
            this.m = null;
        }
        this.a = null;
        this.c = null;
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        if (this.f != null) {
            this.f.ae();
            this.f = null;
        }
        if (this.g != null) {
            this.g.a();
            this.g = null;
        }
        if (this.j != null) {
            this.j.a();
            this.j = null;
        }
        this.k = null;
        if (this.n != null) {
            for (int i = 0; i < this.n.size(); ++i) {
                ((ab)this.n.elementAt(i)).c();
            }
            this.n.removeAllElements();
            this.n = null;
        }
        this.o.removeAllElements();
        this.o = null;
        this.p.removeAllElements();
        this.p = null;
        this.removeAll();
    }
    
    public void a(final String s, final v v) {
        this.g.a(v);
        if (s.equals("init")) {
            this.a.d().a(this.c).setTitle(this.f.b(true));
            this.f.r();
            this.f.u();
            final av layout = new av(this.a, this);
            this.setLayout(layout);
            this.j();
            layout.c();
            this.j = new ac(this.a, this);
            this.a(1);
            this.c.removeAll();
            this.d.a((String)null);
            this.c.setBackground(this.f.c().c);
            final Container c = this.c;
            this.a.d();
            c.setForeground(f.a(this.c.getBackground()));
            this.c.setLayout(new BorderLayout());
            this.c.add(this.d().g());
            this.c.add(this, "Center");
            this.c.add(this.j, "South");
            if (this.h != null) {
                this.c.add(this.h, "North");
            }
            this.invalidate();
            this.c.invalidate();
            this.c.validate();
            this.repaint();
            this.j.a(2);
        }
        else if (s.equals("update") || s.equals("reinit")) {
            synchronized (this.b) {
                if (this.f.d()) {
                    try {
                        this.g();
                        boolean b = false;
                        int n = -1;
                        String aa = null;
                        boolean x = true;
                        for (int i = 0; i < this.f.f(); ++i) {
                            final s b2 = this.f.b(i);
                            if (b2.v()) {
                                if (!this.f.ad() && this.f.z() == i) {
                                    n = i;
                                    aa = this.f.aa();
                                    x = this.f.x();
                                    this.f.ac();
                                }
                                b2.c(false);
                                b = true;
                            }
                        }
                        if (b) {
                            this.j();
                            if (aa != null && this.f.b(n).e(aa) != null) {
                                if (x) {
                                    this.f.a(n, aa);
                                }
                                else {
                                    this.f.b(n, aa);
                                }
                            }
                            this.f.r();
                            this.f.u();
                            this.invalidate();
                            this.validate();
                        }
                        this.a(1);
                    }
                    finally {
                        this.h();
                    }
                }
                else {
                    this.a(8);
                }
            }
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
    }
    
    public boolean a(final String s, final IOException ex) {
        String a = null;
        if (ex != null) {
            ex.printStackTrace();
        }
        if (s.equals("init")) {
            this.a.c().a(this, 12, new Object[] { ex.getMessage() });
        }
        else if (s.equals("reinit")) {
            this.a.c().a(this, 9, new Object[] { ex.getMessage() });
        }
        else if (s.equals("update") && ex instanceof ProtocolException) {
            a = this.a.c().a(this, -1, new Object[0]);
        }
        else {
            this.a.c().a(this, 10, new Object[] { s, ex.getMessage() });
        }
        this.d.a("Error loading Heatmaps®.");
        this.d.setCursor(Cursor.getDefaultCursor());
        if (ex instanceof r.ar) {
            final IOException ioException = (r.ar)ex;
            this.d.a(ioException.a(), ioException.b());
        }
        if (ex instanceof r.ar) {
            if (this.l != null) {
                this.l.a();
            }
            if (this.m != null) {
                this.m.a();
            }
            this.c.removeAll();
            this.c.add(this.d, "Center");
            this.c.invalidate();
            this.c.validate();
            this.d.repaint();
        }
        return a == null || a.equals("No");
    }
    
    public void a(final int n, final Date date) {
        if (this.j != null) {
            this.j.a(n, date);
            this.j.repaint();
        }
    }
    
    public s f() {
        return this.f;
    }
    
    public void a(final t t) {
        this.f().a(t);
    }
    
    private void j() {
        final aw aw = (aw)this.getLayout();
        this.removeAll();
        aw.a();
        for (int i = 0; i < this.f.f(); ++i) {
            final s b = this.f.b(i);
            if (b.q() == 0) {
                if (i != 0) {
                    this.add("", new af(this.a, this, false));
                }
                this.add("", new ax(this.a, this, i, -1, false));
                final Panel panel = new Panel();
                panel.setSize(0, 0);
                this.add("", panel);
            }
            for (int j = 0; j < b.q(); ++j) {
                if (j == 0) {
                    if (i != 0) {
                        this.add("", new af(this.a, this, false));
                    }
                    this.add("", new ax(this.a, this, i, j, false));
                }
                else {
                    this.add("", new af(this.a, this, true));
                    this.add("", new ax(this.a, this, i, j, true));
                }
                this.add("", new ae(this.a, this, i, j));
            }
        }
        this.invalidate();
        this.validate();
    }
    
    public void a(final int n) {
        boolean b = (n & 0x40C0) != 0x0;
        if ((n & 0x20) != 0x0) {
            this.f.t();
        }
        if ((n & 0x40) != 0x0) {
            this.f.r();
        }
        if ((n & 0x80) != 0x0) {
            this.f.s();
        }
        if ((n & 0x1) != 0x0 || (n & 0x2) != 0x0 || (n & 0x4) != 0x0 || (n & 0x20) != 0x0 || (n & 0x200) != 0x0) {
            this.j.a(n);
        }
        if ((n & 0x10) != 0x0 || (n & 0x100) != 0x0 || (n & 0x200) != 0x0 || (n & 0x400) != 0x0 || (n & 0x800) != 0x0) {
            b = true;
        }
        if ((n & 0x1) != 0x0 || (n & 0x4) != 0x0 || (n & 0x8) != 0x0 || (n & 0x10) != 0x0 || (n & 0x20) != 0x0 || (n & 0x40) != 0x0 || (n & 0x80) != 0x0 || (n & 0x100) != 0x0 || (n & 0x200) != 0x0 || (n & 0x400) != 0x0 || (n & 0x800) != 0x0) {
            for (int i = 0; i < this.getComponentCount(); ++i) {
                final Component component = this.getComponent(i);
                if (component instanceof ae) {
                    ((ae)component).a(n);
                }
            }
            this.b();
            if (b) {
                this.invalidate();
                this.validate();
            }
            this.repaint();
        }
    }
    
    public void g() {
        final Cursor predefinedCursor = Cursor.getPredefinedCursor(3);
        synchronized (this.o) {
            final Hashtable<q, Cursor> hashtable = new Hashtable<q, Cursor>();
            final Vector<q> vector = new Vector<q>();
            final Cursor cursor;
            if ((cursor = this.getCursor()) != null) {
                hashtable.put(this, cursor);
            }
            else {
                vector.addElement(this);
            }
            this.setCursor(predefinedCursor);
            final Cursor cursor2;
            if ((cursor2 = this.j.getCursor()) != null) {
                hashtable.put((q)this.j, cursor2);
            }
            else {
                vector.addElement((q)this.j);
            }
            this.j.setCursor(predefinedCursor);
            for (int i = 0; i < this.getComponentCount(); ++i) {
                final Component component = this.getComponent(i);
                final Cursor cursor3 = component.getCursor();
                if (cursor3 != null) {
                    if (cursor3.equals(ag.a)) {
                        continue;
                    }
                    hashtable.put((q)component, cursor3);
                }
                else {
                    vector.addElement((q)component);
                }
                component.setCursor(predefinedCursor);
            }
            this.o.push(hashtable);
            this.p.push(vector);
        }
    }
    
    public void h() {
        synchronized (this.o) {
            final Hashtable hashtable = this.o.pop();
            final Enumeration<Component> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final Component component = keys.nextElement();
                component.setCursor(hashtable.get(component));
            }
            final Vector<Component> vector = this.p.pop();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).setCursor(Cursor.getDefaultCursor());
            }
        }
    }
    
    public void a(final boolean q) {
        this.q = q;
    }
    
    public boolean i() {
        return this.q;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}

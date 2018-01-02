import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class ag extends Component implements MouseListener, MouseMotionListener
{
    public static final Cursor a;
    public d b;
    public boolean c;
    public int d;
    public int e;
    public Color f;
    public Color g;
    public int h;
    public q i;
    public Vector j;
    public Hashtable k;
    public int l;
    
    public ag(final d b, final q i) {
        this.j = new Vector();
        this.k = new Hashtable();
        this.b = b;
        this.i = i;
        this.setBackground(this.i.f().c().c);
        this.b.d();
        this.setForeground(f.a(this.getBackground()));
        this.g = this.i.f().c().f;
        this.f = this.getBackground();
        this.h = 0;
        this.setSize(this.e = this.i.f().c().l, this.getSize().height);
        this.setVisible(this.i.f().c().ab);
        this.l = Math.max(6, 2 * this.e);
        this.a(false);
        this.a(0);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public final void a(final Component component) {
        this.j.addElement(component);
        this.k.put(component, component.getCursor());
        component.addMouseListener(this);
    }
    
    public final void b(final Component component) {
        this.j.removeElement(component);
        this.k.remove(component);
        component.removeMouseListener(this);
        component.removeMouseMotionListener(this);
    }
    
    public final void a(final boolean c) {
        this.c = c;
        Rectangle rectangle;
        if (this.e == 0) {
            if (this.c) {
                this.setSize(1, this.getSize().height);
                rectangle = this.getBounds();
            }
            else {
                rectangle = this.getBounds();
                this.setSize(0, this.getSize().height);
            }
        }
        else {
            rectangle = this.getBounds();
        }
        this.a(rectangle);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.b == null) {
            super.paint(graphics);
            return;
        }
        super.paint(graphics);
        final Rectangle bounds = this.getBounds();
        graphics.setColor(this.g);
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        if (this.a()) {
            graphics.setColor(this.f);
            for (int max = Math.max(1, this.e), i = max + this.h * max; i < bounds.height; i += 2 * max) {
                graphics.fillRect(0, i, bounds.width, max);
            }
            graphics.setColor(this.g);
            if (bounds.width > 2) {
                graphics.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
            }
            graphics.fillRect(0, 0, this.e, this.e);
            graphics.fillRect(0, bounds.height - this.e, this.e, this.e);
        }
    }
    
    public final boolean a() {
        return this.c;
    }
    
    public void a(final int d) {
        this.d = d;
    }
    
    public int b() {
        return this.d;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final Rectangle bounds = this.getBounds();
        super.setBounds(n, n2, n3, n4);
        if (this.a()) {
            this.h = (this.h + 1) % 2;
            this.a(this.getBounds().union(bounds));
        }
    }
    
    public int c() {
        return Math.max(1, 3 * this.e);
    }
    
    public int d() {
        final int width = this.getParent().getSize().width;
        return (width == 0) ? Integer.MAX_VALUE : (width - this.getSize().width - this.c());
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        synchronized (this.i.b) {
            if (this.a()) {
                if (mouseEvent.getSource() == this) {
                    final ag ag = (ag)mouseEvent.getSource();
                    final Rectangle bounds = ag.getBounds();
                    final int n = bounds.x + ag.b() + mouseEvent.getPoint().x;
                    if (ag.c() <= n && n < ag.d()) {
                        ag.setLocation(n, bounds.y);
                    }
                    else {
                        ag.a(-mouseEvent.getPoint().x);
                    }
                }
                else if (this.c((Component)mouseEvent.getSource()) != null) {
                    ag ag2 = null;
                    if (this.isShowing()) {
                        ag2 = this;
                    }
                    if (ag2 != null && ((Component)mouseEvent.getSource()).isShowing()) {
                        final Rectangle bounds2 = ag2.getBounds();
                        final int n2 = bounds2.x + ag2.b() + (((Component)mouseEvent.getSource()).getLocationOnScreen().x + mouseEvent.getPoint().x) - ag2.getLocationOnScreen().x;
                        if (ag2.c() <= n2 && n2 < ag2.d()) {
                            ag2.setLocation(n2, bounds2.y);
                        }
                        else {
                            ag2.a(ag2.getLocationOnScreen().x - (((Component)mouseEvent.getSource()).getLocationOnScreen().x + mouseEvent.getPoint().x));
                        }
                    }
                }
            }
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        synchronized (this.i.b) {
            if (!this.a()) {
                final Component c;
                if ((c = this.c((Component)mouseEvent.getSource())) != null && c.isShowing()) {
                    if (c.getLocationOnScreen().x + c.getBounds().width - this.l < ((Component)mouseEvent.getSource()).getLocationOnScreen().x + mouseEvent.getPoint().x) {
                        c.setCursor(ag.a);
                    }
                    else {
                        c.setCursor((Cursor)this.k.get(c));
                    }
                }
                else if (mouseEvent.getSource() == this) {
                    if (0 < mouseEvent.getX() && mouseEvent.getX() < this.getSize().width) {
                        this.setCursor(ag.a);
                    }
                    else {
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        synchronized (this.i.b) {
            if (!this.a()) {
                final Component c;
                if ((c = this.c((Component)mouseEvent.getSource())) != null && c.isShowing()) {
                    if (c.getLocationOnScreen().x + c.getBounds().width - this.l < ((Component)mouseEvent.getSource()).getLocationOnScreen().x + mouseEvent.getPoint().x) {
                        c.setCursor(ag.a);
                    }
                    else {
                        c.setCursor((Cursor)this.k.get(c));
                    }
                }
                else if (mouseEvent.getSource() == this) {
                    if (0 < mouseEvent.getX() && mouseEvent.getX() < this.getSize().width) {
                        this.setCursor(ag.a);
                    }
                    else {
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        synchronized (this.i.b) {
            if (!this.a()) {
                if (mouseEvent.getSource() == this && 0 < mouseEvent.getX() && mouseEvent.getX() < this.getSize().width) {
                    final ag ag = (ag)mouseEvent.getSource();
                    ag.a(true);
                    ag.a(-mouseEvent.getPoint().x);
                }
                else {
                    final Component c;
                    if ((c = this.c((Component)mouseEvent.getSource())) != null && c.getCursor() != null && c.getCursor().equals(ag.a)) {
                        ag ag2 = null;
                        if (this.isShowing()) {
                            ag2 = this;
                        }
                        if (ag2 != null && ag2.isShowing()) {
                            ag2.a(true);
                            ag2.a(ag2.getLocationOnScreen().x - (((Component)mouseEvent.getSource()).getLocationOnScreen().x + mouseEvent.getPoint().x));
                        }
                    }
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        synchronized (this.i.b) {
            if (this.a()) {
                if (mouseEvent.getSource() == this) {
                    final ag ag = (ag)mouseEvent.getSource();
                    ag.a(false);
                    final Rectangle bounds = ag.getBounds();
                    if (this.i.f().c().y != bounds.x - (this.e - this.e / 2)) {
                        this.i.f().c().y = bounds.x - (this.e - this.e / 2);
                        this.i.a(16);
                    }
                }
                else {
                    final Component c;
                    if ((c = this.c((Component)mouseEvent.getSource())) != null && c.getCursor() != null && c.getCursor().equals(ag.a)) {
                        ag ag2 = null;
                        if (this.isShowing()) {
                            ag2 = this;
                        }
                        if (ag2 != null) {
                            ag2.a(false);
                            final Rectangle bounds2 = ag2.getBounds();
                            if (this.i.f().c().y != bounds2.x - (this.e - this.e / 2)) {
                                this.i.f().c().y = bounds2.x - (this.e - this.e / 2);
                                this.i.a(16);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    private void a(final Rectangle rectangle) {
        this.i.a(rectangle);
    }
    
    private Component c(Component parent) {
        while (parent != null && !this.j.contains(parent)) {
            parent = parent.getParent();
        }
        return parent;
    }
    
    static {
        a = Cursor.getPredefinedCursor(11);
    }
}

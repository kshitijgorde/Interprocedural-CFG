import java.awt.BorderLayout;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.image.ImageObserver;
import java.awt.Window;
import java.awt.LayoutManager;
import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.FontMetrics;
import java.awt.Insets;
import javax.swing.JDialog;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Font;
import java.util.BitSet;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.event.WindowListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends JPanel implements WindowListener
{
    private Hashtable a;
    private Vector b;
    private int[] c;
    private BitSet d;
    private Font e;
    private Image f;
    private int g;
    private String h;
    private boolean i;
    private boolean j;
    private Rectangle k;
    private Rectangle l;
    private Hashtable m;
    private JDialog n;
    
    public Insets getInsets() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.e);
        final Insets insets = new Insets(5, 1, 1, 1);
        final int n = fontMetrics.getHeight() + 4;
        final Insets insets2 = insets;
        insets2.top += n;
        return insets;
    }
    
    public boolean a(final String s) {
        return s != null && this.m.containsKey(s);
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    private String b() {
        for (int size = this.b.size(), i = 0; i < size; ++i) {
            final String string = this.b.elementAt(i).toString();
            if (this.d(string)) {
                return string;
            }
        }
        if (this.b.size() > 0) {
            return this.b.elementAt(0).toString();
        }
        return this.h = null;
    }
    
    public void doLayout() {
        final Dimension size = this.getSize();
        final Insets insets = this.getInsets();
        final Enumeration<Component> elements = (Enumeration<Component>)this.a.elements();
        while (elements.hasMoreElements()) {
            final Component component = elements.nextElement();
            if (component != null) {
                if (component.getName().equals(this.h)) {
                    final int n = size.width - insets.left - insets.right;
                    final int n2 = size.height - insets.top - insets.bottom;
                    component.setLocation(insets.left, insets.top);
                    component.setSize(n, n2);
                    component.setVisible(true);
                    component.validate();
                }
                else {
                    component.setVisible(false);
                }
            }
        }
        this.repaint();
    }
    
    public k b(final String s) {
        if (s == null) {
            return null;
        }
        if (this.a(s)) {
            this.c(s);
        }
        final k k = this.a.remove(s);
        if (k != null) {
            super.remove((Component)k);
            final int index = this.b.indexOf(s);
            if (index >= 0) {
                this.b.removeElementAt(index);
                final int size = this.d.size();
                for (int i = index; i < size - 1; ++i) {
                    if (this.d.get(i + 1)) {
                        this.d.set(i);
                    }
                    else {
                        this.d.clear(i);
                    }
                }
                this.d.clear(size - 1);
            }
            if (this.h.equals(s)) {
                this.g(this.b());
            }
        }
        this.repaint();
        if (this.b.size() == 0) {
            this.n.setVisible(false);
        }
        return k;
    }
    
    public void c(final String s) {
        if (s == null) {
            return;
        }
        final JDialog dialog = this.m.get(s);
        if (dialog == null) {
            return;
        }
        Component component;
        try {
            component = dialog.getContentPane().getComponent(0);
            dialog.getContentPane().remove(0);
            if (component == null) {
                return;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(3));
        dialog.setVisible(false);
        dialog.dispose();
        this.m.remove(s);
        ((k)component).a(false);
        component.setVisible(false);
        this.add(component);
        component.setVisible(true);
        this.a.put(s, component);
        this.a(s, true);
        this.g(s);
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public boolean d(final String s) {
        final int index = this.b.indexOf(s);
        return index >= 0 && this.d.get(index);
    }
    
    public boolean e(final String s) {
        return this.a.containsKey(s);
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    private void a(final MouseEvent mouseEvent) {
        final Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        if (this.k.contains(point)) {
            this.i = true;
            this.repaint();
        }
        else if (this.l.contains(point)) {
            this.j = true;
            this.repaint();
        }
        else if (this.a(point) != null) {
            final String a = this.a(point);
            final boolean b = this.h != null && this.h.equals(a) && mouseEvent.isMetaDown();
            final k f = this.f(a);
            if (b) {
                f.a(mouseEvent.getX() - 1, mouseEvent.getY() - 25);
            }
            else if (f != null) {
                final k f2 = this.f(this.h);
                if (f2 != null) {
                    f2.b();
                }
                f.b();
                this.g(a);
            }
        }
    }
    
    public void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        this.a(graphics, size);
        this.b(graphics);
        this.b(graphics, size);
    }
    
    private void a(final Graphics graphics, final Dimension dimension) {
        final Insets insets = this.getInsets();
        graphics.setColor(dj.v);
        graphics.fillRect(0, insets.top - 5, dimension.width - 1, dimension.height - 1 - insets.top + 5);
        this.a(graphics, 0, insets.top - 5, dimension.width - 1, dimension.height - 1 - insets.top + 5);
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    private void a(final Graphics graphics, final Rectangle rectangle) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.draw3DRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, !this.i);
        graphics.setColor(dj.m);
        final Point point2;
        final Point point = point2 = new Point(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
        point2.x += (this.i ? 1 : 0);
        final Point point3 = point;
        point3.y += (this.i ? 1 : 0);
        graphics.drawLine(point.x + 1, point.y - 3, point.x + 1, point.y + 3);
        graphics.drawLine(point.x, point.y - 2, point.x, point.y + 2);
        graphics.drawLine(point.x - 1, point.y - 1, point.x - 1, point.y + 1);
        graphics.drawLine(point.x - 2, point.y, point.x - 2, point.y);
    }
    
    private void b(final Graphics graphics, final Rectangle rectangle) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.draw3DRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, !this.j);
        graphics.setColor(dj.m);
        final Point point2;
        final Point point = point2 = new Point(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
        point2.x += (this.j ? 1 : 0);
        final Point point3 = point;
        point3.y += (this.j ? 1 : 0);
        graphics.drawLine(point.x - 1, point.y - 3, point.x - 1, point.y + 3);
        graphics.drawLine(point.x, point.y - 2, point.x, point.y + 2);
        graphics.drawLine(point.x + 1, point.y - 1, point.x + 1, point.y + 1);
        graphics.drawLine(point.x + 2, point.y, point.x + 2, point.y);
    }
    
    public void processEvent(final AWTEvent awtEvent) {
        if (awtEvent instanceof MouseEvent) {
            switch (awtEvent.getID()) {
                case 501: {
                    this.a((MouseEvent)awtEvent);
                    break;
                }
                case 502: {
                    this.b((MouseEvent)awtEvent);
                    break;
                }
            }
        }
        super.processEvent(awtEvent);
    }
    
    public j(final JDialog n) {
        this.n = n;
        this.setBackground(dj.m);
        this.setLayout(null);
        this.e = new Font("Dialog", 0, 12);
        this.a = new Hashtable();
        this.b = new Vector();
        this.c = new int[100];
        this.d = new BitSet();
        this.k = new Rectangle();
        this.l = new Rectangle();
        this.m = new Hashtable();
        this.enableEvents(16L);
    }
    
    public void a(final String h, final k k) {
        if (h == null || k == null) {
            return;
        }
        this.a.put(h, k);
        this.b.addElement(h);
        this.d.set(this.b.indexOf(h));
        this.add((Component)k);
        if (this.h == null) {
            this.h = h;
        }
        this.doLayout();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            final Window window = windowEvent.getWindow();
            if (window instanceof JDialog) {
                this.c(((JDialog)window).getTitle());
            }
        }
    }
    
    private boolean c() {
        final int size = this.b.size();
        return (size > 0 && this.c[size - 1] > this.getSize().width - 2) || this.g > 0;
    }
    
    private void b(final MouseEvent mouseEvent) {
        final Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        if (this.k.contains(point)) {
            this.g -= 20;
            this.g = Math.max(this.g, 0);
            this.i = false;
        }
        else if (this.l.contains(point)) {
            final int size = this.a.size();
            if (size > 0 && this.c[size - 1] > this.k.x) {
                this.g += 20;
            }
            this.j = false;
        }
        this.repaint();
    }
    
    private void b(final Graphics graphics) {
        if (this.b.size() == 0) {
            return;
        }
        this.getInsets();
        int n = 2 - this.g;
        final FontMetrics fontMetrics = this.getFontMetrics(this.e);
        int n2 = 0;
        graphics.setFont(this.e);
        final Enumeration<String> elements = (Enumeration<String>)this.b.elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            final k f = this.f(s);
            if (f == null) {
                this.a(graphics, n, s, false, 0);
            }
            else {
                this.a(graphics, n, s, f.a(), f.d());
            }
            n += fontMetrics.stringWidth(s) + 12;
            this.c[n2] = n;
            ++n2;
        }
        final int index = this.b.indexOf(this.h);
        final k f2 = this.f(this.h);
        int d = 0;
        if (f2 != null) {
            d = f2.d();
        }
        int n3 = 2 - this.g;
        for (int i = 0; i < index; ++i) {
            n3 += fontMetrics.stringWidth((String)this.b.elementAt(i)) + 12;
        }
        this.a(graphics, n3, this.h, false, d);
    }
    
    private void a(final Graphics graphics, int n, final String s, final boolean b, final int n2) {
        final String s2 = (s == null) ? "" : s;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Insets insets = this.getInsets();
        int n3 = fontMetrics.stringWidth(s2) + 12 - 1;
        int n4 = fontMetrics.getHeight() + 4;
        final int n5 = insets.top - 5;
        this.getSize();
        final boolean b2 = this.h != null && this.h.equals(s2);
        if (b2) {
            n4 += 2;
            n -= 2;
            n3 += 4;
        }
        graphics.setColor(dj.t);
        if (b2) {
            graphics.fillRect(n - 1, n5 - n4 + 3, n3, n4 - 1);
        }
        final int n6 = n + n3 / 2 - fontMetrics.stringWidth(s2) / 2;
        int n7 = n5 - fontMetrics.getDescent();
        if (b2) {
            n7 -= 2;
            if (n2 == 0) {
                graphics.setColor(dj.q);
            }
            else {
                graphics.setColor(dj.o);
            }
            graphics.drawLine(n - 1, n5 - n4 + 2, n + n3 - 1, n5 - n4 + 2);
            graphics.drawLine(n - 1, n5 - n4 + 3, n + n3 - 1, n5 - n4 + 3);
            graphics.drawLine(n - 1, n5 - n4 + 4, n + n3 - 1, n5 - n4 + 4);
            graphics.setColor(dj.a);
            graphics.drawLine(n - 1, n5, n - 1, n5 - n4 + 2);
        }
        else {
            graphics.setColor(dj.s);
            graphics.fillRect(n - 1, n5 - n4 + 3, n3, n4 - 3);
            graphics.setColor(dj.a);
            graphics.drawLine(n - 1, n5 - n4 + 2, n + n3 - 1, n5 - n4 + 2);
            if (b) {
                if (n2 == 0) {
                    graphics.setColor(dj.r);
                }
                else {
                    graphics.setColor(dj.p);
                }
                graphics.drawLine(n - 1, n5 - n4 + 3, n + n3 - 1, n5 - n4 + 3);
                graphics.drawLine(n - 1, n5 - n4 + 4, n + n3 - 1, n5 - n4 + 4);
            }
        }
        if (this.d(s2)) {
            graphics.setColor(dj.x);
            graphics.drawString(s2, n6, n7);
        }
        else {
            graphics.setColor(dj.x.brighter());
            graphics.drawString(s2, n6 + 1, n7 + 1);
            graphics.setColor(dj.x.darker());
            graphics.drawString(s2, n6, n7);
        }
        graphics.setColor(dj.a);
        if (n < 0) {
            if (n > -n3 && n < 0) {
                final int n8 = insets.top - 5 - n4 + 2;
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, n8, 3, n4 - 2);
                graphics.fillRect(0, n8 + 1, 5, n4 - 4);
                graphics.setColor(dj.n);
                graphics.fillRect(2, n8 + 1, 3, n4 - 4);
                graphics.setColor(dj.a);
                graphics.drawLine(2, n8 + 1, 0, n8 + (int)(n4 * 0.25));
                graphics.drawLine(0, n8 + (int)(n4 * 0.25), 2, n8 + (int)(n4 * 0.5));
                graphics.drawLine(2, n8 + (int)(n4 * 0.5), 0, n8 + (int)(n4 * 0.75));
                graphics.setColor(dj.a);
                graphics.drawLine(0, n8 + (int)(n4 * 0.75), 0, n8 + n4 + 1);
                graphics.drawLine(4, n8, 1, n8 + (int)(n4 * 0.25));
                graphics.drawLine(1, n8 + (int)(n4 * 0.25), 3, n8 + (int)(n4 * 0.5));
                graphics.drawLine(3, n8 + (int)(n4 * 0.5), 1, n8 + (int)(n4 * 0.75));
            }
        }
        if (!this.c() || n + n3 <= this.k.x) {
            graphics.setColor(dj.a);
            graphics.drawLine(n + n3 - 1, n5, n + n3 - 1, n5 - n4 + 2);
        }
        else {
            n = this.k.x - 5;
            final int n9 = insets.top - 5 - n4 + 2;
            graphics.setColor(this.getBackground());
            graphics.fillRect(n, n9, n3, n4 - 2);
            graphics.setColor(dj.a);
            graphics.drawLine(n, n9, n + 1, n9 + (int)(n4 * 0.25));
            graphics.drawLine(n + 1, n9 + (int)(n4 * 0.25), n, n9 + (int)(n4 * 0.5));
            graphics.drawLine(n, n9 + (int)(n4 * 0.5), n + 1, n9 + (int)(n4 * 0.75));
            graphics.drawLine(n + 1, n9 + (int)(n4 * 0.75), n, n9 + n4 - 3);
            graphics.setColor(dj.a);
            graphics.drawLine(n + 1, n9 + 1, n + 2, n9 + (int)(n4 * 0.25));
            graphics.drawLine(n + 2, n9 + (int)(n4 * 0.25), n + 1, n9 + (int)(n4 * 0.5));
            graphics.drawLine(n + 1, n9 + (int)(n4 * 0.5), n + 2, n9 + (int)(n4 * 0.75));
            graphics.drawLine(n + 2, n9 + (int)(n4 * 0.75), n + 1, n9 + n4 - 3);
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(dj.a);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2, n + n3, n2);
    }
    
    private String a(final Point point) {
        String s = null;
        final int min = Math.min(this.b.size(), this.c.length);
        final int n = 0;
        final Insets insets = this.getInsets();
        int height = 12;
        final FontMetrics fontMetrics = this.getFontMetrics(this.e);
        if (fontMetrics != null) {
            height = fontMetrics.getHeight();
        }
        final int n2 = insets.top - 5;
        final int n3 = n2 - height - 5;
        for (int i = 0; i < min; ++i) {
            if (point.x >= n && point.x < this.c[i] && point.y > n3 && point.y < n2) {
                s = (String)this.b.elementAt(i);
                break;
            }
        }
        if (s != null && !this.d(s)) {
            s = null;
        }
        return s;
    }
    
    public k f(final String s) {
        return this.a.get(s);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Dimension size = this.getSize();
        if (this.f == null || this.f.getWidth(this) < size.width || this.f.getHeight(this) < size.height) {
            this.f = this.createImage(size.width, size.height);
        }
        Graphics graphics2 = graphics;
        if (this.f != null) {
            graphics2 = this.f.getGraphics();
        }
        this.a(graphics2);
        if (graphics2 != graphics) {
            graphics.drawImage(this.f, 0, 0, this);
            graphics2.dispose();
        }
    }
    
    public void a() {
        final Enumeration<Object> keys = this.m.keys();
        while (keys.hasMoreElements()) {
            this.c(keys.nextElement().toString());
        }
        final Enumeration<k> elements = this.a.elements();
        while (elements.hasMoreElements()) {
            final k k = elements.nextElement();
            if (k != null) {
                k.c();
            }
        }
    }
    
    public boolean g(final String h) {
        if (h == null || !this.d(h)) {
            return false;
        }
        this.h = h;
        this.doLayout();
        this.f(this.h).e();
        return true;
    }
    
    private void b(final Graphics graphics, final Dimension dimension) {
        final Insets insets = this.getInsets();
        final int n = dimension.width - 20 - 2;
        final int n2 = insets.top - 14 - 5 - 1;
        this.k.setBounds(n, n2, 10, 14);
        this.l.setBounds(n + 10 + 1, n2, 10, 14);
        if (this.c()) {
            this.a(graphics, this.k);
            this.b(graphics, this.l);
        }
    }
    
    public void h(final String s) {
        if (s != null && this.e(s) && !this.a(s)) {
            this.setCursor(Cursor.getPredefinedCursor(3));
            this.a(s, false);
            this.g(this.b());
            final Component component = this.a.remove(s);
            ((k)component).a(true);
            final JDialog dialog = new JDialog(Main.h());
            dialog.setName(s);
            dialog.setTitle(s);
            component.setVisible(false);
            dialog.getContentPane().setLayout(new BorderLayout());
            dialog.getContentPane().add(component, "Center");
            this.m.put(s, dialog);
            dialog.setSize(this.getSize().width, this.getSize().height);
            final Point locationOnScreen = this.getLocationOnScreen();
            dialog.setLocation(locationOnScreen.x + 20, locationOnScreen.y + 20);
            dialog.addWindowListener(this);
            component.setVisible(true);
            dialog.setVisible(true);
            dialog.pack();
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    public void a(final String s, final boolean b) {
        final int index = this.b.indexOf(s);
        if (b && index >= 0) {
            this.d.set(index);
        }
        else if (index >= 0) {
            this.d.clear(index);
        }
        this.repaint();
    }
}

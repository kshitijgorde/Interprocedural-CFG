import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class db extends Panel implements MouseListener, ActionListener
{
    private final esChat a;
    public PopupMenu b;
    public int c;
    private transient Graphics d;
    private transient Image e;
    private int f;
    private int g;
    private Vector h;
    private Vector i;
    private String[] j;
    private int k;
    private Font l;
    private Font m;
    private FontMetrics n;
    private FontMetrics o;
    private int p;
    public Hashtable q;
    private boolean r;
    private static String[] z;
    
    public db(final esChat a) {
        this.a = a;
        this.r = true;
        this.f = 0;
        this.g = 0;
        this.c = 0;
        this.h = new Vector();
        this.j = null;
        this.k = 4;
        this.q = new Hashtable();
        this.addMouseListener(this);
        this.i = new Vector();
        this.setForeground(a.i);
        this.setLayout(new CardLayout());
        this.b = new PopupMenu(db.z[5]);
        final MenuItem menuItem = new MenuItem(a.a(3, "", "", ""));
        final MenuItem menuItem2 = new MenuItem(a.a(4, "", "", ""));
        final MenuItem menuItem3 = new MenuItem(a.a(5, "", "", ""));
        final MenuItem menuItem4 = new MenuItem(a.a(6, "", "", ""));
        final MenuItem menuItem5 = new MenuItem(a.a(53, "", "", ""));
        final MenuItem menuItem6 = new MenuItem(a.a(54, "", "", ""));
        final Menu menu = new Menu(a.a(55, "", "", ""));
        this.b.add(menuItem);
        this.b.add(menuItem2);
        this.b.addSeparator();
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        this.b.add(menu);
        this.add(this.b);
        menuItem.setFont(new Font("", 1, 11));
        menu.addActionListener(this);
        this.b.addActionListener(this);
    }
    
    public void a() {
        final int m = fb.m;
        int n = this.getComponentCount() - 1;
        while (true) {
            Label_0037: {
                if (m == 0) {
                    break Label_0037;
                }
                final Component a = this.a(n);
                if (a instanceof v) {
                    this.a(a, n);
                }
                --n;
            }
            if (n <= 0) {
                return;
            }
            continue;
        }
    }
    
    public void b() {
        final int m = fb.m;
        int n = this.getComponentCount() - 1;
        while (true) {
            Label_0037: {
                if (m == 0) {
                    break Label_0037;
                }
                final Component a = this.a(n);
                if (a instanceof x) {
                    this.a(a, n);
                }
                --n;
            }
            if (n <= 0) {
                return;
            }
            continue;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int m = fb.m;
        boolean b5;
        boolean equals;
        boolean b4;
        boolean b3;
        final boolean b2;
        final boolean b = b2 = (b3 = (b4 = (equals = (b5 = (actionEvent.getSource() instanceof PopupMenu)))));
        final boolean b6;
        final boolean b7;
        Label_0056: {
            if (m == 0) {
                if (!b) {
                    b6 = (b3 = (b4 = (equals = (b5 = (actionEvent.getSource() instanceof Menu)))));
                    if (m != 0) {
                        break Label_0056;
                    }
                    if (!b6) {
                        return;
                    }
                }
                b7 = (equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(3, "", "", ""))));
            }
        }
        if (m == 0) {
            if (b) {
                this.a(this.a(this.c), this.c);
                return;
            }
            equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(4, "", "", "")));
        }
        if (m == 0) {
            if (b6) {
                final boolean b8 = this.a(this.c) instanceof v;
                if (m == 0 && b8) {
                    goto Label_0137;
                }
                if (b8) {
                    this.a();
                    if (m == 0) {
                        return;
                    }
                }
                this.b();
                if (m == 0) {
                    return;
                }
            }
            equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(5, "", "", "")));
        }
        if (m == 0) {
            if (b7) {
                this.a.P = db.z[0];
                this.invalidate();
                this.validate();
                this.repaint();
                if (m == 0) {
                    return;
                }
            }
            equals = (b4 = (b5 = actionEvent.getActionCommand().equals(this.a.a(6, "", "", ""))));
        }
        if (m == 0) {
            if (b7) {
                this.a.P = db.z[3];
                this.invalidate();
                this.validate();
                this.repaint();
                if (m == 0) {
                    return;
                }
            }
            b5 = (equals = actionEvent.getActionCommand().equals(this.a.a(53, "", "", "")));
        }
        if (m == 0) {
            if (equals) {
                this.a.P = db.z[1];
                this.invalidate();
                this.validate();
                this.repaint();
                if (m == 0) {
                    return;
                }
            }
            b5 = actionEvent.getActionCommand().equals(this.a.a(54, "", "", ""));
        }
        if (b5) {
            this.a.P = db.z[2];
            this.invalidate();
            this.validate();
            this.repaint();
        }
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (fb.m == 0) {
            if (o != component.getName()) {
                this.q.put(component, o);
            }
        }
        super.addImpl(component, component.getName(), n);
        this.repaint();
    }
    
    public void a(final String s, final Component component, final boolean b) {
        final int m = fb.m;
        Runtime.getRuntime().gc();
        this.h.addElement(this.a.i);
        db db = this;
        Label_0070: {
            if (m == 0) {
                this.i.addElement(new Rectangle());
                if (b) {
                    this.add(component, s, 0);
                    if (m == 0) {
                        break Label_0070;
                    }
                }
                db = this;
            }
            db.add(component, s);
        }
        boolean equals;
        final boolean b2 = equals = this.a.cb.equals(db.z[4]);
        final int componentCount;
        if (m == 0) {
            if (b2) {
                componentCount = this.getComponentCount();
                if (m == 0) {
                    if (componentCount > 1) {
                        final boolean b3 = equals = (this.a(0) instanceof cb);
                        if (m == 0) {
                            if (b3) {
                                this.a.Ob.b.a(this.a.Mb, 0);
                            }
                        }
                    }
                }
            }
        }
        if (componentCount != 0) {
            this.c = 0;
        }
    }
    
    protected void a(final Graphics graphics) {
        final int m = fb.m;
        final int componentCount = this.getComponentCount();
        if (componentCount == 0) {
            return;
        }
        final String[] c = this.c();
        final Dimension size = this.getSize();
        graphics.setColor(this.a.f);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n5;
        int equalsIgnoreCase;
        final int n4 = equalsIgnoreCase = (n5 = (this.a.P.equalsIgnoreCase(db.z[1]) ? 1 : 0));
        int width = 0;
        Label_0238: {
            if (m == 0) {
                if (n4 != 0) {
                    n = 0;
                    n2 = 0;
                    n3 = size.height;
                    width = 72;
                    if (m == 0) {
                        break Label_0238;
                    }
                }
                final int n6;
                equalsIgnoreCase = (n6 = (n5 = (this.a.P.equalsIgnoreCase(db.z[2]) ? 1 : 0)));
            }
            if (m == 0) {
                if (n4 != 0) {
                    n = 0;
                    n2 = size.width - 72;
                    n3 = size.height;
                    width = 72;
                    if (m == 0) {
                        break Label_0238;
                    }
                }
                n5 = (equalsIgnoreCase = (this.a.P.equalsIgnoreCase(db.z[0]) ? 1 : 0));
            }
            if (m == 0) {
                if (equalsIgnoreCase != 0) {
                    n = 0;
                    n2 = 0;
                    n3 = 24;
                    width = size.width;
                    if (m == 0) {
                        break Label_0238;
                    }
                }
                n = size.height - 24;
                n2 = 0;
                n3 = 24;
                n5 = size.width;
            }
            width = n5;
        }
        int n7 = 3;
        int n8 = 100;
        int equalsIgnoreCase2;
        final int n9 = equalsIgnoreCase2 = (this.a.P.equalsIgnoreCase(db.z[1]) ? 1 : 0);
        int equalsIgnoreCase4;
        while (true) {
        Label_0445:
            while (true) {
                int n12 = 0;
                Label_0439: {
                    if (m == 0) {
                        if (n9 == 0) {
                            final boolean equalsIgnoreCase3;
                            final int n10 = (equalsIgnoreCase3 = this.a.P.equalsIgnoreCase(db.z[2])) ? 1 : 0;
                            if (m != 0 || n10 == 0) {
                                final int n11;
                                if (m == 0) {
                                    if (n10 >= size.width - 22) {
                                        n8 = (n11 = (size.width - 22) / componentCount);
                                        if (m == 0) {
                                            if (n11 < 22) {
                                                n8 = 22;
                                            }
                                        }
                                    }
                                }
                                n12 = n11;
                                if (m != 0) {
                                    break Label_0402;
                                }
                                break Label_0439;
                            }
                        }
                        n8 = 0;
                        equalsIgnoreCase2 = 0;
                    }
                    int n13 = equalsIgnoreCase2;
                    while (true) {
                        Label_0337: {
                            if (m == 0) {
                                break Label_0337;
                            }
                            ((Rectangle)this.i.elementAt(n13)).setBounds(n2, n8 + 2, 70, 21);
                            n8 += 24;
                            ++n13;
                        }
                        if (n13 >= componentCount) {
                            break Label_0445;
                        }
                        continue;
                    }
                    ((Rectangle)this.i.elementAt(n12)).setBounds(n7, n + 2, n8 - 2, 21);
                    n7 = equalsIgnoreCase4;
                    ++n12;
                }
                if (n12 < componentCount) {
                    continue;
                }
                break;
            }
            int n12 = 17;
            equalsIgnoreCase4 = (this.a.P.equalsIgnoreCase(db.z[1]) ? 1 : 0);
            if (m != 0) {
                continue;
            }
            break;
        }
        Label_0719: {
            Label_0605: {
                if (m == 0) {
                    if (equalsIgnoreCase4 == 0) {
                        final boolean equalsIgnoreCase5 = this.a.P.equalsIgnoreCase(db.z[2]);
                        if (m != 0) {
                            break Label_0719;
                        }
                        if (!equalsIgnoreCase5) {
                            break Label_0605;
                        }
                    }
                    graphics.drawImage(this.a.x, n2, 0, n2 + width, 5, 125, 39, 225, 44, this);
                    graphics.drawImage(this.a.x, n2, 5, n2 + width, n3 - 5, 125, 44, 225, 58, this);
                    graphics.drawImage(this.a.x, n2, n3 - 5, n2 + width, n3, 125, 58, 225, 63, this);
                }
                if (m == 0) {
                    break Label_0719;
                }
            }
            graphics.drawImage(this.a.x, 0, n, 10, n + 24, 125, 39, 135, 63, this);
            graphics.drawImage(this.a.x, 10, n, size.width - 80, n + 24, 135, 39, 145, 63, this);
            graphics.drawImage(this.a.x, size.width - 80, n, size.width, n + 24, 145, 39, 225, 63, this);
        }
        int n14 = 0;
        while (true) {
        Label_1137_Outer:
            while (true) {
                Label_1396: {
                    if (m == 0) {
                        break Label_1396;
                    }
                    final Object element = this.i.elementAt(n14);
                    final Rectangle rectangle = (Rectangle)element;
                    final int n15 = n14;
                    Label_1069: {
                        if (m == 0) {
                            if (n15 == this.c) {
                                this.h.setElementAt(this.getForeground(), n14);
                                graphics.setFont(this.m);
                                final int x = rectangle.x;
                                final int y = rectangle.y;
                                graphics.drawImage(this.a.x, x, y, x + 10, y + 20, 30, 41, 40, 61, null);
                                graphics.drawImage(this.a.x, x + 10, y, x + rectangle.width - 10, y + 20, 40, 41, 50, 61, null);
                                graphics.drawImage(this.a.x, x + rectangle.width - 10, y, x + rectangle.width, y + 20, 50, 41, 60, 61, null);
                                if (m == 0) {
                                    break Label_1069;
                                }
                            }
                            graphics.setFont(this.l);
                            final int x2 = rectangle.x;
                        }
                        final int n16 = n15;
                        final int y2 = rectangle.y;
                        graphics.drawImage(this.a.x, n16, y2, n16 + 10, y2 + 20, 0, 41, 10, 61, null);
                        graphics.drawImage(this.a.x, n16 + 10, y2, n16 + rectangle.width - 10, y2 + 20, 10, 41, 20, 61, null);
                        graphics.drawImage(this.a.x, n16 + rectangle.width - 10, y2, n16 + rectangle.width, y2 + 20, 20, 41, 30, 61, null);
                    }
                    graphics.setColor((Color)this.h.elementAt(n14));
                    int n17 = 4;
                    int n18 = 0;
                    final FontMetrics fontMetrics = (n14 != this.c) ? this.n : this.o;
                    int n19;
                    int n20;
                    while (true) {
                    Label_1168:
                        while (true) {
                            Label_1156: {
                                if (m == 0) {
                                    break Label_1156;
                                }
                                n17 += fontMetrics.charWidth(c[n14].charAt(n18));
                                if (n17 >= rectangle.width - 16) {
                                    break Label_1168;
                                }
                                ++n18;
                            }
                            if (n18 < c[n14].length()) {
                                continue Label_1137_Outer;
                            }
                            break;
                        }
                        n19 = rectangle.x + 3;
                        n20 = rectangle.y + 3;
                        if (m != 0) {
                            continue;
                        }
                        break;
                    }
                    final Image x3 = this.a.x;
                    Label_1337: {
                        if (m == 0) {
                            Label_1269: {
                                if (x3 != null) {
                                    final boolean startsWith = c[n14].substring(0, n18).startsWith("#");
                                    if (m == 0) {
                                        if (startsWith) {
                                            break Label_1269;
                                        }
                                        graphics.drawImage(this.a.x, n19, n20, n19 + 15, n20 + 14, 61, 41, 76, 55, this);
                                    }
                                    if (m == 0) {
                                        break Label_1337;
                                    }
                                }
                            }
                            final Image x4 = this.a.x;
                        }
                        if (x3 != null) {
                            final boolean startsWith2 = c[n14].substring(0, n18).startsWith("#");
                            if (m == 0) {
                                if (startsWith2) {
                                    graphics.drawImage(this.a.x, n19, n20, n19 + 15, n20 + 14, 77, 41, 92, 55, this);
                                }
                            }
                        }
                    }
                    Graphics graphics2 = graphics;
                    if (m == 0) {
                        if (graphics.getColor() != Color.red) {
                            graphics.setColor(this.a.i);
                        }
                        graphics2 = graphics;
                    }
                    graphics2.drawString(c[n14].substring(0, n18), rectangle.x + 19, rectangle.y + 15);
                    ++n14;
                }
                if (n14 < componentCount) {
                    continue;
                }
                break;
            }
            final Object element = graphics;
            if (m == 0) {
                graphics.setFont(this.l);
                return;
            }
            continue;
        }
    }
    
    public String[] c() {
        final int m = fb.m;
        final int componentCount = this.getComponentCount();
        final String[] array = new String[componentCount];
        final Component[] components = this.getComponents();
        int n = 0;
        while (true) {
            Label_0049: {
                if (m == 0) {
                    break Label_0049;
                }
                final String[] array2;
                array2[n] = this.q.get(components[n]);
                ++n;
            }
            if (n < componentCount) {
                continue;
            }
            final String[] array2 = array;
            if (m == 0) {
                return array2;
            }
            continue;
        }
    }
    
    public Component a(final int n) {
        return this.getComponent(n);
    }
    
    public Insets getInsets() {
        final int m = fb.m;
        this.getFontMetrics(this.getFont());
        boolean b2;
        final boolean b = b2 = this.a.P.equalsIgnoreCase(db.z[0]);
        if (m == 0) {
            if (b) {
                return new Insets(24, 1, 1, 1);
            }
            final boolean equalsIgnoreCase;
            b2 = (equalsIgnoreCase = this.a.P.equalsIgnoreCase(db.z[1]));
        }
        if (m == 0) {
            if (b) {
                return new Insets(1, 72, 1, 1);
            }
            b2 = this.a.P.equalsIgnoreCase(db.z[2]);
        }
        if (b2) {
            return new Insets(1, 1, 1, 72);
        }
        return new Insets(1, 1, 24, 1);
    }
    
    protected int a(final Object o) {
        final int m = fb.m;
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        int n = 0;
        while (true) {
            Label_0026: {
                if (m == 0) {
                    break Label_0026;
                }
                ++n;
            }
            if (n < componentCount && components[n] != o) {
                continue;
            }
            break;
        }
        final int n2 = n;
        if (m == 0 && n2 == componentCount) {
            n = -1;
            goto Label_0054;
        }
        return n2;
    }
    
    public int d() {
        return this.c;
    }
    
    public Component e() {
        return this.getComponent(this.c);
    }
    
    public int a(final String s) {
        final int m = fb.m;
        final String[] c = this.c();
        int n = 0;
        while (true) {
            while (true) {
                Label_0037: {
                    if (m == 0) {
                        break Label_0037;
                    }
                    final boolean equalsIgnoreCase;
                    int n2 = (equalsIgnoreCase = s.equalsIgnoreCase(c[n])) ? 1 : 0;
                    if (m == 0) {
                        final int n3;
                        if (n3 == 0) {
                            ++n;
                            break Label_0037;
                        }
                        n2 = n;
                    }
                    return n2;
                }
                if (n < c.length) {
                    continue;
                }
                break;
            }
            int n2;
            final int n3 = n2 = -1;
            if (m == 0) {
                return n3;
            }
            continue;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int m = fb.m;
        this.a(mouseEvent);
        final int n = mouseEvent.getModifiers() & 0x4;
        if (m == 0) {
            if (n <= 0) {
                return;
            }
            this.getComponentCount();
        }
        final int n2 = n;
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (m == 0) {
                    break Label_0086;
                }
                db db = this;
                if (m == 0) {
                    if (!((Rectangle)this.i.elementAt(n3)).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        ++n3;
                        break Label_0086;
                    }
                    db = this;
                }
                db.b.show(this, mouseEvent.getX(), mouseEvent.getY());
                return;
            }
            if (n3 < n2) {
                continue;
            }
            break;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        db db = this;
        if (m == 0) {
            if (this.l != this.getFont()) {
                this.l = this.getFont();
                this.m = new Font(this.l.getName(), 1, this.l.getSize());
                this.n = this.getFontMetrics(this.l);
                this.o = this.getFontMetrics(this.m);
                this.p = this.n.getHeight();
            }
            db = this;
        }
        final Dimension size = db.getSize();
        db db2 = null;
        Label_0212: {
            Label_0191: {
                if (m == 0) {
                    if (size.width == this.g && size.height == this.f) {
                        db2 = this;
                        if (m != 0) {
                            break Label_0212;
                        }
                        if (this.e != null) {
                            break Label_0191;
                        }
                    }
                    this.e = this.createImage(size.width, size.height);
                }
                db db3 = this;
                if (m == 0) {
                    if (this.d != null) {
                        this.d.dispose();
                    }
                    this.d = this.e.getGraphics();
                    this.g = size.width;
                    db3 = this;
                }
                db3.f = size.height;
            }
            this.a(this.d);
            graphics.drawImage(this.e, 0, 0, this);
            db2 = this;
        }
        db2.paint(graphics);
    }
    
    public void a(final Component component, final int n) {
        final int m = fb.m;
        this.q.remove(component);
        int n5;
        int c;
        int n4;
        int n3;
        final int n2 = n3 = (n4 = (c = (n5 = ((component instanceof v) ? 1 : 0))));
        Label_0088: {
            Label_0084: {
                if (m == 0) {
                    if (n2 != 0) {
                        ((v)component).a();
                        if (m == 0) {
                            break Label_0084;
                        }
                    }
                    final int n6;
                    n3 = (n6 = (n4 = (c = (n5 = ((component instanceof x) ? 1 : 0)))));
                }
                if (m == 0) {
                    if (n2 != 0) {
                        ((x)component).a();
                        if (m == 0) {
                            break Label_0084;
                        }
                    }
                    n4 = (n3 = (c = (n5 = ((component instanceof z) ? 1 : 0))));
                }
                if (m != 0) {
                    break Label_0088;
                }
                if (n3 != 0) {
                    this.a.Qb = null;
                    this.a.Pb = null;
                }
            }
            n4 = (n5 = this.c);
        }
        int n7 = n;
        int n8 = n;
        final int n9;
        if (m == 0) {
            Label_0126: {
                if (n4 >= n) {
                    n9 = (n5 = this.c);
                    if (m != 0) {
                        break Label_0126;
                    }
                    if (n9 > 0) {
                        --this.c;
                    }
                }
                super.remove(n);
                c = this.c;
            }
            n7 = n;
            n8 = n;
        }
        int n11 = 0;
        final int n12;
        Label_0190: {
            final int n10;
            if (m == 0) {
                Label_0159: {
                    if (n9 >= n8) {
                        final int componentCount;
                        n10 = (componentCount = (n11 = this.c));
                        if (m != 0) {
                            break Label_0159;
                        }
                        if (n10 > 0) {
                            --this.c;
                        }
                    }
                    n11 = this.c;
                }
                if (m != 0) {
                    break Label_0190;
                }
                n7 = -1;
            }
            if (n10 > n7) {
                n12 = (n11 = this.getComponentCount());
                if (m != 0) {
                    break Label_0190;
                }
                if (n12 > 0) {
                    this.b(this.c);
                }
            }
            int componentCount;
            n11 = (componentCount = this.getComponentCount());
        }
        if (m == 0) {
            if (n12 == 0) {
                this.a(db.z[7], this.a.Mb, true);
                this.a.Ob.b.b(0);
            }
            this.h.removeElementAt(n);
            if (m != 0) {
                return;
            }
            n11 = ((component instanceof cb) ? 1 : 0);
        }
        if (n11 == 0) {}
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    protected void a(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final int componentCount;
        final int n = componentCount = this.getComponentCount();
        if (m == 0 && componentCount < 1) {
            return;
        }
        int n2 = componentCount;
        while (true) {
            Label_0067: {
                if (m == 0) {
                    break Label_0067;
                }
                db db = this;
                if (m == 0) {
                    if (!((Rectangle)this.i.elementAt(n2)).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        ++n2;
                        break Label_0067;
                    }
                    db = this;
                }
                db.b(n2);
                return;
            }
            if (n2 >= n) {
                return;
            }
            continue;
        }
    }
    
    public void a(final int n, final Color color) {
        this.h.setElementAt(color, n);
        this.invalidate();
        this.repaint();
    }
    
    public void a(final Component component) {
        final int m = fb.m;
        final Component[] components = this.getComponents();
        int n = 0;
        while (true) {
            Label_0020: {
                if (m == 0) {
                    break Label_0020;
                }
                ++n;
            }
            if (components[n] == component) {
                if (n < this.getComponentCount()) {
                    this.b(n);
                }
                return;
            }
            continue;
        }
    }
    
    public void b(final int c) {
        final int m = fb.m;
        this.c = c;
        final Component component2;
        final Component component = component2 = this.getComponent(c);
        if (m == 0) {
            if (component2 == null) {
                return;
            }
            ((CardLayout)this.getLayout()).show(this, component.getName());
            this.invalidate();
            this.validate();
            this.repaint();
            this.a.Ob.d.repaint();
        }
        boolean b2;
        final boolean b = b2 = (component2 instanceof v);
        if (m == 0) {
            if (b) {
                ((v)component).n.requestFocus();
                final String r = ((v)component).r;
                final int k = ((v)component).k;
                final String s = ((v)component).s;
                String s2 = "";
                final String s3 = r;
                Label_0236: {
                    if (m == 0) {
                        if (s3 != null) {
                            s2 = String.valueOf(s2) + r;
                        }
                        s2 = String.valueOf(s2) + db.z[9] + k + db.z[10];
                        if (m != 0) {
                            break Label_0236;
                        }
                    }
                    if (s3 != null) {
                        s2 = String.valueOf(s2) + s;
                    }
                    this.a.pb = r;
                    this.a.q(s2);
                }
                this.a.e();
            }
            final boolean b3;
            b2 = (b3 = (component instanceof x));
        }
        final x x;
        Label_0349: {
            if (m == 0) {
                if (b) {
                    ((x)component).f.requestFocus();
                    this.a.ob = ((x)component).c;
                    this.a.q(String.valueOf(((x)component).c) + ((x)component).d);
                    this.a.e();
                }
                x = (x)component;
                if (m != 0) {
                    break Label_0349;
                }
                b2 = (x instanceof cb);
            }
            if (!b2) {
                return;
            }
            this.a.ob = db.z[7];
        }
        ((cb)x).b.requestFocus();
        this.a.q(String.valueOf(((cb)component).e) + db.z[6] + this.a.m + db.z[8] + this.a.sb);
        this.a.e();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "7<4".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'c';
                    break;
                }
                case 1: {
                    c2 = 'S';
                    break;
                }
                case 2: {
                    c2 = 'D';
                    break;
                }
                case 3: {
                    c2 = '}';
                    break;
                }
                default: {
                    c2 = 'T';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "/6\"\t".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'c';
                    break;
                }
                case 1: {
                    c4 = 'S';
                    break;
                }
                case 2: {
                    c4 = 'D';
                    break;
                }
                case 3: {
                    c4 = '}';
                    break;
                }
                default: {
                    c4 = 'T';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "1:#\u0015 ".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'c';
                    break;
                }
                case 1: {
                    c6 = 'S';
                    break;
                }
                case 2: {
                    c6 = 'D';
                    break;
                }
                case 3: {
                    c6 = '}';
                    break;
                }
                default: {
                    c6 = 'T';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "!<0\t;\u000e".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'c';
                    break;
                }
                case 1: {
                    c8 = 'S';
                    break;
                }
                case 2: {
                    c8 = 'D';
                    break;
                }
                case 3: {
                    c8 = '}';
                    break;
                }
                default: {
                    c8 = 'T';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = ",5\"".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'c';
                    break;
                }
                case 1: {
                    c10 = 'S';
                    break;
                }
                case 2: {
                    c10 = 'D';
                    break;
                }
                case 3: {
                    c10 = '}';
                    break;
                }
                default: {
                    c10 = 'T';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "72&-;\u0013&4".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'c';
                    break;
                }
                case 1: {
                    c12 = 'S';
                    break;
                }
                case 2: {
                    c12 = 'D';
                    break;
                }
                case 3: {
                    c12 = '}';
                    break;
                }
                default: {
                    c12 = 'T';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "C\bd".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'c';
                    break;
                }
                case 1: {
                    c14 = 'S';
                    break;
                }
                case 2: {
                    c14 = 'D';
                    break;
                }
                case 3: {
                    c14 = '}';
                    break;
                }
                default: {
                    c14 = 'T';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "0'%\t!\u0010".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'c';
                    break;
                }
                case 1: {
                    c16 = 'S';
                    break;
                }
                case 2: {
                    c16 = 'D';
                    break;
                }
                case 3: {
                    c16 = '}';
                    break;
                }
                default: {
                    c16 = 'T';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "C\u000ed]t".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'c';
                    break;
                }
                case 1: {
                    c18 = 'S';
                    break;
                }
                case 2: {
                    c18 = 'D';
                    break;
                }
                case 3: {
                    c18 = '}';
                    break;
                }
                default: {
                    c18 = 'T';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "C\b".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'c';
                    break;
                }
                case 1: {
                    c20 = 'S';
                    break;
                }
                case 2: {
                    c20 = 'D';
                    break;
                }
                case 3: {
                    c20 = '}';
                    break;
                }
                default: {
                    c20 = 'T';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = ">s~".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'c';
                    break;
                }
                case 1: {
                    c22 = 'S';
                    break;
                }
                case 2: {
                    c22 = 'D';
                    break;
                }
                case 3: {
                    c22 = '}';
                    break;
                }
                default: {
                    c22 = 'T';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        db.z = z;
    }
}

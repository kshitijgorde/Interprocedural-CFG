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
import java.awt.PopupMenu;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class db extends Panel implements MouseListener, ActionListener
{
    private final esChat a;
    public int b;
    private transient Graphics c;
    private transient Image d;
    private int e;
    private int f;
    private Vector g;
    private Vector h;
    private String[] i;
    private int j;
    private Font k;
    private Font l;
    private FontMetrics m;
    private FontMetrics n;
    private int o;
    public Hashtable p;
    private boolean q;
    private static String[] z;
    
    public db(final esChat a) {
        this.a = a;
        this.q = true;
        this.e = 0;
        this.f = 0;
        this.b = 0;
        this.g = new Vector();
        this.i = null;
        this.j = 4;
        this.p = new Hashtable();
        this.addMouseListener(this);
        this.h = new Vector();
        this.setForeground(a.j);
        this.setLayout(new CardLayout());
        a.Eb = new PopupMenu(db.z[3]);
        final MenuItem menuItem = new MenuItem(a.a(3, "", "", ""));
        final MenuItem menuItem2 = new MenuItem(a.a(4, "", "", ""));
        final MenuItem menuItem3 = new MenuItem(a.a(5, "", "", ""));
        final MenuItem menuItem4 = new MenuItem(a.a(6, "", "", ""));
        final MenuItem menuItem5 = new MenuItem(a.a(53, "", "", ""));
        final MenuItem menuItem6 = new MenuItem(a.a(54, "", "", ""));
        final Menu menu = new Menu(a.a(55, "", "", ""));
        a.Eb.add(menuItem);
        a.Eb.add(menuItem2);
        a.Eb.addSeparator();
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        a.Eb.add(menu);
        this.add(a.Eb);
        menuItem.setFont(new Font("", 1, 11));
        menu.addActionListener(this);
        a.Eb.addActionListener(this);
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
                this.a(this.a(this.b), this.b);
                return;
            }
            equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(4, "", "", "")));
        }
        if (m == 0) {
            if (b6) {
                final boolean b8 = this.a(this.b) instanceof v;
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
                this.a.S = db.z[2];
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
                this.a.S = db.z[10];
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
                this.a.S = db.z[0];
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
            this.a.S = db.z[1];
            this.invalidate();
            this.validate();
            this.repaint();
        }
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (fb.m == 0) {
            if (o != component.getName()) {
                this.p.put(component, o);
            }
        }
        super.addImpl(component, component.getName(), n);
        this.repaint();
    }
    
    public void a(final String s, final Component component, final boolean b) {
        final int m = fb.m;
        Runtime.getRuntime().gc();
        this.g.addElement(this.a.j);
        db db = this;
        Label_0070: {
            if (m == 0) {
                this.h.addElement(new Rectangle());
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
        final boolean b2 = equals = this.a.fb.equals(db.z[4]);
        final int componentCount;
        if (m == 0) {
            if (b2) {
                componentCount = this.getComponentCount();
                if (m == 0) {
                    if (componentCount > 1) {
                        final boolean b3 = equals = (this.a(0) instanceof cb);
                        if (m == 0) {
                            if (b3) {
                                this.a.Tb.b.a(this.a.Rb, 0);
                            }
                        }
                    }
                }
            }
        }
        if (componentCount != 0) {
            this.b = 0;
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
        graphics.setColor(this.a.g);
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n5;
        int equalsIgnoreCase;
        final int n4 = equalsIgnoreCase = (n5 = (this.a.S.equalsIgnoreCase(db.z[0]) ? 1 : 0));
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
                equalsIgnoreCase = (n6 = (n5 = (this.a.S.equalsIgnoreCase(db.z[1]) ? 1 : 0)));
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
                n5 = (equalsIgnoreCase = (this.a.S.equalsIgnoreCase(db.z[2]) ? 1 : 0));
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
        final int n9 = equalsIgnoreCase2 = (this.a.S.equalsIgnoreCase(db.z[0]) ? 1 : 0);
        int equalsIgnoreCase4;
        while (true) {
        Label_0445:
            while (true) {
                int n12 = 0;
                Label_0439: {
                    if (m == 0) {
                        if (n9 == 0) {
                            final boolean equalsIgnoreCase3;
                            final int n10 = (equalsIgnoreCase3 = this.a.S.equalsIgnoreCase(db.z[1])) ? 1 : 0;
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
                            ((Rectangle)this.h.elementAt(n13)).setBounds(n2, n8 + 2, 70, 21);
                            n8 += 24;
                            ++n13;
                        }
                        if (n13 >= componentCount) {
                            break Label_0445;
                        }
                        continue;
                    }
                    ((Rectangle)this.h.elementAt(n12)).setBounds(n7, n + 2, n8 - 2, 21);
                    n7 = equalsIgnoreCase4;
                    ++n12;
                }
                if (n12 < componentCount) {
                    continue;
                }
                break;
            }
            int n12 = 17;
            equalsIgnoreCase4 = (this.a.S.equalsIgnoreCase(db.z[0]) ? 1 : 0);
            if (m != 0) {
                continue;
            }
            break;
        }
        Label_0719: {
            Label_0605: {
                if (m == 0) {
                    if (equalsIgnoreCase4 == 0) {
                        final boolean equalsIgnoreCase5 = this.a.S.equalsIgnoreCase(db.z[1]);
                        if (m != 0) {
                            break Label_0719;
                        }
                        if (!equalsIgnoreCase5) {
                            break Label_0605;
                        }
                    }
                    graphics.drawImage(this.a.y, n2, 0, n2 + width, 5, 125, 39, 225, 44, this);
                    graphics.drawImage(this.a.y, n2, 5, n2 + width, n3 - 5, 125, 44, 225, 58, this);
                    graphics.drawImage(this.a.y, n2, n3 - 5, n2 + width, n3, 125, 58, 225, 63, this);
                }
                if (m == 0) {
                    break Label_0719;
                }
            }
            graphics.drawImage(this.a.y, 0, n, 10, n + 24, 125, 39, 135, 63, this);
            graphics.drawImage(this.a.y, 10, n, size.width - 80, n + 24, 135, 39, 145, 63, this);
            graphics.drawImage(this.a.y, size.width - 80, n, size.width, n + 24, 145, 39, 225, 63, this);
        }
        int n14 = 0;
        while (true) {
        Label_1137_Outer:
            while (true) {
                Label_1396: {
                    if (m == 0) {
                        break Label_1396;
                    }
                    final Object element = this.h.elementAt(n14);
                    final Rectangle rectangle = (Rectangle)element;
                    final int n15 = n14;
                    Label_1069: {
                        if (m == 0) {
                            if (n15 == this.b) {
                                this.g.setElementAt(this.getForeground(), n14);
                                graphics.setFont(this.l);
                                final int x = rectangle.x;
                                final int y = rectangle.y;
                                graphics.drawImage(this.a.y, x, y, x + 10, y + 20, 30, 41, 40, 61, null);
                                graphics.drawImage(this.a.y, x + 10, y, x + rectangle.width - 10, y + 20, 40, 41, 50, 61, null);
                                graphics.drawImage(this.a.y, x + rectangle.width - 10, y, x + rectangle.width, y + 20, 50, 41, 60, 61, null);
                                if (m == 0) {
                                    break Label_1069;
                                }
                            }
                            graphics.setFont(this.k);
                            final int x2 = rectangle.x;
                        }
                        final int n16 = n15;
                        final int y2 = rectangle.y;
                        graphics.drawImage(this.a.y, n16, y2, n16 + 10, y2 + 20, 0, 41, 10, 61, null);
                        graphics.drawImage(this.a.y, n16 + 10, y2, n16 + rectangle.width - 10, y2 + 20, 10, 41, 20, 61, null);
                        graphics.drawImage(this.a.y, n16 + rectangle.width - 10, y2, n16 + rectangle.width, y2 + 20, 20, 41, 30, 61, null);
                    }
                    graphics.setColor((Color)this.g.elementAt(n14));
                    int n17 = 4;
                    int n18 = 0;
                    final FontMetrics fontMetrics = (n14 != this.b) ? this.m : this.n;
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
                    final Image y3 = this.a.y;
                    Label_1337: {
                        if (m == 0) {
                            Label_1269: {
                                if (y3 != null) {
                                    final boolean startsWith = c[n14].substring(0, n18).startsWith("#");
                                    if (m == 0) {
                                        if (startsWith) {
                                            break Label_1269;
                                        }
                                        graphics.drawImage(this.a.y, n19, n20, n19 + 15, n20 + 14, 61, 41, 76, 55, this);
                                    }
                                    if (m == 0) {
                                        break Label_1337;
                                    }
                                }
                            }
                            final Image y4 = this.a.y;
                        }
                        if (y3 != null) {
                            final boolean startsWith2 = c[n14].substring(0, n18).startsWith("#");
                            if (m == 0) {
                                if (startsWith2) {
                                    graphics.drawImage(this.a.y, n19, n20, n19 + 15, n20 + 14, 77, 41, 92, 55, this);
                                }
                            }
                        }
                    }
                    Graphics graphics2 = graphics;
                    if (m == 0) {
                        if (graphics.getColor() != Color.red) {
                            graphics.setColor(this.a.j);
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
                graphics.setFont(this.k);
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
                array2[n] = this.p.get(components[n]);
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
        final boolean b = b2 = this.a.S.equalsIgnoreCase(db.z[2]);
        if (m == 0) {
            if (b) {
                return new Insets(24, 1, 1, 1);
            }
            final boolean equalsIgnoreCase;
            b2 = (equalsIgnoreCase = this.a.S.equalsIgnoreCase(db.z[0]));
        }
        if (m == 0) {
            if (b) {
                return new Insets(1, 72, 1, 1);
            }
            b2 = this.a.S.equalsIgnoreCase(db.z[1]);
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
        return this.b;
    }
    
    public Component e() {
        return this.getComponent(this.b);
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
            Label_0089: {
                if (m == 0) {
                    break Label_0089;
                }
                db db = this;
                if (m == 0) {
                    if (!((Rectangle)this.h.elementAt(n3)).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        ++n3;
                        break Label_0089;
                    }
                    db = this;
                }
                db.a.Eb.show(this, mouseEvent.getX(), mouseEvent.getY());
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
            if (this.k != this.getFont()) {
                this.k = this.getFont();
                this.l = new Font(this.k.getName(), 1, this.k.getSize());
                this.m = this.getFontMetrics(this.k);
                this.n = this.getFontMetrics(this.l);
                this.o = this.m.getHeight();
            }
            db = this;
        }
        final Dimension size = db.getSize();
        db db2 = null;
        Label_0212: {
            Label_0191: {
                if (m == 0) {
                    if (size.width == this.f && size.height == this.e) {
                        db2 = this;
                        if (m != 0) {
                            break Label_0212;
                        }
                        if (this.d != null) {
                            break Label_0191;
                        }
                    }
                    this.d = this.createImage(size.width, size.height);
                }
                db db3 = this;
                if (m == 0) {
                    if (this.c != null) {
                        this.c.dispose();
                    }
                    this.c = this.d.getGraphics();
                    this.f = size.width;
                    db3 = this;
                }
                db3.e = size.height;
            }
            this.a(this.c);
            graphics.drawImage(this.d, 0, 0, this);
            db2 = this;
        }
        db2.paint(graphics);
    }
    
    public void a(final Component component, final int n) {
        final int m = fb.m;
        this.p.remove(component);
        int n5;
        int b;
        int n4;
        int n3;
        final int n2 = n3 = (n4 = (b = (n5 = ((component instanceof v) ? 1 : 0))));
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
                    n3 = (n6 = (n4 = (b = (n5 = ((component instanceof x) ? 1 : 0)))));
                }
                if (m == 0) {
                    if (n2 != 0) {
                        ((x)component).a();
                        if (m == 0) {
                            break Label_0084;
                        }
                    }
                    n4 = (n3 = (b = (n5 = ((component instanceof z) ? 1 : 0))));
                }
                if (m != 0) {
                    break Label_0088;
                }
                if (n3 != 0) {
                    this.a.Vb = null;
                    this.a.Ub = null;
                }
            }
            n4 = (n5 = this.b);
        }
        int n7 = n;
        int n8 = n;
        final int n9;
        if (m == 0) {
            Label_0126: {
                if (n4 >= n) {
                    n9 = (n5 = this.b);
                    if (m != 0) {
                        break Label_0126;
                    }
                    if (n9 > 0) {
                        --this.b;
                    }
                }
                super.remove(n);
                b = this.b;
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
                        n10 = (componentCount = (n11 = this.b));
                        if (m != 0) {
                            break Label_0159;
                        }
                        if (n10 > 0) {
                            --this.b;
                        }
                    }
                    n11 = this.b;
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
                    this.b(this.b);
                }
            }
            int componentCount;
            n11 = (componentCount = this.getComponentCount());
        }
        if (m == 0) {
            if (n12 == 0) {
                this.a(db.z[6], this.a.Rb, true);
                this.a.Tb.b.b(0);
            }
            this.g.removeElementAt(n);
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
                    if (!((Rectangle)this.h.elementAt(n2)).contains(mouseEvent.getX(), mouseEvent.getY())) {
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
        this.g.setElementAt(color, n);
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
    
    public void b(final int b) {
        final int m = fb.m;
        this.b = b;
        final Component component2;
        final Component component = component2 = this.getComponent(b);
        if (m == 0) {
            if (component2 == null) {
                return;
            }
            ((CardLayout)this.getLayout()).show(this, component.getName());
            this.invalidate();
            this.validate();
            this.repaint();
            this.a.Tb.d.repaint();
        }
        boolean b3;
        final boolean b2 = b3 = (component2 instanceof v);
        if (m == 0) {
            if (b2) {
                ((v)component).n.requestFocus();
                final String r = ((v)component).r;
                final int k = ((v)component).k;
                final String s = ((v)component).s;
                String s2 = "";
                final String s3 = r;
                Label_0235: {
                    if (m == 0) {
                        if (s3 != null) {
                            s2 = String.valueOf(s2) + r;
                        }
                        s2 = String.valueOf(s2) + db.z[5] + k + db.z[7];
                        if (m != 0) {
                            break Label_0235;
                        }
                    }
                    if (s3 != null) {
                        s2 = String.valueOf(s2) + s;
                    }
                    this.a.tb = r;
                    this.a.q(s2);
                }
                this.a.e();
            }
            final boolean b4;
            b3 = (b4 = (component instanceof x));
        }
        final x x;
        Label_0325: {
            if (m == 0) {
                if (b2) {
                    ((x)component).f.requestFocus();
                    this.a.sb = ((x)component).c;
                    this.a.q(((x)component).c);
                    this.a.e();
                }
                x = (x)component;
                if (m != 0) {
                    break Label_0325;
                }
                b3 = (x instanceof cb);
            }
            if (!b3) {
                return;
            }
            this.a.sb = db.z[6];
        }
        ((cb)x).b.requestFocus();
        this.a.q(String.valueOf(((cb)component).e) + db.z[9] + this.a.n + db.z[8] + this.a.wb);
        this.a.e();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "Z_#e".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0016';
                    break;
                }
                case 1: {
                    c2 = ':';
                    break;
                }
                case 2: {
                    c2 = 'E';
                    break;
                }
                case 3: {
                    c2 = '\u0011';
                    break;
                }
                default: {
                    c2 = '\t';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "DS\"y}".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0016';
                    break;
                }
                case 1: {
                    c4 = ':';
                    break;
                }
                case 2: {
                    c4 = 'E';
                    break;
                }
                case 3: {
                    c4 = '\u0011';
                    break;
                }
                default: {
                    c4 = '\t';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "BU5".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0016';
                    break;
                }
                case 1: {
                    c6 = ':';
                    break;
                }
                case 2: {
                    c6 = 'E';
                    break;
                }
                case 3: {
                    c6 = '\u0011';
                    break;
                }
                default: {
                    c6 = '\t';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "B['AffO5".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0016';
                    break;
                }
                case 1: {
                    c8 = ':';
                    break;
                }
                case 2: {
                    c8 = 'E';
                    break;
                }
                case 3: {
                    c8 = '\u0011';
                    break;
                }
                default: {
                    c8 = '\t';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "Y\\#".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0016';
                    break;
                }
                case 1: {
                    c10 = ':';
                    break;
                }
                case 2: {
                    c10 = 'E';
                    break;
                }
                case 3: {
                    c10 = '\u0011';
                    break;
                }
                default: {
                    c10 = '\t';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "6a".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0016';
                    break;
                }
                case 1: {
                    c12 = ':';
                    break;
                }
                case 2: {
                    c12 = 'E';
                    break;
                }
                case 3: {
                    c12 = '\u0011';
                    break;
                }
                default: {
                    c12 = '\t';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "EN$e|e".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0016';
                    break;
                }
                case 1: {
                    c14 = ':';
                    break;
                }
                case 2: {
                    c14 = 'E';
                    break;
                }
                case 3: {
                    c14 = '\u0011';
                    break;
                }
                default: {
                    c14 = '\t';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "K\u001a\u007f".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0016';
                    break;
                }
                case 1: {
                    c16 = ':';
                    break;
                }
                case 2: {
                    c16 = 'E';
                    break;
                }
                case 3: {
                    c16 = '\u0011';
                    break;
                }
                default: {
                    c16 = '\t';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "6ge1)".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0016';
                    break;
                }
                case 1: {
                    c18 = ':';
                    break;
                }
                case 2: {
                    c18 = 'E';
                    break;
                }
                case 3: {
                    c18 = '\u0011';
                    break;
                }
                default: {
                    c18 = '\t';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "6ae".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0016';
                    break;
                }
                case 1: {
                    c20 = ':';
                    break;
                }
                case 2: {
                    c20 = 'E';
                    break;
                }
                case 3: {
                    c20 = '\u0011';
                    break;
                }
                default: {
                    c20 = '\t';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "TU1ef{".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\u0016';
                    break;
                }
                case 1: {
                    c22 = ':';
                    break;
                }
                case 2: {
                    c22 = 'E';
                    break;
                }
                case 3: {
                    c22 = '\u0011';
                    break;
                }
                default: {
                    c22 = '\t';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        db.z = z;
    }
}

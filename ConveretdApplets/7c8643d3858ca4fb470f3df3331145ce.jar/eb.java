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
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class eb extends Panel implements MouseListener, MouseMotionListener, ActionListener
{
    private final esChat a;
    public int b;
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
    int r;
    private static String[] z;
    
    public eb(final esChat a) {
        this.a = a;
        this.r = 0;
        this.f = 0;
        this.g = 0;
        this.b = 0;
        this.h = new Vector();
        this.j = null;
        this.k = 4;
        this.q = new Hashtable();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.i = new Vector();
        this.setForeground(a.j);
        this.setLayout(new CardLayout());
        a.Kb = new PopupMenu(eb.z[4]);
        final MenuItem menuItem = new MenuItem(a.a(3, "", "", ""));
        final MenuItem menuItem2 = new MenuItem(a.a(4, "", "", ""));
        final MenuItem menuItem3 = new MenuItem(a.a(5, "", "", ""));
        final MenuItem menuItem4 = new MenuItem(a.a(6, "", "", ""));
        final MenuItem menuItem5 = new MenuItem(a.a(53, "", "", ""));
        final MenuItem menuItem6 = new MenuItem(a.a(54, "", "", ""));
        final Menu menu = new Menu(a.a(55, "", "", ""));
        a.Kb.add(menuItem);
        a.Kb.add(menuItem2);
        a.Kb.addSeparator();
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        a.Kb.add(menu);
        this.add(a.Kb);
        menuItem.setFont(new Font("", 1, 11));
        menu.addActionListener(this);
        a.Kb.addActionListener(this);
    }
    
    public void a() {
        final boolean r = d.r;
        int n = this.getComponentCount() - 1;
        while (true) {
            Label_0037: {
                if (!r) {
                    break Label_0037;
                }
                final Component a = this.a(n);
                if (a instanceof w) {
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
        final boolean r = d.r;
        int n = this.getComponentCount() - 1;
        while (true) {
            Label_0037: {
                if (!r) {
                    break Label_0037;
                }
                final Component a = this.a(n);
                if (a instanceof y) {
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
        final boolean r = d.r;
        boolean b5;
        boolean equals;
        boolean b4;
        boolean b3;
        final boolean b2;
        final boolean b = b2 = (b3 = (b4 = (equals = (b5 = (actionEvent.getSource() instanceof PopupMenu)))));
        final boolean b6;
        final boolean b7;
        Label_0056: {
            if (!r) {
                if (!b) {
                    b6 = (b3 = (b4 = (equals = (b5 = (actionEvent.getSource() instanceof Menu)))));
                    if (r) {
                        break Label_0056;
                    }
                    if (!b6) {
                        return;
                    }
                }
                b7 = (equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(3, "", "", ""))));
            }
        }
        if (!r) {
            if (b) {
                this.a(this.a(this.b), this.b);
                return;
            }
            equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(4, "", "", "")));
        }
        if (!r) {
            if (b6) {
                final boolean b8 = this.a(this.b) instanceof w;
                if (!r && b8) {
                    goto Label_0137;
                }
                if (b8) {
                    this.a();
                    if (!r) {
                        return;
                    }
                }
                this.b();
                if (!r) {
                    return;
                }
            }
            equals = (b5 = actionEvent.getActionCommand().equals(this.a.a(5, "", "", "")));
        }
        if (!r) {
            if (b7) {
                this.a.W = eb.z[1];
                this.invalidate();
                this.validate();
                this.repaint();
                if (!r) {
                    return;
                }
            }
            equals = (b4 = (b5 = actionEvent.getActionCommand().equals(this.a.a(6, "", "", ""))));
        }
        if (!r) {
            if (b7) {
                this.a.W = eb.z[5];
                this.invalidate();
                this.validate();
                this.repaint();
                if (!r) {
                    return;
                }
            }
            b5 = (equals = actionEvent.getActionCommand().equals(this.a.a(53, "", "", "")));
        }
        if (!r) {
            if (equals) {
                this.a.W = eb.z[0];
                this.invalidate();
                this.validate();
                this.repaint();
                if (!r) {
                    return;
                }
            }
            b5 = actionEvent.getActionCommand().equals(this.a.a(54, "", "", ""));
        }
        if (b5) {
            this.a.W = eb.z[2];
            this.invalidate();
            this.validate();
            this.repaint();
        }
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (!d.r) {
            if (o != component.getName()) {
                this.q.put(component, o);
            }
        }
        super.addImpl(component, component.getName(), n);
        this.repaint();
    }
    
    public void a(final String s, final Component component, final boolean b) {
        final boolean r = d.r;
        Runtime.getRuntime().gc();
        this.h.addElement(this.a.j);
        eb eb = this;
        Label_0070: {
            if (!r) {
                this.i.addElement(new Rectangle());
                if (b) {
                    this.add(component, s, 0);
                    if (!r) {
                        break Label_0070;
                    }
                }
                eb = this;
            }
            eb.add(component, s);
        }
        boolean equals;
        final boolean b2 = equals = this.a.jb.equals(eb.z[6]);
        final int componentCount;
        if (!r) {
            if (b2) {
                componentCount = this.getComponentCount();
                if (!r) {
                    if (componentCount > 1) {
                        final boolean b3 = equals = (this.a(0) instanceof db);
                        if (!r) {
                            if (b3) {
                                this.a.Zb.b.a(this.a.Xb, 0);
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
        final boolean r = d.r;
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
        final int n4 = equalsIgnoreCase = (n5 = (this.a.W.equalsIgnoreCase(eb.z[0]) ? 1 : 0));
        int width = 0;
        Label_0238: {
            if (!r) {
                if (n4 != 0) {
                    n = 0;
                    n2 = 0;
                    n3 = size.height;
                    width = 72;
                    if (!r) {
                        break Label_0238;
                    }
                }
                final int n6;
                equalsIgnoreCase = (n6 = (n5 = (this.a.W.equalsIgnoreCase(eb.z[2]) ? 1 : 0)));
            }
            if (!r) {
                if (n4 != 0) {
                    n = 0;
                    n2 = size.width - 72;
                    n3 = size.height;
                    width = 72;
                    if (!r) {
                        break Label_0238;
                    }
                }
                n5 = (equalsIgnoreCase = (this.a.W.equalsIgnoreCase(eb.z[1]) ? 1 : 0));
            }
            if (!r) {
                if (equalsIgnoreCase != 0) {
                    n = 0;
                    n2 = 0;
                    n3 = 24;
                    width = size.width;
                    if (!r) {
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
        final int n9 = equalsIgnoreCase2 = (this.a.W.equalsIgnoreCase(eb.z[0]) ? 1 : 0);
        int equalsIgnoreCase4;
        while (true) {
        Label_0445:
            while (true) {
                int n12 = 0;
                Label_0439: {
                    if (!r) {
                        if (n9 == 0) {
                            final boolean equalsIgnoreCase3;
                            final int n10 = (equalsIgnoreCase3 = this.a.W.equalsIgnoreCase(eb.z[2])) ? 1 : 0;
                            if (r || n10 == 0) {
                                final int n11;
                                if (!r) {
                                    if (n10 >= size.width - 22) {
                                        n8 = (n11 = (size.width - 22) / componentCount);
                                        if (!r) {
                                            if (n11 < 22) {
                                                n8 = 22;
                                            }
                                        }
                                    }
                                }
                                n12 = n11;
                                if (r) {
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
                            if (!r) {
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
            equalsIgnoreCase4 = (this.a.W.equalsIgnoreCase(eb.z[0]) ? 1 : 0);
            if (r) {
                continue;
            }
            break;
        }
        Label_0719: {
            Label_0605: {
                if (!r) {
                    if (equalsIgnoreCase4 == 0) {
                        final boolean equalsIgnoreCase5 = this.a.W.equalsIgnoreCase(eb.z[2]);
                        if (r) {
                            break Label_0719;
                        }
                        if (!equalsIgnoreCase5) {
                            break Label_0605;
                        }
                    }
                    graphics.drawImage(this.a.z, n2, 0, n2 + width, 5, 125, 39, 225, 44, this);
                    graphics.drawImage(this.a.z, n2, 5, n2 + width, n3 - 5, 125, 44, 225, 58, this);
                    graphics.drawImage(this.a.z, n2, n3 - 5, n2 + width, n3, 125, 58, 225, 63, this);
                }
                if (!r) {
                    break Label_0719;
                }
            }
            graphics.drawImage(this.a.z, 0, n, 10, n + 24, 125, 39, 135, 63, this);
            graphics.drawImage(this.a.z, 10, n, size.width - 80, n + 24, 135, 39, 145, 63, this);
            graphics.drawImage(this.a.z, size.width - 80, n, size.width, n + 24, 145, 39, 225, 63, this);
        }
        int n14 = 0;
        while (true) {
        Label_1164_Outer:
            while (true) {
                Label_1636: {
                    if (!r) {
                        break Label_1636;
                    }
                    final Object element = this.i.elementAt(n14);
                    final Rectangle rectangle = (Rectangle)element;
                    final int n15 = n14;
                    final int b = this.b;
                    Label_1096: {
                        Label_1088: {
                            int x2 = 0;
                            Label_0949: {
                                if (!r) {
                                    if (n15 == b) {
                                        this.h.setElementAt(this.getForeground(), n14);
                                        graphics.setFont(this.m);
                                        final int x = rectangle.x;
                                        final int y = rectangle.y;
                                        graphics.drawImage(this.a.z, x, y, x + 10, y + 20, 30, 41, 40, 61, null);
                                        graphics.drawImage(this.a.z, x + 10, y, x + rectangle.width - 10, y + 20, 40, 41, 50, 61, null);
                                        graphics.drawImage(this.a.z, x + rectangle.width - 10, y, x + rectangle.width, y + 20, 50, 41, 60, 61, null);
                                        if (!r) {
                                            break Label_1096;
                                        }
                                    }
                                    final int n16;
                                    x2 = (n16 = n14);
                                    if (r) {
                                        break Label_0949;
                                    }
                                    final int c2 = this.c;
                                }
                                if (n15 != b) {
                                    break Label_1088;
                                }
                                graphics.setFont(this.l);
                                x2 = rectangle.x;
                            }
                            final int n17 = x2;
                            final int y2 = rectangle.y;
                            graphics.drawImage(this.a.z, n17, y2, n17 + 10, y2 + 20, 0, 41, 10, 61, null);
                            graphics.drawImage(this.a.z, n17 + 10, y2, n17 + rectangle.width - 10, y2 + 20, 10, 41, 20, 61, null);
                            graphics.drawImage(this.a.z, n17 + rectangle.width - 10, y2, n17 + rectangle.width, y2 + 20, 20, 41, 30, 61, null);
                            if (!r) {
                                break Label_1096;
                            }
                        }
                        graphics.setFont(this.l);
                    }
                    graphics.setColor((Color)this.h.elementAt(n14));
                    int n18 = 4;
                    int n19 = 0;
                    final FontMetrics fontMetrics = (n14 != this.b) ? this.n : this.o;
                    int n20;
                    int n21;
                    while (true) {
                    Label_1195:
                        while (true) {
                            Label_1183: {
                                if (!r) {
                                    break Label_1183;
                                }
                                n18 += fontMetrics.charWidth(c[n14].charAt(n19));
                                if (n18 >= rectangle.width - 16) {
                                    break Label_1195;
                                }
                                ++n19;
                            }
                            if (n19 < c[n14].length()) {
                                continue Label_1164_Outer;
                            }
                            break;
                        }
                        n20 = rectangle.x + 3;
                        n21 = rectangle.y + 3;
                        if (r) {
                            continue;
                        }
                        break;
                    }
                    final Image z = this.a.z;
                    Label_1364: {
                        if (!r) {
                            Label_1296: {
                                if (z != null) {
                                    final boolean startsWith = c[n14].substring(0, n19).startsWith("#");
                                    if (!r) {
                                        if (startsWith) {
                                            break Label_1296;
                                        }
                                        graphics.drawImage(this.a.z, n20, n21, n20 + 15, n21 + 14, 61, 41, 76, 55, this);
                                    }
                                    if (!r) {
                                        break Label_1364;
                                    }
                                }
                            }
                            final Image z2 = this.a.z;
                        }
                        if (z != null) {
                            final boolean startsWith2 = c[n14].substring(0, n19).startsWith("#");
                            if (!r) {
                                if (startsWith2) {
                                    graphics.drawImage(this.a.z, n20, n21, n20 + 15, n21 + 14, 77, 41, 92, 55, this);
                                }
                            }
                        }
                    }
                    Graphics graphics2 = graphics;
                    if (!r) {
                        if (graphics.getColor() != Color.red) {
                            graphics.setColor(this.a.j);
                        }
                        graphics2 = graphics;
                    }
                    graphics2.drawString(c[n14].substring(0, n19), rectangle.x + 19, rectangle.y + 15);
                    int n24;
                    int n23;
                    final int n22 = n23 = (n24 = n14);
                    Label_1632: {
                        if (!r) {
                            if (n22 <= 0) {
                                break Label_1632;
                            }
                            final int n25;
                            n23 = (n25 = (n24 = n14));
                        }
                        int n27;
                        final int n26 = n27 = this.b;
                        if (!r) {
                            if (n22 != n26) {
                                break Label_1632;
                            }
                            n23 = n14;
                            final int c3 = this.c;
                        }
                        final int r2;
                        final int n28;
                        Label_1565: {
                            if (!r) {
                                if (n23 == n26) {
                                    r2 = this.r;
                                    n28 = rectangle.x + (rectangle.width - 16);
                                    if (r) {
                                        break Label_1565;
                                    }
                                    if (r2 > n28) {
                                        graphics.drawImage(this.a.z, rectangle.x + rectangle.width - 17, rectangle.y + 3, rectangle.x + rectangle.width - 2, rectangle.y + 18, 196, 92, 211, 107, null);
                                        if (!r) {
                                            break Label_1632;
                                        }
                                    }
                                }
                                if (r) {
                                    break Label_1632;
                                }
                                n27 = this.b;
                            }
                        }
                        if (r2 == n28) {
                            graphics.drawImage(this.a.z, rectangle.x + rectangle.width - 17, rectangle.y + 3, rectangle.x + rectangle.width - 2, rectangle.y + 18, 212, 92, 227, 107, null);
                        }
                    }
                    ++n14;
                }
                if (n14 < componentCount) {
                    continue;
                }
                break;
            }
            final Object element = graphics;
            if (!r) {
                graphics.setFont(this.l);
                return;
            }
            continue;
        }
    }
    
    public String[] c() {
        final boolean r = d.r;
        final int componentCount = this.getComponentCount();
        final String[] array = new String[componentCount];
        final Component[] components = this.getComponents();
        int n = 0;
        while (true) {
            Label_0049: {
                if (!r) {
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
            if (!r) {
                return array2;
            }
            continue;
        }
    }
    
    public Component a(final int n) {
        return this.getComponent(n);
    }
    
    public Insets getInsets() {
        final boolean r = d.r;
        this.getFontMetrics(this.getFont());
        boolean b2;
        final boolean b = b2 = this.a.W.equalsIgnoreCase(eb.z[1]);
        if (!r) {
            if (b) {
                return new Insets(24, 1, 1, 1);
            }
            final boolean equalsIgnoreCase;
            b2 = (equalsIgnoreCase = this.a.W.equalsIgnoreCase(eb.z[0]));
        }
        if (!r) {
            if (b) {
                return new Insets(1, 72, 1, 1);
            }
            b2 = this.a.W.equalsIgnoreCase(eb.z[2]);
        }
        if (b2) {
            return new Insets(0, 4, 0, 76);
        }
        return new Insets(0, 4, 24, 4);
    }
    
    protected int a(final Object o) {
        final boolean r = d.r;
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        int n = 0;
        while (true) {
            Label_0026: {
                if (!r) {
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
        if (!r && n2 == componentCount) {
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
        final boolean r = d.r;
        final String[] c = this.c();
        int n = 0;
        while (true) {
            while (true) {
                Label_0037: {
                    if (!r) {
                        break Label_0037;
                    }
                    final boolean equalsIgnoreCase;
                    int n2 = (equalsIgnoreCase = s.equalsIgnoreCase(c[n])) ? 1 : 0;
                    if (!r) {
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
            if (!r) {
                return n3;
            }
            continue;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.c = -1;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.c = 0;
        this.r = mouseEvent.getX();
        final int componentCount;
        final int n = componentCount = this.getComponentCount();
        if (!r && componentCount < 1) {
            return;
        }
        int c = componentCount;
        while (true) {
            Label_0084: {
                if (!r) {
                    break Label_0084;
                }
                eb eb = this;
                if (!r) {
                    if (!((Rectangle)this.i.elementAt(c)).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        ++c;
                        break Label_0084;
                    }
                    this.c = c;
                    eb = this;
                }
                eb.repaint();
                return;
            }
            if (c >= n) {
                return;
            }
            continue;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.a(mouseEvent);
        final int n = mouseEvent.getModifiers() & 0x4;
        if (!r) {
            if (n <= 0) {
                return;
            }
            this.getComponentCount();
        }
        final int n2 = n;
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (!r) {
                    break Label_0093;
                }
                final Rectangle rectangle = this.i.elementAt(n3);
                if (!r) {
                    if (!rectangle.contains(mouseEvent.getX(), mouseEvent.getY())) {
                        ++n3;
                        break Label_0093;
                    }
                    this.a.Kb.show(this, mouseEvent.getX(), mouseEvent.getY());
                }
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
        final boolean r = d.r;
        eb eb = this;
        if (!r) {
            if (this.l != this.getFont()) {
                this.l = this.getFont();
                this.m = new Font(this.l.getName(), 1, this.l.getSize());
                this.n = this.getFontMetrics(this.l);
                this.o = this.getFontMetrics(this.m);
                this.p = this.n.getHeight();
            }
            eb = this;
        }
        final Dimension size = eb.getSize();
        eb eb2 = null;
        Label_0285: {
            Label_0191: {
                if (!r) {
                    if (size.width == this.g && size.height == this.f) {
                        eb2 = this;
                        if (r) {
                            break Label_0285;
                        }
                        if (this.e != null) {
                            break Label_0191;
                        }
                    }
                    this.e = this.createImage(size.width, size.height);
                }
                eb eb3 = this;
                if (!r) {
                    if (this.d != null) {
                        this.d.dispose();
                    }
                    this.d = this.e.getGraphics();
                    this.g = size.width;
                    eb3 = this;
                }
                eb3.f = size.height;
            }
            this.a(this.d);
            this.d.drawImage(this.a.z, 0, 0, 4, size.height, 125, 69, 129, 87, this);
            this.d.drawImage(this.a.z, size.width - 4, 0, size.width, size.height, 221, 69, 225, 87, this);
            graphics.drawImage(this.e, 0, 0, this);
            eb2 = this;
        }
        eb2.paint(graphics);
    }
    
    public void a(final Component component, final int n) {
        final boolean r = d.r;
        this.q.remove(component);
        int n5;
        int b;
        int n4;
        int n3;
        final int n2 = n3 = (n4 = (b = (n5 = ((component instanceof w) ? 1 : 0))));
        Label_0088: {
            Label_0084: {
                if (!r) {
                    if (n2 != 0) {
                        ((w)component).a();
                        if (!r) {
                            break Label_0084;
                        }
                    }
                    final int n6;
                    n3 = (n6 = (n4 = (b = (n5 = ((component instanceof y) ? 1 : 0)))));
                }
                if (!r) {
                    if (n2 != 0) {
                        ((y)component).a();
                        if (!r) {
                            break Label_0084;
                        }
                    }
                    n4 = (n3 = (b = (n5 = ((component instanceof ab) ? 1 : 0))));
                }
                if (r) {
                    break Label_0088;
                }
                if (n3 != 0) {
                    this.a.bc = null;
                    this.a.ac = null;
                }
            }
            n4 = (n5 = this.b);
        }
        int n7 = n;
        int n8 = n;
        final int n9;
        if (!r) {
            Label_0126: {
                if (n4 >= n) {
                    n9 = (n5 = this.b);
                    if (r) {
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
            if (!r) {
                Label_0159: {
                    if (n9 >= n8) {
                        final int componentCount;
                        n10 = (componentCount = (n11 = this.b));
                        if (r) {
                            break Label_0159;
                        }
                        if (n10 > 0) {
                            --this.b;
                        }
                    }
                    n11 = this.b;
                }
                if (r) {
                    break Label_0190;
                }
                n7 = -1;
            }
            if (n10 > n7) {
                n12 = (n11 = this.getComponentCount());
                if (r) {
                    break Label_0190;
                }
                if (n12 > 0) {
                    this.c(this.b);
                }
            }
            int componentCount;
            n11 = (componentCount = this.getComponentCount());
        }
        if (!r) {
            if (n12 == 0) {
                this.a(eb.z[3], this.a.Xb, true);
                this.a.Zb.b.c(0);
            }
            this.h.removeElementAt(n);
            if (r) {
                return;
            }
            n11 = ((component instanceof db) ? 1 : 0);
        }
        if (n11 == 0) {}
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void b(final int n) {
        if (n >= this.getComponentCount()) {
            return;
        }
        this.a(this.getComponent(n), n);
    }
    
    protected void a(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int componentCount;
        final int n = componentCount = this.getComponentCount();
        if (!r && componentCount < 1) {
            return;
        }
        int n2 = componentCount;
        while (true) {
            Label_0128: {
                if (!r) {
                    break Label_0128;
                }
                final Rectangle rectangle = this.i.elementAt(n2);
                int n5;
                int n4;
                final int n3 = n4 = (n5 = (rectangle.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0));
                if (!r) {
                    if (n3 == 0) {
                        ++n2;
                        break Label_0128;
                    }
                    final int n6;
                    n4 = (n6 = (n5 = n2));
                }
                Label_0119: {
                    if (!r) {
                        if (n3 <= 0) {
                            break Label_0119;
                        }
                        n5 = (n4 = n2);
                    }
                    final int b = this.b;
                    if (!r) {
                        if (n4 != b) {
                            break Label_0119;
                        }
                        n5 = mouseEvent.getX();
                        final int n7 = rectangle.x + (rectangle.width - 16);
                    }
                    if (n5 > b) {
                        this.b(n2);
                        this.repaint();
                        if (!r) {
                            return;
                        }
                    }
                }
                this.c(n2);
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
        final boolean r = d.r;
        final Component[] components = this.getComponents();
        int n = 0;
        while (true) {
            Label_0020: {
                if (!r) {
                    break Label_0020;
                }
                ++n;
            }
            if (components[n] == component) {
                if (n < this.getComponentCount()) {
                    this.c(n);
                }
                return;
            }
            continue;
        }
    }
    
    public void c(final int b) {
        final boolean r = d.r;
        this.b = b;
        final Component component2;
        final Component component = component2 = this.getComponent(b);
        if (!r) {
            if (component2 == null) {
                return;
            }
            ((CardLayout)this.getLayout()).show(this, component.getName());
            this.invalidate();
            this.validate();
            this.repaint();
            this.a.Zb.e.repaint();
        }
        boolean b3;
        final boolean b2 = b3 = (component2 instanceof w);
        if (!r) {
            if (b2) {
                ((w)component).n.requestFocus();
                final String r2 = ((w)component).r;
                final int k = ((w)component).k;
                final String s = ((w)component).s;
                String s2 = "";
                final String s3 = r2;
                Label_0236: {
                    if (!r) {
                        if (s3 != null) {
                            s2 = String.valueOf(s2) + r2;
                        }
                        s2 = String.valueOf(s2) + eb.z[9] + k + eb.z[10];
                        if (r) {
                            break Label_0236;
                        }
                    }
                    if (s3 != null) {
                        s2 = String.valueOf(s2) + s;
                    }
                    this.a.yb = r2;
                    this.a.q(s2);
                }
                this.a.e();
            }
            final boolean b4;
            b3 = (b4 = (component instanceof y));
        }
        final y y;
        Label_0325: {
            if (!r) {
                if (b2) {
                    ((y)component).f.requestFocus();
                    this.a.xb = ((y)component).c;
                    this.a.q(((y)component).c);
                    this.a.e();
                }
                y = (y)component;
                if (r) {
                    break Label_0325;
                }
                b3 = (y instanceof db);
            }
            if (!b3) {
                return;
            }
            this.a.xb = eb.z[3];
        }
        ((db)y).b.requestFocus();
        this.a.q(String.valueOf(((db)component).e) + eb.z[7] + this.a.n + eb.z[8] + this.a.Bb);
        this.a.e();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = ")\u0003t\u0003".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'e';
                    break;
                }
                case 1: {
                    c2 = 'f';
                    break;
                }
                case 2: {
                    c2 = '\u0012';
                    break;
                }
                case 3: {
                    c2 = 'w';
                    break;
                }
                default: {
                    c2 = ';';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "1\tb".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'e';
                    break;
                }
                case 1: {
                    c4 = 'f';
                    break;
                }
                case 2: {
                    c4 = '\u0012';
                    break;
                }
                case 3: {
                    c4 = 'w';
                    break;
                }
                default: {
                    c4 = ';';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "7\u000fu\u001fO".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'e';
                    break;
                }
                case 1: {
                    c6 = 'f';
                    break;
                }
                case 2: {
                    c6 = '\u0012';
                    break;
                }
                case 3: {
                    c6 = 'w';
                    break;
                }
                default: {
                    c6 = ';';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "6\u0012s\u0003N\u0016".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'e';
                    break;
                }
                case 1: {
                    c8 = 'f';
                    break;
                }
                case 2: {
                    c8 = '\u0012';
                    break;
                }
                case 3: {
                    c8 = 'w';
                    break;
                }
                default: {
                    c8 = ';';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "1\u0007p'T\u0015\u0013b".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'e';
                    break;
                }
                case 1: {
                    c10 = 'f';
                    break;
                }
                case 2: {
                    c10 = '\u0012';
                    break;
                }
                case 3: {
                    c10 = 'w';
                    break;
                }
                default: {
                    c10 = ';';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "'\tf\u0003T\b".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'e';
                    break;
                }
                case 1: {
                    c12 = 'f';
                    break;
                }
                case 2: {
                    c12 = '\u0012';
                    break;
                }
                case 3: {
                    c12 = 'w';
                    break;
                }
                default: {
                    c12 = ';';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "*\u0000t".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'e';
                    break;
                }
                case 1: {
                    c14 = 'f';
                    break;
                }
                case 2: {
                    c14 = '\u0012';
                    break;
                }
                case 3: {
                    c14 = 'w';
                    break;
                }
                default: {
                    c14 = ';';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "E=2".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'e';
                    break;
                }
                case 1: {
                    c16 = 'f';
                    break;
                }
                case 2: {
                    c16 = '\u0012';
                    break;
                }
                case 3: {
                    c16 = 'w';
                    break;
                }
                default: {
                    c16 = ';';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "E;2W\u001b".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'e';
                    break;
                }
                case 1: {
                    c18 = 'f';
                    break;
                }
                case 2: {
                    c18 = '\u0012';
                    break;
                }
                case 3: {
                    c18 = 'w';
                    break;
                }
                default: {
                    c18 = ';';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "E=".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'e';
                    break;
                }
                case 1: {
                    c20 = 'f';
                    break;
                }
                case 2: {
                    c20 = '\u0012';
                    break;
                }
                case 3: {
                    c20 = 'w';
                    break;
                }
                default: {
                    c20 = ';';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "8F(".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'e';
                    break;
                }
                case 1: {
                    c22 = 'f';
                    break;
                }
                case 2: {
                    c22 = '\u0012';
                    break;
                }
                case 3: {
                    c22 = 'w';
                    break;
                }
                default: {
                    c22 = ';';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        eb.z = z;
    }
}

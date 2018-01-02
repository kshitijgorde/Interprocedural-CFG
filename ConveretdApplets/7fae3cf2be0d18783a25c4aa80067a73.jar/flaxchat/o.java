// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.Color;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.Point;
import flaxchat.a.h;
import java.awt.event.MouseEvent;
import flaxchat.c.a;
import flaxchat.e.c;
import flaxchat.e.d;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import flaxchat.d.b;
import flaxchat.i.f;
import java.awt.Image;
import java.awt.Component;
import flaxchat.i.m;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import flaxchat.i.i;

public class o extends i implements ActionListener, MouseListener
{
    private n k;
    private m l;
    private static String[] z;
    
    public o(final n k, final boolean b) {
        super(b);
        this.k = k;
        (this.l = new m()).a(this);
        this.addMouseListener(this);
    }
    
    public void a(final String s, final Component component, final Image image) {
        if (this.l.a(s) != null) {
            return;
        }
        final f f = new f(s, s, image);
        f.c(1);
        f.addMouseListener(this);
        f.a(o.z[15], component);
        if (flaxchat.d.b.a(o.z[16], true)) {
            final l layout = new l(this, f);
            f.setLayout(layout);
            f.add(layout);
        }
        this.l.a(f);
        this.add(f);
        this.doLayout();
        this.b();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof m) {
            this.k.d(actionEvent.getActionCommand());
            return;
        }
        if (o.z[5].equals(actionEvent.getActionCommand())) {
            this.k.e(((MenuItem)actionEvent.getSource()).getName());
            return;
        }
        if (o.z[9].equals(actionEvent.getActionCommand())) {
            this.k.q();
            return;
        }
        if (o.z[4].equals(actionEvent.getActionCommand())) {
            this.k.s();
            return;
        }
        if (o.z[2].equals(actionEvent.getActionCommand())) {
            this.k.r();
            return;
        }
        if (o.z[14].equals(actionEvent.getActionCommand())) {
            this.k.f(((MenuItem)actionEvent.getSource()).getName());
            return;
        }
        if (o.z[0].equals(actionEvent.getActionCommand())) {
            this.b(o.z[13]);
            return;
        }
        if (o.z[8].equals(actionEvent.getActionCommand())) {
            this.b(o.z[12]);
            return;
        }
        if (o.z[6].equals(actionEvent.getActionCommand())) {
            this.b(o.z[10]);
            return;
        }
        if (o.z[1].equals(actionEvent.getActionCommand())) {
            this.b(o.z[11]);
        }
    }
    
    public void b(final String s) {
        flaxchat.e.b layout = null;
        Label_0088: {
            if (s == o.z[11] || s == o.z[10]) {
                layout = new d(3, 3, 50, flaxchat.d.b.a(o.z[19], -1));
                if (n.w == 0) {
                    break Label_0088;
                }
            }
            layout = new c(flaxchat.d.b.a(o.z[20], 5), 3, flaxchat.d.b.a(o.z[21], -1), flaxchat.d.b.a(o.z[18], 100));
        }
        layout.a(s);
        this.k.remove(this);
        this.setLayout(layout);
        this.k.add(this);
        this.k.b();
        this.k.b();
    }
    
    public void removeAll() {
        super.removeAll();
        this.l.a();
    }
    
    public int a() {
        return this.getComponentCount();
    }
    
    public int a(final a a) {
        final int w = n.w;
        final int a2 = this.a();
        int n = 0;
        while (true) {
            Label_0061: {
                if (w == 0) {
                    break Label_0061;
                }
                final f f = (f)this.getComponent(n);
                if (f.a((Object)o.z[15]) == a) {
                    this.remove(f);
                    this.l.b(f);
                    return n;
                }
                ++n;
            }
            if (n >= a2) {
                return -1;
            }
            continue;
        }
    }
    
    public f c(final String s) {
        return this.l.b(s);
    }
    
    public f d(final String s) {
        return this.l.a(s);
    }
    
    public String a(final int n) {
        if (n < 0 || n >= this.a()) {
            return null;
        }
        return ((f)this.getComponent(n)).h();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.a.h.b(mouseEvent)) {
            return;
        }
        if (mouseEvent.getSource() == this) {
            this.a(mouseEvent.getPoint());
            return;
        }
        this.a(mouseEvent.getComponent());
    }
    
    private void a(final Point point) {
        final PopupMenu b = flaxchat.a.h.b();
        this.c(b, o.z[17]);
        b.addSeparator();
        final Menu a = this.a(b, o.z[7]);
        this.a(a, o.z[8]);
        this.a(a, o.z[0]);
        this.a(a, o.z[6]);
        this.a(a, o.z[1]);
        this.add(b);
        b.show(this, point.x, point.y);
    }
    
    private void a(final Component component) {
        final f f = (f)component;
        final PopupMenu b = flaxchat.a.h.b();
        this.c(b, o.z[5]).setName(f.i());
        final Menu b2 = this.b(b, o.z[3]);
        this.a(b2, o.z[4]);
        this.a(b2, o.z[2]);
        b.addSeparator();
        final Menu a = this.a(b, o.z[7]);
        this.a(a, o.z[8]);
        this.a(a, o.z[0]);
        this.a(a, o.z[6]);
        this.a(a, o.z[1]);
        flaxchat.a.h.a(b, component);
    }
    
    private Menu a(final PopupMenu popupMenu, final String s) {
        final Menu menu = new Menu(flaxchat.d.c.a(s));
        popupMenu.add(menu);
        return menu;
    }
    
    private Menu b(final PopupMenu popupMenu, final String s) {
        final Menu menu = new Menu(flaxchat.d.c.a(s));
        popupMenu.add(menu);
        return menu;
    }
    
    private MenuItem c(final PopupMenu popupMenu, final String actionCommand) {
        final MenuItem add = popupMenu.add(new MenuItem(flaxchat.d.c.a(actionCommand)));
        add.setActionCommand(actionCommand);
        add.addActionListener(this);
        return add;
    }
    
    private void a(final Menu menu, final String actionCommand) {
        final MenuItem add = menu.add(new MenuItem(flaxchat.d.c.a(actionCommand)));
        add.setActionCommand(actionCommand);
        add.addActionListener(this);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void e(final String s) {
        final f d = this.d(s);
        if (d == null) {
            return;
        }
        if (d.a()) {
            return;
        }
        d.setForeground(Color.red);
    }
    
    protected Image d() {
        return flaxchat.d.d.f(o.z[22]);
    }
    
    public int b(final a a) {
        final int w = n.w;
        final f d = this.d(a.j());
        int n = 0;
        while (true) {
            Label_0040: {
                if (w == 0) {
                    break Label_0040;
                }
                if (this.getComponent(n) == d) {
                    return n;
                }
                ++n;
            }
            if (n >= this.getComponentCount()) {
                return -1;
            }
            continue;
        }
    }
    
    static n a(final o o) {
        return o.k;
    }
    
    static {
        o.z = new String[] { z(z("h7,(\u000ex")), z(z("b# ")), z(z("Z#)(\u0010}#5-\u001d\u007f\u0003>;\u0015}")), z(z("V'+ \u000f|+4")), z(z("^8\"%,t,$,\u000et.\";\u0015Z#7(\b")), z(z("Z#7(\b")), z(z("b-+")), z(z("v-5<\u0012d/")), z(z("p1&.\u0015")), z(z("Y'7:\u0015\u007f+\f(\fp6")), z(z("F'4=")), z(z("T#4=")), z(z("B-2=\u0014")), z(z("_-5=\u0014")), z(z("y'7:\u0015\u007f+g\"\u001da#3")), z(z("A\u0003\t\f")), z(z("b*(>?}-4,(p ")), z(z("W.&1?y#3")), z(z("|+)\u0001\u0013c+=&\u0012e#+\u000b\te6('+x&3!")), z(z("|#?\u000b\te6('+x&3!")), z(z("|#?\u001a\fp!\"")), z(z("|#?\u0001\u0013c+=&\u0012e#+\u000b\te6('+x&3!")), z(z("e#%=\u0013~.%(\u000e")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '|';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0011';
                    break;
                }
                case 1: {
                    c2 = 'B';
                    break;
                }
                case 2: {
                    c2 = 'G';
                    break;
                }
                case 3: {
                    c2 = 'I';
                    break;
                }
                default: {
                    c2 = '|';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}

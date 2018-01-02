// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.awt.Color;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.Point;
import flaxchat.e.g;
import java.awt.event.MouseEvent;
import flaxchat.c.a;
import java.awt.LayoutManager;
import flaxchat.g.c;
import flaxchat.g.d;
import flaxchat.i.b;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import flaxchat.d.f;
import java.awt.Image;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import flaxchat.d.i;

public class n extends i implements ActionListener, MouseListener
{
    private m h;
    private flaxchat.d.m i;
    private static String[] z;
    
    public n(final m h, final boolean b) {
        super(b);
        this.h = h;
        (this.i = new flaxchat.d.m()).a(this);
        this.addMouseListener(this);
    }
    
    public void a(final String s, final Component component, final Image image) {
        if (this.i.a(s) != null) {
            return;
        }
        final f f = new f(s, s, image);
        f.c(1);
        f.addMouseListener(this);
        f.a(n.z[9], component);
        this.i.a(f);
        this.add(f);
        this.doLayout();
        this.b();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof flaxchat.d.m) {
            this.h.d(actionEvent.getActionCommand());
            return;
        }
        if (n.z[14].equals(actionEvent.getActionCommand())) {
            this.h.e(((MenuItem)actionEvent.getSource()).getName());
            return;
        }
        if (n.z[2].equals(actionEvent.getActionCommand())) {
            this.h.q();
            return;
        }
        if (n.z[4].equals(actionEvent.getActionCommand())) {
            this.h.s();
            return;
        }
        if (n.z[3].equals(actionEvent.getActionCommand())) {
            this.h.r();
            return;
        }
        if (n.z[15].equals(actionEvent.getActionCommand())) {
            this.h.f(((MenuItem)actionEvent.getSource()).getName());
            return;
        }
        if (n.z[8].equals(actionEvent.getActionCommand())) {
            this.b(n.z[12]);
            return;
        }
        if (n.z[6].equals(actionEvent.getActionCommand())) {
            this.b(n.z[13]);
            return;
        }
        if (n.z[5].equals(actionEvent.getActionCommand())) {
            this.b(n.z[11]);
            return;
        }
        if (n.z[0].equals(actionEvent.getActionCommand())) {
            this.b(n.z[10]);
        }
    }
    
    public void b(final String s) {
        flaxchat.g.b layout = null;
        Label_0068: {
            if (s == n.z[10] || s == n.z[11]) {
                layout = new d(0, 1, 50, flaxchat.i.b.a(n.z[17], -1));
                if (!m.s) {
                    break Label_0068;
                }
            }
            layout = new c(1, 0, flaxchat.i.b.a(n.z[16], -1));
        }
        layout.a(s);
        this.h.remove(this);
        this.setLayout(layout);
        this.h.add(this);
        this.h.b();
        this.h.b();
    }
    
    public void removeAll() {
        super.removeAll();
        this.i.a();
    }
    
    public int a() {
        return this.getComponentCount();
    }
    
    public int a(final a a) {
        final boolean s = m.s;
        final int a2 = this.a();
        int n = 0;
        while (true) {
            Label_0061: {
                if (!s) {
                    break Label_0061;
                }
                final f f = (f)this.getComponent(n);
                if (f.a((Object)flaxchat.n.z[9]) == a) {
                    this.remove(f);
                    this.i.b(f);
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
        return this.i.b(s);
    }
    
    public f d(final String s) {
        return this.i.a(s);
    }
    
    public String a(final int n) {
        if (n < 0 || n >= this.a()) {
            return null;
        }
        return ((f)this.getComponent(n)).e();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.b(mouseEvent)) {
            return;
        }
        if (mouseEvent.getSource() == this) {
            this.a(mouseEvent.getPoint());
            return;
        }
        this.a(mouseEvent.getComponent());
    }
    
    private void a(final Point point) {
        final PopupMenu b = flaxchat.e.g.b();
        this.c(b, n.z[1]);
        b.addSeparator();
        this.c(b, n.z[2]);
        this.c(b, n.z[4]);
        this.c(b, n.z[3]);
        b.addSeparator();
        final Menu a = this.a(b, n.z[7]);
        this.a(a, n.z[6]);
        this.a(a, n.z[8]);
        this.a(a, n.z[5]);
        this.a(a, n.z[0]);
        this.add(b);
        b.show(this, point.x, point.y);
    }
    
    private void a(final Component component) {
        final f f = (f)component;
        final PopupMenu b = flaxchat.e.g.b();
        this.c(b, n.z[14]).setName(f.f());
        final Menu b2 = this.b(b, n.z[18]);
        this.a(b2, n.z[4]);
        this.a(b2, n.z[3]);
        b.addSeparator();
        final Menu a = this.a(b, n.z[7]);
        this.a(a, n.z[6]);
        this.a(a, n.z[8]);
        this.a(a, n.z[5]);
        this.a(a, n.z[0]);
        flaxchat.e.g.a(b, component);
    }
    
    private Menu a(final PopupMenu popupMenu, final String s) {
        final Menu menu = new Menu(flaxchat.i.c.a(s));
        popupMenu.add(menu);
        return menu;
    }
    
    private Menu b(final PopupMenu popupMenu, final String s) {
        final Menu menu = new Menu(flaxchat.i.c.a(s));
        popupMenu.add(menu);
        return menu;
    }
    
    private MenuItem c(final PopupMenu popupMenu, final String actionCommand) {
        final MenuItem add = popupMenu.add(new MenuItem(flaxchat.i.c.a(actionCommand)));
        add.setActionCommand(actionCommand);
        add.addActionListener(this);
        return add;
    }
    
    private void a(final Menu menu, final String actionCommand) {
        final MenuItem add = menu.add(new MenuItem(flaxchat.i.c.a(actionCommand)));
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
        return flaxchat.i.d.f(n.z[19]);
    }
    
    public int b(final a a) {
        final boolean s = m.s;
        final f d = this.d(a.j());
        int n = 0;
        while (true) {
            Label_0040: {
                if (!s) {
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
    
    static {
        n.z = new String[] { z(z("o\u0010H")), z(z("Z\u001dNO{t\u0010[")), z(z("T\u0014_DQr\u0018dVH}\u0005")), z(z("W\u0010AVTp\u0010]SYr0VEQp")), z(z("S\u000bJ[hy\u001fLRJy\u001dJEQW\u0010_VL")), z(z("o\u001eC")), z(z("}\u0002NPQ")), z(z("{\u001e]BVi\u001c")), z(z("e\u0004DVJu")), z(z("L0ar")), z(z("Y\u0010\\C")), z(z("K\u0014\\C")), z(z("R\u001e]CP")), z(z("O\u001eZCP")), z(z("W\u0010_VL")), z(z("t\u0014_DQr\u0018\u000f\\Yl\u0010[")), z(z("q\u0010W\u007fWn\u0018UXVh\u0010CuMh\u0005@You\u0015[_")), z(z("q\u0010WuMh\u0005@You\u0015[_")), z(z("[\u0014C^Kq\u0018\\")), z(z("h\u0010MCWs\u001dMVJ")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '8';
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
                    c2 = '\u001c';
                    break;
                }
                case 1: {
                    c2 = 'q';
                    break;
                }
                case 2: {
                    c2 = '/';
                    break;
                }
                case 3: {
                    c2 = '7';
                    break;
                }
                default: {
                    c2 = '8';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}

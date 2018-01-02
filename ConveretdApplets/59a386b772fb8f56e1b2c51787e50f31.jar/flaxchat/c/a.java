// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.h.d;
import java.awt.Container;
import flaxchat.b.h;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.util.Stack;
import java.awt.Point;
import flaxchat.a.j;
import flaxchat.i.b;
import flaxchat.q;
import java.awt.event.MouseEvent;
import java.net.URL;
import flaxchat.h.c;
import java.awt.event.ActionEvent;
import java.awt.Component;
import flaxchat.e.g;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import flaxchat.m;
import flaxchat.d.i;

public abstract class a extends i
{
    protected m h;
    public static int i;
    private static String[] z;
    
    public a(final m h) {
        this.h = h;
        this.setLayout(new BorderLayout(0, 0));
    }
    
    public void a(final Graphics graphics) {
        flaxchat.e.g.a(graphics, this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (a.z[3].equals(actionCommand)) {
            this.h.g(((c)actionEvent.getSource()).a());
            return;
        }
        if (a.z[2].equals(actionCommand)) {
            this.h.l().getAppletContext().showDocument((URL)actionEvent.getSource(), a.z[0]);
            return;
        }
        if (a.z[1].equals(actionCommand)) {
            this.h.a((flaxchat.h.g)actionEvent.getSource());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void b() {
        this.getParent().invalidate();
        this.getParent().validate();
    }
    
    public q a() {
        return this.h.h();
    }
    
    public void b(final String s) {
    }
    
    public void a(final String s, final String s2) {
    }
    
    public void c(final String s) {
    }
    
    public final void d(final String s) {
        this.a(flaxchat.i.b.b(a.z[6], a.z[5]), s);
    }
    
    public void d() {
        this.h.d(this.j());
        this.h.g().requestFocus();
    }
    
    public void e() {
    }
    
    public void f() {
    }
    
    public void a(final Component component, final String s, final String s2, final flaxchat.h.g g) {
        new j(this.h, component, s, s2, g, "", flaxchat.i.b.a(a.z[4]).get(s)).actionPerformed(new ActionEvent(this, 1001, s));
    }
    
    protected void a(final Component component, final String s, final String s2, final flaxchat.h.g g, final Point point) {
        a(this.h, component, s, s2, g, point);
    }
    
    public static void a(final m m, final Component component, final String s, final String s2, final flaxchat.h.g g, final Point point) {
        final int i = a.i;
        final Hashtable a = b.a(s);
        if (a == null || a.size() == 0) {
            return;
        }
        final Stack<PopupMenu> stack = new Stack<PopupMenu>();
        final PopupMenu b = g.b();
        stack.push(b);
        int n = 0;
        PopupMenu popupMenu = stack.firstElement();
        MenuItem menuItem = null;
        for (int j = 1; j < 100; ++j) {
            final String s3 = a.get("n" + j);
            if (s3 == null) {
                break;
            }
            final String trim = s3.trim();
            if (trim.length() == 0) {
                break;
            }
            final String[] e = e(trim);
            final int int1 = Integer.parseInt(e[0]);
            final String s4 = e[1];
            Label_0214: {
                if (int1 < n) {
                    final int n2 = n - int1;
                    int n3 = 0;
                    while (true) {
                        Label_0173: {
                            if (i == 0) {
                                break Label_0173;
                            }
                            final PopupMenu popupMenu2 = stack.pop();
                            ++n3;
                        }
                        if (n3 < n2) {
                            continue;
                        }
                        break;
                    }
                    popupMenu = stack.peek();
                    if (i == 0) {
                        break Label_0214;
                    }
                }
                if (int1 > n) {
                    stack.push((PopupMenu)menuItem);
                    popupMenu = (PopupMenu)menuItem;
                }
            }
            n = int1;
            final int index = s4.indexOf(":");
            if (s4.equals("-")) {
                popupMenu.addSeparator();
                if (i == 0) {
                    continue;
                }
            }
            if (index == -1) {
                final Menu menu = new Menu(s4);
                popupMenu.add(menu);
                menuItem = menu;
                if (i == 0) {
                    continue;
                }
            }
            final MenuItem a2 = a(m, component, s, s2, g, s4.substring(0, index), s4.substring(index + 1));
            popupMenu.add(a2);
            menuItem = a2;
        }
        component.add(b);
        b.show(component, point.x, point.y);
    }
    
    private static MenuItem a(final m m, final Component component, final String s, final String s2, final flaxchat.h.g g, final String s3, final String s4) {
        final j j = new j(m, component, s, s2, g, s3, s4);
        final MenuItem menuItem = new MenuItem(s3);
        menuItem.addActionListener(j);
        return menuItem;
    }
    
    private static String[] e(final String s) {
        final int i = a.i;
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0039: {
                if (i == 0) {
                    break Label_0039;
                }
                if (s.charAt(n2) != '.') {
                    return new String[] { String.valueOf(n), s.substring(n) };
                }
                ++n;
                ++n2;
            }
            if (n2 < length) {
                continue;
            }
            break;
        }
        return new String[] { String.valueOf(n), s.substring(n) };
    }
    
    public boolean g() {
        return this.h.b(this);
    }
    
    public boolean h() {
        return !this.g();
    }
    
    public String i() {
        return this.a().g();
    }
    
    public abstract String j();
    
    public abstract h k();
    
    public abstract String b(final String p0, final String p1);
    
    public void a(final boolean b) {
    }
    
    protected String a(final int n) {
        return flaxchat.i.c.b(String.valueOf(n));
    }
    
    protected String a(final int n, final String s) {
        return flaxchat.i.c.a(String.valueOf(n), new String[] { s });
    }
    
    protected String a(final int n, final String s, final String s2) {
        return flaxchat.i.c.a(String.valueOf(n), new String[] { s, s2 });
    }
    
    protected String a(final int n, final String s, final String s2, final String s3) {
        return flaxchat.i.c.a(String.valueOf(n), new String[] { s, s2, s3 });
    }
    
    public void b(final boolean b) {
    }
    
    public void c(final boolean b) {
    }
    
    public void f(final String s) {
    }
    
    public Container l() {
        return this;
    }
    
    public flaxchat.f.c g(final String s) {
        return null;
    }
    
    public boolean h(String c) {
        final int i = a.i;
        final String[] c2 = flaxchat.i.a.c();
        if (c2 == null) {
            return false;
        }
        c = flaxchat.h.d.c(c);
        int n = 0;
        while (true) {
            Label_0043: {
                if (i == 0) {
                    break Label_0043;
                }
                if (c.indexOf(c2[n]) != -1) {
                    return true;
                }
                ++n;
            }
            if (n >= c2.length) {
                return false;
            }
            continue;
        }
    }
    
    static {
        a.z = new String[] { z(z("03%\u0001\f\u0004")), z(z("\u001a\",\u0012")), z(z("\u001a#%")), z(z("\f9(\u000e\f\n=")), z(z("\u000b><\u0002\u000e\n|*\f\u000b\f:")), z(z("la}")), z(z("\n#;\u000f\u0010\"\".#\r\u0003>;")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'b';
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
                    c2 = 'o';
                    break;
                }
                case 1: {
                    c2 = 'Q';
                    break;
                }
                case 2: {
                    c2 = 'I';
                    break;
                }
                case 3: {
                    c2 = '`';
                    break;
                }
                default: {
                    c2 = 'b';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}

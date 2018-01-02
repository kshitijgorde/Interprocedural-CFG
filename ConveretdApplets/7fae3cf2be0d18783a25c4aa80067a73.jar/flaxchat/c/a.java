// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.f.d;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.util.Stack;
import java.awt.Point;
import flaxchat.g.j;
import flaxchat.d.b;
import flaxchat.r;
import java.awt.event.MouseEvent;
import flaxchat.f.g;
import java.net.URL;
import flaxchat.f.c;
import java.awt.event.ActionEvent;
import java.awt.Component;
import flaxchat.a.h;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import flaxchat.n;
import flaxchat.i.i;

public abstract class a extends i
{
    protected n k;
    public static int l;
    private static String[] z;
    
    public a(final n k) {
        this.k = k;
        this.setLayout(new BorderLayout(0, 0));
    }
    
    public void a(final Graphics graphics) {
        flaxchat.a.h.a(graphics, this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (a.z[5].equals(actionCommand)) {
            this.k.g(((c)actionEvent.getSource()).a());
            return;
        }
        if (a.z[3].equals(actionCommand)) {
            this.k.l().getAppletContext().showDocument((URL)actionEvent.getSource(), a.z[2]);
            return;
        }
        if (a.z[4].equals(actionCommand)) {
            this.k.a((g)actionEvent.getSource());
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
    
    public r a() {
        return this.k.h();
    }
    
    public void b(final String s) {
    }
    
    public void a(final String s, final String s2) {
    }
    
    public void c(final String s) {
    }
    
    public final void d(final String s) {
        this.a(flaxchat.d.b.b(a.z[0], a.z[1]), s);
    }
    
    public void d() {
        this.k.d(this.j());
        this.k.g().requestFocus();
    }
    
    public void e() {
    }
    
    public void f() {
    }
    
    public void a(final Component component, final String s, final String s2, final g g) {
        new j(this.k, component, s, s2, g, "", flaxchat.d.b.a(a.z[6]).get(s)).actionPerformed(new ActionEvent(this, 1001, s));
    }
    
    protected void a(final Component component, final String s, final String s2, final g g, final Point point) {
        a(this.k, component, s, s2, g, point);
    }
    
    public static void a(final n n, final Component component, final String s, final String s2, final g g, final Point point) {
        final int l = a.l;
        final Hashtable a = b.a(s);
        final Stack<PopupMenu> stack = new Stack<PopupMenu>();
        final PopupMenu b = h.b();
        stack.push(b);
        int n2 = 0;
        PopupMenu popupMenu = stack.firstElement();
        MenuItem menuItem = null;
        for (int i = 1; i < 100; ++i) {
            final String s3 = a.get("n" + i);
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
            Label_0200: {
                if (int1 < n2) {
                    final int n3 = n2 - int1;
                    int n4 = 0;
                    while (true) {
                        Label_0159: {
                            if (l == 0) {
                                break Label_0159;
                            }
                            final PopupMenu popupMenu2 = stack.pop();
                            ++n4;
                        }
                        if (n4 < n3) {
                            continue;
                        }
                        break;
                    }
                    popupMenu = stack.peek();
                    if (l == 0) {
                        break Label_0200;
                    }
                }
                if (int1 > n2) {
                    stack.push((PopupMenu)menuItem);
                    popupMenu = (PopupMenu)menuItem;
                }
            }
            n2 = int1;
            final int index = s4.indexOf(":");
            if (s4.equals("-")) {
                popupMenu.addSeparator();
                if (l == 0) {
                    continue;
                }
            }
            if (index == -1) {
                final Menu menu = new Menu(s4);
                popupMenu.add(menu);
                menuItem = menu;
                if (l == 0) {
                    continue;
                }
            }
            final MenuItem a2 = a(n, component, s, s2, g, s4.substring(0, index), s4.substring(index + 1));
            popupMenu.add(a2);
            menuItem = a2;
        }
        component.add(b);
        b.show(component, point.x, point.y);
    }
    
    private static MenuItem a(final n n, final Component component, final String s, final String s2, final g g, final String s3, final String s4) {
        final j j = new j(n, component, s, s2, g, s3, s4);
        final MenuItem menuItem = new MenuItem(s3);
        menuItem.addActionListener(j);
        return menuItem;
    }
    
    private static String[] e(final String s) {
        final int l = a.l;
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0039: {
                if (l == 0) {
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
        return this.k.b(this);
    }
    
    public boolean h() {
        return !this.g();
    }
    
    public String i() {
        return this.a().g();
    }
    
    public abstract String j();
    
    public abstract flaxchat.b.h k();
    
    public abstract String b(final String p0, final String p1);
    
    public void a(final boolean b) {
    }
    
    protected String a(final int n) {
        return flaxchat.d.c.b(String.valueOf(n));
    }
    
    protected String a(final int n, final String s) {
        return flaxchat.d.c.a(String.valueOf(n), new String[] { s });
    }
    
    protected String a(final int n, final String s, final String s2) {
        return flaxchat.d.c.a(String.valueOf(n), new String[] { s, s2 });
    }
    
    protected String a(final int n, final String s, final String s2, final String s3) {
        return flaxchat.d.c.a(String.valueOf(n), new String[] { s, s2, s3 });
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
    
    public flaxchat.h.c g(final String s) {
        return null;
    }
    
    public boolean h(String c) {
        final int l = a.l;
        final String[] c2 = flaxchat.d.a.c();
        if (c2 == null) {
            return false;
        }
        c = flaxchat.f.d.c(c);
        int n = 0;
        while (true) {
            Label_0043: {
                if (l == 0) {
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
    
    public abstract void i(final String p0);
    
    public abstract void m();
    
    public abstract f n();
    
    static {
        a.z = new String[] { z(z("<l\b1c\u0014m\u001d\u001d~5q\b")), z(z("Z.N")), z(z("\u0006|\u0016?\u007f2")), z(z(",l\u0016")), z(z(",m\u001f,")), z(z(":v\u001b0\u007f<r")), z(z("=q\u000f<}<3\u00192x:u")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0011';
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
                    c2 = 'Y';
                    break;
                }
                case 1: {
                    c2 = '\u001e';
                    break;
                }
                case 2: {
                    c2 = 'z';
                    break;
                }
                case 3: {
                    c2 = '^';
                    break;
                }
                default: {
                    c2 = '\u0011';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}

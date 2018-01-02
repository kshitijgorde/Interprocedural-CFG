// 
// Decompiled by Procyon v0.5.30
// 

package epop;

import java.net.MalformedURLException;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.awt.Font;
import java.util.Vector;
import java.util.Hashtable;
import java.net.URL;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class Epop extends Applet implements MouseListener, ActionListener, MouseMotionListener, Runnable
{
    String f;
    Thread d;
    Thread n;
    Color c;
    Color m;
    Color g;
    b l;
    URL b;
    String h;
    Hashtable a;
    Vector e;
    a j;
    b k;
    boolean i;
    
    public void init() {
        final String parameter = this.getParameter("bg");
        try {
            this.c = Color.decode(parameter);
        }
        catch (Exception ex) {}
        final String parameter2 = this.getParameter("fg");
        try {
            this.m = Color.decode(parameter2);
        }
        catch (Exception ex2) {}
        final String parameter3 = this.getParameter("roll");
        try {
            this.g = Color.decode(parameter3);
        }
        catch (Exception ex3) {}
        int size = this.getFont().getSize();
        if (this.getToolkit().getScreenSize().height < 601) {
            --size;
        }
        if (this.getToolkit().getScreenSize().height < 769) {
            --size;
        }
        final Font font = new Font("SansSerif", 0, size + 2);
        final Font font2 = new Font("SansSerif", 0, size);
        final Font font3 = new Font("SansSerif", 2, size);
        final FontMetrics fontMetrics = this.getFontMetrics(font2);
        int n = this.getToolkit().getScreenSize().height / ((fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()) * 2);
        if (this.getToolkit().getScreenSize().height < 601) {
            n /= 2;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.getParameter("xoff"));
        }
        catch (NumberFormatException ex4) {
            int1 = 10;
        }
        final String parameter4 = this.getParameter("arr");
        Color color = Color.black;
        try {
            color = Color.decode(parameter4);
        }
        catch (Exception ex5) {}
        final String parameter5 = this.getParameter("ad");
        if (parameter5 != null) {
            this.h = this.c(parameter5);
            this.b = this.b(this.d(parameter5));
        }
        this.e = new Vector();
        String s;
        int n2;
        b b;
        for (s = this.getParameter("md0"), n2 = 0; s != null; s = this.getParameter("md" + n2)) {
            b = new b(s);
            b.setBackground(this.c);
            b.setForeground(this.m);
            b.setFont(font);
            b.b = int1;
            b.h = color;
            this.e.addElement(b);
            ++n2;
        }
        final String parameter6 = this.getParameter("replist");
        if (parameter6 != null) {
            this.a = new Hashtable();
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter6, "|");
            while (stringTokenizer.hasMoreTokens()) {
                this.a.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
            }
        }
        this.j = new a();
        if (this.i) {
            this.j.setLayout(new GridLayout(this.e.size() + 1, 1));
        }
        else {
            this.j.setLayout(new GridLayout(this.e.size(), 1));
        }
        this.f = this.getParameter("dframe");
        final Enumeration<b> elements = (Enumeration<b>)this.e.elements();
        while (elements.hasMoreElements()) {
            final b b2 = elements.nextElement();
            this.j.add(b2);
            int n3 = 0;
            String s2 = this.getParameter("sd" + this.e.indexOf(b2) + "-" + n3);
            if (s2 != null && this.a != null) {
                s2 = this.a(s2);
            }
            String c = s2;
            Menu menu;
            final PopupMenu g = (PopupMenu)(menu = new PopupMenu());
            while (s2 != null) {
                final int index = s2.indexOf("|");
                if (index >= 0) {
                    final MenuItem menuItem = new MenuItem(s2.substring(0, index));
                    menuItem.setActionCommand(s2.substring(index + 1));
                    menuItem.setFont(font2);
                    menu.add(menuItem);
                }
                ++n3;
                c = s2;
                s2 = this.getParameter("sd" + this.e.indexOf(b2) + "-" + n3);
                if (s2 != null && this.a != null) {
                    s2 = this.a(s2);
                }
                if (n3 % n == 0 && s2 != null) {
                    final Menu menu2 = new Menu("More...");
                    menu2.setFont(font3);
                    menu2.addActionListener(this);
                    menu.addSeparator();
                    menu.add(menu2);
                    menu = menu2;
                }
            }
            if (n3 <= 1) {
                final int index2 = c.indexOf("|");
                if (index2 > 0) {
                    b2.c = c.substring(index2 + 1);
                    b2.a = c.substring(0, index2);
                }
                else {
                    b2.c = c;
                    b2.a = this.f;
                }
            }
            else {
                g.addActionListener(this);
                b2.add(b2.g = g);
            }
        }
        if (this.i) {
            final b b3 = new b("Free Web Tools");
            b3.setBackground(this.c);
            b3.setForeground(this.m);
            b3.setFont(font);
            b3.b = int1;
            b3.h = color;
            b3.c = "http://www.europa.bc.ca/software";
            b3.a = "_top";
            this.e.addElement(b3);
            this.j.add(b3);
            ++n2;
        }
        this.j.addMouseMotionListener(this);
        this.j.addMouseListener(this);
        this.setLayout(new BorderLayout());
        final Canvas canvas = new Canvas();
        canvas.setBackground(Color.black);
        canvas.setSize(this.getSize().width, 1);
        this.add(canvas, "North");
        this.add(this.j, "Center");
    }
    
    public void start() {
    }
    
    public void stop() {
        if (this.d != null) {
            this.d.stop();
        }
        this.d = null;
        if (this.n != null) {
            this.n.stop();
        }
        this.n = null;
    }
    
    public void run() {
        if (this.l != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                Thread.sleep(Math.max(0L, currentTimeMillis + 60L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            this.l.g.show(this.l, this.l.getSize().width, 0);
            this.l = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public String a(String string) {
        final Enumeration<String> keys = this.a.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this.a.get(s);
            if (string.indexOf(s) > -1) {
                string = String.valueOf(string.substring(0, string.indexOf(s))) + s2 + string.substring(string.indexOf(s) + s.length());
            }
        }
        return string;
    }
    
    public String c(final String s) {
        final int index = s.indexOf("|");
        if (index >= 0) {
            return s.substring(0, index);
        }
        return this.f;
    }
    
    public String d(final String s) {
        final int index = s.indexOf("|");
        if (index >= 0) {
            return s.substring(index + 1);
        }
        return s;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.k != null) {
            this.d.stop();
            this.d = null;
            this.k.setForeground(this.m);
            this.k.i = this.k.d;
            this.k.repaint();
            this.k = null;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getY() / ((this.getSize().height - 1) / this.e.size());
        b b = null;
        if (n < this.e.size()) {
            b = this.e.elementAt(n);
        }
        if (b != null) {
            if (b != this.k) {
                if (this.k != null) {
                    this.d.stop();
                    this.d = null;
                    this.k.setForeground(this.m);
                    this.k.i = this.k.d;
                    this.k.repaint();
                }
                (this.k = b).setForeground(this.g);
                b.repaint();
                (this.d = new Thread(b)).start();
            }
            if (b.g != null) {
                this.l = b;
                (this.n = new Thread(this)).start();
                return;
            }
            this.a(b.a, b.c);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getY() / ((this.getSize().height - 1) / this.e.size());
        b k = null;
        if (n < this.e.size()) {
            k = this.e.elementAt(n);
        }
        if (k != null && k != this.k) {
            if (this.k != null) {
                this.d.stop();
                this.d = null;
                this.k.setForeground(this.m);
                this.k.i = this.k.d;
                this.k.repaint();
            }
            (this.k = k).setForeground(this.g);
            k.repaint();
            (this.d = new Thread(k)).start();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getY() / ((this.getSize().height - 1) / this.e.size());
        b k = null;
        if (n < this.e.size()) {
            k = this.e.elementAt(n);
        }
        if (k != null && k != this.k) {
            if (this.k != null) {
                this.d.stop();
                this.d = null;
                this.k.setForeground(this.m);
                this.k.i = this.k.d;
                this.k.repaint();
            }
            (this.k = k).setForeground(this.g);
            k.repaint();
            (this.d = new Thread(k)).start();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a(this.c(actionEvent.getActionCommand()), this.d(actionEvent.getActionCommand()));
    }
    
    public void a(final String s, final String s2) {
        try {
            final URL b = this.b(s2);
            if (this.b != null && this.h != null) {
                this.getAppletContext().showDocument(this.b, this.h);
            }
            Thread.sleep(100L);
            this.getAppletContext().showDocument(b, s);
        }
        catch (Exception ex) {}
    }
    
    public URL b(final String s) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            try {
                if (this.getDocumentBase().getProtocol().equalsIgnoreCase("file://")) {
                    url = new URL(this.getDocumentBase().getProtocol(), this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), String.valueOf(this.getDocumentBase().getFile().substring(0, this.getDocumentBase().getFile().lastIndexOf(File.separator))) + File.separator + s);
                    return url;
                }
                if (s.charAt(0) == '/') {
                    url = new URL(this.getDocumentBase().getProtocol(), this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), s);
                    return url;
                }
                url = new URL(this.getDocumentBase().getProtocol(), this.getDocumentBase().getHost(), this.getDocumentBase().getPort(), String.valueOf(this.getDocumentBase().getFile().substring(0, this.getDocumentBase().getFile().lastIndexOf("/"))) + "/" + s);
                return url;
            }
            catch (MalformedURLException ex2) {}
        }
        return url;
    }
    
    public Epop() {
        this.c = Color.white;
        this.m = Color.black;
        this.g = Color.white;
        this.i = true;
    }
}

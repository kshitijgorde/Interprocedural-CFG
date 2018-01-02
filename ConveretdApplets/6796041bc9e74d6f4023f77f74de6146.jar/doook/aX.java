// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Dialog;
import java.applet.Applet;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class aX extends Panel implements MouseListener, WindowListener
{
    private aZ b;
    private Window a;
    private boolean an;
    public int aA;
    public String name;
    private int ao;
    private int ap;
    private Component f;
    private Component g;
    private t g;
    private String X;
    public boolean ao;
    public Color[] a;
    public String[] d;
    public int aB;
    static Class h;
    
    public aX(final t g, final Component component, final boolean b) {
        this.ao = false;
        this.aB = -1;
        this.g = g;
        this.an = false;
        this.ao = 25;
        this.ap = 25;
        this.setBackground(new Color(238, 238, 238));
        this.aA = 0;
        if (!b) {
            this.f = component;
        }
        else {
            this.g = component;
        }
        this.addMouseListener(this);
    }
    
    public aX(final t t, final Component component) {
        this(t, component, false);
    }
    
    public aX(final t t) {
        this(t, null);
    }
    
    public aX(final t t, final Component component, final String x) {
        this(t, component, false);
        this.X = x;
    }
    
    public void resize(final int ao, final int ap) {
        super.resize(ao, ap);
        this.ao = ao;
        this.ap = ap;
    }
    
    public void a(final aG ag, final boolean b) {
        this.a(ag, b, false);
    }
    
    public void a(final aG ag, final boolean ao, final boolean b) {
        if (ag != null) {
            int n = 0;
            for (int i = 0; i < ag.b(); ++i) {
                if (this.aB == -1 || ((bs)ag.a(i)).d(this.aB)) {
                    ++n;
                }
            }
            if (n == 0) {
                this.a = null;
                this.ao = false;
                return;
            }
            this.a = new Color[n];
            if (b) {
                this.d = new String[n];
            }
            ag.a(false);
            try {
                int j = 0;
                int n2 = 0;
                while (j < ag.b()) {
                    if (this.aB == -1 || ((bs)ag.a(j)).d(this.aB)) {
                        this.a[n2] = new Color(Integer.parseInt(((bs)ag.a(j)).Y, 16));
                        if (b) {
                            this.d[n2] = ((bs)ag.a(j)).f();
                        }
                        ++n2;
                    }
                    ++j;
                }
            }
            finally {
                ag.a();
            }
            this.ao = ao;
        }
        else {
            this.a = null;
            this.ao = false;
        }
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.ao, this.ap);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void windowActivated(final WindowEvent windowEvent) {
    }
    
    public final void windowClosed(final WindowEvent windowEvent) {
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
    }
    
    public final void windowDeactivated(final WindowEvent windowEvent) {
        this.a(windowEvent);
    }
    
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public final void windowIconified(final WindowEvent windowEvent) {
    }
    
    public final void windowOpened(final WindowEvent windowEvent) {
    }
    
    public static final Window a(Component parent, final Rectangle rectangle, final boolean b) {
        boolean b2 = false;
        Window window = null;
        while (!(parent instanceof Frame)) {
            if ((parent = parent.getParent()) == null) {
                break;
            }
            if (!b2 && rectangle != null) {
                if (parent instanceof Applet) {
                    final Point locationOnScreen = parent.getLocationOnScreen();
                    rectangle.x += locationOnScreen.x;
                    rectangle.y += locationOnScreen.y;
                }
                else {
                    rectangle.x += parent.getBounds().x;
                    rectangle.y += parent.getBounds().y;
                }
            }
            if (!(b2 = (parent instanceof Window))) {
                continue;
            }
            window = (Window)parent;
            if (!b && parent instanceof Dialog) {
                break;
            }
        }
        if (b) {
            return window;
        }
        return (Window)parent;
    }
    
    public static final Frame a(final Component component, final Rectangle rectangle) {
        return (Frame)a(component, rectangle, true);
    }
    
    public final void d() {
        if (this.an) {
            this.a(true);
        }
    }
    
    public final synchronized void a(final AWTEvent awtEvent) {
        switch (awtEvent.getID()) {
            case 501: {
                if (!this.an) {
                    this.c();
                    return;
                }
                this.a(true);
            }
            case 206: {
                if (this.an) {
                    this.a(true);
                    break;
                }
                break;
            }
        }
    }
    
    public final void a(final boolean b) {
        if (b) {
            this.a.setVisible(false);
        }
        else {
            this.requestFocus();
        }
        this.an = false;
        this.repaint();
    }
    
    public final void c() {
        if (this.a != null && this.a.isVisible()) {
            this.a(true);
        }
        if (this.b != null) {
            this.b.removeAll();
            this.b = null;
        }
        this.b = new aZ(this, this.g);
        final Rectangle bounds = this.getBounds();
        Frame a = null;
        Window a2 = null;
        if (System.getProperty("java.vendor").startsWith("Sun")) {
            a2 = a(this, bounds, false);
        }
        else {
            a = a((Component)this, bounds);
        }
        if (this.a != null) {
            this.a.removeAll();
            try {
                this.a.dispose();
            }
            catch (Exception ex) {}
            this.a = null;
        }
        if (this.a == null || !this.a.equals((a != null) ? new Window(a) : new Window(a2))) {
            if (a != null) {
                this.a = new Window(a);
            }
            else {
                this.a = new Window(a2);
            }
            this.a.setLayout(new BorderLayout());
            this.a.add(this.b);
        }
        final Rectangle rectangle = bounds;
        rectangle.y += bounds.height;
        bounds.width = 300;
        bounds.height = 220;
        a((Component)this, bounds);
        this.a.setBounds(bounds);
        this.b.doLayout();
        try {
            this.a.removeWindowListener(this);
        }
        catch (Exception ex2) {}
        try {
            this.a.addWindowListener(this);
        }
        catch (Exception ex3) {}
        this.an = true;
        this.repaint();
        this.a.setVisible(true);
        this.a.pack();
        this.a.requestFocus();
    }
    
    public void a(final Color foreground) {
        this.aA = foreground.getRGB();
        this.setBackground(foreground);
        if (this.f != null && this.g != null) {
            this.f.setBackground(foreground);
            this.g.setBackground(foreground);
        }
        else if (this.f != null) {
            if (this.X != null) {
                try {
                    this.f.getClass().getMethod(this.X, (aX.h == null) ? (aX.h = a("java.awt.Color")) : aX.h).invoke(this.f, foreground);
                    this.f.repaint();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (this.f instanceof bQ) {
                ((bQ)this.f).a(foreground, Color.black);
            }
            else if (this.f instanceof TextArea) {
                this.f.setForeground(foreground);
            }
            else if (this.f instanceof TextField) {
                ((TextField)this.f).setText(bd.b(foreground));
            }
        }
        else if (this.g != null) {
            this.g.setForeground(foreground);
        }
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size = this.size();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            final Color background = this.getBackground();
            background.brighter();
            background.darker().darker();
            graphics.setColor(this.getParent().getBackground());
            graphics.drawRect(0, 0, n, n2);
            graphics.drawRect(1, 1, n - 2, n2 - 2);
            graphics.setColor(this.getBackground());
            graphics.fillRect(2, 2, n - 4, n2 - 4);
            final int n3 = n - 1;
            final int n4 = n2 - 1;
            graphics.setColor(Color.gray);
            graphics.drawLine(3, 0, n3 - 2, 0);
            graphics.drawLine(n3 - 1, 1, n3, 2);
            graphics.drawLine(n3, 3, n3, n4 - 2);
            graphics.drawLine(n3 - 1, n4 - 1, n3 - 2, n4);
            graphics.drawLine(n3 - 3, n4, 2, n4);
            graphics.drawLine(1, n4 - 1, 0, n4 - 2);
            graphics.drawLine(0, n4 - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            graphics.setColor(background);
            graphics.drawLine(1, n4 - 2, 1, 2);
            graphics.drawLine(2, 1, n3 - 2, 1);
            graphics.drawLine(2, n4 - 3, 2, 2);
            graphics.drawLine(3, 2, n3 - 2, 2);
            graphics.drawLine(2, n4 - 1, n3 - 2, n4 - 1);
            graphics.drawLine(n3 - 1, n4 - 2, n3 - 1, 2);
            graphics.drawLine(2, n4 - 2, n3 - 2, n4 - 2);
            graphics.drawLine(n3 - 2, n4 - 3, n3 - 2, 3);
        }
    }
    
    public static final void a(final Component component, final Rectangle rectangle) {
        final Dimension screenSize = component.getToolkit().getScreenSize();
        if (rectangle.y + rectangle.height + 30 > screenSize.height) {
            rectangle.y -= component.getSize().height + rectangle.height;
        }
        final int y = rectangle.y;
        final Dimension dimension = screenSize;
        if (y > (dimension.height -= rectangle.height + 30)) {
            rectangle.y = screenSize.height;
        }
        if (rectangle.y < 0) {
            rectangle.y = 0;
        }
        if (rectangle.x + component.getSize().width > 1 && rectangle.x < screenSize.width) {
            final int x = rectangle.x;
            final Dimension dimension2 = screenSize;
            if (x > (dimension2.width -= rectangle.width)) {
                rectangle.x = screenSize.width;
            }
            if (rectangle.x < 0) {
                rectangle.x = 0;
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public String a(final Color color) {
        this.name = "";
        try {
            if (this.a != null || this.d != null || this.a.length != 0 || this.d.length != 0) {
                for (int i = 0; i < this.a.length; ++i) {
                    if (this.a[i] != null && this.d[i] != null && this.a[i].equals(color)) {
                        this.name = this.d[i];
                        break;
                    }
                }
            }
            return this.name;
        }
        catch (Exception ex) {
            return this.name;
        }
    }
    
    static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}

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

public class bp extends Panel implements MouseListener, WindowListener
{
    private bq c;
    private Window a;
    private boolean ag;
    public int aw;
    private int aq;
    private int p;
    private Component f;
    private Component g;
    private as a;
    private String Z;
    public boolean ah;
    public Color[] a;
    public String[] f;
    public int ax;
    static Class a;
    
    public bp(final as a, final Component component, final boolean b) {
        this.ah = false;
        this.ax = -1;
        this.a = a;
        this.ag = false;
        this.aq = 25;
        this.p = 25;
        this.setBackground(new Color(238, 238, 238));
        this.aw = 0;
        if (!b) {
            this.f = component;
        }
        else {
            this.g = component;
        }
        this.addMouseListener(this);
    }
    
    public bp(final as as, final Component component, final String z) {
        this(as, component, false);
        this.Z = z;
    }
    
    public void resize(final int aq, final int p2) {
        super.resize(aq, p2);
        this.aq = aq;
        this.p = p2;
    }
    
    public void a(final aT at, final boolean b) {
        this.a(at, b, false);
    }
    
    public void a(final aT at, final boolean ah, final boolean b) {
        try {
            if (at != null) {
                int n = 0;
                for (int i = 0; i < at.b(); ++i) {
                    if (this.ax == -1 || ((aS)at.a(i)).a(this.ax)) {
                        ++n;
                    }
                }
                if (n == 0) {
                    this.a = null;
                    this.ah = false;
                    return;
                }
                this.a = new Color[n];
                if (b) {
                    this.f = new String[n];
                }
                at.a(false);
                try {
                    int j = 0;
                    int n2 = 0;
                    while (j < at.b()) {
                        if (this.ax == -1 || ((aS)at.a(j)).a(this.ax)) {
                            this.a[n2] = new Color(Integer.parseInt(((aS)at.a(j)).O, 16));
                            if (b) {
                                this.f[n2] = ((aS)at.a(j)).d();
                            }
                            ++n2;
                        }
                        ++j;
                    }
                }
                finally {
                    at.f();
                }
                this.ah = ah;
            }
            else {
                this.a = null;
                this.ah = false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.aq, this.p);
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
    
    public final void g() {
        if (this.ag) {
            this.a(true);
        }
    }
    
    public final synchronized void a(final AWTEvent awtEvent) {
        switch (awtEvent.getID()) {
            case 501: {
                if (!this.ag) {
                    this.c();
                    return;
                }
                this.a(true);
            }
            case 206: {
                if (this.ag) {
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
        this.ag = false;
        this.repaint();
    }
    
    public final void c() {
        if (this.a != null && this.a.isVisible()) {
            this.a(true);
        }
        if (this.c != null) {
            this.c.removeAll();
            this.c = null;
        }
        this.c = new bq(this, this.a);
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
            this.a.add(this.c);
        }
        final Rectangle rectangle = bounds;
        rectangle.y += bounds.height;
        bounds.width = 300;
        bounds.height = 220;
        a((Component)this, bounds);
        this.a.setBounds(bounds);
        this.c.doLayout();
        try {
            this.a.removeWindowListener(this);
        }
        catch (Exception ex2) {}
        try {
            this.a.addWindowListener(this);
        }
        catch (Exception ex3) {}
        this.ag = true;
        this.repaint();
        this.a.setVisible(true);
        this.a.pack();
        this.a.requestFocus();
    }
    
    public void b(final Color foreground) {
        this.aw = foreground.getRGB();
        this.setBackground(foreground);
        if (this.f != null && this.g != null) {
            this.f.setBackground(foreground);
            this.g.setBackground(foreground);
        }
        else if (this.f != null) {
            if (this.Z != null) {
                try {
                    this.f.getClass().getMethod(this.Z, (bp.a == null) ? (bp.a = a("java.awt.Color")) : bp.a).invoke(this.f, foreground);
                    this.f.repaint();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else if (this.f instanceof ag) {
                ((ag)this.f).a(foreground, Color.black);
            }
            else if (this.f instanceof TextArea) {
                this.f.setForeground(foreground);
            }
            else if (this.f instanceof TextField) {
                ((TextField)this.f).setText(aD.a(foreground));
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
    
    public String b(final Color color) {
        if (this.a == null || this.f == null || this.a.length == 0 || this.f.length == 0) {
            return "";
        }
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i].equals(color)) {
                System.out.println(this.f[i]);
                return this.f[i];
            }
        }
        return "";
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

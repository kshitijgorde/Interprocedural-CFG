// 
// Decompiled by Procyon v0.5.30
// 

package chat;

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
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public final class o extends Panel implements MouseListener, WindowListener
{
    private x a;
    private Window a;
    private boolean c;
    public int a;
    private int b;
    private int c;
    private Component a;
    private Component b;
    private bh a;
    public boolean a;
    public boolean b;
    public Color[] a;
    private Hashtable a;
    
    public o(final bh a, final Component a2) {
        this.a = a;
        this.c = false;
        this.b = 25;
        this.c = 25;
        this.a = true;
        this.b = false;
        this.setBackground(new Color(238, 238, 238));
        this.a = 0;
        this.a = a2;
        this.addMouseListener(this);
    }
    
    public o(final bh bh) {
        this(bh, null);
    }
    
    public o(final bh bh, final Component component, final Component b) {
        this(bh, component);
        this.b = b;
    }
    
    public final void resize(final int b, final int c) {
        super.resize(b, c);
        this.b = b;
        this.c = c;
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.b, this.c);
    }
    
    public final Dimension preferredSize() {
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
    
    private static Window a(Component parent, final Rectangle rectangle, final boolean b) {
        boolean b2 = false;
        Window window = null;
        while (!(parent instanceof Frame) && (parent = parent.getParent()) != null) {
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
            if (b2 = (parent instanceof Window)) {
                window = (Window)parent;
                if (!b && parent instanceof Dialog) {
                    break;
                }
                continue;
            }
        }
        if (b) {
            return window;
        }
        return (Window)parent;
    }
    
    public final void a() {
        if (this.c) {
            this.b();
        }
    }
    
    private synchronized void a(AWTEvent bounds) {
        switch (bounds.getID()) {
            default: {}
            case 501: {
                if (!this.c) {
                    if ((this = this).a != null && this.a.isVisible()) {
                        this.b();
                    }
                    if (this.a != null) {
                        this.a.removeAll();
                        this.a = null;
                    }
                    this.a = new x(this, this.a);
                    bounds = (AWTEvent)this.getBounds();
                    Frame frame = null;
                    Window a = null;
                    if (System.getProperty("java.vendor").startsWith("Sun")) {
                        a = a(this, (Rectangle)bounds, false);
                    }
                    else {
                        frame = (Frame)a(this, (Rectangle)bounds, true);
                    }
                    if (this.a != null) {
                        this.a.removeAll();
                        try {
                            this.a.dispose();
                        }
                        catch (Exception ex) {}
                        this.a = null;
                    }
                    if (this.a == null || !this.a.equals((frame == null) ? new Window(a) : new Window(frame))) {
                        if (frame != null) {
                            this.a = new Window(frame);
                        }
                        else {
                            this.a = new Window(a);
                        }
                        this.a.setLayout(new BorderLayout());
                        this.a.add(this.a);
                    }
                    final AWTEvent awtEvent = bounds;
                    ((Rectangle)awtEvent).y += ((Rectangle)bounds).height;
                    ((Rectangle)bounds).width = 300;
                    ((Rectangle)bounds).height = 220;
                    final o o = this;
                    final AWTEvent awtEvent2 = bounds;
                    final o o2 = o;
                    final Dimension screenSize = o.getToolkit().getScreenSize();
                    if (((Rectangle)awtEvent2).y + ((Rectangle)awtEvent2).height + 30 > screenSize.height) {
                        final AWTEvent awtEvent3 = awtEvent2;
                        ((Rectangle)awtEvent3).y -= o2.getSize().height + ((Rectangle)awtEvent2).height;
                    }
                    final int y = ((Rectangle)awtEvent2).y;
                    final Dimension dimension = screenSize;
                    if (y > (dimension.height -= ((Rectangle)awtEvent2).height + 30)) {
                        ((Rectangle)awtEvent2).y = screenSize.height;
                    }
                    if (((Rectangle)awtEvent2).y < 0) {
                        ((Rectangle)awtEvent2).y = 0;
                    }
                    if (((Rectangle)awtEvent2).x + o2.getSize().width > 1 && ((Rectangle)awtEvent2).x < screenSize.width) {
                        final int x = ((Rectangle)awtEvent2).x;
                        final Dimension dimension2 = screenSize;
                        if (x > (dimension2.width -= ((Rectangle)awtEvent2).width)) {
                            ((Rectangle)awtEvent2).x = screenSize.width;
                        }
                        if (((Rectangle)awtEvent2).x < 0) {
                            ((Rectangle)awtEvent2).x = 0;
                        }
                    }
                    this.a.setBounds((Rectangle)bounds);
                    this.a.doLayout();
                    try {
                        this.a.removeWindowListener(this);
                    }
                    catch (Exception ex2) {}
                    try {
                        this.a.addWindowListener(this);
                    }
                    catch (Exception ex3) {}
                    this.c = true;
                    this.repaint();
                    this.a.setVisible(true);
                    this.a.pack();
                    this.a.requestFocus();
                    return;
                }
                this.b();
            }
            case 206: {
                if (this.c) {
                    this.b();
                }
            }
        }
    }
    
    private void b() {
        this.a.setVisible(false);
        try {
            this.a.dispose();
        }
        catch (Exception ex) {}
        this.a = null;
        this.c = false;
        this.repaint();
    }
    
    public final void a(final int n) {
        int a = 0;
        if (n != 0) {
            for (int i = 0; i < this.a.r.a(); ++i) {
                if (((br)this.a.r.a(i)).a(n)) {
                    ++a;
                }
            }
            if (a == 0) {
                this.a = null;
                this.a = null;
                return;
            }
        }
        else {
            a = this.a.r.a();
        }
        this.a = new Color[a];
        this.a = new Hashtable();
        try {
            int j = 0;
            int n2 = 0;
            while (j < this.a.r.a()) {
                final br br = (br)this.a.r.a(j);
                if (n == 0 || br.a(n)) {
                    this.a[n2] = new Color(Integer.parseInt(br.c, 16));
                    this.a.put(this.a[n2], br.a);
                    ++n2;
                }
                ++j;
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    public final void a(final Color background) {
        this.a = background.getRGB();
        this.setBackground(background);
        if (this.a != null && this.b != null) {
            this.a.setBackground(background);
            this.b.setBackground(background);
        }
        else if (this.a != null) {
            if (this.a instanceof bg) {
                ((bg)this.a).a(background, Color.black);
            }
            else if (this.a instanceof TextArea) {
                this.a.setForeground(background);
            }
            else if (this.a instanceof TextField) {
                ((TextField)this.a).setText(Integer.toString(background.getRGB() & 0xFFFFFF, 16).toUpperCase());
            }
            else {
                this.a.setBackground(background);
            }
        }
        this.repaint();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size;
            final int n = (size = this.size()).width - 1;
            int n2 = size.height - 1;
            final Color background;
            (background = this.getBackground()).brighter();
            background.darker().darker();
            graphics.setColor(this.getParent().getBackground());
            graphics.drawRect(0, 0, n, n2);
            graphics.drawRect(1, 1, n - 2, n2 - 2);
            graphics.setColor(this.getBackground());
            graphics.fillRect(2, 2, n - 4, n2 - 4);
            final int n3 = n - 1;
            --n2;
            graphics.setColor(Color.gray);
            graphics.drawLine(3, 0, n3 - 2, 0);
            graphics.drawLine(n3 - 1, 1, n3, 2);
            graphics.drawLine(n3, 3, n3, n2 - 2);
            graphics.drawLine(n3 - 1, n2 - 1, n3 - 2, n2);
            graphics.drawLine(n3 - 3, n2, 2, n2);
            graphics.drawLine(1, n2 - 1, 0, n2 - 2);
            graphics.drawLine(0, n2 - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            graphics.setColor(background);
            graphics.drawLine(1, n2 - 2, 1, 2);
            graphics.drawLine(2, 1, n3 - 2, 1);
            graphics.drawLine(2, n2 - 3, 2, 2);
            graphics.drawLine(3, 2, n3 - 2, 2);
            graphics.drawLine(2, n2 - 1, n3 - 2, n2 - 1);
            graphics.drawLine(n3 - 1, n2 - 2, n3 - 1, 2);
            graphics.drawLine(2, n2 - 2, n3 - 2, n2 - 2);
            graphics.drawLine(n3 - 2, n2 - 3, n3 - 2, 3);
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.a(mouseEvent);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public final class bG extends Panel implements MouseListener, WindowListener
{
    private ac q;
    private Window q;
    public static boolean q;
    private int q;
    private int w;
    private Component q;
    private Component w;
    public Point q;
    private Color q;
    public A q;
    
    public bG(final Component q) {
        bG.q = false;
        this.q = 25;
        this.w = 25;
        this.setBackground(Color.black);
        this.q = q;
        this.addMouseListener(this);
    }
    
    public bG(final Component component, final Component w, final Color q) {
        this(component);
        this.w = w;
        this.q = q;
    }
    
    public final A q() {
        if (this.q == null) {
            this.q = W.q(3);
        }
        return this.q;
    }
    
    public final void resize(final int q, final int w) {
        super.resize(q, w);
        this.q = q;
        this.w = w;
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.q, this.w);
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
        this.q(windowEvent);
    }
    
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public final void windowIconified(final WindowEvent windowEvent) {
    }
    
    public final void windowOpened(final WindowEvent windowEvent) {
    }
    
    public final void q() {
        if (bG.q) {
            this.e();
        }
    }
    
    private synchronized void q(final AWTEvent awtEvent) {
        switch (awtEvent.getID()) {
            case 501: {
                if (!bG.q) {
                    this.w();
                    return;
                }
                this.e();
            }
            case 206: {
                if (bG.q) {
                    this.e();
                    break;
                }
                break;
            }
        }
    }
    
    private synchronized void e() {
        this.q.setVisible(false);
        bG.q = false;
        this.repaint();
    }
    
    public final synchronized void w() {
        if (this.q != null && this.q.isVisible()) {
            this.e();
        }
        if (this.q != null) {
            this.q.removeAll();
            this.q = null;
        }
        this.q = new ac(this);
        final Rectangle bounds = this.q.getBounds();
        Component component = this.q;
        if (this.w != null) {
            component = this.w;
        }
        (this.q = aK.q(this.q, component)).add(this.q);
        Point point;
        if (this.q != null) {
            point = this.q;
        }
        else {
            point = component.getLocationOnScreen();
        }
        bounds.x = point.x;
        bounds.y = point.y;
        final Rectangle rectangle = bounds;
        rectangle.y += bounds.height;
        bounds.width = 300;
        bounds.height = 220;
        final Component q = this.q;
        final Rectangle rectangle2 = bounds;
        final Component component2 = q;
        final Dimension screenSize = q.getToolkit().getScreenSize();
        if (rectangle2.y + rectangle2.height + 30 > screenSize.height) {
            final Rectangle rectangle3 = rectangle2;
            rectangle3.y -= component2.getSize().height + rectangle2.height;
        }
        final int y = rectangle2.y;
        final Dimension dimension = screenSize;
        if (y > (dimension.height -= rectangle2.height + 30)) {
            rectangle2.y = screenSize.height;
        }
        if (rectangle2.y < 0) {
            rectangle2.y = 0;
        }
        if (rectangle2.x + component2.getSize().width > 1 && rectangle2.x < screenSize.width) {
            final int x = rectangle2.x;
            final Dimension dimension2 = screenSize;
            if (x > (dimension2.width -= rectangle2.width)) {
                rectangle2.x = screenSize.width;
            }
            if (rectangle2.x < 0) {
                rectangle2.x = 0;
            }
        }
        this.q.setBounds(bounds);
        this.q.doLayout();
        try {
            this.q.removeWindowListener(this);
        }
        catch (Exception ex) {}
        try {
            this.q.addWindowListener(this);
        }
        catch (Exception ex2) {}
        bG.q = true;
        this.repaint();
        this.q.setVisible(true);
        this.q.pack();
        this.q.requestFocus();
    }
    
    public final void q(final Color color) {
        color.getRGB();
        this.setBackground(color);
        if (this.q != null && this.w != null) {
            this.q.setForeground(color);
            this.w.setBackground(color);
        }
        else if (this.q != null) {
            this.q.setBackground(color);
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
            final int n2 = size.height - 1;
            final Color background;
            (background = this.getBackground()).brighter();
            background.darker().darker();
            Color color = this.getParent().getBackground();
            if (this.q != null) {
                color = this.q;
            }
            graphics.setColor(color);
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
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.q(mouseEvent);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
}

import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Panel;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class db extends Frame implements WindowListener
{
    public y p;
    
    public db(final y p2, final String s) {
        super(s);
        this.p = p2;
        this.setFont(dw.v);
        this.addWindowListener(this);
    }
    
    public void addNotify() {
        super.addNotify();
        this.b();
    }
    
    public final void b() {
        final Vector p = du.p(this);
        for (int i = 0; i < p.size(); ++i) {
            final Component component = p.elementAt(i);
            component.removeMouseListener(this.p);
            component.removeMouseMotionListener(this.p);
            component.addMouseListener(this.p);
            component.addMouseMotionListener(this.p);
        }
    }
    
    public final void p(final int n, final int n2) {
        this.p(n, n2, false);
    }
    
    public final void p(int n, int n2, final boolean b) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int n3 = b ? 2 : 0;
        int width = this.getSize().width;
        if (width < 140) {
            width = 140;
        }
        if (n + width > screenSize.width) {
            n -= width;
            n3 = -n3;
        }
        n += n3;
        if (n2 + this.getSize().height > screenSize.height) {
            n2 -= this.getSize().height;
        }
        this.setBounds(n, n2, width, this.getSize().height);
        this.show();
    }
    
    public final void p(final URL url) {
        this.setVisible(false);
        this.p.p(url);
    }
    
    public final void d(final String s, final Point point) {
        this.p.d(s, point);
    }
    
    public final void p() {
        this.p.p();
    }
    
    public final boolean d(final String s) {
        this.setVisible(false);
        return this.p.d(s);
    }
    
    public final void windowActivated(final WindowEvent windowEvent) {
        this.p.g();
    }
    
    public final void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public final void windowDeactivated(final WindowEvent windowEvent) {
        this.p.f();
    }
    
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public final void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void d(final Component component) {
        final Color[] array = dw.p[this.p.p()];
        if (component instanceof dl) {
            component.setForeground(array[5]);
            component.setBackground(array[4]);
            return;
        }
        if (component instanceof TextField) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
            return;
        }
        if (component instanceof dh) {
            component.setBackground(array[10]);
            return;
        }
        if (component instanceof Panel) {
            component.setBackground(array[6]);
            return;
        }
        if (component instanceof Scrollbar) {
            component.setBackground(array[10].darker());
            return;
        }
        if (component instanceof Panel || component instanceof Label) {
            component.setForeground(array[5]);
            component.setBackground(array[6]);
        }
    }
    
    public final void l() {
        final Vector p = du.p(this);
        for (int i = 0; i < p.size(); ++i) {
            this.d(p.elementAt(i));
        }
    }
}

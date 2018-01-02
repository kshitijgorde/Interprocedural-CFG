import java.awt.event.AdjustmentEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.MenuComponent;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.PopupMenu;
import javax.swing.JScrollBar;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class aa extends JPanel implements AdjustmentListener, KeyListener, MouseWheelListener
{
    private JScrollBar a;
    private ab b;
    private PopupMenu c;
    private Dimension d;
    public boolean e;
    
    public void a(final int blockIncrement) {
        this.a.setBlockIncrement(blockIncrement);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.isVisible()) {
            if (keyEvent.getKeyCode() == 38) {
                this.a.setValue(this.a.getValue() - this.a.getUnitIncrement());
                this.b(this.a.getValue());
            }
            else if (keyEvent.getKeyCode() == 40) {
                this.a.setValue(this.a.getValue() + this.a.getUnitIncrement());
                this.b(this.a.getValue());
            }
        }
    }
    
    public void a(final int n, final int n2) {
        this.d = new Dimension(n, n2);
    }
    
    public Dimension getPreferredSize() {
        return this.d;
    }
    
    public boolean a(final av av) {
        return this.b.a(av);
    }
    
    public boolean a(final String s) {
        return this.b.a(s);
    }
    
    public void a(final PopupMenu c, final int n, final int n2) {
        if (c != null) {
            if (this.c != null) {
                this.remove(this.c);
            }
            this.add(this.c = c);
            this.c.show(this, n, n2);
        }
    }
    
    public void a() {
        if (this.isVisible() && !this.e) {
            this.e = true;
            final int b = this.b.b();
            if (b != this.a.getMaximum()) {
                this.a.setValues(this.a.getValue(), this.b.getSize().height, 0, b);
                this.a(this.getSize().height);
                this.b.a(-this.a.getValue());
            }
            this.b.repaint();
            this.e = false;
        }
    }
    
    public av[] b() {
        return this.b.a();
    }
    
    public synchronized void b(final String s) {
        this.b.b(s);
    }
    
    public void c() {
        this.b.d();
    }
    
    public av c(final String s) {
        return this.b.c(s);
    }
    
    public aa(final z z) {
        this.a = new JScrollBar(1);
        this.d = new Dimension(0, 0);
        this.e = false;
        this.b = new ab(z, this);
        this.d();
    }
    
    public aa() {
        this.a = new JScrollBar(1);
        this.d = new Dimension(0, 0);
        this.e = false;
        this.b = new ab(null, this);
        this.d();
    }
    
    private void d() {
        this.a.addAdjustmentListener(this);
        this.addMouseWheelListener(this);
        this.setLayout(new BorderLayout());
        this.add("Center", this.b);
        this.add("East", this.a);
        this.a.setUnitIncrement(17);
        this.a.setVisible(true);
        this.a.setMaximum(0);
        this.setOpaque(true);
        this.a(dj.b);
        this.a.setBackground(dj.b);
        this.a.setForeground(dj.b);
    }
    
    public void a(final Color color) {
        this.b.setBackground(color);
        this.setBackground(color);
    }
    
    public void b(final int n) {
        this.b.a(-n);
        if (!this.e) {
            this.e = true;
            this.b.repaint();
        }
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        this.a.setValue(this.a.getValue() + mouseWheelEvent.getUnitsToScroll() * this.a.getUnitIncrement());
        this.b(this.a.getValue());
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.b(adjustmentEvent.getValue());
    }
    
    public void validate() {
        super.validate();
        this.a.setValues(this.a.getValue(), this.b.getSize().height, 0, this.b.b());
        this.b.a(-this.a.getValue());
        this.a.setVisibleAmount(this.b.getSize().height);
    }
}

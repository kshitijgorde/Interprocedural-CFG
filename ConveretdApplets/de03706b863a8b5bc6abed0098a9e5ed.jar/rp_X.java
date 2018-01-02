import java.awt.Point;
import java.awt.Container;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.font.LineMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.AWTEventMulticaster;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.Scrollable;
import java.awt.event.MouseListener;
import java.awt.ItemSelectable;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_X extends JPanel implements ItemSelectable, MouseListener, Scrollable
{
    private static ImageIcon a;
    private static ImageIcon b;
    private int a;
    private int b;
    private int c;
    private rp_ao a;
    private rp_ao b;
    private Vector a;
    private int d;
    private int e;
    private ItemListener a;
    
    public rp_X() {
        this.a = 22;
        this.b = 16;
        this.c = 0;
        this.a = null;
        this.b = null;
        this.a = new Vector();
        this.d = 8;
        this.e = 0;
        this.a = null;
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.addMouseListener(this);
        this.setFont(rp_aJ.a);
        ToolTipManager.sharedInstance().registerComponent(this);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.a = AWTEventMulticaster.add(this.a, itemListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.a = AWTEventMulticaster.remove(this.a, itemListener);
    }
    
    public Object[] getSelectedObjects() {
        if (this.b == null) {
            return new Object[0];
        }
        return new rp_ao[] { this.b };
    }
    
    public final rp_ao a() {
        return this.b;
    }
    
    public final rp_ao b() {
        return this.a;
    }
    
    public final void a(final rp_ao b) {
        if (b == this.b) {
            return;
        }
        final rp_ao b2 = this.b;
        this.b = b;
        final Graphics graphics = this.getGraphics();
        this.a(graphics, b2, true);
        this.a(graphics, this.b, true);
        if (this.a != null) {
            this.a.itemStateChanged(new ItemEvent(this, 701, b2, 2));
            this.a.itemStateChanged(new ItemEvent(this, 701, this.b, 1));
        }
        this.repaint();
    }
    
    private void a(final Graphics graphics, final rp_ao rp_ao, final boolean b) {
        if (rp_ao == null || graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        if (this.c == 0) {
            final Graphics2D graphics2D2 = graphics2D;
            final String s = "Ay";
            final int a = this.a;
            final LineMetrics lineMetrics = graphics2D2.getFont().getLineMetrics(s, graphics2D2.getFontRenderContext());
            this.c = (int)(0.5 + (a - lineMetrics.getHeight()) / 2.0 + lineMetrics.getDescent());
        }
        final int index = this.a.indexOf(rp_ao);
        int n = 6 + rp_ao.a * this.b;
        final int n2 = index * this.a;
        Color color;
        if (rp_ao == this.b) {
            color = rp_aJ.z;
        }
        else {
            color = rp_aJ.y;
        }
        graphics.setColor(color);
        graphics.fillRect(1, n2, 1500, this.a - 1);
        graphics.setColor(rp_aJ.A);
        graphics.drawLine(1, n2 + this.a - 1, 1500, n2 + this.a - 1);
        graphics.setColor(rp_aJ.B);
        final Image image;
        if (rp_ao.a() && (image = (rp_ao.a ? rp_X.b : rp_X.a).getImage()) != null) {
            image.getWidth(null);
            graphics.drawImage(image, n, n2 + (this.a - image.getHeight(null)) / 2, null);
        }
        n += 14;
        graphics2D.scale(1.15, 1.15);
        graphics.drawString(rp_ao.a, n, (int)((n2 + this.a - this.c) / 1.15));
        graphics2D.scale(0.8695652173913044, 0.8695652173913044);
    }
    
    public void paintComponent(final Graphics graphics) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final Rectangle clipBounds = graphics.getClipBounds();
        graphics.setColor(rp_aJ.x);
        graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        for (int i = 0; i < this.a.size(); ++i) {
            this.a(graphics, (rp_ao)this.a.elementAt(i), true);
        }
    }
    
    public final void b(final rp_ao a) {
        this.a = a;
        this.a();
    }
    
    public String getToolTipText(final MouseEvent mouseEvent) {
        final rp_ao a;
        if ((a = this.a(mouseEvent.getPoint())) != null) {
            int n = 1 + (a.a + 1) * this.b;
            n += 12;
            final int n2;
            if ((n2 = n + this.getGraphics().getFontMetrics().stringWidth(a.a)) > this.getSize().width) {
                return a.a;
            }
        }
        return null;
    }
    
    private void a() {
        this.a.removeAllElements();
        this.a.a = 0;
        this.c(this.a);
    }
    
    private void c(rp_ao a) {
        while (a != null) {
            this.a.addElement(a);
            if (a.a) {
                a.b.a = a.a + 1;
                this.c(a.b);
            }
            if (a.a == null) {
                return;
            }
            a.a.a = a.a;
            final rp_X rp_X = this;
            a = a.a;
            this = rp_X;
        }
    }
    
    private Dimension a() {
        Container container;
        if ((container = this.getParent()) instanceof JScrollPane) {
            container = container.getParent();
        }
        return container.getSize();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.a().width, this.a.size() * this.a);
    }
    
    private rp_ao a(final Point point) {
        final int n;
        if ((n = point.y / this.a) < this.a.size()) {
            return (rp_ao)this.a.elementAt(n);
        }
        return null;
    }
    
    public final void a(final int e) {
        this.e = e;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final rp_ao a;
        if ((a = this.a(mouseEvent.getPoint())) != null) {
            final rp_ao rp_ao;
            boolean b;
            if ((rp_ao = a).a) {
                final rp_ao rp_ao2;
                if ((rp_ao2 = rp_ao).a) {
                    rp_ao2.a = false;
                    b = true;
                }
                else {
                    b = false;
                }
            }
            else {
                b = rp_ao.b();
            }
            if (b) {
                this.a();
                final Dimension preferredSize = this.getPreferredSize();
                this.setSize(preferredSize.width, preferredSize.height);
                return;
            }
            this.a(a);
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
    
    public int getScrollableUnitIncrement(final Rectangle rectangle, final int n, final int n2) {
        return this.a;
    }
    
    public int getScrollableBlockIncrement(final Rectangle rectangle, final int n, final int n2) {
        return this.a * (this.a().height / this.a);
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
    
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
    
    public Dimension getPreferredScrollableViewportSize() {
        final Dimension preferredSize = this.getPreferredSize();
        int d;
        if (this.e == 0) {
            d = this.d;
        }
        else {
            d = this.e / this.a;
        }
        return new Dimension(preferredSize.width, Math.min(d, this.a.size()) * this.a);
    }
    
    static {
        final ClassLoader classLoader;
        rp_X.a = new ImageIcon((classLoader = rp_X.class.getClassLoader()).getResource("res/tree_arrow.png"));
        rp_X.b = new ImageIcon(classLoader.getResource("res/tree_sel.png"));
    }
}

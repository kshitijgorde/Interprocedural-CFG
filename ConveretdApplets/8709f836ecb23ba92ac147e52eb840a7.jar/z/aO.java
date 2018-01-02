// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.BoundedRangeModel;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.net.URL;
import java.awt.event.ComponentListener;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.Icon;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Hashtable;
import com.photochannel.easyUploader.b;
import java.util.Observer;
import javax.swing.JPanel;

public class aO extends JPanel implements Observer
{
    private final w b;
    private final b c;
    private final Hashtable d;
    private final bg e;
    private JPanel f;
    private JLabel g;
    private boolean h;
    private ImageIcon i;
    JScrollBar a;
    private static /* synthetic */ boolean j;
    
    public aO(final b c, final w b, bg e, Rectangle rectangle) {
        this.d = new Hashtable();
        this.h = false;
        if (!aO.j && c == null) {
            throw new AssertionError();
        }
        if (!aO.j && b == null) {
            throw new AssertionError();
        }
        if (!aO.j && e == null) {
            throw new AssertionError();
        }
        (this.b = b).addObserver(this);
        this.c = c;
        this.e = e;
        if (e.a("pdfIcon")) {
            this.i = V.a(e.i("pdfIcon"));
        }
        final Rectangle rectangle2 = rectangle;
        e = e;
        final Rectangle bounds = rectangle2;
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(bounds);
        rectangle = new Rectangle(0, 0, bounds.width, bounds.height);
        final URL j = e.j("clickHereImage");
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n = width;
        final JLabel g;
        (g = new JLabel()).setIcon(V.a(j));
        g.setCursor(Cursor.getPredefinedCursor(12));
        final int iconWidth = g.getIcon().getIconWidth();
        final int iconHeight = g.getIcon().getIconHeight();
        g.setBounds(new Rectangle(n / 2 - iconWidth / 2, height / 2 - iconHeight / 2, iconWidth, iconHeight));
        g.addMouseListener(new bq(this));
        g.setDropTarget(new ay(this, null));
        this.add(this.g = g);
        this.setComponentZOrder(this.g, 0);
        final int b2 = e.f("row").b("height");
        final int b3 = e.f("row").b("columns");
        final int b4 = e.b("horizGap");
        final int b5 = e.b("vertGap");
        final bg bg = e;
        final int n2 = b2;
        final int n3 = b3;
        final int n4 = b4;
        final int n5 = b5;
        final int n6 = n4;
        final int n7 = n3;
        final int n8 = n2;
        final bg bg2 = bg;
        final boolean g2;
        (this.f = new bo(this, g2, n8, n7, (g2 = bg2.g("dividingLine")) ? bg2.f("dividingLine").b("thickness") : 0, g2 ? bg2.f("dividingLine").d("color") : null, n6, n5)).setPreferredSize(new Dimension(bounds.width - 10, b2));
        this.f.setBackground(e.d("backcolor"));
        final as layout = new as(b3, b4, b5, b2);
        this.f.setLayout(layout);
        final JPanel f = this.f;
        final Rectangle rectangle3 = rectangle;
        final as as = layout;
        final Rectangle bounds2 = rectangle3;
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(f)).setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setBounds(bounds2);
        scrollPane.setDropTarget(new ay(this, null));
        scrollPane.getViewport().addComponentListener(new bp(this, scrollPane, as));
        final JScrollPane scrollPane2 = scrollPane;
        this.a = scrollPane2.getVerticalScrollBar();
        this.add(scrollPane2);
        this.setComponentZOrder(scrollPane2, 1);
        this.addComponentListener(new bm(this, this));
    }
    
    public void update(final Observable observable, final Object o) {
        if (!aO.j && observable != this.b) {
            throw new AssertionError();
        }
        SwingUtilities.invokeLater(new br(this, o));
    }
    
    public final void a() {
        this.h = true;
        synchronized (this.d) {
            final Iterator<bj> iterator = this.d.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().setEnabled(false);
            }
        }
    }
    
    public final void b() {
        SwingUtilities.invokeLater(new bt(this));
    }
    
    static {
        aO.j = !aO.class.desiredAssertionStatus();
    }
}

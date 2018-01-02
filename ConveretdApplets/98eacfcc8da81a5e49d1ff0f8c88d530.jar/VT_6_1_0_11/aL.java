// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreeCellRenderer;
import com.hw.client.util.c;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.Font;
import java.awt.Color;
import com.wimba.clients.vboard.n;
import javax.swing.ImageIcon;
import java.awt.dnd.Autoscroll;
import javax.swing.JTree;

public final class aL extends JTree implements Autoscroll
{
    private ImageIcon b;
    private cl c;
    protected n a;
    private Color d;
    private Color e;
    private Font f;
    private TreePath[] g;
    
    public final TreePath[] a() {
        return this.g;
    }
    
    public final void a(final TreePath[] array) {
        this.g = null;
    }
    
    public aL(final n a) {
        super(new co(a));
        this.d = ca.a("#FAF69F", Color.yellow);
        this.e = ca.a("#DDE4F7", Color.blue);
        this.a = a;
        this.setSelectionModel(new bt());
        this.f = new Font(h.a(), 0, 12);
        this.b = com.hw.client.util.c.a("/images/presentation/tree_slide.png");
        final ImageIcon a2 = com.hw.client.util.c.a("/images/vboard/tree_expanded.png");
        final ImageIcon a3 = com.hw.client.util.c.a("/images/vboard/tree_collapsed.png");
        this.setRootVisible(false);
        this.setShowsRootHandles(true);
        this.setScrollsOnExpand(true);
        this.putClientProperty("JTree.lineStyle", "None");
        this.getSelectionModel().setSelectionMode(4);
        this.setCellRenderer(new ah(this));
        if (this.getUI() instanceof BasicTreeUI) {
            ((BasicTreeUI)this.getUI()).setExpandedIcon(a2);
            ((BasicTreeUI)this.getUI()).setCollapsedIcon(a3);
        }
        this.c = new cl(this);
        this.addMouseListener(new u(this));
        ToolTipManager.sharedInstance().registerComponent(this);
    }
    
    public final cl b() {
        return this.c;
    }
    
    public final Insets getAutoscrollInsets() {
        final Rectangle visibleRect = this.getVisibleRect();
        final Dimension size = this.getSize();
        return new Insets(visibleRect.y + 50, visibleRect.x, size.height - visibleRect.y - visibleRect.height + 50, size.width - visibleRect.x - visibleRect.width);
    }
    
    public final void autoscroll(final Point point) {
        this.scrollRectToVisible(new Rectangle(0, (point.y < this.getVisibleRect().y + 50) ? (point.y - 40) : point.y, this.getVisibleRect().width, 40));
    }
    
    static TreePath[] a(final aL al, final TreePath[] g) {
        return al.g = g;
    }
    
    static Font a(final aL al) {
        return al.f;
    }
    
    static ImageIcon b(final aL al) {
        return al.b;
    }
    
    static Color c(final aL al) {
        return al.e;
    }
    
    static cl d(final aL al) {
        return al.c;
    }
    
    static Color e(final aL al) {
        return al.d;
    }
}

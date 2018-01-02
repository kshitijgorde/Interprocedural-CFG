// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Font;
import java.awt.Container;
import javax.swing.JViewport;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.Component;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.Color;
import javax.swing.JTable;

public class aL extends JTable
{
    private Color a;
    private static /* synthetic */ boolean b;
    
    public final void a(final Color a) {
        this.a = a;
    }
    
    public final void b(final Color background) {
        this.setBackground(background);
    }
    
    public aL(final TableModel tableModel, final TableColumnModel tableColumnModel) {
        super(tableModel, tableColumnModel);
        (this = this).setOpaque(true);
        this.setFocusable(false);
        this.setAutoCreateColumnsFromModel(false);
        this.setTableHeader(null);
        this.setShowHorizontalLines(false);
        this.setCellSelectionEnabled(false);
        this.setDropTarget(new ay(this, null));
        ToolTipManager.sharedInstance().unregisterComponent(this);
    }
    
    public Component prepareRenderer(final TableCellRenderer tableCellRenderer, int n, final int n2) {
        final Component prepareRenderer = super.prepareRenderer(tableCellRenderer, n, n2);
        final aL al = this;
        n = n;
        this = al;
        if (!aL.b && (n < 0 || n >= this.getRowCount())) {
            throw new AssertionError();
        }
        prepareRenderer.setBackground((n % 2 == 0) ? this.a : this.getBackground());
        return prepareRenderer;
    }
    
    public boolean getScrollableTracksViewportHeight() {
        final Container parent;
        return (parent = this.getParent()) instanceof JViewport && this.getPreferredSize().height < parent.getHeight();
    }
    
    public final int a() {
        return this.getHeight() / this.getRowHeight();
    }
    
    public void setFont(final Font font) {
        if (!aL.b && font == null) {
            throw new AssertionError();
        }
        super.setFont(font);
        ao.a(font);
        l.b.setFont(font);
    }
    
    public final void b() {
        this.setEnabled(false);
    }
    
    public final void a(final int n) {
        this.scrollRectToVisible(this.getCellRect(n, 0, true));
    }
    
    static {
        aL.b = !aL.class.desiredAssertionStatus();
    }
}

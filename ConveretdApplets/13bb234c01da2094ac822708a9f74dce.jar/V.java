import javax.swing.table.TableColumnModel;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.JViewport;
import javax.swing.table.TableModel;
import javax.swing.JTable;

// 
// Decompiled by Procyon v0.5.30
// 

public class V extends JTable
{
    public V(final TableModel tableModel) {
        super(tableModel);
    }
    
    public boolean getScrollableTracksViewportHeight() {
        return this.getParent() instanceof JViewport && ((JViewport)this.getParent()).getHeight() > this.getPreferredSize().height;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        this.a(graphics);
    }
    
    protected void a(final Graphics graphics) {
        final int rowCount = this.getRowCount();
        final Rectangle clipBounds = graphics.getClipBounds();
        final int n = clipBounds.y + clipBounds.height;
        if (rowCount * this.rowHeight < n) {
            for (int i = rowCount; i <= n / this.rowHeight; ++i) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(clipBounds.x, i * this.rowHeight, clipBounds.width, this.rowHeight);
            }
            if (this.getShowVerticalLines()) {
                graphics.setColor(this.gridColor);
                final TableColumnModel columnModel = this.getColumnModel();
                int n2 = 0;
                for (int j = 0; j < columnModel.getColumnCount(); ++j) {
                    n2 += columnModel.getColumn(j).getWidth();
                    graphics.drawLine(n2 - 1, rowCount * this.rowHeight, n2 - 1, n);
                }
            }
        }
    }
}

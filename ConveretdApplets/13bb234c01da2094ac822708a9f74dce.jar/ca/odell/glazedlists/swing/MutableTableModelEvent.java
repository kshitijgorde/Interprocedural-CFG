// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;

final class MutableTableModelEvent extends TableModelEvent
{
    public MutableTableModelEvent(final TableModel tableModel) {
        super(tableModel);
    }
    
    public void a(final int firstRow, final int lastRow, final int n) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        if (n == 2) {
            this.type = 1;
        }
        else if (n == 0) {
            this.type = -1;
        }
        else if (n == 1) {
            this.type = 0;
        }
        this.column = -1;
    }
}

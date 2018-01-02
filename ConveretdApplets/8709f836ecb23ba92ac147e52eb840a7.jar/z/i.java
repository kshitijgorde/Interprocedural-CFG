// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.SwingUtilities;
import java.util.Observable;
import java.util.Vector;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;
import java.util.EventObject;

final class i implements Runnable
{
    private static /* synthetic */ boolean a;
    private /* synthetic */ Object b;
    private /* synthetic */ aj c;
    
    i(final aj c, final Object b) {
        this.c = c;
        this.b = b;
    }
    
    public final void run() {
        if (this.b instanceof aN) {
            this.c.b.add(((aN)this.b).a);
            final int n = this.c.b.size() - 1;
            this.c.fireTableRowsInserted(n, n);
            return;
        }
        if (this.b instanceof an) {
            final String a = ((an)this.b).a;
            final int index = this.c.b.indexOf(a);
            if (!i.a && index == -1) {
                throw new AssertionError();
            }
            this.c.b.remove(a);
            this.c.fireTableRowsDeleted(index, index);
        }
        else {
            if (this.b instanceof ba) {
                this.c.fireTableCellUpdated(this.c.b.indexOf(((ba)this.b).a), 1);
                return;
            }
            if (this.b instanceof P) {
                final int a2 = aj.a(this.c, ((P)this.b).a);
                if (this.c.a != null) {
                    this.c.a.a(new J(this, a2));
                }
            }
        }
    }
    
    static {
        i.a = !aj.class.desiredAssertionStatus();
    }
}

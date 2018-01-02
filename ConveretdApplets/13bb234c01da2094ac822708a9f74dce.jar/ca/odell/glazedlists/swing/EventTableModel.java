// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import ca.odell.glazedlists.gui.WritableTableFormat;
import ca.odell.glazedlists.gui.AdvancedTableFormat;
import javax.swing.event.TableModelEvent;
import ca.odell.glazedlists.event.ListEvent;
import javax.swing.table.TableModel;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.event.ListEventListener;
import javax.swing.table.AbstractTableModel;

public class EventTableModel extends AbstractTableModel implements ListEventListener
{
    protected TransformedList a;
    protected EventList b;
    private TableFormat c;
    private final MutableTableModelEvent d;
    
    public EventTableModel(final EventList b, final TableFormat c) {
        this.d = new MutableTableModelEvent(this);
        b.b().b().a();
        try {
            final TransformedList a = this.a(b);
            if (a != null && a != b) {
                final TransformedList list = a;
                this.a = list;
                this.b = list;
            }
            else {
                this.b = b;
            }
            this.c = c;
            this.b.a(this);
        }
        finally {
            b.b().b().b();
        }
    }
    
    protected TransformedList a(final EventList list) {
        return GlazedListsSwing.b(list) ? null : GlazedListsSwing.a(list);
    }
    
    public TableFormat a() {
        return this.c;
    }
    
    public void a(final ListEvent listEvent) {
        this.b(listEvent);
    }
    
    protected void b(final ListEvent listEvent) {
        while (listEvent.c()) {
            this.d.a(listEvent.g(), listEvent.h(), listEvent.i());
            this.fireTableChanged(this.d);
        }
    }
    
    public String getColumnName(final int n) {
        return this.c.a(n);
    }
    
    public int getRowCount() {
        this.b.b().b().a();
        try {
            return this.b.size();
        }
        finally {
            this.b.b().b().b();
        }
    }
    
    public int getColumnCount() {
        return this.c.a();
    }
    
    public Class getColumnClass(final int n) {
        if (this.c instanceof AdvancedTableFormat) {
            return ((AdvancedTableFormat)this.c).b(n);
        }
        return super.getColumnClass(n);
    }
    
    public Object getValueAt(final int n, final int n2) {
        this.b.b().b().a();
        try {
            return this.c.a(this.b.get(n), n2);
        }
        finally {
            this.b.b().b().b();
        }
    }
    
    public boolean isCellEditable(final int n, final int n2) {
        if (!(this.c instanceof WritableTableFormat)) {
            return false;
        }
        this.b.b().b().a();
        try {
            return ((WritableTableFormat)this.c).a_(this.b.get(n), n2);
        }
        finally {
            this.b.b().b().b();
        }
    }
    
    public void setValueAt(final Object o, final int n, final int n2) {
        if (!(this.c instanceof WritableTableFormat)) {
            throw new UnsupportedOperationException("Unexpected setValueAt() on read-only table");
        }
        this.b.b().a().a();
        try {
            final Object value = this.b.get(n);
            final Object a = ((WritableTableFormat)this.c).a(value, o, n2);
            if (a != null && (n < this.getRowCount() && this.b.get(n) == value)) {
                this.b.set(n, a);
            }
        }
        finally {
            this.b.b().a().b();
        }
    }
}

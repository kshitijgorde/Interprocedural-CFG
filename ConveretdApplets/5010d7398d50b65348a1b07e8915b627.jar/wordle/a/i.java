// 
// Decompiled by Procyon v0.5.30
// 

package wordle.a;

import java.util.Iterator;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTable;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.TableModelListener;
import java.awt.event.MouseListener;
import javax.swing.table.JTableHeader;
import java.util.Comparator;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;

public final class i extends AbstractTableModel
{
    protected TableModel a;
    private static d b;
    private static Comparator c;
    private static Comparator d;
    private f[] e;
    private int[] f;
    private JTableHeader g;
    private transient MouseListener h;
    private transient TableModelListener i;
    private Map j;
    private List k;
    
    static {
        i.b = new d(-1, 0);
        i.c = new c();
        i.d = new a();
    }
    
    public i() {
        this.j = new HashMap();
        this.k = new ArrayList();
        this.h = new e(this);
        this.i = new j(this);
    }
    
    public i(TableModel a) {
        this();
        final i i = this;
        a = a;
        this = i;
        if (i.a != null) {
            this.a.removeTableModelListener(this.i);
        }
        this.a = a;
        if (this.a != null) {
            this.a.addTableModelListener(this.i);
        }
        this.b();
        this.fireTableStructureChanged();
    }
    
    private void b() {
        this.e = null;
        this.f = null;
    }
    
    public final void a(final JTableHeader g) {
        if (this.g != null) {
            this.g.removeMouseListener(this.h);
            final TableCellRenderer defaultRenderer;
            if ((defaultRenderer = this.g.getDefaultRenderer()) instanceof h) {
                this.g.setDefaultRenderer(((h)defaultRenderer).a);
            }
        }
        this.g = g;
        if (this.g != null) {
            this.g.addMouseListener(this.h);
            this.g.setDefaultRenderer(new h(this, this.g.getDefaultRenderer()));
        }
    }
    
    public final boolean a() {
        return this.k.size() != 0;
    }
    
    private d c(final int n) {
        for (int i = 0; i < this.k.size(); ++i) {
            final d d;
            if ((d = this.k.get(i)).a == n) {
                return d;
            }
        }
        return wordle.a.i.b;
    }
    
    public final int a(final int n) {
        return this.c(n).b;
    }
    
    private void c() {
        this.b();
        this.fireTableDataChanged();
        if (this.g != null) {
            this.g.repaint();
        }
    }
    
    public final void a(final int n, final int n2) {
        final d c;
        if ((c = this.c(n)) != wordle.a.i.b) {
            this.k.remove(c);
        }
        if (n2 != 0) {
            this.k.add(new d(n, n2));
        }
        this.c();
    }
    
    protected final Icon b(final int n, final int n2) {
        final d c;
        if ((c = this.c(n)) == wordle.a.i.b) {
            return null;
        }
        return new g(c.b == -1, n2, this.k.indexOf(c));
    }
    
    protected final Comparator b(final int n) {
        final Class<?> columnClass = this.a.getColumnClass(n);
        final Comparator comparator;
        if ((comparator = this.j.get(columnClass)) != null) {
            return comparator;
        }
        if (Comparable.class.isAssignableFrom(columnClass)) {
            return wordle.a.i.c;
        }
        return wordle.a.i.d;
    }
    
    private f[] d() {
        if (this.e == null) {
            final int rowCount = this.a.getRowCount();
            this.e = new f[rowCount];
            for (int i = 0; i < rowCount; ++i) {
                this.e[i] = new f(this, i);
            }
            if (this.a()) {
                Arrays.sort(this.e);
            }
        }
        return this.e;
    }
    
    private int d(final int n) {
        return this.d()[n].a;
    }
    
    public final int getRowCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.getRowCount();
    }
    
    public final int getColumnCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.getColumnCount();
    }
    
    public final String getColumnName(final int n) {
        return this.a.getColumnName(n);
    }
    
    public final Class getColumnClass(final int n) {
        return this.a.getColumnClass(n);
    }
    
    public final boolean isCellEditable(final int n, final int n2) {
        return this.a.isCellEditable(this.d(n), n2);
    }
    
    public final Object getValueAt(final int n, final int n2) {
        return this.a.getValueAt(this.d(n), n2);
    }
    
    public final void setValueAt(final Object o, final int n, final int n2) {
        this.a.setValueAt(o, this.d(n), n2);
    }
}

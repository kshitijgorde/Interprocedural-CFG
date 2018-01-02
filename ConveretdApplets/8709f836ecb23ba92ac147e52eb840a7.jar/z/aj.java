// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.SwingUtilities;
import java.util.Observable;
import java.util.Vector;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;

public class aj extends AbstractTableModel implements Observer
{
    private Vector b;
    private int c;
    private w d;
    I a;
    private static /* synthetic */ boolean e;
    
    public aj(final w d) {
        this.b = new Vector();
        if (!aj.e && d == null) {
            throw new AssertionError();
        }
        (this.d = d).addObserver(this);
    }
    
    public final void a(final int c) {
        if (!aj.e && c < 0) {
            throw new AssertionError();
        }
        this.c = c;
    }
    
    public int getColumnCount() {
        return 2;
    }
    
    public int getRowCount() {
        return Math.max(this.b.size(), this.c);
    }
    
    public Object getValueAt(final int n, final int n2) {
        if (!aj.e && n < 0) {
            throw new AssertionError();
        }
        if (!aj.e && n2 < 0) {
            throw new AssertionError();
        }
        if (!aj.e && n2 >= this.getColumnCount()) {
            throw new AssertionError();
        }
        if (n >= this.b.size()) {
            return null;
        }
        final String s = this.b.get(n);
        switch (n2) {
            case 0: {
                return this.d.c(s);
            }
            case 1: {
                return this.d.d(s);
            }
            default: {
                throw new RuntimeException("Invalid column number: " + n2);
            }
        }
    }
    
    public Class getColumnClass(final int n) {
        switch (n) {
            case 0: {
                return String.class;
            }
            case 1: {
                return Integer.class;
            }
            default: {
                throw new RuntimeException("Invalid column number: " + n);
            }
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (!aj.e && observable != this.d) {
            throw new AssertionError();
        }
        SwingUtilities.invokeLater(new i(this, o));
    }
    
    public final void a(final I a) {
        this.a = a;
    }
    
    static {
        aj.e = !aj.class.desiredAssertionStatus();
    }
}

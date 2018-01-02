// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import javax.swing.JTable;
import com.eventim.common.transfer.saalplan.SaalplanRabattstufeDetails;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;

public final class o extends JComboBox implements TableCellRenderer
{
    private String a;
    private int b;
    
    public o(final SaalplanRabattstufeDetails[] array) {
        super(a(array).toArray());
        this.a = "";
        this.b = array.length;
        if (array.length > 0) {
            this.a = array[0].getName();
        }
    }
    
    public final Component getTableCellRendererComponent(final JTable table, final Object selectedItem, final boolean b, final boolean b2, final int n, final int n2) {
        ((e)table.getModel()).a(((p)this.getSelectedObjects()[0]).a(), n);
        if (b) {
            this.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        else {
            this.setForeground(table.getForeground());
            this.setBackground(table.getBackground());
        }
        this.setSelectedItem(selectedItem);
        return this;
    }
    
    public final int a() {
        return this.b;
    }
    
    public final String toString() {
        return this.a;
    }
    
    private static List a(final SaalplanRabattstufeDetails[] array) {
        final ArrayList<p> list = new ArrayList<p>();
        for (int i = 0; i < array.length; ++i) {
            list.add(new p(array[i]));
        }
        return list;
    }
}

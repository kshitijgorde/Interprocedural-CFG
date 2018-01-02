// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

import org.jfree.base.Library;
import java.util.ResourceBundle;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LibraryTableModel extends AbstractTableModel
{
    private List libraries;
    private String nameColumnLabel;
    private String versionColumnLabel;
    private String licenceColumnLabel;
    private String infoColumnLabel;
    
    public LibraryTableModel(final List libraries) {
        this.libraries = libraries;
        final ResourceBundle bundle = ResourceBundle.getBundle("org.jfree.ui.about.resources.AboutResources");
        this.nameColumnLabel = bundle.getString("libraries-table.column.name");
        this.versionColumnLabel = bundle.getString("libraries-table.column.version");
        this.licenceColumnLabel = bundle.getString("libraries-table.column.licence");
        this.infoColumnLabel = bundle.getString("libraries-table.column.info");
    }
    
    public int getRowCount() {
        return this.libraries.size();
    }
    
    public int getColumnCount() {
        return 4;
    }
    
    public String getColumnName(final int n) {
        String s = null;
        switch (n) {
            case 0: {
                s = this.nameColumnLabel;
                break;
            }
            case 1: {
                s = this.versionColumnLabel;
                break;
            }
            case 2: {
                s = this.licenceColumnLabel;
                break;
            }
            case 3: {
                s = this.infoColumnLabel;
                break;
            }
        }
        return s;
    }
    
    public Object getValueAt(final int n, final int n2) {
        Object o = null;
        final Library library = this.libraries.get(n);
        if (n2 == 0) {
            o = library.getName();
        }
        else if (n2 == 1) {
            o = library.getVersion();
        }
        else if (n2 == 2) {
            o = library.getLicenceName();
        }
        else if (n2 == 3) {
            o = library.getInfo();
        }
        return o;
    }
}

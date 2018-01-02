// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import java.awt.Color;
import risco_table.TableCell.ITableCell;

public class SimpleTableRow implements TableRow
{
    private ITableCell[] strings;
    
    public SimpleTableRow(final ITableCell[] strings) {
        this.strings = strings;
    }
    
    public Color[] getBackgrounds() {
        return null;
    }
    
    public Color[] getForegrounds() {
        return null;
    }
    
    public ITableCell[] getStrings() {
        return this.strings;
    }
}

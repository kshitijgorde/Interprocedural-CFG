// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import risco_table.TableCell.ITableCell;
import java.awt.Color;

public interface TableRow
{
    Color[] getBackgrounds();
    
    Color[] getForegrounds();
    
    ITableCell[] getStrings();
}

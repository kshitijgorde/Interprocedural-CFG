// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.tree;

import org.xmodel.xpath.expression.StatefulContext;
import java.util.List;
import org.xidget.tree.Row;

public interface ITreeWidgetFeature
{
    void insertRows(final Row p0, final int p1, final Row[] p2);
    
    void removeRows(final Row p0, final int p1, final Row[] p2, final boolean p3);
    
    void commit(final Row p0);
    
    List<Row> getChildren(final Row p0);
    
    Row findRow(final StatefulContext p0);
    
    boolean isVisible(final Row p0);
    
    void setTitle(final int p0, final String p1);
    
    void setColumnWidth(final int p0, final int p1);
    
    void updateCell(final Row p0, final int p1);
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.tree;

import org.xidget.tree.Column;
import java.util.List;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.tree.Row;

public interface IColumnSetFeature
{
    void bind(final Row p0, final StatefulContext p1);
    
    void unbind(final Row p0);
    
    List<Column> getColumns();
}

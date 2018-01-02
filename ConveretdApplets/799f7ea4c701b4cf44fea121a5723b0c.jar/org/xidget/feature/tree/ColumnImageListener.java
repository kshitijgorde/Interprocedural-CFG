// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.tree;

import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.tree.Row;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.ExpressionListener;

public class ColumnImageListener extends ExpressionListener
{
    private IXidget xidget;
    private Row row;
    private int columnIndex;
    
    public ColumnImageListener(final IXidget xidget, final Row row, final int columnIndex) {
        this.xidget = xidget;
        this.row = row;
        this.columnIndex = columnIndex;
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.row.getCell(this.columnIndex).icon = expression.query(context, null).get(0).getValue();
        this.xidget.getFeature(ITreeWidgetFeature.class).updateCell(this.row, this.columnIndex);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final List<IModelObject> query = expression.query(context, null);
        this.row.getCell(this.columnIndex).icon = ((query.size() > 0) ? query.get(0).getValue() : null);
        this.xidget.getFeature(ITreeWidgetFeature.class).updateCell(this.row, this.columnIndex);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object icon, final Object o) {
        this.row.getCell(this.columnIndex).icon = icon;
        this.xidget.getFeature(ITreeWidgetFeature.class).updateCell(this.row, this.columnIndex);
    }
    
    @Override
    public boolean requiresValueNotification() {
        return true;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.tree;

import org.xmodel.Xlate;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObject;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.tree.Row;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.ExpressionListener;

public class ColumnSourceListener extends ExpressionListener
{
    private IXidget xidget;
    private Row row;
    private int columnIndex;
    
    public ColumnSourceListener(final IXidget xidget, final Row row, final int columnIndex) {
        this.xidget = xidget;
        this.row = row;
        this.columnIndex = columnIndex;
    }
    
    private void setText(final String text) {
        this.row.getCell(this.columnIndex).text = text;
        this.xidget.getFeature(ITreeWidgetFeature.class).updateCell(this.row, this.columnIndex);
    }
    
    private void setSource(final IModelObject source) {
        this.row.getCell(this.columnIndex).source = source;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.setText(Boolean.toString(b));
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.setText(Double.toString(n));
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String text, final String s) {
        this.setText(text);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final List<IModelObject> query = expression.query(context, null);
        this.setText(Xlate.get((IModelObject)query.get(0), ""));
        this.setSource(query.get(0));
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final List<IModelObject> query = expression.query(context, null);
        final IModelObject source = (query.size() > 0) ? query.get(0) : null;
        this.setText(Xlate.get(source, ""));
        this.setSource(source);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.setText((o != null) ? o.toString() : "");
    }
    
    @Override
    public boolean requiresValueNotification() {
        return true;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.tree;

import org.xidget.tree.Cell;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.tree.Row;
import java.util.Iterator;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import org.xidget.tree.Column;
import java.util.List;
import org.xidget.IXidget;
import org.xidget.ifeature.tree.IColumnSetFeature;

public class ColumnSetFeature implements IColumnSetFeature
{
    protected IXidget xidget;
    private List<Column> columns;
    
    public ColumnSetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.columns = new ArrayList<Column>(5);
        this.configure(xidget.getConfig());
    }
    
    private void configure(final IModelObject modelObject) {
        for (final IModelObject modelObject2 : modelObject.getChildren("cell")) {
            final Column column = new Column();
            column.imageExpr = Xlate.get(modelObject2, "image", Xlate.childGet(modelObject2, "image", (IExpression)null));
            column.sourceExpr = Xlate.get(modelObject2, "source", Xlate.childGet(modelObject2, "source", (IExpression)null));
            this.columns.add(column);
        }
    }
    
    @Override
    public List<Column> getColumns() {
        return this.columns;
    }
    
    @Override
    public void bind(final Row row, final StatefulContext statefulContext) {
        for (int i = 0; i < this.columns.size(); ++i) {
            final Column column = this.columns.get(i);
            final Cell cell = row.getCell(i);
            cell.imageListener = new ColumnImageListener(this.xidget, row, i);
            cell.sourceListener = new ColumnSourceListener(this.xidget, row, i);
            if (column.imageExpr != null) {
                column.imageExpr.addNotifyListener(row.getContext(), cell.imageListener);
            }
            if (column.sourceExpr != null) {
                column.sourceExpr.addNotifyListener(row.getContext(), cell.sourceListener);
            }
        }
    }
    
    @Override
    public void unbind(final Row row) {
        for (int i = 0; i < this.columns.size(); ++i) {
            final Column column = this.columns.get(i);
            final Cell cell = row.getCell(i);
            if (column.imageExpr != null) {
                column.imageExpr.removeListener(row.getContext(), cell.imageListener);
            }
            if (column.sourceExpr != null) {
                column.sourceExpr.removeListener(row.getContext(), cell.sourceListener);
            }
        }
    }
}

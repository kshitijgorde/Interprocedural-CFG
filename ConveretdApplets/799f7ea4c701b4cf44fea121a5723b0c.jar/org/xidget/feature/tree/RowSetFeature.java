// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.tree;

import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import org.xidget.tree.Row;
import org.xidget.ifeature.tree.IColumnSetFeature;
import org.xmodel.diff.ListDiffer;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Iterator;
import org.xidget.IXidget;
import org.xidget.ifeature.tree.IRowSetFeature;

public class RowSetFeature implements IRowSetFeature
{
    protected IXidget xidget;
    private int tableIndex;
    
    public RowSetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.tableIndex = this.findTableIndex(xidget);
    }
    
    private int findTableIndex(final IXidget xidget) {
        int n = 0;
        for (final IXidget xidget2 : xidget.getParent().getChildren()) {
            if (xidget2.getConfig().isType(xidget.getConfig().getType())) {
                if (xidget2 == xidget) {
                    return n;
                }
                ++n;
            }
        }
        return -1;
    }
    
    @Override
    public void setRows(final StatefulContext statefulContext, final List<IModelObject> list) {
        final ITreeWidgetFeature treeWidgetFeature = this.xidget.getFeature(ITreeWidgetFeature.class);
        final Row row = treeWidgetFeature.findRow(statefulContext);
        final List<Row> children = row.getChildren(this.tableIndex);
        final int offset = row.getOffset(this.tableIndex);
        final ListDiffer listDiffer = new ListDiffer();
        listDiffer.diff(this.toElements(children), list);
        final List<ListDiffer.Change> changes = listDiffer.getChanges();
        final IColumnSetFeature columnSetFeature = this.xidget.getFeature(IColumnSetFeature.class);
        for (final ListDiffer.Change change : changes) {
            if (change.rIndex >= 0) {
                final Row[] array = new Row[change.count];
                for (int i = 0; i < change.count; ++i) {
                    final IModelObject modelObject = list.get(change.rIndex + i);
                    final Row row2 = new Row(this.xidget);
                    row2.setContext(new StatefulContext(statefulContext, modelObject));
                    row.addChild(this.tableIndex, change.lIndex + i, row2);
                    array[i] = row2;
                }
                treeWidgetFeature.insertRows(row, change.lIndex + offset, array);
                for (int j = 0; j < change.count; ++j) {
                    final Row row3 = array[j];
                    columnSetFeature.bind(row3, row3.getContext());
                }
            }
            else {
                final Row[] array2 = new Row[change.count];
                for (int k = 0; k < change.count; ++k) {
                    columnSetFeature.unbind(array2[k] = children.get(change.lIndex));
                    row.removeChild(this.tableIndex, change.lIndex);
                }
                treeWidgetFeature.removeRows(row, change.lIndex + offset, array2, false);
            }
        }
        treeWidgetFeature.commit(row);
    }
    
    @Override
    public List<IModelObject> getRows(final StatefulContext statefulContext) {
        return this.toElements(this.xidget.getFeature(ITreeWidgetFeature.class).findRow(statefulContext).getChildren());
    }
    
    private List<IModelObject> toElements(final List<Row> list) {
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
        final Iterator<Row> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().getContext().getObject());
        }
        return list2;
    }
}

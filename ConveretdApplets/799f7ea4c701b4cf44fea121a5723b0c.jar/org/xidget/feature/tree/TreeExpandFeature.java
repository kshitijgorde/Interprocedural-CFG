// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.tree;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xmodel.xpath.XPath;
import java.util.ArrayList;
import java.util.List;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.Log;
import org.xidget.tree.Row;
import org.xmodel.IModelObject;
import org.xmodel.ModelObject;
import java.util.Iterator;
import java.util.Collections;
import org.xidget.ifeature.tree.IRowSetFeature;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.IXidget;
import org.xidget.ConfigurationSwitch;
import org.xidget.ifeature.tree.ITreeExpandFeature;

public class TreeExpandFeature implements ITreeExpandFeature
{
    private ConfigurationSwitch.IListener<IXidget> switchListener;
    protected IXidget xidget;
    private StatefulContext dummy;
    
    public TreeExpandFeature(final IXidget xidget) {
        this.switchListener = new ConfigurationSwitch.IListener<IXidget>() {
            @Override
            public void notifyMatch(final StatefulContext statefulContext, final IXidget xidget) {
                xidget.getFeature(IBindFeature.class).bind(statefulContext);
            }
            
            @Override
            public void notifyMismatch(final StatefulContext statefulContext, final IXidget xidget) {
                xidget.getFeature(IBindFeature.class).unbind(statefulContext);
                final Iterator<IXidget> iterator = xidget.getChildren().iterator();
                while (iterator.hasNext()) {
                    final IRowSetFeature rowSetFeature = iterator.next().getFeature(IRowSetFeature.class);
                    if (rowSetFeature != null) {
                        rowSetFeature.setRows(statefulContext, Collections.emptyList());
                    }
                }
            }
        };
        this.xidget = xidget;
        this.dummy = new StatefulContext(new ModelObject("dummy"));
    }
    
    @Override
    public void rowAdded(final Row row) {
        if (!row.isExpanded()) {
            this.fakeExpand(row);
        }
    }
    
    @Override
    public void rowRemoved(final Row row) {
        if (row.isExpanded()) {
            this.realCollapse(row);
        }
    }
    
    @Override
    public void expand(final Row row) {
        Log.printf("xidget.tree", "%s: expand: %s\n", this.hashCode(), row);
        this.fakeCollapse(row);
        this.realExpand(row);
        final Iterator<Row> iterator = row.getChildren().iterator();
        while (iterator.hasNext()) {
            this.fakeExpand(iterator.next());
        }
        this.xidget.getFeature(ITreeWidgetFeature.class).commit(row);
    }
    
    @Override
    public void collapse(final Row row) {
        Log.printf("xidget.tree", "%s: collapse: %s\n", this.hashCode(), row);
        final Iterator<Row> iterator = row.getChildren().iterator();
        while (iterator.hasNext()) {
            this.fakeCollapse(iterator.next());
        }
        this.realCollapse(row);
        final Row parent = row.getParent();
        if (parent != null && parent.isExpanded()) {
            this.fakeExpand(row);
        }
    }
    
    private void realExpand(final Row row) {
        if (row.isExpanded()) {
            return;
        }
        row.setExpanded(true);
        Log.printf("xidget.tree", "%s: real expand: %s\n", this.hashCode(), row);
        this.getSwitch(row).bind(row.getContext());
    }
    
    private void realCollapse(final Row row) {
        if (!row.isExpanded()) {
            return;
        }
        row.setExpanded(false);
        Log.printf("xidget.tree", "%s: real collapse: %s\n", this.hashCode(), row);
        this.getSwitch(row).unbind(row.getContext());
    }
    
    private void fakeExpand(final Row row) {
        Log.printf("xidget.tree", "%s: fake expand: %s\n", this.hashCode(), row);
        if (this.needsTemporaryChild(row)) {
            final ITreeWidgetFeature treeWidgetFeature = this.xidget.getFeature(ITreeWidgetFeature.class);
            final Row row2 = new Row(null);
            row2.setContext(this.dummy);
            row2.getCell(0).text = "Oops!";
            row.addChild(0, 0, row2);
            treeWidgetFeature.insertRows(row, 0, new Row[] { row2 });
        }
    }
    
    private void fakeCollapse(final Row row) {
        Log.printf("xidget.tree", "%s: fake collapse: %s\n", this.hashCode(), row);
        final ITreeWidgetFeature treeWidgetFeature = this.xidget.getFeature(ITreeWidgetFeature.class);
        final List<Row> children = row.getChildren(0);
        for (int i = 0; i < children.size(); ++i) {
            final Row row2 = children.get(i);
            if (row2.getContext() == this.dummy) {
                row.removeChild(0, i);
                treeWidgetFeature.removeRows(row, i, new Row[] { row2 }, true);
                break;
            }
        }
    }
    
    private boolean needsTemporaryChild(final Row row) {
        final Row parent = row.getParent();
        return (parent == null || parent.isExpanded()) && this.getSwitch(row).getHandlers().size() != 0 && !row.isExpanded() && row.getChildCount() == 0;
    }
    
    private List<IXidget> findTree(final Row row) {
        final ArrayList<IXidget> list = new ArrayList<IXidget>();
        final IXidget table = row.getTable();
        if (table == null) {
            return Collections.emptyList();
        }
        final IModelObject config = table.getConfig();
        final IModelObject parent = config.getParent();
        for (final IXidget xidget : this.findTreeCandidates(row)) {
            if (Xlate.get(xidget.getConfig(), "table", XPath.createExpression("table")).query(parent, null).contains(config)) {
                list.add(xidget);
            }
        }
        return list;
    }
    
    private List<IXidget> findTreeCandidates(final Row row) {
        final IXidget table = row.getTable();
        if (table == null) {
            return Collections.emptyList();
        }
        final ArrayList<IXidget> list = new ArrayList<IXidget>();
        final IXidget parent = table.getParent();
        for (final IXidget xidget : parent.getChildren()) {
            if (xidget.getConfig().isType(parent.getConfig().getType())) {
                list.add(xidget);
            }
        }
        if (parent != null) {
            for (IXidget xidget2 = parent.getParent(); xidget2 != null && xidget2.getFeature(ITreeWidgetFeature.class) != null; xidget2 = xidget2.getParent()) {
                for (final IXidget xidget3 : xidget2.getChildren()) {
                    final IModelObject config = xidget3.getConfig();
                    if (Xlate.get(config, "recursive", false) && config.isType(xidget2.getConfig().getType())) {
                        list.add(xidget3);
                    }
                }
            }
        }
        return list;
    }
    
    private ConfigurationSwitch<IXidget> getSwitch(final Row row) {
        if (row.getSwitch() == null) {
            final ConfigurationSwitch<IXidget> switch1 = new ConfigurationSwitch<IXidget>(this.switchListener);
            for (final IXidget defaultHandler : this.findTree(row)) {
                final IExpression value = Xlate.get(defaultHandler.getConfig(), "when", (IExpression)null);
                if (value != null) {
                    switch1.addCase(value, defaultHandler);
                }
                else {
                    switch1.setDefaultHandler(defaultHandler);
                }
            }
            row.setSwitch(switch1);
        }
        return row.getSwitch();
    }
}

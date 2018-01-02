// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.tree;

import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import org.xidget.ConfigurationSwitch;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.List;
import org.xidget.IXidget;

public class Row
{
    private IXidget table;
    private Row parent;
    private List<List<Row>> tables;
    private StatefulContext context;
    private List<Cell> cells;
    private ConfigurationSwitch<IXidget> treeSwitch;
    private boolean expanded;
    
    public Row(final IXidget table) {
        this.cells = new ArrayList<Cell>(1);
        this.tables = new ArrayList<List<Row>>(1);
        this.table = table;
    }
    
    public void setContext(final StatefulContext context) {
        this.context = context;
    }
    
    public StatefulContext getContext() {
        return this.context;
    }
    
    public Row getParent() {
        return this.parent;
    }
    
    public void addChild(final int i, final int n, final Row row) {
        if (i >= this.tables.size()) {
            while (i >= this.tables.size()) {
                this.tables.add(null);
            }
        }
        List<Row> list = this.tables.get(i);
        if (list == null) {
            list = new ArrayList<Row>();
            this.tables.set(i, list);
        }
        list.add(n, row);
        row.parent = this;
    }
    
    public Row removeChild(final int n, final int n2) {
        if (n >= this.tables.size()) {
            return null;
        }
        final Row row = this.tables.get(n).remove(n2);
        row.parent = null;
        return row;
    }
    
    public int getOffset(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += this.getChildren(i).size();
        }
        return n2;
    }
    
    public List<Row> getChildren(final int n) {
        if (n < this.tables.size()) {
            final List<Row> list = this.tables.get(n);
            if (list != null) {
                return list;
            }
        }
        return Collections.emptyList();
    }
    
    public List<Row> getChildren() {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<Row>();
        for (final List<Row> list2 : this.tables) {
            if (list2 != null) {
                list.addAll(list2);
            }
        }
        return (List<Row>)list;
    }
    
    public int getChildCount() {
        int n = 0;
        for (final List<Row> list : this.tables) {
            if (list != null) {
                n += list.size();
            }
        }
        return n;
    }
    
    public Cell getCell(final int n) {
        for (int i = this.cells.size(); i <= n; ++i) {
            this.cells.add(new Cell());
        }
        return this.cells.get(n);
    }
    
    public List<Cell> getCells() {
        return this.cells;
    }
    
    public IXidget getTable() {
        return this.table;
    }
    
    public IXidget getTree(final IXidget xidget) {
        return (this.table != null) ? this.table.getParent() : xidget;
    }
    
    public void setSwitch(final ConfigurationSwitch<IXidget> treeSwitch) {
        this.treeSwitch = treeSwitch;
    }
    
    public ConfigurationSwitch<IXidget> getSwitch() {
        return this.treeSwitch;
    }
    
    public void setExpanded(final boolean expanded) {
        this.expanded = expanded;
    }
    
    public boolean isExpanded() {
        return this.expanded;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.context != null) {
            sb.append(this.context.getObject().toString());
            sb.append(" [");
            if (this.cells != null) {
                final Iterator<Cell> iterator = this.cells.iterator();
                while (iterator.hasNext()) {
                    sb.append(iterator.next().text);
                    sb.append("|");
                }
            }
            sb.append("]");
        }
        return sb.toString();
    }
}

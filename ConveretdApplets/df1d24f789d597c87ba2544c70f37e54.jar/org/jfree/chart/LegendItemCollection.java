// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class LegendItemCollection
{
    private List items;
    
    public LegendItemCollection() {
        this.items = new ArrayList();
    }
    
    public void add(final LegendItem item) {
        this.items.add(item);
    }
    
    public void addAll(final LegendItemCollection collection) {
        this.items.addAll(collection.items);
    }
    
    public LegendItem get(final int index) {
        return this.items.get(index);
    }
    
    public int getItemCount() {
        return this.items.size();
    }
    
    public Iterator iterator() {
        return this.items.iterator();
    }
}

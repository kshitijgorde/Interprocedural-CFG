// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.gantt;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.Series;

public class TaskSeries extends Series
{
    private List tasks;
    
    public TaskSeries(final String name) {
        super(name);
        this.tasks = new ArrayList();
    }
    
    public void add(final Task task) {
        this.tasks.add(task);
        this.fireSeriesChanged();
    }
    
    public void remove(final Task task) {
        this.tasks.remove(task);
        this.fireSeriesChanged();
    }
    
    public void removeAll() {
        this.tasks.clear();
        this.fireSeriesChanged();
    }
    
    public List getTasks() {
        return Collections.unmodifiableList((List<?>)this.tasks);
    }
    
    public Task get(final int index) {
        return this.tasks.get(index);
    }
    
    public int getItemCount() {
        return this.tasks.size();
    }
}

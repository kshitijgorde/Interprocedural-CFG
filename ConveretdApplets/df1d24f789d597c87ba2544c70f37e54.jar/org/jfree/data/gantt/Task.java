// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.gantt;

import org.jfree.data.time.SimpleTimePeriod;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.time.TimePeriod;

public class Task
{
    private String description;
    private TimePeriod duration;
    private Double percentComplete;
    private List subtasks;
    
    public Task(final String description, final TimePeriod duration) {
        this.description = description;
        this.duration = duration;
        this.percentComplete = null;
        this.subtasks = new ArrayList();
    }
    
    public Task(final String description, final Date start, final Date end) {
        this(description, new SimpleTimePeriod(start, end));
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public TimePeriod getDuration() {
        return this.duration;
    }
    
    public void setDuration(final TimePeriod duration) {
        this.duration = duration;
    }
    
    public Double getPercentComplete() {
        return this.percentComplete;
    }
    
    public void setPercentComplete(final double percent) {
        this.setPercentComplete(new Double(percent));
    }
    
    public void setPercentComplete(final Double percent) {
        this.percentComplete = percent;
    }
    
    public void addSubtask(final Task subtask) {
        this.subtasks.add(subtask);
    }
    
    public void removeSubtask(final Task subtask) {
        this.subtasks.remove(subtask);
    }
    
    public int getSubtaskCount() {
        return this.subtasks.size();
    }
    
    public Task getSubtask(final int index) {
        return this.subtasks.get(index);
    }
}

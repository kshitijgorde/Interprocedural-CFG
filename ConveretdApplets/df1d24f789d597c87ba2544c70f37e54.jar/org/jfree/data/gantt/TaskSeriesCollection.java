// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data.gantt;

import org.jfree.data.SeriesChangeEvent;
import java.util.Iterator;
import org.jfree.data.SeriesChangeListener;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.AbstractSeriesDataset;

public class TaskSeriesCollection extends AbstractSeriesDataset implements GanttCategoryDataset
{
    private List keys;
    private List data;
    
    public TaskSeriesCollection() {
        this.keys = new ArrayList();
        this.data = new ArrayList();
    }
    
    public String getSeriesName(final int series) {
        final TaskSeries ts = this.data.get(series);
        return ts.getName();
    }
    
    public int getSeriesCount() {
        return this.getRowCount();
    }
    
    public int getRowCount() {
        return this.data.size();
    }
    
    public int getColumnCount() {
        return this.keys.size();
    }
    
    public List getRowKeys() {
        return this.data;
    }
    
    public List getColumnKeys() {
        return this.keys;
    }
    
    public Comparable getColumnKey(final int item) {
        return this.keys.get(item);
    }
    
    public int getColumnIndex(final Comparable columnKey) {
        return this.keys.indexOf(columnKey);
    }
    
    public int getRowIndex(final Comparable rowKey) {
        return this.data.indexOf(rowKey);
    }
    
    public Comparable getRowKey(final int index) {
        final TaskSeries series = this.data.get(index);
        return series.getName();
    }
    
    public void add(final TaskSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("XYSeriesCollection.addSeries(...): cannot add null series.");
        }
        this.data.add(series);
        series.addChangeListener(this);
        for (final Task task : series.getTasks()) {
            final String key = task.getDescription();
            final int index = this.keys.indexOf(key);
            if (index < 0) {
                this.keys.add(key);
            }
        }
        this.fireDatasetChanged();
    }
    
    public void remove(final int series) {
        if (series < 0 || series > this.getSeriesCount()) {
            throw new IllegalArgumentException("TaskSeriesCollection.remove(...): index outside valid range.");
        }
        final TaskSeries ts = this.data.get(series);
        ts.removeChangeListener(this);
        this.data.remove(series);
        this.fireDatasetChanged();
    }
    
    public void remove(final TaskSeries series) {
        if (series == null) {
            throw new IllegalArgumentException("TaskSeriesCollection.remove(...): cannot remove null series.");
        }
        if (this.data.contains(series)) {
            series.removeChangeListener(this);
            this.data.remove(series);
            this.fireDatasetChanged();
        }
    }
    
    public void removeAll() {
        for (final TaskSeries series : this.data) {
            series.removeChangeListener(this);
        }
        this.data.clear();
        this.fireDatasetChanged();
    }
    
    public Number getValue(final Comparable rowKey, final Comparable columnKey) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getValue(row, column);
    }
    
    public Number getValue(final int row, final int column) {
        return this.getStartValue(row, column);
    }
    
    public Number getStartValue(final Comparable rowKey, final Comparable columnKey) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getStartValue(row, column);
    }
    
    public Number getStartValue(final int row, final int column) {
        Number result = null;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            result = new Long(task.getDuration().getStart().getTime());
        }
        return result;
    }
    
    public Number getEndValue(final Comparable rowKey, final Comparable columnKey) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getEndValue(row, column);
    }
    
    public Number getEndValue(final int row, final int column) {
        Number result = null;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            result = new Long(task.getDuration().getEnd().getTime());
        }
        return result;
    }
    
    public Number getPercentComplete(final int row, final int column) {
        Number result = null;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            result = task.getPercentComplete();
        }
        return result;
    }
    
    public Number getPercentComplete(final Comparable rowKey, final Comparable columnKey) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getPercentComplete(row, column);
    }
    
    public int getSubIntervalCount(final int row, final int column) {
        int result = 0;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            result = task.getSubtaskCount();
        }
        return result;
    }
    
    public int getSubIntervalCount(final Comparable rowKey, final Comparable columnKey) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getSubIntervalCount(row, column);
    }
    
    public Number getStartValue(final int row, final int column, final int subinterval) {
        Number result = null;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            final Task subtask = task.getSubtask(subinterval);
            result = new Long(subtask.getDuration().getStart().getTime());
        }
        return result;
    }
    
    public Number getStartValue(final Comparable rowKey, final Comparable columnKey, final int subinterval) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getStartValue(row, column, subinterval);
    }
    
    public Number getEndValue(final int row, final int column, final int subinterval) {
        Number result = null;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            final Task subtask = task.getSubtask(subinterval);
            result = new Long(subtask.getDuration().getEnd().getTime());
        }
        return result;
    }
    
    public Number getEndValue(final Comparable rowKey, final Comparable columnKey, final int subinterval) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getStartValue(row, column, subinterval);
    }
    
    public Number getPercentComplete(final int row, final int column, final int subinterval) {
        Number result = null;
        final TaskSeries series = this.data.get(row);
        final int tasks = series.getItemCount();
        if (column < tasks) {
            final Task task = series.get(column);
            final Task subtask = task.getSubtask(subinterval);
            result = subtask.getPercentComplete();
        }
        return result;
    }
    
    public Number getPercentComplete(final Comparable rowKey, final Comparable columnKey, final int subinterval) {
        final int row = this.getRowIndex(rowKey);
        final int column = this.getColumnIndex(columnKey);
        return this.getPercentComplete(row, column, subinterval);
    }
    
    public void seriesChanged(final SeriesChangeEvent event) {
        this.refreshKeys();
        this.fireDatasetChanged();
    }
    
    private void refreshKeys() {
        this.keys.clear();
        for (int i = 0; i < this.getSeriesCount(); ++i) {
            final TaskSeries series = this.data.get(i);
            for (final Task task : series.getTasks()) {
                final String key = task.getDescription();
                final int index = this.keys.indexOf(key);
                if (index < 0) {
                    this.keys.add(key);
                }
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.util.List;
import org.jfree.util.TableOrder;

public class CategoryToPieDataset extends AbstractDataset implements PieDataset, DatasetChangeListener
{
    private CategoryDataset source;
    private TableOrder extract;
    private int index;
    public static final int ROW = 0;
    public static final int COLUMN = 1;
    
    public CategoryToPieDataset(final CategoryDataset source, final TableOrder extract, final int index) {
        if (extract == null) {
            throw new IllegalArgumentException("Null 'extract' argument.");
        }
        (this.source = source).addChangeListener(this);
        this.extract = extract;
        this.index = index;
    }
    
    public int getItemCount() {
        int result = 0;
        if (this.source != null) {
            if (this.extract == TableOrder.BY_ROW) {
                result = this.source.getColumnCount();
            }
            else if (this.extract == TableOrder.BY_COLUMN) {
                result = this.source.getRowCount();
            }
        }
        return result;
    }
    
    public Number getValue(final int item) {
        Number result = null;
        if (this.source != null) {
            if (this.extract == TableOrder.BY_ROW) {
                result = this.source.getValue(this.index, item);
            }
            else if (this.extract == TableOrder.BY_COLUMN) {
                result = this.source.getValue(item, this.index);
            }
        }
        return result;
    }
    
    public Comparable getKey(final int index) {
        Comparable result = null;
        if (this.extract == TableOrder.BY_ROW) {
            result = this.source.getColumnKey(index);
        }
        else if (this.extract == TableOrder.BY_COLUMN) {
            result = this.source.getRowKey(index);
        }
        return result;
    }
    
    public int getIndex(final Comparable key) {
        int result = -1;
        if (this.extract == TableOrder.BY_ROW) {
            result = this.source.getColumnIndex(key);
        }
        else if (this.extract == TableOrder.BY_COLUMN) {
            result = this.source.getRowIndex(key);
        }
        return result;
    }
    
    public List getKeys() {
        List result = null;
        if (this.extract == TableOrder.BY_ROW) {
            result = this.source.getColumnKeys();
        }
        else if (this.extract == TableOrder.BY_COLUMN) {
            result = this.source.getRowKeys();
        }
        return result;
    }
    
    public Number getValue(final Comparable key) {
        Number result = null;
        final int keyIndex = this.getIndex(key);
        if (this.extract == TableOrder.BY_ROW) {
            result = this.source.getValue(this.index, keyIndex);
        }
        else if (this.extract == TableOrder.BY_COLUMN) {
            result = this.source.getValue(keyIndex, this.index);
        }
        return result;
    }
    
    public void datasetChanged(final DatasetChangeEvent event) {
        this.fireDatasetChanged();
    }
    
    public CategoryToPieDataset(final CategoryDataset source, final int extract, final int index) {
        (this.source = source).addChangeListener(this);
        this.extract = TableOrder.BY_ROW;
        if (extract == 1) {
            this.extract = TableOrder.BY_COLUMN;
        }
        this.index = index;
    }
}

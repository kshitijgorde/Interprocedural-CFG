// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import java.util.HashMap;
import prefuse.data.util.Index;
import prefuse.util.DataLib;
import prefuse.util.TypeLib;
import prefuse.util.collections.DefaultLiteralComparator;
import java.util.Map;
import java.util.Comparator;
import prefuse.data.Table;
import prefuse.data.event.ColumnListener;

public class ColumnMetadata implements ColumnListener
{
    private Table m_table;
    private String m_field;
    private boolean m_dynamic;
    private boolean m_init;
    private Comparator m_cmp;
    private Object m_default;
    private int m_min;
    private int m_max;
    private int m_median;
    private int m_unique;
    private Double m_mean;
    private Double m_stdev;
    private Double m_sum;
    private Object[] m_ordinalA;
    private Map m_ordinalM;
    
    public ColumnMetadata(final Table table, final String s) {
        this(table, s, DefaultLiteralComparator.getInstance(), true);
    }
    
    public ColumnMetadata(final Table table, final String field, final Comparator cmp, final boolean dynamic) {
        this.m_table = table;
        this.m_field = field;
        this.m_cmp = cmp;
        this.m_dynamic = dynamic;
    }
    
    public void dispose() {
        this.m_table.getColumn(this.m_field).removeColumnListener(this);
    }
    
    private void clearCachedValues() {
        this.m_min = -1;
        this.m_max = -1;
        this.m_median = -1;
        this.m_unique = -1;
        this.m_mean = null;
        this.m_stdev = null;
        this.m_sum = null;
        this.m_ordinalA = null;
        this.m_ordinalM = null;
    }
    
    public void calculateValues() {
        this.clearCachedValues();
        final boolean dynamic = this.m_dynamic;
        this.m_dynamic = true;
        this.getMinimumRow();
        this.getMaximumRow();
        this.getMedianRow();
        this.getUniqueCount();
        if (TypeLib.isNumericType(this.m_table.getColumnType(this.m_field))) {
            this.getMean();
            this.getDeviation();
            this.getSum();
        }
        this.getOrdinalArray();
        this.getOrdinalMap();
        this.m_init = true;
        this.m_dynamic = dynamic;
    }
    
    private void accessCheck() {
        if (this.m_init) {
            return;
        }
        if (this.m_dynamic) {
            this.clearCachedValues();
            this.m_table.getColumn(this.m_field).addColumnListener(this);
        }
        else {
            this.calculateValues();
        }
        this.m_init = true;
    }
    
    public Comparator getComparator() {
        return this.m_cmp;
    }
    
    public void setComparator(final Comparator cmp) {
        this.m_cmp = cmp;
        this.clearCachedValues();
    }
    
    public Object getDefaultValue() {
        return this.m_default;
    }
    
    public int getMinimumRow() {
        this.accessCheck();
        if (this.m_min == -1 && this.m_dynamic) {
            final Index index = this.m_table.getIndex(this.m_field);
            if (index != null) {
                this.m_min = index.minimum();
            }
            else {
                this.m_min = DataLib.min(this.m_table.tuples(), this.m_field, this.m_cmp).getRow();
            }
        }
        return this.m_min;
    }
    
    public int getMaximumRow() {
        this.accessCheck();
        if (this.m_max == -1 && this.m_dynamic) {
            final Index index = this.m_table.getIndex(this.m_field);
            if (index != null) {
                this.m_max = index.maximum();
            }
            else {
                this.m_max = DataLib.max(this.m_table.tuples(), this.m_field, this.m_cmp).getRow();
            }
        }
        return this.m_max;
    }
    
    public int getMedianRow() {
        this.accessCheck();
        if (this.m_median == -1 && this.m_dynamic) {
            final Index index = this.m_table.getIndex(this.m_field);
            if (index != null) {
                this.m_max = index.median();
            }
            else {
                this.m_median = DataLib.median(this.m_table.tuples(), this.m_field, this.m_cmp).getRow();
            }
        }
        return this.m_median;
    }
    
    public int getUniqueCount() {
        this.accessCheck();
        if (this.m_unique == -1 && this.m_dynamic) {
            final Index index = this.m_table.getIndex(this.m_field);
            if (index != null) {
                this.m_unique = index.uniqueCount();
            }
            else {
                this.m_unique = DataLib.uniqueCount(this.m_table.tuples(), this.m_field);
            }
        }
        return this.m_unique;
    }
    
    public double getMean() {
        this.accessCheck();
        if (this.m_mean == null && this.m_dynamic) {
            this.m_mean = new Double(DataLib.mean(this.m_table.tuples(), this.m_field));
        }
        return this.m_mean;
    }
    
    public double getDeviation() {
        this.accessCheck();
        if (this.m_stdev == null && this.m_dynamic) {
            this.m_stdev = new Double(DataLib.deviation(this.m_table.tuples(), this.m_field, this.getMean()));
        }
        return this.m_stdev;
    }
    
    public double getSum() {
        this.accessCheck();
        if (this.m_sum == null && this.m_dynamic) {
            this.m_sum = new Double(DataLib.sum(this.m_table.tuples(), this.m_field));
        }
        return this.m_sum;
    }
    
    public Object[] getOrdinalArray() {
        this.accessCheck();
        if (this.m_ordinalA == null && this.m_dynamic) {
            this.m_ordinalA = DataLib.ordinalArray(this.m_table.tuples(), this.m_field, this.m_cmp);
        }
        return this.m_ordinalA;
    }
    
    public Map getOrdinalMap() {
        this.accessCheck();
        if (this.m_ordinalM == null && this.m_dynamic) {
            final Object[] ordinalArray = this.getOrdinalArray();
            this.m_ordinalM = new HashMap();
            for (int i = 0; i < ordinalArray.length; ++i) {
                this.m_ordinalM.put(ordinalArray[i], new Integer(i));
            }
        }
        return this.m_ordinalM;
    }
    
    public void columnChanged(final Column column, final int n, final int n2, final int n3) {
        this.clearCachedValues();
    }
    
    public void columnChanged(final Column column, final int n, final boolean b) {
        this.columnChanged(column, 0, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final double n2) {
        this.columnChanged(column, 0, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final float n2) {
        this.columnChanged(column, 0, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final int n2) {
        this.columnChanged(column, 0, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final long n2) {
        this.columnChanged(column, 0, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final Object o) {
        this.columnChanged(column, 0, n, n);
    }
}

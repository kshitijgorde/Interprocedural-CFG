// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.util;

public interface ColumnSortable
{
    public static final int LESS_THAN = -1;
    public static final int EQUAL = 0;
    public static final int GREATER_THAN = 1;
    
    int compare(final Object p0, final Object p1, final int p2, final int p3, final String p4);
    
    int compare(final Object p0, final Object p1, final int[] p2, final int[] p3, final boolean[] p4, final String[] p5);
}

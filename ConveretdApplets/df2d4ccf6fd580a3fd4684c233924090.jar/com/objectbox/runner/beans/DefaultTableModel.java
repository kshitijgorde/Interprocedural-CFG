// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.awt.Point;
import java.util.Hashtable;

public class DefaultTableModel extends AbstrTableModel
{
    private Hashtable data;
    private Point cellpoint;
    private String[] h;
    private int nrows;
    
    public DefaultTableModel() {
        this.data = new Hashtable();
        this.cellpoint = new Point(0, 0);
        this.h = new String[] { "A" };
        this.nrows = 10;
    }
    
    public String getCell(final int n, final int n2) {
        if (n == 0) {
            return String.valueOf(n2);
        }
        final String value = this.data.get(new Point(n, n2));
        if (value == null) {
            return "";
        }
        return value;
    }
    
    public String[] getHeaders() {
        return this.h;
    }
    
    public int getNumberOfColumns() {
        return this.getHeaders().length;
    }
    
    public int getNumberOfRows() {
        return this.nrows;
    }
    
    public void setCell(final int n, final int n2) {
    }
    
    public void setCell(final int n, final int n2, final String s) {
        this.data.put(new Point(n, n2), s);
    }
    
    public void setHeaders(final String[] h) {
        this.h = h;
    }
    
    public void setNumberOfRows(final int nrows) {
        this.nrows = nrows;
    }
}

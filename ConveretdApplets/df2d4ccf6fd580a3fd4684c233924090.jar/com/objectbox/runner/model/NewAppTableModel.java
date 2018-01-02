// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Vector;
import java.awt.Point;
import java.util.Hashtable;
import com.objectbox.runner.beans.AbstrTableModel;

public class NewAppTableModel extends AbstrTableModel
{
    private Hashtable data;
    private Point cellpoint;
    private String[] h;
    private int nrows;
    private Hashtable newProps;
    private Hashtable newParam;
    
    public NewAppTableModel() {
        this.data = new Hashtable();
        this.cellpoint = new Point(0, 0);
        this.h = new String[] { "Name", "URL" };
        this.nrows = 0;
        this.newProps = null;
        this.newParam = null;
    }
    
    public void addData(final Vector vector) {
        final int n = this.data.size() + 1;
        for (int i = 0; i < vector.size(); ++i) {
            this.data.put(new Point(0, i + n), vector.elementAt(i));
        }
        this.nrows = this.data.size() + 1;
    }
    
    public String getCell(final int n, final int n2) {
        final JBAppletProperties value = this.data.get(new Point(0, n2));
        if (value == null) {
            return "";
        }
        final JBAppletProperties jbAppletProperties = value;
        if (n == 0) {
            return (String)jbAppletProperties.getProps().get("code");
        }
        return (String)jbAppletProperties.getProps().get("documentbase");
    }
    
    public Hashtable getData() {
        return this.data;
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
    
    public JBProperties getProperty(final int n) {
        return this.data.get(new Point(0, n));
    }
    
    public void init(final String s) {
        for (int i = 0; i < 10; ++i) {
            this.data.put(new Point(1, i), "abc" + i);
        }
    }
    
    public void setCell(final int n, final int n2) {
    }
    
    public void setCell(final int n, final int n2, final String s) {
        final Point point = new Point(n, n2);
        this.data.put(point, s);
        final Point point2 = new Point(0, n2);
        if (point.y < this.newProps.size()) {
            this.newProps.put(this.data.get(point2), s);
        }
        else {
            this.newParam.put(this.data.get(point2), s);
        }
    }
    
    public void setData(final Vector vector) {
        this.data.clear();
        final int n = 1;
        for (int i = 0; i < vector.size(); ++i) {
            this.data.put(new Point(0, i + n), vector.elementAt(i));
        }
        this.nrows = this.data.size() + 1;
    }
    
    public void setHeaders(final String[] h) {
        this.h = h;
    }
    
    public void setNumberOfRows(final int nrows) {
        this.nrows = nrows;
    }
}

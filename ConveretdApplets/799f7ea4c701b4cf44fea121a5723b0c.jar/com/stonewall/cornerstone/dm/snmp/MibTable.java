// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

import java.util.Iterator;
import java.util.Collection;
import java.util.LinkedList;

public class MibTable extends MibObject implements SnmpListener
{
    LinkedList rows;
    String[] columns;
    
    public MibTable(final SnmpSession ssn, final String oid, final String[] columns) {
        super(ssn, oid);
        this.columns = columns;
        this.rows = new LinkedList();
    }
    
    public synchronized void clear() {
        this.rows.clear();
    }
    
    @Override
    public synchronized int get() throws SnmpException {
        System.out.println("table:get");
        this.clear();
        final MibRow primer = new MibRow(this);
        primer.setListener((SnmpListener)(primer.parent = this));
        return primer.getNext();
    }
    
    @Override
    public synchronized int getNext() throws SnmpException {
        System.out.println("Table (GetNext) not supported");
        return -1;
    }
    
    @Override
    public synchronized int set() throws SnmpException {
        System.out.println("Table (Set) not supported");
        return -1;
    }
    
    @Override
    public synchronized int getBulk() throws SnmpException {
        System.out.println("Table (GetBulk) not supported");
        return -1;
    }
    
    public String[] getColumns() {
        return this.columns;
    }
    
    public Collection getRows() {
        return this.rows;
    }
    
    public MibObject[][] getMatrix() {
        final int nRows = this.rows.size();
        final int nColumns = this.columns.length;
        if (nRows < 1 || nColumns < 1) {
            return null;
        }
        final MibObject[][] result = new MibObject[nRows][nColumns];
        final Iterator itr = this.rows.iterator();
        int r = 0;
        while (itr.hasNext()) {
            final MibRow row = itr.next();
            for (int c = 0; c < this.columns.length; ++c) {
                result[r][c] = row.columns[c];
            }
            ++r;
        }
        return result;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Table: ");
        sb.append(this.oid);
        sb.append('\n');
        final Iterator itr = this.rows.iterator();
        while (itr.hasNext()) {
            sb.append("Row: ");
            sb.append(itr.next().toString());
            sb.append('\n');
        }
        return sb.toString();
    }
    
    @Override
    public void requestSucceeded(final MibObject obj, final int ctag) {
        if (obj.hasExceptionValue()) {
            obj.setListener(null);
            this.listener.requestSucceeded(this, ctag);
            return;
        }
        final MibRow row = (MibRow)obj;
        if (this.detectColumnShift(row)) {
            obj.setListener(null);
            this.listener.requestSucceeded(this, ctag);
            return;
        }
        this.rows.add(row);
        try {
            row.getNext();
        }
        catch (SnmpException e) {
            System.out.println(e.toString());
            this.listener.requestFailed(this, ctag);
        }
    }
    
    @Override
    public void requestFailed(final MibObject obj, final int ctag) {
        this.listener.requestFailed(this, ctag);
    }
    
    @Override
    public void trapReceived(final Trap t) {
    }
    
    private boolean detectColumnShift(final MibRow row) {
        return !this.matchColumn(0, row);
    }
    
    private boolean matchColumn(final int index, final MibRow row) {
        boolean result = false;
        try {
            final String matchId = this.columns[index];
            final int matchLen = matchId.length();
            final String testId = row.columns[index].getId();
            final String substr = testId.substring(0, matchLen);
            result = substr.equals(matchId);
        }
        catch (Exception ex) {}
        return result;
    }
}

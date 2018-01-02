// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.util.Collection;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class TickUnits implements TickUnitSource, Cloneable, Serializable
{
    private List tickUnits;
    
    public TickUnits() {
        this.tickUnits = new ArrayList();
    }
    
    public void add(final TickUnit unit) {
        if (unit == null) {
            throw new NullPointerException("TickUnits.add(..): Null not permitted.");
        }
        this.tickUnits.add(unit);
        Collections.sort((List<Comparable>)this.tickUnits);
    }
    
    public int size() {
        return this.tickUnits.size();
    }
    
    public TickUnit get(final int pos) {
        return this.tickUnits.get(pos);
    }
    
    public TickUnit getLargerTickUnit(final TickUnit unit) {
        int index = Collections.binarySearch(this.tickUnits, unit);
        if (index >= 0) {
            ++index;
        }
        else {
            index = -index;
        }
        return this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
    }
    
    public TickUnit getCeilingTickUnit(final TickUnit unit) {
        int index = Collections.binarySearch(this.tickUnits, unit);
        if (index >= 0) {
            return this.tickUnits.get(index);
        }
        index = -(index + 1);
        return this.tickUnits.get(Math.min(index, this.tickUnits.size() - 1));
    }
    
    public TickUnit getCeilingTickUnit(final double size) {
        return this.getCeilingTickUnit(new NumberTickUnit(size, null));
    }
    
    public static TickUnitSource createStandardTickUnits() {
        final TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(1.0E-7, new DecimalFormat("0.0000000")));
        units.add(new NumberTickUnit(1.0E-6, new DecimalFormat("0.000000")));
        units.add(new NumberTickUnit(1.0E-5, new DecimalFormat("0.00000")));
        units.add(new NumberTickUnit(1.0E-4, new DecimalFormat("0.0000")));
        units.add(new NumberTickUnit(0.001, new DecimalFormat("0.000")));
        units.add(new NumberTickUnit(0.01, new DecimalFormat("0.00")));
        units.add(new NumberTickUnit(0.1, new DecimalFormat("0.0")));
        units.add(new NumberTickUnit(1.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(10.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(100.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(1000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(10000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(100000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1000000.0, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(1.0E7, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(1.0E8, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(1.0E9, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(2.5E-7, new DecimalFormat("0.00000000")));
        units.add(new NumberTickUnit(2.5E-6, new DecimalFormat("0.0000000")));
        units.add(new NumberTickUnit(2.5E-5, new DecimalFormat("0.000000")));
        units.add(new NumberTickUnit(2.5E-4, new DecimalFormat("0.00000")));
        units.add(new NumberTickUnit(0.0025, new DecimalFormat("0.0000")));
        units.add(new NumberTickUnit(0.025, new DecimalFormat("0.000")));
        units.add(new NumberTickUnit(0.25, new DecimalFormat("0.00")));
        units.add(new NumberTickUnit(2.5, new DecimalFormat("0.0")));
        units.add(new NumberTickUnit(25.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(250.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(2500.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(25000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(250000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2500000.0, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(2.5E7, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(2.5E8, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(2.5E9, new DecimalFormat("#,###,###,##0")));
        units.add(new NumberTickUnit(5.0E-7, new DecimalFormat("0.0000000")));
        units.add(new NumberTickUnit(5.0E-6, new DecimalFormat("0.000000")));
        units.add(new NumberTickUnit(5.0E-5, new DecimalFormat("0.00000")));
        units.add(new NumberTickUnit(5.0E-4, new DecimalFormat("0.0000")));
        units.add(new NumberTickUnit(0.005, new DecimalFormat("0.000")));
        units.add(new NumberTickUnit(0.05, new DecimalFormat("0.00")));
        units.add(new NumberTickUnit(0.5, new DecimalFormat("0.0")));
        units.add(new NumberTickUnit(5.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(50.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(500.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(5000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(50000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(500000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5000000.0, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(5.0E7, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(5.0E8, new DecimalFormat("#,###,##0")));
        units.add(new NumberTickUnit(5.0E9, new DecimalFormat("#,###,###,##0")));
        return units;
    }
    
    public static TickUnitSource createIntegerTickUnits() {
        final TickUnits units = new TickUnits();
        units.add(new NumberTickUnit(1.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(2.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(5.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(10.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(20.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(50.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(100.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(200.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(500.0, new DecimalFormat("0")));
        units.add(new NumberTickUnit(1000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(10000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(20000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(50000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(100000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(200000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(500000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1000000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2000000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5000000.0, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E7, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2.0E7, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5.0E7, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E8, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2.0E8, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5.0E8, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E9, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(2.0E9, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(5.0E9, new DecimalFormat("#,##0")));
        units.add(new NumberTickUnit(1.0E10, new DecimalFormat("#,##0")));
        return units;
    }
    
    public static TickUnitSource createStandardTickUnits(final Locale locale) {
        final TickUnits units = new TickUnits();
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        units.add(new NumberTickUnit(1.0E-7, numberFormat));
        units.add(new NumberTickUnit(1.0E-6, numberFormat));
        units.add(new NumberTickUnit(1.0E-5, numberFormat));
        units.add(new NumberTickUnit(1.0E-4, numberFormat));
        units.add(new NumberTickUnit(0.001, numberFormat));
        units.add(new NumberTickUnit(0.01, numberFormat));
        units.add(new NumberTickUnit(0.1, numberFormat));
        units.add(new NumberTickUnit(1.0, numberFormat));
        units.add(new NumberTickUnit(10.0, numberFormat));
        units.add(new NumberTickUnit(100.0, numberFormat));
        units.add(new NumberTickUnit(1000.0, numberFormat));
        units.add(new NumberTickUnit(10000.0, numberFormat));
        units.add(new NumberTickUnit(100000.0, numberFormat));
        units.add(new NumberTickUnit(1000000.0, numberFormat));
        units.add(new NumberTickUnit(1.0E7, numberFormat));
        units.add(new NumberTickUnit(1.0E8, numberFormat));
        units.add(new NumberTickUnit(1.0E9, numberFormat));
        units.add(new NumberTickUnit(2.5E-7, numberFormat));
        units.add(new NumberTickUnit(2.5E-6, numberFormat));
        units.add(new NumberTickUnit(2.5E-5, numberFormat));
        units.add(new NumberTickUnit(2.5E-4, numberFormat));
        units.add(new NumberTickUnit(0.0025, numberFormat));
        units.add(new NumberTickUnit(0.025, numberFormat));
        units.add(new NumberTickUnit(0.25, numberFormat));
        units.add(new NumberTickUnit(2.5, numberFormat));
        units.add(new NumberTickUnit(25.0, numberFormat));
        units.add(new NumberTickUnit(250.0, numberFormat));
        units.add(new NumberTickUnit(2500.0, numberFormat));
        units.add(new NumberTickUnit(25000.0, numberFormat));
        units.add(new NumberTickUnit(250000.0, numberFormat));
        units.add(new NumberTickUnit(2500000.0, numberFormat));
        units.add(new NumberTickUnit(2.5E7, numberFormat));
        units.add(new NumberTickUnit(2.5E8, numberFormat));
        units.add(new NumberTickUnit(2.5E9, numberFormat));
        units.add(new NumberTickUnit(5.0E-7, numberFormat));
        units.add(new NumberTickUnit(5.0E-6, numberFormat));
        units.add(new NumberTickUnit(5.0E-5, numberFormat));
        units.add(new NumberTickUnit(5.0E-4, numberFormat));
        units.add(new NumberTickUnit(0.005, numberFormat));
        units.add(new NumberTickUnit(0.05, numberFormat));
        units.add(new NumberTickUnit(0.5, numberFormat));
        units.add(new NumberTickUnit(5.0, numberFormat));
        units.add(new NumberTickUnit(50.0, numberFormat));
        units.add(new NumberTickUnit(500.0, numberFormat));
        units.add(new NumberTickUnit(5000.0, numberFormat));
        units.add(new NumberTickUnit(50000.0, numberFormat));
        units.add(new NumberTickUnit(500000.0, numberFormat));
        units.add(new NumberTickUnit(5000000.0, numberFormat));
        units.add(new NumberTickUnit(5.0E7, numberFormat));
        units.add(new NumberTickUnit(5.0E8, numberFormat));
        units.add(new NumberTickUnit(5.0E9, numberFormat));
        return units;
    }
    
    public static TickUnitSource createIntegerTickUnits(final Locale locale) {
        final TickUnits units = new TickUnits();
        final NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        units.add(new NumberTickUnit(1.0, numberFormat));
        units.add(new NumberTickUnit(2.0, numberFormat));
        units.add(new NumberTickUnit(5.0, numberFormat));
        units.add(new NumberTickUnit(10.0, numberFormat));
        units.add(new NumberTickUnit(20.0, numberFormat));
        units.add(new NumberTickUnit(50.0, numberFormat));
        units.add(new NumberTickUnit(100.0, numberFormat));
        units.add(new NumberTickUnit(200.0, numberFormat));
        units.add(new NumberTickUnit(500.0, numberFormat));
        units.add(new NumberTickUnit(1000.0, numberFormat));
        units.add(new NumberTickUnit(2000.0, numberFormat));
        units.add(new NumberTickUnit(5000.0, numberFormat));
        units.add(new NumberTickUnit(10000.0, numberFormat));
        units.add(new NumberTickUnit(20000.0, numberFormat));
        units.add(new NumberTickUnit(50000.0, numberFormat));
        units.add(new NumberTickUnit(100000.0, numberFormat));
        units.add(new NumberTickUnit(200000.0, numberFormat));
        units.add(new NumberTickUnit(500000.0, numberFormat));
        units.add(new NumberTickUnit(1000000.0, numberFormat));
        units.add(new NumberTickUnit(2000000.0, numberFormat));
        units.add(new NumberTickUnit(5000000.0, numberFormat));
        units.add(new NumberTickUnit(1.0E7, numberFormat));
        units.add(new NumberTickUnit(2.0E7, numberFormat));
        units.add(new NumberTickUnit(5.0E7, numberFormat));
        units.add(new NumberTickUnit(1.0E8, numberFormat));
        units.add(new NumberTickUnit(2.0E8, numberFormat));
        units.add(new NumberTickUnit(5.0E8, numberFormat));
        units.add(new NumberTickUnit(1.0E9, numberFormat));
        units.add(new NumberTickUnit(2.0E9, numberFormat));
        units.add(new NumberTickUnit(5.0E9, numberFormat));
        units.add(new NumberTickUnit(1.0E10, numberFormat));
        return units;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final TickUnits clone = (TickUnits)super.clone();
        clone.tickUnits = new ArrayList(this.tickUnits);
        return clone;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof TickUnits) {
            final TickUnits tu = (TickUnits)object;
            return tu.tickUnits.equals(this.tickUnits);
        }
        return false;
    }
}

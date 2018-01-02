// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import prefuse.util.TypeLib;
import prefuse.util.ui.ValuedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

public class NumberRangeModel extends DefaultBoundedRangeModel implements ValuedRangeModel
{
    protected Class m_type;
    protected Number m_min;
    protected Number m_max;
    protected Number m_lo;
    protected Number m_hi;
    
    public NumberRangeModel(final int n, final int n2, final int n3, final int n4) {
        this(new Integer(n), new Integer(n2), new Integer(n3), new Integer(n2));
    }
    
    public NumberRangeModel(final long n, final long n2, final long n3, final long n4) {
        this(new Long(n), new Long(n2), new Long(n3), new Long(n4));
    }
    
    public NumberRangeModel(final float n, final float n2, final float n3, final float n4) {
        this(new Float(n), new Float(n2), new Float(n3), new Float(n4));
    }
    
    public NumberRangeModel(final double n, final double n2, final double n3, final double n4) {
        this(new Double(n), new Double(n2), new Double(n3), new Double(n4));
    }
    
    public NumberRangeModel(final Number n, final Number n2, final Number n3, final Number n4) {
        this.m_type = TypeLib.getPrimitiveType(n3.getClass());
        this.setValueRange(n, n2, n3, n4);
    }
    
    protected void updateRange() {
        if (this.m_type == Integer.TYPE) {
            this.setRange(this.m_lo.intValue(), this.m_hi.intValue() - this.m_lo.intValue(), this.m_min.intValue(), this.m_max.intValue());
        }
        else if (this.m_type == Long.TYPE) {
            final int n = 10000 * (int)((this.m_lo.longValue() - this.m_min.longValue()) / (this.m_max.longValue() - this.m_min.longValue()));
            this.setRange(n, 10000 * (int)((this.m_hi.longValue() - this.m_min.longValue()) / (this.m_max.longValue() - this.m_min.longValue())) - n, 0, 10000);
        }
        else {
            final int n2 = 10000 * (int)((this.m_lo.doubleValue() - this.m_min.doubleValue()) / (this.m_max.doubleValue() - this.m_min.doubleValue()));
            this.setRange(n2, 10000 * (int)((this.m_hi.doubleValue() - this.m_min.doubleValue()) / (this.m_max.doubleValue() - this.m_min.doubleValue())) - n2, 0, 10000);
        }
    }
    
    protected void setRange(final int n, final int n2, final int n3, final int n4) {
        super.setRangeProperties(n, n2, n3, n4, false);
    }
    
    public void setRangeProperties(final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (n3 != this.getMinimum() || n4 != this.getMaximum()) {
            throw new IllegalArgumentException("Can not change min or max.");
        }
        this.m_lo = null;
        this.m_hi = null;
        super.setRangeProperties(n, n2, n3, n4, b);
    }
    
    public void setValueRange(final Number lo, final Number hi, final Number min, final Number max) {
        this.m_lo = lo;
        this.m_hi = hi;
        this.m_min = min;
        this.m_max = max;
        this.updateRange();
    }
    
    public void setValueRange(final double n, final double n2, final double n3, final double n4) {
        this.m_lo = new Double(n);
        this.m_hi = new Double(n2);
        this.m_min = new Double(n3);
        this.m_max = new Double(n4);
        this.updateRange();
    }
    
    public void setValueRange(final int n, final int n2, final int n3, final int n4) {
        this.m_lo = new Integer(n);
        this.m_hi = new Integer(n2);
        this.m_min = new Integer(n3);
        this.m_max = new Integer(n4);
        this.updateRange();
    }
    
    public void setValueRange(final long n, final long n2, final long n3, final long n4) {
        this.m_lo = new Long(n);
        this.m_hi = new Long(n2);
        this.m_min = new Long(n3);
        this.m_max = new Long(n4);
        this.updateRange();
    }
    
    public Object getMinValue() {
        return this.m_min;
    }
    
    public void setMinValue(final Number n) {
        this.setValueRange((Number)this.getLowValue(), (Number)this.getHighValue(), n, this.m_max);
    }
    
    public Object getMaxValue() {
        return this.m_max;
    }
    
    public void setMaxValue(final Number n) {
        this.setValueRange((Number)this.getLowValue(), (Number)this.getHighValue(), this.m_min, n);
    }
    
    public Object getLowValue() {
        if (this.m_lo == null) {
            this.m_lo = (Number)this.value(this.getValue());
        }
        return this.m_lo;
    }
    
    public void setLowValue(final Number n) {
        this.setValueRange(n, (Number)this.getHighValue(), this.m_min, this.m_max);
    }
    
    public Object getHighValue() {
        if (this.m_hi == null) {
            this.m_hi = (Number)this.value(this.getValue() + this.getExtent());
        }
        return this.m_hi;
    }
    
    public void setHighValue(final Number n) {
        this.setValueRange((Number)this.getLowValue(), n, this.m_min, this.m_max);
    }
    
    protected Object value(final int n) {
        final int minimum = this.getMinimum();
        final int maximum = this.getMaximum();
        if (this.m_type == Double.TYPE || this.m_type == Float.TYPE) {
            final double n2 = (n - minimum) / (maximum - minimum);
            final double doubleValue = this.m_min.doubleValue();
            final double n3 = doubleValue + n2 * (this.m_max.doubleValue() - doubleValue);
            return (this.m_type == Float.TYPE) ? new Float((float)n3) : new Double(n3);
        }
        if (this.m_type == Long.TYPE) {
            final long longValue = this.m_min.longValue();
            return new Long(longValue + (n - minimum) * (this.m_max.longValue() - longValue) / (maximum - minimum));
        }
        return new Integer(n);
    }
    
    public void setMinimum(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public void setMaximum(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public void setValue(final int value) {
        this.m_lo = null;
        super.setValue(value);
    }
    
    public void setExtent(final int extent) {
        this.m_hi = null;
        super.setExtent(extent);
    }
}

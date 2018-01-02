// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import java.util.HashMap;
import java.util.Map;
import prefuse.util.ui.ValuedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

public class ObjectRangeModel extends DefaultBoundedRangeModel implements ValuedRangeModel
{
    private Object[] m_objects;
    private Map m_ordinal;
    
    public ObjectRangeModel(final Object[] valueRange) {
        this.setValueRange(valueRange);
    }
    
    public void setValueRange(final Object[] array) {
        if (this.m_objects != null && array.length == this.m_objects.length) {
            boolean b = true;
            for (int i = 0; i < array.length; ++i) {
                if (array[i] != this.m_objects[i]) {
                    b = false;
                    break;
                }
            }
            if (b) {
                return;
            }
        }
        System.arraycopy(array, 0, this.m_objects = new Object[array.length], 0, array.length);
        if (this.m_ordinal == null) {
            this.m_ordinal = new HashMap();
        }
        else {
            this.m_ordinal.clear();
        }
        for (int j = 0; j < array.length; ++j) {
            this.m_ordinal.put(array[j], new Integer(j));
        }
        this.setRangeProperties(0, array.length - 1, 0, array.length - 1, false);
    }
    
    public Object getObject(final int n) {
        return this.m_objects[n];
    }
    
    public int getIndex(final Object o) {
        final Integer n = this.m_ordinal.get(o);
        return (n == null) ? -1 : n;
    }
    
    public Object getMinValue() {
        return this.m_objects[this.getMinimum()];
    }
    
    public Object getMaxValue() {
        return this.m_objects[this.getMaximum()];
    }
    
    public Object getLowValue() {
        return this.m_objects[this.getValue()];
    }
    
    public Object getHighValue() {
        return this.m_objects[this.getValue() + this.getExtent()];
    }
}

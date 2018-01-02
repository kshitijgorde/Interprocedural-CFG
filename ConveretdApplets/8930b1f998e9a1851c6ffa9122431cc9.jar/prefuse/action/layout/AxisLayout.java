// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import prefuse.data.query.ObjectRangeModel;
import prefuse.util.MathLib;
import prefuse.data.query.NumberRangeModel;
import prefuse.util.DataLib;
import prefuse.visual.VisualItem;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import prefuse.data.Tuple;
import prefuse.data.Table;
import prefuse.data.tuple.TupleSet;
import prefuse.data.expression.Predicate;
import prefuse.util.ui.ValuedRangeModel;

public class AxisLayout extends Layout
{
    private String m_field;
    private int m_scale;
    private int m_axis;
    private int m_type;
    private boolean m_modelSet;
    private ValuedRangeModel m_model;
    private Predicate m_filter;
    private double m_min;
    private double m_range;
    private double[] m_dist;
    
    public AxisLayout(final String s, final String field) {
        super(s);
        this.m_scale = 0;
        this.m_axis = 0;
        this.m_type = -1;
        this.m_modelSet = false;
        this.m_model = null;
        this.m_filter = null;
        this.m_dist = new double[2];
        this.m_field = field;
    }
    
    public AxisLayout(final String s, final String s2, final int axis) {
        this(s, s2);
        this.setAxis(axis);
    }
    
    public AxisLayout(final String s, final String s2, final int n, final Predicate filter) {
        this(s, s2, n);
        this.setFilter(filter);
    }
    
    public void setDataField(final String field) {
        this.m_field = field;
        if (!this.m_modelSet) {
            this.m_model = null;
        }
    }
    
    public String getDataField() {
        return this.m_field;
    }
    
    public void setRangeModel(final ValuedRangeModel model) {
        this.m_model = model;
        this.m_modelSet = (model != null);
    }
    
    public ValuedRangeModel getRangeModel() {
        return this.m_model;
    }
    
    public void setFilter(final Predicate filter) {
        this.m_filter = filter;
    }
    
    public Predicate getFilter() {
        return this.m_filter;
    }
    
    public int getScale() {
        return this.m_scale;
    }
    
    public void setScale(final int scale) {
        if (scale < 0 || scale >= 4) {
            throw new IllegalArgumentException("Unrecognized scale value: " + scale);
        }
        this.m_scale = scale;
    }
    
    public int getAxis() {
        return this.m_axis;
    }
    
    public void setAxis(final int axis) {
        if (axis < 0 || axis >= 2) {
            throw new IllegalArgumentException("Unrecognized axis value: " + axis);
        }
        this.m_axis = axis;
    }
    
    public int getDataType() {
        return this.m_type;
    }
    
    public void setDataType(final int type) {
        if (type < 0 || type >= 3) {
            throw new IllegalArgumentException("Unrecognized data type value: " + type);
        }
        this.m_type = type;
    }
    
    public void run(final double n) {
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        this.setMinMax();
        switch (this.getDataType(group)) {
            case 2: {
                this.numericalLayout(group);
                break;
            }
            default: {
                this.ordinalLayout(group);
                break;
            }
        }
    }
    
    protected int getDataType(final TupleSet set) {
        if (this.m_type != -1) {
            return this.m_type;
        }
        boolean canGetDouble = true;
        if (set instanceof Table) {
            canGetDouble = ((Table)set).canGetDouble(this.m_field);
        }
        else {
            final Iterator tuples = set.tuples();
            while (tuples.hasNext()) {
                if (!tuples.next().canGetDouble(this.m_field)) {
                    canGetDouble = false;
                    break;
                }
            }
        }
        if (canGetDouble) {
            return 2;
        }
        return 1;
    }
    
    private void setMinMax() {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        if (this.m_axis == 0) {
            this.m_min = layoutBounds.getMinX();
            this.m_range = layoutBounds.getMaxX() - this.m_min;
        }
        else {
            this.m_min = layoutBounds.getMaxY();
            this.m_range = layoutBounds.getMinY() - this.m_min;
        }
    }
    
    protected void set(final VisualItem visualItem, final double n) {
        final double n2 = this.m_min + n * this.m_range;
        if (this.m_axis == 0) {
            this.setX(visualItem, null, n2);
        }
        else {
            this.setY(visualItem, null, n2);
        }
    }
    
    protected void numericalLayout(final TupleSet set) {
        if (!this.m_modelSet) {
            this.m_dist[0] = DataLib.min(set, this.m_field).getDouble(this.m_field);
            this.m_dist[1] = DataLib.max(set, this.m_field).getDouble(this.m_field);
            final double n = this.m_dist[0];
            final double n2 = this.m_dist[1];
            if (this.m_model == null) {
                this.m_model = new NumberRangeModel(n, n2, n, n2);
            }
            else {
                ((NumberRangeModel)this.m_model).setValueRange(n, n2, n, n2);
            }
        }
        else {
            this.m_dist[0] = ((Number)this.m_model.getLowValue()).doubleValue();
            this.m_dist[1] = ((Number)this.m_model.getHighValue()).doubleValue();
        }
        final Iterator items = this.m_vis.items(this.m_group, this.m_filter);
        while (items.hasNext()) {
            final VisualItem visualItem = items.next();
            this.set(visualItem, MathLib.interp(this.m_scale, visualItem.getDouble(this.m_field), this.m_dist));
        }
    }
    
    protected void ordinalLayout(final TupleSet set) {
        if (!this.m_modelSet) {
            final Object[] ordinalArray = DataLib.ordinalArray(set, this.m_field);
            if (this.m_model == null) {
                this.m_model = new ObjectRangeModel(ordinalArray);
            }
            else {
                ((ObjectRangeModel)this.m_model).setValueRange(ordinalArray);
            }
        }
        final ObjectRangeModel objectRangeModel = (ObjectRangeModel)this.m_model;
        final int value = objectRangeModel.getValue();
        final double n = value + objectRangeModel.getExtent() - value;
        final Iterator items = this.m_vis.items(this.m_group, this.m_filter);
        while (items.hasNext()) {
            final VisualItem visualItem = items.next();
            this.set(visualItem, (objectRangeModel.getIndex(visualItem.get(this.m_field)) - value) / n);
        }
    }
}

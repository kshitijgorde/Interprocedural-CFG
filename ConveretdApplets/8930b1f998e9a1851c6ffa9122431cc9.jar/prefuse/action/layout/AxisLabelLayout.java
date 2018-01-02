// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import prefuse.data.tuple.TupleSet;
import prefuse.data.Tuple;
import prefuse.util.PrefuseLib;
import prefuse.data.util.Index;
import java.util.Iterator;
import prefuse.visual.VisualItem;
import prefuse.util.MathLib;
import prefuse.visual.VisualTable;
import prefuse.data.query.ObjectRangeModel;
import java.util.logging.Logger;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import prefuse.util.ui.ValuedRangeModel;

public class AxisLabelLayout extends Layout
{
    public static final String FRAC = "frac";
    public static final String LABEL = "_label";
    public static final String VALUE = "_value";
    private AxisLayout m_layout;
    private ValuedRangeModel m_model;
    private double m_lo;
    private double m_hi;
    private double m_prevlo;
    private double m_prevhi;
    private NumberFormat m_nf;
    private int m_axis;
    private boolean m_asc;
    private int m_scale;
    private double m_spacing;
    
    public AxisLabelLayout(final String s, final int n, final ValuedRangeModel valuedRangeModel) {
        this(s, n, valuedRangeModel, null);
    }
    
    public AxisLabelLayout(final String s, final int axis, final ValuedRangeModel model, final Rectangle2D layoutBounds) {
        super(s);
        this.m_nf = NumberFormat.getInstance();
        this.m_asc = true;
        this.m_scale = 0;
        if (layoutBounds != null) {
            this.setLayoutBounds(layoutBounds);
        }
        this.m_model = model;
        this.m_axis = axis;
        this.m_spacing = 50.0;
    }
    
    public AxisLabelLayout(final String s, final AxisLayout axisLayout) {
        this(s, axisLayout, null, 50.0);
    }
    
    public AxisLabelLayout(final String s, final AxisLayout axisLayout, final Rectangle2D rectangle2D) {
        this(s, axisLayout, rectangle2D, 50.0);
    }
    
    public AxisLabelLayout(final String s, final AxisLayout layout, final Rectangle2D layoutBounds, final double spacing) {
        super(s);
        this.m_nf = NumberFormat.getInstance();
        this.m_asc = true;
        this.m_scale = 0;
        if (layoutBounds != null) {
            this.setLayoutBounds(layoutBounds);
        }
        this.m_layout = layout;
        this.m_model = layout.getRangeModel();
        this.m_axis = layout.getAxis();
        this.m_scale = layout.getScale();
        this.m_spacing = spacing;
    }
    
    public NumberFormat getNumberFormat() {
        return this.m_nf;
    }
    
    public void setNumberFormat(final NumberFormat nf) {
        this.m_nf = nf;
    }
    
    public double getSpacing() {
        return this.m_spacing;
    }
    
    public void setSpacing(final double spacing) {
        this.m_spacing = spacing;
    }
    
    public int getScale() {
        return this.m_scale;
    }
    
    public void setScale(final int scale) {
        if (scale < 0 || scale >= 4) {
            throw new IllegalArgumentException("Unrecognized scale type: " + scale);
        }
        this.m_scale = scale;
    }
    
    public boolean isAscending() {
        return this.m_asc;
    }
    
    public void setAscending(final boolean asc) {
        this.m_asc = asc;
    }
    
    public void setRangeModel(final ValuedRangeModel model) {
        this.m_model = model;
    }
    
    public void run(final double n) {
        if (this.m_model == null && this.m_layout != null) {
            this.m_model = this.m_layout.getRangeModel();
        }
        if (this.m_model == null) {
            Logger.getLogger(this.getClass().getName()).warning("Axis labels missing a range model.");
            return;
        }
        final VisualTable table = this.getTable();
        final Double n2 = (Double)table.getClientProperty("frac");
        final double n3 = (n2 == null) ? 1.0 : n2;
        this.m_prevlo += n3 * (this.m_lo - this.m_prevlo);
        this.m_prevhi += n3 * (this.m_hi - this.m_prevhi);
        if (this.m_model instanceof ObjectRangeModel) {
            this.m_lo = this.m_model.getValue();
            this.m_hi = this.m_lo + this.m_model.getExtent();
            this.ordinalLayout(table);
        }
        else {
            this.m_lo = ((Number)this.m_model.getLowValue()).doubleValue();
            this.m_hi = ((Number)this.m_model.getHighValue()).doubleValue();
            switch (this.m_scale) {
                case 1: {
                    this.logLayout(table);
                    break;
                }
                case 2: {
                    this.sqrtLayout(table);
                    break;
                }
                default: {
                    this.linearLayout(table);
                    break;
                }
            }
        }
        this.garbageCollect(table);
    }
    
    protected void linearLayout(final VisualTable visualTable) {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double breadth = this.getBreadth(layoutBounds);
        final double n = this.m_hi - this.m_lo;
        final double n2 = this.m_prevhi - this.m_prevlo;
        double pow;
        if (this.m_lo >= 0.0) {
            pow = Math.pow(10.0, Math.floor(MathLib.log10(this.m_lo)));
        }
        else {
            pow = -Math.pow(10.0, 1.0 + Math.floor(MathLib.log10(-this.m_lo)));
        }
        final Iterator tuples = visualTable.tuples();
        while (tuples.hasNext()) {
            final VisualItem visualItem = tuples.next();
            this.reset(visualItem);
            final double double1 = visualItem.getDouble("_value");
            this.set(visualItem, (n == 0.0) ? 0.0 : ((double1 - this.m_lo) / n * breadth), layoutBounds);
        }
        final Index index = visualTable.index("_value");
        double linearStep = this.getLinearStep(n, (n == 0.0) ? 0.0 : (breadth / n));
        if (linearStep == 0.0) {
            linearStep = 1.0;
        }
        for (double n3 = pow; n3 <= this.m_hi; n3 += linearStep) {
            final double n4 = (n3 - this.m_lo) / n * breadth;
            if (n4 >= -0.5) {
                final int value;
                if ((value = index.get(n3)) >= 0) {
                    final VisualItem item = visualTable.getItem(value);
                    item.setVisible(true);
                    item.setEndVisible(true);
                }
                else {
                    final VisualItem addItem = visualTable.addItem();
                    addItem.set("_label", this.m_nf.format(n3));
                    addItem.setDouble("_value", n3);
                    final double n5 = (n2 == 0.0) ? 0.0 : ((n3 - this.m_prevlo) / n2);
                    if (n5 <= 0.0 || n5 >= 1.0) {
                        addItem.setStartVisible(true);
                    }
                    this.set(addItem, n5 * breadth, layoutBounds);
                    this.set(addItem, n4, layoutBounds);
                }
            }
        }
    }
    
    protected void sqrtLayout(final VisualTable visualTable) {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double breadth = this.getBreadth(layoutBounds);
        final double n = this.m_hi - this.m_lo;
        final double safeSqrt = MathLib.safeSqrt(this.m_prevlo);
        final double n2 = MathLib.safeSqrt(this.m_prevhi) - safeSqrt;
        final double pow = Math.pow(10.0, Math.floor(MathLib.safeLog10(this.m_lo)));
        final double safeSqrt2 = MathLib.safeSqrt(this.m_lo);
        final double n3 = MathLib.safeSqrt(this.m_hi) - safeSqrt2;
        final Iterator tuples = visualTable.tuples();
        while (tuples.hasNext()) {
            final VisualItem visualItem = tuples.next();
            this.reset(visualItem);
            final double double1 = visualItem.getDouble("_value");
            this.set(visualItem, (n == 0.0) ? 0.0 : ((MathLib.safeSqrt(double1) - safeSqrt2) / n3 * breadth), layoutBounds);
        }
        final Index index = visualTable.index("_value");
        double linearStep = this.getLinearStep(n, breadth / n);
        if (linearStep == 0.0) {
            linearStep = 1.0;
        }
        for (double n4 = pow; n4 <= this.m_hi; n4 += linearStep) {
            final double n5 = (MathLib.safeSqrt(n4) - safeSqrt2) / n3 * breadth;
            if (n5 >= -0.5) {
                final int value;
                if ((value = index.get(n4)) >= 0) {
                    final VisualItem item = visualTable.getItem(value);
                    item.setVisible(true);
                    item.setEndVisible(true);
                }
                else {
                    final VisualItem addItem = visualTable.addItem();
                    addItem.set("_label", this.m_nf.format(n4));
                    addItem.setDouble("_value", n4);
                    final double n6 = (n2 == 0.0) ? 0.0 : ((MathLib.safeSqrt(n4) - safeSqrt) / n2);
                    if (n6 <= 0.0 || n6 >= 1.0) {
                        addItem.setStartVisible(true);
                    }
                    this.set(addItem, n6 * breadth, layoutBounds);
                    this.set(addItem, n5, layoutBounds);
                }
            }
        }
    }
    
    protected void logLayout(final VisualTable visualTable) {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double breadth = this.getBreadth(layoutBounds);
        visualTable.clear();
        final double safeLog10 = MathLib.safeLog10(this.m_lo);
        final double safeLog11 = MathLib.safeLog10(this.m_hi);
        final double n = safeLog11 - safeLog10;
        final int n2 = (int)Math.floor(MathLib.log10(safeLog11 - safeLog10));
        final int n3 = (int)Math.floor(safeLog10);
        final int n4 = (int)Math.ceil(safeLog11);
        final double pow = Math.pow(10.0, n3);
        for (double pow2 = Math.pow(10.0, n4), n5 = pow * Math.pow(10.0, n2), n6 = pow, n7 = 0.0; n6 <= pow2; n6 += n5, ++n7) {
            final double safeLog12 = MathLib.safeLog10(n6);
            if (n7 != 0.0 && Math.abs(safeLog12 - Math.round(safeLog12)) < 1.0E-4) {
                n7 = 0.0;
                n5 *= 10.0;
            }
            final double n8 = (safeLog12 - safeLog10) / n * breadth;
            if (n8 >= -0.5) {
                final VisualItem addItem = visualTable.addItem();
                this.set(addItem, n8, layoutBounds);
                addItem.set("_label", (n7 == 0.0) ? this.m_nf.format(n6) : null);
                addItem.setDouble("_value", n6);
            }
        }
    }
    
    protected double getBreadth(final Rectangle2D rectangle2D) {
        switch (this.m_axis) {
            case 0: {
                return rectangle2D.getWidth();
            }
            default: {
                return rectangle2D.getHeight();
            }
        }
    }
    
    protected double adjust(final double n) {
        switch (this.m_scale) {
            case 1: {
                return Math.pow(10.0, n);
            }
            case 2: {
                return n * n;
            }
            default: {
                return n;
            }
        }
    }
    
    protected double getLinearStep(final double n, final double n2) {
        double pow = Math.pow(10.0, Math.floor(Math.log(n) / Math.log(10.0)));
        final double n3 = pow * n2 / this.m_spacing;
        if (n3 > 20.0) {
            pow /= 20.0;
        }
        else if (n3 > 10.0) {
            pow /= 10.0;
        }
        else if (n3 > 5.0) {
            pow /= 5.0;
        }
        else if (n3 > 4.0) {
            pow /= 4.0;
        }
        else if (n3 > 2.0) {
            pow /= 2.0;
        }
        else if (n3 < 1.0) {
            pow *= 2.0;
        }
        return pow;
    }
    
    protected void ordinalLayout(final VisualTable visualTable) {
        final ObjectRangeModel objectRangeModel = (ObjectRangeModel)this.m_model;
        final double n = this.m_hi - this.m_lo;
        final double n2 = this.m_prevhi - this.m_prevlo;
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double breadth = this.getBreadth(layoutBounds);
        int ordinalStep = this.getOrdinalStep(n, breadth / n);
        if (ordinalStep <= 0) {
            ordinalStep = 1;
        }
        final Iterator tuples = visualTable.tuples();
        while (tuples.hasNext()) {
            final VisualItem visualItem = tuples.next();
            this.reset(visualItem);
            final double double1 = visualItem.getDouble("_value");
            this.set(visualItem, (n == 0.0) ? 0.0 : ((double1 - this.m_lo) / n * breadth), layoutBounds);
        }
        final Index index = visualTable.index("_value");
        for (int n3 = (int)this.m_lo; n3 <= this.m_hi; n3 += ordinalStep) {
            final int value;
            if ((value = index.get((double)n3)) >= 0) {
                final VisualItem item = visualTable.getItem(value);
                item.set(VisualItem.LABEL, objectRangeModel.getObject(n3).toString());
                item.setVisible(true);
                item.setEndVisible(true);
            }
            else {
                final VisualItem addItem = visualTable.addItem();
                addItem.set(VisualItem.LABEL, objectRangeModel.getObject(n3).toString());
                addItem.setDouble(VisualItem.VALUE, n3);
                final double n4 = (n2 == 0.0) ? 0.0 : ((n3 - this.m_prevlo) / n2);
                if (n4 <= 0.0 || n4 >= 1.0) {
                    addItem.setStartVisible(true);
                }
                this.set(addItem, n4 * breadth, layoutBounds);
                this.set(addItem, (n3 - this.m_lo) * breadth / n, layoutBounds);
            }
        }
    }
    
    protected int getOrdinalStep(final double n, final double n2) {
        return (n2 >= this.m_spacing) ? 1 : ((int)Math.ceil(this.m_spacing / n2));
    }
    
    protected void set(final VisualItem visualItem, double n, final Rectangle2D rectangle2D) {
        switch (this.m_axis) {
            case 0: {
                n = (this.m_asc ? (n + rectangle2D.getMinX()) : (rectangle2D.getMaxX() - n));
                PrefuseLib.updateDouble(visualItem, VisualItem.X, n);
                PrefuseLib.updateDouble(visualItem, VisualItem.Y, rectangle2D.getMinY());
                PrefuseLib.updateDouble(visualItem, VisualItem.X2, n);
                PrefuseLib.updateDouble(visualItem, VisualItem.Y2, rectangle2D.getMaxY());
                break;
            }
            case 1: {
                n = (this.m_asc ? (rectangle2D.getMaxY() - n - 1.0) : (n + rectangle2D.getMinY()));
                PrefuseLib.updateDouble(visualItem, VisualItem.X, rectangle2D.getMinX());
                PrefuseLib.updateDouble(visualItem, VisualItem.Y, n);
                PrefuseLib.updateDouble(visualItem, VisualItem.X2, rectangle2D.getMaxX());
                PrefuseLib.updateDouble(visualItem, VisualItem.Y2, n);
                break;
            }
        }
    }
    
    protected void reset(final VisualItem visualItem) {
        visualItem.setVisible(false);
        visualItem.setEndVisible(false);
        visualItem.setStartStrokeColor(visualItem.getStrokeColor());
        visualItem.revertToDefault(VisualItem.STROKECOLOR);
        visualItem.revertToDefault(VisualItem.ENDSTROKECOLOR);
        visualItem.setStartTextColor(visualItem.getTextColor());
        visualItem.revertToDefault(VisualItem.TEXTCOLOR);
        visualItem.revertToDefault(VisualItem.ENDTEXTCOLOR);
        visualItem.setStartFillColor(visualItem.getFillColor());
        visualItem.revertToDefault(VisualItem.FILLCOLOR);
        visualItem.revertToDefault(VisualItem.ENDFILLCOLOR);
    }
    
    protected void garbageCollect(final VisualTable visualTable) {
        final Iterator tuples = visualTable.tuples();
        while (tuples.hasNext()) {
            final VisualItem visualItem = tuples.next();
            if (!visualItem.isStartVisible() && !visualItem.isEndVisible()) {
                visualTable.removeTuple(visualItem);
            }
        }
    }
    
    protected VisualTable getTable() {
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        if (group == null) {
            final VisualTable addTable = this.m_vis.addTable(this.m_group, PrefuseLib.getAxisLabelSchema());
            addTable.index("_value");
            return addTable;
        }
        if (group instanceof VisualTable) {
            return (VisualTable)group;
        }
        throw new IllegalStateException("Group already exists, not being used for labels");
    }
}

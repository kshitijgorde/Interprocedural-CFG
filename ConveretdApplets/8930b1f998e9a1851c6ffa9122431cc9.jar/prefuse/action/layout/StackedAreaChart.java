// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import prefuse.util.ArrayLib;
import java.util.Iterator;
import prefuse.util.MathLib;
import prefuse.visual.VisualItem;
import prefuse.data.Table;
import java.util.Arrays;
import prefuse.util.ui.ValuedRangeModel;
import prefuse.util.PrefuseLib;
import prefuse.data.query.NumberRangeModel;
import java.awt.geom.Rectangle2D;

public class StackedAreaChart extends Layout
{
    private String m_field;
    private String m_start;
    private String m_end;
    private String[] columns;
    private double[] baseline;
    private double[] peaks;
    private float[] poly;
    private double m_padding;
    private float m_threshold;
    private Rectangle2D bounds;
    private int m_orientation;
    private boolean m_horiz;
    private boolean m_top;
    private boolean m_norm;
    private NumberRangeModel m_model;
    
    public StackedAreaChart(final String s, final String s2, final String[] array) {
        this(s, s2, array, 1.0);
    }
    
    public StackedAreaChart(final String s, final String field, final String[] columns, final double threshold) {
        super(s);
        this.m_padding = 0.05;
        this.m_orientation = 3;
        this.m_horiz = false;
        this.m_top = false;
        this.m_norm = false;
        this.columns = columns;
        this.baseline = new double[columns.length];
        this.peaks = new double[columns.length];
        this.poly = new float[4 * columns.length];
        this.m_field = field;
        this.m_start = PrefuseLib.getStartField(field);
        this.m_end = PrefuseLib.getEndField(field);
        this.setThreshold(threshold);
        this.m_model = new NumberRangeModel(0, 1, 0, 1);
    }
    
    public void setColumns(final String[] columns) {
        this.columns = columns;
    }
    
    public void setNormalized(final boolean norm) {
        this.m_norm = norm;
    }
    
    public boolean isNormalized() {
        return this.m_norm;
    }
    
    public double getPaddingPercentage() {
        return this.m_padding;
    }
    
    public void setPaddingPercentage(final double padding) {
        if (padding < 0.0 || padding > 1.0) {
            throw new IllegalArgumentException("Illegal padding percentage: " + padding);
        }
        this.m_padding = padding;
    }
    
    public double getThreshold() {
        return this.m_threshold;
    }
    
    public void setThreshold(final double n) {
        this.m_threshold = (float)n;
    }
    
    public ValuedRangeModel getRangeModel() {
        return this.m_model;
    }
    
    public int getOrientation() {
        return this.m_orientation;
    }
    
    public void setOrientation(final int orientation) {
        if (orientation != 2 && orientation != 3 && orientation != 0 && orientation != 1) {
            throw new IllegalArgumentException("Invalid orientation value: " + orientation);
        }
        this.m_orientation = orientation;
        this.m_horiz = (this.m_orientation == 0 || this.m_orientation == 1);
        this.m_top = (this.m_orientation == 2 || this.m_orientation == 0);
    }
    
    public void run(final double n) {
        this.bounds = this.getLayoutBounds();
        Arrays.fill(this.baseline, 0.0);
        final float n2 = (float)(this.m_horiz ? this.bounds.getMaxY() : this.bounds.getMinX());
        final float n3 = (float)(this.m_horiz ? this.bounds.getWidth() : this.bounds.getHeight());
        final int horiz = this.m_horiz ? 1 : 0;
        final int n4 = this.m_horiz ? 0 : 1;
        final int n5 = this.m_top ? 1 : -1;
        final float n6 = (float)(this.m_horiz ? (this.bounds.getMinY() - this.bounds.getMaxY()) : (this.bounds.getMaxX() - this.bounds.getMinX())) / (this.columns.length - 1);
        final int length = this.columns.length;
        final double peaks = this.getPeaks();
        Arrays.fill(this.baseline, (float)(this.m_horiz ? (this.m_top ? this.bounds.getMinX() : this.bounds.getMaxX()) : (this.m_top ? this.bounds.getMinY() : this.bounds.getMaxY())));
        this.m_model.setValueRange(0.0, peaks, 0.0, peaks);
        final Iterator tuplesReversed = ((Table)this.m_vis.getGroup(this.m_group)).tuplesReversed();
        while (tuplesReversed.hasNext()) {
            final VisualItem visualItem = tuplesReversed.next();
            if (!visualItem.isVisible()) {
                continue;
            }
            float max = 0.0f;
            int n7 = length;
            while (--n7 >= 0) {
                this.poly[2 * (length - 1 - n7) + horiz] = n2 + n7 * n6;
                this.poly[2 * (length - 1 - n7) + n4] = (float)this.baseline[n7];
            }
            for (int i = 0; i < this.columns.length; ++i) {
                final int n8 = 2 * (length + i);
                final double double1 = visualItem.getDouble(this.columns[i]);
                final double[] baseline = this.baseline;
                final int n9 = i;
                baseline[n9] += n5 * n3 * MathLib.linearInterp(double1, 0.0, this.peaks[i]);
                this.poly[n8 + horiz] = n2 + i * n6;
                this.poly[n8 + n4] = (float)this.baseline[i];
                max = Math.max(max, Math.abs(this.poly[2 * (length - 1 - i) + n4] - this.poly[n8 + n4]));
            }
            if (max < this.m_threshold) {
                visualItem.setVisible(false);
            }
            this.setX(visualItem, null, 0.0);
            this.setY(visualItem, null, 0.0);
            this.setPolygon(visualItem, this.poly);
        }
    }
    
    private double getPeaks() {
        double n = 0.0;
        Arrays.fill(this.peaks, 0.0);
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            for (int i = 0; i < this.columns.length; ++i) {
                final double double1 = visualItem.getDouble(this.columns[i]);
                final double[] peaks = this.peaks;
                final int n2 = i;
                peaks[n2] += double1;
                n += double1;
            }
        }
        double max = ArrayLib.max(this.peaks);
        if (!this.m_norm) {
            Arrays.fill(this.peaks, max);
        }
        if (!this.m_norm) {
            for (int j = 0; j < this.peaks.length; ++j) {
                final double[] peaks2 = this.peaks;
                final int n3 = j;
                peaks2[n3] += this.m_padding * this.peaks[j];
            }
            max += this.m_padding * max;
        }
        if (this.m_norm) {
            max = 1.0;
        }
        if (Double.isNaN(max)) {
            max = 0.0;
        }
        return max;
    }
    
    private void setPolygon(final VisualItem visualItem, final float[] array) {
        final float[] polygon = this.getPolygon(visualItem, this.m_field);
        final float[] polygon2 = this.getPolygon(visualItem, this.m_start);
        final float[] polygon3 = this.getPolygon(visualItem, this.m_end);
        System.arraycopy(polygon, 0, polygon2, 0, polygon.length);
        System.arraycopy(array, 0, polygon, 0, array.length);
        System.arraycopy(array, 0, polygon3, 0, array.length);
        visualItem.setValidated(false);
    }
    
    private float[] getPolygon(final VisualItem visualItem, final String s) {
        float[] array = (float[])visualItem.get(s);
        if (array == null || array.length < 4 * this.columns.length) {
            final int length = this.columns.length;
            final float n = (float)(this.m_horiz ? (this.bounds.getMinY() - this.bounds.getMaxY()) : (this.bounds.getMaxX() - this.bounds.getMinX())) / (length - 1);
            final float n2 = (float)(this.m_horiz ? (this.m_top ? this.bounds.getMaxX() : this.bounds.getMinX()) : (this.m_top ? this.bounds.getMinY() : this.bounds.getMaxY()));
            final float n3 = (float)(this.m_horiz ? this.bounds.getMaxY() : this.bounds.getMinX());
            final int horiz = this.m_horiz ? 1 : 0;
            array = new float[4 * length];
            Arrays.fill(array, n2);
            for (int i = 0; i < length; ++i) {
                array[2 * (length - 1 - i) + horiz] = (array[2 * (length + i) + horiz] = i * n + n3);
            }
            visualItem.set(s, array);
        }
        return array;
    }
}

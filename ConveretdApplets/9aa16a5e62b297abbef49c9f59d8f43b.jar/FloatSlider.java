import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

public class FloatSlider extends Scrollbar
{
    private static final int DEFAULT_RANGE = 1000;
    private static final int DEFAULT_VISIBLE = 20;
    private final int RANGE;
    private float minFloat;
    private float maxFloat;
    private boolean isLogScale;
    
    public FloatSlider(final int n, final float n2, final int n3, final float n4, final float n5, final int range, final boolean isLogScale) {
        super(n, 0, n3, 0, range);
        this.RANGE = range;
        this.isLogScale = isLogScale;
        this.setAll(n4, n5, n2);
    }
    
    public FloatSlider(final int n, final float n2, final int n3, final float n4, final float n5, final int n6) {
        this(n, n2, n3, n4, n5, n6, false);
    }
    
    public FloatSlider(final int n, final float n2, final float n3, final float n4, final boolean b) {
        this(n, n2, 20, n3, n4, 1000, b);
    }
    
    public FloatSlider(final int n, final float n2, final float n3, final float n4) {
        this(n, n2, 20, n3, n4, 1000, false);
    }
    
    protected int rangeValue(final float n) {
        if (this.isLogScale) {
            return Math.round(super.getMinimum() + (super.getMaximum() - super.getVisibleAmount() - super.getMinimum()) * (float)(Math.log(n / this.minFloat) / Math.log(this.maxFloat / this.minFloat)));
        }
        return Math.round(n * this.RANGE / (this.maxFloat - this.minFloat));
    }
    
    public float getFloatMinimum() {
        return this.minFloat;
    }
    
    public float getFloatMaximum() {
        return this.maxFloat;
    }
    
    public float getFloatValue() {
        if (this.isLogScale) {
            super.getValue();
            super.getVisibleAmount();
            super.getMinimum();
            super.getMaximum();
            return (float)(this.minFloat * Math.pow(this.maxFloat / this.minFloat, (super.getValue() - super.getMinimum()) / (super.getMaximum() - super.getVisibleAmount() - super.getMinimum())));
        }
        return super.getValue() * (this.maxFloat - this.minFloat) / this.RANGE + this.minFloat;
    }
    
    public void setFloatMinimum(final float n) {
        this.setAll(n, this.maxFloat, this.getFloatValue());
    }
    
    public void setFloatMaximum(final float n) {
        this.setAll(this.minFloat, n, this.getFloatValue());
    }
    
    public void setFloatValue(float n) {
        if (n < this.minFloat || this.maxFloat < n) {
            n = (this.maxFloat + this.minFloat) / 2.0f;
        }
        super.setValues(this.rangeValue(n), this.getVisibleAmount(), this.getMinimum(), this.getMaximum());
    }
    
    public void setAll(final float minFloat, final float maxFloat, final float floatValue) {
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.setFloatValue(floatValue);
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("FloatSlider example");
        final FloatSlider floatSlider = new FloatSlider(0, 100.1f, 5.5f, 50000.0f, true);
        final Label label = new Label("FloatSlider value: " + floatSlider.getFloatValue());
        floatSlider.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                label.setText("FloatSlider value: " + floatSlider.getFloatValue());
            }
        });
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(new Label("Range: " + floatSlider.getFloatMinimum() + " -> " + floatSlider.getFloatMaximum()));
        panel.add(floatSlider);
        panel.add(label);
        frame.add(panel);
        frame.setSize(new Dimension(800, 100));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(1);
            }
        });
        frame.setVisible(true);
    }
}

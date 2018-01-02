// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog.blur;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import jmaster.util.D.A.P;
import javax.swing.event.ChangeEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import jmaster.jumploader.view.impl.image.dialog.FilterPreviewView;

public class FilterGblurView extends FilterPreviewView implements ChangeListener
{
    private static final long \u0109 = 9163425500791196556L;
    public static final int RADIUS_MIN_VALUE = 1;
    public static final int RADIUS_MAX_VALUE = 10;
    public static final int RADIUS_DEF_VALUE = 2;
    public static final String PREFIX = "filterGblurView";
    protected JSlider \u010a;
    protected JLabel \u010d;
    protected JLabel \u010c;
    protected JLabel \u010b;
    
    public FilterGblurView(final B b, final IMainView mainView) {
        super(b, mainView);
        this.\u010a = new JSlider(1, 10);
        this.\u010d = new JLabel();
        this.\u010c = new JLabel();
        this.\u010b = new JLabel();
        this.I.injectProperties(this, "filterGblurView");
        this.I.injectProperties(this.\u010a, "filterGblurView", "radiusSlider");
        this.I.injectProperties(this.\u010d, "filterGblurView", "radiusLabel1");
        this.I.injectProperties(this.\u010c, "filterGblurView", "radiusLabel5");
        this.I.injectProperties(this.\u010b, "filterGblurView", "radiusLabel10");
        this.\u010a.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        this.\u010a.getLabelTable().put(new Integer(1), this.\u010d);
        this.\u010a.getLabelTable().put(new Integer(5), this.\u010c);
        this.\u010a.getLabelTable().put(new Integer(10), this.\u010b);
        this.\u010a.setLabelTable(this.\u010a.getLabelTable());
        this.\u010a.addChangeListener(this);
        this.setLayout(new GridBagLayout());
        this.add(this.\u00fb);
        this.add(this.\u010a);
        this.resetRadiusValue();
    }
    
    public int getRadiusValue() {
        return this.\u010a.getValue();
    }
    
    public void resetRadiusValue() {
        this.setRadiusValue(2);
    }
    
    public void setRadiusValue(final int n) {
        int value = n;
        if (value < 1) {
            value = 1;
        }
        if (value > 10) {
            value = 10;
        }
        this.\u010a.setValue(value);
        this.repaint();
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource().equals(this.\u010a) && this.\u00f8 != null) {
            final P p = new P();
            p.D(this.getRadiusValue());
            this.setPreviewImage(this.F.L().D(this.createImage(new FilteredImageSource(this.\u00f8.getSource(), p))));
        }
    }
}

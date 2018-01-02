// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image.dialog.contrast;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.event.ChangeEvent;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.Component;
import jmaster.util.swing.GUIHelper;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.util.D.A.M;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import jmaster.jumploader.view.impl.image.dialog.FilterPreviewView;

public class FilterContrastView extends FilterPreviewView implements ChangeListener
{
    private static final long \u0100 = 9163425500791196556L;
    public static final int CONTRAST_MIN_VALUE = 0;
    public static final int CONTRAST_MAX_VALUE = 100;
    public static final int CONTRAST_DEF_VALUE = 50;
    public static final int BRIGHTNESS_MIN_VALUE = 0;
    public static final int BRIGHTNESS_MAX_VALUE = 100;
    public static final int BRIGHTNESS_DEF_VALUE = 50;
    public static final String PREFIX = "filterContrastView";
    JLabel \u0101;
    JSlider \u0102;
    JLabel \u00fd;
    JLabel \u00ff;
    JLabel \u0106;
    JLabel \u0104;
    JSlider \u00fe;
    JLabel \u0103;
    JLabel \u0108;
    JLabel \u0107;
    protected M \u0105;
    
    public FilterContrastView(final B b, final IMainView mainView) {
        super(b, mainView);
        this.\u0101 = new JLabel();
        this.\u0102 = new JSlider();
        this.\u00fd = new JLabel();
        this.\u00ff = new JLabel();
        this.\u0106 = new JLabel();
        this.\u0104 = new JLabel();
        this.\u00fe = new JSlider();
        this.\u0103 = new JLabel();
        this.\u0108 = new JLabel();
        this.\u0107 = new JLabel();
        this.\u0105 = new M();
        this.I.injectProperties(this, "filterContrastView");
        this.I.injectProperties(this.\u0101, "filterContrastView", "lbBrightness");
        this.I.injectProperties(this.\u0102, "filterContrastView", "sdBrightness");
        this.I.injectProperties(this.\u00fd, "filterContrastView", "lb0");
        this.I.injectProperties(this.\u00ff, "filterContrastView", "lb50");
        this.I.injectProperties(this.\u0106, "filterContrastView", "lb100");
        this.\u0102.getLabelTable().put(new Integer(0), this.\u00fd);
        this.\u0102.getLabelTable().put(new Integer(50), this.\u00ff);
        this.\u0102.getLabelTable().put(new Integer(100), this.\u0106);
        this.\u0102.setLabelTable(this.\u0102.getLabelTable());
        this.setBrightnessValue(0.5f);
        this.I.injectProperties(this.\u0104, "filterContrastView", "lbContrast");
        this.I.injectProperties(this.\u00fe, "filterContrastView", "sdContrast");
        this.I.injectProperties(this.\u0103, "filterContrastView", "lc0");
        this.I.injectProperties(this.\u0108, "filterContrastView", "lc50");
        this.I.injectProperties(this.\u0107, "filterContrastView", "lc100");
        this.\u00fe.getLabelTable().put(new Integer(0), this.\u0103);
        this.\u00fe.getLabelTable().put(new Integer(50), this.\u0108);
        this.\u00fe.getLabelTable().put(new Integer(100), this.\u0107);
        this.\u00fe.setLabelTable(this.\u00fe.getLabelTable());
        this.setContrastValue(0.5f);
        final GridBagConstraints gbc = this.I.newGBC();
        this.setLayout(new GridBagLayout());
        this.add(this.\u00fb, this.A(gbc, 0, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS0));
        final JPanel panel = new JPanel();
        this.add(panel, this.A(gbc, 1, 0, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        panel.setLayout(new GridBagLayout());
        panel.add(this.\u0101, this.A(gbc, 0, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
        panel.add(this.\u0102, this.A(gbc, 1, 0, 1, 1, 0, 0, 2, 18, GUIHelper.INSETS4));
        panel.add(this.\u0104, this.A(gbc, 0, 1, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
        panel.add(this.\u00fe, this.A(gbc, 1, 1, 1, 1, 0, 0, 2, 18, GUIHelper.INSETS4));
        this.\u00fe.addChangeListener(this);
        this.\u0102.addChangeListener(this);
    }
    
    public float getContrastValue() {
        float n = 1.0f - (float)(this.\u00fe.getValue() / 100.0);
        if (n == 0.0f) {
            n = 0.01f;
        }
        if (n == 1.0f) {
            n = 0.99f;
        }
        return n;
    }
    
    public void resetContrastValue() {
        this.setContrastValue(0.5f);
    }
    
    public void setContrastValue(float n) {
        n = 1.0f - n;
        int value = (int)(n * 100.0f);
        if (value < 0) {
            value = 0;
        }
        if (value > 100) {
            value = 100;
        }
        this.\u00fe.setValue(value);
        this.repaint();
    }
    
    public float getBrightnessValue() {
        float n = (float)(this.\u0102.getValue() / 100.0);
        if (n == 0.0f) {
            n = 0.01f;
        }
        if (n == 1.0f) {
            n = 0.99f;
        }
        return n;
    }
    
    public void resetBrightnessValue() {
        this.setBrightnessValue(0.5f);
    }
    
    public void setBrightnessValue(final float n) {
        int value = (int)(n * 100.0f);
        if (value < 0) {
            value = 0;
        }
        if (value > 100) {
            value = 100;
        }
        this.\u0102.setValue(value);
        this.repaint();
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (changeEvent.getSource().equals(this.\u00fe) || changeEvent.getSource().equals(this.\u0102)) {
            this.\u0105.B(this.getContrastValue());
            this.\u0105.A(this.getBrightnessValue());
            this.setPreviewImage(this.F.L().D(this.createImage(new FilteredImageSource(this.\u00f8.getSource(), this.\u0105))));
        }
    }
}

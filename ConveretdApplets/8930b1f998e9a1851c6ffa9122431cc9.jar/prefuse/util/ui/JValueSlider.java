// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.util.Iterator;
import prefuse.util.StringLib;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class JValueSlider extends JComponent
{
    private Number m_min;
    private Number m_max;
    private Number m_value;
    private boolean m_ignore;
    private JLabel m_label;
    private JSlider m_slider;
    private JTextField m_field;
    private List m_listeners;
    private int m_smin;
    private int m_srange;
    
    public JValueSlider(final String s, final double n, final double n2, final double n3) {
        this(s, new Double(n), new Double(n2), new Double(n3));
    }
    
    public JValueSlider(final String s, final float n, final float n2, final float n3) {
        this(s, new Float(n), new Float(n2), new Float(n3));
    }
    
    public JValueSlider(final String s, final int n, final int maximum, final int n2) {
        this(s, new Integer(n), new Integer(maximum), new Integer(n2));
        this.m_smin = n;
        this.m_srange = maximum - n;
        this.m_slider.setMinimum(n);
        this.m_slider.setMaximum(maximum);
        this.setValue(new Integer(n2));
    }
    
    public JValueSlider(final String s, final long n, final long n2, final long n3) {
        this(s, new Long(n), new Long(n2), new Long(n3));
    }
    
    public JValueSlider(final String s, final Number min, final Number max, final Number value) {
        this.m_ignore = false;
        this.m_smin = 0;
        this.m_srange = 100;
        this.m_min = min;
        this.m_max = max;
        this.m_value = value;
        this.m_slider = new JSlider();
        this.m_label = new JLabel(s);
        this.m_field = new JTextField();
        this.m_listeners = new ArrayList();
        this.m_field.setBorder(null);
        this.setSliderValue();
        this.setFieldValue();
        this.initUI();
    }
    
    protected void initUI() {
        this.m_slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                if (JValueSlider.this.m_ignore) {
                    return;
                }
                JValueSlider.this.m_ignore = true;
                JValueSlider.this.m_value = JValueSlider.this.getSliderValue();
                JValueSlider.this.setFieldValue();
                JValueSlider.this.fireChangeEvent();
                JValueSlider.this.m_ignore = false;
            }
        });
        this.m_field.addActionListener(new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JValueSlider.this.m_ignore) {
                    return;
                }
                JValueSlider.this.m_ignore = true;
                final Number access$400 = JValueSlider.this.getFieldValue();
                if (access$400 != JValueSlider.this.m_value) {
                    JValueSlider.this.m_value = access$400;
                    JValueSlider.this.setSliderValue();
                }
                JValueSlider.this.fireChangeEvent();
                JValueSlider.this.m_ignore = false;
            }
        });
        this.m_field.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                final String text = JValueSlider.this.m_field.getText();
                if (isTextObscured(JValueSlider.this.m_field, text)) {
                    JValueSlider.this.m_field.setToolTipText(text);
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                JValueSlider.this.m_field.setToolTipText(null);
            }
        });
        this.m_label.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent mouseEvent) {
                final String text = JValueSlider.this.m_label.getText();
                if (isTextObscured(JValueSlider.this.m_label, text)) {
                    JValueSlider.this.m_label.setToolTipText(text);
                }
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                JValueSlider.this.m_label.setToolTipText(null);
            }
        });
        this.setLayout(new BoxLayout(this, 0));
        this.add(this.m_label);
        this.add(this.m_slider);
        this.add(this.m_field);
    }
    
    private static boolean isTextObscured(final JComponent component, final String s) {
        return component.getGraphics().getFontMetrics(component.getFont()).stringWidth(s) > component.getWidth();
    }
    
    public Number getValue() {
        return this.m_value;
    }
    
    public void setValue(final Number value) {
        this.m_value = value;
        this.setSliderValue();
        this.setFieldValue();
    }
    
    private Number getSliderValue() {
        if (this.m_value instanceof Integer) {
            final int value = this.m_slider.getValue();
            final int intValue = this.m_min.intValue();
            return new Integer(intValue + (value - this.m_smin) * (this.m_max.intValue() - intValue) / this.m_srange);
        }
        if (this.m_value instanceof Long) {
            final int value2 = this.m_slider.getValue();
            final long longValue = this.m_min.longValue();
            return new Long(longValue + (value2 - this.m_smin) * (this.m_max.longValue() - longValue) / this.m_srange);
        }
        final double n = (this.m_slider.getValue() - this.m_smin) / this.m_srange;
        final double doubleValue = this.m_min.doubleValue();
        final double n2 = doubleValue + n * (this.m_max.doubleValue() - doubleValue);
        return (this.m_value instanceof Double) ? new Double(n2) : new Float((float)n2);
    }
    
    private void setSliderValue() {
        int value;
        if (this.m_value instanceof Double || this.m_value instanceof Float) {
            final double doubleValue = this.m_value.doubleValue();
            final double doubleValue2 = this.m_min.doubleValue();
            value = this.m_smin + (int)Math.round(this.m_srange * ((doubleValue - doubleValue2) / (this.m_max.doubleValue() - doubleValue2)));
        }
        else {
            final long longValue = this.m_value.longValue();
            final long longValue2 = this.m_min.longValue();
            value = this.m_smin + (int)(this.m_srange * (longValue - longValue2) / (this.m_max.longValue() - longValue2));
        }
        this.m_slider.setValue(value);
    }
    
    private Number getFieldValue() {
        if (!(this.m_value instanceof Double)) {
            if (!(this.m_value instanceof Float)) {
                long long1;
                try {
                    long1 = Long.parseLong(this.m_field.getText());
                }
                catch (Exception ex) {
                    return this.m_value;
                }
                if (long1 < this.m_min.longValue() || long1 > this.m_max.longValue()) {
                    return this.m_value;
                }
                return (this.m_value instanceof Long) ? new Long(long1) : new Integer((int)long1);
            }
        }
        double double1;
        try {
            double1 = Double.parseDouble(this.m_field.getText());
        }
        catch (Exception ex2) {
            return this.m_value;
        }
        if (double1 < this.m_min.doubleValue() || double1 > this.m_max.doubleValue()) {
            return this.m_value;
        }
        return (this.m_value instanceof Double) ? new Double(double1) : new Float((float)double1);
    }
    
    private void setFieldValue() {
        String text;
        if (this.m_value instanceof Double || this.m_value instanceof Float) {
            text = StringLib.formatNumber(this.m_value.doubleValue(), 3);
        }
        else {
            text = String.valueOf(this.m_value.longValue());
        }
        this.m_field.setText(text);
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        if (!this.m_listeners.contains(changeListener)) {
            this.m_listeners.add(changeListener);
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.m_listeners.remove(changeListener);
    }
    
    protected void fireChangeEvent() {
        final Iterator<ChangeListener> iterator = (Iterator<ChangeListener>)this.m_listeners.iterator();
        final ChangeEvent changeEvent = new ChangeEvent(this);
        while (iterator.hasNext()) {
            iterator.next().stateChanged(changeEvent);
        }
    }
    
    public void setBackground(final Color color) {
        this.m_field.setBackground(color);
        this.m_label.setBackground(color);
        this.m_slider.setBackground(color);
        super.setBackground(color);
    }
    
    public void setForeground(final Color color) {
        this.m_field.setForeground(color);
        this.m_label.setForeground(color);
        this.m_slider.setForeground(color);
        super.setForeground(color);
    }
    
    public void setFont(final Font font) {
        this.m_field.setFont(font);
        this.m_label.setFont(font);
        this.m_slider.setFont(font);
        super.setFont(font);
    }
    
    public void setPreferredSize(final Dimension preferredSize) {
        final int min = Math.min(40, preferredSize.width / 5);
        final int min2 = Math.min(100, (preferredSize.width - min) / 2);
        final int n = preferredSize.width - min - min2;
        super.setPreferredSize(preferredSize);
        this.m_label.setPreferredSize(new Dimension(min2, preferredSize.height));
        this.m_slider.setPreferredSize(new Dimension(n, preferredSize.height));
        this.m_field.setPreferredSize(new Dimension(min, preferredSize.height));
    }
}

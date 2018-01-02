// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

import java.awt.event.TextEvent;
import java.beans.PropertyChangeEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.TextListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.awt.TextField;

public class SNumber extends TextField implements PropertyChangeListener
{
    protected PropertyChangeSupport boundSupport;
    private double value;
    private String formStr;
    private Format valFormat;
    private boolean validData;
    private boolean noColor;
    
    public SNumber(final double value) {
        this.value = 0.0;
        this.formStr = "%-+6.3g";
        this.valFormat = new Format("%-+6.3g");
        this.validData = true;
        this.noColor = false;
        this.boundSupport = new PropertyChangeSupport(this);
        this.value = value;
        this.setText(this.valFormat.form(this.value));
        this.addTextListener(new SNumber_this_textAdapter(this));
    }
    
    public SNumber() {
        this(0.0);
    }
    
    public void setFormat(final String formStr) {
        if (this.formStr.equals(formStr)) {
            return;
        }
        this.formStr = formStr;
        this.valFormat = new Format(formStr);
        this.setText(this.valFormat.form(this.value));
    }
    
    public String getFormat() {
        return this.formStr;
    }
    
    public void setValue(final double value) {
        this.validData = true;
        if (value == this.value) {
            return;
        }
        final double value2 = this.value;
        this.value = value;
        this.setText(this.valFormat.form(this.value));
        this.boundSupport.firePropertyChange("DValue", new Double(value2), new Double(this.value));
        if (this.isEditable()) {
            this.setBackground(Color.white);
        }
        else {
            this.setBackground(SystemColor.control);
        }
    }
    
    public double getValue() {
        if (this.noColor) {
            return this.value;
        }
        if (!this.isEditable()) {
            this.setBackground(SystemColor.control);
        }
        else if (!this.validData) {
            this.setBackground(Color.red);
        }
        else {
            this.setBackground(Color.white);
        }
        return this.value;
    }
    
    public boolean isValid() {
        return this.validData;
    }
    
    public boolean isNoColor() {
        return this.noColor;
    }
    
    public void setNoColor(final boolean noColor) {
        this.noColor = noColor;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.boundSupport.addPropertyChangeListener(propertyChangeListener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.boundSupport.removePropertyChangeListener(propertyChangeListener);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!this.isEditable()) {
            this.setBackground(SystemColor.control);
        }
        else {
            this.setBackground(Color.white);
        }
        if (propertyChangeEvent.getPropertyName().equals("DValue")) {
            final Double n = (Double)propertyChangeEvent.getNewValue();
            try {
                if (n == this.value) {
                    return;
                }
                final String form = this.valFormat.form(n);
                this.value = Double.valueOf(form);
                this.setText(form);
            }
            catch (Exception ex) {}
        }
    }
    
    void this_textValueChanged(final TextEvent textEvent) {
        final double value = this.value;
        final String trim = this.getText().trim();
        try {
            this.validData = true;
            if (trim != null && trim != "") {
                this.value = Double.valueOf(trim);
                if (!this.noColor) {
                    this.setBackground(Color.yellow);
                }
                if (this.value == value) {
                    return;
                }
                this.boundSupport.firePropertyChange("DValue", new Double(value), new Double(this.value));
            }
            else {
                if (!this.noColor) {
                    this.setBackground(Color.red);
                }
                this.validData = false;
                this.value = value;
            }
        }
        catch (NumberFormatException ex) {
            if (!this.noColor) {
                this.setBackground(Color.red);
            }
            this.validData = false;
            this.value = value;
        }
    }
}

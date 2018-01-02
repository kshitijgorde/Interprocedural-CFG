// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.event.AdjustmentEvent;
import java.awt.Dimension;
import edu.hws.jcm.data.Constant;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.MathObject;
import java.awt.Color;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Value;
import java.awt.Scrollbar;

public class VariableSlider extends Scrollbar implements InputObject, Tieable, Value
{
    protected VS variable;
    protected Value min;
    protected Value max;
    private Controller onUserAction;
    protected boolean integerValued;
    protected int intervals;
    protected long serialNumber;
    protected boolean needsValueCheck;
    protected int oldPosition;
    protected double minVal;
    protected double maxVal;
    
    public VariableSlider() {
        this(null, null, null, null);
    }
    
    public VariableSlider(final Value value, final Value value2) {
        this(null, value, value2, null);
    }
    
    public VariableSlider(final String s, final Value value, final Value value2, final Parser parser) {
        this(s, value, value2, parser, -1, 0);
    }
    
    public VariableSlider(final String name, final Value min, final Value max, final Parser parser, int intervals, final int n) {
        super(n);
        this.minVal = Double.NaN;
        this.setBackground(Color.lightGray);
        this.setMin(min);
        this.setMax(max);
        if (intervals <= 0) {
            intervals = 1000;
        }
        if (intervals <= 10) {
            intervals = 10;
        }
        this.intervals = intervals;
        final int n2 = intervals / 50 + 3;
        if (intervals < 100) {
            this.setBlockIncrement(1);
        }
        else {
            this.setBlockIncrement(intervals / 100);
        }
        this.setValues(intervals / 2, n2, 0, intervals + n2);
        this.variable = new VS(name);
        if (name != null) {
            super.setName(name);
        }
        if (parser != null && name != null) {
            parser.add(this.variable);
        }
        this.needsValueCheck = true;
        this.oldPosition = -1;
        this.getVal();
    }
    
    public void setName(final String s) {
        this.variable.setName(s);
        if (s != null) {
            super.setName(s);
        }
    }
    
    public void addTo(final Parser parser) {
        if (parser != null && this.variable.getName() != null) {
            parser.add(this.variable);
        }
    }
    
    public Variable getVariable() {
        return this.variable;
    }
    
    public void setIntegerValued(final boolean integerValued) {
        this.integerValued = integerValued;
        if (integerValued && !Double.isNaN(this.minVal) && !Double.isNaN(this.maxVal)) {
            this.checkIntegerLimits(this.minVal, this.maxVal);
        }
        this.needsValueCheck = true;
    }
    
    public boolean getIntegerValued() {
        return this.integerValued;
    }
    
    public void setMin(final Value value) {
        this.min = ((value == null) ? new Constant(-5.0) : value);
    }
    
    public void setMax(final Value value) {
        this.max = ((value == null) ? new Constant(5.0) : value);
    }
    
    public Value getMin() {
        return this.min;
    }
    
    public Value getMax() {
        return this.max;
    }
    
    public void setOnUserAction(final Controller onUserAction) {
        this.onUserAction = onUserAction;
        this.enableEvents(256L);
    }
    
    public void notifyControllerOnChange(final Controller onUserAction) {
        this.setOnUserAction(onUserAction);
    }
    
    public Controller getOnUserAction() {
        return this.onUserAction;
    }
    
    public long getSerialNumber() {
        if (this.needsValueCheck) {
            this.getVal();
        }
        return this.serialNumber;
    }
    
    public void sync(final Tie tie, final Tieable tieable) {
        if (tieable != this) {
            if (!(tieable instanceof Value)) {
                throw new IllegalArgumentException("Internal Error:  A VariableSlider can only sync with Value objects.");
            }
            this.setVal(((Value)tieable).getVal());
            this.serialNumber = tieable.getSerialNumber();
        }
    }
    
    public double getVal() {
        if (this.needsValueCheck) {
            boolean b = false;
            final double variableValue = this.variable.getVariableValue();
            try {
                final double val = this.min.getVal();
                final double val2 = this.max.getVal();
                if (!Double.isNaN(val) && !Double.isNaN(val2) && (val != this.minVal || val2 != this.maxVal)) {
                    if (this.integerValued) {
                        this.checkIntegerLimits(val, val2);
                    }
                    this.minVal = val;
                    this.maxVal = val2;
                    b = true;
                }
            }
            catch (JCMError jcmError) {}
            if (Double.isNaN(this.minVal) || Double.isNaN(this.maxVal) || Double.isInfinite(this.minVal) || Double.isInfinite(this.maxVal)) {
                this.variable.setVariableValue(Double.NaN);
                if (!Double.isNaN(variableValue)) {
                    ++this.serialNumber;
                }
                this.setValue(0);
            }
            else if (this.oldPosition != this.getValue()) {
                double clamp = this.clamp(this.minVal + (this.maxVal - this.minVal) * this.getValue() / this.intervals, this.minVal, this.maxVal);
                if (this.integerValued) {
                    clamp = Math.round(clamp);
                }
                if (clamp != variableValue) {
                    this.variable.setVariableValue(clamp);
                    ++this.serialNumber;
                }
            }
            else if (!Double.isNaN(variableValue) && b) {
                final double clamp2 = this.clamp(variableValue, this.minVal, this.maxVal);
                if (clamp2 != variableValue) {
                    this.variable.setVariableValue(clamp2);
                    ++this.serialNumber;
                }
                if (this.minVal != this.maxVal) {
                    this.setValue((int)((variableValue - this.minVal) / (this.maxVal - this.minVal) * this.intervals));
                }
            }
            this.oldPosition = this.getValue();
            this.needsValueCheck = false;
        }
        return this.variable.getVariableValue();
    }
    
    public void setVal(final double variableValue) {
        try {
            double val = this.min.getVal();
            double val2 = this.max.getVal();
            if (!Double.isNaN(variableValue) && !Double.isNaN(val) && !Double.isNaN(val2) && !Double.isInfinite(variableValue) && !Double.isInfinite(val) && !Double.isInfinite(val2)) {
                if (this.integerValued) {
                    val = Math.round(val);
                    val2 = Math.round(val2);
                }
                this.setValue((int)((this.clamp(variableValue, val, val2) - val) / (val2 - val) * this.intervals));
            }
        }
        catch (JCMError jcmError) {}
        this.variable.setVariableValue(variableValue);
        this.needsValueCheck = false;
        this.oldPosition = this.getValue();
        ++this.serialNumber;
    }
    
    public void checkInput() {
        this.needsValueCheck = true;
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (this.getOrientation() == 0) {
            return new Dimension(200, preferredSize.height);
        }
        return new Dimension(preferredSize.width, 200);
    }
    
    private void checkIntegerLimits(double n, double n2) {
        final int value = this.getValue();
        n = Math.round(n);
        n2 = Math.round(n2);
        final double n3 = Math.round(this.variable.getVariableValue());
        final double abs = Math.abs(n - n2);
        if (abs > 0.0 && abs != this.intervals) {
            this.intervals = (int)Math.min(abs, 10000.0);
            final int n4 = (int)((this.clamp(n3, n, n2) - n) / (n2 - n) * this.intervals);
            final int n5 = this.intervals / 50 + 3;
            if (this.intervals < 10) {
                this.setBlockIncrement(1);
            }
            else if (this.intervals < 100) {
                this.setBlockIncrement(this.intervals / 10);
            }
            else {
                this.setBlockIncrement(10 + this.intervals / 100);
            }
            this.setValues(n4, n5, 0, this.intervals + n5);
        }
        if (value == this.oldPosition) {
            this.oldPosition = this.getValue();
        }
        else {
            this.oldPosition = -1;
        }
    }
    
    private double clamp(final double n, final double n2, final double n3) {
        double n4 = n;
        if (n2 < n3) {
            if (n4 < n2) {
                n4 = n2;
            }
            else if (n4 > n3) {
                n4 = n3;
            }
        }
        else if (n4 < n3) {
            n4 = n3;
        }
        else if (n4 > n2) {
            n4 = n2;
        }
        return n4;
    }
    
    public void processAdjustmentEvent(final AdjustmentEvent adjustmentEvent) {
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
        super.processAdjustmentEvent(adjustmentEvent);
    }
    
    private class VS extends Variable
    {
        VS(final String s) {
            super(s);
        }
        
        public double getVal() {
            return VariableSlider.this.getVal();
        }
        
        public void setVal(final double val) {
            VariableSlider.this.setVal(val);
        }
        
        void setVariableValue(final double val) {
            super.setVal(val);
        }
        
        double getVariableValue() {
            return super.getVal();
        }
    }
}

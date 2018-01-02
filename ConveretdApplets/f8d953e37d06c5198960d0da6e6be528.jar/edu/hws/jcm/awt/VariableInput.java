// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import edu.hws.jcm.data.NumUtils;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.MathObject;
import java.awt.Component;
import java.awt.Label;
import edu.hws.jcm.data.Variable;
import java.awt.Color;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Value;
import java.awt.TextField;

public class VariableInput extends TextField implements InputObject, Tieable, Value
{
    protected VI variable;
    protected boolean throwErrors;
    protected String errorMessage;
    protected long serialNumber;
    protected String previousContents;
    private Controller onUserAction;
    private Controller onTextChange;
    protected double minValue;
    protected double maxValue;
    protected int inputStyle;
    public static final int EXPRESSION = 0;
    public static final int REAL = 1;
    public static final int INTEGER = 2;
    private transient Parser constantParser;
    
    public VariableInput() {
        this(null, null);
    }
    
    public VariableInput(final String name, final String s) {
        super((s == null) ? "0" : s, 12);
        this.minValue = -1.7976931348623157E308;
        this.maxValue = Double.MAX_VALUE;
        this.inputStyle = 0;
        this.setBackground(Color.white);
        this.variable = new VI(name);
        if (name != null) {
            super.setName(name);
        }
        this.previousContents = null;
        this.variable.checkInput();
        this.throwErrors = true;
    }
    
    public VariableInput(final String s, final String s2, final Parser parser) {
        this(s, s2);
        this.addTo(parser);
    }
    
    public Variable getVariable() {
        return this.variable;
    }
    
    public JCMPanel withLabel() {
        return this.withLabel(null, null);
    }
    
    public JCMPanel withLabel(final Color color, final Color color2) {
        final Label label = new Label(" " + this.variable.getName() + " =");
        final JCMPanel jcmPanel = new JCMPanel();
        if (color != null) {
            jcmPanel.setBackground(color);
            label.setBackground(color);
        }
        if (color2 != null) {
            jcmPanel.setForeground(color2);
            label.setBackground(color2);
        }
        jcmPanel.add(label, "West");
        jcmPanel.add(this, "Center");
        return jcmPanel;
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
    
    public void setOnUserAction(final Controller onUserAction) {
        this.onUserAction = onUserAction;
        this.enableEvents(128L);
    }
    
    public Controller getOnUserAction() {
        return this.onUserAction;
    }
    
    public void notifyControllerOnChange(final Controller onUserAction) {
        this.setOnUserAction(onUserAction);
    }
    
    public void setOnTextChange(final Controller onTextChange) {
        this.onTextChange = onTextChange;
        this.enableEvents(1024L);
        if (onTextChange != null) {
            this.throwErrors = false;
        }
    }
    
    public Controller getOnTextChange() {
        return this.onTextChange;
    }
    
    public double getVal() {
        return this.variable.getVal();
    }
    
    public void setVal(final double val) {
        this.variable.setVal(val);
    }
    
    public void setThrowErrors(final boolean throwErrors) {
        this.throwErrors = throwErrors;
    }
    
    public boolean getThrowErrors() {
        return this.throwErrors;
    }
    
    public void setMin(final double minValue) {
        if (!Double.isNaN(minValue)) {
            this.minValue = minValue;
            this.previousContents = null;
        }
    }
    
    public double getMin() {
        return this.minValue;
    }
    
    public void setMax(final double maxValue) {
        if (!Double.isNaN(maxValue)) {
            this.maxValue = maxValue;
            this.previousContents = null;
        }
    }
    
    public double getMax() {
        return this.maxValue;
    }
    
    public void setInputStyle(final int inputStyle) {
        if ((inputStyle == 0 || inputStyle == 1 || inputStyle == 2) && inputStyle != this.inputStyle) {
            this.previousContents = null;
            this.inputStyle = inputStyle;
        }
    }
    
    public int getInputStyle() {
        return this.inputStyle;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public void checkInput() {
        this.variable.checkInput();
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public void sync(final Tie tie, final Tieable tieable) {
        if (tieable == this) {
            return;
        }
        if (!(tieable instanceof Value)) {
            throw new IllegalArgumentException("Internal Error:  A VariableInput can only sync with Value objects.");
        }
        this.variable.setVal(((Value)tieable).getVal());
        this.serialNumber = tieable.getSerialNumber();
    }
    
    protected double convertInput(final String s) {
        double n = Double.NaN;
        if (this.inputStyle == 0) {
            if (this.constantParser == null) {
                this.constantParser = new Parser();
            }
            try {
                n = this.constantParser.parse(s).getVal();
            }
            catch (ParseError parseError) {
                this.errorMessage = "Illegal constant expression:  " + parseError.getMessage();
                if (this.throwErrors) {
                    this.setCaretPosition(parseError.context.pos);
                    this.requestFocus();
                }
            }
        }
        else if (this.inputStyle == 1) {
            try {
                n = new Double(s);
            }
            catch (NumberFormatException ex) {
                this.errorMessage = "Value is not a legal real number.";
                if (this.throwErrors) {
                    this.requestFocus();
                }
            }
        }
        else {
            try {
                n = Long.parseLong(s);
            }
            catch (NumberFormatException ex2) {
                this.errorMessage = "Value is not a legal integer.";
                if (this.throwErrors) {
                    this.requestFocus();
                }
            }
        }
        if (this.errorMessage == null && (n < this.minValue || n > this.maxValue)) {
            this.errorMessage = "Value outside legal range. It should be ";
            if (this.inputStyle == 2) {
                this.errorMessage += "an integer ";
            }
            else if (this.inputStyle == 1) {
                this.errorMessage += "a real number ";
            }
            if (this.minValue > -1.7976931348623157E308 && this.maxValue < Double.MAX_VALUE) {
                this.errorMessage = this.errorMessage + "between " + NumUtils.realToString(this.minValue) + " and " + NumUtils.realToString(this.maxValue);
            }
            else if (this.minValue > -1.7976931348623157E308) {
                this.errorMessage = this.errorMessage + "greater than or equal to " + NumUtils.realToString(this.minValue);
            }
            else {
                this.errorMessage = this.errorMessage + "less than or equal to " + NumUtils.realToString(this.maxValue);
            }
            if (this.throwErrors) {
                this.requestFocus();
            }
        }
        if (this.errorMessage != null) {
            throw new JCMError(this.errorMessage, this);
        }
        return n;
    }
    
    public void setText(final String text) {
        super.setText(text);
        this.previousContents = null;
    }
    
    private void justSetText(final String text) {
        super.setText(text);
    }
    
    public void processActionEvent(final ActionEvent actionEvent) {
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
        super.processActionEvent(actionEvent);
    }
    
    public void processTextEvent(final TextEvent textEvent) {
        this.previousContents = null;
        if (this.onTextChange != null) {
            this.onTextChange.compute();
        }
        super.processTextEvent(textEvent);
    }
    
    private class VI extends Variable
    {
        VI(final String s) {
            super(s);
        }
        
        public void setVal(final double val) {
            final double val2 = this.getVal();
            if (VariableInput.this.previousContents != null && VariableInput.this.previousContents.equals(VariableInput.this.getText()) && ((Double.isNaN(val) && Double.isNaN(val2)) || val == val2)) {
                return;
            }
            final VariableInput this$0 = VariableInput.this;
            ++this$0.serialNumber;
            VariableInput.this.justSetText(NumUtils.realToString(val));
            VariableInput.this.previousContents = VariableInput.this.getText();
            VariableInput.this.errorMessage = null;
            super.setVal(val);
        }
        
        void checkInput() {
            if (VariableInput.this.previousContents != null && VariableInput.this.previousContents.equals(VariableInput.this.getText())) {
                return;
            }
            VariableInput.this.errorMessage = null;
            final String text = VariableInput.this.getText();
            try {
                final double convertInput = VariableInput.this.convertInput(text);
                final double val = this.getVal();
                if ((Double.isNaN(convertInput) && Double.isNaN(val)) || convertInput == val) {
                    return;
                }
                final VariableInput this$0 = VariableInput.this;
                ++this$0.serialNumber;
                super.setVal(convertInput);
            }
            catch (JCMError jcmError) {
                if (!Double.isNaN(this.getVal())) {
                    final VariableInput this$2 = VariableInput.this;
                    ++this$2.serialNumber;
                }
                super.setVal(Double.NaN);
                if (VariableInput.this.throwErrors) {
                    throw jcmError;
                }
            }
        }
    }
}

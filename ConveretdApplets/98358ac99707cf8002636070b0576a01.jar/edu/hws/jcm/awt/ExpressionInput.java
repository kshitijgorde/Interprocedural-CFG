// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.ExpressionProgram;
import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Expression;
import java.awt.Color;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Value;
import java.awt.TextField;

public class ExpressionInput extends TextField implements InputObject, Value
{
    protected EI expr;
    protected Parser parser;
    protected String previousContents;
    protected boolean throwErrors;
    private Controller onUserAction;
    private Controller onTextChange;
    protected String errorMessage;
    private long serialNumber;
    
    public ExpressionInput() {
        this("", null);
    }
    
    public ExpressionInput(String text, final Parser parser) {
        super(30);
        this.expr = new EI();
        if (text == null) {
            text = "";
        }
        super.setText(text);
        this.setBackground(Color.white);
        this.setParser(parser);
        this.checkInput();
        this.throwErrors = true;
    }
    
    public void setParser(final Parser parser) {
        this.parser = ((parser == null) ? new Parser() : parser);
        this.previousContents = null;
    }
    
    public Expression getExpression() {
        return this.expr;
    }
    
    public Function getFunction(final Variable variable) {
        return new SimpleFunction(this.expr, variable);
    }
    
    public Function getFunction(final Variable[] array) {
        return new SimpleFunction(this.expr, array);
    }
    
    public double getVal() {
        return this.expr.getVal();
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
    
    public void setThrowErrors(final boolean throwErrors) {
        this.throwErrors = throwErrors;
    }
    
    public boolean getThrowErrors() {
        return this.throwErrors;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public void checkInput() {
        if (this.previousContents != null && this.previousContents.equals(this.getText())) {
            return;
        }
        final EI expr = this.expr;
        ++expr.serialNumber;
        final String text = this.getText();
        try {
            this.expr.exp = this.parser.parse(text);
            this.errorMessage = null;
            this.previousContents = this.getText();
        }
        catch (ParseError parseError) {
            this.expr.exp = null;
            if (this.throwErrors) {
                this.errorMessage = "Error in expression: " + parseError.getMessage();
                this.setCaretPosition(parseError.context.pos);
                this.requestFocus();
                throw new JCMError(parseError.getMessage(), this);
            }
            this.errorMessage = "Error in expression at position " + parseError.context.pos + ": " + parseError.getMessage();
        }
    }
    
    public void setText(final String text) {
        super.setText(text);
        this.previousContents = null;
    }
    
    public void processActionEvent(final ActionEvent actionEvent) {
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
        super.processActionEvent(actionEvent);
    }
    
    public void processTextEvent(final TextEvent textEvent) {
        if (this.onTextChange != null) {
            this.onTextChange.compute();
        }
        super.processTextEvent(textEvent);
    }
    
    protected class EI implements Expression
    {
        ExpressionProgram exp;
        EI derivativeOf;
        Variable wrt;
        int serialNumber;
        
        EI() {
            this.serialNumber = -1;
        }
        
        public double getVal() {
            this.checkForChanges();
            if (this.exp == null) {
                return Double.NaN;
            }
            return this.exp.getVal();
        }
        
        public double getValueWithCases(final Cases cases) {
            this.checkForChanges();
            if (this.exp == null) {
                return Double.NaN;
            }
            return this.exp.getValueWithCases(cases);
        }
        
        public String toString() {
            this.checkForChanges();
            if (this.exp == null) {
                return "(undefined)";
            }
            return this.exp.toString();
        }
        
        public Expression derivative(final Variable wrt) {
            final EI ei = new EI();
            ei.derivativeOf = this;
            ei.wrt = wrt;
            return ei;
        }
        
        public boolean dependsOn(final Variable variable) {
            this.checkForChanges();
            return this.exp.dependsOn(variable);
        }
        
        void checkForChanges() {
            if (this.derivativeOf != null) {
                this.derivativeOf.checkForChanges();
                if (this.serialNumber != this.derivativeOf.serialNumber) {
                    this.serialNumber = this.derivativeOf.serialNumber;
                    if (ExpressionInput.this.errorMessage != null) {
                        this.exp = null;
                    }
                    else {
                        this.exp = (ExpressionProgram)this.derivativeOf.exp.derivative(this.wrt);
                    }
                }
            }
        }
    }
}

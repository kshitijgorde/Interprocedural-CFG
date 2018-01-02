// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import java.util.LinkedList;

public class CalculatorModelVariable
{
    private int vIdentifier;
    private CalculatorModel vModel;
    private Object vValue;
    private LinkedList vListeners;
    private boolean vIsUpdatable;
    
    public CalculatorModelVariable(final CalculatorModel vModel, final int vIdentifier) {
        this.vModel = vModel;
        this.vIdentifier = vIdentifier;
        this.vIsUpdatable = false;
    }
    
    public int getIdentifier() {
        return this.vIdentifier;
    }
    
    public void addListener(final CalculatorModelListener calculatorModelListener) {
        if (this.vListeners == null) {
            this.vListeners = new LinkedList();
        }
        this.vListeners.add(calculatorModelListener);
    }
    
    public void publish(final Object value) {
        this.setValue(value);
        this.publish();
    }
    
    public Object getValue() {
        return this.vValue;
    }
    
    public void setValue(final Object vValue) {
        this.vValue = vValue;
    }
    
    public void setUpdatable(final boolean vIsUpdatable) {
        this.vIsUpdatable = vIsUpdatable;
    }
    
    public boolean isUpdatable() {
        return this.vIsUpdatable;
    }
    
    public void publish() {
        if (this.vListeners == null) {
            return;
        }
        for (int i = 0; i < this.vListeners.size(); ++i) {
            ((CalculatorModelListener)this.vListeners.get(i)).updateComponent(this.vModel, this.vIdentifier, this.vValue);
        }
    }
}

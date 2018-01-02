import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Memory
{
    double value;
    String currency;
    String operation;
    Vector valueList;
    
    public Memory() {
        this.valueList = new Vector();
    }
    
    public void setValue(final element element) {
        final double value = element.getValue();
        final String currency = element.getCurrency();
        final double euroValue = element.getEuroValue();
        final String lastOperation = element.getLastOperation();
        this.value = value;
        this.currency = currency;
        this.valueList.addElement(new element(currency, value, euroValue, lastOperation));
    }
    
    public void setValue(final double value, final String currency, final double n, final String s) {
        this.value = value;
        this.currency = currency;
        this.valueList.addElement(new element(currency, value, n, s));
    }
    
    public double getValue() {
        return this.value;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void clear() {
        this.value = 0.0;
    }
    
    public void clearList() {
        this.valueList.removeAllElements();
    }
    
    public void addElementInList(final element element) {
        this.valueList.addElement(element);
    }
    
    public double getValueInList(final int n) {
        return this.valueList.elementAt(n).getValue();
    }
    
    public double getEuroValueInList(final int n) {
        return this.valueList.elementAt(n).getEuroValue();
    }
    
    public String getLastOperationInList(final int n) {
        return this.valueList.elementAt(n).getLastOperation();
    }
    
    public String getCurrencyInList(final int n) {
        return this.valueList.elementAt(n).getCurrency();
    }
    
    private element getElementInList(final int n) {
        return this.valueList.elementAt(n);
    }
    
    public int getValueNumber() {
        return this.valueList.size();
    }
}

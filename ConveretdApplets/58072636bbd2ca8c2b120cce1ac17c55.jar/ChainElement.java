// 
// Decompiled by Procyon v0.5.30
// 

class ChainElement
{
    public static final short NUMERIC = 0;
    public static final short VARIABLE = 1;
    public static final short BASIC_OPERATOR = 2;
    public static final short PARANTHESIS = 3;
    public static final short FUNCTION = 4;
    public static final short TAG = 5;
    public static final short ERROR = 6;
    private short type;
    private String value;
    private boolean negative;
    private ChainElement next;
    private Parameter parameter;
    
    public ChainElement(final short type, final String value) {
        this.type = type;
        this.value = value;
        this.negative = false;
        this.next = null;
        this.parameter = null;
    }
    
    public ChainElement(final ChainElement chainElement) {
        if (chainElement != null) {
            this.type = chainElement.getType();
            this.value = chainElement.getValue();
            this.negative = chainElement.isNegative();
            this.parameter = chainElement.getParameter();
            if (chainElement.getNext() != null) {
                this.next = new ChainElement(chainElement.getNext());
            }
            else {
                this.next = null;
            }
        }
    }
    
    public void setNext(final ChainElement next) {
        this.next = next;
    }
    
    public void setParameter(final Chain chain) {
        this.parameter = new Parameter(chain);
    }
    
    public void removeParameter() {
        this.parameter = null;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public void setType(final short type) {
        this.type = type;
    }
    
    public void setNegative() {
        if (this.negative) {
            this.negative = false;
        }
        else {
            this.negative = true;
        }
    }
    
    public void eliminateNegativeSign() {
        this.negative = false;
    }
    
    public ChainElement getNext() {
        return this.next;
    }
    
    public Parameter getParameter() {
        return this.parameter;
    }
    
    public short getType() {
        return this.type;
    }
    
    public boolean isNegative() {
        return this.negative;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getSignedValue() {
        if (this.type != 0) {
            return null;
        }
        final double doubleValue = new Double(this.value);
        if (doubleValue > 0.0 && this.negative) {
            return "-" + this.value;
        }
        if (doubleValue < 0.0 && this.negative) {
            return this.value.substring(1, this.value.length());
        }
        return this.value;
    }
    
    public boolean hasBranch() {
        return this.parameter != null;
    }
    
    public boolean isError() {
        return this.getType() == 6;
    }
    
    public String toString() {
        if (this.parameter == null) {
            return this.getValue();
        }
        Parameter parameter = this.parameter;
        String s = this.getValue();
        while (parameter != null) {
            s = s + "<" + parameter.toString() + ">";
            parameter = parameter.getNextParameter();
        }
        return s;
    }
}

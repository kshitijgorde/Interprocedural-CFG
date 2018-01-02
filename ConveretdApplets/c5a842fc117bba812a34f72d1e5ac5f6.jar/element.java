// 
// Decompiled by Procyon v0.5.30
// 

public final class element
{
    private String currency;
    private String lastOperation;
    private double value;
    private double euroValue;
    
    public element(final String currency, final double euroValue, final double value, final String lastOperation) {
        this.currency = currency;
        this.lastOperation = lastOperation;
        this.value = value;
        this.euroValue = euroValue;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public double getEuroValue() {
        return this.euroValue;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public String getLastOperation() {
        return this.lastOperation;
    }
}

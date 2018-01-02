// 
// Decompiled by Procyon v0.5.30
// 

package parser;

public class Param
{
    private String name;
    private double value;
    
    public Param(final String name, final double value) {
        this.name = name;
        this.value = value;
    }
    
    public String name() {
        return this.name;
    }
    
    public double value() {
        return this.value;
    }
}

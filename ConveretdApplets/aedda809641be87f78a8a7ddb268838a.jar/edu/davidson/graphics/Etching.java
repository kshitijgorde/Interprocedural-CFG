// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

public class Etching
{
    public static final Etching OUT;
    public static final Etching IN;
    
    public String toString() {
        if (this == Etching.OUT) {
            return String.valueOf(String.valueOf(this.getClass().getName())).concat("=OUT");
        }
        return String.valueOf(String.valueOf(this.getClass().getName())).concat("=IN");
    }
    
    static {
        OUT = new Etching();
        IN = new Etching();
    }
}

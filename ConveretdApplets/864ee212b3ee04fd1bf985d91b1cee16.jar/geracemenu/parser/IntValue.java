// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class IntValue extends Type
{
    public IntValue() {
        this(0);
    }
    
    public IntValue(final int n) {
        this(new Integer(n));
    }
    
    public IntValue(final Integer n) {
        super(n);
    }
}

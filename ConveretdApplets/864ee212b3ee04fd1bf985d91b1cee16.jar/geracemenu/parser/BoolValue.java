// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class BoolValue extends Type
{
    public BoolValue() {
        this(false);
    }
    
    public BoolValue(final boolean b) {
        this(new Boolean(b));
    }
    
    public BoolValue(final Boolean b) {
        super(b);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public abstract class ArrayField extends Field
{
    public ArrayField(final Node node, final String s, final int n) {
        super(node, s, n);
    }
    
    public abstract int getLength();
}

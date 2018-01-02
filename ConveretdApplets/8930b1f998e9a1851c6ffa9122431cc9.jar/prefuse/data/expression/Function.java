// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

public interface Function extends Expression
{
    public static final int VARARGS = -1;
    
    String getName();
    
    void addParameter(final Expression p0);
    
    int getParameterCount();
}

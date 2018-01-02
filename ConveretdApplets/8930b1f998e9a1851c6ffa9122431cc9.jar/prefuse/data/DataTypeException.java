// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

public class DataTypeException extends RuntimeException
{
    public DataTypeException() {
    }
    
    public DataTypeException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public DataTypeException(final String s) {
        super(s);
    }
    
    public DataTypeException(final Throwable t) {
        super(t);
    }
    
    public DataTypeException(final Class clazz) {
        super("Type " + clazz.getName() + " not supported.");
    }
}

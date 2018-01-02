// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Collection implements Cloneable
{
    public abstract Object butFirst() throws TTRuntimeError;
    
    public abstract Object butLast() throws TTRuntimeError;
    
    public abstract Integer count();
    
    public abstract Boolean emptyp();
    
    public abstract Object first() throws TTRuntimeError;
    
    public abstract Boolean isArray();
    
    public abstract Boolean isList();
    
    public abstract Boolean isWord();
    
    public abstract Object item(final int p0) throws TTRuntimeError;
    
    public abstract Object last() throws TTRuntimeError;
    
    public abstract Boolean memberp(final Object p0);
    
    public abstract String toString();
}

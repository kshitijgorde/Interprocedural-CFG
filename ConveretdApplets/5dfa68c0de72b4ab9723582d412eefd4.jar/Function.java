// 
// Decompiled by Procyon v0.5.30
// 

class Function
{
    Primitives instance;
    int dispatchOffset;
    int nargs;
    boolean ipm;
    
    Function(final Primitives primitives, final int n, final int n2) {
        this(primitives, n, n2, false);
    }
    
    Function(final Primitives instance, final int nargs, final int dispatchOffset, final boolean ipm) {
        this.instance = instance;
        this.nargs = nargs;
        this.dispatchOffset = dispatchOffset;
        this.ipm = ipm;
    }
}

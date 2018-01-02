// 
// Decompiled by Procyon v0.5.30
// 

class OpAmpSwapElm extends OpAmpElm
{
    public OpAmpSwapElm(final int n, final int n2) {
        super(n, n2);
        this.flags |= 0x1;
    }
    
    Class getDumpClass() {
        return OpAmpElm.class;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

class NMosfetElm extends MosfetElm
{
    public NMosfetElm(final int n, final int n2) {
        super(n, n2, false);
    }
    
    Class getDumpClass() {
        return MosfetElm.class;
    }
}

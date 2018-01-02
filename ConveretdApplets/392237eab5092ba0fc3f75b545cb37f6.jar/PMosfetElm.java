// 
// Decompiled by Procyon v0.5.30
// 

class PMosfetElm extends MosfetElm
{
    public PMosfetElm(final int n, final int n2) {
        super(n, n2, true);
    }
    
    Class getDumpClass() {
        return MosfetElm.class;
    }
}

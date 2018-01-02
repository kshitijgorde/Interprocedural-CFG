// 
// Decompiled by Procyon v0.5.30
// 

class NTransistorElm extends TransistorElm
{
    public NTransistorElm(final int n, final int n2) {
        super(n, n2, false);
    }
    
    Class getDumpClass() {
        return TransistorElm.class;
    }
}

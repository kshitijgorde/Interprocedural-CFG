// 
// Decompiled by Procyon v0.5.30
// 

class PTransistorElm extends TransistorElm
{
    public PTransistorElm(final int n, final int n2) {
        super(n, n2, true);
    }
    
    Class getDumpClass() {
        return TransistorElm.class;
    }
}

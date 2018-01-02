// 
// Decompiled by Procyon v0.5.30
// 

class ACVoltageElm extends VoltageElm
{
    public ACVoltageElm(final int n, final int n2) {
        super(n, n2, 1);
    }
    
    Class getDumpClass() {
        return VoltageElm.class;
    }
}

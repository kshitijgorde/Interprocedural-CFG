// 
// Decompiled by Procyon v0.5.30
// 

class DCVoltageElm extends VoltageElm
{
    public DCVoltageElm(final int n, final int n2) {
        super(n, n2, 0);
    }
    
    Class getDumpClass() {
        return VoltageElm.class;
    }
}

import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class NandGateElm extends AndGateElm
{
    public NandGateElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public NandGateElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    boolean isInverting() {
        return true;
    }
    
    String getGateName() {
        return "NAND gate";
    }
    
    int getDumpType() {
        return 151;
    }
}

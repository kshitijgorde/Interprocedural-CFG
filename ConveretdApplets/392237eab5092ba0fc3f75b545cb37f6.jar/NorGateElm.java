import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class NorGateElm extends OrGateElm
{
    public NorGateElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public NorGateElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getGateName() {
        return "NOR gate";
    }
    
    boolean isInverting() {
        return true;
    }
    
    int getDumpType() {
        return 153;
    }
}

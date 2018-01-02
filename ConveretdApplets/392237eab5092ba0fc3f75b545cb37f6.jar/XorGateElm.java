import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class XorGateElm extends OrGateElm
{
    public XorGateElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public XorGateElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getGateName() {
        return "XOR gate";
    }
    
    boolean calcFunction() {
        boolean b = false;
        for (int i = 0; i != this.inputCount; ++i) {
            b ^= this.getInput(i);
        }
        return b;
    }
    
    int getDumpType() {
        return 154;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.Enumeration;

public class InversionNode extends ScriptNode
{
    public InversionNode() {
    }
    
    public InversionNode(final int n, final int n2) {
        super(n, n2);
    }
    
    public int getSymbol() {
        return 117;
    }
    
    public Enumeration resolvedEnumeration(final boolean b) {
        return super.resolvedEnumeration(!b);
    }
}

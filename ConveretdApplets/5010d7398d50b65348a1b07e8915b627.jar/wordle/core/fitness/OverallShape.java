// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

public enum OverallShape
{
    a("SQUARISH", 0), 
    b("BLOBBY", 1);
    
    static {
        c = new OverallShape[] { OverallShape.a, OverallShape.b };
    }
    
    private OverallShape(final String s, final int n) {
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package imaging.filters;

public class AverageFilter extends ConvolveFilter
{
    protected static float[] theMatrix;
    
    static {
        AverageFilter.theMatrix = new float[] { 0.05f, 0.1f, 0.05f, 0.1f, 0.2f, 0.1f, 0.05f, 0.1f, 0.05f };
    }
    
    public AverageFilter() {
        super(AverageFilter.theMatrix);
    }
    
    @Override
    public String toString() {
        return "Blur/Average Blur";
    }
}

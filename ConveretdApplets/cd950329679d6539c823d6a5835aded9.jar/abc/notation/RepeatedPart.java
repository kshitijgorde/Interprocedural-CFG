// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public class RepeatedPart extends RepeatedPartAbstract
{
    private Part m_part;
    
    public RepeatedPart(final Part part) {
        this.m_part = null;
        this.m_part = part;
    }
    
    public Part[] toPartsArray() {
        final int repeatNumber = this.getNumberOfRepeats();
        final Part[] parts = new Part[repeatNumber];
        for (int i = 0; i < repeatNumber; ++i) {
            parts[i] = this.m_part;
        }
        return parts;
    }
}

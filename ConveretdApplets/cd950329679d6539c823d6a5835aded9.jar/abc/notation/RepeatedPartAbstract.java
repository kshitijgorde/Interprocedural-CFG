// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

public abstract class RepeatedPartAbstract
{
    private int m_repeatNumber;
    
    public RepeatedPartAbstract() {
        this.m_repeatNumber = 1;
    }
    
    public int getNumberOfRepeats() {
        return this.m_repeatNumber;
    }
    
    public void setNumberOfRepeats(final int repeatNumber) {
        this.m_repeatNumber = repeatNumber;
    }
    
    public abstract Part[] toPartsArray();
}

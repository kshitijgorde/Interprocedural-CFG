// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import java.util.Vector;

public class CircularBuffer extends Vector
{
    private int m_sizeLimite;
    
    public CircularBuffer(final int sizeLimit) {
        this.m_sizeLimite = 0;
        this.m_sizeLimite = sizeLimit;
    }
    
    public void setSizeLimit(final int sizeLimit) {
        this.m_sizeLimite = sizeLimit;
    }
    
    public void addElement(final Object o) {
        if (this.size() >= this.m_sizeLimite) {
            this.removeElementAt(0);
        }
        super.addElement(o);
    }
}

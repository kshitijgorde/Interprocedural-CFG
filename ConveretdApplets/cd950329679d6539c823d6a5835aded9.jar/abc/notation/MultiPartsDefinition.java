// 
// Decompiled by Procyon v0.5.30
// 

package abc.notation;

import java.util.Vector;

public class MultiPartsDefinition extends RepeatedPartAbstract implements Cloneable
{
    private Vector m_parts;
    
    public MultiPartsDefinition() {
        this.m_parts = new Vector();
        new Vector();
    }
    
    public MultiPartsDefinition(final MultiPartsDefinition root) {
        this.m_parts = new Vector();
        this.m_parts = (Vector)root.m_parts.clone();
    }
    
    public void addPart(final RepeatedPartAbstract p) {
        this.m_parts.addElement(p);
    }
    
    public Part[] toPartsArray() {
        final Vector parts = this.getPartsAsRepeatedOnceVector();
        final Part[] partsArray = new Part[parts.size()];
        for (int i = 0; i < parts.size(); ++i) {
            partsArray[i] = parts.elementAt(i);
        }
        return partsArray;
    }
    
    public Vector getPartsAsRepeatedOnceVector() {
        final Vector parts = new Vector();
        RepeatedPartAbstract c = null;
        for (int i = 0; i < this.getNumberOfRepeats(); ++i) {
            for (int j = 0; j < this.m_parts.size(); ++j) {
                c = this.m_parts.elementAt(j);
                final Part[] p = c.toPartsArray();
                for (int k = 0; k < p.length; ++k) {
                    parts.addElement(p[k]);
                }
            }
        }
        return parts;
    }
    
    public Object clone() {
        return new MultiPartsDefinition(this);
    }
}

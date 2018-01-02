// 
// Decompiled by Procyon v0.5.30
// 

package XMLConsumer;

import java.util.Vector;

class FtsKeyInt
{
    private String m_sName;
    private Vector m_vTopics;
    
    public FtsKeyInt(final String sName) {
        this.m_sName = sName;
    }
    
    public Vector getTopics() {
        return this.m_vTopics;
    }
    
    public void addTopic(final int n) {
        if (this.m_vTopics == null) {
            this.m_vTopics = new Vector();
        }
        this.m_vTopics.addElement(new Integer(n));
    }
    
    public String getName() {
        return this.m_sName;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public class MixPosition
{
    private int m_position;
    private String m_MixId;
    
    public MixPosition(final int position, final String mixId) {
        this.m_position = position;
        this.m_MixId = mixId;
    }
    
    public int getPosition() {
        return this.m_position;
    }
    
    public String getId() {
        return this.m_MixId;
    }
    
    public String toString() {
        return this.m_MixId;
    }
    
    public boolean equals(final Object o) {
        return o != null && o instanceof MixPosition && (this == o || this.getId().equals(((MixPosition)o).getId()));
    }
    
    public int hashCode() {
        return this.m_MixId.hashCode();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

public final class NVPair
{
    private String name;
    private String value;
    private boolean m_quoteValue;
    
    NVPair() {
        this("", "");
    }
    
    public NVPair(final NVPair nvPair) {
        this(nvPair.name, nvPair.value);
    }
    
    public NVPair(final String s, final String s2) {
        this(s, s2, true);
    }
    
    public NVPair(final String name, final String value, final boolean quoteValue) {
        this.name = name;
        this.value = value;
        this.m_quoteValue = quoteValue;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final String getValue() {
        return this.value;
    }
    
    public boolean quoteValue() {
        return this.m_quoteValue;
    }
    
    public String toString() {
        return this.getClass().getName() + "[name=" + this.name + ",value=" + this.value + "]";
    }
}

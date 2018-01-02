// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.address;

public class AddressParameter
{
    private String m_name;
    private String m_value;
    
    public AddressParameter(final String name, final String value) {
        this.m_name = name;
        this.m_value = value;
    }
    
    public AddressParameter(final String s) {
        this(s, "");
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public String getValue() {
        return this.m_value;
    }
    
    public int hashCode() {
        return this.m_name.hashCode();
    }
}

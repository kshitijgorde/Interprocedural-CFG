// 
// Decompiled by Procyon v0.5.30
// 

package modules;

public interface Module
{
    void connect(final String p0, final int p1);
    
    void disconnect();
    
    String receive(final String p0);
    
    void setLoader(final Object p0);
}

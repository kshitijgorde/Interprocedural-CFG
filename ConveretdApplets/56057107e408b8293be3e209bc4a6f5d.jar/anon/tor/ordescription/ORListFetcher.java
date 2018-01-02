// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.ordescription;

public interface ORListFetcher
{
    byte[] getRouterStatus();
    
    byte[] getDescriptor(final String p0);
    
    byte[] getDescriptorByFingerprint(final String p0);
    
    byte[] getAllDescriptors();
    
    byte[] getStatus(final String p0);
}

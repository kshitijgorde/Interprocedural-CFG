// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.ordescription;

import anon.infoservice.InfoServiceHolder;

public final class InfoServiceORListFetcher implements ORListFetcher
{
    public byte[] getORList() {
        return InfoServiceHolder.getInstance().getTorNodesList();
    }
    
    public byte[] getAllDescriptors() {
        return InfoServiceHolder.getInstance().getTorNodesList();
    }
    
    public byte[] getDescriptor(final String s) {
        return null;
    }
    
    public byte[] getStatus(final String s) {
        return null;
    }
    
    public byte[] getDescriptorByFingerprint(final String s) {
        return null;
    }
    
    public byte[] getRouterStatus() {
        return null;
    }
}

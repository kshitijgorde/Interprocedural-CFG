// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.ordescription;

import HTTPClient.HTTPResponse;
import anon.infoservice.ListenerInterface;
import anon.infoservice.HTTPConnectionFactory;
import logging.LogHolder;
import logging.LogType;

public final class PlainORListFetcher implements ORListFetcher
{
    private String m_ORListServer;
    private int m_ORListPort;
    
    public PlainORListFetcher(final String orListServer, final int orListPort) {
        this.m_ORListServer = orListServer;
        this.m_ORListPort = orListPort;
    }
    
    public byte[] getRouterStatus() {
        return this.getDocument("/tor/status/authority.z");
    }
    
    public byte[] getDescriptor(final String s) {
        return this.getDocument("/tor/server/d/" + s + ".z");
    }
    
    public byte[] getDescriptorByFingerprint(final String s) {
        return this.getDocument("/tor/server/fp/" + s + ".z");
    }
    
    public byte[] getAllDescriptors() {
        return this.getDocument("/tor/server/all.z");
    }
    
    public byte[] getStatus(final String s) {
        return this.getDocument("/tor/status/fp/" + s + ".z");
    }
    
    private byte[] getDocument(final String s) {
        try {
            LogHolder.log(7, LogType.TOR, "fetching " + s + " from directory server");
            final HTTPResponse get = HTTPConnectionFactory.getInstance().createHTTPConnection(new ListenerInterface(this.m_ORListServer, this.m_ORListPort), 1, true).Get(s);
            if (get.getStatusCode() != 200) {
                return null;
            }
            final byte[] data = get.getData();
            if (data.length <= 0) {
                return null;
            }
            return data;
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.TOR, "error while fetching " + s + " from directory server: " + t.getMessage());
            return null;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package anon.xmlrpc.server;

import anon.AnonChannel;
import java.util.Hashtable;

class ClientEntry
{
    private int m_id;
    private Hashtable m_hashtableChannels;
    
    ClientEntry(final int id) {
        this.m_id = id;
        this.m_hashtableChannels = new Hashtable();
    }
    
    public int hashCode() {
        return this.m_id;
    }
    
    public void addChannel(final AnonChannel anonChannel) {
        this.m_hashtableChannels.put(new Integer(anonChannel.hashCode()), anonChannel);
    }
    
    public AnonChannel getChannel(final Integer n) {
        return this.m_hashtableChannels.get(n);
    }
}

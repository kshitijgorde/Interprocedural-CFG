// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.util;

import anon.infoservice.AbstractDatabaseEntry;

public class DNSCacheEntry extends AbstractDatabaseEntry
{
    private String m_Id;
    private String m_Ip;
    private long m_lastUpdate;
    
    public DNSCacheEntry(final String id, final String ip, final long n) {
        super(n);
        this.m_lastUpdate = System.currentTimeMillis();
        this.m_Id = id;
        this.m_Ip = ip;
    }
    
    public String getId() {
        return this.m_Id;
    }
    
    public String getIp() {
        return this.m_Ip;
    }
    
    public long getVersionNumber() {
        return this.getExpireTime();
    }
    
    public long getLastUpdate() {
        return this.m_lastUpdate;
    }
}

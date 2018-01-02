// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.mmrdescription;

import HTTPClient.HTTPResponse;
import HTTPClient.HTTPConnection;
import logging.LogHolder;
import logging.LogType;

public class PlainMMRListFetcher implements MMRListFetcher
{
    private String m_MMRListServer;
    private int m_MMRListPort;
    
    public PlainMMRListFetcher() {
        this.m_MMRListServer = "mixminion.net";
        this.m_MMRListPort = 80;
    }
    
    public byte[] getMMRList() {
        try {
            LogHolder.log(7, LogType.MISC, "[UPDATE OR-LIST] Starting update on " + this.m_MMRListServer + ":" + this.m_MMRListPort);
            final HTTPResponse get = new HTTPConnection(this.m_MMRListServer, this.m_MMRListPort).Get("/directory/Directory.gz");
            if (get.getStatusCode() != 200) {
                return null;
            }
            final byte[] data = get.getData();
            LogHolder.log(7, LogType.MISC, "[UPDATE OR-LIST] Update finished");
            return data;
        }
        catch (Throwable t) {
            LogHolder.log(7, LogType.MISC, "There was a problem with fetching the available MMRRouters: " + t.getMessage());
            return null;
        }
    }
}

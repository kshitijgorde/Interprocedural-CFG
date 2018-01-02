// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import logging.LogHolder;
import logging.LogType;
import anon.tor.ordescription.ORDescriptor;
import java.util.Vector;

public class FirstOnionRouterConnectionFactory
{
    private Vector m_firstOnionRouters;
    private Tor m_Tor;
    
    public FirstOnionRouterConnectionFactory(final Tor tor) {
        this.m_firstOnionRouters = new Vector();
        this.m_Tor = tor;
    }
    
    public synchronized FirstOnionRouterConnection createFirstOnionRouterConnection(final ORDescriptor orDescriptor) {
        FirstOnionRouterConnection firstOnionRouterConnection = null;
        int i = 0;
        while (i < this.m_firstOnionRouters.size()) {
            firstOnionRouterConnection = (FirstOnionRouterConnection)this.m_firstOnionRouters.elementAt(i);
            if (firstOnionRouterConnection.getORDescription().isSimilar(orDescriptor)) {
                if (!firstOnionRouterConnection.isClosed()) {
                    return firstOnionRouterConnection;
                }
                break;
            }
            else {
                firstOnionRouterConnection = null;
                ++i;
            }
        }
        if (firstOnionRouterConnection == null) {
            firstOnionRouterConnection = new FirstOnionRouterConnection(orDescriptor, this.m_Tor);
        }
        try {
            firstOnionRouterConnection.connect();
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.TOR, "Error while connection to first OnionRouter");
            LogHolder.log(2, LogType.TOR, ex);
            return null;
        }
        this.m_firstOnionRouters.addElement(firstOnionRouterConnection);
        return firstOnionRouterConnection;
    }
    
    public synchronized void closeAll() {
        for (int i = 0; i < this.m_firstOnionRouters.size(); ++i) {
            ((FirstOnionRouterConnection)this.m_firstOnionRouters.elementAt(i)).close();
        }
        this.m_firstOnionRouters.removeAllElements();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion;

import anon.AnonServerDescription;

public class MixminionServiceDescription implements AnonServerDescription
{
    private int m_iRouteLen;
    private String m_myEmail;
    
    public MixminionServiceDescription(final int routeLen, final String myEmail) {
        this.setRouteLen(routeLen);
        this.m_myEmail = myEmail;
    }
    
    public int getRouteLen() {
        return this.m_iRouteLen;
    }
    
    public void setRouteLen(final int iRouteLen) {
        if (iRouteLen >= 2 && iRouteLen <= 10) {
            this.m_iRouteLen = iRouteLen;
        }
    }
    
    public String getMyEmail() {
        return this.m_myEmail;
    }
}

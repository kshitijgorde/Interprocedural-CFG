// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion;

import anon.AnonServiceEventListener;
import java.net.ConnectException;
import anon.AnonChannel;
import anon.terms.TermsAndConditionConfirmation;
import anon.IServiceContainer;
import anon.AnonServerDescription;
import anon.infoservice.IMutableProxyInterface;
import anon.AnonService;

public class Mixminion implements AnonService
{
    private static MixminionServiceDescription m_serviceDescription;
    public static final int MAX_ROUTE_LEN = 10;
    public static final int MIN_ROUTE_LEN = 2;
    private static Mixminion ms_theMixminionInstance;
    private IMutableProxyInterface m_proxyInterface;
    
    public int initialize(final AnonServerDescription anonServerDescription, final IServiceContainer serviceContainer, final TermsAndConditionConfirmation termsAndConditionConfirmation) {
        Mixminion.m_serviceDescription = (MixminionServiceDescription)anonServerDescription;
        return 0;
    }
    
    public void setRouteLen(final int routeLen) {
        if (routeLen >= 2 && routeLen <= 10) {
            Mixminion.m_serviceDescription.setRouteLen(routeLen);
        }
    }
    
    public static int getRouteLen() {
        return Mixminion.m_serviceDescription.getRouteLen();
    }
    
    public static String getMyEMail() {
        return Mixminion.m_serviceDescription.getMyEmail();
    }
    
    public int setProxy(final IMutableProxyInterface proxyInterface) {
        this.m_proxyInterface = proxyInterface;
        return 0;
    }
    
    public IMutableProxyInterface getProxy() {
        return this.m_proxyInterface;
    }
    
    public void shutdown(final boolean b) {
    }
    
    public boolean isConnected() {
        return false;
    }
    
    public AnonChannel createChannel(final int n) throws ConnectException {
        if (n != 2) {
            return null;
        }
        try {
            return new MixminionSMTPChannel();
        }
        catch (Exception ex) {
            throw new ConnectException("Could not create a Mixminion-Channel: " + ex.getMessage());
        }
    }
    
    public AnonChannel createChannel(final String s, final int n) throws ConnectException {
        return null;
    }
    
    public void addEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public void removeEventListener(final AnonServiceEventListener anonServiceEventListener) {
    }
    
    public void removeEventListeners() {
    }
    
    public static Mixminion getInstance() {
        if (Mixminion.ms_theMixminionInstance == null) {
            Mixminion.ms_theMixminionInstance = new Mixminion();
        }
        return Mixminion.ms_theMixminionInstance;
    }
    
    static {
        Mixminion.ms_theMixminionInstance = null;
    }
}

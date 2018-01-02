// 
// Decompiled by Procyon v0.5.30
// 

package anon;

import anon.xmlrpc.client.AnonServiceImplProxy;
import anon.mixminion.Mixminion;
import anon.tor.Tor;
import anon.client.AnonClient;

public final class AnonServiceFactory
{
    public static final String SERVICE_ANON = "AN.ON";
    public static final String SERVICE_TOR = "TOR";
    public static final String SERVICE_MIXMINION = "Mixminion";
    private static AnonService ms_AnonService;
    
    public static AnonService getAnonServiceInstance(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equals("AN.ON")) {
            if (AnonServiceFactory.ms_AnonService == null) {
                AnonServiceFactory.ms_AnonService = new AnonClient();
            }
            return AnonServiceFactory.ms_AnonService;
        }
        if (s.equals("TOR")) {
            return Tor.getInstance();
        }
        if (s.equals("Mixminion")) {
            return Mixminion.getInstance();
        }
        return null;
    }
    
    public static AnonService create(final String s, final int n) {
        try {
            return new AnonServiceImplProxy(s, n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    static {
        AnonServiceFactory.ms_AnonService = null;
    }
}

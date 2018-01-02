// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import com.pchat.sc.ServicePack;

public interface AdmInter
{
    void setPara(final AppletSpice p0);
    
    void startUp();
    
    boolean isShowing();
    
    void clearAll();
    
    void serverMsg(final ServicePack p0);
}

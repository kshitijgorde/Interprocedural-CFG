// 
// Decompiled by Procyon v0.5.30
// 

package pclient.ady;

import com.pchat.sc.MsgOptions;
import java.util.Hashtable;
import pclient.adv.AppletSpice;
import pclient.adv.PrivInter;

public class PcManager implements PrivInter
{
    protected AppletSpice paraApplet;
    protected boolean isBeep;
    private Hashtable thirdParties;
    
    public PcManager() {
        this.isBeep = true;
    }
    
    public void setPara(final AppletSpice appletSpice) {
    }
    
    public void checkTyping() {
    }
    
    public void setBeep(final boolean b) {
    }
    
    public void receivedPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
    }
    
    public void receivedSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
    }
    
    public void receivedPrivSound(final String s, final String s2, final String s3) {
    }
    
    public void initPrivate(final String s, final String s2, final String s3) {
    }
    
    public void initAv(final String s, final String s2, final String s3) {
    }
    
    public void receivedAvReq(final String s, final String s2, final String s3) {
    }
    
    public void receivedAvReject(final String s, final String s2) {
    }
    
    public void receivedAvWeb(final String s, final String s2, final String s3) {
    }
    
    public void showTyping(final String s) {
    }
    
    public void joinLeave(final String s, final boolean b) {
    }
    
    public void info(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
    }
    
    public void warnOnce(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
    }
    
    public void broadcast(final String s, final MsgOptions msgOptions) {
    }
    
    public void clearAll() {
    }
}

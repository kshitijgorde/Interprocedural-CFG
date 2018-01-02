// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import pclient.shd.ClientUtil;

public class ReconnectChat implements Runnable
{
    private AppletSpice appletChat;
    private boolean attemptReconnect;
    private boolean hasDisconnected;
    private boolean hadSuccessEver;
    private boolean keepGoing;
    private long lastTime;
    private long frequencyMinutes;
    private ReconnectList historyList;
    
    public ReconnectChat(final AppletSpice appletChat) {
        this.attemptReconnect = true;
        this.hasDisconnected = false;
        this.hadSuccessEver = false;
        this.keepGoing = true;
        this.lastTime = 0L;
        this.frequencyMinutes = 5L;
        this.appletChat = appletChat;
        this.hadSuccessEver = false;
        this.hasDisconnected = false;
        this.attemptReconnect = true;
        this.keepGoing = true;
        this.lastTime = 0L;
        this.frequencyMinutes = this.appletChat.paraConf.getInt("Val.Rec.Frq", 5);
        this.historyList = new ReconnectList(appletChat);
        new Thread(this).start();
    }
    
    public void clearOnGoodJoin() {
    }
    
    public void hadSuccess() {
        this.hadSuccessEver = true;
    }
    
    public void setState(final boolean b) {
        this.hasDisconnected = !b;
    }
    
    public void setHint(final boolean attemptReconnect) {
        this.attemptReconnect = attemptReconnect;
    }
    
    public void stopIt() {
        this.keepGoing = false;
        this.historyList.setVisible(false);
        this.historyList.cancelClicked = true;
    }
    
    public void run() {
        while (this.keepGoing) {
            if (this.appletChat.globalChoice.reconnect) {
                this.tryReconnect();
            }
            ClientUtil.doze(5000);
        }
    }
    
    private boolean isTrying() {
        return this.hadSuccessEver && !this.appletChat.chatModel.cmIsConnected() && this.attemptReconnect && this.hasDisconnected;
    }
    
    private void tryReconnect() {
        if (!this.isTrying()) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastTime < this.frequencyMinutes * 60L * 1000L) {
            return;
        }
        this.lastTime = currentTimeMillis;
        this.historyList.tryReconnect();
    }
}

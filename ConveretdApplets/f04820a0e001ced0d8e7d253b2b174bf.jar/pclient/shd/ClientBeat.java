// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.io.IOException;

public class ClientBeat extends Thread
{
    private SessionEnclosure mainSession;
    private long lastWrite;
    private boolean keepGoing;
    
    public ClientBeat(final SessionEnclosure mainSession) {
        this.mainSession = mainSession;
        this.lastWrite = System.currentTimeMillis();
    }
    
    public void stopIt() {
        this.keepGoing = false;
    }
    
    public void run() {
        this.keepGoing = true;
        while (this.keepGoing) {
            try {
                this.checkHeart();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
            ClientUtil.doze(9000);
        }
    }
    
    private void checkHeart() throws IOException {
        if (!this.mainSession.heartBeat()) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastWrite < this.mainSession.heartSeconds() * 1000L) {
            return;
        }
        this.lastWrite = currentTimeMillis;
        this.mainSession.cmSend(this.mainSession.getHeart());
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

public class SessionStatus
{
    public static final int ST_ERROR = 0;
    public static final int ST_DISCONNECTED = 1;
    public static final int ST_LOG_PENDING = 2;
    public static final int ST_LOG_GOOD = 3;
    public static final int ST_JOIN_PENDING = 4;
    public static final int ST_JOIN_GOOD = 5;
    public static final int ST_SWITCH_PENDING = 7;
    public int currentState;
    private SessionEnclosure chatSession;
    
    public SessionStatus(final SessionEnclosure chatSession) {
        this.currentState = 1;
        this.chatSession = chatSession;
    }
    
    public boolean isError() {
        return this.currentState == 0;
    }
    
    public void setError() {
        this.currentState = 0;
    }
    
    public int getState() {
        return this.currentState;
    }
    
    public void setState(final int currentState) {
        this.chatSession.paraConf.printer().print("set state:" + currentState);
        this.currentState = currentState;
    }
}

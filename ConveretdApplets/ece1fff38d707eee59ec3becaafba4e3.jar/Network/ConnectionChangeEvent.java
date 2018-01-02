// 
// Decompiled by Procyon v0.5.30
// 

package Network;

public class ConnectionChangeEvent
{
    private boolean connected;
    
    public boolean getConnected() {
        return this.connected;
    }
    
    public ConnectionChangeEvent(final Object source, final boolean c) {
        this.connected = c;
    }
}

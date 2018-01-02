// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.util.Vector;

public class ConnectionNotifier
{
    private boolean connected;
    private Vector connectionChangeListeners;
    
    public ConnectionNotifier() {
        this.connected = false;
        this.connectionChangeListeners = new Vector();
    }
    
    public synchronized void addConnectionChangeListener(final ConnectionChangeListener l) {
        if (!this.connectionChangeListeners.contains(l)) {
            this.connectionChangeListeners.addElement(l);
        }
    }
    
    public void setConnection(final boolean c) {
        this.connected = c;
        this.notifyConnectionChange();
    }
    
    public boolean getConnection() {
        return this.connected;
    }
    
    protected void notifyConnectionChange() {
        final ConnectionChangeEvent evt = new ConnectionChangeEvent(this, this.connected);
        final Vector v;
        synchronized (this) {
            v = (Vector)this.connectionChangeListeners.clone();
        }
        for (int cnt = v.size(), i = 0; i < cnt; ++i) {
            final ConnectionChangeListener client = v.elementAt(i);
            client.connectionChanged(evt);
        }
    }
    
    public synchronized void removeConnectionChangeListeners(final ConnectionChangeListener l) {
        if (this.connectionChangeListeners.contains(l)) {
            this.connectionChangeListeners.removeElement(l);
        }
    }
}

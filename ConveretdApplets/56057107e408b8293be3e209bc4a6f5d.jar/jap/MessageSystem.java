// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Observable;

public class MessageSystem extends Observable
{
    public void sendMessage() {
        synchronized (this) {
            this.setChanged();
            this.notifyObservers();
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.AWTEvent;
import java.util.Vector;

public class EventManager
{
    protected Vector objectsToNotify;
    
    public EventManager() {
        this.objectsToNotify = new Vector();
    }
    
    public void registerForEventNotification(final Object o, final int[] array) {
        for (int i = 0; i < array.length; ++i) {
            final Vector<Integer> vector = new Vector<Integer>(2);
            final int n = array[i];
            vector.addElement((Integer)o);
            vector.addElement(new Integer(n));
            this.objectsToNotify.addElement(vector);
        }
    }
    
    public void registerForSingleEventNotification(final Object o, final int n) {
        final Vector<Integer> vector = new Vector<Integer>(2);
        vector.addElement((Integer)o);
        vector.addElement(new Integer(n));
        this.objectsToNotify.addElement(vector);
    }
    
    public void removeFromNotificationRegistry(final Object o) {
        for (int i = 0; i < this.objectsToNotify.size(); ++i) {
            if (((Vector)this.objectsToNotify.elementAt(i)).contains(o)) {
                this.objectsToNotify.removeElementAt(i);
            }
        }
    }
    
    public boolean handleEvent(final AWTEvent awtEvent) {
        boolean handleRequestedEvent = false;
        for (int i = 0; i < this.objectsToNotify.size(); ++i) {
            final Vector<Integer> vector = this.objectsToNotify.elementAt(i);
            if (awtEvent.getID() == vector.elementAt(1)) {
                handleRequestedEvent = ((EventHandler)vector.elementAt(0)).handleRequestedEvent(awtEvent);
            }
        }
        return handleRequestedEvent;
    }
}

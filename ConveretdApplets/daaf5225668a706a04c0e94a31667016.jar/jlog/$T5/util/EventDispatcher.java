// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.util;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Enumeration;
import java.util.Vector;

public abstract class EventDispatcher
{
    private boolean $DDB;
    private Vector l;
    public Object source;
    
    public synchronized Enumeration $JOD() {
        return this.l.elements();
    }
    
    public synchronized void $SSB(final EventObject eventObject) {
        if (!this.$DDB) {
            return;
        }
        final Enumeration $jod = this.$JOD();
        while ($jod.hasMoreElements()) {
            this.$SSB(eventObject, $jod.nextElement());
        }
    }
    
    public abstract void $SSB(final EventObject p0, final EventListener p1);
    
    public EventDispatcher(final Object source) {
        this.$DDB = true;
        this.l = new Vector();
        this.setSource(source);
    }
    
    public synchronized void addListener(final EventListener eventListener) {
        this.l.addElement(eventListener);
    }
    
    public synchronized boolean getEnabled(final boolean b) {
        return b;
    }
    
    public synchronized void removeListener(final EventListener eventListener) {
        this.l.removeElement(eventListener);
    }
    
    public synchronized void setEnabled(final boolean $ddb) {
        this.$DDB = $ddb;
    }
    
    public synchronized void setSource(final Object source) {
        this.source = source;
    }
}

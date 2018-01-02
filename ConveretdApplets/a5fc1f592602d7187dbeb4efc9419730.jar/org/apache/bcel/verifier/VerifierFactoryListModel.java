// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier;

import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;
import java.util.TreeSet;
import java.util.ArrayList;
import javax.swing.ListModel;

public class VerifierFactoryListModel implements VerifierFactoryObserver, ListModel
{
    private ArrayList listeners;
    private TreeSet cache;
    
    public VerifierFactoryListModel() {
        this.listeners = new ArrayList();
        this.cache = new TreeSet();
        VerifierFactory.attach(this);
        this.update(null);
    }
    
    public synchronized void update(final String s) {
        final int size = this.listeners.size();
        final Verifier[] verifiers = VerifierFactory.getVerifiers();
        final int num_of_verifiers = verifiers.length;
        this.cache.clear();
        for (int i = 0; i < num_of_verifiers; ++i) {
            this.cache.add(verifiers[i].getClassName());
        }
        for (int j = 0; j < size; ++j) {
            final ListDataEvent e = new ListDataEvent(this, 0, 0, num_of_verifiers - 1);
            this.listeners.get(j).contentsChanged(e);
        }
    }
    
    public synchronized void addListDataListener(final ListDataListener l) {
        this.listeners.add(l);
    }
    
    public synchronized void removeListDataListener(final ListDataListener l) {
        this.listeners.remove(l);
    }
    
    public synchronized int getSize() {
        return this.cache.size();
    }
    
    public synchronized Object getElementAt(final int index) {
        return this.cache.toArray()[index];
    }
}

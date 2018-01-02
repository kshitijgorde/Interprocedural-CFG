// 
// Decompiled by Procyon v0.5.30
// 

package com.stackframe.pathfinder;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPathFinder<T extends Node> implements PathFinder<T>
{
    protected final List<PathListener<T>> listeners;
    protected volatile boolean canceled;
    
    public AbstractPathFinder() {
        this.listeners = new ArrayList<PathListener<T>>();
    }
    
    public void cancel() {
        this.canceled = true;
    }
    
    protected void fireConsidered(final PathEvent<T> pathEvent) {
        for (final PathListener<T> listener : this.listeners) {
            listener.considered(pathEvent);
        }
    }
    
    public void addPathListener(final PathListener<T> l) {
        this.listeners.add(l);
    }
    
    public void removePathListener(final PathListener<T> l) {
        this.listeners.remove(l);
    }
}
